package com.game.bugtrace.manager;

import org.apache.log4j.Logger;

import com.game.bugtrace.log.BugTraceLog;
import com.game.dblog.LogService;
import com.game.player.structs.Player;
import com.game.server.impl.WServer;

public class BugTraceManager {
	
	private static Logger log = Logger.getLogger(BugTraceLog.class);
	
	private static BugTraceManager me = null;
	public static synchronized BugTraceManager getInstance() {
		if (me == null) {
			me = new BugTraceManager();
		}
		return me;
	}
	private BugTraceManager() {
		
	}
	
	/**
	 * notice: problem的长度限制100，detail限制2000
	 * @param role
	 * @param problem
	 * @param detail
	 */
	public void trace(long role, String problem, String detail) {
		try {
			if (WServer.getInstance().getServerWeb().length() < 20 && problem.length() < 100 && detail.length() < 2000) {
				BugTraceLog log = new BugTraceLog();
				
				log.setAgent(WServer.getInstance().getServerWeb());
				log.setZone(WServer.getInstance().getServerId());
				log.setRole(role);
				log.setProblem(problem);
				log.setDetail(detail);
				
				LogService.getInstance().execute(log);
			}else{
				log.error("trace fail role:"+role+"\tproblem:"+problem+"\tdetail:"+detail);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
	/**
	 * notice: problem的长度限制100，detail限制2000
	 * @param role
	 * @param rolename
	 * @param problem
	 * @param detail
	 */
	public void trace(long role, String rolename, String problem, String detail) {
		try {
			if (WServer.getInstance().getServerWeb().length() < 20 && problem.length() < 100 && detail.length() < 2000) {
				BugTraceLog log = new BugTraceLog();
				
				log.setAgent(WServer.getInstance().getServerWeb());
				log.setZone(WServer.getInstance().getServerId());
				log.setRole(role);
				log.setRolename(rolename);
				log.setProblem(problem);
				log.setDetail(detail);
				
				LogService.getInstance().execute(log);
			}else{
				log.error("trace fail role:"+role+"\tproblem:"+problem+"\tdetail:"+detail);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	/**
	 * notice: problem的长度限制100，detail限制2000
	 * @param player
	 * @param problem
	 * @param detail
	 */
	public void trace(Player player, String problem, String detail) {
		try {
			if(player==null){
				log.error("trace fail player is null "+problem+"\t"+detail);
				return;
			}
			if (WServer.getInstance().getServerWeb().length() < 20 && problem.length() < 100 && detail.length() < 2000) {
				BugTraceLog log = new BugTraceLog();
				
				log.setAgent(WServer.getInstance().getServerWeb());
				log.setZone(WServer.getInstance().getServerId());
				log.setUsername(player.getUserName());
				log.setUserid(player.getUserId());
				log.setRole(player.getId());
				log.setRolename(player.getName());
				log.setProblem(problem);
				log.setDetail(detail);
				
				LogService.getInstance().execute(log);
			}else{
				log.error("trace fail username:"+player.getUserName()+" name:"+player.getName()+"\trole:"+player.getId()+"\tproblem:"+problem+"\tdetail:"+detail);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
	}
}
