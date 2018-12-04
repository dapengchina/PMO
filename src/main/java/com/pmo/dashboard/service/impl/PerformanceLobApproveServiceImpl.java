package com.pmo.dashboard.service.impl;

import com.pmo.dashboard.dao.PerformanceLobApproveMapper;
import com.pmo.dashboard.dao.PerformanceResultMapper;
import com.pmo.dashboard.entity.*;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.PerformanceLobApproveService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PerformanceLobApproveServiceImpl implements PerformanceLobApproveService {

    @Resource
    PerformanceLobApproveMapper performanceLobApproveMapper;
    
    @Resource
    PerformanceResultMapper     mapper;

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
                b.setPerformanceSkip(new Utils().skip(b.getResult(), preB.getResult()));
            } else {
                b.setPerformanceSkip(new Utils().skip(b.getResult(), null));
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

	@Override
	public List<PerformanceManageEvaBean> hrReport() {
		Map<String, Object> filter = new HashMap<String, Object>();
        //Calendar c = Calendar.getInstance();
        //String startYear = c.get(Calendar.YEAR) + "";
        //String startQuarter = "Q" + PerformanceEmpHistoryServiceImpl.getSeason();
        //String startQuarter = PerformanceEmpHistoryServiceImpl.getSeason()+"";
        //filter.put("startYear", startYear);
        //filter.put("startQuarter", startQuarter);// 当年-当季
        filter.put("finalize", "True");// 已定稿
        List<PerformanceManageEvaBean> list = mapper.filterQuery(filter);
        getPreviousResult(list);
        return list;
	}
	
	private void getPreviousResult(List<PerformanceManageEvaBean> list) {
        for (PerformanceManageEvaBean b : list) {
            String year = b.getYear();
            String quarter = b.getQuarter();
            PerformanceManageEvaBean preB = getPreResult(b.getEhr(), year, quarter);
            if (preB != null) {
                b.setPrevious1Quarter(preB.getResult());
                PerformanceManageEvaBean prePreB = getPreResult(preB.getEhr(), preB.getYear(), preB.getQuarter());
                if (prePreB != null) {
                    b.setPrevious2Quarter(prePreB.getResult());
                    PerformanceManageEvaBean prePrePreB = getPreResult(prePreB.getEhr(), prePreB.getYear(), prePreB.getQuarter());
                    if (prePrePreB != null) {
                        b.setPrevious3Quarter(prePrePreB.getResult());
                    }

                }
	            b.setJump(new Utils().skip(b.getResult(), preB.getResult()));
	        } else {
	            b.setJump(new Utils().skip(b.getResult(), null));
	        }
        }
    }
	
	
	private PerformanceManageEvaBean getPreResult(String ehr, String year, String quarter) {
        PerformanceQueryCondition condition2 = new PerformanceQueryCondition();
        condition2.seteHr(ehr);
        if ("4".equalsIgnoreCase(quarter)) {
            quarter = "3";
        } else if ("3".equalsIgnoreCase(quarter)) {
            quarter = "2";
        } else if ("2".equalsIgnoreCase(quarter)) {
            quarter = "1";
        } else if ("1".equalsIgnoreCase(quarter)) {
            year = "" + (Integer.parseInt(year) - 1);
            quarter = "4";
        }
        condition2.setStartYear(year);
        condition2.setStartQuarter(quarter);
        List<PerformanceManageEvaBean> preList = mapper.queryManageEvaPreviousResult(condition2);
        for (PerformanceManageEvaBean preB : preList) {
            return preB;
        }
        return null;
    }

	@Override
	public List<Map<String, Object>> groupStatByResultFinalize() {
		return groupStatByResult(null, null, null, "True");
	}
	
	private List<Map<String, Object>> groupStatByResult(String bu, String du, String rm, String finalize) {
        //Calendar c = Calendar.getInstance();
        //String startYear = c.get(Calendar.YEAR) + "";
        //String startQuarter = "Q" + PerformanceEmpHistoryServiceImpl.getSeason();
        //String startQuarter = PerformanceEmpHistoryServiceImpl.getSeason()+"";
        Map<String, Object> filter = new HashMap<String, Object>();
        //filter.put("startYear", startYear);
        //filter.put("startQuarter", startQuarter);
        //filter.put("bu", bu);
        //filter.put("du", du);
        //filter.put("rm", rm);
        filter.put("finalize", finalize);// 已定稿
        List<Map<String, Object>> list = mapper.groupStatByResult(filter);
        return list;
    }

	@Override
	public Map<String, Object> percentage(List<Map<String, Object>> list) {
		Map<String, Object> rtn = new HashMap<String, Object>();
        if (list == null || list.size() == 0)
            return rtn;
        // 计算总数
        int sum = 0;
        for (Map<String, Object> map : list) {
            if (map.get("result") == null || "".equals(map.get("result"))) {// 空值不统计
                continue;
            }
            sum += Integer.parseInt(map.get("count") + ""); 
        }
        // 总数为0直接返回
        if (sum == 0) {
            rtn.put("sum", sum);
            return rtn;
        }
        // 计算百分比
        NumberFormat nf = NumberFormat.getPercentInstance();
        String level = "";
        int count = 0;
        for (Map<String, Object> map : list) {
            if (map.get("result") == null || "".equals(map.get("result"))) {
                continue;
            }
            level = ((String) map.get("result")).toUpperCase();
            count = Integer.parseInt(map.get("count") + "");
            rtn.put(level, count);
            rtn.put("percent" + level, nf.format((float) count / sum));
        }
        rtn.put("sum", sum);
        return rtn;
	}
}
