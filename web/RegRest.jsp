<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Restaurant</title>
</head>
<body>
	<div align="center">
		<BR><BR><h1 style="font-family: 'Comfortaa'">Register Restaurant</h1><BR><BR>
	</div>
	
	<form action="ActionController" method="post" style="font-family: 'Comfortaa'">
		<span style="margin-left:35em">Name </span> <span style="margin-left:5.3em"><input type="text" name="name" style="font-family: 'Comfortaa'" autocomplete="on"></span> <BR><BR>
		<span style="margin-left:35em">Location </span><BR>
		<span style="margin-left:36em">Street </span> <span style="margin-left:6.2em"><input type="text" name="street" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:36em">City </span> <span style="margin-left:7.3em"><input type="text" name="city" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Telephone Number </span> <span style="margin-left:0.7em"><input type="text" name="telpnum" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Detail </span> <span style="margin-left:7.6em"><input type="text" name="detail" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Restaurant Picture </span><BR>
		<span style="margin-left:36em">Select image to upload</span> <br>
		<span style="margin-left:36em">
			<img id="showRestImg" height=auto width=auto/><br>
			<span style="margin-left:36em"><input type="file" name="restimg" id="restimg" accept="image/*" onchange="loadFile(event)"></span>
			<script>
			  var loadFile = function(event) {
			    var output = document.getElementById('showRestImg');
			    output.src = URL.createObjectURL(event.target.files[0]);
			    output.onload = function() {
			      URL.revokeObjectURL(output.src) // free memory
			    }
			  };
			</script>
		</span><BR><BR>
		
		<span style="margin-left:35em">Food </span><BR>
		<span style="margin-left:36em">Merk </span> <span style="margin-left:6.8em"><input type="text" name="merk" style="font-family: 'Comfortaa'"></span><BR><BR>
		
		<span style="margin-left:60em">
			<input type="hidden" name="action" value="RegDriver">
			<input type="submit" style="font-family: 'Comfortaa'"/>
		</span>
	</form>
</body>
</html>