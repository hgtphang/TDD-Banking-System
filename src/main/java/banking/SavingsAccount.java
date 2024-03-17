package banking;

public class SavingsAccount extends Account {
	public SavingsAccount(int id, double apr) {
		super(id, apr);
	}

	@Override
	public void pass(int months) {
		setAge(months);
		alreadyWithdrawn = false;
	}
}