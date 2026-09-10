package gmit;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BookWriter
{
	private static CodeBuhk codeBook;
	private static DecodeBuhk decodeBook;
	private static PrintWriter writer;
	private String codeFileName = "CodeBook.txt";
	private String decodeFileName = "DecodeBook.txt";
	
	public BookWriter(CodeBuhk code)
	{
		codeBook = code;
		
		try 
		{
			writer = new PrintWriter(codeFileName);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public BookWriter(DecodeBuhk code)
	{
		decodeBook = code;
		
		try 
		{
			writer = new PrintWriter(decodeFileName);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void PrintCodeBook()
	{		
		for(String s : codeBook.getBook().keySet())
		{
			writer.print(s + " ");
			
			for(int i = 0; i < codeBook.getBook().get(s).size();i++)
			{
				writer.print(codeBook.getBook().get(s).get(i) + " ");
			}	
			
			writer.print("\n");
		}
		
	}// Prints out the code book the Key Word and each individual digit
	
}// BookWriter