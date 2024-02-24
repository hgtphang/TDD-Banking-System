import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {

	private CommandStorage commandStorage;

	@BeforeEach
	void setUp() {
		commandStorage = new CommandStorage();
	}

	@Test
	void invalid_commands_is_in_empty() {
		assertEquals(0, commandStorage.getInvalidCommands().size());
	}

	@Test
	void store_invalid_command() {
		String invalidCommand = "creat checking 12345678 0.01";
		commandStorage.store(invalidCommand);

		assertEquals(invalidCommand, commandStorage.getInvalidCommands().get(0));
	}

	@Test
	void store_invalid_deposit_command() {
		String invalidCommand = "deposit checking 23456789 200";
		commandStorage.store(invalidCommand);

		assertEquals(invalidCommand, commandStorage.getInvalidCommands().get(0));
	}

	@Test
	void two_invalid_commands() {
		commandStorage.store("create ch3cking 12345678 0.01");
		commandStorage.store("deposit checking 23456789 300");

		assertEquals("create ch3cking 12345678 0.01", commandStorage.getInvalidCommands().get(0));
		assertEquals("deposit checking 23456789 300", commandStorage.getInvalidCommands().get(1));
	}

	@Test
	void four_invalid_commands_are_stored_in_storage() {
		commandStorage.store("create ch3cking 12345678 0.01");
		commandStorage.store("deposit checking 23456789 300");
		commandStorage.store("create savings 23456789 0.3 300");

		assertEquals(3, commandStorage.getInvalidCommands().size());
	}

}
