package com.example.projectskills.player;

public class Player {
    public int userID;
    public String username;

    public Player(int userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }
}
