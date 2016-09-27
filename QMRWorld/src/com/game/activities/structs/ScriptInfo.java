package com.game.activities.structs;

/**
 * 活动信息
 *
 * @author 杨鸿岚
 */
public class ScriptInfo {

	private int type;	//活动类型
	private int id;     //活动id
	private int script; //脚本编号

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScript() {
		return script;
	}

	public void setScript(int script) {
		this.script = script;
	}
}
