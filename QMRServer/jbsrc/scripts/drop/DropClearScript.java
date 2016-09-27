package scripts.drop;

import com.game.drop.script.IDropClearScript;
import com.game.drop.structs.MapDropInfo;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import org.apache.log4j.Logger;

/**
 * 掉落物品消失事件
 *
 * @author 杨鸿岚
 */
public class DropClearScript implements IDropClearScript {

	private Logger log = Logger.getLogger(DropClearScript.class);
	public static int jiaochang_scriptId = 4003;		//校场副本scriptid

	@Override
	public int getId() {
		return ScriptEnum.DROP_CLEAR;
	}

	@Override
	public boolean dropClear(MapDropInfo mapDropInfo) {
		IDropClearScript script = (IDropClearScript) ScriptManager.getInstance().getScript(jiaochang_scriptId);
		if (script != null) {
			try {
				return script.dropClear(mapDropInfo);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		return false;
	}
}
