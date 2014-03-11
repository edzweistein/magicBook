package magicbook.gui.viewmedia;

import magicbook.objects.Medium;
import magicbook.objects.Type;
import tools.Utilities;

/**
 * All media type have got a title and a special type.
 * This class is used to display the common attributes for all media in a table ( of MediaListTool).
 * 
 * @author Niels
 *
 */
public class MediaListFormatter {

	private Medium _medium;
	
	public MediaListFormatter(Medium medium)
	{
		_medium = medium;
	}

	/**
	 * Returns assigned medium
	 * 
	 * @return medium of this formatter object.
	 */
	public Medium getMedium()
	{
		return _medium;
	}
	
	/**
	 * Returns the title of the medium assigned to this formatter object
	 * 
	 * @return title of medium
	 */
	public String getTitle()
	{
		return _medium.getTitle();
	}

	/**
	 * Returns the type of the medium assigned to this formatter object.
	 * 
	 * @return type /kind of media , e.g. 'music', 'video', ...
	 */
	public String getMediaType()
	{
		Type mtype = _medium.getMediaType();
	    
		String mediaType = Utilities.getMediaTypeAsString(mtype);
		
		return mediaType;
	} 
	
	
}

