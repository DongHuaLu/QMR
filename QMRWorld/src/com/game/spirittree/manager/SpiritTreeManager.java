package com.game.spirittree.manager;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.config.Config;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_globalBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_spirittree_kiwiBean;
import com.game.data.bean.Q_spirittree_pack_conBean;
import com.game.data.bean.Q_spirittree_packetBean;
import com.game.data.bean.Q_vipBean;
import com.game.data.manager.DataManager;
import com.game.db.bean.SpirittreeBean;
import com.game.db.dao.SpirittreeDao;
import com.game.dblog.LogService;
import com.game.guild.bean.MemberInfo;
import com.game.guild.manager.GuildWorldManager;
import com.game.guild.structs.Guild;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.prompt.structs.Notifys;
import com.game.server.WorldServer;
import com.game.spirittree.bean.FruitInfo;
import com.game.spirittree.bean.GuildFruitLog;
import com.game.spirittree.bean.Rewardbriefinfo;
import com.game.spirittree.log.SpiritPickLog;
import com.game.spirittree.log.SpiritStealLog;
import com.game.spirittree.log.SpiritWateringLog;
import com.game.spirittree.log.SpiritXianLuLog;
import com.game.spirittree.message.ReqActivityCheckFruitToWorldMessage;
import com.game.spirittree.message.ReqContinuousRipeningToWorldMessage;
import com.game.spirittree.message.ReqGetAllFruitInfoToWorldMessage;
import com.game.spirittree.message.ReqGetSingleFruitInfoToWorldMessage;
import com.game.spirittree.message.ReqGuildFruitLogToWorldMessage;
import com.game.spirittree.message.ReqGuildFruitOperatingToWorldMessage;
import com.game.spirittree.message.ReqGuildGMToWorldMessage;
import com.game.spirittree.message.ReqOpenGuildFruitToWorldMessage;
import com.game.spirittree.message.ReqRipeningDecYBToGameMessage;
import com.game.spirittree.message.ReqRipeningFruitToWorldMessage;
import com.game.spirittree.message.ReqUrgeRipeToWorldMessage;
import com.game.spirittree.message.ResActivityReturnFruitToGameMessage;
import com.game.spirittree.message.ResContinuousRipeningToClientMessage;
import com.game.spirittree.message.ResFruitErrorToClientMessage;
import com.game.spirittree.message.ResFruitOperatingToGameMessage;
import com.game.spirittree.message.ResFruitTheftNumToClientMessage;
import com.game.spirittree.message.ResGameMakeFruitInfoMessage;
import com.game.spirittree.message.ResGetAllFruitInfoToClientMessage;
import com.game.spirittree.message.ResGetSingleFruitInfoToClientMessage;
import com.game.spirittree.message.ResGuildAccExpToWorldMessage;
import com.game.spirittree.message.ResGuildFruitLogToClientMessage;
import com.game.spirittree.message.ResOpenGuildFruitToClientMessage;
import com.game.spirittree.message.ResRipeningDecYBToClientMessage;
import com.game.spirittree.message.ResRipeningDecYBToWorldMessage;
import com.game.spirittree.message.ResShowGuildMatureClientMessage;
import com.game.spirittree.message.ResShowToRewardListToClientMessage;
import com.game.spirittree.structs.Fruit;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.SpiritTree;
import com.game.spirittree.structs.SpiritTreeLog;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
import com.game.utils.VersionUpdateUtil;


public class SpiritTreeManager {

	private Logger log = Logger.getLogger(SpiritTreeManager.class);
	private static ConcurrentHashMap<Long, SpiritTree> spirittreelist = new  ConcurrentHashMap<Long, SpiritTree>();
	
	
	
	private static SpiritTreeManager manager;
	private static Object obj = new Object();
	private SpiritTreeManager(){
		loadAllSpirittree();
	}
	
	public static SpiritTreeManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new SpiritTreeManager();
			}
		}
		return manager;
	}
	private static int TOP_LOAD_MAXCOUNT = 1000;//数据库读取每次最大值
	//第一次默认组包ID
	private static int[] DEFAULTGROUP = {100001,100002,100003};
	//银色奇异果数据索引
	private int SilverKiwi = 10001;
	//金色奇异果数据索引
	private int GoldKiwi = 20001;
	//每次偷窃比例
	private int STEALNUM = 20;	
	//保存本行会上个果实成熟玩家名
	private static HashMap<Long, String> MatuMapNnme = new HashMap<Long, String>();
	//保存本行会上个果实成熟时间
	private static HashMap<Long, Integer> MatuMapTime = new HashMap<Long, Integer>();
	//开放等级
	private int OPENLEVEL = 25;
//	96	6		灵树-每日可浇灌仙露次数
//	97	600		灵树-仙露浇灌间隔冷却时间（秒）
//	98	20		灵树-每次仙露浇灌加速果实成熟时间（百分比）

	
	
	/**灵树-每日可浇灌仙露次数
	 * 
	 * @return
	 */
	public int getdewnumData() {
		Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(96);
		return data.getQ_int_value();
	}
	
	/**灵树-仙露浇灌间隔冷却时间（秒）
	 * 
	 * @return
	 */
	public int getdewcooldownData() {
		Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(97);
		return data.getQ_int_value();
	}
	
	/**灵树-每次仙露浇灌加速果实成熟时间（百分比）
	 * 
	 * @return
	 */
	public int getdewSpeedData() {
		Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(98);
		return data.getQ_int_value();
	}
	
	/**灵树每天抢摘次数上限
	 * 
	 * @return
	 */
	public int getTheftViewsmaxnum() {
		Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(125);
		return data.getQ_int_value();
	}
	
	/**灵树偷取经验补偿系数
	 * 
	 * @return
	 */
	public int getexpcompendata() {
		Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(109);
		return data.getQ_int_value();
	}
	
	
	/**获取个人灵树信息
	 * 
	 * @param pid
	 * @return
	 */
	public SpiritTree getSpiritTree(long pid ){
		if (spirittreelist.containsKey(pid)) {
			SpiritTree spiritTree = spirittreelist.get(pid);
			checkday(spiritTree);
			return spiritTree;
		}
		return null;
	}
	
	
	/**普通果实包
	 * @return 
	 * 
	 */
	public HashMap<Integer, Q_spirittree_packetBean> getpacketdata(){
		 HashMap<Integer, Q_spirittree_packetBean> data = DataManager.getInstance().q_spirittree_packetContainer.getMap();
		return data;
	}
	
	
	/**奇异果组包
	 * @return 
	 * 
	 */
	public HashMap<String, Q_spirittree_kiwiBean> getkiwidata(){
		 HashMap<String, Q_spirittree_kiwiBean> data = DataManager.getInstance().q_spirittree_kiwiContainer.getMap();
		return data;
	}
	
	
	/**组包内容
	 * @return 
	 * 
	 */
	public HashMap<Integer, Q_spirittree_pack_conBean> getpackcondata(){
		 HashMap<Integer, Q_spirittree_pack_conBean> data = DataManager.getInstance().q_spirittree_pack_conContainer.getMap();
		return data;
	}
	
	
	//灵树数据接口
	private SpirittreeDao spirittreeDao = new SpirittreeDao();

	public SpirittreeDao getSpirittreeDao() {
		return spirittreeDao;
	}
	
	
	/**灵树基本数据（外观，生长时间）
	 * 
	 * @param fruit
	 * @return 
	 */
	public Q_spirittree_packetBean Getbasicfruitinfo(Fruit fruit){
		Q_spirittree_packetBean packet = null;
		if (fruit.getType() == 1) {
			 packet = getpacketdata().get(SilverKiwi);
		}else if (fruit.getType() == 2) {
			 packet = getpacketdata().get(GoldKiwi);
		}else {
			 packet = getpacketdata().get(fruit.getGroupidlist().get(0));
		}
		return packet;
	}
	
	
	
	
	
	/**
	 * 服务器启动 ，从数据库 读取所有灵树信息
	 * 
	 */
