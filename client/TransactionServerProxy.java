package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// import java.net.Socket;
import comm.Message;
import comm.MessageTypes;

/**
 * represents the server on the client side, implements and 
 * advertises the four operations of the transactional API, 
 * i.e. read/write and openTransaction/closeTransaction. 
 * It's life span is exactly the life span of one transaction, 
 * which in our case is doing two pairs of read/write operations 
 * on a randomly chosen pair of accounts, transferring a 
 * random dollar amount.
 * 
*/
public class TransactionServerProxy implements MessageTypes
{
  String host = null;
  int port;

  // private Socket dbConnection = null;
  private ObjectOutputStream writeToNet = null;
  private ObjectInputStream readFromNet = null;
  private Integer transactionID = 0;

  TransactionServerProxy(String hostString, int hostPort)
  {
    this.host = hostString;
    this.port = hostPort;
  }

  public void abortTransmission()
  {
      
  }
  // This method handles opening a transaction with the server
  public int openTransaction()
  {
      //Starts a new transaction and delivers a unique TID trans. 
      // This identifier will be used in the other operations in the transaction.
    return 0;
  }

  // This method handles closing a transaction with the server
  public void closeTransaction()
  {
      // Ends a transaction: a commit return value indicates that the 
      // transaction has committed; an abort return value indicates that it has aborted
  }

  // This Method handles requested read operations
  public int read(int acctNum)
  {

    Message readMessage = new Message(READ, acctNum);
    Integer returnBalance = null;

    try
    {

       writeToNet.writeObject(readMessage);
       returnBalance = (Integer) readFromNet.readObject();

    }
    catch(Exception ex)
    {

      System.out.println("Error ");
      ex.printStackTrace();
    }

    return returnBalance;

  }

  // This function is designed to handle write requests
  public int write( int accountNumber, int amount )
  {
    // Make a Object with accountNumber & amount here
    Object messageObject = new Object(accountNumber, amount);
    Message writeMessage = new Message (WRITE, messageObject);
    // Var name is changed here to reflect that the blance after the write
    // is stored and returned
    Integer newReturnBalance = null;

    try
    {
      writeToNet.writeObject(writeMessage);
      Integer newReturnBalance = (Integer) readFromNet.readObject();
    }
    catch(Exception ex)
    {

      System.out.println("Error");
      ex.printStackTrace();
    }

    return newReturnBalance;
  }

}
