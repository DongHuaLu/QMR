package com.game.stalls.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.db.bean.StallsBean;
import com.game.db.dao.StallsDao;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.stalls.bean.StallsBriefInfo;
import com.game.stalls.bean.StallsBriefList;
import com.game.stalls.bean.StallsGoodsInfo;
import com.game.stalls.bean.StallsInfo;
import com.game.stalls.log.StallsWorldLog;
import com.game.stalls.message.ReqChangeStallsNameToWorldMessage;
import com.game.stalls.message.ReqStallsAdjustPricesToWorldMessage;
import com.game.stalls.message.ReqStallsBuyToWorldMessage;
import com.game.stalls.message.ReqStallsBuycheckToWorldMessage;
import com.game.stalls.message.ReqStallsOffShelfToWorldMessage;
import com.game.stalls.message.ReqStallsOpenUpToWorldMessage;
import com.game.stalls.message.ReqStallsPlayerIdLookToWorldMessage;
import com.game.stalls.message.ReqStallsProductWasAddedToWorldMessage;
import com.game.stalls.message.ReqStallsSearchToWorldMessage;
import com.game.stalls.message.ReqStallsSortToWorldMessage;
import com.game.stalls.message.ResChangeStallsNameMessage;
import com.game.stalls.message.ResStallsAdjustPricesMessage;
import com.game.stalls.message.ResStallsBuyAddMoneyToGameMessage;
import com.game.stalls.message.ResStallsBuyDeductingFailToGameMessage;
import com.game.stalls.message.ResStallsBuyDeductingToGameMessage;
import com.game.stalls.message.ResStallsBuyMessage;
import com.game.stalls.message.ResStallsBuycheckToGameMessage;
import com.game.stalls.message.ResStallsErrorMessage;
import com.game.stalls.message.ResStallsOffShelfMessage;
import com.game.stalls.message.ResStallsOffShelfToGameMessage;
import com.game.stalls.message.ResStallsOpenUpMessage;
import com.game.stalls.message.ResStallsPlayerIdLookMessage;
import com.game.stalls.message.ResStallsProductWasAddedFailToGameMessage;
import com.game.stalls.message.ResStallsProductWasAddedMessage;
import com.game.stalls.message.ResStallsSearchMessage;
import com.game.stalls.message.ResStallsSortMessage;
import com.game.stalls.structs.StallsInfoSave;
import com.game.stalls.structs.StallsItem;
import com.game.utils.MessageUtil;
import com.game.utils.VersionUpdateUtil;

public class StallsManager {
	private static Object obj = new Object();
	private Logger log = Logger.getLogger(StallsManager.class);
	// 管理类实例
	private static StallsManager manager;
	private static int stallsid = 0;
	private StallsManager() {}

