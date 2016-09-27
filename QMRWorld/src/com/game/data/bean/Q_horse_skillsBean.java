package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horse_skills Bean
 */
public class Q_horse_skillsBean {

	//坐骑技能编号（需与技能表保持一致）
	private int q_skillid;
	
	//坐骑技能名称
	private String q_skill_name;
	
	//显示所需坐骑阶数
	private int q_show_need_layer;
	
	//激活所需坐骑阶数
	private int q_activate_need_layer;
	
	//第一格被选中几率（区间互斥法）
	private int q_a_grid_rnd;
	
	//第二格被复选的几率（万分比）
	private int q_b_grid_rnd;
	
	//第三格被复选的几率（万分比）
	private int q_c_grid_rnd;
	
	//单球增加经验
	private int q_addexp_single;
	
	//小爆击增加经验
	private int q_addexp_crit;
	
	//大爆击增加经验
	private int q_addexp_bigcrit;
	
	//历次拉动消耗铜币（XXXX;XXX;依次类推）
	private String q_expend_gold;
	
	//历次拉动消耗元宝（XXXX;XXX;依次类推）
	private String q_expend_yuanbao;
	
	
	/**
	 * get 坐骑技能编号（需与技能表保持一致）
	 * @return 
	 */
	public int getQ_skillid(){
		return q_skillid;
	}
	
	/**
	 * set 坐骑技能编号（需与技能表保持一致）
	 */
	public void setQ_skillid(int q_skillid){
		this.q_skillid = q_skillid;
	}
	
	/**
	 * get 坐骑技能名称
	 * @return 
	 */
	public String getQ_skill_name(){
		return q_skill_name;
	}
	
	/**
	 * set 坐骑技能名称
	 */
	public void setQ_skill_name(String q_skill_name){
		this.q_skill_name = q_skill_name;
	}
	
	/**
	 * get 显示所需坐骑阶数
	 * @return 
	 */
	public int getQ_show_need_layer(){
		return q_show_need_layer;
	}
	
	/**
	 * set 显示所需坐骑阶数
	 */
	public void setQ_show_need_layer(int q_show_need_layer){
		this.q_show_need_layer = q_show_need_layer;
	}
	
	/**
	 * get 激活所需坐骑阶数
	 * @return 
	 */
	public int getQ_activate_need_layer(){
		return q_activate_need_layer;
	}
	
	/**
	 * set 激活所需坐骑阶数
	 */
	public void setQ_activate_need_layer(int q_activate_need_layer){
		this.q_activate_need_layer = q_activate_need_layer;
	}
	
	/**
	 * get 第一格被选中几率（区间互斥法）
	 * @return 
	 */
	public int getQ_a_grid_rnd(){
		return q_a_grid_rnd;
	}
	
	/**
	 * set 第一格被选中几率（区间互斥法）
	 */
	public void setQ_a_grid_rnd(int q_a_grid_rnd){
		this.q_a_grid_rnd = q_a_grid_rnd;
	}
	
	/**
	 * get 第二格被复选的几率（万分比）
	 * @return 
	 */
	public int getQ_b_grid_rnd(){
		return q_b_grid_rnd;
	}
	
	/**
	 * set 第二格被复选的几率（万分比）
	 */
	public void setQ_b_grid_rnd(int q_b_grid_rnd){
		this.q_b_grid_rnd = q_b_grid_rnd;
	}
	
	/**
	 * get 第三格被复选的几率（万分比）
	 * @return 
	 */
	public int getQ_c_grid_rnd(){
		return q_c_grid_rnd;
	}
	
	/**
	 * set 第三格被复选的几率（万分比）
	 */
	public void setQ_c_grid_rnd(int q_c_grid_rnd){
		this.q_c_grid_rnd = q_c_grid_rnd;
	}
	
	/**
	 * get 单球增加经验
	 * @return 
	 */
	public int getQ_addexp_single(){
		return q_addexp_single;
	}
	
	/**
	 * set 单球增加经验
	 */
	public void setQ_addexp_single(int q_addexp_single){
		this.q_addexp_single = q_addexp_single;
	}
	
	/**
	 * get 小爆击增加经验
	 * @return 
	 */
	public int getQ_addexp_crit(){
		return q_addexp_crit;
	}
	
	/**
	 * set 小爆击增加经验
	 */
	public void setQ_addexp_crit(int q_addexp_crit){
		this.q_addexp_crit = q_addexp_crit;
	}
	
	/**
	 * get 大爆击增加经验
	 * @return 
	 */
	public int getQ_addexp_bigcrit(){
		return q_addexp_bigcrit;
	}
	
	/**
	 * set 大爆击增加经验
	 */
	public void setQ_addexp_bigcrit(int q_addexp_bigcrit){
		this.q_addexp_bigcrit = q_addexp_bigcrit;
	}
	
	/**
	 * get 历次拉动消耗铜币（XXXX;XXX;依次类推）
	 * @return 
	 */
	public String getQ_expend_gold(){
		return q_expend_gold;
	}
	
	/**
	 * set 历次拉动消耗铜币（XXXX;XXX;依次类推）
	 */
	public void setQ_expend_gold(String q_expend_gold){
		this.q_expend_gold = q_expend_gold;
	}
	
	/**
	 * get 历次拉动消耗元宝（XXXX;XXX;依次类推）
	 * @return 
	 */
	public String getQ_expend_yuanbao(){
		return q_expend_yuanbao;
	}
	
	/**
	 * set 历次拉动消耗元宝（XXXX;XXX;依次类推）
	 */
	public void setQ_expend_yuanbao(String q_expend_yuanbao){
		this.q_expend_yuanbao = q_expend_yuanbao;
	}
	
}