package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_pets Bean
 */
public class Q_petsBean {

	//美人ID
	private String q_id;
	
	//宠物模型
	private int q_model_id;
	
	//等级
	private int q_level;
	
	//美人名称
	private String q_name;
	
	//召唤军功条件
	private int q_rank_cond;
	
	//召唤消耗声望
	private int q_gain_resume_sw;
	
	//召唤需要完成任务
	private int q_gain_need_task;
	
	//召唤需要人物等级
	private int q_gain_need_grade;
	
	//召唤消耗金币
	private int q_gain_resume_money;
	
	//召唤需要已获得美人
	private int q_gain_needpet;
	
	//造型资源编号
	private int q_body_resid;
	
	//头像资源编号
	private int q_head_resid;
	
	//攻击时音效编号
	private int q_shoud_id;
	
	//被攻击时音效编号
	private int q_shoud_id2;
	
	//死亡时音效编号
	private int q_shoud_id3;
	
	//生命
	private int q_maxhp;
	
	//攻击
	private int q_attack;
	
	//攻击速度
	private int q_attack_speed;
	
	//暴击
	private int q_crit;
	
	//闪避
	private int q_dodge;
	
	//被攻击固定少血
	private int q_fixresume_hp;
	
	//合体增加生命
	private int q_ht_addhp;
	
	//合体增加内力
	private int q_ht_addmp;
	
	//合体增加攻击
	private int q_ht_addattack;
	
	//合体增加防御
	private int q_ht_adddefence;
	
	//合体增加暴击
	private int q_ht_addcrit;
	
	//合体增加闪避
	private int q_ht_adddodge;
	
	//默认技能编号
	private int q_default_skillId;
	
	//特殊技能几率
	private int q_skill_prob;
	
	//美人重生时间
	private int q_revive_time;
	
	//自动回血（5秒一次）
	private int q_recovery_hp;
	
	//自动回蓝（5秒一次）
	private int q_recovery_mp;
	
	//可选技能
	private String q_skill;
	
	//携带技能格数量
	private int q_max_skills;
	
	//切换技能需消耗材料数量
	private int q_changeskill_resume;
	
	//合体冷却时长
	private int q_ht_cd;
	
	
	/**
	 * get 美人ID
	 * @return 
	 */
	public String getQ_id(){
		return q_id;
	}
	
