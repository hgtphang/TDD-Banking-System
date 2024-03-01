package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	Bank bank;
	Account checkingAccount;
	Account savingsAccount;
	Account cdAccount;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		checkingAccount = new CheckingAccount(00000001, 0.1);
		savingsAccount = new SavingsAccount(00000002, 0.2);
		cdAccount = new CDAccount(00000003, 0.3, 1000.00);
	}

	@Test
	public void bank_created_with_no_accounts() {
		int actual = bank.getNumberOfAccounts();
		assertEquals(0, actual);
	}

	@Test
	public void bank_has_one_account() {
		bank.addAccount(checkingAccount);
		int actual = bank.getNumberOfAccounts();

		assertEquals(1, actual);
	}

	@Test
	public void bank_has_two_accounts() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		int actual = bank.getNumberOfAccounts();

		assertEquals(2, actual);
	}

	@Test
	public void correct_account_is_retrieved() {
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		Account actual = bank.getAccountById(checkingAccount.hashCode());

		assertEquals(checkingAccount, actual);
	}

	@Test
	public void correct_account_gets_deposited_by_id() {
		bank.addAccount(savingsAccount);
		bank.addAccount(checkingAccount);
		bank.depositById(checkingAccount.hashCode(), 500.00);
		double actual = checkingAccount.getBalance();

		assertEquals(500.00, actual);
	}

	@Test
	public void correct_account_gets_withdrawn_by_id() {
		bank.addAccount(checkingAccount);
		bank.addAccount(cdAccount);
		bank.withdrawById(cdAccount.hashCode(), 400.00);
		double actual = cdAccount.getBalance();

		assertEquals(600.00, actual);
	}

	@Test
	public void depositing_twice_through_the_bank_works_as_expected() {
		bank.addAccount(checkingAccount);
		bank.depositById(checkingAccount.hashCode(), 200.00);
		bank.depositById(checkingAccount.hashCode(), 300.00);
		double actual = checkingAccount.getBalance();

		assertEquals(500.00, actual);
	}

	@Test
	public void withdrawing_twice_through_the_bank_works_as_expected() {
		bank.addAccount(checkingAccount);
		bank.depositById(checkingAccount.hashCode(), 500.00);
		bank.withdrawById(checkingAccount.hashCode(), 100.00);
		bank.withdrawById(checkingAccount.hashCode(), 200.00);
		double actual = checkingAccount.getBalance();

		assertEquals(200.00, actual);
	}
}