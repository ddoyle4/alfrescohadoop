package alfreso.hadoop.store.hadoop_store.store;
/**
 * @author david
 * This file is intialliy a rough copy of the Folder class. Although this code duplication now,
 * new code will be written that differs from the Folder class.
 */
public class File {
	String OPEN = 	"<figure style=\"float:left;\">"
		 			+ "<img src=\"/alfresco/assets/file.png\" />"
		 			+ "<figcaption><a href=\"/alfresco/service/hadoop/store/download?path=\"";
	
	String MIDDLE = "\">";
	
	String CLOSE = "</a></figcaption></figure>";
	
	String NAME;
	public File(String name)
	{
		NAME = name;
	}
	
	public String getHTML()
	{
		return OPEN + NAME + MIDDLE + NAME + CLOSE;
	}
}
