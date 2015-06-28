package main;

import view.LunchFrame;
import models.AccountPool;

import controls.Control;

public class LunchMain 
{
	
	public static void main( String [] args){
    	
		 LunchFrame view = new LunchFrame();
		 view.setVisible( true );
		 AccountPool accPool = new AccountPool();

		 Control control = new Control(view, accPool);
		 
		 control.init();
		 
		 control.start();
    	
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