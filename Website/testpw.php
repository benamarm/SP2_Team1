<?php

$pass = "test1";
echo $pass;

echo '<br/>';

$salt = "E5D5d4ced2f98Z7F8T1N6O51d61f54rg61";
$pepper = "M";

echo $salt;
echo '<br/>';

$passwHash = hash('sha512', $salt = substr($salt, 4, 15) + $pepper );
echo $passwHash;



?>