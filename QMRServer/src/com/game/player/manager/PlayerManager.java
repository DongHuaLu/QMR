package com.game.player.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ResUseItemSuccessMessage;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.biwudao.manager.BiWuDaoManager;
import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffConst;
import com.game.buff.structs.BuffType;
import com.game.cache.impl.MemoryCache;
import com.game.collect.manager.CollectManager;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.country.manager.CountryAwardManager;
import com.game.country.manager.CountryManager;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_globalBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_monsterBean;
import com.game.data.bean.Q_newrole_defaultvalueBean;
import com.game.data.bean.Q_skill_realmBean;
import com.game.data.bean.Q_task_mainBean;
import com.game.data.manager.DataManager;
import com.game.dataserver.manager.DataServerManager;
import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.db.bean.Gold;
import com.game.db.bean.Role;
import com.game.db.dao.RoleDao;
import com.game.dblog.LogService;
import com.game.dblog.UpdateLogService;
import com.game.equip.bean.EquipInfo;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.gem.struts.Gem;
import com.game.guild.bean.BannerInfo;
import com.game.guild.bean.OtherGuildInfo;
import com.game.guild.manager.GuildServerManager;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.login.message.ResRemoveCharacterToGateMessage;
import com.game.login.message.ResRemoveCharacterToWorldMessage;
import com.game.longyuan.bean.ShowEffectInfo;
import com.game.longyuan.message.ResShowEffectToClientMessage;
import com.game.longyuan.structs.LongYuanData;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.structs.Map;
import com.game.marriage.structs.Marriage;
import com.game.monster.manager.MonsterManager;
import com.game.monster.structs.Monster;
import com.game.pet.manager.PetInfoManager;
import com.game.pet.manager.PetOptManager;
import com.game.pet.manager.PetScriptManager;
import com.game.pet.message.ResPetShowMessage;
import com.game.pet.struts.Pet;
import com.game.player.bean.OtherPlayerInfo;
import com.game.player.bean.PlayerAppearanceInfo;
import com.game.player.bean.PlayerAttributeItem;
import com.game.player.log.RoleExpChanageLog;
import com.game.player.log.RoleLevelUpLog;
import com.game.player.log.RoleLoginLog;
import com.game.player.log.RoleLoginOutLog;
import com.game.player.log.RoleReportLog;
import com.game.player.log.RoleZhenQiLog;
import com.game.player.message.ReqChangePlayerNameMessage;
import com.game.player.message.ReqChangePlayerNameToWorldMessage;
import com.game.player.message.ReqDelPlayerToGameMessage;
import com.game.player.message.ReqDelPlayerToWorldMessage;
import com.game.player.message.ReqGetPlayerAppearanceInfoMessage;
import com.game.player.message.ReqGetPlayerAppearanceInfoToWorldMessage;
import com.game.player.message.ReqNonageRegisterToWorldMessage;
import com.game.player.message.ReqNonageTimeToWorldMessage;
import com.game.player.message.ReqOtherPlayerInfoToWorldMessage;
import com.game.player.message.ReqPlayerCheckOnlineMessage;
import com.game.player.message.ReqPlayerCheckOnlineToWorldMessage;
import com.game.player.message.ReqScriptCommonPlayerToServerMessage;
import com.game.player.message.ReqSyncPlayerAppearanceInfoMessage;
import com.game.player.message.ReqSyncPlayerArrowMessage;
import com.game.player.message.ReqSyncPlayerAttributeMessage;
import com.game.player.message.ReqSyncPlayerEquipMessage;
import com.game.player.message.ReqSyncPlayerGemMessage;
import com.game.player.message.ReqSyncPlayerInfoMessage;
import com.game.player.message.ReqSyncPlayerLevelMessage;
import com.game.player.message.ReqSyncPlayerOrderInfoMessage;
import com.game.player.message.ReqSyncPlayerRankMessage;
import com.game.player.message.ResAutoStartStateMessage;
import com.game.player.message.ResChangePKStateMessage;
import com.game.player.message.ResChangePlayerNameToClientMessage;
import com.game.player.message.ResChangePlayerNameToGameMessage;
import com.game.player.message.ResJumpMaxSpeedMessage;
import com.game.player.message.ResMyPlayerInfoMessage;
import com.game.player.message.ResOtherPlayerInfoMessage;
import com.game.player.message.ResPlayerAvatarMessage;
import com.game.player.message.ResPlayerBattleExpChangeMessage;
import com.game.player.message.ResPlayerDieMessage;
import com.game.player.message.ResPlayerExpChangeMessage;
import com.game.player.message.ResPlayerHpChangeMessage;
import com.game.player.message.ResPlayerLevelUpMessage;
import com.game.player.message.ResPlayerMaxHpChangeMessage;
import com.game.player.message.ResPlayerMaxMpChangeMessage;
import com.game.player.message.ResPlayerMaxSpChangeMessage;
import com.game.player.message.ResPlayerMpChangeMessage;
import com.game.player.message.ResPlayerNonageStateMessage;
import com.game.player.message.ResPlayerPrisonStateMessage;
import com.game.player.message.ResPlayerSpChangeMessage;
import com.game.player.message.ResPlayerSpeedChangeMessage;
import com.game.player.message.ResPlayerZhenqiChangeMessage;
import com.game.player.message.ResReviveSuccessMessage;
import com.game.player.message.ResScriptCommonServerToServerMessage;
import com.game.player.message.ResVipPlayerChangeMapToClientMessage;
import com.game.player.scheduler.Midnight0ClockEvent;
import com.game.player.script.IBeforePlayerLoadScript;
import com.game.player.script.IPlayerCheckScript;
import com.game.player.script.IPlayerDieScript;
import com.game.player.script.IPlayerLoadScript;
import com.game.player.script.IPlayerLoginEndScript;
import com.game.player.script.IPlayerLoginScript;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.LaterTask;
import com.game.player.structs.Person;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.player.structs.PlayerSpeedReport;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.registrar.manager.RegistrarManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.config.ServerType;
import com.game.server.impl.WServer;
import com.game.shortcut.manager.ShortCutManager;
import com.game.skill.manager.SkillManager;
import com.game.skill.structs.Skill;
import com.game.structs.Attributes;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.task.log.MainTaskLog;
import com.game.task.manager.TaskManager;
import com.game.task.struts.MainTask;
import com.game.task.struts.RankTaskEnum;
import com.game.task.struts.Task;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;
import com.game.utils.VersionUpdateUtil;
import com.game.utils.WordFilter;
import com.game.vip.manager.VipManager;
import com.game.zones.structs.ZoneContext;

/**
 * @author heyang E-mail: szy_heyang@163.com
 *
 * @version 1.0.0
 *
 * @since 2011-5-20
 *
 * 玩家管理类
 */
public class PlayerManager {

	private static Logger log = Logger.getLogger(PlayerManager.class);
	private static Logger playerlog = Logger.getLogger("SERVERPLAYER");
	//private static Logger savelog = Logger.getLogger("SAVEPLAYER");
	
	private static Object obj = new Object();
	//玩家管理类实例
	private static PlayerManager manager;
	//玩家数据缓存
	private static MemoryCache<Long, Player> players = new MemoryCache<Long, Player>();
	//玩家在线列表（key为角色编号）
	@Deprecated
	private static ConcurrentHashMap<Long, Player> online = new ConcurrentHashMap<Long, Player>();
	
	//账号在线列表（key为账号名称）
	@Deprecated
	private static HashMap<String, Player> user = new HashMap<String, Player>();
	
	//在线举报列表
	private ConcurrentHashMap<String, PlayerSpeedReport> serverSpeedReportMap = new ConcurrentHashMap<String, PlayerSpeedReport>();
	
	//测试GM IP
	private Set<String> testGmIps = new HashSet<String>();
	
	//数据库
	private RoleDao dao = new RoleDao();
	//玩家同步坐标
	private static HashSet<Long> syncPosition = new HashSet<Long>();
	//玩家名字
	private static HashSet<String> names = new HashSet<String>();
	public static boolean CHECK_NONAGE = true;
	private static int[][] birth = new int[][]{{97, 131},
		{94, 136},
		{100, 138},
		{107, 136},
		{110, 132},
		{103, 130}};

	private PlayerManager() {
	}

