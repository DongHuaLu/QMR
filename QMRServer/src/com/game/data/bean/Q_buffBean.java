package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_buff Bean
 */
public class Q_buffBean {

	//BUFF编号
	private int q_buff_id;
	
	//BUFF名称
	private String q_buff_name;
	
	//获得BUFF时向获得者发送提示信息
	private String q_effect_prompt;
	
	//作用目标（1自己，2目标，3自己与目标，4自己和队友,5主人）
	private int q_target;
	
	//获得BUFF时向施加者发送提示信息
	private String q_user_prompt;
	
	//BUFF广播类型（0隐藏式BUFF，1显示给自己知道的BUFF，2广播给自己和其他玩家知道的BUFF）
	private int q_cast_type;
	
	//损益类型（0无所谓，1正面BUFF，2负面BUFF）
	private int q_effect_type;
	
	//效果类型
	private int q_action_type;
	
	//效果添加成功几率（本处填万分比的分子）
	private int q_trigger_prob;
	
	//效果作用数值（允许负值）
	private int q_effect_value;
	
	//效果作用比例（本处填万分比的分子）（允许负值）
	private int q_effect_ratio;
	
	//效果的总持续时间（单位：秒）（填-1为永久生效）
	private int q_effect_time;
	
	//效果的总容量
	private int q_effect_maxvalue;
	
	//效果分次作用间隔时间（单位：毫秒）
	private int q_effect_cooldown;
	
	//效果重复选项（1效果叠加，2效果替换，3容量叠加,4时间叠加，5重复无效）
	private int q_overlay;
	
	//叠加次数上限（-1为无限）
	private int q_overlay_maxcount;
	
	//替换层级
	private int q_replace_level;
	
	//获得者死亡或下线后是否清除（0不清除，1清除）
	private int q_effect_dieordown_clear;
	
	//施加者死亡或下线后是否清除（0不清除，1清除）
	private int q_user_dieordown_clear;
	
	//获得BUFF特效编号
	private String q_small_ico;
	
	//BUFF图标（36*36）
	private String q_ico;
	
	//显示于BUFF获得者身上的状态SWF动画编号（尺寸：24*24）
	private String q_swf;
	
	//鼠标TIPS界面上的描述信息（支持Html）
	private String q_tips;
	
	//BUFF参数，技能ID、技能ID，【攻击：X,防御:X,闪避:X,暴击:X,幸运:X,生命上限:X,内力上限:X,体力上限:X,攻击速度:X,移动速度:X,攻击比例：万分比,经验比例：X，真气比例：X。】
	private String q_Bonus_skill;
	
	//下线是否计时（0不是，1是）
	private int q_Line_time;
	
	//触发几率
	private int q_trigger_probability;
	
	//是否受VIP时间加成（1是，0否）
	private int q_vip_bonus;
	
	//脚本BUFFID
	private int q_script_id;
	
	//是否受技能玲珑霸体影响（[1001][1002]）
	private String q_skill_influnce;
	
	//针对目标（1玩家）
	private int q_target_type;
	
	//拥有BUFF者是否变色（0否，1是）
	private int q_colour;
	
	
	/**
	 * get BUFF编号
	 * @return 
	 */
	public int getQ_buff_id(){
		return q_buff_id;
	}
	
	/**
	 * set BUFF编号
	 */
	public void setQ_buff_id(int q_buff_id){
		this.q_buff_id = q_buff_id;
	}
	
	/**
	 * get BUFF名称
	 * @return 
	 */
	public String getQ_buff_name(){
		return q_buff_name;
	}
	
	/**
	 * set BUFF名称
	 */
	public void setQ_buff_name(String q_buff_name){
		this.q_buff_name = q_buff_name;
	}
	
	/**
	 * get 获得BUFF时向获得者发送提示信息
	 * @return 
	 */
	public String getQ_effect_prompt(){
		return q_effect_prompt;
	}
	
	/**
	 * set 获得BUFF时向获得者发送提示信息
	 */
	public void setQ_effect_prompt(String q_effect_prompt){
		this.q_effect_prompt = q_effect_prompt;
	}
	
	/**
	 * get 作用目标（1自己，2目标，3自己与目标，4自己和队友,5主人）
	 * @return 
	 */
	public int getQ_target(){
		return q_target;
	}
	
	/**
	 * set 作用目标（1自己，2目标，3自己与目标，4自己和队友,5主人）
	 */
	public void setQ_target(int q_target){
		this.q_target = q_target;
	}
	
	/**
	 * get 获得BUFF时向施加者发送提示信息
	 * @return 
	 */
	public String getQ_user_prompt(){
		return q_user_prompt;
	}
	
	/**
	 * set 获得BUFF时向施加者发送提示信息
	 */
	public void setQ_user_prompt(String q_user_prompt){
		this.q_user_prompt = q_user_prompt;
	}
	
	/**
	 * get BUFF广播类型（0隐藏式BUFF，1显示给自己知道的BUFF，2广播给自己和其他玩家知道的BUFF）
	 * @return 
	 */
	public int getQ_cast_type(){
		return q_cast_type;
	}
	
