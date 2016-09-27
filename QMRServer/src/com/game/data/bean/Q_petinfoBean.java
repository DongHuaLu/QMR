package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_petinfo Bean
 */
public class Q_petinfoBean {

	//侍宠ID
	private int q_model_id;
	
	//侍宠名称
	private String q_name;
	
	//显示需要人物等级
	private int q_level;
	
	//钦点军功条件
	private int q_rank_cond;
	
	//钦点需要已获得侍宠
	private int q_got_needpet;
	
	//钦点需要人物等级
	private int q_got_needlevel;
	
	//钦点需要完成任务
	private int q_got_need_task;
	
	//钦点消耗材料编号_数量;材料编号_数量
	private String q_got_resumeitem;
	
	//钦点消耗材料数量
	private int q_got_resumenum;
	
	//钦点消耗声望
	private int q_got_resume_sw;
	
	//钦点消耗铜币
	private int q_got_resume_gold;
	
	//钦点成功是否公告（0不公告，1公告）
	private int q_got_notice;
	
	//侍宠可升级至最大等级
	private int q_pet_maxlevel;
	
	//侍宠场景造型资源编号
	private String q_res_scene_id;
	
	//侍宠展示面板造型资源编号
	private String q_res_panel_id;
	
	//情诗资源编号
	private int q_res_poetry_id;
	
	//头像资源编号
	private int q_res_head_id;
	
	//攻击时音效编号
	private int q_sound_attack_id;
	
	//被攻击时音效编号
	private int q_sound_defence_id;
	
	//死亡时音效编号
	private int q_sound_die_id;
	
	//普通合体增加属性次数
	private int q_ht_count;
	
	//每日合体次数
	private int q_ht_daycount;
	
	//首次合体增加攻击
	private int q_ht_firstattack;
	
	//首次合体增加防御
	private int q_ht_firstdefence;
	
	//首次合体增加暴击
	private int q_ht_firstcrit;
	
	//首次合体增加闪避
	private int q_ht_firstdodge;
	
	//首次合体增加生命
	private int q_ht_firsthp;
	
	//首次合体增加内力
	private int q_ht_firstmp;
	
	//普通合体增加攻击
	private int q_ht_addattack;
	
	//普通合体增加防御
	private int q_ht_adddefence;
	
	//普通合体增加暴击
	private int q_ht_addcrit;
	
	//普通合体增加闪避
	private int q_ht_adddodge;
	
	//普通合体增加生命
	private int q_ht_addhp;
	
	//普通合体增加内力
	private int q_ht_addmp;
	
	//首次合体增加经验脚本编号
	private int q_ht_first_exp_script;
	
	//普通合体增加经验脚本编号
	private int q_ht_exp_script;
	
	//合体消耗游戏铜币
	private int q_ht_resume_copper;
	
	//合体消耗材料编号
	private int q_ht_resume_item;
	
	//合体消耗材料数量
	private int q_ht_resume_num;
	
	//合体冷却时长（毫秒）
	private int q_ht_cooldown;
	
	//清除冷却时间时单位时间（1分钟）消耗元宝数量
	private int q_ht_clearcd_gold;
	
	//被攻击固定少血
	private int q_fiexd_value;
	
	//是否可以切换技能(1可以切换，0不能切换)
	private int q_iscgs;
	
	//切换技能所需VIP等级
	private int q_cgs_need_viplevel;
	
	//侍宠主动技能ID（单攻）
	private int q_skill_single;
	
	//侍宠主动技能ID（群攻）
	private int q_skill_multi;
	
	//侍宠天赋技能
	private int q_skill;
	
	//可切换技能
	private String q_ablecg_skill;
	
	//携带技能格数
	private int q_skill_num;
	
	//侍宠重生时间(毫秒)
	private int q_revive_time;
	
	//侍宠发言[{"侍宠出战":"亲爱的我来啦！","发言万分比概率":1000},{"侍宠休息":"亲爱的，我走了呦，我不在的日子你要好好照顾自己","发言万分比概率":10000}]
	private String q_chat_ai;
	
	//侍宠品质
	private int q_quality;
	
	
	/**
	 * get 侍宠ID
	 * @return 
	 */
	public int getQ_model_id(){
		return q_model_id;
	}
	
	/**
	 * set 侍宠ID
	 */
	public void setQ_model_id(int q_model_id){
		this.q_model_id = q_model_id;
	}
	
	/**
	 * get 侍宠名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 侍宠名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 显示需要人物等级
	 * @return 
	 */
	public int getQ_level(){
		return q_level;
	}
	
