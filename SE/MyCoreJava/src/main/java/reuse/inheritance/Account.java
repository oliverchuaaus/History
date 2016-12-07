package reuse.inheritance;

public abstract class Account {
	public String deposit(double amount) {
		return "depositing " + amount;
	}

	public String withdraw(double amount) {
		return "withdrawing " + amount;
	}

	public abstract double calculateInterest(double amount);

}
