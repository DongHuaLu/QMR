package scripts.server;

import org.apache.log4j.Logger;

import com.game.script.structs.ScriptEnum;
import com.game.server.script.IMidNightScript;
import com.game.utils.ScriptsUtils;

public class MidNightScript implements IMidNightScript {

	protected Logger log = Logger.getLogger(MidNightScript.class);

	@Override
	public int getId() {
		return ScriptEnum.MID_NIGHT;
	}

	@Override
	public void onMidNight() {	
		//基础活动脚本定时调用
		ScriptsUtils.call(ScriptEnum.BASEACTIVITIES, "delayCallEveryDay0Clock");
	}

}
