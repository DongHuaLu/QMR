package com.game.vip.struts;

/**引导连接类型
 * 
 * @author zhangrong
 *
 */
public enum GuideType  {
	
//	坐骑系统
	HORSE (3),
	
//	宝石升级系统
	GEM_A (4),
	
//	极品宝石激活
	GEM_B (5),
	
//	宝石强化符
	GEM_QH (6),
	
//	装备强化
	EQUIP(7),
	
//	装备升阶
	EQUIP_UP(8),
	
//	弓箭(有可搜索战魂时)
	ARROW_A(9),
	
//	百宝袋
	BAIBAODAI(10),
	
//	神树(奇异果)
	SPIRITTREE(11),
	
//	VIP(已购买至尊VIP时)
	VIP (12),
	
//   首充激活美女	
	JIHUOMEINV (13),

	
//	真气兑换  霸者
	ZHENQI_16019 (15),
	
//	经验兑换 霸者
	EXP_16017 (16),
	
//	一键日常
	TASK_DAY (17),
	
//	一键军功
	RANK (18),
	
//	一键讨伐
	TASK_TAOFA (19),

//	坐骑丹限购
	ZUOQIDAN (20),
	
	//激活套装 普通
	TAOZHUANG (21),
	
	//激活套装紫色
	TAOZHUANG_ZISE  (22),
	
	//龙皇逆鳞
	LONGHUANG  (23),
	
//	真气兑换 王威真气丹
	ZHENQI_16020 (24),
	
//	经验兑换 王威经验丹
	EXP_16018 (25),
	
//	真气兑换 海量真气丹
	ZHENQI_16024 (26),
	
//	经验兑换 海量经验丹
	EXP_16023 (27),

//  激活骑兵
	HORSEWEAPON (28),
	
//	骑兵升级
	HORSEWEAPON_UP (29),
	
	//	宝箱抽奖
	CHESTBOX_GETITEM (30),
	
	//比武岛查看
	BIWUDAO (31),
	
	//比武岛传送
	BIWUDAO_MOVE (32),
	
	//	坐骑炼骨丹
	LIANGUDAN (33),
	
	//	境界激活
	REALM (34),
	
	//绝世宝箱
	JSBX (35),
	
	//	境界突破
	REALM_TUPO (36),
	
	//	精美礼包
	JMLB (37),
	
	// 暗器激活
	HIDDENWEAPON(38),
	
	// 暗器升级
	HIDDENWEAPON_UP(39)
	;
	private int value;

	public int getValue() {
		return value;
	}

	private GuideType(int value) {
		this.value = value;
	}

}
