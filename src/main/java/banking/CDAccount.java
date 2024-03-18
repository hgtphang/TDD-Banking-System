package banking;

public class CDAccount extends Account {
	public CDAccount(int id, double apr, double balance) {
		super(id, apr, balance);
	}

	@Override
	public void pass(int months) {
		setAge(months);
		if (getAge() >= 12) {
			alreadyWithdrawn = false;
		}
	}
}