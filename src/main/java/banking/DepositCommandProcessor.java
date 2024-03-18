package banking;

public class DepositCommandProcessor {
	private final Bank bank;

	public DepositCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void depositHandler(String command) {
		String[] parts = command.stripTrailing().split(" ");

		int accountId = Integer.parseInt(parts[1]);
		double amount = Double.parseDouble(parts[2]);

		Account account = bank.getAccountById(accountId);
		if (account != null) {
			account.deposit(amount);
			account.addTransactions(command);
		}
	}
}
