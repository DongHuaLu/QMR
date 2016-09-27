package com.game.manager;

import com.game.activities.manager.ActivitiesManager;
import com.game.arrow.manager.ArrowManager;
import com.game.backpack.manager.BackpackManager;
import com.game.batter.manager.BatterManager;
import com.game.biwudao.manager.BiWuDaoManager;
import com.game.buff.manager.BuffManager;
import com.game.challenge.manager.ChallengeManager;
import com.game.chat.manager.ChatManager;
import com.game.chestbox.manager.ChestBoxManager;
import com.game.collect.manager.CollectManager;
import com.game.cooldown.manager.CooldownManager;
import com.game.count.manager.CountManager;
import com.game.count.manager.ServerCountManager;
import com.game.country.manager.CountryManager;
import com.game.data.manager.DataManager;
import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.db.ddl.mapping.StrutsCenter;
import com.game.dblog.LogService;
import com.game.dblog.UpdateLogService;
import com.game.dianjiangchun.manager.DianjiangchunManager;
import com.game.epalace.manager.EpalaceManeger;
import com.game.equip.manager.EquipManager;
import com.game.equipstreng.manager.EquipStrengManager;
import com.game.fight.manager.FightManager;
import com.game.friend.manager.FriendManager;
import com.game.gem.manager.GemManager;
import com.game.gm.manager.GMCommandManager;
import com.game.guild.manager.GuildServerManager;
import com.game.guildflag.manager.GuildFlagManager;
import com.game.hiddenweapon.manager.HiddenWeaponManager;
import com.game.horse.manager.HorseManager;
import com.game.horseweapon.manager.HorseWeaponManager;
import com.game.languageres.manager.ResManager;
import com.game.longyuan.manager.LongYuanManager;
import com.game.mail.manager.MailServerManager;
import com.game.map.manager.MapManager;
import com.game.marriage.manager.MarriageManager;
import com.game.melting.manager.MeltingManager;
import com.game.monster.manager.HatredManager;
import com.game.monster.manager.MonsterManager;
import com.game.npc.manager.NpcManager;
import com.game.pet.manager.PetInfoManager;
import com.game.pet.manager.PetOptManager;
import com.game.pitfall.manager.PitfallManager;
import com.game.player.manager.PlayerAttributeManager;
import com.game.player.manager.PlayerManager;
import com.game.player.manager.PlayerRandomNameManager;
import com.game.plugset.manager.PlugSetManager;
import com.game.rank.manager.RankManager;
import com.game.realm.manager.RealmManager;
import com.game.sceneobj.manager.SceneobjManager;
import com.game.schedular.manager.SchedularManager;
import com.game.script.manager.ScriptManager;
import com.game.setupmenu.manager.SetupMenuManager;
import com.game.shop.manager.ShopManager;
import com.game.shortcut.manager.ShortCutManager;
import com.game.signwage.manager.SignWageManager;
import com.game.skill.manager.SkillManager;
import com.game.spirittree.manager.SpiritTreeManager;
import com.game.stalls.manager.StallsManager;
import com.game.store.manager.StoreManager;
import com.game.task.manager.TaskManager;
import com.game.team.manager.TeamManager;
import com.game.toplist.manager.TopListManager;
import com.game.transactions.manager.TransactionsManager;
import com.game.version.manager.VersionManager;
import com.game.vip.manager.VipManager;
import com.game.ybcard.manager.YbcardManager;
import com.game.zones.manager.ZonesFlopManager;
import com.game.zones.manager.ZonesManager;
import com.game.zones.manager.ZonesTeamManager;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-15
 * 
 * 类说明 
 */
public class ManagerPool {
	//字符串资源管理
	public static ResManager resManager = ResManager.getInstance();
	//数据资源管理类
	public static DataManager dataManager = DataManager.getInstance();
	//地图管理类
	public static MapManager mapManager = MapManager.getInstance();
	//玩家管理类
	public static PlayerManager playerManager = PlayerManager.getInstance();
	//背包管理类
	public static BackpackManager backpackManager = BackpackManager.getInstance();
	//仓库管理类
	public static StoreManager storeManager=StoreManager.getInstance();
	//商店管理类
	public static ShopManager shopManager = ShopManager.getInstance();
	//装备管理类
	public static EquipManager equipManager = EquipManager.getInstance();
	//技能管理类
	public static SkillManager skillManager = SkillManager.getInstance();
	//怪物管理类
	public static MonsterManager monsterManager = MonsterManager.getInstance();
	//NPC管理类
	public static NpcManager npcManager=NpcManager.getInstance();
	//快捷管理类
	public static ShortCutManager shortCutManager = ShortCutManager.getInstance();
	//战斗管理类
	public static FightManager fightManager = FightManager.getInstance();
//	//提示信息管理类
//	public static PromptManager promptManager = PromptManager.getInstance();
	//冷却管理类
	public static CooldownManager cooldownManager = CooldownManager.getInstance();
	//聊天管理类
	public static ChatManager chatManager = ChatManager.getInstance();
	//BUFF管理类
	public static BuffManager buffManager = BuffManager.getInstance();
	//点绛唇管理类
	public static DianjiangchunManager dianjiangchunManager = DianjiangchunManager.getInstance();
	//宠物操作管理
	public static PetOptManager petOptManager=PetOptManager.getInstance();
	//宠物信息管理
	public static PetInfoManager petInfoManager=PetInfoManager.getInstance();
	
