<?php

include 'DB_CONNECT.php';

$db = new DB();
$co = $db->getConnection();

if (!isset($_POST['managerID'])) {
    print("No manager ID");
} elseif (!isset($_POST['groupName'])) {
    print("No group name");
} else {
    $db->createGroup($_POST['managerID'], $_POST['groupName']);
}

?>