	/**
	 * set BUFF广播类型（0隐藏式BUFF，1显示给自己知道的BUFF，2广播给自己和其他玩家知道的BUFF）
	 */
	public void setQ_cast_type(int q_cast_type){
		this.q_cast_type = q_cast_type;
	}
	
	/**
	 * get 损益类型（0无所谓，1正面BUFF，2负面BUFF）
	 * @return 
	 */
	public int getQ_effect_type(){
		return q_effect_type;
	}
	
	/**
	 * set 损益类型（0无所谓，1正面BUFF，2负面BUFF）
	 */
	public void setQ_effect_type(int q_effect_type){
		this.q_effect_type = q_effect_type;
	}
	
	/**
	 * get 效果类型
	 * @return 
	 */
	public int getQ_action_type(){
		return q_action_type;
	}
	
	/**
	 * set 效果类型
	 */
	public void setQ_action_type(int q_action_type){
		this.q_action_type = q_action_type;
	}
	
	/**
	 * get 效果添加成功几率（本处填万分比的分子）
	 * @return 
	 */
	public int getQ_trigger_prob(){
		return q_trigger_prob;
	}
	
	/**
	 * set 效果添加成功几率（本处填万分比的分子）
	 */
	public void setQ_trigger_prob(int q_trigger_prob){
		this.q_trigger_prob = q_trigger_prob;
	}
	
	/**
	 * get 效果作用数值（允许负值）
	 * @return 
	 */
	public int getQ_effect_value(){
		return q_effect_value;
	}
	
	/**
	 * set 效果作用数值（允许负值）
	 */
	public void setQ_effect_value(int q_effect_value){
		this.q_effect_value = q_effect_value;
	}
	
	/**
	 * get 效果作用比例（本处填万分比的分子）（允许负值）
	 * @return 
	 */
	public int getQ_effect_ratio(){
		return q_effect_ratio;
	}
	
	/**
	 * set 效果作用比例（本处填万分比的分子）（允许负值）
	 */
	public void setQ_effect_ratio(int q_effect_ratio){
		this.q_effect_ratio = q_effect_ratio;
	}
	
	/**
	 * get 效果的总持续时间（单位：秒）（填-1为永久生效）
	 * @return 
	 */
	public int getQ_effect_time(){
		return q_effect_time;
	}
	
	/**
	 * set 效果的总持续时间（单位：秒）（填-1为永久生效）
	 */
	public void setQ_effect_time(int q_effect_time){
		this.q_effect_time = q_effect_time;
	}
	
	/**
	 * get 效果的总容量
	 * @return 
	 */
	public int getQ_effect_maxvalue(){
		return q_effect_maxvalue;
	}
	
	/**
	 * set 效果的总容量
	 */
	public void setQ_effect_maxvalue(int q_effect_maxvalue){
		this.q_effect_maxvalue = q_effect_maxvalue;
	}
	
	/**
	 * get 效果分次作用间隔时间（单位：毫秒）
	 * @return 
	 */
	public int getQ_effect_cooldown(){
		return q_effect_cooldown;
	}
	
	/**
	 * set 效果分次作用间隔时间（单位：毫秒）
	 */
	public void setQ_effect_cooldown(int q_effect_cooldown){
		this.q_effect_cooldown = q_effect_cooldown;
	}
	
	/**
	 * get 效果重复选项（1效果叠加，2效果替换，3容量叠加,4时间叠加，5重复无效）
	 * @return 
	 */
	public int getQ_overlay(){
		return q_overlay;
	}
	
	/**
	 * set 效果重复选项（1效果叠加，2效果替换，3容量叠加,4时间叠加，5重复无效）
	 */
	public void setQ_overlay(int q_overlay){
		this.q_overlay = q_overlay;
	}
	
	/**
	 * get 叠加次数上限（-1为无限）
	 * @return 
	 */
	public int getQ_overlay_maxcount(){
		return q_overlay_maxcount;
	}
	
	/**
	 * set 叠加次数上限（-1为无限）
	 */
	public void setQ_overlay_maxcount(int q_overlay_maxcount){
		this.q_overlay_maxcount = q_overlay_maxcount;
	}
	
	/**
	 * get 替换层级
	 * @return 
	 */
	public int getQ_replace_level(){
		return q_replace_level;
	}
	
	/**
	 * set 替换层级
	 */
	public void setQ_replace_level(int q_replace_level){
		this.q_replace_level = q_replace_level;
	}
	
	/**
	 * get 获得者死亡或下线后是否清除（0不清除，1清除）
	 * @return 
	 */
	public int getQ_effect_dieordown_clear(){
		return q_effect_dieordown_clear;
	}
	
	/**
	 * set 获得者死亡或下线后是否清除（0不清除，1清除）
	 */
	public void setQ_effect_dieordown_clear(int q_effect_dieordown_clear){
		this.q_effect_dieordown_clear = q_effect_dieordown_clear;
	}
	
