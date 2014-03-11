package magicbook.gui.viewmedia;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import magicbook.MagicBook;
import magicbook.material.Observable;
import magicbook.objects.Medium;
import tools.Utilities;
import tools.database.DataBaseSQLTool;
import tools.database.DataBaseToolGen;

public class MediumListTool extends Observable {
	private MediumListToolUI _ui;
	
	private DataBaseToolGen _database ;
	
	private JTextField _searchfield;
	
	//entered  characters for search query
	private String _searchquery = "";
	
	private HashMap<String, Medium> _mediabuffer ;

	public MediumListTool() {
		_ui = new MediumListToolUI();
		
		_searchfield = _ui.getSearchField();
		_mediabuffer = new HashMap<String, Medium>();

		registerListener();

		_database = MagicBook.getDatabase();
		if(_database != null)
		{
		    List<Medium> medialist = _database.loadAllMedia();
		    
		    medialist = Utilities.existsMediaDirectory(medialist);
		    setMediaBuffer(medialist);
		    
		    setMediaToTable(medialist);
		}
	}

	/**
	 * Return the UI-Panel of the ui for this tool.
	 * 
	 * @return panel of the ui
	 */
	public JPanel getUIPanel() {
		return _ui.getUIPanel();
	}
	
	/** 
	 *  Returns the UI.
	 * 
	 * @return ui of mediumlisttool.
	 */
	public MediumListToolUI getUI()
	{
	    return _ui;
	}

	/**
	 * Sets the media(stored in parameter) which will be displayed in the table.
	 * 
	 * @param list of media to show in table
	 * 
	 */
	public void setMediaToTable(List<Medium> medialist) 
	{
			List<MediaListFormatter> mediaFormatter = new ArrayList<MediaListFormatter>();

			for (Medium medium : medialist) {
				mediaFormatter.add(new MediaListFormatter(medium));
			}

			_ui.getMediaTableModel().setMedia(mediaFormatter);
	}

	
	/**
	 * Registers event listener. Trigger action if a least one row is seleceted
	 * 
	 */
	private void registerListener() {
		
	    ListSelectionModel listmodel = _ui.getMediaListTable()
				.getSelectionModel();
		listmodel.addListSelectionListener(new ListSelectionListener() {
		    
			@Override
			public void valueChanged(ListSelectionEvent e) {
				informAllObserversAboutChanges();
			}
		});
		
	   _searchfield.getDocument().addDocumentListener(new DocumentListener()
        {
	       
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                Document document = e.getDocument();
                int length = document.getLength();
                try
                {
                    _searchquery = document.getText(0, length);
                }
                catch (BadLocationException e1)
                {
                    e1.printStackTrace();
                }
                updateMediaInTable();
            }
            
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                Document document = e.getDocument();
                int length = document.getLength();
                try
                {
                    _searchquery = document.getText(0, length);
                }
                catch (BadLocationException e1)
                {
                    e1.printStackTrace();
                }
                updateMediaInTable();
                
            }
            
            @Override
            public void changedUpdate(DocumentEvent e)
            {
                // TODO Auto-generated method stub
                
            }
        });
        
        _searchfield.addMouseListener(new MouseListener()
        {
            
            @Override
            public void mouseReleased(MouseEvent arg0)
            {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mousePressed(MouseEvent e)
            {
                JTextField searchfield = (JTextField) e.getComponent();
                
                if(searchfield.getText().equals("search"))
                {
                    searchfield.setText("");
                }
                
            }
            
            @Override
            public void mouseExited(MouseEvent e)
            {
              // do nothing               
            }
            
            @Override
            public void mouseEntered(MouseEvent arg0)
            {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void mouseClicked(MouseEvent arg0)
            {
                // TODO Auto-generated method stub
                
            }
        });
	}

	/**
	 * Returns a list containing the selected media entry in the table.
	 * 
	 * @return ArrayList containing selected media
	 */
	public ArrayList<Medium> getSelectedMedia() {
	
	    ArrayList<Medium> selectedmedia = new ArrayList<Medium>();

		int[] rows = _ui.getMediaListTable().getSelectedRows();

		MediaTableModel mediatablemodel = _ui.getMediaTableModel();

		for (int row : rows) {
			if (mediatablemodel.rowExists(row)) {
				Medium medium = mediatablemodel.getMediaPerRow(row);
				selectedmedia.add(medium);
			}
		}
		return selectedmedia;
	}
	
	/**
	 * Updates the table containing media.
	 * 
	 */
	public void updateMediaInTable()
	{
	    if(_database != null && DataBaseSQLTool.isOpen())
        {
            //updates the buffer 
            List<Medium> allmedialist = _database.loadAllMedia();
            
            allmedialist = Utilities.existsMediaDirectory(allmedialist);
            
            setMediaBuffer(allmedialist);
	        
            reactOnSearchQuery();
        }
	}
	
	/**
	 * Sets the media in the table depening on query
	 * 
	 */
	private void reactOnSearchQuery()
	{  
	    List<Medium> medialist = retrieveMediaMatchesTerm(_searchquery);
	    medialist = Utilities.existsMediaDirectory(medialist);
        setMediaToTable(medialist);
	}
	
	/**
	 * Sets the media which should be buffered.
	 * 
	 * @param list containing media
	 */
	private void setMediaBuffer(List<Medium> media)
	{
	    //clears the buffer first
	    if(!_mediabuffer.isEmpty())
	    {
	        _mediabuffer.clear();
	    }
	    
	    //creates buffer
	    for(Medium medium : media)
	    {
	        String key = medium.getTitle();
	        	        
	        _mediabuffer.put(key, medium);
	    }
	}
	
	
	/**
	 * Checks whether the given serchterm matches with media (title) in the database.
	 * 
	 * @param searchterm to compare 
	 * @return List containing media which match with searchterm
	 */
	private List<Medium> retrieveMediaMatchesTerm(String searchterm)
	{
	    String regex = ".*" + searchterm.toLowerCase() +"{1,}.*";
	    
	    List<Medium> matchinglist = new ArrayList<Medium>();
	    
	    Iterator<Entry<String, Medium>> it = _mediabuffer.entrySet().iterator();
	    
	    while(it.hasNext())
	    {
	        Entry<String, Medium> entry = (Entry<String, Medium>) it.next();
	       
	        Medium medium = (Medium) entry.getValue();
	        
	        if(Pattern.matches(regex, medium.getTitle().toLowerCase()))
            {
	            matchinglist.add(medium);
            }
	    }
	    return matchinglist;
	}
}
