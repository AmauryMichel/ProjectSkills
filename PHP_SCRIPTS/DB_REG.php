<?php

include 'DB_CONNECT.php';

$db = new DB();
$co = $db->getConnection();

if (!isset($_POST['login'])) {
    print("No username");
} elseif (!isset($_POST['pass'])) {
    print("No password");
} else {
  $db->register($_POST['login'], password_hash($_POST['pass'], PASSWORD_DEFAULT));
}