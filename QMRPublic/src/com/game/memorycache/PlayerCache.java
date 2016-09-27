package com.game.memorycache;

import java.util.concurrent.ConcurrentHashMap;

import com.game.cache.impl.MemoryCache;
import com.game.memorycache.structs.PlayerInfo;

/**
 * 玩家数据缓存
 * @author heyang
 *
 */
public class PlayerCache {

	private MemoryCache<Long, PlayerInfo> players = new MemoryCache<Long, PlayerInfo>(50000, 0);
	
	private ConcurrentHashMap<String, Long> ids = new ConcurrentHashMap<String, Long>();
	
	/**
	 * 获取玩家信息
	 * @param dataServerPlayerId
	 * @return
	 */
	public PlayerInfo getPlayer(long dataServerPlayerId){
		return players.get(dataServerPlayerId);
	}
	
	/**
	 * 获取玩家信息
	 * @param web
	 * @param playerId
	 * @return
	 */
	public PlayerInfo getPlayer(String web, long playerId){
		Long dataServerPlayerId = ids.get(web + "_" + playerId);
		if(dataServerPlayerId!=null){
			return players.get(dataServerPlayerId.longValue());
		}
		return null;
	}
	
	/**
	 * 增加玩家信息
	 * @param player
	 */
	public void putPlayer(PlayerInfo player){
		players.put(player.getDataServerPlayerId(), player);
		ids.put(player.getWeb() + "_" + player.getPlayerId(), player.getDataServerPlayerId());
	}
}
