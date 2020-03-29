package server.account;

import java.util.ArrayList;
// Import packages

public class AccountManager implements LockTypes
{
  private static ArrayList<Account> accounts;
  private static int numberAccounts;
  private static int initialBalance;

  public class AccountManager(int numberAccounts, int initialBalance)
  {
    accounts = new ArrayList();
    AccountManager.numberAccounts = numberAccounts;
    AccountManager.initialBalance = initialBalance;

    for(int i = 0; i < numberAccounts; i++)
    {
      accounts.add(i, new Account(i, initialBalance));
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

    (TransactionServer.lockManager).lock(account, transaction, WRITE_LOCK);

    return (getAccount(accountNumber)).getBalance();
  }

  public int write(int accountNumber, Transaction transaction, int balance)
  {
    Account account = getAccount(accountNumber);

    (TransactionServer.lockManager).lock(account, transaction, WRITE_LOCK);

    account.setBalance(balance);
    return balance;
  }
}
