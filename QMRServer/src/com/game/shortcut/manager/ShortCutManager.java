package com.game.shortcut.manager;

import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.shortcut.bean.ShortCutInfo;
import com.game.shortcut.message.ShortCutAddMessage;
import com.game.shortcut.message.ShortCutInfosMessage;
import com.game.shortcut.message.ShortCutRemoveMessage;
import com.game.shortcut.structs.ShortCut;
import com.game.skill.structs.Skill;
import com.game.utils.MessageUtil;

public class ShortCutManager {

	//private Logger log = Logger.getLogger(BackpackManager.class);
	
	private static Object obj = new Object();

	//玩家管理类实例
	private static ShortCutManager manager;
	
	private ShortCutManager(){}
	
	public static ShortCutManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new ShortCutManager();
			}
		}
		return manager;
	}
	
	/**
	 * 发送玩家全部快捷信息
	 * @param roleId 玩家id
	 */
	public void sendShortcutInfos(Player player){
		//获得玩家
		ShortCutInfosMessage msg = new ShortCutInfosMessage();
		for (int i = 0; i < player.getShortCuts().length; i++) {
			ShortCut shortcut = player.getShortCuts()[i];
			if(shortcut==null) continue;
			msg.getShortcuts().add(getShortCutInfo(shortcut, i + 1));
		}
		
		MessageUtil.tell_player_message(player, msg);
	}
	
	/**
	 * 增加快捷信息
	 * @param roleId 玩家Id
	 * @param shortcutType 快捷类型 1-物品 2-技能
	 * @param shortcutSource 指向目标
	 * @param shortcutSourceModel 指向模板
	 * @param shortcutGrid 放置格子
	 */
	public void addShortCut(Player player, int shortcutType, long shortcutSource, int shortcutSourceModel, int shortcutGrid){
		//前台发送格子位置从1开始
		shortcutGrid -= 1;
		//检查位置
		if(shortcutGrid >= player.getShortCuts().length || shortcutGrid < 0) return;
		
		//检查是否可以放置
		if(shortcutType==1){
			//物品检查
			Q_itemBean item = ManagerPool.dataManager.q_itemContainer.getMap().get(shortcutSourceModel);
			if(item==null) return;
			if(item.getQ_shortcut()!=1){
				return;
			}
		}else if(shortcutType==2){
			Skill skill = ManagerPool.skillManager.getSkillByModelId(player, shortcutSourceModel);
			if(skill==null) return;
			//技能检查
			
//			Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(SkillManager.skillKey(player, skill.getSkillModelId()));
			Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getRealLevel(player));
			if(skillModel==null) return;
			if(skillModel.getQ_trigger_type()!=1){
				return;
			}
			if(skillModel.getQ_shortcut()!=1){
				return;
			}
		}else{
			return;
		}
				
		ShortCut shortcut = new ShortCut();
		shortcut.setId(Config.getId());
		shortcut.setShortcutType(shortcutType);
		shortcut.setShortcutSource(shortcutSource);
		shortcut.setShortcutSourceModel(shortcutSourceModel);
		
		player.getShortCuts()[shortcutGrid] = shortcut;
		
		ManagerPool.playerManager.savePlayer(player);
		
		MessageUtil.tell_player_message(player, getShortCutAddMessage(shortcut, shortcutGrid + 1));

	}
	
	/**
	 * 移除快捷
	 * @param roleId 玩家Id
	 * @param shortcutId 快捷Id
	 */
	public void removeShortCut(Player player, long shortcutId){
		//获得快捷
		int grid = getShortCutGrid(player, shortcutId);
		if(grid==-1) return;
		
		player.getShortCuts()[grid] = null;
		
		ManagerPool.playerManager.savePlayer(player);
		
		MessageUtil.tell_player_message(player, getShortCutRemoveMessage(grid + 1));
	}
	
	/**
	 * 移动快捷
	 * @param roleId 玩家Id
	 * @param shortcutId 快捷Id
	 * @param toGrid 放置格子
	 */
	public void moveShortCut(Player player, long shortcutId, int toGrid){
		//前台发送格子位置从1开始
		toGrid -= 1;
		//获得快捷
		int grid = getShortCutGrid(player, shortcutId);
		if(grid==-1) return;
		
		if(toGrid >= player.getShortCuts().length || toGrid < 0) return;
		
		ShortCut toShortCut = player.getShortCuts()[toGrid];
		if(toShortCut==null){
			//移动快捷
			player.getShortCuts()[toGrid] = player.getShortCuts()[grid];
			player.getShortCuts()[grid] = null;
			
			MessageUtil.tell_player_message(player, getShortCutAddMessage(player.getShortCuts()[toGrid], toGrid + 1));
			MessageUtil.tell_player_message(player, getShortCutRemoveMessage(grid + 1));
		}else{
			//交换快捷
			player.getShortCuts()[toGrid] = player.getShortCuts()[grid];
			player.getShortCuts()[grid] = toShortCut;
			MessageUtil.tell_player_message(player, getShortCutAddMessage(player.getShortCuts()[toGrid], toGrid + 1));
			MessageUtil.tell_player_message(player, getShortCutAddMessage(player.getShortCuts()[grid], grid + 1));
		}
	}
	
	/**
	 * 按Id获得快捷信息
	 * @param roleId 玩家Id
	 * @param shortcutId 快捷Id
	 * @return
	 */
	public ShortCut getShortCut(Player player, long shortcutId){
		for (int i = 0; i < player.getShortCuts().length; i++) {
			ShortCut shortcut = player.getShortCuts()[i];
			if(shortcut==null) continue;
			if(shortcut.getId() == shortcutId) return shortcut;
		}
		
		return null;
	}
	
	/**
	 * 按Id获得快捷所在格子
	 * @param roleId 玩家Id
	 * @param shortcutId 快捷Id
	 * @return
	 */
	public int getShortCutGrid(Player player, long shortcutId){
		for (int i = 0; i < player.getShortCuts().length; i++) {
			ShortCut shortcut = player.getShortCuts()[i];
			if(shortcut==null) continue;
			if(shortcut.getId() == shortcutId) return i;
		}
		
		return -1;
	}
	
	/**
	 * 获取快捷增加消息
	 * @param shortcut 快捷
	 * @param grid 所在格子
	 * @return
	 */
	public ShortCutAddMessage getShortCutAddMessage(ShortCut shortcut, int grid){
		ShortCutAddMessage msg = new ShortCutAddMessage();
		msg.setShortcut(getShortCutInfo(shortcut, grid));
		return msg;
	}
	
	/**
	 * 获取快捷移除消息
	 * @param shortcut 快捷
	 * @return
	 */
	public ShortCutRemoveMessage getShortCutRemoveMessage(int grid){
		ShortCutRemoveMessage msg = new ShortCutRemoveMessage();
		msg.setShortcutGrid(grid);
		return msg;
	}
	
	/**
	 * 获取快捷信息
	 * @param shortcut 快捷
	 * @param grid 所在格子
	 * @return
	 */
	private ShortCutInfo getShortCutInfo(ShortCut shortcut, int grid){
		ShortCutInfo info = new ShortCutInfo();
		info.setShortcutId(shortcut.getId());
		info.setShortcutType(shortcut.getShortcutType());
		info.setShortcutSource(shortcut.getShortcutSource());
		info.setShortcutSourceModel(shortcut.getShortcutSourceModel());
		info.setShortcutGrid(grid);
		return info;
	}
}
