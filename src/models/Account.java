package models;

import java.util.Date;

public class Account {

	public Account()
	{
		
	}
	
    public Account(String name, double init_fund)
    {
        this.name = name;
        this.balance = init_fund;
        this.totalExpense = 0;
        this.totalRecharge = this.balance;
        this.dateCreated = new Date();
    }
    
    public String getName()
    {
    	return name;
    }
    
    public void withdraw(double amount)
    {
    	balance -= amount;
    	totalExpense += amount;
    }
    
    public void deposit(double amount)
    {
    	balance += amount;
    	totalRecharge += amount;
    }
    
    public double getTotalExpense()
    {
    	return totalExpense;
    }
    
    public double getTotalRecharge()
    {
    	return totalRecharge;
    }
    
    public double getBalance()
    {
    	return balance;
    }
    
    public void set(String name, double balance, double totalEx, double totalRec)
    {
        this.name = name;
        this.balance = balance;
        this.totalExpense = totalEx;
        this.totalRecharge = totalRec;
        this.dateCreated = new Date();
    }
    
    private String name;
    private double balance = 0;
    private double totalExpense = 0;
    private double totalRecharge = 0;
    private Date   dateCreated;
    
}
