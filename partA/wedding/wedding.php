<html> 
<head> 
<title>Wedding Venues</title> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src = "http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script type="text/javascript">

$(document).ready(function(){
	//hides end date input when document is ready
	$("#end_date").hide()
	$("#label").hide()
	//if range date check box is ticked, end date input shows
	$("#rangeDates").click(function(){
		$("#end_date").toggle()
		$("#label").toggle()
		$("#end_date").val("");
	});
});

function doQuery(){
	//stores user inputs in separate variables
	var start_date=$("#start_date").val(); 
	var partySize=$("#partySize").val();
	var cateringGrade=$("#cateringGrade").val();
	var end_date=$("#end_date").val();
	var date1 = new Date(start_date);
	var date2 = new Date(end_date);
	
	if(start_date != '' && partySize !='' && cateringGrade !=0 && end_date==''){
		$.get("weddingquery.php",{'start_date':start_date, 'end_date':end_date, 'partySize': partySize, 'cateringGrade':cateringGrade},function(json){
			if(json && json[0]){ // checks whether there is a return value, else error message is returned
				console.log(json);	
				//stores table headers in htm variable
				var htm= "<table border='1' id='show_results'><tr><th>Date Available</th><th>Venue ID</th><th>Name</th><th>Max Capacity</th><th>Venue price</th><th>Catering cost</th><th>Licensed</th></tr>";
				//gets venue name, venue price and catering cost of all suitable venues based on user input and puts it in a table
				for(var i=0; i<json.length; i++){
					htm+="<tr><td>"+json[i].date_available+"</td>"+
					"<td>"+json[i].venue_id+"</td>"+
					"<td>"+json[i].name+"</td>"+
					"<td>"+json[i].capacity+"</td>"+
					"<td>"+json[i].Price+"</td>"+
					"<td>"+json[i].cost+"</td>"+
					"<td>"+json[i].licensed+"</td></tr>";
				}
				htm+="</table>";

			}else{
				var htm= "Venue not found";
			}
			//outputs table results
			$("#search_results").html(htm)
		},'json');
	}
	else if(start_date!='' && end_date!='' && start_date != end_date && date2>date1){
		$.get("weddingquery2.php",{'start_date':start_date, 'end_date':end_date, 'partySize': partySize, 'cateringGrade':cateringGrade},function(json){
			if(json && json[0]){ // checks whether there is a return value, else error message is returned
				console.log(json);	
				//stores table headers in htm variable
				var htm= "<table border='1' id='show_results'><tr><th>Date Available</th><th>Venue ID</th><th>Name</th><th>Max Capacity</th><th>Venue price</th><th>Catering cost</th><th>Licensed</th></tr>";
				//gets venue name, venue price and catering cost of all suitable venues based on user input and puts it in a table
				for(var i=0; i<json.length; i++){
					htm+="<tr><td>"+json[i].date_available+"</td>"+
					"<td>"+json[i].venue_id+"</td>"+
					"<td>"+json[i].name+"</td>"+
					"<td>"+json[i].capacity+"</td>"+
					"<td>"+json[i].Price+"</td>"+
					"<td>"+json[i].cost+"</td>"+
					"<td>"+json[i].licensed+"</td></tr>";
				}
				htm+="</table>";

			}else {
				var htm= "Venue not found";
			}
			//outputs table results
			$("#search_results").html(htm)
		},'json');
	}
	else if(start_date==''|| end_date==''|| cateringGrade=='' || partySize){
		alert("Please enter valid details");
	}
}
//datepicker
$(function(){
	$("#start_date").datepicker({dateFormat: 'dd/mm/yy'});
	$("#end_date").datepicker({dateFormat: 'dd/mm/yy'});
});

</script>
<style type="text/css">
body {
	font-family: "Apple Chancery", Times, serif;
	background-color: #7a003b;
	margin:30px;
}
.heading {
	text-align:center;
	color: #ffffff;
    font-style: oblique;
	font-size: 50px;
}
#show_results{
	position: absolute;
	top: 20px;
}
#left_div{
	position: fixed; 
	left: 30px; 
	width: 50%;
	background-color:#efefef;
	top: 205px;
	height: 64%;
}
#search_table{
	position: absolute;
	top: 20px;
}
#search_results{
	position: fixed; 
	right:30px; 
	width: 50%;
	background-color:#efefef;
	top: 205px;
	float:left;
	overflow-y: auto;
	height: 64%;
}
.form_text{
	font-size: 20px;
	font-weight: bold;
}
.textbox{
	height: 30px;
	font-size:15pt;
}
#search_button{
	border-radius: 12px;
	font-size: 20px;
	background-color: #555555;
	color: #ffffff;
}
#text{
	font-size: 20px;

}
</style>
</head> 
 
<body> 
<div style="background-color: #c91c6f; padding:20px;">
</div>
<div id="heading" style="background-color:#000000; padding:5px;">
	<h1 class="heading">Wedding Venue Search</h1> 
</div>
<div>
	<div id="left_div">
		<table id="search_table">
			<tr>
				<td><label class="form_text" for="start_date">Date as dd/mm/yyyy</label></br>
					<input type="checkbox" name="rangeDates" id="rangeDates"> Select date range </td>
				<td><input type="text" name="start_date" id="start_date" class= "textbox" size="10"/>
					<label class="form_text" for = "end_date" id="label">to</label>
					<input name="end_date" type="text" id="end_date" class= "textbox" size="10"/></td>
			</tr>
			<tr>
				<td><label class="form_text" for="partySize">Party size</label></td>
				<td><input name="partySize" type="number" id="partySize" class= "textbox" min="1" max="1000" /></td>
			</tr>
			<tr>
				<td><label class="form_text" for="cateringGrade"> Select catering grade (1-5) </label></td>
				<td><input type="number" name="cateringGrade" id="cateringGrade" class= "textbox" min="1" max="5"></td>
			<tr>
				<td></td>
				<td><input type="submit" name="search" id="search_button" value="Search" onClick="doQuery();"/></td>
			</tr>
		</table>
	</div>
	<div id="search_results"> <p id="text">Venue results will show up here</p>
	</div>
</div>
</body> 
</html>
