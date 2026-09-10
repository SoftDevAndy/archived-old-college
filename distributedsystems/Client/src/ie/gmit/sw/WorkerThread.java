package ie.gmit.sw;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;

// This worker thread is used to go off and decypt the text
// Once its done it updates it on the outQ

public class WorkerThread implements Runnable 
{
	private MessageRequest messageRequest;
	private HashMap<Long,String> outQ;
	
	public WorkerThread(MessageRequest messageRequest,HashMap<Long, String> outQ)
	{
		System.out.println("Thread Created");
		
		this.messageRequest = messageRequest;
		this.outQ = outQ;
		
		try 
		{
			decrypt();
		} 
		catch (MalformedURLException e)
		{			
			e.printStackTrace();
		}
		catch (RemoteException e) 
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		System.out.println("Thread Running");
	}
	
	public void decrypt() throws MalformedURLException, RemoteException
	{
		System.out.println("Decrypt");
		
		String result = null;
		
		if(System.getSecurityManager() == null)
		{
			//System.setSecurityManager(new RMISecurityManager());
		}
			
		VigenereBreaker vb = null;
		
		try 
		{
			vb = (VigenereBreaker)Naming.lookup("rmi://localhost/cypher-service"); // The RMI service
		} 
		catch (NotBoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			System.out.println("Trying this"); 
			
			result = vb.decrypt(messageRequest.getMessage(), messageRequest.getKeyLenght()); // decrypting the message with given key lenght
			outQ.replace(messageRequest.getJob(),result); // replaces the empty string from the outQ hashmap with a decypted string
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
}
