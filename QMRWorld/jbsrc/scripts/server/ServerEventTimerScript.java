package scripts.server;

import com.game.script.structs.ScriptEnum;
import com.game.server.script.IServerEventTimerScript;
import com.game.utils.ScriptsUtils;
import org.apache.log4j.Logger;

public class ServerEventTimerScript implements IServerEventTimerScript {

	protected Logger log = Logger.getLogger(ServerEventTimerScript.class);

	@Override
	public int getId() {
		return ScriptEnum.SERVER_EVENT;
	}

	
	@Override
	public void action() {
		//基础活动脚本定时调用
		ScriptsUtils.call(ScriptEnum.BASEACTIVITIES, "delayCallEveryDay0Clock");

	}

}
