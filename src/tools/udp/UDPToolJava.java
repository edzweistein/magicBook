package tools.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import magicbook.MagicBook;
import magicbook.material.Observable;
import magicbook.objects.Medium;
import tools.database.DataBaseToolGen;

/**
 * @author edzweistein
 * 
 */

public class UDPToolJava extends Observable implements UDPTool,Runnable {

	private final int PORT = 2562;
	private DatagramSocket _serverSocket;
	private byte[] _receiveData = new byte[1024];
	private byte[] _sendData = new byte[1024];
	private DatagramPacket _receivePacket;
	private String _foundString;//,_lastString;
	

	/**
	 * Constructor
	 * 
	 */
	public UDPToolJava() throws SocketException {
		try {
			initConnection();
			_receivePacket = new DatagramPacket(_receiveData, _receiveData.length);
		} catch (SocketException e) {
			throw e;
		}
	}

	/**
	 * Intializes the connection to the socket
	 * 
	 * @throws SocketException
	 */
	private void initConnection() throws SocketException {
		try {
			_serverSocket = new DatagramSocket(PORT);
			//_serverSocket.setReceiveBufferSize(1024);
			startCheckLoop();
		} catch (SocketException e) {
			throw e;
		}
	}
	
	/**
	 * Closes the connection from the socket to the Port,should be called when closing program
	 * 
	 */
	public void closeConnection(){
		if(_serverSocket != null)
			_serverSocket.close();
	}
	
	/**
	 * Starts the loop to check whether information received via udp.
	 */
	private void startCheckLoop(){
		Thread runner = new Thread(this, "UDPCheckThread");
		runner.start();
	}

	/**
	 * Gets the data from the socket (on PORT)
	 * 
	 * @return
	 * @throws IOException
	 */
	private String getDataFromSocket() throws IOException {
		if(_serverSocket != null){
			DatagramPacket newPacket = new DatagramPacket(_receiveData,_receiveData.length);
			_serverSocket.receive(newPacket);
			String temp = new String(newPacket.getData());
			System.out.println("Datapacket: "+temp);
			_receivePacket=newPacket;
			if(_receivePacket != null)
				return new String(_receivePacket.getData());
			
		}
		return null;
	}

	/**
	 * Sends data to the last communication partner (only works after one
	 * package was received before)
	 * 
	 * @param data
	 * @throws IOException
	 */
	public void sendDataToLastContact(String data) throws UDPException {
		if (isKnowingCommunicationPartner()) {
			InetAddress IPAddress = _receivePacket.getAddress();
			int port = _receivePacket.getPort();
			_sendData = data.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(_sendData,
					_sendData.length, IPAddress, port);
			try {
				_serverSocket.send(sendPacket);
			} catch (IOException e) {
				throw new UDPException(e.getMessage());
			}
		} else {
			throw new UDPException(
					"Don't know where to send data.Need to receive one package first!");
		}
	}

	/**
	 * Returns whether the communication partner is already known.
	 * 
	 * @return true, if communication partner is known
	 */
	private boolean isKnowingCommunicationPartner() {
		return (_receivePacket != null && _receivePacket.getAddress()!=null);/*TODO: check port as well 15.06.2012 */
	}

	@Override
	public Medium getMedium() throws UDPException {
		
		if(_foundString == null)
			return null;
		DataBaseToolGen database = MagicBook.getDatabase();
		if(database!=null)
			return database.loadMediumbyQRCode(_foundString);
		return null;

	}

	public void run(){
		while(true){
			
			try {
				if(isKnowingCommunicationPartner()){
					//System.out.println("Found Communication Partner with IP-adress:"+_receivePacket.getAddress().toString());
					//_lastString = _foundString;
					_foundString = getDataFromSocket();
					System.out.println("_foundString: "+_foundString);
					informAllObserversAboutChanges();
				}
				else
				{
					checkForCommunicationPartner();
				}
			}catch(NullPointerException e){
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks for new communication partner.
	 * 
	 */
	private void checkForCommunicationPartner() throws IOException {
		_foundString = getDataFromSocket();
	}



}