	/**
	 * set 美人ID
	 */
	public void setQ_id(String q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 宠物模型
	 * @return 
	 */
	public int getQ_model_id(){
		return q_model_id;
	}
	
	/**
	 * set 宠物模型
	 */
	public void setQ_model_id(int q_model_id){
		this.q_model_id = q_model_id;
	}
	
	/**
	 * get 等级
	 * @return 
	 */
	public int getQ_level(){
		return q_level;
	}
	
	/**
	 * set 等级
	 */
	public void setQ_level(int q_level){
		this.q_level = q_level;
	}
	
	/**
	 * get 美人名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 美人名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 召唤军功条件
	 * @return 
	 */
	public int getQ_rank_cond(){
		return q_rank_cond;
	}
	
	/**
	 * set 召唤军功条件
	 */
	public void setQ_rank_cond(int q_rank_cond){
		this.q_rank_cond = q_rank_cond;
	}
	
	/**
	 * get 召唤消耗声望
	 * @return 
	 */
	public int getQ_gain_resume_sw(){
		return q_gain_resume_sw;
	}
	
	/**
	 * set 召唤消耗声望
	 */
	public void setQ_gain_resume_sw(int q_gain_resume_sw){
		this.q_gain_resume_sw = q_gain_resume_sw;
	}
	
	/**
	 * get 召唤需要完成任务
	 * @return 
	 */
	public int getQ_gain_need_task(){
		return q_gain_need_task;
	}
	
	/**
	 * set 召唤需要完成任务
	 */
	public void setQ_gain_need_task(int q_gain_need_task){
		this.q_gain_need_task = q_gain_need_task;
	}
	
	/**
	 * get 召唤需要人物等级
	 * @return 
	 */
	public int getQ_gain_need_grade(){
		return q_gain_need_grade;
	}
	
	/**
	 * set 召唤需要人物等级
	 */
	public void setQ_gain_need_grade(int q_gain_need_grade){
		this.q_gain_need_grade = q_gain_need_grade;
	}
	
	/**
	 * get 召唤消耗金币
	 * @return 
	 */
	public int getQ_gain_resume_money(){
		return q_gain_resume_money;
	}
	
	/**
	 * set 召唤消耗金币
	 */
	public void setQ_gain_resume_money(int q_gain_resume_money){
		this.q_gain_resume_money = q_gain_resume_money;
	}
	
	/**
	 * get 召唤需要已获得美人
	 * @return 
	 */
	public int getQ_gain_needpet(){
		return q_gain_needpet;
	}
	
	/**
	 * set 召唤需要已获得美人
	 */
	public void setQ_gain_needpet(int q_gain_needpet){
		this.q_gain_needpet = q_gain_needpet;
	}
	
	/**
	 * get 造型资源编号
	 * @return 
	 */
	public int getQ_body_resid(){
		return q_body_resid;
	}
	
	/**
	 * set 造型资源编号
	 */
	public void setQ_body_resid(int q_body_resid){
		this.q_body_resid = q_body_resid;
	}
	
	/**
	 * get 头像资源编号
	 * @return 
	 */
	public int getQ_head_resid(){
		return q_head_resid;
	}
	
	/**
	 * set 头像资源编号
	 */
	public void setQ_head_resid(int q_head_resid){
		this.q_head_resid = q_head_resid;
	}
	
	/**
	 * get 攻击时音效编号
	 * @return 
	 */
	public int getQ_shoud_id(){
		return q_shoud_id;
	}
	
	/**
	 * set 攻击时音效编号
	 */
	public void setQ_shoud_id(int q_shoud_id){
		this.q_shoud_id = q_shoud_id;
	}
	
	/**
	 * get 被攻击时音效编号
	 * @return 
	 */
	public int getQ_shoud_id2(){
		return q_shoud_id2;
	}
	
	/**
	 * set 被攻击时音效编号
	 */
	public void setQ_shoud_id2(int q_shoud_id2){
		this.q_shoud_id2 = q_shoud_id2;
	}
	
	/**
	 * get 死亡时音效编号
	 * @return 
	 */
	public int getQ_shoud_id3(){
		return q_shoud_id3;
	}
	
	/**
	 * set 死亡时音效编号
	 */
	public void setQ_shoud_id3(int q_shoud_id3){
		this.q_shoud_id3 = q_shoud_id3;
	}
	
	/**
	 * get 生命
	 * @return 
	 */
	public int getQ_maxhp(){
		return q_maxhp;
	}
	
	/**
	 * set 生命
	 */
	public void setQ_maxhp(int q_maxhp){
		this.q_maxhp = q_maxhp;
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
	 * get 攻击速度
	 * @return 
	 */
	public int getQ_attack_speed(){
		return q_attack_speed;
	}
	
	/**
	 * set 攻击速度
	 */
	public void setQ_attack_speed(int q_attack_speed){
		this.q_attack_speed = q_attack_speed;
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
	 * get 被攻击固定少血
	 * @return 
	 */
	public int getQ_fixresume_hp(){
		return q_fixresume_hp;
	}
	
	/**
	 * set 被攻击固定少血
	 */
	public void setQ_fixresume_hp(int q_fixresume_hp){
		this.q_fixresume_hp = q_fixresume_hp;
	}
	
	/**
	 * get 合体增加生命
	 * @return 
	 */
	public int getQ_ht_addhp(){
		return q_ht_addhp;
	}
	
	/**
	 * set 合体增加生命
	 */
	public void setQ_ht_addhp(int q_ht_addhp){
		this.q_ht_addhp = q_ht_addhp;
	}
	
	/**
	 * get 合体增加内力
	 * @return 
	 */
	public int getQ_ht_addmp(){
		return q_ht_addmp;
	}
	
	/**
	 * set 合体增加内力
	 */
	public void setQ_ht_addmp(int q_ht_addmp){
		this.q_ht_addmp = q_ht_addmp;
	}
	
	/**
	 * get 合体增加攻击
	 * @return 
	 */
	public int getQ_ht_addattack(){
		return q_ht_addattack;
	}
	
	/**
	 * set 合体增加攻击
	 */
	public void setQ_ht_addattack(int q_ht_addattack){
		this.q_ht_addattack = q_ht_addattack;
	}
	
	/**
	 * get 合体增加防御
	 * @return 
	 */
	public int getQ_ht_adddefence(){
		return q_ht_adddefence;
	}
	
	/**
	 * set 合体增加防御
	 */
	public void setQ_ht_adddefence(int q_ht_adddefence){
		this.q_ht_adddefence = q_ht_adddefence;
	}
	
	/**
	 * get 合体增加暴击
	 * @return 
	 */
	public int getQ_ht_addcrit(){
		return q_ht_addcrit;
	}
	
	/**
	 * set 合体增加暴击
	 */
	public void setQ_ht_addcrit(int q_ht_addcrit){
		this.q_ht_addcrit = q_ht_addcrit;
	}
	
	/**
	 * get 合体增加闪避
	 * @return 
	 */
	public int getQ_ht_adddodge(){
		return q_ht_adddodge;
	}
	
	/**
	 * set 合体增加闪避
	 */
	public void setQ_ht_adddodge(int q_ht_adddodge){
		this.q_ht_adddodge = q_ht_adddodge;
	}
	
	/**
	 * get 默认技能编号
	 * @return 
	 */
	public int getQ_default_skillId(){
		return q_default_skillId;
	}
	
	/**
	 * set 默认技能编号
	 */
	public void setQ_default_skillId(int q_default_skillId){
		this.q_default_skillId = q_default_skillId;
	}
	
	/**
	 * get 特殊技能几率
	 * @return 
	 */
	public int getQ_skill_prob(){
		return q_skill_prob;
	}
	
	/**
	 * set 特殊技能几率
	 */
	public void setQ_skill_prob(int q_skill_prob){
		this.q_skill_prob = q_skill_prob;
	}
	
	/**
	 * get 美人重生时间
	 * @return 
	 */
	public int getQ_revive_time(){
		return q_revive_time;
	}
	
	/**
	 * set 美人重生时间
	 */
	public void setQ_revive_time(int q_revive_time){
		this.q_revive_time = q_revive_time;
	}
	
	/**
	 * get 自动回血（5秒一次）
	 * @return 
	 */
	public int getQ_recovery_hp(){
		return q_recovery_hp;
	}
	
	/**
	 * set 自动回血（5秒一次）
	 */
	public void setQ_recovery_hp(int q_recovery_hp){
		this.q_recovery_hp = q_recovery_hp;
	}
	
	/**
	 * get 自动回蓝（5秒一次）
	 * @return 
	 */
	public int getQ_recovery_mp(){
		return q_recovery_mp;
	}
	
	/**
	 * set 自动回蓝（5秒一次）
	 */
	public void setQ_recovery_mp(int q_recovery_mp){
		this.q_recovery_mp = q_recovery_mp;
	}
	
	/**
	 * get 可选技能
	 * @return 
	 */
	public String getQ_skill(){
		return q_skill;
	}
	
	/**
	 * set 可选技能
	 */
	public void setQ_skill(String q_skill){
		this.q_skill = q_skill;
	}
	
	/**
	 * get 携带技能格数量
	 * @return 
	 */
	public int getQ_max_skills(){
		return q_max_skills;
	}
	
	/**
	 * set 携带技能格数量
	 */
	public void setQ_max_skills(int q_max_skills){
		this.q_max_skills = q_max_skills;
	}
	
	/**
	 * get 切换技能需消耗材料数量
	 * @return 
	 */
	public int getQ_changeskill_resume(){
		return q_changeskill_resume;
	}
	
	/**
	 * set 切换技能需消耗材料数量
	 */
	public void setQ_changeskill_resume(int q_changeskill_resume){
		this.q_changeskill_resume = q_changeskill_resume;
	}
	
	/**
	 * get 合体冷却时长
	 * @return 
	 */
	public int getQ_ht_cd(){
		return q_ht_cd;
	}
	
	/**
	 * set 合体冷却时长
	 */
	public void setQ_ht_cd(int q_ht_cd){
		this.q_ht_cd = q_ht_cd;
	}
	
}