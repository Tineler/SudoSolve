package ch.sudosolve.swing.view;

import ch.sudosolve.core.model.Sudoku;
import ch.sudosolve.swing.controller.SudokuHelper;
import ch.sudosolve.swing.controller.SudokuSolverThread;
import ch.sudosolve.swing.controller.UserInterfaceUpdaterThread;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class MainWindow {

	private JFrame frmSudosolve;
	private JFormattedTextField[][] boardVisualization;
	private JProgressBar progressBar;
	private JButton btnSolve;
	private JButton btnClear;

	private SudokuSolverThread solver;
	private static MainWindow instance;

	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}

	/**
	 * Shows the window.
	 */
	public void show() {
		frmSudosolve.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	private MainWindow() {
		boardVisualization = new JFormattedTextField[9][9];
		initializeUI();
		instance = this;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeUI() {
		initFrame();
		initButtons();
		initProgressBar();
		initSeparators();
		initFields();
        initFocusListener();
	}

    private void initFrame() {
		frmSudosolve = new JFrame();
		frmSudosolve.setResizable(false);
		frmSudosolve.setTitle("SudoSolve - The backtracking Sudoku Solver");
		frmSudosolve.setBounds(100, 100, 380, 385);
		frmSudosolve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSudosolve.getContentPane().setLayout(null);
	}

	private void initProgressBar() {
		progressBar = new JProgressBar();
		progressBar.setBounds(6, 337, 368, 20);
		frmSudosolve.getContentPane().add(progressBar);
	}

	private void initSeparators() {
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(6, 92, 365, 12);
		frmSudosolve.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(6, 185, 365, 12);
		frmSudosolve.getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(122, 6, 12, 276);
		frmSudosolve.getContentPane().add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(245, 6, 12, 276);
		frmSudosolve.getContentPane().add(separator_3);
	}

	private void initButtons() {
		btnSolve = new JButton("Solve");
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (solver != null && !solver.isShutdown()) {
					solver.shutdown();
				} else {
					Sudoku sudoku = SudokuHelper.convertBoardVisualizationToSudoku(boardVisualization);
					solver = new SudokuSolverThread(sudoku);
					UserInterfaceUpdaterThread interfaceUpdaterThread = new UserInterfaceUpdaterThread(solver);
					solver.start();
					interfaceUpdaterThread.start();
				}
			}
		});
		btnSolve.setBounds(257, 296, 117, 29);
		frmSudosolve.getContentPane().add(btnSolve);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// reset all field values to 0
				for (int row = 0; row < 9; row++) {
					for (int column = 0; column < 9; column++) {
                        JFormattedTextField field = boardVisualization[row][column];
                        field.setValue(0);
					}
				}
			}
		});
		btnClear.setBounds(128, 296, 117, 29);
		frmSudosolve.getContentPane().add(btnClear);
	}

	private void initFields() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(9);

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {

                JFormattedTextField numberField = new JFormattedTextField(formatter);
                numberField.setBounds(6 + 41 * column, 6 + 31 * row, 30, 20);
                numberField.setValue(0);
				frmSudosolve.getContentPane().add(numberField);
				boardVisualization[row][column] = numberField;
			}
		}
	}

    /**
     * Source from:
     * http://stackoverflow.com/questions/1178312/how-to-select-all-text-in-a-jformattedtextfield-when-it-gets-focus
     */
    private void initFocusListener() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addPropertyChangeListener("permanentFocusOwner", new PropertyChangeListener()
                {
                    public void propertyChange(final PropertyChangeEvent e)
                    {
                        if (e.getNewValue() instanceof JFormattedTextField)
                        {
                            SwingUtilities.invokeLater(new Runnable()
                            {
                                public void run()
                                {
                                    JFormattedTextField textField = (JFormattedTextField)e.getNewValue();
                                    textField.selectAll();
                                }
                            });

                        }
                    }
                });
    }

	/**
	 * Updates the current values of the board visualization with the values provided by the given {@link Sudoku}.
	 */
	public void updateBoardVisualization(Sudoku sudoku) {
		SudokuHelper.updateGivenBoardVisualizationWithSudokuValues(boardVisualization, sudoku);
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JButton getBtnSolve() {
		return btnSolve;
	}

	public JButton getBtnClear() {
		return btnClear;
	}
}
