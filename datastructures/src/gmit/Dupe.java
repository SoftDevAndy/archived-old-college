package gmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dupe
{
	private static Set<String> wordList;
	private static String word;
	
	public void findDupe(String fileName) throws IOException
	{
		BufferedReader buff = new BufferedReader(new FileReader(new File(fileName)));
		wordList = new HashSet<String>();
		
		int dupe = 0;
		int legit = 0;
		int count = 0;
		
		System.out.println();
		
		while((word = buff.readLine()) != null)	// reading the word in from the buffer
		{
			++count;	// total word count
			
			if(wordList.contains(word))			// if the word read in from the buffer already exists in the HashSet it is a duplicate
			{
				System.out.println("Duplicate found: \"" + word + "\" at index of: " + count  +  "\n");
				++dupe;	// increment duplicate count
			}	
			else
			{
				++legit;	// if the word isn't found in the set increment legit count
				wordList.add(word);	// add the word to the HashSet
			}
		}
		
		buff.close(); // closing the buffer
		
		System.out.println("Word Count: " + count);
		System.out.println("Legitimate words: " + legit);
		System.out.println("Duplicate words: " + dupe);
	}
}