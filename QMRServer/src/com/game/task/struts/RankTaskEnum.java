package com.game.task.struts;

/**
 * 军衔任务类型枚举
 *
 * @author 杨鸿岚
 */
public class RankTaskEnum {

	/**
	 * //击杀怪物数量	1	击杀与玩家角色等级，相差5级范围的怪物，才能计数
	 */
	public static int KILLNORMAL = 1;
	/**
	 * //击杀精英数量	2	击杀与玩家角色等级，相差10级范围的精英才能计数
	 */
	public static int KILLELITE = 2;
	/**
	 * //击杀BOSS数量	3	击杀与玩家角色等级，相差10级范围的BOSS才能计数
	 */
	public static int KILLBOSS = 3;
	/**
	 * //王帮战中击杀非本帮玩家	4	仅在每周6才会出现任务，在战场中击杀玩家有效——若当前周6不开启攻城战，则隐藏任务
	 */
	public static int KINGKILLNOSAMEGUILDPLAYER = 4;
	/**
	 * //神树浇水	5	给自己、帮会神树浇水均可完成任务，浇水数量根据配置
	 */
	public static int SPIRITTREEWATERING = 5;
	/**
	 * //每日登陆	6	每日登陆后直接完成任务并获得奖励，每日1次
	 */
	public static int EVERYDAYLOGIN = 6;
	/**
	 * //完成日常任务	7	完成当天的20环日常任务后方可领取，直接付费完成的也算
	 */
	public static int COMPLETEDAYTASK = 7;
	/**
	 * //消费礼金	8	消费礼金达到多少，即可完成任务
	 */
	public static int COSTBINDGOLD = 8;
	/**
	 * //消费元宝	9	消费元宝达到多少，即可完成任务
	 */
	public static int COSTGOLD = 9;
	/**
	 * //通关经典战役	10	完成经典战役副本通关次数6次，即可完成任务
	 */
	public static int COMPLETECLASSICBATTLE = 10;
	/**
	 * //使用道具类型	11	按照任务要求，使用某种物品成功，即可完成任务
	 */
	public static int USEITEM = 11;
	/**
	 * //点绛唇摇香唇	12	根据摇到的香唇数量来完成任务，逆天改运获得的香唇，也算
	 */
	public static int INCENSELIP = 12;
	/**
	 * //击杀仇人	13	击杀与自己等级相差5级范围的仇人，可完成任务；同一名仇人只能计1次击杀
	 */
	public static int KILLENEMY = 13;
	/**
	 * //完成讨伐任务	14	每日完成讨伐任务的数量达到多少，即可完成任务；吞噬的讨伐任务只算1个；
	 */
	public static int COMPLETECONQUERTASK = 14;
	/**
	 * //打掉帮旗	15
	 * 在帮旗争夺战的当天，出现该任务；完成方式同击杀BOSS的判断：打掉20%的血或最后1刀，若帮旗死了算完成，帮旗不死就不能完成；
	 */
	public static int KILLGUILDBANNER = 15;
	/**
	 * //获得物品	16	指定通过打怪、购买获得某种物品，并达到一定数量，即可完成任务
	 */
	public static int GETITEM = 16;
	/**
	 * //地宫寻宝任务完成	17	在地宫寻宝中，触发任务并完成，完成的数量根据配置
	 */
	public static int COMPLETETREASUREhUNTTASK = 17;
	/**
	 * //摘取神树果子	18	摘取其他玩家的神树果子，根据摘取的数量来完成任务
	 */
	public static int HARVESTINGSPIRITTREEFRUIT = 18;
	/**
	 * //采集类	19	按照任务要求，在某张地图采集某种物品，即可完成任务
	 */
	public static int COMPLETEGATHER = 19;
	/**
	 * //组队完成副本X次	20	在组队情况下，通关多人副本即可完成任务
	 */
	public static int COMPLETETEAMZONE = 20;
	/**
	 * //获得真气xx	21	通过各种方式获得真气数量达到多少，即可完成
	 */
	public static int GETZHENQI = 21;
}
