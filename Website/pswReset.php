<html lang="en">
    <head>
        <title>Wachtwoord vergeten</title>
        <link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
       <link rel="stylesheet" type="text/css"
             href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
    </head>
    
    <body class="body">
    <?php
		$pid = $_GET['pid'];
		echo'
        <div class="container-fluid container">
            <form class="vormLogin" action="pswReset.php?pid='.$pid.'" method="post">
			
            <h4 class="wwVergetenTekst">Nieuwe paswoord </h4>   <input type="password" name="newPassword">
				<br/><br/>
				<h4 class="wwVergetenTekst">Paswoord bevestigen</h4>  <input type="password" name="confirmPassword">
				<br/> <br/>
                <input type="submit" name="submit" value="submit">
            </form>
        </div>';
			?>
	
    </body>
	<script>
function validatePassword() {
var currentPassword,newPassword,confirmPassword,output = true;

currentPassword = document.frmChange.currentPassword;
newPassword = document.frmChange.newPassword;
confirmPassword = document.frmChange.confirmPassword;

if(!currentPassword.value) {
currentPassword.focus();
document.getElementById("currentPassword").innerHTML = "required";
output = false;
}
else if(!newPassword.value) {
newPassword.focus();
document.getElementById("newPassword").innerHTML = "required";
output = false;
}
else if(!confirmPassword.value) {
confirmPassword.focus();
document.getElementById("confirmPassword").innerHTML = "required";
output = false;
}
if(newPassword.value != confirmPassword.value) {
newPassword.value="";
confirmPassword.value="";
newPassword.focus();
document.getElementById("confirmPassword").innerHTML = "not same";
output = false;
} 	
return output;
}
</script>
</html>



<?php 
session_start();
include ('connectie.php');
include ('sessie.php');
$pid = $_GET['pid'];

if($_POST['submit']=='submit' )
{
	
$queryTest ="select * from SP2Team01.weblogin where pers_id='$pid'";
	
			
$stmt = mysqli_query($conn, $queryTest);

		if (!$stmt) {
			die ('SQL Error: ' . mysqli_error($conn));
		}
		$salt="";
	
		while ($row = mysqli_fetch_array($stmt))
		{

		$salt = $row['salt'];
			
		}



$currentPassword = $_POST['currentPassword'];
$hashabel = substr($salt, 0, 15).$_POST['currentPassword'].substr($salt, 15, 30);
$pepper = "PEPPER";
$var = hash('sha512', $hashabel);
$encryptedPWcur = ($var.$pepper);

$newPassword = $_POST['newPassword'];
$hashabel = substr($salt, 0, 15).$_POST['newPassword'].substr($salt, 15, 30);
$pepper = "PEPPER";
$var = hash('sha512', $hashabel);
$encryptedPWnew = ($var.$pepper);


$confirmPassword = $_POST['confirmPassword'];
$hashabel = substr($salt, 0, 15). $_POST['confirmPassword'].substr($salt, 15, 30);
$pepper = "PEPPER";
$var = hash('sha512', $hashabel);
$encryptedPWconf = ($var.$pepper);

	
$queryTest ="select * from SP2Team01.weblogin where pers_id = '$pid'";
			
$stmt = mysqli_query($conn, $queryTest);

		if (!$stmt) 
		{
			die ('SQL Error: ' . mysqli_error($conn));
		}
		$salt="";
		
		while ($row = mysqli_fetch_array($stmt))
		{

		$salt = $row['salt'];
		$psw = $row['password'];
			
		}
	
if($encryptedPWnew == $encryptedPWconf)
{
$sql = "UPDATE SP2Team01.weblogin SET password ='$encryptedPWnew' where pers_id= '$pid'";

if (mysqli_query($conn, $sql))
{
		echo '<script>window.alert("Uw paswoord is veranderd!")</script>
		<div class="opHeader3">
		<a href="me.php?pid='.$pid.'"><h4>Keer terug naar mijn profiel</h4></a>
			
		</div>';

} 
	else 
{
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

}
	else
	{
		echo'
		<div class="opHeader4">
		<h4>De wachtwoorden komen niet overeen. </h4>
			
		</div>';
	}
}

?>
