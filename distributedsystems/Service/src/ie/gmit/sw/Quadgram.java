package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// Basic class that reads a file in then
// Builds,Stores and gives access to the quadgram map

public class Quadgram
{	
	private Map<String, Integer> quadGramMap = new HashMap<String, Integer>();

	private BufferedReader bufferedReader = null;
	private StringBuffer stringBuffer = null;
	
	public void buildQuadgram(String fileName) throws Exception
	{
		bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		stringBuffer = new StringBuffer();
		
		int j;
		
		while((j = bufferedReader.read()) != -1)
		{
			char next = (char)j;
			
			if(next >= 'A' && next <= 'z')
			{
				stringBuffer.append(next);
			}
			
			if(stringBuffer.length() == 4)
			{
				String qGram = stringBuffer.toString().toUpperCase();
				stringBuffer = new StringBuffer();
				
				int freq = 0;
				
				if(quadGramMap.containsKey(qGram))
				{
					freq = quadGramMap.get(qGram);
				}
				
				freq++;
				quadGramMap.put(qGram, freq);
			}
		}
		
		bufferedReader.close();
		
	}
	
	public double getScore(String text)
	{
		double score = 0.00f;
		
		for (int i = 0; i < text.length(); i++)
		{
			if(i+4 >= text.length()) 
				break;
			
			String next = text.substring(i, i+4);
			
			if(quadGramMap.containsKey(next))
			{
				double freq = (double)quadGramMap.get(next);
				int total = quadGramMap.size();
				
				score += Math.log10(freq);				
			}	
		}
		
		return score;
	}	
	
	public Map<String, Integer> getQuadgramMap()
	{
		return this.quadGramMap;
	}
	
	public int getQuadgramMapCount()
	{
		return quadGramMap.size();
	}
	
	public String getHighestQuadgram()
	{		
		int HighestValue = getHighestQuadgramValue();
		String key = "";
		
		for (Entry<String, Integer> entry : quadGramMap.entrySet())
		{ 
            if (entry.getValue() == HighestValue) 
            {
                key = entry.getKey();
            }
        }
		
		return key;
	}
	
	public int getHighestQuadgramValue()
	{		
		return Collections.max(quadGramMap.values());
	}
	
	
}
