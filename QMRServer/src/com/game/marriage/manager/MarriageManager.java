package com.game.marriage.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.backpack.structs.Item;
import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffType;
import com.game.config.Config;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_globalBean;
import com.game.db.bean.MarriageBean;
import com.game.db.bean.WeddingBean;
import com.game.db.dao.MarriageDao;
import com.game.db.dao.WeddingDao;
import com.game.dblog.LogService;
import com.game.guild.bean.GuildInfo;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.longyuan.structs.LongYuanData;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.marriage.bean.WeddingInfo;
import com.game.marriage.log.MarriageLog;
import com.game.marriage.log.WeddingLog;
import com.game.marriage.message.ReqApplyWeddingToGameMessage;
import com.game.marriage.message.ReqChangeRingToGameMessage;
import com.game.marriage.message.ReqCockRedenvelopeToGameMessage;
import com.game.marriage.message.ReqDeleteLeaveMsgToGameMessage;
import com.game.marriage.message.ReqDeleteMarriageToWorldMessage;
import com.game.marriage.message.ReqDivorceSelectToGameMessage;
import com.game.marriage.message.ReqDivorceToGameMessage;
import com.game.marriage.message.ReqGetSpouseOtherToGameMessage;
import com.game.marriage.message.ReqLaunchProposeStartToGameMessage;
import com.game.marriage.message.ReqNewLeaveMsgToGameMessage;
import com.game.marriage.message.ReqProposeSelectToGameMessage;
import com.game.marriage.message.ReqUpdatedMarriageToWorldMessage;
import com.game.marriage.message.ReqWeddingEatingToGameMessage;
import com.game.marriage.message.ReqWeddingParticipateToGameMessage;
import com.game.marriage.message.ResDivorceToClientMessage;
import com.game.marriage.message.ResEdibleInfoToClientMessage;
import com.game.marriage.message.ResForceDivorceToClientMessage;
import com.game.marriage.message.ResGetSpouseLongYuanToClientMessage;
import com.game.marriage.message.ResLaunchProposeStartToClientMessage;
import com.game.marriage.message.ResLaunchProposeToClientMessage;
import com.game.marriage.message.ResMarriageinfoToClientMessage;
import com.game.marriage.message.ResMarriedSuccessToClientMessage;
import com.game.marriage.message.ResNoticeNewLeaveMsgToClientMessage;
import com.game.marriage.message.ResNoticeReceiveRedToClientMessage;
import com.game.marriage.message.ResOnlineStatusToClientMessage;
import com.game.marriage.message.ResRedEnvelopeToClientMessage;
import com.game.marriage.message.ResReplaceRingToClientMessage;
import com.game.marriage.message.ResShowLeaveMsgToClientMessage;
import com.game.marriage.message.ResSpouseInfoToClientMessage;
import com.game.marriage.message.ResTeaseSpouseToClientMessage;
import com.game.marriage.message.ResWeddingExteriorToClientMessage;
import com.game.marriage.message.ResWeddingListToClientMessage;
import com.game.marriage.message.ResWeddingTimeToClientMessage;
import com.game.marriage.message.ResWeddingbanquetToClientMessage;
import com.game.marriage.structs.LeaveMsg;
import com.game.marriage.structs.Marriage;
import com.game.marriage.structs.Propose;
import com.game.marriage.structs.RedEnvelope;
import com.game.marriage.structs.Spouse;
import com.game.marriage.structs.Wedding;
import com.game.npc.struts.NPC;
import com.game.player.bean.OtherPlayerInfo;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.server.impl.WServer;
import com.game.skill.structs.Skill;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
import com.game.utils.VersionUpdateUtil;
import com.game.utils.WordFilter;

/**结婚系统
 * 
 * @author zhangrong
 *
 */
public class MarriageManager {
	private Logger log = Logger.getLogger(MarriageManager.class);
	
	/**婚姻信息列表
	 * 
	 */
	private static ConcurrentHashMap<Long, Marriage> marriagemap = new  ConcurrentHashMap<Long, Marriage>();

	/**正在举办的婚宴ID索引
	 */
	private static List<Long> Weddingidxlist = new ArrayList<Long>();
	
	/**婚宴信息
	 */
	private static ConcurrentHashMap<Long,Wedding> Weddingmap = new  ConcurrentHashMap<Long, Wedding>();
	
	/**婚宴状态 0没有进行，1进行中，2今天已经结束
	 * 
	 */
	private byte weddingstatus;
	
	/**
	 * 求婚列表
	 */
	private static ConcurrentHashMap<Long, List<Propose>> Proposemap = new  ConcurrentHashMap<Long, List<Propose>>();
	
	/**标记要删除的结婚ID
	 *  KEY=ID， 时间
	 */
	public static ConcurrentHashMap<Long, Long> MARKMAP = new  ConcurrentHashMap<Long,Long>();
	
	
	
	private static Object obj = new Object();
	
	
	private MarriageManager(){
		
	}
	
	
	private static MarriageManager manager;
	
