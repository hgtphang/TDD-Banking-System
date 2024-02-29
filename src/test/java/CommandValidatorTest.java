import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator commandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void create_account_is_valid() {
		boolean actual = commandValidator.validate("create savings 12345678 0.6");
		assertTrue(actual);
	}

	@Test
	void create_has_words_in_caps_is_valid() {
		boolean actual = commandValidator.validate("CREATE CHECKING 12345678 0.2");
		assertTrue(actual);
	}

	@Test
	void typo_in_create_is_invalid() {
		boolean actual = commandValidator.validate("cre@te checking 12345678 0.4");
		assertFalse(actual);
	}

	@Test
	void create_has_missing_create_is_invalid() {
		boolean actual = commandValidator.validate("savings 12345678 0.2");
		assertFalse(actual);
	}

	@Test
	void deposit_has_words_in_caps_is_valid() {
		bank.createCheckingAccount(12345678, 1.2);

		boolean actual = commandValidator.validate("DEPOSIT 12345678 200");
		assertTrue(actual);
	}

	@Test
	void typo_in_deposit_is_invalid() {
		bank.createSavingsAccount(12345678, 1.3);

		boolean actual = commandValidator.validate("dep@sit 12345678 300");
		assertFalse(actual);
	}

	@Test
	void deposit_has_missing_deposit_is_invalid() {
		bank.createCheckingAccount(12345678, 1.4);

		boolean actual = commandValidator.validate("12345678 400");
		assertFalse(actual);
	}

	@Test
	void withdraw_command_is_valid() {
		boolean actual = commandValidator.validate("withdraw 12345678 200");
		assertTrue(actual);
	}

	@Test
	void withdraw_command_has_withdraw_in_cap_is_valid() {
		boolean actual = commandValidator.validate("WITHDRAW 12345678 200");
		assertTrue(actual);
	}

	@Test
	void withdraw_command_has_missing_withdraw_is_invalid() {
		boolean actual = commandValidator.validate("12345678 200");
		assertFalse(actual);
	}

}
