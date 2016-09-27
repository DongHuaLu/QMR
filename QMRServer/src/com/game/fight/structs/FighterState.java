package com.game.fight.structs;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * 战斗状态枚举 
 */
public enum FighterState {
	/**
	 * 定身
	 */
	DINGSHEN   				(0x00000001),
	/**
	 * 禁止生命恢复
	 */
	JINZHISHENGMINGHUOFU  	(0x00000002),
	/**
	 * 禁止内力恢复
	 */
	JINZHINEILIHUIFU   		(0x00000004),
	/**
	 * 禁止体力恢复
	 */
	JINZHITILIHUIFU		 	(0x00000008),
	/**
	 * 睡眠
	 */
	SHUIMIAN				(0x00000010),
	/**
	 * 方向倒置
	 */
	FANGXIANGDAOZHI     	(0x00000020),
	/**
	 * 无敌
	 */
	WUDI				   	(0x00000040),
	/**
	 * 禁止骑乘
	 */
	JINZHIQICHENG    		(0x00000080),
	/**
	 * 跳跃不减少体力
	 */
	TIAOYUEBUJIANSHAOTILI	(0x00000100),
	/**
	 * 格挡不减少内力
	 */
	GEDANGBUJIANSHAONEILI	(0x00000200),
	/**
	 * 免费复活
	 */
	MIANFEIFUHUO			(0x00000400),
	/**
	 * 负面状态自动解除
	 */
	FUMIANZIDONGJIECHU    	(0x00000800),
	/**
	 * 禁止使用药品
	 */
	JINZHISHIYONGYAOPIN    	(0x00001000),
	/**
	 * PK保护
	 */
	PKBAOHU					(0x00002000),
	/**
	 * 攻击清零
	 */
	GONGJIWEILING			(0x00004000),
	/**
	 * 防御清零
	 */
	FANGYUWEILING			(0x00008000),
	/**
	 * 暴击清零
	 */
	BAOJIWEILING			(0x00010000),
	/**
	 * 闪避清零
	 */
	SHANBIWEILING			(0x00020000),
	/**
	 * 致盲
	 */
	ZHIMANG					(0x00040000),
	/**
	 * 反弹无视防御伤害
	 */
	FANTAN					(0x00080000),
	/**
	 * PK保护
	 */
	PKBAOHUFORNIGHT			(0x00100000),
	/**
	 * 移动减血
	 */
	DAMAGEONMOVE			(0x00200000),
	/**
	 * 玲珑
	 */
	LINGLONG				(0x00400000),
	/**
	 * 遮影
	 */
	ZHEYING					(0x00800000),
	/**
	 * 强行PK
	 */
	FORCEPK					(0x01000000),
	/**
	 * 紫芒
	 */
	FORCEDODGE				(0x02000000),
	
	/**
	 * 虚弱
	 */
	WEAK					(0x04000000),
	
	/**
	 * 骑战兵器缴械
	 */
	HORSEWEAPONUNWEAR		(0x08000000),
	
	/**
	 * 防骑战兵器缴械
	 */
	HORSEWEAPONPROTECT		(0x10000000),
	;
	
	private int value;
	
	FighterState(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public boolean compare(int state){
		return ((this.value & state)!=0);
	}
}
