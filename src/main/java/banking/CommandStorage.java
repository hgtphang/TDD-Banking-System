package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
	private List<String> invalidCommands;

	public CommandStorage() {
		this.invalidCommands = new ArrayList<>();
	}

	public void store(String command) {
		invalidCommands.add(command);
	}

	public List<String> getInvalidCommands() {
		return new ArrayList<>(invalidCommands);
	}
}
