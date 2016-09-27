package com.game.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.script.timer.ScriptEventTimer;
import com.game.scripts.message.ReqScriptToWorldMessage;

public class ScriptsUtils {

	private static Logger log = Logger.getLogger(ScriptsUtils.class);
	
	/**
	 * 调用方法
	 * @param parent
	 * @param method
	 * @param delay
	 * @param paras
	 */
	public static void call(int scriptId, String method, Object... paras){
		ArrayList<Object> parameters = new ArrayList<Object>();
		for (int i = 0; i < paras.length; i++) {
			parameters.add(paras[i]);
		}
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
	
	/**
	 * 调用方法
	 * @param parent
	 * @param method
	 * @param delay
	 * @param paras
	 */
	public static void callFromWorld(int scriptId, String method, List<String> paras){
		ArrayList<Object> parameters = new ArrayList<Object>();
		for (int i = 0; i < paras.size(); i++) {
			parameters.add(paras.get(i));
		}
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
	
	/**
	 * 调用方法
	 * @param parent
	 * @param method
	 * @param delay
	 * @param paras
	 */
	public static void call(int scriptId, String method, Player player, List<String> paras){
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(player);
		for (int i = 0; i < paras.size(); i++) {
			parameters.add(paras.get(i));
		}
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
	
	/**
	 * 延迟调用方法
	 * @param parent
	 * @param method
	 * @param delay
	 * @param paras
	 */
	public static void delayCall(int scriptId, String method, long delay, Object... paras){
		ArrayList<Object> parameters = new ArrayList<Object>();
		for (int i = 0; i < paras.length; i++) {
			parameters.add(paras[i]);
		}
		ScriptEventTimer timer = new ScriptEventTimer(scriptId, method, parameters, delay);
		TimerEventUtil.addTimerEvent(timer);
	}
	
	/**
	 * 调用世界服务器脚本方法
	 * @param scriptId
	 * @param method
	 * @param paras
	 */
	public static void callWorld(int scriptId, String method, String... paras){
		ReqScriptToWorldMessage msg = new ReqScriptToWorldMessage();
		msg.setScriptId(scriptId);
		msg.setMethod(method);
		for (int i = 0; i < paras.length; i++) {
			msg.getParas().add(paras[i]);
		}
		MessageUtil.send_to_world(msg);
	}
	
//	/**
//	 * 调用游戏服务器脚本方法
//	 * @param player
//	 * @param scriptId
//	 * @param method
//	 * @param paras
//	 */
//	public void callGame(Player player, int scriptId, String method, String... paras){
//		
//	}
}
