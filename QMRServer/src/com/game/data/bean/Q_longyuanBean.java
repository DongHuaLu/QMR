package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_longyuan Bean
 */
public class Q_longyuanBean {

	//星图名称
	private String q_name;
	
	//星图编号
	private String q_realm;
	
	//星位名称
	private String q_pointname;
	
	//星位编号
	private String q_id;
	
	//未激活星位图档名称
	private String q_imagename_unaction;
	
	//已激活星位图档名称
	private String q_imagename_action;
	
	//星位激活成功特效编号
	private int q_effectsid_suc;
	
	//星位激活失败特效编号
	private int q_effectsid_fai;
	
	//是否为最后一个星位（0为否，1为是，2最后一张星图最后一个）
	private int q_islastpoint;
	
	//每次激活所需真气
	private int q_ordzhenqi;
	
	//激活所需次数下限
	private int q_limit;
	
	//激活所需次数上限
	private int q_max;
	
	//每次需求游戏币
	private int q_ordgold;
	
	//开服天数减免系数
	private int q_days_delay;
	
	//实际用成功几率（万分比）
	private int q_successrate;
	
	//前端显示成功几率
	private String q_show_successrate;
	
	//需求等级
	private int q_requireslv;
	
	//加成武学编号（填0表示不增加）
	private int q_addskillid;
	
	//加成武学名称（填0表示不增加）
	private String q_addskillname;
	
	//加成层数（填0表示不增加）
	private int q_addskilllevel;
	
	//自动学习的武功技能编号（空白表示不增加）
	private String q_skillid;
	
	//自动学习的武功名称
	private String q_skillname;
	
	//暴气范围半径格子数(25像素=1格子，不要超出可视范围,)
	private int q_radius;
	
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
	 * get 星图名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 星图名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 星图编号
	 * @return 
	 */
	public String getQ_realm(){
		return q_realm;
	}
	
	/**
	 * set 星图编号
	 */
	public void setQ_realm(String q_realm){
		this.q_realm = q_realm;
	}
	
	/**
	 * get 星位名称
	 * @return 
	 */
	public String getQ_pointname(){
		return q_pointname;
	}
	
	/**
	 * set 星位名称
	 */
	public void setQ_pointname(String q_pointname){
		this.q_pointname = q_pointname;
	}
	
	/**
	 * get 星位编号
	 * @return 
	 */
	public String getQ_id(){
		return q_id;
	}
	
