<?php

include 'DB_CONNECT.php';

$db = new DB();
$co = $db->getConnection();

if (!isset($_POST['userID'])) {
    print("No user ID");
} else {
    $db->getDrills($_POST['userID']);
}


