package server.transaction;

import java.util.ArrayList;
// Import Packages
import server.lock.Lock; 


public class Transaction {

  public int transID;
  ArrayList<Lock> locks = null;

  StringBuffer log = new StringBuffer("");

  Transaction(int transID) {
    this.transID = transID;
    this.locks = new ArrayList();
  }

  public int getID() {
    return transID;
  }

  public ArrayList<Lock> getLocks() {
    return locks;
  }

  public void addLock(Lock lock) {
    locks.add(lock);
  }

  public void log(String str) {

    System.out.println("Transaction # " + this.getID() + "\n");

  }

  public StringBuffer getLog() {
    return log;
  }

}
