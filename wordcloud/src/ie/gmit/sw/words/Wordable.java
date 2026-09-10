package ie.gmit.sw.words;

import java.awt.Color;
import java.awt.Font;

public interface Wordable 
{
	/**
	 * gets the Bounds of the wordable
	 * @return an Bounds
	 */
	public Bounds getBounds();
	
	/**
	 * gets the Color of the wordable
	 * @return an Color
	 */
	public Color getColor();	

	/**
	 * gets the Font of the wordable
	 * @return an Font
	 */
	public Font getFont();
	
	/**
	 * gets the text of the wordable
	 * @return an String
	 */
	public String getText();
	
	/**
	 * returns the size of the text
	 * @return an int
	 */
	public int getSize();
	
	/**
	 * returns the type of word
	 * @return an WordType
	 */
	public WordType getWt();	
	
	/**
	 * Sets the bounds object for the wordable
	 * @param bounds object
	 */
	public void setBounds(Bounds bounds);
	
	/**
	 * Sets the color the wordable
	 * @param color color of the word
	 */
	public void setColor(Color color);
	
	/**
	 * Sets the font
	 * @param font font of the word
	 */
	public void setFont(Font font);
	
	/**
	 * Sets the size of the font
	 * @param size size of the font
	 */
	public void setSize(int size);
	
	/**
	 * Sets the text of the word
	 * @param text text of the word
	 */
	public void setText(String text);	
		
	/**
	 * Sets the WordType
	 * @param wt type of the word
	 */
	public void setWt(WordType wt);
}
