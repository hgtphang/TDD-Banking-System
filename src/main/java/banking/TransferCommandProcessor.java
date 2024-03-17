package banking;

public class TransferCommandProcessor {
	private final Bank bank;

	public TransferCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void transferHandler(String[] parts) {
		int fromAccountID = Integer.parseInt(parts[1]);
		int toAccountID = Integer.parseInt(parts[2]);

		double amount = Double.parseDouble(parts[3]);
		bank.transferById(fromAccountID, toAccountID, amount);
	}
}
