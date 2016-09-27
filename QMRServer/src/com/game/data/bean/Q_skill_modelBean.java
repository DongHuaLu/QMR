package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_skill_model Bean
 */
public class Q_skill_modelBean {

	//技能编号_技能等级
	private String q_skillID_q_grade;
	
	//技能编号
	private int q_skillID;
	
	//技能名称
	private String q_skillName;
	
	//所处武功面板大类（0不在技能面板中，1墨子剑法，2百战武学）
	private int q_panel_type;
	
	//位置编号（0不在武功面板中）
	private int q_index;
	
	//显示所需人物等级
	private int q_show_needgrade;
	
	//武功面板上的简单描述（需支持加色，换行，加粗Html语法）
	private String q_desc;
	
	//武功面板上显示的SWF动画
	private String q_swf;
	
	//鼠标TIPS界面描述信息（支持Html语法）
	private String q_tips;
	
	//学习所需人物等级
	private int q_study_needgrade;
	
	//是否默认学会（1默认学会，0不学会）
	private int q_default_study;
	
	//学习所需技能书编号
	private int q_study_needbook;
	
	//技能书出处描述信息
	private String q_book_desc;
	
	//使用者（1人物技能，2怪物技能，3坐骑技能，4弓箭技能，5暗器技能，6侍宠技能）
	private int q_skill_user;
	
	//使用方式（1主动技能，2被动技能）
	private int q_trigger_type;
	
	//使用距离限制（自身与目标之间的距离）（单位：格数）
	private int q_range_limit;
	
	//触发方式（0不是被动触发，1在攻击时触发，2在挨打时触发,3攻击与挨打时都触发,4攻击前触发,5被攻击前触发，6侍宠攻击时触发，7侍宠被攻击时触发，8侍宠攻击、被攻击均触发，9主角死亡时触发，10暗器次数触发）
	private int q_passive_action;
	
	//被动触发几率（本处填万分比的分子）
	private int q_passive_prob;
	
	//作用对象（1自己，2友好目标，3敌对目标，4当前目标，5场景中鼠标的当前坐标点，6主人）
	private int q_target;
	
	//作用范围形状（1单体，2矩形，3扇形，4圆形）
	private int q_area_shape;
	
	//作用范围中心点（1自身为中心，2目标为中心）
	private int q_area_target;
	
	//矩形长（像素）
	private int q_area_length;
	
	//矩形宽
	private int q_area_width;
	
	//扇形开始角度
	private int q_sector_start;
	
	//扇形结束角度
	private int q_secto_end;
	
	//扇形半径
	private int q_sector_radius;
	
	//圆半径
	private int q_circular_radius;
	
	//作用人数上限
	private int q_target_max;
	
	//是否可以设为默认技能（0不可设为默认技能，1可以设为默认技能）
	private int q_default_enable;
	
	//是否可以注册快捷栏（1可以，2不可以）
	private int q_shortcut;
	
	//冷却时间
	private int q_cd;
	
	//公共冷却时间
	private int q_public_cd;
	
	//公共冷却层级
	private int q_public_cd_level;
	
	//该技能的克制技能编号
	private int q_restriction;
	
	//是否触发一次战斗公式的伤害（0不触发，1触发）
	private int q_trigger_figth_hurt;
	
	//伤害修正系数（万分比）
	private int q_hurt_correct_factor;
	
	//技能等级
	private int q_grade;
	
	//升级所需人物等级
	private int q_needgrade;
	
	//升级所需材料编号及数量序列（格式：材料物品ID_数量;材料物品ID_数量;）
	private String q_up_need_goods;
	
	//所需材料的出处说明（文字描述）
	private String q_up_need_goods_desc;
	
	//升级所需铜币
	private int q_up_need_copper;
	
	//升级所需真气
	private int q_up_need_zhengqi;
	
	//真气来源出处说明（文字描述）
	private String q_up_need_zhengqi_desc;
	
	//升级所需时间（单位：毫秒）
	private int q_up_need_time;
	
	//升级成功几率
	private int q_up_prob;
	
	//使用消耗内力值
	private int q_need_mp;
	
