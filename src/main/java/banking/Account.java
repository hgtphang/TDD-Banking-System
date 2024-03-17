package banking;

public abstract class Account {
	protected boolean alreadyWithdrawn;
	private int id;
	private double apr;
	private double balance;
	private int age = 0;

	public Account(int id, double apr) {
		this.id = id;
		this.apr = apr;
	}

	public Account(int id, double apr, double balance) {
		this.id = id;
		this.apr = apr;
		this.balance = balance;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int months) {
		this.age += months;
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
		this.balance += depositAmount;
	}

	public void withdraw(double withdrawAmount) {
		alreadyWithdrawn = true;
		if (withdrawAmount >= this.balance) {
			this.balance = 0;
		} else {
			this.balance -= withdrawAmount;
		}
	}

	public void pass(int months) {
		setAge(months);
	}
}