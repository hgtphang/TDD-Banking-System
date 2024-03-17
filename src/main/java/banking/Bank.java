package banking;

import java.util.HashMap;

public class Bank {
	private HashMap<Integer, Account> accounts = new HashMap<>();

	public HashMap<Integer, Account> getAccounts() {
		return accounts;
	}

	public void createCheckingAccount(Integer id, double apr) {
		CheckingAccount account = new CheckingAccount(id, apr);
		accounts.put(id, account);
	}

	public void createSavingsAccount(Integer id, double apr) {
		SavingsAccount account = new SavingsAccount(id, apr);
		accounts.put(id, account);
	}

	public void createCDAccount(Integer id, double apr, double balance) {
		CDAccount account = new CDAccount(id, apr, balance);
		accounts.put(id, account);
	}

	public void addAccount(Account account) {
		int id = account.hashCode();
		accounts.put(id, account);
	}

	public int getNumberOfAccounts() {
		return accounts.size();
	}

	public Account getAccountById(int accountId) {
		return accounts.get(accountId);
	}

	public void depositById(int accountId, double amount) {
		Account account = accounts.get(accountId);
		account.deposit(amount);
	}

	public void withdrawById(int accountId, double amount) {
		Account account = accounts.get(accountId);
		account.withdraw(amount);
	}

	public void transferById(int fromAccountID, int toAccountID, double amount) {
		Account fromAccount = accounts.get(fromAccountID);
		Account toAccount = accounts.get(toAccountID);

		fromAccount.withdraw(amount);
		toAccount.deposit(amount);
	}
}