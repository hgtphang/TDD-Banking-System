package banking;

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
}
