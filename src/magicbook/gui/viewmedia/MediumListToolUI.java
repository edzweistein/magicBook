package magicbook.gui.viewmedia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


import tools.ToolUI;

public class MediumListToolUI extends ToolUI
{

	private MediaTableModel _mediaTableModel;
	private JTable _mediaTable;
	
	private JTextField _searchfield;
	
	//private final String defaultsearch = "search";
	
	
    public MediumListToolUI()
    {
        _mainPanel = createPanel();
    }
    
    
    @Override
    public JPanel createPanel()
    {
        JPanel mPanel = new JPanel(new BorderLayout());
        
        _searchfield = new JTextField("search");

        mPanel.add(_searchfield, BorderLayout.NORTH);
        mPanel.add(createMediaTable(), BorderLayout.CENTER);
    	
    	return mPanel;
    }
    
     
    /**
     * Creates the media table and adds the Tablemodele.
     * 
     * @return ui component for media table
     * 
     */
    private JComponent createMediaTable()
    {
    	JScrollPane medienListScrollPane = new JScrollPane();
        medienListScrollPane.setBorder(BorderFactory.createTitledBorder(
                null, "Media", TitledBorder.LEADING,
                TitledBorder.DEFAULT_POSITION, new Font("SansSerif", Font.BOLD, 14)));
        
        //sets the color for the background and vertical / horizontal scrollbar
        medienListScrollPane.setBackground(Color.GRAY);
        medienListScrollPane.getVerticalScrollBar().setBackground(
                Color.WHITE);
        medienListScrollPane.getHorizontalScrollBar().setBackground(
                Color.WHITE); 
        
       _mediaTableModel = new MediaTableModel();
       
       _mediaTable = new JTable();
       
       medienListScrollPane.setViewportView(_mediaTable);
       
       _mediaTable.setModel(_mediaTableModel);
       
       //font for entries
       _mediaTable.setFont( new Font("Tahoma", Font.BOLD, 14));
       
       //allow multiple selection
       _mediaTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
       
       TableColumnModel columnModel = _mediaTable.getColumnModel();
       TableColumn columntype = columnModel.getColumn(0);
       TableColumn columntitle = columnModel.getColumn(1);
       
       columntype.setPreferredWidth(40);
       columntitle.setPreferredWidth(100);
       
       JTableHeader tableheader = _mediaTable.getTableHeader();
       tableheader.setFont( new Font("Tahoma", Font.BOLD, 14));
       tableheader.setReorderingAllowed(false);
       tableheader.setResizingAllowed(true);
       
       return medienListScrollPane;
    }
    
    /**
     * Creates the searchfield, which is located above the media table.
     * 
     * @return textfield used to search through media 
     */
    private JTextField createSearchField()
    {
        JTextField msearchfield = new JTextField("search");
        
        msearchfield.getDocument().addDocumentListener(new DocumentListener()
        {
            
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                //do nothing
                
            }
            
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void changedUpdate(DocumentEvent e)
            {
                // TODO Auto-generated method stub
                
            }
        });
        
        msearchfield.addMouseListener(new MouseListener()
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
        
        return msearchfield;
    }
    
    
    
    /**
     * Returns the textfield for searching through media listed in the table
     * 
     * @return textfield used to search through media in table
     */
    public JTextField getSearchField()
    {
        return _searchfield;
    }
    
    /**
     * Returns the tablemodel for the table of Panel.
     * 
     * @return
     */
    public MediaTableModel getMediaTableModel()
    {
        return _mediaTableModel;
    }
    
    /**
     * Returns the Table object of this panel.
     * 
     * @return
     */
    public JTable getMediaListTable()
    {
        return _mediaTable;
    }
}
