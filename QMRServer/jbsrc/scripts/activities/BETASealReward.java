package scripts.activities;

import com.game.activities.script.IActivityScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

//封测活动领奖
public class BETASealReward implements IActivityScript {

	private static String KEY="BETASEALREWARD";
	
	@Override
	public int getId() {
		return ScriptEnum.BETA_SEALREWARD;
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
		return "[{\"id\":-5, \"num\":{num}}]";
	}

	@Override
	public int getState(Player player) {
		return 3;
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
