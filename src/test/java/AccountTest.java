import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

	Account checkingAccount;
	Account savingsAccount;
	Account cdAccount;

	@BeforeEach
	public void setUp() {
		checkingAccount = new CheckingAccount(0.00);
		savingsAccount = new SavingsAccount(0.00);
		cdAccount = new CDAccount(1000.00);
	}

	@Test
	public void account_created_with_supplied_apr() {
		double actual = checkingAccount.getAPR();

		assertEquals(0.02, actual);
	}

	@Test
	public void deposit_increases_balance() {
		checkingAccount.deposit(500.00);
		double actual = checkingAccount.getBalance();

		assertEquals(500.00, actual);
	}

	@Test
	public void withdraw_decreases_balance() {
		checkingAccount.withdraw(200.00);
		double actual = checkingAccount.getBalance();

		assertEquals(0, actual);
	}

	@Test
	public void cannot_withdraw_below_zero_balance() {
		checkingAccount.withdraw(500.00);
		double actual = checkingAccount.getBalance();

		assertEquals(0.00, actual);
	}

	@Test
	public void deposit_twice_works_as_expected() {
		checkingAccount.deposit(200.0);
		checkingAccount.deposit(300.0);
		double actual = checkingAccount.getBalance();

		assertEquals(500.0, actual);
	}

	@Test
	public void withdrawing_twice_works_as_expected() {
		checkingAccount.withdraw(100.00);
		checkingAccount.withdraw(200.00);
		double actual = checkingAccount.getBalance();

		// Verify balance is the result of both withdrawals
		assertEquals(0.00, actual);
	}
}
