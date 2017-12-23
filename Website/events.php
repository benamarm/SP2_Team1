<?php

include ('sessie.php');
session_start();

if(!$_SESSION["loginemail"])
{
	header("location:index.php?notloged=You are not loged in!");
}

?>

<html lang="en">
	
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="login.php">
	<link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script 		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<head>
	<link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
	</head>
</head>

<body class="eve">
	
	<div class="ev1">
	<form class="ev">
		<img src='afbeeldingen/events-banner-v2.jpg' width="700px" height="200px">
		
		</form>
	
	</div>
	
	<table class="data-table">
		<br/>
		<thead>
			
			
			<tr>
			
				<th>Trainer</th>
				<th>Startdatum</th>
				<th>Einddatum</th>
				<th>Inschrijving</th>
		
			

			</tr>
		</thead>
		<tbody>
			
<?php

include ('connectie.php');

			$opid = $_GET['opid'];
			
			$pid = $_GET['pid'];
			
			
$sql = "SELECT event_id, opleiding_id, naam_trainer, startdatum, einddatum FROM SP2Team01.events where opleiding_id = '$opid' and event_id NOT IN(SELECT event_id from SP2Team01.vaardigheden where pers_id='$pid') and afgelast = 0 and aantal_deelnames < max_deelnames and startdatum > sysdate()";
			
			
	
$query = mysqli_query($conn, $sql);

if (!$query) 
{	die ('SQL Error: ' . mysqli_error($conn));
 
}
			
			
while ($row = mysqli_fetch_assoc($query))
{
	
echo "<tr><td>";
 
    echo $row['naam_trainer'];
	echo "</td><td>";
    echo $row['startdatum'];
    echo "</td><td>";
    echo $row['einddatum'];
	echo "</td><td>";
    echo '<a href="vaardigheid.php?idev='.$row['event_id'].'&pid='.$pid.'&opid='.$opid.'">  &nbsp; Inschrijving</a>';
	 echo "</td></tr>";
}
		if(mysqli_num_rows($query) == 0)
		
		{
			echo "<tr><td colspan = '4'>";
			echo 'Er zijn momenteel nog geen events gepland voor deze opleiding.';
			echo "</td></tr>";
		}

?>
			
</tbody>
	</table>
		
	
	</body>
		<br/>
		<br/>
	
	
	
</html>