	//军衔管理类
	public static RankManager rankManager=RankManager.getInstance();
	//玩家属性管理类
	public static PlayerAttributeManager playerAttributeManager = PlayerAttributeManager.getInstance();
	//组队管理类
	public static TeamManager  teamManager = TeamManager.getInstance();
	//好友管理类
	public static FriendManager friendManager = FriendManager.getInstance();
	//交易管理类
	public static TransactionsManager transactionsManager = TransactionsManager.getInstance();
	//摆摊管理类
	public static StallsManager stallsManager = StallsManager.getInstance();
	//任务管理类
	public static TaskManager taskManager=TaskManager.getInstance();
	//定时任务管理类
	public static SchedularManager schedularManager = SchedularManager.getInstance();
	//脚本管理类
	public static ScriptManager scriptManager = ScriptManager.getInstance();
	//日志管理类
	public static LogService logManager=LogService.getInstance();
	
	public static StrutsCenter strutscenter=StrutsCenter.getInstance();
	//更新类日志管理类
	public static UpdateLogService updateLogService=UpdateLogService.getInstance();
	
	//邮件管理类
	public static MailServerManager mailServerManager = MailServerManager.getInstance();
	//龙元心法管理
	public static LongYuanManager longyuanManager = LongYuanManager.getInstance();
	//仇恨管理
	public static HatredManager hatredManager = HatredManager.getInstance();
	//前端游戏设置面板管理
	public static SetupMenuManager setupMenuManager = SetupMenuManager.getInstance();
	//坐骑管理类
	public static HorseManager horseManager = HorseManager.getInstance();
	//角色打座管理
	public static PlayerDaZuoManager dazuoManager=PlayerDaZuoManager.getInstacne();
	//副本管理
	public static ZonesManager zonesManager=ZonesManager.getInstance();
	//冷却管理
	public static CountManager countManager=CountManager.getInstance();
	//装备强化升阶管理
	public static EquipStrengManager  equipstrengManager =EquipStrengManager.getInstance();
	//前端外挂设置管理
	public static PlugSetManager  plugSetManager =PlugSetManager.getInstance();
	//宝石系统管理
	public static GemManager  gemManager =GemManager.getInstance();
	//活动系统管理
	public static ActivitiesManager activitiesManager =ActivitiesManager.getInstance();
	//玩家随机名字
	public static PlayerRandomNameManager randomNameManager=PlayerRandomNameManager.getInstance();
	//灵树系统管理
	public static SpiritTreeManager  spiritTreeManager =SpiritTreeManager.getInstance();
	//RES版本管理
	public static VersionManager  versionManager =VersionManager.getInstance();
	//公测元宝卡管理
	public static YbcardManager ybcardManager = YbcardManager.getInstance();
	
	//副本奖励管理
	public static ZonesFlopManager zonesFlopManager = ZonesFlopManager.getInstance();
	//多人副本管理
	public static ZonesTeamManager zonesTeamManager= ZonesTeamManager.getInstance();
	//连斩管理
	public static BatterManager batterManager = BatterManager.getInstance();
	//地宫寻宝
	public static EpalaceManeger epalaceManeger = EpalaceManeger.getInstance();
	//排行榜管理
	public static TopListManager topListManager = TopListManager.getInstance();
	//挑战管理
	public static ChallengeManager challengeManager = ChallengeManager.getInstance();
	//GM权限管理
	public static GMCommandManager gmcommandManager = GMCommandManager.getInstance();
	//王城争霸战管理
	public static CountryManager countryManager = CountryManager.getInstance();
	//VIP管理
	public static VipManager vipManager = VipManager.getInstance();
	//服务器级统计
	public static ServerCountManager serverCountManager=ServerCountManager.getInstance();
	//场景物件
	public static SceneobjManager sceneobjManager =SceneobjManager.getInstance();
	//场景陷阱点
	public static PitfallManager pitfallManager =PitfallManager.getInstance();
	//帮会领地争夺战
	public static GuildFlagManager guildFlagManager = GuildFlagManager.getInstance();
	//帮会管理
	public static GuildServerManager guildServerManager=GuildServerManager.getInstance();
	//弓箭管理
	public static ArrowManager arrowManager=ArrowManager.getInstance();
	//签到工资管理
	public static SignWageManager signWageManager =SignWageManager.getInstance();
	//收集管理
	public static CollectManager collectManager=CollectManager.getInstance();
	//熔炼管理
	public static MeltingManager meltingManager = MeltingManager.getInstance();
	//骑战兵器管理
	public static HorseWeaponManager horseWeaponManager = HorseWeaponManager.getInstance();
	//宝箱幸运轮盘管理
	public static ChestBoxManager chestBoxManager = ChestBoxManager.getInstance();
	//比武岛管理
	public static BiWuDaoManager biWuDaoManager = BiWuDaoManager.getInstance();
	//境界系统管理
	public static RealmManager realmManager = RealmManager.getInstance();
	//暗器管理
	public static HiddenWeaponManager hiddenWeaponManager = HiddenWeaponManager.getInstance();
	//婚姻系统管理
	public static MarriageManager marriageManager = MarriageManager.getInstance();

	public static void  reloadDataManager(){
		dataManager = DataManager.getInstance();
	}
		
	
}
