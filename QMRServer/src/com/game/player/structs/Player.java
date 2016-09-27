package com.game.player.structs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.game.arrow.structs.ArrowData;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.bag.structs.Bag;
import com.game.buff.manager.BuffManager;
import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffConst;
import com.game.chat.struts.ChatCountBean;
import com.game.chestbox.structs.ChestBoxData;
import com.game.collect.struts.Collect;
import com.game.count.structs.Count;
import com.game.country.manager.CountryAwardManager;
import com.game.country.manager.CountryManager;
import com.game.country.structs.KingCity;
import com.game.country.structs.KingCityChest;
import com.game.country.structs.KingData;
import com.game.db.bean.Gold;
import com.game.dianjiangchun.structs.DianjiangchunSaveData;
import com.game.epalace.structs.Epalace;
import com.game.equipstreng.structs.EquipStreng;
import com.game.fight.structs.Fighter;
import com.game.gem.struts.Gem;
import com.game.gift.struts.GiftSaveInfo;
import com.game.guild.bean.GuildInfo;
import com.game.guild.bean.MemberInfo;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.horse.struts.Horse;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.longyuan.structs.LongYuanData;
import com.game.mail.structs.MailDetailInfoData;
import com.game.manager.ManagerPool;
import com.game.map.structs.IMapObject;
import com.game.map.structs.Jump;
import com.game.pet.struts.Pet;
import com.game.player.message.ResPlayerStateChangeMessage;
import com.game.plugset.structs.PlugSet;
import com.game.realm.structs.Realm;
import com.game.server.impl.WServer;
import com.game.shortcut.structs.ShortCut;
import com.game.signwage.structs.Sign;
import com.game.signwage.structs.Wage;
import com.game.skill.structs.Skill;
import com.game.stalls.structs.StallsTransactionLog;
import com.game.structs.Position;
import com.game.task.struts.ConquerTask;
import com.game.task.struts.DailyTask;
import com.game.task.struts.MainTask;
import com.game.task.struts.RankTask;
import com.game.task.struts.TreasureHuntTask;
import com.game.transactions.structs.TempYuanbaoLogData;
import com.game.transactions.structs.Transactions;
import com.game.utils.MessageUtil;
import com.game.vip.struts.VipRight;
import com.game.zones.structs.ContinuousRaidsInfo;
import com.game.zones.structs.Raid;
import com.game.zones.structs.RaidFlop;

public class Player extends Person implements IMapObject, Fighter, Comparable<Player> {

	protected Logger log = Logger.getLogger(Player.class);
	private static final long serialVersionUID = 7829192173484491552L;
	//创建账号服务器
	private int createServerId;
	//登陆类型
	private int loginType;
	//所在地区 1-秦国。。。0-公共区
	private int locate;
	//账号id
	private String userId;
	//账号名字
	private String userName;
	//创建服务器标识
	private String serverName;
	//平台标识
	private String webName;
	//原来的名字
	private String oldname;
	//是否跨服角色
	private transient boolean cross = false;
	//原名字存在时间
	private int oldnametime;
	//版本号
	private int version;
	//玩家状态
	private transient int state;
	//删除状态
	private boolean delete;
	//封停状态
	private boolean forbid;
	//是否可以改名 0-不 1-可
	private transient byte changeName;
	//是否可以更改用户 0-不 1-可
	private transient byte changeUser;
	//性别
	private byte sex;
	//国家
	private int country;
	//玩家起跳信息
	private transient Jump jump = new Jump();
	//玩家起跳保护
	private transient boolean jumpProtect = false;
	//玩家敌对列表
	private transient HashMap<Long, Enemy> enemys = new HashMap<Long, Enemy>();
	//玩家攻击和被攻击列表
	private transient HashSet<Long> hits = new HashSet<Long>();
	//最后战斗时间
	private transient long lastFightTime;
	//身上装备
	private Equip[] equips = new Equip[12];
	//玩家头像模板id	
	private int avatarid;
	//登录IP
	private String loginIp;
	//登录时间
	private long loginTime;
	//登录网关
	private transient int gateId;
	//PK模式 0-和平 1-组队 2-帮会 3-全体
	private int pkState;
	//前PK模式  0-和平 1-组队 2-帮会 3-全体
	private int prePkState;
	//杀死该玩家的战斗者类型 1-怪物 2-玩家
	private int killerType;
	//杀死该玩家的名字
	private String killer;
	//自动挂机开始时间
	private transient long autoStartTime;
	//防沉迷状态
	private transient int nonage;
	//gm状态
	private int gmState;
	//GM等级 
	private transient int gmlevel;
	//待切换线
	private transient int changeLine;
	//坐标
	private transient Position lastPosition;
	//移动路径
	private transient List<Byte> lastRoads = new ArrayList<Byte>();
	//屏幕宽度
	private int width = 1920;
	//屏幕高度
	private int height = 1080;
	//平台参数1
	private String agentPlusdata;
	//平台参数2
	private String agentColdatas;
	//升级时间
	private long levelUpTime;
	//是否保存
	private boolean save = false;
	//计数列表
	private HashMap<String, Count> counts = new HashMap<String, Count>();
	
	private HashMap<String, String> variables = new HashMap<String, String>();
	//是否显示
	private boolean show = true;
	//移动步数
	private transient int movestep = 0;
	//作弊次数
	private transient int checkTimes = 0;
	//作弊事件
	private transient long checkTime = 0;
	//心跳作弊次数
	private transient int heartCheckTimes = 0;
	
	//------------------监狱部分begin--------------------//
	//进入监狱次数
	private int prisonTimes;
	//进入监狱时间
	private long prisonEnterTime;
	//监狱中剩余时间
	private int prisonRemainTime;
	
	//------------------显示隐藏部分begin--------------------//
	//------------------显示隐藏部分begin--------------------//
	//显示set
	private HashSet<String> showSet = new HashSet<String>();
	//隐藏set
	private HashSet<String> hideSet = new HashSet<String>();
	//------------------显示隐藏部分end--------------------//
	//------------------背包部分begin--------------------//
	//背包物品
	private ConcurrentHashMap<String, Item> backpackItems = new ConcurrentHashMap<String, Item>();
	//仓库物品
	private HashMap<String, Item> storeItems = new HashMap<String, Item>();
	// 游戏币
	private int money;
	//绑定元宝
	private int bindGold;
	// 包裹已开格子数
	private int bagCellsNum;
	// 仓库已开格子数
	private int storeCellsNum;
	// 包裹开格时间统计
	private int bagCellTimeCount;
	// 仓库开格时间统计
	private int storeCellTimeCount;
	// 跨服包裹	
	private Bag globalBag = new Bag();
	
	/**
	 * 今天第一次使用修为丹的等级
	 */
	private int dayFirstUseXiuWeiDanGrade;
	
	/**
	 * 最后一次使用修为丹的时间
	 */
	private long lastUseXiuWeiDanTime;
	
	
	//是否已发送到时间点消息
	private transient boolean isSendBagOpenCellTime = false;
	private transient boolean isSendStoreOpenCellTime = false;
	//------------------背包部分end----------------------//
	//-------------------交易部分------------------------//
	//回购列表
	private List<Item> buybackList = new ArrayList<Item>();
	//-------------------交易部分end---------------------//
	//------------------技能部分begin--------------------//
	private List<Skill> skills = new ArrayList<Skill>();
	//当前领悟中的技能
	private int nowLearnSkillId;
	//领悟时间
	private int skillLearnTime;
	//默认技能
	private int defaultSkill;
	//技能总层数
	private int totalSkillLevel;
	//技能升级时间
	private long skillUpTime;
	//------------------技能部分end----------------------//
	//-------------------聊天部分-------------------------//
	//普通最后聊天时间
	private transient long lastSceneChatTime = 0;
	//私聊最后聊天时间
	private transient long lastPrivateChatTime = 0;
	//组队聊最后聊天时间
	private transient long lastTeamChatTime = 0;
	//帮会聊最后聊天时间
	private transient long lastGroupChatTime = 0;
	//世界聊最后聊天时间
	private transient long lastWorldChatTime = 0;
	//喇叭聊最后聊天时间
	private transient long lastLabaChatTime = 0;
	
	private transient long lastGmChatTime=0;
	
	private transient HashMap<String,ChatCountBean> chatCount=new HashMap<String, ChatCountBean>();
	
	private int autojinyancount=0;
	
	//禁言时间 毫秒
	private long prohibitChatTime = 0;
	//禁言开始时间
	private long startProhibitChatTime = 0;
	//自动禁言检查起始时间点
	private long addBlackTime = 0;
	//被加入黑名单的次数
	private int addBlackCount = 0;
	//-------------------聊天部分end----------------------//
	//-------------------好友部分-------------------------//
	//黑名单
	private List<Long> blackRoleList = new ArrayList<Long>();
	//-------------------属性部分-------------------------//
	private transient HashMap<Integer, PlayerAttribute> attributes = new HashMap<Integer, PlayerAttribute>();
	//其他属性
	private PlayerAttribute otherAttribute = new PlayerAttribute();
	//-------------------属性部分end-------------------------//
	//-------------------宠物部分--------------------------//
	//个人拥有的宠物列表
	private List<Pet> petList = new ArrayList<Pet>();
	//-------------------宠物部分end--------------------------//
	//快捷栏
	private ShortCut[] shortCuts = new ShortCut[20];
	//经验值
	private long exp;
	//真气
	private int zhenqi;
	//战场声望
	private int prestige;
	//死亡时间
	private long dieTime;
	//原地复活开始时间
	private long placerevivetime;
	//---------------龙元心法-------------------
	//龙元心法阶段
	private LongYuanData longyuan = new LongYuanData();
	//龙元心法升级时间
	private long longyuanUpTime;
	//龙元心法计时
	private transient long longyuantime;
	//龙元心法临时保存要升级的阶段
	private transient byte longyuanactlv;
	//龙元心法临时保存要升级的阶段
	private transient byte longyuanactnum;
	//--------------队伍--------------------
	//队伍ID
	private transient long teamid;
	//是否可被自动邀请加入
	private byte autoteaminvited;
	//队长设置是否允许自动加入队伍
	private byte autoIntoteamapply;
	//-----------------交易和摆摊--------------------
	//交易信息
	private transient Transactions transactionsinfo = null;
	//可领取元宝（当正常元宝为0，则交易元宝存到这里）
	private int canreceiveyuanbao;
	//自动拒绝交易
	private transient byte autorefusaldeal;
	//摆摊20条日志
	private List<StallsTransactionLog> stallsloglist = new ArrayList<StallsTransactionLog>();
	//临时 可领取元宝的记录
	private List<TempYuanbaoLogData> tempyuanbaoLogdata = new ArrayList<TempYuanbaoLogData>();

