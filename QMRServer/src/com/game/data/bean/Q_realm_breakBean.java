package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_realm_break Bean
 */
public class Q_realm_breakBean {

	//境界阶数
	private int q_id;
	
	//境界说明，支持html语法
	private String q_explain;
	
	//境界面板展示用造型资源编号
	private int q_panel_res;
	
	//场景中境界用造型资源编号
	private int q_scene_res;
	
	//场景境界光效资源
	private int q_scene_effect;
	
	//突破境界所需人物等级
	private int q_break_level;
	
	//突破境界消费铜币数量
	private int q_break_money;
	
	//每次突破境界所需消耗材料（id_数量）
	private String q_break_item;
	
	//突破境界成功所需进阶次数min
	private int q_success_min;
	
	//突破境界成功所需进阶次数max
	private int q_success_max;
	
	//服务器端计算用突破境界成功几率（万分比）
	private int q_cipher_random;
	
	//每次失败增加祝福值区间min
	private int q_fail_blessing_min;
	
	//每次失败增加祝福值区间max
	private int q_fail_blessing_max;
	
	//祝福值上限值
	private int q_fail_blessing_limit;
	
	//每次失败常规几率及增加人物经验（格式：几率|经验）
	private String q_fail_general_exp;
	
	//每次失败小爆击几率及增加人物经验（格式：几率|经验）
	private String q_fail_crit_exp;
	
	//每次失败大爆击几率及增加人物经验（格式：几率|经验）
	private String q_fail_maxcrit_exp;
	
	//进阶失败所获人物经验上限
	private int q_fail_limit_exp;
	
	//进阶成功是否全服公告（0不公告，1公告）
	private int q_isbulletin;
	
	
	/**
	 * get 境界阶数
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 境界阶数
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 境界说明，支持html语法
	 * @return 
	 */
	public String getQ_explain(){
		return q_explain;
	}
	
	/**
	 * set 境界说明，支持html语法
	 */
	public void setQ_explain(String q_explain){
		this.q_explain = q_explain;
	}
	
	/**
	 * get 境界面板展示用造型资源编号
	 * @return 
	 */
	public int getQ_panel_res(){
		return q_panel_res;
	}
	
	/**
	 * set 境界面板展示用造型资源编号
	 */
	public void setQ_panel_res(int q_panel_res){
		this.q_panel_res = q_panel_res;
	}
	
	/**
	 * get 场景中境界用造型资源编号
	 * @return 
	 */
	public int getQ_scene_res(){
		return q_scene_res;
	}
	
	/**
	 * set 场景中境界用造型资源编号
	 */
	public void setQ_scene_res(int q_scene_res){
		this.q_scene_res = q_scene_res;
	}
	
	/**
	 * get 场景境界光效资源
	 * @return 
	 */
	public int getQ_scene_effect(){
		return q_scene_effect;
	}
	
	/**
	 * set 场景境界光效资源
	 */
	public void setQ_scene_effect(int q_scene_effect){
		this.q_scene_effect = q_scene_effect;
	}
	
	/**
	 * get 突破境界所需人物等级
	 * @return 
	 */
	public int getQ_break_level(){
		return q_break_level;
	}
	
	/**
	 * set 突破境界所需人物等级
	 */
	public void setQ_break_level(int q_break_level){
		this.q_break_level = q_break_level;
	}
	
	/**
	 * get 突破境界消费铜币数量
	 * @return 
	 */
	public int getQ_break_money(){
		return q_break_money;
	}
	
	/**
	 * set 突破境界消费铜币数量
	 */
	public void setQ_break_money(int q_break_money){
		this.q_break_money = q_break_money;
	}
	
	/**
	 * get 每次突破境界所需消耗材料（id_数量）
	 * @return 
	 */
	public String getQ_break_item(){
		return q_break_item;
	}
	
	/**
	 * set 每次突破境界所需消耗材料（id_数量）
	 */
	public void setQ_break_item(String q_break_item){
		this.q_break_item = q_break_item;
	}
	
	/**
	 * get 突破境界成功所需进阶次数min
	 * @return 
	 */
	public int getQ_success_min(){
		return q_success_min;
	}
	