	//每次造成怪物仇恨值
	private int q_enmity;
	
	//技能加成攻击力值
	private int q_attack_addition;
	
	//技能造成无视防御伤害值
	private int q_ignore_defence;
	
	//使用成功或被动触发后附加的BUFF编号序列（格式：BUFF编号;BUFF编号）
	private String q_passive_buff;
	
	//成功施加BUFF系数
	private int q_bufq_trigger_factor;
	
	//成功施加BUFF抵抗系数
	private int q_bufq_defence_factor;
	
	//BUFF持续时间提升系数
	private int q_bufq_timeup_factor;
	
	//BUFF持续时间减免系数
	private int q_bufq_timedown_factor;
	
	//BUFF作用数值修正系数
	private int q_bufq_num_factor;
	
	//BUFF作用比例修正系数
	private int q_bufq_action_factor;
	
	//技能调用攻击动作编号
	private int q_attack_id;
	
	//延迟命中时间（单位：毫秒）
	private int q_delay_time;
	
	//弹道飞行速度（单位：像素/秒）
	private int q_trajectory_speed;
	
	//技能施法特效编号
	private String q_use_effect;
	
	//技能弹道特效编号
	private String q_trajectory_effect;
	
	//技能命中特效编号
	private String q_hit_effect;
	
	//技能持续特效编号
	private String q_series_effect;
	
	//技能小图标编号（36*36）
	private int q_small_ico;
	
	//技能施法音效
	private String q_use_sound;
	
	//技能命中音效
	private String q_hit_sound;
	
	//（前端使用）战斗力加成
	private int q_fight_bonus;
	
	//最大等级
	private int q_max_level;
	
	//跳跃是否可使用技能(1是，0否)
	private int q_is_Jump_skill;
	
	//调用脚本编号
	private int q_skill_script;
	
	//是否忽视（防御，闪避，跳跃）
	private int q_is_ignore;
	
	//技能类型（0普通技能，1火墙类型）
	private int q_skill_type;
	
	//技能持续时间（毫秒）
	private int q_skill_time;
	
	
	/**
	 * get 技能编号_技能等级
	 * @return 
	 */
	public String getQ_skillID_q_grade(){
		return q_skillID_q_grade;
	}
	
	/**
	 * set 技能编号_技能等级
	 */
	public void setQ_skillID_q_grade(String q_skillID_q_grade){
		this.q_skillID_q_grade = q_skillID_q_grade;
	}
	
	/**
	 * get 技能编号
	 * @return 
	 */
	public int getQ_skillID(){
		return q_skillID;
	}
	
	/**
	 * set 技能编号
	 */
	public void setQ_skillID(int q_skillID){
		this.q_skillID = q_skillID;
	}
	
	/**
	 * get 技能名称
	 * @return 
	 */
	public String getQ_skillName(){
		return q_skillName;
	}
	
	/**
	 * set 技能名称
	 */
	public void setQ_skillName(String q_skillName){
		this.q_skillName = q_skillName;
	}
	
	/**
	 * get 所处武功面板大类（0不在技能面板中，1墨子剑法，2百战武学）
	 * @return 
	 */
	public int getQ_panel_type(){
		return q_panel_type;
	}
	
	/**
	 * set 所处武功面板大类（0不在技能面板中，1墨子剑法，2百战武学）
	 */
	public void setQ_panel_type(int q_panel_type){
		this.q_panel_type = q_panel_type;
	}
	
	/**
	 * get 位置编号（0不在武功面板中）
	 * @return 
	 */
	public int getQ_index(){
		return q_index;
	}
	
	/**
	 * set 位置编号（0不在武功面板中）
	 */
	public void setQ_index(int q_index){
		this.q_index = q_index;
	}
	
	/**
	 * get 显示所需人物等级
	 * @return 
	 */
	public int getQ_show_needgrade(){
		return q_show_needgrade;
	}
	
	/**
	 * set 显示所需人物等级
	 */
	public void setQ_show_needgrade(int q_show_needgrade){
		this.q_show_needgrade = q_show_needgrade;
	}
	
