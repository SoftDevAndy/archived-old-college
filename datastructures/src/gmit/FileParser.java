package gmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileParser
{
	private static String fileName;
	private static BufferedReader fileIn;
	private static long startTime,timeTaken;
	private static String fileInput;
	
	public FileParser(String fName)
	{	
		fileName = fName;
		
	}// Basic constructor takes in a file name and an enum that determines whether to show the input as it parses it or not.	
	
	public void ShowFile()
	{		
		startTime = System.currentTimeMillis();
		
		try
		{			
			fileIn = new BufferedReader(new FileReader(new File(fileName)));	
			
			System.out.printf("\n\nParsing file %s\n\n",fileName);
						
			while((fileInput = fileIn.readLine()) != null)
			{
				System.out.printf("%-13s \n",fileInput);
			}	
			
			fileIn.close();

		}
		catch(Exception exception)
		{
			System.out.println("Wrong info passed to constructor, silly user!");
		}
		
		timeTaken = System.currentTimeMillis() - startTime;
		
		System.out.printf("\nFile Parsed in %d ms ",timeTaken);
		
	}// Parses and prints out a file to the console. //O(n) Time
	
	public int FileLines(String fileName)
	{
		int fieldCount = 0;
		
		try 
		{			
			fileIn = new BufferedReader(new FileReader(new File(fileName)));
			
			while((fileInput = fileIn.readLine()) != null)
			{
				++fieldCount;
			}
		} 
		catch (IOException e)
		{
			System.out.println("Problem getting file lines...");
		}
		
		return fieldCount;	
		
	}//O(n) Time
	
	public void WriteWords(String s)
	{
		PrintWriter writer;
		
		try 
		{
			writer = new PrintWriter(fileName);
			writer.print(s);
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("Problems writing file");
		}
		
	}//O(1) Time
		
}// FileParser