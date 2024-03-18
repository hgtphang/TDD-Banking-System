package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommandValidatorTest {
	CommandValidator commandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new CommandValidator(bank);
	}

	@Test
	void create_checking_or_savings_account_is_valid() {
		boolean actual = commandValidator.validate("create savings 12345678 0.6");
		assertTrue(actual);
	}

	@Test
	void create_cd_account_is_valid() {
		boolean actual = commandValidator.validate("create cd 12345678 1.2 2000");
		assertTrue(actual);
	}

	@Test
	void create_has_case_insensitivity() {
		boolean actual = commandValidator.validate("CREATE SAVINGS 12345678 0.2");
		assertTrue(actual);
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
	void create_has_wrong_account_type_is_invalid() {
		boolean actual = commandValidator.validate("create ch@cking 12345678");
		assertFalse(actual);
	}

	@Test
	void create_has_wrong_account_id_is_invalid() {
		boolean actual = commandValidator.validate("create checking 123A5@78 0.5");
		assertFalse(actual);
	}

	@Test
	void create_has_wrong_apr_is_invalid() {
		boolean actual = commandValidator.validate("create checking 12345678 0.A@");
		assertFalse(actual);
	}

	@Test
	void create_has_wrong_account_type_and_account_id_is_invalid() {
		boolean actual = commandValidator.validate("create ch@cking 1ABC5678 1.2");
		assertFalse(actual);
	}

	@Test
	void create_cd_has_wrong_balance_is_invalid() {
		boolean actual = commandValidator.validate("create cd 12345678 0.2 8A0");
		assertFalse(actual);
	}

	@Test
	void create_has_negative_apr_is_invalid() {
		boolean actual = commandValidator.validate("create checking 12345678 -0.5");
		assertFalse(actual);
	}

	@Test
	void create_cd_account_has_less_initial_balance_is_invalid() {
		boolean actual = commandValidator.validate("create cd 12345678 0.6 500");
		assertFalse(actual);
	}

	@Test
	void create_cd_account_has_more_initial_balance_is_invalid() {
		boolean actual = commandValidator.validate("create cd 12345678 0.6 20000");
		assertFalse(actual);
	}

	@Test
	void create_cd_has_negative_balance_is_invalid() {
		boolean actual = commandValidator.validate("create cd 12345678 0.3 -3000");
		assertFalse(actual);
	}

	@Test
	void create_account_already_exits_is_invalid() {
		bank.createCheckingAccount(12345678, 2.2);
		boolean actual = commandValidator.validate("create checking 12345678 1,2");
		assertFalse(actual);
	}

	@Test
	void create_checking_or_savings_account_with_amount_is_invalid() {
		boolean actual = commandValidator.validate("create checking 12345678 2.3 500");
		assertFalse(actual);
	}

	@Test
	void create_has_upper_letter_in_create_and_account_type_is_valid() {
		boolean actual = commandValidator.validate("creAte cHecKing 98765432 0.01");
		assertTrue(actual);
	}
}