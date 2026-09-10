/*
 * 
 *  Code Buhk Class .. where the book gets manipulated
 * 
 */

package gmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CodeBuhk 
{
	private static final int MAX = 100000;		// Maximum range for the numbers
	private static final int MIN = 10000;		// Minimum range for the numbers
	
	private static Random seed;
	private static int key;
	private static String fileName;
	private static int wordCount = 0;
	
	int numberCount = 0;
	
	private static List<Integer> uniqueNums = new LinkedList<Integer>(); // Hashset to make sure numbers we generate are unique
	
	private static Set<String> uniqueWords = new HashSet<String>();
	
	private static Map<String,LinkedList<Integer>> codeBook = new LinkedHashMap<String,LinkedList<Integer>>();	// encode LinkedHashMap
	
	public CodeBuhk(String fileName)
	{
		SetFileName(fileName); // Setting the fileName in this case CommonWords.txt
		
		init(); 	// Initialize Method
	}		
	
	private void init()
	{
		CreateKey();			// Creates a random key for the Random Method based on the current system time.
		
		CreateNumberPool();     // Creates 75,000 numbers and uses Collections.Shuffle to get a random linkedlist of numbers
						
		UniqueWords();			// Reads in the passed dictionary "CommonWords.txt" and creates a set of unique words
		
		try
		{
			CreateBook(fileName);	// Distributes 75,000 numbers to unique words in the set
		}
		catch (IOException e)
		{
			System.out.println("Problem creating the book");
		}
		
	}// init method, just a place holder 
	
	private void CreateNumberPool()
	{
		for(int i = MIN; i < MAX;++i)
		{
			uniqueNums.add(i);
		}
		
		Collections.shuffle(uniqueNums,seed);
		
	} // Creates a pool of 90k words and uses the Collections shuffle to jumble them. Swiss Army Style.		O(n) Time
	 
	private void CreateKey()
	{
		int key;

		key = (int)Math.abs(System.currentTimeMillis());	
		
		seed = new Random(key);
		
		setCodeSeed(key);
	
	} // Generates a seed from the system clock and writes it to file. I later wanted to reuse this key but never got around to implementing it. O(1) Time
	
	public int getCodeSeed()
	{
		return key;
		
	}// return the seed that was generated for Random 		O(1) Time
	
	public void setCodeSeed(int k)
	{
		key = k;
		
	}// set the seed to be used with Random 				O(1) Time
	
	public void SetFileName(String fName)
	{
		fileName = fName;
		
	}// Setting the file name of the code dictionary		O(1) Time
	
	public String GetFileName()
	{
		return fileName;
		
	}// Returning the file name of the code dictionary

	private void CreateBook(String fileName) throws IOException 
	{		
		int wordAmt = uniqueWords.size();
		int third,half,sixtyFive,rest,numberPool;
		int dist_1,dist_2,dist_3,dist_4;
		
		numberPool = 75000;
		
		third = (numberPool / 100) * 33;       //24,750
		half = (numberPool / 100) * 17;		   //12,750
		sixtyFive = (numberPool / 100) * 15;   //11,250
		rest = (numberPool / 100) * 35;		   //26,250
		
		dist_1 = 3;
		dist_2 = 8;
		dist_3 = 22;
		dist_4 = 67;			
		
		for(String currentWord : uniqueWords) 
		{
			int i = 0;
			LinkedList<Integer> myCodes = new LinkedList<Integer>();
			
			if(wordCount < (wordAmt / 100 * dist_1))									
			{
				for(i = 0; i < third / (wordAmt / 100 * dist_1); ++i)  // 
				{
					myCodes.add(GiveNumber());								
				} //O(n) Time
			}
			else if(wordCount < (wordAmt / 100 * dist_2))
			{
				for(i = 0; i < half / (wordAmt / 100 * dist_2); ++i)   //
				{
					myCodes.add(GiveNumber());								
				} //O(n) Time	
			}
			else if(wordCount < (wordAmt / 100 * dist_3))
			{
				for(i = 0; i < sixtyFive  / (wordAmt / 100 * dist_3); ++i) // 
				{
					myCodes.add(GiveNumber());									
				} //O(n) Time
			}					
			else
			{
				for(i = 0; i < rest / (wordAmt / 100 * dist_4); ++i) //  
				{
					myCodes.add(GiveNumber());								
				} //O(n) Time
			}	
			
			codeBook.put(currentWord, myCodes);					
			
			wordCount++;										
			
		} // while		O(n2) Time
		
	}// This method pops a unique number from my unique list of shuffled numbers and distributes them out to a linkedlist.
	 // The codeBook gets populated with the integer lists to the corresponding unique word.
	
	private Integer GiveNumber()
	{
		Integer x = new Integer(0);
		
		x = new Integer(uniqueNums.get(uniqueNums.size() - 1)); 
			
		uniqueNums.remove(uniqueNums.size() - 1);
			
		numberCount++;
		
		return x;
		
	}// Removes a number from the top of the uniqueNums LinkedList (Constant) and returns it where needed as a random integer //O(1) Time
	
	private void UniqueWords()
	{
		BufferedReader fileIn = null;
		String currentWord;	
		
		try
		{
			fileIn = new BufferedReader(new FileReader(new File(fileName)));
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found");
		}			
		
		try 
		{
			while((currentWord = fileIn.readLine()) != null)
			{				
				String[] words = currentWord.split(" ");
				
				for(String s : words)
				{
					if(uniqueWords.contains(s) != true)
					{
						uniqueWords.add(s);
					}
				}
			}
			
			fileIn.close();
		} 
		catch (IOException e)
		{
			System.out.println("Bad stuff");
		}
		
	}// Reads in from a dictionary file (900 words) and puts them into a set ensuring all words are unique   //O(n) Time
	
	public void UnknownWord(String w)
	{
		LinkedList<Integer> codes = new LinkedList<Integer>();
		
		if(!uniqueWords.contains(w))
		{
			uniqueWords.add(w);
			
			int x = uniqueNums.size() - 10;
			
			if(x > 0)
			{				
				for(int i = 0; i < 5; i++)
				{
					codes.add(GiveNumber());
				}
				
				codeBook.put(w, codes);
			}
		}	
		
	}// This method is very important. If a word isn't in the uniqueWords set it "generates" 5 numbers for the unknown word and adds the unknown  //O(n) Time

	public String toString()
	{
		return codeBook.toString();
		
	}// overriding toString //O(1) Time
	
	public Map<String,LinkedList<Integer>> getBook()
	{
		return codeBook;
		
	}// Return the codebook //O(1) Time
	
	public boolean containsKey(String s)
	{
		return codeBook.containsKey(s);
		
	}// Return if a codebook contains a value //O(1) Time
	
}// CodeBuhk
