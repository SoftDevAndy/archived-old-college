package ie.gmit.sw;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Interface needed for RMI, notice the imports

public interface VigenereBreaker extends Remote
{
	public String decrypt(String cypherText, int keylength) throws RemoteException;
}
