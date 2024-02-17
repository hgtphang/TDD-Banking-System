public class CommandValidator {
	public boolean validate(String str) {
		String[] parts = str.split(" ");
		if (parts[0].equals("create")) {
			return true;
		}
		return false;
	}
}
