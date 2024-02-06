import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsAccountTest {

	SavingsAccount savingsAccount;

	@BeforeEach
	public void setUp() {
		savingsAccount = new SavingsAccount();
	}

	@Test
	public void savings_account_created_with_zero_balance() {
		double actual = savingsAccount.getBalance();

		assertEquals(SavingsAccount.SAVINGS_DEFAULT_BALANCE, actual);
	}
}
