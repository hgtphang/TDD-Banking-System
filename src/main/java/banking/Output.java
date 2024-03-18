package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Output {
	private CommandStorage commandStorage;
	private List<String> outputList;
	private banking.Bank bank;

	public Output(banking.Bank bank, CommandStorage commandStorage) {
		this.bank = bank;
		this.commandStorage = commandStorage;
	}

	public List<String> getFormattedOutput() {
		outputList = new ArrayList<>();
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		decimalFormat.setRoundingMode(RoundingMode.FLOOR);

		// Sort accounts by ID
		List<banking.Account> sortedAccounts = new ArrayList<>(bank.getAccounts().values());
		Collections.sort(sortedAccounts, Comparator.comparingInt(banking.Account::getId));

		for (Account currentAccount : sortedAccounts) {
			String accountInfo = "";

			// Add account's current state string
			if (currentAccount instanceof SavingsAccount) {
				accountInfo = accountInfo.concat("Savings ");
			} else if (currentAccount instanceof CheckingAccount) {
				accountInfo = accountInfo.concat("Checking ");
			} else if (currentAccount instanceof CDAccount) {
				accountInfo = accountInfo.concat("Cd ");
			}

			// Add account ID, balance, and APR
			accountInfo = accountInfo.concat(String.valueOf(currentAccount.getId()));
			accountInfo = accountInfo.concat(" ");

			String roundedBalance = decimalFormat.format(currentAccount.getBalance());
			accountInfo = accountInfo.concat(roundedBalance);
			accountInfo = accountInfo.concat(" ");

			String roundedApr = decimalFormat.format(currentAccount.getAPR());
			accountInfo = accountInfo.concat(roundedApr);

			// Add account state to output list
			if (!accountInfo.isEmpty()) {
				outputList.add(accountInfo);
			}

			// Add transactions
			for (String transaction : currentAccount.getTransactions()) {
				outputList.add(transaction);
			}

		}

		// Add invalid commands
		if (commandStorage != null) {
			for (String invalidCommand : commandStorage.getInvalidCommands()) {
				outputList.add(invalidCommand);
			}
		}

		return outputList;
	}

}