	public static PlayerManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new PlayerManager();
			}
		}
		return manager;
	}

	/**
	 * 加载所有名字
	 */
	public void loadNames() {
		try {
			List<String> namelist = dao.selectNames();
			Iterator<String> iter = namelist.iterator();
			synchronized (names) {
				while (iter.hasNext()) {
					String name = (String) iter.next();
					names.add(name);
				}
			}
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	/**
	 * 名字检查重复
	 *
	 * @param name 名字
	 * @return true 重名 false 不
	 */
	public boolean checkName(String name) {
		synchronized (names) {
			if (names.contains(name)) {
				return true;
			} else {
				names.add(name);
				return false;
			}
		}
	}
	
	/**
	 * 名字检查重复
	 *
	 * @param name 名字
	 * @return true 重名 false 不
	 */
	public boolean removeName(String name) {
		synchronized (names) {
			if (names.contains(name)) {
				names.remove(name);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 获取玩家
	 *
	 * @param roleId 玩家Id
	 * @return
	 */
	public Player getPlayer(long roleId) {
		return players.get(roleId);
	}

	/**
	 * 注册玩家
	 *
	 * @param player
	 */
	public void registerPlayer(Player player) {
		players.put(player.getId(), player);
	}

	@SuppressWarnings("deprecation")
	public Player createPlayer(String name, String icon, byte sex) {
		Player player = new Player();
		player.setId(Config.getId());
		player.setMap(20001);
		player.setMapModelId(20001);
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(player.getMapModelId());
		int[] gridpos = birth[RandomUtils.random(birth.length)];
		Grid grid = MapUtils.getGrid(gridpos[0], gridpos[1], grids);
		player.setPosition(grid.getCenter());
		player.setSex(sex);
		player.setLevel(1);
		player.setAvatarid(1);
		player.setIcon(icon);
		player.setName(name);
		player.setMoney(0);
		//player.setCountry(((WServer.getGameConfig().getCountryByServer(WServer.getInstance().getServerId()) - 1)%7) + 1);
		player.setCountry(WServer.getGameConfig().getCountryByServer(WServer.getInstance().getServerId()));
		player.setLocate(player.getCountry());
		player.setBagCellsNum(Global.DEFAULT_BAG_CELLS);
		player.setStoreCellsNum(Global.DEFAULT_STORE_CELLS);
		player.setBagCellTimeCount(0);
		player.setStoreCellTimeCount(0);
		player.setDailyTaskCount(0);
		player.setConquerTaskCount(0);
		player.setConquerTaskMaxCount(0);
		player.setAutoteaminvited((byte) 1);
		player.setAutoIntoteamapply((byte) 1);
		player.setOpenMapLocation((byte) 1);
		player.setShowrelation((byte) 1);
		player.setShowicon((byte) 1);
		player.setAutoArgeeAddGuild((byte) 1);
		player.setTaskclear(true);
		buildDefaultValue(player);
		//基本加成
//		Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
//		player.setHp(model.getQ_hp());
//		player.setMp(model.getQ_mp());
//		player.setSp(model.getQ_sp());
		//升级自动学会的
		SkillManager.getInstance().autoStudySkill(player);
		String beforeReceiveAble = JSONserializable.toString(player.getTaskRewardsReceiveAble());
		TaskManager.getInstance().acceptMainTask(player, TaskManager.CREATEPLAYERDEFAULTTASK);
		Task task = TaskManager.getInstance().getTaskByModelId(player, TaskManager.CREATEPLAYERDEFAULTTASK);

		ManagerPool.buffManager.addBuff(player, player, Global.NEWBIE_BUFF, 0, 0, 0);
		try {
			if (task != null) {
				MainTaskLog log = new MainTaskLog();
				log.setRoleId(player.getId());
				log.setAcceptafterReceiveAble(JSONserializable.toString(player.getTaskRewardsReceiveAble()));
				log.setAcceptbeforeReceiveAble(beforeReceiveAble);
				log.setAcceptmodelId(TaskManager.CREATEPLAYERDEFAULTTASK);
				log.setAccepttaskInfo(JSONserializable.toString(task));
				log.setAcceptlevel(player.getLevel());
				log.setAcceptonlinetime(player.getAccunonlinetime());
				log.setUserId(player.getUserId());
				LogService.getInstance().execute(log);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		//新人设置默认宝石
		ManagerPool.gemManager.createDefaultGem(player);
		return player;
	}

	private void buildDefaultValue(Player player) {
		try {
			//出生等级
			Q_newrole_defaultvalueBean model = DataManager.getInstance().q_newrole_defaultvalueContainer.getMap().get((int) player.getSex());
			if (model == null) {
				return;
			}
			int q_initlevel = model.getQ_initlevel();
			if (q_initlevel > 1) {
				PlayerManager.getInstance().setLevel(player, q_initlevel, false, Reasons.LEVELUPCREATE);
			}
			//默认装备
			addEquip(player, model.getQ_body1(), 0);
			addEquip(player, model.getQ_body2(), 1);
			addEquip(player, model.getQ_body3(), 2);
			addEquip(player, model.getQ_body4(), 3);
			addEquip(player, model.getQ_body5(), 4);
			addEquip(player, model.getQ_body6(), 5);
			addEquip(player, model.getQ_body7(), 6);
			addEquip(player, model.getQ_body8(), 7);
			addEquip(player, model.getQ_body9(), 8);
			addEquip(player, model.getQ_body10(), 9);

			//包裹 物品
			String q_bageitems = model.getQ_bageitems();
			buildBagItems(player, q_bageitems);

			//默认学会技能
			String q_skills = model.getQ_skills();
			if (!StringUtil.isBlank(q_skills)) {
				String[] split = q_skills.split(Symbol.FENHAO_REG);
				for (String string : split) {
					if (!StringUtil.isBlank(string)) {
						String[] split2 = string.split(Symbol.DOUHAO_REG);
						int modelId = Integer.parseInt(split2[0]);
						int level = Integer.parseInt(split2[1]);
						SkillManager.getInstance().addSkill(player, modelId);
						if (level > 1) {
							Skill skill = SkillManager.getInstance().getSkillByModelId(player, modelId);
							SkillManager.getInstance().endUpLevel(player, skill, level, true);
						}
					}
				}
			}
			//快捷键
			String q_short_cut = model.getQ_short_cut();
			buildShortCut(player, q_short_cut);
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	private void addEquip(Player player, String q_body1, int i) {
		//		装备1(模型ID,是否绑定,强化等级,属性类型|属性值;属性类型|属性值）
		if (!StringUtil.isBlank(q_body1)) {
			String[] split = q_body1.split(Symbol.DOUHAO);
			int modelId = Integer.parseInt(split[0]);
			boolean isbind = split[1].equals("1");
			int gradenum = Integer.parseInt(split[2]);
			String append = "";
			if (split.length > 3) {
				append = split[3];
			}
			List<Item> createItems = Item.createItems(modelId, 1, isbind, 0, gradenum, append);
			Item item = createItems.get(0);
			if (item instanceof Equip) {
				player.getEquips()[i] = (Equip) item;
			}
		}
	}

	private void buildBagItems(Player player, String items) {
		if (!StringUtil.isBlank(items)) {
//			包裹物品（模型ID,数量,是否绑定,强化等级,属性类型|属性值;属性类型|属性值:模型ID,数量,是否绑定,强化等级,属性类型|属性值;属性类型|属性值)
			long id = Config.getId();
			String[] split = items.split(Symbol.MAOHAO_REG);
			for (String string : split) {
				if (!StringUtil.isBlank(string)) {
					String[] itemparm = string.split(Symbol.DOUHAO_REG);
					if (itemparm.length < 4) {
						log.error("策划配错了" + string);
					}
					int modelId = Integer.parseInt(itemparm[0]);
					int num = Integer.parseInt(itemparm[1]);
					boolean isbind = itemparm[2].equals("1");
					int gradenum = Integer.parseInt(itemparm[3]);
					String append = "";
					if (itemparm.length > 4) {
						append = itemparm[4];
					}
					if (num > BackpackManager.getInstance().getAbleAddNum(player, modelId, isbind, 0)) {
						log.error("策划配错了 包裹里装不下" + string);
						return;
					}
					List<Item> createItems = Item.createItems(modelId, num, isbind, 0, gradenum, append);
					BackpackManager.getInstance().addItems(player, createItems, Reasons.SYSTEM_GIFT, id);
				}
			}
		}
	}

	private void buildShortCut(Player player, String cuts) {
		if (!StringUtil.isBlank(cuts)) {
			String[] split = cuts.split(Symbol.FENHAO_REG);
			for (int i = 0; i < split.length; i++) {
				String string = split[i];
				int parseInt = Integer.parseInt(string);
				Skill skillByModelId = SkillManager.getInstance().getSkillByModelId(player, parseInt);
				if (skillByModelId != null) {
					ShortCutManager.getInstance().addShortCut(player, 2, skillByModelId.getId(), parseInt, i + 1);
				}
			}
		}

	}

	/**
	 * 加载玩家
	 *
	 * @param roleId 玩家Id
	 * @return
	 */
	public Player loadPlayer(long roleId) {
		try {
			Role role = dao.selectById(roleId);

			if (role == null) {
				return null;
			}

			IBeforePlayerLoadScript bscript = (IBeforePlayerLoadScript) ManagerPool.scriptManager.getScript(ScriptEnum.BEFORE_PLAYER_LOAD);
			if (bscript != null) {
				try {
					role = bscript.beforeLoad(role);
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				log.error("人物加载前脚本不存在！");
			}

			Player player = (Player) JSONserializable.toObject(VersionUpdateUtil.dateLoad(role.getData()), Player.class);
			player.setUserId(role.getUserid());
			player.setName(role.getName());
			player.setCreateServerId(role.getCreateServer());
			player.setCountry(role.getCountry());
			player.setLocate(role.getLocate());

			IPlayerLoadScript script = (IPlayerLoadScript) ManagerPool.scriptManager.getScript(ScriptEnum.PLAYER_LOAD);
			if (script != null) {
				try {
					script.onLoad(player, role.getData());
				} catch (Exception e) {
					log.error(e, e);
				}
			} else {
				log.error("人物加载后脚本不存在！");
			}
			return player;
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	/**
	 * 更新玩家数据
	 *
	 * @param player 玩家数据
	 */
	public void updatePlayer(Player player) {
		try {
			if(!player.isCross()){
				Role role = new Role();
				
				role.setUserid(player.getUserId());
				role.setCreateServer(player.getCreateServerId());
				role.setSex((int) player.getSex());
				
				role.setRoleid(player.getId());
				role.setName(player.getName());
				role.setLocate(player.getLocate());
				role.setLevel((int) player.getLevel());
	
				role.setVersion(player.getVersion());
				role.setIsDelete(player.isDelete());
				role.setIsForbid(player.isForbid());
				role.setCountry(player.getCountry());
				role.setLoginIp(player.getLoginIp());
				role.setWidth(player.getWidth());
				role.setHeight(player.getHeight());
				role.setMoney(player.getMoney());
				role.setBindGold(player.getBindGold());
				role.setBagCellsNum(player.getBagCellsNum());
				role.setStoreCellsNum(player.getStoreCellsNum());
				role.setProhibitChatTime(player.getProhibitChatTime());
				role.setStartProhibitChatTime(player.getStartProhibitChatTime());
				role.setAddBlackTime(player.getAddBlackTime());
				role.setAddBlackCount(player.getAddBlackCount());
				role.setExp(player.getExp());
				role.setZhenqi(player.getZhenqi());
				role.setPrestige(player.getPrestige());
				role.setDieTime(player.getDieTime());
				role.setArrowLevel(player.getArrowLevel());
				role.setTianyuanLevel(player.getTianyuanLevel());
				role.setPrestigePoint(player.getPrestigePoint());
				role.setAchievementPoint(player.getAchievementPoint());
				role.setFightPower(player.getFightPower());
				role.setHorseLevel(HorseManager.getInstance().getHorse(player).getLayer());
				role.setOnlineTime(player.getAccunonlinetime());
				role.setMap(player.getMap());
				role.setMapModelId(player.getMapModelId());
				role.setX(player.getPosition().getX());
				role.setY(player.getPosition().getY());
				role.setHp(player.getHp());
				role.setMp(player.getMp());
				role.setSp(player.getSp());
	
				role.setData(VersionUpdateUtil.dateSave(JSONserializable.toString(player)));
	
				role.setLogintime(player.getLoginTime());
			
				WServer.getInstance().getSavePlayerThread().addRole(role);
				
				if (player.getLevel() >= Global.SYNC_PLAYER_LEVEL) {
					//syncPlayerAttributes(player);
					//同步玩家排行信息
					syncPlayerOrderInfo(player);
				}
			}else{
				//同步到跨服数据服务器
				DataServerManager.getInstance().reqSyncPlayerCrossToDataServer(player);
			}
			
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	/**
	 * 更新玩家数据
	 *
	 * @param player 玩家数据
	 */
	public void updatePlayerSync(Player player) {
		if(!player.isCross()){
			Role role = new Role();
			role.setRoleid(player.getId());
			role.setName(player.getName());
			role.setLocate(player.getLocate());
			role.setLevel((int) player.getLevel());

			role.setVersion(player.getVersion());
			role.setIsDelete(player.isDelete());
			role.setIsForbid(player.isForbid());
			role.setCountry(player.getCountry());
			role.setLoginIp(player.getLoginIp());
			role.setWidth(player.getWidth());
			role.setHeight(player.getHeight());
			role.setMoney(player.getMoney());
			role.setBindGold(player.getBindGold());
			role.setBagCellsNum(player.getBagCellsNum());
			role.setStoreCellsNum(player.getStoreCellsNum());
			role.setProhibitChatTime(player.getProhibitChatTime());
			role.setStartProhibitChatTime(player.getStartProhibitChatTime());
			role.setAddBlackTime(player.getAddBlackTime());
			role.setAddBlackCount(player.getAddBlackCount());
			role.setExp(player.getExp());
			role.setZhenqi(player.getZhenqi());
			role.setPrestige(player.getPrestige());
			role.setDieTime(player.getDieTime());
			role.setArrowLevel(player.getArrowLevel());
			role.setTianyuanLevel(player.getTianyuanLevel());
			role.setPrestigePoint(player.getPrestigePoint());
			role.setAchievementPoint(player.getAchievementPoint());
			role.setFightPower(player.getFightPower());
			role.setHorseLevel(HorseManager.getInstance().getHorse(player).getLayer());
			role.setOnlineTime(player.getAccunonlinetime());
			role.setMap(player.getMap());
			role.setMapModelId(player.getMapModelId());
			role.setX(player.getPosition().getX());
			role.setY(player.getPosition().getY());
			role.setHp(player.getHp());
			role.setMp(player.getMp());
			role.setSp(player.getSp());

			role.setData(VersionUpdateUtil.dateSave(JSONserializable.toString(player)));

			role.setLogintime(player.getLoginTime());
			
			dao.update(role);
		}else{
			//同步到跨服数据服务器
			DataServerManager.getInstance().reqSyncPlayerCrossToDataServer(player);
		}
	}

	/**
	 * 移除玩家数据
	 *
	 * @param player 玩家数据
	 */
	public void removePlayer(Player player) {
		online.remove(player.getId());
		players.remove(player.getId());
	}

	/**
	 * 插入玩家数据
	 *
	 * @param player 玩家数据
	 */
	public void insertPlayer(Player player) {
		Role role = new Role();
		role.setRoleid(player.getId());
		role.setUserid(player.getUserId());
		role.setCreateServer(player.getCreateServerId());
		role.setLocate(player.getLocate());
		role.setName(player.getName());
		role.setLevel((int) player.getLevel());
		role.setSex((int) player.getSex());

		role.setVersion(player.getVersion());
		role.setIsDelete(player.isDelete());
		role.setIsForbid(player.isForbid());
		role.setCountry(player.getCountry());
		role.setLoginIp(player.getLoginIp());
		role.setWidth(player.getWidth());
		role.setHeight(player.getHeight());
		role.setMoney(player.getMoney());
		role.setBindGold(player.getBindGold());
		role.setBagCellsNum(player.getBagCellsNum());
		role.setStoreCellsNum(player.getStoreCellsNum());
		role.setProhibitChatTime(player.getProhibitChatTime());
		role.setStartProhibitChatTime(player.getStartProhibitChatTime());
		role.setAddBlackTime(player.getAddBlackTime());
		role.setAddBlackCount(player.getAddBlackCount());
		role.setExp(player.getExp());
		role.setZhenqi(player.getZhenqi());
		role.setPrestige(player.getPrestige());
		role.setDieTime(player.getDieTime());
		role.setArrowLevel(player.getArrowLevel());
		role.setTianyuanLevel(player.getTianyuanLevel());
		role.setPrestigePoint(player.getPrestigePoint());
		role.setAchievementPoint(player.getAchievementPoint());
		role.setFightPower(player.getFightPower());
		role.setHorseLevel(HorseManager.getInstance().getHorse(player).getLayer());
		role.setOnlineTime(player.getAccunonlinetime());
		role.setMap(player.getMap());
		role.setMapModelId(player.getMapModelId());
		role.setX(player.getPosition().getX());
		role.setY(player.getPosition().getY());
		role.setHp(player.getHp());
		role.setMp(player.getMp());
		role.setSp(player.getSp());
		role.setAgentPlusdata(player.getAgentPlusdata());
		role.setAgentColdatas(player.getAgentColdatas());

		role.setData(VersionUpdateUtil.dateSave(JSONserializable.toString(player)));

		role.setLogintime(System.currentTimeMillis());
		dao.insert(role);
	}

//	/**
//	 * 检查玩家名字是否重名
//	 *
//	 * @param name 玩家名字
//	 */
//	public boolean checkPlayer(String name) {
//		try {
//			return dao.selectByName(name) > 0;
//		} catch (SQLException e) {
//			log.error(e);
//		}
//
//		return true;
//	}
	/**
	 * 登陆流程
	 *
	 * @param roleId @type 0-登陆 1-切换地图登陆
	 */
	public void login(Player player, byte type, int width, int height) {
		IPlayerLoginScript script = (IPlayerLoginScript) ManagerPool.scriptManager.getScript(ScriptEnum.PLAYER_LOGIN);
		if (script != null) {
			try {
				script.onLogin(player, type);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("人物登录脚本不存在！");
		}

		if (PlayerState.QUIT.compare(player.getState())) {
			log.error("Player " + player.getId() + " quit when load map!");
			return;
		}

		player.setSendBagOpenCellTime(false);
		player.setSendStoreOpenCellTime(false);
		//buff初始化
		Iterator<Buff> iter = player.getBuffs().iterator();
		while (iter.hasNext()) {
			//遍历buff
			Buff buff = (Buff) iter.next();
			//非永久有效
			if (buff.getTotalTime()!=-1 && buff.getCount()==1) {
				//buff过期
				if (buff.getStart() + buff.getTotalTime() < System.currentTimeMillis()) {
					buff.remove(player);
					iter.remove();
					continue;
				}
			}
			buff.add(player, player);
		}

		if (PlayerState.RUN.compare(player.getState()) || PlayerState.SWIM.compare(player.getState())) {
			ManagerPool.mapManager.playerStopRun(player);
		}
		
		//清除王城BUFF(上线直接清除，等世界服务器返回具体的帮会和成员信息时再加上)
		CountryAwardManager.getInstance().removeKingCityBuff(player);

		//计算玩家属性
		ManagerPool.playerAttributeManager.initPlayerAttribute(player);

//		//死亡复活
		if (player.getHp() == 0 && !player.isDie()) {
			player.setHp(player.getMaxHp());
			player.setMp(player.getMaxMp());
			player.setSp(player.getMaxSp());
			player.setState(PlayerState.NOTHING);
		}

		ManagerPool.playerManager.savePlayer(player);

		online.put(player.getId(), player);

		playerlog.info("玩家" + player.getUserId() + "角色" + player.getId() + "登陆！-->在线:" + online.size());

		if (player.getLevel() >= Global.SYNC_PLAYER_LEVEL) {
			//syncPlayerAttributes(player);
			//同步玩家排行信息
			syncPlayerOrderInfo(player);
		}

		if (type == 0) {	//如果是登录
			player.setLoginTime(System.currentTimeMillis());//写最后登录时间
//			try {
//				if (!player.isTaskclear()) {
//					TaskManager.getInstance().resetTask(player);
//					player.setTaskclear(true);
//				}
//			} catch (Exception ex) {
//				log.error(ex, ex);
//			}
//			if(player.getLastCheckZeroClockTimer()==0){
			player.setLastCheckZeroClockTimer(System.currentTimeMillis());
//			}
			
			ManagerPool.playerManager.sendMyPlayerDetails(player);
			ManagerPool.backpackManager.sendCanreceiveYuanbaoInfo(player);//发送可领取元宝
			ManagerPool.skillManager.sendSkillInfos(player);
			ManagerPool.buffManager.sendBuffInfos(player);
			ManagerPool.shortCutManager.sendShortcutInfos(player);
			try {
				ManagerPool.petInfoManager.sendPetInfo(player);
			} catch (Exception ex) {
				log.error(ex, ex);
			}

//			ManagerPool.taskManager.sendTaskInfo(player);
			try {
				ManagerPool.taskManager.loginCheckTask(player);
			} catch (Exception ex) {
				log.error(ex, ex);
			}
			ManagerPool.mailServerManager.getNewMailTip(player.getId());
			ManagerPool.longyuanManager.longyuanChangeDate(player, 0);
			ManagerPool.friendManager.loginSendFriendAndGuildConfig(player);
			ManagerPool.friendManager.relationLoginTip(player);//好友上线提示
			ManagerPool.setupMenuManager.stResMenuStatus(player);//发送系统设置菜单
			ManagerPool.plugSetManager.stResPlugSetInfo(player);//发送外挂设置
			//登录后检查是否有默认宝石，如果没有，则生成
			ManagerPool.gemManager.createDefaultGem(player);
			TaskManager.getInstance().loginMonsterInfo(player);
			ManagerPool.activitiesManager.sendActivitiesInfo(player, true);
//			GiftManager.getInstance().loginSendGiftInfos(player);
			player.setState(PlayerState.NOAUTOFIGHT);//清除挂机状态
			ManagerPool.signWageManager.loginRessignnum(player);//发送累计签到次数
			ManagerPool.zonesManager.stReqZoneOpenPanelMessage(player,null);//发送副本信息
			ManagerPool.signWageManager.loginsignwageitem(player);//计算上次在线时间
			if(player.getPanelverify().size() > 0){
				player.getPanelverify().clear();	//登录清除按钮验证内容
			}
			CollectManager.getInstance().sendCollectInfo(player,(byte)0);
			//幸运轮盘上线调用（现在改为上线调用，顶号才能调用到）
			ManagerPool.chestBoxManager.quitChestGridData(player);
		}
		ManagerPool.mapManager.enterMap(player, width, height);
		//攻城战状态，上线通知
		ManagerPool.countryManager.siegeLoginHandle(player);
		//登陆完成，玩家状态改为正常
		player.setState(PlayerState.NORMAL);
		// 通知世界服务器玩家信息
		MessageUtil.send_to_world(getPlayerInfoRegister(player, type));
		
		if (type == 0) {
			RoleLoginLog log = new RoleLoginLog(player);
			LogService.getInstance().execute(log);
		}

		//登录或者跨服加载坐骑信息
		ManagerPool.horseManager.LoginLoadHorse(player);
		//检查并改变套装属性BUFF
		ManagerPool.equipManager.stTaoZhuang(player,0);
		//检查并更改宝石BUFF
		ManagerPool.gemManager.addGemBuff(player);
		//通知前端版本号
		ManagerPool.versionManager.resVersionResInfo(player);

		ManagerPool.activitiesManager.nextLogin(player);

		//发送玩家VIP信息
		ManagerPool.vipManager.sendVipInfoToClient(player);
		
		//发送玩家监狱信息
		ManagerPool.playerManager.sendPlayerPrisonState(player);
		
		//发送最大跳跃速度
		ManagerPool.playerManager.sendJumpMaxSpeed(player);
		
//		ManagerPool.taskManager.sendConquerMonsterSyncInfo(player);

//		PlayerDaZuoManager.getInstacne().breakDaZuo(player);

		//登录的时候死亡复活等待5秒
		if (player.getPlacerevivetime() == 0 && player.isDie()) {
			ShowEffectInfo effcet = new ShowEffectInfo();
			effcet.setId(player.getId());
			effcet.setType((byte) 2);
			effcet.setEffectid(3);	//3表示头顶倒计时5秒
			ResShowEffectToClientMessage cmsg = new ResShowEffectToClientMessage();
			cmsg.setShoweffectinfo(effcet);
			MessageUtil.tell_player_message(player, cmsg);
			player.setDieTime(System.currentTimeMillis()-25*1000);
			ResPlayerDieMessage diemsg = new ResPlayerDieMessage();
			diemsg.setAttackerid(player.getId());
			diemsg.setAttackername("未知");
			diemsg.setPersonId(player.getId());
			diemsg.setType((byte) 1);
			diemsg.setPosition(player.getPosition());
			MessageUtil.tell_player_message(player, diemsg);
		}
		
		//登录发送副本奖励消息
		ManagerPool.zonesFlopManager.LoginRaidRewarded(player);

		ManagerPool.zonesManager.stResZoneSurplusSum(player);//登录发送剩余扫荡次数
		//通知前端是否显示首冲按钮
		RegistrarManager.getInstance().sendFirstRecharged(player);
		//登录通知前端BOSS刷新倒计时
		ManagerPool.monsterManager.loginTrackShow(player);
		//上线通知帮派领地争夺战，剩余时间
		ManagerPool.guildFlagManager.loginGuildFlagStatus(player);
		//发送军衔消息
		ManagerPool.rankManager.sendRankinfo(player);
		//加载弓箭技能
		ManagerPool.arrowManager.addAllArrowSkill(player);
		//发送境界消息
		ManagerPool.realmManager.sendRealmInfo(player);
		//登录处理结婚事件
		ManagerPool.marriageManager.logintrigger(player);
		//上线发送玩家的暗器技能
		ManagerPool.hiddenWeaponManager.sendHiddenWeaponInfo(player);

		
		IPlayerLoginEndScript loginend = (IPlayerLoginEndScript) ManagerPool.scriptManager.getScript(ScriptEnum.PLAYER_LOGIN_END);
		if (loginend != null) {
			try {
				loginend.onLogin(player, type);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("人物登录脚本IPlayerLoginEndScript不存在！");
		}
		
	}

	/**
	 * 死亡
	 */
	public void die(Player player, Fighter attacker) {
		//停止采集
		ManagerPool.npcManager.playerStopGather(player);

		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		player.getRoads().clear();
		// 清除待移动路径
		player.setLastRoads(null);
		//跳跃状态移除
		if (PlayerState.JUMP.compare(player.getState()) || PlayerState.DOUBLEJUMP.compare(player.getState())) {
			ManagerPool.cooldownManager.removeCooldown(player, CooldownTypes.JUMP, null);
			//移除跳跃保护
			player.setJumpProtect(false);
		}
		//移除Buff
		ManagerPool.buffManager.removeOnDieOrQuit(player, true);
		//触发死亡时触发技能
		List<Skill> skills = ManagerPool.skillManager.getSkillTriggerByDie(player);
		for (int i = 0; i < skills.size(); i++) {
			Skill skill = skills.get(i);
			ManagerPool.skillManager.triggerSkill(player, player, skill, false);
		}
		
		//设置死亡状态
		player.setState(PlayerState.DIE);

		//设置死亡时间
		player.setDieTime(System.currentTimeMillis());
		//清除仇恨
		Map map = ManagerPool.mapManager.getMap(player);
		ManagerPool.monsterManager.cleanHatred(map, player);

		IPlayerDieScript script = (IPlayerDieScript) ManagerPool.scriptManager.getScript(ScriptEnum.PLAYER_DIE);
		if (script != null) {
			try {
				script.onPlayerDie(player, attacker);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("人物死亡脚本不存在！");
		}
		//打断交易
		if (player.getTransactionsinfo() != null) {
			ManagerPool.transactionsManager.stTransactionsCanceled(player);
		}

		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(ResManager.getInstance().getString("yyyy年MM月dd日HH时mm分"));
		Player killer = null;
		ResPlayerDieMessage msg = new ResPlayerDieMessage();
		if (attacker instanceof Player) {			//玩家
			Player attackplayer = (Player) attacker;
			killer = attackplayer;
			player.setKillerType(2);
			player.setKiller(attackplayer.getName());
			if (!((Player)attacker).getEnemys().containsKey(player.getId())) {
				if (map.getZoneModelId() != 3005 && map.getMapModelid() != ManagerPool.countryManager.SIEGE_MAPID && map.getMapModelid() != BiWuDaoManager.BIWUDAO_MAPID) {
					//增加大恶人buff
					if (!ManagerPool.guildFlagManager.ckFlagBuff(player)) {
						ManagerPool.buffManager.addBuff(attacker, player, Global.EVIL_BUFF, 0, 0, 0);
					}
				}
			}

			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您被玩家【{1}】击败了，击败时间：{2}"), ((Player) attacker).getName(), format.format(date));
			MessageUtil.notify_player((Player) attacker, Notifys.NORMAL, ResManager.getInstance().getString("您击败了玩家【{1}】，击败时间：{2}"), player.getName(), format.format(date));
			
			log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")被玩家(" + attacker.getId() + ")攻击导致死亡！");
			
			if (map.getMapModelid() != BiWuDaoManager.BIWUDAO_MAPID && map.getZoneModelId() != 3005) {
				relationInnerAdd(player ,attacker);
			}

			GuildServerManager.getInstance().reqInnerGuildNotifyToWorld(player, 0, GuildServerManager.getParserString(GuildServerManager.getEventString(ResManager.getInstance().getString("成员被其他玩家击败")), new ParseUtil.PlayerParm(player.getId(), player.getName()), new ParseUtil.MapParm(player.getMapModelId(), player.getLine(), player.getPosition().getX(), player.getPosition().getY()), new ParseUtil.PlayerParm(attackplayer.getId(), attackplayer.getName())));
			GuildServerManager.getInstance().reqInnerGuildNotifyToWorld(attackplayer, 1, GuildServerManager.getParserString(GuildServerManager.getEventString(ResManager.getInstance().getString("成员击败其他玩家")), new ParseUtil.PlayerParm(attackplayer.getId(), attackplayer.getName()), new ParseUtil.MapParm(player.getMapModelId(), player.getLine(), player.getPosition().getX(), player.getPosition().getY()), new ParseUtil.PlayerParm(player.getId(), player.getName())));
			if (player.getTeamid() > 0) {
				ManagerPool.teamManager.TeammateDieNotify(player, (Player) attacker);
			}
			msg.setAttackerid(attackplayer.getId());
			msg.setAttackername(attackplayer.getName());
			
			
			Pet showPet = PetInfoManager.getInstance().getShowPet(attackplayer);
			if (showPet != null) {
				PetScriptManager.getInstance().playerKillTarget(attackplayer, player);
			}			
		} else if (attacker instanceof Monster) {		//怪物
			Monster attackMon = (Monster) attacker;
			player.setKillerType(1);
			Q_monsterBean monsterModel = ManagerPool.dataManager.q_monsterContainer.getMap().get(attackMon.getModelId());
			player.setKiller(monsterModel.getQ_name());
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您被怪物【{1}】击败了，击败时间：{2}"), monsterModel.getQ_name(), format.format(date));
			
			log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")被怪物(" + attacker.getId() + ")攻击导致死亡！");
			if (MonsterManager.getInstance().isBoss(attackMon.getModelId())) {
				GuildServerManager.getInstance().reqInnerGuildNotifyToWorld(player, 3, GuildServerManager.getParserString(GuildServerManager.getEventString(ResManager.getInstance().getString("成员被BOSS击败"), monsterModel.getQ_name()), new ParseUtil.PlayerParm(player.getId(), player.getName()), new ParseUtil.MapParm(player.getMapModelId(), player.getLine(), player.getPosition().getX(), player.getPosition().getY())));
			}
			msg.setMonstermodelid(attackMon.getModelId());
		} else if (attacker instanceof Pet) {			//美人
			Pet attackPet = (Pet) attacker;
			long attackplayerid = attackPet.getOwnerId();
			Player attackplayer = ManagerPool.playerManager.getPlayer(attackplayerid);
			killer = attackplayer;
			if (attackplayer != null) {
				player.setKillerType(2);
				player.setKiller(attackplayer.getName());
				if (!attackplayer.getEnemys().containsKey(player.getId())) {
					if (map.getZoneModelId() != 3005 && map.getMapModelid() != ManagerPool.countryManager.SIEGE_MAPID && map.getMapModelid() != BiWuDaoManager.BIWUDAO_MAPID) {
						//增加大恶人buff
						if (!ManagerPool.guildFlagManager.ckFlagBuff(player)) {
							ManagerPool.buffManager.addBuff(attackplayer, player, Global.EVIL_BUFF, 0, 0, 0);
						}
					}
				}

				
				msg.setAttackername(attackplayer.getName());
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您被玩家【{1}】击败了，击败时间：{2}"), attackplayer.getName(), format.format(date));
				MessageUtil.notify_player(attackplayer, Notifys.NORMAL, ResManager.getInstance().getString("您击败了玩家【{1}】，击败时间：{2}"), player.getName(), format.format(date));
				
				log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")被玩家(" + attackplayer.getId() + ")的宠物攻击导致死亡！");
				
				if (map.getMapModelid() != BiWuDaoManager.BIWUDAO_MAPID && map.getZoneModelId() != 3005) {
					relationInnerAdd(player ,attackplayer);
				}
				GuildServerManager.getInstance().reqInnerGuildNotifyToWorld(player, 0, GuildServerManager.getParserString(GuildServerManager.getEventString(ResManager.getInstance().getString("成员被其他玩家击败")), new ParseUtil.PlayerParm(player.getId(), player.getName()), new ParseUtil.MapParm(player.getMapModelId(), player.getLine(), player.getPosition().getX(), player.getPosition().getY()), new ParseUtil.PlayerParm(attackplayer.getId(), attackplayer.getName())));
				GuildServerManager.getInstance().reqInnerGuildNotifyToWorld(attackplayer, 1, GuildServerManager.getParserString(GuildServerManager.getEventString(ResManager.getInstance().getString("成员击败其他玩家")), new ParseUtil.PlayerParm(attackplayer.getId(), attackplayer.getName()), new ParseUtil.MapParm(player.getMapModelId(), player.getLine(), player.getPosition().getX(), player.getPosition().getY()), new ParseUtil.PlayerParm(player.getId(), player.getName())));
				if (player.getTeamid() > 0) {
					ManagerPool.teamManager.TeammateDieNotify(player, attackplayer);
				}
			}
			msg.setMonstermodelid(attackPet.getModelId());
			msg.setAttackerid(attackplayerid);
			PetScriptManager.getInstance().petKillTarget(attackPet, player);
		}
		
		if(map.getMapModelid() == ManagerPool.countryManager.SIEGE_MAPID && ManagerPool.countryManager.getSiegestate() == 1){
			//死亡检测是否在攻城战抢夺玉玺
			if (player.getId() == ManagerPool.countryManager.getKingcity().getHoldplayerid()) {
				ManagerPool.countryManager.SiegeHomingYuXi(player);
				if (attacker instanceof Player) {
					MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("帮主{1}被{2}玩家击败，秦王玉玺回归到了王座上"), player.getName(), player.getKiller());
				} else {
					MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("帮主{1}死亡，秦王玉玺回归到了王座上"), player.getName());
				}
			}
			ShowEffectInfo effcet = new ShowEffectInfo();
			effcet.setId(player.getId());
			effcet.setType((byte) 2);
			effcet.setEffectid(3);	//3表示头顶倒计时5秒
			ResShowEffectToClientMessage cmsg = new ResShowEffectToClientMessage();
			cmsg.setShoweffectinfo(effcet);
			MessageUtil.tell_round_message(player, cmsg);
			player.setDieTime(System.currentTimeMillis()-25*1000);	//5秒复活
			msg.setType((byte) 1);
			if (attacker instanceof Player) {
				if (((Player) attacker).getGuildId() != player.getGuildId()) {//军衔任务加次数
					ManagerPool.taskManager.action((Player)attacker, Task.ACTION_TYPE_RANK, RankTaskEnum.KINGKILLNOSAMEGUILDPLAYER, 1);
				}
				ManagerPool.countryManager.changeSiegeSMSTopData((Player) attacker, CountryManager.KILL, 1);
				ManagerPool.countryManager.changeSiegeSMSTopData(player, CountryManager.DEATH, 1);
			}else if (attacker instanceof Pet) {
				Player petplayer = ManagerPool.petInfoManager.getPetHost((Pet)attacker);
				if (petplayer != null && petplayer.getGuildId() != player.getGuildId()) {//军衔任务加次数
					ManagerPool.taskManager.action(petplayer, Task.ACTION_TYPE_RANK, RankTaskEnum.KINGKILLNOSAMEGUILDPLAYER, 1);
				}
				ManagerPool.countryManager.changeSiegeSMSTopData(petplayer, CountryManager.KILL, 1);
				ManagerPool.countryManager.changeSiegeSMSTopData(player, CountryManager.DEATH, 1);
			}
			
		//在特定副本死亡后，5秒复活  (水淹大梁)
		}else if (map.isCopy() && map.getZoneModelId() == 3005) {
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			if (zone != null) {
				ShowEffectInfo effcet = new ShowEffectInfo();
				effcet.setId(player.getId());
				effcet.setType((byte) 2);
				effcet.setEffectid(3);	//3表示头顶倒计时5秒
				ResShowEffectToClientMessage cmsg = new ResShowEffectToClientMessage();
				cmsg.setShoweffectinfo(effcet);
				MessageUtil.tell_round_message(player, cmsg);
				player.setDieTime(System.currentTimeMillis()-25*1000);	//5秒复活
				msg.setType((byte) 1);
			}
		}else {
			//检查是否允许原地复活
			Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
			if (mapBean != null && mapBean.getQ_rose_resurrection() == 1) {
				msg.setType((byte) 2);	//不允许原地复活
			}else {
				//如果地图属性允许自动复活，则先检测是否有 夫妻技能  守护  这个BUFF
				List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, 9168);
				if (!bufflist.isEmpty()) {
					player.setPlacerevivetime(System.currentTimeMillis()-4*1000);;	//1秒复活
					msg.setType((byte) 1);	//不显示复活面板
				}
			}
		}
		
		//清除反击列表
		player.getEnemys().clear();
		
		log.error("玩家(" + player.getId() + ")敌人列表为" + MessageUtil.castListToString(player.getEnemys().values()));
		
		if(killer!=null){
			log.error("敌对玩家(" + killer.getId() + ")敌人列表为" + MessageUtil.castListToString(killer.getEnemys().values()));
		}
				
		//回收宠物
		for (int i = 0; i < player.getPetList().size(); i++) {
			Pet pet = player.getPetList().get(i);
			if(pet.isShow() && !pet.isDie()){
				log.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[die force hide]");
				ManagerPool.petOptManager.forceHidePet(pet);
			}
		}

		//广播死亡消息
		msg.setTime((int) (System.currentTimeMillis() / 1000));		//死亡时间
		msg.setPersonId(player.getId());
		//自动复活判断
		if (FighterState.MIANFEIFUHUO.compare(player.getFightState())) {
			//设置玩家自动复活
			player.setState(PlayerState.AUTOREVIVE);
			//移除自动复活buff
			ManagerPool.buffManager.removeByType(player, BuffType.FREERELIVE);
			msg.setAutoRevive((byte) 1);
			msg.setPosition(player.getPosition());
		} else {
			msg.setAutoRevive((byte) 0);
			msg.setPosition(player.getPosition());
		}

		MessageUtil.tell_round_message(player, msg);
		ManagerPool.zonesManager.zoneplayerdie(player);//副本内死亡记录次数
	}

	/**自动添加仇人
	 * 
	 * @param player
	 * @param attacker
	 */
	public void relationInnerAdd(Player player , Fighter attacker){
		ManagerPool.friendManager.RelationInnerAdd(player, (byte) 2, attacker.getId(), "");//自动添加仇人
		ManagerPool.friendManager.relationKillTip(player, (Player) attacker);//好友仇人杀人提示
		ManagerPool.friendManager.relationBackKillTip(player, (Player) attacker);//好友仇人被杀提示
	}
	
	
	
	
	/**
	 * 原地复活倒计时
	 *
	 */
	public void reviveReady(Player player, int type, int itemid,byte usetype) {

		if (player.getPlacerevivetime() + 1000 * 5 > System.currentTimeMillis()) {
			return;
		}

		if (!PlayerState.DIE.compare(player.getState())) {
			return;
		}
		
		//检查是否允许原地复活
		Map map = ManagerPool.mapManager.getMap(player);	//死亡时所在地图
		if (map != null) {
			Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
			if (mapBean != null && mapBean.getQ_rose_resurrection() == 1) {
				return;
			}
		}

		
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(32);
		boolean isReady = false;
		if (global != null && player.getLevel() > global.getQ_int_value()) {	//系统设定大于指定等级复活需要收取道具
			Reasons usereason = Reasons.REVIVE;
			if (usetype == 0) {	//0自动使用玫瑰
				usereason = Reasons.REVIVE_AUTO;
			}
			if (itemid == 0) {			//按照顺序自动找设定的复活道具
				int[] itemmodel = {1100, 1101, 1102, 1103, 1104, 1105};
				for (int i : itemmodel) {
					if (ManagerPool.backpackManager.removeItem(player, i, 1, usereason, Config.getId())) {
						log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")使用玫瑰(类型：" + usetype + ")自动复活");
						Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(i);
						isReady = true;
						MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您使用{1}，5秒后原地复活。"), BackpackManager.getInstance().getName(itemBean.getQ_id()));
						ResUseItemSuccessMessage itemmsg = new ResUseItemSuccessMessage();
						itemmsg.setItemId(i);
						MessageUtil.tell_player_message(player, itemmsg);
						break;
					}
				}
				if (isReady == false) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有复活用的道具，无法原地复活。"));
				}
			} else {
				Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(itemid);
				if (ManagerPool.backpackManager.removeItem(player, itemid, 1, usereason, Config.getId())) {
					isReady = true;
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您使用{1}，5秒后原地复活。"), BackpackManager.getInstance().getName(itemBean.getQ_id()));
					ResUseItemSuccessMessage itemmsg = new ResUseItemSuccessMessage();
					itemmsg.setItemId(itemid);
					MessageUtil.tell_player_message(player, itemmsg);
				} else {
					MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您没有{1}，无法原地复活。"), BackpackManager.getInstance().getName(itemBean.getQ_id()));
					return;
				}
			}
		} else {
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("请等待5秒后原地复活。"));
			isReady = true;
		}

		if (isReady) {
			ShowEffectInfo effcet = new ShowEffectInfo();
			effcet.setId(player.getId());
			effcet.setType((byte) 2);
			effcet.setEffectid(3);	//3表示头顶倒计时5秒
			player.setState(PlayerState.REVIVESTART);
			ResShowEffectToClientMessage cmsg = new ResShowEffectToClientMessage();
			cmsg.setShoweffectinfo(effcet);
			MessageUtil.tell_round_message(player, cmsg);
			player.setPlacerevivetime(System.currentTimeMillis());

//			CommonDelayTimerUtil.addDelayTimer(5, new Runnable() {		//延迟5秒
//				@Override
//				public void run() {
//					revive(player, type);
//				}
//			});

		}
	}

	/**设置死亡自动复活
	 * 
	 * @param player
	 */
	public void autoRevive(Player player) {
		if (player.isDie()) {	//死亡自动复活
			player.setHp(player.getMaxHp());
			player.setMp(player.getMaxMp());
			player.setSp(player.getMaxSp());
			player.setState(PlayerState.NOTHING);
			ResReviveSuccessMessage vmsg = new ResReviveSuccessMessage();
			vmsg.setPersonId(player.getId());
			MessageUtil.tell_round_message(player, vmsg);
			ManagerPool.playerManager.onHpChange(player);
			ManagerPool.playerManager.onMpChange(player);
			ManagerPool.playerManager.onSpChange(player);
			log.error("玩家：" + player.getName() + "(" + player.getId() + ")回复生命,因为自动复活");
		}
	}
	
	/**
	 * 复活流程
	 *
	 * @param roleId 复活Id
	 * @param type 复活方式 1-原地复活 2-回城复活 3-自动复活
	 */
	public void revive(Player player, int type) {
		if (ManagerPool.playerManager.getOnLinePlayer(player.getId()) == null) {//中途下线，停止操作
			return;
		}

		if (!PlayerState.DIE.compare(player.getState())) {
			return;
		}

		player.setPlacerevivetime(0);	//清除原地复活开始时间

		boolean isrevive = false;
		player.setState(PlayerState.REVIVEEND);

		Map map = ManagerPool.mapManager.getMap(player);	//死亡时所在地图
		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());

		if (type == 1) {	//原地复活 
			isrevive = true;
		} else if (type == 2) {	//回城复活
			isrevive = true;
		} else if (type == 3) {	//自动复活
			isrevive = true;
		}

		if (isrevive) {
//			//如果死的时候是骑乘状态，那么在复活之前下马，为了复活后自动骑乘。
//			byte horsestatus = 0;
//			Horse horse = ManagerPool.horseManager.getHorse(player);
//			if (horse != null && horse.getLayer() > 0) {
//				if (horse.getStatus() == 1) {
//					ManagerPool.horseManager.unride(player);//自动下马
//					horsestatus = 1;
//				}
//			}
			player.setState(PlayerState.NOTHING); //清除状态
			
			//-------------------------------守护技能效果， 排除攻城战地图和水淹大梁----------------
			boolean isshouhu = true;
			if ((map.isCopy() && map.getZoneModelId() == 3005 )|| (map.getMapModelid() == ManagerPool.countryManager.SIEGE_MAPID)){
				isshouhu = false;
			}
			if (mapBean.getQ_rose_resurrection() == 1) {//是否可用玫瑰复活（0是，1否）
				isshouhu = false;
			}
			
			List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, 9168);
			if (isshouhu && !bufflist.isEmpty()) {
				ManagerPool.buffManager.removeByBuffId(player, 9168);
				ManagerPool.buffManager.addBuff(player, player, 20116, 0, 0, 0);	//复活保护
				if (player.getMaxHp() > 1000) {
					player.setHp(1000);
				}else {
					player.setHp(player.getMaxHp());
				}
			}else {
				player.setHp(player.getMaxHp());
			}
			//----------------------------------------------------
			player.setMp(player.getMaxMp());
			player.setSp(player.getMaxSp());
			ResReviveSuccessMessage msg = new ResReviveSuccessMessage();
			msg.setPersonId(player.getId());
			MessageUtil.tell_round_message(player, msg);

			ManagerPool.playerManager.onHpChange(player);
			ManagerPool.playerManager.onMpChange(player);
			ManagerPool.playerManager.onSpChange(player);
			
			log.error("玩家：" + player.getName() + "(" + player.getId() + ")回复生命,因为复活");

			Pet showPet = PetInfoManager.getInstance().getShowPet(player);
			if(player.getPetList().size()>0){
				if(showPet!=null){
					for (Pet pet : player.getPetList()) {
						pet.setForceHide(false);
					}
				}else{
					boolean flag = true;
					for (Pet pet : player.getPetList()) {
						if(pet.isForceHide()){
							if (flag) {
								// 重新出战的美人处于满血状态
								pet.resetPet();
								pet.setHp(pet.getMaxHp());
								log.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[player revive]");
								MapManager.getInstance().enterMap(pet);
								ResPetShowMessage showmsg=new ResPetShowMessage();
								showmsg.setPet(PetInfoManager.getInstance().getDetailInfo(pet));
								MessageUtil.tell_player_message(player, showmsg);
								PlayerDaZuoManager.getInstacne().petShow(player);
								flag = false;
							}
							else {
								log.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[fatal 2 girl revive]");
							}
							pet.setForceHide(false);
						}
					}	
				}
			}

			if ( map.getMapModelid() == ManagerPool.countryManager.SIEGE_MAPID && ManagerPool.countryManager.getSiegestate() == 1) {
				//攻城战期间。在攻城战地图死亡
				//player.setAutohorse((byte) 1);
				ManagerPool.countryManager.SiegeMoveMap(player);
				
				
				//在特定副本死亡后，5秒复活  (水淹大梁)
			}else if (map.isCopy() && map.getZoneModelId() == 3005) {
				ManagerPool.buffManager.addBuff(player, player, 20116, 0, 0, 0);	//复活保护
				
			} else {
				if (type == 2) {//回城复活
					//自动挂机设为false
					endAuto(player);
					
					//player.setAutohorse((byte) 1);
					Position position = ManagerPool.mapManager.RandomDieBackCity(mapBean);
					int mapid = mapBean.getQ_map_die();
					if (position == null) {
						position = player.getFormerposition();
						mapid = player.getFormermapid();
					}else {
						List<Grid> gridlist = MapUtils.getRoundNoBlockGrid(position,10*MapUtils.GRID_BORDER , mapid);
						int rnd = RandomUtils.random(1, gridlist.size())-1;
						position = gridlist.get(rnd).getCenter();
					}
					//10格范围内随机地点
					ManagerPool.mapManager.changeMap(player, mapid,mapid, 0,position, this.getClass().getName() + ".revive");
				}
				
				
				//按照地图属性  复活给予pk保护buff
				if (mapBean.getQ_map_buff() == 0 && player.getKillerType() == 2) {
					if (!ManagerPool.guildFlagManager.ckFlagBuff(player)) {
						ManagerPool.buffManager.addBuff(player, player, Global.PROTECT_FOR_KILLED, 0, 0, 0);
						log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")获得和平保护BUFF");
					}else {
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您参与了帮旗争夺战，期间将不受复活保护。"));
					}
				}
				
				//挂机状态下，按照地图属性设置为和平模式
				if (PlayerState.AUTOFIGHT.compare(player.getState())) {
					if (mapBean.getQ_map_buff() == 0) {
						ManagerPool.playerManager.changePkState(player, 0, 0);
					}
				}
			}

			
			
			

//			//如果死的时候是骑马，复活后自动骑乘
//			if (horsestatus == 1 && type != 2) {
//				ManagerPool.horseManager.stChangeRidingState(player, (byte) 1, horse.getCurlayer());
//			}
			
		}
		
	}

	
	
	
	
	/**
	 * 退出流程
	 *
	 * @param roleId
	 */
	public void quiting(Player player, byte force) {
		//判断是否可以退出游戏
		if (PlayerState.FIGHT.compare(player.getState())) {
			if (force == 1) {
				player.setState(PlayerState.QUITING);
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("尚未脱离战斗状态，请{1}秒后再试"), String.valueOf((int) (Global.FIGHT_OVERDUE / 1000)));
			}
		} //跳跃中延迟退出游戏
		else if (PlayerState.JUMP.compare(player.getState()) || PlayerState.DOUBLEJUMP.compare(player.getState())) {
			player.setState(PlayerState.QUITING);
		} else {
			quit(player);
		}
	}

	/**
	 * 退出流程
	 *
	 * @param roleId
	 */
	public void quit(Player player) {
		ManagerPool.npcManager.playerStopGather(player);
		//打断打坐
		PlayerDaZuoManager.getInstacne().breakDaZuo(player);
		//自动挂机设为false
		endAuto(player);
		//移除Buff
		ManagerPool.buffManager.removeOnDieOrQuit(player, false);

		//攻城战离线
		if (player.getId() == ManagerPool.countryManager.getKingcity().getHoldplayerid() &&  ManagerPool.countryManager.getSiegestate() == 1 ) {
			ManagerPool.countryManager.SiegeHomingYuXi(player);
			MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("帮主{1}离线，秦王玉玺回归到了王座上"), player.getName());
		}

		//退出地图
		ManagerPool.mapManager.quitMap(player);
	
		//清理交易数据
		if (player.getTransactionsinfo() != null) {
			ManagerPool.transactionsManager.stTransactionsCanceled(player);
		}
		//去掉组队打架模式
		if (player.getPkState() == 1) {
			ManagerPool.playerManager.changePkState(player, 3, 0);
		}
		//龙元心法累计加经验用
		ManagerPool.longyuanManager.longyuanChangeDate(player, 1);
		//执行角色队列中的延时任务 
		LinkedList<LaterTask> laterList = player.getLaterList();
		for (LaterTask laterTask : laterList) {
			try {
				laterTask.getRun().run();
			} catch (Exception ex) {
				log.error(ex, ex);
			}
		}

		player.setLongyuantime(0);	//龙元心法计时时间清理
		player.setState(PlayerState.LONGYUANEND);	//龙元心法结束
		//立即完成格子奖励
		ManagerPool.epalaceManeger.setimmediatelyComplete(player);

		player.getLaterList().clear();
//		//下线换地图
//		Map map = ManagerPool.mapManager.getMap(player.getServerId(), player.getLine(), player.getMap());
//		if(map==null && !PlayerState.CHANGEMAP.compare(player.getState())) return;

		RoleLoginOutLog logoutlog = new RoleLoginOutLog(player);
		LogService.getInstance().execute(logoutlog);
		boolean change = false;
		Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
		if (mapBean != null && mapBean.getQ_map_quit() != 0) {
			Grid[][] grids = ManagerPool.mapManager.getMapBlocks(mapBean.getQ_map_quit());
			Grid grid = MapUtils.getGrid(mapBean.getQ_map_quit_x(), mapBean.getQ_map_quit_y(), grids);

			player.setMap(mapBean.getQ_map_quit());
			player.setMapModelId(mapBean.getQ_map_quit());
			player.setPosition(grid.getCenter());

			mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
			// 切换服务器
			if (mapBean.getQ_map_public() == 1) {
				if (player.getLocate() != ServerType.PUBLIC.getValue()) {
					// 新地图是公共地图
					player.setLocate(ServerType.PUBLIC.getValue());
					change = true;
				}
			} else if (mapBean.getQ_map_public() == 0) {
				if (player.getLocate() != player.getCountry()) {
					// 新地图是普通地图
					player.setLocate(player.getCountry());
					change = true;
				}
			}
		}
		player.setLine(0);
		log.debug("玩家" + player.getId() + "退出重置选线为" + player.getLine());

		player.setState(PlayerState.QUIT);

		online.remove(player.getId());
		playerlog.info("玩家" + player.getUserId() + "角色" + player.getId() + "退出！-->在线:" + online.size());
		//onlineUser.remove(player.getUserId());

		ResRemoveCharacterToGateMessage gatemsg = new ResRemoveCharacterToGateMessage();
		gatemsg.setPlayerId(player.getId());
		MessageUtil.send_to_gate(player.getGateId(), player.getId(), gatemsg);

		ResRemoveCharacterToWorldMessage worldmsg = new ResRemoveCharacterToWorldMessage();
		worldmsg.setPlayerId(player.getId());
		MessageUtil.send_to_world(worldmsg);

		//好友下线提示（放在世界服务器下线消息后面）
		ManagerPool.friendManager.relationQuitTip(player);
		//配偶离线通知
		ManagerPool.marriageManager.offlineMarriage(player);
		updatePlayer(player);

		if (change) {
			removePlayer(player);
		}
		
		if(player.isCross()){
			DataServerManager.getInstance().reqPlayerQuitToDataServer(player);
			removePlayer(player);
		}
		log.info(player.getId() + " quited!");
	}

	public MemoryCache<Long, Player> getPlayers() {
		return players;
	}

	public void savePlayer(Player player) {
		player.setVersion(WServer.VERSION);
		players.put(player.getId(), player);
		UpdateLogService.getInstance().updateRoleDate(player.getId());
	}

	/**
	 * 发送本人详细信息
	 */
	public void sendMyPlayerDetails(Player player) {
		//获得玩家
		MessageUtil.tell_player_message(player, getMyPlayerInfo(player));
		//发送头像信息
		ResPlayerAvatarMessage ram = new ResPlayerAvatarMessage();
		ram.setAvatarid(player.getAvatarid());
		ram.setPlayerid(player.getId());
		MessageUtil.tell_player_message(player, ram);
	}

	/**
	 * 发送他人详细信息
	 */
	public void sendPlayerDetails(Player player, long otherId, byte type) {
		//获得玩家
		Player other = ManagerPool.playerManager.getPlayer(otherId);
		if (other == null) {
			ReqOtherPlayerInfoToWorldMessage msg = new ReqOtherPlayerInfoToWorldMessage();
			msg.setPlayerId(player.getId());
			msg.setOtherPlayerId(otherId);
			MessageUtil.send_to_world(msg);
			return;
		}
		if (type == 0) {
			ResOtherPlayerInfoMessage othermsg=new ResOtherPlayerInfoMessage();
			othermsg.setOtherPlayerInfo(getOtherPlayerInfo(other));
			MessageUtil.tell_player_message(player, othermsg);
			//两名玩家在一个屏内（32格）,则通知被查看玩家			
			if (player.getServerId() == other.getServerId() && player.getMap() == other.getMap() && player.getLine() == other.getLine()
				&& MapUtils.countDistance(player.getPosition(), other.getPosition()) <= MapUtils.GRID_BORDER * 32) {
				MessageUtil.notify_player(other, Notifys.NORMAL, ResManager.getInstance().getString("附近有位{1}正在上下打量你"), player.getSex() == 1 ? ResManager.getInstance().getString("侠士") : ResManager.getInstance().getString("侠女"));
			}
		} else if (type == 1) {
			MessageUtil.tell_player_message(player, ManagerPool.horseManager.getOthersHorseInfo(other));
		}


	}

	/**
	 * 血量变化
	 *
	 * @param player
	 */
	public void onHpChange(Player player) {
		ResPlayerHpChangeMessage msg = new ResPlayerHpChangeMessage();
		msg.setPersonId(player.getId());
		msg.setCurrentHp(player.getHp());
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 最大血量变化
	 *
	 * @param player
	 */
	public void onMaxHpChange(Player player) {
		if (player.getHp() > player.getMaxHp()) {
			player.setHp(player.getMaxHp());
		}

		ResPlayerMaxHpChangeMessage msg = new ResPlayerMaxHpChangeMessage();
		msg.setPersonId(player.getId());
		msg.setCurrentHp(player.getHp());
		msg.setMaxHp(player.getMaxHp());
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 内力变化
	 *
	 * @param player
	 */
	public void onMpChange(Player player) {
		ResPlayerMpChangeMessage msg = new ResPlayerMpChangeMessage();
		msg.setPersonId(player.getId());
		msg.setCurrentMp(player.getMp());
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 最大内力变化
	 *
	 * @param player
	 */
	public void onMaxMpChange(Player player) {
		if (player.getMp() > player.getMaxMp()) {
			player.setMp(player.getMaxMp());
		}

		ResPlayerMaxMpChangeMessage msg = new ResPlayerMaxMpChangeMessage();
		msg.setPersonId(player.getId());
		msg.setCurrentMp(player.getMp());
		msg.setMaxMp(player.getMaxMp());
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 体力变化
	 *
	 * @param player
	 */
	public void onSpChange(Player player) {
		ResPlayerSpChangeMessage msg = new ResPlayerSpChangeMessage();
		msg.setPersonId(player.getId());
		msg.setCurrentSp(player.getSp());
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 最大体力变化
	 *
	 * @param player
	 */
	public void onMaxSpChange(Player player) {
		if (player.getSp() > player.getMaxSp()) {
			player.setSp(player.getMaxSp());
		}

		ResPlayerMaxSpChangeMessage msg = new ResPlayerMaxSpChangeMessage();
		msg.setPersonId(player.getId());
		msg.setCurrentSp(player.getSp());
		msg.setMaxSp(player.getMaxSp());
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 速度变化
	 *
	 * @param player
	 */
	public void onSpeedChange(Player player) {
		ResPlayerSpeedChangeMessage msg = new ResPlayerSpeedChangeMessage();
		msg.setPersonId(player.getId());
		msg.setSpeed(player.getSpeed());
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 玩家增加经验
	 *
	 * @param roleId
	 * @param exp
	 */
	public void addExp(Player player, long exp,AttributeChangeReason reason) {
		// 达到顶级后不再增加经验
		if (PlayerManager.getInstance().isTopLevel(player)) {
			return;
		}
		if (exp > 0 && CHECK_NONAGE) {
			if (player.getNonage() == 2) {
				exp = (long) Math.ceil((double)exp / 2);
			} else if (player.getNonage() == 3) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得经验{1}"), "0");
				//发送经验消息
				ResPlayerExpChangeMessage msg = new ResPlayerExpChangeMessage();
				msg.setCurrentExp(player.getExp());
				MessageUtil.tell_player_message(player, msg);
				return;
			}
		}
		
		long beforeexp = player.getExp();
		int beforelevel=player.getLevel();
		
		
		//增加经验
		player.setExp(player.getExp() + exp);
		//经验不为负
		if (player.getExp() < 0) {
			player.setExp(0l);
		}		
		boolean levelUp = false;
		while (true) {
			if (player.getLevel() >= Global.MAX_LEVEL) {
				break;
			}
			Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());

			if (model == null) {
				break;
			}
			if (player.getExp() >= model.getQ_exp()) {
				//升级
				player.setExp(player.getExp() - model.getQ_exp());
				setLevel(player, player.getLevel() + 1, false, Reasons.LEVELUPADDEXP);
				levelUp = true;
			} else {
				break;
			}
		}
		if (PlayerManager.getInstance().isTopLevel(player)) {
			player.setExp(0);
		}
		savePlayer(player);

		try{
			List<AttributeChangeReason> nologlist=new ArrayList<AttributeChangeReason>();
			nologlist.add(AttributeChangeReason.KILLMONSTER);
			nologlist.add(AttributeChangeReason.DAZUO);
			nologlist.add(AttributeChangeReason.SKILLLEVELUP);
			nologlist.add(AttributeChangeReason.SPIRITTREEFRUIT);
			nologlist.add(AttributeChangeReason.COUNTRY);
			nologlist.add(AttributeChangeReason.COUNTRY_BOX);
			nologlist.add(AttributeChangeReason.EXP_CASK);
			if(!nologlist.contains(reason)){
				RoleExpChanageLog log=new RoleExpChanageLog();
				log.setAfternum(player.getExp());
				log.setAfterlevel(player.getLevel());
				log.setBeforenum(beforeexp);
				log.setBeforelevel(beforelevel);
				log.setChangenum(exp);
				log.setReason(reason.toString());
				log.setRoleid(player.getId());
				log.setSid(player.getCreateServerId());
				log.setUserid(player.getUserId());
				log.setUsername(player.getUserName());	
				LogService.getInstance().execute(log);	
			}
		}catch (Exception e) {
			log.error(e,e);
		}
		if (levelUp) {
			levelUpSyn(player);
		}

		if (reason == AttributeChangeReason.KILLMONSTER && VipManager.getInstance().getPlayerVipId(player) == 0) {
			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("经验%d（{@}+经验0）"), exp), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player), 1));
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, parseUtil.toString());
		} else {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得经验{1}"), exp + "");
		}
		//MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "您获得经验{1}", exp + "");
		//发送经验消息
		ResPlayerExpChangeMessage msg = new ResPlayerExpChangeMessage();
		msg.setCurrentExp(player.getExp());
		MessageUtil.tell_player_message(player, msg);
	}

	
	/**等级提升后同步
	 * 
	 * @param player
	 */
	public void levelUpSyn(Player player){
		//重新计算属性
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.BASE);

		player.setHp(player.getMaxHp());
		onHpChange(player);
		player.setMp(player.getMaxMp());
		onMpChange(player);
		player.setSp(player.getMaxSp());
		onSpChange(player);
		
		log.error("玩家：" + player.getName() + "(" + player.getId() + ")回复生命,因为升级");

		//升级消息
		ResPlayerLevelUpMessage msg = new ResPlayerLevelUpMessage();
		msg.setPersonId(player.getId());
		msg.setCurrentLevel(player.getLevel());
		//广播升级消息
		MessageUtil.tell_round_message(player, msg);

		ReqSyncPlayerLevelMessage syncmsg = new ReqSyncPlayerLevelMessage();
		syncmsg.setPlayerId(player.getId());
		syncmsg.setLevel(player.getLevel());
		syncmsg.setLevelUpTime(player.getLevelUpTime());
		MessageUtil.send_to_world(syncmsg);
		//设置坐骑等级
		ManagerPool.horseManager.sethorselevel(player, true);
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE);
		if (player.getLevel() == 65) {
			ManagerPool.realmManager.sendRealmInfo(player);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 玩家增加真气
	 *
	 * @param roleId
	 * @param zhenqi
	 * @param action 
	 */
	public int addZhenqi(Player player, int zhenqi,AttributeChangeReason reason) {
		if (zhenqi > 0 && CHECK_NONAGE) {
			if (player.getNonage() == 2) {
				zhenqi = zhenqi / 2;
			} else if (player.getNonage() == 3) {
				//发送真气消息
				ResPlayerZhenqiChangeMessage msg = new ResPlayerZhenqiChangeMessage();
				msg.setCurrentZhenqi(player.getZhenqi());
				MessageUtil.tell_player_message(player, msg);
				return 0;
			}
		}
		int add = zhenqi;
		int before=player.getZhenqi();
		int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
		if (zhenqi > 0) {	//加真气的时候检测上限
			if (player.getZhenqi() > max) {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("获得真气已达上限"));
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("获得真气已达上限"));
				return 0;
			}
		}

		zhenqi = player.getZhenqi() + zhenqi;

		if (zhenqi > max) {
			add = max - player.getZhenqi();
			player.setZhenqi(max);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("获得真气已达上限"));
		} else {
//			add=zhenqi;
			player.setZhenqi(zhenqi);
		}
		if (add > 0) {
			TaskManager.getInstance().action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.GETZHENQI, add);
		}
		//经验不为负
		if (player.getZhenqi() < 0) {
			player.setZhenqi(0);
		}
		try{
			List<AttributeChangeReason> nologlist=new ArrayList<AttributeChangeReason>();
			nologlist.add(AttributeChangeReason.KILLMONSTER);
			nologlist.add(AttributeChangeReason.DAZUO);
			nologlist.add(AttributeChangeReason.SKILLLEVELUP);
			nologlist.add(AttributeChangeReason.SPIRITTREEFRUIT);
			nologlist.add(AttributeChangeReason.COUNTRY);
			nologlist.add(AttributeChangeReason.COUNTRY_BOX);
			nologlist.add(AttributeChangeReason.ZHENQI_CASK);
			if(!nologlist.contains(reason)){
				RoleZhenQiLog log=new RoleZhenQiLog();
				log.setAfternum(player.getZhenqi());
				log.setBeforenum(before);
				log.setChangenum(add);
				log.setReason(reason.toString());
				log.setRoleid(player.getId());
				log.setSid(player.getCreateServerId());
				log.setUserid(player.getUserId());
				log.setUsername(player.getUserName());
				LogService.getInstance().execute(log);				
			}
		}catch (Exception e) {
			log.error(e,e);
		}
		//发送真气消息
		ResPlayerZhenqiChangeMessage msg = new ResPlayerZhenqiChangeMessage();
		msg.setCurrentZhenqi(player.getZhenqi());
		MessageUtil.tell_player_message(player, msg);
		return add;
	}

	/**
	 * 玩家增加战场经验
	 *
	 * @param roleId
	 * @param exp
	 */
	public int addBattleExp(Player player, int exp,AttributeChangeReason reason) {
		if (player.getPrestige() >= Global.MAX_BATTLEEXP) {
			return 0;
		}
		exp = player.getPrestige() + exp;
		//增加经验
		if (exp > Global.MAX_BATTLEEXP) {
			player.setPrestige(Global.MAX_BATTLEEXP);
		} else {
			player.setPrestige(exp);
		}
		//经验不为负
		if (player.getPrestige() < 0) {
			player.setPrestige(0);
		}
		
		//发送战场经验消息
		ResPlayerBattleExpChangeMessage msg = new ResPlayerBattleExpChangeMessage();
		msg.setCurrentBattleExp(player.getPrestige());
		MessageUtil.tell_player_message(player, msg);

		return 1;
	}

	/**
	 * 设置玩家等级
	 *
	 * @param roleId
	 * @param level
	 */
	public int setLevel(Player player, int level, boolean boardcast, Reasons reason) {
		if (level > Global.MAX_LEVEL) {
			return 0;
		}

		//记录升级日志
		RoleLevelUpLog levellog = new RoleLevelUpLog(player);
		levellog.setBeforelevel(player.getLevel());
		levellog.setAfterlevel(level);
		levellog.setReason(reason.getValue());
		levellog.setSid(player.getCreateServerId());
		LogService.getInstance().execute(levellog);


		//设置等级
		player.setLevel(level);
		//设置升级时间
		player.setLevelUpTime(System.currentTimeMillis());
		
		ManagerPool.horseWeaponManager.setHorseWeaponLevel(player);
		
		//设置宠物等级
//		for (int i = 0; i < player.getPetList().size(); i++) {
//			Pet pet = player.getPetList().get(i);
//			if (pet != null) {
//				pet.setLevel(level);
//			}
//		}
		PetOptManager.getInstance().changeLevel(player);

		savePlayer(player);

		if (level == 60) {
			//60级自动切换一次全体pk
			changePkState(player, 3, 0);
		}

		if (level == 30) {
			ManagerPool.buffManager.removeByBuffId(player, Global.NEWBIE_BUFF);
			//30级自动接受日常任务
//			TaskManager.getInstance().acceptDailyTask(player,true);
			//同步玩家排行信息
			syncPlayerOrderInfo(player);
		}
		if (level == 15) {
//			if (player.getStoreCellsNum() == Global.DEFAULT_STORE_CELLS) {
//				//五级自动开第一格
//				StoreManager.getInstance().openCell(player, player.getStoreCellsNum() + 1);
//			}
//			if (player.getBagCellsNum() == Global.DEFAULT_BAG_CELLS) {
//				//五级自动开第一格
//				BackpackManager.getInstance().openCell(player, player.getBagCellsNum() + 1);
//			}
		}
		if (level == 26) {
//			if (player.getStoreCellsNum() == Global.DEFAULT_STORE_CELLS) {
//				//五级自动开第一格
//				StoreManager.getInstance().openCell(player, player.getStoreCellsNum() + 1);
//			}
//			if (player.getBagCellsNum() == Global.DEFAULT_BAG_CELLS) {
//				//五级自动开第一格
//				BackpackManager.getInstance().openCell(player, player.getBagCellsNum() + 1);
//			}
		}
		if (boardcast) {

			ReqSyncPlayerLevelMessage syncmsg = new ReqSyncPlayerLevelMessage();
			syncmsg.setPlayerId(player.getId());
			syncmsg.setLevel(level);
			syncmsg.setLevelUpTime(player.getLevelUpTime());
			MessageUtil.send_to_world(syncmsg);

			//升级消息
			ResPlayerLevelUpMessage msg = new ResPlayerLevelUpMessage();
			msg.setPersonId(player.getId());
			msg.setCurrentLevel(level);
			//广播升级消息
			MessageUtil.tell_round_message(player, msg);
		}
		//升级自动学会的
		SkillManager.getInstance().autoStudySkill(player);
		//升级触发军衔升级
		ManagerPool.rankManager.rankup(player);
		//升级触发军衔任务
		ManagerPool.taskManager.addRankTaskLevelUp(player);
		
		if (player.getLevel() == 50 || player.getLevel() == 60) {//自动学习技能
			ManagerPool.marriageManager.addMarriageSkill(player);	
		}
		ManagerPool.marriageManager.saveMarriagelevel(player);//升级后更新婚姻数据玩家简要信息， (等级)
		
		if (boardcast) {
			//重新计算属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.BASE);

			player.setHp(player.getMaxHp());
			onHpChange(player);
			player.setMp(player.getMaxMp());
			onMpChange(player);
			player.setSp(player.getMaxSp());
			onSpChange(player);
			
			log.error("玩家：" + player.getName() + "(" + player.getId() + ")回复生命,因为升级");
		}

		return 1;
	}

	/**
	 * 同步玩家排行信息
	 *
	 * @param player
	 */
	public void syncPlayerOrderInfo(Player player) {
		ReqSyncPlayerOrderInfoMessage msg = new ReqSyncPlayerOrderInfoMessage();
		msg.setPlayerId(player.getId());
		msg.setUserId(player.getUserId());
		msg.setName(player.getName());
		msg.setSex(player.getSex());
		msg.setCountry(player.getCountry());
		msg.setMoney(player.getMoney());
		msg.setExp(player.getExp());
		msg.setZhenqi(player.getZhenqi());
		msg.setAvatar(player.getAvatarid());
		int chapter = 0;
		if (player.getCurrentMainTasks().size() > 0) {
			MainTask mainTask = player.getCurrentMainTasks().get(0);
			Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(mainTask.getModelid());
			if (model != null) {
				chapter = model.getQ_chapter();
			}
		}
		msg.setChapter(chapter);
		msg.setPrestige(player.getPrestige());
		if (player.checkKingCityBuffOfKing()) {
			msg.setKingcitybuffid(BuffConst.KINGCITY_KING);
		}
		msg.setLevel(player.getLevel());
		msg.setLevelUpTime(player.getLevelUpTime());
		msg.setEventcut(player.getMaxEventcut());
		msg.setEventcutTime(player.getMaxEventcutTime());
		msg.setSkillLevel(player.getTotalSkillLevel());
		msg.setSkillTime(player.getSkillUpTime());
		msg.setMapId(player.getMapModelId());
		msg.setLongyuanSection(player.getLongyuan().getLysection());
		msg.setLongyuanLevel(player.getLongyuan().getLylevel());
		msg.setLongyuanTime(player.getLongyuanUpTime());
		msg.setVipid(VipManager.getInstance().getPlayerVipId(player)); //玩家VIP等级
		
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.MAXHP, player.getMaxHp()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.MAXMP, player.getMaxMp()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.MAXSP, player.getMaxSp()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.ATTACK, player.getAttack()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.DEFENSE, player.getDefense()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.DODGE, player.getDodge()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.CRIT, player.getCrit()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.LUCK, player.getLuck()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.ATTACKSPEED, player.getAttackSpeed()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.SPEED, player.getSpeed()));

		msg.setFightPower(player.getFightPower());
		msg.setEquip(JSONserializable.toString(player.getEquips()));
		msg.setGem(JSONserializable.toString(player.getGems()));
		msg.setSkills(JSONserializable.toString(player.getSkills()));
		
		Horse horse = ManagerPool.horseManager.getHorse(player);
		msg.setHorseStage(horse.getLayer());
		msg.setHorseLevel(horse.getHorselevel());
		msg.setHorseSkillLevel(horse.getSkilllevelsum());
		msg.setHorseTime(horse.getHorseUpTime());
		msg.setHorseEquip(JSONserializable.toString(horse.getHorseequips()));
		msg.setHorseSkill(JSONserializable.toString(horse.getSkills()));
		
		msg.setPets(JSONserializable.toString(player.getPetList()));
		
		msg.setRanklevel((byte) player.getRanklevel());
		msg.setRankpoint(player.getRankexp());
		
		msg.setArrowinfo(JSONserializable.toString(player.getArrowData()));
		
		msg.setCostgold(0);//TODO 消耗元宝
		//骑兵
		HorseWeapon horseWeapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
		if (horseWeapon != null) {
			msg.setHorseweaponlayer(horseWeapon.getLayer());
			msg.setHorseweaponlevel(horseWeapon.getLevel());
			msg.setHorseWeaponSkill(JSONserializable.toString(horseWeapon.getSkills()));
		}
		//暗器
		HiddenWeapon hiddenWeapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
		if (hiddenWeapon != null) {
			msg.setHiddenweaponlayer(hiddenWeapon.getLayer());
			msg.setHiddenWeaponSkill(JSONserializable.toString(hiddenWeapon.getSkillList()));
		}
		msg.setRealmintensifylevel(player.getRealm().getIntensifylevel());
		msg.setRealmlevel(player.getRealm().getRealmlevel());
		MessageUtil.send_to_world(msg);
	}

	/**
	 * 同步玩家装备信息
	 */
	public void syncPlayerEquip(Player player, byte site, Equip equip) {
		ReqSyncPlayerEquipMessage msg = new ReqSyncPlayerEquipMessage();
		msg.setPlayerId(player.getId());
		msg.setSite(site);
		if (equip != null) {
			msg.setEquip(JSONserializable.toString(equip));
		}
		MessageUtil.send_to_world(msg);
	}

	/**
	 * 同步玩家宝石信息
	 */
	public void syncPlayerGem(Player player, byte site, Gem gem) {
		ReqSyncPlayerGemMessage msg = new ReqSyncPlayerGemMessage();
		msg.setPlayerId(player.getId());
		msg.setSite(site);
		msg.setGem(JSONserializable.toString(gem));
		MessageUtil.send_to_world(msg);
	}

	/**
	 * 同步玩家装备，宝石， 战斗属性
	 */
	public void syncPlayerAttributes(Player player) {
		ReqSyncPlayerAttributeMessage msg = new ReqSyncPlayerAttributeMessage();
		msg.setPlayerId(player.getId());

		msg.setExp(player.getExp());
		msg.setZhenqi(player.getZhenqi());
		msg.setAvatar(player.getAvatarid());
		int chapter = 0;
		if (player.getCurrentMainTasks().size() > 0) {
			MainTask mainTask = player.getCurrentMainTasks().get(0);
			Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(mainTask.getModelid());
			if (model != null) {
				chapter = model.getQ_chapter();
			}
		}
		msg.setChapter(chapter);
		msg.setPrestige(player.getPrestige());

		msg.setEquip(JSONserializable.toString(player.getEquips()));
		msg.setGem(JSONserializable.toString(player.getGems()));
		msg.setSkills(JSONserializable.toString(player.getSkills()));

		msg.getAttributes().add(getPlayerAttributeItem(Attributes.MAXHP, player.getMaxHp()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.MAXMP, player.getMaxMp()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.MAXSP, player.getMaxSp()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.ATTACK, player.getAttack()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.DEFENSE, player.getDefense()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.DODGE, player.getDodge()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.CRIT, player.getCrit()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.LUCK, player.getLuck()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.ATTACKSPEED, player.getAttackSpeed()));
		msg.getAttributes().add(getPlayerAttributeItem(Attributes.SPEED, player.getSpeed()));

		msg.setFightPower(player.getFightPower());

		Horse horse = ManagerPool.horseManager.getHorse(player);
		msg.setHorseEquip(JSONserializable.toString(horse.getHorseequips()));
		msg.setHorseSkill(JSONserializable.toString(horse.getSkills()));
		
		msg.setPets(JSONserializable.toString(player.getPetList()));
		
		if (player.checkKingCityBuffOfKing()) {
			msg.setKingcitybuffid(BuffConst.KINGCITY_KING);
		}
		msg.setVipid(VipManager.getInstance().getPlayerVipId(player));
		
		msg.setArrowinfo(JSONserializable.toString(player.getArrowData()));
		
		msg.setCostgold(0);//TODO 消耗元宝
		HorseWeapon horseWeapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
		if (horseWeapon != null) {
			msg.setHorseWeaponSkill(JSONserializable.toString(horseWeapon.getSkills()));
		}
		
		HiddenWeapon hiddenWeapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
		if (hiddenWeapon != null) {
			msg.setHiddenWeaponSkill(JSONserializable.toString(hiddenWeapon.getSkillList()));
		}
		msg.setRealmintensifylevel(player.getRealm().getRealmInfo().getIntensifylevel());
		msg.setRealmlevel(player.getRealm().getRealmlevel());
		MessageUtil.send_to_world(msg);
	}
	
	/**
	 * 同步军衔
	 */
	public void syncPlayerRank(Player player){
		ReqSyncPlayerRankMessage sendMessage = new ReqSyncPlayerRankMessage();
		sendMessage.setPlayerId(player.getId());
		sendMessage.setRanklevel((byte) player.getRanklevel());
		sendMessage.setRankpoint(player.getRankexp());
		MessageUtil.send_to_world(sendMessage);
	}
	
	/**
	 * 同步弓箭
	 */
	public void syncPlayerArrow(Player player){
		ReqSyncPlayerArrowMessage sendMessage = new ReqSyncPlayerArrowMessage();
		sendMessage.setPlayerId(player.getId());
		sendMessage.setArrowinfo(JSONserializable.toString(player.getArrowData()));
		MessageUtil.send_to_world(sendMessage);
	}

	/**
	 * 获取所有保持会话的在线玩家ID
	 *
	 * @return
	 */
	public Long[] getOnlineRolesId() {
		return online.keySet().toArray(new Long[0]);
	}

	/**
	 * 开始自动挂机
	 *
	 * @param roleId
	 * @return
	 */
	public void startAuto(Player player) {
		if (PlayerState.DIE.compare(player.getState())) {
			return;
		}
		player.setState(PlayerState.AUTOFIGHT);
		player.setAutoStartTime(System.currentTimeMillis());
		log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态更改为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")");
		
		ResAutoStartStateMessage msg = new ResAutoStartStateMessage();
		msg.setResult((byte)1);
		MessageUtil.tell_player_message(player, msg);
	}

	public boolean inProtectTime() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (Global.PROTECT_IN_NIGHT_START < Global.PROTECT_IN_NIGHT_END) {
			if (hour >= Global.PROTECT_IN_NIGHT_START && hour < Global.PROTECT_IN_NIGHT_END) {
				return true;
			}
		} else {
			if (hour >= Global.PROTECT_IN_NIGHT_START || hour < Global.PROTECT_IN_NIGHT_END) {
				return true;
			}
		}
		return false;
	}

	public long getProtectCostTime() {
		Calendar now = Calendar.getInstance();
		Calendar start = Calendar.getInstance();
		int hour = start.get(Calendar.HOUR_OF_DAY);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		if (Global.PROTECT_IN_NIGHT_START < Global.PROTECT_IN_NIGHT_END) {
			start.set(Calendar.HOUR_OF_DAY, Global.PROTECT_IN_NIGHT_START);
		} else {
			if (hour >= Global.PROTECT_IN_NIGHT_START) {
				start.set(Calendar.HOUR_OF_DAY, Global.PROTECT_IN_NIGHT_START);
			} else if (hour <= Global.PROTECT_IN_NIGHT_END) {
				start.set(Calendar.HOUR_OF_DAY, Global.PROTECT_IN_NIGHT_START);
				start.add(Calendar.DAY_OF_YEAR, -1);
			}

		}

		return now.getTimeInMillis() - start.getTimeInMillis();
	}

	/**
	 * 结束自动挂机
	 *
	 * @param roleId
	 * @return
	 */
	public void endAuto(Player player) {
		player.setState(PlayerState.NOAUTOFIGHT);
		log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态更改为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")");
		//移除夜晚保护Buff
		if (FighterState.PKBAOHUFORNIGHT.compare(player.getFightState())) {
			ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_IN_NIGHT);
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您退出了挂机状态，夜晚挂机PK保护状态消失了"));
			log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")移除夜晚和平保护buff因为挂机结束");	
		}
		
		ResAutoStartStateMessage msg = new ResAutoStartStateMessage();
		msg.setResult((byte)0);
		MessageUtil.tell_player_message(player, msg);
	}

	/**更改pk模式 
	 *  0-和平 1-组队 2-帮会 3-全体
	 * 
	 */
	public void changePkState(Player player, int pkState, int auto) {
		if (player.getTeamid() == 0 && pkState == 1) {
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("很抱歉，您当前没有组队，无法切换到组队PK模式"));
			return;
		}
		if (player.getGuildId() == 0 && pkState == 2) {
			MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("很抱歉，您当前没有帮会，无法切换到帮会PK模式"));
			return;
		}

		player.setPkState(pkState);
		
		log.error("玩家(" + player.getId() + ")PK状态切换为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")");
		
		ResChangePKStateMessage msg = new ResChangePKStateMessage();
		msg.setPersonId(player.getId());
		msg.setPkState(pkState);
		msg.setAuto(auto);
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 生成本人详细信息
	 *
	 * @param player 玩家数据
	 * @return
	 */
	private ResMyPlayerInfoMessage getMyPlayerInfo(Player player) {
		ResMyPlayerInfoMessage info = new ResMyPlayerInfoMessage();
		info.setPersonId(player.getId());
		info.setName(player.getName());
		info.setSex(player.getSex());
		info.setLevel((short) player.getLevel());
		info.setMapId(player.getMap());
		info.setX(player.getPosition().getX());
		info.setY(player.getPosition().getY());
		info.setState(player.getState());
		info.setExp(player.getExp());
		info.setZhenqi(player.getZhenqi());
		info.setPrestige(player.getPrestige());
		info.setDir(player.getDirection());
		info.setAvatar(player.getAvatarid());
		info.setVipid(VipManager.getInstance().getPlayerVipId(player));
		
		info.getAttributes().add(getPlayerAttributeItem(Attributes.HP, player.getHp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.MP, player.getMp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.SP, player.getSp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.MAXHP, player.getMaxHp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.MAXMP, player.getMaxMp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.MAXSP, player.getMaxSp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.ATTACK, player.getAttack()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.DEFENSE, player.getDefense()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.DODGE, player.getDodge()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.CRIT, player.getCrit()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.LUCK, player.getLuck()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.ATTACKSPEED, player.getAttackSpeed()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.SPEED, player.getSpeed()));
		info.setPkState(player.getPkState());
		if (PlayerState.JUMP.compare(player.getState()) || PlayerState.DOUBLEJUMP.compare(player.getState())) {
			//info.setTime((int)(player.getJump().getTotalTime() - (System.currentTimeMillis() - player.getJump().getJumpStartTime())));
			List<Position> roads = new ArrayList<Position>();
			roads.add(player.getJump().getJumpStart());
			roads.addAll(player.getRoads());
			info.setPositions(roads);
		} else {
			info.setPositions(player.getRoads());
		}

		for (int i = 0; i < player.getEquips().length; i++) {
			Equip equip = (Equip) player.getEquips()[i];
			if (equip != null) {
				EquipInfo equipinfo = ManagerPool.equipManager.getEquipInfo(equip);
				info.getEquips().add(equipinfo);
			}
		}
		info.setCellnum((short) player.getBagCellsNum());
		info.setStorecellnum((short) player.getStoreCellsNum());
		Collection<Item> allItem = ManagerPool.backpackManager.getAllItem(player);
		for (Item item : allItem) {
			info.getItems().add(item.buildItemInfo());
		}
		info.setMoney(player.getMoney());
		Gold gold = player.getGold();
		int goldnum = (gold == null) ? 0 : gold.getGold();
		info.setGold(goldnum);
		info.setBindgold(player.getBindGold());
		info.setNonage((byte) player.getNonage());
		Horse horse = ManagerPool.horseManager.getHorse(player);
		if (horse != null && horse.getStatus() == 1) {	//骑马状态
			info.setHorseid(horse.getCurlayer());
		}
		//骑战兵器
		if (ManagerPool.horseWeaponManager.isWearHorseWeapon(player)){
			HorseWeapon weapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
			info.setHorseweaponid((short)weapon.getCurLayer());
		}
		//暗器
		if (ManagerPool.hiddenWeaponManager.isWearHiddenWeapon(player)){
			HiddenWeapon weapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
			info.setHiddenweaponid((short)weapon.getLayer());
		}
		info.setSkills(SkillManager.getInstance().getSkillLevelSum(player));
		Q_skill_realmBean realm = SkillManager.getInstance().getSkillRealm(player);
		if (realm != null) {
			info.setSkill((byte) realm.getQ_jingjieid());
		} else {
			info.setSkill((byte) 0);
		}
		info.setPosallgeminfo(ManagerPool.gemManager.getAllGem(player));
		if (player.getCurrentMainTasks().size() > 0) {
			info.setMaintaskId(player.getCurrentMainTasks().get(0).getModelid());
		}
		if (player.checkKingCityBuffOfKing()) {
			info.setKingcitybuffid(BuffConst.KINGCITY_KING);
		}
		
		info.setLongyuanlv(player.getLongyuanactlv());
		info.setLongyuannum(player.getLongyuanactnum());
		info.setRanklevel((byte) player.getRanklevel());
		info.setArrowInfo(player.getArrowData().toInfo());
		info.setWebvip(player.getVipright().getWebVipLevel());
		info.setWebvip2(player.getVipright().getWebVipLevel2());
		return info;
	}

	private PlayerAttributeItem getPlayerAttributeItem(Attributes type, int value) {
		PlayerAttributeItem item = new PlayerAttributeItem();
		item.setType(type.getValue());
		item.setValue(value);
		return item;
	}

