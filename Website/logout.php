<?php
session_start();
session_destroy();
unset($_SESSION['loginemail']);
header("location:index.php");


?>