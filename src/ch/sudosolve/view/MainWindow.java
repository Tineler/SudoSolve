package ch.sudosolve.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import ch.sudosolve.controller.SudokuHelper;
import ch.sudosolve.controller.SudokuSolverThread;
import ch.sudosolve.controller.UserInterfaceUpdaterThread;
import ch.sudosolve.model.Sudoku;

public class MainWindow {

	private JFrame frmSudosolve;
	private JSpinner[][] boardVisualization;
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
		boardVisualization = new JSpinner[9][9];
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
		initSpinners();
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

				// reset all spinner values to 0
				for (int row = 0; row < 9; row++) {
					for (int column = 0; column < 9; column++) {
						JSpinner spinner = boardVisualization[row][column];
						spinner.setValue(0);
					}
				}
			}
		});
		btnClear.setBounds(128, 296, 117, 29);
		frmSudosolve.getContentPane().add(btnClear);
	}

	private void initSpinners() {
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 9, 1);
				JSpinner spinner = new JSpinner(spinnerNumberModel);
				spinner.setBounds(6 + 41 * column, 6 + 31 * row, 37, 28);
				frmSudosolve.getContentPane().add(spinner);
				boardVisualization[row][column] = spinner;
			}
		}
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