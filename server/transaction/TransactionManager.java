package server.transaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList; 
import java.util.Scanner;
import java.util.concurrent.Executors;
// packages
import comm.Message;
import comm.MessageTypes;
import server.TransactionServer; 

/**
 * Transaction Manager - as the name implies, has 
 * oversight of all transactions and spawns Transaction 
 * Manager Workers to handle incoming transactions.
 */
public class TransactionManager implements MessageTypes {

    private static final ArrayList<Transaction> transactions = new ArrayList<>(); 

    public TransactionManager() 
    {
        //nothing
    }

    public ArrayList<Transaction> getTransaction() {
        return transactions; 
    }

    public void runTransaction(Socket client) {
        new TransactionManagerWorker(client).start(); 
    }

    public class TransactionManagerWorker extends Thread {

        Socket client = null; 
        ObjectInputStream read = null; 
        ObjectOutputStream write = null; 

        private TransactionManagerWorker(Socket client) {
            client = client;
            ObjectInputStream reading; 
            ObjectOutputStream writing; 
            try {
                reading = new ObjectInputStream(client.getInputStream()); 
                writing = new ObjectOutputStream(client.getOutputStream()); 
            } catch(IOException e) {
                System.out.println("blahhhh"); 
                e.printStackTrace(); 
                System.exit(1);
            }
        }

        @Override
        public void run() {
            //todo
        }


    }
}
 