	/**
	 * set 突破境界成功所需进阶次数min
	 */
	public void setQ_success_min(int q_success_min){
		this.q_success_min = q_success_min;
	}
	
	/**
	 * get 突破境界成功所需进阶次数max
	 * @return 
	 */
	public int getQ_success_max(){
		return q_success_max;
	}
	
	/**
	 * set 突破境界成功所需进阶次数max
	 */
	public void setQ_success_max(int q_success_max){
		this.q_success_max = q_success_max;
	}
	
	/**
	 * get 服务器端计算用突破境界成功几率（万分比）
	 * @return 
	 */
	public int getQ_cipher_random(){
		return q_cipher_random;
	}
	
	/**
	 * set 服务器端计算用突破境界成功几率（万分比）
	 */
	public void setQ_cipher_random(int q_cipher_random){
		this.q_cipher_random = q_cipher_random;
	}
	
	/**
	 * get 每次失败增加祝福值区间min
	 * @return 
	 */
	public int getQ_fail_blessing_min(){
		return q_fail_blessing_min;
	}
	
	/**
	 * set 每次失败增加祝福值区间min
	 */
	public void setQ_fail_blessing_min(int q_fail_blessing_min){
		this.q_fail_blessing_min = q_fail_blessing_min;
	}
	
	/**
	 * get 每次失败增加祝福值区间max
	 * @return 
	 */
	public int getQ_fail_blessing_max(){
		return q_fail_blessing_max;
	}
	
	/**
	 * set 每次失败增加祝福值区间max
	 */
	public void setQ_fail_blessing_max(int q_fail_blessing_max){
		this.q_fail_blessing_max = q_fail_blessing_max;
	}
	
	/**
	 * get 祝福值上限值
	 * @return 
	 */
	public int getQ_fail_blessing_limit(){
		return q_fail_blessing_limit;
	}
	
	/**
	 * set 祝福值上限值
	 */
	public void setQ_fail_blessing_limit(int q_fail_blessing_limit){
		this.q_fail_blessing_limit = q_fail_blessing_limit;
	}
	
	/**
	 * get 每次失败常规几率及增加人物经验（格式：几率|经验）
	 * @return 
	 */
	public String getQ_fail_general_exp(){
		return q_fail_general_exp;
	}
	
	/**
	 * set 每次失败常规几率及增加人物经验（格式：几率|经验）
	 */
	public void setQ_fail_general_exp(String q_fail_general_exp){
		this.q_fail_general_exp = q_fail_general_exp;
	}
	
	/**
	 * get 每次失败小爆击几率及增加人物经验（格式：几率|经验）
	 * @return 
	 */
	public String getQ_fail_crit_exp(){
		return q_fail_crit_exp;
	}
	
	/**
	 * set 每次失败小爆击几率及增加人物经验（格式：几率|经验）
	 */
	public void setQ_fail_crit_exp(String q_fail_crit_exp){
		this.q_fail_crit_exp = q_fail_crit_exp;
	}
	
	/**
	 * get 每次失败大爆击几率及增加人物经验（格式：几率|经验）
	 * @return 
	 */
	public String getQ_fail_maxcrit_exp(){
		return q_fail_maxcrit_exp;
	}
	
	/**
	 * set 每次失败大爆击几率及增加人物经验（格式：几率|经验）
	 */
	public void setQ_fail_maxcrit_exp(String q_fail_maxcrit_exp){
		this.q_fail_maxcrit_exp = q_fail_maxcrit_exp;
	}
	
	/**
	 * get 进阶失败所获人物经验上限
	 * @return 
	 */
	public int getQ_fail_limit_exp(){
		return q_fail_limit_exp;
	}
	
	/**
	 * set 进阶失败所获人物经验上限
	 */
	public void setQ_fail_limit_exp(int q_fail_limit_exp){
		this.q_fail_limit_exp = q_fail_limit_exp;
	}
	
	/**
	 * get 进阶成功是否全服公告（0不公告，1公告）
	 * @return 
	 */
	public int getQ_isbulletin(){
		return q_isbulletin;
	}
	
	/**
	 * set 进阶成功是否全服公告（0不公告，1公告）
	 */
	public void setQ_isbulletin(int q_isbulletin){
		this.q_isbulletin = q_isbulletin;
	}
	
}