/*
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package alfreso.hadoop.store.hadoop_store.store;

import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import alfreso.hadoop.store.hadoop_store.store.File;
import alfreso.hadoop.store.hadoop_store.store.Folder;
import alfreso.hadoop.store.hadoop_store.store.Hadoop;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */	
public class HadoopStore extends DeclarativeWebScript {
	
	String basePath = "/home/david/ownCloud/linux_eclipse/my-alfresco-amp/src/main/java/dk/ohej/my_alfresco_amp/hadoop/";
	String coreSite = basePath + "core-site.xml";
	String hdfsSite = basePath + "hdfs-site.xml";
	String maprSite = basePath + "mapred-site.xml";
	//Hadoop HD;
	
	protected Map<String, Object> executeImpl(WebScriptRequest req,Status status) {
		//HD = new Hadoop(coreSite, hdfsSite, maprSite);
		
		Map<String, String> templateArgs = req.getServiceMatch().getTemplateVars();
		Map<String, Object> output = new HashMap<String, Object>();

		String folderPath = templateArgs.get("param");
		String htmlOut = getDirectoryContents(folderPath);
		output.put("currentDirectory", "/");
		output.put("fromStore", htmlOut);
		
		return output;
	}
	
	
	
	private String getDirectoryContents(String path){
		
		//TODO: call hadoop
		String example = "";
		example += new Folder("Sample Docs").getHTML();
		example += new Folder("Other Docs").getHTML();
		example += new File("Big Data File").getHTML();
		
		return example;
	}
		
	
}