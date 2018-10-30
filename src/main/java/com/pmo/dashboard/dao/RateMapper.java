package com.pmo.dashboard.dao;

import java.util.List;

import com.pmo.dashboard.entity.Rate;

public interface RateMapper
{
    List<Rate> queryRateBySkill(String skill);
}
