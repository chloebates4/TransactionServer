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
    // 18 lines
    return 0;
  }

  public void closeTransaction()
  {
    // 14 lines
  }

  public int read(int accountNumber)
  {
    // 15 lines
    return 0;
  }

  public int write( int accountNumber, int amount )
  {
    //16 lines
    return 0;
  }

}
