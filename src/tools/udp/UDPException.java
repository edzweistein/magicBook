package tools.udp;

/**
 * @author edzweistein
 *
 */
public class UDPException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _message;
	
	/**Constructor 
	 * @param message The message of this exception 
	 */
	public UDPException(String message){
	   assert message != null : "Vorbedingung verletzt: s!=null";
	   _message =message;
	}
	
	@Override
	public String getMessage(){
		return _message;
	}
}
