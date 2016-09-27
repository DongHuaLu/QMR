package scripts.zone;

import com.game.manager.ManagerPool;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.MServer;
import com.game.zones.script.IZoneDeleteScript;
import com.game.zones.structs.ZoneContext;
import org.apache.log4j.Logger;

public class ZoneDeleteScript implements IZoneDeleteScript {
	
	private Logger log = Logger.getLogger(ZoneDeleteScript.class);
	public static int jiaochang_scriptId = 4003;		//校场副本scriptid
	public static int mizong_scriptId = 4006;		//迷踪幻境副本scriptid
	public static int maze_scriptId = 4007;		//迷宫scriptid
	@Override
	public int getId() {
		return ScriptEnum.ZONE_DELETE;
	}

	@Override
	public void onDelete(MServer mServer, ZoneContext context) {
		IZoneDeleteScript script = (IZoneDeleteScript) ManagerPool.scriptManager.getScript(jiaochang_scriptId);
		if (script != null) {
			try {
				script.onDelete(mServer, context);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
	}

}
