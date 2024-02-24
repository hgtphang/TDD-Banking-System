class CommandValidator {

	private final Bank bank;

	CommandValidator(Bank bank) {
		this.bank = bank;
	}

	boolean validate(String command) {
		String[] parts = command.split("\\s+");
		if (parts[0].equals("create")) {
			return createValidator(parts);
		} else if (parts[0].equals("deposit")) {
			return depositValidator(parts);
		} else {
			return false;
		}
	}

	public boolean createValidator(String[] parts) {
		if (parts.length < 4) {
			return false;
		}

		if (parts[1].equals("cd") && parts.length < 5) {
			return false;
		}

		try {
			if (parts[1].equals("cd")) {
				double balance = Double.parseDouble(parts[4]);
				if (balance < 0) {
					return false;
				}
			}
		} catch (NumberFormatException e) {
			return false;
		}

		int accountId = Integer.parseInt(parts[2]);
		if (bank.getAccountById(accountId) != null) {
			return false;
		}

		return true;
	}

	public boolean depositValidator(String[] parts) {
		if (parts.length != 3) {
			return false;
		}
		return true;
	}
}
