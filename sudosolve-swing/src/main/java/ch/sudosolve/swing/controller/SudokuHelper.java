package ch.sudosolve.swing.controller;

import ch.sudosolve.core.model.Field;
import ch.sudosolve.core.model.Sudoku;

import javax.swing.*;

public class SudokuHelper {
	/**
	 * Convert a two dimensional array of {@link JFormattedTextField Number Fields} to a {@link Sudoku}.
	 */
	public static Sudoku convertBoardVisualizationToSudoku(JFormattedTextField[][] boardVisualization) {
		Sudoku sudoku = new Sudoku();
		Field[][] board = sudoku.getBoard();
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
                JFormattedTextField numberField = boardVisualization[row][column];
				int numberFieldValue = (int) numberField.getValue();

				Field field = board[row][column];
				field.setNumber(numberFieldValue);
				if (numberFieldValue != 0) {
					field.setPreset(true);
				}
			}
		}
		return sudoku;
	}

	/**
	 * Convert a two dimensional array of {@link JFormattedTextField Number Fields} to a {@link Sudoku}.
	 */
	public static void updateGivenBoardVisualizationWithSudokuValues(JFormattedTextField[][] boardVisualization, Sudoku sudoku) {
		Field[][] board = sudoku.getBoard();
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				Field field = board[row][column];
                JFormattedTextField numberField = boardVisualization[row][column];
                numberField.setValue(field.getNumber());
			}
		}
	}
}
