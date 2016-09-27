package scripts.zone;

import com.game.manager.ManagerPool;
import com.game.script.structs.ScriptEnum;
import com.game.zones.script.IZoneEventTimerScript;
import java.util.List;
import org.apache.log4j.Logger;

public class ZoneEventTimerScript implements IZoneEventTimerScript {

	private Logger log = Logger.getLogger(CreateZoneScript.class);
	public static int jiaochang_scriptId = 4003;		//校场副本scriptid
	public static int MeiHuaXuanWuSriptId = 4011;		//梅花玄武阵副本scriptid
	
	@Override
	public int getId() {
		return ScriptEnum.ZONE_EVENT;
	}

	@Override
	public void action(long zoneId, int zoneModelId, List<Object> parameters) {
		IZoneEventTimerScript script = (IZoneEventTimerScript) ManagerPool.scriptManager.getScript(jiaochang_scriptId);
		if (script != null) {
			try {
				script.action(zoneId, zoneModelId, parameters);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		script = (IZoneEventTimerScript) ManagerPool.scriptManager.getScript(MeiHuaXuanWuSriptId);
		if (script != null) {
			try {
				script.action(zoneId, zoneModelId, parameters);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
	}

}
