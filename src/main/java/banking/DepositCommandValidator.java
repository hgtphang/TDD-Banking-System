package banking;

public class DepositCommandValidator {
	private final Bank bank;

	public DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (isValidDeposit(parts)) {
			if (isCheckingAccount(parts)) {
				return isValidCheckingDeposit(parts);
			} else if (isSavingsAccount(parts)) {
				return isValidSavingsDeposit(parts);
			}
		}
		return false;
	}

	private boolean isValidDeposit(String[] parts) {
		return checkAccountIdIsValid(parts) && checkAccountExistsInBank(parts) && !checkIfCDAccount(parts);
	}

	private boolean isValidCheckingDeposit(String[] parts) {
		return checkAmountIsInValid(parts) && checkAmountIsNotNegative(parts)
				&& checkDepositCheckingMaximumAllowed(parts);
	}

	private boolean isValidSavingsDeposit(String[] parts) {
		return checkAmountIsInValid(parts) && checkAmountIsNotNegative(parts)
				&& checkDepositSavingsMaximumAllowed(parts);
	}

	// some functions for validate()
	public boolean isCheckingAccount(String[] parts) {
		Account account = bank.getAccounts().get(Integer.parseInt(parts[1]));
		if (account instanceof CheckingAccount) {
			return true;
		}
		return false;
	}

	public boolean isSavingsAccount(String[] parts) {
		Account account = bank.getAccounts().get(Integer.parseInt(parts[1]));
		if (account instanceof SavingsAccount) {
			return true;
		}
		return false;
	}

	public boolean checkIfCDAccount(String[] parts) {
		Account account = bank.getAccounts().get(Integer.parseInt(parts[1]));
		if (account instanceof CDAccount) {
			return true;
		}
		return false;
	}

	public boolean checkAmountIsInValid(String[] parts) {
		try {
			Double.parseDouble(parts[2]);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean checkAmountIsNotNegative(String[] parts) {
		return Double.parseDouble(parts[2]) >= 0;
	}

	public boolean checkAccountIdIsValid(String[] parts) {
		try {
			Integer.parseInt(parts[1]);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean checkDepositCheckingMaximumAllowed(String[] parts) {
		double checkingMaxAmountAllowed = 1000;

		if (Double.parseDouble(parts[2]) > checkingMaxAmountAllowed) {
			return false;
		}

		return true;
	}

	public boolean checkDepositSavingsMaximumAllowed(String[] parts) {
		double savingsMaxAmountAllowed = 2500;

		if (Double.parseDouble(parts[2]) > savingsMaxAmountAllowed) {
			return false;
		}

		return true;
	}

	public boolean checkAccountExistsInBank(String[] parts) {
		return bank.getAccounts().containsKey(Integer.parseInt(parts[1]));
	}

}
