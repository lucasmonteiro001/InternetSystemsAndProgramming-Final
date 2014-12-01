package model;

public class BankModel {
	Account account;

	public BankModel(Account acc) {

		this.account = acc;
	}

	public Boolean isTransactionAllowed(double totalCost)
			throws NullPointerException, NumberFormatException {

		if (account.getBalance() - totalCost >= 0) {
			account.setBalance(account.getBalance() - totalCost);
			return true;
		} else {
			return false;
		}

	}
}