	public static StallsManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new StallsManager();
			}
		}
		return manager;
	}
	

	/**
	 * 玩家摆摊简要信息
	 */
	private static Vector<StallsBriefInfo> stallsbrieflist =  new Vector<StallsBriefInfo>();
	
	/**
	 * 玩家摊位详细信息
	 */
	private static  HashMap<Long, StallsInfoSave> stallsinfolist = new HashMap<Long, StallsInfoSave>();

	//摊位数据接口
	private StallsDao stallsDao = new StallsDao();

	public StallsDao getStallsDao() {
		return stallsDao;
	}

	
	/**
	 * 服务器启动 ，从数据库  读取所有摊位信息
	 * 
	 */
	public void loadAllStalls() {
		try {
			List<StallsBean> list = getStallsDao().select();
			Iterator<StallsBean> iterator = list.iterator();
			while (iterator.hasNext()) {
				StallsBean stallsBean = (StallsBean) iterator.next();
				if (stallsBean != null) {
					StallsInfoSave stallsInfo = (StallsInfoSave) JSONserializable.toObject(VersionUpdateUtil.dateLoad(stallsBean.getStallsdata()), StallsInfoSave.class);
					stallsinfolist.put(stallsBean.getRoleid(), stallsInfo);
				}else {
					log.error("摊位数据读取错误");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 从数据库  读取单个摊位信息,内存中已经存在，则不读取
	 * 
	 */
	public void loadStalls(long pid) {
		try {
			if(stallsinfolist.containsKey(pid) == false){
				StallsBean stallsBean = getStallsDao().selectsingle(pid);
				if (stallsBean != null) {
					StallsInfoSave stallsInfo = (StallsInfoSave) JSONserializable.toObject(VersionUpdateUtil.dateLoad(stallsBean.getStallsdata()), StallsInfoSave.class);
					stallsinfolist.put(stallsBean.getRoleid(), stallsInfo);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 储存所有摊位数据 到数据库
	 */
//	public void saveAllStalls() {
//		for (Entry<Long, StallsInfoSave> entry : stallsinfolist.entrySet()) {
//			StallsInfoSave stallsInfo = entry.getValue();
//			long pid = entry.getKey();
//			if (stallsInfo != null) {
//				saveStalls(pid,stallsInfo, false);
//			}
//		}
//	}
	
	
	/**
	 * 储存单个摊位数据  到数据库
	 * @param pid
	 * @param stallsInfo
	 */
	public void saveStalls(long pid ,StallsInfoSave stallsInfo, boolean insert) {
		try {
			StallsBean stallsBean = new StallsBean();
			stallsBean.setRoleid(pid);
			stallsBean.setStallsdata(VersionUpdateUtil.dateSave(JSONserializable.toString(stallsInfo)));
			if (insert) {
				if (getStallsDao().insert(stallsBean) == 0) {
					log.error(String.format("摊位数据保存错误，玩家id[%s]，内容[%s]", pid,stallsBean));
				}	
			}else {
				if (getStallsDao().update(stallsBean) == 0) {
					log.error(String.format("摊位数据保存错误，玩家id[%s]，内容[%s]", pid,stallsBean));
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**更新单个摊位数据
	 * 
	 * @param pid
	 * @param stallsInfo
	 */
	public void saveStallsinfo(long pid ,StallsInfoSave stallsInfo, boolean insert){
		stallsinfolist.put(pid, stallsInfo);
		saveStalls(pid, stallsInfo, insert);
	}
	

	
	
	//-------------------------------------------------------------------------------------
	
	//检测是否在摆摊    1在摆摊，0没有摆摊
	public byte getToStalls(long pid){
		Player player  = ManagerPool.playerManager.getPlayer(pid);
		if (player != null) {
			StallsInfoSave stallsInfoSave = getStallsinfolist(pid);
			if (stallsInfoSave != null && stallsInfoSave.getStallsgoodsinfo().size() > 0) {
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * 获得个人摊位详细信息
	 * @param pid
	 * @param pinfo
	 * @return 
	 */
	public StallsInfoSave getStallsinfolist(long pid ) {
		StallsInfoSave stallsInfoSave = stallsinfolist.get(pid);
		if (stallsInfoSave  != null) {
			return stallsInfoSave;
		}
		return null;
	}
	
	

	/**
	 * 插入个人摆摊简要信息
	 */
	public void setStallslist(long pid ,StallsBriefInfo pinfo) {
		boolean is = true;
		int size = stallsbrieflist.size();
		for (int i = 0; i < size; i++) {
			StallsBriefInfo inof = stallsbrieflist.get(i);
			if(inof.getPlayerid() == pid) {
				pinfo.setStallsid(inof.getStallsid());
				stallsbrieflist.set(i, pinfo);
				is = false;
				break;
			}
		}
		//新加入摊位
		if(is) {
			stallsid = stallsid + 1;
			pinfo.setStallsid(stallsid);
			stallsbrieflist.add(pinfo);	
		}
		
		//如果摊位ID是0，肯定刚开始摆摊，给个新ID
		if (pinfo.getStallsid() == 0) {	
			stallsid = stallsid + 1;
			pinfo.setStallsid(stallsid);
		}
		
	}
	
	
	/**
	 * 获得个人摆摊简要信息
	 */
	public StallsBriefInfo  getStallslist(long pid ) {
		for (int i = 0; i < stallsbrieflist.size(); i++) {
			StallsBriefInfo inof = stallsbrieflist.get(i);
			if(inof.getPlayerid() == pid) {
				return inof;
			}
		}
		return null;
	}	
	
	
	/**
	 * 删除个人摆摊简要信息
	 */
	public void  removeStallslist(long pid ) {
		int size = stallsbrieflist.size();
		int pos = -1;
		for (int i = 0; i < size; i++) {
			StallsBriefInfo inof = stallsbrieflist.get(i);
			if(inof.getPlayerid() == pid) {
				pos = i;
				break;
			}
		}
		
		if (pos >= 0) {
			stallsbrieflist.remove(pos);
		}
	}
	
	
	
	
	/**得到同个国家的摊位简要信息
	 * 
	 * @param player
	 * @return 
	 */
	public List<StallsBriefInfo> getSameCountryStallslist(Player player){
		List<StallsBriefInfo> tmplist =  new ArrayList<StallsBriefInfo>();
		int country = player.getCountry();
		for (StallsBriefInfo stallsBriefInfo : stallsbrieflist) {
			Player xplayer = ManagerPool.playerManager.getPlayer(stallsBriefInfo.getPlayerid());
			if (xplayer != null && xplayer.getCountry() == country ) {
				tmplist.add(stallsBriefInfo);
			}
		}
		return tmplist ;
	}
	
	
	
	
	//-----------------------------------------------------------------
	
	/**
	 * 登录增加个人摆摊简要信息
	 * 
	 */
	public void loginAddStallslist(Player player){
		StallsBriefInfo info = getPersonalStallsinfo(player);
		if (info!= null) {
			removeStallslist(player.getId());
			setStallslist(player.getId(),info);
		}
	}

	/**离线清除摆摊个人简要信息
	 * 
	 */
	public void stOfflineRemoveStallslist(long pid){
		removeStallslist(pid);
	}
	
	
	/**刷新摆摊个人简要信息
	 * 
	 * @param player
	 */
	public void refreshStallslist(Player player) {
		StallsBriefInfo  stallsbriefinfo = getPersonalStallsinfo(player);
		if (stallsbriefinfo != null) {
			stallsbriefinfo.setPlayerlv((short) player.getLevel());
			setStallslist(player.getId(),stallsbriefinfo);
		}else {
			removeStallslist(player.getId() );
		}	
	}
	
	
	//-----------------------------------------------------------------
	/**
	 * 设置并返回个人摆摊简要信息
	 * 
	 * @param player
	 */
	public  StallsBriefInfo getPersonalStallsinfo(Player player) {
		StallsInfoSave playerstalls = getStallsinfolist(player.getId());
		if (playerstalls == null) {
			return null;
		}
		
		List<StallsItem> items = playerstalls.getStallsgoodsinfo();
		if (items.size() > 0) {
			StallsBriefInfo info = new StallsBriefInfo();
			info.setPlayerid(player.getId());
			info.setPlayerlv((short) player.getLevel());
			info.setPlayername(player.getName());
			info.setStallslv(playerstalls.acqStallslv());
			info.setStallsname(playerstalls.getStallsname());
			info.setSellgold(0);
			info.setSellyuanbao(0);
			byte itemnum = 0; 
			List<Integer> rmnull = new ArrayList<Integer>();

			for (int i = 0; i < items.size(); i++) {
				if (items.get(i) == null) {
					rmnull.add(i);
				}else {
					if(items.get(i).getItem().getItemModelId() == -1) {	//元宝数量
						info.setSellgold(items.get(i).getItem().getNum());
					}else if (items.get(i).getItem().getItemModelId() == -2) {//元宝数量
						info.setSellyuanbao(items.get(i).getItem().getNum());
					}else {
						itemnum = (byte) (itemnum + 1);
					}	
				}
			}
			
			for (int i = 0; i < rmnull.size(); i++) {
				items.remove(i);
			}
			rmnull= null;
			info.setSellgoodsnum(itemnum);
			return info;
		}
		return null;
	}
	
	

	/**测试用数据产生
	 * 
	 * @param player
	 * @return
	 */
	public List<StallsBriefInfo> sttestmsg(Player player){
		List<StallsBriefInfo> tmplist =  new ArrayList<StallsBriefInfo>();
		Random rndRandom = new Random();
		for (int i = 0; i < 100; i++) {
			StallsBriefInfo info = new StallsBriefInfo();
			info.setPlayerid(i);
			info.setPlayerlv((short) i);
			info.setPlayername(""+ i);
			info.setSellgold(rndRandom.nextInt(2));
			info.setSellyuanbao(rndRandom.nextInt(3));
			info.setStallsid((short) i);
			info.setSellgoodsnum((byte) i);
			tmplist.add(info);
		}
		return tmplist;
	}
	
	
	//-----------------------------------------------------------------
	/**
	 * 打开摆摊面板  分段发送数据 
	 * @param player
	 * @param little   开始
	 * @param large		结束
	 */
	 
	public void stReqStallsOpenUpToWorldMessage(ReqStallsOpenUpToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player == null) {
			return;
		}
		StallsBriefList stallstop = new StallsBriefList();
		List<StallsBriefInfo>  tmpList = getSameCountryStallslist(player);
		stallstop.setIndexlittle(msg.getIndexlittle());
		stallstop.setIndexLarge(msg.getIndexLarge());
		int size = tmpList.size();
		stallstop.setStallssnum((short)size);
		List<StallsBriefInfo>  tab = stallstop.getStallsbrieflist();
		if(size > msg.getIndexLarge() ) {
			for (int i = msg.getIndexlittle(); i < msg.getIndexLarge(); i++) {
				tab.add(tmpList.get(i));
			}
		}else {
			for (int i = msg.getIndexlittle(); i < size; i++) {
				tab.add(tmpList.get(i));
			}
		}
		ResStallsOpenUpMessage cmsg = new ResStallsOpenUpMessage();
		cmsg.setStallsbrieflist(stallstop);
		MessageUtil.tell_player_message(player, cmsg);
		if (size == 0) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，当前没有玩家摆摊。"));
		}
		tmpList = null;
	}
	
	
	
	
	/**发送商品信息到前端
	 * 
	 * @param player
	 * @param pid
	 * @param type   1看摊，2购买商品后，3商品上架，4商品调整，5商品下架
	 */
	public void  stSendboothsdata(Player player,long pid,byte type ) {
		Player xplayer = ManagerPool.playerManager.getPlayer(pid);
		if (xplayer == null) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该玩家已下线，摊位已关闭。"));
			ResStallsErrorMessage errmsg = new ResStallsErrorMessage();
			errmsg.setPlayerid(pid);
			errmsg.setType((byte) 1);
			MessageUtil.tell_player_message(player, errmsg);	//错误消息
			return ;
		}
		
		StallsInfoSave pinof = getStallsinfolist(pid);	//得到摊位数据
		if (pinof == null ) {
			if (type == 1 && player.getId() == pid ) {
				pinof = new StallsInfoSave();
			}else {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，没有找到『{1}』的摊位信息。"),xplayer.getName());
				return ;
			}
		}else {
			if(pinof.getStallsgoodsinfo().size() == 0 && type == 1 && player.getId() != pid ){
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，没有找到『{1}』的摊位信息。"),xplayer.getName());
				return ;
			}
		}
		
		StallsInfo tab  = new StallsInfo();
		tab.setStallslv(pinof.acqStallslv());
		tab.setStallsname(pinof.getStallsname());
		tab.setPlayerid(pid);
		tab.setPlayername(xplayer.getName());
		List<StallsGoodsInfo> goodslist = tab.getStallsgoodsinfo();
		List<Integer> rmnull = new ArrayList<Integer>();
		
		for (int i = 0; i < pinof.getStallsgoodsinfo().size(); i++) {
			StallsItem pitem = pinof.getStallsgoodsinfo().get(i);
			if (pitem == null) {
				rmnull.add(i);
			}else {
				StallsGoodsInfo goodsinfo = new StallsGoodsInfo();
				goodsinfo.setPricegold(pitem.getPricegold());
				goodsinfo.setPriceyuanbao(pitem.getPriceyuanbao());
				goodsinfo.setIteminfo(pitem.getItem().buildItemInfo());	
				goodslist.add(goodsinfo);
			}

		}
		for (int i = 0; i < rmnull.size(); i++) {
			pinof.getStallsgoodsinfo().remove(i);
		}
			
		switch (type) {
		case 1:
			ResStallsPlayerIdLookMessage msga = new ResStallsPlayerIdLookMessage();
			msga.setStallsinfo(tab);
			MessageUtil.tell_player_message(player, msga);
			break;
		case 2:
			ResStallsBuyMessage msgb = new ResStallsBuyMessage();
			msgb.setStallsinfo(tab);
//			if (stCkStallsRating(player,xplayer)) {
//				msgb.setIsrating((byte) 0);		//允许评分
//			}else {
				msgb.setIsrating((byte) 1);
//			}
			MessageUtil.tell_player_message(player, msgb);
			break;
		case 3:
			ResStallsProductWasAddedMessage msgc = new ResStallsProductWasAddedMessage();
			msgc.setStallsinfo(tab);
			MessageUtil.tell_player_message(player, msgc);
			break;		
		case 4:
			ResStallsAdjustPricesMessage msgd = new ResStallsAdjustPricesMessage();
			msgd.setStallsinfo(tab);
			MessageUtil.tell_player_message(player, msgd);
			break;			
		case 5:
			ResStallsOffShelfMessage msge = new ResStallsOffShelfMessage();
			msge.setStallsinfo(tab);
			MessageUtil.tell_player_message(player, msge);
			break;
		default:
			break;
		}
	}
	
	

	
	/**
	 * 点击某个摊位，进行看摊消息
	 */
	
	public void stReqStallsPlayerIdLookToWorldMessage(ReqStallsPlayerIdLookToWorldMessage msg) {
		Player xplayer = ManagerPool.playerManager.getPlayer(msg.getStallsplayerid());
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player == null) return ;
		
		if (xplayer == null) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，该玩家已下线，摊位已关闭。"));
			ResStallsErrorMessage errmsg = new ResStallsErrorMessage();
			errmsg.setPlayerid(msg.getStallsplayerid());
			errmsg.setType((byte) 1);
			MessageUtil.tell_player_message(player, errmsg);	//错误消息
			return ;
		}
		if (xplayer.getName() == null) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，暂时不能查看此摊位，对方已下线或者正在登录中。"));
			return;
		}
		
		if (player.getCountry() == xplayer.getCountry()) {
			stSendboothsdata(player,xplayer.getId(),(byte) 1);
		}else {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，只能查看相同国家的玩家摊位。"));
		}
	}

	
	
	/**
	 * 按照条件排序（如果是按照名字搜到到的数据，前端自行排序）
	 * @param player
	 * @param little
	 * @param large
	 * @param type 0，摊位星级，1，玩家等级，2，物品数量，3在售货币
	 */
	@SuppressWarnings("unchecked")
	public void  stReqStallsSortToWorldMessage(ReqStallsSortToWorldMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());
		if (player == null) return;
		List<StallsBriefInfo>  tmplist = getSameCountryStallslist(player);
		if(tmplist.size() > 0 ){
			switch (msg.getType()) {
			case 0:
				//Collections.sort(tmplist, new StallsSort() );
				break;
			case 1:
				Collections.sort(tmplist, new StallsSort1() );
				break;
			case 2:
				Collections.sort(tmplist, new StallsSort2() );
				break;
			case 3:
				Collections.sort(tmplist, new StallsSort3() );
				break;
			case 4:
				Collections.sort(tmplist, new StallsSort4() );
				break;
			default:
				break;
			}

			StallsBriefList stallstop = new StallsBriefList();
			stallstop.setIndexlittle(msg.getIndexlittle());
			stallstop.setIndexLarge(msg.getIndexLarge());
			stallstop.setStallssnum((short)tmplist.size());
			List<StallsBriefInfo>  tab = stallstop.getStallsbrieflist();
			if(tmplist.size() > msg.getIndexLarge() ) {
				for (int i = msg.getIndexlittle(); i < msg.getIndexLarge(); i++) {
					tab.add(tmplist.get(i));
				}
			}else {
				for (int i = msg.getIndexlittle(); i < tmplist.size(); i++) {
					tab.add(tmplist.get(i));
				}
			}
			ResStallsSortMessage cmsg = new ResStallsSortMessage();
			stallstop.setType(msg.getType());
			cmsg.setStallsbrieflist(stallstop);
			MessageUtil.tell_player_message(player, cmsg);
		}else {
			ResStallsSortMessage cmsg = new ResStallsSortMessage();
			cmsg.setStallsbrieflist(new StallsBriefList());
			MessageUtil.tell_player_message(player, cmsg);
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，当前没有玩家摆摊。"));
		}
		tmplist = null;
	}
	
	
	
	
	/**
	 * 商品上架
	 * @param player
	 * @param itemid	-1元宝，-2元宝
	 * @param goldnum	铜币售价
	 * @param yuanbaonum   	元宝售价
	 * @param itemnum    要出售的货物数量
	 */
	public void  stReqStallsProductWasAddedToWorldMessage(ReqStallsProductWasAddedToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getStallsplayerid());
		if (player == null) return;
		
		boolean insert = false;
		Item item = (Item) JSONserializable.toObject(msg.getItem(),Item.class);
		if (item == null) {
			log.error("玩家:"+msg.getStallsplayerid()+"商品上架数据错误"+ msg.getItem());
			return;
		}
		StallsInfoSave pinof = null;
		pinof = getStallsinfolist(msg.getStallsplayerid());	//得到摊位数据
		if (pinof == null ) {
			pinof =  new StallsInfoSave();
			insert = true;
		}
		
		int num = 0; 
		for (int i = 0; i < pinof.getStallsgoodsinfo().size(); i++) {
			Item gitem = pinof.getStallsgoodsinfo().get(i).getItem();
			if (gitem.getItemModelId() > 0) {//道具数量
				num =  num + 1;
			}else {
				if (item.getItemModelId() == gitem.getItemModelId()) {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("您已经上架元宝或者铜币，同类型不能多次上架。"));
					stResStallsProductWasAddedFailToGameMessage(player,msg.getItem());	//返还道具
					return;
				}
			}
		}
		
		if (item.getItemModelId() > 0 && num >=10) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("在售物品已经达到上限10件，请先下架部分货物。"));
			stResStallsProductWasAddedFailToGameMessage(player,msg.getItem());	//返还道具
			return ;
		}
		

		if (msg.getPricegold() == 0 && msg.getPriceyuanbao() == 0) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("请先输入对该物品的铜币或元宝定价。"));
			stResStallsProductWasAddedFailToGameMessage(player,msg.getItem());	//返还道具
			return ;
		}
		
		Q_itemBean model = ManagerPool.dataManager.q_itemContainer.getMap().get(item.getItemModelId());
		if (model == null ) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("该物品暂时无法上架。"));
			stResStallsProductWasAddedFailToGameMessage(player,msg.getItem());	//返还道具
			log.error("世界服itemID: "+item.getItemModelId() +"不存在");
			return ;
		}
		
		
		if (msg.getPricegold() < 0 || msg.getPriceyuanbao() < 0) {
			return;
		}
		String pricestrString = "";
		if (msg.getPricegold() > 0) {
			pricestrString = ResManager.getInstance().getString("铜币")+msg.getPricegold()+" ";
		}
		if (msg.getPriceyuanbao() > 0) {
			pricestrString = pricestrString+ResManager.getInstance().getString("元宝:")+msg.getPriceyuanbao() ;
		}
		
		List<StallsItem> tab = pinof.getStallsgoodsinfo();
		boolean issuccess = false;
		StallsItem 	stallsitem = null;
		if (msg.getGoodsid() < 0) {
			stallsitem = getstallsitem(tab,msg.getGoodsid());
		}
		long itemid = msg.getGoodsid();
	
		int itemnum = item.getNum();
		String nameString = "";
		if (itemid == -1 ) {	//上架铜币
			nameString = ResManager.getInstance().getString("铜币");
			if (stallsitem == null) {
				stallsitem = new StallsItem();
				stallsitem.setItem(item);
				tab.add(stallsitem);
			}else {
				stallsitem.getItem().setNum(itemnum);
				}
			stallsitem.setPriceyuanbao(msg.getPriceyuanbao()); //设定铜币的元宝售价
			issuccess = true;
			
		}else if (itemid == -2) {//上架元宝
			nameString = ResManager.getInstance().getString("元宝");
			if (stallsitem == null) {
				stallsitem = new StallsItem();
				stallsitem.setItem(item);
				tab.add(stallsitem);
			}else {
				stallsitem.getItem().setNum(itemnum);
			}
			stallsitem.setPricegold(msg.getPricegold());	//设定元宝的铜币售价
			issuccess = true;
			
		}else{		//上架道具
			nameString = model.getQ_name();
			stallsitem = new StallsItem();
			stallsitem.setItem(item);
			stallsitem.setPricegold(msg.getPricegold());
			stallsitem.setPriceyuanbao(msg.getPriceyuanbao());
			tab.add(stallsitem);
			issuccess = true;
		}
		if (issuccess) {
			saveStallsinfo(player.getId(),pinof,insert);
			refreshStallslist(player);
			MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("商品:{1}({2}) 上架完成,定价：{3}"),nameString,String.valueOf(itemnum),pricestrString);
			stSendboothsdata(player,player.getId(),(byte) 3);
			stStallsLog(player.getId(),-1,item,stallsitem.getPricegold(),stallsitem.getPriceyuanbao());	//数据库日志记录
		}
		
	}
	
	/**上架失败，返还道具
	 * 
	 */
	public void stResStallsProductWasAddedFailToGameMessage(Player player,String item){
		ResStallsProductWasAddedFailToGameMessage msg = new ResStallsProductWasAddedFailToGameMessage();
		msg.setItem(item);
		msg.setStallsplayerid(player.getId());
		MessageUtil.send_to_game(player,msg);
	}
	
	
	
	
	/**购买时获取指定商品
	 * 
	 * @param goodsinfo
	 * @param itemid
	 * @return
	 */
	public StallsItem getstallsitem(List<StallsItem> goodsinfo ,long itemid){
		if (itemid > 0) {
			for (int i = 0; i < goodsinfo.size(); i++) {
				StallsItem 	stallsitem = goodsinfo.get(i);
				if (stallsitem.getItem().getId() == itemid) {
					return stallsitem;
				}
			}
		}else {
			for (int i = 0; i < goodsinfo.size(); i++) {
				StallsItem 	stallsitem = goodsinfo.get(i);
				if (stallsitem.getItem().getItemModelId() == itemid) {
					return stallsitem;
				}
			}
		}
		return null;
	}
	
	
	
	//---------------------------------------------------------------------------
	
	/**购买商品，验证
	 * 
	 * @param msg
	 */
	public void stReqStallsBuycheckToWorldMessage(ReqStallsBuycheckToWorldMessage msg ){
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());		
		Player stallsplayer = ManagerPool.playerManager.getPlayer(msg.getStallsplayerid());		//摊主
		if (player != null) {

			long time = System.currentTimeMillis();
			if (time - player.getStallverifytime() < 1000) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，购买速度过快。"));
				log.error("玩家："+msg.getPlayerid() + "摊位道具购买速度过快，中断购买");
				return;
			}
			
			byte stat = 1;
			if (stallsplayer != null){
				if (getStallslist(msg.getStallsplayerid()) ==null || stallsplayer.isSyncdata() == false) {
					if (stallsplayer.isSyncdata() ) {
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，请刷新，摊主的货物好像已经卖完了。"));
						return;
					}else {
						MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，摊主目前不在线。"));
						return;
					}
	
				}
				
				StallsInfoSave pinof = getStallsinfolist(msg.getStallsplayerid());	//得到摊位数据
				if (pinof != null) {
					StallsItem goodinfo = getstallsitem(pinof.getStallsgoodsinfo(),msg.getGoodsid());
					if (goodinfo != null) {
						if (goodinfo.getPricegold() == msg.getPricegold() && goodinfo.getPriceyuanbao() == msg.getPriceyuanbao() ) {
							stat = 0;//检测通过
						}else {
							stat = 2;//价格修改
	
						}
					}
	
				}
			}
		
			player.setStallverifytime(time);//写入验证时间
			ResStallsBuycheckToGameMessage gmsg = new ResStallsBuycheckToGameMessage();	
			gmsg.setPricegold(msg.getPricegold());
			gmsg.setPriceyuanbao(msg.getPriceyuanbao());
			gmsg.setStallsplayerid(msg.getStallsplayerid());
			gmsg.setGoodsid(msg.getGoodsid());
			gmsg.setPlayerid(msg.getPlayerid());
			gmsg.setStatus(stat);	
			MessageUtil.send_to_game(player,gmsg);
		}
	}
	
	
	
	
	
	/**购买商品，GAME扣钱后，这里给道具（如果失败，返还钱）
	 * 
	 * @param msg
	 */
	public void  stReqStallsBuyToWorldMessage(ReqStallsBuyToWorldMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getPlayerid());		
		if (player == null) {
			log.error("玩家:"+msg.getPlayerid()+"购买商品"+msg.getGoodsid()+"，已付钱，但不在世界服务器");
			return;
		}
		Player stallsplayer = ManagerPool.playerManager.getPlayer(msg.getStallsplayerid());		//摊主
		
		if (stallsplayer != null){
			if (getStallslist(msg.getStallsplayerid()) != null && stallsplayer.isSyncdata()) {
				StallsInfoSave pinof = getStallsinfolist(msg.getStallsplayerid());	//得到摊位数据
				if (pinof != null) {
					StallsItem stallsitem = getstallsitem(pinof.getStallsgoodsinfo(),msg.getGoodsid());
					if (stallsitem != null && stallsitem.getPricegold() == msg.getPricegold() && stallsitem.getPriceyuanbao() == msg.getPriceyuanbao() ) {
						if (pinof.getStallsgoodsinfo().remove(stallsitem)) {		//删除商品
							String iteString = JSONserializable.toString(stallsitem.getItem());
							//买家得到商品
							ResStallsBuyDeductingToGameMessage gmsg = new ResStallsBuyDeductingToGameMessage();	
							long logid = Config.getId();		//数据记录用唯一ID
							gmsg.setPricegold(msg.getPricegold());
							gmsg.setPriceyuanbao(msg.getPriceyuanbao());
							gmsg.setStallsplayername(stallsplayer.getName());
							gmsg.setStallsplayerid(stallsplayer.getId());
							gmsg.setGoodsid(msg.getGoodsid());
							gmsg.setPlayerid(msg.getPlayerid());
							gmsg.setItem(iteString);
							gmsg.setPlayername(player.getName());
							gmsg.setTradersid(logid);
							MessageUtil.send_to_game(player,gmsg);	
							
							//摊主得到钱
							ResStallsBuyAddMoneyToGameMessage tmsg = new ResStallsBuyAddMoneyToGameMessage();
							tmsg.setPricegold(msg.getPricegold());
							tmsg.setPriceyuanbao(msg.getPriceyuanbao());
							tmsg.setStallsplayername(stallsplayer.getName());
							tmsg.setStallsplayerid(stallsplayer.getId());
							tmsg.setGoodsid(msg.getGoodsid());
							tmsg.setPlayerid(msg.getPlayerid());
							tmsg.setItem(iteString);
							tmsg.setPlayername(player.getName());
							tmsg.setTradersid(logid);
							MessageUtil.send_to_game(stallsplayer,tmsg);
							saveStallsinfo(stallsplayer.getId(),pinof,false);
							refreshStallslist(stallsplayer);
							stSendboothsdata(player,stallsplayer.getId(),(byte) 2);
							stSendboothsdata(stallsplayer,stallsplayer.getId(),(byte) 1);//刷新摊主摆摊面板
							stStallsLog(msg.getStallsplayerid(),msg.getPlayerid(),stallsitem.getItem(),stallsitem.getPricegold(),stallsitem.getPriceyuanbao());	//数据库日志记录
							return;
						}
					}
				}
			}
		}
		
		//购买失败，还钱给买家
		ResStallsBuyDeductingFailToGameMessage smsg = new ResStallsBuyDeductingFailToGameMessage();
		smsg.setPricegold(msg.getPricegold());
		smsg.setPriceyuanbao(msg.getPriceyuanbao());
		smsg.setStallsplayerid(stallsplayer.getId());
		smsg.setGoodsid(msg.getGoodsid());
		smsg.setPlayerid(msg.getPlayerid());
		MessageUtil.send_to_game(player,smsg);	
		refreshStallslist(stallsplayer);
		stSendboothsdata(player,stallsplayer.getId(),(byte) 2);
		log.error("玩家"+msg.getPlayerid()+",itemID："+msg.getGoodsid()+ "购买失败");
	}
	
	
	
	//---------------------------------------------------

	/**商品下架
	 * 
	 * @param player
	 * @param itemid  铜币-1，元宝-2
	 */
	public void stReqStallsOffShelfToWorldMessage(ReqStallsOffShelfToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getPlayer(msg.getStallsplayerid());		//摊主
		if (player == null) return;
		StallsInfoSave xpinof = getStallsinfolist(msg.getStallsplayerid());	//得到摊位数据
		if (xpinof == null) {
			return;
		}

		List<StallsItem> goodsinfo = xpinof.getStallsgoodsinfo();
		StallsItem 	stallsitem = getstallsitem(goodsinfo,msg.getGoodsid());
		if(stallsitem == null ) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您想要下架的物品已不存在，可能被其他玩家抢购。"));
			return;
		}
		
		Item item = stallsitem.getItem();
		if (item != null ) {
			if (goodsinfo.remove(stallsitem)) {
				saveStallsinfo(player.getId(),xpinof,false);
				refreshStallslist(player);
				ResStallsOffShelfToGameMessage gmsg = new ResStallsOffShelfToGameMessage();
				gmsg.setItem(JSONserializable.toString(item));
				gmsg.setStallsplayerid(msg.getStallsplayerid());
				MessageUtil.send_to_game(player,gmsg);	
				stSendboothsdata(player,player.getId(),(byte) 5);
				stStallsLog(player.getId(),-3,item,stallsitem.getPricegold(),stallsitem.getPriceyuanbao());	//数据库日志记录
				return;
				}
			}
		log.error("玩家:"+msg.getStallsplayerid()+"商品下架失败，"+msg.getGoodsid()+"");
		
	}

	
	
	
	
	/**
	 * 道具调整进行中（现在不让调整道具数量）
	 * @param msg
	 */
	public void stReqStallsAdjustPricesToWorldMessage(ReqStallsAdjustPricesToWorldMessage msg){
		Player player = ManagerPool.playerManager.getPlayer(msg.getStallsplayerid());		//摊主
		if (player == null) return;
		StallsInfoSave xpinof = getStallsinfolist(msg.getStallsplayerid());	//得到摊位数据
		if (xpinof == null) {
			return;
		}

		List<StallsItem> goodsinfo = xpinof.getStallsgoodsinfo();
		StallsItem 	stallsitem = getstallsitem(goodsinfo,msg.getGoodsid());
		if(stallsitem == null ) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，您想要调整的物品已不存在，可能被其他玩家抢购。"));
			return;
		}
		
		//int num = msg.getNum() - msg.getCurrentnum();
		//Item item = stallsitem.getItem();
		//item.setNum(msg.getNum());
		stallsitem.setPricegold(msg.getPricegold());
		stallsitem.setPriceyuanbao(msg.getPriceyuanbao());
		if(msg.getPos() >= 0 && msg.getPos() < goodsinfo.size() && msg.getGoodsid() > 0) {	//更换位置
			StallsItem 	laoitem = goodsinfo.get(msg.getPos());
			if (laoitem != null && laoitem.getItem().getId() != msg.getGoodsid()) {
				StallsItem 	newitem = getstallsitem(goodsinfo,msg.getGoodsid());
				StallsItem 	yuanbao = getstallsitem(goodsinfo,-2);	//元宝和金币放在链表最后
				StallsItem 	gold  = getstallsitem(goodsinfo,-1);
				if (gold!= null) {
					if(goodsinfo.remove(gold)){
						goodsinfo.add(gold);
					}
				}

				if (yuanbao != null) {
					if(goodsinfo.remove(yuanbao)){
						goodsinfo.add(yuanbao);
					}
				}
				
				if (newitem != null) {
					boolean isremove = goodsinfo.remove(newitem);
					if (isremove) {
						goodsinfo.add(msg.getPos(), newitem);
					}
				}
	

			}
		}

		saveStallsinfo(player.getId(),xpinof,false);
		refreshStallslist(player);
		stSendboothsdata(player,player.getId(),(byte) 4);
		stStallsLog(player.getId(),-2,stallsitem.getItem(),stallsitem.getPricegold(),stallsitem.getPriceyuanbao());	//数据库日志记录
