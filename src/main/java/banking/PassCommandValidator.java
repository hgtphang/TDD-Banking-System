package banking;

public class PassCommandValidator {

	public boolean validate(String str) {
		String[] parts = str.stripTrailing().split(" ");

		if (checkPassCommandHasAllArguments(parts) && checkPassCommandHasRightMonths(parts)) {
			int month = Integer.parseInt(parts[1]);
			return (month > 0) && (month < 61);
		}

		return false;
	}

	public boolean checkPassCommandHasRightMonths(String[] parts) {
		try {
			Integer.parseInt(parts[1]);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean checkPassCommandHasAllArguments(String[] parts) {
		return (parts.length == 2);
	}
}
