class CommandValidator {

	private final Bank bank;

	public CommandValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (parts[0].equalsIgnoreCase("create")) {
			CreateCommandValidator createCommandValidator = new CreateCommandValidator(bank);
			return createCommandValidator.validate(str);
		} else if (parts[0].equalsIgnoreCase("deposit")) {
			DepositCommandValidator depositCommandValidator = new DepositCommandValidator(bank);
			return depositCommandValidator.validate(str);
		} else {
			return false;
		}

	}
}