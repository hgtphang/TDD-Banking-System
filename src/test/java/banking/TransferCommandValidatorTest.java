package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferCommandValidatorTest {

	TransferCommandValidator commandValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandValidator = new TransferCommandValidator(bank);
	}

	@Test
	void transfer_has_missing_two_account_id_is_invalid() {
		boolean actual = commandValidator.validate("transfer 200");
		assertFalse(actual);
	}

	@Test
	void transfer_has_missing_amount_is_invalid() {
		boolean actual = commandValidator.validate("transfer 12345678 23456789");
		assertFalse(actual);
	}

	@Test
	void transfer_has_missing_one_account_is_invalid() {
		boolean actual = commandValidator.validate("transfer 23456789 300");
		assertFalse(actual);
	}

	@Test
	void transfer_has_first_account_does_not_exists_is_invalid() {
		bank.createSavingsAccount(23456789, 1.2);

		boolean actual = commandValidator.validate("transfer 12345678 23456789 100");
		assertFalse(actual);
	}

	@Test
	void transfer_has_second_account_does_not_exists_is_invalid() {
		bank.createCheckingAccount(12345678, 1.2);

		boolean actual = commandValidator.validate("transfer 12345678 23456789 100");
		assertFalse(actual);
	}

	@Test
	void transfer_command_has_two_account_id_do_not_exist_is_invalid() {
		boolean actual = commandValidator.validate("transfer 12345678 23456789 200");
		assertFalse(actual);
	}

	@Test
	void transfer_command_has_cd_account_is_invalid() {
		bank.createCheckingAccount(12345678, 1.2);
		bank.createCDAccount(23456789, 2.1, 300);

		boolean actual = commandValidator.validate("transfer 12345678 23456789 200");
		assertFalse(actual);
	}
}
