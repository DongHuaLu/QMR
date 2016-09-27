package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Guildbanner Bean
 */
public class Q_guildbannerBean {

	//帮旗等级
	private int guildbannerlv;
	
	//对应的怪物表编号（用来获取帮旗造型编号）
	private String monsterlist;
	
	//旗帜属性（）
	private String shuxing;
	
	//改名消耗帮贡仓库（格式：游戏币_青龙令数量_白虎令数量_朱雀令数量_玄武令数量）
	private String changname;
	
	//更换造型消耗帮贡仓库（格式：游戏币_青龙令数量_白虎令数量_朱雀令数量_玄武令数量）
	private String changicon;
	
	//帮旗升级消耗帮贡仓库（格式：游戏币_青龙令数量_白虎令数量_朱雀令数量_玄武令数量）
	private String levelup;
	
	//帮旗升级成功几率（万分比，本处填分子）
	private int successcof;
	
	//帮旗加成人物属性BUFF编号（对应技能效果数据库）
	private int buffid;
	
	//增加打怪经验收益比例（万分比，本处填分子）
	private int expcof;
	
	//增加打坐收益比例（万分比，本处填分子）
	private int dazuocof;
	
	
	/**
	 * get 帮旗等级
	 * @return 
	 */
	public int getGuildbannerlv(){
		return guildbannerlv;
	}
	
	/**
	 * set 帮旗等级
	 */
	public void setGuildbannerlv(int guildbannerlv){
		this.guildbannerlv = guildbannerlv;
	}
	
	/**
	 * get 对应的怪物表编号（用来获取帮旗造型编号）
	 * @return 
	 */
	public String getMonsterlist(){
		return monsterlist;
	}
	
	/**
	 * set 对应的怪物表编号（用来获取帮旗造型编号）
	 */
	public void setMonsterlist(String monsterlist){
		this.monsterlist = monsterlist;
	}
	
	/**
	 * get 旗帜属性（）
	 * @return 
	 */
	public String getShuxing(){
		return shuxing;
	}
	
	/**
	 * set 旗帜属性（）
	 */
	public void setShuxing(String shuxing){
		this.shuxing = shuxing;
	}
	
	/**
	 * get 改名消耗帮贡仓库（格式：游戏币_青龙令数量_白虎令数量_朱雀令数量_玄武令数量）
	 * @return 
	 */
	public String getChangname(){
		return changname;
	}
	
	/**
	 * set 改名消耗帮贡仓库（格式：游戏币_青龙令数量_白虎令数量_朱雀令数量_玄武令数量）
	 */
	public void setChangname(String changname){
		this.changname = changname;
	}
	
	/**
	 * get 更换造型消耗帮贡仓库（格式：游戏币_青龙令数量_白虎令数量_朱雀令数量_玄武令数量）
	 * @return 
	 */
	public String getChangicon(){
		return changicon;
	}
	
	/**
	 * set 更换造型消耗帮贡仓库（格式：游戏币_青龙令数量_白虎令数量_朱雀令数量_玄武令数量）
	 */
	public void setChangicon(String changicon){
		this.changicon = changicon;
	}
	
	/**
	 * get 帮旗升级消耗帮贡仓库（格式：游戏币_青龙令数量_白虎令数量_朱雀令数量_玄武令数量）
	 * @return 
	 */
	public String getLevelup(){
		return levelup;
	}
	
	/**
	 * set 帮旗升级消耗帮贡仓库（格式：游戏币_青龙令数量_白虎令数量_朱雀令数量_玄武令数量）
	 */
	public void setLevelup(String levelup){
		this.levelup = levelup;
	}
	
	/**
	 * get 帮旗升级成功几率（万分比，本处填分子）
	 * @return 
	 */
	public int getSuccesscof(){
		return successcof;
	}
	
	/**
	 * set 帮旗升级成功几率（万分比，本处填分子）
	 */
	public void setSuccesscof(int successcof){
		this.successcof = successcof;
	}
	
	/**
	 * get 帮旗加成人物属性BUFF编号（对应技能效果数据库）
	 * @return 
	 */
	public int getBuffid(){
		return buffid;
	}
	
	/**
	 * set 帮旗加成人物属性BUFF编号（对应技能效果数据库）
	 */
	public void setBuffid(int buffid){
		this.buffid = buffid;
	}
	
	/**
	 * get 增加打怪经验收益比例（万分比，本处填分子）
	 * @return 
	 */
	public int getExpcof(){
		return expcof;
	}
	
	/**
	 * set 增加打怪经验收益比例（万分比，本处填分子）
	 */
	public void setExpcof(int expcof){
		this.expcof = expcof;
	}
	
	/**
	 * get 增加打坐收益比例（万分比，本处填分子）
	 * @return 
	 */
	public int getDazuocof(){
		return dazuocof;
	}
	
	/**
	 * set 增加打坐收益比例（万分比，本处填分子）
	 */
	public void setDazuocof(int dazuocof){
		this.dazuocof = dazuocof;
	}
	
}