	/**
	 * get 施加者死亡或下线后是否清除（0不清除，1清除）
	 * @return 
	 */
	public int getQ_user_dieordown_clear(){
		return q_user_dieordown_clear;
	}
	
	/**
	 * set 施加者死亡或下线后是否清除（0不清除，1清除）
	 */
	public void setQ_user_dieordown_clear(int q_user_dieordown_clear){
		this.q_user_dieordown_clear = q_user_dieordown_clear;
	}
	
	/**
	 * get 获得BUFF特效编号
	 * @return 
	 */
	public String getQ_small_ico(){
		return q_small_ico;
	}
	
	/**
	 * set 获得BUFF特效编号
	 */
	public void setQ_small_ico(String q_small_ico){
		this.q_small_ico = q_small_ico;
	}
	
	/**
	 * get BUFF图标（36*36）
	 * @return 
	 */
	public String getQ_ico(){
		return q_ico;
	}
	
	/**
	 * set BUFF图标（36*36）
	 */
	public void setQ_ico(String q_ico){
		this.q_ico = q_ico;
	}
	
	/**
	 * get 显示于BUFF获得者身上的状态SWF动画编号（尺寸：24*24）
	 * @return 
	 */
	public String getQ_swf(){
		return q_swf;
	}
	
	/**
	 * set 显示于BUFF获得者身上的状态SWF动画编号（尺寸：24*24）
	 */
	public void setQ_swf(String q_swf){
		this.q_swf = q_swf;
	}
	
	/**
	 * get 鼠标TIPS界面上的描述信息（支持Html）
	 * @return 
	 */
	public String getQ_tips(){
		return q_tips;
	}
	
	/**
	 * set 鼠标TIPS界面上的描述信息（支持Html）
	 */
	public void setQ_tips(String q_tips){
		this.q_tips = q_tips;
	}
	
	/**
	 * get BUFF参数，技能ID、技能ID，【攻击：X,防御:X,闪避:X,暴击:X,幸运:X,生命上限:X,内力上限:X,体力上限:X,攻击速度:X,移动速度:X,攻击比例：万分比,经验比例：X，真气比例：X。】
	 * @return 
	 */
	public String getQ_Bonus_skill(){
		return q_Bonus_skill;
	}
	
	/**
	 * set BUFF参数，技能ID、技能ID，【攻击：X,防御:X,闪避:X,暴击:X,幸运:X,生命上限:X,内力上限:X,体力上限:X,攻击速度:X,移动速度:X,攻击比例：万分比,经验比例：X，真气比例：X。】
	 */
	public void setQ_Bonus_skill(String q_Bonus_skill){
		this.q_Bonus_skill = q_Bonus_skill;
	}
	
	/**
	 * get 下线是否计时（0不是，1是）
	 * @return 
	 */
	public int getQ_Line_time(){
		return q_Line_time;
	}
	
	/**
	 * set 下线是否计时（0不是，1是）
	 */
	public void setQ_Line_time(int q_Line_time){
		this.q_Line_time = q_Line_time;
	}
	
	/**
	 * get 触发几率
	 * @return 
	 */
	public int getQ_trigger_probability(){
		return q_trigger_probability;
	}
	
	/**
	 * set 触发几率
	 */
	public void setQ_trigger_probability(int q_trigger_probability){
		this.q_trigger_probability = q_trigger_probability;
	}
	
	/**
	 * get 是否受VIP时间加成（1是，0否）
	 * @return 
	 */
	public int getQ_vip_bonus(){
		return q_vip_bonus;
	}
	
	/**
	 * set 是否受VIP时间加成（1是，0否）
	 */
	public void setQ_vip_bonus(int q_vip_bonus){
		this.q_vip_bonus = q_vip_bonus;
	}
	
	/**
	 * get 脚本BUFFID
	 * @return 
	 */
	public int getQ_script_id(){
		return q_script_id;
	}
	
	/**
	 * set 脚本BUFFID
	 */
	public void setQ_script_id(int q_script_id){
		this.q_script_id = q_script_id;
	}
	
	/**
	 * get 是否受技能玲珑霸体影响（[1001][1002]）
	 * @return 
	 */
	public String getQ_skill_influnce(){
		return q_skill_influnce;
	}
	
	/**
	 * set 是否受技能玲珑霸体影响（[1001][1002]）
	 */
	public void setQ_skill_influnce(String q_skill_influnce){
		this.q_skill_influnce = q_skill_influnce;
	}
	
	/**
	 * get 针对目标（1玩家）
	 * @return 
	 */
	public int getQ_target_type(){
		return q_target_type;
	}
	
	/**
	 * set 针对目标（1玩家）
	 */
	public void setQ_target_type(int q_target_type){
		this.q_target_type = q_target_type;
	}
	
	/**
	 * get 拥有BUFF者是否变色（0否，1是）
	 * @return 
	 */
	public int getQ_colour(){
		return q_colour;
	}
	
	/**
	 * set 拥有BUFF者是否变色（0否，1是）
	 */
	public void setQ_colour(int q_colour){
		this.q_colour = q_colour;
	}
	
}