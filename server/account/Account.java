
package server.account;

/**
 * Represents the account of a user within the transaction server
 * Includes simple getters and setter for essential account information
 */
public class Account
{
    private int accountNum;
    private int balance;
    
    public Account()
    {
        accountNum = 0;
    }

    public Account(int num)
    {
        accountNum = num;
    }

    public int getNumber()
    {
        return accountNum;
    }

    public void setNumber(int num)
    {
         accountNum=num;
    }

    public int getBalance()
    {
         return balance;
    }

    public void setBalance(int newBalance)
    {
         balance = newBalance;
    }
}
