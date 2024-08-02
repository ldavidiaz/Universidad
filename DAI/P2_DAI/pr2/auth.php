<?php
session_start();
if ($_SESSION["isAuth"]!=true){
	header("Location: ../NoAutorizado.html");
	exit();
}
?>