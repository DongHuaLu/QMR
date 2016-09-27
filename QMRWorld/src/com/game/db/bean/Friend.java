package com.game.db.bean;

public class Friend {

    private Long roleid;

    private String friendlist;

    private String enemylist;

    private String recentcontactpersonlist;

    private String blacklist;

    private String attentionlist;

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

	public String getFriendlist() {
		return friendlist;
	}

	public void setFriendlist(String friendlist) {
		this.friendlist = friendlist;
	}

	public String getEnemylist() {
		return enemylist;
	}

	public void setEnemylist(String enemylist) {
		this.enemylist = enemylist;
	}

	public String getRecentcontactpersonlist() {
		return recentcontactpersonlist;
	}

	public void setRecentcontactpersonlist(String recentcontactpersonlist) {
		this.recentcontactpersonlist = recentcontactpersonlist;
	}

	public String getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}

	public String getAttentionlist() {
		return attentionlist;
	}

	public void setAttentionlist(String attentionlist) {
		this.attentionlist = attentionlist;
	}

}