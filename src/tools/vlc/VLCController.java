package tools.vlc;

import magicbook.objects.Medium;

public interface VLCController {

	/**Starts playing the medium in the VLC player.
	 * @param m
	 */
	public void playMedium(Medium m);
	
	/**Starts playing a medium in a random order of the elements by creating a randomized
	 * XSPF-Playlist
	 * @param m
	 */
	public void playMediumRandomly(Medium m);
	
	/**
	 * Starts playing or pauses the music player depending on which state vlc is in
	 */
	public void playPause();
	
	/**
	 * Next Item of the VLC playlist
	 */
	public void next();
	
	/**
	 * Previous Item in playlist
	 */
	public void previous();
	
	/**
	 * If fullscreen is not enabled this will enable it. Otherwise if enabled it will disable the fullscreen
	 */
	public void toggleFullscreen();
	
	/**
	 * If the path to VLC has changed, this method can be called. It will start an instance of VLC
	 * with the new path and the new port.
	 */
	public void updateVLCConnection(String path,int port);
	
	
}
