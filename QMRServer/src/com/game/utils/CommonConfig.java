package com.game.utils;

public enum CommonConfig {

	/**
	 * 第一次跳跃消耗体力
	 */
	FIRST_JUMP_RESUM(1),
	/**
	 * 第二次跳跃消耗体力
	 */
	SECOND_JUMP_RESUM(2),
	/**
	 * 跳跃冷确时间
	 */
	JUMP_COOLDOWN(3),
	/**
	 * 内力盾每秒消耗内力值比例(万分比)
	 */
	SHIELD_RESUM_PS(4),
	/**
	 * 内力盾减免所受攻击伤害比例（万分比
	 */
	SHIELD_REDUCTION(5),
	/**
	 * 内力盾造成的攻击伤害的系数（所消耗内力值*本系数）
	 */
	SHIELD_ATTACK_FACTOR(6),
	/**
	 * 人物真气获得上限
	 */
	ZHENGQI_MAX(7),
	/**
	 * 战场声望获得上限
	 */
	BATTLE_FAME_MAX(8),
	/**
	 * 顶级强化装备佩戴时将装备设为绑定（0不生效，1生效）
	 */
	EQUIP_TOPLEVEL_INTENSIFY_USEBIND(9),
	/**
	 * 顶级镶嵌装备佩戴将装备设为绑定（0不生效，1生效）
	 */
	EQUIP_TOPLEVEL_INLAY_USEBIND(10),
	/**
	 * 紫色装备佩戴时将装备设为绑定（0不生效，1生效）
	 */
	EQUIP_PURPLE_USEBIND(11),
	/**
	 * 顶级强化装备卖给商店时弹出二次确认面板提示（0不生效，1生效）
	 */
	EQUIP_TOPLEVEL_INTENSIFY_SELLCONFIRM(12),
	/**
	 * 顶级镶嵌装备卖给商店时弹出二次确认面板提示（0不生效，1生效）
	 */
	EQUIP_TOPLEVEL_INLAY_SELLCONFIRM(13),
	/**
	 * 紫色装备卖给商店时弹出二次确认面板提示（0不生效，1生效）
	 */
	EQUIP_PURPLE_SELLCONFIRM(14),
	/**
	 * 顶级强化装备丢弃时弹出二次确认面板提示（0不生效，1生效）
	 */
	EQUIP_TOPLEVEL_INTENSIFY_CHUCKCONFIRM(15),
	/**
	 * 顶级镶嵌装备丢弃时弹出二次确认面板提示（0不生效，1生效）
	 */
	EQUIP_TOPLEVEL_INLAY_CHUCKCONFIRM(16),
	/**
	 * 紫色装备丢弃时弹出二次确认面板提示（0不生效，1生效）
	 */
	EQUIP_PURPLE_CHUCKCONFIRM(17),
	/**
	 * 背包面板上的商店按钮显示所需人物等级
	 */
	BAG_BUTTON_SHOP_NEED_GRADE(18),
	/**
	 * 背包面板上的仓库按钮显示所需人物等级
	 */
	BAG_BUTTON_STORE_NEED_GRADE(19),
	/**
	 * 背包面板上的随身摊位按钮显示所需人物等级
	 */
	BAG_BUTTON_BOOTH_NEED_GRADE(20),
	/**
	 * 背包面板上的获取元宝按钮点击后打开网址
	 */
	BAG_RECHARGE_URL(21),
	/**
	 * 小怪怪物尸体的消失时间，单位秒
	 */
	DISAPPEAR_TIME_MONSTER_CORPSE(22),
	/**
	 * 小怪掉出物品的消失时间，单位秒
	 */
	DISAPPEAR_TIME_MONSTER_DROP(23),
	/**
	 * BOSS尸体的消失时间，单位秒
	 */
	DISAPPEAR_TIME_BOSS_CORPSE(24),
	/**
	 * BOSS掉出物品的消失时间，单位秒
	 */
	DISAPPEAR_TIME_BOSS_DROP(25),
	/**
	 * 1RMB = 10元宝	元宝商城面板上显示的充值比率文字描述
	 */
	RMB_EXCHANGE_RATE_DESC(26),
	/**
	 * 背包面板上的获取元宝按钮点击后打开网址
	 */
	SHOP_RECHARGE_URL(27),
	/**
	 * 自动禁言检测的时间跨度（单位：秒）
	 */
	PROHIBIT_TALK_CHECK_STEP(28),
	/**
	 * 自动禁言检测被加入黑名单的次数
	 */
	PROHIBIT_TALK_ADD_BLACKLIST_COUNT(29),
	/**
	 * 自动禁言的用户等级上限
	 */
	PROHIBIT_TALK_ROLE_MAX_GRADE(30),
	/**
	 * 自动禁言持续时间（单位：秒）
	 */
	PROHIBIT_TALK_TIME(31),
	/**
	 * 免费原地复活所限人物等级设定为
	 */
	FREE_RELIVE_MAX_GRADE(32),
	/**
	 * 主界面主操作栏按钮，人物：
	 */
	MAIN_BUTTON_ROLE(33),
	/**
	 * 主界面主操作栏按钮，背包：
	 */
	MAIN_BUTTON_BAG(34),
	/**
	 * 主界面主操作栏按钮，技能
	 */
	MAIN_BUTTON_SKILL(35),
	/**
	 * 主界面主操作栏按钮，美人：
	 */
	MAIN_BUTTON_ESCRAVAS(36),
	/**
	 * 主界面主操作栏按钮，任务：
	 */
	MAIN_BUTTON_TASK(37),
	/**
	 * 主界面主操作栏按钮，队伍：
	 */
	MAIN_BUTTON_TEAM(38),
	/**
	 * 主界面主操作栏按钮，好友：
	 */
	MAIN_BUTTON_FRIEND(39),
	/**
	 * 主界面主操作栏按钮，帮会：
	 */
	MAIN_BUTTON_GROUP(40),
	/**
	 * 主界面主操作栏按钮，设置：
	 */
	MAIN_BUTTON_SETTING(41),
	/**
	 * 主界面主操作栏按钮，商城：
	 */
	MIAN_BUTTON_SHOP(42),
	/**
	 * 主界面快捷操作栏按钮，打坐：
	 */
	MAIN_BUTTON_DAZHUO(43),
	/**
	 * 主界面快捷操作栏按钮，骑乘：
	 */
	MAIN_BUTTON_MOUNT(44),
	/**
	 * 主界面快捷操作栏按钮，设挂：
	 */
	MAIN_BUTTON_AUTO_SETTING(45),
	/**
	 * 主界面快捷操作栏按钮，挂机：
	 */
	MAIN_BUTTON_AUTO(46),
	/**
	 * 主界面雷达地图周边按钮，隐藏：
	 */
	RADAR_BUTTON_HIDDEN(47),
	/**
	 * 主界面雷达地图周边按钮，音乐：
	 */
	RADAR_BUTTON_SOUND(48),
	/**
	 * 主界面雷达地图周边按钮，成就：
	 */
	RADAR_BUTTON_ACHIEVE(49),
	/**
	 * 主界面雷达地图周边按钮，排行榜：
	 */
	RADAR_BUTTON_RANKINGLIST(50),
	/**
	 * 主界面雷达地图周边按钮，地图：
	 */
	RADAR_BUTTON_MAP(51),
	/**
	 * 主界面雷达地图周边按钮，引导：
	 */
	RADAR_BUTTON_LEAD(52),
	/**
	 * 主界面雷达地图周边按钮，商城：
	 */
	RADAR_BUTTON_SHOP(53),
	/**
	 * 人物属性面板境界图片，境界：
	 */
	ROLEINFO_PANEL_JINGJIE(54),
	/**
	 * 包裹默认开启的格子数
	 */
	BAG_CELLS_DEFAULT(55),
	/**
	 * 包裹最大开格数量
	 */
	BAG_CELLS_MAX(56),
	/**
	 * 包裹默认开启的格子数
	 */
	STORE_CELLS_DEFAULT(57),
	/**
	 * 包裹最大开格数量
	 */
	STORE_CELLS_MAX(58),
	/**
	 * 整理仓库和包裹的时间间隔(秒)
	 */
	BAG_STORE_CLEARUP_TIME_INTERVAL(59),
	/**
	 * 语言区域
	 */
	LANGUAGE(60),
	/**
	 * 最大概率系数
	 */
	BASE_PROB(61),
	/**
	 * 最小跳跃时间(毫秒)
	 */
	JUMP_MINCD(62),
	//	点绛唇最大允许次数
	DIANJIANGCHUN_MAXCOUNT(63),
	//	点绛唇最大免费改运次数
	DIANJIANGCHUN_FREECHANGELUCK_MAXCOUNT(64),
	//	点绛唇改运元宝数
	DIANJIANGCHUN_RMB_COUNT(65),
	//	点绛唇色子几率1
	DIANJIANGCHUN_COF1(66),
	//	点绛唇色子几率2
	DIANJIANGCHUN_COF2(67),
	//	点绛唇色子几率3
	DIANJIANGCHUN_COF3(68),
	//	点绛唇色子几率4
	DIANJIANGCHUN_COF4(69),
	//	点绛唇色子几率5
	DIANJIANGCHUN_COF5(70),
	//	点绛唇色子几率6
	DIANJIANGCHUN_COF6(71),
	//	军衔系统可用技能链表
	RANK_SKILL_LIST(72),
	//TODO  系统参数缺少

