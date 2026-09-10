package gmit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileDecoder
{
	private static DecodeBuhk decodeBook;
	private static int code;
	private static String decodeName;
	private static PrintWriter fileOut;
	
	public FileDecoder(DecodeBuhk dec,String dName)
	{
		decodeBook = dec;
		decodeName = dName;
	}
	
	public void DecodeFile() throws FileNotFoundException
	{		
		int formatCount = 0;
		
		String fileName = "Encoded.txt";
		Scanner fileIn = new Scanner(new FileReader(fileName));
		fileOut = new PrintWriter(decodeName);
		
		while(fileIn.hasNext())	
		{
			code = (Integer)fileIn.nextInt();
		
			if(decodeBook.getBook().containsKey(code))
			{					
				decodeBook.getBook().get(code);
				
				fileOut.print(decodeBook.getBook().get(code) + " ");
				
				++formatCount;
				
				if(formatCount > 9)
				{
					fileOut.printf("%n");
					formatCount = 0;
				}
				
			}// if
			
		}//while O(n) Time
		
		fileIn.close();
		fileOut.close();
		
	} // DecodeFile searches the decode book for the corresponding digit and prints that word to file roughly formatted
	
}// FileDecoder
