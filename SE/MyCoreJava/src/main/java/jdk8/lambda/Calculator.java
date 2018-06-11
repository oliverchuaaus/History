package jdk8.lambda;

public class Calculator {

	interface IntegerMath {
		int operation(int a, int b, int c);
	}

	public int operateTernary(int a, int b, int c, IntegerMath op) {
		return op.operation(a, b, c);
	}

}