	/**
	 * 每日真气丹获取真气上限
	 */
	DAY_ZHENQIDAN_ZQTOP(111),
	/**
	 * 每日修为丹获取经验上限
	 */
	DAY_XIUWEIDAN_EXPTOP(112),
	
	
	/**
	 * 攻城战 弩箭
	 */
	SIEGE_CRISSBOW (113),
	
	/**
	 * 攻城战 投石车
	 */
	SIEGE_CATAPULTS (114),
	
	/**
	 * 攻城战 发射火炮需要铜币
	 */
	SIEGE_MONEY  (115),
	

	
	
	
	
	/**
	 * 王帮成员经验加成系数 
	 */
	KINGCITY_EXPCOF(116),
	/**
	 * 王帮成员打坐速度计算加成
	 */
	KINGCITY_DAZUOSPEEDADDCOF(117),
	/**
	 * 王帮成员组队状态,杀怪血量百分比<20%，修正系数
	 */
	KINGCITY_KILLMONSTER20COF(118),
	/**
	 * 王帮成员组队状态,20%<=杀怪血量<40%，修正系数
	 */
	KINGCITY_KILLMONSTER40COF(119),
	/**
	 * 王帮成员组队状态,40%<=杀怪血量<70%，修正系数
	 */
	KINGCITY_KILLMONSTER70COF(120),
	/**
	 * 王帮成员组队状态,70%<=杀怪血量，修正系数
	 */
	KINGCITY_KILLMONSTER100COF(121),
	/**
	 * 获得王帮成员BUFF限制时间
	 */
	KINGCITY_GETBUFFLIMITTIME(122),
	
