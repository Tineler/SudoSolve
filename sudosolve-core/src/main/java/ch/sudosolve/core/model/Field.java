package ch.sudosolve.core.model;

public class Field {
	private boolean isPreset;
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isPreset() {
		return isPreset;
	}

	public void setPreset(boolean isPreset) {
		this.isPreset = isPreset;
	}
}
