<?php

require "DB_CONNECT.php";

$user_name = $_POST["user_name"];
$pass_word = $_POST["pass_word"];

$query = "select * from player where Username like '$user_name' and Pass like '$pass_word'";

$result = mysqli_query($connection, $query);

if(mysqli_num_rows($result) > 0)
{
	echo "Success, Hello " . $user_name . "!";
}
else
{
	$query = "select * from manager where Username like '$user_name' and Pass like '$pass_word'";
	
	$result = mysqli_query($connection, $query);
	
	if(mysqli_num_rows($result) > 0)
	{
		echo "Success, Hello " . $user_name . "!";
	}
	else
	{
		echo "Failed, password or username incorrect.";
	}
}