package banking;

public class PassCommandValidator {
	private final Bank bank;

	public PassCommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		return true;
	}
}
