package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
		assertEquals(0.01, bank.getAccountById(12345678).getAPR());
	}

	@Test
	void create_savings_account() {
		commandProcessor.handle("create savings 2345678 0.02");

		assertEquals(2345678, bank.getAccounts().get(2345678).getId());
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
	void withdraw_from_checking_account() {
		bank.createCheckingAccount(12345678, 1.2);
		Account account = bank.getAccountById(12345678);
		account.deposit(100);

		commandProcessor.handle("withdraw 12345678 50");

		assertEquals(50, bank.getAccountById(12345678).getBalance());
	}

	@Test
	void withdraw_zero_amount_from_checking_account() {
		bank.createCheckingAccount(12345678, 1.2);
		Account account = bank.getAccountById(12345678);
		account.deposit(100);

		commandProcessor.handle("withdraw 12345678 0");

		assertEquals(100, bank.getAccountById(12345678).getBalance());
	}

	@Test
	void withdraw_twice_from_checking_account() {
		bank.createCheckingAccount(12345678, 1.2);
		Account account = bank.getAccountById(12345678);
		account.deposit(100);

		commandProcessor.handle("withdraw 12345678 50");
		commandProcessor.handle("withdraw 12345678 20");

		assertEquals(30, bank.getAccountById(12345678).getBalance());
	}

	@Test
	void withdraw_from_savings_account() {
		bank.createSavingsAccount(23456789, 2.1);
		Account account = bank.getAccountById(23456789);
		account.deposit(200);

		commandProcessor.handle("withdraw 23456789 50");

		assertEquals(150, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void withdraw_zero_amount_from_savings_account() {
		bank.createSavingsAccount(23456789, 2.1);
		Account account = bank.getAccountById(23456789);
		account.deposit(300);

		commandProcessor.handle("withdraw 23456789 0");

		assertEquals(300, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void withdraw_twice_from_savings_account() {
		bank.createSavingsAccount(23456789, 2.1);
		Account account = bank.getAccountById(23456789);
		account.deposit(400);

		commandProcessor.handle("withdraw 23456789 20");
		commandProcessor.handle("withdraw 23456789 50");

		assertEquals(330, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void withdraw_from_cd_account() {
		bank.createCDAccount(34567891, 3.2, 5000);

		commandProcessor.handle("withdraw 34567891 5000");

		assertEquals(0, bank.getAccountById(34567891).getBalance());
	}

	@Test
	void transfer_from_checking_to_savings_account() {
		bank.createCheckingAccount(12345678, 1.2);
		bank.createSavingsAccount(23456789, 2.1);

		bank.depositById(12345678, 200);

		commandProcessor.handle("transfer 12345678 23456789 150");

		assertEquals(50, bank.getAccountById(12345678).getBalance());
		assertEquals(150, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void transfer_from_savings_to_checking_account() {
		bank.createCheckingAccount(12345678, 1.2);
		bank.createSavingsAccount(23456789, 2.1);

		bank.depositById(23456789, 500);

		commandProcessor.handle("transfer 23456789 12345678 100");

		assertEquals(100, bank.getAccountById(12345678).getBalance());
		assertEquals(400, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void transfer_from_checking_to_checking_account() {
		bank.createCheckingAccount(12345678, 1.2);
		bank.createCheckingAccount(23456789, 1.3);

		bank.depositById(12345678, 100);
		bank.depositById(23456789, 200);

		commandProcessor.handle("transfer 12345678 23456789 100");

		assertEquals(0, bank.getAccountById(12345678).getBalance());
		assertEquals(300, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void transfer_from_savings_to_savings_account() {
		bank.createSavingsAccount(12345678, 2.1);
		bank.createSavingsAccount(23456789, 3.1);

		bank.depositById(12345678, 50);
		bank.depositById(23456789, 100);

		commandProcessor.handle("transfer 12345678 23456789 20");

		assertEquals(30, bank.getAccountById(12345678).getBalance());
		assertEquals(120, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void transfer_twice_from_checking_to_savings_account() {
		bank.createCheckingAccount(12345678, 1.2);
		bank.createSavingsAccount(23456789, 2.1);

		bank.depositById(12345678, 200);

		commandProcessor.handle("transfer 12345678 23456789 50");
		commandProcessor.handle("transfer 12345678 23456789 70");

		assertEquals(80, bank.getAccountById(12345678).getBalance());
		assertEquals(120, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void transfer_twice_from_savings_to_checking_account() {
		bank.createCheckingAccount(12345678, 1.2);
		bank.createSavingsAccount(23456789, 2.1);

		bank.depositById(23456789, 500);

		commandProcessor.handle("transfer 23456789 12345678 100");
		commandProcessor.handle("transfer 23456789 12345678 200");

		assertEquals(300, bank.getAccountById(12345678).getBalance());
		assertEquals(200, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void transfer_twice_from_checking_to_checking_account() {
		bank.createCheckingAccount(12345678, 1.2);
		bank.createCheckingAccount(23456789, 1.3);

		bank.depositById(12345678, 100);
		bank.depositById(23456789, 200);

		commandProcessor.handle("transfer 12345678 23456789 30");
		commandProcessor.handle("transfer 12345678 23456789 20");

		assertEquals(50, bank.getAccountById(12345678).getBalance());
		assertEquals(250, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void transfer_twice_from_savings_to_savings_account() {
		bank.createSavingsAccount(12345678, 2.1);
		bank.createSavingsAccount(23456789, 3.1);

		bank.depositById(12345678, 50);
		bank.depositById(23456789, 100);

		commandProcessor.handle("transfer 12345678 23456789 20");
		commandProcessor.handle("transfer 12345678 23456789 20");

		assertEquals(10, bank.getAccountById(12345678).getBalance());
		assertEquals(140, bank.getAccountById(23456789).getBalance());
	}

	@Test
	void pass_one_month_when_balance_equals_zero() {
		bank.createCheckingAccount(12345678, 1.2);

		commandProcessor.handle("pass 1");

		assertNull(bank.getAccountById(12345678));
	}

	@Test
	void pass_one_month_when_balance_below_100() {
		bank.createCheckingAccount(12345678, 1.2);
		bank.getAccountById(12345678).deposit(50);

		commandProcessor.handle("pass 1");

		assertEquals(25.025, bank.getAccountById(12345678).getBalance());
	}

	@Test
	void pass_one_month_when_balance_above_100() {
		bank.createCheckingAccount(12345678, 3);
		bank.getAccountById(12345678).deposit(1000);

		commandProcessor.handle("pass 1");

		assertEquals(1002.5, bank.getAccountById(12345678).getBalance());
	}

	@Test
	void pass_two_month_when_balance_above_100() {
		bank.createCheckingAccount(12345678, 3);
		bank.getAccountById(12345678).deposit(1000);

		commandProcessor.handle("pass 2");

		assertEquals(1005.01, bank.getAccountById(12345678).getBalance(), 2);
	}

	@Test
	void cd_account_pass_one_month_when_balance_above_100() {
		bank.createCDAccount(12345678, 2.1, 2000);

		commandProcessor.handle("pass 1");

		assertEquals(2014.04, bank.getAccountById(12345678).getBalance(), 2);
	}
}
