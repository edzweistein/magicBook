package tools.database.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import magicbook.objects.Medium;
import magicbook.objects.Music;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tools.database.DataBaseSQLTool;

/**
 * Test class for interaction with SQL-database
 * 
 * @author Niels
 *
 */
@SuppressWarnings("static-access")
public class DataBaseSQLToolTest {

	private static DataBaseSQLTool database;
	private static Medium refmedium, refmusic;
	
	@BeforeClass
	public static void setUp(){
		 database = new DataBaseSQLTool();
		 refmusic = new Music("Mein Song", "Guter Interpret" , 2012);
		 refmedium = new Music("Mein Lied", "Karl der Star", 2011);
	}
	
	@AfterClass
	public static void tearDown(){
		if(!database.isOpen())
		{
			database.openConnection();
		}
		database.deleteMusic(refmusic.getID());
		database.deleteMusic(refmedium.getID());
	}
	
    
	@Test
    public void testOpenandCloseConnection()
    {
        database.openConnection();
        
        Connection connection = database.getConnection();
        
        try{
            //checks open operation
            assertFalse(connection.isClosed());
        
            database.closeConnection();
            
            //checks close operation
            assertTrue(connection.isClosed());
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
	
    
    /**
     * Tests the method save.
     * 
     */
	@Test
	public void testSave() 
	{
		
	    /*--- Music --- */
		int hash = refmusic.getID();
		
		database.openConnection();
		database.save(refmusic);
		
		Music music = (Music) database.loadMediumForType("music", "" +hash);
		
		//checks existence
		assertTrue(database.exists(refmusic));
		
		//checks correct reading of db entry
		assertEquals("Mein Song",music.getTitle());
		assertEquals("Guter Interpret", music.getInterpret());
		assertEquals(2012 , music.getYear());
		
		DataBaseSQLTool.closeConnection();
	}
	
	@Test 
	public void testLoadMediumByQRCode()
	{
	       
	    int hash = refmedium.getID();
	    
	    database.openConnection();
	    database.save(refmedium);
	    
	    Music medium = (Music) database.loadMediumbyQRCode("MUSIC " + hash);
	    
	    assertEquals("Mein Lied", medium.getTitle());
	    assertEquals("Karl der Star", medium.getInterpret());
	    assertEquals(2011, medium.getYear());
	    
	    database.closeConnection();
	}

}
