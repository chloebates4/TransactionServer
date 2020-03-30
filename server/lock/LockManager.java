package server.lock;

import java.util.HashMap; 
import java.util.Iterator; 
// import transaction packages: 
import server.transaction.Transaction; 
import server.account.Account; 

/**
 * 
 * Pseudo Code 
 *
 */
public class LockManager implements LockTypes
{
	
	// TODO: 
	private static HashMap<Account, Lock> locks; 
	
	public LockManager ()
	{
		locks = new HashMap<>(); 
	}
	
	
	public void lock(Account account, Transaction trans, int lockType)
	{
		Lock foundlock; 
		synchronized (this)
		{
                        // find the lock associated with object
			foundlock = locks.get(account);
                        
                        // if there isn’t one, create it and add it to the hashtable
			if (foundlock == null)
			{
				foundlock = new Lock(account); 
				locks.put(account, foundlock); 
			
				trans.log("CREATE: account #: " + account.getNumber()
				+ "\n     @LockManager - lock()");   
				
				
			
			}
		}
		
		foundlock.acquire(trans, lockType);
	
	}
		
	/**
	 * 
	 * @param trans
	 */
	public synchronized void unLock(Transaction trans)
	{
                Iterator<Lock> lockMover = trans.getLocks().listIterator(); 

		Lock currLock;		
                // release each lock 
		while (lockMover.hasNext())
		{
			// TODO: Change
			currLock = lockMover.next(); 
			
			trans.log("RELEASED: account #: " + currLock.getAccount() 
					+ "\n     type: " + currLock.getLockType()
					+ "\n     @LockManager - unLock()"); 
					 
			
			currLock.release(trans); 
			
		}
		
	}
	
	/**
	 * getter function
	 * @return list of locks
	 */
	public HashMap<Account, Lock> getLocks() 
	{
		return locks;
	}
}
