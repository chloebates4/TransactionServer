
package server.account;

/**
 *
 */
public class Account
{
    private int accountNum;

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
