package magicbook.gui.addmedia;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import magicbook.gui.DropBoxUI;
import tools.ToolUI;

/**
 * This UI provides an interface to put media into the database (the first step
 * which has to be made by the user)
 * 
 * @author edzweistein, Niels
 * 
 */
public class AddMediumUI extends ToolUI
{

    private DropBoxUI _dropbox;
	private JButton _nextButton;
	private JButton _resetButton;
	private JButton _cancelButton;
	
	//private final Dimension _size;
	private JLabel _heading;
	private JTextField _url;

	/**
	 * Constructor
	 */
	public AddMediumUI(DropBoxUI dropbox) 
	{
		_dropbox = dropbox;
		JPanel dropboxPanel = _dropbox.getPanel();
		//_size = new Dimension(400, 400);
			
		_mainPanel = createPanel();
		
		_mainPanel.add(dropboxPanel, BorderLayout.CENTER);
	
	}

	/**Sets the heading over the dropbox 
	 * @param text
	 */
	public void setHeading(String text){
		_heading.setText(text);
	}
	/**
	 * Returns a reference of the DropBoxUI.
	 * 
	 * @return ui for the assign dropbox
	 */
	public DropBoxUI getDropBox() {
		return _dropbox;
	}
	
	/**
	 * Returns reference of the 'next'Button.
	 * 
	 * @return nextbutton
	 */
	public JButton getNextButton(){
		return _nextButton;
	}
	
	/**
	 * Returns a reference of the 'URL' textfield.
	 * 
	 * @return textfield containing url input
	 */
	public JTextField getURLTextField(){
		return _url;
	}
	
	/**
	 * Return a reference of the reset button.
	 * 
	 * @return reset button
	 */
	public JButton getResetButton()
	{
	    return _resetButton;
	}
	
	/**
	 * Returns a reference of the cancel button 
	 * 
	 * @return cancel button
	 */
	public JButton getCancelButton()
	{
	    return _cancelButton;
	}
	
	/**Get the text written in the url textfield
	 * @return
	 */
	public String getURLText(){
		return _url.getText();
	}

    @Override
    public JPanel createPanel()
    {
		_heading = new JLabel("Hello,how are you? :)");
		
		JPanel mPanel = new JPanel(new BorderLayout());

		_url = new JTextField("or put your URL here");
		_nextButton = new JButton("Next");
		_nextButton.setEnabled(false);
		
		_resetButton = new JButton("Reset");
		_resetButton.setEnabled(false);
		
		_cancelButton = new JButton("Cancel");
		_cancelButton.setEnabled(false);
		
		Container southPane = new Container();

		southPane.setLayout(new BorderLayout());
		southPane.add(_url,BorderLayout.NORTH);
		
		Container embeddedsouthPane = new Container();
		embeddedsouthPane.setLayout(new FlowLayout());
		
		embeddedsouthPane.add(_nextButton);
		embeddedsouthPane.add(_resetButton);
		embeddedsouthPane.add(_cancelButton);
		
/*		southPane.add(_nextButton,BorderLayout.WEST);
		southPane.add(_resetButton, BorderLayout.EAST);  */
		
		southPane.add(embeddedsouthPane);
		
		mPanel.add(_heading, BorderLayout.NORTH);
		mPanel.add(southPane, BorderLayout.SOUTH);
		
		return mPanel;
    }
    
    /**
     * Resets the UI to default state.
     * 
     */
    public void resetUI()
    {    	
    	setHeading("Hello, how are you.");
    		
    	_nextButton.setText("Next");
    	_nextButton.setEnabled(false);
    	
        repaintUI();
    }
    
    
    /**
     * Repaints the main UI container.
     * 
     */
    public void repaintUI()
    {
        _mainPanel.repaint();
    }
    
    /**
     * Sets the Dropbox UI;
     * 
     */
    public void setDropBoxUI(DropBoxUI dropboxui)
    {
    	_dropbox = dropboxui;
    }
	
	
	

		
}
