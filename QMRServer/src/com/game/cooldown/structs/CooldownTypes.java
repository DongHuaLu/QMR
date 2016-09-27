package com.game.cooldown.structs;


/**
 * 冷却类型枚举
 * @author heyang
 *
 */
public enum CooldownTypes {
	//总公共冷却
	PUBLIC			("PUBLIC"),
	//药品公共冷却
	DRUG_PUBLIC     ("DRUG_PUBLIC"),
	//药品冷却
	DRUG			("DRUG"),
	//技能公共冷却
	SKILL_PUBLIC	("SKILL_PUBLIC"),
	//技能冷却
	SKILL			("SKILL"),
	//技能触发公共冷却
	SKILL_TRIGGER_PUBLIC	("SKILL_TRIGGER_PUBLIC"),
	//技能触发冷却
	SKILL_TRIGGER		("SKILL_TRIGGER"),
	//包裹整理冷确
	BAG_CLEAR		("BAG_CLEAR"),
	//仓库整理冷确
	STORE_CLEAR		("STORE_CLEAR"),
	//走路冷却
	STEP			("STEP"),
	//移动冷却
	RUN				("RUN"),
	//移动冷却
	RUN_STEP		("RUN_STEP"),
	//跳跃冷却
	JUMP			("JUMP"),
	//跳跃落地冷却
	JUMP_COOLDOWN	("JUMP_COOLDOWN"),
	//格挡冷却
	BLOCK			("BLOCK"),
	//自动回复
	RECOVER			("RECOVER"),
	//boss死亡时被攻击冷却
	BE_ATTACK		("BE_ATTACK"),
	//怪物跑动冷却
	MONSTER_RUN		("MONSTER_RUN"),
	//怪物攻击冷却
	MONSTER_ATTACK	("MONSTER_ATTACK"),
	//怪物巡逻冷却
	MONSTER_PATROL	("MONSTER_PATROL"),
	//debuff清除
	CLEAR_DEBUFF	("CLEAR_DEBUFF"),
	//交易经验冷却
	TS_EXP_CD		("TS_EXP_CD"),
	//摆摊经验冷却
	STALL_EXP_CD	("STALL_EXP_CD"),
	//摊位道具搜索冷却
	STALL_SEARCH	("STALL_SEARCH"),
	//摊位金币搜索冷却
	STALL_MONEYSEARCH	("STALL_MONEYSEARCH"),
	//摊位元宝搜索冷却
	STALL_YBSEARCH	("STALL_YBSEARCH"),
	//怪物施展技能动作冷却
	MONSTER_ACTION	("MONSTER_ACTION"),
	//队伍搜索
	TEAM_SHARCH		("STALL_SEARCH"),
	//队伍搜索(打开搜索面板)
	TEAM_OPEN_SHARCH		("TEAM_OPEN_SHARCH"),
	//宠物合体
	PET_HETI		("PET_HETI"),
	//宠物跑动冷确
	PET_RUN			("MONSTER_RUN"),
	//宠物攻击冷却
	PET_ATTACK		("PET_ATTACK"),
	//宠物动作冷确
	PET_ACTION		("PET_ACTION"),
	//坐骑技能升级次数冷却
	HORSE_SKILLUPCD		("HORSE_SKILLUPCD"),
	//攻击保护提示冷却
	NOTIFY_KILL		("NOTIFY_KILL"),
	//元宝卡数据查询
	YB_CARD		("YB_CARD"),
	//攻城车冷却时间
	SIEGE_COOL		("SIEGE_COOL"),
	
	//攻城车冷却时间
	SIEGE_SUPER_COOL		("SIEGE_SUPER_COOL"),
	
	//金钱盗贼冷却时间
	GOLDTHIEF_COOL		("GOLDTHIEF_COOL"),
	//玩家攻击
	PLAYER_ATTACK	("PLAYER_ATTACK"),
	

	//使用奇异果催熟
	USE_FRUIR	("USE_FRUIR"),
	
	;
	
	private String value;
	
	CooldownTypes(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}
