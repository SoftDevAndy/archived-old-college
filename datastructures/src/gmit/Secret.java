/*
 * 
 * A very basic class thats serializable. It holds a string buffer
 * that can be returned as a string. Keep your Secret a Secret :P 
 * 
 */

package gmit;

import java.io.Serializable;

public class Secret implements Serializable
{
	private static final long serialVersionUID = 1234567L;
	
	private StringBuffer fileText;
	
	public Secret(StringBuffer temp)
	{
		setText(temp);
	}
	
	public void setText(StringBuffer temp)
	{
		fileText = temp;
	}
	
	public String getText()
	{
		return fileText.toString();
	}	
}
