package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_realm_basic Bean
 */
public class Q_realm_basicBean {

	//境界阶数
	private int q_id;
	
	//境界名称
	private String q_name;
	
	//攻击上限(默认属性|上限属性）
	private String q_attack_limit;
	
	//防御上限(默认属性|上限属性）
	private String q_defence_limit;
	
	//暴击上限(默认属性|上限属性）
	private String q_crit_limit;
	
	//闪避上限(默认属性|上限属性）
	private String q_dodge_limit;
	
	//血量上限(默认属性|上限属性）
	private String q_maxhp_limit;
	
	//内力上限(默认属性|上限属性）
	private String q_maxmp_limit;
	
	//体力上限(默认属性|上限属性）
	private String q_maxsp_limit;
	
	//攻速上限(默认属性|上限属性）
	private String q_attackspeed_limit;
	
	//移速上限(默认属性|上限属性）
	private String q_speed_limit;
	
	//忽视防御上限(默认属性|上限属性）
	private String q_neg_defence_limit;
	
	//弓箭几率（万分比）(默认属性|上限属性）
	private String q_arrow_probability_limit;
	
	//强化消耗真气
	private int q_use_zhenqi;
	
	//面板显示强化成功几率描述，支持HTML
	private String q_show_random;
	
	//真实强化成功几率（万分比）
	private int q_true_random;
	
	//强化增加攻击
	private int q_attack;
	
	//强化增加防御
	private int q_defence;
	
	//强化增加暴击
	private int q_crit;
	
	//强化增加闪避
	private int q_dodge;
	
	//强化增加血量
	private int q_maxhp;
	
	//强化增加内力
	private int q_maxmp;
	
	//强化增加体力
	private int q_maxsp;
	
	//强化增加攻速
	private int q_attackspeed;
	
	//强化增加移速
	private int q_speed;
	
	//强化增加忽视防御
	private int q_neg_defence;
	
	//强化增加弓箭几率（万分比）
	private int q_arrow_probability;
	
	
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
	 * get 境界名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 境界名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 攻击上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_attack_limit(){
		return q_attack_limit;
	}
	
	/**
	 * set 攻击上限(默认属性|上限属性）
	 */
	public void setQ_attack_limit(String q_attack_limit){
		this.q_attack_limit = q_attack_limit;
	}
	
	/**
	 * get 防御上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_defence_limit(){
		return q_defence_limit;
	}
	
	/**
	 * set 防御上限(默认属性|上限属性）
	 */
	public void setQ_defence_limit(String q_defence_limit){
		this.q_defence_limit = q_defence_limit;
	}
	
	/**
	 * get 暴击上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_crit_limit(){
		return q_crit_limit;
	}
	
	/**
	 * set 暴击上限(默认属性|上限属性）
	 */
	public void setQ_crit_limit(String q_crit_limit){
		this.q_crit_limit = q_crit_limit;
	}
	
	/**
	 * get 闪避上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_dodge_limit(){
		return q_dodge_limit;
	}
	
	/**
	 * set 闪避上限(默认属性|上限属性）
	 */
	public void setQ_dodge_limit(String q_dodge_limit){
		this.q_dodge_limit = q_dodge_limit;
	}
	
	/**
	 * get 血量上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_maxhp_limit(){
		return q_maxhp_limit;
	}
	
	/**
	 * set 血量上限(默认属性|上限属性）
	 */
	public void setQ_maxhp_limit(String q_maxhp_limit){
		this.q_maxhp_limit = q_maxhp_limit;
	}
	
	/**
	 * get 内力上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_maxmp_limit(){
		return q_maxmp_limit;
	}
	
	/**
	 * set 内力上限(默认属性|上限属性）
	 */
	public void setQ_maxmp_limit(String q_maxmp_limit){
		this.q_maxmp_limit = q_maxmp_limit;
	}
	
	/**
	 * get 体力上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_maxsp_limit(){
		return q_maxsp_limit;
	}
	
	/**
	 * set 体力上限(默认属性|上限属性）
	 */
	public void setQ_maxsp_limit(String q_maxsp_limit){
		this.q_maxsp_limit = q_maxsp_limit;
	}
	
	/**
	 * get 攻速上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_attackspeed_limit(){
		return q_attackspeed_limit;
	}
	
	/**
	 * set 攻速上限(默认属性|上限属性）
	 */
	public void setQ_attackspeed_limit(String q_attackspeed_limit){
		this.q_attackspeed_limit = q_attackspeed_limit;
	}
	
	/**
	 * get 移速上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_speed_limit(){
		return q_speed_limit;
	}
	
	/**
	 * set 移速上限(默认属性|上限属性）
	 */
	public void setQ_speed_limit(String q_speed_limit){
		this.q_speed_limit = q_speed_limit;
	}
	
	/**
	 * get 忽视防御上限(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_neg_defence_limit(){
		return q_neg_defence_limit;
	}
	
	/**
	 * set 忽视防御上限(默认属性|上限属性）
	 */
	public void setQ_neg_defence_limit(String q_neg_defence_limit){
		this.q_neg_defence_limit = q_neg_defence_limit;
	}
	
