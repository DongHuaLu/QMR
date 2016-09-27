package com.game.guild.structs;

import java.util.ArrayList;
import java.util.List;

import com.game.data.bean.Q_guildbannerBean;

/**
 *
 * @author 杨洪岚 公会旗帜配置类
 */
public class GuildBanner {

	//帮旗等级
	private int guildbannerlv;
	//对应的怪物表编号（用来获取帮旗造型编号）
	private List<Integer> monsterList = new ArrayList<Integer>();
	//旗帜属性（）
	private String shuxing;
	//改名消耗帮贡仓库 游戏币
	private int changenamegold;
	//改名消耗帮贡仓库 青龙令
	private int changenamedragon;
	//改名消耗帮贡仓库 白虎令
	private int changenamewhitetiger;
	//改名消耗帮贡仓库 朱雀令
	private int changenamesuzaku;
	//改名消耗帮贡仓库 玄武令
	private int changenamebasaltic;
	//更换造型消耗帮贡仓库 游戏币
	private int changicongold;
	//更换造型消耗帮贡仓库 青龙令
	private int changicondragon;
	//更换造型消耗帮贡仓库 白虎令
	private int changiconwhitetiger;
	//更换造型消耗帮贡仓库 朱雀令
	private int changiconsuzaku;
	//更换造型消耗帮贡仓库 玄武令
	private int changiconbasaltic;
	//帮旗升级消耗帮贡仓库 游戏币
	private int levelupgold;
	//帮旗升级消耗帮贡仓库 青龙令
	private int levelupdragon;
	//帮旗升级消耗帮贡仓库 白虎令
	private int levelupwhitetiger;
	//帮旗升级消耗帮贡仓库 朱雀令
	private int levelupsuzaku;
	//帮旗升级消耗帮贡仓库 玄武令
	private int levelupbasaltic;
	//帮旗升级成功几率（万分比，本处填分子）
	private int successcof;
	//帮旗加成人物属性BUFF编号（对应技能效果数据库）
	private int buffid;
	//增加打怪经验收益比例（万分比，本处填分子）
	private int expcof;
	//增加打坐收益比例（万分比，本处填分子）
	private int dazuocof;

	public int getBuffid() {
		return buffid;
	}

	public void setBuffid(int buffid) {
		this.buffid = buffid;
	}

	public int getChangenamebasaltic() {
		return changenamebasaltic;
	}

	public void setChangenamebasaltic(int changenamebasaltic) {
		this.changenamebasaltic = changenamebasaltic;
	}

	public int getChangenamedragon() {
		return changenamedragon;
	}

	public void setChangenamedragon(int changenamedragon) {
		this.changenamedragon = changenamedragon;
	}

	public int getChangenamegold() {
		return changenamegold;
	}

	public void setChangenamegold(int changenamegold) {
		this.changenamegold = changenamegold;
	}

	public int getChangenamesuzaku() {
		return changenamesuzaku;
	}

	public void setChangenamesuzaku(int changenamesuzaku) {
		this.changenamesuzaku = changenamesuzaku;
	}

	public int getChangenamewhitetiger() {
		return changenamewhitetiger;
	}

	public void setChangenamewhitetiger(int changenamewhitetiger) {
		this.changenamewhitetiger = changenamewhitetiger;
	}

	public int getChangiconbasaltic() {
		return changiconbasaltic;
	}

	public void setChangiconbasaltic(int changiconbasaltic) {
		this.changiconbasaltic = changiconbasaltic;
	}

	public int getChangicondragon() {
		return changicondragon;
	}

	public void setChangicondragon(int changicondragon) {
		this.changicondragon = changicondragon;
	}

	public int getChangicongold() {
		return changicongold;
	}

	public void setChangicongold(int changicongold) {
		this.changicongold = changicongold;
	}

	public int getChangiconsuzaku() {
		return changiconsuzaku;
	}

	public void setChangiconsuzaku(int changiconsuzaku) {
		this.changiconsuzaku = changiconsuzaku;
	}

	public int getChangiconwhitetiger() {
		return changiconwhitetiger;
	}

	public void setChangiconwhitetiger(int changiconwhitetiger) {
		this.changiconwhitetiger = changiconwhitetiger;
	}

	public int getDazuocof() {
		return dazuocof;
	}

	public void setDazuocof(int dazuocof) {
		this.dazuocof = dazuocof;
	}

	public int getExpcof() {
		return expcof;
	}

	public void setExpcof(int expcof) {
		this.expcof = expcof;
	}