//	public void loadAllSpirittree() {
//		try {
//			List<SpirittreeBean> list = getSpirittreeDao().select();
//			Iterator<SpirittreeBean> iterator = list.iterator();
//			while (iterator.hasNext()) {
//				SpirittreeBean spirittreeBean = (SpirittreeBean) iterator.next();
//				if (spirittreeBean != null) {
//					SpiritTree spirittree = (SpiritTree) JSONserializable.toObject(VersionUpdateUtil.dateLoad(spirittreeBean.getData()), SpiritTree.class);
//					spirittreelist.put(spirittreeBean.getRoleid(), spirittree);
//				}
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	public void loadAllSpirittree() {
		try {
			int roleCount = getSpirittreeDao().getcount();
			log.error(String.format("Spirittree满足条件数据量==%d", roleCount));
			int loadCount = 0;
			int maxloadCount = roleCount % TOP_LOAD_MAXCOUNT == 0 ? roleCount / TOP_LOAD_MAXCOUNT : (roleCount / TOP_LOAD_MAXCOUNT) + 1;
			int ms = (int) (System.currentTimeMillis()/1000) - 24*60*60*7;
			while (loadCount < maxloadCount) {
				int beginidx = loadCount * TOP_LOAD_MAXCOUNT;
				int endidx = TOP_LOAD_MAXCOUNT;
				List<SpirittreeBean> roleList = spirittreeDao.selectbeginandend(beginidx, endidx,ms);
				log.error(String.format("Spirittree读取成功，第%d次", loadCount + 1));
				log.error(String.format("Spirittree读取本次总个数[%d]个", roleList.size()));
				log.error("Spirittree本次开始解析");
				int roleJsonNum = 0;
				Iterator<SpirittreeBean> iter = roleList.iterator();
				while (iter.hasNext()) {
					SpirittreeBean spirittreeBean = (SpirittreeBean) iter.next();
					if (spirittreeBean != null) {
						try {
							SpiritTree spirittree = JSON.parseObject(VersionUpdateUtil.dateLoad(spirittreeBean.getData()), SpiritTree.class);
							//SpiritTree spirittree = (SpiritTree) JSONserializable.toObject(VersionUpdateUtil.dateLoad(spirittreeBean.getData()), SpiritTree.class);
							spirittreelist.put(spirittreeBean.getRoleid(), spirittree);
						} catch (Exception e) {
							log.error("Spirittree读取出错"+e.getMessage());
						}
					}
					roleJsonNum++;
					if (roleJsonNum % 100 == 0) {
						log.error(String.format("Spirittree本次已经解析[%d]个，剩余[%d]个。", roleJsonNum, roleList.size() - roleJsonNum));
					}
				}
				log.error(String.format("Spirittree本次已经解析[%d]个，剩余[%d]个。", roleJsonNum, roleList.size() - roleJsonNum));
				loadCount++;
			}
			log.error("Spirittree全部解析完成");
		} catch (SQLException ex) {
			log.error(ex.getMessage());
		}
	}
	
	
	
	
	/**
	 * 从数据库  读取单个灵树信息,内存中已经存在，则不读取
	 * 
	 */
	public void loadSpirittree(long pid) {
		try {
			if(spirittreelist.containsKey(pid) == false){
				SpirittreeBean spirittreeBean = getSpirittreeDao().selectsingle(pid);
				if (spirittreeBean != null) {   
					SpiritTree spiritTree = JSON.parseObject(VersionUpdateUtil.dateLoad(spirittreeBean.getData()), SpiritTree.class);
					
					//SpiritTree spiritTree = (SpiritTree) JSONserializable.toObject(VersionUpdateUtil.dateLoad(spirittreeBean.getData()), SpiritTree.class);
					spirittreelist.put(spirittreeBean.getRoleid(), spiritTree);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

//	/**
//	 * 储存单个灵树数据  到数据库
//	 * @param pid
//	 * @param 
//	 */
//	public void saveSpirittree(long pid ,SpiritTree spirittree, boolean insert) {
//		try {
//			SpirittreeBean spirittreeBean  = new SpirittreeBean();
//			spirittreeBean.setRoleid(pid);
//			spirittreeBean.setData(JSONserializable.toString(spirittree));
//			if (insert) {
//				if (getSpirittreeDao().insert(spirittreeBean) == 0) {
//					log.error(String.format("灵树数据保存错误，玩家id[%s]，内容[%s]", pid,spirittreeBean.getData()));
//				}	
//			}else {
//				if (getSpirittreeDao().update(spirittreeBean) == 0) {
//					log.error(String.format("灵树数据保存错误，玩家id[%s]，内容[%s]", pid,spirittreeBean.getData()));
//				}	
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	/**
	 * 储存单个灵树数据  到数据库 异步
	 * @param pid
	 * @param 
	 */
	public void saveSpirittree(long pid ,SpiritTree spirittree, boolean insert) {
		try {
			int ms = (int) (System.currentTimeMillis()/1000);
			SpirittreeBean spirittreeBean  = new SpirittreeBean();
			spirittreeBean.setRoleid(pid);
			spirittreeBean.setTime(ms);
			spirittreeBean.setData(VersionUpdateUtil.dateSave(JSONserializable.toString(spirittree)));
			int type=0;	//更新
			if (insert) {
				type = 1;//插入
			}
			WorldServer.getInstance().getwSaveFruitThread().dealMail(spirittreeBean, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**更新单个灵树数据
	 * 
	 * @param pid
	 * @param stallsInfo
	 */
	public void saveSpirittreeInfo(long pid ,SpiritTree spirittree, boolean insert){
		spirittreelist.put(pid, spirittree);
		saveSpirittree(pid, spirittree, insert);
	}

	
	
	

	
	
	
	/**获得灵树全部果实信息
	 * 
	 */
	public void getAllFruitInfo(Player player){
		if(spirittreelist.containsKey(player.getId()) == false){
			try {
				SpirittreeBean spirittreeBean = getSpirittreeDao().selectsingle(player.getId());
				if (spirittreeBean == null) {
					uplvgenerateFruit(player);
				}
			} catch (Exception e) {
				return;
			}
		}else {
			SpiritTree spirittreedata = spirittreelist.get(player.getId());
			Fruit[] fruits = spirittreedata.getFruitlist();
			boolean is=false;
			for (int i = 0; i < fruits.length; i++) {
				if (fruits[i] == null) {
					if (i == 6) {
						fruits[i] = generateFruit(player,1);
					}else if (i==7) {
						fruits[i] =generateFruit(player,2);
					}else {
						fruits[i] =generateFruit(player,0);
					}
					is =true;
				}
			}
			
			if (is) {
				saveSpirittree(player.getId(),spirittreedata,false);
			}
		}

		SpiritTree spirittreeinfo = getSpiritTree(player.getId());
		ResGetAllFruitInfoToClientMessage cmsg = new ResGetAllFruitInfoToClientMessage();
		Fruit[] fruitlist =  spirittreeinfo.getFruitlist();
		ArrayList<Integer> nexttimes = new ArrayList<Integer>();
		for (Fruit fruit : fruitlist) {
			cmsg.getFruitinfo().add(fruit.makeinfo());
			int shengyu = fruit.getRipeningtime() -  (int) (System.currentTimeMillis()/1000L);
			if (shengyu > 0) {
				nexttimes.add(shengyu);
			}
		}
		cmsg.setDewnum(spirittreeinfo.getDewnum());
		cmsg.setNextdew(spirittreeinfo.getDewcooldown() - (int) (System.currentTimeMillis()/1000L));

		if (nexttimes.size() > 0) {
			Collections.sort(nexttimes,new Comparator<Integer>() {	
				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1 > o2) {
						return 1;
					}
					return 0;
				}
			});
			cmsg.setNexttime(nexttimes.get(0));
		}
		MessageUtil.tell_player_message(player, cmsg);
		ResGuildAccExpToWorldMessage expmsg = new ResGuildAccExpToWorldMessage();
		int exp = spirittreeinfo.getBuchangexp();
		if (exp > 0 ) {
			spirittreeinfo.setBuchangexp(0);
			expmsg.setExp(exp);
			expmsg.setPlayerid(player.getId());
			MessageUtil.send_to_game(player, expmsg);
			MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("获得离线后灵树果实被偷的补偿经验+{1}。"),exp+"");
			saveSpirittree(player.getId(),spirittreeinfo,false);
			
		} 
	}
	

	/**打开个人灵树面板
	 * 
	 */
	public void stReqGetAllFruitInfoToWorldMessage(ReqGetAllFruitInfoToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			if (player.getLevel() >= OPENLEVEL) {
				getAllFruitInfo(player);
			}
//			else {
//				MessageUtil.notify_player(player,Notifys.ERROR,"达到{1}级才能开放灵树。",OPENLEVEL+"");
//			}
		}
	}

	
	
	
	/**等级达到后激活灵树
	 * 
	 * @param player
	 */
	// 激活灵树，等级达到后首次激活调用这里
	public void uplvgenerateFruit(Player player){
		if(spirittreelist.containsKey(player.getId()) == false){
			SpiritTree spiritTree = new SpiritTree();
			Fruit[] Fruitlist = spiritTree.getFruitlist();
			for (int i = 0; i < Fruitlist.length; i++) {
				Fruit fruit = new Fruit();
				fruit.setFruithostid(player.getId());
				if (i == 6) {
					Fruitlist[i]=generateFruit(player,1);
				}else if(i == 7){
					Fruitlist[i]= generateFruit(player,2);
				}else {
					if(DEFAULTGROUP.length > i && player.getLevel() < 60){	//判断60 防刷
						Q_spirittree_packetBean data = getpacketdata().get(DEFAULTGROUP[i]);
						if(data != null){
							int time = growthtime(data.getQ_growing_up(),data.getQ_packet_id());
							if (time > 0) {
								fruit.setRipeningtime( time + (int)(System.currentTimeMillis()/1000L));//设置果子成熟时间
							}else {
								fruit.setRipeningtime(0 - 1);//设置果子成熟时间
							}
							
							fruit.getGroupidlist().add(DEFAULTGROUP[i]);
							fruit.setType((byte) 0);
							fruit.setYield(100);
							fruit.getFruitrewardlist();
							HashMap<Integer, Q_spirittree_pack_conBean> rewarddata = getpackcondata();
							createFruitRewardList(player,fruit,rewarddata);	//筛选并设置奖励列表 
							
							Fruitlist[i] = fruit;
						}
					}else {
						Fruitlist[i]= generateFruit(player,0);
					}
				}
			}
			spirittreelist.put(player.getId(), spiritTree);
			saveSpirittree(player.getId(),spiritTree,true);
		}
	}
	
	
	
	/**解析字符串得到果实成熟时间（秒）
	 * 
	 * @param str
	 * @return
	 */
	public int growthtime(String str,int gid) {
		int sum = 0;
		if (str != null && str.length() > 2) {
			try {
				String [] tabStrings= str.split(Symbol.FENHAO);
				for (String string : tabStrings) {
					String[] data = string.split(Symbol.SHUXIAN_REG);
					if (data.length == 3) {
						int n = Integer.parseInt(data[2] );
						sum = n + sum;
					}else {
						log.error("组包"+gid+",果实成熟时间解析格式错误="+str);
						sum = 60*60*24;
					}
				}
			} catch (Exception e) {
				sum = 60*60*24;
				log.error("组包"+gid+",果实成熟时间解析错误="+str+e);
			}
		}
		return sum;
	}
	
	
	
	
	/**生成果实信息，0普通，1银色，2金色
	 * @return 
	 * 
	 */
	public Fruit generateFruit(Player player,int type){
		Fruit fruit = new Fruit();
		fruit.setType((byte) type);
		fruit.setFruithostid(player.getId());
		randomGroup(player ,type,fruit);
		HashMap<Integer, Q_spirittree_pack_conBean> rewarddata = getpackcondata();
		
		createFruitRewardList(player,fruit,rewarddata);	//筛选并设置奖励列表 

		if (type == 1) {//银色
			Q_spirittree_packetBean data = getpacketdata().get(SilverKiwi);
			int time = growthtime(data.getQ_growing_up(),data.getQ_packet_id());
			fruit.setRipeningtime( time + (int)(System.currentTimeMillis()/1000));//设置果子成熟时间
			fruit.setYield(-1);
		}else if(type == 2){//金色
			Q_spirittree_packetBean data = getpacketdata().get(GoldKiwi);
			int time = growthtime(data.getQ_growing_up(),data.getQ_packet_id());
			fruit.setRipeningtime(time + (int)(System.currentTimeMillis()/1000));//设置果子成熟时间
			fruit.setYield(-2);
		}else {
			if (fruit.getGroupidlist().size() > 0) {
				Q_spirittree_packetBean data = getpacketdata().get(fruit.getGroupidlist().get(0));
				int time = growthtime(data.getQ_growing_up(),data.getQ_packet_id());
				fruit.setRipeningtime(time + (int)(System.currentTimeMillis()/1000));//设置果子成熟时间
				fruit.setYield(100);
			}else {
				return null;
			}
		}
		return fruit;
	}
	
	
	
	
	/**
	 * 筛选并设置奖励列表 
	 * @param player
	 * @param fruit
	 * @param rewarddata
	 */
	public void createFruitRewardList(Player player,Fruit fruit , HashMap<Integer, Q_spirittree_pack_conBean> rewarddata){
		for (Integer gid : fruit.getGroupidlist()) {
			List<Integer> tmpidxs =new ArrayList<Integer>(); // 储存符合条件的道具奖励索引
			List<Integer> tmprnds =new ArrayList<Integer>(); //选中道具的几率值
			Iterator<Entry<Integer, Q_spirittree_pack_conBean>> it = rewarddata.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Integer, Q_spirittree_pack_conBean> pack = it.next();
				Q_spirittree_pack_conBean data = pack.getValue();
				if (gid ==data.getQ_packet_id() ) {
					if (player.getLevel() >= data.getQ_need_level() && player.getLevel() <= data.getQ_exclude_level() ) {
						if (data.getQ_item_id() > 0) {
							 Q_itemBean itemdata = ManagerPool.dataManager.q_itemContainer.getMap().get(data.getQ_item_id());
							 if (itemdata != null) {
								if (itemdata.getQ_type() == 1) {	
									//检测装备性别
									if (itemdata.getQ_sex() == 0 || itemdata.getQ_sex() == player.getSex()) {
										tmpidxs.add(data.getQ_id());
										tmprnds.add(data.getQ_selected_rnd());
									}
								}else {
									tmpidxs.add(data.getQ_id());
									tmprnds.add(data.getQ_selected_rnd());
								}
							}
						}else {
							tmpidxs.add(data.getQ_id());
							tmprnds.add(data.getQ_selected_rnd());
						}
					}
				}
			}
			
			if (tmprnds.size() > 0) {
				int index= RandomUtils.randomIndexByProb(tmprnds);//在道具合集中随机一个道具
				int idx = tmpidxs.get(index);
				Q_spirittree_pack_conBean rewdata = rewarddata.get(idx);//获取选中道具信息
				FruitReward fruitreward = createFruitreward(rewdata);
				fruit.getFruitrewardlist().add(fruitreward);
			}else {
				log.error("玩家等级="+player.getLevel()+"，组包ID="+gid+"，没有符合条件的奖励");
			}
		}
		
		
	}

	
	
	/**
	 * 创建果实奖励
	 * @param rewdata
	 * @return
	 */

	public FruitReward createFruitreward(Q_spirittree_pack_conBean rewdata){
		if (rewdata != null ) {

			FruitReward  fruitreward = new FruitReward();
			fruitreward.setNum(rewdata.getQ_item_num());
			fruitreward.setSum(rewdata.getQ_item_num());
			fruitreward.setItemModelid(rewdata.getQ_item_id());
			fruitreward.setIdx(rewdata.getQ_id());
			fruitreward.setStrenglevel(rewdata.getQ_streng_level());	//产生道具属性
			fruitreward.setLosttime(rewdata.getQ_existtime());
			if (rewdata.getQ_item_id() > 0 && rewdata.getQ_item_num() >= 1000) {
				fruitreward.setNum(50000);
				fruitreward.setSum(50000);
				fruitreward.setItemModelid(-4);
			}
			
			boolean isbidn = false;
			if (rewdata.getQ_is_binding() == 1) {
				isbidn=true;
			}
			fruitreward.setBind(isbidn);
			if (rewdata.getQ_addProperty_num() > 0 && rewdata.getQ_item_id() > 0) {
				FruitAppendManager.getInstance().buildAppend(fruitreward, rewdata.getQ_addProperty_num());
			}
			if (fruitreward.getItemModelid() == 0) {
				log.error("创建果实奖励=0");
			}
			return fruitreward;
		}
		return null;
	}
	
	
	
	
	/**随机组包 :0普通，1银色，2金色
	 * 
	 */
	public void randomGroup(Player player ,int type,Fruit fruit){
		List<Integer > gidlist = new ArrayList<Integer>();  //选中的组包列表
		List<Integer > rndlist = new ArrayList<Integer>();	//随机率列表
		List<Integer > tmplist = new ArrayList<Integer>();	//临时补充列表
		if(type == 1 || type == 2){
			HashMap<Integer, Integer> numMap= new HashMap<Integer, Integer>();
			List<Q_spirittree_kiwiBean> data = DataManager.getInstance().q_spirittree_kiwiContainer.getList();
			 Collections.sort(data, new Comparator<Q_spirittree_kiwiBean>() {
					@Override
					public int compare(Q_spirittree_kiwiBean o1, Q_spirittree_kiwiBean o2) {
						if (o1.getQ_is_show() > o2.getQ_is_show()) {	//大的在最前
							return 1;
						}
						return 0;
					}
				} );
			 for (Q_spirittree_kiwiBean kiwiBean : data) {
				 if ((kiwiBean.getQ_type() == 1 && type == 1 ) || (kiwiBean.getQ_type() == 2 && type == 2)){
					 if (player.getLevel() >= kiwiBean.getQ_need_level()) {
						 int num = 0;
						 if(numMap.containsKey(kiwiBean.getQ_packet_id())){
							 num = numMap.get(kiwiBean.getQ_packet_id());
						 }
						 
						for (int i = 0; i < kiwiBean.getQ_check_num(); i++) {
							if(RandomUtils.isGenerate2(10000, kiwiBean.getQ_arise_rnd()) ){
								 if (num < kiwiBean.getQ_check_num() ) {
									 if (gidlist.size() >= 10) {
										 break;
									}
									 gidlist.add(kiwiBean.getQ_packet_id());
									 numMap.put(kiwiBean.getQ_packet_id(), num+1);
								}
							} 
						}	
						tmplist.add(kiwiBean.getQ_packet_id());
					 }
				} 
			}
			 if (gidlist.size() < 10) {
				//组包不足10个，需要补充
				 if (tmplist.size() > 1) {
					 Collections.sort(tmplist, new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							if (o1< o2) {
								return 1;
							}
							return 0;
						}
					} );
					int num = 10 - gidlist.size();//补充数量
					for (int i = 0; i < num; i++) {
						gidlist.add(tmplist.get(0));
					}
				}
			}
			 //打乱顺序
			if (gidlist.size() > 0) {
				for (int i = 0; i < 10; i++) {
					int rnd = RandomUtils.random(1,gidlist.size()) - 1;
					Integer id = gidlist.remove(rnd);
					gidlist.add(id);
				}
			}
			 
			 
			fruit.setGroupidlist(gidlist);
		}else {
			 List<Q_spirittree_packetBean> data = DataManager.getInstance().q_spirittree_packetContainer.getList();
			 for (Q_spirittree_packetBean packetBean : data) {
				 if (player.getLevel() >= packetBean.getQ_need_level()) {
					 if (packetBean.getQ_type() == 1) {
						 gidlist.add(packetBean.getQ_packet_id());
						 rndlist.add(packetBean.getQ_arise_rnd());
					} 
				}
			}
			int index= RandomUtils.randomIndexByProb(rndlist);
			if (index>= 0) {
				fruit.getGroupidlist().add(gidlist.get(index));
			}else {
				log.error("果实奖励出错，组包数量:"+fruit.getGroupidlist().size());
			}
		}	
	}
	
	
	/**随机干旱事件
	 * 
	 */
	public void aridevent(Fruit fruit){
		if (fruit.getType() == 0) {
			Q_spirittree_packetBean data = getpacketdata().get(fruit.getGroupidlist().get(0));
			if (data != null) {
				int sm = (int)(System.currentTimeMillis()/1000L);
				if(fruit.getIsarid() == 0 && fruit.getRipeningtime() > sm){	//果实未成熟
					int interval = sm - fruit.getAridtime();
					if (interval > data.getQ_drought_time()) {	//只有大于间隔时间，才会进入干旱随机
						fruit.setAridtime(sm);	//进入后无论是否干旱都更新间隔时间
						if (RandomUtils.isGenerate2(10000, data.getQ_drought_rnd())) {
							//SpiritTree spiritTree = getSpiritTree(fruit.getFruithostid());
							PlayerWorldInfo winfo = ManagerPool.playerManager.getPlayerWorldInfo(fruit.getFruithostid());
							if (winfo != null) {
								Q_vipBean vipdb = ManagerPool.dataManager.q_vipContainer.getMap().get(winfo.getVip());
								if(vipdb != null && vipdb.getQ_auto_water() > 0){
									Player player = ManagerPool.playerManager.getPlayer(fruit.getFruithostid());
									if (player != null ) {
										int exp = fruitOperatingAddExp( player , player.getId(), (byte) 1, fruit);
										if (exp > 0) {
											MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("VIP果实自动浇水，获得经验{1}。"),exp+"");
										}
									}else {
										
									}
									return ;
								}
							}
							fruit.setIsarid((byte) 1);//干旱不往数据库写，减轻数据负担
							//saveSpirittree(fruit.getFruithostid(),spiritTree,false);
						}
					}
				}
			}
		}
	}

	

	
	/**通过ID找果实
	 * 
	 * @param id
	 * @param fruits
	 * @return
	 */
	public Fruit  getFruitByid(long id,Fruit[] fruits){
		for (Fruit fruit : fruits) {
			if( fruit != null && fruit.getId() == id){
				return fruit;
			}
		}
		return null ;
	}
	
	/**检测天数，清理数据
	 * 
	 * @param spiritTree
	 */
	public void checkday(SpiritTree spiritTree){
		int day = (int)TimeUtil.GetCurTimeInMin(4);
		if (spiritTree.getDay() != day) {
			spiritTree.setDay(day);
			spiritTree.setDewnum((byte) 0);
			spiritTree.setDewcooldown(0);
			spiritTree.setDayexp(0);
			spiritTree.setTheftviewsnum(0);
		}
	}
	

	/**移除果实
	 * 
	 */
	public void removeFruit(SpiritTree spiritTree , long fid){
		Fruit[] fruits = spiritTree.getFruitlist();
		for (int i = 0; i < fruits.length; i++) {
			if (fruits[i] != null ) {
				if(fruits[i].getId() == fid){
					fruits[i] = null;
				}
			}
		}
	}
	
	
	
	/**收到前端请求单个信息消息
	 * 
	 * @param msg
	 */

	public void stReqGetSingleFruitInfoToWorldMessage(ReqGetSingleFruitInfoToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			// 类型：0查看，1浇水，2，浇仙露，3采摘
			SpiritTree spiritTree = getSpiritTree(player.getId());
			int sm = (int)(System.currentTimeMillis()/1000L);
			if (spiritTree != null) {
				Fruit fruit = getFruitByid(msg.getFruitid(),spiritTree.getFruitlist());
				if (fruit == null) {
					return;
				}
				if(msg.getType() == 0){//查看果实（暂时无用）
					
					
				}else if (msg.getType() == 1) {
					ResGetSingleFruitInfoToClientMessage cmsg = new ResGetSingleFruitInfoToClientMessage();
					if(fruit.getIsarid() == 1){
						fruit.setIsarid((byte) 0);
						cmsg.setFruitinfo(fruit.makeinfo());
						MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("成功给果实浇水。"));
						int exp =fruitOperatingAddExp(player ,player.getId(),msg.getType(),fruit);
						cmsg.setExp(exp);
						saveSpirittree(fruit.getFruithostid(),spiritTree,false);
						stSpiritWateringLog(player.getId(),fruit,exp);
					}else {
						cmsg.setFruitinfo(fruit.makeinfo());
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("果实没有干旱，无需浇水。"));
					}
					cmsg.setType((byte) 2);
					MessageUtil.tell_player_message(player, cmsg);
				}else if (msg.getType() == 2) {	//浇仙露
					byte dewnum = spiritTree.getDewnum();
					if (sm > fruit.getRipeningtime()) {
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("果实已成熟，无需浇灌。"));
						return ;
					}
					Q_spirittree_packetBean fruitdata = Getbasicfruitinfo(fruit);
					if (fruitdata == null) {
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("找不到组包ID。"));
						return ;
					}
					
					int vipnum = 0;//VIP加次数
					if (player.getVipid()> 0 ) {
						Q_vipBean vipdb = ManagerPool.dataManager.q_vipContainer.getMap().get(player.getVipid());
						if (vipdb != null) {
							vipnum = vipdb.getQ_xianlu();
						}
					}
					
					if (dewnum < getdewnumData() + vipnum) {
						if(spiritTree.getDewcooldown() <= sm){
							spiritTree.setDewcooldown(sm + getdewcooldownData());	//下一次浇灌时间冷却
							spiritTree.setDewnum((byte) (dewnum+1));
							double s = (double)getdewSpeedData()/100;
							int time = growthtime(fruitdata.getQ_growing_up(),fruitdata.getQ_packet_id());
							double speed = time *s;
							int speedtime = (int)Math.ceil(speed);
							fruit.setRipeningtime(fruit.getRipeningtime()-speedtime);
							getAllFruitInfo(player);
							int dewexp = fruitOperatingAddExp(player ,player.getId(),msg.getType(),fruit);
							MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("成功给果实浇灌仙露。"));
							saveSpirittree(fruit.getFruithostid(),spiritTree,false);
							ResGetSingleFruitInfoToClientMessage cmsg = new ResGetSingleFruitInfoToClientMessage();
							cmsg.setFruitinfo(fruit.makeinfo(1));
							cmsg.setExp(dewexp);
							cmsg.setType((byte) 3);
							MessageUtil.tell_player_message(player, cmsg);
							stSpiritXianLuLog(player.getId(),fruit,speedtime);
						}else {
							MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("请稍后进行浇灌。"));
						}
					}else {
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("今日仙露浇灌次数已用完。"));
					}
				}else if (msg.getType() == 3) {//采摘
					List<FruitReward> rewardlist = fruit.getFruitrewardlist();
					if (rewardlist == null || rewardlist.size() == 0) {
						log.error("果实奖励为空");
						ResShowToRewardListToClientMessage cmsg = new ResShowToRewardListToClientMessage();
						cmsg.setIndex(-1);
						cmsg.setType(fruit.getType());
						cmsg.setFruitid(fruit.getId());
						MessageUtil.tell_player_message(player, cmsg);
						removeFruit(spiritTree,fruit.getId());
						return;
					}
					
					if (sm < fruit.getRipeningtime()) {
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("果实尚未成熟，不能采摘。"));
						return ;
					}

					ResGameMakeFruitInfoMessage gmsg = new ResGameMakeFruitInfoMessage();
					gmsg.setPlayerid(msg.getPlayerid());
					gmsg.setHostid(fruit.getFruithostid());
					int rnd= 0;
					if (fruit.getType() == 1 || fruit.getType() == 2) {
						List<Integer> rndlist = new ArrayList<Integer>();
						for (FruitReward fruitReward : rewardlist) {
							 Q_spirittree_pack_conBean fruitdata = getpackcondata().get(fruitReward.getIdx());
							 if (fruitdata == null) {
								 rndlist.add(1);
							}else{
								rndlist.add(fruitdata.getQ_selected_rnd());
							}
						}
						rnd = RandomUtils.randomIndexByProb(rndlist);
						//防御数据库填错，大于0的道具数量不应超过1000
						if (rewardlist.get(rnd).getItemModelid() > 0 && rewardlist.get(rnd).getNum() >= 1000) {
							log.error("果实数据错误：ID："+rewardlist.get(rnd).getItemModelid() + ",数量："+rewardlist.get(rnd).getNum());
							rewardlist.get(rnd).setItemModelid(-4);
							rewardlist.get(rnd).setNum(50000);
						}
						gmsg.setJsFruitdata(JSONserializable.toString(rewardlist.get(rnd)));
					}else {
						//防御数据库填错，大于0的道具数量不应超过1000
						if (rewardlist.get(0).getItemModelid() > 0 && rewardlist.get(0).getNum() >= 1000) {
							log.error("果实数据错误：ID："+rewardlist.get(0).getItemModelid() + ",数量："+rewardlist.get(0).getNum());
							rewardlist.get(0).setItemModelid(-4);
							rewardlist.get(0).setNum(50000);
						}
						gmsg.setJsFruitdata(JSONserializable.toString(rewardlist.get(0)));
					}
					long eventid = Config.getId();
					gmsg.setEventid(eventid);
					MessageUtil.send_to_game(player, gmsg);
					//log.error("发送果实奖励消息："+gmsg);
					removeFruit(spiritTree,fruit.getId());
					saveSpirittree(fruit.getFruithostid(),spiritTree,false);
					ResShowToRewardListToClientMessage cmsg = new ResShowToRewardListToClientMessage();
					cmsg.setIndex(rnd);
					cmsg.setType(fruit.getType());
					cmsg.setFruitid(fruit.getId());
					MessageUtil.tell_player_message(player, cmsg);
					stSpiritPickLog(fruit,eventid,rnd);
				}
			}
		}
	}

	
	
	
	/**浇水或者仙露获得经验
	 * 
	 * @param player
	 * @param hostid
	 */
	public int  fruitOperatingAddExp(Player player ,long hostid,byte type,Fruit fruit) {
		SpiritTree spiritTree = getSpiritTree(player.getId());
		if (spiritTree != null) {
			Q_characterBean expbean = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
			int exp = 0;
			if(type == 1){
				exp = expbean.getQ_spirittree_arid_exp();
			}else {
				exp = expbean.getQ_spirittree_dew_exp();
			}
			if(spiritTree.getDayexp() < expbean.getQ_spirittree_exp_limit()){
				if(spiritTree.getDayexp() + exp < expbean.getQ_spirittree_exp_limit()){
					spiritTree.setDayexp(spiritTree.getDayexp() + exp );
				}else {
					exp = expbean.getQ_spirittree_exp_limit() - spiritTree.getDayexp();
					spiritTree.setDayexp(expbean.getQ_spirittree_exp_limit());
				}

				if (player.getId() != hostid && exp > 0) {
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("为{1}的果实浇水成功，您的人物经验+{2}。"),fruit.obtainPlayeNname(),exp+"");
				}
			}else {
				exp = 0;	//经验满
			}
			ResFruitOperatingToGameMessage gmsg = new ResFruitOperatingToGameMessage();
			gmsg.setExp(exp);
			gmsg.setFruitid(fruit.getId());
			gmsg.setHostid(hostid);
			gmsg.setPlayerid(player.getId());
			gmsg.setType((byte) type);
			MessageUtil.send_to_game(player, gmsg);	//浇水不管经验是否满了，都要发到游戏服务器，因为浇水加军功需要
			return exp;
		}
		return 0;
	}

	
	
	/**
	 * 催熟果实消息
	 * @param msg
	 */
	public void stReqRipeningFruitToWorldMessage(ReqRipeningFruitToWorldMessage msg) {
		int sm = (int)(System.currentTimeMillis()/1000L);
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			SpiritTree spiritTree = getSpiritTree(player.getId());
			if (spiritTree != null) {
				Fruit fruit = getFruitByid(msg.getFruitid(),spiritTree.getFruitlist());
				if (fruit != null ) {
					if (fruit.getType() == 1 || fruit.getType() == 2) {
						if (sm > fruit.getRipeningtime()) {
							MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("该奇异果已成熟，请采摘"));
							return ;
						}
						Q_spirittree_packetBean kiwibean = null;
						if (fruit.getType() == 1) {
							kiwibean= ManagerPool.dataManager.q_spirittree_packetContainer.getMap().get(SilverKiwi);
						}else {
							kiwibean=ManagerPool.dataManager.q_spirittree_packetContainer.getMap().get(GoldKiwi);
						}
						if (kiwibean != null) {
//							计算公式 = （1 - 已成长时间/所有状态秒数相加之和）* 所需元宝
//							计算结果予以取整，最低为1元宝；
//							其中”所需元宝“填写于”神树组包表“中的”手动催熟所需元宝“字段；
							int shengyu = fruit.getRipeningtime() - sm;
							int time = growthtime(kiwibean.getQ_growing_up(),kiwibean.getQ_packet_id());
							int hastime= time-shengyu;	//已经成长时间
							double x = 1-((double)hastime/(double)time);
							double urgyb = x * kiwibean.getQ_urgeripening();
							int yb = (int)Math.ceil(urgyb);
							ReqRipeningDecYBToGameMessage gmsg = new ReqRipeningDecYBToGameMessage();
							gmsg.setFruitid(fruit.getId());
							gmsg.setPlayerid(player.getId());
							gmsg.setYuanbao(yb);
							MessageUtil.send_to_game(player, gmsg);
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * 催熟果实扣除元宝后返回消息
	 * @param msg
	 */
	public void stResRipeningDecYBToWorldMessage(ResRipeningDecYBToWorldMessage msg) {
		int sm = (int)(System.currentTimeMillis()/1000L);
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			SpiritTree spiritTree = getSpiritTree(player.getId());
			if (spiritTree != null) {
				Fruit fruit = getFruitByid(msg.getFruitid(),spiritTree.getFruitlist());
				if (fruit != null ) {
					if (msg.getType() == 1) {
						fruit.setRipeningtime(sm-1);
						ResGetSingleFruitInfoToClientMessage fmsg = new ResGetSingleFruitInfoToClientMessage();
						fmsg.setFruitinfo(fruit.makeinfo());
						fmsg.setType((byte) 0);
						MessageUtil.tell_player_message(player, fmsg);
					}
					ResRipeningDecYBToClientMessage cmsg = new ResRipeningDecYBToClientMessage();
					cmsg.setYuanbao(msg.getYuanbao());
					cmsg.setFruitid(msg.getFruitid());
					cmsg.setType(msg.getType());
					MessageUtil.tell_player_message(player, cmsg);
				}
			}
		}
	}

	
	/**使用道具催熟果实
	 * 
	 */
	public void stReqActivityCheckFruitToWorldMessage(ReqActivityCheckFruitToWorldMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			SpiritTree spiritTree = getSpiritTree(player.getId());
			if (spiritTree != null) {
				for (Fruit fruit : spiritTree.getFruitlist()) {
					if (fruit == null) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("果实正在采摘中，请稍后使用"));
						return;
					}
					if (fruit.getType() == msg.getType()) {
						int sm = (int)(System.currentTimeMillis()/1000L);
						if(fruit.getRipeningtime() <= sm){
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("果实已经成熟，请先采摘吧"));
						}else {
							ResActivityReturnFruitToGameMessage smsg =new ResActivityReturnFruitToGameMessage();
							smsg.setFruitid(fruit.getId());
							smsg.setPlayerid(msg.getPlayerid());
							smsg.setType(msg.getType());
							MessageUtil.send_to_game(player, smsg);
						}
						return;
					}
				}
			}
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("灵树功能未开启，不能催熟"));
		}
	}
	
	
	/**
	 * 催熟果实后返回消息
	 * @param msg
	 */
	public void stReqUrgeRipeToWorldMessage(ReqUrgeRipeToWorldMessage msg) {
		int sm = (int)(System.currentTimeMillis()/1000L);
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			SpiritTree spiritTree = getSpiritTree(player.getId());
			if (spiritTree != null) {
				Fruit fruit = getFruitByid(msg.getFruitid(),spiritTree.getFruitlist());
				if (fruit != null ) {
					
					fruit.setRipeningtime(sm-1);
					ResGetSingleFruitInfoToClientMessage fmsg = new ResGetSingleFruitInfoToClientMessage();
					fmsg.setFruitinfo(fruit.makeinfo());
					fmsg.setType((byte) 0);
					MessageUtil.tell_player_message(player, fmsg);
					
					ResRipeningDecYBToClientMessage cmsg = new ResRipeningDecYBToClientMessage();
					cmsg.setYuanbao(0);
					cmsg.setFruitid(msg.getFruitid());
					cmsg.setType((byte) 1);
					MessageUtil.tell_player_message(player, cmsg);
				}else {
					log.error(msg.getPlayerid()+",使用道具催熟果实类型：["+msg.getType()+"],果实不存在，ID："+msg.getFruitid());
				}
			}
		}
	}	
	
	
	
	
	//----------------------------行会灵树-------------------------------
	/**筛选行会内可被偷的果实
	 * 
	 * @param player
	 * @return
	 */
	public List<Fruit> selectGuildFruit(Player player){
		Guild guild = GuildWorldManager.getInstance().getGuild(player.getGuildid());
		List<Fruit > fruitlist = new ArrayList<Fruit>();
		if (guild != null) {
			HashMap<Long, MemberInfo> guildmap = guild.getMemberinfoHashMap();
			Iterator<Entry<Long, MemberInfo>> it = guildmap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, MemberInfo> entry = it.next();
				if (entry.getKey() != player.getId()) {
					PlayerWorldInfo winfo = ManagerPool.playerManager.getPlayerWorldInfo(entry.getKey());
					if (winfo != null) {
						Q_vipBean vipdb = ManagerPool.dataManager.q_vipContainer.getMap().get(winfo.getVip());
						if(vipdb != null && vipdb.getQ_auto_fruit() > 0){
							continue;
						}
					}
					SpiritTree spiritTree = getSpiritTree(entry.getKey());
					if (spiritTree != null) {
						Fruit[] fruits = spiritTree.getFruitlist();
						for (Fruit fruit : fruits) {
							if (fruit != null) {
								if (istheft(player ,fruit)) {
									fruitlist.add(fruit);
								}	
							}
						}
					}
				}
			}
		}
		return fruitlist;
	}
		

	/**计算剩余百分比
	 * 
	 * @param reward
	 * @return
	 */
	public int calculatperc(FruitReward reward){
		double n = (double)reward.getNum()/(double)reward.getSum();
		n = n*100;
		return (int)Math.ceil(n);
	}
	
	
	
	/**
	 * 检测这个果实是否可被偷窃
	 */
	public boolean istheft(Player player,Fruit fruit){
		Q_spirittree_packetBean packet = getpacketdata().get(fruit.getGroupidlist().get(0));
		if(packet !=null && fruit.getType() == 0 && packet.getQ_is_steal() == 1){		//是普通果实而且允许被偷
			List<FruitReward> rewardlist = fruit.getFruitrewardlist();
			if (rewardlist.size() > 0) {
				FruitReward reward = rewardlist.get(0);
				Q_spirittree_pack_conBean packcon = getpackcondata().get(reward.getIdx());
				if (packcon != null) {
					if(packcon.getQ_Theft_Percentage() > 0 && calculatperc(reward) > 100-packcon.getQ_Theft_Percentage()){//还有被偷的产量
						if(fruit.getStealer().containsKey(player.getId()+"")){	//是否已经当前玩家偷过
							return false;	//不可被偷
						}else {
							return true;	//可被偷
						}
					}
				}
			}
		}
		return false;	//不可被偷
	}
	
	
	/**获取干旱果实
	 * 
	 * @param player
	 * @return
	 */
	public List<Fruit > selectGuildAridFruit(Player player){
		Guild guild = GuildWorldManager.getInstance().getGuild(player.getGuildid());
		List<Fruit > fruitlist = new ArrayList<Fruit>();
		if (guild != null) {
			HashMap<Long, MemberInfo> guildmap = guild.getMemberinfoHashMap();
			Iterator<Entry<Long, MemberInfo>> it = guildmap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Long, MemberInfo> entry = it.next();
				if (entry.getKey() != player.getId()) {
					PlayerWorldInfo winfo = ManagerPool.playerManager.getPlayerWorldInfo(entry.getKey());
					if (winfo != null) {
						Q_vipBean vipdb = ManagerPool.dataManager.q_vipContainer.getMap().get(winfo.getVip());
						if(vipdb != null && vipdb.getQ_auto_water() > 0){
							continue;
						}
					}
					
					SpiritTree spiritTree = getSpiritTree(entry.getKey());
					if (spiritTree != null) {
						Fruit[] fruits = spiritTree.getFruitlist();
						for (Fruit fruit : fruits) {
							if(fruit != null){
								aridevent(fruit);
								if (fruit.getIsarid() == 1) {
									fruitlist.add(fruit);
								}
							}
						}
					}
				}
			}
		}
		return fruitlist;
	}
	
	
	
	
	
	
	/**开打行会灵树
	 * 
	 * @param msg
	 */
	public void stReqOpenGuildFruitToWorldMessage(ReqOpenGuildFruitToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			long gid = player.getGuildid();
			if(gid > 0){
				int sm = (int)(System.currentTimeMillis()/1000L);
				List<Fruit>  fruitlist = selectGuildFruit( player);
				List<Fruit> tmplist= new ArrayList<Fruit>();
				ResOpenGuildFruitToClientMessage fruitlistmsg = new ResOpenGuildFruitToClientMessage();
				
				//打乱顺序
				if (fruitlist.size() > 1) {
					for (int i = 0; i < 25; i++) {
						int rnd = RandomUtils.random(1,fruitlist.size()) - 1;
						Fruit fruit = fruitlist.remove(rnd);
						fruitlist.add(fruit);
					}
				}
				
				//筛选果实
				for (Fruit fruit : fruitlist) {
					FruitInfo fruitinfo = fruit.makeinfo();
					if (fruit.getRipeningtime() < sm ) {	//已经成熟的果实
						fruitlistmsg.getFruitinfo().add(fruitinfo);
						if (MatuMapTime.containsKey(gid) == false) {		//几率最后成熟果子
							MatuMapTime.put(gid, fruit.getRipeningtime());
							MatuMapNnme.put(gid,ManagerPool.playerManager.getPlayerName(fruit.getFruithostid()));
						}else {
							if (MatuMapTime.get(gid) < fruit.getRipeningtime()) {
								MatuMapTime.put(gid, fruit.getRipeningtime());
								MatuMapNnme.put(gid,ManagerPool.playerManager.getPlayerName(fruit.getFruithostid()));
							}
						}
						
					}else {
						tmplist.add(fruit);		//其他的
					}
					if(fruitlistmsg.getFruitinfo().size() >= 6){
						break;
					}
				}
				
				if(tmplist.size() > 0){
					Collections.sort(tmplist,new Comparator<Fruit>() {	
						@Override
						public int compare(Fruit o1, Fruit o2) {
							if(o1.getRipeningtime() > o2.getRipeningtime()){
								return 1;
							}
							return 0;
						}
					});
				}
				
				if (fruitlistmsg.getFruitinfo().size() < 6) {
					for (Fruit fruit : tmplist) {
						if (fruitlistmsg.getFruitinfo().size() < 6) {	//下面add会使列表大于6，所以要判断
							if (fruit.getIsarid() == 0) {
								fruitlistmsg.getFruitinfo().add(fruit.makeinfo(1));	
							}
						}else {
							break;
						}
					}
				}
				//寻找干旱果实
				List<Fruit> aridfruits = selectGuildAridFruit(player);
				if (aridfruits.size() > 0) {
					//打乱顺序
					for (int i = 0; i < 20; i++) {	
						int rnd = RandomUtils.random(1,aridfruits.size()) - 1;
						Fruit fruit = aridfruits.remove(rnd);
						aridfruits.add(fruit);
					}
					//填入前3个果实
					for (Fruit fruit : aridfruits) {
						fruitlistmsg.getAridfruitinfo().add(fruit.makeinfo());
						if (fruitlistmsg.getAridfruitinfo().size() >= 3) {
							break;
						}
					}
				}
				SpiritTree spiritTree = getSpiritTree(player.getId());
				if (spiritTree != null) {
					fruitlistmsg.setTheftnum(spiritTree.getTheftviewsnum());//抢摘次数
				}
				
				MessageUtil.tell_player_message(player, fruitlistmsg);
				
				//上一个和下一个果实成熟时间
				ResShowGuildMatureClientMessage Maturemsg = new ResShowGuildMatureClientMessage();
				if (MatuMapNnme.containsKey(gid)) {
					Maturemsg.setLastname(MatuMapNnme.get(gid));
					Maturemsg.setLasttime(sm - MatuMapTime.get(gid));
				}else {
					Maturemsg.setLastname("");
					Maturemsg.setLasttime(0);
				}

				if (tmplist.size() > 0) {
					Maturemsg.setNextname(ManagerPool.playerManager.getPlayerName(tmplist.get(0).getFruithostid()));
					Maturemsg.setNexttime(tmplist.get(0).getRipeningtime() - sm);
				}else {
					Maturemsg.setNextname("");
					Maturemsg.setNexttime(0);
				}
				MessageUtil.tell_player_message(player, Maturemsg);
				
			}else {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("您没有加入行会。"));
			}
		}
	}

	

	
	
	
	
	
	
	
	
	
	/**行会灵树操作
	 * @param msg
	 */
	public void stReqGuildFruitOperatingToWorldMessage(ReqGuildFruitOperatingToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			int sm = (int)(System.currentTimeMillis()/1000L);
			Guild guild = GuildWorldManager.getInstance().getGuild(player.getGuildid());
			if (guild == null) {
				return;
			}
			HashMap<Long, MemberInfo> membermap = guild.getMemberinfoHashMap();
			if (membermap.containsKey(msg.getHostid()) == false) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对方已经离开您所在帮会，不能对他的果实进行操作。"));
				ResShowToRewardListToClientMessage xmsg = new ResShowToRewardListToClientMessage();
				xmsg.setIndex(-1);
				MessageUtil.tell_player_message(player, xmsg);
				return;
			}
			if (msg.getHostid() == msg.getPlayerid()) {
				return;
			}

			SpiritTree spiritTree = getSpiritTree(msg.getHostid());
			if (spiritTree == null) {
				return;
			}
			SpiritTree plaspiritTree = getSpiritTree(player.getId());
			if (plaspiritTree == null) {
				return;
			}

			
			Fruit fruit = getFruitByid(msg.getFruitid(),spiritTree.getFruitlist());
			if (fruit == null) {
				ResFruitErrorToClientMessage errmsg = new ResFruitErrorToClientMessage();
				errmsg.setType( (byte) 1);//错误类型：1表示果实不存在
				MessageUtil.tell_player_message(player, errmsg);
				return;
			}
			
			if (msg.getType() == 1) {//浇水
				ResGetSingleFruitInfoToClientMessage cmsg = new ResGetSingleFruitInfoToClientMessage();
				if (fruit.getIsarid() == 1) {
					fruit.setIsarid((byte) 0);
					int exp = fruitOperatingAddExp(player ,fruit.getFruithostid(),msg.getType(),fruit);
					if (exp > 0) {
						MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("	为『{1}』的果实浇水成功，您的人物经验+{2}。"),fruit.obtainPlayeNname(),exp+"");
					}
					saveSpirittree(fruit.getFruithostid(),spiritTree,false);
					setclientlog(player,msg.getHostid(),(byte)2,exp,fruit);
					cmsg.setExp(exp);
					Player hostplayer = ManagerPool.playerManager.getPlayer(fruit.getFruithostid());
					if (hostplayer != null) {
						MessageUtil.notify_player(hostplayer,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("『{1}』为您的灵树果实浇水。"),player.getName());
					}
					stSpiritWateringLog(player.getId(),fruit,exp);
				}else {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该果实没有干旱，或者已经被人浇水。"));
				}
				cmsg.setFruitinfo(fruit.makeinfo(1));
				cmsg.setType((byte) 2);
				MessageUtil.tell_player_message(player, cmsg);
			}else if (msg.getType() == 2) {	//抢摘
				if (plaspiritTree .getTheftviewsnum() >= getTheftViewsmaxnum()) {
					ResFruitErrorToClientMessage errmsg = new ResFruitErrorToClientMessage();
					errmsg.setType( (byte) 2);//错误类型：2表示摘取次数达到上限
					MessageUtil.tell_player_message(player, errmsg);
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("今天的抢摘次数已经达到上限，明天再来吧"));
					return;
				}
				
				List<FruitReward> rewardlist = fruit.getFruitrewardlist();
				if (rewardlist == null || rewardlist.size() == 0) {
					log.error("果实奖励为空");
					ResShowToRewardListToClientMessage cmsg = new ResShowToRewardListToClientMessage();
					cmsg.setIndex(-1);
					cmsg.setType(fruit.getType());
					cmsg.setFruitid(fruit.getId());
					MessageUtil.tell_player_message(player, cmsg);
					removeFruit(spiritTree,fruit.getId());
					return;
				}

				PlayerWorldInfo winfo = ManagerPool.playerManager.getPlayerWorldInfo(fruit.getFruithostid());
				if (winfo != null) {
					Q_vipBean vipdb = ManagerPool.dataManager.q_vipContainer.getMap().get(winfo.getVip());
					if(vipdb != null && vipdb.getQ_auto_fruit() > 0){
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("对方是VIP，不能偷取果实。"));
						ResFruitErrorToClientMessage errmsg = new ResFruitErrorToClientMessage();
						errmsg.setType( (byte) 2);//错误类型：2表示摘取次数达到上限
						MessageUtil.tell_player_message(player, errmsg);
						return;
					}
				}
				
				if (sm < fruit.getRipeningtime()) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("果实尚未成熟，不能采摘。"));
					return ;
				}
				FruitReward reward = rewardlist.get(0);
				Q_spirittree_pack_conBean packcon = getpackcondata().get(reward.getIdx());
				if (packcon == null) {
					return;
				}
				
				if (calculatperc(reward) <= 100-packcon.getQ_Theft_Percentage()){
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("灵果产量保底了，请手下留情。"));
					ResGetSingleFruitInfoToClientMessage xcmsg = new ResGetSingleFruitInfoToClientMessage();
					xcmsg.setFruitinfo(fruit.makeinfo());
					xcmsg.setType((byte) 1);
					MessageUtil.tell_player_message(player, xcmsg);
					return;
				}
				HashMap<String ,Integer> logmap = fruit.getStealer();
				if (logmap.containsKey(player.getId()+"")) {
					ResFruitErrorToClientMessage errmsg = new ResFruitErrorToClientMessage();
					errmsg.setType( (byte) 1);//错误类型：1表示果实不存在
					MessageUtil.tell_player_message(player, errmsg);
					return;
				}
				
				
				ResGameMakeFruitInfoMessage gmsg = new ResGameMakeFruitInfoMessage();
				long eventid = Config.getId();
				gmsg.setEventid(eventid);
				gmsg.setPlayerid(msg.getPlayerid());
				gmsg.setHostid(fruit.getFruithostid());
				
				if (fruit.getType() != 1 && fruit.getType() != 2) {
					FruitReward newReward = new  FruitReward();
					
					copyReward(newReward ,rewardlist.get(0));	//复制奖励内容
					int beutou = calculateSteal(newReward,fruit,packcon);	//计算偷窃比例
					
					if (newReward.getNum() > 0) {
						gmsg.setJsFruitdata(JSONserializable.toString(newReward));
						MessageUtil.send_to_game(player, gmsg);	//发送到游戏服务器
						
						ResShowToRewardListToClientMessage cmsg = new ResShowToRewardListToClientMessage();
						cmsg.setIndex(0);
						cmsg.setType(fruit.getType());
						cmsg.setFruitid(fruit.getId());
						MessageUtil.tell_player_message(player, cmsg);	//发送前端奖励展示
						
						
						logmap.put(player.getId()+"", beutou);	//记录偷窃者ID
					
						
						ResGetSingleFruitInfoToClientMessage fruitmsg = new ResGetSingleFruitInfoToClientMessage();
						fruitmsg.setFruitinfo(fruit.makeinfo(1));
						fruitmsg.setType((byte) 0);
						fruitmsg.setNum(newReward.getNum());
						MessageUtil.tell_player_message(player, fruitmsg);	//发送前端果实数量
						
						
						
						//----------------补偿
						int buchangexp = 0;
						Player hostplayer = ManagerPool.playerManager.getPlayer(fruit.getFruithostid());
						PlayerWorldInfo worldInfo = ManagerPool.playerManager.getPlayerWorldInfo(fruit.getFruithostid());
						int lv = 1;
						if (worldInfo != null) {
							lv = worldInfo.getLevel();
							Q_spirittree_packetBean pacdata = getpacketdata().get(fruit.getGroupidlist().get(0));
							if (lv ==0) {
								lv =10;
							}
							Q_characterBean expbean = ManagerPool.dataManager.q_characterContainer.getMap().get(lv);
							int addexp = expbean.getQ_basis_exp()*getexpcompendata();
							if(spiritTree.getDayexp() < expbean.getQ_spirittree_exp_limit()){
								spiritTree.setDayexp(addexp+spiritTree.getDayexp());
								if (hostplayer != null ) {
									MessageUtil.notify_player(hostplayer,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("您的[{1}]被[{2}]抢收了一部分产量，为此您获得了[经验+{3}]的补偿。"),pacdata.getQ_packet_name(),player.getName(),addexp+"");
									ResGuildAccExpToWorldMessage expmsg = new ResGuildAccExpToWorldMessage();
									expmsg.setExp(addexp);
									expmsg.setPlayerid(hostplayer.getId());
									MessageUtil.send_to_game(hostplayer, expmsg);
								}else {
									spiritTree.setBuchangexp(addexp+spiritTree.getBuchangexp());//玩家不在线，储存补偿经验
								}
								buchangexp = addexp;
								
							}else {
								if (hostplayer != null ) {
									MessageUtil.notify_player(hostplayer,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("您的[{1}]被[{2}]抢收了一部分产量。"),pacdata.getQ_packet_name(),player.getName());
								}
							}
						}
						setclientlog(player,msg.getHostid(),(byte)0,buchangexp,fruit,beutou);	//被偷日志记录
						saveSpirittree(fruit.getFruithostid(),spiritTree,false);
						stSpiritStealLog(newReward.getNum(),player.getId(),fruit,buchangexp,eventid);

						int num = plaspiritTree.getTheftviewsnum() + 1;	//记录偷窃次数
						plaspiritTree.setTheftviewsnum(num);
						MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("今日第{1}次抢摘"),num+"");
						
						ResFruitTheftNumToClientMessage tmsg = new ResFruitTheftNumToClientMessage();
						tmsg.setTheftnum(num);	//偷窃次数发送前端
						MessageUtil.tell_player_message(player, tmsg);
						
					}else {
						ResShowToRewardListToClientMessage cmsg = new ResShowToRewardListToClientMessage();
						cmsg.setIndex(-1);
						cmsg.setType(fruit.getType());
						cmsg.setFruitid(fruit.getId());
						MessageUtil.tell_player_message(player, cmsg);
						log.error("果实可偷取数量为0");
					}
				}
			}
		}
	}


	/**复制奖励内容
	 * 
	 * @param reward
	 */
	public void copyReward(FruitReward newReward, FruitReward reward){
		newReward.setBind(reward.isBind());
		newReward.setFruitRewardAttrslist(reward.getFruitRewardAttrslist());
		newReward.setIdx(reward.getIdx());
		newReward.setItemModelid(reward.getItemModelid());
		newReward.setLosttime(reward.getLosttime());
		newReward.setSum(reward.getSum());
		newReward.setNum(0);
		newReward.setStrenglevel(reward.getStrenglevel());
		newReward.setUse(reward.isUse());

		
	}

	
	/**计算偷窃比例
	 * 
	 * @param newReward
	 * @param fruit
	 * @param packcon
	 * @return 
	 */
	public int calculateSteal(FruitReward newReward, Fruit fruit,Q_spirittree_pack_conBean packcon) {
		FruitReward oldReward = fruit.getFruitrewardlist().get(0);
		int shengyu = calculatperc(oldReward);
		int beitou = shengyu - (100-packcon.getQ_Theft_Percentage());//得到最大可被偷的值
		if (beitou >= STEALNUM) {
			double n = (double)oldReward.getSum()*((double)STEALNUM/100);//得到被偷的值
			double y = oldReward.getNum() - n;	//得到剩余值
			oldReward.setNum((int) y);
			newReward.setNum((int) n);
			fruit.setYield(calculatperc(oldReward));
			return (int)n;
		}else {
			if (beitou > 0) {
				double n = (double)oldReward.getSum()*((double)beitou/100);//得到被偷的值
				double y = oldReward.getNum() - n;	//得到剩余值
				oldReward.setNum((int) y);
				newReward.setNum((int) n);	
				fruit.setYield(calculatperc(oldReward));
				return (int)n;
			}
		}
		return 0;
	}
	
	
	
	
	/**日志处理
	 * 设置客户端展示日志
	 * type 类型  0抢收，1被抢收，2互助（浇水），3互助（被浇水）
	 */
	public void setclientlog(Player player , long otherid, byte type,int exp,Fruit fruit  ) {
		setclientlog(player,otherid,type,exp,fruit,0);
	}
	
	
	public void setclientlog(Player player , long otherid, byte type,int exp,Fruit fruit ,int num) {
		SpiritTree spiritTree = getSpiritTree(player.getId());
		int time = (int) (System.currentTimeMillis()/1000);
		if (spiritTree != null) {
			SpiritTreeLog clog = new SpiritTreeLog();
			clog.setExp(exp);
			clog.setTime(time);
			clog.setOtherid(otherid);
			clog.setType(type);
			clog.setGroupid(fruit.getGroupidlist().get(0));
			if (fruit.getFruitrewardlist().size() > 0) {
				clog.setItemid(fruit.getFruitrewardlist().get(0).getItemModelid());
			}
			clog.setItemnum(num);
			if(spiritTree.getSpirittreelogs().size() >= 101){
				spiritTree.getSpirittreelogs().remove(100);
			}
			spiritTree.getSpirittreelogs().add(0,clog);
		}


		
		//------------------------------------------
		SpiritTree otherspiritTree = getSpiritTree(otherid);
		if (otherspiritTree != null) {
			SpiritTreeLog otherlog = new SpiritTreeLog();
			otherlog.setExp(exp);
			otherlog.setTime(time);
			otherlog.setOtherid(player.getId());
			otherlog.setType((byte)(type+1));
			otherlog.setGroupid(fruit.getGroupidlist().get(0));
			if (fruit.getFruitrewardlist().size() > 0) {
				otherlog.setItemid(fruit.getFruitrewardlist().get(0).getItemModelid());
			}
			otherlog.setItemnum(num);
			if(otherspiritTree.getSpirittreelogs().size() >= 101){
				otherspiritTree.getSpirittreelogs().remove(100);
			}
			otherspiritTree.getSpirittreelogs().add(0,otherlog);
		}

	}

	
	
	/**前端请求获取行会神树操作日志
	 * 
	 * @param msg
	 */
	public void stReqGuildFruitLogToWorldMessage(ReqGuildFruitLogToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null ) {
			SpiritTree spiritTree = getSpiritTree(player.getId());
			if (spiritTree!= null) {
				ResGuildFruitLogToClientMessage cmsg = new ResGuildFruitLogToClientMessage();
				ArrayList<SpiritTreeLog> loglist = spiritTree.getSpirittreelogs();
				for (SpiritTreeLog spiritTreeLog : loglist) {
					GuildFruitLog guildFruitLog = new GuildFruitLog();
					guildFruitLog.setExp(spiritTreeLog.getExp());
					guildFruitLog.setGroupid(spiritTreeLog.getGroupid());
					guildFruitLog.setOtherid(spiritTreeLog.getOtherid());
					Player other = ManagerPool.playerManager.getPlayer(spiritTreeLog.getOtherid());
					if (other != null) {
						guildFruitLog.setIsonline((byte) 1);
						guildFruitLog.setOthername(other.getName());
					}else {
						//得到玩家名字
						PlayerWorldInfo worldInfo = ManagerPool.playerManager.getPlayerWorldInfo(spiritTreeLog.getOtherid());
						if (worldInfo != null) {
							guildFruitLog.setOthername(worldInfo.getName());
						}else {
							guildFruitLog.setOthername("未知");
						}
					}
					guildFruitLog.setItemmodelid(spiritTreeLog.getItemid());
					guildFruitLog.setItemnum(spiritTreeLog.getItemnum());
					guildFruitLog.setType(spiritTreeLog.getType());
					guildFruitLog.setTime(spiritTreeLog.getTime());
					cmsg.getGuildfruitlog().add(guildFruitLog);
				}
				MessageUtil.tell_player_message(player, cmsg);
			}
		}
	}
	
	
	
	/**采摘日志
	 * 
	 */
	public void stSpiritPickLog(Fruit fruit,long eventid,int rnd){
		SpiritPickLog picklog = new SpiritPickLog();
		picklog.setFruitid(fruit.getId());
		picklog.setPlayerid(fruit.getFruithostid());
		if (fruit.getFruitrewardlist().size() > 0) {
			picklog.setRewardinfo(JSONserializable.toString(fruit.getFruitrewardlist().get(rnd)));
		}else {
			picklog.setRewardinfo("");
		}
		picklog.setYield(fruit.getYield());
		picklog.setEventid(eventid);
		LogService.getInstance().execute(picklog);
	}
	
	/**偷窃日志
	 * 
	 * @param num
	 * @param stolenplayerid
	 * @param fruit
	 */
	public void stSpiritStealLog(int num ,long playerid,Fruit fruit,int exp,long eventid){
		SpiritStealLog steallog = new SpiritStealLog();
		steallog.setExp(exp);
		steallog.setFruitid(fruit.getId());
		steallog.setStolenplayerid(fruit.getFruithostid());
		steallog.setStolennum(num);
		steallog.setPlayerid(playerid);
		if (fruit.getFruitrewardlist().size() > 0) {
			steallog.setRewardinfo(JSONserializable.toString(fruit.getFruitrewardlist().get(0)));
		}else {
			steallog.setRewardinfo("");
		}
		steallog.setEventid(eventid);
		LogService.getInstance().execute(steallog);
	}
	
	
	
	
	/**浇水日志
	 * 
	 * @param num
	 * @param stolenplayerid
	 * @param fruit
	 */
	public void stSpiritWateringLog(long playerid ,Fruit fruit,int exp){
		SpiritWateringLog wateringlog = new SpiritWateringLog();
		wateringlog.setExp(exp);
		wateringlog.setFruitid(fruit.getId());
		wateringlog.setHostplayerid(fruit.getFruithostid());
		wateringlog.setPlayerid(playerid);
		LogService.getInstance().execute(wateringlog);
	}
	
	/**浇水日志
	 * 
	 * @param num
	 * @param stolenplayerid
	 * @param fruit
	 */
	public void stSpiritXianLuLog(long playerid ,Fruit fruit,int time){
		SpiritXianLuLog xianlulog = new SpiritXianLuLog();
		xianlulog.setFruitid(fruit.getId());
		xianlulog.setAcceleratetime(time);
		xianlulog.setPlayerid(playerid);
		LogService.getInstance().execute(xianlulog);
	}

	
	/**GM指令
	 * 
	 * @param msg
	 */
	public void stReqGuildGMToWorldMessage(ReqGuildGMToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player != null) {
			if (msg.getType() == 1) {
				SpiritTree spiritTree = getSpiritTree(msg.getPlayerid());
				if (spiritTree != null) {
					Fruit[] fruits = spiritTree.getFruitlist();
					for (Fruit fruit : fruits) {
						if (fruit != null) {
							fruit.setRipeningtime((int)(System.currentTimeMillis()/1000)-1);
						}
					}
					MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("果实成熟了。"));
				}
			}else if (msg.getType() == 2) {
				SpiritTree spiritTree = getSpiritTree(msg.getPlayerid());
				if (spiritTree != null) {
					spiritTree.setTheftviewsnum(0);
					spiritTree.setDewcooldown(0);
					spiritTree.setDewnum((byte) 0);
					MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("仙露浇灌次数已经清除。"));
				}
			}
		}
	}
	
	
	
	
	/**清理没有帮会而且不在线的玩家神树数据
	 * 
	 */
	public void clearupSpiritdata(){
		int num = 0;
		int sum = spirittreelist.size();
		Iterator<Entry<Long, SpiritTree>> it = spirittreelist.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Long, SpiritTree> entry =  it.next();
			if(ManagerPool.guildWorldManager.getGuildByUserId(entry.getKey()) == null){//没有帮会
				if (ManagerPool.playerManager.getPlayer(entry.getKey()) == null) {//不在线
					it.remove();
					num = num+1;
				}
			}
		}
		log.error("spirittreelist总数:"+sum+",清理："+num);
	}


	
	
	
	/**连续催熟果实
	 * @param msg 
	 * 
	 */
	public void continuousripening(ReqContinuousRipeningToWorldMessage msg){
		long pid = msg.getPlayerid();
		int type = msg.getType();
		int num = msg.getNum();
		Player player = ManagerPool.playerManager.getPlayer(pid);
		if (player != null) {
			HashMap<Integer, Q_spirittree_pack_conBean> rewarddata = getpackcondata();
			ResContinuousRipeningToClientMessage cmsg=new ResContinuousRipeningToClientMessage();
			for (int i = 0; i < num; i++) {
				Fruit fruit = new Fruit();
				fruit.setType((byte) type);
				fruit.setFruithostid(player.getId());
				fruit.setYield(-1);
				fruit.setType((byte) type);
				randomGroup(player ,type,fruit);
				Rewardbriefinfo info = new Rewardbriefinfo();
				createFruitRewardList(player,fruit,rewarddata);	//筛选并设置奖励列表 
				ResGameMakeFruitInfoMessage gmsg = new ResGameMakeFruitInfoMessage();
				gmsg.setPlayerid(player.getId());
				gmsg.setHostid(player.getId());
				long eventid = Config.getId();
				gmsg.setEventid(eventid);
				gmsg.setType((byte) type);
				int rnd= 0;
				List<FruitReward> rewardlist = fruit.getFruitrewardlist();
				if (fruit.getType() == 1 || fruit.getType() == 2) {
					List<Integer> rndlist = new ArrayList<Integer>();
					for (FruitReward fruitReward : rewardlist) {
						 Q_spirittree_pack_conBean fruitdata = getpackcondata().get(fruitReward.getIdx());
						 if (fruitdata == null) {
							 rndlist.add(1);
						}else{
							rndlist.add(fruitdata.getQ_selected_rnd());
						}
					}
					rnd = RandomUtils.randomIndexByProb(rndlist);
					//防御数据库填错，大于0的道具数量不应超过1000
					if (rewardlist.get(rnd).getItemModelid() > 0 && rewardlist.get(rnd).getNum() >= 1000) {
						log.error("果实数据错误：ID："+rewardlist.get(rnd).getItemModelid() + ",数量："+rewardlist.get(rnd).getNum());
						rewardlist.get(rnd).setItemModelid(-4);
						rewardlist.get(rnd).setNum(50000);
					}
					gmsg.setJsFruitdata(JSONserializable.toString(rewardlist.get(rnd)));
					info.setItemmodelid(rewardlist.get(rnd).getItemModelid());
					info.setItemnum(rewardlist.get(rnd).getNum());
				}else {
					//防御数据库填错，大于0的道具数量不应超过1000
					if (rewardlist.get(0).getItemModelid() > 0 && rewardlist.get(0).getNum() >= 1000) {
						log.error("果实数据错误：ID："+rewardlist.get(0).getItemModelid() + ",数量："+rewardlist.get(0).getNum());
						rewardlist.get(0).setItemModelid(-4);
						rewardlist.get(0).setNum(50000);
					}
					gmsg.setJsFruitdata(JSONserializable.toString(rewardlist.get(0)));
					info.setItemmodelid(rewardlist.get(0).getItemModelid());
					info.setItemnum(rewardlist.get(0).getNum());
				}

				MessageUtil.send_to_game(player, gmsg);
				stSpiritPickLog(fruit,eventid,rnd);
				cmsg.getRewardbriefinfo().add(info);
			}
			
			MessageUtil.tell_player_message(player, cmsg);
		
		}

	}
	

}
