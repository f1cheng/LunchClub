package operations;

import models.Account;
import models.AccountPool;
import models.TransactionRecord;
import models.FileDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class TransactionOperation {
	
	//private AccountPool accountPool = new AccountPool();
	protected Map<String, Account> accounts = new HashMap<String, Account>();
	
	protected ArrayList<TransactionRecord> transactions = new ArrayList<TransactionRecord>();
	
	public TransactionOperation(Map<String, Account> loadAccounts, ArrayList<TransactionRecord> transactions)
	{
		this.transactions = transactions;
		System.out.println("trans:size=");
		System.out.println(transactions.size());
		for ( int i = 0; i < transactions.size(); i++)
		{
			//accountPool.joinAccount(loadAccounts.getAccountByName(transactions.get(i).getAccountName()));
			Account acc = loadAccounts.get(transactions.get(i).getAccountName());
			//accounts.put(transactions.get(i).getAccountName(), loadAccounts.getAccountByName(transactions.get(i).getAccountName()));
			//Account account = new Account(transactions.get(i).getAccountName(), transactions.get(i).getTransactionAmount());
			//acc.withdraw(transactions.get(i).getTransactionAmount());
			System.out.println("put");
			accounts.put(transactions.get(i).getAccountName(), acc);

			System.out.println(i);
			System.out.println(accounts.size());
		}
	}
	
	public void setTransactions(ArrayList<TransactionRecord> trans)
	{
		this.transactions = trans;
	}
	
	public ArrayList<TransactionRecord> getTransactions()
	{
		return transactions;
	}
	
	public Map<String, Account> getAccounts()
	{
		return accounts;
	}
	
	public void writeTransactions(FileDB db, String type)
	{
		if (type.equals("Recharge"))
		    db.writeRechargeRecsToFile(transactions);
		else
			db.writeExpenseRecsToFile(transactions);
	}

	public abstract void updateBalance();
}
