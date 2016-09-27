package com.game.monster.manager;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.monster.handler.ReqMonsterDoubleNoticeHandler;
import com.game.monster.message.ReqMonsterDoubleNoticeMessage;
import com.game.monster.message.ReqMonsterDoubleTimeToGameMessage;
import com.game.monster.message.ResMonsterDoubleNoticeMessage;
import com.game.monster.message.ResMonsterSyncMessage;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.server.config.ServerType;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;


public class MonsterManager {

	//关注怪物列表 一层key为怪物模板ID 2层key为服务器ID 3层key为怪物ID
	private ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, ConcurrentHashMap<Long, Monster>>> bosses = new ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, ConcurrentHashMap<Long, Monster>>>();
	Logger log = Logger.getLogger(MonsterManager.class);
	private static Object obj = new Object();
	//玩家管理类实例
	private static MonsterManager manager;
	
	private MonsterManager(){}
	
	public static MonsterManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new MonsterManager();
			}
		}
		return manager;
	}
	
	private byte DaguaiDoubleStatus;
	private String DaguaiDoubleTime;	//前端使用，展示时间
	
	public void syncMonster(long monsterId, int serverId,int lineId,int mapmodelId, int modelId, int hp, int maxHp, int state, String killer, long revive, short x, short y){
		Monster monster = getMonster(monsterId, serverId, modelId);
		if(monster==null){
			monster = new Monster();
			monster.setId(monsterId);
			monster.setServerId(serverId);
			monster.setLineId(lineId);
			monster.setModelId(modelId);
			monster.setMapModelId(mapmodelId);
			monster.setBirthX(x);
			monster.setBirthY(y);
		}
		
		monster.setHp(hp);
		monster.setMaxHp(maxHp);
		monster.setState(state);
		if(killer!=null) monster.setKiller(killer);
		if(revive!=0) monster.setReviveTime(revive);
		
		addMonster(monster);
		
		ResMonsterSyncMessage msg = new ResMonsterSyncMessage();
		msg.setMonsterId(monsterId);
		msg.setServerId(serverId);
		msg.setLineId(lineId);
		msg.setModelId(modelId);
		msg.setCurrentHp(hp);
		msg.setMaxHp(maxHp);
		msg.setState(state);
		msg.setKiller(killer);
		msg.setRevive(revive);
		
		MessageUtil.send_to_game(msg);
	}
	
	public ConcurrentHashMap<Long, Monster> getMonsters(int serverId, int modelId){
		if(bosses.containsKey(modelId)){
			ConcurrentHashMap<Integer, ConcurrentHashMap<Long, Monster>> monsters = bosses.get(modelId);
			ConcurrentHashMap<Long, Monster> allMonsters = new ConcurrentHashMap<Long, Monster>();
			if(monsters.containsKey(serverId)){
				allMonsters.putAll(monsters.get(serverId));
			}
			if(monsters.containsKey(ServerType.PUBLIC.getValue())){
				allMonsters.putAll(monsters.get(ServerType.PUBLIC.getValue()));
			}
			return allMonsters;
		}
		return null;
	}
	
	private Monster getMonster(long monsterId, int serverId, int modelId){
		if(bosses.containsKey(modelId)){
			ConcurrentHashMap<Integer, ConcurrentHashMap<Long, Monster>> monsters = bosses.get(modelId);
			if(monsters.containsKey(serverId)){
				ConcurrentHashMap<Long, Monster> mon = monsters.get(serverId);
				return mon.get(monsterId);
			}
		}
		return null;
	}
	
	private void addMonster(Monster monster){
		ConcurrentHashMap<Integer, ConcurrentHashMap<Long, Monster>> monsters = null;
		if(bosses.containsKey(monster.getModelId())){
			monsters = bosses.get(monster.getModelId());
		}else{
			monsters = new ConcurrentHashMap<Integer, ConcurrentHashMap<Long, Monster>>();
			bosses.put(monster.getModelId(), monsters);
		}
		
		ConcurrentHashMap<Long, Monster> mon = null;
		if(monsters.containsKey(monster.getServerId())){
			mon = monsters.get(monster.getServerId());
		}else{
			mon = new ConcurrentHashMap<Long, Monster>();
			monsters.put(monster.getServerId(), mon);
		}
		
		mon.put(monster.getId(), monster);
	}
	
	
	public byte getDaguaiDoubleStatus() {
		return DaguaiDoubleStatus;
	}

	public void setDaguaiDoubleStatus(byte daguaiDoubleStatus) {
		DaguaiDoubleStatus = daguaiDoubleStatus;
	}
	
	public String getDaguaiDoubleTime() {
		return DaguaiDoubleTime;
	}

	public void setDaguaiDoubleTime(String daguaiDoubleTime) {
		DaguaiDoubleTime = daguaiDoubleTime;
	}
	
	
	/**通知所有玩家双倍开启或者关闭
	 * 
	 * @param msg
	 */
	public void stReqMonsterDoubleNoticeMessage(ReqMonsterDoubleNoticeMessage msg) {
		if(msg.getType() == 1){
			if ( (getDaguaiDoubleTime() == null && msg.getContent()!= null) || (msg.getContent() != null && !getDaguaiDoubleTime().equals(msg.getContent())) ){
				setDaguaiDoubleTime(msg.getContent());
				long time = TimeUtil.getRangeTimeBeforeOrAfter(msg.getContent(),false,false);
				int ms = (int)(time /1000);
				setDaguaiDoubleStatus(msg.getStatus());
				ResMonsterDoubleNoticeMessage cmsg = new ResMonsterDoubleNoticeMessage();
				cmsg.setStatus(msg.getStatus());
				cmsg.setType(msg.getType());
				cmsg.setContent(msg.getContent());
				cmsg.setTime(ms);
				MessageUtil.tell_world_message(cmsg);
			}else {
				if (msg.getStatus() == 0) {
					setDaguaiDoubleStatus(msg.getStatus());
					setDaguaiDoubleTime(null);	//清理双倍时间
					ResMonsterDoubleNoticeMessage cmsg = new ResMonsterDoubleNoticeMessage();
					cmsg.setStatus(msg.getStatus());
					cmsg.setType(msg.getType());
					cmsg.setContent("");
					MessageUtil.tell_world_message(cmsg);
				}
			}
		}
	}

	/**登录通知玩家双倍开放
	 * 
	 * @param player
	 */
	public void  LoginMonsterDoubleNotice(Player player){
		if (getDaguaiDoubleStatus()== 1) {
			ResMonsterDoubleNoticeMessage cmsg = new ResMonsterDoubleNoticeMessage();
			cmsg.setStatus((byte) 1);
			cmsg.setType((byte) 1);
			cmsg.setContent(getDaguaiDoubleTime());
			
			long time = TimeUtil.getRangeTimeBeforeOrAfter(getDaguaiDoubleTime(),false,false);
			int ms = (int)(time /1000);
			cmsg.setTime(ms);
			MessageUtil.tell_player_message(player, cmsg);
		}
	}
	
	
	
	/**自定义服务器双倍时间
	 * 
	 * @param dateString
	 * @param serverid
	 */
	public void setsysDouble(String dateString ,int serverid){
		ReqMonsterDoubleTimeToGameMessage smsg = new ReqMonsterDoubleTimeToGameMessage();
		if (dateString != null && !dateString.equals("")) {
			smsg.setContent(dateString);
			smsg.setSendId(serverid);
			MessageUtil.send_to_game(smsg);
			log.error("系统自定义双倍设置为："+dateString);
		}else {
			smsg.setContent("");
			smsg.setSendId(serverid);
			MessageUtil.send_to_game(smsg);
		}
	}
	

}
