public class CreateCommandValidator {

	private final Bank bank;

	public CreateCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (checkCreateCheckingSavingsHasFourArguments(parts) && checkCreateCheckingSavingsWithNoBalance(parts)) {
			if (checkCreateCheckingAccountType(parts) || checkCreateSavingsAccountType(parts)) {
				return checkAccountIdAndApr(parts);
			}
		} else if (checkCreateCDAccountHasFourArguments(parts) && checkCreateCDWithBalance(parts)) {
			if (checkCreateCDAccountType(parts)) {
				return checkAccountIdAndApr(parts);
			}
		}

		return false;
	}

	// -- checking and savings account --
	public boolean checkCreateCheckingSavingsHasFourArguments(String[] parts) {
		return parts.length == 4;
	}

	public boolean checkCreateCheckingSavingsWithNoBalance(String[] parts) {
		return parts.length != 5;
	}

	public boolean checkCreateCheckingAccountType(String[] parts) {
		return parts[1].equalsIgnoreCase("checking");
	}

	public boolean checkCreateSavingsAccountType(String[] parts) {
		return parts[1].equalsIgnoreCase("savings");
	}

	public boolean checkAccountIdAndApr(String[] parts) {
		try {
			int accountID = Integer.parseInt(parts[2]);
			double apr = Double.parseDouble(parts[3]);
			return checkAccountIdIsInValid(accountID) && checkAccountAprIsInValid(apr)
					&& checkAnEmptyAccount(accountID);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean checkAccountIdIsInValid(int accountId) {
		return String.valueOf(accountId).length() == 8;
	}

	public boolean checkAccountAprIsInValid(double apr) {
		return apr >= 0 && apr <= 10;
	}

	public boolean checkAnEmptyAccount(int accountID) {
		return !bank.getAccounts().containsKey(accountID);
	}

	// -- cd account --
	public boolean checkCreateCDAccountHasFourArguments(String[] parts) {
		return parts.length == 5;
	}

	public boolean checkCreateCDAccountType(String[] parts) {
		return parts[1].equalsIgnoreCase("cd");
	}

	public boolean checkCreateCDWithBalance(String[] parts) {
		try {
			double initialBalance = Double.parseDouble(parts[4]);
			return (initialBalance >= 1000 && initialBalance <= 10000);
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
