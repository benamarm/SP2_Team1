<?php

include ('sessie.php');
session_start();

$pid = $_GET['pid'];


if(!$_SESSION["loginemail"])
{
	header("location:index.php?notloged=You are not logged in!");
}


	$opid = $_GET['opid'];
	$idev=$_GET['idev'];

echo'

<html>
   <head>
   <title>Mijn profiel</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="login.php">
   <link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
   </head>
   
   <body class="home">
   		<div class="logoHome">
		<form class="logologo">
		<img src="afbeeldingen/Infinit_company_logo.png" width="150px" class="logoo">
		</form>
		</div>
		
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
		<li class="active"><a href="me.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Mijn profiel</a></li>
        <li><a href="opleidingen.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Inschrijvingen</a></li>
       	<li><a href="faq.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Mijn events</a></li> 
		<li><a href="vragen.php?pid='.$pid.'&opid='.$opid.'&idev='.$idev.'">Vragen</a></li> 
		<li><a href="logout.php"><span class="glyphicon glyphicon-log-in"></span>&nbsp; Logout</a></li>
		</ul>
		</div>
		</div>
		</nav>
		</form>
		</div>
		
   		<div class="opHeader">
			<h1 class="mijnNaam"></h1>
			<h4 class="mijnTitel"></h4>
			<br/>
			<p class="opUitleg">
				<p class="myBirth">Geboortedatum: </p>
				<p class="mijnAdres">Adres: </p>
				<p class="mijnTelefoon">Telefoonnr.: </p>

			</p>
		</div>
		
		<div class="opHeader2">
		<a href="pswReset.php?pid='.$pid.'"><h4>Mijn paswoord resetten</h4></a>
		<form class="pasword">
		<img src="afbeeldingen/52840-password.png" width="120px" class="logoo">
		</form>
		
		
			
		</div>
		
   </body>';
?>
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

<?php
echo'</html>';
	
?>
