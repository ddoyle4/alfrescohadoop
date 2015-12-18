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
public class HadoopUpload extends DeclarativeWebScript {
	
	String basePath = "/home/david/ownCloud/linux_eclipse/my-alfresco-amp/src/main/java/dk/ohej/my_alfresco_amp/hadoop/";
	String coreSite = basePath + "core-site.xml";
	String hdfsSite = basePath + "hdfs-site.xml";
	String maprSite = basePath + "mapred-site.xml";
	//Hadoop HD;
	String CURRENT_PATH;
	
	protected Map<String, Object> executeImpl(WebScriptRequest req,Status status) {
		//HD = new Hadoop(coreSite, hdfsSite, maprSite);
		Map<String, String> templateArgs = req.getServiceMatch().getTemplateVars();
		
		CURRENT_PATH = templateArgs.get("currentPath");
		String filepath = templateArgs.get("filePath");

		Map<String, Object> output = new HashMap<String, Object>();

		String uploadStatus = uploadFile(filepath);
		System.out.println("hererererer");
		output.put("uploadStatus", uploadStatus);
		
		return output;
	}
	
	
	
	private String uploadFile(String localPath){
		String newPath = CURRENT_PATH + localPath;
		return "<div class=\"success\">File upload succeeded</div>";
		//TODO: fix build path to include jars and uncomment below
	/*	if(HD.copyFromLocal(localPath, newPath))
			return "<div class="success">File upload succeeded</div>";
		else 
			return "<div class="fail">ERROR - file upload failed</div>";
	*/
	}
		
	
}