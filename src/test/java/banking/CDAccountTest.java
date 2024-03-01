package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CDAccountTest {

	CDAccount cdAccount;

	@BeforeEach
	public void setUp() {
		cdAccount = new CDAccount(00000003, 0.3, 1000.00);
	}

	@Test
	public void cd_account_created_with_supplied_balance() {
		double balance = cdAccount.getBalance();

		assertEquals(1000.00, balance);
	}
}