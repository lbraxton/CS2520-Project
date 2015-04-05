import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Lanette
 *
 */
public class Command implements Serializable

{
	private CommandEnum command;
	private ArrayList<File> files;
	
	Command()
	{
		files = new ArrayList<File>();
	}

	public CommandEnum getCommand() {
		return command;
	}

	public void setCommand(CommandEnum command) {
		this.command = command;
	}

	public ArrayList<File> getFiles() {
		return files;
	}
	
		
}
