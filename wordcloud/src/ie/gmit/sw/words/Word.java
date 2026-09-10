package ie.gmit.sw.words;

import java.awt.Color;
import java.awt.Font;

/**
 * Word is a class for storing different characteristics of a word
 * @author Andy Sweeney
 * @version 1.0 
 */
public class Word implements Wordable
{
	private Bounds bounds;
	private Color color;
	private Font font;
	private String text;
	private int size;
	private WordType wt;
	
	/**
	 * Public constructor for setting the initial variables
	 * @param bounds for setting the bounds of the word
	 * @param text the actual text it displays on the word map
	 * @param size the font size for the font
	 * @param wt the type of word
	 */
	public Word(Bounds bounds,String text,int size,WordType wt)
	{
		this.bounds = bounds;
		this.text = text;
		this.color = Color.black;
		this.font = new Font(Font.SERIF, Font.BOLD, getSize());
		this.wt = wt;
	}
	
	/**
	 * Returns the bounds object for the word
	 * @return Bounds
	 */
	public Bounds getBounds() {
		return bounds;
	}
	
	/**
	 * Sets the bounds object for the word
	 * @param bounds is passing in a bounds object and setting the current one to it
	 */
	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}
	
	/**
	 * Returns a word type
	 * @return WordType
	 */
	public WordType getWt() {
		return wt;
	}

	/**
	 * Sets the WordType object for the word
	 * @param wt is setting the wordtype
	 */
	public void setWt(WordType wt) {
		this.wt = wt;
	}

	/**
	 * Returns the color of the word
	 * @return Color is returning the color
	 */
	public Color getColor() 
	{
		return this.color;
	}

	/**
	 * Sets the current color of the word
	 * @param color is setting the color
	 */
	public void setColor(Color color) 
	{
		this.color = color;
	}

	/**
	 * Getting the font
	 * @return font returning the font
	 */
	public Font getFont() 
	{
		return this.font;
	}

	/**
	 * Sets the current font of the word
	 * @param font is setting the color
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * Getting the text
	 * @return text returning the text of the word
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the current text of the word
	 * @param text is setting the color
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Getting the size
	 * @return returning the size of the word
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Sets the current text of the word
	 * @param size is setting the size
	 */
	public void setSize(int size) {
		this.size = size;
	}
}
