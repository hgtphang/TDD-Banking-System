package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterControlTest {
	MasterControl masterControl;
	List<String> input;
	CommandStorage commandStorage;

	@BeforeEach
	void setUp() {
		input = new ArrayList<>();
		Bank bank = new Bank();
		masterControl = new MasterControl(new CommandValidator(bank), new CommandProcessor(bank), new CommandStorage(),
				new Output(bank, commandStorage));
	}

	private void assertSingleCommand(String command, List<String> actual) {
		assertEquals(1, actual.size());
		assertEquals(command, actual.get(0));
	}

	@Test
	void create_three_account() {
		input.add("create savings 12345678 0.01");
		input.add("create cd 45678901 0.03 2000");
		input.add("creAte cHecKing 98765432 0.01");

		List<String> actual = masterControl.start(input);

		assertEquals(3, actual.size());
		assertEquals("Savings 12345678 0.00 0.01", actual.get(0));
		assertEquals("Cd 45678901 2000.00 0.03", actual.get(1));
		assertEquals("Checking 98765432 0.00 0.01", actual.get(2));
	}

	@Test
	void deposit_to_an_savings_account() {
		input.add("create savings 12345678 0.01");
		input.add("deposit 12345678 200");

		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals("Savings 12345678 200.00 0.01", actual.get(0));
		assertEquals("deposit 12345678 200", actual.get(1));
	}

	@Test
	void create_a_checking_account() {
		input.add("Create checking 12345678 1.2");
		List<String> actual = masterControl.start(input);

		assertEquals(1, actual.size());
		assertEquals("Checking 12345678 0.00 1.20", actual.get(0));
	}

	@Test
	void create_a_savings_account() {
		input.add("Create sAvings 12345678 1.2");
		List<String> actual = masterControl.start(input);

		assertEquals(1, actual.size());
		assertEquals("Savings 12345678 0.00 1.20", actual.get(0));
	}

	@Test
	void deposit_to_a_checking_account() {
		input.add("Create checking 12345678 1.2");
		input.add("Deposit 12345678 200");
		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals("Checking 12345678 200.00 1.20", actual.get(0));
		assertEquals("Deposit 12345678 200", actual.get(1));
	}

	@Test
	void deposit_twice_to_a_checking_account() {
		input.add("Create checking 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Deposit 12345678 300");

		List<String> actual = masterControl.start(input);

		assertEquals(3, actual.size());
		assertEquals("Checking 12345678 1000.00 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Deposit 12345678 300", actual.get(2));
	}

	@Test
	void deposit_twice_to_a_savings_account() {
		input.add("Create savings 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Deposit 12345678 300");

		List<String> actual = masterControl.start(input);

		assertEquals(3, actual.size());
		assertEquals("Savings 12345678 1000.00 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Deposit 12345678 300", actual.get(2));
	}

	@Test
	void withdraw_from_a_savings_account() {
		input.add("Create savings 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Withdraw 12345678 300");

		List<String> actual = masterControl.start(input);

		assertEquals(3, actual.size());
		assertEquals("Savings 12345678 400.00 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Withdraw 12345678 300", actual.get(2));
	}

	@Test
	void withdraw_from_a_checking_account() {
		input.add("Create chEcking 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Withdraw 12345678 200");

		List<String> actual = masterControl.start(input);

		assertEquals(3, actual.size());
		assertEquals("Checking 12345678 500.00 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Withdraw 12345678 200", actual.get(2));
	}

	@Test
	void transfer_from_a_checking_to_savings_account() {
		input.add("Create chEcking 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Create savings 23456789 1.2");
		input.add("Transfer 12345678 23456789 200");
		List<String> actual = masterControl.start(input);

		assertEquals(5, actual.size());
		assertEquals("Checking 12345678 500.00 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Transfer 12345678 23456789 200", actual.get(2));
		assertEquals("Savings 23456789 200.00 1.20", actual.get(3));
		assertEquals("Transfer 12345678 23456789 200", actual.get(4));
	}

	@Test
	void cannot_withdraw_twice_in_one_month_from_savings_account() {
		input.add("Create savings 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Withdraw 12345678 200");
		input.add("Withdraw 12345678 300");
		List<String> actual = masterControl.start(input);

		assertEquals(3, actual.size());
		assertEquals("Savings 12345678 500.00 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Withdraw 12345678 200", actual.get(2));
	}

	@Test
	void withdraw_once_in_a_month_from_savings_account_and_pass_one_month() {
		input.add("Create savings 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Withdraw 12345678 200");
		input.add("Pass 1");
		List<String> actual = masterControl.start(input);

		assertEquals(3, actual.size());
		assertEquals("Savings 12345678 500.25 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Withdraw 12345678 200", actual.get(2));
	}
}
