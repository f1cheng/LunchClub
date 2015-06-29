package models;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.*;

public class FileDB {
	private Map<String, Account> accounts = new HashMap<String, Account>();
	private ArrayList<TransactionRecord> rechargeRecs;
	private ArrayList<TransactionRecord> expenseRecs;
	
	static String accsFileName = "UML\\accounts.txt";
	static String rechargeFileName = "UML\\recharges.txt";
	static String expenseFileName = "UML\\expenses.txt";
	//File accountFile;
	
	public FileDB()
	{
		File file;
		try 
		{
		    file = new File(accsFileName);
		    if (!file.exists())
		    {
			    file.createNewFile();
			    System.out.println("create " + accsFileName);
		    }
		    
		    file = new File(rechargeFileName);
		    if (!file.exists())
		    {
			    file.createNewFile();
			    System.out.println("create " + rechargeFileName);
		    }
		    
		    file = new File(expenseFileName);
		    if (!file.exists())
		    {
			    file.createNewFile();
			    System.out.println("create " + expenseFileName);
		    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		//accountFile = new File(accsFileName);
		//loadAccountsFromFile();
	}
	
	public void loadAccountsFromFile()
	{
	
		BufferedReader bufferedReader= null;
		try
		{
			bufferedReader = new BufferedReader(new FileReader(accsFileName));
			while (bufferedReader.ready())
			{
				String[] rows = bufferedReader.readLine().split(",");
				Account acc = new Account();
				acc.set(rows[0], Double.valueOf(rows[1]), Double.valueOf(rows[2]), Double.valueOf(rows[3]));
				System.out.println("readout:"+rows[0]);
				accounts.put(acc.getName(), acc);
				//System.out.println("readout:"+rows[0]);
		
			}
			 
		    //StringBuffer strBuffer = new StringBuffer();

			for (Map.Entry<String, Account> entry : accounts.entrySet())
			{
				System.out.println("readout:");
				System.out.println(entry.getValue().getName()+","+entry.getValue().getBalance()+","+entry.getValue().getTotalRecharge()+','+entry.getValue().getTotalExpense());
			}
		    
		}
		catch (FileNotFoundException ex) 
		{
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		finally 
		{
	        try 
	        {
	        	bufferedReader.close();
		    }
	        catch (IOException ex) 
	        {
                ex.printStackTrace();
            }

		}
		
	}
	
	public void loadRechargeRecs()
	{
		
	}
	
	public void loadExpenseRecs()
	{
		
	}
	
	public void writeAccountsToFile(Map<String, Account> accounts)
	{
		//open file
		//write the string: Cheng Fei, 100, 100, 0
		//close file
		BufferedWriter bufferedWriter = null;
		try
		{
		    bufferedWriter = new BufferedWriter(new FileWriter(accsFileName));
		    StringBuffer strBuffer = new StringBuffer();

			for (Map.Entry<String, Account> entry : accounts.entrySet())
			{
				strBuffer.append(entry.getValue().getName()+","+entry.getValue().getBalance()+","+entry.getValue().getTotalRecharge()+','+entry.getValue().getTotalExpense());
				strBuffer.append("\r\n");  
			}
		    bufferedWriter.write(strBuffer.toString());
		    
		}
		catch (FileNotFoundException ex) 
		{
			 System.out.println("writeTransRectoFile not found"+accsFileName);
            ex.printStackTrace();
            System.out.println("writeTransRectoFile not found2"+accsFileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		finally 
		{
	        try 
	        {
		        bufferedWriter.close();
		    }
	        catch (IOException ex) 
	        {
                ex.printStackTrace();
            }

		}

	}

	public void writeTransRecsToFile(ArrayList<TransactionRecord> recs, String filename)
	{
		//open file
				//write the string: Cheng Fei, 100, 100, 0
				//close file
				BufferedWriter bufferedWriter = null;
				try
				{
				    bufferedWriter = new BufferedWriter(new FileWriter(filename, true));
				    StringBuffer strBuffer = new StringBuffer();

				    int len = recs.size();
					for (int i = 0; i < len; i++)
					{
						strBuffer.append(recs.get(i).getAccountName()+","+recs.get(i).getTransactionAmount()+","+recs.get(i).getTransactionType()+','+recs.get(i).getDate());
						strBuffer.append("\r\n");  
					}
				    bufferedWriter.write(strBuffer.toString());
				    System.out.println("writeTransRectoFile"+filename+" "+len);

				}
				catch (FileNotFoundException ex) 
				{
		            ex.printStackTrace();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
				finally 
				{
			        try 
			        {
				        bufferedWriter.close();
				    }
			        catch (IOException ex) 
			        {
		                ex.printStackTrace();
		            }

				}
				

	}
	
	public void writeRechargeRecsToFile(ArrayList<TransactionRecord> rechargeRecs)
	{

		writeTransRecsToFile(rechargeRecs, rechargeFileName);
		
	}
	
	public void writeExpenseRecsToFile(ArrayList<TransactionRecord> expenseRecs)
	{
		writeTransRecsToFile(expenseRecs, expenseFileName);
	}
	
	public Map<String, Account> getAccounts()
	{
		return accounts;
		
	}

	public ArrayList<TransactionRecord> getRechargeRecs()
	{
		return rechargeRecs;
	}
	
	public ArrayList<TransactionRecord> getExpenseRecs()
	{
		return expenseRecs;
	}
}
