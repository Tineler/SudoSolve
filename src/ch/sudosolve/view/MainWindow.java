package ch.sudosolve.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frmSudosolveThe;
	private JSpinner[][] boardVisualization;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmSudosolveThe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		boardVisualization = new JSpinner[9][9];
		initializeUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeUI() {
		frmSudosolveThe = new JFrame();
		frmSudosolveThe.setTitle("SudoSolve - The backtracking Sudoku Solver");
		frmSudosolveThe.setBounds(100, 100, 380, 385);
		frmSudosolveThe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSudosolveThe.getContentPane().setLayout(null);

		JButton btnSolve = new JButton("Solve");
		btnSolve.setBounds(257, 296, 117, 29);
		frmSudosolveThe.getContentPane().add(btnSolve);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(128, 296, 117, 29);
		frmSudosolveThe.getContentPane().add(btnClear);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(6, 337, 368, 20);
		frmSudosolveThe.getContentPane().add(progressBar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(6, 92, 365, 12);
		frmSudosolveThe.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(6, 185, 365, 12);
		frmSudosolveThe.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(122, 6, 12, 276);
		frmSudosolveThe.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(245, 6, 12, 276);
		frmSudosolveThe.getContentPane().add(separator_3);
		
		JButton btnRandomize = new JButton("Randomize");
		btnRandomize.setBounds(5, 296, 117, 29);
		frmSudosolveThe.getContentPane().add(btnRandomize);

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, 9, 1);
				JSpinner spinner = new JSpinner(spinnerNumberModel);
				spinner.setBounds(6 + 41 * column, 6 + 31 * row, 37, 28);
				frmSudosolveThe.getContentPane().add(spinner);
				boardVisualization[row][column] = spinner;
			}
		}
	}
}
