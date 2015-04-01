import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 
 */

/**
* @author Lanette Braxton
 *
 */
public class Connection 
{
	private DatagramSocket socket;
	
	
	public Connection()
	{
		try 
		{
			socket = new DatagramSocket();
		} catch (SocketException e) {
			
			e.printStackTrace();
			System.out.println("Error creating connection");
			System.exit(0);
		}
	}
	
	public Connection(int port)
	{
		try 
		{
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			
			e.printStackTrace();
			System.out.println("Error creating connection");
			System.exit(0);
		}
	}
	
	
	public DatagramSocket getSocket() 
	{
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}
	
	
}
