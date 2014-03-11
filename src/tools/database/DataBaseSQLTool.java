package tools.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import magicbook.objects.Medium;
import magicbook.objects.Music;
import magicbook.objects.Type;
import magicbook.objects.URL;
import magicbook.objects.Video;

/**
 * Offers methods to connect / disconnect / update / insert (to) a SQL Database.
 * e.g. create new entry for a new medium 
 * 
 * @author Niels 
 *
 */
public class DataBaseSQLTool extends DataBaseToolGen{
    
    
    
    public static final String CREATE_TABLE_MUSIC = "CREATE TABLE music IF NOT EXISTS (hash INTEGER, id INTEGER AUTOINCREMENT, title VARCHAR(60) NOT NULL, " +
    		"interpret VARCHAR(60) NOT NULL, year INTEGER(4), PRIMARY KEY(hash, id)); ";
    
    
    public static final String SELECT_MUSIC = "SELECT hash, id, title, interpret, year FROM music";
    

    public static final String SELECT_VIDEO = "SELECT hash, id, title, regie, year FROM video";
    
    public static final String SELECT_PICURES = "SELECT hash, id , title ,....";
    

    private static Connection _connection ;    
   
    
    public DataBaseSQLTool() 
    {
        openConnection();
    }

    
	/**
	 * Creates and opens a connection to the local database.
	 * 
	 */
	public static void openConnection()
	{
		try
        {
            Class.forName("org.sqlite.JDBC");            
        }
        catch(Exception e)
        {
            System.err.println("Kann Treiber nicht laden : " + e);
        }
        
        try
        {
        	//open connection    
        	_connection = DriverManager.getConnection("jdbc:sqlite:data/magicbook.db");
        	
        }
        catch(SQLException e)
        {
            System.out.print("Datenbank existiert nicht.");
            System.err.println(" " + e.getMessage());
        }  
	}
	
	/**
	 * Returns the connection object for database transactin.
	 * 
	 * @return object to interact with local database
	 */
	public static Connection getConnection()
	{
	    return _connection;
	}
	

