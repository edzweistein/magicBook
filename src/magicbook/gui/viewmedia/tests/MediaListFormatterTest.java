package magicbook.gui.viewmedia.tests;

import static org.junit.Assert.assertEquals;
import magicbook.gui.viewmedia.MediaListFormatter;
import magicbook.objects.Music;
import magicbook.objects.Video;

import org.junit.Test;

import tools.Utilities;

public class MediaListFormatterTest
{

	/**
	 * Tests whether music object are properly formatted. 
	 */
    @Test
    public void testFormatMusic()
    {
        Music music = new Music("Mein Lied", "Toller Interpret", 2011);
        String musictype = Utilities.getMediaTypeAsString(music.getMediaType());
        
        MediaListFormatter formatter = new MediaListFormatter(music);
        
        assertEquals(formatter.getTitle(), music.getTitle());
        assertEquals(formatter.getMediaType(), musictype);
    }
    
    /**
	 * Tests whether video object are properly formatted. 
	 */
    @Test
    public void testFormatVideo()
    {
        Video video = new Video("Mein Video", "Tolles Regisseur", 2012);
        String videotype = Utilities.getMediaTypeAsString(video.getMediaType());
        
        MediaListFormatter formatter = new MediaListFormatter(video);
        
        assertEquals(formatter.getTitle(), video.getTitle());
        assertEquals(formatter.getMediaType(), videotype);
    }

}
