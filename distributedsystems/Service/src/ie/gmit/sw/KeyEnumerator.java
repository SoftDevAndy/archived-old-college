package ie.gmit.sw;

public class KeyEnumerator 
{	
	private double score = -1;
	private double bestscore = -1;
	private String bestkey = ""; 
	
	private Quadgram quadgram = null;
	
	// Contructor which takes in the filename to build the quadgrams for
	
	public KeyEnumerator(String filename) throws Exception
	{
		quadgram = new Quadgram();
		quadgram.buildQuadgram(filename);
	}
	
	// getNextKey takes in each character
	// creates and returns char arrays
	
	private char[] getNextKey(char[] key)
	{
		for (int i = key.length - 1; i >=0; i--)
		{
			if (key[i] =='Z')
			{
				if (i == 0) 
					return null;
				
				key[i] = 'A';
			}
			else
			{
				key[i]++;
				
				break;
			}
		}
		
		return key;
	}	
	
	// Taking in a ciphered piece of text and a keylength
	// keylength must be at least 3 and 4 for a quick cipher
	// Tries to use generated keys to unencrypt the cypheredText
	// Scores the keys based off the most englishy cyphers
	// Returns the unencrypted text with the best scored key
	
	public String crackCypher(String cypherText, int maxKeyLength)
	{
		char[] key = null;
		String finalResult = "Couldn't decipher your text: " + cypherText;
		
		for (int j = 3; j <= maxKeyLength; j++)
		{
			key = new char[j];
			
			for (int k = 0; k < key.length; k++) 
			{
				key[k] = 'A';
			}
				
			do
			{
				String keyStr = new String(key);	
				String resultStr = "";
				
				Vigenere vinny = new Vigenere(keyStr);				
				resultStr = vinny.doCypher(cypherText,false);
				
				if (keyStr.equals("BALL")){
					System.out.println("Scored at " + score);
				}
				
				score = quadgram.getScore(resultStr);
				
				if(score > bestscore)
				{
					bestscore = score;
					bestkey = keyStr;
				}
				
			}while ((key = getNextKey(key)) != null);
			
		}
		
		finalResult = new Vigenere(bestkey).doCypher(cypherText, false);
		
		return finalResult;
	}
	
}
