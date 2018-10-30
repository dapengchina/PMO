package com.pmo.dashboard.entity;

public class UserAuthority {
	
	    private String menuId;	
	
		private String userId;				
		
		private String menuParentId;		
		
		private String menuName;
		
        private String menuUrl;		
		
		private String menuStatus;

		
		public String getMenuId() {
			return menuId;
		}

		public void setMenuId(String menuId) {
			this.menuId = menuId;
		}
		
		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}		

		public String getMenuParentId() {
			return menuParentId;
		}

		public void setMenuParentId(String menuParentId) {
			this.menuParentId = menuParentId;
		}

		public String getMenuName() {
			return menuName;
		}

		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}

		public String getMenuUrl() {
			return menuUrl;
		}

		public void setMenuUrl(String menuUrl) {
			this.menuUrl = menuUrl;
		}

		public String getMenuStatus() {
			return menuStatus;
		}

		public void setMenuStatus(String menuStatus) {
			this.menuStatus = menuStatus;
		}		
		

		
}