	/**
	 * Closes database connection and resultset.
	 * @throws SQLException 
	 * 
	 */
	public static void closeConnection() 
	{
		try
		{
			_connection.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves a given medium to the database.
	 * 
	 */
	public void save(Medium medium) 
	{
		//different db tables for media types
		switch(medium.getMediaType())
		{
			case MUSIC :
				Music music = (Music) medium;
				saveMusic(music.getID(), music.getTitle(), music.getInterpret(), music.getYear());
					break;
			case VIDEO :
				Video video = (Video) medium;
				saveVideo(video.getID() ,video.getTitle(), video.getRegie(), video.getYear());
					break;
			case URL:
				URL url = (URL) medium;
				saveURL(url.getID(),url.getURL(),url.getTitle());
				break;
			default : 
				break;
		}
		
	}

	/** Checks whether the medium already exists in the database.
	 * 
	 * 
	 * @param medium 
	 * @return true, if medium got an entry in the database
	 */
	public boolean exists(Medium medium)
	{
	    switch(medium.getMediaType())
	    {
	        case MUSIC :
	            return existsMusic(medium.getID());
	        case VIDEO :
	            return existsVideo(medium.getID());
	        case URL:
	        	return existsURL(medium.getID());
            default : 
                return false;
	    }
	}
	
	private static boolean existsURL(int id) {
		 String query = "SELECT COUNT(hash) FROM url WHERE hash = "+  id + " ;" ;
         
         try{
             Statement mstm = _connection.createStatement();
             
             ResultSet result = mstm.executeQuery(query);
         
             //if resultset is not empty
             if(result.next())
             {
                 return true;
             }
         }
         catch(SQLException e)
         {
             e.printStackTrace();
         }
		
     return false;
	}


	/**
	 * Checks whether the media (music type) exists in the database.
	 * 
	 * @param hash of the media
	 * @return true, if media exists in db
	 */
	private boolean existsMusic(int hash) 
	{
		if(isOpen())
		{
    	    String query = "SELECT COUNT(hash) FROM music WHERE hash = "+  hash + " ;" ;
            
            try{
                Statement mstm = _connection.createStatement();
                
                ResultSet result = mstm.executeQuery(query);
            
                //if resultset is not empty
                if(result.next())
                {
                    return true;
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
		}
        return false;
	}
	
	/**
	 *  * Checks whether the media (music video) exists in the database.
     * 
     * @param hash of the media
     * @return true, if media exists in db
	 */
	private boolean existsVideo(int hash) 
	{
	    if(isOpen())
	    {
    	    String query = "SELECT COUNT(hash) FROM video WHERE hash = "+  hash + " ;" ;
    	    
    	    try{
        	    Statement mstm = _connection.createStatement();
        	    
        	    ResultSet result = mstm.executeQuery(query);
    	    
        	    //if resultset is not empty
        	    if(result.next())
        	    {
        	        return true;
        	    }
    	    }
    	    catch(SQLException e)
    	    {
    	        e.printStackTrace();
    	    }
	    }
        return false;
	    
	}
	
	
	/**
	 * Loads a medium for a specific type and given id from QR-Code.
	 * 
	 * @return medium with the id
	 */
	public Medium loadMediumForType(String stringtype, String qrID)
	{
	    int type = getType(stringtype);
	    
	    if(type != -1)
	    {
	        if(isOpen())
	        {
	            Medium m;
    	        switch (type)
                {
                    case 0 :
                        m = loadMusicByHash(qrID);
                        return m;
                    case 1 :
                        m = loadVideoByHash(qrID);
                        return m;
                    case 2 :
                    	m = loadURLByHash(qrID);
                    	return m;
                    default:
                        return null;
                }
	        }
	    }
	    return null;
	}
	
	
	private Medium loadURLByHash(String qrID) {
		String query = "SELECT title,url FROM url WHERE hash = " + qrID + " ;" ;
	    
	    try
	    {
	        Statement stm = _connection.createStatement();
	        ResultSet result = stm.executeQuery(query);
	        
	        
	        if(result.next())
	        {
	            URL url = new URL(result.getString("url"),result.getString("title"));
	            return url;
	        }
	    }
	    catch(SQLException e)
	    {
	        e.printStackTrace();
	    }
        return null;
	}


	@Override 
	public ArrayList<Medium> loadAllMedia()
	{
		ArrayList<Medium> media = new ArrayList<Medium>();
		
		//loads the music
		media.addAll(loadAllMedia(Type.MUSIC));
		
		//loads the videos
		media.addAll(loadAllMedia(Type.VIDEO));
		
		//loads the pictures
		media.addAll(loadAllMedia(Type.PICTURES));
		
		return media;
	}
	
    @Override
    public List<Medium> loadMediaByTerm(String term)
	    {
	        if(!term.equals("") || !term.equals("search"))
	        {
                ArrayList<Medium> media = new ArrayList<Medium>();
    	         
    	         //loads the music matching the term
    	         media.addAll(loadMusicByTerm(term));
    	         
    	         //loads the videos matching the term
    	         media.addAll(loadVideoByTerm(term));
    	         
    	         return media;
	        }
	        else 
	        {
	            return loadAllMedia();
	        }
	    }
	
	/**
	 * Returns media(of type) which is stored in the database.
	 * e.g. if parameter type = 'music', all music data from database will be returned
	 * 
	 * @return List containing media object of particular Type
	 */
	private ArrayList<Medium> loadAllMedia(Type type)

	{
	    ArrayList<Medium> media = new ArrayList<Medium>();
	    String sql = "";
  	    ResultSet result;
	    
	    if(isOpen())
	    {
	    	switch(type)
		    {
		    	case MUSIC :
		    		sql = SELECT_MUSIC;
		    		try
		    		{
		    			Statement stm = _connection.createStatement();
		    			result = stm.executeQuery(sql);
		    			media = retrieveMusic(result);
		    			break;
		    		}
		    		catch(SQLException e)
		    		{
		    			System.out.print("Fehler beim Ausf�hren von SELECT_MUSIC");
		    			e.printStackTrace();
		    		}
		    	case VIDEO : 
		    		sql = SELECT_VIDEO;
		    		try{
		    			Statement stm = _connection.createStatement();
			    		result = stm.executeQuery(sql);
			    		media = retrieveVideos(result);
			    		break;
		    		}
		    		catch(SQLException e)
		    		{
		    			System.out.print("Fehler beim Ausf�hren von SELECT_VIDEO");
		    			e.printStackTrace();
		    		}
		    }
	    }
	    return media;
	}

	
	
	/**
	 * Saves music to the database.
	 * 
	 * @param hashcode of the medium
	 * @param title of the medium
	 * @param interpret of the medium
	 * @param year of the medium
	 */
	private static void saveMusic(int hashcode, String title, String interpret, int year)  
	{
		
    	String insert = "INSERT INTO  music (hash, title, interpret, year) VALUES ( " +
    						hashcode + " , " +
    						"'" + title + "' , " + 
    						"'" + interpret + "' , " +
    						year + " )";
    	
    	try
    	{
        	Statement stm = _connection.createStatement();
    	    
    	    stm.executeUpdate(insert);
    	    
    	    System.out.println("Erfolgreich gespeichert");
    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.err.println("" + e.getMessage());
    	}
	}
	
	/**
	 * Saves a video in the database.
	 * 
	 * @param hashcode of the medium
	 * @param title of the medium
	 * @param regisseur of the medium
	 * @param year of the medium
	 */
	private static void saveVideo(int hashcode, String title, String regisseur, int year)
	{
		String insert = "INSERT INTO video (hash, title, regie, year) VALUES ( " +
				hashcode + " , " +
				"'" + title + "' , " + 
				"'" + regisseur + "' , " +
				year + " )";

    	try
    	{
    		Statement stm = _connection.createStatement();
    		
    		stm.executeUpdate(insert);
    		
    		System.out.println("Erfolgreich gespeichert");
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.err.println("" + e.getMessage());
    	}
	}
	private void saveURL(int hashcode,String url,String title){
		String insert = "INSERT INTO url (hash, url,title) VALUES ( " +
				hashcode + " , " +
				"'" + url + "' , " + 
				"'" + title + "' )";


	try
	{
		Statement stm = _connection.createStatement();
		
		stm.executeUpdate(insert);
		
		System.out.println("Erfolgreich gespeichert");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.err.println("" + e.getMessage());
	}
	}
	
	/**
	 * Loads a song by given hashcode.
	 * 
	 * @param hashcode
	 * @return
	 */
	private static Medium loadMusicByHash(String hashcode)
	{
	    String query = "SELECT hash, id, title, interpret, year FROM music WHERE hash = " + hashcode + " ;" ;
	    
	    try
	    {
	        Statement stm = _connection.createStatement();
	        ResultSet result = stm.executeQuery(query);
	        
	        
	        if(result.next())
	        {
	            Music music = new Music(result.getString("title"), result.getString("interpret"), result.getInt("year"));
	            return music;
	        }
	    }
	    catch(SQLException e)
	    {
	        e.printStackTrace();
	    }
        return null;
	}

	/**
	 * Loads music entry from database matching the parameter
	 * 
	 * @param term 
	 * @return list of music matching the term
	 */
	private List<Medium> loadMusicByTerm(String term)
	{
	    List<Medium> medialist = new ArrayList<Medium>();
	    
	    String query = "SELECT id , hash, title, interpret, year FROM music WHERE title = " + term +" OR interpret = " + term + " ;" ;

	    try
	    {
	        Statement stm = _connection.createStatement();
	        ResultSet result = stm.executeQuery(query);
	        
	        while(result.next())
	        {
	            String title = result.getString("title");
	            String interpret = result.getString("interpret");
	            int year = result.getInt("year");
	            
	            Music music = new Music(title, interpret, year);
	            
	            medialist.add(music);
	        }
	    }
	    catch(SQLException e)
	    {
	        e.printStackTrace();
	    }
	    
	    return medialist;
	}
	
	/**
	 * Loads video entry from database matching the parameter
	 * 
	 * @param term 
	 * @return list of video matching the term
	 */
	private List<Medium> loadVideoByTerm(String term)
    {
        List<Medium> medialist = new ArrayList<Medium>();
        
        String query = "SELECT  id, hash, title, regie, year FROM video WHERE title = " + term +" OR regie = " + term + " ;" ;

        try
        {
            Statement stm = _connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            
            while(result.next())
            {
                String title = result.getString("title");
                String regisseur = result.getString("regie");
                int year = result.getInt("year");
                
                Video video = new Video(title, regisseur, year);
                
                medialist.add(video);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return medialist;
    }
	
	/**
	 * Loads a video by given hashcode.
	 * 
	 * @param hashcode
	 * @return
	 */
	private static Medium loadVideoByHash(String hashcode)
	{
	    String query = "SELECT hash, id, title, regie, year FROM video WHERE hash = " + hashcode + " ;" ;
        
        try
        {
            Statement stm = _connection.createStatement();
            ResultSet result = stm.executeQuery(query);
            
            
            if(result.next())
            {
                Video video = new Video(result.getString("title"), result.getString("regie"), result.getInt("year"));
                return video;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
	    
	}
	
	
	/**
	 * Retrieves all music entry in the database. 
	 * 
	 * @param results  Cursor over rows music table
	 * @return lists containing whole stored music(only music objects)
	 */
	private ArrayList<Medium> retrieveMusic(ResultSet results)
	{
		
		ArrayList<Medium> media = new ArrayList<Medium>();
		
		try{
			while(results.next())
			{
				 String title = results.getString("title");
				 String interpret = results.getString("interpret");
				 int year = results.getInt("year");
		            
				 Music music  = new Music(title, interpret, year);
				 media.add(music);
			}
		}
		catch(Exception e)
		{
			System.out.println("Fehler beim Laden der Musik");
			e.printStackTrace();
		}
		return media;
	}
	
	
	/**
	 * Retrieves all video stored in database
	 * 
	 * @param results , Cursor over rows in video table
	 * @return List containing whole stored videos(only video objects) 
	 */
	private ArrayList<Medium> retrieveVideos(ResultSet results)
	{
		ArrayList<Medium> media = new ArrayList<Medium>();
		
		try{
			while(results.next())
			{
				 String title = results.getString("title");
				 String regisseur = results.getString("regie");
				 int year = results.getInt("year");
		            
				 Video video = new Video(title, regisseur, year);
				 media.add(video);
			}
		}
		catch(Exception e)
		{
			System.out.println("Fehler beim Laden der Videos");
			e.printStackTrace();
		}
		return media;
		
	}
	/**
     * Decides whether the sent data (string) from the android app is a control
     * command and not a medium
     * 
     * @param qrCode
     * @return
     */
    public static boolean isCommand(String qrCode) {
        if (qrCode != null) {
            if (qrCode.startsWith("volume"))
                return true;
            if (qrCode.startsWith("c"))
                return true;
            if (qrCode.startsWith("prev"))
                return true;

        }
        return false;
    }
	
	/**
     * Indicates the status of the database connection.
     * 
     * @return true, if connection is still open, false if connection is closed
     */
    public static boolean isOpen()
    {
        if(_connection != null)
        {
            try
            {
                if(!_connection.isClosed())
                {
                    return true;
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }
    
	/*
	private void createDBTables()
    {
        String sqlstatement = "CREATE IF NOT EXISTS medien ....";
    }*/
	

    @Override 
    public void delete()
    {
    	//TODO implementation : delete media from db
    }
    

/****************************** for test purposes *****************************************/
    
    /**
     * Deletes the entry in the database with this id.
     */
	
	public void deleteMusic(int hashcode) {
		String query = "DELETE FROM music WHERE hash = " +hashcode + " ;";
		
		try
		{
			Statement stm = _connection.createStatement();
	        stm.executeUpdate(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
