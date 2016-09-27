package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_monster Bean
 */
public class Q_monsterBean {

	//怪物ID
	private int q_id;
	
	//怪物名字
	private String q_name;
	
	//怪物爆率文字描述（小地图tips描述，支持html）
	private String q_monster_dropdesc;
	
	//是否在小地图中显示（1是，2否）
	private int q_isminimap;
	
	//怪物类型(1普通小怪,2精英,3BOSS)
	private int q_monster_type;
	
	//怪物攻击模式(1主动攻击,2被动攻击，3木桩类怪物)
	private int q_evasive_style;
	
	//怪物造型资源编号
	private String q_sculpt_resid;
	
	//怪物头像资源编号
	private String q_head_resid;
	
	//攻击时音效编号
	private String q_fire_soundid;
	
	//被攻击时音效编号
	private String q_underfire_soundid;
	
	//死亡时音效编号
	private String q_die_soundid;
	
	//怪物在场景中的发言频率间隔(单位：毫秒)
	private int q_tosay_timeinterval;
	
	//怪物在场景中的发言内容(多句以分号分隔)
	private String q_say_condition;
	
	//怪物等级
	private int q_grade;
	
	//生命值
	private int q_maxhp;
	
	//内力
	private int q_maxmp;
	
	//体力
	private int q_maxsp;
	
	//攻击力
	private int q_attack;
	
	//无视防御伤害（忽视玩家防御，可以被元气盾反弹）
	private int q_ignore_damage;
	
	//武器攻击（可以被玩家的化龙破技能清0）
	private int q_equip_attack;
	
	//防御力
	private int q_defense;
	
	//护甲防御（可以被玩家的破甲击技能清零）
	private int q_equip_defense;
	
	//暴击值
	private int q_crt;
	
	//闪避值
	private int q_dodge;
	
	//攻击速度
	private int q_attack_speed;
	
	//移动速度
	private int q_speed;
	
	//幸运值
	private int q_luck;
	
	//怪物变身设置（格式：变身类型_变身倍率|变身几率分子/变身几率分母;变身类型_变身倍率|变身几率分子/变身几率分母;）
	private String q_variation;
	
	//视野距离半径(单位：格子数)
	private int q_eyeshot;
	
	//巡逻距离半径(单位：格子数)
	private int q_patrol;
	
	//巡逻间隔时间（毫秒）
	private int q_patrol_time;
	
	//巡逻几率（万分比）
	private int q_patrol_pro;
	
	//追击距离半径(单位：格子数)本距离值不得小于怪物的巡逻距离值
	private int q_pursuit;
	
	//怪物攻击时使用的默认技能(格式：技能ID_技能等级）
	private String q_default_skill;
	
	//怪物使用的特殊技能ID与触发几率列表（格式：技能ID_技能等级|触发几率分子/触发几率分母;技能ID_技能等级|触发几率分子/触发几率分母;）
	private String q_special_skill;
	
	//怪物被攻击时是否固定少血(0否,1是)
	private int q_fixed_hurt;
	
	//怪物被攻击时固定少血值
	private int q_fiexd_value;
	
	//怪物携带经验
	private int q_carry_exp;
	
	//怪物携带军功值
	private int q_ranknum;
	
	//是否有额外军功值 0为无军功值 N为额外的军功值数值
	private int q_extra_ranknum;
	
	//是否排除在经验衰减规则之外（1不排除，0排除在经验衰减规则之外）
	private int q_isexclude;
	
	//爆出带强化等级装备的几率（格式：强化等级|几率;强化等级|几率;强化等级|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个强化等级
	private String q_intensify_prob;
	
	//爆出带附加属性装备的几率（格式：附加属性条数|几率;附加属性条数|几率;附加属性条数|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个条数
	private String q_addition_prob;
	
	//BOSS类怪物，固定刷新时间定义
	private String q_refreshtime;
	
	//怪物的重生时间(单位：秒)
	private int q_revive_time;
	
	//关联的AI脚本ID
	private int q_script_id;
	
	//是否跨服同步信息（0否）
	private int q_info_sync;
	
	//[{"类型":1,"间隔":10000,"几率":10000,"说话":"怪物计时器触发","预警":0,"触发":10002_2,"对象":1},{"类型":2,"几率":5000,"说话":"怪物攻击触发","预警":0,"触发":10012_2,"对象":1},{"类型":3,"血量":5,"几率":10000,"说话":"怪物掉血触发2","预警":0,"触发":10003_3,"对象":1}] 
	private String q_monster_ai;
	
