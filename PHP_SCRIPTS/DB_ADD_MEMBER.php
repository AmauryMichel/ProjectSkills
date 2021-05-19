<?php

include 'DB_CONNECT.php';

$db = new DB();
$co = $db->getConnection();

if (!isset($_POST['userID'])) {
    print("No user ID");
} elseif (!isset($_POST['groupID'])) {
    print("No group ID");
} else {
    $db->addMember($_POST['userID'], $_POST['groupID']);
}