	/**
	 * get 弓箭几率（万分比）(默认属性|上限属性）
	 * @return 
	 */
	public String getQ_arrow_probability_limit(){
		return q_arrow_probability_limit;
	}
	
	/**
	 * set 弓箭几率（万分比）(默认属性|上限属性）
	 */
	public void setQ_arrow_probability_limit(String q_arrow_probability_limit){
		this.q_arrow_probability_limit = q_arrow_probability_limit;
	}
	
	/**
	 * get 强化消耗真气
	 * @return 
	 */
	public int getQ_use_zhenqi(){
		return q_use_zhenqi;
	}
	
	/**
	 * set 强化消耗真气
	 */
	public void setQ_use_zhenqi(int q_use_zhenqi){
		this.q_use_zhenqi = q_use_zhenqi;
	}
	
	/**
	 * get 面板显示强化成功几率描述，支持HTML
	 * @return 
	 */
	public String getQ_show_random(){
		return q_show_random;
	}
	
	/**
	 * set 面板显示强化成功几率描述，支持HTML
	 */
	public void setQ_show_random(String q_show_random){
		this.q_show_random = q_show_random;
	}
	
	/**
	 * get 真实强化成功几率（万分比）
	 * @return 
	 */
	public int getQ_true_random(){
		return q_true_random;
	}
	
	/**
	 * set 真实强化成功几率（万分比）
	 */
	public void setQ_true_random(int q_true_random){
		this.q_true_random = q_true_random;
	}
	
	/**
	 * get 强化增加攻击
	 * @return 
	 */
	public int getQ_attack(){
		return q_attack;
	}
	
	/**
	 * set 强化增加攻击
	 */
	public void setQ_attack(int q_attack){
		this.q_attack = q_attack;
	}
	
	/**
	 * get 强化增加防御
	 * @return 
	 */
	public int getQ_defence(){
		return q_defence;
	}
	
	/**
	 * set 强化增加防御
	 */
	public void setQ_defence(int q_defence){
		this.q_defence = q_defence;
	}
	
	/**
	 * get 强化增加暴击
	 * @return 
	 */
	public int getQ_crit(){
		return q_crit;
	}
	
	/**
	 * set 强化增加暴击
	 */
	public void setQ_crit(int q_crit){
		this.q_crit = q_crit;
	}
	
	/**
	 * get 强化增加闪避
	 * @return 
	 */
	public int getQ_dodge(){
		return q_dodge;
	}
	
	/**
	 * set 强化增加闪避
	 */
	public void setQ_dodge(int q_dodge){
		this.q_dodge = q_dodge;
	}
	
	/**
	 * get 强化增加血量
	 * @return 
	 */
	public int getQ_maxhp(){
		return q_maxhp;
	}
	
	/**
	 * set 强化增加血量
	 */
	public void setQ_maxhp(int q_maxhp){
		this.q_maxhp = q_maxhp;
	}
	
	/**
	 * get 强化增加内力
	 * @return 
	 */
	public int getQ_maxmp(){
		return q_maxmp;
	}
	
	/**
	 * set 强化增加内力
	 */
	public void setQ_maxmp(int q_maxmp){
		this.q_maxmp = q_maxmp;
	}
	
	/**
	 * get 强化增加体力
	 * @return 
	 */
	public int getQ_maxsp(){
		return q_maxsp;
	}
	
	/**
	 * set 强化增加体力
	 */
	public void setQ_maxsp(int q_maxsp){
		this.q_maxsp = q_maxsp;
	}
	
	/**
	 * get 强化增加攻速
	 * @return 
	 */
	public int getQ_attackspeed(){
		return q_attackspeed;
	}
	
	/**
	 * set 强化增加攻速
	 */
	public void setQ_attackspeed(int q_attackspeed){
		this.q_attackspeed = q_attackspeed;
	}
	
	/**
	 * get 强化增加移速
	 * @return 
	 */
	public int getQ_speed(){
		return q_speed;
	}
	
	/**
	 * set 强化增加移速
	 */
	public void setQ_speed(int q_speed){
		this.q_speed = q_speed;
	}
	
	/**
	 * get 强化增加忽视防御
	 * @return 
	 */
	public int getQ_neg_defence(){
		return q_neg_defence;
	}
	
	/**
	 * set 强化增加忽视防御
	 */
	public void setQ_neg_defence(int q_neg_defence){
		this.q_neg_defence = q_neg_defence;
	}
	
	/**
	 * get 强化增加弓箭几率（万分比）
	 * @return 
	 */
	public int getQ_arrow_probability(){
		return q_arrow_probability;
	}
	
	/**
	 * set 强化增加弓箭几率（万分比）
	 */
	public void setQ_arrow_probability(int q_arrow_probability){
		this.q_arrow_probability = q_arrow_probability;
	}
	
}