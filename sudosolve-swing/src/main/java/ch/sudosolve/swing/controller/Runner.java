package ch.sudosolve.swing.controller;

import ch.sudosolve.swing.view.MainWindow;

public class Runner {
	private static MainWindow window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		window = MainWindow.getInstance();
		window.show();
	}
}
