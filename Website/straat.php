<?php

include ('sessie.php');
session_start();

if(!$_SESSION["loginemail"])
{
	header("location:index.php?notloged=You are not loged in!");
}
$straatid = $_GET['straatid'];
$postid = $_GET['postid'];
$nrid = $_GET['nrid'];
$pid = $_GET['pid'];

?>


<html>
  <head>
	  <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="login.php">
	<link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
			body
		{
			background: url(afbeeldingen/road-1176997_1920.jpg);
	background-repeat: no-repeat;
	background-attachment: fixed;
	width: 95%; height: 90vh;
	color:black;
		}
       #map 
		{
        height: 400px;
        width: 100%;
		
       }
		
		.frame
		{
		max-width: 600px;
			border: 4px;
-moz-border-radius: 15px;
border-radius: 15px;
overflow: hidden;
			margin: auto;
			margin-top: 55px;
		-webkit-box-shadow: -2px -1px 23px 21px rgba(0,0,0,0.75);
-moz-box-shadow: -2px -1px 23px 21px rgba(0,0,0,0.75);
box-shadow: -2px -1px 23px 21px rgba(0,0,0,0.75);
		}
		.knopTerug
		{
			max-width: 130px;
			padding: 20px;
			margin: auto;
			margin-top: 40px;
			
			
		}
	
		
		
    </style>
	  
	  
  </head>
  <body>
	  
	  <div class="straatframe">
	<form class="frame">
   <iframe
  width="600"
  height="450"
  frameborder="0" style="border:0"
  src="https://www.google.com/maps/embed/v1/place?key=AIzaSyCgO3Z-zL2ggpnAipHRay4qVv6jKdRl0ok
    &q=<?php echo $straatid; echo ''; echo $nrid; echo ' '; echo' '; echo $postid;?> " allowfullscreen>
</iframe>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCgO3Z-zL2ggpnAipHRay4qVv6jKdRl0ok&callback=initMap">
    </script>
			  </form>
		  </div>
	  
	  <div class="terug">
		  <form class="knopTerug">
	 <?php 
		  echo'

			  <a href="faq.php?pid='.$pid.'" class="btn btn-info btn-lg" role="button">Keer terug</a>';
			  
		
			  ?>
	  </form>
	  </div>
	  
	  
  </body>
	
	
</html>

