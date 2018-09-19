package com.pmo.dashboard.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * left菜单实体类，会转换成json供treeview使用
 * @author Yankui
 *
 */
public class NewTree {

	private String id;
    private String text;
    private String href;
    private String parentId;
	private Map<String, Boolean> state = new HashMap<>();  
    private List<NewTree> nodes = new ArrayList<NewTree>();
    
    public NewTree(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	public NewTree(String id, String text, List<NewTree> nodes) {
		super();
		this.id = id;
		this.text = text;
		this.nodes = nodes;
	}
    public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<NewTree> getNodes() {
		return nodes;
	}
	public void setNodes(List<NewTree> nodes) {
		this.nodes = nodes;
	}

	public Map<String, Boolean> getState() {
		return state;
	}
	public void setState(Map<String, Boolean> state) {
		this.state = state;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
    
}
