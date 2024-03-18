package banking;

public class WithdrawCommandProcessor {
	private final Bank bank;

	public WithdrawCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void withdrawHandler(String command) {
		String[] parts = command.stripTrailing().split(" ");

		int accountID = Integer.parseInt(parts[1]);
		double amount = Double.parseDouble(parts[2]);

		bank.getAccountById(accountID).withdraw(amount);
		bank.getAccountById(accountID).addTransactions(command);
	}
}
