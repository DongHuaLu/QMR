package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_rank Bean
 */
public class Q_rankBean {

	//军衔等级
	private int q_ranklv;
	
	//军衔名称
	private String q_rankname;
	
	//角色名颜色（填入色值程序直接调用）
	private String q_name_reg;
	
	//需要军功值
	private int q_ranknum;
	
	//是否需要服务器排名（0：不需要，数字N：表示排名前N名玩家有资格获得该军衔）
	private int q_isranking;
	
	//需要主角等级
	private int q_need_level;
	
	//自动领悟技能编号（需要支持失去军衔自动删除技能,0：不领悟技能）
	private int q_savvy_skill;
	
	//军衔激活技能（0：不触发技能激活事件。10010为技能编号，以此类推）
	private int q_activation_skill;
	
	//攻击
	private int q_attack;
	
	//防御
	private int q_defence;
	
	//暴击
	private int q_crit;
	
	//闪避
	private int q_dodge;
	
	//血量
	private int q_maxhp;
	
	//内力
	private int q_maxmp;
	
	//体力
	private int q_maxsp;
	
	//攻击速度
	private int q_attackspeed;
	
	//移动速度
	private int q_speed;
	
	//幸运
	private int q_luck;
	
	//达到军衔发送公告
	private int q_bulletin;
	
	//是否开启，1开启，0关闭
	private int q_switch;
	
	
	/**
	 * get 军衔等级
	 * @return 
	 */
	public int getQ_ranklv(){
		return q_ranklv;
	}
	
	/**
	 * set 军衔等级
	 */
	public void setQ_ranklv(int q_ranklv){
		this.q_ranklv = q_ranklv;
	}
	
	/**
	 * get 军衔名称
	 * @return 
	 */
	public String getQ_rankname(){
		return q_rankname;
	}
	
	/**
	 * set 军衔名称
	 */
	public void setQ_rankname(String q_rankname){
		this.q_rankname = q_rankname;
	}
	
	/**
	 * get 角色名颜色（填入色值程序直接调用）
	 * @return 
	 */
	public String getQ_name_reg(){
		return q_name_reg;
	}
	
	/**
	 * set 角色名颜色（填入色值程序直接调用）
	 */
	public void setQ_name_reg(String q_name_reg){
		this.q_name_reg = q_name_reg;
	}
	
	/**
	 * get 需要军功值
	 * @return 
	 */
	public int getQ_ranknum(){
		return q_ranknum;
	}
	
	/**
	 * set 需要军功值
	 */
	public void setQ_ranknum(int q_ranknum){
		this.q_ranknum = q_ranknum;
	}
	
	/**
	 * get 是否需要服务器排名（0：不需要，数字N：表示排名前N名玩家有资格获得该军衔）
	 * @return 
	 */
	public int getQ_isranking(){
		return q_isranking;
	}
	
	/**
	 * set 是否需要服务器排名（0：不需要，数字N：表示排名前N名玩家有资格获得该军衔）
	 */
	public void setQ_isranking(int q_isranking){
		this.q_isranking = q_isranking;
	}
	
	/**
	 * get 需要主角等级
	 * @return 
	 */
	public int getQ_need_level(){
		return q_need_level;
	}
	
	/**
	 * set 需要主角等级
	 */
	public void setQ_need_level(int q_need_level){
		this.q_need_level = q_need_level;
	}
	
	/**
	 * get 自动领悟技能编号（需要支持失去军衔自动删除技能,0：不领悟技能）
	 * @return 
	 */
	public int getQ_savvy_skill(){
		return q_savvy_skill;
	}
	
	/**
	 * set 自动领悟技能编号（需要支持失去军衔自动删除技能,0：不领悟技能）
	 */
	public void setQ_savvy_skill(int q_savvy_skill){
		this.q_savvy_skill = q_savvy_skill;
	}
	
	/**
	 * get 军衔激活技能（0：不触发技能激活事件。10010为技能编号，以此类推）
	 * @return 
	 */
	public int getQ_activation_skill(){
		return q_activation_skill;
	}
	
	/**
	 * set 军衔激活技能（0：不触发技能激活事件。10010为技能编号，以此类推）
	 */
	public void setQ_activation_skill(int q_activation_skill){
		this.q_activation_skill = q_activation_skill;
	}
	
	/**
	 * get 攻击
	 * @return 
	 */
	public int getQ_attack(){
		return q_attack;
	}
	
	/**
	 * set 攻击
	 */
	public void setQ_attack(int q_attack){
		this.q_attack = q_attack;
	}
	
	/**
	 * get 防御
	 * @return 
	 */
	public int getQ_defence(){
		return q_defence;
	}
	
	/**
	 * set 防御
	 */
	public void setQ_defence(int q_defence){
		this.q_defence = q_defence;
	}
	
	/**
	 * get 暴击
	 * @return 
	 */
	public int getQ_crit(){
		return q_crit;
	}
	
	/**
	 * set 暴击
	 */
	public void setQ_crit(int q_crit){
		this.q_crit = q_crit;
	}
	
	/**
	 * get 闪避
	 * @return 
	 */
	public int getQ_dodge(){
		return q_dodge;
	}
	
	/**
	 * set 闪避
	 */
	public void setQ_dodge(int q_dodge){
		this.q_dodge = q_dodge;
	}
	
	/**
	 * get 血量
	 * @return 
	 */
	public int getQ_maxhp(){
		return q_maxhp;
	}
	
	/**
	 * set 血量
	 */
	public void setQ_maxhp(int q_maxhp){
		this.q_maxhp = q_maxhp;
	}
	
	/**
	 * get 内力
	 * @return 
	 */
	public int getQ_maxmp(){
		return q_maxmp;
	}
	
	/**
	 * set 内力
	 */
	public void setQ_maxmp(int q_maxmp){
		this.q_maxmp = q_maxmp;
	}
	
	/**
	 * get 体力
	 * @return 
	 */
	public int getQ_maxsp(){
		return q_maxsp;
	}
	
	/**
	 * set 体力
	 */
	public void setQ_maxsp(int q_maxsp){
		this.q_maxsp = q_maxsp;
	}
	
	/**
	 * get 攻击速度
	 * @return 
	 */
	public int getQ_attackspeed(){
		return q_attackspeed;
	}
	
	/**
	 * set 攻击速度
	 */
	public void setQ_attackspeed(int q_attackspeed){
		this.q_attackspeed = q_attackspeed;
	}
	
	/**
	 * get 移动速度
	 * @return 
	 */
	public int getQ_speed(){
		return q_speed;
	}
	
	/**
	 * set 移动速度
	 */
	public void setQ_speed(int q_speed){
		this.q_speed = q_speed;
	}
	
	/**
	 * get 幸运
	 * @return 
	 */
	public int getQ_luck(){
		return q_luck;
	}
	
	/**
	 * set 幸运
	 */
	public void setQ_luck(int q_luck){
		this.q_luck = q_luck;
	}
	
	/**
	 * get 达到军衔发送公告
	 * @return 
	 */
	public int getQ_bulletin(){
		return q_bulletin;
	}
	
	/**
	 * set 达到军衔发送公告
	 */
	public void setQ_bulletin(int q_bulletin){
		this.q_bulletin = q_bulletin;
	}
	
	/**
	 * get 是否开启，1开启，0关闭
	 * @return 
	 */
	public int getQ_switch(){
		return q_switch;
	}
	
	/**
	 * set 是否开启，1开启，0关闭
	 */
	public void setQ_switch(int q_switch){
		this.q_switch = q_switch;
	}
	
}