<?php

	$salt1 = "salt1";
	$salt2 = "salt2";
	$salt3 = "salt3";
	$salt4 = "salt4";
	$salt5 = "salt5";
	$salt6 = "salt6";
	$salt7 = "salt7";

	$pass1 = "test1";
	$pass2 = "test2";
	$pass3 = "test3";
	$pass4 = "test4";
	$pass5 = "test5";
	$pass6 = "test6";
	$pass7 = "test7s";
	
	$pepper = "PEPPER";

	$hash1 = hash('sha512', substr($salt1, 0,4).$pass1.substr($salt1, 0,5)).$pepper;
	echo $hash1;
	echo '<br/>';
	$hash2 = hash('sha512', substr($salt2, 0,4).$pass2.substr($salt2, 0,5)).$pepper;
	echo $hash2;
	


?>


