package com.game.count.structs;

/**
 * 计数类型枚举
 * @author heyang
 *
 */
public enum CountTypes {
	/**副本自动进入次数
	 */
	ZONE_AUTO       ("ZONE_AUTO"),
	
	/**副本手动进入次数
	 */
	ZONE_MANUAL		("ZONE_MANUAL"),

	/**多人副本今日状态
	 * 
	 */
	ZONE_TEAM_ST	("ZONE_TEAM_ST"),
	
	
	/**走路出错次数
	 */
	RUN_STEP       ("RUN_STEP"),
	
	/**校场副本限制次数
	 */
	JIAOCHANG_NUM       ("JIAOCHANG_NUM"),
	
	/**过关斩将副本限制次数
	 */
	ZHANJIANG_NUM       ("ZHANJIANG_NUM"),
	
	/**迷踪幻境副本限制次数
	 */
	MIZONG_NUM       ("MIZONG_NUM"),
	/**
	 * 宠物合体
	 */
	PET_HT			("PET_HT"),
	/**
	 * 宠物出战次数
	 */
	PET_SHOW		("PET_SHOW"),
	/**
	 * 讨伐任务完成数
	 */
	CONQUERTASK_FINSH	("CONQUERTASK_FINSH"),
	
	/**迷宫领奖励次数
	 * 
	 */
	MAZE_NUM	("MAZE_NUM"),
	
	/**
	 * 修为丹
	 */
	XIUWEIDANTOP	("XIUWEIDANTOP"),
	
	/**
	 * 真气丹
	 */
	ZHENQIDANTOP	("ZHENQIDANTOP"),
	
	/**
	 * 万寿修为丹日统计
	 */
	WANSHOUXIUWEIDANDAYCOUNT("WANSHOUXIUWEIDANDAYCOUNT"),
	
	/**
	 * 今日获得军功值
	 */
	DAY_RANK	("DAY_RANK"),
	/**
	 * 每月签到次数
	 */
	SIGN_NUM	("SIGN_NUM"),
	/**
	 * 七曜战魂搜索次数
	 */
	FIGHTSPIRIT_NUM	("FIGHTSPIRIT_NUM"),
	/**
	 * 紫色讨伐任务秒杀次数
	 */
	CONQUERTASKQUICK_NUM	("CONQUERTASKQUICK_NUM"),
	/**
	 * 物品使用次数
	 */
	ITEM_USE	("ITEM_USE"),
	
	/**
	 * 经验丹使用
	 */
	DANYAO_ADDEXP  	("DANYAO_ADDEXP"),
	
	/**
	 * 真气丹使用
	 */
	DANYAO_ADDZHENQI  	("DANYAO_ADDZHENQI"),
	
	/**春节对联
	 * 
	 */
	COUPLET		("COUPLET"),
	
	/**
	 * 情人节使用
	 */
	VALENTINE_DAY		("VALENTINE_DAY"),

	
	/**
	 * 累计送出红包
	 */
	RED_ENVELOPE		("RED_ENVELOPE"),
	
	
	
	/**
	 * 参加婚宴累计获得经验
	 */
	WEDDING_EXP		("WEDDING_EXP"),
	
	
	
	/**
	 * 参加婚宴累计获得真气
	 */
	WEDDING_ZHENQI		("WEDDING_ZHENQI"),
	
	
	/**
	 * 每天使用他人凝丹数量 
	 */
	USE_NINGDAN		("USE_NINGDAN"),	
	
	
	;
	
	private String value;
	
	CountTypes(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}
