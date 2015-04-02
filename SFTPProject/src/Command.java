import java.io.File;
import java.io.Serializable;

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
	private File sourceFile;
	private File destinationFile;
	
	Command()
	{
		
	}
	
	Command(CommandEnum userCommand, File source)
	{
		command = userCommand;
		sourceFile = source;
		destinationFile = null;
		
	}
	
	Command(CommandEnum userCommand, File source, File destination)
	{
		command = userCommand;
		sourceFile = source;
		destinationFile = destination;
		
	}

	public CommandEnum getCommand() {
		return command;
	}

	public void setCommand(CommandEnum command) {
		this.command = command;
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public File getDestinationFile() {
		return destinationFile;
	}

	public void setDestinationFile(File destinationFile) {
		this.destinationFile = destinationFile;
	}
	
	
}
