package alfreso.hadoop.store.hadoop_store.store;

public class Folder {
	String OPEN = 	"<figure style=\"float:left;\">"
		 			+ "<img src=\"/alfresco/assets/folder.png\" />"
		 			+ "<figcaption>";
	
	String CLOSE = "</figcaption></figure>";
	
	String NAME;
	public Folder(String name)
	{
		NAME = name;
	}
	
	public String getHTML()
	{
		String folderHTML = OPEN;
		folderHTML += NAME;
		return folderHTML + CLOSE;
	}
}
