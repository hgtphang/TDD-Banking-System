package banking;

import java.util.List;

public class MasterControl {
	private final CommandValidator commandValidator;
	private final CommandProcessor commandProcessor;
	private final CommandStorage commandStorage;
	private final Output output;

	public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor,
			CommandStorage commandStorage, Output output) {
		this.commandValidator = commandValidator;
		this.commandProcessor = commandProcessor;
		this.commandStorage = commandStorage;
		this.output = output;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (commandValidator.validate(command)) {
				commandProcessor.handle(command);
				commandStorage.addValidCommand(command);
			} else {
				commandStorage.addInvalidCommand(command);
			}
		}
		return output.getFormattedOutput();
	}

}
