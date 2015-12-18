<html>
	<head>
		<link rel="stylesheet" href="${url.context}/css/hadoop.css" TYPE="text/css">
	</head>
	
	<body>
	<b>Hadop Content Store</b>
	<p>Upload file to current HDFS directory...</p>
	<form action="hadoop" method="get">
		<input type="">
		<input type="file" required name="file" accept="*">
		<input type="submit" value="Upload">
	</form> 
	<!-- the current directory will be printed out using the string 'fromStore' returned from the HadoopStore -->
	${fromStore}

	</body>
	
</html>