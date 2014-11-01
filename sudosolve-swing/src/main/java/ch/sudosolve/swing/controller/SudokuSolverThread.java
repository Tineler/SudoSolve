package ch.sudosolve.swing.controller;

import ch.sudosolve.core.model.Field;
import ch.sudosolve.core.model.Sudoku;

import java.util.Random;

/**
 * Solves a given {@link Sudoku} and always provides the current solving state so it can be displayed on the UI.
 */
public class SudokuSolverThread extends Thread {

	private Sudoku sudoku;
	private boolean shutdown = false;

	protected Long tryCount = 0L;
	protected boolean foundSolution = false;

	public SudokuSolverThread(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	@Override
	public void run() {
		super.run();

		Random random = new Random();
		while (!shutdown) {
			// This is an example implementation that trys to find a valid sudoku using random generator.
			// Chance to find a valid sudoku is very low.
			// TODO: Implement the solving algorithm using backtracking.
			Field[][] board = sudoku.getBoard();
			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					Field field = board[row][column];
					if (field.isPreset()) {
						continue;
					}
					int randomValue = random.nextInt(10);
					field.setNumber(randomValue);
				}
			}
			if (sudoku.isValid()) {
				shutdown = true;
				foundSolution = true;
			} else {
				tryCount++;
			}
		}
	}

	/**
	 * @return the current state of the solve process as {@link Sudoku}.
	 */
	public Sudoku getCurrentBoard() {
		try {
			return (Sudoku) sudoku.clone();
		} catch (CloneNotSupportedException e) {
			// TODO: Make this nice
			throw new RuntimeException("Clone() is not supported by class Sudoku.");
		}
	}

	/**
	 * Asks the thread to shut down.
	 */
	public void shutdown() {
		shutdown = true;
	}

	public boolean isShutdown() {
		return shutdown;
	}
}
