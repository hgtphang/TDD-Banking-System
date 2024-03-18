package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommandProcessorTest {
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

		assertEquals(12345678, bank.getAccountById(12345678).getId());
		assertEquals(0.01, bank.getAccountById(12345678).getAPR());
	}

	@Test
	void create_savings_account() {
		commandProcessor.handle("create savings 2345678 0.02");

		assertEquals(2345678, bank.getAccountById(2345678).getId());
		assertEquals(0.02, bank.getAccountById(2345678).getAPR());
	}

	@Test
	void create_cd_account() {
		commandProcessor.handle("create cd 3456789 0.03 1000");

		assertEquals(3456789, bank.getAccountById(3456789).getId());
		assertEquals(0.03, bank.getAccountById(3456789).getAPR());
		assertEquals(1000.00, bank.getAccountById(3456789).getBalance());
	}

	@Test
	void create_two_accounts() {
		commandProcessor.handle("create checking 12345678 1.2");
		commandProcessor.handle("create savings 23456789 1.3");

		assertEquals(1.2, bank.getAccountById(12345678).getAPR());
		assertEquals(1.3, bank.getAccountById(23456789).getAPR());
	}

	@Test
	void create_an_account_has_upper_letter_in_create_and_account_type() {
		commandProcessor.handle("creAte cHecKing 98765432 0.01");

		assertEquals(98765432, bank.getAccountById(98765432).getId());
		assertEquals(0.01, bank.getAccountById(98765432).getAPR());
	}
}
