package gmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileEncoder
{
	private final String fileToEncode = "Encoded.txt";
	
	private String fileName;
	private int formatCount = 0;
	
	private CodeBuhk codeDictionary;	
	private DecodeBuhk decodeDictionary;
	private List<String> words = new ArrayList<String>();
	
	private static PrintWriter writer;
	
	public FileEncoder(String fName,CodeBuhk enc,DecodeBuhk dec)
	{
		fileName = fName;           //  File to Encode War and Peace or Zimmerman etc
		codeDictionary = enc;		//  CodeBook
		decodeDictionary = dec;     //  DecodeBook
	}
	
	public void EncodeFile()
	{
		try 
		{
			writer = new PrintWriter(fileToEncode);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 	
		
		try
		{			
			BufferedReader fileIn = new BufferedReader(new FileReader(new File(fileName)));	
			String fileInput;
						
			while((fileInput = fileIn.readLine()) != null)
			{
				String[] longWord = fileInput.split(" ");
				
				for(int i = 0; i < longWord.length; i++)
				{					
					String next = process(longWord[i]);
					if(next != null)
					{
						words.add(next);
					}
					
				}// O(n) Time
				
			}// O(n2) Time
						
			for(String s : words)
			{
				if(codeDictionary.getBook().containsKey(s))
				{
					printWord(s);
				}
				else
				{
					codeDictionary.UnknownWord(s);
					printWord(s);
				}
				
			}//O(n) Time
			
			writer.close();	
			fileIn.close();

		}
		catch(Exception exception)
		{
			System.out.println("Wrong info passed to constructor");
		}		
		
	}// Parses the file Zimmerman/WarandPeace, Checks the codeBook for the current word, if it doesn't exist, call the unknown word code generator
	 // Once it finds the word or generates the 5 digits for the unknown,unique word
	
	private void printWord(String word)
	{
		Random r = new Random();						
		int mx,num;
		
		word = process(word); // cleans up the word 
		
		++formatCount;									

		if(codeDictionary.containsKey(word))
		{
			mx = codeDictionary.getBook().get(word).size();
			num = r.nextInt(mx);	
			
			decodeDictionary.getBook().put(codeDictionary.getBook().get(word).get(num), word);
			
			writer.print(codeDictionary.getBook().get(word).get(num) + " ");		
		}
			
		if(formatCount > 9)								
		{
			writer.printf("%n");
			formatCount = 0;							
		}
		
	}// Pulls a random integer from the code dictionary with the corresponding word, prints that 5 digit code to file, nicely formatted and all. O(n) Time
	
	private String process(String s)
	{		
		String word = s.trim().toLowerCase();
		StringBuffer buf = new StringBuffer();
	
		for(int i = 0; i < word.length();i++)
		{
			if(LegalCharacter(word.charAt(i)))
			{
				buf.append(word.charAt(i));
			}
			else
			{
				break;
			}
			
		}//O(n) Time
		
		return (buf.toString());
		 
	}// And adapted version of your process method. This makes sure the word is made soley up of characters. 
	
	private boolean LegalCharacter(char c)
	{
		final int CHAR_MIN = 97;
		final int CHAR_MAX = 122;
		
		if(c >= CHAR_MIN && c <= CHAR_MAX)
		{
			return true;
		}
		
		return false;		
		
	}// LegalCharacter Checker adapted from yours.
	
}// FileEncoder