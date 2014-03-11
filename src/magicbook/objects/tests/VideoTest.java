package magicbook.objects.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import magicbook.material.Comment;
import magicbook.objects.Medium;
import magicbook.objects.Video;

import org.junit.Before;
import org.junit.Test;

public class VideoTest {

	private Medium v1, v2;
	
	 @Before
	    public void setUp()
	    {
	        v1 = new Video("Video1", "Peter Jackson", 2011);
	        v2 = new Video("Video2", "Quentin Tarantino", 2009);
	    }
	    
		@Test
		public void testEquals()
		{
		    assertTrue(v1.equals(v1));
		    assertTrue(v2.equals(v2));
		    assertFalse(v1.equals(v2));
		}
		@Test
		public void testTags() {
			v1.addTag("toll");
			assertTrue(v1.getTags().contains("toll"));
		}

		@Test
		public void testComments() {
			Comment c = new Comment("Hallo");
			v2.addComment(c);
			assertTrue(v2.getComments().contains(c));
		}
		
		@Test
		public void testHashCode(){
			assertFalse(v1.getID()==v2.getID());
		}
}
