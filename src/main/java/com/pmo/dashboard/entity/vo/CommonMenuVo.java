package com.pmo.dashboard.entity.vo;

import java.util.List;

public class CommonMenuVo {
	
	private String id;
	
	private String text;
	   
	private String icon;
	   
	private String selectedIcon;
	   
	private String color;
	   
	private String href;
	   
	private List<CommonMenuVo> nodes;
	   
	   

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<CommonMenuVo> getNodes() {
		return nodes;
	}

	public void setNodes(List<CommonMenuVo> nodes) {
		this.nodes = nodes;
	}

}
