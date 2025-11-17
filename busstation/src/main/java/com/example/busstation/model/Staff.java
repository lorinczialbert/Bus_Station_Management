package com.example.busstation.model;

// 1. ADD THESE IMPORTS
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// 2. ADD THESE ANNOTATIONS
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@type") // This is the property we will add to the JSON
@JsonSubTypes({
        // List all concrete classes that extend Staff
        @JsonSubTypes.Type(value = Driver.class, name = "driver"),
        @JsonSubTypes.Type(value = TripManager.class, name = "manager")
})
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