	/**
	 * get 武功面板上的简单描述（需支持加色，换行，加粗Html语法）
	 * @return 
	 */
	public String getQ_desc(){
		return q_desc;
	}
	
	/**
	 * set 武功面板上的简单描述（需支持加色，换行，加粗Html语法）
	 */
	public void setQ_desc(String q_desc){
		this.q_desc = q_desc;
	}
	
	/**
	 * get 武功面板上显示的SWF动画
	 * @return 
	 */
	public String getQ_swf(){
		return q_swf;
	}
	
	/**
	 * set 武功面板上显示的SWF动画
	 */
	public void setQ_swf(String q_swf){
		this.q_swf = q_swf;
	}
	
	/**
	 * get 鼠标TIPS界面描述信息（支持Html语法）
	 * @return 
	 */
	public String getQ_tips(){
		return q_tips;
	}
	
	/**
	 * set 鼠标TIPS界面描述信息（支持Html语法）
	 */
	public void setQ_tips(String q_tips){
		this.q_tips = q_tips;
	}
	
	/**
	 * get 学习所需人物等级
	 * @return 
	 */
	public int getQ_study_needgrade(){
		return q_study_needgrade;
	}
	
	/**
	 * set 学习所需人物等级
	 */
	public void setQ_study_needgrade(int q_study_needgrade){
		this.q_study_needgrade = q_study_needgrade;
	}
	
	/**
	 * get 是否默认学会（1默认学会，0不学会）
	 * @return 
	 */
	public int getQ_default_study(){
		return q_default_study;
	}
	
	/**
	 * set 是否默认学会（1默认学会，0不学会）
	 */
	public void setQ_default_study(int q_default_study){
		this.q_default_study = q_default_study;
	}
	
	/**
	 * get 学习所需技能书编号
	 * @return 
	 */
	public int getQ_study_needbook(){
		return q_study_needbook;
	}
	
	/**
	 * set 学习所需技能书编号
	 */
	public void setQ_study_needbook(int q_study_needbook){
		this.q_study_needbook = q_study_needbook;
	}
	
	/**
	 * get 技能书出处描述信息
	 * @return 
	 */
	public String getQ_book_desc(){
		return q_book_desc;
	}
	
	/**
	 * set 技能书出处描述信息
	 */
	public void setQ_book_desc(String q_book_desc){
		this.q_book_desc = q_book_desc;
	}
	
	/**
	 * get 使用者（1人物技能，2怪物技能，3坐骑技能，4弓箭技能，5暗器技能，6侍宠技能）
	 * @return 
	 */
	public int getQ_skill_user(){
		return q_skill_user;
	}
	
	/**
	 * set 使用者（1人物技能，2怪物技能，3坐骑技能，4弓箭技能，5暗器技能，6侍宠技能）
	 */
	public void setQ_skill_user(int q_skill_user){
		this.q_skill_user = q_skill_user;
	}
	
	/**
	 * get 使用方式（1主动技能，2被动技能）
	 * @return 
	 */
	public int getQ_trigger_type(){
		return q_trigger_type;
	}
	
	/**
	 * set 使用方式（1主动技能，2被动技能）
	 */
	public void setQ_trigger_type(int q_trigger_type){
		this.q_trigger_type = q_trigger_type;
	}
	
	/**
	 * get 使用距离限制（自身与目标之间的距离）（单位：格数）
	 * @return 
	 */
	public int getQ_range_limit(){
		return q_range_limit;
	}
	
	/**
	 * set 使用距离限制（自身与目标之间的距离）（单位：格数）
	 */
	public void setQ_range_limit(int q_range_limit){
		this.q_range_limit = q_range_limit;
	}
	
	/**
	 * get 触发方式（0不是被动触发，1在攻击时触发，2在挨打时触发,3攻击与挨打时都触发,4攻击前触发,5被攻击前触发，6侍宠攻击时触发，7侍宠被攻击时触发，8侍宠攻击、被攻击均触发，9主角死亡时触发，10暗器次数触发）
	 * @return 
	 */
	public int getQ_passive_action(){
		return q_passive_action;
	}
	
