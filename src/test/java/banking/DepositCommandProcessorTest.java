package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandProcessorTest {
	private CommandProcessor commandProcessor;
	private Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	void deposit_into_an_empty_checking_account() {
		bank.createCheckingAccount(12345678, 0.01);

		commandProcessor.handle("deposit 12345678 200");

		assertEquals(200, bank.getAccountById(12345678).getBalance());
	}

	@Test
	void deposit_into_an_existing_checking_account() {
		bank.createCheckingAccount(12345678, 0.01);
		Account account = bank.getAccountById(12345678);
		account.deposit(50);

		commandProcessor.handle("deposit 12345678 100");

		assertEquals(150, bank.getAccountById(12345678).getBalance());
	}

	@Test
	void deposit_into_an_empty_savings_account() {
		bank.createCheckingAccount(23456789, 0.02);

		commandProcessor.handle("deposit 23456789 1000.23");

		assertEquals(1000.23, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void deposit_into_an_existing_savings_account() {
		bank.createCheckingAccount(23456788, 0.01);
		Account account = bank.getAccountById(23456788);
		account.deposit(59.5);

		commandProcessor.handle("deposit 23456788 100");

		assertEquals(159.5, bank.getAccountById(23456788).getBalance());
	}

	@Test
	void deposit_twice_to_a_checking_account() {
		bank.createCheckingAccount(12345678, 1.2);

		commandProcessor.handle("deposit 12345678 300");
		commandProcessor.handle("deposit 12345678 200");

		assertEquals(500, bank.getAccountById(12345678).getBalance());
	}

	@Test
	void deposit_twice_to_a_savings_account() {
		bank.createSavingsAccount(12345678, 1.2);

		commandProcessor.handle("deposit 12345678 300");
		commandProcessor.handle("deposit 12345678 200");

		assertEquals(500, bank.getAccountById(12345678).getBalance());
	}

}
