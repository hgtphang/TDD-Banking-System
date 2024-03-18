package banking;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
	protected boolean alreadyWithdrawn;
	protected int id;
	protected double apr;
	protected double balance;
	protected String accountType;
	protected int age = 0;
	protected List<String> transactions = new ArrayList<>();

	protected Account(int id, double apr) {
		this.id = id;
		this.apr = apr;
	}

	protected Account(int id, double apr, double balance) {
		this.id = id;
		this.apr = apr;
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int months) {
		this.age += months;
	}

	public Integer getId() {
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

	public List<String> getTransactions() {
		return this.transactions;
	}

	public void addTransactions(String command) {
		this.transactions.add(command);
	}
}