	/**
	 * set 触发方式（0不是被动触发，1在攻击时触发，2在挨打时触发,3攻击与挨打时都触发,4攻击前触发,5被攻击前触发，6侍宠攻击时触发，7侍宠被攻击时触发，8侍宠攻击、被攻击均触发，9主角死亡时触发，10暗器次数触发）
	 */
	public void setQ_passive_action(int q_passive_action){
		this.q_passive_action = q_passive_action;
	}
	
	/**
	 * get 被动触发几率（本处填万分比的分子）
	 * @return 
	 */
	public int getQ_passive_prob(){
		return q_passive_prob;
	}
	
	/**
	 * set 被动触发几率（本处填万分比的分子）
	 */
	public void setQ_passive_prob(int q_passive_prob){
		this.q_passive_prob = q_passive_prob;
	}
	
	/**
	 * get 作用对象（1自己，2友好目标，3敌对目标，4当前目标，5场景中鼠标的当前坐标点，6主人）
	 * @return 
	 */
	public int getQ_target(){
		return q_target;
	}
	
	/**
	 * set 作用对象（1自己，2友好目标，3敌对目标，4当前目标，5场景中鼠标的当前坐标点，6主人）
	 */
	public void setQ_target(int q_target){
		this.q_target = q_target;
	}
	
	/**
	 * get 作用范围形状（1单体，2矩形，3扇形，4圆形）
	 * @return 
	 */
	public int getQ_area_shape(){
		return q_area_shape;
	}
	
	/**
	 * set 作用范围形状（1单体，2矩形，3扇形，4圆形）
	 */
	public void setQ_area_shape(int q_area_shape){
		this.q_area_shape = q_area_shape;
	}
	
	/**
	 * get 作用范围中心点（1自身为中心，2目标为中心）
	 * @return 
	 */
	public int getQ_area_target(){
		return q_area_target;
	}
	
	/**
	 * set 作用范围中心点（1自身为中心，2目标为中心）
	 */
	public void setQ_area_target(int q_area_target){
		this.q_area_target = q_area_target;
	}
	
	/**
	 * get 矩形长（像素）
	 * @return 
	 */
	public int getQ_area_length(){
		return q_area_length;
	}
	
	/**
	 * set 矩形长（像素）
	 */
	public void setQ_area_length(int q_area_length){
		this.q_area_length = q_area_length;
	}
	
	/**
	 * get 矩形宽
	 * @return 
	 */
	public int getQ_area_width(){
		return q_area_width;
	}
	
	/**
	 * set 矩形宽
	 */
	public void setQ_area_width(int q_area_width){
		this.q_area_width = q_area_width;
	}
	
	/**
	 * get 扇形开始角度
	 * @return 
	 */
	public int getQ_sector_start(){
		return q_sector_start;
	}
	
	/**
	 * set 扇形开始角度
	 */
	public void setQ_sector_start(int q_sector_start){
		this.q_sector_start = q_sector_start;
	}
	
	/**
	 * get 扇形结束角度
	 * @return 
	 */
	public int getQ_secto_end(){
		return q_secto_end;
	}
	
	/**
	 * set 扇形结束角度
	 */
	public void setQ_secto_end(int q_secto_end){
		this.q_secto_end = q_secto_end;
	}
	
	/**
	 * get 扇形半径
	 * @return 
	 */
	public int getQ_sector_radius(){
		return q_sector_radius;
	}
	
	/**
	 * set 扇形半径
	 */
	public void setQ_sector_radius(int q_sector_radius){
		this.q_sector_radius = q_sector_radius;
	}
	
	/**
	 * get 圆半径
	 * @return 
	 */
	public int getQ_circular_radius(){
		return q_circular_radius;
	}
	
	/**
	 * set 圆半径
	 */
	public void setQ_circular_radius(int q_circular_radius){
		this.q_circular_radius = q_circular_radius;
	}
	
	/**
	 * get 作用人数上限
	 * @return 
	 */
	public int getQ_target_max(){
		return q_target_max;
	}
	
	/**
	 * set 作用人数上限
	 */
	public void setQ_target_max(int q_target_max){
		this.q_target_max = q_target_max;
	}
	
