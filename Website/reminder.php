<?php

include ('connectie.php');
include ('sessie.php');

    $sqlMail=("SELECT loginemail, startdatum, einddatum, naam
                        FROM SP2Team01.weblogin JOIN SP2Team01.vaardigheden ON (weblogin.pers_id=vaardigheden.pers_id) JOIN SP2Team01.events ON (vaardigheden.event_id=events.event_id) JOIN SP2Team01.opleidingen ON(opleidingen.opleiding_id = events.opleiding_id) WHERE
                       startdatum-CURRENT_DATE = 1");
	
		$query = mysqli_query($conn, $sqlMail);

		if (!$query) 
		{
			die ('SQL Error: ' . mysqli_error($conn));
		}

		while ($row = mysqli_fetch_array($query))
		{
			
			$subject = 'Reminder';
			$message = 'Beste,
			
			Dit is een reminder voor een event waarvoor u bent ingeschreven. Het event '.$row['naam'].' begint morgen '.$row['startdatum'].' en eindigt '.$row['einddatum'].'.Gelieve niet te antwoorden op deze mail.' ;
    		$header = 'From: SP2Team01' . "";
			
			mail($row['loginemail'],  $subject, $message, $header);
			
			
		};

?>