	/**
	 * set 显示需要人物等级
	 */
	public void setQ_level(int q_level){
		this.q_level = q_level;
	}
	
	/**
	 * get 钦点军功条件
	 * @return 
	 */
	public int getQ_rank_cond(){
		return q_rank_cond;
	}
	
	/**
	 * set 钦点军功条件
	 */
	public void setQ_rank_cond(int q_rank_cond){
		this.q_rank_cond = q_rank_cond;
	}
	
	/**
	 * get 钦点需要已获得侍宠
	 * @return 
	 */
	public int getQ_got_needpet(){
		return q_got_needpet;
	}
	
	/**
	 * set 钦点需要已获得侍宠
	 */
	public void setQ_got_needpet(int q_got_needpet){
		this.q_got_needpet = q_got_needpet;
	}
	
	/**
	 * get 钦点需要人物等级
	 * @return 
	 */
	public int getQ_got_needlevel(){
		return q_got_needlevel;
	}
	
	/**
	 * set 钦点需要人物等级
	 */
	public void setQ_got_needlevel(int q_got_needlevel){
		this.q_got_needlevel = q_got_needlevel;
	}
	
	/**
	 * get 钦点需要完成任务
	 * @return 
	 */
	public int getQ_got_need_task(){
		return q_got_need_task;
	}
	
	/**
	 * set 钦点需要完成任务
	 */
	public void setQ_got_need_task(int q_got_need_task){
		this.q_got_need_task = q_got_need_task;
	}
	
	/**
	 * get 钦点消耗材料编号_数量;材料编号_数量
	 * @return 
	 */
	public String getQ_got_resumeitem(){
		return q_got_resumeitem;
	}
	
	/**
	 * set 钦点消耗材料编号_数量;材料编号_数量
	 */
	public void setQ_got_resumeitem(String q_got_resumeitem){
		this.q_got_resumeitem = q_got_resumeitem;
	}
	
	/**
	 * get 钦点消耗材料数量
	 * @return 
	 */
	public int getQ_got_resumenum(){
		return q_got_resumenum;
	}
	
	/**
	 * set 钦点消耗材料数量
	 */
	public void setQ_got_resumenum(int q_got_resumenum){
		this.q_got_resumenum = q_got_resumenum;
	}
	
	/**
	 * get 钦点消耗声望
	 * @return 
	 */
	public int getQ_got_resume_sw(){
		return q_got_resume_sw;
	}
	
	/**
	 * set 钦点消耗声望
	 */
	public void setQ_got_resume_sw(int q_got_resume_sw){
		this.q_got_resume_sw = q_got_resume_sw;
	}
	
	/**
	 * get 钦点消耗铜币
	 * @return 
	 */
	public int getQ_got_resume_gold(){
		return q_got_resume_gold;
	}
	
	/**
	 * set 钦点消耗铜币
	 */
	public void setQ_got_resume_gold(int q_got_resume_gold){
		this.q_got_resume_gold = q_got_resume_gold;
	}
	
	/**
	 * get 钦点成功是否公告（0不公告，1公告）
	 * @return 
	 */
	public int getQ_got_notice(){
		return q_got_notice;
	}
	
	/**
	 * set 钦点成功是否公告（0不公告，1公告）
	 */
	public void setQ_got_notice(int q_got_notice){
		this.q_got_notice = q_got_notice;
	}
	
	/**
	 * get 侍宠可升级至最大等级
	 * @return 
	 */
	public int getQ_pet_maxlevel(){
		return q_pet_maxlevel;
	}
	
	/**
	 * set 侍宠可升级至最大等级
	 */
	public void setQ_pet_maxlevel(int q_pet_maxlevel){
		this.q_pet_maxlevel = q_pet_maxlevel;
	}
	
	/**
	 * get 侍宠场景造型资源编号
	 * @return 
	 */
	public String getQ_res_scene_id(){
		return q_res_scene_id;
	}
	
	/**
	 * set 侍宠场景造型资源编号
	 */
	public void setQ_res_scene_id(String q_res_scene_id){
		this.q_res_scene_id = q_res_scene_id;
	}
	
	/**
	 * get 侍宠展示面板造型资源编号
	 * @return 
	 */
	public String getQ_res_panel_id(){
		return q_res_panel_id;
	}
	
	/**
	 * set 侍宠展示面板造型资源编号
	 */
	public void setQ_res_panel_id(String q_res_panel_id){
		this.q_res_panel_id = q_res_panel_id;
	}
	
