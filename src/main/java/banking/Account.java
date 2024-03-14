package banking;

public abstract class Account {
	private int id;
	private double apr;
	private double balance;

	public Account(int id, double apr) {
		this.id = id;
		this.apr = apr;
	}

	public Account(int id, double apr, double balance) {
		this.id = id;
		this.apr = apr;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public double getAPR() {
		return apr;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double depositAmount) {
		balance += depositAmount;
	}

	public void withdraw(double withdrawAmount) {
		if (withdrawAmount > balance) {
			balance = 0;
		} else {
			balance -= withdrawAmount;
		}
	}
}