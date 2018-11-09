package com.pom.dashboard.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pmo.dashboard.entity.NewTree;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.UserAuthority;

public interface PerformanceService {
    List<NewTree> transferToMenuList(String currentPageUrl, List<UserAuthority> performanceList);

    /**
     * 创建绩效结果列表excel-sheet
     * @author: xuexuan
     * 2018年10月25日 上午10:05:22
     * @param book
     * @param data 
     * void
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    public void createSheetDetailList(XSSFWorkbook book, String sheetName, List<PerformanceManageEvaBean> data) throws IllegalArgumentException, IllegalAccessException;
}
