public class Client {

	final static String HADOOP_ROOT_PATH = "/usr/local/hadoop/";
	final static String HADOOP_CORE_SITE_PATH = HADOOP_ROOT_PATH + "etc/hadoop/core-site.xml";
	final static String HADOOP_HDFS_SITE_PATH = HADOOP_ROOT_PATH + "etc/hadoop/hdfs-site.xml";
	final static String HADOOP_MAPRD_SITE_PATH = HADOOP_ROOT_PATH + "etc/hadoop/maprd-site.xml";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Hadoop client = new Hadoop(HADOOP_CORE_SITE_PATH, 
				HADOOP_HDFS_SITE_PATH, 
				HADOOP_MAPRD_SITE_PATH);
		
		//client.copyFromLocal("/home/hadoop/test.txt", "/");
		//client.copyToLocal("/test.txt", "/home/hadoop/");
		//client.move("/test.txt", "/test_other.txt");
		
		client.stopClient();
	}

}
