package banking;

public class TransferCommandProcessor {
	private final Bank bank;

	public TransferCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void transferHandler(String command) {
		String[] parts = command.stripTrailing().split(" ");

		int fromAccountID = Integer.parseInt(parts[1]);
		bank.getAccountById(fromAccountID).addTransactions(command);

		int toAccountID = Integer.parseInt(parts[2]);
		bank.getAccountById(toAccountID).addTransactions(command);

		double amount = Double.parseDouble(parts[3]);
		bank.transferById(fromAccountID, toAccountID, amount);

	}
}