	/**
	 * get 情诗资源编号
	 * @return 
	 */
	public int getQ_res_poetry_id(){
		return q_res_poetry_id;
	}
	
	/**
	 * set 情诗资源编号
	 */
	public void setQ_res_poetry_id(int q_res_poetry_id){
		this.q_res_poetry_id = q_res_poetry_id;
	}
	
	/**
	 * get 头像资源编号
	 * @return 
	 */
	public int getQ_res_head_id(){
		return q_res_head_id;
	}
	
	/**
	 * set 头像资源编号
	 */
	public void setQ_res_head_id(int q_res_head_id){
		this.q_res_head_id = q_res_head_id;
	}
	
	/**
	 * get 攻击时音效编号
	 * @return 
	 */
	public int getQ_sound_attack_id(){
		return q_sound_attack_id;
	}
	
	/**
	 * set 攻击时音效编号
	 */
	public void setQ_sound_attack_id(int q_sound_attack_id){
		this.q_sound_attack_id = q_sound_attack_id;
	}
	
	/**
	 * get 被攻击时音效编号
	 * @return 
	 */
	public int getQ_sound_defence_id(){
		return q_sound_defence_id;
	}
	
	/**
	 * set 被攻击时音效编号
	 */
	public void setQ_sound_defence_id(int q_sound_defence_id){
		this.q_sound_defence_id = q_sound_defence_id;
	}
	
	/**
	 * get 死亡时音效编号
	 * @return 
	 */
	public int getQ_sound_die_id(){
		return q_sound_die_id;
	}
	
	/**
	 * set 死亡时音效编号
	 */
	public void setQ_sound_die_id(int q_sound_die_id){
		this.q_sound_die_id = q_sound_die_id;
	}
	
	/**
	 * get 普通合体增加属性次数
	 * @return 
	 */
	public int getQ_ht_count(){
		return q_ht_count;
	}
	
	/**
	 * set 普通合体增加属性次数
	 */
	public void setQ_ht_count(int q_ht_count){
		this.q_ht_count = q_ht_count;
	}
	
	/**
	 * get 每日合体次数
	 * @return 
	 */
	public int getQ_ht_daycount(){
		return q_ht_daycount;
	}
	
	/**
	 * set 每日合体次数
	 */
	public void setQ_ht_daycount(int q_ht_daycount){
		this.q_ht_daycount = q_ht_daycount;
	}
	
	/**
	 * get 首次合体增加攻击
	 * @return 
	 */
	public int getQ_ht_firstattack(){
		return q_ht_firstattack;
	}
	
	/**
	 * set 首次合体增加攻击
	 */
	public void setQ_ht_firstattack(int q_ht_firstattack){
		this.q_ht_firstattack = q_ht_firstattack;
	}
	
	/**
	 * get 首次合体增加防御
	 * @return 
	 */
	public int getQ_ht_firstdefence(){
		return q_ht_firstdefence;
	}
	
	/**
	 * set 首次合体增加防御
	 */
	public void setQ_ht_firstdefence(int q_ht_firstdefence){
		this.q_ht_firstdefence = q_ht_firstdefence;
	}
	
	/**
	 * get 首次合体增加暴击
	 * @return 
	 */
	public int getQ_ht_firstcrit(){
		return q_ht_firstcrit;
	}
	
	/**
	 * set 首次合体增加暴击
	 */
	public void setQ_ht_firstcrit(int q_ht_firstcrit){
		this.q_ht_firstcrit = q_ht_firstcrit;
	}
	
	/**
	 * get 首次合体增加闪避
	 * @return 
	 */
	public int getQ_ht_firstdodge(){
		return q_ht_firstdodge;
	}
	
	/**
	 * set 首次合体增加闪避
	 */
	public void setQ_ht_firstdodge(int q_ht_firstdodge){
		this.q_ht_firstdodge = q_ht_firstdodge;
	}
	
	/**
	 * get 首次合体增加生命
	 * @return 
	 */
	public int getQ_ht_firsthp(){
		return q_ht_firsthp;
	}
	
	/**
	 * set 首次合体增加生命
	 */
	public void setQ_ht_firsthp(int q_ht_firsthp){
		this.q_ht_firsthp = q_ht_firsthp;
	}
	
	/**
	 * get 首次合体增加内力
	 * @return 
	 */
	public int getQ_ht_firstmp(){
		return q_ht_firstmp;
	}
	
	/**
	 * set 首次合体增加内力
	 */
	public void setQ_ht_firstmp(int q_ht_firstmp){
		this.q_ht_firstmp = q_ht_firstmp;
	}
	
