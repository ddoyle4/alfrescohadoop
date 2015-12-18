<html>
	<head>
		<link rel="stylesheet" href="${url.context}/css/hadoop.css" TYPE="text/css">
	</head>
	
	<body>
	<h1>Hadop Content Store</h1>
	<p>Upload file to current HDFS directory...</p>
	<form action="/alfresco/service/hadoop/store/upload" method="get">
		<input type="hidden" required name="curdir" value="${currentDirectory}">
		<input type="file" required name="file" accept="*">
		<input type="submit" value="Upload">
	</form> 
	<!-- the current directory will be printed out using the string 'fromStore' returned from the HadoopStore -->
	${fromStore}

	</body>
	
</html>