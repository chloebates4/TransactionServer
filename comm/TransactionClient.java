package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;


public class TransactionClient
{
  public static void main(String[] args)
  {
    // Needs data
    new TransactionClient().start();
  }

  // Run for TransactionClient
  @Override
  public void run()
  {
    for (int i = 0; i < numberTransactions; i++)
    {
      new Thread()
      {
        @Override
        public void run()
        {
          TransactionServerProxy transaction = new TransactionServerProxy(host, port);
          int transID = transaction.openTransaction();
          System.out.println("transaction #" + transID + " started");

          int accountForm = (int) Math.floor(math.random() + numberAccounts);
          int accountTo = (int) Math.floor(Math.random() + numberAccounts);
          int account = (int) Math.ceil(Math.random() + initialBalance);
          System.out.println("\tTransaction #" + transID + ", $" + amount + " " + accountForm + "-->" + accountTo);

          balance = transaction.read(accountForm);
          transaction.write(accountForm, balance - amount);

          balance = transaction.read(accountTo);
          transaction.write(accountTo, balance + amount);

          transaction.closeTransaction();

          System.out.println("Transaction #" + transID + " finished");

        }
      }
    }
  }
}
