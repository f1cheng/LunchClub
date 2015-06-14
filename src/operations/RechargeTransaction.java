package operations;

import java.util.ArrayList;
import java.util.Map;

import models.Account;
import models.AccountPool;
import models.TransactionRecord;

public class RechargeTransaction extends TransactionOperation {

	public RechargeTransaction(Map<String, Account> loadAccounts, ArrayList<TransactionRecord> transactions)
	{
		super(loadAccounts, transactions);
	}
	
	@Override
	public void updateBalance() {
		// TODO Auto-generated method stub
		for ( int i = 0; i < transactions.size(); i++)
		{
			//accountPool.getAccountByName(transactions.get(i).getAccountName()).deposit(transactions.get(i).getTransactionAmount());;
			accounts.get(transactions.get(i).getAccountName()).deposit(transactions.get(i).getTransactionAmount());
		}
	}

}
