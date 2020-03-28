package server.transaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
// packages
import comm.Message;
import comm.MessageTypes;
import server.TransactionServer; 

public class TransactionManager implements MessageTypes {

    private static int countOfTransactions = 0; 
    private static final ArrayList<Transaction> transactions = new ArrayList<>(); 

    public TransactionManager() {

    }

    public ArrayList<Transaction> getTransaction() {
        return transactions; 
    }

    public void runTransaction(Socket client) {
        new TransactionManagerWorker(client).start(); 
    }

    public class TransactionManagerWorker extends Thread {

        boolean flag = true; // flags if transaction is closed
        Transaction transaction = null; 
        Socket client = null; 
        ObjectInputStream read = null; 
        ObjectOutputStream write = null; 
        Message message = null; 
        int accountNum = 0; 
        int accountBalance = 0; 

        private TransactionManagerWorker(Socket client) {
            client = client;

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
            
        }


    }
}
 