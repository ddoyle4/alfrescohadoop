/**
 * Main Hadoop Wrapper
 * Contains methods to interact with the Hadoop DFS
 */

package alfreso.hadoop.store.hadoop_store.store;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

public class Hadoop {

	private Configuration config;
	private FileSystem fileSystem;
	
	/**
	 * Constructor requires a string representing the path to the core-site.xml,
	 * hdfs-site.xml, and maprd-site.xml in the hadoop/etc directory
	 * @param coreSite
	 * @param hdfsSite
	 * @param maprdSite
	 */
	public Hadoop(String coreSite, String hdfsSite, String maprdSite)
	{
		config = new Configuration();
		config.addResource(new Path(coreSite));
		config.addResource(new Path(hdfsSite));
		config.addResource(new Path(maprdSite));
		System.out.println("SAFGSFGAS");
		try {
			fileSystem = FileSystem.get(config);
		} catch (IOException e) { e.printStackTrace();}
	}
	
	public String test()
	{
		return "hadadopopopopo";
	}
	
	/**
	 * Copies a file from the local file system to the hdfs.
	 * Directory must already exist.
	 * @param src path to file stored on local file system
	 * @param dst path to directory on hdfs to store copy
	 * @return boolean success/failure of copy
	 * @throws IOException
	 */
	public boolean copyFromLocal(String src, String dst)
	{
		Path srcPath = new Path(src);
		Path dstPath = new Path(dst);
		
		/*
		 * The destination directory must exists
		 * TODO: Add option to this method to create directory if required & requested 
		 */
		try {
			if(!(fileSystem.exists(dstPath)))
			{
				System.out.println("No such destination " + dstPath);
				return false;
			}
		} catch (IOException e1) { e1.printStackTrace(); }
		
		String filename = src.substring(src.lastIndexOf('/') + 1, src.length());
		
		try{
			fileSystem.copyFromLocalFile(srcPath, dstPath);
			System.out.println("File " + filename + " copied to " + dst);
		}catch(Exception e){
			System.err.println("Exception:" + e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Copies file from the hdfs to the local file system.
	 * @param src path to file stored on the hdfs
	 * @param dst path to directory to store copy
	 * @return boolean success/failure
	 * @throws IOException 
	 */
	public boolean copyToLocal(String src, String dst)
	{
		Path srcPath = new Path(src);
		Path dstPath = new Path(dst);
		
		//check that the file exists
		try {
			if(!(fileSystem.exists(srcPath)))
			{
				System.out.println("No such file " + srcPath);
				return false;
			}
		} catch (IOException e1) { e1.printStackTrace(); }
		
		String filename = src.substring(src.lastIndexOf('/') + 1, src.length());
		
		try{
			fileSystem.copyToLocalFile(srcPath, dstPath);
			System.out.println("File " + filename + " copied to " + dst);
		}catch(Exception e){
			System.err.println("Exception:" + e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Moves file from src to dst on the hdfs
	 * @param src path to file on hdfs
	 * @param dst path to new desired location for file on hdfs
	 * @return
	 */
	public boolean move(String src, String dst)
	{
		Path srcPath = new Path(src);
		Path dstPath = new Path(dst);
		
		try {
			if(!(fileSystem.exists(srcPath)))
			{
				System.out.println("No such file in HDFS" + srcPath);
				return false;
			}
			
			if((fileSystem.exists(dstPath)))
			{
				System.out.println("File already exists in HDFS" + srcPath);
				return false;
			}
			
		} catch (IOException e1) { e1.printStackTrace(); }
		
		try{
			if(fileSystem.rename(srcPath, dstPath))
					System.out.println("Renamed " + srcPath + " to " + dstPath);
			
		} catch(Exception e){
			System.out.println("Exception: " + e);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Delete a file
	 * @param path path to a file
	 * @return
	 */
	public boolean deleteFile(String path)
	{
		return delete(path, false);
	}
	
	/**
	 * Delete a directory
	 * @param path path to a directory
	 * @return
	 */
	public boolean deleteDirectory(String path)
	{
		return delete(path, true);
	}
	
	/**
	 * Deletes a file or folder. If deleting directory, must set recursive to true
	 * @param path to file/directory 
	 * @param recursive if deleting directory - set to true
	 * @return
	 */
	private boolean delete(String path, boolean recursive)
	{
		Path filePath = new Path(path);
		
		try {
			if(!fileSystem.exists(filePath))
			{
				System.out.println("File " + filePath + "doesn't exist");
				return false;
			}
			
			fileSystem.delete(filePath, recursive);
			System.out.println("deleted " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Creates directory
	 * @param dir path to directory to create
	 * @return
	 */
	public boolean makeDirectory(String dir)
	{
		Path dirPath = new Path(dir);
		
		try {
			if(fileSystem.exists(dirPath))
			{
				System.out.println(dirPath + " already exists");
				return false;
			}
			
			fileSystem.mkdirs(dirPath);
		} catch (IOException e) { e.printStackTrace(); }
		
		return true;
	}
	
	/**
	 * Kills FileSystem
	 * @return
	 */
	public boolean stopClient()
	{
		try {
			fileSystem.close();
		} catch (IOException e) {
			System.out.println("ERROR: Unable to close file system");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
