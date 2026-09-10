package ie.gmit.sw;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Worker class.
 * Creates the importer, the blacklist, the list of words for the cloud.
 * Once done it creates the wordcloud 
 * @author Andy Sweeney
 * @version 1.0
 */
public class Worker 
{	
	private ArrayList<String> orderedWordList;
	private Importer importer;
	private WordValueMap worldvaluemap;

	/**
	 * Takes a string of the file or url location for the wordcloud and the filelocation for the blacklist
	 * Creates the wordcloud
	 * @param fileToBlackList String of the filelocation for the blacklist
	 * @param fileOrUrl String of the file location or url for the wordcloud
	 */
	public void doWork(String fileToBlackList, String fileOrUrl)
	{
		importer = new Importer();		
		importer.ImportBlackList(fileToBlackList);
		importer.ImportWords(fileOrUrl);	
		
		worldvaluemap = importer.getWordValueMap();
		orderedWordList = worldvaluemap.getOrderedList();
		
		try 
		{
			WordCloud wordcloud = new WordCloud();
			wordcloud.CreateWordCloud(orderedWordList,worldvaluemap);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.exit(0); 
	}	
}
