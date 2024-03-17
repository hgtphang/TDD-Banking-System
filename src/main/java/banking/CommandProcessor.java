package banking;

public class CommandProcessor {

	private final Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void handle(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (parts[0].equalsIgnoreCase("create")) {
			CreateCommandProcessor createCommandProcessor = new CreateCommandProcessor(bank);
			createCommandProcessor.createHandler(parts);
		} else if (parts[0].equalsIgnoreCase("deposit")) {
			DepositCommandProcessor depositCommandProcessor = new DepositCommandProcessor(bank);
			depositCommandProcessor.depositHandler(parts);
		} else if (parts[0].equalsIgnoreCase("withdraw")) {
			WithdrawCommandProcessor withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
			withdrawCommandProcessor.withdrawHandler(parts);
		} else if (parts[0].equalsIgnoreCase("transfer")) {
			WithdrawCommandProcessor withdrawCommandProcessor = new WithdrawCommandProcessor(bank);
			withdrawCommandProcessor.withdrawHandler(parts);
		}
	}

}
