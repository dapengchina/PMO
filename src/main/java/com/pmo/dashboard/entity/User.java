package com.pmo.dashboard.entity;

/**
 * 用户实体类
 * 
 * @author dilu
 * @version 1.0，2017-9-4 18:23:12
 */
public class User {

	//用户编号
	private String userId;
	
	//用户名
	private String userName;
	
	//昵称
	private String nickname;
	
	//密码
	private String password;
	
	//用户类型
	private String userType;
	
	//所属部门
	private CSDept csDept;
	
	//事业部
	private String bu;
	
	private String csdeptId;
	
	// Felix, 180105, Begin
	private String du;
	// Felix, 180105, End
	
	//用户状态(0:可登录,1:不可登录)
	private String loginStatus;
	
	//邮箱
	private String email;
	
	private String userLocation;
	
	public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getDu() {
		return du;
	}

	public void setDu(String du) {
		this.du = du;
	}

	public CSDept getCsDept() {
		return csDept;
	}

	public void setCsDept(CSDept csDept) {
		this.csDept = csDept;
	}

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getCsdeptId() {
		return csdeptId;
	}

	public void setCsdeptId(String csdeptId) {
		this.csdeptId = csdeptId;
	}

	public String getBu()
    {
        return bu;
    }

    public void setBu(String bu)
    {
        this.bu = bu;
    }

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public User(String userId, String userName, String nickname,
            String password, String userType, String bu, String csDeptId,String loginStatus)
    {
        super();
        this.userId = userId;
        this.userName = userName;
        this.nickname = nickname;
        this.password = password;
        this.userType = userType;
        this.bu = bu;
        this.csdeptId = csDeptId;
        this.loginStatus = loginStatus;
    }
	
	public User(String userId, String userName, String nickname,
            String password, String userType, String bu, String csDeptId,String loginStatus, String email)
    {
        super();
        this.userId = userId;
        this.userName = userName;
        this.nickname = nickname;
        this.password = password;
        this.userType = userType;
        this.bu = bu;
        this.csdeptId = csDeptId;
        this.loginStatus = loginStatus;
        this.email = email;
    }

    public User(String userId, String userName, String nickname,
            String password, String userType, CSDept csDept, String bu,
            String csdeptId)
    {
        super();
        this.userId = userId;
        this.userName = userName;
        this.nickname = nickname;
        this.password = password;
        this.userType = userType;
        this.csDept = csDept;
        this.bu = bu;
        this.csdeptId = csdeptId;
    }

    public User()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	public User(String userId, String userName, String nickname, String userType, String email, String bu, String csdeptId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.nickname = nickname;
		this.userType = userType;
		this.email = email;
		this.bu = bu;
		this.csdeptId = csdeptId;
	}
	
	public boolean isRM() {
		if("5".equals(userType)) {
			return true;
		}
		return false;
	}
	public boolean isSubDept() {
		if("3".equals(userType)) {
			return true;
		}
		return false;
	}
	public boolean isDept() {
		if("1".equals(userType)) {
			return true;
		}
		return false;
	}
	public boolean isAdmin() {
		if("0".equals(userType)) {
			return true;
		}
		return false;
	}
	
}
