/**
 * 
 */

/**
 * @author Lanette Braxton
 *
 */
public enum ProcessSourceEnum 
{
	SERVER_PROCESS("ServerProcess"), CLIENT_PROCESS("ClientProcess");
	
	private String processType;
	
	private ProcessSourceEnum(String type)
	{
		processType = type;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
}
