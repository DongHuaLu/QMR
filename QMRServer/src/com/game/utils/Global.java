package com.game.utils;

import com.game.data.bean.Q_globalBean;
import com.game.data.manager.DataManager;

public class Global {
	/**
	 * 万分比
	 */
	public static int MAX_PROBABILITY = 10000;
	/**
	 * 最大等级
	 */
	public static int MAX_LEVEL = 120;
	/**
	 * 最大战场声望
	 */
	public static int MAX_BATTLEEXP = 10000;
	/**
	 * 暴击倍率
	 */
	public static int CRIT_MULTIPLE = 2;
	/**
	 * PVP敌对过期时间
	 */
	public static int ENEMY_TIME = 60000;
	/**
	 * 格挡清除debuff间隔
	 */
	public static int CLEAN_DEBUFF_TIME = 2000;
	/**
	 * boss死亡后被攻击间隔
	 */
	public static int BOSS_ATTACK_TIME = 1000;
	/**
	 * boss死亡后站立时间
	 */
	public static int BOSS_DIEING = 10000;
	/**
	 * 自动复活时间
	 */
	public static int REVIVE_TIME = 10000;
	/**
	 * 自动回血比例
	 */
	public static int RECOVER_HP = 1000;
	/**
	 * 自动回内力比例
	 */
	public static int RECOVER_MP = 1000;
	/**
	 * 自动回体力比例
	 */
	public static int RECOVER_SP = 1000;
	/**
	 * 自动回复间隔
	 */
	public static long RECOVER_TIME = 10*1000;
	/**
	 * 攻击过期时间
	 */
	public static long OVERDUE = 6000;
	/**
	 * 最小跳跃时间
	 */
	public static int MIN_JUMPTIME = 500;
	/**
	 * 最大跳跃距离
	 */
	public static int MAX_JUMPDISTANCE = 500;
	/**
	 * 跳跃数据加成
	 */
	public static int JUMP_SPEED = 25000;
	/**
	 * 跳跃最大速度
	 */
	public static int JUMP_MAX_SPEED = 750;
	/**
	 * 格挡开始延迟时间
	 */
	public static int BLOCK_START = 500;
	/**
	 * 格挡伤害最大半径
	 */
	public static int BLOCK_DAMAGE_RADIUS = 195;
	/**
	 * 格挡伤害最大数量
	 */
	public static int BLOCK_DAMAGE_NUM = 8;
	/**
	 * 战斗状态过期时间
	 */
	public static long FIGHT_OVERDUE = 12000;
	/**
	 * 游泳速度
	 */
	public static int SPEED_FOR_SWIM = 80;
	/**
	 * 游泳BUFF
	 */
	public static int BUFF_FOR_SWIM = 1203;
	/**
	 * 语言区域
	 */
	public static final String LANGUAGE_CODE="cn";
	
	
	
	/*****************************包裹仓库部分********************************/
	/**
	 * 包裹默认开启的格子数
	 */
	public static int DEFAULT_BAG_CELLS=42;
	/**
	 * 开启自动开格的格数
	 */
	public static int BAG_AUTOOPEN_CELL_ID=42;
	/**
	 * 包裹最大开格数量
	 */
	public static int MAX_BAG_CELLS=147;
	
	/**
	 * 仓库默认开启的格子数
	 */
	public static int DEFAULT_STORE_CELLS=50;
	/**
	 * 开启自动开格的格数
	 */
	public static int STORE_AUTOOPEN_CELL_ID=50;
	/**
	 * 仓库最大开格数量
	 */
	public static int MAX_STORE_CELLS=200;
	
	
	
	/**
	 * 整理仓库和包裹的时间间隔(秒)
	 */
	public static int CLEARUP_TIME_INTERVAL=30;
	
	/**
	 * 包裹类型
	 */
	public static final byte BAG_TYPE=1;
	
	/**
	 * 仓库类型
	 */
	public static final byte STORE_TYPE=2;
	
	/**
	 * 仓库开启等级
	 */
	public static final byte STORE_NEED_GRADE=13;
	
	/**
	 * 最大携带金币数
	 */
	public static final int BAG_MAX_COPPER=2000000000;
	/**
	 * 最大携带元宝数
	 */
	public static final int BAG_MAX_GOLD=2000000000;
	
	/**
	 * 最大携带绑定元宝数
	 */
	public static final int BAG_MAX_BINDGOLD=2000000000;
	
	
	/*****************************包裹仓库部分结束*********************************/
	
