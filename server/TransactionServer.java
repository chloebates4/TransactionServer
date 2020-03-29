package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class TransactionServer
{

  public static boolean transactionView;
  public static AccountManager accountManager = null;
  public static TransactionManager transactionManager = null;
  public static LockManager lockManager = null;

  static ServerSocket serverSocket = null;

  public TransactionServer(String serverPropertiesFile)
  {
    Propertiers serverProperties = null;

    try
    {
      serverProperties = new PropertyHandler(serverPropertiesFile);
    } catch (Exception e)
    {
      System.out.println("(TransactionServer.TransactionServer) Did not find properties file \"" + serverPropertiesFile + "\"");
      e.printStackTrace();
      System.exit(1);
    }

    // transaction manager creation
    transactionView = Boolean.valueOf(serverProperties.getProperty("TRANSACTION_VIEW"));
    TransactionServer.transactionManager = new TransactionManager();
    System.out.println("(TransactionServer.TransactionServer) TransactionManager created");

    // lock manager creation
    boolean applyLocking = Boolean.valueOf(serverProperties.getProperty("APPLY_LOCKING"));
    TransactionServer.lockManager = new LockManager(applyLocking);
    System.out.println("(TransactionServer.TransactionServer) LockManager created");

    // account manager creation
    int numberAccounts = 0;
    numberAccounts = Integer.parseInt(serverProperties.getProperty("NUMBER_ACCOUNTS"));
    int initialBalance = 0;
    initialBalance = Integer.parseInt(serverProperties.getProperty("INITIAL_BALANCE"));

    TransactionServer.accoutnManager = new AccountManager(numberAccounts, initialBalance);
    System.out.println("(TransactionServer.TransactionServer) AccountManager created");


    // server socket  creation
    try
    {
      serverSocket = new ServerSocket(Integer.parseInt(serverProperties.getProperty("PORT")));
      System.out.println("(TransactionServer.TransactionServer) ServerSocket created");
      System.exit(1);
    } catch (IOException ex)
    {
      System.out.println("(TransactionServer.TransactionServer) could not create server socket");
      ex.printStackTrace();
      System.exit(1);
    }
  }

  @Override
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
        System.out.println("(TransactionServer.run) Warning: Error accepting the client");
        ex.printStackTrace();
      }
    }
  }
