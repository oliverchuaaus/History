package reuse.composition;

public class TermDepositAccount implements Account {
	private AccountHelper helper = new AccountHelperImpl();

	public double calculateInterest(double amount) {
		return amount * 0.05;
	}

	public String deposit(double amount) {
		return helper.deposit(amount);
	}

	public String withdraw(double amount) {
		return helper.withdraw(amount);
	}
}
