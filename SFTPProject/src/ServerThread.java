import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @author Lanette Braxton
 *
 */
public class ServerThread extends Thread
{
	private Socket thread;
	private BufferedReader inputReader;
	private DataInputStream inputDataStream;
	private DataOutputStream outputDataStream;
	private PrintStream outputData;
	private FileOutputStream fileOutputStream;
	private ObjectInputStream objInputStream;
	private ObjectOutputStream objOutputStream;
	private String outputFile;
	private int connCount;
 
	public ServerThread(Socket socket, int socketCount)
	{
		thread = socket;
		connCount = socketCount;
		try
		{
			inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputData = new PrintStream(socket.getOutputStream());
			inputDataStream = new DataInputStream(socket.getInputStream());
			outputDataStream = new DataOutputStream(socket.getOutputStream());
			
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error creating output steams.");
			System.exit(0);
		}
		
	}
	
	private void DataTranspSend()
	{
		
	}
	
	private void DataTranspRecv()
	{
				
	}
	
	private void CtrlTranspRecv()
	{
		try
		{
			String input;
			
			while((input = inputReader.readLine()) != null)
			{
				System.out.println("Got message: \"" + input + "\" from localhost");
				outputData.println("Server got your message " + input );
			}
			
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error creating input steams.");
			System.exit(0);
		}
	}
	
	public void run()
	{		
		this.CtrlTranspRecv();		
	}
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
