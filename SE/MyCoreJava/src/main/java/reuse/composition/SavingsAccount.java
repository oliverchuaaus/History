package reuse.composition;

public class SavingsAccount implements Account {
	private AccountHelper helper = new AccountHelperImpl();

	public double calculateInterest(double amount) {
		return amount * 0.03;
	}

	public String deposit(double amount) {
		return helper.deposit(amount);
	}

	public String withdraw(double amount) {
		return helper.withdraw(amount);
	}
}