	//--------------------好友信息----------------//
	//心情
	private String mood;
	//是否公开我的地图位置
	private byte openMapLocation;
	//是否显示不在线的好友或仇人
	private byte showrelation;
	//是否在列表中显示头像
	private byte showicon;
	//-----------------打座双修部分----------------//
	//打座状态 0是正常  1是打座  2是双修打座
	private transient byte dazuoState;
	//打座对象
	private transient long dazuotarget;
	//开始打座的时间
	private transient long dazuoBeginTime;
	//打座爆击次数
	private transient int dazuoEruptCount;
	//爆击获得经验
	private transient int dazuoEruptExp;
	//爆击获得真气
	private transient int dazuoEruptZq;
	private transient int dazuoExp;
	private transient int dazuoZq;
	//其它玩家发来的请求列表<玩家ID，请求时间>
	private transient HashMap<Long, Long> dazuoAcceptList = new HashMap<Long, Long>();
	//-----------------打座双修部分end----------------//
	//-----------------活动奖励部分----------------//
	private HashMap<String, String> activitiesReward = new HashMap<String, String>();
	private transient int repeatNum;		//重复次数
	
	public int getRepeatNum() {
		return repeatNum;
	}

	public void setRepeatNum(int repeatNum) {
		this.repeatNum = repeatNum;
	}
	//上次领取封测礼金时间
	private long lastReceiveGiftTime = 0;
	//-----------------活动奖励部分end----------------//
	//-----------------副本保存信息部分----------------//
	private HashMap<String, String> zoneSaveInfoMap = new HashMap<String, String>();
	//-----------------副本保存信息部分----------------//
	//-----------------采集信息部分----------------//
	//采集开始时间
	private transient long gatherStarttime;
	//采集对象Id
	private transient long gatherId;
	//采集耗时
	private transient long gatherCosttime;
	//-----------------采集信息部分----------------//
	//-----------------登陆领奖信息部分----------------//
	//上次增加登陆测试有效时间
	private long lastAddLoginTime;
	//最大连续登陆天数
	private int maxLoginTimes;
	//当前连续登陆天数
	private int loginTimes;
	//-----------------登陆领奖信息部分----------------//
	//------------------迷宫部分begin--------------------//
	//进入迷宫时间
	private long enterTime;
	//通过时间
	private int passTime;
	//排名
	private int sort;
	//前进类型 1-正确进入， 2-倒退
	private int transType;
	//------------------迷宫部分end--------------------//
	
	//比武岛累计经验值
	private transient int biwudaototalexp;
	//比武岛累计真气值
	private transient int biwudaototalzhenqi;
	//比武岛累计宝箱数量
	private transient int biwudaototalBox;
	//比武岛累计军功值
	private int biwudaototalrank;
	//比武岛参与时间（天）
	private int biwudaoday;
	
	//是否己创建更新日志
	private volatile boolean updatelogtag=false;
	//境界
	private Realm realm = new Realm();
	
	//返还礼金总额
	private double retbindgoldsum;
	//返还礼金当前数量
	private double retcurrbindgold;
	//返还礼金活动ID
	private String retbindgoldid;
	//返还礼金活动每日领取日期记录
	private int retbindgoldday;
	
	//结婚ID
	private long marriageid;
	//离婚标记（存入结婚ID）
	private transient long divorceid;
	//婚戒ID
	private int weddingringid;
	//手机版本礼包领取索引
	private int mobileGiftIndex = 0;
	
	public HashMap<String, String> getZoneSaveInfoMap() {
		return zoneSaveInfoMap;
	}

	public void setZoneSaveInfoMap(HashMap<String, String> zoneSaveInfoMap) {
		this.zoneSaveInfoMap = zoneSaveInfoMap;
	}
	//-----------------副本保存信息部分----------------//
	//-----------------礼包奖励部分----------------//
	private GiftSaveInfo newGiftSaveInfo = new GiftSaveInfo();	//保存当前的
	private GiftSaveInfo oldGiftSaveInfo = new GiftSaveInfo();	//保存已经领取的
	private HashMap<String, Integer> getGiftMap = new HashMap<String, Integer>();	//获得了的礼包Map(Key, 类型)

	public HashMap<String, Integer> getGetGiftMap() {
		return getGiftMap;
	}

	public void setGetGiftMap(HashMap<String, Integer> getGiftMap) {
		this.getGiftMap = getGiftMap;
	}

	public GiftSaveInfo getNewGiftSaveInfo() {
		return newGiftSaveInfo;
	}

	public void setNewGiftSaveInfo(GiftSaveInfo newGiftSaveInfo) {
		this.newGiftSaveInfo = newGiftSaveInfo;
	}

	public GiftSaveInfo getOldGiftSaveInfo() {
		return oldGiftSaveInfo;
	}

	public void setOldGiftSaveInfo(GiftSaveInfo oldGiftSaveInfo) {
		this.oldGiftSaveInfo = oldGiftSaveInfo;
	}
	//-----------------礼包奖励部分end----------------//
	private transient Gold gold;

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public byte getOpenMapLocation() {
		return openMapLocation;
	}

	public void setOpenMapLocation(byte openMapLocation) {
		this.openMapLocation = openMapLocation;
	}

	public byte getShowicon() {
		return showicon;
	}

	public void setShowicon(byte showicon) {
		this.showicon = showicon;
	}

	public byte getShowrelation() {
		return showrelation;
	}

	public void setShowrelation(byte showrelation) {
		this.showrelation = showrelation;
	}
	//--------------------邮件列表----------------//
	private transient HashMap<Long, MailDetailInfoData> maildataMap = new HashMap<Long, MailDetailInfoData>();

	public HashMap<Long, MailDetailInfoData> getMaildataMap() {
		return maildataMap;
	}

	public void setMaildataMap(HashMap<Long, MailDetailInfoData> maildataMap) {
		this.maildataMap = maildataMap;
	}
	//--------------------帮会信息----------------//
	//自己帮会ID
	private transient long guildId;
	//自己帮会信息
	private transient GuildInfo guildInfo = new GuildInfo();
	//帮会自己成员信息
	private transient MemberInfo memberInfo = new MemberInfo();
	//王城宝箱保存信息
	private KingCityChest kingCityChest = new KingCityChest();
	//弓箭阶数
	private byte arrowLevel;
	//天元阶数
	private byte tianyuanLevel;
	//声望点
	private int prestigePoint;
	//成就点
	private int achievementPoint;
	//战斗力
	private int fightPower;
	//自动同意加入帮会
	private byte autoArgeeAddGuild;
	//离开帮会时间
	private long lastAfkGuildTime;
	//帮贡点
	private int contributionPoint;
	//游戏设置菜单，服务端状态保存
	private int menustatus;
	//游戏设置菜单，客户端状态保存
	private int clientset;
	//装备强化状态保存
	private EquipStreng equipStreng = new EquipStreng();
	//身上宝石10个部位，每个部位5颗宝石
	private Gem[][] gems = new Gem[10][5];
	//是否开放高阶宝石，0不开放，1开放
	private byte isopenhighgem;
	//副本扫荡信息
	private Raid raidinfo = new Raid();
	//副本扫荡翻牌奖励列表储存
	private RaidFlop raidflop = new RaidFlop();
	//副本连续扫荡列表
	private List<ContinuousRaidsInfo> raidcontinuouslist = new ArrayList<ContinuousRaidsInfo>();
	//副本连续扫荡开始时间
	private int  raidcontinuoustime ;
	
	//七曜副本连续扫荡列表
	private List<ContinuousRaidsInfo> qiyaocontinuouslist = new ArrayList<ContinuousRaidsInfo>();
	//七曜副本连续扫荡开始时间
	private int  qiyaocontinuoustime ;
	
	
	//副本奖励次数储存
	private HashMap<String, Integer> zonerewardmap = new HashMap<String, Integer>();
	//多人副本奖励次数储存
	private HashMap<String, Integer> zoneteamrewardmap = new HashMap<String, Integer>();
	//副本胜利次数储存，发奖励的时候用
	private HashMap<String, Integer> zonevictorymap = new HashMap<String, Integer>();
	
	//七耀战将副本奖励次数储存
	private HashMap<String, Integer> zoneqiyaorewardmap = new HashMap<String, Integer>();
	
	//副本通关数据储存
	private HashMap<String, Integer> zoneinfo = new HashMap<String, Integer>();
	
	//组队进入副本唯一ID临时储存
	private transient long zoneteamenterid;
	
	//连斩攻击力保存
	private int evencutatk;
	//连斩次数
	private int evencutnum;
	//连斩时间
	private long evencuttime;
	//连斩等级（数据库索引）
	private int evencutdblv;
	//连斩BUFF时间
	private long evencutbufftime;
	//连击BOSS次数
	private int bossbatternum;
	//连击BOSS冷却时间
	private long bossbattertime;
	//历史最大连斩数
	private int maxEventcut;
	//历史最大连斩数时间
	private long maxEventcutTime;
	//连斩发生地图ID
	private int evencutmapid;
	//最后连斩的怪物ID
	private int evencutmonid;
	//连斩发生坐标X
	private short evencutmapx;
	//连斩发生坐标Y
	private short evencutmapy;
	
	//面板点击验证
	private transient HashMap<String,String> panelverify  = new HashMap<String,String>();
	
	//NPC对话验证
	private transient HashSet<String> npcverify = new HashSet<String>();
	//地宫寻宝保存
	private Epalace epalace = new Epalace();
	//称号id列表
	private transient List<Integer> titleidlist = new ArrayList<Integer>();
	//当前称号id
	private int toptitleid;
	//退出副本回到原地图
	private int formermapid;
	//退出副本回到原线
	private int formerline;
	//退出副本回到坐标
	private Position formerposition;
	//攻城战 时间奖励记录(秒)
	private int kingcityrewtime;
	//攻城战 清理标记
	private int kingcityrewday;
	//攻城战 打坐累计经验
	private int kingcityexp;
	//攻城战 打坐累计真气 
	private int kingcityzq;
	//上次闭关领取时间
	private long retreatGetAwardTime;
	
