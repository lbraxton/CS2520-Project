import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.List;
import java.util.Scanner;

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
	private PrintStream outputData;
	private ByteArrayOutputStream baoStream;
	private ByteArrayInputStream baiStream;
	private ObjectOutputStream objoStream;
	private ObjectInputStream objiStream;
	//private Connection serverCntrlConnection; 
	//private Connection clientCntrlConnection;
	private Connection connection;
	private DDLayer ddLayer;
	private DatagramPacket sendPacket;
	private DatagramPacket receivePacket;
	private CtrlPacket ctrlPacket;
	private SFTPServer sftpServer;
	private ProcessSourceEnum processSource;
	private InetAddress inetAddress;
	private int port;
	
	public CFMLayer(ProcessSourceEnum processSrcEnum)
	{
		processSource = processSrcEnum;
		
		//Remove this code once the DNS code is implemented		
		if(ProcessSourceEnum.SERVER_PROCESS.equals(processSrcEnum))
		{
			connection = new Connection(5432);
		}else
		{
			connection = new Connection();
		}
		
		inetAddress = connection.getSocket().getInetAddress();
		port =  connection.getSocket().getLocalPort();
		
	}
	
	public void TranspInitCntrl()
	{
			//TODO write code to in Client to get the IP and port number of the DNS server to 
			//make a connection to receive the server IP and Port number
		/*if(null == serverCntrlConnection || null == serverCntrlConnection.getSocket())
		{
			sftpServer = new SFTPServer();
			serverCntrlConnection = new Connection(sftpServer.getPort());
		}
				
		if(null == clientCntrlConnection || null == clientCntrlConnection.getSocket())
		{
			clientCntrlConnection = new Connection();
		}*/
		
	}
	
	public void CtrlTranspSend(List<String> commandLine, InetAddress hostAddress, int serverPort)
	{
		
		baoStream = new ByteArrayOutputStream();
		ctrlPacket = new CtrlPacket();
		ctrlPacket.setPayload(commandLine);
		try 
		{
			objoStream = new ObjectOutputStream(baoStream);
			objoStream.writeObject(ctrlPacket);
			byte[] outPacket = baoStream.toByteArray();
			sendPacket = new DatagramPacket(outPacket,outPacket.length,hostAddress,serverPort);
			
			connection.getSocket().send(sendPacket);
			
			System.out.println("Packet sent successfully.");
			
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error creating output steam.");
		}
		
		
	}
	
	public void CtrlTranspRecv()
	{
		byte[] inPacket = new byte[1024];
		receivePacket = new DatagramPacket(inPacket, inPacket.length);
		
		try 
		{
			connection.getSocket().receive(receivePacket);
			byte[] receivedData = receivePacket.getData();
			baiStream = new ByteArrayInputStream(receivedData);
			objiStream = new ObjectInputStream(baiStream);
			try 
			{
				ctrlPacket = (CtrlPacket) objiStream.readObject();
				System.out.println("CtrlPacket object received " + ctrlPacket );
				
			} catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				System.out.println("Error reading packet.");
				System.exit(0);
			} 
			
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Error receiving packet.");
			System.exit(0);
		}
	}
	
	public void TranspInitData()
	{
		if(sftpServer.getPort() == 0)
		{
			sftpServer = new SFTPServer();
		}
		ddLayer = new DDLayer(sftpServer.getPort());
		
	}
	
	public void DataTranspSend()
	{
		
	}
	
	public void DataTranspRecv()
	{
		
	}
	
	public void execute()
	{
		List<String> commandLine = this.getCtrlPacket().getPayload();
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
	
	
	public void TranspCloseCntrl()
	{
		this.connection.getSocket().close();
	}

	public CtrlPacket getCtrlPacket() {
		return ctrlPacket;
	}

	public void setCtrlPacket(CtrlPacket ctrlPacket) {
		this.ctrlPacket = ctrlPacket;
	}

	public InetAddress getInetAddress() {
		return inetAddress;
	}

	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
			
}
