public class CommandProcessor {

	private final Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void handle(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (parts[0].equals("create")) {
			createHandler(parts);
		} else if (parts[0].equals("deposit")) {
			depositHandler(parts);
		}
	}

	public void createHandler(String[] parts) {
		int accountId = Integer.parseInt(parts[2]);
		double apr = Double.parseDouble(parts[3]);
		if (parts[1].equals("checking")) {
			bank.createCheckingAccount(accountId, apr);
		} else if (parts[1].equals("savings")) {
			bank.createSavingsAccount(accountId, apr);
		} else if (parts[1].equals("cd")) {
			double balance = Double.parseDouble(parts[4]);
			bank.createCDAccount(accountId, apr, balance);
		}
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
