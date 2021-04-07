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

  //<editor-fold desc="Login/Register">
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
      print("Success");
      print($data['id']);
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
    print($this->conn->insert_id);
    $stmtSelect->close();
    $stmtInsert->close();
  }
  //</editor-fold>

  //<editor-fold desc="Get functions">
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
  //</editor-fold>

  //<editor-fold desc="Add functions">
  function createGroup($managerID, $groupName) {
    // Create the group
    $stmtGroup = $this->conn->prepare("INSERT INTO thegroup (group_name) VALUES (?)");
    $stmtGroup->bind_param("s", $groupName);
    $stmtGroup->execute();

    $groupID = $this->conn->insert_id; // Get the ID of the newly created group

    // Add the user as a manager in that group
    $stmtPlayerGroup = $this->conn->prepare("INSERT INTO player_group (player_id, group_id, is_manager) VALUES (?, ?, 1)");
    $stmtPlayerGroup->bind_param("ii", $managerID, $groupID);
    $stmtPlayerGroup->execute();
    print("Success");
  }

  function createDrill($managerID, $drillName) {
    $stmt = $this->conn->prepare("INSERT INTO drill (man_id, drill_name) VALUES (?, ?)");
    $stmt->bind_param("is", $managerID, $drillName);
    $stmt->execute();
  }
  //</editor-fold>
}

?>
