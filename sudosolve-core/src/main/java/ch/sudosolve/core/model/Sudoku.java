package ch.sudosolve.core.model;

import java.util.Arrays;

public class Sudoku implements Cloneable {
	private Field[][] board;

	public Sudoku() {
		board = new Field[9][9];
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				board[row][column] = new Field();
			}
		}
	}

	public Field[][] getBoard() {
		return board;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * This method validates the whole sudoku board according to the sudoku rules and returns true if the board fulfills
	 * all requirements, false otherwise.
	 */
	public boolean isValid() {
		for (int row = 0; row < 9; row++) {
			int[] numbersOfRow = new int[9];
			for (int column = 0; column < 9; column++) {
				Field currentField = board[row][column];
				numbersOfRow[column] = currentField.getNumber();
			}
			if (!checkIfArrayContainsAllNumbers(numbersOfRow)) {
				return false;
			}
		}

		for (int column = 0; column < 9; column++) {
			int[] numbersOfColumn = new int[9];
			for (int row = 0; row < 9; row++) {
				Field currentField = board[row][column];
				numbersOfColumn[row] = currentField.getNumber();
			}
			if (!checkIfArrayContainsAllNumbers(numbersOfColumn)) {
				return false;
			}
		}

		// TODO: add validation logic for a 3x3 square

		return true;
	}

	private boolean checkIfArrayContainsAllNumbers(int[] numbers) {
		Arrays.sort(numbers);
		for (int i = 0; i < 9; i++) {
			if (numbers[i] != i + 1) {
				return false;
			}
		}
		return true;
	}
}
