public class CommandValidator {
	public boolean validate(String str) {
		String[] parts = str.split(" ");

		// --- Testing for Deposit ---
		if (parts[0].equals("deposit")) {
			if (parts.length != 3) {
				return false;
			}
			return true;
		}

		// --- Testing for Create ---
		if (parts.length != 4) {
			return false;
		}

		// parts[0]
		if (!(parts[0].equals("create") || parts[0].equals("deposit"))) {
			return false;
		}

		// parts[1]
		if (!(parts[1].equals("savings") || parts[1].equals("checking") || parts[1].equals("cd"))) {
			return false;
		}

		// parts[2]
		try {
			Integer.parseInt(parts[2]);
		} catch (NumberFormatException e) {
			return false;
		}

		// parts[3]
		try {
			Double.parseDouble(parts[3]);
		} catch (NumberFormatException e) {
			return false;
		}

		// testing cd account missing balance and negative balance
		if (parts[1].equals("cd")) {
			if (parts.length != 5) {
				return false;
			}
			try {
				double balance = Double.parseDouble(parts[4]);
				if (balance < 0) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}

		// all checks passed, command is valid
		return true;
	}
}
