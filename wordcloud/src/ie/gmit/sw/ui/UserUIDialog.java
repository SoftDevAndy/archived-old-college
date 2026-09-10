package ie.gmit.sw.ui;

import java.awt.Frame;

import javax.swing.JOptionPane;
/**
 * UserUIDialog handles UI messages. A very basic class
 * @author Andy Sweeney
 * @version 1.0 
 */
public class UserUIDialog 
{
	private Frame frame = new Frame();
	
	/**
	 * Makes the appropriate display message display using JOptionPane
	 * @param message a String containing the text for the dialog pane
	 * @param uim enumerator with the appropriate message type
	 */
	public UserUIDialog(String message,UserUIMessageType uim)
	{		
		if(message != null && !(message.equals("")))
		{		
			if(uim == UserUIMessageType.ERROR)
				JOptionPane.showMessageDialog(frame,message,"Error!",JOptionPane.ERROR_MESSAGE, null);	
			else if(uim == UserUIMessageType.WARNING)
				JOptionPane.showMessageDialog(frame,message,"Warning!",JOptionPane.WARNING_MESSAGE, null);
			else if(uim == UserUIMessageType.SUCCESS)
				JOptionPane.showMessageDialog(frame,message,"Success!",JOptionPane.INFORMATION_MESSAGE, null);
			else if(uim == UserUIMessageType.PLAIN)
				JOptionPane.showMessageDialog(frame,message,"",JOptionPane.PLAIN_MESSAGE, null);
		}
	}
	
	/**
	 * Creates a basic user dialog box with a title aswell
	 * @param title a String containg the title for the dialog box
	 * @param message the message for the dialog box
	 */
	public UserUIDialog(String title,String message)
	{		
		JOptionPane.showMessageDialog(frame,message,title,JOptionPane.PLAIN_MESSAGE, null);
	}
}
