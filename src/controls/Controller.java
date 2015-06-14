package controls;
/*
 * data from GUI's list write into dataDBO, then update to GUI query list again.
 */

//import view.LunchFrame;
import main.LunchMain;
import models.Account;
import models.AccountPool;
import models.TransactionRecord;
import models.FileDB;
import operations.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

//import javax.swing.JTable;
//import javax.swing.table.*;

public class Controller implements ActionListener {

	private LunchMain lunch;
	private TransactionOperation transOperation;

	
	private ArrayList<TransactionRecord> inputTransactionRec = new ArrayList<TransactionRecord>();
	
	public Controller(LunchMain lunch){
		this.lunch = lunch;
	}
	
	/*
	 * Frame's control funs should be migrated to Controller !!!
	 * */
	//for add accounts
	public  void fetchAccountsToPoolFromInputTable()
	{
		Account account;
		double amount = 0;
		int accIndex = 0;
		
		DefaultTableModel tableModel3 = (DefaultTableModel)(lunch.frame.table_1.getModel());
		int rowCount = tableModel3.getRowCount();

		System.out.println(rowCount);

		
		for(int i=0; i<rowCount; i++)
		{
			if (tableModel3.getValueAt(i,0).toString().equals(null) || 
			    tableModel3.getValueAt(i,0).toString().equals(""))
			{
				System.out.println("continue="+i);
				continue;
			}
			
			if (!tableModel3.getValueAt(i, 1).toString().equals(""))
			{
				amount = Double.valueOf(tableModel3.getValueAt(i, 1).toString());
			}
			
			account = new Account(tableModel3.getValueAt(i, 0).toString(), amount);
			lunch.pool.joinAccount(account);
			
			System.out.println("fill account"+i);
		}
		
		
		System.out.println("fill account end");
		
	}

	public void setAccoutPoolToTable()
	{

		DefaultTableModel outputModel = (DefaultTableModel)(lunch.frame.table.getModel());
        //refresh 
		outputModel.setRowCount(0);
		Map<String, Account> accounts_2 = lunch.pool.getAccounts();

		for (Map.Entry<String, Account> entry : accounts_2.entrySet())
		{
			outputModel.addRow(new Object[]{ entry.getKey(), 
					                         entry.getValue().getBalance(), 
					                         entry.getValue().getTotalRecharge(), 
					                         entry.getValue().getTotalExpense()});
		}
		
	}
	
	
	
	//join accounts
	public void joinAccountsAction()
	{
		fetchAccountsToPoolFromInputTable();
    	setAccoutPoolToTable();


	}
	
	public void loadAccountsToInputTable()
	{
  		DefaultTableModel outputModel = (DefaultTableModel)(lunch.frame.table_1.getModel());
    
		outputModel.setRowCount(0);
		Map<String, Account> accounts_2 = lunch.pool.getAccounts();

		for (Map.Entry<String, Account> entry : accounts_2.entrySet())
		{
			outputModel.addRow(new Object[]{ entry.getKey(), 0});
		}
 
	}
	

	public void fetchTransactionRecFromInputTable(String transactionType)
	{
		ArrayList<TransactionRecord> recs = new ArrayList<TransactionRecord>();
		double amount;
		DefaultTableModel tableModel3 = (DefaultTableModel)(lunch.frame.table_1.getModel());
		int rowCount = tableModel3.getRowCount();

		System.out.println(rowCount);
		
		int j = 0;
		for(int i=0; i<rowCount; i++)
		{

			if (tableModel3.getValueAt(i, 1).toString().length() == 0)
				amount = 0;
			else 
				amount = Double.valueOf(tableModel3.getValueAt(i, 1).toString());

			if (amount != 0)
			{
			    recs.add(j, new TransactionRecord(tableModel3.getValueAt(i, 0).toString(), amount, transactionType));
			    System.out.println("fill"+j);
			    j++;
			}
			System.out.println(i);
		}
		
		inputTransactionRec = recs;
		System.out.println("fillrecEnd");
		
	}
	
