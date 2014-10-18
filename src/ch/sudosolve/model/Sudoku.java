package ch.sudosolve.model;

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
		return false;
	}
}
