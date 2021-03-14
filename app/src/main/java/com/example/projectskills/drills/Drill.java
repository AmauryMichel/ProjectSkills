package com.example.projectskills.drills;

public class Drill {
    int drillId;
    String drillName;
    String drillDesc;
    String drillGroupName;

    public Drill(int drillId, String drillName, String drillDesc, String drillGroupName) {
        this.drillId = drillId;
        this.drillName = drillName;
        this.drillDesc = drillDesc;
        this.drillGroupName = drillGroupName;
    }

    public int getDrillId() {
        return drillId;
    }

    public String getDrillName() {
        return drillName;
    }

    public String getDrillDesc() {
        return drillDesc;
    }

    public String getDrillGroupName() {
        return drillGroupName;
    }
}
