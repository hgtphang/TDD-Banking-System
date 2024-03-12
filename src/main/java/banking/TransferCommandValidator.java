package banking;

public class TransferCommandValidator {
	private final Bank bank;

	public TransferCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (checkTransferCommandHasFourArguments(parts)) {
			if (isValidTransfer(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]))) {
				return true;
			}
		}

		return false;
	}

	public boolean checkTransferCommandHasFourArguments(String[] parts) {
		return (parts.length == 4);
	}

	public boolean isValidTransfer(int from, int to) {
		Account fromAccount = bank.getAccounts().get(from);
		Account toAccount = bank.getAccounts().get(to);

		return (fromAccount instanceof CheckingAccount || fromAccount instanceof SavingsAccount)
				&& (toAccount instanceof CheckingAccount || toAccount instanceof SavingsAccount);
	}
}
