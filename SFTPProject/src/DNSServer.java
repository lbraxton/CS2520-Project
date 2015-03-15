import java.io.*;
import java.net.*;
import java.util.HashMap;

/**
 * @author Lanette Braxton
 *
 */
public class DNSServer
{
	
	private SFTPClient client;
	private SFTPServer server;
	private HashMap serverMap;
	private File serverNameFile;
	private String url;
	private String fileLocation;
	

	
	public String getUrl() {
		return url;
	}	

	public HashMap getServerMap() {
		return serverMap;
	}
	

	public String getFileLocation() {
		return fileLocation;
	}


	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
