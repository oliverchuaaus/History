package collections.compare;

import java.util.Comparator;

public class AppleComparator implements Comparator<Apple> {

	@Override
	public int compare(Apple apple1, Apple apple2) {
		if (apple1.getVariety().equals(apple2.getVariety())) {
			return -1 * apple1.getFruitSize().compareTo(apple2.getFruitSize());
		}
		return apple1.getVariety().compareTo(apple2.getVariety());
	}

	// Comparator interface has equals although it is optional
	// Note that it is always safe not to override Object.equals(Object).
	// However, overriding this method may, in some cases,
	// improve performance by allowing programs to determine
	// that two distinct comparators impose the same order.

}
