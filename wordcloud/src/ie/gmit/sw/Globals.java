package ie.gmit.sw;

/**
 * This class is an example of a Singleton. It's created to hold most of the global variables used in the project
 * @author Andy
 *
 * @version 1.1
 */
public class Globals 
{	
   private static Globals instance = null;
   protected Globals() {}
 
   private boolean isPackagedRunnable = true; // Debug mode represents the code being run from eclipse otherwise its run from jar
   private boolean GUI = false;
   
   private String backupStopwordsInternal = "/txt/stopwords.txt";
   private String backUpWordCloudInternal = "/txt/warandpeace.txt";
   
   private int WordLimit = 70;
   private int fontSize = 200;
   private int ImageDimension = 3000;
   private int minFontSize = 10;
   private int minWordLen = 3;

   /**
   * Returns the singleton instance of Globals.
   * @return an Globals
   */   
	public static Globals getInstance()
    {
		if(instance == null) 
		{
		   instance = new Globals();
		}
  
		return instance;
    }	
	
	/**
	* Returns the Backup Stopwords Location
	* @return an String
	*/  
	public String getBackupStopwordsInternal() 
	{
		return backupStopwordsInternal;
	}

	/**
	* Returns the Backup Cloudwords Location
	* @return an String
	*/  
	public String getBackUpWordCloudInternal()
	{
		return backUpWordCloudInternal;
	}

	/**
	* Returns the minimum word size.
	* @return an int
	*/  
	public int getMinWordLen()
	{
		return minWordLen;
	}

	/**
	* Sets the minimum word size.
	* @param minWordLen int
	*/
	public void setMinWordLen(int minWordLen)
	{
		this.minWordLen = minWordLen;
	}

	/**
	* Returns the minimum font size.
	* @return an int
	*/   
    public int getMinFontSize()
    {
    	return minFontSize;
    }

	/**
	* Sets the minimum font size.
	* @param minFontSize int
	*/   
	public void setMinFontSize(int minFontSize) 
	{
		this.minFontSize = minFontSize;
	}	

	/**
	* Returns the image dimension used to set the length and height of the image
	* @return an int
	*/   	
   	public int getImageDimension()
    {
	   return ImageDimension;
    }

	/**
	* Sets the length and height variable of the image
	* @param imageDimension int
	*/  
	public void setImageDimension(int imageDimension) 
	{
		ImageDimension = imageDimension;
	}

	/**
	* Returns the current font size
	* @return an int
	*/  
	public int getFontSize() 
    {
	   return fontSize;
    }

	/**
	* Sets the current font size
	* @param fontSize int
	*/  
	public void setFontSize(int fontSize) 
	{
		this.fontSize = fontSize;
	} 
   
	/**
	* Sets whether the runner is in DEBUG mode or not
	* @param debug boolean
	*/  
	public void setIsPackagedRunnable(boolean debug)
    {
  	   this.isPackagedRunnable = debug;
    }
   
	/**
	* Sets the whether the runner is in GUI mode or not
	* @param gui boolean
	*/  
    public void setIsGui(boolean gui)
    {
 	   this.GUI = gui;
    }
   
	/**
	* Returns whether the runner is in DEBUG mode or not
	* @return an int
	*/  
    public boolean getIsPackagedRunnable()
    {
	    return this.isPackagedRunnable;
    }
   
	/**
	* Returns whether the runner is in GUI mode or not
	* @return an boolean
	*/  
    public boolean getIsGui()
    {
 	   return this.GUI;
    }
 
	/**
	* Returns the limit of words displayed on the word cloud
	* @return an int
	*/  
    public int getWordLimit()
    {
	    return this.WordLimit;
    }
}