	//每个月领取工资的累计时间（秒）
	private List<Wage> wagelist =new ArrayList<Wage>();
	//每个月签到记录和奖励领取记录
	private HashMap<String,Sign> signmap =new HashMap<String,Sign>();

	//累计签到次数
	private int signsum;
	//军衔等级
	private int ranklevel;
	//军功数量
	private int rankexp;
	//临时保存战斗力差值
	private transient int  oldfightpower;
	//套装升级提示公告保存
	private int equipbulletin;
	//套装升级紫色提示公告保存
	private int equipquality;
	//-------------弓箭系统----------------//
	private ArrowData arrowData = new ArrowData();	//弓箭数据
	//-------------宝箱系统----------------//
	private ChestBoxData chestBoxData = new ChestBoxData();	//宝箱数据
	
	//进副本  分组标识
	private transient int groupmark ;
	
	//商店限购记录
	private HashMap<String,Integer> shoplimitmap =new HashMap<String,Integer>();
	//系统邮件记录
	private HashMap<String,String> sysmailmap =new HashMap<String,String>();
	//春联激活状态
	private HashMap<String,List<Integer>> coupletmap =new HashMap<String,List<Integer>>();
	
	
	//二级保护IP记录
	private transient String protectip; 
	//二级保护时间记录
	private transient long protecttime; 
	//二级保护个人状态：0默认弹出，1暂时屏蔽弹出设置窗口
	private transient int protectstatus; 
	//二级保护密码
	private transient String protectpassword; 
	//二级保护邮箱
	private transient String protectmail; 
	//二级保护验证码
	private transient String protectPIN; 
	//二级保护验证码冷却时间
	private transient long protectpincooldown; 
	
	
	public ChestBoxData getChestBoxData() {
		return chestBoxData;
	}

	public void setChestBoxData(ChestBoxData chestBoxData) {
		this.chestBoxData = chestBoxData;
	}

	public ArrowData getArrowData() {
		return arrowData;
	}

	public void setArrowData(ArrowData arrowData) {
		this.arrowData = arrowData;
	}

	public long getRetreatGetAwardTime() {
		return retreatGetAwardTime;
	}

	public void setRetreatGetAwardTime(long retreatGetAwardTime) {
		this.retreatGetAwardTime = retreatGetAwardTime;
	}

	public int getToptitleid() {
		return toptitleid;
	}

	public void setToptitleid(int toptitleid) {
		this.toptitleid = toptitleid;
	}

	public long getGuildId() {
		return guildId;
	}

	public void setGuildId(long guildId) {
		this.guildId = guildId;
	}

	public GuildInfo getGuildInfo() {
		return guildInfo;
	}

