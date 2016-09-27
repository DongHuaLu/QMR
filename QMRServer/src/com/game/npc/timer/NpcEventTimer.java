package com.game.npc.timer;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.npc.script.INpcEventTimerScript;
import com.game.npc.struts.NPC;
import com.game.timer.TimerEvent;

public class NpcEventTimer extends TimerEvent {
	
	private Logger log = Logger.getLogger(NpcEventTimer.class);
	//副本Id
	private NPC npc;
	//脚本Id
	private int scriptId;
	//副本参数
	private List<Object> parameters;
		
	public NpcEventTimer(NPC npc, int scriptId, List<Object> parameters, long delay) {
		super(1, delay);
		this.npc = npc;
		this.scriptId = scriptId;
		this.parameters = parameters;
	}
	
	@Override
	public void action() {
		INpcEventTimerScript script = (INpcEventTimerScript) ManagerPool.scriptManager.getScript(scriptId);
		if (script != null) {
			try {
				script.action(npc, parameters);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("NPC延时事件" + scriptId + "脚本不存在！");
		}
	}
}
