public class SavingsAccount extends Account {
	public static final double SAVINGS_DEFAULT_BALANCE = 0;
	public double balance = 0;

	public SavingsAccount(double amount) {
		super(amount);
	}

	public SavingsAccount() {
		super(SAVINGS_DEFAULT_BALANCE);
	}

	@Override
	public double deposit(double depositAmount) {
		balance += depositAmount;
		return balance;
	}

	@Override
	public double withdraw(double withdrawAmount) {
		if (withdrawAmount > balance) {
			balance = 0;
		} else {
			balance -= withdrawAmount;
		}
		return balance;
	}

	@Override
	public double getBalance() {
		return balance;
	}
}
