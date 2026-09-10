package gmit;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DecodeBuhk
{
	private CodeBuhk codeBook;
	
	private static Map<Integer, String> decodeBook = new HashMap<Integer,String>();	 // The almighty decode book
	
	public DecodeBuhk(CodeBuhk bk)
	{
		init(bk);	// Calling the init method and keeping things cleaned	
	}
	
	private void init(CodeBuhk bk)
	{
		SetCodeBook(bk); // Take in the code book from the constructor and holds it privately
		CreateDecode();  // Calling the CreateDecode Method see below...
		
	}// Makes sure the decode book gets setup properly 
	
	private void CreateDecode()
	{
		Random r = new Random();
		
		for(String key: codeBook.getBook().keySet())
		{								
			int num = 0;
			
			if(codeBook.getBook().get(key).size() != 0)
			{
				num = r.nextInt(codeBook.getBook().get(key).size());
				decodeBook.put(codeBook.getBook().get(key).get(num), key); 
			}
    	}	
		
	}// Runs through the code book by keyset (each word) findings a random position in it's LinkedList of integers and amends that to the code book. //O(n) Time
	
	private void SetCodeBook(CodeBuhk bk)
	{
		codeBook = bk;
		
	}// Setting the passed CodeBuhk (used for generating the decode book) //O(1) Time
	
	public Map<Integer, String> getBook()
	{
		return decodeBook;
		
	}// Returning a handle to the decodeBook, we don't want to give static direct public access to our book now ;) //O(1) Time
	
}// DecodeBuhk
