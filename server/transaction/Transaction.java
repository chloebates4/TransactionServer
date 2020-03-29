package server.transaction;

import java.util.ArrayList;
// Import Packages
public class Transaction {

  int transID;
  ArrayList<Lock> locks = null;

  StringBuffer log = new StringBuffer("");

  Transaction(int transID) {
    this.transID = transID;
    this.locks = new ArrayList();
  }

  public int getID() {
    return transID;
  }

  public ArrayList<lock> getLocks() {
    return locks;
  }

  public void addLock(Lock lock) {
    locks.add(lock);
  }

  public void log(String logString) {
    log.append("\n").append(logString);

    if(TransactionServer.transactionView) {
      System.out.println("Transaction # " + this.getID() + ((this.getID() < 10) ? " " : " ") + logString);
    }
  }

  public StringBuffer getLog() {
    return log;
  }

}