	/**
	 * get 普通合体增加攻击
	 * @return 
	 */
	public int getQ_ht_addattack(){
		return q_ht_addattack;
	}
	
	/**
	 * set 普通合体增加攻击
	 */
	public void setQ_ht_addattack(int q_ht_addattack){
		this.q_ht_addattack = q_ht_addattack;
	}
	
	/**
	 * get 普通合体增加防御
	 * @return 
	 */
	public int getQ_ht_adddefence(){
		return q_ht_adddefence;
	}
	
	/**
	 * set 普通合体增加防御
	 */
	public void setQ_ht_adddefence(int q_ht_adddefence){
		this.q_ht_adddefence = q_ht_adddefence;
	}
	
	/**
	 * get 普通合体增加暴击
	 * @return 
	 */
	public int getQ_ht_addcrit(){
		return q_ht_addcrit;
	}
	
	/**
	 * set 普通合体增加暴击
	 */
	public void setQ_ht_addcrit(int q_ht_addcrit){
		this.q_ht_addcrit = q_ht_addcrit;
	}
	
	/**
	 * get 普通合体增加闪避
	 * @return 
	 */
	public int getQ_ht_adddodge(){
		return q_ht_adddodge;
	}
	
	/**
	 * set 普通合体增加闪避
	 */
	public void setQ_ht_adddodge(int q_ht_adddodge){
		this.q_ht_adddodge = q_ht_adddodge;
	}
	
	/**
	 * get 普通合体增加生命
	 * @return 
	 */
	public int getQ_ht_addhp(){
		return q_ht_addhp;
	}
	
	/**
	 * set 普通合体增加生命
	 */
	public void setQ_ht_addhp(int q_ht_addhp){
		this.q_ht_addhp = q_ht_addhp;
	}
	
	/**
	 * get 普通合体增加内力
	 * @return 
	 */
	public int getQ_ht_addmp(){
		return q_ht_addmp;
	}
	
	/**
	 * set 普通合体增加内力
	 */
	public void setQ_ht_addmp(int q_ht_addmp){
		this.q_ht_addmp = q_ht_addmp;
	}
	
	/**
	 * get 首次合体增加经验脚本编号
	 * @return 
	 */
	public int getQ_ht_first_exp_script(){
		return q_ht_first_exp_script;
	}
	
	/**
	 * set 首次合体增加经验脚本编号
	 */
	public void setQ_ht_first_exp_script(int q_ht_first_exp_script){
		this.q_ht_first_exp_script = q_ht_first_exp_script;
	}
	
	/**
	 * get 普通合体增加经验脚本编号
	 * @return 
	 */
	public int getQ_ht_exp_script(){
		return q_ht_exp_script;
	}
	
	/**
	 * set 普通合体增加经验脚本编号
	 */
	public void setQ_ht_exp_script(int q_ht_exp_script){
		this.q_ht_exp_script = q_ht_exp_script;
	}
	
	/**
	 * get 合体消耗游戏铜币
	 * @return 
	 */
	public int getQ_ht_resume_copper(){
		return q_ht_resume_copper;
	}
	
	/**
	 * set 合体消耗游戏铜币
	 */
	public void setQ_ht_resume_copper(int q_ht_resume_copper){
		this.q_ht_resume_copper = q_ht_resume_copper;
	}
	
	/**
	 * get 合体消耗材料编号
	 * @return 
	 */
	public int getQ_ht_resume_item(){
		return q_ht_resume_item;
	}
	
	/**
	 * set 合体消耗材料编号
	 */
	public void setQ_ht_resume_item(int q_ht_resume_item){
		this.q_ht_resume_item = q_ht_resume_item;
	}
	
	/**
	 * get 合体消耗材料数量
	 * @return 
	 */
	public int getQ_ht_resume_num(){
		return q_ht_resume_num;
	}
	
	/**
	 * set 合体消耗材料数量
	 */
	public void setQ_ht_resume_num(int q_ht_resume_num){
		this.q_ht_resume_num = q_ht_resume_num;
	}
	
	/**
	 * get 合体冷却时长（毫秒）
	 * @return 
	 */
	public int getQ_ht_cooldown(){
		return q_ht_cooldown;
	}
	
	/**
	 * set 合体冷却时长（毫秒）
	 */
	public void setQ_ht_cooldown(int q_ht_cooldown){
		this.q_ht_cooldown = q_ht_cooldown;
	}
	
