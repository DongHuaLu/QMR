package com.game.manager;

import com.game.country.manager.CountryManager;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.friend.manager.FriendManager;
import com.game.guild.manager.GuildWorldManager;
import com.game.marriage.manager.MarriageManager;
import com.game.monster.manager.MonsterManager;
import com.game.player.manager.PlayerManager;
import com.game.schedular.manager.SchedularManager;
import com.game.script.manager.ScriptManager;
import com.game.spirittree.manager.SpiritTreeManager;
import com.game.stalls.manager.StallsManager;
import com.game.team.manager.TeamManager;
import com.game.toplist.manager.TopListManager;
import com.game.txconsume.manager.TxConsumeManager;
import com.game.version.manager.VersionManager;
import com.game.ybcard.manager.YbcardManager;

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
	//数据资源管理类
	public static DataManager dataManager = DataManager.getInstance();
	//玩家管理类
	public static PlayerManager playerManager = PlayerManager.getInstance();
	//怪物管理类
	public static MonsterManager monsterManager = MonsterManager.getInstance();
	//组队管理类
	public static TeamManager teamManager = TeamManager.getInstance();
	//好友管理类
	public static FriendManager friendManager = FriendManager.getInstance();
	//摆摊管理类
	public static StallsManager stallsManager = StallsManager.getInstance();
	//公会管理类
	public static GuildWorldManager guildWorldManager = GuildWorldManager.getInstance();
	//灵树管理类
	public static SpiritTreeManager spiritTreeManager = SpiritTreeManager.getInstance();
	//RES文件版本管理
	public static VersionManager versionManager = VersionManager.getInstance();
	//公测元宝卡管理
	public static YbcardManager ybcardManager = YbcardManager.getInstance();
	//脚本管理
	public static ScriptManager scriptManager = ScriptManager.getInstance();
	//排行榜管理
	public static TopListManager topListManager = TopListManager.getInstance();
	//王城争霸战管理
	public static CountryManager countryManager = CountryManager.getInstance();
	//调度管理
	public static SchedularManager schedularManager = SchedularManager.getInstance(); 
	//腾讯发放管理
	public static TxConsumeManager txconsumeManager = TxConsumeManager.getInstance();
	//结婚管理
	public static MarriageManager marriageManager = MarriageManager.getInstance();
	public static LogService logservice=LogService.getInstance();
	
	
	public static void  reloadDataManager(){
		dataManager = DataManager.getInstance();
	}
	
	
	
	
	
	
	
	
}
