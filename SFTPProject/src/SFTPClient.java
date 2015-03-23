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
	private Socket connection;
	private FileManager serverNameFile;
	private FileManager fileManager;
	private BufferedReader inputReader;
	private DataInputStream inputDataStream;
	private DataOutputStream outputDataStream;
	private PrintStream outputData;
	private FileInputStream fileOutputStream;
	private ObjectInputStream objInputStream;
	private ObjectOutputStream objOutputStream;
	private EnumSet<CommandEnum> commandEnumSet;
	private Scanner userInput;
	private String inputFile;
	private String url;
	private String severFileLocation;
	private String serverAddress;
	private String clientAddress;
	private int portNumber;
	
	public SFTPClient()
	{
		cfmInterface = new CFMLayer();
	}
	
	
	public void run()
	{
		cfmInterface.TranspInitCntrl();
		cfmInterface.TranspInitData();
		cfmInterface.CtrlTranspSend();		
	}
	
	/*public void CtrlTranspSend(List<String> commandLine)
	{
		
		CommandEnum command = CommandEnum.valueOf(commandLine.get(0).toUpperCase());
		
		switch (command)
		{
			case RCD :
			{
				
				break;
			}
			case LCD :
			{
				break;
			}
			case PWD :
			{
				break;
			}
			case RLS :
			{
				break;
			}
			case LLS :
			{
				break;
			}
			case GET :
			{
				break;
			}
			case MGET :
			{
				break;
			}
			case PUT :
			{
				break;
			}
			case MPUT :
			{
				break;
			}
		}
				
		
	}*/
	
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
		
	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}	

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public FileManager getFileManager() {
		return fileManager;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SFTPClient client = new SFTPClient();
		
		client.run();
		
	}

}
