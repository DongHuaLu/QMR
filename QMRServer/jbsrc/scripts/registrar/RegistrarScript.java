package scripts.registrar;

import java.util.List;

import com.game.player.structs.Player;
import com.game.registrar.script.IRegistrar;


public class RegistrarScript implements IRegistrar {

	@Override
	public int getId() {
		return 1200;
	}

	@Override
	public String getFirstReward(Player player) {  //首次登陆大奖  礼金500 双倍收益丹2 装备强化石10
		return "[{\"id\":\"-5\",\"num\":\"500\"},{\"id\":\"1015\",\"num\":\"2\"},{\"id\":\"1001\",\"num\":\"10\"}]";
	}

	@Override
	public String getCommonReward(Player player) { //普通登陆奖励 50礼金 
		return "[{\"id\":\"-5\",\"num\":\"50\"}]";  
	}
	
	@Override
	public void giveFirstReward(Player player) {
		
	}

	@Override
	public void giveCommonReward(Player player) {

	}

	@Override
	public void callWorld(Player player) {
		
	}

	@Override
	public void worldCallback(List<Object> paras) {
		
	}

}
