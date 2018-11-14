<?php	
require_once('MDB2.php');

include "wedding-mysql-connect.php"; 

$host='localhost';
$dbName='coa123wdb';
			
$dsn = "mysql://$username:$password@$host/$dbName"; 
$db =& MDB2::connect($dsn); 
			
if(PEAR::isError($db)){ 
	die($db->getMessage());
}
			
$db->setFetchMode(MDB2_FETCHMODE_ASSOC);

//function to check whether a date is a weekend
function isWeekend($date){
	$day=date('l', strtotime($date));
	if(date($day == 'Saturday' || $day == 'Sunday')){
		return true;
	}
	else{
		return false;
	}
}

//store user inputs in separate variables			
$start_date = date('Y-m-d', strtotime(str_replace('/','-', $_GET['start_date'])));
$end_date = date('Y-m-d', strtotime(str_replace('/','-', $_GET['end_date'])));
$partySize=$_GET['partySize'];
$cateringGrade = $_GET['cateringGrade'];

/*$sql= "SELECT *, '$start_date' AS `date_available` FROM (`catering` INNER JOIN `venue` ON catering.`venue_id`=venue.`venue_id`) 
INNER JOIN `venue_booking` ON catering.`venue_id` = venue_booking.`venue_id` 
WHERE venue.`capacity` >= $partySize AND catering.`grade`= $cateringGrade
AND NOT venue.`venue_id` IN (SELECT `venue_id`FROM venue_booking WHERE `date_booked`= '$start_date') 
GROUP BY venue.`venue_id`";*/

$sql = "SELECT *, '$start_date' AS date_available FROM `venue` INNER JOIN `catering` ON venue.`venue_id`=catering.`venue_id` WHERE venue.`capacity` >= $partySize 
AND catering.`grade` = $cateringGrade AND NOT venue.venue_id IN(SELECT `venue_id`FROM venue_booking WHERE `date_booked`= '$start_date')";

$res =& $db->query($sql);
		
if(PEAR::isError($res)){ 
	die($res->getMessage());
}

	$return_array=array();

	while ($row = $res->fetchRow()) {
	$array['venue_id']=$row['venue_id'];
	$array['date_available']=$row['date_available'];
	$array['name']=$row['name'];
	if(isWeekend($start_date)){
		$array['Price']=$row['weekend_price'];
	}
	else{
		$array['Price']=$row['weekday_price'];
	}
	$array['cost']=$row['cost'];
	$array['capacity']=$row['capacity'];
	$array['licensed']=$row['licensed'];
	
	array_push($return_array, $array);
}

$value = json_encode($return_array);
echo $value;


?>