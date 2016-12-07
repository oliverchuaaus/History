package reuse.composition;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestComposition {
	@Test
	public void testSingleton() {
		Account savings = new SavingsAccount();
		assertEquals("depositing 100.0", savings.deposit(100.00));
		assertEquals(3.0, savings.calculateInterest(100.00), 0.0);

		Account term = new TermDepositAccount();
		assertEquals("depositing 100.0", term.deposit(100.00));
		assertEquals(5.0, term.calculateInterest(100.00), 0.0);

	}

}
