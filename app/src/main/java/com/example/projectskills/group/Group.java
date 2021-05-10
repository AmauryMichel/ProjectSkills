package com.example.projectskills.group;

public class Group {
    int groupID;
    String groupName;
    boolean groupManager;

    public Group(int groupID, String groupName, boolean groupManager) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.groupManager = groupManager;
    }

    public int getGroupID() {
        return groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public boolean isGroupManager() {
        return groupManager;
    }
}
