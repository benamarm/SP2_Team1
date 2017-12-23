<html>
<head></head>
<body>
	
	
	<script>window.alert("Uw antwoord werd doorgestuurd!")</script>
	</body>
</html>
<?php
include ('connectie.php');
if (isset($_POST["antwoord"])) 
{
  	$antwoord = $_POST['antwoord'];
	$vrid = $_GET['vrid'];
	$pid = $_GET['pid'];
	
	$sql = "INSERT INTO SP2Team01.antwoorden (vraag_id, antwoord) VALUES ('$vrid', '$antwoord')";
	
	
	
	
if (mysqli_query($conn, $sql))
{
include('vragen.php');
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
	
	
	
} 

 ?>