	/**
	 * get 是否可以设为默认技能（0不可设为默认技能，1可以设为默认技能）
	 * @return 
	 */
	public int getQ_default_enable(){
		return q_default_enable;
	}
	
	/**
	 * set 是否可以设为默认技能（0不可设为默认技能，1可以设为默认技能）
	 */
	public void setQ_default_enable(int q_default_enable){
		this.q_default_enable = q_default_enable;
	}
	
	/**
	 * get 是否可以注册快捷栏（1可以，2不可以）
	 * @return 
	 */
	public int getQ_shortcut(){
		return q_shortcut;
	}
	
	/**
	 * set 是否可以注册快捷栏（1可以，2不可以）
	 */
	public void setQ_shortcut(int q_shortcut){
		this.q_shortcut = q_shortcut;
	}
	
	/**
	 * get 冷却时间
	 * @return 
	 */
	public int getQ_cd(){
		return q_cd;
	}
	
	/**
	 * set 冷却时间
	 */
	public void setQ_cd(int q_cd){
		this.q_cd = q_cd;
	}
	
	/**
	 * get 公共冷却时间
	 * @return 
	 */
	public int getQ_public_cd(){
		return q_public_cd;
	}
	
	/**
	 * set 公共冷却时间
	 */
	public void setQ_public_cd(int q_public_cd){
		this.q_public_cd = q_public_cd;
	}
	
	/**
	 * get 公共冷却层级
	 * @return 
	 */
	public int getQ_public_cd_level(){
		return q_public_cd_level;
	}
	
	/**
	 * set 公共冷却层级
	 */
	public void setQ_public_cd_level(int q_public_cd_level){
		this.q_public_cd_level = q_public_cd_level;
	}
	
	/**
	 * get 该技能的克制技能编号
	 * @return 
	 */
	public int getQ_restriction(){
		return q_restriction;
	}
	
	/**
	 * set 该技能的克制技能编号
	 */
	public void setQ_restriction(int q_restriction){
		this.q_restriction = q_restriction;
	}
	
	/**
	 * get 是否触发一次战斗公式的伤害（0不触发，1触发）
	 * @return 
	 */
	public int getQ_trigger_figth_hurt(){
		return q_trigger_figth_hurt;
	}
	
	/**
	 * set 是否触发一次战斗公式的伤害（0不触发，1触发）
	 */
	public void setQ_trigger_figth_hurt(int q_trigger_figth_hurt){
		this.q_trigger_figth_hurt = q_trigger_figth_hurt;
	}
	
	/**
	 * get 伤害修正系数（万分比）
	 * @return 
	 */
	public int getQ_hurt_correct_factor(){
		return q_hurt_correct_factor;
	}
	
	/**
	 * set 伤害修正系数（万分比）
	 */
	public void setQ_hurt_correct_factor(int q_hurt_correct_factor){
		this.q_hurt_correct_factor = q_hurt_correct_factor;
	}
	
	/**
	 * get 技能等级
	 * @return 
	 */
	public int getQ_grade(){
		return q_grade;
	}
	
	/**
	 * set 技能等级
	 */
	public void setQ_grade(int q_grade){
		this.q_grade = q_grade;
	}
	
	/**
	 * get 升级所需人物等级
	 * @return 
	 */
	public int getQ_needgrade(){
		return q_needgrade;
	}
	
	/**
	 * set 升级所需人物等级
	 */
	public void setQ_needgrade(int q_needgrade){
		this.q_needgrade = q_needgrade;
	}
	
	/**
	 * get 升级所需材料编号及数量序列（格式：材料物品ID_数量;材料物品ID_数量;）
	 * @return 
	 */
	public String getQ_up_need_goods(){
		return q_up_need_goods;
	}
	
	/**
	 * set 升级所需材料编号及数量序列（格式：材料物品ID_数量;材料物品ID_数量;）
	 */
	public void setQ_up_need_goods(String q_up_need_goods){
		this.q_up_need_goods = q_up_need_goods;
	}
	
