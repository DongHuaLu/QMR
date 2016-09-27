package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_gem_up Bean
 */
public class Q_gem_upBean {

	//表编号 (q_gem_type_q_gem_level)
	private String q_id;
	
	//宝石名称
	private String q_gem_name;
	
	//宝石类型
	private int q_gem_type;
	
	//宝石等级
	private int q_gem_level;
	
	//是否可升级（0不可升级，1可升级）
	private int q_is_up;
	
	//宝石颜色值R|G|B（填法格式：90|200|80）
	private String q_gem_rgb;
	
	//宝石面板造型SWF资源编号
	private int q_gem_swf;
	
	//鼠标TIPS界面宝石造型SWF资源编号
	private int q_gem_tips_swf;
	
	//升级消耗材料ID序列与数量（物品ID_数量;物品ID_数量）
	private String q_consumable_item;
	
	//升级消耗真气数量
	private int q_consumable_zhenqi;
	
	//升级所需经验
	private int q_need_up_exp;
	
	//常规概率（互斥）
	private int q_normal_rnd;
	
	//常规增加经验
	private int q_normal_exp;
	
	//小爆击概率（互斥）
	private int q_crit_rnd;
	
	//小爆击增加经验
	private int q_crit_exp;
	
	//大爆击概率（互斥）
	private int q_max_crit_rnd;
	
	//大爆击增加经验
	private int q_max_crit_exp;
	
	//攻击附加值
	private int q_attack;
	
	//防御附加值
	private int q_defence;
	
	//闪避附加值
	private int q_dodge;
	
	//爆击附加值
	private int q_crit;
	
	//生命上限附加值
	private int q_maxhp;
	
	//内力上限附加值
	private int q_maxmp;
	
	//体力上限附加值
	private int q_maxsp;
	
	//攻击速度附加值
	private int q_attackspeed;
	
	//移动速度附加值
	private int q_speed;
	
	//幸运附加值
	private int q_luck;
	
	
	/**
	 * get 表编号 (q_gem_type_q_gem_level)
	 * @return 
	 */
	public String getQ_id(){
		return q_id;
	}
	
