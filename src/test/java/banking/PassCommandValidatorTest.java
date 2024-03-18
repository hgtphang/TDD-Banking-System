package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassCommandValidatorTest {

	PassCommandValidator commandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new PassCommandValidator();
	}

	@Test
	void pass_time_command_has_missing_month_is_invalid() {
		boolean actual = commandValidator.validate("pass");
		assertFalse(actual);
	}

	@Test
	void pass_time_command_has_invalid_month_is_invalid() {
		boolean actual = commandValidator.validate("pass 1@");
		assertFalse(actual);
	}

	@Test
	void pass_time_command_has_negative_month_is_invalid() {
		boolean actual = commandValidator.validate("pass -10");
		assertFalse(actual);
	}

	@Test
	void pass_time_command_has_zero_month_is_invalid() {
		boolean actual = commandValidator.validate("pass 0");
		assertFalse(actual);
	}

	@Test
	void pass_time_command_has_month_more_than_allowed_is_invalid() {
		boolean actual = commandValidator.validate("pass 61");
		assertFalse(actual);
	}
}
