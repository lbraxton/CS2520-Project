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
		
	}
	
	private void establishConnection()
	{
		/*System.out.println("Please enter a url.");
		
		userInput = new BufferedReader(new InputStreamReader(System.in));
		url = userInput.readLine();*/
		
		//TODO write code to connect to DNS Server and resolve URL
		if(null == connection || !connection.isConnected())
		{
			try
			{
				connection = new Socket("localhost", 5432);
				System.out.println("The control connection has been made");
							
			}catch(IOException e)
			{
				e.printStackTrace();
				System.out.println("Error making a connection.");
				System.exit(0);
			}
		}
		
	}
	
	
	private void TranspInitCntrl()
	{
		if(!connection.isConnected())
		{
			System.out.println("Please establish a connection to host.");
			System.exit(0);
		}
				
		try
		{
			outputData = new PrintStream(connection.getOutputStream());
			inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			System.out.println("Control Connection has been established.");
			
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error creating Control Connection.");
		}
	}
	
	private void TranspInitData()
	{
		if(!connection.isConnected())
		{
			System.out.println("Please establish a connection to host.");
			System.exit(0);
		}
				
		try
		{
			inputDataStream = new DataInputStream(connection.getInputStream());
			outputDataStream = new DataOutputStream(connection.getOutputStream());
			
			
			System.out.println("Data Connection has been established.");
			
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error creating Data Connection.");
		}
		
	}
	
	private void DataTranspSend()
	{
		
	}
	
	private void DataTranspRecv()
	{
		
	}
	
	private void CtrlTranspSend()
	{
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
					outputData.println(commandLine);
					System.out.println("echo: " + inputReader.readLine());
				}
				
			}
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error reading input.");
			System.exit(0);
		}
		
	}
	
	private void TranspCloseCntrl()
	{
		try
		{
			this.userInput.close();
			this.inputReader.close();
			this.outputData.close();
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error closing control connection.");
			System.exit(0);
		}
		
	}
	
	private void TranspClose()
	{
		
	}
	
	public void run()
	{
		this.establishConnection();
		this.TranspInitCntrl();
		this.TranspInitData();
		this.CtrlTranspSend();		
	}
	
	public void CtrlTranspSend(List<String> commandLine)
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
