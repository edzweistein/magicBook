package magicbook.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This UI provides visual feedback to the user which type of data he can/should
 * drop onto it.
 * 
 * @author edzweistein
 * 
 */
public class DropBoxUI {

	
	private JScrollPane _scrollPane;
	private JTextArea _text;
	private JPanel _panel;

	

	public DropBoxUI() {
		_panel = new JPanel();
		_panel.setLayout(new BorderLayout());
		_text = new JTextArea("Drop your media files here");
		_text.setEditable(false);
		_scrollPane = new JScrollPane(_text);
		_panel.add(_scrollPane);

	}

	/**
	 * Returns a reference of the textfield of dropbox.
	 * 
	 * @return textarea for added media files
	 */
	public JTextArea getTextArea() {
		return _text;
	}
	
	/**
	 * Returns the UI panel.
	 * 
	 * @return ui panel
	 */
	public JPanel getPanel() {
		return _panel;
	}
	

	/**
	 *  Changes the description in the textarea to drop CoverArt.
	 * 
	 */
	public void setCoverArtDropMode(){
		/*TODO:schön: hier etwas ändern,sodass gedroptes cover auch in klein danach angezeigt wird  */
		_text.setText("Drop the CoverArt for these files here");
	}
	
	/**
	 * Changes the description in the textarea to drop media files.
	 */
	public void setMediumDropMode()
	{
		_text.setText("Drop your media files here");
	}

}
