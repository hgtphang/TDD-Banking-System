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
	void withdraw_more_than_allowed_from_savings_account_is_invalid() {
		bank.createSavingsAccount(23456789, 1.2);
		boolean actual = commandValidator.validate("withdraw 23456789 2000");
		assertFalse(actual);
	}

	@Test
	void withdraw_zero_amount_is_valid() {
		bank.createSavingsAccount(12345678, 1.3);
		boolean actual = commandValidator.validate("withdraw 12345678 0");
		assertTrue(actual);
	}

	@Test
	void withdraw_negative_amount_is_invalid() {
		bank.createCheckingAccount(12345678, 2.3);
		boolean actual = commandValidator.validate("withdraw 12345678 -200");
		assertFalse(actual);
	}

	@Test
	void withdraw_a_right_amount_from_checking_account_is_valid() {
		bank.createCheckingAccount(12345678, 2.3);
		boolean actual = commandValidator.validate("withdraw 12345678 300");
		assertTrue(actual);
	}

	@Test
	void withdraw_a_right_amount_from_savings_account_is_valid() {
		bank.createSavingsAccount(23456789, 3.2);
		boolean actual = commandValidator.validate("withdraw 23456789 900");
		assertTrue(actual);
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

}
