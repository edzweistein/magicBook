package magicbook.gui.viewmedia;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import magicbook.objects.Medium;

public class MediaTableModel extends AbstractTableModel {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -5769590242603531487L;


	private static final String[] COLUMN_NAMES = new String[] { "Medientype" ,"Titel" };
	
	
	//List, which temporarly saves media entry.
	private List<MediaListFormatter> _medialist;

	
	public MediaTableModel()
	{
		_medialist = new ArrayList<MediaListFormatter>();
	}
	
	
	@Override
	public int getColumnCount() 
	{
		return COLUMN_NAMES.length;
	}

	
	@Override 
	public String getColumnName(int position)
	{
		return COLUMN_NAMES[position];
	}
	
	@Override
	public int getRowCount() 
	{
		return _medialist.size();
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
	
		MediaListFormatter formatter = getMediaFormatter(row);
		String result = "";
		
		switch (column)
		{
			case 0:
			    result = formatter.getMediaType();
			    break;
			case 1:
			    result = formatter.getTitle();
				break;
		}
		return result;
	}
	
	/**
	 * Returns the Medium for a specific row.
	 * 
	 * @param rowIndex of the data
	 * @return the medium which is assigned to the row or null if row does not exists
	 */
	public Medium getMediaPerRow(int row)
	{
		if(rowExists(row))
		{
			return getMediaFormatter(row).getMedium();
		}
		return null;
	}
	
	/**
	 * Sets the media for the table.
	 * 
	 * @param media
	 */
	public void setMedia(List<MediaListFormatter> media)
	{
		_medialist = new ArrayList<MediaListFormatter>(media);
		
		//refreshes the table data
		fireTableDataChanged();
	}
	
	/**
	 * Returns the medialistformatter-object of the table;
	 * 
	 * @return list containing medialistformatter of the table
	 */
	public List<MediaListFormatter> getMedia()
	{
	    return _medialist;
	}
	
	private MediaListFormatter getMediaFormatter(int index)
    {
	        return _medialist.get(index);
    }
	
	/**
	 * Checks whether the nth row exists.
	 * 
	 * @param rowIndex
	 * @return true, if row exists
	 */
	public boolean rowExists(int row)
	{
		if(row < _medialist.size() && row >= 0)
		{
			return true;
		}
		return false;
	}

}
