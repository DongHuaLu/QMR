package com.game.npc.struts;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.structs.IMapObject;
import com.game.npc.script.INpcCanSeeScript;
import com.game.player.structs.Person;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;

/**
 * 
 * @author 赵聪慧
 * @2012-8-23 下午5:02:17
 */
public class NPC extends Person implements IMapObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605542454890837015L;
	
	protected Logger log = Logger.getLogger(NPC.class);
	
	private int serverId;
	
	private boolean show = true;
	
	//临时参数列表
	private HashMap<String, Object> parameters = new HashMap<String, Object>();
	

	
	
	
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	
	@Override
	public int getServerId() {
		return serverId;
	}
	@Override
	public boolean canSee(Player player) {
		INpcCanSeeScript script = (INpcCanSeeScript)ManagerPool.scriptManager.getScript(ScriptEnum.NPC_SEE);
		if(script!=null){
			try{
				return script.cansee(player, this);
			}catch (Exception e) {
				log.error(e, e);
			}
		}else{
			log.error("npc是否可见脚本不存在！");
		}
		return true;
	}
	public HashMap<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}

}
