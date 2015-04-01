import java.io.*;
import java.net.*;

/**
 * @author Lanette Braxton
 *
 */
public class SFTPServer 
{	
	private ServerThread thread;
	private CFMLayer cfmLayer;
	private InetAddress inetAddress;
	private int port;

	public SFTPServer()
	{
		
	}
	
	public void initialize()
	{
		cfmLayer = new CFMLayer(ProcessSourceEnum.SERVER_PROCESS);
		//Retrieve Port number and InetAddress from the Datagram Socket
		port = cfmLayer.getPort();
		inetAddress = cfmLayer.getInetAddress();
		// write address and port to server_info file
		
	}
	
	public void run()
	{
		//This method will take a File object argument to store the server
		//InetAddress and Port number
		this.initialize();
		System.out.println("Running Server");
	
		for(;;)
		{
			
			try 
			{
				cfmLayer.CtrlTranspRecv();
				cfmLayer.execute();
				
			} catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("Error making connection to the server");
				System.exit(0);
			}
			
		}
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public InetAddress getInetAddress() {
		return inetAddress;
	}

	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SFTPServer server = new SFTPServer();
		server.run();

	}

}
