import java.util.List;

public class MasterControl {
	private CommandValidator commandValidator;
	private CommandProcessor commandProcessor;
	private CommandStorage commandStorage;

	public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor,
			CommandStorage commandStorage) {
		this.commandValidator = commandValidator;
		this.commandProcessor = commandProcessor;
		this.commandStorage = commandStorage;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			String[] parts = command.stripTrailing().split(" ");
			if (commandValidator.validate(command)) {
				commandProcessor.handle(parts);
			} else {
				commandStorage.store(command);
			}

		}
		return commandStorage.getInvalidCommands();
	}
}
