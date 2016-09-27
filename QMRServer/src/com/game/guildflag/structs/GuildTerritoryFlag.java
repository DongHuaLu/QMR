package com.game.guildflag.structs;

import java.util.List;

import com.game.guild.manager.GuildServerManager;
import com.game.guild.structs.GuildTmpInfo;
import com.game.guildflag.bean.GuildFlagInfo;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.server.impl.WServer;
import com.game.structs.Position;

/**领地占领帮会数据
 * 
 * @author zhangrong
 *
 */
public class GuildTerritoryFlag {
	//帮会ID
	private long guildid;
	//帮会名字
	private String guildname;
	//旗帜等级
	private int flaglevel;
	//旗帜ID
	private int flagid;
	//刷新坐标
	private Position position;
	//帮主ID
	private long guildheadid;
	//帮主名字
	private String guildheadname;
	
	
	public GuildFlagInfo  makeGuildFlagInfo(int mapmodelid){
		GuildFlagInfo guildFlagInfo = new GuildFlagInfo();
		guildFlagInfo.setGuildflaglevel(getFlaglevel());
		guildFlagInfo.setGuildid(getGuildid());
		guildFlagInfo.setGuildname(getGuildname());
		guildFlagInfo.setMapmodelid(mapmodelid);
		guildFlagInfo.setGuildflagid(getFlagid());
		
		GuildTmpInfo guildTmpInfo = GuildServerManager.getInstance().getGuildTmpInfo(getGuildid());	//读取帮会临时数据
	
		//帮主名称和帮会内缓存数据不对则按照帮会数据来
		if(guildTmpInfo !=null && (guildTmpInfo.getbangzhu(1).getMemberid()> 0 && getGuildheadid() != guildTmpInfo.getbangzhu(1).getMemberid())) {
			guildFlagInfo.setGuildheadid(guildTmpInfo.getbangzhu(1).getMemberid());	
			guildFlagInfo.setGuildheadname(guildTmpInfo.getbangzhu(1).getMembername());
			this.setGuildheadid(guildTmpInfo.getbangzhu(1).getMemberid());
			this.setGuildheadname(guildTmpInfo.getbangzhu(1).getMembername());
		}else {
			guildFlagInfo.setGuildheadid(getGuildheadid());
			guildFlagInfo.setGuildheadname(getGuildheadname());
		}
		

		Map map = ManagerPool.mapManager.getMap(WServer.getInstance().getServerId(),1, mapmodelid);
		if (map != null) {
			List<Monster> monsters = ManagerPool.monsterManager.getSameMonster(map,getFlagid());	//根据旗帜ID找怪物
			if (monsters.size() > 0) {
				double hpdou =  ((double)monsters.get(0).getHp() / monsters.get(0).getMaxHp()) * 100;
				int hps = (int)hpdou;
				guildFlagInfo.setHppercentage(hps);
			}
		}
		return guildFlagInfo;
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




	public Position getPosition() {
		return position;
	}




	public void setPosition(Position position) {
		this.position = position;
	}




	public long getGuildheadid() {
		return guildheadid;
	}




	public void setGuildheadid(long guildheadid) {
		this.guildheadid = guildheadid;
	}




	public String getGuildheadname() {
		return guildheadname;
	}




	public void setGuildheadname(String guildheadname) {
		this.guildheadname = guildheadname;
	}

	
	
	
	
	
	
	
	
}
