package com.game.db.bean;

/**
 * @author ExcelUtil Auto Maker
 *
 * @version 1.0.0
 *
 * Guild Bean
 */
public class GuildBean {

	//帮会id
	private long guildid;
	//帮会名
	private String guildName;
	//帮会数据
	private String guilddata;
	//帮会保存消息数据
	private String guildmsgdata;
	//帮会保存活跃值数据
	private String guildactivevalue;
	//帮会保存计算活跃值时间
	private long guildcalactivevaluetime;
	//帮会战斗力
	private int guildfightpower;

	/**
	 * get 帮会id
	 *
	 * @return
	 */
	public long getGuildid() {
		return guildid;
	}

	/**
	 * set 帮会id
	 */
	public void setGuildid(long guildid) {
		this.guildid = guildid;
	}

	/**
	 * get 帮会名
	 *
	 * @return
	 */
	public String getGuildName() {
		return guildName;
	}

	/**
	 * set 帮会名
	 */
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	/**
	 * get 帮会数据
	 *
	 * @return
	 */
	public String getGuilddata() {
		return guilddata;
	}

	/**
	 * set 帮会数据
	 */
	public void setGuilddata(String guilddata) {
		this.guilddata = guilddata;
	}

	/**
	 * get 帮会保存消息数据
	 *
	 * @return
	 */
	public String getGuildmsgdata() {
		return guildmsgdata;
	}

	/**
	 * set 帮会保存消息数据
	 */
	public void setGuildmsgdata(String guildmsgdata) {
		this.guildmsgdata = guildmsgdata;
	}

	/**
	 * get 帮会保存活跃值数据
	 *
	 * @return
	 */
	public String getGuildactivevalue() {
		return guildactivevalue;
	}

	/**
	 * set 帮会保存活跃值数据
	 */
	public void setGuildactivevalue(String guildactivevalue) {
		this.guildactivevalue = guildactivevalue;
	}

	/**
	 * get 帮会保存计算活跃值时间
	 * 
	 * @return
	 */
	public long getGuildcalactivevaluetime() {
		return guildcalactivevaluetime;
	}

	/**
	 * set 帮会保存计算活跃值时间
	 */
	public void setGuildcalactivevaluetime(long guildcalactivevaluetime) {
		this.guildcalactivevaluetime = guildcalactivevaluetime;
	}

	/**
	 * get 帮会战斗力
	 * 
	 * @return
	 */
	public int getGuildfightpower() {
		return guildfightpower;
	}

	/**
	 * set 帮会战斗力
	 */
	public void setGuildfightpower(int guildfightpower) {
		this.guildfightpower = guildfightpower;
	}
}