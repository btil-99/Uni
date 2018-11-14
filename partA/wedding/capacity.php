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
	<h1 class="center"> Task 3 - Capacity </h1>
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
			$minCapacity=$_REQUEST['minCapacity']; //gets value of minCapaciy entered
			$maxCapacity=$_REQUEST['maxCapacity']; //gets value of maxCapacity entered
			//checks whether valid min and max capacities are entered, and that max capacity is greater than min capacity, if not, error message is returned
			if(is_numeric($minCapacity) && is_numeric($maxCapacity) && $minCapacity<$maxCapacity && $minCapacity>=0){
				//sql to search for name and prices of licensed venues within the min and max capacity
				$sql= "SELECT `name`, `weekend_price`, `weekday_price` FROM $table_venue WHERE `capacity` >= $minCapacity AND `capacity` <= $maxCapacity AND `licensed` = 1" ;
				$res =& $db->query($sql);
		
				if(PEAR::isError($res)){
					die($res->getMessage());
				}
				//displays table headers for name, weekend and weekday price 
				echo "<tr><th> Venue name </th>";
				echo "<th> Weekend price </th>";
				echo "<th> Weekday price </th></tr>";
				
				//displays details in seperate rows of all licensed venues within min and max capacity 
				while ($row = $res->fetchRow()) {
					echo "<tr><td>".$row['name']."</td>";
					echo "<td>".$row['weekend_price']."</td>";
					echo "<td>".$row['weekday_price']."</td></tr>";
				}
			}
			else{
				echo "Error, invalid search";
			}
		?>
	
	</table>
</body>
</html>