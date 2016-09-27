package com.game.db.bean;


public class GameUser {

    private String username;

    private Long userid;

    private Long createtime;

    private Long lastlogintime;
    
    private Integer server;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public Long getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Long lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

	public Integer getServer() {
		return server;
	}

	public void setServer(Integer server) {
		this.server = server;
	}
    
}