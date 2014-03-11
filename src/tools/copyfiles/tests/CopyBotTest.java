package tools.copyfiles.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import magicbook.MagicBook;

import org.junit.Test;

import tools.copyfiles.CopyBotTool;

public class CopyBotTest {

	@Test
	public void testCopyFiles() {
		Set<File> files = new HashSet<File>();
		files.add(new File(MagicBook.getMagicDirectory()+"//tests//EAR//ego.mp3"));
		files.add(new File(MagicBook.getMagicDirectory()+"//tests//EAR//EAR.mp3"));
		try {
			List<File> newFiles = CopyBotTool.copyFiles(files,MagicBook.getMagicDirectory()+"//tests//");
			assertTrue(newFiles!=null);
			for(File f : newFiles){
				assertTrue(f.exists());
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testWorkingDirectory(){
		System.out.println("Working Directory = " +
		           System.getProperty("user.dir"));
	}

}
