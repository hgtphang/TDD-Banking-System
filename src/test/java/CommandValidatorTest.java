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
		commandValidator = new CommandValidator();
	}

	@Test
	void create_is_valid() {
		boolean actual = commandValidator.validate("create savings 12345678 0.6");
		assertTrue(actual);
	}

	@Test
	void typo_in_create_is_invalid() {
		boolean actual = commandValidator.validate("cre@te checking 12345678 0.4");
		assertFalse(actual);
	}

	@Test
	void create_has_missing_create_is_invalid() {
		boolean actual = commandValidator.validate("");
		assertFalse(actual);
	}

	@Test
	void create_has_missing_account_type_is_invalid() {
		boolean actual = commandValidator.validate("create 12345678 0.2");
		assertFalse(actual);
	}

	@Test
	void create_has_missing_id_is_invalid() {
		boolean actual = commandValidator.validate("create checking 0.3");
		assertFalse(actual);
	}

	@Test
	void create_has_missing_apr_is_invalid() {
		boolean actual = commandValidator.validate("create checking 12345678");
		assertFalse(actual);
	}

	@Test
	void create_cd_has_missing_balance_is_invalid() {
		boolean actual = commandValidator.validate("create cd 12345678 0.3");
		assertFalse(actual);
	}

	@Test
	void create_cd_has_negative_balance_is_invalid() {
		boolean actual = commandValidator.validate("create cd 12345678 0.3 -10000.00");
		assertFalse(actual);
	}

	@Test
	void deposit_is_valid() {
		boolean actual = commandValidator.validate("deposit 12345678 200");
		assertTrue(actual);
	}

	@Test
	void deposit_has_missing_id_is_invalid() {
		boolean actual = commandValidator.validate("deposit 200");
		assertFalse(actual);
	}

}
