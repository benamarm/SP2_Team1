<?php
session_start();
include ('sessie.php');

$idev = $_GET['idev'];
//echo $idev;

$pid = $_GET['pid'];
//echo $persid2;

$opid = $_GET['opid'];
//echo $opid;

$sql = "INSERT INTO SP2Team01.vaardigheden (pers_id, event_id, checked, certificaat) VALUES ('$pid', $idev, NULL, NULL)" ;

$sql2 = "UPDATE SP2Team01.events SET aantal_deelnames = aantal_deelnames + 1 WHERE event_id= '$idev'";



if (mysqli_query($conn, $sql))
{
    echo "Uw inschrijving werd volbracht! ";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

if (mysqli_query($conn, $sql2))
{
    echo " ";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}




mysqli_close($conn);

echo '</br>';

echo '<a href="faq.php?idev='.$idev.'&pid='.$pid.'&opid='.$opid.'">Overzicht van opleidingen</a>';


?>


