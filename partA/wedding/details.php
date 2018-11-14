<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<style type="text/css">
		body {
			font-family: "Apple Chancery", Times, serif;
			background-color: #D6D6D6;
			color: #06F
		}
		table {
			margin-left:auto;
			margin-right:auto;
		}
		td {
			color: #06F; 
		}
		.center{
			text-align: center;
		}
		
	</style>
</head>
<body>
	<h1 class="center"> Task 2 - Details </h1>
	<table class="table" border="1" >
		<?php	
			require_once('MDB2.php');

			include "wedding-mysql-connect.php"; //to provide $username,$password 
			//defines host and database name 
			$host='localhost';
			$dbName='coa123wdb';
			
			// create connection to the server 
			$dsn = "mysql://$username:$password@$host/$dbName"; 
			$db =& MDB2::connect($dsn); 
			
			if(PEAR::isError($db)){ 
				die($db->getMessage());
			}
			
			$db->setFetchMode(MDB2_FETCHMODE_ASSOC);
			
			$table_venue= "venue";
			$venueId=$_REQUEST['venueId']; //gets the value of Venue Id
			//checks whether a valid Venue Id is entered, if not, error message is returned
			if(is_numeric($venueId) && $venueId<=10 && $venueId>=1){
				//sql to search for details from the database of the selected venue, results saved in $res
				$sql= "SELECT * FROM $table_venue WHERE `venue_id`=$venueId";
				$res =& $db->query($sql);
				
				if(PEAR::isError($res)){
					die($res->getMessage());
				}
				echo "<p class='center'> Here are the details of Venue $venueId: </p>";
				//creates a row for Venue Id
				echo "<tr><th> Venue Id </th>";
				echo "<td> $venueId </td></tr>";
					
				//displays details of the selected venue in seperate rows
				while ($row = $res->fetchRow()) {
					echo "<tr><th> Name </th>";
					echo "<td>".$row['name']."</td></tr>";
					echo "<tr><th> Capacity </th>";
					echo "<td>".$row['capacity']."</td></tr>";
					echo "<tr><th> Weekend Price </th>";
					echo "<td>".$row['weekend_price']."</td></tr>";
					echo "<tr><th> Weekday Price </th>";
					echo "<td>".$row['weekday_price']."</td></tr>";					
					echo "<tr><th> Licensed </th>";
					echo "<td>".$row['licensed']."</td></tr>";
				}
			}
			else{
				echo "Error. Venue not found";
			}
		?>
	
	</table>
</body>
</html>