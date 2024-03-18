package banking;

public class WithdrawCommandValidator {
	private final Bank bank;

	public WithdrawCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (checkWithdrawHasAllArguments(parts)) {
			if (isAccountOfType(parts, CheckingAccount.class)) {
				return checkWithdrawFromCheckingAccount(parts);
			} else if (isAccountOfType(parts, SavingsAccount.class)) {
				return checkWithdrawFromSavingsAccount(parts);
			} else if (isAccountOfType(parts, CDAccount.class)) {
				return checkWithdrawFromCDAccount(parts);
			}
		}

		return false;
	}

	public boolean checkWithdrawHasAllArguments(String[] parts) {
		return parts.length == 3;
	}

	public boolean isAccountOfType(String[] parts, Class<? extends Account> accountType) {
		int accountID = Integer.parseInt(parts[1]);
		Account account = bank.getAccounts().get(accountID);
		return accountType.isInstance(account);
	}

	public boolean checkWithdrawFromCheckingAccount(String[] parts) {
		double amount = Double.parseDouble(parts[2]);
		return (amount >= 0) && (amount <= 400);
	}

	public boolean checkWithdrawFromSavingsAccount(String[] parts) {
		double amount = Double.parseDouble(parts[2]);
		int accountID = Integer.parseInt(parts[1]);

		if (bank.getAccountById(accountID).alreadyWithdrawn == true) {
			return false;
		}

		return (amount >= 0) && (amount <= 1000);
	}

	public boolean checkWithdrawFromCDAccount(String[] parts) {
		double amount = Double.parseDouble(parts[2]);
		int accountID = Integer.parseInt(parts[1]);

		if (bank.getAccountById(accountID).alreadyWithdrawn == true) {
			return false;
		}

		return (amount >= bank.getAccountById(accountID).getBalance());
	}

}
