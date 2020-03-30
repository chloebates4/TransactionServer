package server.lock;

import java.util.Iterator; 
import java.util.HashMap; 
import java.util.ArrayList;
// import transaction packages: 
import server.transaction.Transaction; 
import server.account.Account; 

/**
 * (See page 696, 697 in book)
 * "Strict two-phase locking: 
 *    When an operation accesses an object within a transaction:
 *       (a) If the object is not already locked, it is locked 
 *       	 and the operation proceeds.
 *       (b) If the object has a conflicting lock set by another 
 *       	 transaction, the transaction must wait until it is unlocked.
 *       (c)If the object has a non-conflicting lock set by another 
 *        	transaction, the lock is shared and the operation proceeds.
 *        (d)If the object has already been locked in the same transaction, 
 *        	 the lock will be promoted if necessary and the operation proceeds. 
 *        	 (Where promotion is prevented by a conflicting lock, rule b is used.)"
 *
 */
public class Lock implements LockTypes
{
    private final Account account; // the object being protected by the lock
    
    private int acctLockType; //current type

    private final ArrayList<Transaction> holders; // the TIDs of current holders

   
    private final HashMap<Transaction, Object[]> lockRequesters;

    public Lock(Account account)
    {
        holders = new ArrayList(); 
        lockRequesters = new HashMap(); 
        account = new Account(account.getNumber()); 
        acctLockType = account.getNumber(); 
    }
    
        
    public synchronized void acquire (Transaction trans, int newLockType)
    {

        trans.log("TRY: account #: " + account.getNumber() 
                        + "\n     type: " + getLockTypeString(newLockType)
                        + "\n     @Lock - acquire()");

        /*another transaction holds the lock in conflicting mode*/
        while (isConflict(trans, newLockType)) 
        {
            try 
            {
                trans.log("TRYING: account #: " + account.getNumber() 
                                + "\n     type: " + getLockTypeString(newLockType)
                                + "\n     @Lock - acquire()");

                
                //from book 
                wait(); 

                trans.log("WAITING, REMOVE: account #: " + account.getNumber() 
                        + "\n     type: " + getLockTypeString(newLockType)
                        + "\n     @Lock - acquire()"); 
            }
            catch (InterruptedException e)
            {
                System.out.println("caught InterruptedException"); 
            }

            if (holders.isEmpty())
            {
                // no TIDs hold lock
                
                holders.add(trans); 
                acctLockType = newLockType; 
                
                trans.addLock(this); 

                trans.log("NEW: account #: " + account.getNumber() 
                + "\n     type: " + getLockTypeString(newLockType)
                + "\n     @Lock - acquire()"); 
            }
            /*another transaction holds the lock, share it*/
            else if (holders.contains(trans))
            {
                trans.log("SHARE: account #: " + account.getNumber() 
                + "\n     type: " + getLockTypeString(newLockType)
                + "\n     @Lock - acquire()"); 
                
                if (!trans.equals(holders))
                {
                    //this transaction not a holder
                    holders.add(trans); 
                }
            }
            /*this transaction is a holder but needs a more exclusive lock*/
            else if(acctLockType != READER && acctLockType != READER )
            {
                trans.log("NEW: account #: " + account.getNumber() 
                + "\n     type: " + getLockTypeString(newLockType)
                + "\n     @Lock - acquire()"); 
            }
            
        }


    }

    public Account getAccount()
    {
        return account;
    }


    public int getLockType()
    {
        return acctLockType;
    }

    public String getLockTypeString(int lock)
    {
        if (lock == EMPTY)
        {
            return "EMPTY"; 
        }
        else if (lock == READER)
        {
            return "READER"; 
        }
        else if (lock == WRITER)
        {
            return "WRITER"; 
        }
           
        return ("oops");
    }

    public boolean isConflict(Transaction trans, int newLock)
    {
        if (holders.isEmpty())
            {
                trans.log("CONFLICT: account #: " + account.getNumber() 
                + "\n     type: " + getLockTypeString(newLock)
                + "\n     @Lock - isConflict()"); 
                
                
                return false; 
            }
        
        /*another transaction holds the lock, share it*/
        else if (holders.contains(trans))
        {
            trans.log("CONFLICT: account #: " + account.getNumber() 
            + "\n     type: " + getLockTypeString(newLock)
            + "\n     @Lock - isConflict()"); 

            return false; 
        }
        /*this transaction is a holder but needs a more exclusive lock*/
        else if(acctLockType != READER && acctLockType != READER )
        {
            trans.log("CONFLICT: account #: " + account.getNumber() 
            + "\n     type: " + getLockTypeString(newLock)
            + "\n     @Lock - isConflict()"); 
            
            return false; 
        }
        else 
        {
            return false; 
        }
    }

    public void release(Transaction trans)
    {
        holders.remove(trans); 
    }

	
}
