package tools.copyfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**This class provides methods to copy files within the filesystem
 * @author edzweistein
 *
 */
public class CopyBotTool {
	
    private static Set<File> _files ;
    private static String _path;
    private static List<File> _newFiles;
//	
//	/**
//	 * @param folder
//	 * @return a sorted List of Strings which contain the Song url
//	 */
//	public static List<String> getSongsFromFolder(String folder) {
//		//testen ob nur Namen der Tracks kommen, oder URL 25.04.2012 */
//		File dir = new File(folder);
//
//		// It is also possible to filter the list of returned files.
//		// This example does not return any files that start with `.'.
//		FilenameFilter filter = new FilenameFilter() {
//			public boolean accept(File dir, String name) {
//				// Musikformate, hinzufügen 25.04.2012 */
//				return name.endsWith(".mp3") || name.endsWith(".wma")
//						|| name.endsWith(".m4a");
//			}
//		};
//
//		return sortByTrackNumber(dir.list(filter));
//
//	}
//
//	private static List<String> sortByTrackNumber(String[] children) {
//		//sortieren testen 10.05.2012 */
//		Arrays.sort(children);
//		List<String> list = new ArrayList<String>();
//		for (String s : children) {
//			list.add(s);
//		}
//		return list;
//	}

	/**Copies all files of the set which really exist to the destinationpath
	 * @param files files to copy
	 * @param destinationPath the destination folder path where the new files should be copied to
	 * @return a list of the new copied files
	 * @throws Exception
	 */
	public static List<File> copyFiles(final Set<File> files, String destinationPath)
			throws Exception 
			{
    		_newFiles = new LinkedList<File>();
    		//Create directory if it doesn't exist
    		File directory = new File(destinationPath);
    		_path = destinationPath;
    		_files = files;
    		
    		if(!directory.isDirectory()){
    			directory.mkdir();
    		}
    		
    		/*Thread copyThread = new Thread(new CopyThread());
    		copyThread.start();*/
    		
    		//copy all files to this directory
            for (File sourceFile : _files) {
                //only copy those files that really exist
                if (sourceFile.exists()) {
                    //filter out "#:=" and "ÄäÜüÖö" because of the vlc mrl syntax (see : http://wiki.videolan.org/Media_resource_locator)
                    String filteredFileName = sourceFile.getName().replaceAll("[#=:']", "");
                    filteredFileName = filteredFileName.replaceAll("[Ää]", "ae");
                    filteredFileName = filteredFileName.replaceAll("[Öö]","oe");
                    filteredFileName = filteredFileName.replaceAll("[Üü]","ue");
                    File targetFile = new File(_path+ filteredFileName);
                    // System.out.println(""+destinationPath+sourceFile.getName());
                    if (!targetFile.exists()) {
                        try
                        {
                            targetFile.createNewFile();
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                        _newFiles.add(targetFile);
                        FileChannel inChannel = null;
                        FileChannel outChannel = null;

                        try {
                            inChannel = new FileInputStream(sourceFile).getChannel();
                            outChannel = new FileOutputStream(targetFile).getChannel();
                            inChannel.transferTo(0, inChannel.size(), outChannel);
                        } catch (Exception e) {
//                            throw e;
                        } 
                        finally {
                            
                            try
                            {
                                if (inChannel != null)
                                inChannel.close();
                               
                                if (outChannel != null)
                                outChannel.close();
                            }
                            catch(IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        
    		System.out.println("Finished copying "+_newFiles.size()+" files!");
    		return _newFiles;
		}

}
