package scripts.map;

import java.util.HashMap;

import com.game.map.script.IChangeMapScript;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.zones.structs.Raid;

public class ChangeMapScript implements IChangeMapScript {

	@Override
	public int getId() {
		return ScriptEnum.CHANGE_MAP;
	}

	@Override
	public void onChangeMap(Player player) {
		//修正部分人自动扫荡再开连续扫荡得不到固定奖励的 BUG，，等19号维护后，可删除
		Raid raid = player.getRaidinfo();
		HashMap<String, Integer> rewmap = player.getZonerewardmap();
		if (raid.getRaidzonemodelid() == 0) {
			for (String strzid : rewmap.keySet()) {
				if((int)rewmap.get(strzid) > 0){
					int zid= Integer.valueOf(strzid);
					raid.setRaidzonemodelid(zid);
					break;
				}
			}
		}
	}
}
