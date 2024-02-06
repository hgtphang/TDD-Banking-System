public class Account {
	private double apr = 0.02;
	private double accountBalance = 0;

	public Account(double initialBalance) {
		deposit(initialBalance);
	}

	public double getAPR() {
		return apr;
	}

	public double deposit(double depositAmount) {
		accountBalance += depositAmount;
		return accountBalance;
	}

	public double withdraw(double withdrawAmount) {
		if (withdrawAmount > accountBalance) {
			accountBalance = 0;
		} else {
			accountBalance -= withdrawAmount;
		}
		return accountBalance;
	}

	public double getBalance() {
		return accountBalance;
	}

//	public abstract double deposit();
//
//	public abstract double withdraw();
//
//	public abstract double getBalance();
}
