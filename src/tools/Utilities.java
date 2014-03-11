package tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import magicbook.MagicBook;
import magicbook.objects.Medium;
import magicbook.objects.Type;

/**
 * Utilities class for application wide services.
 * 
 * @author Niels
 *
 */
public class Utilities
{

    /**
     * Returns the String representation for this Media type (enum)
     * 
     * @param type
     * @return e.g. 'Music', if Type.Music, ...
     */
    public static String getMediaTypeAsString(Type type)
    {
        String mediaType = "";
        switch(type)
        {
            case MUSIC :
                mediaType = "Music";
                break;
            case VIDEO :
                mediaType = "Video";
                break;
            case PICTURES :
                mediaType = "Pictures";
                break;
            case PROGRAM :
                mediaType = "Program";
                break;
            case REALLIFEOBJECT :
                mediaType = "RealLifeObject";
                break;
        }
        return mediaType;
    }
    
    /**
     * Sorts the files in the standard order (considering their names)
	 * 
	 * @param files to sort
	 */
	public static void sortFilesByName(List<File> files) {
		Collections.sort(files);
	}
	/**
	 * Sorts a given list of files randomly
	 * 
	 * @param files
	 * @return shuffled list of files
	 */
	public static List<File> sortRandomly(List<File> files) {
		Collections.shuffle(files);
		return files;
	}
	
	
	/**
	 * Checks whether an appropriate directory exists for media entry in the db.
	 * 
	 * @param mediafromdb list containing media from database
	 * @return list adjusted list of media with existing directories
	 */
	public static List<Medium> existsMediaDirectory(List<Medium> mediafromdb)
	{
	    ArrayList<Medium> existingmedia = new ArrayList<Medium>(mediafromdb);
	    for(int index = 0; index < existingmedia.size(); index++)
	    {
	        Medium m = existingmedia.get(index);
	        String mediaFolderPath = MagicBook.getMagicDirectory() + m.getFolder();
	        
	        File file = new File(mediaFolderPath);
	        if(!file.exists())
	        {
	            existingmedia.remove(index);
	        }
	    }
	    return existingmedia;
	}
	
}