	//怪物被动技能
	private String q_passive_skill;
	
	
	/**
	 * get 怪物ID
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 怪物ID
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 怪物名字
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 怪物名字
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 怪物爆率文字描述（小地图tips描述，支持html）
	 * @return 
	 */
	public String getQ_monster_dropdesc(){
		return q_monster_dropdesc;
	}
	
	/**
	 * set 怪物爆率文字描述（小地图tips描述，支持html）
	 */
	public void setQ_monster_dropdesc(String q_monster_dropdesc){
		this.q_monster_dropdesc = q_monster_dropdesc;
	}
	
	/**
	 * get 是否在小地图中显示（1是，2否）
	 * @return 
	 */
	public int getQ_isminimap(){
		return q_isminimap;
	}
	
	/**
	 * set 是否在小地图中显示（1是，2否）
	 */
	public void setQ_isminimap(int q_isminimap){
		this.q_isminimap = q_isminimap;
	}
	
	/**
	 * get 怪物类型(1普通小怪,2精英,3BOSS)
	 * @return 
	 */
	public int getQ_monster_type(){
		return q_monster_type;
	}
	
	/**
	 * set 怪物类型(1普通小怪,2精英,3BOSS)
	 */
	public void setQ_monster_type(int q_monster_type){
		this.q_monster_type = q_monster_type;
	}
	
	/**
	 * get 怪物攻击模式(1主动攻击,2被动攻击，3木桩类怪物)
	 * @return 
	 */
	public int getQ_evasive_style(){
		return q_evasive_style;
	}
	
	/**
	 * set 怪物攻击模式(1主动攻击,2被动攻击，3木桩类怪物)
	 */
	public void setQ_evasive_style(int q_evasive_style){
		this.q_evasive_style = q_evasive_style;
	}
	
	/**
	 * get 怪物造型资源编号
	 * @return 
	 */
	public String getQ_sculpt_resid(){
		return q_sculpt_resid;
	}
	
	/**
	 * set 怪物造型资源编号
	 */
	public void setQ_sculpt_resid(String q_sculpt_resid){
		this.q_sculpt_resid = q_sculpt_resid;
	}
	
	/**
	 * get 怪物头像资源编号
	 * @return 
	 */
	public String getQ_head_resid(){
		return q_head_resid;
	}
	
	/**
	 * set 怪物头像资源编号
	 */
	public void setQ_head_resid(String q_head_resid){
		this.q_head_resid = q_head_resid;
	}
	
	/**
	 * get 攻击时音效编号
	 * @return 
	 */
	public String getQ_fire_soundid(){
		return q_fire_soundid;
	}
	
	/**
	 * set 攻击时音效编号
	 */
	public void setQ_fire_soundid(String q_fire_soundid){
		this.q_fire_soundid = q_fire_soundid;
	}
	
	/**
	 * get 被攻击时音效编号
	 * @return 
	 */
	public String getQ_underfire_soundid(){
		return q_underfire_soundid;
	}
	
	/**
	 * set 被攻击时音效编号
	 */
	public void setQ_underfire_soundid(String q_underfire_soundid){
		this.q_underfire_soundid = q_underfire_soundid;
	}
	
	/**
	 * get 死亡时音效编号
	 * @return 
	 */
	public String getQ_die_soundid(){
		return q_die_soundid;
	}
	
	/**
	 * set 死亡时音效编号
	 */
	public void setQ_die_soundid(String q_die_soundid){
		this.q_die_soundid = q_die_soundid;
	}
	
	/**
	 * get 怪物在场景中的发言频率间隔(单位：毫秒)
	 * @return 
	 */
	public int getQ_tosay_timeinterval(){
		return q_tosay_timeinterval;
	}
	
	/**
	 * set 怪物在场景中的发言频率间隔(单位：毫秒)
	 */
	public void setQ_tosay_timeinterval(int q_tosay_timeinterval){
		this.q_tosay_timeinterval = q_tosay_timeinterval;
	}
	
	/**
	 * get 怪物在场景中的发言内容(多句以分号分隔)
	 * @return 
	 */
	public String getQ_say_condition(){
		return q_say_condition;
	}
	
