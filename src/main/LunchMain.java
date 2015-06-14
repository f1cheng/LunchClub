package main;

import view.LunchFrame;
import models.Account;
import models.AccountPool;
import models.FileDB;

import controls.Controller;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JButton;

public class LunchMain implements Runnable {

	public Controller control;
	
	public AccountPool pool = new AccountPool();
	public FileDB db = new FileDB();
	
	public LunchFrame frame;
	
    public void initView()
    {
    	// load the data list from db or file
    	//pool.loadFromFile("Account.TXT");
    	pool.loadFromFile(db);
    	frame.showAccountsToTable(pool.getAccounts());
    	control.accumulateBalance();
    	//db.writeAccountsToFile(pool.getAccounts());
    	
    }
	
    public void run() {

    	frame = new LunchFrame(this);
		
    	frame.setVisible( true );
    	control = new Controller(this);
    	//db.writeAccountsToFile(pool.getAccounts());
    	initView();
		
    }
    
	public static void main( String [] args){
    	
		LunchMain app = new LunchMain();
    	
		//app.init();
    	app.run();
    	
    	System.out.println("Running: left is fixed, right side panel will show only one of below 3 for each action time");
    	System.out.println("Running: left is fixed, right side panel each table is a extend class based on Jtable");
    	System.out.println("Menu trigger right side only show: Add account ");
    	System.out.println("Menu trigger right side only show: Add Expense ");
    	System.out.println("Menu trigger right side only show: Add Recharging ");
    	
    }
}

/*
 * main(LunchClub) include members: control, viewGUI, dataDAO
 * e.g control(LunchClub), viewGUI(LunchClub), dataDAO(lunchClub)
 * 
 */