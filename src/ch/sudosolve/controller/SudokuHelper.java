package ch.sudosolve.controller;

import javax.swing.JSpinner;

import ch.sudosolve.model.Field;
import ch.sudosolve.model.Sudoku;

public class SudokuHelper {

	/**
	 * Convert a two dimensional array of {@link JSpinner JSpinners} to a {@link Sudoku}.
	 */
	public static Sudoku convertBoardVisualizationToSudoku(JSpinner[][] boardVisualization) {
		Sudoku sudoku = new Sudoku();
		Field[][] board = sudoku.getBoard();
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				JSpinner spinner = boardVisualization[row][column];
				int spinnerValue = (int) spinner.getValue();

				Field field = board[row][column];
				field.setNumber(spinnerValue);
				if (spinnerValue != 0) {
					field.setPreset(true);
				}
			}
		}
		return sudoku;
	}

	/**
	 * Convert a two dimensional array of {@link JSpinner JSpinners} to a {@link Sudoku}.
	 */
	public static void updateGivenBoardVisualizationWithSudokuValues(JSpinner[][] boardVisualization, Sudoku sudoku) {
		Field[][] board = sudoku.getBoard();
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Field field = board[row][column];
				JSpinner spinner = boardVisualization[row][column];
				spinner.setValue(field.getNumber());
			}
		}
	}
}
