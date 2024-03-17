package banking;

public class WithdrawCommandProcessor {
	private final Bank bank;

	public WithdrawCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void withdrawHandler(String[] parts) {
		int accountID = Integer.parseInt(parts[1]);
		double amount = Double.parseDouble(parts[2]);

		bank.getAccountById(accountID).withdraw(amount);
	}
}