	public int getGuildbannerlv() {
		return guildbannerlv;
	}

	public void setGuildbannerlv(int guildbannerlv) {
		this.guildbannerlv = guildbannerlv;
	}

	public int getLevelupbasaltic() {
		return levelupbasaltic;
	}

	public void setLevelupbasaltic(int levelupbasaltic) {
		this.levelupbasaltic = levelupbasaltic;
	}

	public int getLevelupdragon() {
		return levelupdragon;
	}

	public void setLevelupdragon(int levelupdragon) {
		this.levelupdragon = levelupdragon;
	}

	public int getLevelupgold() {
		return levelupgold;
	}

	public void setLevelupgold(int levelupgold) {
		this.levelupgold = levelupgold;
	}

	public int getLevelupsuzaku() {
		return levelupsuzaku;
	}

	public void setLevelupsuzaku(int levelupsuzaku) {
		this.levelupsuzaku = levelupsuzaku;
	}

	public int getLevelupwhitetiger() {
		return levelupwhitetiger;
	}

	public void setLevelupwhitetiger(int levelupwhitetiger) {
		this.levelupwhitetiger = levelupwhitetiger;
	}

	public List<Integer> getMonsterList() {
		return monsterList;
	}

	public void setMonsterList(List<Integer> monsterList) {
		this.monsterList = monsterList;
	}

	public String getShuxing() {
		return shuxing;
	}

	public void setShuxing(String shuxing) {
		this.shuxing = shuxing;
	}

	public int getSuccesscof() {
		return successcof;
	}

	public void setSuccesscof(int successcof) {
		this.successcof = successcof;
	}

	public void getBannerInfo(Q_guildbannerBean guildbannerBean) {
		if (guildbannerBean != null) {
			setGuildbannerlv(guildbannerBean.getGuildbannerlv());
			setShuxing(guildbannerBean.getShuxing());
			setSuccesscof(guildbannerBean.getSuccesscof());
			setBuffid(guildbannerBean.getBuffid());
			setExpcof(guildbannerBean.getExpcof());
			setDazuocof(guildbannerBean.getDazuocof());

			String[] spStrings = guildbannerBean.getMonsterlist().split("_");
			for (int i = 0; i <= spStrings.length - 1; i++) {
				getMonsterList().add(Integer.valueOf(spStrings[i]));
			}

			spStrings = guildbannerBean.getChangname().split("_");
			for (int i = 0; i <= spStrings.length - 1; i++) {
				switch (i) {
					case 0: {
						setChangenamegold(Integer.valueOf(spStrings[i]));
					}
					break;
					case 1: {
						setChangenamedragon(Integer.valueOf(spStrings[i]));
					}
					break;
					case 2: {
						setChangenamewhitetiger(Integer.valueOf(spStrings[i]));
					}
					break;
					case 3: {
						setChangenamesuzaku(Integer.valueOf(spStrings[i]));
					}
					break;
					case 4: {
						setChangenamebasaltic(Integer.valueOf(spStrings[i]));
					}
					break;
				}
			}
			
			if (guildbannerBean.getChangicon().trim().length() > 0) {
				spStrings = guildbannerBean.getChangicon().split("_");
				for (int i = 0; i <= spStrings.length - 1; i++) {
					switch (i) {
						case 0: {
							setChangicongold(Integer.valueOf(spStrings[i]));
						}
						break;
						case 1: {
							setChangicondragon(Integer.valueOf(spStrings[i]));
						}
						break;
						case 2: {
							setChangiconwhitetiger(Integer.valueOf(spStrings[i]));
						}
						break;
						case 3: {
							setChangiconsuzaku(Integer.valueOf(spStrings[i]));
						}
						break;
						case 4: {
							setChangiconbasaltic(Integer.valueOf(spStrings[i]));
						}
						break;
					}
				}
			}
			
			spStrings = guildbannerBean.getLevelup().split("_");
			for (int i = 0; i <= spStrings.length - 1; i++) {
				switch (i) {
					case 0: {
						setLevelupgold(Integer.valueOf(spStrings[i]));
					}
					break;
					case 1: {
						setLevelupdragon(Integer.valueOf(spStrings[i]));
					}
					break;
					case 2: {
						setLevelupwhitetiger(Integer.valueOf(spStrings[i]));
					}
					break;
					case 3: {
						setLevelupsuzaku(Integer.valueOf(spStrings[i]));
					}
					break;
					case 4: {
						setLevelupbasaltic(Integer.valueOf(spStrings[i]));
					}
					break;
				}
			}
		}
	}
}
