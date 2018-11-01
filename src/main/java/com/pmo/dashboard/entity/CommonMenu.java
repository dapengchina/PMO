package com.pmo.dashboard.entity;

import java.util.List;

public class CommonMenu {
	
	
    private String id;

    private String parentid;

    private String menuid;

    private String menuName;

    private String menuUrl;

    private String menuStatus;
    
    private List<CommonMenu> nodes;
    
    
    /**
     * 表外字段
     * @return
     */
    
    

    
    public List<CommonMenu> getNodes() {
		return nodes;
	}

	public void setNodes(List<CommonMenu> nodes) {
		this.nodes = nodes;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus == null ? null : menuStatus.trim();
    }
}