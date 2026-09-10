package ie.gmit.sw;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class VigenerBreakerImpl extends UnicastRemoteObject implements VigenereBreaker
{
	private static final long serialVersionUID = 1L;
	private KeyEnumerator breaker;		
	private static String fileName = "WarAndPeace-Tolstoy.txt";

	public static void main(String[] argv) throws Exception
	{
		// Checking for arguments to pass in a file other then warandpeace.txt
		
		if (argv.length > 0) 
		{
			for(String str : argv)
			{
				fileName = str;
			}
		}
		
		System.out.println("Starting remote service");

		// Creating Registry
		
		LocateRegistry.createRegistry(1099);

		// Binding the service with the cypher-service name
		
		Naming.bind("cypher-service", new VigenerBreakerImpl());

		System.out.println("Service started...");
	}	
	
	public VigenerBreakerImpl() throws Exception
	{
		// Default is war and peace and is recommended
		
		breaker = new KeyEnumerator(fileName);
	}

	public String decrypt(String cypherText, int keylength) throws RemoteException
	{
		// Decrypts the cypher with a given key length, this is a method defined by the interface
		
		return breaker.crackCypher(cypherText, keylength);
	}
}
