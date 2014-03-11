package magicbook.gui.addmedia.tests;

import static org.junit.Assert.*;

import javax.swing.JButton;

import magicbook.gui.addmedia.AddMediumTool;
import magicbook.gui.addmedia.AddMediumUI;

import org.junit.Before;
import org.junit.Test;

public class AddMediumToolTest {

	AddMediumTool _addmediumtool;
	AddMediumUI _ui;
	
	@Before
	public void setUp()
	{
	    _addmediumtool = new AddMediumTool();
	    _ui = _addmediumtool.getUI();
	}
	
	@Test
	public void testReset()
	{
	    _addmediumtool.reset();
	    
	    assertEquals(0, _addmediumtool.getNextButtonCount());
	}
	
	@Test
	public void testSetCoverArtDropMode()
	{
	    JButton nextbutton = _ui.getNextButton();
	    JButton resetbutton = _ui.getResetButton();
	    
	    assertFalse(nextbutton.isEnabled());
	    assertFalse(resetbutton.isEnabled());
	    
	}
}
