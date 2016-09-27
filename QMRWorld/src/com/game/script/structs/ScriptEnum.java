package com.game.script.structs;

public class ScriptEnum {
	//地图创建脚本

	public static int MAP_CREATE = 1;
	//副本创建脚本
	public static int ZONE_CREATE = 2;
	//进入地图格子脚本
	public static int ENTER_GRID = 3;
	//人物死亡脚本
	public static int PLAYER_DIE = 4;
	//怪物死亡脚本
	public static int MONSTER_DIE = 5;
	//人物传送脚本
	public static int CHANGE_MAP = 6;
	//人物传送条件判断脚本
	public static int BEFORE_CHANGE_MAP = 7;
	//进入地图脚本
	public static int ENTER_MAP = 8;
	//npc功能脚本（待做）
	public static int NPC_ACTION = 9;
	//人物加载完成前,
	public static int BEFORE_PLAYER_LOAD = 10;
	//人物加载完成
	public static int PLAYER_LOAD = 11;
	//人物登录时
	public static int PLAYER_LOGIN = 12;
	//GM命令
	public static int GM_COMMAND = 13;
	//目标受到伤害
	public static int HIT_DAMAGE = 14;
	//传送点传送
	public static int ON_TELEPORTERS = 15;
	//掉落物品消失
	public static int DROP_CLEAR = 16;
	//npc功能列表脚本
	public static int NPC_SERVICES = 17;
	//副本延时事件脚本
	public static int ZONE_EVENT = 18;
	//NPC是否可见脚本
	public static int NPC_SEE = 19;
	//服务器每分钟调用
	public static int SERVER_EVENT = 20;
	//MONSTER是否可见脚本
	public static int MONSTER_SEE = 21;
	//退出地图脚本
	public static int QUIT_MAP = 22;
	/**
	 * 主线任务接受后
	 */
	public static int TASK_ACCEPTAFTER = 23;
	/**
	 * 主线任务完成后
	 */
	public static int TASK_FINISHAFTER = 24;
	//掉落物品是否可见脚本
	public static int DROPGOODS_SEE = 25;
	//副本消失
	public static int ZONE_DELETE = 26;
	
	//领取礼金
	public static int RECEIVELIJINGIFT = 27;
	
	/**
	 * 主线任务达成
	 */
	public static int MAINTASK_REACH=28;
	
	/**
	 * 坐骑进阶
	 */
	public static int HORSE_UP=29;
	
	/**
	 * 商城物品列表
	 */
	public static int SHOP_ITEM=30;
	
	/**
	 * 怪物死亡掉落
	 */
	public static int MONSTER_DROP=31;
	
	/**
	 * 怪物是否可攻击对手
	 */
	public static int MONSTER_ATTACK=32;
	
	/**
	 * 怪物是否可以被对手攻击
	 */
	public static int MONSTER_BEATTACK=33;
	
	/**
	 * 怪物停止走路
	 */
	public static int MONSTER_STOP=34;
	
	/**
	 * 查询充值
	 */
	public static int QUERYRECHAGE=35;
	
	/**
	 * 基础活动脚本
	 */
	public static int BASEACTIVITIES=36;
	
	/**
	 * 商店购买脚本
	 */
	public static int SHOPBUY=37;
	
	

	/*
	 * 玩家被攻击脚本
	 */
	public static int PLAYERWASHIT=38;
	
	/**
	 * 宠物被攻击脚本
	 */
	public static int PETWASHIT=39;
	
	/**
	 * 攻击检查脚本
	 */
	public static int CHECKATTACK=40;
	
	/**
	 * CDKEY解析脚本
	 */
	public static int CARDPARSE=41;
	
	/**
	 * 后台脚本
	 */
	public static int BACKEND=99;
	/**
	 * 后台游戏服脚本
	 */
	public static int BACKENDSERVER=100;
	/**
	 * 腾讯道具发放接口脚本
	 */
	public static int TXADDITEMS=101;
	
	/**
	 * 充值脚本
	 */
	public static int RECHARGE = 102;
	
	/**
	 * 公测活动脚本  BETA
	 */
	public static int BETA_NOVICECODE=2080;        //新手激活码
	public static int BETA_SUMRECHARGE500=2081;    //累积充值500
	public static int BETA_SUMRECHARGE1000=2082;   //累积充值1000
	public static int BETA_SUMRECHARGE2000=2083;   //累积充值2000
	public static int BETA_SUMRECHARGE5000=2084;   //累积充值5000
	public static int BETA_SUMRECHARGE10000=2085;  //累积充值10000
	public static int BETA_TOPLISTLEVEL=2086;      //等级排名
	public static int BETA_TOPLISTHORSE=2087;      //坐骑排名
	public static int BETA_TOPLISTSKILL=2088;      //武功排名
	public static int BETA_LORDGUILD=2089;         //公测王帮领奖
	public static int BETA_ALLLEVEL=2090;           //全民练级奖励
	public static int BETA_ALLHORSE=2091;           //全民坐骑奖励
	public static int BETA_ALLSKILL=2092;           //全民武功奖励
	public static int BETA_SHOPDISCOUNT=2093;      //商城打折
	public static int BETA_VIPREWARD=2094;         //开通VIP奖励
	public static int BETA_SEALREWARD=2095;        //封测礼金
	/**
	 * 国庆活动脚本 GUOQING
	 */
	public static int GQHORSE6=2100;	//坐骑升阶触手可得 5-6
	public static int GQHORSE7=2101;	//6-7
	public static int GQHORSE8=2102; 	//7-8
	public static int GQHORSE9=2103;	//8-9
	public static int GQHORSE10=2104;	//9-10
	public static int GQRECHARGE500=2105;	//累计充值500
	public static int GQRECHARGE2000=2106;	//累计充值2000
	public static int GQRECHARGE5000=2107;	//累计充值5000
	public static int GQRECHARGE10000=2108;	//累计充值10000
	public static int GQRECHARGE100000=2109;//累计充值100000
}
