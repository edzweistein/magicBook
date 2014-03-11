package magicbook.gui.addmedia;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class AddMusicUI extends AddMediumSubToolUI{

	private JFrame _frame;
	private JTextField _title;
	private JTextField _interpret;
	private JTextField _year;
	
	public AddMusicUI(){
		_frame = new JFrame("Add more information to this music medium");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createWindow();
		
	}
	
	
	@Override
	public void showWindow() {
		
		_frame.setSize(400,400);
		//sets center position
		_frame.setLocationRelativeTo(null);
		_frame.pack();
		_frame.setVisible(true);
		
	}
	
	/**
	 * Closes the window, which has been created by AddMusicTool.
	 */
	public void closeWindow(){
		_frame.dispose();
	}
	
	/**Returns the user content about the title
	 * 
	 * @return title of the music media
	 */
	public String getTitleText(){
		return _title.getText();
	}
	
	/**Returns the user content about the Interpret
	 * 
	 * @return interpret of music media
	 */
	public String getInterpretText(){
		return _interpret.getText();
	}
	
	/**Returns the user content about the year
	 * 
	 * @return year for the music media
	 */
	public int getYear(){
		return Integer.parseInt(_year.getText());
	}

	@Override
	public JFrame getFrame() {
		return _frame;
	}

	@Override
	protected void createWindow() 
	{
		Container contentPane = _frame.getContentPane();
		
		contentPane.setLayout(new FlowLayout());
		
		_title = new JTextField("title");
		_title.setPreferredSize(new Dimension(80,25));
		contentPane.add(_title);
		
		_interpret = new JTextField("interpret");
		_interpret.setPreferredSize(new Dimension(80,25));
		contentPane.add(_interpret);
		
		_year= new JTextField("year");
		_year.setPreferredSize(new Dimension(80,25));
		contentPane.add(_year);
		
		/*TODO: saveButton dynamisch dann wieder freischalten, vorläufig ausgeschaltet 12.06.2012 */
		//_saveButton.setEnabled(false);
		contentPane.add(_saveButton);
	}

}
