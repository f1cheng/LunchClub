package view;


import models.TransactionRecord;

import java.util.ArrayList;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;

import java.awt.Color;

import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.border.CompoundBorder;
//import javax.swing.table.JTableHeader;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.List;



//import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.ScrollPane;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import models.Account;

import java.util.Map;
import java.util.HashMap;

public class LunchFrame extends JFrame {

	//private Controller control;
	public JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItemRecharge;
	private JMenuItem menuItemJoin;
	private JMenuItem menuItemExpense;
	public JTable table = new JTable();
	public JTable table_1 = new JTable();
	public JScrollPane scrollPane;
	private JLabel lblTotalBalance;
	public JTextField textField;
	private JScrollPane scrollPane_1;
	
	public JButton btnNewButton;
	private Account account;

	/**
	 * Create the frame.
	 */
	public LunchFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 475);
		setTitle("LunchClub");
		
		String str = "ChengFeiStrtest";
		account = new Account(str, 100);
		
		JMenuBar menuBar = new JMenuBar();
		
		menu = new JMenu("Accounts");
		menuItemJoin = new JMenuItem("Join");
		menu.add(menuItemJoin);
		menuBar.add( menu );
		
		menu = new JMenu("Transaction");
		menuItemExpense = new JMenuItem("Expense");
        
		//menuItemExpense.setSelected(true);
		menu.add(menuItemExpense);
		
		menuItemRecharge = new JMenuItem("Recharging");
		menu.add(menuItemRecharge);
		
		menuBar.add(menu);	
		this.setJMenuBar(menuBar);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 354, 340);				

		String[] columnNames = {"Name", "Balance", "Total Recharge", "Total Expense"};
		Object[][] data = {
				/*{"f1", "100", "100","1110"},
				{"f2", "100","11", "99"},
				{"f3"},*/
		};
		
		table.setModel(new DefaultTableModel(data, columnNames));
		//table.setValueAt(account.getName(), 0, 0);
		//table.setValueAt(290, 0, 1);
				

		//table.invalidate();
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
				
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFillsViewportHeight(true);
		table.setToolTipText("ddd");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//table.setTableHeader(columnNames);
		//JScrollPane scrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		
		lblTotalBalance = new JLabel("Total Balance");
		lblTotalBalance.setBounds(10, 31, 110, 34);
		
		textField = new JTextField();
		textField.setBounds(117, 31, 110, 34);
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("0");
		textField.setColumns(10);
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(419, 65, 209, 340);
		//scrollPane_1.setViewportView(table_1);
		table_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
				{"", ""},
			},
			new String[] {
				"Name", "Amount"
			}
		));
		
		table_1.validate();
		
		table_1.setPreferredScrollableViewportSize(new Dimension(500, 70));
		
		table_1.setBounds(279, 65, 297, 340);
		//table_1.getColumnModel().getColumn(0);
		//table_1.isCellEditable(0, 0) = false;
		scrollPane_1.add(table_1);
		scrollPane_1.setViewportView(table_1);
		
		scrollPane_1.setVisible(true);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		contentPane.add(lblTotalBalance);
		contentPane.add(textField);
		contentPane.add(scrollPane_1);
		
		btnNewButton = new JButton("Join Lunch");
		btnNewButton.setBounds(419, 31, 209, 34);
		contentPane.add(btnNewButton);

		
	}

	
	public void showTotalBalance(double totalBalance)
	{
		textField.setText(String.valueOf(totalBalance));
	}

	public void updateButtonText(String str)
	{
		btnNewButton.setText(str);
	}
	
	public void addActionListenerButton(ActionListener e)
	{
		btnNewButton.addActionListener(e);
	}

	
	
	public void addActionListenerJoinMenu(ActionListener e)
	{
		menuItemJoin.addActionListener(e);
	}

	public void addActionListenerExpenseMenu(ActionListener e)
	{
		menuItemExpense.addActionListener(e);
	}

	public void addActionListenerRechargeMenu(ActionListener e)
	{
		menuItemRecharge.addActionListener(e);
	}

	

	/* table for view */
	
	public void showAccountsToViewTable(Map<String, Account> accountss)
	{
		DefaultTableModel outputModel = (DefaultTableModel)(table.getModel());
		outputModel.setRowCount(0);
		for (Map.Entry<String, Account> entry : accountss.entrySet())
		{
			outputModel.addRow(new Object[] {
					                          entry.getValue().getName(), 
					                          entry.getValue().getBalance(), 
					                          entry.getValue().getTotalRecharge(), 
					                          entry.getValue().getTotalExpense()
					                         }
			                   );
		}

	}	

	public void showAccountsToInputTable(Map<String, Account> accountss)
	{
		DefaultTableModel outputModel = (DefaultTableModel)(table_1.getModel());
		outputModel.setRowCount(0);
		for (Map.Entry<String, Account> entry : accountss.entrySet())
		{
			outputModel.addRow(new Object[] {
					                          entry.getValue().getName(), 
					                          0
					                         }
			                   );
		}

	}	

	
	public  Map<String, Account> extractAccountsFromInputTable()
	{
		Map<String, Account> accMap = new HashMap<String, Account>();
		Account account;
		double amount = 0;
		
		DefaultTableModel tableModel3 = (DefaultTableModel)(table_1.getModel());
		int rowCount = tableModel3.getRowCount();

		System.out.println(rowCount);

		
		for(int i = 0; i < rowCount; i++)
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
			accMap.put(account.getName(), account);
			
			System.out.println("fill account"+i);
		}
		
		
		System.out.println("fill account end");
		
		return accMap;
		
	}


	
	


	public ArrayList<TransactionRecord> extractTransactionRecFromInputTable(String transactionType)
	{
		ArrayList<TransactionRecord> recs = new ArrayList<TransactionRecord>();
		double amount;
		DefaultTableModel tableModel3 = (DefaultTableModel)(table_1.getModel());
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
		
		System.out.println("fillrecEnd");
		
		return recs;
		
	}
	
	
	public void setInputTableForJoin()
	{
		
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{"", ""},
					{"", ""},
					{"", ""},
					{"", ""},
					{"", ""},
					{"", ""},
				},
				new String[] {
					"Name", "Amount"
				}
			));
		//table_1.validate();
	}
	
	// later for use
	public void showTransactionRecsToTable(ArrayList<TransactionRecord> recs)
	{
		DefaultTableModel outputModel = (DefaultTableModel)(table.getModel());
		int rowCount = recs.size();

		for(int i=0; i<rowCount; i++)
		{
		    outputModel.addRow(new Object[]{recs.get(i).getAccountName(), recs.get(i).getTransactionAmount()});
		}
		
	}	
	
	
	
	
	
}
