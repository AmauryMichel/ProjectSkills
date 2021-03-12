<?php

require "DB_CONNECT.php";

$user_name = $_POST["user_name"];
$pass_word = $_POST["pass_word"];
$acc_type = $_POST["acc_type"];

$query = "select * from player where Username like '$user_name'";
	
$result = mysqli_query($connection, $query);

if(mysqli_num_rows($result) > 0)
{
	echo 'ERROR: Username Taken.';
}
else
{
	$query = "select * from manager where Username like '$user_name'";
	
	$result = mysqli_query($connection, $query);
	
	if(mysqli_num_rows($result) > 0)
	{
		echo 'ERROR: Username Taken.';
	}
	else
	{
		if($acc_type == "player")
		{	
			$query = "insert into player(Username, Pass) values('$user_name', '$pass_word')";
			
			$result = mysqli_query($connection, $query);
			
			echo "Player Register Success! Welcome " . $user_name . "!";
		}
		else if($acc_type == "manager")
		{
			$query = "insert into manager(Username, Pass) values('$user_name', '$pass_word')";
			
			$result = mysqli_query($connection, $query);
			
			echo "Manager Register Success! Welcome " . $user_name . "!";
		}
	}
	
}