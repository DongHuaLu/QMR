package com.game.map.script;

import com.game.map.structs.Map;
import com.game.script.IScript;

public interface IMapCreateScript extends IScript {

	public void onCreate(Map map);
}
