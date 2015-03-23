import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class PacketsForData 
{
	private static final int MAX_FILE_SIZE = 1024; 
	private List<ArrayList> windowsOfPackets = null;
	 
	public PacketsForData()
	{	
	}
	
	public PacketsForData(File file)
	{
		byte[] fileContents = this.retrieveFileData(file);
		
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
	public List<ArrayList> getWindowsOfPackets() {
		return windowsOfPackets;
	}

	public void setWindowsOfPackets(List<ArrayList> windowsOfPackets) {
		this.windowsOfPackets = windowsOfPackets;
	}
	
		
}
