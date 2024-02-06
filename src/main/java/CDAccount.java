public class CDAccount extends Account {
	public static final double CD_DEFAULT_BALANCE = 1000.00;
	public double balance = 0;

	public CDAccount(double amount) {
		super(amount);
	}

	public CDAccount() {
		super(CD_DEFAULT_BALANCE);
	}

	@Override
	public double deposit(double depositAmount) {
		return super.deposit(depositAmount);
	}

	@Override
	public double withdraw(double withdrawAmount) {
		if (withdrawAmount > getBalance()) {
			return 0;
		} else {
			return super.withdraw(withdrawAmount);
		}
	}

	@Override
	public double getBalance() {
		return super.getBalance();
	}
}
