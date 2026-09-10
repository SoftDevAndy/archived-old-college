package ie.gmit.sw.ui;

import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ie.gmit.sw.Worker;

/**
 * UserUI manages the creation of the UI version of the program
 * @author Andy Sweeney
 * @version 1.0 
 */
public class UserUI 
{
	private int HI = 140;
	private int WI = 600;
	private int FIELD_HIGH = 25;
	private int MIN_BUFF = 3;
	private int BORDER = 4;
	private int LABELWI = 110;
	
	private JFrame jFrame;
	private JPanel jPanel;
	private JMenuBar menuBar;
	private JMenu menu;		
	private JMenuItem menuItem;
	private JButton okButton;
	private TextField txt_wordCloudFileUrl;
	private TextField txt_blackListFileUrl;
	private Label wordCloudLabel;
	private Label blackListLabel;
	
	/**
	 * Initializes and builds the UI
	 */
	public void Init()
	{
		BuildUI();
	}
	
	/**
	 * Initializes the UI given some defaults for the blacklist text field and cloudfile textfield
	 * @param bl a String containing the file location for the blacklist
	 * @param cl a String containing the file or url location of the wordcloud
	 */
	public void Init(String bl,String cl)
	{
		BuildUI();
		txt_blackListFileUrl.setText(bl);
		txt_wordCloudFileUrl.setText(cl);		
	}
	
	/**
	 * Creates the UI and adds the actionlisteners for the buttons
	 * Creating essentially a GUI version of the program
	 */
	public void BuildUI()
	{			
		jFrame = new JFrame("Word Cloud Application");				
		jPanel = new JPanel();
		menuBar = new JMenuBar();
		menu = new JMenu("?");
		menuItem = new JMenuItem("About", null);
		wordCloudLabel= new Label("WordCloud File/Url: ");
		blackListLabel= new Label("Blacklist File: ");		

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		jFrame.setPreferredSize(new Dimension(WI,HI));
		
		jFrame.add(jPanel);
		jPanel.setLayout(null);
		menuBar.add(menu);
		menu.add(menuItem);
		jFrame.setJMenuBar(menuBar);
		
		okButton = new JButton("Submit");
		okButton.setPreferredSize(new Dimension(WI - (MIN_BUFF * BORDER),FIELD_HIGH * 2));
		okButton.setSize(new Dimension(WI - (MIN_BUFF * BORDER),FIELD_HIGH));
		okButton.setLocation(MIN_BUFF, (FIELD_HIGH * 2) + MIN_BUFF * 3);
		jPanel.add(okButton);

		txt_blackListFileUrl = new TextField("");
		txt_blackListFileUrl.setPreferredSize(new Dimension(WI - (MIN_BUFF * BORDER) - LABELWI,FIELD_HIGH));
		txt_blackListFileUrl.setSize(new Dimension(WI - (MIN_BUFF * BORDER) - LABELWI,FIELD_HIGH));
		txt_blackListFileUrl.setLocation(MIN_BUFF + LABELWI, MIN_BUFF + FIELD_HIGH);
		jPanel.add(txt_blackListFileUrl);
		
		txt_wordCloudFileUrl = new TextField("http://www.independent.ie/");
		txt_wordCloudFileUrl.setPreferredSize(new Dimension(WI - (MIN_BUFF * BORDER) - LABELWI,FIELD_HIGH));
		txt_wordCloudFileUrl.setSize(new Dimension(WI - (MIN_BUFF * BORDER) - LABELWI,FIELD_HIGH));
		txt_wordCloudFileUrl.setLocation(MIN_BUFF + LABELWI, MIN_BUFF);
		jPanel.add(txt_wordCloudFileUrl);
		
		wordCloudLabel.setPreferredSize(new Dimension(LABELWI,FIELD_HIGH));
		wordCloudLabel.setSize(new Dimension(LABELWI,FIELD_HIGH));
		wordCloudLabel.setLocation(MIN_BUFF, MIN_BUFF);
		jPanel.add(wordCloudLabel);
		
		blackListLabel.setPreferredSize(new Dimension(LABELWI,FIELD_HIGH));
		blackListLabel.setSize(new Dimension(LABELWI,FIELD_HIGH));
		blackListLabel.setLocation(MIN_BUFF, MIN_BUFF + FIELD_HIGH);		
		jPanel.add(blackListLabel);
		
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
		jFrame.setResizable(false);
		
		menuItem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {

				String messsage = "The project was created for the GMIT Advanced OO module for John Healy."
		    			+ "\nThis project was programmed by SoftDevAndy.";
				
				new UserUIDialog("About - Andy",messsage);
		    }
		});
		
		okButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	OkButton();
		    }
		});
	}
	
	/*
	 * When the user clicks the OkButton a worker is created similar to the runner when the user passes in the correct arguments through the console.
	 */
	public void OkButton()
	{		
		Worker worker = new Worker();
		
		System.out.println("Ok btn.." +  getTextFieldBlackListFile() + " : " + getTextFieldFile());
		
		worker.doWork(getTextFieldBlackListFile(),getTextFieldFile());
	}	
	
	/**
	 * Gets the text from the wordCloudFile field
	 * @return a String passed in through the textfield for the wordcloud
	 */
	private String getTextFieldFile()
	{
		if(txt_wordCloudFileUrl.getText().equals(""))
			return "Empty text";
		
		return txt_wordCloudFileUrl.getText();
	}
	
	/**
	 * Gets the text from the blacListFile field
	 * @return a String passed in through the textfield for the blacklist
	 */	
	private String getTextFieldBlackListFile()
	{
		if(txt_blackListFileUrl.getText().equals(""))
			return "Empty text";
				
		return txt_blackListFileUrl.getText();
	}
}
