package com.game.guild.structs;

import java.util.HashMap;

import com.game.guild.bean.GuildInfo;

/**帮会临时缓存
 * 
 * @author zhangrong
 *
 */
public class GuildTmpInfo {
	//帮会ID
	private long guildid;
	//帮会名字
	private String guildname;
	//旗帜等级
	private int flaglevel;
	//旗帜ID
	private int flagid;
	//职位列表    帮会职位等级 1 帮主 2 副帮主 3 长老 4 堂主 5 舵主
	private HashMap<Integer, GuildTmpMember> postmap = new HashMap<Integer, GuildTmpMember>();

	
	/**
	 * 储存更新临时帮会消息
	 * @param guildInfo
	 * @return
	 */
	public GuildTmpInfo saveGuildTmpInfo(GuildInfo guildInfo){
		this.setGuildid(guildInfo.getGuildId());
		this.setGuildname(guildInfo.getGuildName());
		this.setFlagid(guildInfo.getBannerIcon());
		this.setFlaglevel(guildInfo.getBannerLevel());
		
		GuildTmpMember guildTmpMember= new GuildTmpMember();
		guildTmpMember.setMemberid(guildInfo.getBangZhuid());
		guildTmpMember.setMembername(guildInfo.getBangZhuName());
		this.getPostmap().put(1, guildTmpMember);
		
		GuildTmpMember fuguildTmpMember= new GuildTmpMember();
		fuguildTmpMember.setMemberid(guildInfo.getViceBangZhuid());
		fuguildTmpMember.setMembername(guildInfo.getViceBangZhuName());
		this.getPostmap().put(2, fuguildTmpMember);
		return this;
	}
	
	
	/**
	 * 传入1和2得到帮主和副帮主
	 * @param pos
	 * @return
	 */
	public GuildTmpMember getbangzhu(int pos){
		if (postmap.containsKey(pos)) {
			return postmap.get(pos);
		}
		return new GuildTmpMember() ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public long getGuildid() {
		return guildid;
	}

	public void setGuildid(long guildid) {
		this.guildid = guildid;
	}

	public String getGuildname() {
		return guildname;
	}

	public void setGuildname(String guildname) {
		this.guildname = guildname;
	}

	public int getFlaglevel() {
		return flaglevel;
	}

	public void setFlaglevel(int flaglevel) {
		this.flaglevel = flaglevel;
	}

	public int getFlagid() {
		return flagid;
	}

	public void setFlagid(int flagid) {
		this.flagid = flagid;
	}

	public HashMap<Integer, GuildTmpMember> getPostmap() {
		return postmap;
	}

	public void setPostmap(HashMap<Integer, GuildTmpMember> postmap) {
		this.postmap = postmap;
	}
	
}
