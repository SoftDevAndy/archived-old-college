
package ie.gmit.sw.words;

/**
 * Bounds is class used to implement basic 2D Box detection when placing the words.
 * @author Andy Sweeney
 * @version 1.0 
 */
public class Bounds
{
	private int x,y,wid,hi;
	
	/**
	 * Constructor that takes a height,width and a bounds object 
	 * @param w the width for the word
	 * @param h the height for the word
	 * @param bounds a bounds object which the x and y position are taken from and assigned to this class
	 */
	public Bounds(int w,int h,Bounds bounds)
	{
		this.wid = w;
		this.hi = h;
		this.x = bounds.x;
		this.y = bounds.y;
	}
	
	/**
	 * A public constructor that takes a bounds objects
	 * @param bounds takes another bounds object position variables x and y
	 */
	public Bounds(Bounds bounds)
	{
		this.x = bounds.x;
		this.y = bounds.y;
	}
	
	/**
	 * Public constructor that takes width,height,x and y position
	 * @param w the width
	 * @param h the height
	 * @param x the x co-ordinate
	 * @param y the y co-ordinate
	 */
	public Bounds(int w,int h,int x,int y)
	{
		this.wid = w;
		this.hi = h;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Takes a bounds object and checks if it collides with this bounds object
	 * @param checkMe the other bounds object that is passed in for collision detection
	 * @param bufferSpace the buffer space for the text so the text doesnt crowd too closely together when placing
	 * @return a Boolean on whether this bounds collides with the bounds object passed in
	 */
	public boolean doIAvoidCollision(Bounds checkMe,int bufferSpace)
	{
		if(checkMe.getX() < this.getX() + this.getWidth() + bufferSpace && checkMe.getX() + checkMe.getWidth() + bufferSpace > this.getX() && checkMe.getY() < this.getY() + this.getHeight() + bufferSpace && checkMe.getHeight() + checkMe.getY() + bufferSpace > this.getY())
		{
			return false;
		}	
		else
		{
			return true;
		}
	}
	
	/**
	 * Returns the X co-ordinate
	 * @return an int
	 */
	public int getX()
	{
		return this.x;
	}

	/**
	 * Returns the Y co-ordinate
	 * @return an int
	 */
	public int getY()
	{
		return this.y;
	}	

	/**
	 * Returns the width
	 * @return an int
	 */
	public int getWidth()
	{
		return this.wid;
	}
	
	/**
	 * Returns the height
	 * @return an int
	 */	
	public int getHeight()
	{
		return this.hi;
	}
}
