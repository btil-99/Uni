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
	<h1 class="center"> Task 4 - Costs </h1>
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
			
			//function to check whether a date is valid
			function isDate($date){
				if(empty($date) || strlen($date)!=10 || !preg_match("/^([0-3][0-9])\\/([0-1][0-9])\\/([0-9]{4})$/", $date)){
					return false;
				}
				else{
					return true;
				}
			}
			//function to check whether the date is a weekend
			function isWeekend($date){
				$day=date('l', strtotime($date));
				if(date($day == 'Saturday' || $day == 'Sunday')){
					return true;
				}
				else{
					return false;
				}
			}
			
			//checks if date enterred is valid, if not, error message is shown
			if(isDate($_REQUEST['date'])){
				//gets the value of the date enterred and converts it into sql format
				$date = date('Y-m-d', strtotime(str_replace('/','-', $_REQUEST['date'])));
				$partySize=$_REQUEST['partySize']; //gets value of party size entered
				//checks whether valid party size is entered, if not, error message is shown
				if(is_numeric($partySize) && $partySize<=1000 && $partySize>=0){
					//sql to search for venue names and prices of available venues providing for the given party size
					$sql= "SELECT `name`, `weekend_price`,`weekday_price` FROM `venue` INNER JOIN `venue_booking` ON venue.`venue_id`=venue_booking.`venue_id` WHERE venue.`capacity` >= $partySize AND NOT venue.`venue_id` IN (SELECT `venue_id`FROM venue_booking WHERE `date_booked`= '$date') GROUP BY venue.`venue_id`" ;
					$res =& $db->query($sql);
			
					if(PEAR::isError($res)){ 
						die($res->getMessage());
					}
					
					//gives table headers
					echo "<tr><th> Venue Name </th>";
					echo "<th> Price </th></tr>";
					
					//displays available venues and prices depending on whether it is a weekend or weekday
					while ($row = $res->fetchRow()) {
						echo "<tr><td>".$row['name']."</td>";
						if(isWeekend($date)){
							echo "<td>".$row['weekend_price']."</td></tr>";
						}
						else{
							echo "<td>".$row['weekday_price']."</td></tr>";
						}
						
					}
				}
				else{
					echo "Error, venue not found, you did not enter a valid party size";
				}
			}
			else{
				echo "Invalid date entered";
			}
			
		?>
	
	</table>
</body>
</html>