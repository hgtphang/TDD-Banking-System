package banking;

public class PassCommandProcessor {
	private final Bank bank;

	public PassCommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public void passHandler(String[] parts) {
		int months = Integer.parseInt(parts[1]);
		bank.pass(months);
	}
}
