package server.account;

import java.util.ArrayList;
// Import packages
import server.lock.LockTypes; 
import server.account.Account; 
import server.transaction.Transaction; 

/**
 * Account Manager - initializes accounts and implements 
 * read/write operations, which, most importantly, involves 
 * locking, if locking is active.
 * 
 */
public class AccountManager implements LockTypes
{
  private static ArrayList<Account> accounts;
  private static int numberAccounts;
  private static int initialBalance;

  public AccountManager(int numberAccounts, int initialBalance)
  {
    accounts = new ArrayList();
    AccountManager.numberAccounts = numberAccounts;
    AccountManager.initialBalance = initialBalance;

    for(int i = 0; i < numberAccounts; i++)
    {
      accounts.add(new Account(i));
    }
  }

  public Account getAccount(int accountNumber)
  {
    return accounts.get(accountNumber);
  }

  public ArrayList<Account> getAccounts()
  {
    return accounts;
  }

  public int read(int accountNumber, Transaction transaction)
  {
    Account account = getAccount(accountNumber);


    return (getAccount(accountNumber)).getNumber();
  }

  public int write(int accountNumber, Transaction transaction, int balance)
  {
    Account account = getAccount(accountNumber);


    account.setNumber(balance);
    return balance;
  }
}
