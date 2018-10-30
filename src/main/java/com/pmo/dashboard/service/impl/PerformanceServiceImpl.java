package com.pmo.dashboard.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.pmo.dashboard.entity.NewTree;
import com.pmo.dashboard.entity.PerformanceManageEvaBean;
import com.pmo.dashboard.entity.UserAuthority;
import com.pom.dashboard.service.PerformanceService;

/**
 * Performance 模块的service
 * @author Yankui
 *
 */

@Service
public class PerformanceServiceImpl implements PerformanceService {
    /** 绩效结果导出文件 **/
    private static String[] groupEvaExcelTitle   = new String[] { "NO.", "EHR ID", "LOB ID", "Name", "Date on-board", "MSA Role", "LOB", "BU", "DU", "Location", "BackBone", "Assessed",
            "Direct Supervisor", "Client Feedback", "Pre-Assessment(Refer Client Feedback)", "Pre-Assessment", "Group Assessment Result", "Group Assessment Result", "Performance Facts(A/C/D)",
            "Reformance Skip", "Remarks", "Last Q", "2 Qs ago", "3 Qs ago" };

    private static String[] groupEvaExcelContent = new String[] { "NO.", "ehr", "lobNo", "name", "hireDate", "position", "serviceLine", "bu", "du", "location", "keymember", "participate", "manager",
            "customerFeedback", "initialEvalution", "pmEvalution", "duEvalution", "duEvaManager", "achievement", "jump", "comments", "previous1Quarter", "previous2Quarter", "previous3Quarter" };

    /**
     * 将数据库的菜单数据，转化为页面显示的menu json数据
     * @param currentPageName 当前的jsp页面
     * @param performanceList 从数据库查询的performance菜单数据
     * @return
     */
    public List<NewTree> transferToMenuList(String currentPageName, List<UserAuthority> performanceList) {
        List<NewTree> topCateList = new ArrayList<NewTree>();
        NewTree twoCate = null;
        for (UserAuthority user : performanceList) {
            //        	System.out.println("user:" + user.getMenuId() + "," + user.getMenuName() + ", " + user.getMenuUrl());
            if (null == user.getMenuParentId() || user.getMenuParentId().equals("")) {
                twoCate = new NewTree(user.getMenuId(), user.getMenuName(), new ArrayList<NewTree>());
                if (user.getMenuUrl() != null) {
                    String[] urls = user.getMenuUrl().split("/");
                    twoCate.setHref(urls[urls.length - 1]);
                }
                twoCate.getState().put("expanded", false);
                twoCate.getState().put("selected", false);
                twoCate.setNodes(recursion(twoCate, performanceList));
                topCateList.add(twoCate);
            }
        }

        updateSelected(topCateList, currentPageName, topCateList);
        removeNullNode(topCateList);
        return topCateList;
    }

    /**
     * 将数据库二级节点递归转换到menu list
     */
    private List<NewTree> recursion(NewTree twoCate, List<UserAuthority> allST) {
        List<NewTree> cateList = new ArrayList<NewTree>();
        for (UserAuthority user : allST) {
            if (twoCate.getId().equals(user.getMenuParentId())) {
                //        			System.out.println("user:" + user.getMenuId() + "," + user.getMenuName() + ", " + user.getMenuUrl());
                NewTree cate = new NewTree(user.getMenuId(), user.getMenuName(), new ArrayList<NewTree>());
                if (user.getMenuUrl() != null) {
                    String[] urls = user.getMenuUrl().split("/");
                    cate.setHref(urls[urls.length - 1]);
                }
                cate.getState().put("expanded", false);
                cate.getState().put("selected", false);
                cate.setParentId(twoCate.getId());
                //            		System.out.println("tree:" + cate.getId() + "," + cate.getText() + ", " + cate.getHref());
                List<NewTree> twoCateList = recursion(cate, allST);
                cate.setNodes(twoCateList);
                cateList.add(cate);
            }
        }
        return cateList;
    }

    /**
     * 删除掉叶子节点的node元素 
     */
    private void removeNullNode(List<NewTree> topCateList) {
        for (NewTree tree : topCateList) {
            if (tree.getNodes().size() == 0) {
                tree.setNodes(null);
            } else {
                removeNullNode(tree.getNodes());
            }
        }
    }

    /**
     * 根据传入的jsp页的名称，在菜单上设置为选中状态
     */
    private void updateSelected(List<NewTree> cateList, String currentPageName, List<NewTree> topCateList) {
        String parentId = "";
        for (NewTree tree : cateList) {
            if (tree.getHref() != null && tree.getHref().equals(currentPageName + ".html")) {
                tree.getState().put("selected", true);
                //            	System.out.println("tree:" + tree.getHref() + "," + tree.getText() + ", " + currentPageName + ", selected=true");
                parentId = tree.getParentId();
                updateExpanded(topCateList, parentId, topCateList);
            } else {
                updateSelected(tree.getNodes(), currentPageName, topCateList);
            }
        }
    }

    /**
     * 将叶子节点的父节点展开
     * @param cateList
     * @param parentId
     * @param topCateList 所有的菜单节点
     */
    private void updateExpanded(List<NewTree> cateList, String parentId, List<NewTree> topCateList) {
        for (NewTree tree : cateList) {
            if (tree.getId().equals(parentId)) {
                tree.getState().put("expanded", true);
                for (int i = 0; i < 5; i++) {
                    updateParentExpanded(topCateList, tree.getParentId(), topCateList);
                }
                break;
            } else {
                updateExpanded(tree.getNodes(), parentId, topCateList);
            }
        }
    }

    /**
     * 将叶子节点的父节点  向上的所以父节点 展开
     * @param cateList
     * @param parentId
     * @param topCateList 所有的菜单节点
     */
    private void updateParentExpanded(List<NewTree> cateList, String parentId, List<NewTree> topCateList) {
        for (NewTree tree : cateList) {
            if (tree.getId().equals(parentId)) {
                tree.getState().put("expanded", true);
                updateParentExpanded(topCateList, tree.getParentId(), topCateList);
                break;
            } else {
                updateParentExpanded(tree.getNodes(), parentId, topCateList);
            }
        }
    }

    @Override
    public void createSheetDetailList(XSSFWorkbook book, String sheetName, List<PerformanceManageEvaBean> data) throws IllegalArgumentException, IllegalAccessException {
        // 创建工作簿
        Sheet sheet_groupEva = book.createSheet(sheetName);
        Row row;
        Cell cell;
        // 创建表头
        row = sheet_groupEva.createRow(0);
        for (int c = 0; c < groupEvaExcelTitle.length; c++) {
            cell = row.createCell(c);// 创建数据各列
            cell.setCellValue(groupEvaExcelTitle[c]);// 赋值
        }
        // 创建表格内容
        for (int r = 0; r < data.size(); r++) {
            row = sheet_groupEva.createRow(r + 1);// 从第二行开始
            cell = row.createCell(0);// 第一列为序号
            cell.setCellValue(r + 1);
            for (int c = 1; c < groupEvaExcelContent.length; c++) {
                cell = row.createCell(c);// 创建数据各列
                Class clazz = data.get(r).getClass();
                Field field;
                try {
                    field = clazz.getDeclaredField(groupEvaExcelContent[c]);
                    field.setAccessible(true);
                    cell.setCellValue((String) field.get(data.get(r)));// 赋值
                } catch (NoSuchFieldException | SecurityException e) {
                    e.printStackTrace();
                    cell.setCellValue("");
                }
            }
        }

    }

}
