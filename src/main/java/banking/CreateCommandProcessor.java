package banking;

public class CreateCommandProcessor {
	private final Bank bank;

	public CreateCommandProcessor(Bank bank) {
		this.bank = bank;
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

}