//	private RoleChatInfo getChatInfo(Player player) {
//		RoleChatInfo info = new RoleChatInfo();
//		info.setId(player.getId());
//		info.setLevel(player.getLevel());
//		info.setName(player.getName());
//		info.setSex(player.getSex());
//		return info;
//	}
	/**
	 * 生成他人详细信息
	 *
	 * @param player 玩家数据
	 * @return
	 */
	public OtherPlayerInfo getOtherPlayerInfo(Player player) {
		OtherPlayerInfo info = new OtherPlayerInfo();
		info.setPersonId(player.getId());
		info.setName(player.getName());
		info.setSex(player.getSex());
		info.setLevel((short) player.getLevel());
		info.setAvatar(player.getAvatarid());
		info.setVipid(VipManager.getInstance().getPlayerVipId(player));
		int chapter = 0;
		if (player.getCurrentMainTasks().size() > 0) {
			MainTask mainTask = player.getCurrentMainTasks().get(0);
			Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(mainTask.getModelid());
			if (model != null) {
				chapter = model.getQ_chapter();
			}

		}
		info.setChapter(chapter);

		for (int i = 0; i < player.getEquips().length; i++) {
			Equip equip = (Equip) player.getEquips()[i];
			if (equip != null) {
				EquipInfo equipinfo = ManagerPool.equipManager.getEquipInfo(equip);
				info.getEquips().add(equipinfo);
			}
		}

		info.setExp(player.getExp());
		//角色真气
		info.setZhenqi(player.getZhenqi());
		//角色战场声望
		info.setPrestige(player.getPrestige());
		//公会信息
		OtherGuildInfo otherGuildInfo = new OtherGuildInfo();
		BannerInfo bannerInfo = new BannerInfo();
		bannerInfo.setGuildId(player.getGuildId());
		bannerInfo.setGuildName(player.getGuildInfo().getGuildName());
		bannerInfo.setGuildBanner(player.getGuildInfo().getGuildBanner());
		bannerInfo.setBangZhuName(player.getGuildInfo().getBangZhuName());
		bannerInfo.setBannerIcon(player.getGuildInfo().getBannerIcon());
		bannerInfo.setBannerLevel(player.getGuildInfo().getBannerLevel());
		bannerInfo.setCreateTime(player.getGuildInfo().getCreateTime());
		otherGuildInfo.setGuildPowerLevel(player.getMemberInfo().getGuildPowerLevel());
		otherGuildInfo.setGuildBanner(bannerInfo);
		info.setOtherGuildInfo(otherGuildInfo);
		//战斗力
		info.setFightpower(player.getFightPower());
		info.setSkills(SkillManager.getInstance().getSkillLevelSum(player));
		Q_skill_realmBean realm = SkillManager.getInstance().getSkillRealm(player);
		if (realm != null) {
			info.setSkill((byte) realm.getQ_jingjieid());
		} else {
			info.setSkill((byte) 0);
		}
		info.getAttributes().add(getPlayerAttributeItem(Attributes.HP, player.getHp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.MP, player.getMp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.SP, player.getSp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.MAXHP, player.getMaxHp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.MAXMP, player.getMaxMp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.MAXSP, player.getMaxSp()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.ATTACK, player.getAttack()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.DEFENSE, player.getDefense()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.DODGE, player.getDodge()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.CRIT, player.getCrit()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.LUCK, player.getLuck()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.ATTACKSPEED, player.getAttackSpeed()));
		info.getAttributes().add(getPlayerAttributeItem(Attributes.SPEED, player.getSpeed()));
		info.setPosallgeminfo(ManagerPool.gemManager.getAllGem(player));
		info.setRanklevel((byte) player.getRanklevel());
		info.setArrowInfo(player.getArrowData().toInfo());
		if (player.checkKingCityBuffOfKing()) {
			info.setKingcitybuffid(BuffConst.KINGCITY_KING);
		}
		info.setHorseWeaponInfo(ManagerPool.horseWeaponManager.getOtherHorseWeaponInfo(player));
		info.setHiddenWeaponInfo(ManagerPool.hiddenWeaponManager.getOtherHiddenWeaponInfo(player));
		info.setRealmlevel(player.getRealm().getRealmlevel());//境界等级
		info.setRealmintensifylevel(player.getRealm().getIntensifylevel());//境界强化等级
		Marriage marriage =ManagerPool.marriageManager.getMarriage(player.getMarriageid());
		if (marriage != null) {//结婚信息
			info.setSpousename(marriage.getSpouse(player).getName());
			info.setRingmodelid(marriage.getCurrringid());
			info.setMarriedtime((int) (marriage.getTime()/1000));
		}
		return info;
	}

	/**
	 * 获取在线玩家
	 *
	 * @param roleId
	 * @return
	 */
	public void addOnLinePlayer(Player player) {
		online.put(player.getId(), player);
	}
	
	/**
	 * 获取在线账号列表
	 *
	 * @return
	 */
	public HashMap<String, Player> getOnLineUser() {
		return user;
	}

	/**
	 * 获取在线玩家
	 *
	 * @param roleId
	 * @return
	 */
	public Player getOnLinePlayer(long roleId) {
		if (online.containsKey(roleId)) {
			return getPlayer(roleId);
		} else {
			return null;
		}
	}

	public boolean isOnline(long roleId) {
		if (online.containsKey(roleId)) {
			return true;
		}
		return false;
	}

	/**
	 * 是否在同一地图
	 *
	 * @param persona
	 * @param personb
	 * @return
	 */
	public boolean isSameMap(Person persona, Person personb) {
		if (persona.getServerId() != personb.getServerId()) {
			return false;
		}
		if (persona.getMap() != personb.getMap()) {
			return false;
		}
		if (persona.getMapModelId() != personb.getMapModelId()) {
			return false;
		}
		if (persona.getLine() != personb.getLine()) {
			return false;
		}
		return true;
	}

	/**
	 * 按名字查找在线的玩家
	 *
	 * @param name
	 * @return
	 */
	public Player getOnlinePlayerByName(String name) {
//		Long[] onlineRolesId = getOnlineRolesId();
//		for (Long roleId : onlineRolesId) {
//			Player player = getPlayer(roleId);
//			if (player != null && player.getName().equals(name)) {
//				return player;
//			}
//		}
		Long[] onlineRolesId = getOnlineRolesId();
		for (Long roleId : onlineRolesId) {
			Player player = players.get(roleId);
			if (player != null && player.getName() != null && player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}

	/**
	 *
	 * 按名称模糊查找
	 *
	 * @param name
	 * @return
	 */
	public List<Player> getOnlinePlayerListByNameLike(String name) {
		List<Player> info = new ArrayList<Player>();
		Long[] onlineRolesId = getOnlineRolesId();
		for (Long roleId : onlineRolesId) {
			Player player = getPlayer(roleId);
			if (player != null && player.getName().contains(name)) {
				info.add(player);
			}
		}
		return info;
	}

	/**同步世界服务器玩家全部信息变动消息
	 * 
	 * @param player
	 * @param type
	 * @return
	 */
	public ReqSyncPlayerInfoMessage getPlayerInfoRegister(Player player, byte type) {
		ReqSyncPlayerInfoMessage msg = new ReqSyncPlayerInfoMessage();

		msg.setPlayerId(player.getId());
		msg.setServerId(player.getServerId());
		msg.setLine(player.getLine());
		//角色名字
		msg.setName(player.getName());
		//角色等级
		msg.setLevel(player.getLevel());
		//国家
		msg.setCountry(player.getCountry());
		//角色所在地图
		Map map = ManagerPool.mapManager.getMap(player.getServerId(), player.getLine(), player.getMap());
		msg.setMapId(map.getMapModelid());
		//角色所在X
		msg.setX(player.getPosition().getX());
		//角色所在Y
		msg.setY(player.getPosition().getY());
		//弓箭阶数
		msg.setArrowLevel((byte) player.getArrowData().getArrowlv());
		//坐骑最高阶数
		Horse horse = ManagerPool.horseManager.getHorse(player);
		if (horse != null) {
			msg.setMountLevel((byte) horse.getLayer());
		}

		//龙元心法同步到世界服务器
		LongYuanData longYuanData = player.getLongyuan();
		msg.setTianyuanLevel(longYuanData.getLysection());
		//声望点
		msg.setPrestigePoint(player.getPrestigePoint());
		//成就点
		msg.setAchievementPoint(player.getAchievementPoint());
		//战斗力
		msg.setFightPower(player.getFightPower());
		//自动邀请组队
		msg.setAutoTeamApply(player.getAutoIntoteamapply());
		//自动邀请组队
		msg.setAutoTeamInvited(player.getAutoteaminvited());
		//心情
		msg.setMood(player.getMood());
		//是否公开我的地图位置
		msg.setOpenMapLocation(player.getOpenMapLocation());
		//T自动同意加入帮会
		msg.setAutoArgeeAddGuild(player.getAutoArgeeAddGuild());
		//离开帮会时间
		msg.setLastAfkGuildTime(player.getLastAfkGuildTime());
		//帮贡点
		msg.setContributionPoint(player.getContributionPoint());
		//外观信息
		PlayerAppearanceInfo appearanceInfo = ManagerPool.transactionsManager.setPlayerAppearanceInfo(player);
		msg.setAppearanceInfo(appearanceInfo);
		//玩家系统设置面板信息
		msg.setMenustatus(player.getMenustatus());
		//登陆类型
		msg.setType(type);
		//VIP等级
		msg.setVipid(VipManager.getInstance().getPlayerVipId(player));
		//改名类型
		msg.setChangeName(player.getChangeName());
		msg.setChangeUser(player.getChangeUser());
		msg.setUserId(player.getUserId());
		msg.setMaponlyId(player.getMap());
		msg.setRanklevel((byte) player.getRanklevel());
		if (player.checkKingCityBuffOfKing()) {
			msg.setKingcitybuffid(BuffConst.KINGCITY_KING);
		}
		msg.setWebvip(player.getVipright().getWebVipLevel());
		return msg;
	}

//	/**
//	 * 查找玩家 带分页
//	 *
//	 * @param name
//	 * @param page
//	 * @return
//	 */
//	private List<Player> queryPlayer(String name, int page) {
//		int pagenum = 10;
//		List<Player> playerlist = new ArrayList<Player>();
//		if (name != null && !name.equals("")) {
//			Iterator<Player> iterator = online.values().iterator();
//			while (iterator.hasNext()) {
//				Player player = (Player) iterator.next();
//				if (player.getName().contains(name)) {
//					playerlist.add(player);
//				}
//			}
//			//模糊查找
//		} else {
//			playerlist.addAll(online.values());
//			//全服查找
//		}
//		int starttag = (page - 1) * pagenum;
//		int endtag = starttag + pagenum > playerlist.size() ? playerlist.size() : starttag + pagenum;
//		List<Player> result = new ArrayList<Player>();
//		if (playerlist.size() > starttag) {
//			result = playerlist.subList(starttag, endtag);
//		}
//		return result;
//	}
//	/**
//	 * 聊天查找玩家
//	 *
//	 * @param queryer
//	 * @param name
//	 * @param page
//	 * @return
//	 */
//	public RoleQueryResultMessage queryChatPlayer(Player queryer, String name, int page) {
//		List<Player> queryPlayer = queryPlayer(name, page);
//		List<RoleChatInfo> infos = new ArrayList<RoleChatInfo>();
//		for (Player player : queryPlayer) {
//			RoleChatInfo chatInfo = getChatInfo(player);
//			infos.add(chatInfo);
//		}
//		RoleQueryResultMessage resp = new RoleQueryResultMessage();
//		return resp;
//	}
	public void setPlayerNonage(long playerId, int nonage) {
		Player player = getPlayer(playerId);
		if (player != null) {
			player.setNonage(nonage);
			ResPlayerNonageStateMessage msg = new ResPlayerNonageStateMessage();
			msg.setNonage((byte) player.getNonage());
			MessageUtil.tell_player_message(player, msg);
		}
	}

	/**
	 * 检查临时背包是否有可领取物品
	 *
	 * @param player
	 * @return
	 */
	public boolean hasAbleReceive(Player player) {
		List<Item> taskRewardsReceiveAble = player.getTaskRewardsReceiveAble();
		if (taskRewardsReceiveAble != null && taskRewardsReceiveAble.size() > 0) {
			return true;
		}
		return false;
	}

	public void getNonageTime(Player player) {
		ReqNonageTimeToWorldMessage msg = new ReqNonageTimeToWorldMessage();
		msg.setPlayerId(player.getId());
		msg.setUserId(player.getUserId());
		MessageUtil.send_to_world(msg);
	}

	public void registerNonage(Player player, String name, String idCard) {
		ReqNonageRegisterToWorldMessage msg = new ReqNonageRegisterToWorldMessage();
		msg.setPlayerId(player.getId());
		msg.setUserId(player.getUserId());
		msg.setName(name);
		msg.setIdCard(idCard);
		MessageUtil.send_to_world(msg);
	}

	/**
	 * 请求更换头像
	 *
	 * @author tangchao
	 * @param player
	 * @param avatarid
	 */
	public void changeAvatar(Player player, int avatarid) {
		player.setAvatarid(avatarid);
		//更换头像请求需要由世界服务器处理		
		stSyncExterior(player);
		//更换头像同步给周围的玩家
		ResPlayerAvatarMessage msg = new ResPlayerAvatarMessage();
		msg.setPlayerid(player.getId());
		msg.setAvatarid(avatarid);
		MessageUtil.tell_round_message(player, msg);
	}

	/**
	 * 同步玩家外观到世界服务器
	 *
	 * @param player
	 */
	public void stSyncExterior(Player player) {
		ReqSyncPlayerAppearanceInfoMessage syncmsg = new ReqSyncPlayerAppearanceInfoMessage();
		syncmsg.setPlayerId(player.getId());
		syncmsg.setAppearanceInfo(ManagerPool.transactionsManager.setPlayerAppearanceInfo(player));
		if (player.checkKingCityBuffOfKing()) {
			syncmsg.setKingcitybuffid(BuffConst.KINGCITY_KING);
		}
		syncmsg.setVipid(VipManager.getInstance().getPlayerVipId(player));
		MessageUtil.send_to_world(syncmsg);
	}

	public int getOnlineCount() {
		return online.size();
	}

	/**
	 * 是否最高等级
	 *
	 * @param player
	 * @return
	 */
	public boolean isTopLevel(Player player) {
		if (player.getLevel() >= Global.MAX_LEVEL) {
			return true;
		}
		return false;
	}

	public boolean isFullHp(Player player) {
		return player.getHp() >= player.getMaxHp();
	}

	public boolean isFullMp(Player player) {
		return player.getMp() >= player.getMaxMp();
	}

	public boolean isFullSp(Player player) {
		return player.getSp() >= player.getMaxSp();
	}

	public boolean isFullZq(Player player) {
		int maxzq = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
		return player.getZhenqi() >= maxzq;
	}

	/**
	 * 前端查询玩家是否在线
	 *
	 * @param player
	 * @param msg
	 */
	public void stReqPlayerCheckOnlineMessage(Player player, ReqPlayerCheckOnlineMessage msg) {
		ReqPlayerCheckOnlineToWorldMessage wmsg = new ReqPlayerCheckOnlineToWorldMessage();
		wmsg.setPlayerId(player.getId());
		wmsg.setOthersid(msg.getOthersid());
		wmsg.setType(msg.getType());
		wmsg.setX(msg.getX());
		wmsg.setY(msg.getY());
		MessageUtil.send_to_world(wmsg);


	}

	/**
	 * 前端请求查看玩家造型
	 *
	 * @param player
	 * @param msg
	 */
	public void stReqGetPlayerAppearanceInfoMessage(Player player, ReqGetPlayerAppearanceInfoMessage msg) {
		ReqGetPlayerAppearanceInfoToWorldMessage wmsg = new ReqGetPlayerAppearanceInfoToWorldMessage();
		wmsg.setOthersid(msg.getOthersid());
		wmsg.setPlayerId(player.getId());
		wmsg.setType(msg.getType());
		MessageUtil.send_to_world(wmsg);
	}

	/**
	 * 删除玩家
	 *
	 * @param msg
	 */
	public void stReqDelPlayerToGameMessage(ReqDelPlayerToGameMessage msg) {
		ReqDelPlayerToWorldMessage wmsg = new ReqDelPlayerToWorldMessage();
		wmsg.setCreateServer(msg.getCreateServer());
		wmsg.setUserId(msg.getUserId());
		wmsg.setPlayerId(msg.getPlayerId());
		wmsg.setGateId(msg.getGateId());
		wmsg.setOptIp(msg.getOptIp());
		MessageUtil.send_to_world(wmsg);
	}

	/**
	 * GM测试改名字
	 *
	 * @param player
	 * @param name
	 */
	public void testChangePlayerName(Player player, String name) {
		ReqChangePlayerNameMessage msg = new ReqChangePlayerNameMessage();
		msg.setNewname(name);
		player.setChangeName((byte) 1);
		stReqChangePlayerNameMessage(player, msg);
	}

	/**
	 * 服务器端处理通用脚本消息
	 * @param msg
	 */
	public void ReqScriptCommonPlayerToServer(Player player, ReqScriptCommonPlayerToServerMessage msg){
		if(msg.getScriptid()==1200){  //客户端请求登录器礼包领取
			RegistrarManager.getInstance().CSDispatch(player, msg);
		}else if(msg.getScriptid()==1201){
			RegistrarManager.getInstance().CSDispatchRecharge(player, msg);
		}
	}
	
	public void ResScriptCommonServerToServer(Player player, ResScriptCommonServerToServerMessage msg){
		
		if(msg.getScriptid()==1200){  //登录器
			RegistrarManager.getInstance().WSDispatch(player, msg);
		}else if(msg.getScriptid()==1201){ //首冲奖励
			RegistrarManager.getInstance().WSDispatchRecharge(player, msg);
		}else if(msg.getScriptid()==1202){ //活动奖励
			RegistrarManager.getInstance().WSDispatchActivities(player, msg); 
		}
	}
	
	/**
	 * 通知世界服务器玩家改名
	 *
	 * @param player
	 * @param msg
	 * @return 
	 */
	public boolean stReqChangePlayerNameMessage(Player player, ReqChangePlayerNameMessage msg) {
		if (player.getChangeName() == 1) {
			String newname = msg.getNewname();
			if (newname == null || newname.length() < 2 || newname.length() > 6) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("请输入6个字符以内"));
				return false;
			}

			if (checkName(newname)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("已有相同名字存在"));
				return false;
			}

			if (WordFilter.getInstance().hashNoLimitedWords(newname)) {//非法提示
				log.debug("玩家名字非法字符：" + newname);
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("玩家名字有非法字符，请输入其他名称"));
				return false;
			}

			if (WordFilter.getInstance().hashBadWords(newname)) {
				log.debug("玩家名字非法字符：" + newname);
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("玩家名字有非法字符，请输入其他名称"));
				return false;
			}

			newname = "[" + WServer.getInstance().getServerId() + "区]" + newname;
			ReqChangePlayerNameToWorldMessage wmsg = new ReqChangePlayerNameToWorldMessage();
			wmsg.setNewname(newname);
			wmsg.setPlayerId(player.getId());
			MessageUtil.send_to_world(wmsg);
			log.error(player.getId() + "原名字:"+player.getName()+"，开始修改角色名："+msg.getNewname());
			return true;
		} else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您无法修改名字"));
			return false;
		}
	}

	/**
	 * 世界服务器改名完成通知GAME
	 *
	 * @param parameter
	 * @param msg
	 */
	public void stResChangePlayerNameToGameMessage(ResChangePlayerNameToGameMessage msg) {
		Player player = getOnLinePlayer(msg.getPlayerId());
		ResChangePlayerNameToClientMessage cmsg = new ResChangePlayerNameToClientMessage();
		cmsg.setPlayerId(player.getId());
		cmsg.setResult(msg.getResult());
		if (msg.getResult() == 1) {	//改名成功
			if (player != null) {
				String oldname = player.getName();
				player.setOldname(oldname);
				player.setOldnametime((int) (System.currentTimeMillis()/1000));
				player.setName(msg.getNewname());
				player.setChangeName((byte) 0);
				cmsg.setNewname(msg.getNewname());
				MessageUtil.tell_round_message(player, cmsg);//通知周围的人
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您成功修改角色名为『{1}』"), msg.getNewname());
				log.error(player.getId() + "原名字:"+oldname+"成功修改角色名："+msg.getNewname());
			}
		} else {
			MessageUtil.tell_player_message(player, cmsg);
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("，修改角色名『{1}』失败"), msg.getNewname());
			log.error(player.getId() + "原名字:"+player.getName()+"，修改角色名失败："+msg.getNewname());
		}
	}
	
	/**
	 * 玩家获取VIP传送信息
	 * @return 
	 */
	public void reqVipPlayerChangeMap(Player player){
		if (player != null) {
			VipManager.getInstance().resetFreeFly(player); //先检查是否需要重置VIP免费传送次数
			ResVipPlayerChangeMapToClientMessage sendMessage = new ResVipPlayerChangeMapToClientMessage();
			sendMessage.setCurnum(player.getVipright().getFreeflytime());
			sendMessage.setViplv(VipManager.getInstance().getPlayerVipId(player));
			MessageUtil.tell_player_message(player, sendMessage);
		}
	}

	public static HashSet<Long> getSyncPosition() {
		return syncPosition;
	}

