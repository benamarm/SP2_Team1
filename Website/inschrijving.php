<?php
include ('connectie.php');


include ('connectie.php');
			$_GET['id'];
			
			$id = $_GET['id'];
			
$sql = "SELECT opleiding_id, naam_trainer, startdatum, einddatum FROM SP2Team01.events where opleiding_id = '$id
'";
			

$query = mysqli_query($conn, $sql);

if (!$query) 
{	die ('SQL Error: ' . mysqli_error($conn));
 
}
			
while ($row = mysqli_fetch_assoc($query))
		{
	

	echo "</td><td>";
    echo $row['opleiding_id'];
    echo "</td><td>";
    echo $row['naam_trainer'];
	echo "</td><td>";
    echo $row['startdatum'];
    echo "</td><td>";
    echo $row['einddatum'];
	echo "</td><td>";
    echo '<a href="events.php?id='.$row['opleiding_id'].'">  &nbsp; Inschrijving</a>';
	 echo "</td></tr>";

	
	
	
}
		


?>
			






?>