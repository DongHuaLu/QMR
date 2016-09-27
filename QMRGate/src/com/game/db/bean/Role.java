package com.game.db.bean;

public class Role {

    private Long roleid;

    private String userid;

    private Integer createServer;

    private Integer locate;

    private Integer level;

    private String name;
    
    private Integer sex;

	private String data;
	
	private Long logintime;
	
    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getCreateServer() {
		return createServer;
	}

	public void setCreateServer(Integer createServer) {
		this.createServer = createServer;
	}

	public Integer getLocate() {
        return locate;
    }

    public void setLocate(Integer locate) {
        this.locate = locate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

	public Long getLogintime() {
		return logintime;
	}

	public void setLogintime(Long logintime) {
		this.logintime = logintime;
	}
}