	/**王城刷怪坐标 
	 * 
	 */
	SIEGE_BRUSH_XY (123),
	
	/**王城刷怪ID和数量
	 * 
	 */
	SIEGE_MON (124),

	
	/**王城刷道具坐标
	 * 
	 */
	SIEGE_ITEM_XY (126),
	
	/**王城刷道具ID
	 * 
	 */
	SIEGE_ITEM (127),
	
	
	
	/**领地争夺战，地图列表
	 * 
	 */
	GUILD_FLAG_MAP (128),
	
	
	/**领地争夺战，插旗需要的帮贡金币
	 * 
	 */
	GUILD_FLAG_GOLD (129),
	
	
	/**领地争夺战，旗帜怪物ID
	 * 
	 */
	GUILD_FLAG_MONID (130),
	
	//	香唇获得真气值
	DIANJIANGCHUN_CHUN(131),
	//	香蕉获得真气值
	DIANJIANGCHUN_XIANGJIAO(132),
	//	葡萄获得真气值
	DIANJIANGCHUN_PUTOU(133),
	//	橘子获得真气值
	DIANJIANGCHUN_JUZI(134),
	//	芒果获得真气值
	DIANJIANGCHUN_MANGGUO(135),
	//	黄瓜获得真气值
	DIANJIANGCHUN_HUANGGUA(136),
	
