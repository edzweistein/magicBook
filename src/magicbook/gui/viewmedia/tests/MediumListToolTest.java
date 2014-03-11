package magicbook.gui.viewmedia.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import magicbook.gui.viewmedia.MediaListFormatter;
import magicbook.gui.viewmedia.MediaTableModel;
import magicbook.gui.viewmedia.MediumListTool;
import magicbook.gui.viewmedia.MediumListToolUI;
import magicbook.objects.Medium;
import magicbook.objects.Music;
import magicbook.objects.Video;

import org.junit.Before;
import org.junit.Test;

import tools.Utilities;

public class MediumListToolTest
{
    MediumListTool _mediumlistTool;
    List<Medium> medialist;
    Music testmusic;
    Video testvideo;
    
    @Before
    public void setUp()
    {
        _mediumlistTool = new MediumListTool();
        
        medialist = new ArrayList<Medium>();
        
        testmusic = new Music("Lied", "Interpret", 2010);
        testvideo = new Video("Video", "Regisseur", 2009);
        
        medialist.add(testmusic);
        medialist.add(testvideo);
        
        _mediumlistTool.setMediaToTable(medialist);
    }
    
    @Test
    public void testSetMediaToTable()
    {
        MediumListToolUI ui = _mediumlistTool.getUI();
        
        MediaTableModel tablemodel = ui.getMediaTableModel();
        
        List<MediaListFormatter> mediatablelist = tablemodel.getMedia();
        
        assertEquals(medialist.size(), mediatablelist.size());
        
        for(int i = 0; i < medialist.size(); i++)
        {
            Medium medium = medialist.get(i);
            MediaListFormatter mediumformatter = mediatablelist.get(i);
            
            //Vergleich der Titel
            assertEquals(medium.getTitle(), mediumformatter.getTitle());
            //Vergleich des Typs
            assertEquals(Utilities.getMediaTypeAsString(medium.getMediaType()), mediumformatter.getMediaType());
            //Vergleich der Referenz
            assertEquals(medium, mediumformatter.getMedium());
        }
    }
    
    @Test 
    public void testGetSelectedMedia()
    {
        MediumListToolUI _ui = _mediumlistTool.getUI();
        
        JTable medialisttable = _ui.getMediaListTable();
        
        ListSelectionModel selectionmodel = medialisttable.getSelectionModel();
        selectionmodel.setSelectionInterval(0,0);
        
        List<Medium> selectedmedia = _mediumlistTool.getSelectedMedia();
        
        assertTrue(selectedmedia.size() == 1);
     
        Medium medium = selectedmedia.get(0);
        
        assertEquals(testmusic.getTitle(), medium.getTitle());
        assertEquals(Utilities.getMediaTypeAsString(testmusic.getMediaType()), Utilities.getMediaTypeAsString(medium.getMediaType()));
    }

}
