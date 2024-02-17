public class CommandValidator {
	public boolean validate(String str) {
		String[] parts = str.split(" ");
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
			return false; // parts[2] is not an integer
		}

		// all checks passed, command is valid
		return true;
	}
}
