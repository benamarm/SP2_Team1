<?php

include ('sessie.php');
session_start();

if(!$_SESSION["loginemail"])
{
	header("location:index.php?notloged=You are not logged in!");
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
		
		
		<?php
	
	$pid = $_GET['pid'];
	$opid = $_GET['opid'];
	$idev=$_GET['idev'];
	
	echo'
	<div class="container">
		<form class="nav">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="#"><h13 class="mijnNaam" style = color:white;></h3></a>
    </div>
   <div class="collapse navbar-collapse" id="myNavbar">
       <ul class="nav navbar-nav">
	    <li><a href="me.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Mijn profiel</a></li>
        <li><a href="opleidingen.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Inschrijvingen</a></li>
        <li><a href="faq.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Mijn events</a></li> 
		  <li class="active"><a href="vragen.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Vragen</a></li>  
	
	
        <li><a href="logout.php"><span class="glyphicon glyphicon-log-in"></span>&nbsp; Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
	</form>

	</div>';
		
	?>
		<head>
	<link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
		</head>

	<div class="mijnOpleidingen">
		<table class="data-table">
		<br/>
		<thead>

			<tr>
			<th colspan="6">Opleiding </th>
			</tr>
		
		</thead>
	
		<?php
		
		$sql1 = "SELECT DISTINCT opleidingen.opleiding_id,naam, naam_trainer, startdatum, einddatum FROM SP2Team01.vaardigheden JOIN SP2Team01.events ON (vaardigheden.event_id=events.event_id) JOIN SP2Team01.opleidingen ON(opleidingen.opleiding_id=events.opleiding_id) JOIN SP2Team01.surveys ON (opleidingen.opleiding_id = surveys.opleiding_id) WHERE opleidingen.opleiding_id= surveys.opleiding_id and vaardigheden.pers_id='$pid' and checked=1 and einddatum < sysdate()";	
	
		$query = mysqli_query($conn, $sql1);

		if (!$query) 
		{
			die ('SQL Error: ' . mysqli_error($conn));
		}

		while ($row = mysqli_fetch_array($query))
		{
			
		echo'<tr>';
			echo "</td><td>";
			echo $row['naam'];
			echo "</td><td>";
			echo $row['naam_trainer'];
			
			echo "</td><td>";
			echo '<a href="survey.php?opid='.$row['opleiding_id'].'&pid='.$pid.'">  Surveys beantwoorden</a>';
						echo "</td></tr>";
	
		}
	
		
		?>
			
		

			</body>
	<script type="text/javascript">
	
$(document).ready(function() 
{
   $.ajax
   ({
       url: "http://services.odata.org/V4/Northwind/Northwind.svc/Employees(<?php echo $pid; ?>)"
   }).then(function(data) 
	{
	   $('.first').append(data.FirstName);
	    $('.mijnTitel').append(data.Title);
	   $('.mijnNaam').append(data.FirstName + " " + data.LastName);
      $('.mijnAdres').append(data.City + " " + data.Address + " " + data.PostalCode + ", " + data.Region );
      $('.myBirth').append(data.BirthDate.substring(0,9));
	   $('.mijnTelefoon').append(data.HomePhone);
   });
});
</script>

	
	<br/>
	

</html>
