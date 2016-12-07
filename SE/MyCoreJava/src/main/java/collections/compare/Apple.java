package collections.compare;

public class Apple implements Comparable<Apple> {
	private String variety;
	private String fruitSize;

	public Apple(String variety, String fruitSize) {
		this.variety = variety;
		this.fruitSize = fruitSize;
	}

	@Override
	public int compareTo(Apple otherApple) {
		return this.variety.compareTo(otherApple.variety);
	}

	public String getVariety() {
		return variety;
	}

	public String getFruitSize() {
		return fruitSize;
	}
}
