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
	
	<link rel="stylesheet" href="login.php">
	<link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<head>
		<link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
</head>
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
				<th>Surveys</th>
				
				
			</tr>
		</thead>
		<tbody>
			

	  
<?php
		
$sql = "SELECT surveyPublicaties.survey_id, surveys.titel, MAX(surveyPublicaties.publicatie_id) AS maxpubid
FROM SP2Team01.surveyPublicaties
JOIN SP2Team01.surveys ON (surveys.survey_id = surveyPublicaties.survey_id)
WHERE surveys.opleiding_id = $opid
AND surveyPublicaties.tot > SYSDATE()
AND surveyPublicaties.actief = 1
AND surveyPublicaties.publicatie_id NOT IN (SELECT publicatie_id
FROM SP2Team01.publicatiePersoneel
WHERE pers_id = $pid)			
GROUP BY surveyPublicaties.survey_id, surveys.titel";	
	
$query = mysqli_query($conn, $sql);

	if (!$query) 
	{
		die ('SQL Error: ' . mysqli_error($conn));
	}

	while ($row = mysqli_fetch_array($query))
	{
			
		echo'<tr>';
		echo "</td><td>";
		$row['maxpubid'];
		echo'<a href="surveyAntwoorden.php?pid='.$pid.'&sid='.$row[survey_id].'&pubid='.$row['maxpubid'].'">'.$row['titel'].'</a>';
		
		echo "</td></tr>";
	
		}
	
		
		?>
					
		</tbody>	
	</table>
	
	  
	
		    <div class="terug">
		  <form class="knopTerug">
	 <?php 
		  echo'
			  
			  <a href="vragen.php?pid='.$pid.'" class="btn btn-info btn-lg" role="button">Keer terug</a>';
			  ?>
	  </form>
	  </div>
		  
	  </div>
	  
	  
  </body>
	
	
</html>
