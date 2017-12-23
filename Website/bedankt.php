<?php

$pid = $_GET['pid'];
echo'
<html lang="en">
    <head>
        <title>Wachtwoord vergeten</title>
        <link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
       <link rel="stylesheet" type="text/css"
             href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
    </head>
    
    <body class="body">
    
        <div class="container-fluid container">
            <form class="vormLogin" >
				<h1 class="merci">Bedankt!</h1>
            <a href="me.php?pid='.$pid.'" class="hrf"><h4>Keer terug naar mijn profiel</h4></a>
			
                
            </form>
        </div>
    </body>
</html>';

?>
