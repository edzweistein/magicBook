package magicbook.gui.addmedia;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Abstract class for GUI of the AddMediumSubTool's such as AddVideoToolUI, AddMusicToolUI,...
 * 
 */
public abstract class AddMediumSubToolUI {

	protected JFrame _frame;
	public JButton _saveButton = new JButton("Finish: Save!");
	
	
	/**
	 * Sets the dimension and makes the window/frame for this tool visible.
	 * 
	 */
	public abstract void showWindow();
	
	/**
	 * Closes the external window for adding specific information for media.
	 */
	public void closeWindow()
	{
		getFrame().dispose();
	}
	
	/**Returns the JFrame for the specific AddMediumSubToolUI
	 * @return
	 */
	public abstract JFrame getFrame();
	
	/**
	 * Returns the 'Save' Button.
	 * 
	 * @return save-button
	 */
	 
	public JButton getSaveButton()
	{
		return _saveButton;
	}
	
	/**
	 * Creates and adds the components to the UI frame.
	 */
	protected abstract void createWindow();
}
