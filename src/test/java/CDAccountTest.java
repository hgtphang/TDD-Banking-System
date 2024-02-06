import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CDAccountTest {

	CDAccount cdAccount;

	@BeforeEach
	public void setUp() {
		cdAccount = new CDAccount(1000.00);
	}

	@Test
	public void cd_account_created_with_supplied_balance() {
		double balance = cdAccount.getBalance();

		assertEquals(CDAccount.CD_DEFAULT_BALANCE, balance);
	}
}
