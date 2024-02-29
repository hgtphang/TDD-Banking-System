public class DepositCommandValidator {
	private final Bank bank;

	public DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		return checkAccountIdIsValid(parts) && checkAccountExistsInBank(parts) && checkDepositToCDIsNotAllowed(parts)
				&& checkAmountIsInValid(parts) && checkAmountIsNotNegative(parts) && checkAccountIdIsValid(parts)
				&& checkDepositCheckingMaximumAllowed(parts) && checkDepositSavingsMaximumAllowed(parts);
	}

	// some functions for validate()
	public boolean checkAmountIsInValid(String[] parts) {
		try {
			Double.parseDouble(parts[2]);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean checkAmountIsNotNegative(String[] parts) {
		return Double.parseDouble(parts[2]) > 0;
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

	public boolean checkDepositToCDIsNotAllowed(String[] parts) {
		Account account = bank.getAccounts().get(Integer.parseInt(parts[1]));
		if (account instanceof CDAccount) {
			return false;
		}
		return true;
	}

}
