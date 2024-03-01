package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsAccountTest {

	SavingsAccount savingsAccount;

	@BeforeEach
	public void setUp() {
		savingsAccount = new SavingsAccount(00000002, 0.2);
	}

	@Test
	public void savings_account_created_with_zero_balance() {
		double actual = savingsAccount.getBalance();

		assertEquals(0, actual);
	}
}