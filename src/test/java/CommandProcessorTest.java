import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {
	private CommandProcessor commandProcessor;
	private Bank bank;
	private Input input;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandProcessor = new CommandProcessor(bank);
		input = new Input();
	}

	@Test
	void create_checking_account() {
		String[] parts = input.splitStr("create checking 12345678 0.01");
		commandProcessor.handle(parts);

		assertEquals(12345678, bank.getAccounts().get(12345678).getId());
		assertEquals(0.01, bank.getAccounts().get(12345678).getAPR());
	}

	@Test
	void create_savings_account() {
		String[] parts = input.splitStr("create savings 2345678 0.02");
		commandProcessor.handle(parts);

		assertEquals(2345678, bank.getAccounts().get(2345678).getId());
		assertEquals(0.02, bank.getAccounts().get(2345678).getAPR());
	}

	@Test
	void create_cd_account() {
		String[] parts = input.splitStr("create cd 3456789 0.03 1000");
		commandProcessor.handle(parts);

		assertEquals(3456789, bank.getAccounts().get(3456789).getId());
		assertEquals(0.03, bank.getAccounts().get(3456789).getAPR());
		assertEquals(1000.00, bank.getAccounts().get(3456789).getBalance());
	}

	@Test
	void deposit_into_an_empty_checking_account() {
		bank.createCheckingAccount(12345678, 0.01);
		String[] commandArgs = input.splitStr("deposit 12345678 200");
		commandProcessor.handle(commandArgs);

		assertEquals(200, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	void deposit_into_an_existing_checking_account() {
		bank.createCheckingAccount(12345678, 0.01);
		Account account = bank.getAccountById(12345678);
		account.deposit(50);

		String[] commandArgs = input.splitStr("deposit 12345678 100");
		commandProcessor.handle(commandArgs);

		assertEquals(150, bank.getAccounts().get(12345678).getBalance());
	}

	@Test
	void deposit_into_an_empty_savings_account() {
		bank.createCheckingAccount(23456789, 0.02);
		String[] commandArgs = input.splitStr("deposit 23456789 1000.23");
		commandProcessor.handle(commandArgs);

		assertEquals(1000.23, bank.getAccounts().get(23456789).getBalance());
	}

	@Test
	void deposit_into_an_existing_savings_account() {
		bank.createCheckingAccount(23456788, 0.01);
		Account account = bank.getAccountById(23456788);
		account.deposit(59.5);

		String[] commandArgs = input.splitStr("deposit 23456788 100");
		commandProcessor.handle(commandArgs);

		assertEquals(159.5, bank.getAccounts().get(23456788).getBalance());
	}
}
