import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingAccountTest {

	CheckingAccount checkingAccount;

	@BeforeEach
	public void setUp() {
		checkingAccount = new CheckingAccount(00000001, 0.1);
	}

	@Test
	public void checking_account_created_with_zero_balance() {
		double actual = checkingAccount.getBalance();

		assertEquals(0, actual);
	}
}