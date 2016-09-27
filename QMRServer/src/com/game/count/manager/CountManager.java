package com.game.count.manager;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.game.count.structs.Count;
import com.game.count.structs.CountTypes;
import com.game.player.structs.Player;
import com.game.pool.MemoryPool;

/**
 * 冷却管理类
 *
 * @author heyang
 *
 */
public class CountManager {
	/**
	 * 天
	 */
	public static int COUNT_DAY=1;//
	/**
	 * 周
	 */
	public static int COUNT_WEEK=2;//
	/**
	 * 月
	 */
	public static int COUNT_MONTH=3;//
	/**
	 * 年
	 */
	public static int COUNT_YEAR=4;	//
	/**
	 * 永久
	 */
	public static int COUNT_PERSISTENT=5;//

	protected Logger log = Logger.getLogger(CountManager.class);
	private static Object obj = new Object();
	//管理类实例
	private static CountManager manager;
	private MemoryPool<Count> countPool = new MemoryPool<Count>(100000);

	private CountManager() {
	}

	public static CountManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new CountManager();
			}
		}
		return manager;
	}

	/**
	 * 添加计数
	 *
	 * @param roleId 玩家Id
	 * @param type 类型
	 * @param key 关键字
	 * @param countNum 数量
	 */
	public void addCount(Player player, CountTypes type, String key, long countNum) {
		//初始化冷却关键字
		String countKey = null;

		if (key == null) {
			countKey = type.getValue();
		} else {
			countKey = type.getValue() + "_" + key;
		}
		if (player.getCounts().containsKey(countKey)) {
			Count count = player.getCounts().get(countKey);
			reset(count);
			count.setCount(count.getCount() + countNum);
			count.setLastTime(System.currentTimeMillis());
		}
	}

	/**
	 * 添加计数
	 *
	 * @param roleId 玩家Id
	 * @param type 类型
	 * @param key 关键字
	 * @param countType 计数时间类型
	 * @param countNum 数量
	 * @param refreshTime 刷新时间
	 */
	public void addCount(Player player, CountTypes type, String key, int countType, long countNum, long refreshTime) {
		//初始化冷却关键字
		String countKey = null;

		if (key == null) {
			countKey = type.getValue();
		} else {
			countKey = type.getValue() + "_" + key;
		}
		if (player.getCounts().containsKey(countKey)) {
			Count count = player.getCounts().get(countKey);
			reset(count);
			count.setCount(count.getCount() + countNum);
			count.setLastTime(System.currentTimeMillis());
			if (count.getStart() == 0) {
				count.setType(countType);
				count.setStart(System.currentTimeMillis());
				count.setRefreshTime(refreshTime);
			}
		} else {
			//初始化冷却信息
			Count count = createCount();
			count.setType(countType);
			count.setKey(countKey);
			count.setStart(System.currentTimeMillis());
			count.setCount(countNum);
			count.setLastTime(System.currentTimeMillis());
			count.setRefreshTime(refreshTime);

			//添加冷却
			player.getCounts().put(countKey, count);
		}
	}

	public long getCount(Player player, CountTypes type, String key) {
		// 初始化冷却关键字
		String countKey = null;
		if (key == null) {
			countKey = type.getValue();
		} else {
			countKey = type.getValue() + "_" + key;
		}
		Count count = player.getCounts().get(countKey);
		if (count != null) {
			reset(count);
			return count.getCount();
		}
		return 0;
	}

	/**
	 * 移除冷却
	 *
	 * @param roleId 玩家Id
	 * @param type 类型
	 * @param key 关键字
	 */
	public void removeCount(Player player, CountTypes type, String key) {
		//初始化冷却关键字
		String countKey = null;
		if (key == null) {
			countKey = type.getValue();
		} else {
			countKey = type.getValue() + "_" + key;
		}

		//移除冷却
		if (player.getCounts().containsKey(countKey)) {
			Count count = player.getCounts().remove(countKey);
			countPool.put(count);
		}
	}

	/**
	 * 计数器重置（通过时间判断）
	 *
	 * @param count
	 */
	private void reset(Count count) {
		if (count == null || count.getStart() == 0) {
			return;
		}
		if (count.getType() == -1) {
			return;
		}
		
		long refresh = count.getRefreshTime() * 1000;
		Calendar cal = Calendar.getInstance();
		//设置星期一为第一天
		cal.setFirstDayOfWeek(Calendar.MONDAY);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		int week = cal.getFirstDayOfWeek() - cal.get(Calendar.DAY_OF_WEEK);

		switch (count.getType()) {
			//天
			case 1:
				cal.set(year, month, date, 0, 0, 0);
				break;
			//周
			case 2:
				cal.set(year, month, date, 0, 0, 0);
				cal.add(Calendar.DATE, week > 0 ? week - 7 : week);
				break;
			//月
			case 3:
				cal.set(year, month, 1, 0, 0, 0);
				break;
			//年
			case 4:
				cal.set(year, 0, 1, 0, 0, 0);
				break;
				//永久
			case 5:
				cal.setTimeInMillis(0);
				break;
		}
		refresh += cal.getTimeInMillis();
		long now = System.currentTimeMillis();
		
		if (count.getLastTime() < refresh && now >= refresh) {
			count.setCount(0);
		}
	}

	private Count createCount() {
		try {
			return countPool.get(Count.class);
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
