package com.pmo.dashboard.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.dao.PerformanceEmpPBCMapper;
import com.pmo.dashboard.dao.PerformanceResultMapper;
import com.pmo.dashboard.entity.PerformanceEmpHistoryBean;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.PerformanceManageResultHistoryBean;
import com.pmo.dashboard.entity.PerformanceQueryCondition;
import com.pmo.dashboard.entity.vo.PresultVo;
import com.pmo.dashboard.util.Utils;
import com.pom.dashboard.service.PerformanceManageEvaService;

@Service
public class PerformanceManageEvaServiceImpl implements PerformanceManageEvaService {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceManageEvaServiceImpl.class);

    @Resource
    PerformanceResultMapper     mapper;

    @Resource
    PerformanceEmpPBCMapper     pbcMapper;

    @Override
    public List<PerformanceManageEvaBean> queryManageEvaFirstDetailWithAchieveList(PerformanceQueryCondition condition) {
        Calendar c = Calendar.getInstance();
        condition.setStartYear(c.get(Calendar.YEAR) + "");
        condition.setStartQuarter("Q" + PerformanceEmpHistoryServiceImpl.getSeason());

        List<PerformanceManageEvaBean> list = mapper.queryManageEvaSecondQueryDUList(condition);
        for (PerformanceManageEvaBean b : list) {
            String year = b.getYear();
            String quarter = b.getQuarter();
            PerformanceManageEvaBean preB = getPreResult(condition.geteHr(), year, quarter);
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
        return list;

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
    public List<PerformanceManageEvaBean> queryManageEvaFinalList(PerformanceQueryCondition condition) {
        Calendar c = Calendar.getInstance();
        condition.setStartYear(c.get(Calendar.YEAR) + "");
        condition.setStartQuarter("Q" + PerformanceEmpHistoryServiceImpl.getSeason());

        List<PerformanceManageEvaBean> list = mapper.queryManageEvaSecondQueryDUList(condition);
        for (PerformanceManageEvaBean b : list) {
            String year = b.getYear();
            String quarter = b.getQuarter();
            PerformanceManageEvaBean preB = getPreResult(condition.geteHr(), year, quarter);
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
        return list;

    }

    @Override
    public List<PerformanceManageEvaBean> queryManageEvaFirstDetailList(PerformanceQueryCondition condition) {
        Calendar c = Calendar.getInstance();
        condition.setStartYear(c.get(Calendar.YEAR) + "");
        condition.setStartQuarter("Q" + PerformanceEmpHistoryServiceImpl.getSeason());
        List<PerformanceManageEvaBean> list = mapper.queryManageEvaSecondQueryDUList(condition);
        return list;
        //		List<PerformanceManageEvaBean> list = new ArrayList<>();
        //		
        //		for(int i=0; i<5; i++) {
        //			PerformanceManageEvaBean bean = new PerformanceManageEvaBean();
        //			bean.setEhr("0090127655");
        //			bean.setLobNo("45678");
        //			bean.setName("William");
        //			bean.setHireDate("2017-10-11 ");
        //			bean.setPosition("Senior Developer ");
        //			bean.setServiceLine("HSBC");
        //			bean.setBu("xxx 事业部");
        //			bean.setDu("xxx 交付部 ");
        //			bean.setLocation("Xian");
        //			bean.setKeymember("是");
        //			bean.setParticipate("是");
        //			bean.setManager("XXX Rm");
        //			bean.setCustomerFeedback("该员工平时工作仔细认真，负责。不但执行力强，而且工作配合度也好，有积极向上的工作态度，能主动协调其他同事工作，并且能及时完成上级领导安排的其他工作");
        //			bean.setInitialEvalution("B+");
        //			bean.setPmEvalution("");
        //			bean.setDuEvalution("");
        //			bean.setDuEvaManager("XXX交付部经理 ");
        //			bean.setAchievement("");
        //			bean.setJump("");
        //			bean.setComments("");
        //			bean.setPrevious1Quarter("B+");
        //			bean.setPrevious2Quarter("B+");
        //			bean.setPrevious3Quarter("A");
        //
        //			list.add(bean);
        //		}
        //		for(int i=0; i<4; i++) {
        //			PerformanceManageEvaBean bean = new PerformanceManageEvaBean();
        //			bean.setEhr("0090127666");
        //			bean.setLobNo("45678");
        //			bean.setName("William");
        //			bean.setHireDate("2017-10-11 ");
        //			bean.setPosition("Senior Developer ");
        //			bean.setServiceLine("HSBC");
        //			bean.setBu("xxx 事业部");
        //			bean.setDu("xxx 交付部 ");
        //			bean.setLocation("Xian");
        //			bean.setKeymember("是");
        //			bean.setParticipate("是");
        //			bean.setManager("XXX Rm");
        //			bean.setCustomerFeedback("该员工平时工作仔细认真，负责。不但执行力强，而且工作配合度也好，有积极向上的工作态度，能主动协调其他同事工作，并且能及时完成上级领导安排的其他工作");
        //			bean.setInitialEvalution("B");
        //			bean.setPmEvalution("");
        //			bean.setDuEvalution("");
        //			bean.setDuEvaManager("XXX交付部经理 ");
        //			bean.setAchievement("");
        //			bean.setJump("");
        //			bean.setComments("");
        //			bean.setPrevious1Quarter("B+");
        //			bean.setPrevious2Quarter("B+");
        //			bean.setPrevious3Quarter("A");
        //
        //			list.add(bean);
        //		}
        //		return list;
    }

    @Override
    public List<PerformanceEmpHistoryBean> queryManageEvaSecondDUList(PerformanceQueryCondition condition) {
        List<PerformanceEmpHistoryBean> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            PerformanceEmpHistoryBean bean = new PerformanceEmpHistoryBean();
            bean.setRM("Abigal ");
            bean.setYear("2018");
            bean.setQuarter("Q2");
            bean.setStatus("待审批");
            list.add(bean);
        }

        return list;
    }

    @Override
    public List<PerformanceEmpHistoryBean> queryManageEvaSecondBUList(PerformanceQueryCondition condition) {
        List<PerformanceEmpHistoryBean> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            PerformanceEmpHistoryBean bean = new PerformanceEmpHistoryBean();
            bean.setDU("移动");
            bean.setYear("2018");
            bean.setQuarter("Q2");
            bean.setStatus("待审批");
            list.add(bean);
        }

        return list;
    }

    @Override
    public List<PerformanceManageEvaBean> queryManageEvaSecondQueryList(PerformanceQueryCondition condition) {
        Calendar c = Calendar.getInstance();
        condition.setStartYear(c.get(Calendar.YEAR) + "");
        condition.setStartQuarter("Q" + PerformanceEmpHistoryServiceImpl.getSeason());
        List<PerformanceManageEvaBean> list = queryManageEvaFirstDetailWithAchieveList(condition);
        return list;
    }

    @Override
    public List<PerformanceManageEvaBean> queryManageEvaSecondQueryDUList(PerformanceQueryCondition condition) {
        Calendar c = Calendar.getInstance();
        condition.setStartYear(c.get(Calendar.YEAR) + "");
        condition.setStartQuarter("Q" + PerformanceEmpHistoryServiceImpl.getSeason());
        List<PerformanceManageEvaBean> list = mapper.queryManageEvaSecondQueryDUList(condition);

        return list;
    }

    @Override
    public List<PerformanceManageResultHistoryBean> queryManageResultHistoryQueryList(PerformanceQueryCondition condition) {
        List<PerformanceManageEvaBean> list0 = mapper.queryManageEvaSecondQueryDUList(condition);

        List<PerformanceManageResultHistoryBean> list = new ArrayList<>();
        for (PerformanceManageEvaBean b : list0) {
            PerformanceManageResultHistoryBean bean = new PerformanceManageResultHistoryBean();
            bean.setEhr(b.getEhr());
            bean.setEmpName(b.getName());
            bean.setDu(b.getDu());
            bean.setBu(b.getBu());
            bean.setRm(b.getRm());
            bean.setResult(b.getResult());
            bean.setComments(b.getResultComments());

            String year = b.getYear();
            String quarter = b.getQuarter();
            String start = "";
            String end = "";
            if (quarter != null && quarter.equalsIgnoreCase("1")) {
                start = "01/01/" + year;
                end = "31/03/" + year;
            } else if (quarter != null && quarter.equalsIgnoreCase("2")) {
                start = "01/04/" + year;
                end = "30/06/" + year;
            } else if (quarter != null && quarter.equalsIgnoreCase("3")) {
                start = "01/07/" + year;
                end = "30/09/" + year;
            } else if (quarter != null && quarter.equalsIgnoreCase("4")) {
                start = "01/10/" + year;
                end = "31/12/" + year;
            }
            bean.setBeginDate(start);
            bean.setEndDate(end);

            list.add(bean);
        }

        return list;
    }

    private List<Map<String, Object>> groupStatByResult(String bu, String du, String rm, String finalize) {
        Calendar c = Calendar.getInstance();
        String startYear = c.get(Calendar.YEAR) + "";
        //String startQuarter = "Q" + PerformanceEmpHistoryServiceImpl.getSeason();
        String startQuarter = PerformanceEmpHistoryServiceImpl.getSeason()+"";
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("startYear", startYear);
        filter.put("startQuarter", startQuarter);
        filter.put("bu", bu);
        filter.put("du", du);
        filter.put("rm", rm);
        filter.put("finalize", finalize);// 已定稿
        List<Map<String, Object>> list = mapper.groupStatByResult(filter);
        return list;
    }

    @Override
    public List<Map<String, Object>> groupStatByResultBU(String bu) {
        return groupStatByResult(bu, null, null, null);
    }

    @Override
    public List<Map<String, Object>> groupStatByResultDU(String du) {
        return groupStatByResult(null, du, null, null);
    }

    @Override
    public List<Map<String, Object>> groupStatByResultRM(String rm) {
        return groupStatByResult(null, null, rm, null);
    }

    @Override
    public List<Map<String, Object>> groupStatByResultFinalize() {
        return groupStatByResult(null, null, null, "True");
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

    @Override
    public List<PerformanceManageEvaBean> processingResultListRM(String rm) {
        return processingResultList(null, null, null, null, rm);
    }

    @Override
    public List<PerformanceManageEvaBean> processingResultListBU(String bu) {
        return processingResultList(bu, null, null, null, null);
    }

    @Override
    public List<PerformanceManageEvaBean> processingResultListDU(String du) {
        return processingResultList(null, du, null, null, null);
    }

    @Override
    public List<PerformanceManageEvaBean> processingResultList(String bu, String du, String eHr, String staffName, String rm) {
        Map<String, Object> filter = new HashMap<String, Object>();
        Calendar c = Calendar.getInstance();
        String startYear = c.get(Calendar.YEAR) + "";
        //String startQuarter = "Q" + PerformanceEmpHistoryServiceImpl.getSeason();
        String startQuarter = PerformanceEmpHistoryServiceImpl.getSeason()+"";
        filter.put("startYear", startYear);
        filter.put("startQuarter", startQuarter);// 当年-当季
        filter.put("bu", bu);
        filter.put("du", du);
        filter.put("eHr", eHr);
        filter.put("staffName", staffName);
        filter.put("rm", rm);
        List<PerformanceManageEvaBean> list = mapper.filterQuery(filter);
        getPreviousResult(list);
        return list;
    }

    @Override
    public List<PerformanceManageEvaBean> finalizeResultList() {
        Map<String, Object> filter = new HashMap<String, Object>();
        Calendar c = Calendar.getInstance();
        String startYear = c.get(Calendar.YEAR) + "";
        //String startQuarter = "Q" + PerformanceEmpHistoryServiceImpl.getSeason();
        String startQuarter = PerformanceEmpHistoryServiceImpl.getSeason()+"";
        filter.put("startYear", startYear);
        filter.put("startQuarter", startQuarter);// 当年-当季
        filter.put("finalize", "True");// 已定稿
        List<PerformanceManageEvaBean> list = mapper.filterQuery(filter);
        getPreviousResult(list);
        return list;
    }

    @Override
    public List<PerformanceManageEvaBean> finalizeResultList(PerformanceQueryCondition condition) {
        Map<String, Object> filter = new HashMap<String, Object>();
        filter.put("eHr", condition.geteHr());
        filter.put("staffName", condition.getStaffName());
        filter.put("bu", condition.getBu());
        filter.put("du", condition.getDu());
        filter.put("startYear", condition.getStartYear());
        filter.put("startQuarter", condition.getStartQuarter());
        filter.put("endYear", condition.getEndYear());
        filter.put("endQuarter", condition.getEndQuarter());
        filter.put("finalize", "True");
        List<PerformanceManageEvaBean> list = mapper.filterQuery(filter);
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

	@Override
    public List<Map<String, Object>> approvalList() {
        PerformanceQueryCondition condition = new PerformanceQueryCondition();
        Calendar c = Calendar.getInstance();
        condition.setStartYear(c.get(Calendar.YEAR) + "");
        //condition.setStartQuarter("Q" + PerformanceEmpHistoryServiceImpl.getSeason());
        condition.setStartQuarter("" + PerformanceEmpHistoryServiceImpl.getSeason());
        return mapper.approvalList(condition);
    }

    @Override
    public PerformanceManageEvaBean queryResultCommentsByBU(String bu) {
        Calendar c = Calendar.getInstance();
        String startYear = c.get(Calendar.YEAR) + "";
        //String startQuarter = "Q" + PerformanceEmpHistoryServiceImpl.getSeason();
        String startQuarter = PerformanceEmpHistoryServiceImpl.getSeason()+"";
        return mapper.queryResultComments(bu, null, startYear, startQuarter);
    }

    @Override
    public PerformanceManageEvaBean queryResultCommentsByDU(String du) {
        Calendar c = Calendar.getInstance();
        String startYear = c.get(Calendar.YEAR) + "";
        //String startQuarter = "Q" + PerformanceEmpHistoryServiceImpl.getSeason();
        String startQuarter = PerformanceEmpHistoryServiceImpl.getSeason()+"";
        return mapper.queryResultComments(null, du, startYear, startQuarter);
    }

    @Override
    public void updateComments(String comments) {
        mapper.updateComments(comments, null, null);

    }

    @Override
    public void updateCommentsByBU(String comments, String bu) {
        mapper.updateComments(comments, bu, null);
    }

    @Override
    public void updateCommentsByDU(String comments, String du) {
        mapper.updateComments(comments, null, du);
    }

    @Override
    public void updateStateByBU(String bu, String state) {
        mapper.updateState(bu, null, null, null, state);
    }

    @Override
    public void updateStateByDU(String du, String state) {
        mapper.updateState(null, du, null, null, state);
    }

    @Override
    public void updateStateByRM(String rm, String state) {
        mapper.updateState(null, null, rm, null, state);
    }

    @Override
    public void updateStateByIds(List<String> list, String state) {
        mapper.updateState(null, null, null, list, state);
    }

    @Override
    public List<Map<String, Object>> listGroupByDU(String bu) {
        Calendar c = Calendar.getInstance();
        String startYear = c.get(Calendar.YEAR) + "";
        //String startQuarter = "Q" + PerformanceEmpHistoryServiceImpl.getSeason();
        String startQuarter = PerformanceEmpHistoryServiceImpl.getSeason()+"";
        return mapper.listGroupByDU(bu, startYear, startQuarter);
    }

    @Override
    public List<Map<String, Object>> listGroupByRM(String csSubDeptName) {
        Calendar c = Calendar.getInstance();
        String startYear = c.get(Calendar.YEAR) + "";
        //String startQuarter = "Q" + PerformanceEmpHistoryServiceImpl.getSeason();
        String startQuarter = PerformanceEmpHistoryServiceImpl.getSeason()+"";
        return mapper.listGroupByRM(csSubDeptName, startYear, startQuarter);
    }

    @Override
    public void updatePreAssessmentResult(String manager,String groupresult,String result,String preAssessment,String state, String id) {
        //mapper.preAssessment(preAssessment,state, id);
        mapper.preAssessment(manager, groupresult, result, preAssessment, state, id);
    }

    @Override
    public Map<String, Object> queryGoalById(String id) {
        return pbcMapper.queryById(id);
    }

    @Override
    public void updateByEmployeeId(String employeeId, String state, String remark) {
        pbcMapper.updateByEmployeeId(employeeId, state, remark);
    }

    @Override
    public Map<String, String> queryEmployeeIdByResultId(String resultId) {
        return mapper.queryEmployeeIdByResultId(resultId);
    }

	@Override
	public int update(PresultVo pv) {
		return mapper.update(pv);
	}

}
