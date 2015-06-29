package controls;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import view.LunchFrame;
import models.*;
import operations.*;

public class Control {

	private LunchFrame view;
	private AccountPool accPool;
	
	private FileDB db = new FileDB();
	
	public Control(LunchFrame view, AccountPool accPool)
	{
		this.view = view;
		this.accPool = accPool;
	}
	
	public void init()
	{

		accPool.loadFromFile(db);
    	view.showAccountsToViewTable(accPool.getAccounts());
    	//control.accumulateBalance();
    	view.showTotalBalance(accPool.getPoolBalance());
		
	}
	
	
	public void start()
	{
		view.addActionListenerJoinMenu(new ActionListenerJoinMenu());
		view.addActionListenerExpenseMenu(new ActionListenerExpenseMenu());
		view.addActionListenerRechargeMenu(new ActionListenerRechargeMenu());
		
		view.addActionListenerButton(new ActionListenerButton());
	}
	
	
	public class ActionListenerJoinMenu implements ActionListener
	{
	
		public void actionPerformed(ActionEvent e)
		{
        	System.out.println("actions for menu join");
        	view.updateButtonText("Join Lunch");	
        	view.setInputTableForJoin();
        	
		}
	}


	public class ActionListenerExpenseMenu implements ActionListener
	{
	
		public void actionPerformed(ActionEvent e)
		{
        	System.out.println("actions for menu expense");
        	view.updateButtonText("Add Expense");
        	view.showAccountsToInputTable(accPool.getAccounts());
		}
	}

	public class ActionListenerRechargeMenu implements ActionListener
	{
	
		public void actionPerformed(ActionEvent e)
		{
        	System.out.println("actions for menu recharge");
        	view.updateButtonText("Add Recharging");
        	view.showAccountsToInputTable(accPool.getAccounts());
		}
	}
	
	public class ActionListenerButton implements ActionListener
	{

		public TransactionOperation makeTrans(Map<String, Account> accs, String tranType)
		{

        	TransactionOperation transOperation;
        	ArrayList<TransactionRecord> tranRecs;
        	
        	System.out.println(tranType);
        	tranRecs = view.extractTransactionRecFromInputTable(tranType);
        	/*
        	 * TransactionFactory.makeTransaction(accs, tranRecs, tranType);
        	 * 
        	 * */
        	if (tranType.equals("Expense"))
        	{
        	    transOperation = new ExpenseTransaction(accs, tranRecs);
        	}
        	else
        	{
        		System.out.println("rrrrr");
        		transOperation = new RechargeTransaction(accs, tranRecs);
        	}
        	
        	transOperation.updateBalance();
        	
        	return transOperation;
		}
		
		public void actionPerformed(ActionEvent e) 
		{


	        if (e.getActionCommand().equals("Join Lunch"))
	        {
	        	System.out.println("Action for: Join Lunch");
	        	
	            Map<String, Account> accs;
	        	accs = view.extractAccountsFromInputTable();
	        	accPool.appendAccounts(accs);
	        	
	        	view.showAccountsToViewTable(accPool.getAccounts());
	        	accPool.writeAccounts(db);
	        	
	            System.out.println("join operation end");
	        	
	        }
	        else if (e.getActionCommand().equals("Add Expense"))
	        {
	        	System.out.println("Action for: Add Expense");

	        	ArrayList<TransactionRecord> tranRecs;
	        	String tranType = "Expense";
	        	tranRecs = view.extractTransactionRecFromInputTable(tranType);
	        	TransactionOperation transOperation = TransactionFactory.makeTransaction(accPool.getAccounts(), tranRecs, tranType);
	        	 
	        	
	        	//TransactionOperation transOperation = makeTrans(accPool.getAccounts(), "Expense");
	        	accPool.appendAccounts(transOperation.getAccounts());
	        	
	        	view.showAccountsToViewTable(accPool.getAccounts());
	        	
	        	transOperation.writeTransactions(db, "Expense");
	        	accPool.writeAccounts(db);
	        	
	        	System.out.println("Expense operation end");
	        	
	        	
	        }
	        else if (e.getActionCommand().equals("Add Recharging"))
	        {
	        	System.out.println("Action for: Add Recharging");
	        	
	        	ArrayList<TransactionRecord> tranRecs;
	        	String tranType = "Recharge";
	        	tranRecs = view.extractTransactionRecFromInputTable(tranType);
	        	TransactionOperation transOperation = TransactionFactory.makeTransaction(accPool.getAccounts(), tranRecs, tranType);
	        	
	        	//TransactionOperation transOperation = makeTrans(accPool.getAccounts(), "Recharge");
	        	accPool.appendAccounts(transOperation.getAccounts());
	        	
	        	view.showAccountsToViewTable(accPool.getAccounts());
	        	
	        	transOperation.writeTransactions(db, "Recharge");
	        	accPool.writeAccounts(db);	        	
	        	
	        	System.out.println("Recharging operation end");
	        }


	        view.showTotalBalance(accPool.getPoolBalance());
	        
			System.out.println("action end");
			
		}
		
	}
	
	
	
	
}

	

