package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandValidatorTest {

	DepositCommandValidator commandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new DepositCommandValidator(bank);
	}

	@Test
	void deposit_into_checking_account_is_valid() {
		bank.createCheckingAccount(123456789, 0.1);
		boolean actual = commandValidator.validate("deposit 123456789 100");
		assertTrue(actual);
	}

	@Test
	void deposit_into_savings_account_is_valid() {
		bank.createSavingsAccount(987654321, 0.05);
		boolean actual = commandValidator.validate("deposit 987654321 100");
		assertTrue(actual);
	}

	@Test
	void deposit_to_cd_account_is_invalid() {
		bank.createCDAccount(24681012, 0.2, 5000);
		boolean actual = commandValidator.validate("deposit 24681012 100");
		assertFalse(actual);
	}

	@Test
	void deposit_has_incorrect_account_id_is_invalid() {
		boolean actual = commandValidator.validate("deposit 12345ab8 100");
		assertFalse(actual);
	}

	@Test
	void deposit_has_incorrect_amount_is_invalid() {
		boolean actual = commandValidator.validate("deposit 123456789 abc");
		assertFalse(actual);
	}

	@Test
	void deposit_has_negative_amount_is_invalid() {
		boolean actual = commandValidator.validate("deposit 123456789 -100");
		assertFalse(actual);
	}

	@Test
	void deposit_checking_has_amount_bigger_than_allowed_amount() {
		bank.createCheckingAccount(123456789, 0.1);
		boolean actual = commandValidator.validate("deposit 123456789 2000");
		assertFalse(actual);
	}

	@Test
	void deposit_savings_has_amount_bigger_than_allowed_amount() {
		bank.createSavingsAccount(987654321, 0.05);
		boolean actual = commandValidator.validate("deposit 987654321 3000");
		assertFalse(actual);
	}

	@Test
	void deposit_to_not_existing_account_invalid_id() {
		boolean actual = commandValidator.validate("deposit 98765432 100");
		assertFalse(actual);
	}

	@Test
	void deposit_zero_amount_to_an_account() {
		bank.createCheckingAccount(12345678, 0.1);
		boolean actual = commandValidator.validate("deposit 12345678 0");
		assertTrue(actual);
	}

	@Test
	void deposit_checking_has_amount_equals_allowed_amount() {
		bank.createCheckingAccount(12345678, 0.1);
		boolean actual = commandValidator.validate("deposit 12345678 1000");
		assertTrue(actual);
	}

	@Test
	void deposit_savings_has_amount_equals_allowed_amount() {
		bank.createSavingsAccount(23456789, 0.1);
		boolean actual = commandValidator.validate("deposit 23456789 2500");
		assertTrue(actual);
	}

	@Test
	void cannot_deposit_5000_to_checking_account() {
		bank.createCheckingAccount(12345678, 1.2);
		boolean actual = commandValidator.validate("Deposit 12345678 5000");
		assertFalse(actual);
	}

	@Test
	void deposit_into_account_with_zero_apr_is_valid() {
		bank.createCheckingAccount(123456789, 0.0);
		boolean actual = commandValidator.validate("deposit 123456789 100");
		assertTrue(actual);
	}

	@Test
	void deposit_maximum_allowed_amount_into_checking_account_is_valid() {
		bank.createCheckingAccount(123456789, 0.1);
		boolean actual = commandValidator.validate("deposit 123456789 1000");
		assertTrue(actual);
	}

	@Test
	void deposit_amount_greater_than_maximum_allowed_is_invalid() {
		bank.createCheckingAccount(123456789, 0.1);
		boolean actual = commandValidator.validate("deposit 123456789 1001");
		assertFalse(actual);
	}

	@Test
	void deposit_decimal_amount_into_account_is_valid() {
		bank.createCheckingAccount(123456789, 0.1);
		boolean actual = commandValidator.validate("deposit 123456789 100.50");
		assertTrue(actual);
	}

}
