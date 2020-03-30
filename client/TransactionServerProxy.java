package transaction.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import comm.Message;
import comm.MessageTypes;

public class TransactionServerProxy implements MessageTypes
{
  String host = null;
  int port;

  private Socket dbConnection = null;
  private ObjectOutputStream writeToNet = null;
  private ObjectInputStream readFromNet = null;
  private Integer transactionID = 0;

  TransactionServerProxy(String hostString, int hostPort)
  {
    this.host = hostString;
    this.port = hostPort;
  }

  public int openTransaction()
  {
    // Should be ~18 lines
    return 0;
  }

  public void closeTransaction()
  {
    // Should be ~14 lines
  }

  // This Method handles requested read operations
  public int read(int accountNumber)
  {

    Message readMessage = new Message(READ, accountNumber);
    Integer returnBalance = null;

    try
    {

       writeToNet.writeObject(readMessage);
       returnBalance = (Integer) readFromNet.readObject();

    }
    catch(Exception ex)
    {

      System.out.println("[TransactionServerProxy.read] Error Occurred");
      ex.printStackTrace();
    }

    return returnBalance;

  }

  // This function is designed to handle write requests
  public int write( int accountNumber, int amount )
  {
    //Make a Ibject with accountNumber & amount here
    new Object messageObject(accountNumber, amount);
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

      System.out.println("[TransactionServerProxy.write] Error Occurred");
      ex.printStackTrace();
    }

    return newReturnBalance;
  }

}
