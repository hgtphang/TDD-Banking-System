import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<Integer, Account> accounts;

	public Bank() {
		this.accounts = new HashMap<>();
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
}