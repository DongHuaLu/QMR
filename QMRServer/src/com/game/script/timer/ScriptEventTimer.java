package com.game.script.timer;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.script.IScript;
import com.game.timer.TimerEvent;

public class ScriptEventTimer extends TimerEvent {
	
	private Logger log = Logger.getLogger(ScriptEventTimer.class);
	//脚本Id
	private int scriptId;
	//方法名
	private String method;
	//副本参数
	private List<Object> parameters;
		
	public ScriptEventTimer(int scriptId, String method, List<Object> parameters, long delay) {
		super(1, delay);
		this.scriptId = scriptId;
		this.method = method;
		this.parameters = parameters;
	}
	
	@Override
	public void action() {
		IScript script = (IScript) ManagerPool.scriptManager.getScript(scriptId);
		if (script != null) {
			try {
				Method fun = script.getClass().getDeclaredMethod(method, List.class);
				fun.invoke(script, parameters);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("脚本" + scriptId + "不存在！");
		}
	}
}
