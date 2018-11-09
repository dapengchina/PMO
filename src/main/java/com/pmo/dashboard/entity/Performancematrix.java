package com.pmo.dashboard.entity;

public class Performancematrix {
	
	
    private String id;

    private String type;

    private Integer index;

    private String weightrate;

    private String phasegoal;

    private String keyaction;

    private String department;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getWeightrate() {
        return weightrate;
    }

    public void setWeightrate(String weightrate) {
        this.weightrate = weightrate == null ? null : weightrate.trim();
    }

    public String getPhasegoal() {
        return phasegoal;
    }

    public void setPhasegoal(String phasegoal) {
        this.phasegoal = phasegoal == null ? null : phasegoal.trim();
    }

    public String getKeyaction() {
        return keyaction;
    }

    public void setKeyaction(String keyaction) {
        this.keyaction = keyaction == null ? null : keyaction.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}