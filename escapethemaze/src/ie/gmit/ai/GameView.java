package ie.gmit.ai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class GameView extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;	
		
	private static Maze maze;	
	private Timer timer;
	private Font font;
	
	private double smallImageSizeWidth = GlobalsVars.SMALL_IMAGE_SIZE_WIDTH;
	private double smallImageSizeHeight = GlobalsVars.SMALL_IMAGE_SIZE_HEIGHT;
			
	public GameView(Maze maze) throws Exception
	{
		this.maze = maze;
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		timer = new Timer(GlobalsVars.TIMER_SPEED, this);
		timer.start();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
		BufferedImage currentImage = null; 
		font = new Font("Times New Roman", Font.PLAIN, 22); 
        g2.setFont(font);      
		
        g2.drawRect(0, 0, GlobalsVars.DEFAULT_VIEW_SIZE, GlobalsVars.DEFAULT_VIEW_SIZE);
        
        int rowMult = 0;
        int colMult = 0;
        
        GlobalsVars.HINTS_TIME += GlobalsVars.TIMER_SPEED;
        
        if(GlobalsVars.HINTS_TIME > GlobalsVars.HINTS_MAX_TIME)
        {        
	    	for(int row = 0; row < GlobalsVars.MAZE_DIMENSION; row++) 
	        {
	        	for (int col = 0; col < GlobalsVars.MAZE_DIMENSION; col++)
	        	{   
	        		 if(maze.getMaze()[row][col].getTilePiece().getTileType() == TileType.HINT)
	        			 maze.getMaze()[row][col].setTilepiece(new TilePiece(TileType.FLOOR,row,col));
	        	}
	        }
        }
        
        if(!GlobalsVars.WON_GAME)
        {        
	        if(!GlobalsVars.HELP_SCREEN)
	        {        
		        if(!GlobalsVars.ZOOM_OUT)
		        {        
			        for(int row = GlobalsVars.playerPositionX - 2; row <= GlobalsVars.playerPositionX + 1; row++) 
			        {
			        	for (int col = GlobalsVars.playerPositionZ - 2; col <= GlobalsVars.playerPositionZ + 2; col++)
			        	{         		        		
			        		try
			        		{        	        		
			        			currentImage = maze.getMaze()[row][col].getTilePiece().getTileImage();    
			        			g2.drawImage(currentImage, colMult, rowMult, GlobalsVars.ImageSize, GlobalsVars.ImageSize, null);
			        		}
			        		catch(Exception e)
			        		{
			        			e.toString(); 
			        			
		        				g2.setColor(Color.BLACK);
						           
		        				Rectangle2D r2d = new Rectangle2D.Float((float)colMult, (float)rowMult, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		        			    g2.fill(r2d);
		        				g2.draw(r2d);
			        		} 
			        		
			        		colMult += GlobalsVars.ImageSize;
			        	}
			        	
			        	rowMult += GlobalsVars.ImageSize;
			        	colMult = 0;
			        }
		        }
		        else
		        {		
		        	double rowMultf = 0;
		        	double colMultf = 0;
		        	        	
		        	for(int row = 0; row < GlobalsVars.MAZE_DIMENSION; row++) 
		            {
		            	for (int col = 0; col < GlobalsVars.MAZE_DIMENSION; col++)
		            	{         		        		
		            		try
		            		{		  		   		            			
		            			if(maze.getMaze()[row][col].containsType(TileType.WALL))
		            			{
		            				g2.setColor(Color.BLACK);
		            				           
		            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		            			    g2.fill(r2d);
		            				g2.draw(r2d);
		            			}
		            			else if(maze.getMaze()[row][col].containsType(TileType.PLAYER))
		            			{            				
		            				g2.setColor(Color.GREEN);
		            				
		            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		            			    g2.fill(r2d);
		            				g2.draw(r2d);          				
		            			}
		            			else if(maze.getMaze()[row][col].containsType(TileType.FOOD))
		            			{
		            				g2.setColor(Color.YELLOW);
		            				
		            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		            			    g2.fill(r2d);
		            				g2.draw(r2d);          				
		            			}
		            			else if(maze.getMaze()[row][col].containsType(TileType.WEAPON))
		            			{
		            				g2.setColor(Color.CYAN);
		            				
		            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		            			    g2.fill(r2d);
		            				g2.draw(r2d);          				
		            			}
		            			else if(maze.getMaze()[row][col].containsType(TileType.HELP))
		            			{
		            				g2.setColor(Color.BLUE);
		            				
		            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		            			    g2.fill(r2d);
		            				g2.draw(r2d);          				
		            			}
		            			else if(maze.getMaze()[row][col].containsType(TileType.ENEMY))
		            			{
		            				g2.setColor(Color.RED);
		            				
		            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		            			    g2.fill(r2d);
		            				g2.draw(r2d);          				
		            			}
		            			else if(maze.getMaze()[row][col].containsType(TileType.EXIT))
		            			{
		            				g2.setColor(Color.PINK);
		            				
		            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		            			    g2.fill(r2d);
		            				g2.draw(r2d);          				
		            			}
		            			else if(maze.getMaze()[row][col].containsType(TileType.HINT))
		            			{
		            				g2.setColor(Color.WHITE);
		            				           
		            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		            			    g2.fill(r2d);
		            				g2.draw(r2d);
		            			}	
		            			else
		            			{
		            				g2.setColor(Color.LIGHT_GRAY);
		            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		            			    g2.fill(r2d);
		            				g2.draw(r2d);
		            			}         			
		            		}
		            		catch(Exception e)
		            		{
		            			e.toString(); 
		        			
		        				g2.setColor(Color.WHITE);
		        				
		        				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
		        			    g2.fill(r2d);
		        				g2.draw(r2d);
		            		} 
		            		
		            		colMultf += smallImageSizeWidth;
		            	}
		            	
		            	rowMultf += smallImageSizeHeight;
		            	colMultf = 0;
		            }
		        }
	        
		        /// Drawing GUI
		        
		        try
				{
					currentImage = ImageIO.read(new java.io.File("Images/texture.png"));
					
					g2.drawImage(currentImage, 0, GlobalsVars.ImageSize * GlobalsVars.drawRows, GlobalsVars.ImageSize * GlobalsVars.drawCols, GlobalsVars.ImageSize, null);
				
				}catch (IOException e){e.toString();} 
		        
		        if(!GlobalsVars.ZOOM_OUT)
		        {   	        
			        g2.setColor(Color.white);
			        
			        // Draw Hungry Status
			        
			        g2.drawString("Turn Count: " + GlobalsVars.TurnCount, 660, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 20);			        
			        g2.drawString(GlobalsVars.currentTime(), 660 ,(GlobalsVars.ImageSize * GlobalsVars.drawRows) + 50);
			        
			        // Draw Turn Count
			        
			        g2.drawString("Health: " + GlobalsVars.player.getHealth(), 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 20);			        
			        g2.drawString("Hunger: " + GlobalsVars.player.getHunger(), 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 50);			        
			        g2.drawString(String.valueOf(GlobalsVars.player.getHungryStatus()), 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 80);			        
			        g2.drawString("Weapons Collected: " + GlobalsVars.player.getWeaponCount(), 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 110);			        
			        g2.drawString("Food Collected: " + GlobalsVars.player.getFoodCount(), 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 140);
			        
			        String wp,fp;
			        
			        if(GlobalsVars.player.getWeaponCount() > 0 == false)
			        	wp = "" + 0;
			        else
			        	wp = "" + (GlobalsVars.player.getWeaponPos() + 1);
			        
			        if(GlobalsVars.player.getFoodCount() > 0 == false)
			        	fp = "" + 0;
			        else
			        	fp = "" + (GlobalsVars.player.getFoodPos() + 1);
			        
			        g2.drawString(wp,690,(GlobalsVars.ImageSize * GlobalsVars.drawRows) + 73);
			        g2.drawString(fp,755,(GlobalsVars.ImageSize * GlobalsVars.drawRows) + 73);
			        
			        if(GlobalsVars.player.getWeaponCount() > 0)
			        {
			        	if(GlobalsVars.player.getCurrentWeapon() != null)	        	
			        			g2.drawImage(GlobalsVars.player.getCurrentWeapon().getTileImage(), 663, 720, 60,60, null);					
			        }
			        else
			        {
			        	g2.setColor(Color.BLACK);
	    				Rectangle2D r2d = new Rectangle2D.Float((float)663, (float)720, 60, 60);
	    			    g2.fill(r2d);
	    				g2.draw(r2d);
			        }
			        
			        if(GlobalsVars.player.getFoodCount() > 0)
			        {
			        	if(GlobalsVars.player.getCurrentFood() != null)		
			        			g2.drawImage(GlobalsVars.player.getCurrentFood().getTileImage(), 730, 720, 60,60, null);	
			        }
			        else
			        {
			        	g2.setColor(Color.BLACK);
	    				Rectangle2D r2d = new Rectangle2D.Float((float)730, (float)720, 60, 60);
	    			    g2.fill(r2d);
	    				g2.draw(r2d);
			        }
				}
		        else
		        {
		        	g2.setColor(Color.white);
		        	g2.drawString("Player: ", 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 25);
		        	g2.drawString("Enemy:  ", 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 55);
		        	g2.drawString("Food:   ", 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 85);
		        	g2.drawString("Weapon: ", 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 115);
		        	g2.drawString("Finish: ", 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 145);
		        	
		        	g2.drawString("Wall:   ", 130, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 25);
		        	g2.drawString("Floor:  ", 130, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 55);
		        	g2.drawString("Help:   ", 130, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 85);
		        	
		        	Rectangle2D r2d;
					g2.setColor(Color.GREEN);
					r2d = new Rectangle2D.Float((float)100,(float)(GlobalsVars.ImageSize * GlobalsVars.drawRows) +  13, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
				    g2.fill(r2d);
					g2.draw(r2d);
					
					g2.setColor(Color.RED);
					r2d = new Rectangle2D.Float((float)100,(float)(GlobalsVars.ImageSize * GlobalsVars.drawRows) +  42, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
				    g2.fill(r2d);
					g2.draw(r2d);
					
					g2.setColor(Color.YELLOW);
					r2d = new Rectangle2D.Float((float)100,(float)(GlobalsVars.ImageSize * GlobalsVars.drawRows) + 73, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
				    g2.fill(r2d);
					g2.draw(r2d);
					
					g2.setColor(Color.CYAN);
					r2d = new Rectangle2D.Float((float)100,(float)(GlobalsVars.ImageSize * GlobalsVars.drawRows) +  103, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
				    g2.fill(r2d);
					g2.draw(r2d);			
					
					g2.setColor(Color.PINK);
					r2d = new Rectangle2D.Float((float)100,(float)(GlobalsVars.ImageSize * GlobalsVars.drawRows) + 133, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
				    g2.fill(r2d);
					g2.draw(r2d);
					
					g2.setColor(Color.BLACK);
					r2d = new Rectangle2D.Float((float)200,(float)(GlobalsVars.ImageSize * GlobalsVars.drawRows) +  13, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
				    g2.fill(r2d);
					g2.draw(r2d);			
					
					g2.setColor(Color.LIGHT_GRAY);
					r2d = new Rectangle2D.Float((float)200,(float)(GlobalsVars.ImageSize * GlobalsVars.drawRows) + 42, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
				    g2.fill(r2d);
					g2.draw(r2d);
					
					g2.setColor(Color.BLUE);
					r2d = new Rectangle2D.Float((float)200,(float)(GlobalsVars.ImageSize * GlobalsVars.drawRows) + 73, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
				    g2.fill(r2d);
					g2.draw(r2d);
		        }
	        }
	        else
	        {
	        	// HELP SCREEN
	        	
	        	BufferedImage backimage = null;
	        	
	        	g2.setColor(Color.BLACK);
	        	Rectangle2D r2d = new Rectangle2D.Float(0,0, GlobalsVars.MAZE_DIMENSION, GlobalsVars.MAZE_DIMENSION);
			    g2.fill(r2d);
				g2.draw(r2d);
	        	        	
	        	try 
	        	{
					backimage = ImageIO.read(new java.io.File("Images/scroll.png"));
				} 
	        	catch (IOException e){e.printStackTrace();}
	        	
	        	g2.drawImage(backimage, 0, 0, GlobalsVars.DEFAULT_VIEW_SIZE, GlobalsVars.DEFAULT_VIEW_SIZE, null);
	        	
	        	g2.setColor(Color.black);
	        	g2.drawString("Press H to close and open these hints", GlobalsVars.DEFAULT_VIEW_SIZE / 2 - 160, 100);
	        	g2.drawString("Use the arrow keys to run around or WASD", GlobalsVars.DEFAULT_VIEW_SIZE / 2 - 180, 160);
	        	g2.drawString("Press E to consume the selected food in your inventory", GlobalsVars.DEFAULT_VIEW_SIZE / 2 - 200, 220);
	        	g2.drawString("Press , to cycle through your collected weapons", GlobalsVars.DEFAULT_VIEW_SIZE / 2 - 180, 280);
	        	g2.drawString("Press / to cycle through your collected food", GlobalsVars.DEFAULT_VIEW_SIZE / 2 - 180, 340);
	        	g2.drawString("Press M to stop and start the music", GlobalsVars.DEFAULT_VIEW_SIZE / 2 - 152, 400);
	        	g2.setColor(Color.red);
	        	g2.drawString("To cheat and see the whole map press Z", GlobalsVars.DEFAULT_VIEW_SIZE / 2 - 180, 460);
	        	g2.setColor(Color.black);
	        	g2.drawString("Try find the door...", GlobalsVars.DEFAULT_VIEW_SIZE / 2 - 67, 540);
	        	
	        	try 
	        	{
					backimage = ImageIO.read(new java.io.File("Images/door.png"));
					g2.drawImage(backimage, GlobalsVars.DEFAULT_VIEW_SIZE / 2 - 60, 550, GlobalsVars.ImageSize, GlobalsVars.ImageSize, null);
				} 
	        	catch (IOException e){e.printStackTrace();}
	        }
        }
        else
        {   
        	// WIN SCREEN
        	
        	double offset = 25;
        	
        	double r = 0;
        	double c = 0;
        	
        	for(int i = 0;i < GlobalsVars.DEFAULT_VIEW_SIZE / offset;i++)
        	{
        		for(int j = 0;j < GlobalsVars.DEFAULT_VIEW_SIZE / offset;j++)
        		{	   
                	Rectangle2D r2d;     	
		        	g2.setColor(new Color(255,255,255,GlobalsVars.RandomNumber(255)));
		        	r2d = new Rectangle2D.Float((float)r,(float)c, (float)offset, (float)offset);		        	
				    g2.fill(r2d);
					g2.draw(r2d);
					
					c+=offset;
        		}
        		
        		c = 0;
        		r += offset;
        	}
        }
	}

	public void actionPerformed(ActionEvent e)
	{			
		this.repaint();
	}	
}