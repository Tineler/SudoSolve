package ch.sudosolve.controller;

import javax.swing.JButton;
import javax.swing.JProgressBar;

import ch.sudosolve.model.Sudoku;
import ch.sudosolve.view.MainWindow;

/**
 * Updates the user interface with the current state of the solving process.
 */
public class UserInterfaceUpdaterThread extends Thread {
	/**
	 * Total number of possible sudokus: 5472730538 It takes on average half the possibilities to find a solution, so
	 * 5472730538 / 2 = 2736365269
	 */
	private static final Long POSSIBLE_SUDOKUS_COUNT = 2736365269L;

	private JProgressBar progressBar;
	private JButton solveButton;
	private JButton clearButton;
	private SudokuSolverThread solverThread;
	private MainWindow window;

	public UserInterfaceUpdaterThread(SudokuSolverThread thread) {
		window = MainWindow.getInstance();
		progressBar = window.getProgressBar();
		solveButton = window.getBtnSolve();
		clearButton = window.getBtnClear();
		solverThread = thread;
	}

	@Override
	public void run() {
		super.run();
		solveButton.setText("Stop");
		clearButton.setEnabled(false);
		while (!solverThread.isShutdown() && !solverThread.foundSolution) {
			Sudoku currentBoard = solverThread.getCurrentBoard();
			window.updateBoardVisualization(currentBoard);

			Long progressPercentage = calculateProgressPercentage();
			progressBar.setValue(progressPercentage.intValue());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		solveButton.setText("Solve");
		clearButton.setEnabled(true);
		progressBar.setValue(100);
	}

	/**
	 * Returns the current progress in percent. If the solver tried more possibilities than POSSIBLE_SUDOKUS_COUNT, then
	 * 99 is returned. Returning 99 means that the solver has a "bad day" and needs more tries than on average.
	 */
	private Long calculateProgressPercentage() {
		Float tryCount = solverThread.tryCount.floatValue();

		if (tryCount > POSSIBLE_SUDOKUS_COUNT) {
			return 99L;
		}

		Long progressPercentage = (long) (tryCount / POSSIBLE_SUDOKUS_COUNT * 100);
		return progressPercentage;
	}
}