//	/**上线给玩家加500000真气
//	 * 
//	 * @param player
//	 */
//	public void testaddzhenqi(Player player){
//		String key = "zhenqi20120818";
//		if (player.getActivitiesReward().containsKey(key) == false) {
//			player.getActivitiesReward().put(key, "真气500000");
//			ManagerPool.playerManager.addZhenqi(player,500000);
//			List<Item> createItems = Item.createItems(1019, 1,true,0 ,0, null);
//			ManagerPool.backpackManager.addItem(player, createItems.get(0), Reasons.ACTIVITY_JINGYANDAN,Config.getId());
//			List<Item> createItems2 = Item.createItems(1011, 100,true,0 ,0, null);
//			ManagerPool.backpackManager.addItem(player, createItems2.get(0), Reasons.ACTIVITY_JINGYANDAN,Config.getId());
//			
//		}
//		String key2 = "yuanbao20120828";
//		if (player.getActivitiesReward().containsKey(key2) == false) {
//			player.getActivitiesReward().put(key2, "礼金5000");
//			ManagerPool.backpackManager.changeBindGold(player, 5000, Reasons.ACTIVITY_JINGYANDAN, Config.getId());
//		}
//	} 
	/**
	 * 增加延时任务
	 *
	 * @param player
	 * @param run
	 * @param millisecond
	 */
	public void addLaterTask(Player player, Runnable run, int millisecond) {
		player.getLaterList().add(new LaterTask(run, millisecond, 1));
	}
	
	public void enterMapCheck(Player player){
		Midnight0ClockEvent.checkZeroClock(player);
	}
	
	public void playercheck(Player player, int type, Object... obj){
		IPlayerCheckScript script = (IPlayerCheckScript) ManagerPool.scriptManager.getScript(ScriptEnum.PLAYER_CHECK);
		if (script != null) {
			try {
				script.onCheck(player, type, obj);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("玩家作弊检测脚本不存在！");
		}
	}

	
	/**
	 * 得到服务器在线举报列表
	 * @return
	 */
	public ConcurrentHashMap<String, PlayerSpeedReport> getServerSpeedReportMap() {
		return serverSpeedReportMap;
	}

	/**
	 * 举报玩家加速
	 * @param player
	 * @param targetid
	 */
	public synchronized void reportPlayerSpeed(Player player, long targetid){
		//找到被举报的玩家
		Player p = PlayerManager.getInstance().getPlayer(targetid);
		if(p!=null){
			long now = System.currentTimeMillis();
			//服务器举报列表
			ConcurrentHashMap<String, PlayerSpeedReport> reportmap = PlayerManager.getInstance().getServerSpeedReportMap();
			if(reportmap.containsKey(String.valueOf(p.getId()))){ //玩家已经被举报过
				PlayerSpeedReport report = reportmap.get(String.valueOf(p.getId()));
				report.setReporttimes(report.getReporttimes()+1); //被举报次数+1
				report.getReportmap().put(String.valueOf(player.getId()), now); //记录举报者以及最后举报时间
				report.setLastreporttime(now);
				//记录日志
				try{
					RoleReportLog rlog = new RoleReportLog(p);
					rlog.setReporterid(player.getId());
					rlog.setReportername(player.getName());
					rlog.setReporttimes(report.getReporttimes());
					LogService.getInstance().execute(rlog);
				}catch (Exception e) {
					log.error(e, e);
				}
			}else{ //玩家第一次被举报
				PlayerSpeedReport psr = new PlayerSpeedReport();
				psr.setRoleid(String.valueOf(p.getId()));
				psr.setReporttimes(1);
				ConcurrentHashMap<String, Long> rmap = new ConcurrentHashMap<String, Long>();
				rmap.put(String.valueOf(player.getId()), now);
				psr.setReportmap(rmap);
				psr.setLastreporttime(now);
				reportmap.put(String.valueOf(p.getId()), psr);
				//记录日志
				try{
					RoleReportLog rlog = new RoleReportLog(p);
					rlog.setReporterid(player.getId());
					rlog.setReportername(player.getName());
					rlog.setReporttimes(psr.getReporttimes());
					LogService.getInstance().execute(rlog);
				}catch (Exception e) {
					log.error(e, e);
				}
			}
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("举报{1}成功"), p.getName());
		}else{
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("举报失败,玩家不在线"));
		}
	}
	
	/**
	 * 返回玩家关监狱状态
	 * @param player
	 */
	public void sendPlayerPrisonState(Player player){
		ResPlayerPrisonStateMessage retmsg = new ResPlayerPrisonStateMessage();
		retmsg.setPrisontimes(player.getPrisonTimes());
		retmsg.setPrisonremaintime(player.getPrisonRemainTime());
		MessageUtil.tell_player_message(player, retmsg);
	}
	
	/**
	 * 得到角色GM等级
	 * @param player
	 * @return
	 */
	public int getPlayerGmlevel(Player player){
		int level = player.getGmlevel();
		long now = System.currentTimeMillis();
		//还没开服 并且登录IP在测试期间GM IP列表中
		Date opendate = WServer.getGameConfig().getServerTimeByPlayer(player);
		if(opendate!=null){
			long opentime = opendate.getTime();
			if(now < opentime){
				String loginip = player.getLoginIp();
				if(!StringUtil.isBlank(loginip)){
					Set<String> testGmIpSet = PlayerManager.getInstance().getTestGmIps();
					if(testGmIpSet.contains(loginip)){
						level = 2;
					}
				}
			}
		}
		return level;
	}
	
	/**
	 * 发送给客户端当前最大跳跃速度
	 */
	public void sendJumpMaxSpeed(Player player){
		int jumpmaxspeed = DataManager.getInstance().q_globalContainer.getMap().get(CommonConfig.JUMP_MAX_SPEED.getValue()).getQ_int_value();
		ResJumpMaxSpeedMessage resmsg = new ResJumpMaxSpeedMessage();
		resmsg.setJumpmaxspeed(jumpmaxspeed);
		MessageUtil.tell_player_message(player, resmsg);
	}
	
	/**
	 * 得到 测试GM IP
	 * @return
	 */
	public Set<String> getTestGmIps() {
		return testGmIps;
	}
	public void setTestGmIps(Set<String> testGmIps) {
		this.testGmIps = testGmIps;
	}
	
}
