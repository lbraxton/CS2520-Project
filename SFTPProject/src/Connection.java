import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
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
	private Socket socket;
	
	public Connection(String hostName, int port)
	{
		try 
		{
			socket = new Socket(hostName, port);
			
		} catch (UnknownHostException e) 
		{
			e.printStackTrace();
			System.out.println("The host name could not be resolved");
			
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error creating connection");
		}
	}
	
	public Connection(InetAddress hostName, int port)
	{
		try 
		{
			socket = new Socket(hostName, port);
			
		} catch (UnknownHostException e) 
		{
			e.printStackTrace();
			System.out.println("The host name could not be resolved");
			
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error creating connection");
		}
	}

	public Socket getSocket() 
	{
		return socket;
	}
	
	
}
