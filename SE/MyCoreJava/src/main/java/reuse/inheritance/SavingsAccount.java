package reuse.inheritance;

public class SavingsAccount extends Account {

	public double calculateInterest(double amount) {
		return amount * 0.03;
	}
}
