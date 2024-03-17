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
			TransferCommandProcessor transferCommandProcessor = new TransferCommandProcessor(bank);
			transferCommandProcessor.transferHandler(parts);
		} else if (parts[0].equalsIgnoreCase("pass")) {
			PassCommandProcessor passCommandProcessor = new PassCommandProcessor(bank);
			passCommandProcessor.passHandler(parts);
		}
	}
}