	/**
	 * get 所需材料的出处说明（文字描述）
	 * @return 
	 */
	public String getQ_up_need_goods_desc(){
		return q_up_need_goods_desc;
	}
	
	/**
	 * set 所需材料的出处说明（文字描述）
	 */
	public void setQ_up_need_goods_desc(String q_up_need_goods_desc){
		this.q_up_need_goods_desc = q_up_need_goods_desc;
	}
	
	/**
	 * get 升级所需铜币
	 * @return 
	 */
	public int getQ_up_need_copper(){
		return q_up_need_copper;
	}
	
	/**
	 * set 升级所需铜币
	 */
	public void setQ_up_need_copper(int q_up_need_copper){
		this.q_up_need_copper = q_up_need_copper;
	}
	
	/**
	 * get 升级所需真气
	 * @return 
	 */
	public int getQ_up_need_zhengqi(){
		return q_up_need_zhengqi;
	}
	
	/**
	 * set 升级所需真气
	 */
	public void setQ_up_need_zhengqi(int q_up_need_zhengqi){
		this.q_up_need_zhengqi = q_up_need_zhengqi;
	}
	
	/**
	 * get 真气来源出处说明（文字描述）
	 * @return 
	 */
	public String getQ_up_need_zhengqi_desc(){
		return q_up_need_zhengqi_desc;
	}
	
	/**
	 * set 真气来源出处说明（文字描述）
	 */
	public void setQ_up_need_zhengqi_desc(String q_up_need_zhengqi_desc){
		this.q_up_need_zhengqi_desc = q_up_need_zhengqi_desc;
	}
	
	/**
	 * get 升级所需时间（单位：毫秒）
	 * @return 
	 */
	public int getQ_up_need_time(){
		return q_up_need_time;
	}
	
	/**
	 * set 升级所需时间（单位：毫秒）
	 */
	public void setQ_up_need_time(int q_up_need_time){
		this.q_up_need_time = q_up_need_time;
	}
	
	/**
	 * get 升级成功几率
	 * @return 
	 */
	public int getQ_up_prob(){
		return q_up_prob;
	}
	
	/**
	 * set 升级成功几率
	 */
	public void setQ_up_prob(int q_up_prob){
		this.q_up_prob = q_up_prob;
	}
	
	/**
	 * get 使用消耗内力值
	 * @return 
	 */
	public int getQ_need_mp(){
		return q_need_mp;
	}
	
	/**
	 * set 使用消耗内力值
	 */
	public void setQ_need_mp(int q_need_mp){
		this.q_need_mp = q_need_mp;
	}
	
	/**
	 * get 每次造成怪物仇恨值
	 * @return 
	 */
	public int getQ_enmity(){
		return q_enmity;
	}
	
	/**
	 * set 每次造成怪物仇恨值
	 */
	public void setQ_enmity(int q_enmity){
		this.q_enmity = q_enmity;
	}
	
	/**
	 * get 技能加成攻击力值
	 * @return 
	 */
	public int getQ_attack_addition(){
		return q_attack_addition;
	}
	
	/**
	 * set 技能加成攻击力值
	 */
	public void setQ_attack_addition(int q_attack_addition){
		this.q_attack_addition = q_attack_addition;
	}
	
	/**
	 * get 技能造成无视防御伤害值
	 * @return 
	 */
	public int getQ_ignore_defence(){
		return q_ignore_defence;
	}
	
	/**
	 * set 技能造成无视防御伤害值
	 */
	public void setQ_ignore_defence(int q_ignore_defence){
		this.q_ignore_defence = q_ignore_defence;
	}
	
	/**
	 * get 使用成功或被动触发后附加的BUFF编号序列（格式：BUFF编号;BUFF编号）
	 * @return 
	 */
	public String getQ_passive_buff(){
		return q_passive_buff;
	}
	
	/**
	 * set 使用成功或被动触发后附加的BUFF编号序列（格式：BUFF编号;BUFF编号）
	 */
	public void setQ_passive_buff(String q_passive_buff){
		this.q_passive_buff = q_passive_buff;
	}
	
