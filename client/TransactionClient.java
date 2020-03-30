package client;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.io.PrintWriter;
import java.io.IOException;

import server.transaction.Transaction;
import client.TransactionServerProxy; 

/**
 * Initializes a Transaction Server Proxy and then, 
 * using the proxy, runs any number of transactions as 
 * specified above.
 */
public class TransactionClient 
{
 private final int num; 
 private final int port; 
 private final String host; 
 
 private TransactionServerProxy transServerProx; 
 
 public TransactionClient (int port, String host, int num) 
 {
     this.host = host; 
     this.port = port; 
     this.num = num; 
 }

  public static void main(String[] args)
  {
    try 
    {
        int port = Integer.parseInt(args[0]);
        String host = args[1]; 
        int num = Integer.parseInt(args[2]);
        TransactionClient client = new TransactionClient(port, host, num);
    }
    catch(Exception e) {}
    
  }

  // Run for TransactionClient
  public void run()
  {
      int i; 

    for ( i = 0; i < num; i++)
    {
      new Thread()
      {
        int balance = 0; 
        @Override
        public void run()
        {
          TransactionServerProxy transaction = new TransactionServerProxy(host, port);
          int transID = transaction.openTransaction();

          int accountForm = (int) Math.floor(Math.random());
          int accountTo = (int) Math.floor(Math.random());
          int account = (int) Math.ceil(Math.random());

          transaction.read(accountForm);
          transaction.write(accountForm);

          balance = transaction.read(accountTo);
          transaction.write(accountTo);

          transaction.closeTransaction();

        }
      }
    }
  }
}

