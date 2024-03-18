package banking;

class CommandValidator {
	private final Bank bank;
	CommandStorage commandStorage;

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
		} else if (parts[0].equalsIgnoreCase("withdraw")) {
			WithdrawCommandValidator withdrawCommandValidator = new WithdrawCommandValidator(bank);
			return withdrawCommandValidator.validate(str);
		} else if (parts[0].equalsIgnoreCase("transfer")) {
			TransferCommandValidator transferCommandValidator = new TransferCommandValidator(bank);
			return transferCommandValidator.validate(str);
		} else if (parts[0].equalsIgnoreCase("pass")) {
			PassCommandValidator passCommandValidator = new PassCommandValidator();
			return passCommandValidator.validate(str);
		} else {
			return false;
		}

	}
}