	/**
	 * set 星位编号
	 */
	public void setQ_id(String q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 未激活星位图档名称
	 * @return 
	 */
	public String getQ_imagename_unaction(){
		return q_imagename_unaction;
	}
	
	/**
	 * set 未激活星位图档名称
	 */
	public void setQ_imagename_unaction(String q_imagename_unaction){
		this.q_imagename_unaction = q_imagename_unaction;
	}
	
	/**
	 * get 已激活星位图档名称
	 * @return 
	 */
	public String getQ_imagename_action(){
		return q_imagename_action;
	}
	
	/**
	 * set 已激活星位图档名称
	 */
	public void setQ_imagename_action(String q_imagename_action){
		this.q_imagename_action = q_imagename_action;
	}
	
	/**
	 * get 星位激活成功特效编号
	 * @return 
	 */
	public int getQ_effectsid_suc(){
		return q_effectsid_suc;
	}
	
	/**
	 * set 星位激活成功特效编号
	 */
	public void setQ_effectsid_suc(int q_effectsid_suc){
		this.q_effectsid_suc = q_effectsid_suc;
	}
	
	/**
	 * get 星位激活失败特效编号
	 * @return 
	 */
	public int getQ_effectsid_fai(){
		return q_effectsid_fai;
	}
	
	/**
	 * set 星位激活失败特效编号
	 */
	public void setQ_effectsid_fai(int q_effectsid_fai){
		this.q_effectsid_fai = q_effectsid_fai;
	}
	
	/**
	 * get 是否为最后一个星位（0为否，1为是，2最后一张星图最后一个）
	 * @return 
	 */
	public int getQ_islastpoint(){
		return q_islastpoint;
	}
	
	/**
	 * set 是否为最后一个星位（0为否，1为是，2最后一张星图最后一个）
	 */
	public void setQ_islastpoint(int q_islastpoint){
		this.q_islastpoint = q_islastpoint;
	}
	
	/**
	 * get 每次激活所需真气
	 * @return 
	 */
	public int getQ_ordzhenqi(){
		return q_ordzhenqi;
	}
	
	/**
	 * set 每次激活所需真气
	 */
	public void setQ_ordzhenqi(int q_ordzhenqi){
		this.q_ordzhenqi = q_ordzhenqi;
	}
	
	/**
	 * get 激活所需次数下限
	 * @return 
	 */
	public int getQ_limit(){
		return q_limit;
	}
	
	/**
	 * set 激活所需次数下限
	 */
	public void setQ_limit(int q_limit){
		this.q_limit = q_limit;
	}
	
	/**
	 * get 激活所需次数上限
	 * @return 
	 */
	public int getQ_max(){
		return q_max;
	}
	
	/**
	 * set 激活所需次数上限
	 */
	public void setQ_max(int q_max){
		this.q_max = q_max;
	}
	
	/**
	 * get 每次需求游戏币
	 * @return 
	 */
	public int getQ_ordgold(){
		return q_ordgold;
	}
	
	/**
	 * set 每次需求游戏币
	 */
	public void setQ_ordgold(int q_ordgold){
		this.q_ordgold = q_ordgold;
	}
	
	/**
	 * get 开服天数减免系数
	 * @return 
	 */
	public int getQ_days_delay(){
		return q_days_delay;
	}
	
	/**
	 * set 开服天数减免系数
	 */
	public void setQ_days_delay(int q_days_delay){
		this.q_days_delay = q_days_delay;
	}
	
	/**
	 * get 实际用成功几率（万分比）
	 * @return 
	 */
	public int getQ_successrate(){
		return q_successrate;
	}
	
	/**
	 * set 实际用成功几率（万分比）
	 */
	public void setQ_successrate(int q_successrate){
		this.q_successrate = q_successrate;
	}
	
	/**
	 * get 前端显示成功几率
	 * @return 
	 */
	public String getQ_show_successrate(){
		return q_show_successrate;
	}
	
	/**
	 * set 前端显示成功几率
	 */
	public void setQ_show_successrate(String q_show_successrate){
		this.q_show_successrate = q_show_successrate;
	}
	
	/**
	 * get 需求等级
	 * @return 
	 */
	public int getQ_requireslv(){
		return q_requireslv;
	}
	
	/**
	 * set 需求等级
	 */
	public void setQ_requireslv(int q_requireslv){
		this.q_requireslv = q_requireslv;
	}
	
	/**
	 * get 加成武学编号（填0表示不增加）
	 * @return 
	 */
	public int getQ_addskillid(){
		return q_addskillid;
	}
	
	/**
	 * set 加成武学编号（填0表示不增加）
	 */
	public void setQ_addskillid(int q_addskillid){
		this.q_addskillid = q_addskillid;
	}
	
	/**
	 * get 加成武学名称（填0表示不增加）
	 * @return 
	 */
	public String getQ_addskillname(){
		return q_addskillname;
	}
	
	/**
	 * set 加成武学名称（填0表示不增加）
	 */
	public void setQ_addskillname(String q_addskillname){
		this.q_addskillname = q_addskillname;
	}
	
	/**
	 * get 加成层数（填0表示不增加）
	 * @return 
	 */
	public int getQ_addskilllevel(){
		return q_addskilllevel;
	}
	
	/**
	 * set 加成层数（填0表示不增加）
	 */
	public void setQ_addskilllevel(int q_addskilllevel){
		this.q_addskilllevel = q_addskilllevel;
	}
	
	/**
	 * get 自动学习的武功技能编号（空白表示不增加）
	 * @return 
	 */
	public String getQ_skillid(){
		return q_skillid;
	}
	
	/**
	 * set 自动学习的武功技能编号（空白表示不增加）
	 */
	public void setQ_skillid(String q_skillid){
		this.q_skillid = q_skillid;
	}
	
	/**
	 * get 自动学习的武功名称
	 * @return 
	 */
	public String getQ_skillname(){
		return q_skillname;
	}
	
	/**
	 * set 自动学习的武功名称
	 */
	public void setQ_skillname(String q_skillname){
		this.q_skillname = q_skillname;
	}
	
	/**
	 * get 暴气范围半径格子数(25像素=1格子，不要超出可视范围,)
	 * @return 
	 */
	public int getQ_radius(){
		return q_radius;
	}
	
	/**
	 * set 暴气范围半径格子数(25像素=1格子，不要超出可视范围,)
	 */
	public void setQ_radius(int q_radius){
		this.q_radius = q_radius;
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