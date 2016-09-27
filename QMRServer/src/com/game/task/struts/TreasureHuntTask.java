package com.game.task.struts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.data.bean.Q_task_explorepalaceBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.AttributeChangeReason;
import com.game.structs.Reasons;
import com.game.task.bean.TaskAttribute;
import com.game.task.bean.TreasureHuntTaskInfo;
import com.game.task.log.TreasureHuntTaskFinshLog;
import com.game.task.manager.TaskManager;
import com.game.task.message.ResConquerTaskChangeMessage;
import com.game.task.message.ResTaskFinshMessage;
import com.game.task.message.ResTreasureHuntTaskChangeMessage;
import com.game.utils.MessageUtil;
import com.game.utils.Symbol;

/**
 * 寻宝任务 
 * @author 赵聪慧
 * @2012-9-5 下午10:37:49
 */
public class TreasureHuntTask extends Task {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TreasureHuntTask.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -7500733270837382193L;
	private int modelId;
	@Override
	public HashMap<Integer, Integer> endNeedKillMonster() {
		HashMap<Integer,Integer> result=new HashMap<Integer, Integer>();
		Q_task_explorepalaceBean model = DataManager.getInstance().q_task_explorepalaceContainer.getMap().get(getModelId());
//		Q_task_conquerBean model = DataManager.getInstance().q_task_conquerContainer.getMap().get(getModelId());
		if(model==null){
			logger.error(getModelId()+"寻宝任务模型找不着",new NullPointerException());
			return result;
		}
		String q_end_need_killmonster = model.getQ_end_needkillmonster();
		
		if(q_end_need_killmonster!=null&&!"".equals(q_end_need_killmonster)){
			String substring = q_end_need_killmonster;
			if(substring.startsWith("@")){
				substring=substring.replace("@","");
			}
			String[] split = substring.split(Symbol.FENHAO_REG);
			for (String string : split) {
				String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
				int parseInt = Integer.parseInt(split2[0]);
				int parseInt2 = Integer.parseInt(split2[1]);
				result.put(parseInt, parseInt2);
			}
		}
		return result;
	}

	@Override
	public HashSet<Integer> endNeedAchieve() {
		Q_task_explorepalaceBean model = DataManager.getInstance().q_task_explorepalaceContainer.getMap().get(getModelId());
		HashSet<Integer> result=new HashSet<Integer>();
		if(model==null){
			logger.error(getModelId()+"寻宝任务模型找不着",new Exception());
			return null;
		}
		return result;
	}

	@Override
	public HashMap<Integer, Integer> endNeedGoods() {
		Q_task_explorepalaceBean model = DataManager.getInstance().q_task_explorepalaceContainer.getMap().get(getModelId());
		HashMap<Integer,Integer> result=new HashMap<Integer, Integer>();
		if(model==null){
			logger.error(getModelId()+"寻宝任务模型找不着",new Exception());
			return result;
		}
//		model.getq
//		String q_end_need_goods = model.GETQ_;
//		if(q_end_need_goods!=null&&!"".equals(q_end_need_goods)){
//			String substring = q_end_need_goods;
//			if(substring.startsWith("@")){
//				substring=substring.replace("@","");
//			}
//			String[] split = substring.split(Symbol.FENHAO_REG);
//			for (String string : split) {
//				if(StringUtils.isBlank(string)){
//					continue;
//				}
//				String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
//				int parseInt = Integer.parseInt(split2[0]);
//				int parseInt2 = Integer.parseInt(split2[1]);
//				result.put(parseInt,parseInt2);
//			}
//		}
		return result;
	}

	@Override
	protected void dealResume() {
		
	}

	@Override
	protected void dealRewards() {
		Q_task_explorepalaceBean model = DataManager.getInstance().q_task_explorepalaceContainer.getMap().get(getModelId());
		if(model==null){
			logger.error(getModelId()+"寻宝任务模型找不着",new Exception());
			return ;
		}
		long action = Config.getId();
		Player player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		int q_rewards_bindyuanbao = model.getQ_rewards_bindyuanbao();
		if(q_rewards_bindyuanbao!=0){
			BackpackManager.getInstance().changeBindGold(player, q_rewards_bindyuanbao, Reasons.TASKREWARDS, action);	
		}
		int q_rewards_coin = model.getQ_rewards_coin();
		if(q_rewards_coin!=0){
			BackpackManager.getInstance().changeMoney(player, q_rewards_coin,Reasons.TASKREWARDS, action);
		}
		
		int q_rewards_exp = model.getQ_rewards_exp();
		if(q_rewards_exp!=0){
			PlayerManager.getInstance().addExp(player, q_rewards_exp, AttributeChangeReason.TASKREWARDS);
		}
		int q_rewards_prestige = model.getQ_rewards_prestige();
		if(q_rewards_prestige!=0){
			PlayerManager.getInstance().addBattleExp(player, q_rewards_prestige, AttributeChangeReason.TASKREWARDS);	
		}
		
		int q_rewards_zq = model.getQ_rewards_zq();
		if(q_rewards_zq!=0){
			PlayerManager.getInstance().addZhenqi(player, q_rewards_zq,AttributeChangeReason.TASKREWARDS);
		}
	}

