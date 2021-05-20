package com.example.projectskills.drills;

public class Drill {
    int drillId;
    String drillName;
    String drillDesc;

    public Drill(int drillId, String drillName, String drillDesc) {
        this.drillId = drillId;
        this.drillName = drillName;
        this.drillDesc = drillDesc;
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
}
