package com.game.collect.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.collect.bean.CollectInfo;
import com.game.collect.bean.FragInfo;
import com.game.collect.log.CollectItemSubmitLog;
import com.game.collect.message.ResCollectInfoMessage;
import com.game.collect.struts.CollectItem;
import com.game.config.Config;
import com.game.data.bean.Q_collectBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_task_mainBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.utils.BeanUtil;
import com.game.utils.MessageUtil;

/**
 * 
 * @author 赵聪慧
 * @2012-11-26 上午11:44:45
 */
public class CollectManager {
	public static int NEEDTASKID=11710;//赴魏国
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CollectManager.class);

	private static CollectManager instance=new CollectManager();
	public static CollectManager getInstance() {
		return instance;
	}
	private CollectManager(){
		
	}
	
	public boolean isActivity(CollectItem item, Q_collectBean model) {
		HashMap<Integer, Integer> tmpMap = CollectModelManager.getInstance().getItemModels(model);
		if (tmpMap == null) return false;
		
		Iterator<Entry<Integer, Integer>> it = tmpMap.entrySet().iterator();;
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> entry = it.next();
			
			Integer num = item.getCollectCount().get(String.valueOf(entry.getKey()));
			if (num == null || num < entry.getValue()) return false;
		}	
		return true;
	}
	
	public boolean checkActivity(Player player,Q_collectBean model){
		HashMap<Integer,Integer> itemModels = CollectModelManager.getInstance().getItemModels(model);
		Set<Integer> keySet = itemModels.keySet();
		CollectItem collectItem = player.getCollect().getInfos().get(String.valueOf(model.getQ_coll_id()));
		for (Integer itemmodel : keySet) {
			if(collectItem!=null&&collectItem.getCollectCount().containsKey(String.valueOf(itemmodel))){
				int neednum = itemModels.get(itemmodel);	
				Integer count = collectItem.getCollectCount().get(String.valueOf(itemmodel));
				int nownum=(count==null?0:count);
				if(nownum>=neednum){
					continue;
				}
			}
			return false;
		}
		return true;
	}
	
	/**
	 * 发送藏品收集信息
	 * @param player
	 */
	public void sendCollectInfo(Player player,byte type){
		ResCollectInfoMessage msg=new ResCollectInfoMessage();
		msg.setType(type);
		List<Q_collectBean> list = DataManager.getInstance().q_collectContainer.getList();
		HashMap<String,CollectItem> infos = player.getCollect().getInfos();
		for (Q_collectBean q_collectBean : list) {
			CollectItem collectItem = infos.get(String.valueOf(q_collectBean.getQ_coll_id()));
			if(collectItem==null){
				collectItem=new CollectItem();
				collectItem.setModelId(q_collectBean.getQ_coll_id());
				infos.put(String.valueOf(q_collectBean.getQ_coll_id()), collectItem);
			}
			CollectInfo buildCollectInfo = buildCollectInfo(collectItem, player);
			msg.getCollectinfo().add(buildCollectInfo);
		}
		MessageUtil.tell_player_message(player, msg);
	}
	
	public CollectInfo buildCollectInfo(CollectItem bean, Player player) {
		CollectInfo info = new CollectInfo();
		try {
			Q_collectBean model = DataManager.getInstance().q_collectContainer.getMap().get(bean.getModelId());
			for (int i = 1; i <= 10; i++) {
				int itemmodel = (Integer) BeanUtil.getMethodValue(model, "Q_frag" + i + "_id");
				int num = (Integer) BeanUtil.getMethodValue(model, "Q_frag" + i + "_num");
				if(itemmodel!=0&&num!=0){
					CollectItem collectItem = player.getCollect().getInfos().get(String.valueOf(model.getQ_coll_id()));
					int count=0;
					if(collectItem!=null&&collectItem.getCollectCount().containsKey(String.valueOf(itemmodel))){
						count=collectItem.getCollectCount().get(String.valueOf(itemmodel));
					}					
//					long count = CountManager.getInstance().getCount(player, CountTypes.COLLECT_COUNT, String.valueOf(itemmodel));
					FragInfo fragInfo = new FragInfo();
					fragInfo.setModelid(itemmodel);
					fragInfo.setNeednum(num);
					fragInfo.setNum((int) count);
					info.getFragList().add(fragInfo);	
				}
			}
			info.setModelid(model.getQ_coll_id());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return info;
	}
	
	/**
	 * 提交碎片 物品
	 * 
	 * @param modelId
	 * @param num
	 */
	public void submitFrag(Player player,int modelId, int num) {
		int initNum = num;
		
		if (player.getCurrentMainTaskId() <= NEEDTASKID) {
			Q_task_mainBean taskmodel = DataManager.getInstance().q_task_mainContainer.getMap().get(NEEDTASKID);
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("宝藏系统未激活,需要完成主线任务{1}"),taskmodel.getQ_name());
			return;
		}

		Q_itemBean itemModel = DataManager.getInstance().q_itemContainer.getMap().get(modelId);
		if(itemModel==null||num<=0) {
			logger.info("submitFrag(Player, int, int) - 非法参数 - Player player=" + player.getId() + ",modelid=" + modelId+",num="+num);
			return;
		}
		long action = Config.getId();
		int itemNum = BackpackManager.getInstance().getItemNum(player, modelId);
		if(num > itemNum) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("物品{1}数量不足,需要{2},现有{3}"), itemModel.getQ_name(), num + "", itemNum + "");
			return;
		}
		
		List<Q_collectBean> list = DataManager.getInstance().q_collectContainer.getList();
		TreeSet<Q_collectBean> activeCollects = new TreeSet<Q_collectBean>();
		
		for (Q_collectBean model : list) {
			if (num < 1) break;
			HashMap<Integer,Integer> itemModels = CollectModelManager.getInstance().getItemModels(model);
			if(!itemModels.containsKey(modelId)) continue;

			CollectItem collectItem = player.getCollect().getInfos().get(String.valueOf(model.getQ_coll_id()));
			if(collectItem == null) {
				collectItem = new CollectItem();
				collectItem.setModelId(model.getQ_coll_id());
				player.getCollect().getInfos().put(String.valueOf(model.getQ_coll_id()), collectItem);
			}
			
			if (isActivity(collectItem, model)) continue;
			
			HashMap<Integer, Integer> tmpMap = CollectModelManager.getInstance().getItemModels(model);
			if (tmpMap == null) continue;
			Integer needTotal = tmpMap.get(modelId);
			if (needTotal == null || needTotal < 1) continue;
			
			Integer alreadyHave = collectItem.getCollectCount().get(String.valueOf(modelId));

			if (alreadyHave == null) {
				alreadyHave = new Integer(0);
				collectItem.getCollectCount().put(String.valueOf(modelId), alreadyHave);
			}
			
			if (alreadyHave >= needTotal) continue;
			
			Integer actuallyNeed = needTotal - alreadyHave;
			actuallyNeed = actuallyNeed > num ? num : actuallyNeed;

			if (BackpackManager.getInstance().removeItem(player, modelId, actuallyNeed, Reasons.COLLECT, action)) {
				num = num - actuallyNeed;
				collectItem.getCollectCount().put(String.valueOf(modelId), alreadyHave + actuallyNeed);
			}
			
			if (isActivity(collectItem, model)) activeCollects.add(model);
		}
		
		sendCollectInfo(player,(byte) 1);
		try{
			CollectItemSubmitLog log=new CollectItemSubmitLog();
			log.setActionid(action);
			log.setRoleid(player.getId());
			log.setItemmodel(modelId);
			log.setNum(num);
			log.setItems(modelId+"_"+num); 
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			logger.error(e,e);
		}
		
		String tipmsg="";
		if(initNum > num) {
			tipmsg += ResManager.getInstance().getString("您成功提交以下藏品碎片：");
			tipmsg += BackpackManager.getInstance().getName(modelId) + "*" + (initNum - num) +",";
			tipmsg =  tipmsg.substring(0,tipmsg.length()-1);
		}
		
		if(activeCollects.size() > 0) {
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.COLLECT);
			tipmsg+=ResManager.getInstance().getString("激活藏品：");
			for (Q_collectBean model : activeCollects) {
				tipmsg+=model.getQ_coll_name() + "、";
			}
			tipmsg = tipmsg.substring(0,tipmsg.length()-1);
		}
		if (tipmsg.length() > 0) {
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,tipmsg);
		}
		
	}
	
	/**
	 * 提交碎片
	 * 
	 * @param collectType 系列ID
	 */
	public void submitFrag(int collectType, Player player) {
		List<Q_collectBean> list = CollectModelManager.getInstance().getCollectModelByType(collectType);
		if(list==null){
			logger.debug("没有这个系列"+collectType);
			return;
		}
		if (player.getCurrentMainTaskId() <= NEEDTASKID) {
			Q_task_mainBean taskmodel = DataManager.getInstance().q_task_mainContainer.getMap().get(NEEDTASKID);
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("宝藏系统未激活,需要完成主线任务{1}"),taskmodel.getQ_name());
			return;
		}
		
		long action = Config.getId();
		
		TreeMap<Integer,Integer> expendItems = new TreeMap<Integer, Integer>();
		TreeSet<Q_collectBean> activeCollects = new TreeSet<Q_collectBean>();
		for (Q_collectBean model : list) {
			CollectItem collectItem = player.getCollect().getInfos().get(String.valueOf(model.getQ_coll_id()));
			if (collectItem == null) {
				collectItem = new CollectItem();
				collectItem.setModelId(model.getQ_coll_id());
				player.getCollect().getInfos().put(String.valueOf(model.getQ_coll_id()), collectItem);
			}
			
			if (isActivity(collectItem, model)) continue;
			
			if (active(expendItems, collectItem, model, player)) {
				activeCollects.add(model);
			}

		}
		sendCollectInfo(player,(byte) 2);
		String tipmsg="", logitems="";
		if(expendItems.size()>0){
			tipmsg+=ResManager.getInstance().getString("您成功提交以下藏品碎片：");
			Set<Integer> keySet = expendItems.keySet();
			for (Integer itemmodel : keySet) {
				Integer num = expendItems.get(itemmodel);
				if(num!=null&&num>0){
					tipmsg+=BackpackManager.getInstance().getName(itemmodel)+"*"+num+",";
					logitems += itemmodel+"_"+num+",";
				}
			}
			tipmsg=tipmsg.substring(0,tipmsg.length()-1);
		}
		try {
			//统一到 itemsubmitlog
			CollectItemSubmitLog log = new CollectItemSubmitLog();
			log.setActionid(action);
			log.setRoleid(player.getId());
			logitems = logitems.endsWith(",")? logitems.substring(0, logitems.length()-1): logitems ;
			log.setItems(logitems); //记录提交的道具id和数量
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e,e);
		}
		if(activeCollects.size() > 0) {
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.COLLECT);
			tipmsg+=ResManager.getInstance().getString("激活藏品：");
			for (Q_collectBean model : activeCollects) {
				tipmsg+=model.getQ_coll_name()+"、";
			}
			tipmsg=tipmsg.substring(0,tipmsg.length()-1);
		}
		if(tipmsg.length()>0){
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,tipmsg);
		}	
	}
	
	/**
	 * 激活典藏
	 * @param collectItem
	 * @param model
	 * @param player
	 * @param expendItems 存放成功提交的物品
	 * @return 是否激活成功
	 */
	private boolean active(TreeMap<Integer, Integer> expendItems, CollectItem collectItem, Q_collectBean model, Player player) {
		boolean ret = true;
		
		HashMap<Integer, Integer> tmpMap = CollectModelManager.getInstance().getItemModels(model);
		if (tmpMap == null) return false;
		
		Iterator<Entry<Integer, Integer>> it = tmpMap.entrySet().iterator();;
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> entry = it.next();
			if (entry.getValue() < 1) continue;
			
			int bagNum = BackpackManager.getInstance().getItemNum(player, entry.getKey());
			if (bagNum < 1) {
				ret = false;
				continue;
			}
			
			int alreadHave = 0;
			if (collectItem.getCollectCount().containsKey(String.valueOf(entry.getKey()))) {
				alreadHave = collectItem.getCollectCount().get(String.valueOf(entry.getKey()));
			}
			else {
				collectItem.getCollectCount().put(String.valueOf(entry.getKey()), alreadHave);
			}
			
			int needNum = entry.getValue() > alreadHave ? entry.getValue() - alreadHave : 0;
			if (needNum < 1) continue;
			
			if (needNum > bagNum) {
				ret = false;
				needNum = bagNum;
			}
			if (BackpackManager.getInstance().removeItem(player, entry.getKey(), needNum, Reasons.COLLECT, Config.getId())) {
				collectItem.getCollectCount().put(String.valueOf(entry.getKey()), alreadHave + needNum);
				if (expendItems.containsKey(entry.getKey())) {
					expendItems.put(entry.getKey(), expendItems.get(entry.getKey()) + needNum);
				}
				else {
					expendItems.put(entry.getKey(), needNum);
				}
			}
		}
		return ret;
	}
}