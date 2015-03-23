import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 */

/**
 * @author Lanette Braxton
 *
 */
public class CFMLayer 
{
	private BufferedReader inputReader;
	private DataInputStream inputDataStream;
	private DataOutputStream outputDataStream;
	private PrintStream outputData;
	private FileInputStream fileOutputStream;
	private ObjectInputStream objInputStream;
	private ObjectOutputStream objOutputStream;
	private Scanner userInput;
	private Connection connection;
	
	
	public CFMLayer()
	{
	
	}
	
	public void TranspInitCntrl()
	{
		/*System.out.println("Please enter a url.");
		
		userInput = new BufferedReader(new InputStreamReader(System.in));
		url = userInput.readLine();*/
		
		//TODO write code to connect to DNS Server and resolve URL
		
				connection = new Connection("localhost", 5432);
				System.out.println("The control connection has been made");
		
		try
		{
			outputData = new PrintStream(connection.getSocket().getOutputStream());
			inputReader = new BufferedReader(new InputStreamReader(connection.getSocket().getInputStream()));
			
			System.out.println("Control Connection has been established.");
			
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error creating Control Connection.");
		}
	}
	
	public void TranspInitData()
	{
		if(!connection.getSocket().isConnected())
		{
			System.out.println("Please establish a connection to host.");
			System.exit(0);
		}
				
		try
		{
			inputDataStream = new DataInputStream(connection.getSocket().getInputStream());
			outputDataStream = new DataOutputStream(connection.getSocket().getOutputStream());
			
			
			System.out.println("Data Connection has been established.");
			
		}catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("Error creating Data Connection.");
		}
		
	}
	
	public void DataTranspSend()
	{
		
	}
	
	public void DataTranspRecv()
	{
		
	}
	
	public void CtrlTranspSend()
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
	
	public void TranspCloseCntrl()
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
}