	/******************************掉落部分*************************************/
	/**
	 * 普通怪掉落衰减等级
	 */
	public static int DROP_COMMON_GRADE_LIMIT=10;
	/**
	 * BOSS掉落衰减等级
	 */
	public static int DROP_BOSS_GRADE_LIMIT=20;
	/******************************掉落部分结束*************************************/
	
	/**
	 * 与NPC的交易距离(象素)
	 */
	public static short NPC_TRADE_DISTANCE=300;
	
	/**
	 * 回购列表最大长度
	 */
	public static short BUYBACK_LIST_MAX=27;
	
	/*******************************聊天部分*************************************/
	/**
	 * 世界聊天最小等级
	 */
	public static short CHAT_WORLD_MIN_GRADE=10;
	/**
	 * 普通聊天最小时间间隔(秒)
	 */
	public static short CHAT_SCENE_MIN_TIME=3;	//普通聊天最小时间间隔(秒)
	
	/**
	 * 私人聊天最小时间间隔(秒)
	 */
	public static short CHAT_PRIVATE_MIN_TIME=3;	//普通聊天最小时间间隔(秒)
	
	/**
	 * 队伍聊天最小时间间隔（秒）
	 */
	public static short CHAT_TEAM_MIN_TIME=3;	//队伍聊天最小时间间隔（秒）
	
	/**
	 * 帮会聊天最小时间间隔(秒)
	 */
	public static short CHAT_GROUP_MIN_TIME=3;	
	
	/**
	 * 世界聊天最小时间间隔(秒)
	 */
	public static short CHAT_WORLD_MIN_TIME=7;	
	
	/**
	 * 喇叭聊天最小时间间隔(秒)
	 */
	public static short CHAT_LABA_MIN_TIME=10;
	
	/**
	 * GM聊天最小时间间隔(毫秒)
	 */
	public static int CHAT_GM_MIN_TIME=200;
	
	/*******************************聊天结束***************************************/
	
	/*******************************任务部分***************************************/
	/**
	 * 击杀怪物有效任务计数所需的伤害比例
	 */
	public static double TASK_EFFECTIVE_DAMAGE_RATIO=0.2;
	
	/**
	 * 击杀精英怪物有效任务计数所需的伤害比例
	 */
	public static double TASK_EFFECTIVE_JINYIN_DAMAGE_RATIO=0.1;
	
	
	
	/*******************************宠物部分*********************************/
	/**
	 * 跟随中触发寻路的距离（格子)
	 */
	public static int PET_RUNNING_FOLLOW_DISTANCE=2;
	/**
	 * 触发跟随的距离（格子)
	 */
	public static int PET_TIGGER_FOLLOW_DISTANCE=5;
	/**
	 * 触发跳跃的距离(格子)
	 */
	public static int PET_TIGGER_JUMP_DISTANCE=10;
	/**
	 * 触发二次跳跃的距离(格子)
	 */
	public static int PET_TIGGER_DOUBLEJUMP_DISTANCE=15;
	/**
	 * 触发传送的距离(格子)
	 */
	public static int PET_TIGGER_TRANSFER_DISTANCE=30;
	/**
	 * 触发传送后传到与玩家格子的距离(格子)
	 */
	public static int PET_TIGGER_TRANSFER_TO=24;
	/**
	 * 美人回复血量需要空闲的时间(毫秒)
	 */
	public static final long PET_RECOVERY_NEEDIDELTIME = 20*1000;
	
	/**
	 * 美人打坐状态下的回血(毫秒)
	 */
	public static final long PET_DAZUORECOVERY_STEP=10*1000;
	/**
	 * 美人回复血量时间间隔(毫秒)
	 */
	public static final long PET_RECOVERY_INTERVALTIME = 10*1000;
	
	/**
	 * 美人最小跳跃时间
	 */
	public static final int PET_MINJUMPTIME=500;
	/**
	 * 美人最大跳跃距离
	 */
	public static final int PET_MAX_JUMPDISTANCE = 500;
	
	/*******************************交易部分*********************************/
	/**
	 * 交易获得经验的冷却时间(毫秒)
	 */
	public static int TS_EXP_TIME=1000*120;
	/**
	 * 摆摊获得经验的冷却时间(毫秒)
	 */
	public static int STALL_EXP_TIME=1000*120;
	/**
	 * 摊位搜索间隔(毫秒)
	 */
	public static int STALL_SEARCH_TIME=1000*1;
	
	/*******************************buff部分*********************************/
	/**
	 * 复活获得的保护buff
	 */
	public static int PROTECT_FOR_KILLED = 1032;
	/**
	 * 夜晚挂机保护buff
	 */
	public static int PROTECT_IN_NIGHT = 1033;
	
