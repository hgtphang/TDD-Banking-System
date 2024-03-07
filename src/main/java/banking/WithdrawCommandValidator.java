package banking;

public class WithdrawCommandValidator {
	private final Bank bank;

	public WithdrawCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (checkWithdrawHasAllArguments(parts)) {

			if (isCheckingAccount(parts)) {
				return checkWithdrawFromCheckingAccount(parts);
			} else if (isSavingsAccount(parts)) {
				return checkWithdrawFromSavingsAccount(parts);
			}
		}

		return false;
	}

	public boolean checkWithdrawHasAllArguments(String[] parts) {
		return parts.length == 3;
	}

	public boolean isCheckingAccount(String[] parts) {
		int accountID = Integer.parseInt(parts[1]);
		Account account = bank.getAccounts().get(accountID);
		return account instanceof CheckingAccount;
	}

	public boolean isSavingsAccount(String[] parts) {
		int accountID = Integer.parseInt(parts[1]);
		Account account = bank.getAccounts().get(accountID);
		return account instanceof SavingsAccount;
	}

	public boolean checkWithdrawFromCheckingAccount(String[] parts) {
		double amount = Double.parseDouble(parts[2]);
		return (amount >= 0) && (amount <= 400);
	}

	public boolean checkWithdrawFromSavingsAccount(String[] parts) {
		double amount = Double.parseDouble(parts[2]);
		return (amount >= 0) && (amount <= 1000);
	}

}
