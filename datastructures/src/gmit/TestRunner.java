//
//
//

package gmit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TestRunner
{
	private static String codeDictionary = "CommonWords.txt";
	private static String zimmerman = "WarPeace.txt";
	private static String decodeName = "Decoded.txt";
	
	private static long totalStart,totalTaken;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner console = new Scanner(System.in);		
		int choice = 0;	
		
		DisplayMenu();
		
		System.out.print("\nMake a choice: ");
		choice = console.nextInt();
		
		while(choice != 9)
		{			
			switch(choice)
			{
				case 1:
					RunProgram();
					break;
				case 2:
					Duplicates();
					break;	
				case 3:
					OneTimePass();
					break;
				default:
					System.out.println("Bad choice..go again");
			}
			
			DisplayMenu();
			
			System.out.print("\nMake a choice: ");
			choice = console.nextInt();				
		};
		
		ExitText();
		console.close();		
	}
	
	private static void RunProgram() throws FileNotFoundException
	{
		long tempTime;
		
		totalStart = System.currentTimeMillis();
		
		tempTime = System.currentTimeMillis();
		
		CodeBuhk codeBook = new CodeBuhk(codeDictionary); // Creating the code book		
		
		System.out.printf("Creating the code book: %d ms from %s",(System.currentTimeMillis() - tempTime),codeDictionary);
		
		/*
		 * 
		 *  The above generates a code book from the 900 common words by  
		 * 
		 */
				
		tempTime = System.currentTimeMillis();
		
		DecodeBuhk decodeBook = new DecodeBuhk(codeBook); // Creating the decode book
		
		System.out.printf("\nCreating the decode book: %d ms",(System.currentTimeMillis() - tempTime));
		
		/*
		 * 
		 *  The above generates a decode book from the code book.
		 *  It takes the code book and for each keyset(word) picks a
		 *  random code value for it and assigns it to the decodebook
		 * 
		 */
		
		tempTime = System.currentTimeMillis();				
		
		FileEncoder fileEnc = new FileEncoder(zimmerman,codeBook,decodeBook);
		
		fileEnc.EncodeFile();
		
		System.out.printf("\nFile Encoder: %d ms",(System.currentTimeMillis() - tempTime)); // Encoding the File
		
		/*
		 * 
		 *  Reads in a file, creates codes for the words not in the code book yet, then writes the encoded message to file
		 * 
		 */
		
		tempTime = System.currentTimeMillis();
		
		FileDecoder fileDec = new FileDecoder(decodeBook,decodeName);
		
		fileDec.DecodeFile();
		
		System.out.printf("\nFile Decoder: %d ms",(System.currentTimeMillis() - tempTime)); // Decoding the File
		
		/*
		 * 
		 *  Uses the decode book as a look up while reading in the 5 digit integers and decoding them to a file names "Decode.txt" 
		 * 
		 */
			
		System.out.printf("\nTotal run time: %d ms \n",(System.currentTimeMillis() - totalStart));	
		
		/*
		 * 
		 * ###########									###########
		 * ###########		E X T R A  --- S T U F F	###########
		 * ###########									###########
		 * 
		 */
		
		/*
		 	tempTime = System.currentTimeMillis();
			
			BookWriter book = new BookWriter(codeBook);
			book.PrintCodeBook();
			
			System.out.printf("\nBookWriter: %d ms",(System.currentTimeMillis() - tempTime)); // Encoding the File

		 // Prints out the code book passed to it.
		 // Can be either the code or decode book 
		  
		*/
		
		
		/*
			Serializer fun = new Serializer(zimmerman);
			fun.SerThisFile();
			fun.DerSerThisFile();

		 // Creates a serialized version of the file sent to it.
		 // Threw this method in to show that I've learned to
		 // do it through the learning process with this project.
		 
		*/	
		
		/*
			try
			{
				 BasicOneTimePass vPass = new BasicOneTimePass();
				 vPass.basicEncryption(zimmerman);
			}
			catch(Exception e)
			{
				
			}
	
		//  Does a very basic one time pass on whatever file is passed to it
		
		*/
		
		/*
		
		Dupe dupeFind = new Dupe();
		
		try
		{
			dupeFind.findDupe(codeDictionary);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	    // Checks a file passed to to it for duplicates.
	 
	    */

		
	}// RunProgram
	
	private static void DisplayMenu()
	{
		System.out.println("\n*************************************");
		System.out.println("**************  Menu  **************");
		System.out.println("*************************************");
		System.out.println("*** 1) Just run the program!        *");
		System.out.println("*** 2) Find duplicate words in file *");
		System.out.println("*** 3) Basic One key pass...        *");
		System.out.println("*** 9) Exit this project! I'm done! *");
		System.out.println("*************************************");
		System.out.println("*************************************");
	}
	
	private static void OneTimePass() throws IOException 
	{
		totalStart = System.currentTimeMillis();
		
		BasicOneTimePass basicPass = new BasicOneTimePass();
		basicPass.basicEncryption(zimmerman);
		
		totalTaken = System.currentTimeMillis() - totalStart;
		
		System.out.printf("\nEntire program runtime is %-4d ms \n",totalTaken);		
	}
	
	private static void ExitText()
	{
		System.out.println("\nExiting the program.....Have a good day...\n");
	}
	
	private static void Duplicates() throws IOException
	{
		Dupe dupe = new Dupe();
		dupe.findDupe(codeDictionary);
	}	
	
}// TestRunner
