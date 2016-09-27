package scripts.zone;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.structs.ZoneContext;
import org.apache.log4j.Logger;

public class CreateZoneScript implements ICreateZoneScript {
	
	private Logger log = Logger.getLogger(CreateZoneScript.class);
	public static int jiaochang_scriptId = 4003;		//校场副本scriptid
	public static int mizong_scriptId = 4006;			//迷踪幻境副本scriptid
	public static int maze_scriptId = 4007;				//迷宫scriptid
	public static int baguazheng_scriptId = 4008;				//八卦阵
	public static int SheZhanQunRusSriptId = 4009;		//舌战群儒副本scriptid
	public static int simen_scriptId = 4010;				//四门阵
	public static int MeiHuaXuanWuSriptId = 4011;		//梅花玄武阵副本scriptid
	public static int[] bow_scriptId = new int[]{4101, 4102, 4103, 4104, 4105, 4106, 4107};				//弓箭boss副本
	public static int shuiyandaliangId = 4012;				//水淹大梁
	
	
	
	@Override
	public int getId() {
		return ScriptEnum.ZONE_CREATE;
	}

	@Override
	public ZoneContext onCreate(Player player, int zoneId) {
		ZoneContext context = null;
		ICreateZoneScript script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(jiaochang_scriptId);
		if (script != null) {
			try {
				context = script.onCreate(player, zoneId);
				if(context!=null) return context;
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(mizong_scriptId);
		if (script != null) {
			try {
				context = script.onCreate(player, zoneId);
				if(context!=null) return context;
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(maze_scriptId);
		if (script != null) {
			try {
				context = script.onCreate(player, zoneId);
				if(context!=null) return context;
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		
		script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(baguazheng_scriptId);
		if (script != null) {
			try {
				context = script.onCreate(player, zoneId);
				if(context!=null) return context;
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(SheZhanQunRusSriptId);
		if (script != null) {
			try {
				context = script.onCreate(player, zoneId);
				if(context!=null) return context;
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(simen_scriptId);
		if (script != null) {
			try {
				context = script.onCreate(player, zoneId);
				if(context!=null) return context;
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(MeiHuaXuanWuSriptId);
		if (script != null) {
			try {
				context = script.onCreate(player, zoneId);
				if(context!=null) return context;
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		for (int i = 0; i < bow_scriptId.length; i++) {
			script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(bow_scriptId[i]);
			if (script != null) {
				try {
					context = script.onCreate(player, zoneId);
					if(context!=null) return context;
				} catch (Exception e) {
					log.error(e, e);
				}
			}
		}
		
		script = (ICreateZoneScript) ManagerPool.scriptManager.getScript(shuiyandaliangId);
		if (script != null) {
			try {
				context = script.onCreate(player, zoneId);
				if(context!=null) return context;
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		
		
		return context;
	}

}
