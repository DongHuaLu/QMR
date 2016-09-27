package com.game.buff.structs;

import com.game.fight.structs.Fighter;

public interface IBuff {

	/**
	 * 增加buff
	 * @param source 来源
	 * @param target 对象
	 * @return
	 */
	public int add(Fighter source, Fighter target);
	
	/**
	 * buff作用
	 * @param source 来源
	 * @param target 对象
	 * @return 作用数值 0-失败 1-成功 2-移除 3-涉及别的buff移除，需要终止计算
	 */
	public int action(Fighter source, Fighter target);
	
	/**
	 * 移除buff
	 * @param source 来源
	 * @return
	 */
	public int remove(Fighter source);
}
