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
}
