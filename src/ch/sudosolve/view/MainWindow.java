package ch.sudosolve.view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MainWindow {

	private JFrame frmSudosolveThe;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSudosolveThe = new JFrame();
		frmSudosolveThe.setTitle("SudoSolve - The backtracking Sudoku Solver");
		frmSudosolveThe.setBounds(100, 100, 450, 500);
		frmSudosolveThe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSudosolveThe.getContentPane().setLayout(null);

		JButton btnSolve = new JButton("Solve");
		btnSolve.setBounds(327, 411, 117, 29);
		frmSudosolveThe.getContentPane().add(btnSolve);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(198, 411, 117, 29);
		frmSudosolveThe.getContentPane().add(btnClear);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(6, 452, 438, 20);
		frmSudosolveThe.getContentPane().add(progressBar);

		SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1, 1, 9, 1);
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				JSpinner spinner = new JSpinner(spinnerNumberModel);
				spinner.setBounds(6 + 40 * k, 6 + 30 * i, 37, 28);
				frmSudosolveThe.getContentPane().add(spinner);
			}
		}
	}
}
