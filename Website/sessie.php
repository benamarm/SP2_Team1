



<?php

include ('connectie.php');
session_start();

if($_SERVER["REQUEST_METHOD"] == "POST")
{
	

$myusername = mysqli_real_escape_string($conn,$_POST['username']);
$mypassword = mysqli_real_escape_string($conn,$_POST['password']);
	
	// mysqli_real_escape -> tegen sql injections !!!!
	
$queryTest ="select * from SP2Team01.weblogin where loginemail='$myusername'";
	
			
$stmt = mysqli_query($conn, $queryTest);

		if (!$stmt) {
			die ('SQL Error: ' . mysqli_error($conn));
		}
		$salt="";
	
		while ($row = mysqli_fetch_array($stmt))
		{

		$salt = $row['salt'];
			
		}
	
	
	$hashabel = substr($salt, 0, 15).$mypassword.substr($salt, 15, 30);
	$pepper = "PEPPER";
	
	$var = hash('sha512', $hashabel);
	$encryptedPW = ($var.$pepper);


	$query = "SELECT pers_id FROM SP2Team01.weblogin WHERE loginemail= '$myusername' 
AND password= '$encryptedPW'";
	

$emp = $row['pers_id'];

	
$rs = mysqli_query($conn,$query);
$row = mysqli_fetch_array($rs,MYSQLI_ASSOC);
	

$count = mysqli_num_rows($rs);

    if($count == 1) 
	  {

         $_SESSION['loginemail'] = $myusername;
         header("location: me.php?pid=" .$row['pers_id']);
		exit;
      }
	else 
	  {
         $error = "Your Login Name or Password is invalid";
      }
}

	
	?>
