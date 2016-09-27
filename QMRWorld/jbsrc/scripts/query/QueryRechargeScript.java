package scripts.query;

import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.recharge.QueryRecharge;
import com.game.recharge.RechargeHistory;
import com.game.script.IScript;
import com.game.script.structs.ScriptEnum;
import com.game.utils.ScriptsUtils;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * 查询充值
 *
 * @author 杨鸿岚
 */
public class QueryRechargeScript implements IScript {

//	private static String Error = "-2";	//类型错误
//	private static String Fail = "-1";	//没有充值历史
	private static String Success = "0";	//有充值历史

	@Override
	public int getId() {
		return ScriptEnum.QUERYRECHAGE;
	}

	/**
	 * 查询充值
	 * @param param 参数列表
	 * @see 参数-0-游戏服务器脚本id,参数-1-回调函数名,参数-2-回调玩家id,参数-3-查询充值类型,参数-4-查询开始时间(可选),参数-5-查询结束时间(可选)
	 * @return 
	 */
	public void queryRecharge(List<Object> param) {
		QueryRecharge.queryRecharge(param); 
	}
	
	public void queryDayRecharge(List<Object> param){
		int gamescriptid = Integer.parseInt(param.get(0).toString()); 
		String methodname = param.get(1).toString();
		long playerid = Long.valueOf(param.get(2).toString());
//		int type = Integer.valueOf(param.get(3).toString());
		String define= param.get(3).toString();
		String itemid= param.get(4).toString();
		Player player = PlayerManager.getInstance().getPlayer(playerid);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0); //当天凌晨
		int result = 0;
		if(player.getRechargeHistorys() != null){
			Iterator<RechargeHistory> iterator = player.getRechargeHistorys().iterator();
			while (iterator.hasNext()) {
				RechargeHistory rechargeHistory = iterator.next();
				if (rechargeHistory != null) {
					if (rechargeHistory.getTime() >= calendar.getTimeInMillis()) {
						result += rechargeHistory.getGold();
					}
				}
			}
		}
		ScriptsUtils.callGame(player, gamescriptid, methodname, Long.toString(player.getId()), Success, Integer.toString(result),define,itemid);
	}
	
	
}