	/**
	 * get 清除冷却时间时单位时间（1分钟）消耗元宝数量
	 * @return 
	 */
	public int getQ_ht_clearcd_gold(){
		return q_ht_clearcd_gold;
	}
	
	/**
	 * set 清除冷却时间时单位时间（1分钟）消耗元宝数量
	 */
	public void setQ_ht_clearcd_gold(int q_ht_clearcd_gold){
		this.q_ht_clearcd_gold = q_ht_clearcd_gold;
	}
	
	/**
	 * get 被攻击固定少血
	 * @return 
	 */
	public int getQ_fiexd_value(){
		return q_fiexd_value;
	}
	
	/**
	 * set 被攻击固定少血
	 */
	public void setQ_fiexd_value(int q_fiexd_value){
		this.q_fiexd_value = q_fiexd_value;
	}
	
	/**
	 * get 是否可以切换技能(1可以切换，0不能切换)
	 * @return 
	 */
	public int getQ_iscgs(){
		return q_iscgs;
	}
	
	/**
	 * set 是否可以切换技能(1可以切换，0不能切换)
	 */
	public void setQ_iscgs(int q_iscgs){
		this.q_iscgs = q_iscgs;
	}
	
	/**
	 * get 切换技能所需VIP等级
	 * @return 
	 */
	public int getQ_cgs_need_viplevel(){
		return q_cgs_need_viplevel;
	}
	
	/**
	 * set 切换技能所需VIP等级
	 */
	public void setQ_cgs_need_viplevel(int q_cgs_need_viplevel){
		this.q_cgs_need_viplevel = q_cgs_need_viplevel;
	}
	
	/**
	 * get 侍宠主动技能ID（单攻）
	 * @return 
	 */
	public int getQ_skill_single(){
		return q_skill_single;
	}
	
	/**
	 * set 侍宠主动技能ID（单攻）
	 */
	public void setQ_skill_single(int q_skill_single){
		this.q_skill_single = q_skill_single;
	}
	
	/**
	 * get 侍宠主动技能ID（群攻）
	 * @return 
	 */
	public int getQ_skill_multi(){
		return q_skill_multi;
	}
	
	/**
	 * set 侍宠主动技能ID（群攻）
	 */
	public void setQ_skill_multi(int q_skill_multi){
		this.q_skill_multi = q_skill_multi;
	}
	
	/**
	 * get 侍宠天赋技能
	 * @return 
	 */
	public int getQ_skill(){
		return q_skill;
	}
	
	/**
	 * set 侍宠天赋技能
	 */
	public void setQ_skill(int q_skill){
		this.q_skill = q_skill;
	}
	
	/**
	 * get 可切换技能
	 * @return 
	 */
	public String getQ_ablecg_skill(){
		return q_ablecg_skill;
	}
	
	/**
	 * set 可切换技能
	 */
	public void setQ_ablecg_skill(String q_ablecg_skill){
		this.q_ablecg_skill = q_ablecg_skill;
	}
	
	/**
	 * get 携带技能格数
	 * @return 
	 */
	public int getQ_skill_num(){
		return q_skill_num;
	}
	
	/**
	 * set 携带技能格数
	 */
	public void setQ_skill_num(int q_skill_num){
		this.q_skill_num = q_skill_num;
	}
	
	/**
	 * get 侍宠重生时间(毫秒)
	 * @return 
	 */
	public int getQ_revive_time(){
		return q_revive_time;
	}
	
	/**
	 * set 侍宠重生时间(毫秒)
	 */
	public void setQ_revive_time(int q_revive_time){
		this.q_revive_time = q_revive_time;
	}
	
	/**
	 * get 侍宠发言[{"侍宠出战":"亲爱的我来啦！","发言万分比概率":1000},{"侍宠休息":"亲爱的，我走了呦，我不在的日子你要好好照顾自己","发言万分比概率":10000}]
	 * @return 
	 */
	public String getQ_chat_ai(){
		return q_chat_ai;
	}
	
	/**
	 * set 侍宠发言[{"侍宠出战":"亲爱的我来啦！","发言万分比概率":1000},{"侍宠休息":"亲爱的，我走了呦，我不在的日子你要好好照顾自己","发言万分比概率":10000}]
	 */
	public void setQ_chat_ai(String q_chat_ai){
		this.q_chat_ai = q_chat_ai;
	}
	
	/**
	 * get 侍宠品质
	 * @return 
	 */
	public int getQ_quality(){
		return q_quality;
	}
	
	/**
	 * set 侍宠品质
	 */
	public void setQ_quality(int q_quality){
		this.q_quality = q_quality;
	}
	
}