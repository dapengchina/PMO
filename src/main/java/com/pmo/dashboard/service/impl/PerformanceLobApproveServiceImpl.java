package com.pmo.dashboard.service.impl;

import com.pmo.dashboard.dao.PerformanceLobApproveMapper;
import com.pmo.dashboard.entity.*;
import com.pom.dashboard.service.PerformanceLobApproveService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PerformanceLobApproveServiceImpl implements PerformanceLobApproveService {

    @Resource
    PerformanceLobApproveMapper performanceLobApproveMapper;

    @Override
    public int updateState(PerformanceLobQueryCondition performanceLobQueryCondition) {
        return performanceLobApproveMapper.updateResultState(performanceLobQueryCondition);
    }

    @Override
    public int updateResultComments(PerformanceLobQueryCondition performanceLobQueryCondition) {
        return performanceLobApproveMapper.updateResultComments(performanceLobQueryCondition);
    }

    @Override
    public PerformanceLobApprove getPerformanceLobApprove(PerformanceLobQueryCondition performanceLobQueryCondition) {
        PerformanceLobApprove performanceLobApprove = new PerformanceLobApprove();
        List<PerformanceLobApprovePerformances> performanceLobApprovePerformances = performanceLobApproveMapper.getPerformanceLobApprovePerformancesList(performanceLobQueryCondition);
        performanceLobApprove.setPerformanceLobApprovePerformances(performanceLobApprovePerformances);
        if( StringUtils.isEmpty(performanceLobQueryCondition.getBu()) && (null != performanceLobApprovePerformances && !performanceLobApprovePerformances.isEmpty())) {
            performanceLobQueryCondition.setBu(performanceLobApprovePerformances.get(0).getBu());
        }
        PerformanceLobApproveProportions performanceLobApproveProportions = performanceLobApproveMapper.getPerformanceLobApproveProportions(performanceLobQueryCondition);
        performanceLobApproveProportions.setApprovalFeedback(performanceLobApproveMapper.getPerformanceLobApproveFeedback(performanceLobQueryCondition));
        performanceLobApproveProportions.setBu(performanceLobQueryCondition.getBu());
        performanceLobApprove.setPerformanceLobApproveProportions(performanceLobApproveProportions);
        return performanceLobApprove;
    }

    @Override
    public List<PerformanceLobDetails> getPerformanceLobApproveReport(PerformanceLobQueryCondition performanceLobQueryCondition) {
        return performanceLobApproveMapper.getPerformanceLobApproveDetails(performanceLobQueryCondition);
    }

    @Override
    public List<PerformanceLobDetails> getPerformanceLobApproveDetails(PerformanceLobQueryCondition performanceLobQueryCondition) {
        List<PerformanceLobDetails> list = performanceLobApproveMapper.getPerformanceLobApproveDetails(performanceLobQueryCondition);
        for (PerformanceLobDetails b :list) {
            PerformanceLobDetails preB = getPreResult(b.getEhr(), performanceLobQueryCondition.getBu(), b.getYear(), b.getQuarter());
            if (preB != null) {
                b.setPrevious1Quarter(preB.getResult());
                PerformanceLobDetails prePreB = getPreResult(preB.getEhr(), preB.getBu(), preB.getYear(), preB.getQuarter());
                if (prePreB != null) {
                    b.setPrevious2Quarter(prePreB.getResult());
                    PerformanceLobDetails prePrePreB = getPreResult(prePreB.getEhr(), prePreB.getBu(), prePreB.getYear(), prePreB.getQuarter());
                    if (prePrePreB != null) {
                        b.setPrevious3Quarter(prePrePreB.getResult());
                    }
                }
            }
        }
        return list;
    }

    @Override
    public PerformanceLobApproveProportions getPerformanceLobReportProportions(PerformanceLobQueryCondition performanceLobQueryCondition) {
        return performanceLobApproveMapper.getPerformanceLobReportProportions(performanceLobQueryCondition);
    }

    @Override
    public int getPerformanceLobApproveDetailsCount(PerformanceLobQueryCondition performanceLobQueryCondition) {
        return performanceLobApproveMapper.getPerformanceLobApproveDetailsCount(performanceLobQueryCondition);
    }

    private PerformanceLobDetails getPreResult(String ehr, String bu, String year, String quarter) {
        PerformanceLobQueryCondition condition2 = new PerformanceLobQueryCondition();
        condition2.setEhr(ehr);
        // TODO： need to confirm quarter value
        if ("4".equalsIgnoreCase(quarter)) {
            quarter = "3";
        } else if ("3".equalsIgnoreCase(quarter)) {
            quarter = "2";
        } else if ("2".equalsIgnoreCase(quarter)) {
            quarter = "1";
        } else if ("1".equalsIgnoreCase(quarter)) {
            year = "" + (Integer.parseInt(year) -1 );
            quarter = "4";
        }
        condition2.setYear(year);
        condition2.setQuarter(quarter);
        condition2.setBu(bu);
        List<PerformanceLobDetails> preList = performanceLobApproveMapper.getPerformanceLobApproveDetails(condition2);
        for (PerformanceLobDetails preB : preList) {
            return preB;
        }
        return null ;
    }

    @Override
    public List<String> getDuListByBu(PerformanceLobQueryCondition performanceLobQueryCondition) {
        return performanceLobApproveMapper.getDuListByBu(performanceLobQueryCondition);
    }
}
