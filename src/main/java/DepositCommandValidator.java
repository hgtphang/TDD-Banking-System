public class DepositCommandValidator {

	private final Bank bank;

	public DepositCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		return true;
	}
}
