<?php

include('connectie.php');

    $adres = "SP2Team01.adressen";
    $ant = "SP2Team01.antwoorden";
    $app = "SP2Team01.applogin";
    $boeken = "SP2Team01.boeken";
    $events = "SP2Team01.events";
    $logs = "SP2Team01.logs";
    $opl = "SP2Team01.opleidingen";
    $opl_boek = "SP2Team01.opleiding_boek";
    $pers = "SP2Team01.personeel";
    $pubPers = "SP2Team01.publicatiePersoneel";
    $surPub = "SP2Team01.surveyPublicaties";
    $sur = "SP2Team01.surveys";
    $vaar = "SP2Team01.vaardigheden";
    $vragen = "SP2Team01.vragen";
    $web = "SP2Team01.weblogin";



    $sql = "select * from SP2Team01.adressen";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('adres.txt', 'w');

    echo 'BACKUP TABEL ADRES';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
       fputcsv($fp, $row);
    }
    
    fclose($fp);

echo'<br/>';
echo'<br/>';




	$sql = "select * from $ant";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('antwoorden.txt', 'w');

    echo 'BACKUP TABEL ANTWOORDEN';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);
echo'<br/>';
echo'<br/>';
   


    $sql = "select * from $app";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('applogin.txt', 'w');

    echo 'BACKUP TABEL APPLOGIN';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);




$sql = "select * from $events";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('events.txt', 'w');

    echo 'BACKUP TABEL BOEKEN';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);





$sql = "select * from $logs";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('logs.txt', 'w');

    echo 'BACKUP TABEL LOGS';echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);

    




$sql = "select * from $opl";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('opleidingen.txt', 'w');

    echo 'BACKUP TABEL OPLEINDINGEN';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);

    



$sql = "select * from $opl_boek";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('opleidingBoek.txt', 'w');

    echo 'BACKUP TABEL OPLEIDING_BOEK';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);

    


$sql = "select * from $pers";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('personeel.txt', 'w');

    echo 'BACKUP TABEL PERSONEEL';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);

  



$sql = "select * from $pubPers";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('publicatiePersoneel.txt', 'w');

    echo 'BACKUP TABEL PUBLICATIE PERSONEEL';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);





$sql = "select * from $surPub";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('surveyPublicaties.txt', 'w');

    echo 'BACKUP TABEL SURVEY PUBLICATIES';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);




$sql = "select * from $sur";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('surveys.txt', 'w');

    echo 'BACKUP TABEL SURVEYS';

echo'<br/>';
    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);





$sql = "select * from $vaar";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('vaardigheden.txt', 'w');

    echo 'BACKUP TABEL VAARDIGHEDEN';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);






$sql = "select * from $vragen";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('vragen.txt', 'w');

    echo 'BACKUP TABEL VRAGEN';
echo'<br/>';
    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);





$sql = "select * from $web";
    $result = mysqli_query($conn, $sql) or die("Selection Error " . mysqli_error($conn));

    $fp = fopen('weblogin.txt', 'w');

    echo 'BACKUP TABEL WEBLOGIN';
echo'<br/>';

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);

    mysqli_close($conn);




	?>
