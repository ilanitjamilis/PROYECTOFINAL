<?php
	$servername = "192.168.1.107";
	$dbname = "db";
	$username = "root";
	$passworddb = "";
	
	$conn = mysqli_connect($severname, $username, $password, $dbname);
	
	if(!$conn){
		die("Connection failed: ".mysqli_connect_error());
	}
	else{
		$query = "SELECT * from denuncias";
		$result = mysqli_query($conn, $query);
		if($result->num_rows > 0){
			echo "Hay registros";
		}
		else{
			echo "No hay registros";
		}
	}
?>