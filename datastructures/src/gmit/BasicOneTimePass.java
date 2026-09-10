package gmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class BasicOneTimePass 
{
	private static String unenceFile = "Encoded OneTimePass.txt";
	private static String decodedFile = "Decoded OneTimePass.txt";
	private static String keyCode = "Vernams Key.txt";
	private static final int ALPHA_LEN = 26;
	private static final int ASCII_MOD = 97;
	
	public void basicEncryption(String fileToEncode) throws IOException
	{
		BufferedReader fileIn = new BufferedReader(new FileReader(new File(fileToEncode)));	// file reader	
		PrintWriter fileOut = new PrintWriter(unenceFile); 
		
		ArrayList<Character> scatter = new ArrayList<Character>();
		ArrayList<Character> alpha = new ArrayList<Character>();		
		ArrayList<Character> decodeKey = new ArrayList<Character>();
		
		StringBuilder cryptWord = new StringBuilder();
		StringBuilder unEncryptWord = new StringBuilder();
		String currentWord;
		
		//// Variables Initialized
		
		createAlpha(alpha);

		System.out.println("\nCreating alphabet array");
		
		createScatter(scatter);
		
		System.out.printf("Creating one time passkey %s",keyCode);
		
		printArr(scatter);
		
		System.out.println("Printing one time passkey");
		
		printArr(alpha);
		
		System.out.println("Printing alphabet");
		
		System.out.printf("\nReading from file %s and ENCODING it",unenceFile);
		System.out.printf("\nDECODING and putting UNENCODED text to file %s \n",decodedFile);
		
		while((currentWord = fileIn.readLine()) != null)
		{			
			for (char c : currentWord.toCharArray())
			{
				if(Character.isLetter(c))
				{				
					c = Character.toLowerCase(c);
					
					char appendMe;
					int x = giveAlphabetPosition(c);
					int y = giveAlphabetPosition((char) scatter.get(x));
					
					appendMe = (char)alpha.get(y);		
					
					cryptWord.append(appendMe);
					
				}//for
				else if(Character.isDigit(c))
				{
					cryptWord.append(c);
				}
				else
				{
					cryptWord.append(' ');
				}
			}
			
			cryptWord.append("\n");
			
		}//while
		
		fileOut.print(cryptWord);
		fileIn.close();
		fileOut.close();
		
		/////// Encode Segment
		
		fileIn = new BufferedReader(new FileReader(new File(keyCode)));
		
		while((currentWord = fileIn.readLine()) != null)
		{			
			for (char c : currentWord.toCharArray())
			{
				decodeKey.add(c);
			}
		}	
		
		fileIn.close();
		
		fileIn = new BufferedReader(new FileReader(new File(unenceFile)));
		fileOut = new PrintWriter(decodedFile); 

		while((currentWord = fileIn.readLine()) != null) 
		{			
			for (char c : currentWord.toCharArray())
			{			
				if(Character.isLetter(c))
				{					
					int x = decodeKey.indexOf(c);
					
					char y = alpha.get(x);

					unEncryptWord.append(y);	
				}
				else if(Character.isDigit(c))
				{
					unEncryptWord.append(c);
				}
				else
				{
					unEncryptWord.append(' ');
				}
				
			}
			
			unEncryptWord.append("\n");	
		}	

		fileOut.print(unEncryptWord);
		
		fileOut.close();
		fileIn.close();
	}
	
	private static void createScatter(ArrayList<Character> scatter) throws FileNotFoundException
	{
		PrintWriter keyOut = new PrintWriter(keyCode);
		Random gimme = new Random();
		char letter;
		
		for (int i = 0; i < ALPHA_LEN; i++) 
		{
			int x = gimme.nextInt(ALPHA_LEN);
			letter = (char)(x + ASCII_MOD);
			
			while(scatter.contains(letter))
			{
				x = gimme.nextInt(ALPHA_LEN);
				letter = (char)(x + ASCII_MOD);
			}
			
			scatter.add(letter);
		}		
		
		keyOut.print(scatterKey(scatter));
		keyOut.close();
		
	}// createScatter
	
	private static void createAlpha(ArrayList<Character> alpha)
	{
		char letter;
		
		for (int i = 0; i < ALPHA_LEN; i++) 
		{
			letter = (char)(i + ASCII_MOD);
			alpha.add(letter);
		}
	}
	
	private static String scatterKey(ArrayList<Character> scatter)
	{
		String cryptKey = "";
		
		for(Object a : scatter)
		{
			cryptKey += ((char)a);
		}
		
		return cryptKey;
	}
	
	private static int giveAlphabetPosition(char a)
	{
		a = Character.toLowerCase(a);
		
		return (a - ASCII_MOD);
	}
	
	private static void printArr(ArrayList<Character> scatter)
	{
		System.out.println("\n===================================================");
		System.out.println("======                 Printing                ====");
		System.out.println("===================================================");
		System.out.println("0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5");
		
		for(Object a : scatter)
		{	
			System.out.print(a.toString() + " ");
		}
		
		System.out.println("\n===================================================\n");
		
	}// printScatter
	
}
