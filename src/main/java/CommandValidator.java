public class CommandValidator {
	public boolean validate(String str) {
		String[] parts = str.split(" ");
		if (parts[0].equals("create")) {
			if (parts[1].equals("checking") || parts[1].equals("savings") || parts[1].equals("cd")) {
				return true;
			}
		}
		return false;
	}

}