	/**
	 * get 成功施加BUFF系数
	 * @return 
	 */
	public int getQ_bufq_trigger_factor(){
		return q_bufq_trigger_factor;
	}
	
	/**
	 * set 成功施加BUFF系数
	 */
	public void setQ_bufq_trigger_factor(int q_bufq_trigger_factor){
		this.q_bufq_trigger_factor = q_bufq_trigger_factor;
	}
	
	/**
	 * get 成功施加BUFF抵抗系数
	 * @return 
	 */
	public int getQ_bufq_defence_factor(){
		return q_bufq_defence_factor;
	}
	
	/**
	 * set 成功施加BUFF抵抗系数
	 */
	public void setQ_bufq_defence_factor(int q_bufq_defence_factor){
		this.q_bufq_defence_factor = q_bufq_defence_factor;
	}
	
	/**
	 * get BUFF持续时间提升系数
	 * @return 
	 */
	public int getQ_bufq_timeup_factor(){
		return q_bufq_timeup_factor;
	}
	
	/**
	 * set BUFF持续时间提升系数
	 */
	public void setQ_bufq_timeup_factor(int q_bufq_timeup_factor){
		this.q_bufq_timeup_factor = q_bufq_timeup_factor;
	}
	
	/**
	 * get BUFF持续时间减免系数
	 * @return 
	 */
	public int getQ_bufq_timedown_factor(){
		return q_bufq_timedown_factor;
	}
	
	/**
	 * set BUFF持续时间减免系数
	 */
	public void setQ_bufq_timedown_factor(int q_bufq_timedown_factor){
		this.q_bufq_timedown_factor = q_bufq_timedown_factor;
	}
	
	/**
	 * get BUFF作用数值修正系数
	 * @return 
	 */
	public int getQ_bufq_num_factor(){
		return q_bufq_num_factor;
	}
	
	/**
	 * set BUFF作用数值修正系数
	 */
	public void setQ_bufq_num_factor(int q_bufq_num_factor){
		this.q_bufq_num_factor = q_bufq_num_factor;
	}
	
	/**
	 * get BUFF作用比例修正系数
	 * @return 
	 */
	public int getQ_bufq_action_factor(){
		return q_bufq_action_factor;
	}
	
	/**
	 * set BUFF作用比例修正系数
	 */
	public void setQ_bufq_action_factor(int q_bufq_action_factor){
		this.q_bufq_action_factor = q_bufq_action_factor;
	}
	
	/**
	 * get 技能调用攻击动作编号
	 * @return 
	 */
	public int getQ_attack_id(){
		return q_attack_id;
	}
	
	/**
	 * set 技能调用攻击动作编号
	 */
	public void setQ_attack_id(int q_attack_id){
		this.q_attack_id = q_attack_id;
	}
	
	/**
	 * get 延迟命中时间（单位：毫秒）
	 * @return 
	 */
	public int getQ_delay_time(){
		return q_delay_time;
	}
	
	/**
	 * set 延迟命中时间（单位：毫秒）
	 */
	public void setQ_delay_time(int q_delay_time){
		this.q_delay_time = q_delay_time;
	}
	
	/**
	 * get 弹道飞行速度（单位：像素/秒）
	 * @return 
	 */
	public int getQ_trajectory_speed(){
		return q_trajectory_speed;
	}
	
	/**
	 * set 弹道飞行速度（单位：像素/秒）
	 */
	public void setQ_trajectory_speed(int q_trajectory_speed){
		this.q_trajectory_speed = q_trajectory_speed;
	}
	
	/**
	 * get 技能施法特效编号
	 * @return 
	 */
	public String getQ_use_effect(){
		return q_use_effect;
	}
	
	/**
	 * set 技能施法特效编号
	 */
	public void setQ_use_effect(String q_use_effect){
		this.q_use_effect = q_use_effect;
	}
	
	/**
	 * get 技能弹道特效编号
	 * @return 
	 */
	public String getQ_trajectory_effect(){
		return q_trajectory_effect;
	}
	
	/**
	 * set 技能弹道特效编号
	 */
	public void setQ_trajectory_effect(String q_trajectory_effect){
		this.q_trajectory_effect = q_trajectory_effect;
	}
	
