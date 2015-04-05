import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.List;

import javax.swing.JFileChooser;

/**
 * 
 */

/**
 * @author Lanette Braxton
 *
 */
public class FileManager
{
	
	private File sourceFile;
	private File destinationFile;
	private static final int BUFFERSIZE = 1024;
	private String fileName;
	private String inputCommand;
	
	
	public FileManager()
	{
		
	}
	
	
	public void deleteFileOrDirectory(File directory)
	{
		if(!directory.exists())
		{ 
			System.out.println("Directory does not exist.");
			System.exit(0);
		}else
		{
			deleteFileAndOrDirectory(directory);
		}
			
	}
	
	private void deleteFileAndOrDirectory(File fileOrDirectory)
	{
		if(fileOrDirectory.isDirectory())
		{
			//Delete empty directory
			if(fileOrDirectory.list().length == 0)
			{
				fileOrDirectory.delete();
				System.out.println("Directory deleted.");
				
			}else
			{
				//Retrieve contents of directory
				String files[] = fileOrDirectory.list();
				
				for(String fileOrDir: files)
				{
					/*Creates a new File instance where the file path 
					of child fileOrDir is resolved against the path
					name of parent fileOrDirectory */
					File fileOrDirToDelete = new File(fileOrDirectory,fileOrDir);
					
					deleteFileAndOrDirectory(fileOrDirToDelete);
				}
				
				//Check again to see if directory was deleted
				if(fileOrDirectory.list().length == 0)
				{
					fileOrDirectory.delete();
					System.out.println("Directory was deleted");
				}
					
			}
		}else
		{
			//Delete file
			fileOrDirectory.delete();
			System.out.println("File deleted");
		}
		
	}
	
	public void copy(File source, File destination)
	{
		if(!source.exists())
		{
			System.out.println("File or Directory does not exist");
			System.exit(0);
		}else
		{
			copyFileOrDirectory(source, destination);
				
		}
	}
	 
	private void copyFileOrDirectory(File source, File destination)
	{
		if(source.isDirectory())
		{
			//If directory does not exist, create it
			if(!destination.exists())
			{
				destination.mkdirs();
				
			}
			
			//Store directory contents
			String directoryFiles[] = source.list();
			
			
			for(String file: directoryFiles)
			{
				//Recursive copy of the source and destination folders contents
				File sourceFile = new File(source,file);
				File destinationFile = new File(destination,file);
				
				copyFileOrDirectory(sourceFile,destinationFile);
			}
		}else
		{
			InputStream readSourceData;
			OutputStream writeDestinationData;
			
			try 
			{
				int lineLength = 0;
				byte[] buffer = new byte[BUFFERSIZE];
				readSourceData = new FileInputStream(source);
				writeDestinationData = new FileOutputStream(destination);
				
				while((lineLength = readSourceData.read(buffer)) > 0)
				{
					writeDestinationData.write(buffer,0,lineLength);
				}
				
				readSourceData.close();
				writeDestinationData.close();
				
				System.out.println("Data has been copied from the source to the destination file");
				
			} catch (FileNotFoundException e) 
			{
				e.printStackTrace();
				System.out.println("Error source or destination file not found");
				System.exit(0);
			} catch (IOException e) 
			{				
				e.printStackTrace();
				System.out.println("Error reading source or destination file");
				System.exit(0);
			}
			
		}
		
	}
	
	public void moveFileOrDirectory(File source, File destination)
	{
		if(!destination.isDirectory())
		{
			System.out.println("Please enter a directory as the destination.");
			System.exit(0);
		}
			try
			{
				if(source.renameTo(new File(destination, source.getName())))
				{
					System.out.println("File or Directory has been moved");
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Error moving file");
			}
			
	}	
	
	public void listDirectorycontents(File file)
	{
		File[] contents = file.listFiles();
		
		for(File fileOrDir : contents)
		{
			try
			{
				System.out.println(fileOrDir.getCanonicalPath());
				
			} catch (IOException e) 
			{
				e.printStackTrace();
				System.out.println("Error retrieving the path for " + fileOrDir);
			}
		}
	}
	
	public void changeDirectories(File file)
	{
		String oldDirectory = System.setProperty("user.dir", file.getAbsolutePath());
		this.listDirectorycontents(file);
	}
	
	public void listPresentWorkingDirectory()
	{
		System.out.println(System.getProperty("user.dir"));
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = this.getFileName();
	}	
	
	public String getInputCommand() {
		return inputCommand;
	}

	public void setInputCommand(String inputCommand) {
		this.inputCommand = inputCommand;
	}

	public File getDestinationFile() {
		return destinationFile;
	}

	public void setDestinationFile(File destinationFile) {
		this.destinationFile = destinationFile;
	}	

	public File getSourceFile() {
		return sourceFile;
	}

	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		FileManager fileManager = new FileManager();
		
		
		
		System.out.println("File name is " + fileManager.getSourceFile().getName());
		
	}
	
	
}
