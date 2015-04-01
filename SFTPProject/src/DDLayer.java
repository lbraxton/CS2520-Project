import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */

/**
 * @author Lanette Braxton
 *
 */
public class DDLayer 
{
	private DatagramSocket dataSocket;
	private DatagramPacket dataPacket;
	private FileInputStream fileOutputStream;
	private ObjectInputStream objInputStream;
	private ObjectOutputStream objOutputStream;
	static final int MAX_FILE_SIZE = 1024; 
	private Connection serverDataConnection; 
	private Connection clientDataConnection;
	private List<ArrayList> windowsOfPackets = null;
	 
	public DDLayer()
	{	
		
	}
	
	public DDLayer(int serverPort)
	{
		//byte[] fileContents = this.retrieveFileData(file);
		if(null == serverDataConnection || null == serverDataConnection.getSocket())
		{
			serverDataConnection = new Connection(serverPort);
		}
				
		if(null == clientDataConnection || null == clientDataConnection.getSocket())
		{
			clientDataConnection = new Connection();
		}	
		
	}
	
	private byte[] retrieveFileData(File file)
	{
		if(!file.isFile())
		{
			System.out.println("This is not a file.");
			System.exit(0);
		}
		
		if(file.length() > MAX_FILE_SIZE)
		{
			System.out.println("File to large.");
			System.exit(0);
		}
		
		byte[] fileData = null;
		InputStream fileDataStream = null;		
		 
		try
		{
			fileData = new byte[(int) file.length()];
			fileDataStream = new FileInputStream(file);
			fileDataStream.read(fileData);
			
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error reading file data.");
			System.exit(0);
		}finally
		{
			if(null != fileDataStream)
			{
				try {
					fileDataStream.close();
				} catch (IOException e) 
				{			
					e.printStackTrace();
					System.out.println("Error closing file.");
					System.exit(0);
				}
			}
			
		}
		
		return fileData;
	}
	
	
	private void createPackets(byte[] fileData )
	{		
		//TODO Create data packets
				
	}
	
	public void TranspClose()
	{
		serverDataConnection.getSocket().close(); 
		clientDataConnection.getSocket().close();
	}
	
	public List<ArrayList> getWindowsOfPackets() {
		return windowsOfPackets;
	}

	public void setWindowsOfPackets(List<ArrayList> windowsOfPackets) {
		this.windowsOfPackets = windowsOfPackets;
	}
	
		
}
