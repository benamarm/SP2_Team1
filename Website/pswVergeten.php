
<html lang="en">
    <head>
        <title>Wachtwoord vergeten</title>
        <link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
       <link rel="stylesheet" type="text/css"
             href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
    </head>
    
    <body class="body">
    
        <div class="container-fluid container">
            <form class="vormLogin" action="pswVergeten.php" method="post">
            <p class="wwVergetenTekst">Jouw username/email: </p> <br/> <br/> <input type="text" name="email">
                <input type="submit" name="submit" value="Send">
            </form>
        </div>
    </body>
</html>

<?php 
error_reporting(0);
if($_POST['submit']=='Send')
{
$email=$_POST['email'];
	
include('connectie.php');
	
$query ="select * from SP2Team01.weblogin where loginemail='$email'";

	
$rs = mysqli_query($conn,$query);
$row = mysqli_fetch_array($rs,MYSQLI_ASSOC);
	

$count = mysqli_num_rows($rs);

    if($count == 1) 
	  {
$stmt = mysqli_query($conn, $query);

		if (!$stmt) {
			die ('SQL Error: ' . mysqli_error($conn));
		}
		$salt="";
	
		while ($row = mysqli_fetch_array($stmt))
		{

		$salt = $row['salt'];
			
		}

function random_password( $length = 15 ) 
{
    $chars = "abcd458a4efghijGHIJKLMNOPklmnuHopqrs54tuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    $code = substr( str_shuffle( $chars ), 0, $length );
    return $code;
}

$code = random_password(15);
	
$bericht="Hier is de link met jouw nieuwe passwoord: http://dtsl.ehb.be/~marwa.ben.smida/softwareProject2/HR/paswoord.php?email=$email&code=$code";
mail($email, "SPTeam01 - Paswoord vergeten", $bericht);


$hashabel = substr($salt, 0, 15).$code.substr($salt, 15, 30);
$pepper = "PEPPER";
	
$var = hash('sha512', $hashabel);
$encryptedPW = ($var.$pepper);

$sql2 = "UPDATE SP2Team01.weblogin SET password ='$encryptedPW' where loginemail='$email'";
	if (mysqli_query($conn, $sql2))
	{
    echo " ";
	} 
		else 
	{
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
		echo '<script>window.alert("Email is gezonden! Check jouw emailbox!")</script>';

		
}
	
	else 
	{
   echo '
		<div class="opHeader4">
		<h4>Dit username/email bestaat niet.</h4>
			
		</div>';
	}

}	
	 



?>