package com.pmo.dashboard.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmo.dashboard.entity.Rate;
import com.pom.dashboard.service.RateService;

@Controller
@RequestMapping(value="/rate")
public class RateController
{
    
    @Resource
    private RateService rateService;
    
    @RequestMapping("/queryRateBySkill")
    @ResponseBody
    public List recruitdemand(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        String skill = request.getParameter("skill");
        
        List<Rate> list = rateService.queryRateBySkill(skill);
        
        return list;
    }
    
}
