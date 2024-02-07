import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

	Account checkingAccount;
	Account savingsAccount;
	Account cdAccount;

	@BeforeEach
	public void setUp() {
		checkingAccount = new CheckingAccount(00000001, 0.1);
		savingsAccount = new SavingsAccount(00000002, 0.2);
		cdAccount = new CDAccount(00000003, 0.3, 1000.00);
	}

	@Test
	public void checking_account_created_with_supplied_apr() {
		double actual = checkingAccount.getAPR();

		assertEquals(0.1, actual);
	}

	@Test
	public void savings_account_created_with_supplied_apr() {
		double actual = savingsAccount.getAPR();

		assertEquals(0.2, actual);
	}

	@Test
	public void cd_account_created_with_supplied_apr() {
		double actual = cdAccount.getAPR();

		assertEquals(0.3, actual);
	}

	@Test
	public void checking_account_deposit_increases_balance() {
		checkingAccount.deposit(500.00);
		double actual = checkingAccount.getBalance();

		assertEquals(500.00, actual);
	}

	@Test
	public void savings_account_deposit_increases_balance() {
		savingsAccount.deposit(700.00);
		double actual = savingsAccount.getBalance();

		assertEquals(700.00, actual);
	}

	@Test
	public void cd_account_deposit_increases_balance() {
		cdAccount.deposit(500.00);
		double actual = cdAccount.getBalance();

		assertEquals(1500.00, actual);
	}

	@Test
	public void checking_account_withdraw_decreases_balance() {
		checkingAccount.deposit(500.00);
		checkingAccount.withdraw(200.00);
		double actual = checkingAccount.getBalance();

		assertEquals(300.00, actual);
	}

	@Test
	public void savings_account_withdraw_decreases_balance() {
		savingsAccount.deposit(700.00);
		savingsAccount.withdraw(200.00);
		double actual = savingsAccount.getBalance();

		assertEquals(500.00, actual);
	}

	@Test
	public void cd_account_withdraw_decreases_balance() {
		cdAccount.withdraw(800.00);
		double actual = cdAccount.getBalance();

		assertEquals(200.00, actual);
	}

	@Test
	public void cannot_withdraw_below_zero_balance() {
		checkingAccount.deposit(500.00);
		checkingAccount.withdraw(600.00);
		double actual = checkingAccount.getBalance();

		assertEquals(0.00, actual);
	}

	@Test
	public void deposit_twice_works_as_expected() {
		checkingAccount.deposit(200.00);
		checkingAccount.deposit(300.00);
		double actual = checkingAccount.getBalance();

		assertEquals(500.00, actual);
	}

	@Test
	public void withdrawing_twice_works_as_expected() {
		checkingAccount.deposit(500.00);
		checkingAccount.withdraw(100.00);
		checkingAccount.withdraw(200.00);
		double actual = checkingAccount.getBalance();

		assertEquals(200.00, actual);
	}
}