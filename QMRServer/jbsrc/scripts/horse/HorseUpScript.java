package scripts.horse;

import com.game.horse.script.IHorseUpScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.utils.TimeUtil;


public class HorseUpScript implements IHorseUpScript{

	@Override
	public int getId() {
		return ScriptEnum.HORSE_UP;
	}

	
	//nextLayer  当前坐骑阶数
	
	@Override
	public void onHorseUp(Player player, byte status, int nextLayer) {
		int sid = WServer.getInstance().getServerId();
		if (sid < 3) {	//国庆活动只开放1和2区
			if (status == 1) {	//进阶成功
				guoqinghuodong(player , nextLayer);
			}
		}
	}
	
	
	/**国庆活动
	 * 
	 * @param player
	 * @param nextLayer  当前坐骑阶数
	 */
	public void guoqinghuodong(Player player,int nextLayer){
		if (nextLayer >= 6) {
			int day = TimeUtil.getDayOfMonth(System.currentTimeMillis());
			int year= TimeUtil.getYear(System.currentTimeMillis());
			int month = TimeUtil.getMonth(System.currentTimeMillis())+1;
			if (year==2012 && month == 10 && (day >= 1 && day <= 7 )) {
				player.getActivitiesReward().put("GQHORSE"+ nextLayer, "1"); 
			}	
		}
	}
}
