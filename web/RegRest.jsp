<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Restaurant</title>
</head>
<body>
	<form action="ActionController" method="post">
		<input type="submit" name="action" value="Back to Main Menu" style="font-family: 'Comfortaa'">
	</form>
	<div align="center">
		<BR><BR><h1 style="font-family: 'Comfortaa'">Register Restaurant</h1><BR><BR>
	</div>
	
	<form action="ActionController" method="post" style="font-family: 'Comfortaa'">
		<span style="margin-left:35em">Name </span> <span style="margin-left:7.5em"><input type="text" name="name" style="font-family: 'Comfortaa'" autocomplete="on"></span> <BR><BR>
		<span style="margin-left:35em">Location </span><BR>
		<span style="margin-left:36em">Street </span> <span style="margin-left:6.4em"><input type="text" name="street" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:36em">City </span> <span style="margin-left:7.4em"><input type="text" name="city" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Telephone Number </span> <span style="margin-left:0.7em"><input type="text" name="telpnum" style="font-family: 'Comfortaa'"></span><BR><BR>
		<span style="margin-left:35em">Detail </span> <span style="margin-left:7.6em"><input type="text" name="detail" style="font-family: 'Comfortaa'"></span><BR><BR>
		
		<div align="center">
			<caption>Restaurant Menu</caption>
			<span style="margin-left:35em">
			<INPUT type="button" value="Add Food" onclick="addRow('dataTable')" />
			<INPUT type="button" value="Delete Food" onclick="deleteRow('dataTable')" />
			</span>
			<br><br>
			<TABLE id="dataTable" border="1" cellpadding="5">
				<tr>
					<th>Choose to Delete</th>
	                <th>Name</th>
	                <th>Price</th>
	                <th>Quantity</th>
	                <th>Detail</th>
	            </tr>
				
				<TR>
					<TD><INPUT type="checkbox" name="chk"/></TD>
					<TD><input type="text" name="foodname" style="font-family: 'Comfortaa'"></TD>
					<TD><input type="text" name="foodprice" style="font-family: 'Comfortaa'"></TD>
					<TD><input type="text" name="foodquant" style="font-family: 'Comfortaa'"></TD>
					<TD><input type="text" name="fooddetail" style="font-family: 'Comfortaa'"></TD>
				</TR>
			</TABLE>
		</div>
		
		<SCRIPT>
			function addRow(tableID) {
	
				var table = document.getElementById(tableID);
	
				var rowCount = table.rows.length;
				var row = table.insertRow(rowCount);
	
				var colCount = table.rows[0].cells.length;
	
				for(var i=0; i<colCount; i++) {
	
					var newcell	= row.insertCell(i);
	
					newcell.innerHTML = table.rows[1].cells[i].innerHTML;
					switch(i){
						case 1:{
							newcell.name = "foodname";
							break;
						}
						case 2:{
							newcell.name = "foodprice";
							break;
						}
						case 3:{
							newcell.name = "foodquant";
							break;
						}
						case 4:{
							newcell.name = "fooddetail";
							break;
						}
					}
					//alert(newcell.childNodes);
					switch(newcell.childNodes[0].type) {
						case "text":
								newcell.childNodes[0].value = "";
								break;
						case "checkbox":
								newcell.childNodes[0].checked = false;
								break;
					}
				}
			}
	
			function deleteRow(tableID) {
				try {
				var table = document.getElementById(tableID);
				var rowCount = table.rows.length;
				if(rowCount > 2){
					for(var i=0; i<rowCount; i++) {
						var row = table.rows[i];
						var chkbox = row.cells[0].childNodes[0];
						if(null != chkbox && true == chkbox.checked) {
							if(rowCount <= 2) {
								alert("Cannot delete all the rows.");
								break;
							}
							table.deleteRow(i);
							rowCount--;
							i--;
						}
		
		
					}
				}
				}catch(e) {
					alert(e);
				}
			}
	
		</SCRIPT>
		
		<br>
		<span style="margin-left:60em">
			<input type="hidden" name="action" value="RegRest">
			<input type="submit" value="Register" style="font-family: 'Comfortaa'"/>
		</span>
	</form>
</body>
</html>