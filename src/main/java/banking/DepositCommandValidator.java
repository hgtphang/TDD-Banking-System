package banking;

public class DepositCommandValidator {
	private final Bank bank;

	public DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (isValidDeposit(parts)) {
			return isValidDepositForAccountType(parts);
		}

		return false;
	}

	private boolean isValidDeposit(String[] parts) {
		return checkAccountIdIsValid(parts) && checkAccountExistsInBank(parts) && !checkIfCDAccount(parts);
	}

	private boolean isValidDepositForAccountType(String[] parts) {
		if (isCheckingAccount(parts)) {
			return isValidCheckingDeposit(parts);
		} else if (isSavingsAccount(parts)) {
			return isValidSavingsDeposit(parts);
		}
		return false;
	}

	private boolean isValidCheckingDeposit(String[] parts) {
		return checkAmountIsInValid(parts) && checkAmountIsNotNegative(parts)
				&& checkDepositCheckingMaximumAllowed(parts);
	}

	private boolean isValidSavingsDeposit(String[] parts) {
		return checkAmountIsInValid(parts) && checkAmountIsNotNegative(parts)
				&& checkDepositSavingsMaximumAllowed(parts);
	}

	public boolean isCheckingAccount(String[] parts) {
		return isAccountOfType(parts, CheckingAccount.class);
	}

	public boolean isSavingsAccount(String[] parts) {
		return isAccountOfType(parts, SavingsAccount.class);
	}

	public boolean checkIfCDAccount(String[] parts) {
		return isAccountOfType(parts, CDAccount.class);
	}

	public boolean isAccountOfType(String[] parts, Class<? extends Account> accountType) {
		Account account = bank.getAccounts().get(Integer.parseInt(parts[1]));
		return accountType.isInstance(account);
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
		return Double.parseDouble(parts[2]) <= checkingMaxAmountAllowed;
	}

	public boolean checkDepositSavingsMaximumAllowed(String[] parts) {
		double savingsMaxAmountAllowed = 2500;
		return Double.parseDouble(parts[2]) <= savingsMaxAmountAllowed;
	}

	public boolean checkAccountExistsInBank(String[] parts) {
		return bank.getAccounts().containsKey(Integer.parseInt(parts[1]));
	}
}
