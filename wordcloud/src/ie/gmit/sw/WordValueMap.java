package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * This class creates the world value map
 * @author Andy Sweeney
 * @version 1.0
 */
public class WordValueMap 
{
	private HashMap<String,Integer> wordValueMap = new HashMap<String,Integer>();
	
	/**
	 * Lets you add to the WordValueMap
	 * @param key passing in key 
	 */
	public void add(String key)
	{
		int INITIAL_VALUE = 1;
		
		if(wordValueMap.containsKey(key))
		{
			int value = wordValueMap.get(key);
			
			value += 1;
			
			wordValueMap.remove(key);
			wordValueMap.put(key, value);
		}
		else
			wordValueMap.put(key, INITIAL_VALUE);
	}
	
	/**
	 * Returns the size of the wordValueMap
	 * @return an int
	 */
	public int size()
	{
		return wordValueMap.size();
	}
	
	/**
	 * Gets the orderedList
	 * @return ArrayList
	 */
	public ArrayList<String> getOrderedList()
	{
		return this.getOrderedKeys(wordValueMap);
	}
	
	/**
	 * Creates the list of OrderedKeys with a given Hashmap
	 * @param map takes a HashMap with String,Integer
	 * @return ArrayList with orderdKeys as Strings
	 */
	public ArrayList<String> getOrderedKeys(HashMap<String,Integer> map)
	{
		ArrayList<String> arrList = new ArrayList<String>();
	
		HashMap<String,Integer> temp = new HashMap<String,Integer>(map);
					
		int max = Globals.getInstance().getWordLimit();
		int count = 0;
		
		while(count < max) 
		{			
			arrList.add(highestKey(temp));			
			temp.remove(highestKey(temp));
			
			count++;
		}
		
		return arrList;
	}
	
	/**
	 * Returns the highestKey in a given HashMap
	 * @param map given hashmap
	 * @return a String which is the highestKey in that map
	 */
	public String highestKey(HashMap<String,Integer> map)
	{
		String highKey = "";
		int highValue = 0;
		
		for(Entry<String, Integer> entry : map.entrySet()) 
		{
			if(entry.getValue() > highValue)
			{
				highKey = entry.getKey();
				highValue = entry.getValue();
			}
		}
		
		return highKey;
	}
	
	/**
	 * Prints out a list to the console of all the words in a given list
	 * @param list of words 
	 */
	public void printList(List<String> list)
	{
		System.out.println("\nList\n");
		
		for(String s : list)
		{
			System.out.println(s);
		}
	}
	
	public int mostUsedCount()
	{
		return wordValueMap.get(highestKey(wordValueMap));
	}
	
	public String mostUsedWord()
	{
		return highestKey(wordValueMap); 
	}
	
	public String firstTen()
	{
		String firstTen = "\nFirst 10 \n\n";
		
		int i = 0;
		
		for(Entry<String, Integer> entry : wordValueMap.entrySet()) 
		{
			if(i > 9)
			{
				return firstTen;
			}				
				
			firstTen += entry.getKey() + "\n";		
			
			++i;
		}
		
		return firstTen;
	}
	
	public HashMap<String, Integer> getStrValMap()
	{
		return this.wordValueMap;
	}
	
	public int getValueForKey(String key)
	{
		if(wordValueMap.containsKey(key))
		{
			return wordValueMap.get(key);			
		}
		
		return 0; 
	}
}
