package com.game.cooldown.manager;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.Cooldown;
import com.game.cooldown.structs.CooldownTypes;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.pool.MemoryPool;

/**
 * 冷却管理类
 *
 * @author heyang
 *
 */
public class CooldownManager {

	protected Logger log = Logger.getLogger(CooldownManager.class);
	private static Object obj = new Object();
	//管理类实例
	private static CooldownManager manager;
	private MemoryPool<Cooldown> cooldownPool = new MemoryPool<Cooldown>(100000);

	private CooldownManager() {
	}

	public static CooldownManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new CooldownManager();
			}
		}
		return manager;
	}

	/**
	 * 添加冷却
	 *
	 * @param roleId 玩家Id
	 * @param type 类型
	 * @param key 关键字
	 * @param delay 冷却时间
	 */
	public void addCooldown(Player player, CooldownTypes type, String key, long delay) {
		//初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}
		if (player.getCooldowns().containsKey(cooldownKey)) {
			Cooldown cooldown = player.getCooldowns().get(cooldownKey);
			cooldown.setStart(System.currentTimeMillis());
			cooldown.setDelay(delay);
		} else {
			//初始化冷却信息
			Cooldown cooldown = createCooldown();
			cooldown.setType(type.getValue());
			cooldown.setKey(cooldownKey);
			cooldown.setStart(System.currentTimeMillis());
			cooldown.setDelay(delay);

			//添加冷却
			player.getCooldowns().put(cooldownKey, cooldown);
		}
	}

	public long getCooldownTime(Player player, CooldownTypes type, String key) {
		// 初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}
		Cooldown cooldown = player.getCooldowns().get(cooldownKey);
		if (cooldown != null) {
			return cooldown.getRemainTime();
		}
		return 0;
	}

	public long getCooldownTime(Pet pet, CooldownTypes type, String key) {
		// 初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}
		Cooldown cooldown = pet.getCooldowns().get(cooldownKey);
		if (cooldown != null) {
			return cooldown.getRemainTime();
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
	public void removeCooldown(Player player, CooldownTypes type, String key) {
		//初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}

		//移除冷却
		if (player.getCooldowns().containsKey(cooldownKey)) {
			Cooldown cooldown = player.getCooldowns().remove(cooldownKey);
			cooldownPool.put(cooldown);
		}
	}

	/**
	 * 是否在冷却中
	 *
	 * @param roleId 玩家Id
	 * @param type 冷却类型
	 * @param key 关键字
	 * @return
	 */
	public boolean isCooldowning(Player player, CooldownTypes type, String key) {
		return isCooldowning(player, type, key, 100);
	}

	/**
	 * 是否在冷却中
	 *
	 * @param roleId 玩家Id
	 * @param type 冷却类型
	 * @param key 关键字
	 * @return
	 */
	public boolean isCooldowning(Player player, CooldownTypes type, String key, int allow) {
		//初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}

		//查看冷却
		if (player.getCooldowns().containsKey(cooldownKey)) {
			Cooldown cooldown = player.getCooldowns().get(cooldownKey);
			//放宽100毫秒冷却
			if (System.currentTimeMillis() > cooldown.getStart() + cooldown.getDelay() - allow) {
				//冷却时间已经结束
				return false;
			} else {
				//log.info("Now:" + System.currentTimeMillis() + ",Cooldown Start:" + cooldown.getStart() + ", Delay:" + cooldown.getDelay());
				return true;
			}
		}
		return false;
	}

	/**
	 * 添加冷却
	 *
	 * @param monsterId 玩家Id
	 * @param type 类型
	 * @param key 关键字
	 * @param delay 冷却时间
	 */
	public void addCooldown(Monster monster, CooldownTypes type, String key, long delay) {
		//获得玩家
		if (monster == null) {
			return;
		}

		//初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}
		if (monster.getCooldowns().containsKey(cooldownKey)) {
			Cooldown cooldown = monster.getCooldowns().get(cooldownKey);
			cooldown.setStart(System.currentTimeMillis());
			cooldown.setDelay(delay);
		} else {
			//初始化冷却信息
			Cooldown cooldown = createCooldown();
			cooldown.setType(type.getValue());
			cooldown.setKey(cooldownKey);
			cooldown.setStart(System.currentTimeMillis());
			cooldown.setDelay(delay);

			//添加冷却
			monster.getCooldowns().put(cooldownKey, cooldown);
		}
	}

	/**
	 * 移除冷却
	 *
	 * @param monsterId 玩家Id
	 * @param type 类型
	 * @param key 关键字
	 */
	public void removeCooldown(Monster monster, CooldownTypes type, String key) {
		//获得玩家
		if (monster == null) {
			return;
		}

		//初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}

		//移除冷却
		if (monster.getCooldowns().containsKey(cooldownKey)) {
			Cooldown cooldown = monster.getCooldowns().remove(cooldownKey);
			cooldownPool.put(cooldown);
		}
	}

	/**
	 * 是否在冷却中
	 *
	 * @param monsterId 玩家Id
	 * @param type 冷却类型
	 * @param key 关键字
	 * @return
	 */
	public boolean isCooldowning(Monster monster, CooldownTypes type, String key) {
		//获得玩家
		if (monster == null) {
			return true;
		}

		//初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}

		//查看冷却
		if (monster.getCooldowns().containsKey(cooldownKey)) {
			Cooldown cooldown = monster.getCooldowns().get(cooldownKey);
			if (System.currentTimeMillis() > cooldown.getStart() + cooldown.getDelay()) {
				//冷却时间已经结束
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 添加冷却
	 *
	 * @param monsterId 玩家Id
	 * @param type 类型
	 * @param key 关键字
	 * @param delay 冷却时间
	 */
	public void addCooldown(Pet pet, CooldownTypes type, String key, long delay) {
		//获得玩家
		if (pet == null) {
			return;
		}

		//初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}
		if (pet.getCooldowns().containsKey(cooldownKey)) {
			Cooldown cooldown = pet.getCooldowns().get(cooldownKey);
			cooldown.setStart(System.currentTimeMillis());
			cooldown.setDelay(delay);
		} else {
			//初始化冷却信息
			Cooldown cooldown = createCooldown();
			cooldown.setType(type.getValue());
			cooldown.setKey(cooldownKey);
			cooldown.setStart(System.currentTimeMillis());
			cooldown.setDelay(delay);

			//添加冷却
			pet.getCooldowns().put(cooldownKey, cooldown);
		}
	}

	/**
	 * 移除冷却
	 *
	 * @param monsterId 玩家Id
	 * @param type 类型
	 * @param key 关键字
	 */
	public void removeCooldown(Pet pet, CooldownTypes type, String key) {
		//获得玩家
		if (pet == null) {
			return;
		}

		//初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}

		//移除冷却
		if (pet.getCooldowns().containsKey(cooldownKey)) {
			Cooldown cooldown = pet.getCooldowns().remove(cooldownKey);
			cooldownPool.put(cooldown);
		}

	}

	/**
	 * 是否在冷却中
	 *
	 * @param monsterId 玩家Id
	 * @param type 冷却类型
	 * @param key 关键字
	 * @return
	 */
	public boolean isCooldowning(Pet pet, CooldownTypes type, String key) {
		//获得玩家
		if (pet == null) {
			return true;
		}

		//初始化冷却关键字
		String cooldownKey = null;
		if (key == null) {
			cooldownKey = type.getValue();
		} else {
			cooldownKey = type.getValue() + "_" + key;
		}

		//查看冷却
		if (pet.getCooldowns().containsKey(cooldownKey)) {
			Cooldown cooldown = pet.getCooldowns().get(cooldownKey);
			if (System.currentTimeMillis() > cooldown.getStart() + cooldown.getDelay()) {
				//冷却时间已经结束
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	private Cooldown createCooldown() {
		try {
			return cooldownPool.get(Cooldown.class);
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}
}
