package view;

import controls.Controller;
import main.LunchMain;
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

public class LunchFrame extends JFrame {

	//private Controller control;
	public LunchMain lunch;
	public JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
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
	public LunchFrame(LunchMain lunch) {
		this.lunch = lunch;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 475);
		setTitle("LunchClub");
		
		String str = "ChengFeiStrtest";
		account = new Account(str, 100);
		lunch.control = new Controller(lunch);
		
		JMenuBar menuBar = new JMenuBar();
		
		menu = new JMenu("Accounts");
        menuItem = new JMenuItem("Join");
        menuItem.addActionListener(lunch.control);
		menu.add(menuItem);
		menuBar.add( menu );
		
		menu = new JMenu("Transaction");
        menuItem_1 = new JMenuItem("Expense");
        
        menuItem_1.addActionListener(lunch.control);
        
        menuItem_1.setSelected(true);
		menu.add(menuItem_1);
        menuItem = new JMenuItem("Recharging");
        menuItem.addActionListener(lunch.control);
		menu.add(menuItem);
		
		menuBar.add( menu );	
		this.setJMenuBar( menuBar);
		
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
		textField.setText("900");
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
		
		/*
		DefaultTableModel tableModel2 = (DefaultTableModel)(table_1.getModel());
		
		tableModel2.addRow(new Object[]{"sitinspring", "35"});
		tableModel2.addRow(new Object[]{"sitinspring", "352"});
		tableModel2.addRow(new Object[]{"sitinspring", "353"});
		
		String a = tableModel2.getValueAt(0, 0).toString();
		System.out.println(a);
		*/
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
		btnNewButton.addActionListener(lunch.control);
		
	}
	
	
	public void showAccountsToTable(Map<String, Account> accountss)
	{
		DefaultTableModel outputModel = (DefaultTableModel)(lunch.frame.table.getModel());

		for (Map.Entry<String, Account> entry : accountss.entrySet())
		{
			outputModel.addRow(new Object[]{entry.getValue().getName(), entry.getValue().getBalance(), entry.getValue().getTotalRecharge(), entry.getValue().getTotalExpense()});
		}

	}	



	

	
	
	
	
}
