package com.pmo.dashboard.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public final static int                 ZERO                       = 0;
    public final static int                 ONE                        = 1;
    public final static int                 TEN                        = 10;
    public final static String              PATH                       = "D:/Excel/";
    public final static String              RESUME_PATH                = "D:/Resume/";
    public final static String              TEMPLATE_PATH              = "D:/template/";
    public final static int                 PAGE_DATA_COUNT            = 10;
    /* public final static int USER_PASSWORD = 123;*/
    public static final String              IS_MAIN_ABILITY            = "0";                                                                  //是主能力
    public static final String              NOT_MAIN_ABILITY           = "1";                                                                  //不是主能力
    public static final String              IS_OFFICIAL_ACCREDITATION  = "0";                                                                  //是官方认证
    public static final String              NOT_OFFICIAL_ACCREDITATION = "1";                                                                  //不是官方认证

    // export offlineoper title
    public final static String[]            CELLTITLES                 = { "月份", "业务线", "事业部", "执行交付部", "项目类型", "员工E-HR编码", "员工姓名", "项目编号", "项目名称", "工作地", "技能", "级别", "币种", "当月汇率", "员工单价（h）-原币种",
            "月标准工时", "实际工时", "无效工时", "加班费工时", "调休工时", "调整上月工时", "实际工时收入-收入1", "无效工时收入", "加班费工时收入-收入2", "调休工时收入-收入3", "调整上月工时收入-收入4", "差旅收入-收入5", "付费设备收入-收入6", "分包收入-收入7", "当月收入合计-原币种", "当月收入合计-RMB",
            "当月有效收入", "有效NR-与月滚一致", "当月有效人力", "当月无效人力", "RM", "备注"    };

    // export offline summary types
    public final static String[]            SUMMARY_TYPES              = { "实际工时收入-收入1", "加班费工时收入-收入2", "调休工时收入-收入3", "调整上月工时收入-收入4", "差旅收入-收入5", "付费设备收入-收入6", "分包收入-收入7", "无效收入", "billable人力",
            "unbillable人力", "理论收入合计", "有效收入合计", "月滚NR合计", "有效人天产能"    };
    // export offline summary remark
    public final static String[]            SUMMARY_REMARKS            = { "∑（每个人有效工时*单价）", "∑（每个人加班费工时*单价）", "∑（每个人调休工时*单价）", "轧差有效付费的收入", "非人员收入（付费差旅）", "非人员收入（客户付费采购类）", "真实分包收入",
            "∑（每个人非付费工时*单价）", "有效工时人力", "非付费工时人力", "∑（收入1+2+3+4+5+6+无效收入）", "∑（收入1+2+3+4+5+6）", "NR=有效收入合计/1.06", "产能=收入1/billable人力1/当月工作日" };
    // export offline summary method
    public final static String[]            SUMMARY_METHODS            = { "getIfaw", "getInfOt", "getInfPt", "getInfAd", "getInfTravel", "getInfEquipment", "getInfSub", "getInvalid",
            "getEffectiveSt", "getInvalidSt", "getThinkTotalIncome", "getRealTotalIncome", "getEffectiveNr", "getEffectiveHuman" };

    public final static Map<String, String> APPROVAL_STATE             = new HashMap<>();

    public final static String              APPROVAL_DU                = "2";
    static {
        APPROVAL_STATE.put("0", "待RM审批");
        APPROVAL_STATE.put("1", "RM审批不通过");
        APPROVAL_STATE.put("2", "待交付部经理审批");
        APPROVAL_STATE.put("3", "交付部经理审批不通过");
        APPROVAL_STATE.put("4", "待事业部经理审批");
        APPROVAL_STATE.put("5", "事业部经理审批不通过");
        APPROVAL_STATE.put("6", "待LOB总审批");
        APPROVAL_STATE.put("7", "LOB总审批不通过");
        APPROVAL_STATE.put("8", "LOB总审批通过");
    }
}
