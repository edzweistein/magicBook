package magicbook.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import magicbook.MagicBook;
import magicbook.gui.options.OptionsTool;

public class MagicBookUI {
	
	public static JFrame _frame;
	public static JButton _buttonAddMedium;

	/**
	 * Creates and manages the UI of the main-frame.
	 * 
	 * @param addmediumPanel , contains the dropbox to add media
	 * @param mediumviewPanel , contains an view with details
	 */
	public MagicBookUI(JPanel addmediumPanel, JPanel mediumviewPanel, JPanel mediumlistPanel){
		
		_frame = new JFrame("magicBook");
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setVisible(true);
		_frame.setIconImage(new ImageIcon("icon.jpg").getImage());
		
		JLabel label = new JLabel("magicBook");
		
		//creates the menubar of the window
		JMenuBar menubar = createMenuBar();
		_frame.setJMenuBar(menubar);
		
		//main-container
		Container contentPane = _frame.getContentPane();
		
	
		contentPane.setLayout(new BorderLayout());
		contentPane.add(label,BorderLayout.NORTH);
		
		JTabbedPane tabbedpane = createTabbedPane(addmediumPanel, mediumviewPanel);
		contentPane.add(tabbedpane, BorderLayout.CENTER);
		
		contentPane.add(mediumlistPanel, BorderLayout.SOUTH);

		_frame.setSize(600,800);
		_frame.pack();
	}

	
	/**
     * Creates the menubar containing different JMenu with Items.
     * 
     * @return a JMenuBar
     */
    private JMenuBar createMenuBar()
    {
        JMenuBar menubar = new JMenuBar();
        
        JMenu filemenu = new JMenu("File");
        
        JMenuItem menuitemclose = new JMenuItem("Close the book...");
        
        menuitemclose.addActionListener(new ActionListener()
        {
            //closes the window and the whole application
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                _frame.dispose(); // close the GUI
                System.exit(0); // and exit.
            }
        });
       
        filemenu.add(menuitemclose);
        
        JMenuItem menuitemoptions = new JMenuItem("Options");
        menuitemoptions.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		new OptionsTool();
        	}
        });
        
        filemenu.add(menuitemoptions);
        menubar.add(filemenu);
        

        return menubar;
    }
    
    /**
     * Creates the tabbed pane.
     * 
     * @param addMediumPanel reference to add media 
     * @param viewMediumPanel reference to view media
     * @return tabbedpane cotaining Panel to add and view media
     */
    private JTabbedPane createTabbedPane(JPanel addMediumPanel, JPanel viewMediumPanel)
    {
    	JTabbedPane tabbedpane = new JTabbedPane();
    	
    	//sets the size for the whole tabbedPane including the tab content
    	tabbedpane.setPreferredSize(new Dimension(500, 400));
    	    	
    	ImageIcon icon = createImageIcon("images/plus.gif");
    	ImageIcon icon2 = createImageIcon("images/info.gif");
    	
    	tabbedpane.addTab("Add medium" , icon, addMediumPanel, "Add a new media");
    	
    	tabbedpane.addTab("View medium", icon2, viewMediumPanel, "View your media");
    	
    	return tabbedpane;
    }
    
    /**
     * Retrieves an image from a path
     * 
     * @param path, where the image is saved
     * 
     * @return ImageIcon
     */
    private ImageIcon createImageIcon(String path) {
        File image = new File(MagicBook.getMagicDirectory()+"/"+path);
        if (image.exists()) {
            try {
            	
				return new ImageIcon(image.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
        } else {
            System.err.println("Couldn't find file: " + MagicBook.getMagicDirectory()+path);
            return null;
        }
    }
    
    /**Returns the window frame
     * @return
     */
    public JFrame getFrame(){
    	return _frame;
    }
	
}
