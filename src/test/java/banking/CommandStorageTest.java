package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {

	private Bank bank;
	private CommandValidator validator;
	private CommandStorage commandStorage;

	@BeforeEach
	void setUp() {
		commandStorage = new CommandStorage();
		bank = new Bank();
		validator = new CommandValidator(bank);
	}

	@Test
	void invalid_commands_is_in_empty() {
		assertEquals(0, commandStorage.getInvalidCommands().size());
	}

	@Test
	void store_invalid_command() {
		String invalidCommand = "creat checking 12345678 0.01";
		commandStorage.addInvalidCommand(invalidCommand);

		assertEquals(invalidCommand, commandStorage.getInvalidCommands().get(0));
	}

	@Test
	void store_invalid_deposit_command() {
		String invalidCommand = "deposit checking 23456789 200";
		commandStorage.addInvalidCommand(invalidCommand);

		assertEquals(invalidCommand, commandStorage.getInvalidCommands().get(0));
	}

	@Test
	void two_invalid_commands() {
		commandStorage.addInvalidCommand("create ch3cking 12345678 0.01");
		commandStorage.addInvalidCommand("deposit checking 23456789 300");

		assertEquals("create ch3cking 12345678 0.01", commandStorage.getInvalidCommands().get(0));
		assertEquals("deposit checking 23456789 300", commandStorage.getInvalidCommands().get(1));
	}

	@Test
	void four_invalid_commands_are_stored_in_storage() {
		commandStorage.addInvalidCommand("create ch3cking 12345678 0.01");
		commandStorage.addInvalidCommand("deposit checking 23456789 300");
		commandStorage.addInvalidCommand("create savings 23456789 0.3 300");

		assertEquals(3, commandStorage.getInvalidCommands().size());
	}

	@Test
	void one_invalid_commands_and_one_valid_command() {
		commandStorage.addInvalidCommand("creat savings 12345678 1.2");
		commandStorage.addValidCommand("create checking 12345678 2.1");

		assertEquals(1, commandStorage.getInvalidCommands().size());
		assertEquals(1, commandStorage.getValidCommands().size());
	}

	@Test
	void two_invalid_commands_and_one_valid_command() {
		commandStorage.addInvalidCommand("create ch3cking 12345678 0.01");
		commandStorage.addInvalidCommand("deposit checking 23456789 300");
		commandStorage.addValidCommand("deposit 12345678 200");

		assertEquals(2, commandStorage.getInvalidCommands().size());
		assertEquals(1, commandStorage.getValidCommands().size());
	}

	@Test
	void store_valid_commands_to_storage() {
		commandStorage.addValidCommand("create checking 12345678 1.2");
		commandStorage.addValidCommand("pass 1");
		commandStorage.addValidCommand("withdraw 12345678 500");

		assertEquals(3, commandStorage.getValidCommands().size());
	}
}
