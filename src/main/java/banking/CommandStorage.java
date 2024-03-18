package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
	private List<String> invalidCommands;
	private List<String> validCommands;

	public CommandStorage() {
		this.invalidCommands = new ArrayList<>();
		this.validCommands = new ArrayList<>();
	}

	public void addInvalidCommand(String command) {
		invalidCommands.add(command);
	}

	public void addValidCommand(String command) {
		validCommands.add(command);
	}

	public List<String> getInvalidCommands() {
		return invalidCommands;
	}

	public List<String> getValidCommands() {
		return validCommands;
	}
}