	/**
	 * set 怪物在场景中的发言内容(多句以分号分隔)
	 */
	public void setQ_say_condition(String q_say_condition){
		this.q_say_condition = q_say_condition;
	}
	
	/**
	 * get 怪物等级
	 * @return 
	 */
	public int getQ_grade(){
		return q_grade;
	}
	
	/**
	 * set 怪物等级
	 */
	public void setQ_grade(int q_grade){
		this.q_grade = q_grade;
	}
	
	/**
	 * get 生命值
	 * @return 
	 */
	public int getQ_maxhp(){
		return q_maxhp;
	}
	
	/**
	 * set 生命值
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
	 * get 攻击力
	 * @return 
	 */
	public int getQ_attack(){
		return q_attack;
	}
	
	/**
	 * set 攻击力
	 */
	public void setQ_attack(int q_attack){
		this.q_attack = q_attack;
	}
	
	/**
	 * get 无视防御伤害（忽视玩家防御，可以被元气盾反弹）
	 * @return 
	 */
	public int getQ_ignore_damage(){
		return q_ignore_damage;
	}
	
	/**
	 * set 无视防御伤害（忽视玩家防御，可以被元气盾反弹）
	 */
	public void setQ_ignore_damage(int q_ignore_damage){
		this.q_ignore_damage = q_ignore_damage;
	}
	
	/**
	 * get 武器攻击（可以被玩家的化龙破技能清0）
	 * @return 
	 */
	public int getQ_equip_attack(){
		return q_equip_attack;
	}
	
	/**
	 * set 武器攻击（可以被玩家的化龙破技能清0）
	 */
	public void setQ_equip_attack(int q_equip_attack){
		this.q_equip_attack = q_equip_attack;
	}
	
	/**
	 * get 防御力
	 * @return 
	 */
	public int getQ_defense(){
		return q_defense;
	}
	
	/**
	 * set 防御力
	 */
	public void setQ_defense(int q_defense){
		this.q_defense = q_defense;
	}
	
	/**
	 * get 护甲防御（可以被玩家的破甲击技能清零）
	 * @return 
	 */
	public int getQ_equip_defense(){
		return q_equip_defense;
	}
	
	/**
	 * set 护甲防御（可以被玩家的破甲击技能清零）
	 */
	public void setQ_equip_defense(int q_equip_defense){
		this.q_equip_defense = q_equip_defense;
	}
	
	/**
	 * get 暴击值
	 * @return 
	 */
	public int getQ_crt(){
		return q_crt;
	}
	
	/**
	 * set 暴击值
	 */
	public void setQ_crt(int q_crt){
		this.q_crt = q_crt;
	}
	
	/**
	 * get 闪避值
	 * @return 
	 */
	public int getQ_dodge(){
		return q_dodge;
	}
	
