package reuse.composition;

public class AccountHelperImpl implements AccountHelper {

	public String deposit(double amount) {
		return "depositing " + amount;
	}

	public String withdraw(double amount) {
		return "withdrawing " + amount;
	}

}