	/**签到工资面板，隔多少时间可摇奖，分钟
	 * 
	 */
	ERNIE_INTERVAL(137),
	
	//打坐双倍时间
	DOUBLEXP_DAZUO (139),
	
	//打怪双倍时间
	DOUBLEXP_MON (140),
	
	//熔炼花费元宝
	METING_GOLD (141),
	
	//熔炼花费铜币
	METING_MONEY (142),
	
	//轮盘外圈物品落入倾向概率增幅参数：1
	CHESTBOX_ADDDROPINCOF (143),
	
	//默认轮盘外圈落入位置概率参数：1
	CHESTBOX_DEFDROPINCOF (144),
	
	//倾向格子数：3（最大为12）
	CHESTBOX_DROPGRIDNUM (145),
	
	//开宝箱需要元宝
	CHESTBOX_OPENCHESTBOXNEEDYUANBAO (146),
	
	//开宝箱需要物品
	CHESTBOX_OPENCHESTBOXNEEDITEM (147),
	
//	/**
//	 * 触发自动禁言最高等级
//	 */
//	AUTOPROHIBIT_LEVEL(141),
//	/**
//	 * 触发自动禁言聊天内容长度
//	 */
//	AUTOPROHIBIT_LENGTH(142),
//	/**
//	 * 自动禁言记录时长
//	 */
//	AUTOPROHIBIT_TIME(143),
//	/**
//	 * 禁言时长
//	 */
//	AUTOPROHIBIT_PROHIBITTIME(144),
//	/**
//	 * 自动禁言相似重复次数
//	 */
//	AUTOPROHIBIT_COUNT(145),
//	/**
//	 * 自动禁言相似度
//	 */
//	AUTOPROHIBIT_SEMBLANCE(146),
	
	
	/**
	 * 攻城战  超级弩箭
	 */
	SIEGE_SUPRE_CRISSBOW (169),
	
	/**
	 * 攻城战  超级投石车
	 */
	SIEGE_SUPRE_CATAPULTS (170),
	
	/**
	 * 攻城战 超级车需要铜币
	 */
	SIEGE_SUPRE_MONEY (171),
	
	/**
	 * 最大跳跃速度
	 */
	JUMP_MAX_SPEED (172),
	
	/**
	 * 年兽击杀前三名的奖励
	 */
	SPRING_MONSTER_PRIZE_1 (173),
	SPRING_MONSTER_PRIZE_2 (174),
	SPRING_MONSTER_PRIZE_3 (175),
	
	/**婚戒价格
	 * 
	 */
	WEDDING_RING	(176),
	
	/**申请婚宴价格
	 * 
	 */
	WEDDING_PRICE	(177),
	
	/**参加婚宴红包数量的下限
	 * 
	 */
	WEDDING_LEAST_RED	(178),
	
	
	/**婚宴刷新坐标
	 * 
	 */
	WEDDING_XY	(179),
	
	/**并蒂连枝技能补充数值百分比
	 * 
	 */
	WEDDING_BDLZ	(180),
	
	/**2级密码排除平台列表
	 * 
	 */
	PROTECT	(182),
	
	
	/**
	 * 枚举最大值
	 */
	MAX_VALUE(999999);
	private int value;

	public int getValue() {
		return value;
	}

	private CommonConfig(int value) {
		this.value = value;
	}
}