package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Output {
	private CommandStorage commandStorage;
	private List<String> output;
	private banking.Bank bank;

	public Output(banking.Bank bank, CommandStorage commandStorage) {
		this.bank = bank;
		this.commandStorage = commandStorage;
	}

	public List<String> getFormattedOutput() {
		output = new ArrayList<>();
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		decimalFormat.setRoundingMode(RoundingMode.FLOOR);

		// Sort accounts by ID
		List<banking.Account> sortedAccounts = new ArrayList<>(bank.getAccounts().values());
		Collections.sort(sortedAccounts, Comparator.comparingInt(banking.Account::getId));

		for (Account currAccount : sortedAccounts) {
			String accountState = "";

			// Add account's current state string
			if (currAccount instanceof SavingsAccount) {
				accountState = accountState.concat("Savings ");
			} else if (currAccount instanceof CheckingAccount) {
				accountState = accountState.concat("Checking ");
			} else if (currAccount instanceof CDAccount) {
				accountState = accountState.concat("Cd ");
			}

			// Add account ID, balance, and APR
			accountState = accountState.concat(String.valueOf(currAccount.getId()));
			accountState = accountState.concat(" ");

			String roundedBalance = decimalFormat.format(currAccount.getBalance());
			accountState = accountState.concat(roundedBalance);
			accountState = accountState.concat(" ");

			String roundedApr = decimalFormat.format(currAccount.getAPR());
			accountState = accountState.concat(roundedApr);

			// Add account state to output list
			if (!accountState.isEmpty()) {
				output.add(accountState);
			}

			// Add transactions
			for (String transaction : currAccount.getTransactions()) {
				output.add(transaction);
			}

		}

		// Add invalid commands
		if (commandStorage != null) {
			for (String invalidCommand : commandStorage.getInvalidCommands()) {
				output.add(invalidCommand);
			}
		}

		return output;
	}

}
