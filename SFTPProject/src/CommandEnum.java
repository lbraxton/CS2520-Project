/**
 * 
 */

/**
 * @author Lanette Braxton
 *
 */
public enum CommandEnum {
	RCD("RCD"), LCD("LCD"), PWD("PWD"), RLS("RLS"), LLS("LLS"), GET("GET"), MGET("MGET"), PUT("PUT"), MPUT("MPUT");
	
	private String commandType;
	
	CommandEnum(String command)
	{
		commandType = command;
	}

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}
	
	
}