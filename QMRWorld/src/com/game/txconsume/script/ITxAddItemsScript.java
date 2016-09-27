package com.game.txconsume.script;

import com.game.script.IScript;

public interface ITxAddItemsScript extends IScript {

	//请求发放道具
	public String reqAddItems(String requeststr);
	
}