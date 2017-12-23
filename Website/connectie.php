<?php

include_once 'dbGegevens.php';
	$conn = new mysqli($db_host, $db_user, $db_pass);

	if ($conn->connect_error)
	{
		die("Connection faild: " .$conn->connect_error);

	}

?>