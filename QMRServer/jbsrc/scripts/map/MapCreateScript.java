package scripts.map;

import com.game.map.script.IMapCreateScript;
import com.game.map.structs.Map;
import com.game.script.structs.ScriptEnum;

public class MapCreateScript implements IMapCreateScript {

	@Override
	public int getId() {
		return ScriptEnum.MAP_CREATE;
	}

	@Override
	public void onCreate(Map map) {
		// TODO Auto-generated method stub

	}

}
