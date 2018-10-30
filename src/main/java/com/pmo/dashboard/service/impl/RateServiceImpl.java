package com.pmo.dashboard.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.RateMapper;
import com.pmo.dashboard.entity.Rate;
import com.pom.dashboard.service.RateService;

@Service
public class RateServiceImpl implements RateService
{
    
    @Resource
    private RateMapper rateMapper;

    @Override
    public List<Rate> queryRateBySkill(String skill)
    {
        List<Rate> list = rateMapper.queryRateBySkill(skill);
        return list;
    }

}
