package operations;

import java.util.ArrayList;
import java.util.Map;

import models.Account;
import models.TransactionRecord;

public class ExpenseTransaction extends TransactionOperation {

	public ExpenseTransaction(Map<String, Account> loadAccounts, ArrayList<TransactionRecord> transactions)
	{
		super(loadAccounts, transactions);
	}
	
	 public void updateBalance()
	 {

		System.out.println("trans size="+transactions.size());
		for ( int i = 0; i < transactions.size(); i++)
		{
			//accountPool.getAccountByName(transactions.get(i).getAccountName()).deposit(transactions.get(i).getTransactionAmount());;
			accounts.get(transactions.get(i).getAccountName()).withdraw(transactions.get(i).getTransactionAmount());
		}
	 }
}