	public static MarriageManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new MarriageManager();
			}
		}
		return manager;
	}
	

	private MarriageDao marriageDao = new MarriageDao();
	private WeddingDao weddingDao = new WeddingDao();
	
	public MarriageDao getMarriageDao() {
		return marriageDao;
	}
	public WeddingDao getWeddingDao() {
		return weddingDao;
	}
	
	
	/**获取结婚信息
	 * 
	 * @param id
	 * @return
	 */
	public Marriage getMarriage(long id){
		synchronized (marriagemap) {
			if(marriagemap.containsKey(id)){
				return marriagemap.get(id);
			}
		}
		return null;
	}
	
	/**获取婚宴信息
	 * 
	 * @param id
	 * @return
	 */
	public Wedding getWedding(long id){
		synchronized (Weddingmap) {
			if(Weddingmap.containsKey(id)){
				return Weddingmap.get(id);
			}
		}
		return null;
	}
	
	
	
	/**得到婚宴价格
	 * 
	 * @param id
	 * @return
	 */
	public Integer[] getWeddingPrice(int id){
		if (id == 0) {//0表示没有选择婚宴
			return null;
		}
		
		Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.WEDDING_PRICE.getValue());
		if (data  == null) {
			return null;
		}
		
		try {
			List<Integer[]> ringPricelist = JSON.parseArray(data.getQ_string_value(), Integer[].class);
			return ringPricelist.get(id-1);
		} catch (Exception e) {
			log.error("婚宴价格错误"+e,e);
		}
		return null;
	}
	
	
	/**得到最小红包
	 * 
	 * @param type 婚宴类型 1，2，3
	 * @return
	 */
	public int getLeastRedEnvelope(int type){
		Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.WEDDING_LEAST_RED.getValue());
		if (data  == null) {
			return -1;
		}
		
		try {
			String[] str = data.getQ_string_value().split(Symbol.SHUXIAN_REG);
			String string= str[type-1];
			return Integer.valueOf(string);
		} catch (Exception e) {
			log.error("婚宴价格错误"+e,e);
		}
		return -1;
	}
	
	
	
	
	/**得到婚戒价格
	 * 
	 * @param id
	 * @return
	 */
	public Integer[] getRingPrice(int id){
//		if (id == 0) {//0表示没有选择婚戒
//			return 0;
//		}
		
		Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.WEDDING_RING.getValue());
		if (data  == null) {
			return  null;
		}
		try {
			List<Integer[]> ringPricelist = JSON.parseArray(data.getQ_string_value(), Integer[].class);
			for (Integer[] integers : ringPricelist) {
				if((int)integers[0] == id){
					if (integers.length < 3) {
						return null;
					}
					return integers;
				}
			}
		} catch (Exception e) {
			log.error("婚戒价格错误"+e,e);
		}

		return null;
	}
	
	
	
	
	//-------------------------------结婚信息----------------------------------------
	/**
	 * 服务器启动 ，从数据库 读取所有结婚信息
	 * 
	 */
	public void loadAllMarriage() {
		try {
			List<MarriageBean> list = getMarriageDao().select();
			Iterator<MarriageBean> iterator = list.iterator();
			while (iterator.hasNext()) {
				MarriageBean marriageBean = (MarriageBean) iterator.next();
				if (marriageBean != null) {
					Marriage marriage = (Marriage) JSONserializable.toObject(VersionUpdateUtil.dateLoad(marriageBean.getData()), Marriage.class);
					marriagemap.put(marriageBean.getId(), marriage);
				}
			}
			log.error("婚姻数据载入完毕");
		}catch (Exception e) {
			log.error(e,e);
		}
	}
	
	
	/**更新单个结婚数据,0更新，1插入
	 * 
	 * @param pid
	 * @param marriage
	 */
	public synchronized void saveMarriageinfo(long id ,Marriage marriage, boolean insert){
		if (insert) {
			marriagemap.put(id, marriage);
		}
		saveMarriage(id, marriage, insert);
	}
	
	
	/**
	 * 删除单个结婚数据  到数据库 异步
	 * @param pid
	 * @param 
	 */
	public synchronized void deleteMarriage(Marriage marriage) {
		try {
			long id = marriage.getId();
			marriagemap.remove(id);
			MarriageBean marriageBean  = new MarriageBean();
			marriageBean.setId(marriage.getId());
			String string=JSONserializable.toString(marriage);
			marriageBean.setData(VersionUpdateUtil.dateSave(string));
			marriageBean.setDeleted(1);
			int type=0;	//更新
			WServer.getInstance().getwSaveMarriageThread().dealMarriage(marriageBean, type);
			log.error("婚姻信息被删除="+string);
		} catch (Exception e) {
			log.error("删除单个结婚数据="+e,e);
		}
	}
	
	/**
	 * 储存单个结婚数据  到数据库 异步
	 * @param pid
	 * @param 
	 */
	public void saveMarriage(long id ,Marriage marriage, boolean insert) {
		try {
			MarriageBean marriageBean  = new MarriageBean();
			marriageBean.setId(id);
			marriageBean.setData(VersionUpdateUtil.dateSave(JSONserializable.toString(marriage)));
			String tmpString= marriageBean.getData();
			int type=0;	//更新
			if (insert) {
				type = 1;//插入
			}
			WServer.getInstance().getwSaveMarriageThread().dealMarriage(marriageBean, type);
			
			if (insert) {
				ReqUpdatedMarriageToWorldMessage wmsg = new ReqUpdatedMarriageToWorldMessage();
				List<Spouse> list = marriage.getSpouseslist();
				wmsg.setMarriageid(id);
				wmsg.setBride(list.get(1).getName());
				wmsg.setBridegroom(list.get(0).getName());
				wmsg.setBrideid(list.get(1).getPlayerid());
				wmsg.setBridegroomid(list.get(0).getPlayerid());
				wmsg.setMarriageid(id);
				MessageUtil.send_to_world(wmsg);
				log.error("新插入单个结婚数据="+ tmpString);
			}
		} catch (Exception e) {
			log.error("储存单个结婚数据出错"+e,e);
		}
	}
	
	
	//-----------------------------婚宴信息------------------------------------------
	/**
	 * 服务器启动 ，从数据库 读取所有婚宴信息
	 * 
	 */
	public void loadAllWedding() {
		try {
			List<WeddingBean> list = getWeddingDao().select();
			Iterator<WeddingBean> iterator = list.iterator();
			while (iterator.hasNext()) {
				WeddingBean weddingBean = (WeddingBean) iterator.next();
				if (weddingBean != null) {
					Wedding wedding = (Wedding) JSONserializable.toObject(VersionUpdateUtil.dateLoad(weddingBean.getData()), Wedding.class);
					Weddingmap.put(weddingBean.getId(), wedding);
				}
			}
		}catch (Exception e) {
			log.error("从数据库 读取所有婚宴信息"+e,e);
		}
	}
	/**更新单个婚宴数据,false更新，true插入
	 * 
	 * @param pid
	 * @param marriage
	 */
	public synchronized void saveWeddinginfo(Wedding wedding, boolean insert){
		if (insert) {
			Weddingmap.put(wedding.getMarriageid(), wedding);
		}
		saveWedding( wedding, insert);
	}
	
	
	/**
	 * 删除单个婚宴数据  到数据库 异步
	 * @param pid
	 * @param 
	 */
	public synchronized void deleteWedding(Wedding wedding) {
		try {
			Weddingmap.remove(wedding.getMarriageid());
			WeddingBean weddingBean  = new WeddingBean();
			weddingBean.setId(wedding.getMarriageid());
			weddingBean.setDeleted(1);
			weddingBean.setData(VersionUpdateUtil.dateSave(JSONserializable.toString(wedding)));
			WServer.getInstance().getwSaveWeddingThread().dealWedding(weddingBean, 0);
			
		} catch (Exception e) {
			log.error("删除单个婚宴数据"+e,e);
		}
	}
	
	
	/**
	 * 储存单个婚宴数据  到数据库 异步
	 * @param pid
	 * @param 
	 */
	public void saveWedding(Wedding wedding, boolean insert) {
		try {
			WeddingBean weddingBean  = new WeddingBean();
			weddingBean.setId(wedding.getMarriageid());
			weddingBean.setData(VersionUpdateUtil.dateSave(JSONserializable.toString(wedding)));
			int type=0;	//更新
			if (insert) {
				type = 1;//插入
			}
			WServer.getInstance().getwSaveWeddingThread().dealWedding(weddingBean, type);
		} catch (Exception e) {
			log.error(" 储存单个婚宴数据"+e,e);
		}
	}


	/**玩家发起求婚，验证在线
	 * 
	 * @param player
	 * @param suitorobjid
	 */
	public void stReqLaunchProposeToGameMessage(Player player,long suitorobjid) {
		if (player.getSex() != 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有男方能发起求婚"));
			return;
		}
		Player suitplayer = ManagerPool.playerManager.getOnLinePlayer(suitorobjid);
		if (suitplayer != null) {
			if (checkmarriage(player,suitplayer)) {
				ResLaunchProposeToClientMessage cmsg= new ResLaunchProposeToClientMessage();
				GuildInfo guildInfo = suitplayer.getGuildInfo();
				if (guildInfo != null) {
					cmsg.setGuildname(guildInfo.getGuildName());
				}
				cmsg.setSuitorobjname(suitplayer.getName());
				cmsg.setSuitorobjlv((short) suitplayer.getLevel());
				cmsg.setAvatarid(suitplayer.getAvatarid());
				cmsg.setSuitorobjid(suitorobjid);
				MessageUtil.tell_player_message(player, cmsg);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对方不在线"));
		}
	}


	/**求婚对象在线，发起求婚
	 * 
	 * @param parameter
	 * @param suitorobjid
	 */
	public void stReqLaunchProposeStartToGameMessage(Player player,ReqLaunchProposeStartToGameMessage msg) {
		if (player.getSex() != 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有男方能发起求婚"));
			return;
		}
		Player suitplayer = ManagerPool.playerManager.getOnLinePlayer(msg.getSuitorobjid());
		if (suitplayer != null) {
			Integer[] ringprice = getRingPrice(msg.getRingmodelid());
			if (ringprice == null) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("婚戒选择错误"));
				return;
			}
			
			if (player.getMoney() < ringprice[1]) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需铜币不足，需要{1}铜币"),ringprice[1]+"");
				return;
			}
			
			if(ringprice[2] > 0 && !ManagerPool.backpackManager.checkGold(player, ringprice[2])){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需元宝不足，需要{1}元宝"),ringprice[2]+"");
				return;
			}
			
			if (checkmarriage(player,suitplayer)) {
				if (ckPropose(player,suitplayer.getId())) {
					return;
				}
				
				addPropose(player.getId(),msg.getSuitorobjid(),msg.getRingmodelid());
				ResLaunchProposeStartToClientMessage cmsg = new ResLaunchProposeStartToClientMessage();
				cmsg.setAvatarid(player.getAvatarid());
				GuildInfo guildInfo = player.getGuildInfo();
				if (guildInfo != null) {
					cmsg.setGuildname(guildInfo.getGuildName());
				}
				cmsg.setPlayerid(player.getId());
				cmsg.setPlayerlv((short) player.getLevel());
				cmsg.setPlayername(player.getName());
				cmsg.setRingmodelid(msg.getRingmodelid());
				MessageUtil.tell_player_message(suitplayer, cmsg);
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您向{1}发起了求婚，请等待对方回应。"),suitplayer.getName());
			}
		}
	}
	
	
	/**婚检
	 * 检查结婚基本条件
	 * @param player
	 * @param suitplayer
	 * @return
	 */
	public boolean checkmarriage(Player player ,Player suitplayer){
		Map amap = ManagerPool.mapManager.getMap(player);
		Map bmap = ManagerPool.mapManager.getMap(suitplayer);
//		player.setMarriageid(0);
//		suitplayer.setMarriageid(0);
		int day = TimeUtil.getOpenAreaDay();
		if (TimeUtil.getOpenAreaDay() < 3) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("开服3天后开放结婚系统,当前是第{1}天"),day+"");
			return false;
		}
		
		if ((amap == null ||  bmap == null ) || (amap.getId() != bmap.getId() || amap.getLineId() != bmap.getLineId())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("必须在同个地图内才可以结婚"));
			return false;
		}
		
		if (player.getLevel() < 30 ) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，等级达到30级后才能结婚"));
			return false;
		}
		
		if (suitplayer.getLevel() < 30 ) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，对方等级没有达到30级，不能结婚"));
			return false;
		}
		
		
		if (player.getMarriageid() > 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您已经结婚了"));
			return false;
		}
		
		if (suitplayer.getSex() == player.getSex()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，暂时不支持同性间的求婚"));
			return false;
		}
		
		if (suitplayer.getMarriageid() > 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，对方已经结婚了"));
			return false;
		}
		
		return true;
	}
	
	
	
	/**检查求婚 , true = 已在求婚列表里
	 * @return 
	 * 
	 */
	public synchronized boolean ckPropose(Player player,long roleid){
		if (Proposemap.containsKey(player.getId())) {
			List<Propose> list = Proposemap.get(player.getId());
			int ms = (int) (System.currentTimeMillis()/1000);
			Iterator<Propose> it = list.iterator();
			while (it.hasNext()) {
				if (ms - it.next().getTime() > 30) {
					it.remove();
				}
			}
			
			if(list.size() == 0 ){
				Proposemap.remove(player.getId());
				return false;
			}
			for (Propose propose : list) {
				if (roleid == propose.getRoleid()) {
					int s = 30 - (ms - propose.getTime());
					if (s > 0) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的求婚信息已发送，请等待对方回应，{1}秒后可重新发送"),s+"");
						return true;	
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	
	/**增加求婚
	 * 
	 * @param playerid
	 * @param suitorobjid
	 * @param ringmodelid
	 */
	public synchronized void addPropose(long playerid ,long suitorobjid,int ringmodelid){
		int ms = (int) (System.currentTimeMillis()/1000);
		if (Proposemap.containsKey(playerid)) {
			boolean is = false;
			List<Propose> list = Proposemap.get(playerid);
			for (Propose propose : list) {
				if(propose.getRoleid() == suitorobjid){
					propose.setTime(ms);
					propose.setRingmodelid(ringmodelid);
					is = true;
					break;
				}
			}
			if (is == false) {
				Propose propose = new  Propose();
				propose.setRingmodelid(ringmodelid);
				propose.setRoleid(suitorobjid);
				propose.setTime(ms);
				list.add(propose);
			}
		}else {
			Propose propose = new  Propose();
			propose.setRingmodelid(ringmodelid);
			propose.setRoleid(suitorobjid);
			propose.setTime(ms);
			List<Propose> list=new ArrayList<Propose>();
			list.add(propose);
			Proposemap.put(playerid,list );
		}
		
	}



	/**被求婚者选择 
	 * 
	 * @param suitplayer
	 * @param msg
	 */
	public synchronized void stReqProposeSelectToGameMessage(Player suitplayer,ReqProposeSelectToGameMessage msg) {
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		if (player == null) {
			MessageUtil.notify_player(suitplayer, Notifys.ERROR, ResManager.getInstance().getString("对方不在线，暂时不能结婚，请稍后再试"));
			return;
		}
		if (msg.getSelect() == 1) { //同意
			if (!checkmarriage(suitplayer,player) ) {
				return;
			}
			
			int ringid= 0;
			boolean is = false;
			if(Proposemap.containsKey(player.getId())){
				List<Propose> list = Proposemap.get(player.getId());
				for (Propose propose : list) {
					if(propose.getRoleid() == suitplayer.getId()){
						ringid = propose.getRingmodelid();
						is = true;
						break;
					}
				}
				
				if (is) {
					Integer[] ringprice = getRingPrice(ringid);
					if(ringprice[2] > 0 && !ManagerPool.backpackManager.checkGold(player, ringprice[2])){
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需元宝不足，需要{1}元宝"),ringprice[2]+"");
						return;
					}
					
					if (player.getMoney() < ringprice[1]) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需铜币不足，需要{1}铜币"),ringprice[1]+"");
						return;
					}
					
					if (ringprice[1] == 0 && ringprice[2] == 0) {
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("婚戒价格设定错误，请通知管理员"));
						return;
					}
					
					long aid= Config.getId();
					if (ringprice[1] > 0) {
						if (!ManagerPool.backpackManager.changeMoney(player, -ringprice[1], Reasons.MARRIED_RING_MONEY, aid)) {
							MessageUtil.notify_player(suitplayer, Notifys.ERROR, ResManager.getInstance().getString("对方购买婚戒所需铜币不足"));
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需铜币不足，需要{1}铜币"),ringprice[1]+"");
							return;
						}
					}
					
					if (ringprice[2] > 0) {
						if (!ManagerPool.backpackManager.changeGold(player,  -ringprice[2],Reasons.MARRIED_RING_GOLD,aid)) {
							MessageUtil.notify_player(suitplayer, Notifys.ERROR, ResManager.getInstance().getString("对方购买婚戒所需元宝不足"));
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需元宝不足，需要{1}元宝"),ringprice[2]+"");
							return;
						}
					}

					//生成结婚数据
					Marriage marriage = new Marriage();
					if (player.getSex() == 1) {//新郎写到Playerid_a
						marriage.getSpouseslist().add(new Spouse(player));
						marriage.getSpouseslist().add(new Spouse(suitplayer));
					}else {
						marriage.getSpouseslist().add(new Spouse(suitplayer));
						marriage.getSpouseslist().add(new Spouse(player));
					}
					
					marriage.setCurrringid(ringid);
					marriage.setTime(System.currentTimeMillis());
					saveMarriageinfo(marriage.getId(),marriage,true);//插入一条结婚数据
					
					player.setMarriageid(marriage.getId());
					suitplayer.setMarriageid(marriage.getId());
					MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("恭喜您，您与『{1}』成功结为夫妻，愿两位生生世世永不分离"),suitplayer.getName());
					MessageUtil.notify_player(suitplayer, Notifys.CHAT_ROLE, ResManager.getInstance().getString("恭喜您，您与『{1}』成功结为夫妻，愿两位生生世世永不分离"),player.getName());
					MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("恭喜『{1}』与『{2}』喜结连理，如此金童玉女真乃天作之合，羡煞旁人"),player.getName(),suitplayer.getName());
					MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("恭喜『{1}』与『{2}』喜结连理，如此金童玉女真乃天作之合，羡煞旁人"),player.getName(),suitplayer.getName());
					ResMarriedSuccessToClientMessage cmsg = new ResMarriedSuccessToClientMessage();
					cmsg.setPlayername(player.getName());
					cmsg.setAvatarid(player.getAvatarid());
					MessageUtil.tell_player_message(suitplayer, cmsg);
					
					ResMarriedSuccessToClientMessage cmsg2 = new ResMarriedSuccessToClientMessage();
					cmsg2.setPlayername(suitplayer.getName());
					cmsg2.setAvatarid(suitplayer.getAvatarid());
					MessageUtil.tell_player_message(player, cmsg2);
					
					Proposemap.remove(player.getId());
					//刷新结婚数据到前端（因为限制了同地图，所以这里是同个线程）
					refreshMarriageinfo( player, marriage );
					refreshMarriageinfo( suitplayer, marriage );
					addMarriageLog(marriage,0);
				}
			}
		}else if(msg.getSelect() == 0){ //拒绝
			MessageUtil.notify_player(suitplayer, Notifys.ERROR, ResManager.getInstance().getString("您拒绝了『{1}』的求婚"),player.getName());
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，『{1}』拒绝了您的的求婚请求，愿君他日逆袭时，再与女神共缠绵"),suitplayer.getName());
		}else if (msg.getSelect() == 2) { // 倒计时结束，前端自动关闭窗口
			MessageUtil.notify_player(suitplayer, Notifys.ERROR, ResManager.getInstance().getString("您没有响应『{1}』的求婚"),player.getName());
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，『{1}』没有响应您的求婚请求"),suitplayer.getName());
		}
	}


	
	/**离婚
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqDivorceToGameMessage(Player player,ReqDivorceToGameMessage msg) {
		long mid = player.getMarriageid();
		Marriage marriage = getMarriage(mid);
		if (marriage == null) {
			return;
		}
		long otherid = marriage.getSpouseid(player);
		Wedding wedding = getWedding(mid);
		if (wedding!= null && wedding.getStatus() == 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，请等待婚宴结束后，才能离婚"));
			return;
		}
		
		if (msg.getType() == 1) {	//1协议离婚，2强行离婚
			Player otherplayer = ManagerPool.playerManager.getOnLinePlayer(otherid);
			if (otherplayer == null) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对方不在线，请稍后再试"));
				return;
			}
			
			ResDivorceToClientMessage cmsg = new ResDivorceToClientMessage();
			MessageUtil.tell_player_message(otherplayer, cmsg);
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("已通知对方，请等待对方回应"));
		}else if (msg.getType() == 2) {//强行离婚
			if (ManagerPool.backpackManager.changeMoney(player, -2000000, Reasons.DIVORCE_GOLD, Config.getId())) {
				Player otherplayer = ManagerPool.playerManager.getOnLinePlayer(otherid);
				if (otherplayer != null) {	//对方在线

					MessageUtil.notify_player(otherplayer, Notifys.ERROR, ResManager.getInstance().getString("您与TA的婚姻关系解除了，您恢复了自由身，您可以取回戒指留作纪念"));
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您与TA的婚姻关系解除了，您恢复了自由身，您可以取回戒指留作纪念"));
					
					ResForceDivorceToClientMessage csmg = new ResForceDivorceToClientMessage();
					csmg.setType(msg.getType());
					MessageUtil.tell_player_message(otherplayer, csmg);
					MessageUtil.tell_player_message(player, csmg);
					player.setDivorceid(mid);
					otherplayer.setDivorceid(mid);
					divorceClearData(mid);
					
				}else {	//对方不在线(只清理自己的)
					player.setDivorceid(mid);
					marriage.setStatus(1);//设置强行离婚标记
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您与TA的婚姻关系解除了，您恢复了自由身，您可以取回戒指留作纪念"));
					ResForceDivorceToClientMessage csmg = new ResForceDivorceToClientMessage();
					csmg.setType(msg.getType());
					MessageUtil.tell_player_message(player, csmg);
					saveMarriageinfo(marriage.getId(), marriage, false);
					if (wedding != null) {
						deleteWedding(wedding);
					}
				}
				addMarriageLog(marriage,1);
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的铜币不足200万。"));
			}
		}
		
	}

	
	/**离婚清理数据库
	 * 
	 * @param id
	 */
	public synchronized void divorceClearData(long id){
		Marriage marriage = ManagerPool.marriageManager.getMarriage(id);
		Wedding wedding = ManagerPool.marriageManager.getWedding(id);
		if (marriage != null) {
			marriage.setStatus(2);	//只设置成功离婚标记
			saveMarriageinfo(id, marriage, false);
			MARKMAP.put(id, System.currentTimeMillis());
		}
		
		if (wedding != null) {
			deleteWedding(wedding);
		}
		
		ReqDeleteMarriageToWorldMessage wmsg = new ReqDeleteMarriageToWorldMessage();
		wmsg.setMarriageid(id);
		MessageUtil.send_to_world(wmsg);
	}

	
	
	/**离婚清理个人数据
	 * 
	 */
	public void divorceClearPlayerData(Player player  ){
		try {
			long mid= player.getMarriageid();
			player.setMarriageid(0);
			player.setDivorceid(0);
			player.setWeddingringid(0);
			ManagerPool.buffManager.removeByBuffId(player, 9166);
			ManagerPool.buffManager.removeByBuffId(player, 9167);
			ManagerPool.buffManager.removeByBuffId(player, 9168);
			ManagerPool.buffManager.removeByBuffId(player, 9172);
			removeMarriageSkill(player);
			Marriage marriage = ManagerPool.marriageManager.getMarriage(mid);
			if (marriage!= null) {
				giveoldRing(player.getId(),mid,marriage.getCurrringid());
			}
			//根据戒指ID计算属性值
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.MARRIAGE);
			
		} catch (Exception e) {
			log.error("离婚个人操作出错divorceClearPlayerData"+e,e);
		}
	}
	
	
	
	/**玩家选择是否同意离婚
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqDivorceSelectToGameMessage(Player player,ReqDivorceSelectToGameMessage msg) {
		long mid = player.getMarriageid();
		Marriage marriage = getMarriage(mid);
		if (marriage == null) {
			return;
		}
		long otherid = marriage.getSpouseid(player);
		Player otherplayer = ManagerPool.playerManager.getOnLinePlayer(otherid);
		if (otherplayer == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("对方不在线，请稍后再试"));
			return;
		}
		
		if (msg.getSelect() == 1) {//同意
			player.setDivorceid(mid);
			otherplayer.setDivorceid(mid);
			divorceClearData(mid);	//清理数据
			MessageUtil.notify_player(otherplayer, Notifys.CHAT_ROLE, ResManager.getInstance().getString("您和『{1}』离婚了"),player.getName());
			MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("您和『{1}』离婚了"),otherplayer.getName());
			ResForceDivorceToClientMessage csmg = new ResForceDivorceToClientMessage();
			csmg.setType((byte) 1);
			MessageUtil.tell_player_message(player, csmg);
			MessageUtil.tell_player_message(otherplayer, csmg);
			addMarriageLog(marriage,1);
		}else {
			MessageUtil.notify_player(otherplayer, Notifys.ERROR, ResManager.getInstance().getString("『{1}』拒绝了您的协议离婚请求，真情不易，您不妨再考虑考虑"),player.getName());
		}
	}

	
	
	/**上线触发
	 * 
	 * @param player
	 */
	public void logintrigger(Player player){
		if (player.getMarriageid() > 0) {
			long min=player.getMarriageid();
			try {
				Marriage marriage = getMarriage(min);
				if (marriage != null) {
					if (marriage.getStatus() == 1) {//如果A方强制离婚的时候，B方不在线，则在B方登录的时候处理掉
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您与TA的婚姻关系解除了，您恢复了自由身，您可以取回戒指留作纪念"));
						ResForceDivorceToClientMessage csmg = new ResForceDivorceToClientMessage();
						MessageUtil.tell_player_message(player, csmg);
						divorceClearPlayerData(player);
						divorceClearData(marriage.getId());	//清理数据
						return;
					}
					boolean issave = false;
					//如果有未读消息，在这里发送
					if (!marriage.ckLeaveMsg(player)) {
						ResNoticeNewLeaveMsgToClientMessage cMsgToClientMessage =new  ResNoticeNewLeaveMsgToClientMessage();
						cMsgToClientMessage.setLeavemsglist(marriage.makeLeaveMsgInfo(player));
						cMsgToClientMessage.setPlayerid(player.getId());
						MessageUtil.tell_player_message(player, cMsgToClientMessage);
					}
					//上线提示配偶
					Player other = getSpousePlayer(player);
					if (other != null) {
						ResOnlineStatusToClientMessage smsg = new ResOnlineStatusToClientMessage();
						smsg.setType((byte) 1);
						MessageUtil.tell_player_message(other, smsg);
						MessageUtil.notify_player(other, Notifys.CHAT_ROLE,  ResManager.getInstance().getString("您的爱人{1}上线了"), player.getName());
					}
					refreshMarriageinfo( player, marriage );
					
					//-------------婚宴未领取的红包----------------
					Wedding wedding =getWedding(min);
					if (wedding != null && wedding.getStatus() == 2 && wedding.getRedsum() > 0) {//婚宴已经结束,而且有钱
						if(!wedding.getRedreceives().contains(player.getId())){//玩家未领取红包
							ResNoticeReceiveRedToClientMessage receiveRedmsg = new ResNoticeReceiveRedToClientMessage();
							MessageUtil.tell_player_message(player, receiveRedmsg);
							MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("您有未领取的红包"));
						}
					}
					
					//------------上线给婚宴经验和真气------------
					Spouse spouseinfo = marriage.getSelfSpouse(player.getId());
					int sexp = spouseinfo.getTotalexp();
					if(sexp > 0){
						spouseinfo.setTotalexp(0);
						ManagerPool.playerManager.addExp(player,sexp , AttributeChangeReason.WEDDING);
						MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("婚宴宾客吃菜给您带来{1}经验"), sexp+"");
					}
					int szhenqi = spouseinfo.getTotalzhenqi();
					if(szhenqi > 0){
						spouseinfo.setTotalzhenqi(0);
						ManagerPool.playerManager.addZhenqi(player, szhenqi, AttributeChangeReason.WEDDING);
						MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("婚宴宾客吃菜给您带来{1}真气"), szhenqi+"");
					}
					if (szhenqi > 0 || sexp > 0 ) {
						issave = true;
					}
					
					//通知婚宴开始时间
					if (wedding != null ){
						if(spouseinfo.getNoticewedding() > 1) {
							
							int day = TimeUtil.getDayOfMonth(wedding.getTime());
							int currday = TimeUtil.getDayOfMonth(System.currentTimeMillis());
							if ((currday <  day)  || (currday == day && getWeddingstatus() < 2)) {//举办日期大于当前天，或者举办日期是当前天，但是宴会还没结束，则给提示
								ResWeddingTimeToClientMessage wmsg = new ResWeddingTimeToClientMessage();
								wmsg.setDay((byte) TimeUtil.getDayOfMonth(wedding.getTime()));
								wmsg.setMonth((byte) (TimeUtil.getMonth(wedding.getTime())+1));
								wmsg.setType(wedding.getType());	
								MessageUtil.tell_player_message(player, wmsg);
								issave = true;
								spouseinfo.setNoticewedding(0);
							}
						} 
					}
//					//更换戒指通知
//					if (spouseinfo.getNoticering() > 0) {
//						spouseinfo.setNoticering(0);
//						ResRingUPToClientMessage upmsg=new ResRingUPToClientMessage();
//						MessageUtil.tell_player_message(player, upmsg);
//						issave = true;
//					}
					
					if (issave) {
						saveMarriageinfo(marriage.getId(), marriage, false);
					}
					
				}else {
					log.error("身上有结婚ID="+min + "，但是没有结婚数据");
				}
			} catch (Exception e) {
				log.error(e,e);
			}
		}
		
		//上线发送改变婚宴主角外观
		if (Weddingidxlist.size() > 0) {
			ResWeddingExteriorToClientMessage extmsg = new ResWeddingExteriorToClientMessage();
			for (Long idx : Weddingidxlist) {
				Marriage marriage = getMarriage(idx);
				if (marriage!= null) {
					extmsg.getRoleslist().add(marriage.getSpouseslist().get(0).getPlayerid());
					extmsg.getRoleslist().add(marriage.getSpouseslist().get(1).getPlayerid());
				}
			}
			MessageUtil.tell_player_message(player, extmsg);
		}
	}

	
	/**刷新结婚消息
	 * 
	 */
	public void refreshMarriageinfo(Player player , Marriage marriage){
		try {
			//加载结婚信息
			long otherid = marriage.getSpouseid(player);
			ResMarriageinfoToClientMessage marriagemsg = new ResMarriageinfoToClientMessage();
			marriagemsg.setMarriageid(player.getMarriageid());
			marriagemsg.setSpouseid(otherid);
			marriagemsg.setSpousename(marriage.getSpouse(player).getName());
			marriagemsg.setTime((int) (marriage.getTime()/1000));
			marriagemsg.setRingmodelid(marriage.getCurrringid());
			MessageUtil.tell_player_message(player, marriagemsg);	
			
			//---------------加婚戒BUFF----------------------
			int itemid = marriage.getCurrringid();
			player.setWeddingringid(itemid);
//			for (Integer buffid : ringbuffmap.values()) {
//				ManagerPool.buffManager.removeByBuffId(player, buffid);	//先全部删除戒指BUFF
//			}
//			Q_itemBean data = ManagerPool.dataManager.q_itemContainer.getMap().get(itemid);
//			if (data!=null) {
//				String[] itemstr = data.getQ_buff().split(Symbol.FENHAO);
//				if (itemstr.length > 0) {//重新加戒指
//					ManagerPool.buffManager.addBuff(player, player, Integer.valueOf(itemstr[0]),0,0,0);
//				}
//			}
			//并蒂莲连枝
			//ManagerPool.buffManager.addBuff(player, player, 9172,0,0,0);
			
			addMarriageSkill(player);
			//根据戒指ID计算属性值
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.MARRIAGE);
		} catch (Exception e) {
			log.error(e,e);
		}

	}
	
	
	
	
	
	
	
	
	/**发送配偶面板信息
	 * 
	 * @param player
	 */
	public void stReqSpouseInfoToGameMessage(Player player) {
		Marriage marriage = getMarriage(player.getMarriageid());
		if (marriage != null) {
			long otherid = marriage.getSpouseid(player);
			Player otherplayer = ManagerPool.playerManager.getOnLinePlayer(otherid);
			ResSpouseInfoToClientMessage cmsg = new ResSpouseInfoToClientMessage();
			if (otherplayer != null) {
				OtherPlayerInfo otherplayerinfo = ManagerPool.playerManager.getOtherPlayerInfo(otherplayer);
				cmsg.setOtherPlayerInfo(otherplayerinfo);
			}else {
				//对方不在线，用自己的重填数据，无作用
				OtherPlayerInfo playerInfo = ManagerPool.playerManager.getOtherPlayerInfo(player);
				playerInfo.setPersonId(0);	//表示不在线
				cmsg.setOtherPlayerInfo(playerInfo);
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的爱人当前不在线"));
			}
			cmsg.setRingmodelid(marriage.getCurrringid());
			cmsg.setLeavemsglist(marriage.makeLeaveMsgInfo(player));
			MessageUtil.tell_player_message(player, cmsg);
		}
	}

	
	
	/**新增留言
	 * 
	 * @param player
	 * @param msg
	 */
	public synchronized void stReqNewLeaveMsgToGameMessage(Player player,ReqNewLeaveMsgToGameMessage msg) {
		try {
			if (msg.getContent()==null || msg.getContent().length() < 2 || msg.getContent().length() > 30) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您输入的轻语字数过少，或者超过30个字符"));
				return;
			}
//			if(WordFilter.getInstance().hashNoLimitedWords(msg.getContent())){
//				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您输入的轻语中包含敏感或非法字符"));
//				return;
//			}
			if ( WordFilter.getInstance().hashBadWords(msg.getContent())){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您输入的轻语中包含敏感或非法字符"));
				return;
			}
			
			Marriage marriage = getMarriage(player.getMarriageid());
			if (marriage != null) {
				LeaveMsg leaveMsg = new LeaveMsg();
				leaveMsg.setContent(msg.getContent());
				leaveMsg.setPlayerid(player.getId());
				List<LeaveMsg> list = marriage.getLeavemsgs();
				if (list.size() >= 30) {//最多30条
					list.remove(0);
				}
				list.add(leaveMsg);
				saveMarriageinfo(marriage.getId(), marriage, false);
				
				ResNoticeNewLeaveMsgToClientMessage cmsg = new ResNoticeNewLeaveMsgToClientMessage();
				cmsg.setLeavemsglist(marriage.makeLeaveMsgInfo(player));//更新自己的留言面板
				MessageUtil.tell_player_message(player, cmsg);
				
				long otherid = marriage.getSpouseid(player);
				Player otherplayer = ManagerPool.playerManager.getOnLinePlayer(otherid);
				if (otherplayer != null) {//对方在线，直接通知对方
					ResNoticeNewLeaveMsgToClientMessage cMsgToClientMessage =new  ResNoticeNewLeaveMsgToClientMessage();
					cMsgToClientMessage.setLeavemsglist(marriage.makeLeaveMsgInfo(otherplayer));
					cMsgToClientMessage.setPlayerid(otherid);//被通知玩家
					MessageUtil.tell_player_message(otherplayer, cMsgToClientMessage);
				}
			}
		
		} catch (Exception e) {
			log.error(e,e);
		}
	}
	
	
	
	/**申请婚宴
	 * 
	 * @param parameter
	 */
	public synchronized void stReqApplyWeddingToGameMessage(Player player,ReqApplyWeddingToGameMessage msg ) {
		try {
			Marriage marriage = getMarriage(player.getMarriageid());
			if (marriage != null) {
				boolean is = false;
		
				if ( getWeddingstatus() == 1) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("今天的婚宴正在举办中，等21点后再来申请吧。"));
					return;
				}
				
				Wedding wedding =getWedding(marriage.getId());
				List<Wedding> wlist = selectWedding(false);
				if ((msg.getType() ==3 && wlist.size() >= 6)  ) {	//最多可预定的婚宴限制
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("每天最多可预定5场婚宴(可额外加一场豪华婚宴)，请明天再来预定吧"));
					return ;
				}
				
				if (msg.getType() != 3 && wlist.size() >= 5 ) {	//最多可预定的婚宴限制
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("每天最多可预定5场婚宴(可额外加一场豪华婚宴)，请明天再来预定吧"));
					return ;
				}
				if (marriage.getCdtiem() > System.currentTimeMillis()) {
					String str = TimeUtil.millisecondToStr(marriage.getCdtiem() - System.currentTimeMillis());
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，婚宴结束后24小时，才能再次申请(剩余{1})"),str);
					return;
				}
				Calendar instance = Calendar.getInstance();
				if(getWeddingstatus() == 2 ){
					instance.setTimeInMillis(System.currentTimeMillis()+ 24*60*60*1000);
				}else {
					instance.setTimeInMillis(System.currentTimeMillis());
				}
				instance.set(Calendar.HOUR_OF_DAY, 20);
				instance.set(Calendar.MINUTE, 0);
				instance.set(Calendar.SECOND, wlist.size());	//数量
				long cd = instance.getTimeInMillis() + 24*60*60*1000 + 60*60*1000;	//CD时间 从晚上20点开始+24小时+30分钟（最后30分钟是宴会进行中） （不让申请）
				
				if (wedding != null) {
					if (wedding.getStatus() == 1) {//举办中
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的婚宴正在举办中，请在婚宴结束后，再申请下一轮"));
						return;
					}else if (wedding.getStatus() == 0) {//准备中
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的婚宴正在筹办中，请在婚宴结束后，再申请下一轮"));
						return;
					}else if (wedding.getStatus() == 2) {
						if (wedding.getRedsum() > 0 && wedding.getRedreceives().size() < 2) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有婚礼双方都领取红包后才可举办新的婚宴"));
							return;
						}
						
						if (ckWeddingmoney(player,msg.getType())) {
							wedding.setStatus((byte) 0);
							wedding.setType(msg.getType());
							wedding.getRedenvelopes().clear();
							wedding.getRedreceives().clear();
							wedding.setRedsum(0);
							wedding.setDay(0);
							wedding.setTime(instance.getTimeInMillis());
							saveWeddinginfo(wedding,false);
							is=true;
						}
					}
				}else {//新的
					if (ckWeddingmoney(player,msg.getType())) {
						wedding=new Wedding();
						wedding.setType(msg.getType());
						wedding.setMarriageid(marriage.getId());
						wedding.setTime(instance.getTimeInMillis());
						saveWeddinginfo(wedding,true);
						is=true;
					}
				}
				if (is) {
					marriage.setCdtiem(cd);	//设置CD时间
					List<Wedding> list = selectWedding(true);
					long time = System.currentTimeMillis();
					if (wedding.getDay() > 0) {
						 time = time + (24*60*60 * 1000 * wedding.getDay());//只是用来获取天数
					}
					long mday = TimeUtil.getDayOfMonth(time);
					long month = TimeUtil.getMonth(time)+1;
					MessageUtil.notify_player(player, Notifys.CHAT_ROLE,  ResManager.getInstance().getString("恭喜您申请婚宴成功"));
					Player otherplayer = ManagerPool.playerManager.getOnLinePlayer(marriage.getSpouseid(player));
					if (otherplayer != null) {
						MessageUtil.notify_player(otherplayer, Notifys.CHAT_ROLE,  ResManager.getInstance().getString("TA申请了一场婚宴，作为主人的您可千万不要缺席哦"));
						ResWeddingTimeToClientMessage wmsg = new ResWeddingTimeToClientMessage();
						wmsg.setDay((byte) TimeUtil.getDayOfMonth(wedding.getTime()));
						wmsg.setMonth((byte) (TimeUtil.getMonth(wedding.getTime())+1));
						wmsg.setType(msg.getType());	
						MessageUtil.tell_player_message(otherplayer, wmsg);
					}else {	//不在线就保存通知
						marriage.getSpouse(player).setNoticewedding((int) (wedding.getTime()/1000));
					}
					saveMarriageinfo(marriage.getId(), marriage, false);
					MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("{1}与{2}的{3}婚宴将在{4}月{5}日晚20点在咸阳王城举行，欢迎大家届时参加，可获经验与真气奖励"), player.getName(),marriage.getSpouse(player).getName(),getWeddingname(msg.getType()),month+"",mday+"");
					addWeddingLog(marriage.getId(),0);
				}
				
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您还没有结婚，无法举办婚宴"));
			}
		} catch (Exception e) { 
			log.error(e,e);
		}
	}
	
	
	/**得到婚宴名字
	 * 
	 * @return
	 */
	public String getWeddingname(int id){
		if (id == 1) {
			return ResManager.getInstance().getString("普通");
		}else if (id == 2) {
			return ResManager.getInstance().getString("富贵");
		}else if (id == 3) {
			return ResManager.getInstance().getString("豪门");
		}
		return "";
	}
	
	
	
	
	
	
	/**检查身上元宝和铜币
	 * 
	 * @param player
	 * @param id
	 * @return
	 */
	public boolean ckWeddingmoney(Player player, int id){
		Integer[] tab = getWeddingPrice(id);
		if (tab != null && tab.length == 2) {
			long aid = Config.getId();
			if(player.getMoney() < tab[0]){//检测金币
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的铜币不足{1}，无法举办该级别婚宴"),tab[0]+"");
				return false;
			}
			
			if (!ManagerPool.backpackManager.checkGold(player,tab[1])) {//检测元宝
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的元宝不足{1}，无法举办该级别婚宴"),tab[1]+"");
				return false;
			}
			
			if (tab[0] > 0 ) {
				if (!ManagerPool.backpackManager.changeMoney(player, -tab[0], Reasons.WEDDING_MONEY, aid)) {
					return false;
				}
			}
			
			if (tab[1] > 0 ) {
				if (!ManagerPool.backpackManager.changeGold(player, -tab[1], Reasons.WEDDING_GOLD, aid)) {
					return false;
				}
			}
			return true;
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("婚宴价格设置不正确，请通知管理员"));
		}
		return false;
	}
	
	
	
	/**
	 * 筛选可举办的婚礼，按照每天5个排序
	 *  false = 不写天数，true=写
	 * @return
	 */
	public synchronized List<Wedding> selectWedding(boolean iswriteday){
		List<Wedding> list = new ArrayList<Wedding>();
		try {
			Set<Entry<Long, Wedding>> entry = Weddingmap.entrySet();
			Iterator<Entry<Long, Wedding>> it = entry.iterator();
			while (it.hasNext()) {
				Entry<Long, Wedding> weddingent = it.next();
				Wedding wedding =weddingent.getValue();
				if (getMarriage(wedding.getMarriageid()) != null && wedding.getStatus() <= 1) {	//得到未举办 和 举办中 的婚宴
					list.add(wedding);
				}
			}
			if (list.size() > 1) {
				Collections.sort(list, new Comparator<Wedding>() {	//按照申请时间排序
					@Override
					public int compare(Wedding o1, Wedding o2) {
						return (int) ( o1.getTime() - o2.getTime());
					}
				});
			}
	
			//进行中：要算入已经举办的
			//已结束：去掉已举办的
			if (iswriteday) {
				//boolean is =TimeUtil.isAfterHourOfCurrentDay(21,System.currentTimeMillis());
				int daynum= 0;
				if (getWeddingstatus() >= 1 || TimeUtil.isAfterHourOfCurrentDay(21,System.currentTimeMillis())) {
					daynum=6;	//宴会进行中，新申请的宴会都从第2天开始算
				}
				for (Wedding wedding: list) {
					if (wedding.getStatus() == 1) {//宴会进行中，不处理天数
						
					}else {
						int day = daynum/6;		//第一天默认是按照6个来算
						wedding.setDay(day);
						daynum = daynum + 1;		//婚宴个数+1
					}
				}
			}
		} catch (Exception e) {
			log.error(e,e);
		}
		return list;
	}

	
	
	
	
	public static List<Long> getWeddingidxlist() {
		return Weddingidxlist;
	}
	public static void setWeddingidxlist(List<Long> weddingidxlist) {
		Weddingidxlist = weddingidxlist;
	}
	
	
	/**请求婚宴列表
	 * 
	 * @param parameter
	 */
	public synchronized void stReqWeddingListToGameMessage(Player player) {
		List<Wedding> list = selectWedding(true);
		ResWeddingListToClientMessage cmsg = new ResWeddingListToClientMessage();
		for (Wedding wedding : list) {
			Marriage marriage= getMarriage(wedding.getMarriageid());
			if (marriage != null) {
				WeddingInfo weddingInfo = new WeddingInfo();
				weddingInfo.setBride(marriage.getSpouseslist().get(1).getName());
				weddingInfo.setBridegroom(marriage.getSpouseslist().get(0).getName());
				weddingInfo.setRednum(wedding.getRedsum());
				weddingInfo.setStatus(wedding.getStatus());
				weddingInfo.setType(wedding.getType());
				weddingInfo.setMarriageid(wedding.getMarriageid());
				int time = (int) (System.currentTimeMillis()/1000);
				if (wedding.getDay() > 0) {
					 time = time + (24*60*60 * wedding.getDay());//	只是用来获取天数
				}
				weddingInfo.setTime(time);
				cmsg.getWeddingInfolist().add(weddingInfo);
			}
		}
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	/**请求红包列表
	 * 
	 * @param parameter
	 */
	public synchronized void stReqRedEnvelopeToGameMessage(Player player) {
		long mid = player.getMarriageid();
		Marriage marriage = getMarriage(mid);
		if (marriage == null) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("很抱歉，您没有红包可领取"));
			return;
		}
		Wedding wedding=getWedding(mid);
		if (wedding != null) {
			ResRedEnvelopeToClientMessage cmsg=new ResRedEnvelopeToClientMessage();
			cmsg.setRedsum(wedding.getRedsum());
			if (wedding.getRedreceives().contains(player.getId())) {
				cmsg.setIsreceive((byte) 1);
			}
			List<RedEnvelope> list = wedding.getRedenvelopes();
			for (RedEnvelope redEnvelope : list) {
				cmsg.getRedenvelopelist().add(redEnvelope.makeRedEnvelopeinfo());
			}
			MessageUtil.tell_player_message(player, cmsg);
		}else {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("很抱歉，您没有红包可领取"));
		}
	}
	
	
	/**领取红包
	 * 
	 * @param parameter
	 */
	public synchronized void stReqReceiveredenvelopeToGameMessage(Player player) {
		long mid = player.getMarriageid();
		Marriage marriage = getMarriage(mid);
		if (marriage == null) {
			return;
		}
		
		Wedding wedding = getWedding(mid);
		if (wedding != null) {
			if(wedding.getStatus() != 2){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("婚宴结束后才能领取红包"));
				return;
			}

			int money=wedding.getRedsum() / 2;
			if (money + player.getMoney() > Global.BAG_MAX_COPPER) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您要领取的铜币和身上的铜币合计数量超过了上限，暂时不能领取"));
				return;
			}
			
			if(!wedding.getRedreceives().contains(player.getId())){
				wedding.getRedreceives().add(player.getId());
				ManagerPool.backpackManager.changeMoney(player, money, Reasons.WEDDING_RED_MONEY, Config.getId());
				MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("恭喜您领取了婚宴红包{1}铜币"),money+"");
				if (wedding.getRedreceives().size() >= 2) {
					deleteWedding(wedding);
				}else {
					saveWeddinginfo(wedding, false);
				}
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您已经领走了属于您的全部婚宴红包"));
			}
		}
	}
	
	
	/**送上红包，给钱钱
	 * 
	 * @param player
	 * @param msg
	 */
	public synchronized void stReqWeddingParticipateToGameMessage(Player player,ReqWeddingParticipateToGameMessage msg) {
		long mid = msg.getMarriageid();
		Marriage marriage = getMarriage(mid);
		if (marriage == null) {
			return;
		}
		
		if (msg.getNum() < 1 || msg.getNum() > Global.BAG_MAX_COPPER) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("请输入正确数量"));
			return;
		}
		
		Wedding wedding=getWedding(mid);
		if (wedding != null) {
			int rednum  = getLeastRedEnvelope(wedding.getType());
			if (rednum == -1) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("红包底限配置出错，请联系管理员"));
				return;
			}
			
			if(wedding.getStatus() != 1){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("婚宴还没开始呢，等开始了再给红包吧"));
				return;
			}
			
			if (wedding.getRedsum() + msg.getNum() < 0 || wedding.getRedsum() + msg.getNum() > Global.BAG_MAX_COPPER) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，婚礼的红包已经达到上限，不能再收了。"));
				return;
			}
			
			String name = getWeddingname(wedding.getType());
			if (rednum > msg.getNum()) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，参加{1}婚宴的红包底限需要{2}铜币"),name,rednum+"");
				return;
			}
			
			if(ManagerPool.backpackManager.changeMoney(player, -msg.getNum(), Reasons.WEDDING_RED_NUM, Config.getId())){
				wedding.setRedsum(wedding.getRedsum() + msg.getNum());
				RedEnvelope redEnvelope = new RedEnvelope();
				redEnvelope.setPlayerid(player.getId());
				redEnvelope.setPlayerlv((short) player.getLevel());
				redEnvelope.setPlayername(player.getName());
				redEnvelope.setRednum(msg.getNum());
				wedding.getRedenvelopes().add(redEnvelope);
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("恭喜您赠送红包成功，现在可以开始食用婚宴菜肴了"));
				saveWeddinginfo(wedding, false);
				long leiji = ManagerPool.countManager.getCount(player, CountTypes.RED_ENVELOPE, null);
				if (leiji == 0) {
					ManagerPool.countManager.addCount(player, CountTypes.RED_ENVELOPE, null,1, 0,0);
				}
				ManagerPool.countManager.addCount(player, CountTypes.RED_ENVELOPE, null,1);
				
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您没有{1}铜币，无法参加婚宴"),msg.getNum()+"");
			}
		}
	}
	
	
	
	/**婚宴开始吃菜，加经验和真气
	 * 
	 * @param player
	 * @param msg
	 */
	public synchronized void stReqWeddingEatingToGameMessage(Player player,ReqWeddingEatingToGameMessage msg) {
		NPC npc = ManagerPool.npcManager.findNpc(player, msg.getNpcid());
		if (npc != null) {
			long marriageid=(Long) npc.getParameters().get("marriageid");
			Marriage marriage = getMarriage(marriageid);
			if (marriage == null || marriage.getStatus() != 0) {
				return;
			}
			
			Wedding wedding = ManagerPool.marriageManager.getWedding(marriageid);
			boolean is = false;	//默认没有给红包
			if (wedding != null) {
				for (RedEnvelope redEnvelope : wedding.getRedenvelopes()) {
					if (redEnvelope.getPlayerid() == player.getId()) {
						is = true;//检测到已经给过红包
						break;
					}
				}
			}
			
			if (!is) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您没有给红包，不能参与此婚宴"));
				return;
			}
			
			if (npc.getParameters().containsKey("player_"+ player.getId())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("婚宴均为流水席，您已经食用过该餐桌上的食物，无法再次食用。请转到请到下一席"));
				return;
			}
			
			int num=(Integer) npc.getParameters().get("num");
			if (num <= 0) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的下手过慢了，该桌上的菜肴已经被吃光了"));
				return;
			}
			
			long exp = ManagerPool.countManager.getCount(player, CountTypes.WEDDING_EXP, null);
			if (exp == 0 ) {
				ManagerPool.countManager.addCount(player, CountTypes.WEDDING_EXP, null,1, 0,0);
			}
			
			long zhenqi = ManagerPool.countManager.getCount(player, CountTypes.WEDDING_ZHENQI, null);
			if (zhenqi == 0) {
				ManagerPool.countManager.addCount(player, CountTypes.WEDDING_ZHENQI, null,1, 0,0);
			}
			//婚宴不同，给的经验不同
			int addition = 1;
			if (wedding.getType() == 2) {
				addition = 2;
			}else if (wedding.getType() == 3) {
				addition = 3;
			}
			
			int level = player.getLevel();
			int expmax= level * level* level *150;//每天经验上限
			int zhenqimax = 30000;//每天真气上限
			
			if (exp >= expmax && zhenqi >= zhenqimax ) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已吃的过饱，今日婚宴所获经验和经验已达上限"));
				return;
			}
			num = num -1;
			npc.getParameters().put("num",num);	//食用次数减1
			npc.getParameters().put("player_"+ player.getId(),player.getId());//记录已使用这桌
			
			String content= ResManager.getInstance().getString("食用婚宴菜肴,");
			if (exp < expmax) {	//给玩家加经验
				int curexp= level * level* level * addition;
				ManagerPool.playerManager.addExp(player,curexp , AttributeChangeReason.WEDDING);
				ManagerPool.countManager.addCount(player, CountTypes.WEDDING_EXP, null,curexp);
				content = content + ResManager.getInstance().getString("经验：")+ curexp;
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已吃的过饱，今日婚宴所获经验已达上限"));
			}
			
			if (zhenqi < zhenqimax) {//给玩家加真气
				int curzhenqi = level *4 *addition;
				ManagerPool.playerManager.addZhenqi(player, curzhenqi, AttributeChangeReason.WEDDING);
				ManagerPool.countManager.addCount(player, CountTypes.WEDDING_ZHENQI, null,curzhenqi);
				content = content + ResManager.getInstance().getString(" 真气：")+ curzhenqi;
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已吃的过饱，今日婚宴所获真气已达上限"));
			}
			MessageUtil.notify_player(player, Notifys.CHAT_ROLE, content);
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, content);
			
			ResEdibleInfoToClientMessage cmsg= new ResEdibleInfoToClientMessage();
			cmsg.setBride(marriage.getSpouseslist().get(1).getName());
			cmsg.setBridegroom(marriage.getSpouseslist().get(0).getName());
			cmsg.setFoodnum(num);
			cmsg.setMarriageid(marriageid);
			cmsg.setNpcid(npc.getId());
			long lexp = ManagerPool.countManager.getCount(player, CountTypes.WEDDING_EXP, null);
			long lzhenqi = ManagerPool.countManager.getCount(player, CountTypes.WEDDING_ZHENQI, null);
			cmsg.setTotalexp((int) lexp);
			cmsg.setTotalzhenqi((int) lzhenqi);
			cmsg.setType((Byte)npc.getParameters().get("weddingtype"));
			MessageUtil.tell_player_message(player, cmsg);
			
			
			//----------------------------给婚礼夫妇加经验和真气-------------------------------
			Player a_player =ManagerPool.playerManager.getOnLinePlayer(marriage.getSpouseslist().get(0).getPlayerid());
			Player b_player =ManagerPool.playerManager.getOnLinePlayer(marriage.getSpouseslist().get(1).getPlayerid());
			boolean issave = false;
			
			if (a_player != null) {//A玩家
				int hostexp = a_player.getLevel() *  a_player.getLevel() *  a_player.getLevel() /4;
				int hostzhenqi = a_player.getLevel() *2;
				ManagerPool.playerManager.addExp(a_player,hostexp , AttributeChangeReason.WEDDING);
				ManagerPool.playerManager.addZhenqi(a_player, hostzhenqi, AttributeChangeReason.WEDDING);
				MessageUtil.notify_player(a_player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("婚宴菜肴被宾客食用，获得经验{1}，真气{2}"),hostexp+"",hostzhenqi+"");
			}else {
				int mlv = marriage.getSpouseslist().get(0).getLevel();
				int hostexp = mlv *  mlv *  mlv /4;
				int hostzhenqi = mlv *2;
				int expsum = marriage.getSpouseslist().get(0).getTotalexp();
				if (hostexp + expsum > 0 && hostexp + expsum < Integer.MAX_VALUE) {
					marriage.getSpouseslist().get(0).setTotalexp(hostexp + expsum);
				}
				
				int zhenqisum = marriage.getSpouseslist().get(1).getTotalzhenqi();
				if (hostzhenqi + zhenqisum > 0 && hostzhenqi + zhenqisum < Integer.MAX_VALUE) {
					marriage.getSpouseslist().get(1).setTotalexp(hostzhenqi + zhenqisum);
				}
				issave = true;
			}
			
			
			if (b_player != null) {//B玩家
				int hostexp = b_player.getLevel() *  b_player.getLevel() *  b_player.getLevel() /4;
				int hostzhenqi = b_player.getLevel() *2;
				ManagerPool.playerManager.addExp(b_player,hostexp , AttributeChangeReason.WEDDING);
				ManagerPool.playerManager.addZhenqi(b_player, hostzhenqi, AttributeChangeReason.WEDDING);
				MessageUtil.notify_player(b_player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("婚宴菜肴被宾客食用，获得经验{1}，真气{2}"),hostexp+"",hostzhenqi+"");
			}else{
				int mlv = marriage.getSpouseslist().get(1).getLevel();
				int hostexp = mlv *  mlv *  mlv /4;
				int hostzhenqi = mlv *2;
				
				int expsum = marriage.getSpouseslist().get(1).getTotalexp();
				if (hostexp + expsum > 0 && hostexp + expsum < Integer.MAX_VALUE) {
					marriage.getSpouseslist().get(1).setTotalexp(hostexp + expsum);
				}
				
				int zhenqisum = marriage.getSpouseslist().get(1).getTotalzhenqi();
				if (hostzhenqi + zhenqisum > 0 && hostzhenqi + zhenqisum < Integer.MAX_VALUE) {
					marriage.getSpouseslist().get(1).setTotalexp(hostzhenqi + zhenqisum);
				}
				issave = true;
			}
			
			if (issave) {
				saveMarriageinfo(marriageid, marriage, false);
			}
				
			if (num == 0) {//隐藏NPC
				ManagerPool.npcManager.hideNpc(npc);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("当前这桌婚宴菜肴被宾客食用完了，请找其他桌子"));
		}
	}
	
	
	
	/**获取配偶ID
	 * 
	 * @param player
	 * @return 0表示没有配偶
	 */
	public long getSpouseid(Player player) {
		long mid = player.getMarriageid();
		Marriage marriage = getMarriage(mid);
		if (marriage == null) {
			return 0;
		}else {
			return marriage.getSpouse(player).getPlayerid();
		}
	}
	
	
	
	/**获取配偶player信息
	 * 
	 * @param player
	 * @return 0表示没有配偶
	 */
	public Player getSpousePlayer(Player player) {
		long mid = player.getMarriageid();
		Marriage marriage = getMarriage(mid);
		if (marriage != null) {
			Player spousePlayer = ManagerPool.playerManager.getOnLinePlayer(marriage.getSpouseid(player));
			return spousePlayer;
		}
		return null;
	}
	
	
	
	
	/**婚宴状态 0没有进行，1进行中，2今天已经结束
	 * 
	 */
	public byte getWeddingstatus() {
		return weddingstatus;
	}
	
	/**婚宴状态 0没有进行，1进行中，2今天已经结束
	 * 
	 */
	public void setWeddingstatus(byte weddingstatus) {
		this.weddingstatus = weddingstatus;
	}
	
	
	
	/**点击送上红包 按钮
	 * 
	 * @param player
	 * @param msg
	 */
	public synchronized void stReqCockRedenvelopeToGameMessage(Player player,ReqCockRedenvelopeToGameMessage msg) {
		Marriage marriage = ManagerPool.marriageManager.getMarriage(msg.getMarriageid());
		if (marriage == null) {
			return;
		}
		if (msg.getMarriageid() == player.getMarriageid()) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("不能给自己红包"));
			return;
		}
		
		Wedding wedding = ManagerPool.marriageManager.getWedding(msg.getMarriageid());
		if (wedding != null) {
			for (RedEnvelope redEnvelope : wedding.getRedenvelopes()) {
				if (redEnvelope.getPlayerid() == player.getId()) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您已经给过红包了"));
					return;
				}
			}

			ResWeddingbanquetToClientMessage cmsg = new ResWeddingbanquetToClientMessage();
			cmsg.setBride(marriage.getSpouseslist().get(1).getName());
			cmsg.setBridegroom(marriage.getSpouseslist().get(0).getName());
			cmsg.setNpcid(0);
			cmsg.setMarriageid(msg.getMarriageid());
			long leiji = ManagerPool.countManager.getCount(player, CountTypes.RED_ENVELOPE, null);
			cmsg.setTotalredenvelope((int) leiji);
			cmsg.setType(wedding.getType());
			MessageUtil.tell_player_message(player, cmsg);
		}
	}
	
	
	
	/**获取配偶其他信息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqGetSpouseOtherToGameMessage(Player player,ReqGetSpouseOtherToGameMessage msg) {
		Player spouse = getSpousePlayer(player);
		if (spouse != null) {
			if (msg.getType() == 1) {	//1获取配偶龙元心法
				ResGetSpouseLongYuanToClientMessage lymsg = new ResGetSpouseLongYuanToClientMessage();
				LongYuanData longyuan = spouse.getLongyuan();
				byte lysection = longyuan.getLysection();	//星图
				byte lylv = longyuan.getLylevel();			//星位
				lymsg.setLongyuanlv(lysection);
				lymsg.setLongyuannum(lylv);
				MessageUtil.tell_player_message(player, lymsg);
			}else if (msg.getType() == 2 ) {//2获取配偶武功
				ManagerPool.skillManager.sendSpouseSkillInfos(player, spouse);
			}
		}
		
	}
	
	/**动TA一下
	 * 
	 * @param player
	 */
	public void stReqTeaseSpouseToGameMessage(Player player) {
		Player spouse = getSpousePlayer(player);
		if (spouse != null) {
			ResTeaseSpouseToClientMessage cmsg=new ResTeaseSpouseToClientMessage();
			MessageUtil.tell_player_message(spouse, cmsg);
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的TA当前不在线"));
		}
	}
	

	
	
	/**离婚或者更新戒指 后给纪念戒指
	 * 
	 */
	public void giveoldRing(long playerid,long marriageid,int ringid){
		Marriage marriage = ManagerPool.marriageManager.getMarriage(marriageid);
		if (marriage == null) {
			return;
		}
		Player player = ManagerPool.playerManager.getOnLinePlayer(playerid);
		List<Item> items = Item.createItems(ringid, 1, true, 0);
		if( !items.isEmpty()){
			items.get(0).getParameters().put("player_a", marriage.getSpouseslist().get(0).getName());
			items.get(0).getParameters().put("player_b", marriage.getSpouseslist().get(1).getName());
			items.get(0).getParameters().put("time", (marriage.getTime()/1000)+"");
			if(player!= null ){
				if(!ManagerPool.backpackManager.addItems(player,items,Reasons.DIV_ITEM,Config.getId())){
					ManagerPool.mailServerManager.sendSystemMail(playerid,null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("婚戒一枚"),(byte)1,0,items);
				}
			}else {
				ManagerPool.mailServerManager.sendSystemMail(playerid,null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("婚戒一枚"),(byte)1,0,items);
			}
		}
	}
	
	
	
	/**删除留言
	 * 
	 * @param parameter
	 * @param msg
	 */
	public synchronized void stReqDeleteLeaveMsgToGameHandler(Player player,ReqDeleteLeaveMsgToGameMessage msg) {
		Marriage marriage = ManagerPool.marriageManager.getMarriage(player.getMarriageid());
		if (marriage == null) {
			return;
		}
		int idx = -1;
		List<LeaveMsg> list = marriage.getLeavemsgs();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == msg.getMsgid()) {
				idx = i;
				break;
			}
		}
		if (idx >= 0) {
			list.remove(idx);
			ResShowLeaveMsgToClientMessage cmsg = new ResShowLeaveMsgToClientMessage();
			cmsg.setLeavemsglist(marriage.makeLeaveMsgInfo(player));
			MessageUtil.tell_player_message(player, cmsg);
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("删除耳边轻语成功。"));
			saveMarriageinfo(marriage.getId(), marriage, false);
		}
	}
	
	
	/**更换戒指
	 * 
	 * @param player
	 * @param msg
	 */
	public synchronized void  stReqChangeRingToGameMessage(Player player,ReqChangeRingToGameMessage msg) {
		Marriage marriage = ManagerPool.marriageManager.getMarriage(player.getMarriageid());
		if (marriage == null) {
			return;
		}
		if(marriage.getCurrringid() >= msg.getRingmodelid()){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，只能更换到比当前更高级的婚戒。"));
		}
		try {
			Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.WEDDING_RING.getValue());
			if (data  != null) {
				List<Integer[]> ringPricelist = JSON.parseArray(data.getQ_string_value(), Integer[].class);
				for (Integer[] integers : ringPricelist) {
					if((int)integers[0] == msg.getRingmodelid()){
						boolean is = true;
						if (player.getMoney() < integers[1]) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需铜币不足，需要{1}铜币"),integers[1]+"");
							is = false;
						}
						
						if(integers[2] > 0 && !ManagerPool.backpackManager.checkGold(player, integers[2])){
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需元宝不足，需要{1}元宝"),integers[2]+"");
							is = false;
						}
						if (integers[2] == 0 && integers[1] == 0) {
							is = false;
						}
						
						long aid= Config.getId();
						if (integers[1] > 0) {
							if (!ManagerPool.backpackManager.changeMoney(player,-integers[1], Reasons.MARRIED_RING_MONEY, aid)) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需铜币不足，需要{1}铜币"),integers[1]+"");
								is = false;
							}
						}
						
						if (integers[2] > 0) {
							if (!ManagerPool.backpackManager.changeGold(player, -integers[2],Reasons.MARRIED_RING_GOLD,aid)) {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("购买婚戒所需元宝不足，需要{1}元宝"),integers[2]+"");
								is = false;
							}
						}
						
						if (is) {
							marriage.setOldringid(marriage.getCurrringid());
							marriage.setCurrringid(msg.getRingmodelid());
							marriage.getSpouse(player).setNoticering(msg.getRingmodelid());//记录婚戒改变，timer里给配偶
							//因为多线程，配偶婚戒不在这里替换，在timer里替换，和给原婚戒
							giveoldRing(player.getId(),marriage.getId(),marriage.getOldringid());//给原来的戒指
							refreshMarriageinfo(player ,marriage);
							MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("更换升级婚戒成功，原戒指已经放入背包"));
							ResReplaceRingToClientMessage cmsg = new ResReplaceRingToClientMessage();
							cmsg.setRingmodelid(msg.getRingmodelid());
							MessageUtil.tell_player_message(player, cmsg);
							saveMarriageinfo(marriage.getId(), marriage, false);
							addMarriageLog(marriage,2);
						}else {
							ResReplaceRingToClientMessage cmsg = new ResReplaceRingToClientMessage();
							MessageUtil.tell_player_message(player, cmsg);//失败发送0
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			log.error("婚戒价格错误"+e,e);
		}
	}

	
	
	
	
	/**夫妻传送
	 * 
	 * @param parameter
	 */
	public void spouseMove(Player player) {
		Player spouse = getSpousePlayer(player);
		if (spouse != null) {
			Map map = ManagerPool.mapManager.getMap(spouse);
			if (map != null  ) {
				if (!map.isCopy()) {
					Map pmap = ManagerPool.mapManager.getMap(player);
					if (pmap.getId() == map.getId() && pmap.getLineId() == map.getLineId()) {
						ManagerPool.mapManager.changePosition(player, spouse.getPosition());
					}else {
						ManagerPool.mapManager.changeMap(player, spouse.getMap(), spouse.getMap(), spouse.getLine(), spouse.getPosition(),(byte)1, this.getClass().getName() + ".spouseMove");
					}
				}else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的TA当前正在副本中，无法传送"));
				}
			}
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您的TA当前不在线，无法传送"));
		}
	}
	
	
	
	
	
	/**加结婚技能，在同地图结婚，同线程
	 * 
	 */
	public void addMarriageSkill(Player player ) {
		try {
			if (player.getMarriageid() == 0) {
				return;
			}
			Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.WEDDING_RING.getValue());
			if (data  != null) {
				Marriage marriage = ManagerPool.marriageManager.getMarriage(player.getMarriageid());
				if (marriage == null) {
					return;
				}
				
				List<Integer[]> ringPricelist = JSON.parseArray(data.getQ_string_value(), Integer[].class);
				int lv=0;
				for (Integer[] integers : ringPricelist) {
					lv = lv +1;
					if((int)integers[0] == marriage.getCurrringid()){
						break;
					}
				}
				if (lv > 6) {
					lv = 6 ;
				}
				
				Skill skill = ManagerPool.skillManager.getSkillByModelId(player ,25005);
				if (skill != null) {
					ManagerPool.skillManager.setLevel(player, skill,lv,true);
				}else {
					ManagerPool.skillManager.addSkill(player, 25005);
					skill = ManagerPool.skillManager.getSkillByModelId(player ,25005);//传送
					ManagerPool.skillManager.setLevel(player, skill,lv,true);
				}
				
				Skill skill1 = ManagerPool.skillManager.getSkillByModelId(player ,25006);//守护
				Skill skill2 = ManagerPool.skillManager.getSkillByModelId(player ,25007);//红颜
				
				if (skill1 == null && player.getLevel() >= 50) {
					ManagerPool.skillManager.addSkill(player, 25006);
				}
				if (skill2 == null && player.getLevel() >= 60) {
					ManagerPool.skillManager.addSkill(player, 25007);
				}
			}	
			
		} catch (Exception e) {
			log.error(e,e);
		}
	}
		
	
	
	
	/**删除结婚技能
	 * 
	 */
	public void removeMarriageSkill(Player player) {
		ManagerPool.skillManager.removeSkill(player,25005);
		ManagerPool.skillManager.removeSkill(player,25006);
		ManagerPool.skillManager.removeSkill(player,25007);
		ManagerPool.skillManager.sendSkillInfos(player);
	}
	
	
	
	/**找到无用的结婚数据，并写到准备删除列表
	 * 
	 */
	public synchronized void filterDeleteMarriage(){
		Set<Entry<Long, Marriage>> entry = marriagemap.entrySet();
		Iterator<Entry<Long, Marriage>> it = entry.iterator();
		while (it.hasNext()) {
			Entry<Long, Marriage> marriageent = it.next();
			Marriage marriage =marriageent.getValue();
			if (marriage.getStatus() == 2) {
				MARKMAP.put(marriage.getId(), System.currentTimeMillis());
			}
		}
	}
	
	/**爱人下线通知
	 * 
	 * @param player
	 */
	public void offlineMarriage(Player player){
		Player other = getSpousePlayer(player);
		if (other != null) {
			ResOnlineStatusToClientMessage smsg = new ResOnlineStatusToClientMessage();
			smsg.setType((byte)2);
			MessageUtil.tell_player_message(other, smsg);
			MessageUtil.notify_player(other, Notifys.CHAT_ROLE,  ResManager.getInstance().getString("您的爱人{1}下线了"), player.getName());
		}
	}
	
	
	/**并蒂莲花 技能，本人使用药物，配偶额外加血
	 * 
	 */
	public void addSolution(Player player ,int type , int num ){
		Player other = getSpousePlayer(player);
		if (other != null && !other.isDie()) {
			Map mapa = ManagerPool.mapManager.getMap(player);
			Map mapb = ManagerPool.mapManager.getMap(other);
			if (mapa.getId() == mapb.getId()) {
				List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(other, 9172);
				Q_globalBean data = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.WEDDING_BDLZ.getValue());
				if (buffs.size() > 0 && data != null && data.getQ_int_value() > 0) {
					int  currnum = (int) (num * ((double)data.getQ_int_value()/100));
					if (type == BuffType.HP) {
						if(other.getHp() + currnum > other.getMaxHp()){
							other.setHp(other.getMaxHp());
						}else {
							other.setHp(other.getHp() + currnum);
						}
						ManagerPool.playerManager.onHpChange(other);
					}else if (type == BuffType.SP) {
						if(other.getSp() +currnum > other.getMaxSp()){
							other.setSp(other.getMaxSp());
						}else {
							other.setSp(other.getSp() + currnum);
						}
						ManagerPool.playerManager.onSpChange(other);
					}else if (type == BuffType.MP) {
						if(other.getMp() +currnum > other.getMaxMp()){
							other.setMp(other.getMaxMp());
						}else {
							other.setMp(other.getMp() + currnum);
						}
						ManagerPool.playerManager.onMpChange(other);
					}
				}
			}
		}
	}
	
	
	
	
	/**升级后更新婚姻数据玩家简要信息， (等级)
	 * 
	 * @param player
	 */
	public void saveMarriagelevel(Player player) {
		Marriage marriage = getMarriage(player.getMarriageid());
		if (marriage!= null) {
			marriage.getSelfSpouse(player.getId()).setLevel(player.getLevel());
			saveMarriageinfo(marriage.getId(), marriage, false);
		}
	}
	
	
	
	/**婚宴刷怪
	 * 
	 */
	public void marriageMonster(Map map){
		for (int i = 0; i < 10; i++) {
			Position position = MapUtils.getMapRandPoint(map.getMapModelid());
			ManagerPool.monsterManager.createMonsterAndEnterMap(5010, map.getServerId(), map.getLineId(), (int)map.getId(),position);
		}
		MessageUtil.notify_map(map,Notifys.CHAT_BULL, ResManager.getInstance().getString("大量招财猫前来助兴，请宾客们前往查探"));
	}
	
	
	
	/**记录婚姻
	 * 
	 * @param marriage
	 * @param type类型，0结婚，1离婚，2换戒指
	 */
	
	public void addMarriageLog(Marriage marriage,int type){
		MarriageLog log = new MarriageLog();
		log.setBridegroomid(marriage.getSpouseslist().get(0).getPlayerid());
		log.setBrideid(marriage.getSpouseslist().get(1).getPlayerid());
		log.setMarriageid(marriage.getId());
		log.setRingid(marriage.getCurrringid());
		log.setType(type);
		LogService.getInstance().execute(log);
	}
	
	/**婚宴状态
	 * 
	 * @param id
	 * @param type类型，0申请，1进行中，2结束
	 */
	public void addWeddingLog(long id ,int type ) {
		WeddingLog log = new WeddingLog();
		log.setMarriageid(id);
		log.setType(type);
		LogService.getInstance().execute(log);
	}
	
	
	public synchronized void removeMARKMAP(){
		Set<Entry<Long, Long>> entr = MarriageManager.MARKMAP.entrySet();
		Iterator<Entry<Long, Long>> it = entr.iterator();
		while (it.hasNext()) {
			Entry<Long, Long> mark = it.next();
			if (System.currentTimeMillis() > mark.getValue() + 5*60*1000 ) {  //5分钟清理一次无用的结婚数据
				Marriage marriage = ManagerPool.marriageManager.getMarriage(mark.getKey());
				if (marriage != null) {
					ManagerPool.marriageManager.deleteMarriage(marriage);
				}
				it.remove();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
}
