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
	<h1 class="center"> Task 1 - Catering </h1>
	<table class="table" border="1" width="400">
		<?php	
			//gets min and max values and stores them in variables
			$min=$_REQUEST['min'];
			$max=$_REQUEST['max'];
			//checks whether min&max is a valid positive number and if min is smaller than max, if not error message is printed
			if(is_numeric($min) && is_numeric($max) && $min >= 0 && $max >= 5 && $min<$max){
				//creates columns headed by c1 to c5, with the first cell empty
				echo "<tr><td></td>";
				for($i=1; $i<=5; $i++){
					echo "<th>".$_REQUEST['c'.$i]."</th>";
				}
				echo "</tr>";
				//create rows headed by min to max, incremented by 5
				for($row=$min; $row <= $max; $row=$row+5){
					echo "<tr>";
					echo "<th>".$row."</th>";
					//loops through rows and columns, to create a table of catering costs by multiplying rows with columns
					for($col=1; $col<=5; $col++){
						echo "<td>".$row * $_REQUEST['c'.$col]."</td>";
					}
					echo "</tr>";
				}	
			}
			else{
				echo "Error. You did not enter a valid integer value";
			}
				
		?>
	
	</table>
</body>
</html>