	/**攻城战和平保护BUFF
	 * 
	 */
	public static int PROTECT_IN_SIEGE = 1034;
	
	/**攻城战中，获得玉玺加 BUFF
	 * 
	 */
	public static int PROTECT_IN_BANGZHU = 22214;
	
	
	/**
	 * 夜晚挂机保护buff开始时间
	 */
	public static int PROTECT_IN_NIGHT_START = 0;
	/**
	 * 夜晚挂机保护buff结束时间
	 */
	public static int PROTECT_IN_NIGHT_END = 8;
	/**
	 * 夜晚挂机保护buff延迟时间（毫秒）
	 */
	public static int PROTECT_IN_NIGHT_DEALY = 5000;
	
	/**
	 * 定时领取时间间隔
	 */
	public static int RECEIVE_GIFT_TIMESTEP=60*60*1000;
	
	/*******************************组队部分*********************************/
	/**
	 * 搜索地图队伍和玩家
	 */
	public static int TEAM_SEARCH_TIME = 2000;
	
	/**
	 * 夜晚打座时间
	 */
	public static String DAZUO_NIGHT_TIME="[*][*][*][*][00:00-8:00]";
	
	/**
	 * 单次打座最大爆击次数
	 */
	public static int ONCEDAZUO_MAXERUPTCOUNT=10;
	
	/**
	 * 进入排行榜人物等级
	 */
	public static int SYNC_PLAYER_LEVEL = 30;
	
	/**
	 * 进入排行榜坐骑阶数
	 */
	public static int SYNC_HORSE_STAGE = 3;
	
	/**
	 * 进入排行榜技能层数
	 */
	public static int SYNC_SKILL_LEVEL = 50;
	
	/**
	 * 进入排行榜连斩最小次数
	 */
	public static int SYNC_EVENT_CUT = 100;
	
	/**
	 * 进入排行榜龙元心法层数
	 */
	public static int SYNC_LONGYUAN = 4;
	
	/**
	 * 进入排行榜侍宠最小MODELID
	 */
	public static int SYNC_EVENT_PET = 4;
	
	/**
	 * 进入排行榜战斗力数值
	 */
	public static int SYNC_FIGHTPOWER = 10000;
	
	/**
	 * 进入排行榜弓箭等阶
	 */
	public static int SYNC_ARROW = 1;
	
	/**
	 * 大恶人buff
	 */
	public static int EVIL_BUFF = 1050;
	
	/**
	 * 新人buff
	 */
	public static int NEWBIE_BUFF = 1051;
	
	/**
	 * 紫芒技能
	 */
	public static int ZIMANG_SKILL = 21007;
	
	/**
	 * 黄色装备加成
	 * 黄色品质装备附加属性修正系数：10500
	 */
	public static int YELLOW_EQUIP_ADD = 10500;
	
	/**
	 * 绿色装备加成
	 * 绿色品质装备附加属性修正系数：11500
	 */
	public static int GREEN_EQUIP_ADD = 11500;
	
	/**
	 * 蓝色装备加成
	 * 蓝色品质装备附加属性修正系数：15000
	 */
	public static int BLUE_EQUIP_ADD = 15000;
	
	/**
	 * 紫色装备加成
	 * 紫色品质装备附加属性修正系数：20000
	 */
	public static int PURPLE_EQUIP_ADD = 20000;
	
	/**
	 * 服务器心跳参数字符串
	 */
	public static String HEART_PARA = "pt=%s&sid=%d&state=%d&tip=alive";
	
	/**
	 * 服务器心跳地址字符串
	 */
	public static String HEART_WEB = "http://122.226.64.45:9080/QMRBackend/r/callback.do?c=tablehearbeat";
	
	/**
	 * 卸下骑战兵器技能
	 */
	public static int UNWEAR_HORSE_WEAPON_SKILL = 25001;
	
	/**
	 * 长虹贯日技能
	 */
	public static int CHANGHONE_SKILL = 10002;
	
	/**
	 * 作弊间隔
	 */
	public static int CHECK_BETWEEN = 30 * 1000;
	
	/**
	 * 允许误差基本距离
	 */
	public static int DISTANCE = 100;
	
	/**
	 * 基本速度
	 */
	public static int BASE_SPEED = 100;
	
	/**
	 * 获取配置化参数
	 * @param config
	 * @return
	 */
	public static Q_globalBean getGlobalBean(CommonConfig config){
		return DataManager.getInstance().q_globalContainer.getMap().get(config.getValue());		
	}
}
