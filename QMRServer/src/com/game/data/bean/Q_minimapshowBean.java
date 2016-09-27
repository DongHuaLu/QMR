package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_minimapshow Bean
 */
public class Q_minimapshowBean {

	//地图编号
	private int q_mapid;
	
	//怪物("|":区分相同怪物,";":区分不同怪物 10087_100_100 表示：怪物ID为10087的怪物会在对应地图的X,Y坐标分别为100,100的位置刷新，相同类型怪物在同一张地图内会存在多个刷新点，配置的第一个刷新点为小地图上显示的标记位置。当玩家点击怪物名称执行自动寻径逻辑时，如果配置表中相同怪物存在多个刷新点则客户端会让玩家寻径到随机的刷怪点)
	private String q_monster_coord;
	
	//适合练级等级（min）
	private int q_min_lv;
	
	//适合练级等级（max）
	private int q_max_lv;
	
	//跨地图寻路点
	private String q_movepoint;
	
	//怪物刷新点不出现在该小地图中
	private String q_noappear;
	
	
	/**
	 * get 地图编号
	 * @return 
	 */
	public int getQ_mapid(){
		return q_mapid;
	}
	
	/**
	 * set 地图编号
	 */
	public void setQ_mapid(int q_mapid){
		this.q_mapid = q_mapid;
	}
	
	/**
	 * get 怪物("|":区分相同怪物,";":区分不同怪物 10087_100_100 表示：怪物ID为10087的怪物会在对应地图的X,Y坐标分别为100,100的位置刷新，相同类型怪物在同一张地图内会存在多个刷新点，配置的第一个刷新点为小地图上显示的标记位置。当玩家点击怪物名称执行自动寻径逻辑时，如果配置表中相同怪物存在多个刷新点则客户端会让玩家寻径到随机的刷怪点)
	 * @return 
	 */
	public String getQ_monster_coord(){
		return q_monster_coord;
	}
	
	/**
	 * set 怪物("|":区分相同怪物,";":区分不同怪物 10087_100_100 表示：怪物ID为10087的怪物会在对应地图的X,Y坐标分别为100,100的位置刷新，相同类型怪物在同一张地图内会存在多个刷新点，配置的第一个刷新点为小地图上显示的标记位置。当玩家点击怪物名称执行自动寻径逻辑时，如果配置表中相同怪物存在多个刷新点则客户端会让玩家寻径到随机的刷怪点)
	 */
	public void setQ_monster_coord(String q_monster_coord){
		this.q_monster_coord = q_monster_coord;
	}
	
	/**
	 * get 适合练级等级（min）
	 * @return 
	 */
	public int getQ_min_lv(){
		return q_min_lv;
	}
	
	/**
	 * set 适合练级等级（min）
	 */
	public void setQ_min_lv(int q_min_lv){
		this.q_min_lv = q_min_lv;
	}
	
	/**
	 * get 适合练级等级（max）
	 * @return 
	 */
	public int getQ_max_lv(){
		return q_max_lv;
	}
	
	/**
	 * set 适合练级等级（max）
	 */
	public void setQ_max_lv(int q_max_lv){
		this.q_max_lv = q_max_lv;
	}
	
	/**
	 * get 跨地图寻路点
	 * @return 
	 */
	public String getQ_movepoint(){
		return q_movepoint;
	}
	
	/**
	 * set 跨地图寻路点
	 */
	public void setQ_movepoint(String q_movepoint){
		this.q_movepoint = q_movepoint;
	}
	
	/**
	 * get 怪物刷新点不出现在该小地图中
	 * @return 
	 */
	public String getQ_noappear(){
		return q_noappear;
	}
	
	/**
	 * set 怪物刷新点不出现在该小地图中
	 */
	public void setQ_noappear(String q_noappear){
		this.q_noappear = q_noappear;
	}
	
}