	public void setTransactionRecsToTable()
	{
		DefaultTableModel outputModel = (DefaultTableModel)(lunch.frame.table.getModel());
		int rowCount = inputTransactionRec.size();

		for(int i=0; i<rowCount; i++)
		{
		    outputModel.addRow(new Object[]{inputTransactionRec.get(i).getAccountName(), inputTransactionRec.get(i).getTransactionAmount()});
		}
		
	}	
	
	
	
	//add expense
	public void calculateDailyExpenseAction()
	{
		fetchTransactionRecFromInputTable("Expense");

    	
    	transOperation = new ExpenseTransaction(lunch.pool.getAccounts(), inputTransactionRec);
    	transOperation.updateBalance();
    	
    	System.out.println("joining2");
    	System.out.println(transOperation.getAccounts().size());
    	
    	lunch.pool.appendAccounts(transOperation.getAccounts());
    	
    	System.out.println("joining3");
    	System.out.println(lunch.pool.getAccounts().size());
    	
    	setAccoutPoolToTable();
    	transOperation.writeTransactions(lunch.db, "Expense");
    	
    	System.out.println("joining4");
		
		
		// setTransactionRecsToTable --- later for display
		
	}
	
	//add recharging
	public void makeRechargeAction()
	{
		System.out.println("joining Recharging");
		fetchTransactionRecFromInputTable("Recharge");
    	
    	transOperation = new RechargeTransaction(lunch.pool.getAccounts(), inputTransactionRec);
    	transOperation.updateBalance();			
    	lunch.pool.appendAccounts(transOperation.getAccounts());
    	
    	transOperation.writeTransactions(lunch.db, "Recharge");
    	
    	System.out.println(lunch.pool.getAccounts().size());
    	setAccoutPoolToTable();
    	System.out.println("joining Rechargeed");
	}
	

	//accumulateBalance
	public void accumulateBalance()
	{

    	double totBalance = lunch.pool.getPoolBalance();
    	lunch.frame.textField.setText(String.valueOf(totBalance));
	}
	
	public void actionPerformed(ActionEvent e) {
        System.out.println("actions");



        /* Menu 1 */
        if (e.getActionCommand().equals("Join")){
        	//this.saleBtnAction();
        	System.out.println("actions1");
        	lunch.frame.btnNewButton.setText("Join Lunch");
        	lunch.frame.contentPane.setVisible(true);
        	
        } 
        /* Action */
        else if (e.getActionCommand().equals("Join Lunch")){
        	//this.saleBtnAction();
        	System.out.println("joining");
        	
        	joinAccountsAction();
        	//lunch.db.writeAccountsToFile(lunch.pool.getAccounts());
        	lunch.pool.writeAccounts(lunch.db);
        	//lunch.db.loadAccountsFromFile();
    		
        	
        	System.out.println("joined");
        	
        }
        
        /* Menu 2 */
        else if (e.getActionCommand().equals("Expense")){
        	
        	loadAccountsToInputTable();
        	//this.saleBtnAction();
        	System.out.println("actions2");
        	lunch.frame.btnNewButton.setText("Add Expense");
        	lunch.frame.contentPane.setVisible(true);
        	
        }
        /* Action */
        else if (e.getActionCommand().equals("Add Expense"))
        {
        	System.out.println("Expensing");
        	calculateDailyExpenseAction();
        	lunch.pool.writeAccounts(lunch.db);
        	System.out.println("Expensed");
        	

        }
        
        /* Menu 3 */
        else if (e.getActionCommand().equals("Recharging")){
        	loadAccountsToInputTable();
            System.out.println("Recharging menu");
        	lunch.frame.contentPane.setVisible(true);
        	lunch.frame.btnNewButton.setText("Add Recharging");


        }
        /* Action */
        else if (e.getActionCommand().equals("Add Recharging"))
        {
        	System.out.println("Rechargebegin");
        	makeRechargeAction();
        	lunch.pool.writeAccounts(lunch.db);
        	//writeRechargeRecsToFile;
        	System.out.println("Rechargeend");

        }
        /*
         * data from GUI's list write into dataDBO, then update to GUI query list again.
         */
        accumulateBalance();
		
	}
}
