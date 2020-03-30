package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
// get transaction imports
import server.account.AccountManager; 
import server.transaction.TransactionManager; 
import server.lock.LockManager;
import utils.PropertyHandler; 
import utils.FileUtils; 

/**
 * Transaction Server - initializes server by reading properties, 
 * according to which all managers are created/initialized and 
 * then runs the (multi-threaded) server loop.
 * 
*/
public class TransactionServer
{

  public static boolean flag;
  public static AccountManager accountManager = null;
  public static TransactionManager transactionManager = null;
  public static LockManager lockManager = null;

  static ServerSocket serverSocket = null;

  public TransactionServer(String serverPropertiesFile)
  {
    Properties serverProperties = null;

    try
    {
      serverProperties = new PropertyHandler(serverPropertiesFile);
    } catch (Exception e)
    {
      System.out.println("no propertes file \"" + serverPropertiesFile + "\"");
      e.printStackTrace();
      System.exit(1);
    }

    // transaction manager creation
    TransactionServer.transactionManager = new TransactionManager();
    System.out.println("CREATED TransactionManager @TransactionServer.TransactionServer");

    // lock manager creation
    boolean applyLocking = Boolean.valueOf(serverProperties.getProperty("APPLY_LOCKING"));
    TransactionServer.lockManager = new LockManager(applyLocking);
    System.out.println("CREATED LockManager @TransactionServer.TransactionServer");

    // account manager creation
    int numAccounts = 0;
    numAccounts = Integer.parseInt(serverProperties.getProperty("NUMBER_ACCOUNTS"));
    
    int initBalance = 0;
    initBalance = Integer.parseInt(serverProperties.getProperty("INITIAL_BALANCE"));

    TransactionServer.accountManager = new AccountManager(numAccounts, initBalance);
    System.out.println("CREATED AccountManager @TransactionServer.TransactionServer");


    // server socket  creation
    try
    {
      serverSocket = new ServerSocket(Integer.parseInt(serverProperties.getProperty("PORT")));
      System.out.println("CREATED ServerSocket @TransactionServer.TransactionServer");
      System.exit(1);
    } catch (Exception ex)
    {
      System.out.println("caught exception");
      ex.printStackTrace();
      System.exit(1);
    }
  }

  public void run()
  {
    // run server loop
    while(true)
    {
      try
      {
        transactionManager.runTransaction(serverSocket.accept());
      } catch (IOException ex)
      {
        System.out.println("caught IOException");
        ex.printStackTrace();
      }
    }
  }
}
