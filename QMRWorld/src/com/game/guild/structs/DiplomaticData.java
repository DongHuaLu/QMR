package com.game.guild.structs;

import com.game.guild.bean.DiplomaticInfo;
import com.game.object.GameObject;

/**
 * 外交关系
 *
 * @author 杨洪岚
 */
public class DiplomaticData extends GameObject {
	
	private static final long serialVersionUID = 8144655445698744872L;
	
	//帮会id
	private long guildId;
	//外交建立时间
	private int diplomaticTime;

	public int getDiplomaticTime() {
		return diplomaticTime;
	}

	public void setDiplomaticTime(int diplomaticTime) {
		this.diplomaticTime = diplomaticTime;
	}

	public long getGuildId() {
		return guildId;
	}

	public void setGuildId(long guildId) {
		this.guildId = guildId;
	}
	
	public void toData(DiplomaticInfo diplomaticInfo){
		setGuildId(diplomaticInfo.getGuildId());
		setDiplomaticTime(diplomaticInfo.getDiplomaticTime());
	}
	
	public DiplomaticInfo toInfo(){
		DiplomaticInfo diplomaticInfo = new DiplomaticInfo();
		diplomaticInfo.setGuildId(getGuildId());
		diplomaticInfo.setDiplomaticTime(getDiplomaticTime());
		return diplomaticInfo;
	}
}