	@Override
	public byte acqType() {
		return TREASUREHUNTTASK;
	}

	@Override
	public byte deliveryType() {
		return COMMIT_OTHER;
	}

	@Override
	public void giveUpTask() {
		Player player=PlayerManager.getInstance().getPlayer(getOwerId());
		player.getCurrentTreasureHuntTasks().remove(this);
	}

	@Override
	public void finshTask(Player player) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("finshTask() - start");
		}
		if(player==null)
		player=PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		if(player==null){
			return;
		}
		player.getCurrentTreasureHuntTasks().remove(this);
		String beforeReceiveAble = JSONserializable.toString(player.getTaskRewardsReceiveAble());
		ResTaskFinshMessage msg=new ResTaskFinshMessage();
		msg.setConquerTadkId(getId());
		msg.setModelId(getModelId());
		msg.setType(Task.TREASUREHUNTTASK);
		MessageUtil.tell_player_message(player, msg);
		dealResume();
		dealRewards();
		TaskManager.getInstance().action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.COMPLETETREASUREhUNTTASK, 1);
		if (logger.isDebugEnabled()) {
			logger.debug("finshTask() - end");
		}
		try {
			TreasureHuntTaskFinshLog log=new TreasureHuntTaskFinshLog();
			log.setRoleId(player.getId());
			log.setTaskId(getId());
			log.setActionId(getId());
			log.setTaskInfo(JSONserializable.toString(this));
			log.setRewardsReceiveAble(JSONserializable.toString(player.getTaskRewardsReceiveAble()));
			log.setBeforeRewardsReceiveAble(beforeReceiveAble);
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e,e);
		}
		
		
	}

	@Override
	public void changeTask() {
		Player player = PlayerManager.getInstance().getPlayer(getOwerId());
		ResTreasureHuntTaskChangeMessage msg=new ResTreasureHuntTaskChangeMessage();
		msg.setTask(buildTaskInfo());
		MessageUtil.tell_player_message(player, msg);
	}

	@Override
	public TreasureHuntTaskInfo buildTaskInfo() {
		
		Player player=PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		TreasureHuntTaskInfo info=new TreasureHuntTaskInfo();
		info.setId(getId());
		info.setModelId(getModelId());
		Q_task_explorepalaceBean model = DataManager.getInstance().q_task_explorepalaceContainer.getMap().get(getModelId());
		if(model==null){
			logger.error(getModelId()+"寻宝任务模型找不着",new Exception());
			return info;
		}
		
		
		
		HashSet<Integer> endNeedAchieve = endNeedAchieve();
		for (Integer integer : endNeedAchieve) {
			//TODO 需要成就系统 
//			info.setPermiseAchieve(permiseAchieve);	
		}
		HashMap<Integer,Integer> endNeedGoods = endNeedGoods();
		Set<Integer> keySet = endNeedGoods.keySet();
		for (Integer key : keySet) {
			Integer need= endNeedGoods.get(key);
			int num = BackpackManager.getInstance().getItemNum(player,key);
			if(num>need){
				num=need;
			}
			TaskAttribute taskAttribute = new TaskAttribute();
			taskAttribute.setModel(key);
			taskAttribute.setNum(num);
			info.getPermiseGoods().add(taskAttribute);
		}
		HashMap<Integer,Integer> endNeedKillMonster = endNeedKillMonster();
		Set<Integer> killMonster = endNeedKillMonster.keySet();
		for (Integer key : killMonster) {
			Integer integer = killmonsters.get(String.valueOf(key));
			TaskAttribute taskAttribute = new TaskAttribute();
			taskAttribute.setModel(key);
			taskAttribute.setNum(integer == null ? 0 : integer);
			info.getPermiseMonster().add(taskAttribute);
		}
		info.setRewardsBindGold(model.getQ_rewards_bindyuanbao());
		info.setRewardsCopper(model.getQ_rewards_coin());
		info.setRewardsExp(model.getQ_rewards_exp());
		info.setRewardsSW(model.getQ_rewards_prestige());
		info.setRewardsZQ(model.getQ_rewards_zq());
		return info;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	@Override
	public int endNeedHorseLevel() {
		return 0;
	}

	@Override
	public int endNeedConquerTaskCount() {
		return 0;
	}

	@Override
	public int endNeedDailyTaskCount() {
		return 0;
	}

}
