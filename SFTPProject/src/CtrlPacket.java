import java.io.Serializable;
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
public class CtrlPacket implements Serializable
{
	private Map<String, List<Byte>> header;
	private Command payload;
	private static final int MAX_PAYLOAD_SIZE = 1024;
	private long whenSent;
	private long whenRecieved;
	private boolean failed;
	
	public CtrlPacket()
	{
		header = new HashMap<String, List<Byte>>();
		header.put("Source Port", new ArrayList<Byte>());
		header.put("Destination Port", new ArrayList<Byte>());
		header.put("Sequence Number", new ArrayList<Byte>());
		header.put("Acknowledgement Number", new ArrayList<Byte>());
		header.put("Data Offset", new ArrayList<Byte>());
		header.put("Reserved", new ArrayList<Byte>());
		header.put("Flags", new ArrayList<Byte>(9));
		header.put("Window Size", new ArrayList<Byte>());
		header.put("Checksum", new ArrayList<Byte>());
		header.put("Urgent Pointer", new ArrayList<Byte>());
		header.put("Options", new ArrayList<Byte>());
		header.put("Padding", new ArrayList<Byte>());
		payload = new Command();
	}

	public long getWhenSent() {
		return whenSent;
	}

	public void setWhenSent(long whenSent) {
		this.whenSent = whenSent;
	}

	public long getWhenRecieved() {
		return whenRecieved;
	}

	public void setWhenRecieved(long whenRecieved) {
		this.whenRecieved = whenRecieved;
	}

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	public Map<String, List<Byte>> getHeader() {
		return header;
	}

	public Command getPayload() {
		return payload;
	}

	public void setPayload(Command payload) {
		this.payload = payload;
	}
	
	
}
