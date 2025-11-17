package com.example.busstation.model;

// 1. Staff implementează BaseEntity
public abstract class Staff implements BaseEntity {

    // 2. Câmpul se numește "id"
    private String id;
    private String staffName;

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    // 3. Metodele 'getId' și 'setId' sunt implementate AICI
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Staff() {
    }
}