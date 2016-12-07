package reuse.inheritance;

public class TermDepositAccount extends Account {

	public double calculateInterest(double amount) {
		return amount * 0.05;
	}
}
