package banking;

public class WithdrawCommandValidator {
	private final Bank bank;

	public WithdrawCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		return checkWithdrawHasAllArguments(parts);
	}

	public boolean checkWithdrawHasAllArguments(String[] parts) {
		return parts.length == 3;
	}

}
