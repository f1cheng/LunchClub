package operations;

import java.util.ArrayList;
import java.util.Map;

import models.TransactionRecord;
import models.Account;


public class TransactionFactory {
	
	public static TransactionOperation makeTransaction(Map<String, Account> accs, ArrayList<TransactionRecord> tranRecs, String tranType)
	{

		TransactionOperation transOperation;
    	
    	System.out.println(tranType);
    	
    	if (tranType.equals("Expense"))
    	{
    	    transOperation = new ExpenseTransaction(accs, tranRecs);
    	}
    	else
    	{
    		transOperation = new RechargeTransaction(accs, tranRecs);
    	}
    	
    	transOperation.updateBalance();
    	
    	return transOperation;
	}

}
