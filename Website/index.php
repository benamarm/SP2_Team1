<?php
include('sessie.php');
session_start();

?>

<html lang="en">
<head>
	
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">


</head>
<body class="body">
	
	<div class="container-fluid container">
		<form class="vormLogin" action="" method="post">
			<img src="afbeeldingen/login-icon-39414.png">
			<h1 class="titelLogin">LOGIN</h1>
				<label for="exampleInputEmail1"class="email">Email</label>
				<input type="email" class="form-control" id="exampleInputEmail1" name="username" placeholder="Email" required autofocus>

				<label for="exampleInputPassword1">
					Password
				</label>
				<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name ="password" required>
			
		<br/><br/>

			<button type="submit" class="btn btn-success btn-block" role="button">LOGIN</button>
			<br/>
	
			<div style = "font-size:15px; color:#cc0000; margin-bottom:10px"><?php echo $error; ?></div>
			<div style = "font-size:15px; color:#cc0000; margin-top:-10px; text-align:center; font-weight: bold;"><?php echo $_GET["notloged"] ?></div>
			
			<a href="pswVergeten.php" class="psw">Paswoord vergeten</a>
			
			</form>
			
		
		
	</div>
	
		
</body>
	


</html>