import java.io.*;
import java.net.*;

/**
 * @author Lanette Braxton
 *
 */
public class SFTPServer {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private ServerThread thread;
	private String ipAddress;

	public SFTPServer()
	{
		
	}
	
	public void run()
	{
		try 
		{
			serverSocket = new ServerSocket(5432);
			System.out.println("Server socket created....");
			
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error creating Server socket");
			System.exit(0);
		}
		
		for(;;)
		{
			try 
			{
				int threadID = 1;
				socket = serverSocket.accept();
				thread = new ServerThread(socket, threadID);
				threadID++;
				
				System.out.println("Running thread.");
				thread.run();
				
			} catch (IOException e) 
			{
				e.printStackTrace();
				System.out.println("Error making connection to the server");
				System.exit(0);
			}
			
		}
	}
	
	public String getIpAddress() {
		return ipAddress;
	}



	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}	


	public ServerSocket getServerSocket() {
		return serverSocket;
	}


	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SFTPServer server = new SFTPServer();
		server.run();

	}

}
