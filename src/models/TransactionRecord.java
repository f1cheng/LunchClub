package models;

import java.util.Date;

public class TransactionRecord {
	
	public TransactionRecord(String name, double amount, String type)
	{
		this.accountName = name;
		this.amount = amount;
		this.date = new Date();
		this.type = type;
		
	}
	
	public void setTransactionType(String type)
	{
		this.type = type;
	}
	
	public String getAccountName()
	{
		return accountName;
	}
	
	public String getTransactionType()
	{
		return type;
	}
	
	public double getTransactionAmount()
	{
		return amount;
	}

	public Date getDate()
	{
		return date;
	}
	
	private String accountName;
	private double amount;
	private Date   date;
	private String type;

}
