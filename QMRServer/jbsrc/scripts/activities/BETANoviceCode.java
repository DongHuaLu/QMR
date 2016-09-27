package scripts.activities;

import com.game.activities.script.IActivityScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

public class BETANoviceCode implements IActivityScript {
	
	//自动补血丹*1、自动聚气丹*1、身形药剂*1、回城卷*5 
	private int giftid=8061;
	private int giftnum=1;
	
	private int itemid1=30301;
	private int itemnum1=1;
	private int itemid2=30302;
	private int itemnum2=1;
	private int itemid3=30701;
	private int itemnum3=1;
	private int itemid4=9001;
	private int itemnum4=5;
	
	@Override
	public int getId() {
		return ScriptEnum.BETA_NOVICECODE;
	}

	@Override
	public void getReward(Player player) {
		
	}

	@Override
	public String getDescribe(Player player) {
		return "";
	}

	@Override
	public String getRewardInfo(Player player) {
		return "[{\"id\":"+itemid1+", \"num\":" + itemnum1 + "}" +
				",{\"id\":"+itemid2+", \"num\":" + itemnum2 + "}" +
				",{\"id\":"+itemid3+", \"num\":" + itemnum3 + "}"+
				",{\"id\":"+itemid4+", \"num\":" + itemnum4 + "}]";
	}

	@Override
	public int getState(Player player) {
		return 1;
	}

	@Override
	public boolean isOpen(Player player) {
		return true;
	}

	@Override
	public int getDuration(Player player){
		
		return 0;
	}
}
