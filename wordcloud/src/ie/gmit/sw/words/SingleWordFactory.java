package ie.gmit.sw.words;

/**
 * SingleWordFactory manages the creation of words
 * @author Andy Sweeney
 * @version 1.0 
 */
public class SingleWordFactory
{
	private static SingleWordFactory instance = null;
	protected SingleWordFactory() {}

	/**
	 * Gets an instance of the SingleWordFactory instance
	 * @return instance of the factory
	 */
    public static SingleWordFactory getInstance()
    {
       if(instance == null) 
       {
          instance = new SingleWordFactory();
       }
      
       return instance;
    }
    
    /**
     * Creates a wordable based off given bounds,text and size
     * @param bounds is the bounds object used for collision detection
     * @param text is the actual text of the word
     * @param size is the size of the word
     * @return a object that implements Wordable
     */
    public Wordable CreateWord(Bounds bounds,String text,int size)
    {
    	WordType wt = WordType.getRandomWordType();
   
    	if (wt == WordType.BASIC)
    	  return new WordBasic(bounds, text,size,WordType.BASIC);
	    else if (wt == WordType.FANCY)
	      return new WordFancy(bounds, text,size,WordType.FANCY);
	    else if (wt == WordType.FUNNY)
	      return new WordFunny(bounds, text,size,WordType.FUNNY);
    	 
    	return null;    	
    }
}
