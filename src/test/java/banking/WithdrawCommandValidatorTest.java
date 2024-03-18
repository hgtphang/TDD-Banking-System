package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandValidatorTest {

	WithdrawCommandValidator commandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new WithdrawCommandValidator(bank);
	}

	@Test
	void a_valid_withdraw_command() {
		bank.createCheckingAccount(12345678, 1.2);
		boolean actual = commandValidator.validate("withdraw 12345678 200");
		assertTrue(actual);
	}

	@Test
	void withdraw_has_missing_id_is_invalid() {
		boolean actual = commandValidator.validate("withdraw 200");
		assertFalse(actual);
	}

	@Test
	void withdraw_has_missing_amount_is_invalid() {
		boolean actual = commandValidator.validate("withdraw 12345678");
		assertFalse(actual);
	}

	@Test
	void withdraw_has_missing_id_and_amount_is_invalid() {
		boolean actual = commandValidator.validate("WITHDRAW");
		assertFalse(actual);
	}

	@Test
	void withdraw_more_than_allowed_from_checking_account_is_invalid() {
		bank.createCheckingAccount(12345678, 2.1);
		boolean actual = commandValidator.validate("withdraw 12345678 500");
		assertFalse(actual);
	}

	@Test
	void withdraw_a_right_amount_from_checking_account_is_valid() {
		bank.createCheckingAccount(12345678, 2.3);
		boolean actual = commandValidator.validate("withdraw 12345678 300");
		assertTrue(actual);
	}

	@Test
	void withdraw_negative_amount_from_checking_is_invalid() {
		bank.createCheckingAccount(12345678, 2.3);
		boolean actual = commandValidator.validate("withdraw 12345678 -200");
		assertFalse(actual);
	}

	@Test
	void withdraw_a_minimum_amount_from_checking_account_is_valid() {
		bank.createCheckingAccount(12345678, 2.1);
		boolean actual = commandValidator.validate("withdraw 12345678 0");
		assertTrue(actual);
	}

	@Test
	void withdraw_a_maximum_amount_from_checking_account_is_valid() {
		bank.createCheckingAccount(12345678, 2.1);
		boolean actual = commandValidator.validate("withdraw 12345678 400");
		assertTrue(actual);
	}

	@Test
	void withdraw_more_than_allowed_from_savings_account_is_invalid() {
		bank.createSavingsAccount(23456789, 1.2);
		boolean actual = commandValidator.validate("withdraw 23456789 2000");
		assertFalse(actual);
	}

	@Test
	void withdraw_minimum_amount_from_savings_is_valid() {
		bank.createSavingsAccount(12345678, 1.3);
		boolean actual = commandValidator.validate("withdraw 12345678 0");
		assertTrue(actual);
	}

	@Test
	void withdraw_maximum_amount_from_savings_is_valid() {
		bank.createSavingsAccount(12345678, 1.3);
		boolean actual = commandValidator.validate("withdraw 12345678 1000");
		assertTrue(actual);
	}

	@Test
	void withdraw_a_right_amount_from_savings_account_is_valid() {
		bank.createSavingsAccount(23456789, 3.2);
		boolean actual = commandValidator.validate("withdraw 23456789 900");
		assertTrue(actual);
	}

	@Test
	void withdraw_a_negative_amount_from_savings_account_is_valid() {
		bank.createSavingsAccount(23456789, 3.2);
		boolean actual = commandValidator.validate("withdraw 23456789 -100");
		assertFalse(actual);
	}

	@Test
	void withdraw_from_savings_account_which_not_exists_is_invalid() {
		bank.createCheckingAccount(12345678, 1.3);
		boolean actual = commandValidator.validate("withdraw 23456789 200");
		assertFalse(actual);
	}

	@Test
	void withdraw_from_checking_account_which_not_exists_is_invalid() {
		bank.createSavingsAccount(23456789, 1.3);
		boolean actual = commandValidator.validate("withdraw 12345678 200");
		assertFalse(actual);
	}

	@Test
	void withdraw_from_savings_account_already_withdrawn_is_invalid() {
		bank.createSavingsAccount(23456789, 2.1);
		bank.getAccountById(23456789).withdraw(200);

		boolean actual = commandValidator.validate("withdraw 23456789 100");

		assertFalse(actual);
	}

	@Test
	void withdraw_from_savings_account_pass_1_month_is_valid() {
		bank.createSavingsAccount(23456789, 2.1);
		bank.getAccountById(23456789).withdraw(200);
		bank.getAccountById(23456789).pass(1);

		boolean actual = commandValidator.validate("withdraw 23456789 100");

		assertTrue(actual);
	}

	@Test
	void withdraw_from_cd_account_pass_3_month_is_invalid() {
		bank.createCDAccount(23456789, 3.1, 2000);
		bank.getAccountById(23456789).withdraw(1000);
		bank.getAccountById(23456789).pass(3);

		boolean actual = commandValidator.validate("withdraw 23456789 1000");

		assertFalse(actual);
	}

	@Test
	void withdraw_from_cd_account_pass_12_month_is_valid() {
		bank.createCDAccount(23456789, 3.1, 2000);
		bank.getAccountById(23456789).withdraw(1000);
		bank.getAccountById(23456789).pass(12);

		boolean actual = commandValidator.validate("withdraw 23456789 1000");

		assertTrue(actual);
	}

}
