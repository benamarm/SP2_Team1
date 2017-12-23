
<?php

include ('sessie.php');
session_start();

if(!$_SESSION["loginemail"])
{
	header("location:index.php?notloged=You are not loged in!");
}
$pid = $_GET['pid'];

?>

<!DOCTYPE html>
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
	
	
</head>
	
	
<body class="home">
	<div class="logoHome">
	<form class="logologo">
		<img src="afbeeldingen/Infinit_company_logo.png" width="120px">
		
		</form>
	
	</div>
<?php
	$pid = $_GET['pid'];
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
        <li class="active"><a href="faq.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Mijn events</a></li> 
		  <li><a href="vragen.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Vragen</a></li> 
	
        <li><a href="logout.php"><span class="glyphicon glyphicon-log-in"></span>&nbsp; Logout</a></li>
      </ul>
  </div>
  </div>
</nav>
	</form>

	</div>';
	
	?>
	


	<div class="mijnOpleidingen">
		<table class="data-table">
		<br/>
		<thead>

			<tr>
			<th colspan="6">Opleidingen aanvaard</th>
			</tr>
			<tr>
				<th>Opleiding</th>
				<th>Trainer</th>
				<th>Startdatum</th>
				<th>Einddatum</th>
				<th>Adres</th>
				
			</tr>
		</thead>
		<tbody>
			
	<?php 
			$pid = $_GET['pid'];
		$opid = $_GET['opid'];
		$idev=$_GET['idev'];
		
		
		$sql1 = "SELECT distinct vaardigheden.event_id, naam, naam_trainer, startdatum, einddatum, straat,  nummer, postcode FROM SP2Team01.vaardigheden JOIN SP2Team01.events ON(vaardigheden.event_id=events.event_id) JOIN SP2Team01.opleidingen ON(opleidingen.opleiding_id=events.opleiding_id) JOIN SP2Team01.adressen ON(events.adres_id=adressen.adres_id) where pers_id='$pid' and checked=1 and adressen.adres_id=events.adres_id and einddatum > sysdate()";	
	
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
			echo $row['startdatum'];
			echo "</td><td>";
			echo $row['einddatum'];
			echo "</td><td>";
			echo $row['straat'];
			echo '&nbsp;';
			echo $row['nummer'];
			echo '<br/>';
			echo $row['postcode'];
echo '<br/>';
			echo '<a href="straat.php?straatid='.$row['straat'].'&nrid='.$row['nummer'].'&postid='.$row['postcode'].'&pid='.$pid.'">GOOGLE MAPS</a>';
			echo "</td></tr>";
	
	
		}
	
		
		?>
			
			
		</tbody>	
	</table>
	
	
			<table class="data-table">
		<br/>
		<thead>
	<tr>
			<th colspan="6">Opleidingen in afwachting </th>
			</tr>
			<tr>


				<th>Opleiding</th>
				<br/>
				<th>Trainer</th>
				<th>Startdatum</th>
				<th>Einddatum</th>
				<th>Adres</th>
			</tr>
		</thead>
		<tbody>
			
	<?php 
		
			$pid = $_GET['pid'];
		
		$sql0 = "SELECT distinct vaardigheden.event_id, naam, naam_trainer, startdatum, einddatum, straat,  nummer, postcode FROM SP2Team01.vaardigheden JOIN SP2Team01.events ON(vaardigheden.event_id=events.event_id) JOIN SP2Team01.opleidingen ON(opleidingen.opleiding_id=events.opleiding_id) JOIN SP2Team01.adressen ON(events.adres_id=adressen.adres_id) where pers_id='$pid' and checked IS NULL and adressen.adres_id=events.adres_id";	
	
	
		$query = mysqli_query($conn, $sql0);

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
			echo $row['startdatum'];
			echo "</td><td>";
			echo $row['einddatum'];
			echo "</td><td>";
			echo $row['straat'];
			echo '&nbsp;';
			echo $row['nummer'];
			echo '<br/>';
			echo $row['postcode'];
			echo '<br/>';
		echo '<a href="straat.php?straatid='.$row['straat'].'&nrid='.$row['nummer'].'&postid='.$row['postcode'].'&pid='.$pid.'">GOOGLE MAPS</a>';
			echo "</td></tr>";

	
			
		}	

	
		
		?>
			
		</tbody>
				
	</table>
		
		<table class="data-table">
		<br/>
		<thead>

			<tr>
			<th colspan="6">Voorbije opleidingen</th>
			</tr>
			<tr>
				<th>Opleiding</th>
				<th>Trainer</th>
				<th>Startdatum</th>
				<th>Einddatum</th>
	
				
			</tr>
			
		
		</thead>
		<tbody>
			
	<?php 
		$pid = $_GET['pid'];
		$opid = $_GET['opid'];
		$idev=$_GET['idev'];
		
		
		$sql3 = "SELECT distinct vaardigheden.event_id, vaardigheden.vaardigheid_id, naam, naam_trainer, startdatum, einddatum, straat,  nummer, postcode FROM SP2Team01.vaardigheden JOIN SP2Team01.events ON(vaardigheden.event_id=events.event_id) JOIN SP2Team01.opleidingen ON(opleidingen.opleiding_id=events.opleiding_id) JOIN SP2Team01.adressen ON(events.adres_id=adressen.adres_id) where pers_id='$pid' and checked=1 and adressen.adres_id=events.adres_id and einddatum < sysdate()";	
	
		$query = mysqli_query($conn, $sql3);

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
			echo $row['startdatum'];
			echo "</td><td>";
			echo $row['einddatum'];

			
			echo "</td></tr>";
			
	
	
		}
	
		
		?>
			<?php
			
			
			
			

			?>
										
		</tbody>	
	</table>
	
	</div>
	<br/>
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

	
		
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</body>

	
</html>