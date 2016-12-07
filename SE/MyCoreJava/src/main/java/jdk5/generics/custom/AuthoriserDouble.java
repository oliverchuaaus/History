package jdk5.generics.custom;

public class AuthoriserDouble extends Authoriser<Double> {

	@Override
	public String authorise(Double number) {
		return "authorise double: " + number.doubleValue();
	}

	// Compile Error: E cannot be resolved to a type
	/*
	 * @Override public void authorise(E number) { super.authorise(number); }
	 */

}
