package banking;

public class DepositCommandProcessor {
	private final Bank bank;

	public DepositCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void depositHandler(String[] parts) {
		int accountId = Integer.parseInt(parts[1]);
		double amount = Double.parseDouble(parts[2]);

		Account account = bank.getAccountById(accountId);
		if (account != null) {
			account.deposit(amount);
		}
	}
}
