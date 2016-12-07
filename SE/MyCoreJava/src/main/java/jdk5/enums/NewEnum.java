package jdk5.enums;

public enum NewEnum {
	ZERO(0), ONE(1), TWO(2);

	private int num;

	// Cannot declare enum constructor as public.
	// As a consequence, enum instance is a singleton.
	private NewEnum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	// we don't provide setter for the num field,
	// as a consequence, we have immutable enums.

	public int square() {
		return num * num;
	}
}
