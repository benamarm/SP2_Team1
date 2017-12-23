<?php

include ('sessie.php');
session_start();

if(!$_SESSION["loginemail"])
{
	header("location:index.php?notloged=You are not loged in!");
}

$pid = $_GET['pid'];
$opid = $_GET['opid'];

?>

<html lang="en">
	
<head>
   <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
	
</head>
	
<body class="home">
	
<div class="logoHome">
<form class="logologo">
<img src="afbeeldingen/Infinit_company_logo.png" width="150px" class="logoo">
</form>
</div>
	 <div class="mijnOpleidingen">
		 <table class="data-table">
		<br/>
		<thead>
			<tr>
				<th>VRAGEN</th>
	

			</tr>
		</thead>
		<tbody>
			

	  
<?php
			$pubid = $_GET['pubid'];
			$sid = $_GET['sid'];
			$pid = $_GET['pid'];
		
$sql = "SELECT vraag_id, vragen.survey_id, vraag, inx
FROM SP2Team01.vragen JOIN SP2Team01.surveys ON (surveys.survey_id = vragen.survey_id)
WHERE vragen.survey_id = $sid AND inx > 0 ORDER BY inx";	
		 
	
$query = mysqli_query($conn, $sql);

	if (!$query) 
	{
		die ('SQL Error: ' . mysqli_error($conn));
	}
	
			$arr = array();
			
		
	echo'<form name="form1" method="post" action="surveyAntwoorden.php?pid='.$pid.'&sid='.$sid.'&pubid='.$pubid.'">';
			
			
	while ($row = mysqli_fetch_array($query))
	{
		
		$arr[$row['vraag_id']] = $row['inx'];
		
		echo "<tr><td>";
		echo $row['vraag'];
		echo '</td></tr>';
		
		echo "<tr><td>";
		echo'<textarea rows="5" name="'.$row['inx'].'" cols="50" value="antwoord" id="antwoord" required></textarea>';
		echo '</td></tr>';
	
	}
			
	echo "<tr><td>";
			
	echo'<input type="submit" class="btn btn-primary" name="submit" value="Submit" id="submit"  >';
	echo '</td></tr>';
	echo '</form>';
		

		?>
			
			
			
<?php
		$pubid = $_GET['pubid'];
include ('connectie.php');
if(isset($_POST["submit"]))
{
	foreach($arr as $vraagid => $inx)	
	{
			$sql = "INSERT INTO SP2Team01.antwoorden (vraag_id, antwoord) VALUES ('$vraagid', '$_POST[$inx]')";
				if (mysqli_query($conn, $sql))
				{
			
				} else {
					echo "Error: " . $sql . "<br>" . mysqli_error($conn);
				}
	}
	$pid = $_GET['pid'];
	$sql = "INSERT INTO SP2Team01.publicatiePersoneel (publicatie_id, pers_id) VALUES ('$pubid', '$pid')";
	if (mysqli_query($conn, $sql))
	{
			header("location: bedankt.php?pid=" .$pid);
			
	} else {
		echo "Error: " . $sql . "<br>" . mysqli_error($conn);
	}
} 

 ?>
			
	
	</tbody>
	</table>

		  
<div class="terug">
  <form class="knopTerug">
	 <?php 
echo'
	<a href="vragen.php?pid='.$pid.'" class="btn btn-info" role="button">Keer terug</a>';
	
	?>
</form>
</div>
	</div>
	  
	  
  </body>
</html>