	/**
	 * set 闪避值
	 */
	public void setQ_dodge(int q_dodge){
		this.q_dodge = q_dodge;
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
	 * get 幸运值
	 * @return 
	 */
	public int getQ_luck(){
		return q_luck;
	}
	
	/**
	 * set 幸运值
	 */
	public void setQ_luck(int q_luck){
		this.q_luck = q_luck;
	}
	
	/**
	 * get 怪物变身设置（格式：变身类型_变身倍率|变身几率分子/变身几率分母;变身类型_变身倍率|变身几率分子/变身几率分母;）
	 * @return 
	 */
	public String getQ_variation(){
		return q_variation;
	}
	
	/**
	 * set 怪物变身设置（格式：变身类型_变身倍率|变身几率分子/变身几率分母;变身类型_变身倍率|变身几率分子/变身几率分母;）
	 */
	public void setQ_variation(String q_variation){
		this.q_variation = q_variation;
	}
	
	/**
	 * get 视野距离半径(单位：格子数)
	 * @return 
	 */
	public int getQ_eyeshot(){
		return q_eyeshot;
	}
	
	/**
	 * set 视野距离半径(单位：格子数)
	 */
	public void setQ_eyeshot(int q_eyeshot){
		this.q_eyeshot = q_eyeshot;
	}
	
	/**
	 * get 巡逻距离半径(单位：格子数)
	 * @return 
	 */
	public int getQ_patrol(){
		return q_patrol;
	}
	
	/**
	 * set 巡逻距离半径(单位：格子数)
	 */
	public void setQ_patrol(int q_patrol){
		this.q_patrol = q_patrol;
	}
	
	/**
	 * get 巡逻间隔时间（毫秒）
	 * @return 
	 */
	public int getQ_patrol_time(){
		return q_patrol_time;
	}
	
	/**
	 * set 巡逻间隔时间（毫秒）
	 */
	public void setQ_patrol_time(int q_patrol_time){
		this.q_patrol_time = q_patrol_time;
	}
	
	/**
	 * get 巡逻几率（万分比）
	 * @return 
	 */
	public int getQ_patrol_pro(){
		return q_patrol_pro;
	}
	
	/**
	 * set 巡逻几率（万分比）
	 */
	public void setQ_patrol_pro(int q_patrol_pro){
		this.q_patrol_pro = q_patrol_pro;
	}
	
	/**
	 * get 追击距离半径(单位：格子数)本距离值不得小于怪物的巡逻距离值
	 * @return 
	 */
	public int getQ_pursuit(){
		return q_pursuit;
	}
	
	/**
	 * set 追击距离半径(单位：格子数)本距离值不得小于怪物的巡逻距离值
	 */
	public void setQ_pursuit(int q_pursuit){
		this.q_pursuit = q_pursuit;
	}
	
	/**
	 * get 怪物攻击时使用的默认技能(格式：技能ID_技能等级）
	 * @return 
	 */
	public String getQ_default_skill(){
		return q_default_skill;
	}
	
	/**
	 * set 怪物攻击时使用的默认技能(格式：技能ID_技能等级）
	 */
	public void setQ_default_skill(String q_default_skill){
		this.q_default_skill = q_default_skill;
	}
	
	/**
	 * get 怪物使用的特殊技能ID与触发几率列表（格式：技能ID_技能等级|触发几率分子/触发几率分母;技能ID_技能等级|触发几率分子/触发几率分母;）
	 * @return 
	 */
	public String getQ_special_skill(){
		return q_special_skill;
	}
	
	/**
	 * set 怪物使用的特殊技能ID与触发几率列表（格式：技能ID_技能等级|触发几率分子/触发几率分母;技能ID_技能等级|触发几率分子/触发几率分母;）
	 */
	public void setQ_special_skill(String q_special_skill){
		this.q_special_skill = q_special_skill;
	}
	
	/**
	 * get 怪物被攻击时是否固定少血(0否,1是)
	 * @return 
	 */
	public int getQ_fixed_hurt(){
		return q_fixed_hurt;
	}
	
	/**
	 * set 怪物被攻击时是否固定少血(0否,1是)
	 */
	public void setQ_fixed_hurt(int q_fixed_hurt){
		this.q_fixed_hurt = q_fixed_hurt;
	}
	
	/**
	 * get 怪物被攻击时固定少血值
	 * @return 
	 */
	public int getQ_fiexd_value(){
		return q_fiexd_value;
	}
	
	/**
	 * set 怪物被攻击时固定少血值
	 */
	public void setQ_fiexd_value(int q_fiexd_value){
		this.q_fiexd_value = q_fiexd_value;
	}
	
	/**
	 * get 怪物携带经验
	 * @return 
	 */
	public int getQ_carry_exp(){
		return q_carry_exp;
	}
	
	/**
	 * set 怪物携带经验
	 */
	public void setQ_carry_exp(int q_carry_exp){
		this.q_carry_exp = q_carry_exp;
	}
	
	/**
	 * get 怪物携带军功值
	 * @return 
	 */
	public int getQ_ranknum(){
		return q_ranknum;
	}
	
	/**
	 * set 怪物携带军功值
	 */
	public void setQ_ranknum(int q_ranknum){
		this.q_ranknum = q_ranknum;
	}
	
	/**
	 * get 是否有额外军功值 0为无军功值 N为额外的军功值数值
	 * @return 
	 */
	public int getQ_extra_ranknum(){
		return q_extra_ranknum;
	}
	
	/**
	 * set 是否有额外军功值 0为无军功值 N为额外的军功值数值
	 */
	public void setQ_extra_ranknum(int q_extra_ranknum){
		this.q_extra_ranknum = q_extra_ranknum;
	}
	
	/**
	 * get 是否排除在经验衰减规则之外（1不排除，0排除在经验衰减规则之外）
	 * @return 
	 */
	public int getQ_isexclude(){
		return q_isexclude;
	}
	
	/**
	 * set 是否排除在经验衰减规则之外（1不排除，0排除在经验衰减规则之外）
	 */
	public void setQ_isexclude(int q_isexclude){
		this.q_isexclude = q_isexclude;
	}
	
	/**
	 * get 爆出带强化等级装备的几率（格式：强化等级|几率;强化等级|几率;强化等级|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个强化等级
	 * @return 
	 */
	public String getQ_intensify_prob(){
		return q_intensify_prob;
	}
	
	/**
	 * set 爆出带强化等级装备的几率（格式：强化等级|几率;强化等级|几率;强化等级|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个强化等级
	 */
	public void setQ_intensify_prob(String q_intensify_prob){
		this.q_intensify_prob = q_intensify_prob;
	}
	
	/**
	 * get 爆出带附加属性装备的几率（格式：附加属性条数|几率;附加属性条数|几率;附加属性条数|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个条数
	 * @return 
	 */
	public String getQ_addition_prob(){
		return q_addition_prob;
	}
	
	/**
	 * set 爆出带附加属性装备的几率（格式：附加属性条数|几率;附加属性条数|几率;附加属性条数|几率;)互斥几率，计算规则：所有几率相加，在1-几率之和之间取值，判断该值落于哪个区间段则附加哪个条数
	 */
	public void setQ_addition_prob(String q_addition_prob){
		this.q_addition_prob = q_addition_prob;
	}
	
	/**
	 * get BOSS类怪物，固定刷新时间定义
	 * @return 
	 */
	public String getQ_refreshtime(){
		return q_refreshtime;
	}
	
	/**
	 * set BOSS类怪物，固定刷新时间定义
	 */
	public void setQ_refreshtime(String q_refreshtime){
		this.q_refreshtime = q_refreshtime;
	}
	
	/**
	 * get 怪物的重生时间(单位：秒)
	 * @return 
	 */
	public int getQ_revive_time(){
		return q_revive_time;
	}
	
	/**
	 * set 怪物的重生时间(单位：秒)
	 */
	public void setQ_revive_time(int q_revive_time){
		this.q_revive_time = q_revive_time;
	}
	
	/**
	 * get 关联的AI脚本ID
	 * @return 
	 */
	public int getQ_script_id(){
		return q_script_id;
	}
	
	/**
	 * set 关联的AI脚本ID
	 */
	public void setQ_script_id(int q_script_id){
		this.q_script_id = q_script_id;
	}
	
	/**
	 * get 是否跨服同步信息（0否）
	 * @return 
	 */
	public int getQ_info_sync(){
		return q_info_sync;
	}
	
	/**
	 * set 是否跨服同步信息（0否）
	 */
	public void setQ_info_sync(int q_info_sync){
		this.q_info_sync = q_info_sync;
	}
	
	/**
	 * get [{"类型":1,"间隔":10000,"几率":10000,"说话":"怪物计时器触发","预警":0,"触发":10002_2,"对象":1},{"类型":2,"几率":5000,"说话":"怪物攻击触发","预警":0,"触发":10012_2,"对象":1},{"类型":3,"血量":5,"几率":10000,"说话":"怪物掉血触发2","预警":0,"触发":10003_3,"对象":1}] 
	 * @return 
	 */
	public String getQ_monster_ai(){
		return q_monster_ai;
	}
	
	/**
	 * set [{"类型":1,"间隔":10000,"几率":10000,"说话":"怪物计时器触发","预警":0,"触发":10002_2,"对象":1},{"类型":2,"几率":5000,"说话":"怪物攻击触发","预警":0,"触发":10012_2,"对象":1},{"类型":3,"血量":5,"几率":10000,"说话":"怪物掉血触发2","预警":0,"触发":10003_3,"对象":1}] 
	 */
	public void setQ_monster_ai(String q_monster_ai){
		this.q_monster_ai = q_monster_ai;
	}
	
	/**
	 * get 怪物被动技能
	 * @return 
	 */
	public String getQ_passive_skill(){
		return q_passive_skill;
	}
	
	/**
	 * set 怪物被动技能
	 */
	public void setQ_passive_skill(String q_passive_skill){
		this.q_passive_skill = q_passive_skill;
	}
	
}