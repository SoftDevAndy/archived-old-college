package ie.gmit.sw;

import java.util.List;

/**
 * Parserable is an interface for classes that are parsers.
 * @author Andy Sweeney
 *
 * @version 1.0
 */
public interface Parserable 
{
	/**
	* Takes in a file location string.
	* If a FILE exists at that location a boolean is return whether it is false or not
	* @param fileLocation String
	* @return an boolean
	*/ 	
	boolean isFile(String fileLocation);
	/**
	* Takes in a file location string.
	* If the URL exists at that location a boolean is return whether it is false or not
	* @param fileLocation String
	* @return an boolean
	*/ 	
	boolean isUrl(String fileLocation);
	/**
	* Takes in a file location string.
	* Finds out if the URL or FILE using that location exists
	* @param fileLocation String
	* @return an boolean
	*/ 		
	boolean isFileOrUrl(String fileLocation);
	/**
	* Takes in a file location string.
	* Creates a list of words based off the source given.
	* @param fileLocation String
	* @return List
	*/ 	
	List<String> ImportWords(String fileLocation);
}
