
<html lang="en">
    <head>
        <title>Wachtwoord vergeten</title>
        <link href="css/mijnCSS.css" type="text/css" rel="stylesheet">
       <link rel="stylesheet" type="text/css"
             href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
    </head>
    
    <body class="body">
    
        <div class="container-fluid container">
            <form class="vormLogin">
            
				<?php

$code = $_GET['code'];

echo 'Jouw niewe code is: '; echo $code;
echo '<br/>';
echo '<br/>';
echo'<a href="index.php">LOGIN met je nieuwe paswoord.</a>';

?>
            </form>
        </div>
    </body>
</html>


