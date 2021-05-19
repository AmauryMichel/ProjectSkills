<?php

include 'DB_CONNECT.php';

$db = new DB();
$co = $db->getConnection();

if (!isset($_POST['groupID'])) {
    print("No user ID");
} else {
    $db->getUsersNotInGroup($_POST['groupID']);
}


