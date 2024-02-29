public class WithdrawCommandValidator {
	private final Bank bank;

	public WithdrawCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		return true;
	}
}
