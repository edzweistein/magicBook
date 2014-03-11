package magicbook.gui.addmedia;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AddVideoUI extends AddMediumSubToolUI {

	private JFrame _frame;
	private JTextField _title;
	private JTextField _year;
	private JTextField _regie;

	public AddVideoUI() 
	{
		_frame = new JFrame("Add video");
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createWindow();
	}

	/**
	 * Makes the window visible to the user
	 */
	public void showWindow() {
		if (_frame != null) {
			_frame.setSize(400, 200);
			//sets center position
			_frame.setLocationRelativeTo(null);
			_frame.pack();
			_frame.setVisible(true);
		}
	}

	/**
	 * Get the entered video title / user input.
	 * 
	 * @return title for the video media
	 */
	public String getTitleText() {
		return _title.getText();
	}

	/**
	 * Get the entered director / user input.
	 * 
	 * @return name of director for video media
	 */
	public String getRegie() {
		return _regie.getText();
	}

	/**
	 * Get the entered year / user input
	 * 
	 * @return year of production for video media
	 */
	public int getYear() {
		try {
			int y = Integer.parseInt(_year.getText());
			return y;
		} catch (NumberFormatException e) {
			System.out.println("Error: Only enter numbers in year field!");
			e.printStackTrace();
			return -1;
		}

	}

    @Override
    protected void createWindow()
    {
               
        Container contentPane = _frame.getContentPane();
        
        contentPane.setLayout(new FlowLayout());
        _title = new JTextField("title");
        contentPane.add(_title);
        _regie = new JTextField("regie");
        contentPane.add(_regie);
        _year = new JTextField("year");
        _year.setText("year");    
        contentPane.add(_year);
        _saveButton = new JButton("Finish: Save this video medium");
        contentPane.add(_saveButton);
    }
        
	public JFrame getFrame(){
		return _frame;
	}

}