	/**
	 * set 表编号 (q_gem_type_q_gem_level)
	 */
	public void setQ_id(String q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 宝石名称
	 * @return 
	 */
	public String getQ_gem_name(){
		return q_gem_name;
	}
	
	/**
	 * set 宝石名称
	 */
	public void setQ_gem_name(String q_gem_name){
		this.q_gem_name = q_gem_name;
	}
	
	/**
	 * get 宝石类型
	 * @return 
	 */
	public int getQ_gem_type(){
		return q_gem_type;
	}
	
	/**
	 * set 宝石类型
	 */
	public void setQ_gem_type(int q_gem_type){
		this.q_gem_type = q_gem_type;
	}
	
	/**
	 * get 宝石等级
	 * @return 
	 */
	public int getQ_gem_level(){
		return q_gem_level;
	}
	
	/**
	 * set 宝石等级
	 */
	public void setQ_gem_level(int q_gem_level){
		this.q_gem_level = q_gem_level;
	}
	
	/**
	 * get 是否可升级（0不可升级，1可升级）
	 * @return 
	 */
	public int getQ_is_up(){
		return q_is_up;
	}
	
	/**
	 * set 是否可升级（0不可升级，1可升级）
	 */
	public void setQ_is_up(int q_is_up){
		this.q_is_up = q_is_up;
	}
	
	/**
	 * get 宝石颜色值R|G|B（填法格式：90|200|80）
	 * @return 
	 */
	public String getQ_gem_rgb(){
		return q_gem_rgb;
	}
	
	/**
	 * set 宝石颜色值R|G|B（填法格式：90|200|80）
	 */
	public void setQ_gem_rgb(String q_gem_rgb){
		this.q_gem_rgb = q_gem_rgb;
	}
	
	/**
	 * get 宝石面板造型SWF资源编号
	 * @return 
	 */
	public int getQ_gem_swf(){
		return q_gem_swf;
	}
	
	/**
	 * set 宝石面板造型SWF资源编号
	 */
	public void setQ_gem_swf(int q_gem_swf){
		this.q_gem_swf = q_gem_swf;
	}
	
	/**
	 * get 鼠标TIPS界面宝石造型SWF资源编号
	 * @return 
	 */
	public int getQ_gem_tips_swf(){
		return q_gem_tips_swf;
	}
	
	/**
	 * set 鼠标TIPS界面宝石造型SWF资源编号
	 */
	public void setQ_gem_tips_swf(int q_gem_tips_swf){
		this.q_gem_tips_swf = q_gem_tips_swf;
	}
	
	/**
	 * get 升级消耗材料ID序列与数量（物品ID_数量;物品ID_数量）
	 * @return 
	 */
	public String getQ_consumable_item(){
		return q_consumable_item;
	}
	
	/**
	 * set 升级消耗材料ID序列与数量（物品ID_数量;物品ID_数量）
	 */
	public void setQ_consumable_item(String q_consumable_item){
		this.q_consumable_item = q_consumable_item;
	}
	
	/**
	 * get 升级消耗真气数量
	 * @return 
	 */
	public int getQ_consumable_zhenqi(){
		return q_consumable_zhenqi;
	}
	
	/**
	 * set 升级消耗真气数量
	 */
	public void setQ_consumable_zhenqi(int q_consumable_zhenqi){
		this.q_consumable_zhenqi = q_consumable_zhenqi;
	}
	
	/**
	 * get 升级所需经验
	 * @return 
	 */
	public int getQ_need_up_exp(){
		return q_need_up_exp;
	}
	
	/**
	 * set 升级所需经验
	 */
	public void setQ_need_up_exp(int q_need_up_exp){
		this.q_need_up_exp = q_need_up_exp;
	}
	
	/**
	 * get 常规概率（互斥）
	 * @return 
	 */
	public int getQ_normal_rnd(){
		return q_normal_rnd;
	}
	
	/**
	 * set 常规概率（互斥）
	 */
	public void setQ_normal_rnd(int q_normal_rnd){
		this.q_normal_rnd = q_normal_rnd;
	}
	
	/**
	 * get 常规增加经验
	 * @return 
	 */
	public int getQ_normal_exp(){
		return q_normal_exp;
	}
	
	/**
	 * set 常规增加经验
	 */
	public void setQ_normal_exp(int q_normal_exp){
		this.q_normal_exp = q_normal_exp;
	}
	
	/**
	 * get 小爆击概率（互斥）
	 * @return 
	 */
	public int getQ_crit_rnd(){
		return q_crit_rnd;
	}
	
	/**
	 * set 小爆击概率（互斥）
	 */
	public void setQ_crit_rnd(int q_crit_rnd){
		this.q_crit_rnd = q_crit_rnd;
	}
	
	/**
	 * get 小爆击增加经验
	 * @return 
	 */
	public int getQ_crit_exp(){
		return q_crit_exp;
	}
	
	/**
	 * set 小爆击增加经验
	 */
	public void setQ_crit_exp(int q_crit_exp){
		this.q_crit_exp = q_crit_exp;
	}
	
	/**
	 * get 大爆击概率（互斥）
	 * @return 
	 */
	public int getQ_max_crit_rnd(){
		return q_max_crit_rnd;
	}
	
	/**
	 * set 大爆击概率（互斥）
	 */
	public void setQ_max_crit_rnd(int q_max_crit_rnd){
		this.q_max_crit_rnd = q_max_crit_rnd;
	}
	
	/**
	 * get 大爆击增加经验
	 * @return 
	 */
	public int getQ_max_crit_exp(){
		return q_max_crit_exp;
	}
	
	/**
	 * set 大爆击增加经验
	 */
	public void setQ_max_crit_exp(int q_max_crit_exp){
		this.q_max_crit_exp = q_max_crit_exp;
	}
	
	/**
	 * get 攻击附加值
	 * @return 
	 */
	public int getQ_attack(){
		return q_attack;
	}
	
	/**
	 * set 攻击附加值
	 */
	public void setQ_attack(int q_attack){
		this.q_attack = q_attack;
	}
	
	/**
	 * get 防御附加值
	 * @return 
	 */
	public int getQ_defence(){
		return q_defence;
	}
	
	/**
	 * set 防御附加值
	 */
	public void setQ_defence(int q_defence){
		this.q_defence = q_defence;
	}
	
	/**
	 * get 闪避附加值
	 * @return 
	 */
	public int getQ_dodge(){
		return q_dodge;
	}
	
	/**
	 * set 闪避附加值
	 */
	public void setQ_dodge(int q_dodge){
		this.q_dodge = q_dodge;
	}
	
	/**
	 * get 爆击附加值
	 * @return 
	 */
	public int getQ_crit(){
		return q_crit;
	}
	
	/**
	 * set 爆击附加值
	 */
	public void setQ_crit(int q_crit){
		this.q_crit = q_crit;
	}
	
	/**
	 * get 生命上限附加值
	 * @return 
	 */
	public int getQ_maxhp(){
		return q_maxhp;
	}
	
	/**
	 * set 生命上限附加值
	 */
	public void setQ_maxhp(int q_maxhp){
		this.q_maxhp = q_maxhp;
	}
	
	/**
	 * get 内力上限附加值
	 * @return 
	 */
	public int getQ_maxmp(){
		return q_maxmp;
	}
	
	/**
	 * set 内力上限附加值
	 */
	public void setQ_maxmp(int q_maxmp){
		this.q_maxmp = q_maxmp;
	}
	
	/**
	 * get 体力上限附加值
	 * @return 
	 */
	public int getQ_maxsp(){
		return q_maxsp;
	}
	
	/**
	 * set 体力上限附加值
	 */
	public void setQ_maxsp(int q_maxsp){
		this.q_maxsp = q_maxsp;
	}
	
	/**
	 * get 攻击速度附加值
	 * @return 
	 */
	public int getQ_attackspeed(){
		return q_attackspeed;
	}
	
	/**
	 * set 攻击速度附加值
	 */
	public void setQ_attackspeed(int q_attackspeed){
		this.q_attackspeed = q_attackspeed;
	}
	
	/**
	 * get 移动速度附加值
	 * @return 
	 */
	public int getQ_speed(){
		return q_speed;
	}
	
	/**
	 * set 移动速度附加值
	 */
	public void setQ_speed(int q_speed){
		this.q_speed = q_speed;
	}
	
	/**
	 * get 幸运附加值
	 * @return 
	 */
	public int getQ_luck(){
		return q_luck;
	}
	
	/**
	 * set 幸运附加值
	 */
	public void setQ_luck(int q_luck){
		this.q_luck = q_luck;
	}
	
}