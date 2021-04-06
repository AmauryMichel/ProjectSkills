<?php

include 'DB_CONNECT.php';

$db = new DB();
$co = $db->getConnection();

if (!isset($_POST['login'])) {
    print("No username");
} elseif (!isset($_POST['pass'])) {
    print("No password");
} else {
    $db->login($_POST['login'], $_POST['pass']);
}

?>
