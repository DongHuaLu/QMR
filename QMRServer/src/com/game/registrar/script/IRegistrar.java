package com.game.registrar.script;


import java.util.List;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IRegistrar extends IScript {

	//首次大奖
	public String getFirstReward(Player player); 
	//每日奖励
	public String getCommonReward(Player player);
	//发放首次奖励
	public void giveFirstReward(Player player); 
	//发放每日奖励
	public void giveCommonReward(Player player);
	//调用世界服方法
	public void callWorld(Player player);
	//世界服回调方法
	public void worldCallback(List<Object> paras);
}
