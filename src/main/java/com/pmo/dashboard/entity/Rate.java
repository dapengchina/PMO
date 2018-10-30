package com.pmo.dashboard.entity;

public class Rate
{
    
    private String location;
    
    private String skill;
    
    private String position;
    
    private String billRate;

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getSkill()
    {
        return skill;
    }

    public void setSkill(String skill)
    {
        this.skill = skill;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getBillRate()
    {
        return billRate;
    }

    public void setBillRate(String billRate)
    {
        this.billRate = billRate;
    }

    public Rate(String location, String skill, String position, String billRate)
    {
        super();
        this.location = location;
        this.skill = skill;
        this.position = position;
        this.billRate = billRate;
    }

    public Rate()
    {
        super();
        // TODO Auto-generated constructor stub
    }

}
