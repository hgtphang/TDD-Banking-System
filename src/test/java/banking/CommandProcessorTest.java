package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
	private CommandProcessor commandProcessor;
	private Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
	}

	@Test
	void create_checking_account() {
		commandProcessor.handle("create checking 12345678 0.01");

		assertEquals(12345678, bank.getAccounts().get(12345678).getId());
		assertEquals(0.01, bank.getAccounts().get(12345678).getAPR());
	}

	@Test
	void create_savings_account() {
		commandProcessor.handle("create savings 2345678 0.02");

		assertEquals(2345678, bank.getAccounts().get(2345678).getId());
		assertEquals(0.02, bank.getAccounts().get(2345678).getAPR());
	}

	@Test
	void create_cd_account() {
		commandProcessor.handle("create cd 3456789 0.03 1000");

		assertEquals(3456789, bank.getAccounts().get(3456789).getId());
		assertEquals(0.03, bank.getAccounts().get(3456789).getAPR());
		assertEquals(1000.00, bank.getAccounts().get(3456789).getBalance());
	}

	@Test
	void deposit_into_an_empty_checking_account() {
		bank.createCheckingAccount(12345678, 0.01);

		commandProcessor.handle("deposit 12345678 200");

		assertEquals(200, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	void deposit_into_an_existing_checking_account() {
		bank.createCheckingAccount(12345678, 0.01);
		Account account = bank.getAccountById(12345678);
		account.deposit(50);

		commandProcessor.handle("deposit 12345678 100");

		assertEquals(150, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	void deposit_into_an_empty_savings_account() {
		bank.createCheckingAccount(23456789, 0.02);

		commandProcessor.handle("deposit 23456789 1000.23");

		assertEquals(1000.23, bank.getAccounts().get(23456789).getBalance());
	}

	@Test
	void deposit_into_an_existing_savings_account() {
		bank.createCheckingAccount(23456788, 0.01);
		Account account = bank.getAccountById(23456788);
		account.deposit(59.5);

		commandProcessor.handle("deposit 23456788 100");

		assertEquals(159.5, bank.getAccounts().get(23456788).getBalance());
	}
}