	public void setGuildInfo(GuildInfo guildInfo) {
		if (this.guildInfo == null) {
			this.guildInfo = new GuildInfo();
		}
		try {
			BeanUtils.copyProperties(this.guildInfo, guildInfo);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			java.util.logging.Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public MemberInfo getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(MemberInfo memberInfo) {
		if (this.memberInfo == null) {
			this.memberInfo = new MemberInfo();
		}
		try {
			BeanUtils.copyProperties(this.memberInfo, memberInfo);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			java.util.logging.Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public KingCityChest getKingCityChest() {
		return kingCityChest;
	}

	public void setKingCityChest(KingCityChest kingCityChest) {
		this.kingCityChest = kingCityChest;
	}

	public int getAchievementPoint() {
		return achievementPoint;
	}

	public void setAchievementPoint(int achievementPoint) {
		this.achievementPoint = achievementPoint;
	}

	public byte getArrowLevel() {
		return arrowLevel;
	}

	public void setArrowLevel(byte arrowLevel) {
		this.arrowLevel = arrowLevel;
	}

	public byte getAutoArgeeAddGuild() {
		return autoArgeeAddGuild;
	}

	public void setAutoArgeeAddGuild(byte autoArgeeAddGuild) {
		this.autoArgeeAddGuild = autoArgeeAddGuild;
	}

	public int getContributionPoint() {
		return contributionPoint;
	}

	public void setContributionPoint(int contributionPoint) {
		this.contributionPoint = contributionPoint;
	}

	public int getFightPower() {
		return fightPower;
	}

	public void setFightPower(int fightPower) {
		this.fightPower = fightPower;
	}

	public long getLastAfkGuildTime() {
		return lastAfkGuildTime;
	}

	public void setLastAfkGuildTime(long lastAfkGuildTime) {
		this.lastAfkGuildTime = lastAfkGuildTime;
	}

	public int getPrestigePoint() {
		return prestigePoint;
	}

	public void setPrestigePoint(int prestigePoint) {
		this.prestigePoint = prestigePoint;
	}

	public byte getTianyuanLevel() {
		return tianyuanLevel;
	}

	public void setTianyuanLevel(byte tianyuanLevel) {
		this.tianyuanLevel = tianyuanLevel;
	}
	//-------------------点绛唇-------------------------//
	private long lgDianjiangchunDay;
	private DianjiangchunSaveData stDianjiangchunSaveData = new DianjiangchunSaveData();
	//-------------------坐骑---------------------------//
	private List<Horse> horselist = new ArrayList<Horse>();
	//死亡回城复活后自动上马,1自动上马
	private transient byte autohorse;
	//骑乘状态计时（5秒倒计时）
	private transient long ridingtime;
	//-------------------坐骑---------------------------//
	private List<HorseWeapon> horseweaponlist = new ArrayList<HorseWeapon>();
	//-------------------暗器---------------------------//
	private List<HiddenWeapon> hiddenweaponlist = new ArrayList<HiddenWeapon>();
	public List<HiddenWeapon> getHiddenweaponlist() {
		return hiddenweaponlist;
	}

	public void setHiddenweaponlist(List<HiddenWeapon> hiddenweaponlist) {
		this.hiddenweaponlist = hiddenweaponlist;
	}
	//--------------------玩家时间----------------------------
	//累计在线总时间
	private int accunonlinetime;
	//今日累计在线时间
	private int dayonlinetime;
	//当前日期 （标记今日累计在线时间用）
	private int curday;
	//累计时间-上线时刻-记录
	private int loginlinetime;
	//-------------------任务---------------------------//
	//当前未完成的主线任务
	private List<MainTask> currentMainTasks = new ArrayList<MainTask>();
	
	private int currentMainTaskId=0;
	
	//当前未完成的日常任务
	private List<DailyTask> currentDailyTasks = new ArrayList<DailyTask>();
	//当前未完成的讨伐任务
	private List<ConquerTask> currentConquerTasks = new ArrayList<ConquerTask>();
	//当前未完成的寻宝任务 
	private List<TreasureHuntTask> currentTreasureHuntTasks = new ArrayList<TreasureHuntTask>();
	//己完成的主线任务 这里只存模型 ID
	private List<Integer> finishedTasks = new ArrayList<Integer>();
	//当前没完成的军衔任务
	private List<RankTask> currentRankTasks = new ArrayList<RankTask>();
	//已完成的军衔任务 这里只存模型 ID
	private List<Integer> finishedRankTasks = new ArrayList<Integer>();
	//军衔任务接取统计
	private int rankTaskCount = 0;
	private long rankTaskTime = 0;
	//日常任务接取统计
	private int dailyTaskCount = 0;
	private long dailyTaskTime = 0;
	//讨伐任务每日接取统计
	private int conquerTaskCount = 0;
	private int conquerTaskMaxCount = 0;
	//当天第一次接取讨伐任务的时间
	private long conquerTaskTime = 0;
	//当日吞噬次数
	private int daydevourcount = 0;
	//当日第一次吞噬时间
	private long daydevourTime = 0;
	//退出游戏时间
	private int quitTime;
	//任务重置
	private boolean taskclear = false;
	//任务 可领取区域
	private List<Item> taskRewardsReceiveAble = new ArrayList<Item>();
	//一键完成序列
	private transient List<DailyTask> supperFinshSerial = new ArrayList<DailyTask>();
	//最优完成序列
	private transient List<DailyTask> supperOptimalFinshSerial = new ArrayList<DailyTask>();
	//延时任务队列
	private transient LinkedList<LaterTask> laterList = new LinkedList<LaterTask>();
	private boolean isActivityDailyTask = false;
	private long lastCheckZeroClockTimer=0;
	public long getLastCheckZeroClockTimer() {
		return lastCheckZeroClockTimer;
	}

	public void setLastCheckZeroClockTimer(long lastCheckZeroClockTimer) {
		this.lastCheckZeroClockTimer = lastCheckZeroClockTimer;
	}
	//------------------任务结束-------------------------//
	//挂机设置
	private PlugSet plugset = new PlugSet();
	//-----------------玩家统计信息部分--------------------//
	private long totalgetbindgold = 0;
	private long totalconsumebindgold = 0;
	private long totalgetmoney = 0;
	private long totalconsumemoney = 0;
	private long lastSaveFinancialTime = 0; //上次保存财务的时间

	public long getLastSaveFinancialTime() {
		return lastSaveFinancialTime;
	}

	public void setLastSaveFinancialTime(long lastSaveFinancialTime) {
		this.lastSaveFinancialTime = lastSaveFinancialTime;
	}

	public long getTotalgetbindgold() {
		return totalgetbindgold;
	}

	public void setTotalgetbindgold(long totalgetbindgold) {
		this.totalgetbindgold = totalgetbindgold;
	}

	public long getTotalconsumebindgold() {
		return totalconsumebindgold;
	}

	public void setTotalconsumebindgold(long totalconsumebindgold) {
		this.totalconsumebindgold = totalconsumebindgold;
	}

	public long getTotalconsumemoney() {
		return totalconsumemoney;
	}

	public void setTotalconsumemoney(long totalconsumemoney) {
		this.totalconsumemoney = totalconsumemoney;
	}

	public long getTotalgetmoney() {
		return totalgetmoney;
	}

	public void setTotalgetmoney(long totalgetmoney) {
		this.totalgetmoney = totalgetmoney;
	}
	
	//-----------------玩家统计信息部分结束----------------//
	
	//-----------------玩家收藏品BEGIN----------------//
	
	
	private Collect collect=new Collect();
	
	
	
	//-----------------玩家收藏品END----------------//
	
	

	public Collect getCollect() {
		return collect;
	}

	public void setCollect(Collect collect) {
		this.collect = collect;
	}
	//
	//-------------------VIP特权----------------------
	private VipRight vipright = new VipRight();

	public VipRight getVipright() {
		return vipright;
	}

	public void setVipright(VipRight vipright) {
		this.vipright = vipright;
	}
	//角色是否领取过首冲奖励
	private int receivedFirstRecharge=0; 
	public int getReceivedFirstRecharge() {
		return receivedFirstRecharge;
	}

	public void setReceivedFirstRecharge(int receivedFirstRecharge) {
		this.receivedFirstRecharge = receivedFirstRecharge;
	}
	//排行榜宝箱  key=chesttype  value=lastrecieveid
	private Map<String, Integer> chestRecievedMap = new HashMap<String, Integer>();

	public Map<String, Integer> getChestRecievedMap() {
		return chestRecievedMap;
	}

	public void setChestRecievedMap(Map<String, Integer> chestRecievedMap) {
		this.chestRecievedMap = chestRecievedMap;
	}

	public DianjiangchunSaveData getStDianjiangchunSaveData() {
		return stDianjiangchunSaveData;
	}

	public List<Item> getTaskRewardsReceiveAble() {
		return taskRewardsReceiveAble;
	}

	public void setTaskRewardsReceiveAble(List<Item> taskRewardsReceiveAble) {
		this.taskRewardsReceiveAble = taskRewardsReceiveAble;
	}

	public List<MainTask> getCurrentMainTasks() {
		return currentMainTasks;
	}

	public void setCurrentMainTasks(List<MainTask> currentMainTasks) {
		this.currentMainTasks = currentMainTasks;
	}

	public List<DailyTask> getCurrentDailyTasks() {
		return currentDailyTasks;
	}

	public void setCurrentDailyTasks(List<DailyTask> currentDailyTasks) {
		this.currentDailyTasks = currentDailyTasks;
	}

	public List<ConquerTask> getCurrentConquerTasks() {
		return currentConquerTasks;
	}

	public void setCurrentConquerTasks(List<ConquerTask> currentConquerTasks) {
		this.currentConquerTasks = currentConquerTasks;
	}

	public List<TreasureHuntTask> getCurrentTreasureHuntTasks() {
		return currentTreasureHuntTasks;
	}

	public void setCurrentTreasureHuntTasks(
		List<TreasureHuntTask> currentTreasureHuntTasks) {
		this.currentTreasureHuntTasks = currentTreasureHuntTasks;
	}

	public LinkedList<LaterTask> getLaterList() {
		return laterList;
	}

	public void setLaterList(LinkedList<LaterTask> laterList) {
		this.laterList = laterList;
	}

	public long getAutoStartTime() {
		return autoStartTime;
	}

	public void setAutoStartTime(long autoStartTime) {
		this.autoStartTime = autoStartTime;
	}

	public List<Integer> getFinishedTasks() {
		return finishedTasks;
	}

	public void setFinishedTasks(List<Integer> finishedTasks) {
		this.finishedTasks = finishedTasks;
	}

	public List<RankTask> getCurrentRankTasks() {
		return currentRankTasks;
	}

	public void setCurrentRankTasks(List<RankTask> currentRankTasks) {
		this.currentRankTasks = currentRankTasks;
	}

	public List<Integer> getFinishedRankTasks() {
		return finishedRankTasks;
	}

	public void setFinishedRankTasks(List<Integer> finishedRankTasks) {
		this.finishedRankTasks = finishedRankTasks;
	}

	public int getRankTaskCount() {
		return rankTaskCount;
	}

	public void setRankTaskCount(int rankTaskCount) {
		this.rankTaskCount = rankTaskCount;
	}

	public long getRankTaskTime() {
		return rankTaskTime;
	}

	public void setRankTaskTime(long rankTaskTime) {
		this.rankTaskTime = rankTaskTime;
	}

	public int getDailyTaskCount() {
		return dailyTaskCount;
	}

	public void setDailyTaskCount(int dailyTaskCount) {
		this.dailyTaskCount = dailyTaskCount;
	}

	public int getConquerTaskCount() {
		return conquerTaskCount;
	}

	public void setConquerTaskCount(int conquerTaskCount) {
		this.conquerTaskCount = conquerTaskCount;
	}

	public int getConquerTaskMaxCount() {
		return conquerTaskMaxCount;
	}

	public void setConquerTaskMaxCount(int conquerTaskMaxCount) {
		this.conquerTaskMaxCount = conquerTaskMaxCount;
	}

	public long getConquerTaskTime() {
		return conquerTaskTime;
	}

	public void setConquerTaskTime(long conquerTaskTime) {
		this.conquerTaskTime = conquerTaskTime;
	}

	public long getDailyTaskTime() {
		return dailyTaskTime;
	}

	public void setDailyTaskTime(long dailyTaskTime) {
		this.dailyTaskTime = dailyTaskTime;
	}

	public void setStDianjiangchunSaveData(DianjiangchunSaveData stDianjiangchunSaveData) {
		this.stDianjiangchunSaveData = stDianjiangchunSaveData;
	}

	public long getLgDianjiangchunDay() {
		return lgDianjiangchunDay;
	}

	public void setLgDianjiangchunDay(long lgDianjiangchunDay) {
		this.lgDianjiangchunDay = lgDianjiangchunDay;
	}
	


	public int getCreateServerId() {
		return createServerId;
	}

	public void setCreateServerId(int createServerId) {
		this.createServerId = createServerId;
	}

	public int getMoney() {
		return money;
	}

	/**
	 * 只有背包类可以使用 如果需要更改 请调用背包类的API
	 * @Annotater(mail="lzrzhao@gmail.com",name="赵聪慧")
	 * {@link BackpackManager}
	 *
	 * @param money
	 */
	@Deprecated
	public void setMoney(int money) {
		this.money = money;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getState() {
		return state;
	}

	public void setState(PlayerState state) {
		int old = this.state;

		this.state = this.state & state.getMark();
		this.state = this.state | state.getValue();

		if (PlayerState.FIGHT.getValue() == state.getValue()) {
			this.lastFightTime = System.currentTimeMillis();
			log.debug("in fight state:" + this.lastFightTime);
			if (!PlayerState.FIGHT.compare(old)) {
				log.debug("send in fight state:" + System.currentTimeMillis());
				ResPlayerStateChangeMessage msg = new ResPlayerStateChangeMessage();
				msg.setState((byte) 0);
				MessageUtil.tell_player_message(this, msg);
			}
		} else {
			if (PlayerState.FIGHT.compare(old) && !PlayerState.FIGHT.compare(this.state)) {
				log.debug("send out fight state:" + System.currentTimeMillis());
				ResPlayerStateChangeMessage msg = new ResPlayerStateChangeMessage();
				msg.setState((byte) 1);
				MessageUtil.tell_player_message(this, msg);
			}
		}
//		PlayerState oldState = PlayerState.STAND;
//		if(PlayerState.RUN.compare(old)){
//			oldState = PlayerState.RUN;
//		}else if(PlayerState.JUMP.compare(old)){
//			oldState = PlayerState.JUMP;
//		}else if(PlayerState.DOUBLEJUMP.compare(old)){
//			oldState = PlayerState.DOUBLEJUMP;
//		}else if(PlayerState.BLOCKPREPARE.compare(old)){
//			oldState = PlayerState.BLOCKPREPARE;
//		}else if(PlayerState.BLOCK.compare(old)){
//			oldState = PlayerState.BLOCK;
//		}else if(PlayerState.SIT.compare(old)){
//			oldState = PlayerState.SIT;
//		}else if(PlayerState.SWIM.compare(old)){
//			oldState = PlayerState.SWIM;
//		}
//		if(getName().equals("[1区]陆听荷"))
//		if(state.getMark() == PlayerState.STAND.getMark()) log.debug("由"+oldState+"切换到"+state+BeanUtil.getStack());
	}

	public int getGateId() {
		return gateId;
	}

	public void setGateId(int gateId) {
		this.gateId = gateId;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public Jump getJump() {
		return jump;
	}

	public void setJump(Jump jump) {
		this.jump = jump;
	}

	/**
	 * @Annotater(mail="lzrzhao@gmail.com",name="赵聪慧") 只允许包裹管理类调用
	 * 如需实现相关功能请在BackPackManager增加相应API
	 * {@link BackpackManager}
	 *
	 * @return
	 */
	@Deprecated
	public ConcurrentHashMap<String, Item> getBackpackItems() {
		return backpackItems;
	}

	/**
	 * 只允许包裹管理类调用 如需实现相关功能请在BackPackManager增加相应API
	 * @Annotater(mail="lzrzhao@gmail.com",name="赵聪慧")
	 * {@link BackpackManager}
	 *
	 * @param backpackItems
	 */
	@Deprecated
	public void setBackpackItems(ConcurrentHashMap<String, Item> backpackItems) {
		this.backpackItems = backpackItems;
	}

	/**
	 * 只允许商店管理类调用 如需实现相关功能请在BackPackManager增加相应API
	 * @Annotater(mail="lzrzhao@gmail.com",name="赵聪慧")
	 * {@link ShopManager}
	 *
	 * @return
	 */
	@Deprecated
	public HashMap<String, Item> getStoreItems() {
		return storeItems;
	}

	/**
	 * 只允许商店管理类调用 如需实现相关功能请在BackPackManager增加相应API
	 * @Annotater(mail="lzrzhao@gmail.com",name="赵聪慧")
	 *  {@link ShopManager}
	 *
	 * @param storeItems
	 */
	@Deprecated
	public void setStoreItems(HashMap<String, Item> storeItems) {
		this.storeItems = storeItems;
	}

	public Equip[] getEquips() {
		return equips;
	}

	public void setEquips(Equip[] equips) {
		this.equips = equips;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public ShortCut[] getShortCuts() {
		return shortCuts;
	}

	public void setShortCuts(ShortCut[] shortCuts) {
		this.shortCuts = shortCuts;
	}

	public HashMap<Long, Enemy> getEnemys() {
		return enemys;
	}

	public void setEnemys(HashMap<Long, Enemy> enemys) {
		this.enemys = enemys;
	}

	public long getLastFightTime() {
		return lastFightTime;
	}

	public void setLastFightTime(long lastFightTime) {
		this.lastFightTime = lastFightTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public int getDefaultSkill() {
		return defaultSkill;
	}

	public void setDefaultSkill(int defaultSkill) {
		this.defaultSkill = defaultSkill;
	}

	public long getExp() {
		return exp;
	}

	public void setExp(long exp) {
		this.exp = exp;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isForbid() {
		return forbid;
	}

	public void setForbid(boolean forbid) {
		this.forbid = forbid;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public int getBindGold() {
		return bindGold;
	}

	/**
	 * 只有背包类可以使用 如果需要更改 请调用背包类的API
	 * @Annotater(mail="lzrzhao@gmail.com",name="赵聪慧")
	 *  {@link ShopManager}
	 *
	 * @param bindGold
	 */
	@Deprecated
	public void setBindGold(int bindGold) {
		this.bindGold = bindGold;
	}

	public int getZhenqi() {
		return zhenqi;
	}

	public void setZhenqi(int zhenqi) {
		this.zhenqi = zhenqi;
	}

	public int getPrestige() {
		return prestige;
	}

	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	public long getDieTime() {
		return dieTime;
	}

	public void setDieTime(long dieTime) {
		this.dieTime = dieTime;
	}

	public int getBagCellsNum() {
		return bagCellsNum;
	}

	public void setBagCellsNum(int bagCellsNum) {
		this.bagCellsNum = bagCellsNum;
	}

	public int getStoreCellsNum() {
		return storeCellsNum;
	}

	public void setStoreCellsNum(int storeCellsNum) {
		this.storeCellsNum = storeCellsNum;
	}

	public int getBagCellTimeCount() {
		return bagCellTimeCount;
	}

	public void setBagCellTimeCount(int bagCellTimeCount) {
		this.bagCellTimeCount = bagCellTimeCount;
	}

	public int getStoreCellTimeCount() {
		return storeCellTimeCount;
	}

	public void setStoreCellTimeCount(int storeCellTimeCount) {
		this.storeCellTimeCount = storeCellTimeCount;
	}

	public int getNowLearnSkillId() {
		return nowLearnSkillId;
	}

	public void setNowLearnSkillId(int nowLearnSkillId) {
		this.nowLearnSkillId = nowLearnSkillId;
	}

	public int getSkillLearnTime() {
		return skillLearnTime;
	}

	public void setSkillLearnTime(int skillLearnTime) {
		this.skillLearnTime = skillLearnTime;
	}

	public List<Item> getBuybackList() {
		return buybackList;
	}

	public void setBuybackList(List<Item> buybackList) {
		this.buybackList = buybackList;
	}

	public long getLastSceneChatTime() {
		return lastSceneChatTime;
	}

	public void setLastSceneChatTime(long lastSceneChatTime) {
		this.lastSceneChatTime = lastSceneChatTime;
	}

	public long getLastPrivateChatTime() {
		return lastPrivateChatTime;
	}

	public void setLastPrivateChatTime(long lastPrivateChatTime) {
		this.lastPrivateChatTime = lastPrivateChatTime;
	}

	public long getLastTeamChatTime() {
		return lastTeamChatTime;
	}

	public void setLastTeamChatTime(long lastTeamChatTime) {
		this.lastTeamChatTime = lastTeamChatTime;
	}

	public long getLastGroupChatTime() {
		return lastGroupChatTime;
	}

	public void setLastGroupChatTime(long lastGroupChatTime) {
		this.lastGroupChatTime = lastGroupChatTime;
	}

	public long getLastWorldChatTime() {
		return lastWorldChatTime;
	}

	public void setLastWorldChatTime(long lastWorldChatTime) {
		this.lastWorldChatTime = lastWorldChatTime;
	}

	public long getLastLabaChatTime() {
		return lastLabaChatTime;
	}

	public void setLastLabaChatTime(long lastLabaChatTime) {
		this.lastLabaChatTime = lastLabaChatTime;
	}

	public List<Long> getBlackRoleList() {
		return blackRoleList;
	}

	public void setBlackRoleList(List<Long> blackRoleList) {
		this.blackRoleList = blackRoleList;
	}

	public long getProhibitChatTime() {
		return prohibitChatTime;
	}

	public void setProhibitChatTime(long prohibitChatTime) {
		this.prohibitChatTime = prohibitChatTime;
	}

	public long getStartProhibitChatTime() {
		return startProhibitChatTime;
	}

	public void setStartProhibitChatTime(long startProhibitChatTime) {
		this.startProhibitChatTime = startProhibitChatTime;
	}

	public long getAddBlackTime() {
		return addBlackTime;
	}

	public void setAddBlackTime(long addBlackTime) {
		this.addBlackTime = addBlackTime;
	}

	public int getAddBlackCount() {
		return addBlackCount;
	}

	public void setAddBlackCount(int addBlackCount) {
		this.addBlackCount = addBlackCount;
	}

	public boolean isDie() {
		return PlayerState.DIE.compare(this.getState());
	}

	public void addPet(Pet e) {
		petList.add(e);
	}

	public List<Pet> getPetList() {
		return petList;
	}

	public void setPetList(List<Pet> petList) {
		this.petList = petList;
	}

	public HashMap<Integer, PlayerAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<Integer, PlayerAttribute> attributes) {
		this.attributes = attributes;
	}

	public PlayerAttribute getOtherAttribute() {
		return otherAttribute;
	}

	public void setOtherAttribute(PlayerAttribute otherAttribute) {
		this.otherAttribute = otherAttribute;
	}

	@Override
	public int getAttack(Skill skill) {
		return ManagerPool.playerAttributeManager.countAttack(this, skill);
	}

	public long getTeamid() {
		return teamid;
	}

	public void setTeamid(long teamid) {
		this.teamid = teamid;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

//	/**
//	 * 获得经验加成
//	 *
//	 * @return
//	 */
//	public int getExpMultiple() {
//		int value = Global.MAX_PROBABILITY;
//		//Buff加成
//		int bufPercent = 0;
//		for (int i = 0; i < this.getBuffs().size(); i++) {
//			Buff buff = this.getBuffs().get(i);
//			if (buff instanceof AttributeBuff) {
//				AttributeBuff aBuff = (AttributeBuff) buff;
//				bufPercent += aBuff.getExpPercent();
//			}
//		}
//		value = value + bufPercent;
//
//		return value;
//	}
	/**
	 * 添加敌人
	 *
	 * @param player
	 */
	public void addEnemy(Player player) {
		log.error("玩家(" + this.getId() + ")PK状态为(" + this.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(this.getState()) + ")敌人列表为" + MessageUtil.castListToString(this.getEnemys().values()) + "增加敌对玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")");
		if (this.enemys.containsKey(player.getId())) {
			Enemy enemy = this.enemys.get(player.getId());
			enemy.setLastTime(System.currentTimeMillis());
		} else {
			Enemy enemy = new Enemy();
			enemy.setEnemyId(player.getId());
			enemy.setLastTime(System.currentTimeMillis());
			this.enemys.put(enemy.getEnemyId(), enemy);
		}
	}

	@Override
	public int compareTo(Player o) {
		return o.getLevel() - getLevel();
	}

	public Transactions getTransactionsinfo() {
		return transactionsinfo;
	}

	public void setTransactionsinfo(Transactions transactionsinfo) {
		this.transactionsinfo = transactionsinfo;
	}

	public int getCanreceiveyuanbao() {
		return canreceiveyuanbao;
	}

	public void setCanreceiveyuanbao(int canreceiveyuanbao) {
		this.canreceiveyuanbao = canreceiveyuanbao;
	}

	public byte getAutorefusaldeal() {
		return autorefusaldeal;
	}

	public void setAutorefusaldeal(byte autorefusaldeal) {
		this.autorefusaldeal = autorefusaldeal;
	}

	public List<StallsTransactionLog> getStallsloglist() {
		return stallsloglist;
	}

	public void setStallsloglist(List<StallsTransactionLog> stallsloglist) {
		this.stallsloglist = stallsloglist;
	}

	public int getPkState() {
		return pkState;
	}

	public void setPkState(int pkState) {
		this.pkState = pkState;
	}

	public int getPrePkState() {
		return prePkState;
	}

	public void setPrePkState(int prePkState) {
		this.prePkState = prePkState;
	}

	public int getKillerType() {
		return killerType;
	}

	public void setKillerType(int killerType) {
		this.killerType = killerType;
	}

	public String getKiller() {
		return killer;
	}

	public void setKiller(String killer) {
		this.killer = killer;
	}

	public int getDaydevourcount() {
		return daydevourcount;
	}

	public void setDaydevourcount(int daydevourcount) {
		this.daydevourcount = daydevourcount;
	}

	public long getDaydevourTime() {
		return daydevourTime;
	}

	public void setDaydevourTime(long daydevourTime) {
		this.daydevourTime = daydevourTime;
	}

	public int getLocate() {
		return locate;
	}

	public void setLocate(int locate) {
		this.locate = locate;
	}

	@Override
	public int getServerId() {
		return WServer.getGameConfig().getServerByCountry(this.getLocate());
	}
//
//	public List<TmpYuanbaoLogData> getTmpyuanbaologdata() {
//		return tmpyuanbaologdata;
//	}
//
//	public void setTmpyuanbaologdata(List<TmpYuanbaoLogData> tmpyuanbaologdata) {
//		this.tmpyuanbaologdata = tmpyuanbaologdata;
//	}

	public byte getAutoteaminvited() {
		return autoteaminvited;
	}

	public void setAutoteaminvited(byte autoteaminvited) {
		this.autoteaminvited = autoteaminvited;
	}

	public byte getAutoIntoteamapply() {
		return autoIntoteamapply;
	}

	public void setAutoIntoteamapply(byte autoIntoteamapply) {
		this.autoIntoteamapply = autoIntoteamapply;
	}

	public LongYuanData getLongyuan() {
		return longyuan;
	}

	public void setLongyuan(LongYuanData longyuan) {
		this.longyuan = longyuan;
	}

	public int getNonage() {
		return nonage;
	}

	public void setNonage(int nonage) {
		this.nonage = nonage;
	}

	public int getGmState() {
		return gmState;
	}

	public void setGmState(int gmState) {
		this.gmState = this.gmState | gmState;
	}

	public int getMenustatus() {
		return menustatus;
	}

	public void setMenustatus(int menustatus) {
		this.menustatus = menustatus;
	}

	public int getClientset() {
		return clientset;
	}

	public void setClientset(int clientset) {
		this.clientset = clientset;
	}

	public HashMap<String, Count> getCounts() {
		return counts;
	}

	public void setCounts(HashMap<String, Count> counts) {
		this.counts = counts;
	}

	public List<Horse> getHorselist() {
		return horselist;
	}

	public void setHorselist(List<Horse> horselist) {
		this.horselist = horselist;
	}

	/**
	 * 累计在线时间
	 *
	 * @return
	 */
	public int getAccunonlinetime() {
		return accunonlinetime;
	}

	public void setAccunonlinetime(int accunonlinetime) {
		this.accunonlinetime = accunonlinetime;
	}

	public byte getDazuoState() {
		return dazuoState;
	}

	public void setDazuoState(byte dazuoState) {
		this.dazuoState = dazuoState;
	}

	public long getDazuotarget() {
		return dazuotarget;
	}

	public void setDazuotarget(long dzzuotarget) {
		this.dazuotarget = dzzuotarget;
	}

	public int getAvatarid() {
		return avatarid;
	}

	public void setAvatarid(int avatarid) {
		this.avatarid = avatarid;
	}

	public List<DailyTask> getSupperFinshSerial() {
		return supperFinshSerial;
	}

	public void setSupperFinshSerial(List<DailyTask> supperFinshSerial) {
		this.supperFinshSerial = supperFinshSerial;
	}

	public List<DailyTask> getSupperOptimalFinshSerial() {
		return supperOptimalFinshSerial;
	}

	public void setSupperOptimalFinshSerial(List<DailyTask> supperOptimalFinshSerial) {
		this.supperOptimalFinshSerial = supperOptimalFinshSerial;
	}

	/**
	 * 装备强化升阶状态
	 *
	 * @return
	 */
	public EquipStreng getEquipStreng() {
		return equipStreng;
	}

	/**
	 * 装备强化升阶状态
	 *
	 * @return
	 */
	public void setEquipStreng(EquipStreng equipStreng) {
		this.equipStreng = equipStreng;
	}

	/**
	 * 挂机设置
	 *
	 * @return
	 */
	public PlugSet getPlugset() {
		return plugset;
	}

	public void setPlugset(PlugSet plugset) {
		this.plugset = plugset;
	}

	public Gem[][] getGems() {
		return gems;
	}

	public void setGems(Gem[][] gems) {
		this.gems = gems;
	}

	public byte getIsopenhighgem() {
		return isopenhighgem;
	}

	public void setIsopenhighgem(byte isopenhighgem) {
		this.isopenhighgem = isopenhighgem;
	}

	public byte getChangeName() {
		return changeName;
	}

	public void setChangeName(byte changeName) {
		this.changeName = changeName;
	}

	public byte getChangeUser() {
		return changeUser;
	}

	public void setChangeUser(byte changeUser) {
		this.changeUser = changeUser;
	}

	/**
	 * 是否临时玩家
	 *
	 * @return
	 */
	public boolean checkTempPlayer() {
		if (getChangeName() == 1 || getChangeUser() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Gold getGold() {
		return gold;
	}

	public void setGold(Gold gold) {
		this.gold = gold;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	public long getDazuoBeginTime() {
		return dazuoBeginTime;
	}

	public void setDazuoBeginTime(long dazuoBeginTime) {
		this.dazuoBeginTime = dazuoBeginTime;
	}

	public int getDazuoEruptCount() {
		return dazuoEruptCount;
	}

	public void setDazuoEruptCount(int dazuoEruptCount) {
		this.dazuoEruptCount = dazuoEruptCount;
	}

	public int getDazuoEruptExp() {
		return dazuoEruptExp;
	}

	public void setDazuoEruptExp(int dazuoEruptExp) {
		this.dazuoEruptExp = dazuoEruptExp;
	}

	public int getDazuoEruptZq() {
		return dazuoEruptZq;
	}

	public void setDazuoEruptZq(int dazuoEruptZq) {
		this.dazuoEruptZq = dazuoEruptZq;
	}

	public HashMap<Long, Long> getDazuoAcceptList() {
		return dazuoAcceptList;
	}

	public void setDazuoAcceptList(HashMap<Long, Long> dazuoAcceptList) {
		this.dazuoAcceptList = dazuoAcceptList;
	}

	public HashMap<String, String> getActivitiesReward() {
		return activitiesReward;
	}

	public void setActivitiesReward(HashMap<String, String> activitiesReward) {
		this.activitiesReward = activitiesReward;
	}

	public byte getAutohorse() {
		return autohorse;
	}

	public void setAutohorse(byte autohorse) {
		this.autohorse = autohorse;
	}

	public List<TempYuanbaoLogData> getTempyuanbaoLogdata() {
		return tempyuanbaoLogdata;
	}

	public void setTempyuanbaoLogdata(List<TempYuanbaoLogData> tempyuanbaoLogdata) {
		this.tempyuanbaoLogdata = tempyuanbaoLogdata;
	}



	public Position getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Position lastPosition) {
		this.lastPosition = lastPosition;
	}

	public List<Byte> getLastRoads() {
		return lastRoads;
	}

	public void setLastRoads(List<Byte> lastRoads) {
		this.lastRoads = lastRoads;
	}

	public long getPlacerevivetime() {
		return placerevivetime;
	}

	public void setPlacerevivetime(long placerevivetime) {
		this.placerevivetime = placerevivetime;
	}

	public boolean isTaskclear() {
		return taskclear;
	}

	public void setTaskclear(boolean taskclear) {
		this.taskclear = taskclear;
	}

	public Raid getRaidinfo() {
		return raidinfo;
	}

	public void setRaidinfo(Raid raidinfo) {
		this.raidinfo = raidinfo;
	}

	public RaidFlop getRaidflop() {
		return raidflop;
	}

	public void setRaidflop(RaidFlop raidflop) {
		this.raidflop = raidflop;
	}

	public int getEvencutatk() {
		return evencutatk;
	}

	public void setEvencutatk(int evencutatk) {
		this.evencutatk = evencutatk;
	}

	public int getEvencutnum() {
		return evencutnum;
	}

	public void setEvencutnum(int evencutnum) {
		this.evencutnum = evencutnum;
	}

	public long getEvencuttime() {
		return evencuttime;
	}

	public void setEvencuttime(long evencuttime) {
		this.evencuttime = evencuttime;
	}

	public int getEvencutdblv() {
		return evencutdblv;
	}

	public void setEvencutdblv(int evencutdblv) {
		this.evencutdblv = evencutdblv;
	}

	public long getEvencutbufftime() {
		return evencutbufftime;
	}

	public void setEvencutbufftime(long evencutbufftime) {
		this.evencutbufftime = evencutbufftime;
	}

	public long getGatherStarttime() {
		return gatherStarttime;
	}

	public void setGatherStarttime(long gatherStarttime) {
		this.gatherStarttime = gatherStarttime;
	}

	public long getGatherId() {
		return gatherId;
	}

	public void setGatherId(long gatherId) {
		this.gatherId = gatherId;
	}

	public long getGatherCosttime() {
		return gatherCosttime;
	}

	public void setGatherCosttime(long gatherCosttime) {
		this.gatherCosttime = gatherCosttime;
	}

	@Override
	public boolean canSee(Player player) {
		return isShow();
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public int getBossbatternum() {
		return bossbatternum;
	}

	public void setBossbatternum(int bossbatternum) {
		this.bossbatternum = bossbatternum;
	}

	public long getBossbattertime() {
		return bossbattertime;
	}

	public void setBossbattertime(long bossbattertime) {
		this.bossbattertime = bossbattertime;
	}

	public long getLastAddLoginTime() {
		return lastAddLoginTime;
	}

	public void setLastAddLoginTime(long lastAddLoginTime) {
		this.lastAddLoginTime = lastAddLoginTime;
	}

	public HashSet<String> getNpcverify() {
		return npcverify;
	}

	public void setNpcverify(HashSet<String> npcverify) {
		this.npcverify = npcverify;
	}

	public int getMaxLoginTimes() {
		return maxLoginTimes;
	}

	public void setMaxLoginTimes(int maxLoginTimes) {
		this.maxLoginTimes = maxLoginTimes;
	}

	public int getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(int loginTimes) {
		this.loginTimes = loginTimes;
	}

	public long getLevelUpTime() {
		return levelUpTime;
	}

	public void setLevelUpTime(long levelUpTime) {
		this.levelUpTime = levelUpTime;
	}

	public int getTotalSkillLevel() {
		return totalSkillLevel;
	}

	public void setTotalSkillLevel(int totalSkillLevel) {
		this.totalSkillLevel = totalSkillLevel;
	}

	public long getSkillUpTime() {
		return skillUpTime;
	}

	public void setSkillUpTime(long skillUpTime) {
		this.skillUpTime = skillUpTime;
	}

	public long getLongyuanUpTime() {
		return longyuanUpTime;
	}

	public void setLongyuanUpTime(long longyuanUpTime) {
		this.longyuanUpTime = longyuanUpTime;
	}

	public int getMaxEventcut() {
		return maxEventcut;
	}

	public void setMaxEventcut(int maxEventcut) {
		this.maxEventcut = maxEventcut;
	}

	public long getMaxEventcutTime() {
		return maxEventcutTime;
	}

	public void setMaxEventcutTime(long maxEventcutTime) {
		this.maxEventcutTime = maxEventcutTime;
	}

	public Epalace getEpalace() {
		return epalace;
	}

	public void setEpalace(Epalace epalace) {
		this.epalace = epalace;
	}

	public int getChangeLine() {
		return changeLine;
	}

	public void setChangeLine(int changeLine) {
		this.changeLine = changeLine;
	}

	/**
	 * @return the titleidlist
	 */
	public List<Integer> getTitleidlist() {
		return titleidlist;
	}

	/**
	 * @param titleidlist the titleidlist to set
	 */
	public void setTitleidlist(List<Integer> titleidlist) {
		this.titleidlist = titleidlist;
	}

	public long getRidingtime() {
		return ridingtime;
	}

	public void setRidingtime(long ridingtime) {
		this.ridingtime = ridingtime;
	}

	public HashSet<String> getShowSet() {
		return showSet;
	}

	public void setShowSet(HashSet<String> showSet) {
		this.showSet = showSet;
	}

	public int getGmlevel() {
		return gmlevel;
	}

	public void setGmlevel(int gmlevel) {
		this.gmlevel = gmlevel;
	}

	public HashSet<String> getHideSet() {
		return hideSet;
	}

	public void setHideSet(HashSet<String> hideSet) {
		this.hideSet = hideSet;
	}

	public long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(long enterTime) {
		this.enterTime = enterTime;
	}

	public int getPassTime() {
		return passTime;
	}

	public void setPassTime(int passTime) {
		this.passTime = passTime;
	}

	public int getTransType() {
		return transType;
	}

	public void setTransType(int transType) {
		this.transType = transType;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public long getLastReceiveGiftTime() {
		return lastReceiveGiftTime;
	}

	public void setLastReceiveGiftTime(long lastReceiveGiftTime) {
		this.lastReceiveGiftTime = lastReceiveGiftTime;
	}

	public int getFormermapid() {
		return formermapid;
	}

	public void setFormermapid(int formermapid) {
		this.formermapid = formermapid;
	}

	public int getFormerline() {
		return formerline;
	}

	public void setFormerline(int formerline) {
		this.formerline = formerline;
	}

	public Position getFormerposition() {
		return formerposition;
	}

	public void setFormerposition(Position formerposition) {
		this.formerposition = formerposition;
	}

	public long getLongyuantime() {
		return longyuantime;
	}

	public void setLongyuantime(long longyuantime) {
		this.longyuantime = longyuantime;
	}

	public byte getLongyuanactlv() {
		return longyuanactlv;
	}

	public void setLongyuanactlv(byte longyuanactlv) {
		this.longyuanactlv = longyuanactlv;
	}

	public byte getLongyuanactnum() {
		return longyuanactnum;
	}

	public void setLongyuanactnum(byte longyuanactnum) {
		this.longyuanactnum = longyuanactnum;
	}

	public boolean isActivityDailyTask() {
		return isActivityDailyTask;
	}

	public void setActivityDailyTask(boolean isActivityDailyTask) {
		this.isActivityDailyTask = isActivityDailyTask;
	}

	public boolean isSendBagOpenCellTime() {
		return isSendBagOpenCellTime;
	}

	public void setSendBagOpenCellTime(boolean isSendBagOpenCellTime) {
		this.isSendBagOpenCellTime = isSendBagOpenCellTime;
	}

	public boolean isSendStoreOpenCellTime() {
		return isSendStoreOpenCellTime;
	}

	public void setSendStoreOpenCellTime(boolean isSendStoreOpenCellTime) {
		this.isSendStoreOpenCellTime = isSendStoreOpenCellTime;
	}

	/**
	 * 是否王城成员
	 *
	 * @return true 是 false 不是
	 */
	public boolean checkKingCity() {
		return CountryAwardManager.getInstance().isKingCity(this);
	}

	/**
	 * 得到王城成员权利等级
	 *
	 * @return 0 不是王城成员 不为0 同帮会权利等级 ，101 亲王，102王后
	 */
	public int gKingLevel() {
		KingCity kingCity = ManagerPool.countryManager.getKingcity();
		long spouseid = ManagerPool.marriageManager.getSpouseid(this);
		if (spouseid > 0 && spouseid == kingCity.getHoldplayerid()) {	//配偶ID是否是城主
			return 100 + this.getSex();   //101 亲王，102王后
		}
		
		if (this.checkKingCity()) {
			return this.getMemberInfo().getGuildPowerLevel();
		}
		return 0;
	}

	/**
	 * 得到王城成员BUFF
	 *
	 * @return 0 不是王城成员 1 秦王 2 普通成员
	 */
	public int gKingBuff() {
		if (this.checkKingCity()) {
			KingData kingData = CountryAwardManager.getInstance().getCurKingData();
			if (kingData != null) {
				return (kingData.getPlayerid() == this.getId()) ? 1 : 2;
			}else{
				log.error("得到王城成员BUFF=kingData=NULL=" + CountryAwardManager.getInstance().getKingTerm());
			}
		} else {
			log.error("得到王城成员BUFF=" + CountryManager.getInstance().getKingcity().getGuildid() + "=playerguildid=" + this.getGuildId());
		}
		return 0;
	}

	public int getKingcityrewtime() {
		return kingcityrewtime;
	}

	public void setKingcityrewtime(int kingcityrewtime) {
		this.kingcityrewtime = kingcityrewtime;
	}

	public int getKingcityrewday() {
		return kingcityrewday;
	}

	public void setKingcityrewday(int kingcityrewday) {
		this.kingcityrewday = kingcityrewday;
	}

	/**
	 * 是否有秦王玉玺BUFF
	 *
	 * @return true 有 false 没有
	 */
	public boolean checkKingCityBuffOfKing() {
		List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(this, BuffConst.KINGCITY_KING);
		if (buffs.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 是否有王城普通BUFF
	 *
	 * @return true 有 false 没有
	 */
	public boolean checkKingCityBuffOfNormal() {
		List<Buff> buffs = BuffManager.getInstance().getBuffByModelId(this, BuffConst.KINGCITY_NORMAL);
		if (buffs.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 是否在本国
	 *
	 * @return true 在 false 不在
	 */
	public boolean checkCountry() {
		if (this.getCountry() == WServer.getGameConfig().getCountryByServer(WServer.getInstance().getServerId())) {
			return true;
		}
		return false;
	}

	public int getKingcityzq() {
		return kingcityzq;
	}

	public void setKingcityzq(int kingcityzq) {
		this.kingcityzq = kingcityzq;
	}

	public int getKingcityexp() {
		return kingcityexp;
	}

	public void setKingcityexp(int kingcityexp) {
		this.kingcityexp = kingcityexp;
	}

	public int getDazuoExp() {
		return dazuoExp;
	}

	public void setDazuoExp(int dazuoExp) {
		this.dazuoExp = dazuoExp;
	}

	public int getDazuoZq() {
		return dazuoZq;
	}

	public void setDazuoZq(int dazuoZq) {
		this.dazuoZq = dazuoZq;
	}

	public int getDayFirstUseXiuWeiDanGrade() {
		return dayFirstUseXiuWeiDanGrade;
	}

	public void setDayFirstUseXiuWeiDanGrade(int dayFirstUseXiuWeiDanGrade) {
		this.dayFirstUseXiuWeiDanGrade = dayFirstUseXiuWeiDanGrade;
	}

	public long getLastUseXiuWeiDanTime() {
		return lastUseXiuWeiDanTime;
	}

	public void setLastUseXiuWeiDanTime(long lastUseXiuWeiDanTime) {
		this.lastUseXiuWeiDanTime = lastUseXiuWeiDanTime;
	}

	public HashMap<String, String> getVariables() {
		return variables;
	}

	public void setVariables(HashMap<String, String> variables) {
		this.variables = variables;
	}

	public int getEvencutmapid() {
		return evencutmapid;
	}

	public void setEvencutmapid(int evencutmapid) {
		this.evencutmapid = evencutmapid;
	}

	public int getEvencutmonid() {
		return evencutmonid;
	}

	public void setEvencutmonid(int evencutmonid) {
		this.evencutmonid = evencutmonid;
	}

	public short getEvencutmapx() {
		return evencutmapx;
	}

	public void setEvencutmapx(short evencutmapx) {
		this.evencutmapx = evencutmapx;
	}

	public short getEvencutmapy() {
		return evencutmapy;
	}

	public void setEvencutmapy(short evencutmapy) {
		this.evencutmapy = evencutmapy;
	}


	public int getRaidcontinuoustime() {
		return raidcontinuoustime;
	}

	public void setRaidcontinuoustime(int raidcontinuoustime) {
		this.raidcontinuoustime = raidcontinuoustime;
	}



	public HashMap<String, Integer> getZoneinfo() {
		return zoneinfo;
	}

	public void setZoneinfo(HashMap<String, Integer> zoneinfo) {
		this.zoneinfo = zoneinfo;
	}

	public List<ContinuousRaidsInfo> getRaidcontinuouslist() {
		return raidcontinuouslist;
	}

	public void setRaidcontinuouslist(List<ContinuousRaidsInfo> raidcontinuouslist) {
		this.raidcontinuouslist = raidcontinuouslist;
	}

	public HashMap<String, Integer> getZonerewardmap() {
		return zonerewardmap;
	}

	public void setZonerewardmap(HashMap<String, Integer> zonerewardmap) {
		this.zonerewardmap = zonerewardmap;
	}


	public long getZoneteamenterid() {
		return zoneteamenterid;
	}

	public void setZoneteamenterid(long zoneteamenterid) {
		this.zoneteamenterid = zoneteamenterid;
	}

	public long getLastGmChatTime() {
		return lastGmChatTime;
	}

	public void setLastGmChatTime(long lastGmChatTime) {
		this.lastGmChatTime = lastGmChatTime;
	}

	public int getMovestep() {
		return movestep;
	}

	public void setMovestep(int movestep) {
		this.movestep = movestep;
	}

	public HashMap<String, Integer> getZoneteamrewardmap() {
		return zoneteamrewardmap;
	}

	public void setZoneteamrewardmap(HashMap<String, Integer> zoneteamrewardmap) {
		this.zoneteamrewardmap = zoneteamrewardmap;
	}

	public String getAgentPlusdata() {
		return agentPlusdata;
	}

	public void setAgentPlusdata(String agentPlusdata) {
		this.agentPlusdata = agentPlusdata;
	}

	public String getAgentColdatas() {
		return agentColdatas;
	}

	public void setAgentColdatas(String agentColdatas) {
		this.agentColdatas = agentColdatas;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public int getRanklevel() {
		return ranklevel;
	}

	public void setRanklevel(int ranklevel) {
		this.ranklevel = ranklevel;
	}

	public int getRankexp() {
		return rankexp;
	}

	public void setRankexp(int rankexp) {
		this.rankexp = rankexp;
	}



	public int getDayonlinetime() {
		return dayonlinetime;
	}

	public void setDayonlinetime(int dayonlinetime) {
		this.dayonlinetime = dayonlinetime;
	}

	public int getCurday() {
		return curday;
	}

	public void setCurday(int curday) {
		this.curday = curday;
	}


	public List<Wage> getWagelist() {
		return wagelist;
	}

	public void setWagelist(List<Wage> wagelist) {
		this.wagelist = wagelist;
	}

	public HashMap<String,Sign> getSignmap() {
		return signmap;
	}

	public void setSignmap(HashMap<String,Sign> signmap) {
		this.signmap = signmap;
	}

	public HashMap<String, Integer> getZoneqiyaorewardmap() {
		return zoneqiyaorewardmap;
	}

	public void setZoneqiyaorewardmap(HashMap<String, Integer> zoneqiyaorewardmap) {
		this.zoneqiyaorewardmap = zoneqiyaorewardmap;
	}

	public int getSignsum() {
		return signsum;
	}

	public void setSignsum(int signsum) {
		this.signsum = signsum;
	}

	public boolean isUpdatelogtag() {
		return updatelogtag;
	}

	public void setUpdatelogtag(boolean updatelogtag) {
		this.updatelogtag = updatelogtag;
	}

	public int getLoginlinetime() {
		return loginlinetime;
	}

	public void setLoginlinetime(int loginlinetime) {
		this.loginlinetime = loginlinetime;
	}


	public HashMap<String,String> getPanelverify() {
		return panelverify;
	}

	public void setPanelverify(HashMap<String,String> panelverify) {
		this.panelverify = panelverify;
	}

	public HashMap<String, ChatCountBean> getChatCount() {
		return chatCount;
	}

	public void setChatCount(HashMap<String, ChatCountBean> chatCount) {
		this.chatCount = chatCount;
	}

	public int getAutojinyancount() {
		return autojinyancount;
	}

	public void setAutojinyancount(int autojinyancount) {
		this.autojinyancount = autojinyancount;
	}

	public int getCurrentMainTaskId() {
		return currentMainTaskId;
	}

	public void setCurrentMainTaskId(int currentMainTaskId) {
		this.currentMainTaskId = currentMainTaskId;
	}

	public String getOldname() {
		return oldname;
	}

	public void setOldname(String oldname) {
		this.oldname = oldname;
	}

	public int getOldnametime() {
		return oldnametime;
	}

	public void setOldnametime(int oldnametime) {
		this.oldnametime = oldnametime;
	}

	public int getOldfightpower() {
		return oldfightpower;
	}

	public void setOldfightpower(int oldfightpower) {
		this.oldfightpower = oldfightpower;
	}

	public int getEquipbulletin() {
		return equipbulletin;
	}

	public void setEquipbulletin(int equipbulletin) {
		this.equipbulletin = equipbulletin;
	}

	public int getEquipquality() {
		return equipquality;
	}

	public void setEquipquality(int equipquality) {
		this.equipquality = equipquality;
	}

	public List<HorseWeapon> getHorseweaponlist() {
		return horseweaponlist;
	}

	public void setHorseweaponlist(List<HorseWeapon> horseweaponlist) {
		this.horseweaponlist = horseweaponlist;
	}

	public int getCheckTimes() {
		return checkTimes;
	}

	public void setCheckTimes(int checkTimes) {
		this.checkTimes = checkTimes;
	}

	public long getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(long checkTime) {
		this.checkTime = checkTime;
	}	
	
	public int getHeartCheckTimes() {
		return heartCheckTimes;
	}

	public void setHeartCheckTimes(int heartCheckTimes) {
		this.heartCheckTimes = heartCheckTimes;
	}

	public int getBiwudaototalexp() {
		return biwudaototalexp;
	}

	public void setBiwudaototalexp(int biwudaototalexp) {
		this.biwudaototalexp = biwudaototalexp;
	}

	public int getBiwudaototalzhenqi() {
		return biwudaototalzhenqi;
	}

	public void setBiwudaototalzhenqi(int biwudaototalzhenqi) {
		this.biwudaototalzhenqi = biwudaototalzhenqi;
	}

	public int getBiwudaototalBox() {
		return biwudaototalBox;
	}

	public void setBiwudaototalBox(int biwudaototalBox) {
		this.biwudaototalBox = biwudaototalBox;
	}

	public int getBiwudaototalrank() {
		return biwudaototalrank;
	}

	public void setBiwudaototalrank(int biwudaototalrank) {
		this.biwudaototalrank = biwudaototalrank;
	}

	public int getBiwudaoday() {
		return biwudaoday;
	}

	public void setBiwudaoday(int biwudaoday) {
		this.biwudaoday = biwudaoday;
	}

	public int getPrisonTimes() {
		return prisonTimes;
	}

	public void setPrisonTimes(int prisonTimes) {
		this.prisonTimes = prisonTimes;
	}

	public long getPrisonEnterTime() {
		return prisonEnterTime;
	}

	public void setPrisonEnterTime(long prisonEnterTime) {
		this.prisonEnterTime = prisonEnterTime;
	}

	public int getPrisonRemainTime() {
		return prisonRemainTime;
	}

	public void setPrisonRemainTime(int prisonRemainTime) {
		this.prisonRemainTime = prisonRemainTime;
	}

	public HashMap<String,Integer> getShoplimitmap() {
		return shoplimitmap;
	}

	public void setShoplimitmap(HashMap<String,Integer> shoplimitmap) {
		this.shoplimitmap = shoplimitmap;
	}

	public int getGroupmark() {
		return groupmark;
	}

	public void setGroupmark(int groupmark) {
		this.groupmark = groupmark;
	}

	public HashMap<String, Integer> getZonevictorymap() {
		return zonevictorymap;
	}

	public void setZonevictorymap(HashMap<String, Integer> zonevictorymap) {
		this.zonevictorymap = zonevictorymap;
	}

	public Bag getGlobalBag() {
		return globalBag;
	}

	public void setGlobalBag(Bag bag) {
		this.globalBag = bag;
	}

	public Realm getRealm() {
		return realm;
	}

	public void setRealm(Realm realm) {
		this.realm = realm;
	}

	public boolean isJumpProtect() {
		return jumpProtect;
	}

	public void setJumpProtect(boolean jumpProtect) {
		this.jumpProtect = jumpProtect;
	}

	public boolean isCross() {
		return cross;
	}

	public void setCross(boolean cross) {
		this.cross = cross;
	}
	
	public HashMap<String,String> getSysmailmap() {
		return sysmailmap;
	}

	public void setSysmailmap(HashMap<String,String> sysmailmap) {
		this.sysmailmap = sysmailmap;
	}

	public double getRetbindgoldsum() {
		return retbindgoldsum;
	}

	public void setRetbindgoldsum(double retbindgoldsum) {
		this.retbindgoldsum = retbindgoldsum;
	}

	public double getRetcurrbindgold() {
		return retcurrbindgold;
	}

	public void setRetcurrbindgold(double retcurrbindgold) {
		this.retcurrbindgold = retcurrbindgold;
	}

	public String getRetbindgoldid() {
		return retbindgoldid;
	}

	public void setRetbindgoldid(String retbindgoldid) {
		this.retbindgoldid = retbindgoldid;
	}

	public int getRetbindgoldday() {
		return retbindgoldday;
	}

	public void setRetbindgoldday(int retbindgoldday) {
		this.retbindgoldday = retbindgoldday;
	}

	public HashMap<String,List<Integer>> getCoupletmap() {
		return coupletmap;
	}

	public void setCoupletmap(HashMap<String,List<Integer>> coupletmap) {
		this.coupletmap = coupletmap;
	}

	public HashSet<Long> getHits() {
		return hits;
	}

	public void setHits(HashSet<Long> hits) {
		this.hits = hits;
	}

	public long getMarriageid() {
		return marriageid;
	}

	public void setMarriageid(long marriageid) {
		this.marriageid = marriageid;
	}

	public long getDivorceid() {
		return divorceid;
	}

	public void setDivorceid(long divorceid) {
		this.divorceid = divorceid;
	}

	public int getWeddingringid() {
		return weddingringid;
	}

	public void setWeddingringid(int weddingringid) {
		this.weddingringid = weddingringid;
	}

	public List<ContinuousRaidsInfo> getQiyaocontinuouslist() {
		return qiyaocontinuouslist;
	}

	public void setQiyaocontinuouslist(List<ContinuousRaidsInfo> qiyaocontinuouslist) {
		this.qiyaocontinuouslist = qiyaocontinuouslist;
	}

	public int getQiyaocontinuoustime() {
		return qiyaocontinuoustime;
	}

	public void setQiyaocontinuoustime(int qiyaocontinuoustime) {
		this.qiyaocontinuoustime = qiyaocontinuoustime;
	}

	public String getProtectip() {
		return protectip;
	}

	public void setProtectip(String protectip) {
		this.protectip = protectip;
	}

	


	public String getProtectpassword() {
		return protectpassword;
	}

	public void setProtectpassword(String protectpassword) {
		this.protectpassword = protectpassword;
	}

	public String getProtectmail() {
		return protectmail;
	}

	public void setProtectmail(String protectmail) {
		this.protectmail = protectmail;
	}

	public long getProtecttime() {
		return protecttime;
	}

	public void setProtecttime(long protecttime) {
		this.protecttime = protecttime;
	}

	public int getProtectstatus() {
		return protectstatus;
	}

	public void setProtectstatus(int protectstatus) {
		this.protectstatus = protectstatus;
	}

	public String getProtectPIN() {
		return protectPIN;
	}

	public void setProtectPIN(String protectPIN) {
		this.protectPIN = protectPIN;
	}

	public long getProtectpincooldown() {
		return protectpincooldown;
	}

	public void setProtectpincooldown(long protectpincooldown) {
		this.protectpincooldown = protectpincooldown;
	}

	public int getMobileGiftIndex() {
		return mobileGiftIndex;
	}

	public void setMobileGiftIndex(int mobileGiftIndex) {
		this.mobileGiftIndex = mobileGiftIndex;
	}

	public int getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(int quitTime) {
		this.quitTime = quitTime;
	}
}
