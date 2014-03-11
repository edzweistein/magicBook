package tools.udp;

import magicbook.objects.Medium;

/**Connects to an UDP port which is saved in the MagicBook.OPTIONS.
 * This port can communicate with other device on the lan.So it builds the bridge to e.g. Android devices.
 * Returns an Medium if getMedium method is called.
 * @author jonas
 *
 */
public interface UDPTool {

	/**
	 * Try to retrieve media for transmitted string via udp.
	 * 
	 * @return Medium if recognized, otherwise null
	 */
	public Medium getMedium() throws UDPException;
	
	
}
