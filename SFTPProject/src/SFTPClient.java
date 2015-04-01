import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Lanette Braxton
 *
 */
public class SFTPClient 
{
	private CFMLayer cfmInterface;
	private FileManager serverNameFile;
	private FileManager fileManager;
	private Scanner userInput;
	private String url;
	private String severFileLocation;
	private InetAddress serverInetAddress;
	private String clientAddress;
	private int clientPort;
	private int serverPort;
	
	public SFTPClient()
	{
		cfmInterface = new CFMLayer(ProcessSourceEnum.CLIENT_PROCESS);
	}
	
	public void intialize()
	{
		//Retrieve Server InetAddress and Port number from DNS server
		try 
		{
			serverInetAddress= InetAddress.getByName("127.0.0.1");
			
		} catch (UnknownHostException e) 
		{
			e.printStackTrace();
			System.out.println("Error retrieving InetAddress");
		}
		serverPort = 5432;
	}
	
	
	public void run()
	{
		//This method will take a File object argument to retrieve the server
		//InetAddress and Port number
		this.intialize();
				
		try
		{
			System.out.println("Please enter a command to send to the Server");			
			userInput = new Scanner(System.in);			
			String commandLine;
			
			while((commandLine = userInput.nextLine()) != null)
			{
				List<String> commandList = new ArrayList<String>();
				StringTokenizer commandTokens = new StringTokenizer(commandLine, " ");
				
				while(commandTokens.hasMoreTokens())
				{
					commandList.add(commandTokens.nextToken());
				}
								
				if(this.validateInput(commandList))
				{
					
					cfmInterface.CtrlTranspSend(commandList, this.getServerInetAddress(), this.getServerPort());	
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error reading input.");
			System.exit(0);
		}
		
		
	}
	
	private boolean validateInput(List<String> inputRequest)
	{
		
		if(!(inputRequest.size() > 2))
		{
			System.out.println("You did not enter a valid request.");
			return false;
		}
		
		try
		{
			CommandEnum.valueOf(inputRequest.get(0).toUpperCase());
			
		}catch(IllegalArgumentException e)
		{
			System.out.println("You did not enter a valid request.");
			return false;
		}
		
		//TODO Still working on this
		/*String WINDOWS_FILE_PATTERN = "(?:[a-zA-Z]\\:)\\\\([\\w-]+\\\\)*\\w([\\w-.])+";
		String UNIX_LIKE_FILE_PATTERN = "^(/)?([^/\0]+(/)?)+$";
				
		Pattern windowsPattern = Pattern.compile(WINDOWS_FILE_PATTERN);
		Pattern unixLikePattern = Pattern.compile(UNIX_LIKE_FILE_PATTERN);
		
		Matcher windowsSourceMatcher = windowsPattern.matcher(inputRequest.get(0));
		Matcher windowsDestMatcher = windowsPattern.matcher(inputRequest.get(0));
		Matcher unixLikeSourceMatcher = unixLikePattern.matcher(inputRequest.get(0));
		Matcher unixLikeDestMatcher = unixLikePattern.matcher(inputRequest.get(0));
		
		if((windowsSourceMatcher.matches() && windowsDestMatcher.matches()) || (unixLikeSourceMatcher.matches() && unixLikeDestMatcher.matches()))
		{
			//TODO Get the server file System path format and compare
			return true;
		}*/
		
		return true;
		
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	

	public String getSeverFileLocation() {
		return severFileLocation;
	}
		
	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}	

	public int getPortNumber() {
		return clientPort;
	}

	public void setPortNumber(int portNumber) {
		this.clientPort = portNumber;
	}

	public FileManager getFileManager() {
		return fileManager;
	}
	
	public int getClientPort() {
		return clientPort;
	}


	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

	public InetAddress getServerInetAddress() {
		return serverInetAddress;
	}

	public void setServerInetAddress(InetAddress serverInetAddress) {
		this.serverInetAddress = serverInetAddress;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SFTPClient client = new SFTPClient();
		
		client.run();
		
	}

}