	/**
	 * get 技能命中特效编号
	 * @return 
	 */
	public String getQ_hit_effect(){
		return q_hit_effect;
	}
	
	/**
	 * set 技能命中特效编号
	 */
	public void setQ_hit_effect(String q_hit_effect){
		this.q_hit_effect = q_hit_effect;
	}
	
	/**
	 * get 技能持续特效编号
	 * @return 
	 */
	public String getQ_series_effect(){
		return q_series_effect;
	}
	
	/**
	 * set 技能持续特效编号
	 */
	public void setQ_series_effect(String q_series_effect){
		this.q_series_effect = q_series_effect;
	}
	
	/**
	 * get 技能小图标编号（36*36）
	 * @return 
	 */
	public int getQ_small_ico(){
		return q_small_ico;
	}
	
	/**
	 * set 技能小图标编号（36*36）
	 */
	public void setQ_small_ico(int q_small_ico){
		this.q_small_ico = q_small_ico;
	}
	
	/**
	 * get 技能施法音效
	 * @return 
	 */
	public String getQ_use_sound(){
		return q_use_sound;
	}
	
	/**
	 * set 技能施法音效
	 */
	public void setQ_use_sound(String q_use_sound){
		this.q_use_sound = q_use_sound;
	}
	
	/**
	 * get 技能命中音效
	 * @return 
	 */
	public String getQ_hit_sound(){
		return q_hit_sound;
	}
	
	/**
	 * set 技能命中音效
	 */
	public void setQ_hit_sound(String q_hit_sound){
		this.q_hit_sound = q_hit_sound;
	}
	
	/**
	 * get （前端使用）战斗力加成
	 * @return 
	 */
	public int getQ_fight_bonus(){
		return q_fight_bonus;
	}
	
	/**
	 * set （前端使用）战斗力加成
	 */
	public void setQ_fight_bonus(int q_fight_bonus){
		this.q_fight_bonus = q_fight_bonus;
	}
	
	/**
	 * get 最大等级
	 * @return 
	 */
	public int getQ_max_level(){
		return q_max_level;
	}
	
	/**
	 * set 最大等级
	 */
	public void setQ_max_level(int q_max_level){
		this.q_max_level = q_max_level;
	}
	
	/**
	 * get 跳跃是否可使用技能(1是，0否)
	 * @return 
	 */
	public int getQ_is_Jump_skill(){
		return q_is_Jump_skill;
	}
	
	/**
	 * set 跳跃是否可使用技能(1是，0否)
	 */
	public void setQ_is_Jump_skill(int q_is_Jump_skill){
		this.q_is_Jump_skill = q_is_Jump_skill;
	}
	
	/**
	 * get 调用脚本编号
	 * @return 
	 */
	public int getQ_skill_script(){
		return q_skill_script;
	}
	
	/**
	 * set 调用脚本编号
	 */
	public void setQ_skill_script(int q_skill_script){
		this.q_skill_script = q_skill_script;
	}
	
	/**
	 * get 是否忽视（防御，闪避，跳跃）
	 * @return 
	 */
	public int getQ_is_ignore(){
		return q_is_ignore;
	}
	
	/**
	 * set 是否忽视（防御，闪避，跳跃）
	 */
	public void setQ_is_ignore(int q_is_ignore){
		this.q_is_ignore = q_is_ignore;
	}
	
	/**
	 * get 技能类型（0普通技能，1火墙类型）
	 * @return 
	 */
	public int getQ_skill_type(){
		return q_skill_type;
	}
	
	/**
	 * set 技能类型（0普通技能，1火墙类型）
	 */
	public void setQ_skill_type(int q_skill_type){
		this.q_skill_type = q_skill_type;
	}
	
	/**
	 * get 技能持续时间（毫秒）
	 * @return 
	 */
	public int getQ_skill_time(){
		return q_skill_time;
	}
	
	/**
	 * set 技能持续时间（毫秒）
	 */
	public void setQ_skill_time(int q_skill_time){
		this.q_skill_time = q_skill_time;
	}
	
}