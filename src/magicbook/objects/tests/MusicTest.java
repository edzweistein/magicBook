package magicbook.objects.tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import magicbook.material.Comment;
import magicbook.objects.Medium;
import magicbook.objects.Music;

import org.junit.Before;
import org.junit.Test;

import tools.database.DataBaseSQLTool;
import tools.database.DataBaseToolGen;
import tools.database.DatabaseTool;

public class MusicTest {

	private Music m1;
	private Music m2;

	@Before
	public void setUp() {
		m1 = new Music("Wolfmother", "Wolfmother", 2005,
				new ArrayList<Comment>(), new HashSet<String>());
		m2 = new Music("The Beatles", "The Beatles", 1968,
				new ArrayList<Comment>(), new HashSet<String>());
	}

	@Test
	public void testTags() {
		m1.addTag("geil");
		assertTrue(m1.getTags().contains("geil"));
	}

	@Test
	public void testComments() {
		Comment c = new Comment("Hallo");
		m2.addComment(c);
		assertTrue(m2.getComments().contains(c));
	}
	
	@Test
	public void testHashCode(){
		assertFalse(m1.getID()==m2.getID());
	}
	
	/*@Test
	public void testPlaylist(){
		LinkedList<File> files = new LinkedList<File>();
		files.add(new File("//home//jonas//Musik//Trojan Seventies Box Set 2//1-01 Fire And Rain.m4a"));
		files.add(new File("//home//jonas//Musik//Trojan Seventies Box Set 2//1-01 Fire And Rain.m4a"));
		try {
			m1.createSpiffPlaylist(files,System.getProperty("user.dir"));
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		}*/
	
	@Test
	public void testSaveAndLoad(){
		try {
			//DataBaseToolGen database = MagicBook.getDatabase();
			//database.save(m2)
			DatabaseTool.saveToFile(m2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*Music m3 =(Music) Database.loadMediumFromFile(Music.TYPE, m1.getID());
		assertTrue(m3!=null);
		assertTrue(m1.getID()==m3.getID());
		System.out.println(m1.getInterpret()+"|"+m3.getInterpret());*/
		
	}
	
	@Test
	public void testEquals(){
		
		assertTrue(m1.equals(m1));
		
	}
	
	@Test
	public void testLoadAllMusicMediums(){
		DataBaseToolGen database = new DataBaseSQLTool();
		List<Medium> liste =database.loadAllMedia();
		for(Medium m : liste){
			Music music = (Music)m;
			System.out.println(music.getInterpret()+" - "+music.getTitle());
		}
		
	}

}
