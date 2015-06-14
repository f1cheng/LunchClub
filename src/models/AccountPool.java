package models;

import java.util.Map;
import java.util.HashMap;


public class AccountPool {

	private Map<String, Account> accounts;
	
	public AccountPool()
	{
	    accounts = new HashMap<String, Account>();
	}
	
	public Map<String, Account> getAccounts()
	{
		return accounts;
	}
	


	public void setAccounts(Map<String, Account> accounts)
	{
		this.accounts = accounts;
	}
	
	public void joinAccount(Account account)
	{
		accounts.put(account.getName(), account);
	}

	public void appendAccounts(Map<String, Account> accounts)
	{
		for (Map.Entry<String, Account> entry : accounts.entrySet())
		{
			this.accounts.put(entry.getKey(), entry.getValue());
		}
	}
	
	public void removeAccount(Account account)
	{
		accounts.remove(account.getName());
	}

	public Account getAccountByName(String name)
	{
		return accounts.get(name);
	}
	
	public double getPoolBalance()
	{
		double total = 0;
		for (Map.Entry<String, Account> entry : accounts.entrySet())
		{
			total += entry.getValue().getBalance();
		}
		return total;
	}
	
	public void loadFromFile(FileDB db)
	{
		db.loadAccountsFromFile();
		accounts = db.getAccounts();
	}

	public void writeAccounts(FileDB db)
	{
		db.writeAccountsToFile(accounts);
		//accounts = db.getAccounts();
	}
	
}
