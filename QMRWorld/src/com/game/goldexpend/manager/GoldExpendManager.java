package com.game.goldexpend.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.config.Config;
import com.game.db.bean.GoldExpend;
import com.game.db.dao.GoldExpendDao;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.server.WorldServer;

public class GoldExpendManager {
	private static GoldExpendManager me;
	private GoldExpendManager() {
	}
	public static synchronized GoldExpendManager getInstance() {
		if (me == null) {
			me = new GoldExpendManager();
		}
		return me;
	}
	private Logger log = Logger.getLogger(GoldExpendManager.class);
	private GoldExpendDao dao = new GoldExpendDao();
	
	public int getExpendGold(Player player, String startYYYYMMDDHHMM, String endYYYYMMDDHHMM) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
			long startTime = sdf.parse(startYYYYMMDDHHMM).getTime();
			long endTime = sdf.parse(endYYYYMMDDHHMM).getTime();
			
			if (!checkValidTime(player, startTime, endTime)) {
				load(player, startTime);
			}
			
			List<GoldExpend> hostorys = player.getGoldExpendHistorys();
			if (hostorys == null) {
				return 0;
			}
			
			int expend = 0;
			Iterator<GoldExpend> it = hostorys.iterator();
			while (it.hasNext()) {
				GoldExpend goldExpend = it.next();
				if (goldExpend == null) continue;
				if (goldExpend.getOptTime() < startTime) continue;
				if (goldExpend.getOptTime() > endTime) continue;
				expend += goldExpend.getGold();
			}
			return expend;
		} catch(Exception e) {
			log.error(e, e);
			return 0;
		}
	}
	
	/**
	 * 加载从starttime到当前时间范围内，指定角色的元宝消耗记录
	 * @param player
	 * @param startTime
	 */
	public void load(Player player, long startTime) {
		List<GoldExpend> hostorys = new ArrayList<GoldExpend>();
		// 这里加个这个来做判断的
		GoldExpend startExpend = new GoldExpend();
		startExpend.setOptTime(startTime);
		hostorys.add(startExpend);

		List<GoldExpend> tmp = dao.select(player.getId(), startTime);
		hostorys.addAll(tmp);
		
		player.setGoldExpendHistorys(hostorys);
	}
	
	private boolean checkValidTime(Player player, long startTime, long endTime) {
		List<GoldExpend> hostorys = player.getGoldExpendHistorys();
		if (hostorys == null || hostorys.size() < 1) {
			return false;
		}
		
		if (hostorys.get(0).getOptTime() > startTime) {
			return false;
		}
		return true;
	}
	
	public void add(long playerId, int gold, int reason) {
		GoldExpend expend = new GoldExpend();
		expend.setUnuseIndex(Config.getId());
		expend.setRole(playerId);
		expend.setGold(gold);
		expend.setType(reason);
		expend.setOptTime(System.currentTimeMillis());
		
		Player player = PlayerManager.getInstance().getPlayer(expend.getRole());
		if (player != null && player.getGoldExpendHistorys() != null) {
			player.getGoldExpendHistorys().add(expend);
		}
		
		WorldServer.getInstance().getwSaveGoldExpendThread().addSaveTask(expend);
	}
}
