package ie.gmit.sw.words;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import ie.gmit.sw.Globals;

/**
 * A type of Word style
 * @author Andy Sweeney
 * @version 1.0 
 */
public class WordFunny extends Word
{	
	/**
	 * Creates a word of a particular style
	 * @param bounds the bounds object
	 * @param text the actual text of the word
	 * @param size the actual size of the word
	 * @param wt the actual type of the word
	 */
	public WordFunny(Bounds bounds, String text, int size, WordType wt) 
	{
		super(bounds, text, size, wt);
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, Globals.getInstance().getFontSize()));
	}	
	
	/**
	 * Overriden getColor method for the funny word style (adding color)
	 * @return Color
	 */
	public Color getColor()
	{
		Random random = new Random();
		
		int R = random.nextInt(256);
		int G = random.nextInt(256);
		int B = random.nextInt(256);
		
		return new Color(R, G, B);
	}
}
