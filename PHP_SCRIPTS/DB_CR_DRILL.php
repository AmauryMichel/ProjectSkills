<?php

include 'DB_CONNECT.php';

$db = new DB();
$co = $db->getConnection();

if (!isset($_POST['managerID'])) {
    print("No manager ID");
} elseif (!isset($_POST['drillName'])) {
    print("No drill name");
}elseif (!isset($_POST['drillDesc'])) {
    print("No drill description");
} else {
    $db->createDrill($_POST['managerID'], $_POST['drillName'], $_POST['drillDesc']);
}


?>