//		if (num < 0){//如果新数量小于原数量，那么减少商品数量(返还给玩家减少的部分)
//			ResStallsAdjustPricesToGameMessage smsg = new ResStallsAdjustPricesToGameMessage();
//			smsg.setNum(msg.getNum());
//			smsg.setStallsplayerid(msg.getStallsplayerid());
//			smsg.setGoodsitem(iteString);
//			MessageUtil.send_to_game(player,smsg);	
//		}

	}
		

	
	/**摆摊搜索目标
	 * 
	 * @param player
	 * @param pname
	 * @param goodsname
	 * @param isyuanbaogold
	 */
	@SuppressWarnings("unchecked")
	public void stReqStallsSearchToWorldMessage(ReqStallsSearchToWorldMessage wmsg) {
		Player player = ManagerPool.playerManager.getPlayer(wmsg.getPlayerid());
		if (player == null) return;
		
		List<StallsBriefInfo> tmplist = getSameCountryStallslist(player);
		ResStallsSearchMessage msg  = new ResStallsSearchMessage();
		StallsBriefList newBriefList = new StallsBriefList();
		newBriefList.setIndexlittle(0);
		newBriefList.setIndexLarge(500);
		newBriefList.setType((byte) 5);
		newBriefList.setStallssnum((short)tmplist.size());
		msg.setStallsbrieflist(newBriefList);
		StallsBriefList stallsbrieflist = msg.getStallsbrieflist();
		List<StallsBriefInfo> stinfo = stallsbrieflist.getStallsbrieflist();

		byte isyuanbaogold = wmsg.getGoldyuanbao();
		String goodsname = wmsg.getGoodsname();
		String pname = wmsg.getPlayername();
		if (goodsname != null) {
			if (goodsname.equals("铜币")) {
				isyuanbaogold = 1;
			}else if(goodsname.equals("元宝")) {
				isyuanbaogold = 2;
			}
		}

		for (int i = 0; i < tmplist.size(); i++) {
			if (stinfo.size() > 200) {	//搜索结果只返回200条
				break;
			}
			if (isyuanbaogold == 0) {
				if ((goodsname!=null && !goodsname.equals("") ) && (pname!=null && !pname.equals("") ))  {
					Player xplayer = ManagerPool.playerManager.getPlayer(tmplist.get(i).getPlayerid());
					if (xplayer != null) {
						if(tmplist.get(i).getPlayername().contains(pname)){
							StallsInfoSave xpinof = getStallsinfolist(xplayer.getId());	//到摊位数据
							if (xpinof != null) {
								List<StallsItem> items = xpinof.getStallsgoodsinfo();
								for (int j = 0; j < items.size(); j++) {
									if(items.get(j).getItem().getItemModelId()> 0){
										Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(items.get(j).getItem().getItemModelId());
										if(q_itemBean.getQ_name().contains(goodsname)){
											stinfo.add(tmplist.get(i));
											break;
										}
									}
								}
							}
						}
					}
				}else if (pname!=null && !pname.equals("")) {
					if(tmplist.get(i).getPlayername().contains(pname)){
						stinfo.add(tmplist.get(i));
					}
				}else if (goodsname!=null && !goodsname.equals(""))  {
					Player xplayer = ManagerPool.playerManager.getPlayer(tmplist.get(i).getPlayerid());
					if (xplayer != null) {
						StallsInfoSave xpinof = getStallsinfolist(xplayer.getId());	//得到摊位数据
						List<StallsItem> items = xpinof.getStallsgoodsinfo();
						for (int j = 0; j < items.size(); j++) {
							if(items.get(j).getItem().getItemModelId()> 0){
								Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(items.get(j).getItem().getItemModelId());
								if(q_itemBean.getQ_name().contains(goodsname)){
									stinfo.add(tmplist.get(i));
									break;
								}
							}
						}
					}
				}else {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("请至少输入一项搜索关键字后再搜索。"));
					return;
				}
			}else if (isyuanbaogold == 1 ) {		//0不搜索，1铜币，2元宝，3 两个都搜索
				if(tmplist.get(i).getSellgold() > 0) {
					stinfo.add(tmplist.get(i));
				}
			}else if(isyuanbaogold == 2) {
				if(tmplist.get(i).getSellyuanbao()> 0) {
					stinfo.add(tmplist.get(i));
				}
			}else if(isyuanbaogold == 3){
				if(tmplist.get(i).getSellyuanbao()> 0 || tmplist.get(i).getSellgold() > 0) {
					stinfo.add(tmplist.get(i));
				}
			}
		}
		
		if (stinfo.size() > 0) {
			if (stinfo.size() > 1) {
				Collections.sort(stinfo, new SearchSort() );	//排序
			}
			MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("摊位搜索完成。"));
		}else {
			if ((pname != null && !pname.equals("")) && (goodsname != null && !goodsname.equals(""))) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，暂时没有出售该关键字的货品或摊主。"));
			}else if (pname != null && !pname.equals("")) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，在线的摊主中暂时没有包含该关键字的摊主。"));
			}else if (goodsname != null && !goodsname.equals("")) {
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，暂时没有出售该关键字货品的摊位。"));
			}
		}
		MessageUtil.tell_player_message(player, msg);
		tmplist = null;
	}
	

	
	
	
	/**修改摊位名称
	 * 
	 * @param player
	 * @param stname
	 */

	public void stReqChangeStallsNameToWorldMessage(ReqChangeStallsNameToWorldMessage wmsg) {
		Player player = ManagerPool.playerManager.getPlayer(wmsg.getStallsplayerid());
		if (player == null) return;
		
		StallsInfoSave xpinof = getStallsinfolist(wmsg.getStallsplayerid());	//从人物身上得到摊位数据
		ResChangeStallsNameMessage msg = new ResChangeStallsNameMessage();
		boolean insert = false;
		if (xpinof == null ) {
			xpinof =  new StallsInfoSave();
			insert = true;
		}
		
		String stname = wmsg.getName();
		if (stname == null) {
			stname = "";
		}
		String dfname = player.getName()+ResManager.getInstance().getString("的摊位");
		if (stname.equals("") || stname.equals(dfname)) {
			msg.setName(dfname);
			stname = "";
			MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("摊位名『{1}』修改完成。"),dfname);
		}else {
			msg.setName(stname);
			MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("摊位名『{1}』修改完成。"),stname);
		}
		
		xpinof.setStallsname(stname);
		saveStallsinfo(player.getId(),xpinof,insert);
		refreshStallslist(player);

		msg.setStatus((byte) 0);
		MessageUtil.tell_player_message(player, msg);
	}
	
	
	//----------------数据库记录--------------------
	/**
	 * 摆摊日志
	 * @param stallsId
	 * @param buyId -1商品上架，-2商品调整，-3商品下架   XXXXXXXX表示买家
	 * @param item
	 * @param gold
	 * @param yuanbao
	 */
	 
	public void stStallsLog(long stallsId,long buyId ,Item item,int gold,int yuanbao) {
		Q_itemBean model= DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
//		if(model==null||model.getQ_log()!=1){
//			return;
//		}
		StallsWorldLog log = new StallsWorldLog();
	
		log.setStallsId(stallsId);

		log.setGoodsNum(item.getNum());
		log.setGoodsModid(item.getItemModelId());
		if(item.getItemModelId() == -1) {
			log.setGoodsName("铜币");
			log.setGoodsInfo("铜币");
		}else if (item.getItemModelId() == -2) {
			log.setGoodsName("元宝");
			log.setGoodsInfo("元宝");
		}else {
			log.setGoodsName(model.getQ_name());
			log.setGoodsInfo(JSONserializable.toString(item));
		}
		log.setGoodsOnlyid(item.getId());
		log.setPricegold(gold);
		log.setPriceyuanbao(yuanbao);
		
		log.setBuyId(buyId);	//-1商品上架，-2商品调整，-3商品下架
		
		LogService.getInstance().execute(log);
	}
	
	
	

	
	
}
	

