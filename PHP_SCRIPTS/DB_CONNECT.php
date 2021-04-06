<?php
class db {
  private $host = "localhost";
  private $dbUser = "root";
  private $dbPass = "root";
  private $dbName = "project";
  private $conn;

  function getConnection() {
    $this->conn = mysqli_connect($this->host, $this->dbUser, $this->dbPass, $this->dbName);
    if (!$this->conn) {
      die("Could not connect:" . mysqli_error());
    }
    return $this->conn;
  }

  function login($login, $pass) {
    $stmt = $this->conn->prepare("SELECT * FROM player WHERE username = ?");
    $stmt->bind_param("s", $login);
    $stmt->execute();
    $result = $stmt->get_result(); // get the mysqli result

    if(mysqli_num_rows($result) != 0) {
      $data = mysqli_fetch_array($result);
    } else {
      print("No user with that username");
      return;
    }

    if (password_verify($pass, $data['pass'])) { //check if the password is correct
      print("Success\n");
    } else {
      print("Wrong password");
    }
    $stmt->close();
  }

  function register($login, $pass) {
    //Check if there is a player with that username
    $stmtSelect = $this->conn->prepare("SELECT * FROM player WHERE username = ?");
    $stmtSelect->bind_param("s", $login);
    $stmtSelect->execute();
    $result = $stmtSelect->get_result(); // get the mysqli result

    if(mysqli_num_rows($result) > 0) {
      print("Username taken");
      return;
    }

    $stmtInsert = $this->conn->prepare("INSERT INTO player (username, pass) VALUES (?, ?)");
    $stmtInsert->bind_param("ss", $login, $pass);
    $stmtInsert->execute();
    print("Success");
    $stmtSelect->close();
    $stmtInsert->close();
  }

  function getDrillsGroup($groupID) {
    $stmt = $this->conn->prepare("SELECT * FROM group_drill NATURAL JOIN drill WHERE group_id = ?");
    $stmt->bind_param("i", $groupID);
    $stmt->execute();
    $result = $stmt->get_result(); // get the mysqli result
  }

  function getDrillsManager($managerID) {
    $stmt = $this->conn->prepare("SELECT * FROM drill WHERE man_ID = ?");
    $stmt->bind_param("i", $managerID);
    $stmt->execute();
    $result = $stmt->get_result(); // get the mysqli result
  }

  function addDrill($managerID, $drillName) {
    $stmt = $this->conn->prepare("INSERT INTO drill (man_id, drill_name) VALUES (?, ?)");
    $stmt->bind_param("is", $managerID, $drillName);
    $stmt->execute();
    $result = $stmt->get_result(); // get the mysqli result
  }
}

?>
