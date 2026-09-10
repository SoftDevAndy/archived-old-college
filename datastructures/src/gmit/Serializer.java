/*
 * 
 * In this class my Serializer calls upon the Secret Class.
 * The Secret Class is just a placeholder really for a 
 * StringBuffer/String simulating my own file object
 * 
 */

package gmit;

import java.io.*;

public class Serializer
{
	private static String serializedFile;	
	private static BufferedReader fileIn;
	
	public Serializer(String fileN)
	{
		serializedFile = fileN;
	}
	
    public void SerThisFile()
    {
    	try
        {
           FileOutputStream outFile = new FileOutputStream(serializedFile + ".ser");           
           ObjectOutputStream out = new ObjectOutputStream(outFile);    
           
           fileIn = new BufferedReader(new FileReader(new File(serializedFile)));
           
           StringBuffer temp = new StringBuffer();
           String s;
           
           while((s = fileIn.readLine()) != null)
		   {
        	   temp.append(s);
		   }	
			
		   fileIn.close();
		   
		   Secret secrets = new Secret(temp); 	   
           
           out.writeObject(secrets);
           out.close();
           outFile.close();
           
           System.out.println("File serialized");
        }
        catch(Exception e)
        {
           System.out.println("Problem writing object to file..");
        }    	
    	
    }// SerThisFile
    
    public void DerSerThisFile()
    {
		Secret s = null;
		
		try
		{
		   FileInputStream fileIn = new FileInputStream(serializedFile + ".ser");
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   
		   s = (Secret) in.readObject();
		   
		   in.close();
		   fileIn.close();
		   
		   System.out.println("Deserialized");
		   
		   FileParser stuff = new FileParser("Secret.txt");
		   stuff.WriteWords(s.getText());		   
		}
		catch(Exception e)
		{
			System.out.println("Problem Deserializing");
		}	
		
    }// DerSerThisFile
	
}// Serializer
