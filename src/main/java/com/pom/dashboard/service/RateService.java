package com.pom.dashboard.service;

import java.util.List;

import com.pmo.dashboard.entity.Rate;

public interface RateService
{
    
    public List<Rate> queryRateBySkill(String skill);

}
