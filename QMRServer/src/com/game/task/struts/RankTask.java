package com.game.task.struts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.data.bean.Q_task_rankBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.manager.RankManager;
import com.game.rank.structs.RankType;
import com.game.task.bean.RankTaskAttribute;
import com.game.task.bean.RankTaskInfo;
import com.game.task.log.RankTaskLog;
import com.game.task.manager.TaskManager;
import com.game.task.message.ResRankTaskChangeMessage;
import com.game.task.message.ResRankTaskFinshMessage;
import com.game.utils.MessageUtil;

/**
 * 军衔任务
 *
 * @author 杨鸿岚
 */
public class RankTask extends Task {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8555957435133075246L;
	
	private static final Logger logger = Logger.getLogger(RankTask.class);
	private int modelid = 0;
	//军衔本任务数据保存(杀怪，动作等)Key动作类型Value值
	private HashMap<String, Integer> rankActionSaveMap = new HashMap<String, Integer>();

	public int getModelid() {
		return modelid;
	}

	public void setModelid(int modelid) {
		this.modelid = modelid;
	}

	public HashMap<String, Integer> getRankActionSaveMap() {
		return rankActionSaveMap;
	}

	public void setRankActionSaveMap(HashMap<String, Integer> rankActionSaveMap) {
		this.rankActionSaveMap = rankActionSaveMap;
	}

	@Override
	public byte acqType() {
		return RANKTASK;
	}

	@Override
	public byte deliveryType() {
		return COMMIT_AUTO;
	}

	@Override
	public void giveUpTask() {
		//军衔任务不能放弃 
	}

	public void initTask(Player player, int modelId, boolean bosend) {
		this.modelid = modelId;
		this.owerId = player.getId();
//		Q_task_rankBean model = DataManager.getInstance().q_task_rankContainer.getMap().get(modelId);
		this.isFinshAction = false;
		player.getCurrentRankTasks().add(this);
		if (bosend) {
			changeTask();
		}
		player.setRankTaskCount(player.getRankTaskCount() + 1);
		player.setRankTaskTime(System.currentTimeMillis());
		try {
			RankTaskLog log = new RankTaskLog();
			log.setRoleId(player.getId());
			log.setUserId(player.getUserId());
			log.setStatus(1);
			log.setTaskmodelId(modelId);
			log.setTaskInfo(JSON.toJSONString(this));
			log.setRankpoint(0);
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void quickAllInitTask(Player player, int modelId, boolean bosend) {
		this.modelid = modelId;
		this.owerId = player.getId();
//		Q_task_rankBean model = DataManager.getInstance().q_task_rankContainer.getMap().get(modelId);
		this.isFinshAction = false;
//		player.getCurrentRankTasks().add(this);
		if (bosend) {
			//changeTask();
		}
		player.setRankTaskCount(player.getRankTaskCount() + 1);
		player.setRankTaskTime(System.currentTimeMillis());
		try {
			RankTaskLog log = new RankTaskLog();
			log.setRoleId(player.getId());
			log.setUserId(player.getUserId());
			log.setStatus(1);
			log.setTaskmodelId(modelId);
			log.setTaskInfo(JSON.toJSONString(this));
			log.setRankpoint(0);
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void finshTask(Player vPlayer) {
		if (logger.isDebugEnabled()) {
			logger.debug("finshTask() - start");
		}
		if (vPlayer == null) {
			vPlayer = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		}
		if (vPlayer == null) {
			return;
		}
		if (vPlayer.getId() != getOwerId()) {
			return;
		}
		vPlayer.getCurrentRankTasks().remove(this);
		vPlayer.getFinishedRankTasks().add(getModelid());
		ResRankTaskFinshMessage sendMessage = new ResRankTaskFinshMessage();
		sendMessage.setModelId(getModelid());
		sendMessage.setFinshType(0);
		MessageUtil.tell_player_message(vPlayer, sendMessage);
		try {
			dealResume();
			dealRewards();
		} catch (Exception e) {
			logger.error(e, e);
		}

		Q_task_rankBean model = DataManager.getInstance().q_task_rankContainer.getMap().get(getModelid());
		if (model == null) {
			logger.error(getModelid() + "军衔任务模型找不着", new NullPointerException());
			return;
		}
		try {
			RankTaskLog log = new RankTaskLog();
			log.setRoleId(vPlayer.getId());
			log.setUserId(vPlayer.getUserId());
			log.setStatus(2);
			log.setTaskmodelId(getModelid());
			log.setTaskInfo(JSON.toJSONString(this));
			log.setRankpoint(model.getQ_rewards_rank());
			log.setSid(vPlayer.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		int q_next_task = model.getQ_next_task();
		if (q_next_task != 0) {
			TaskManager.getInstance().acceptRankTask(vPlayer, q_next_task, true);
		}
	}

	public void quickFinshTask(Player vPlayer) {
		if (logger.isDebugEnabled()) {
			logger.debug("quickFinshTask() - start");
		}
		if (vPlayer == null) {
			vPlayer = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		}
		if (vPlayer == null) {
			return;
		}
		if (vPlayer.getId() != getOwerId()) {
			return;
		}
		vPlayer.getCurrentRankTasks().remove(this);
		vPlayer.getFinishedRankTasks().add(getModelid());
		ResRankTaskFinshMessage sendMessage = new ResRankTaskFinshMessage();
		sendMessage.setModelId(getModelid());
		sendMessage.setFinshType(1);
		MessageUtil.tell_player_message(vPlayer, sendMessage);
		try {
			dealResume();
			dealRewards();
		} catch (Exception e) {
			logger.error(e, e);
		}

		Q_task_rankBean model = DataManager.getInstance().q_task_rankContainer.getMap().get(getModelid());
		if (model == null) {
			logger.error(getModelid() + "军衔任务模型找不着", new NullPointerException());
			return;
		}
		try {
			RankTaskLog log = new RankTaskLog();
			log.setRoleId(vPlayer.getId());
			log.setUserId(vPlayer.getUserId());
			log.setStatus(3);
			log.setTaskmodelId(getModelid());
			log.setTaskInfo(JSON.toJSONString(this));
			log.setRankpoint(model.getQ_rewards_rank());
			log.setSid(vPlayer.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		int q_next_task = model.getQ_next_task();
		if (q_next_task != 0) {
			TaskManager.getInstance().acceptRankTask(vPlayer, q_next_task, true);
		}
	}
	
	public void quickAllFinshTask(Player vPlayer) {
		if (logger.isDebugEnabled()) {
			logger.debug("quickFinshTask() - start");
		}
		if (vPlayer == null) {
			vPlayer = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		}
		if (vPlayer == null) {
			return;
		}
		if (vPlayer.getId() != getOwerId()) {
			return;
		}
		vPlayer.getCurrentRankTasks().remove(this);
		vPlayer.getFinishedRankTasks().add(getModelid());
//		ResRankTaskFinshMessage sendMessage = new ResRankTaskFinshMessage();
//		sendMessage.setModelId(getModelid());
//		sendMessage.setFinshType(1);
//		MessageUtil.tell_player_message(vPlayer, sendMessage);
		try {
			dealResume();
			dealRewards();
		} catch (Exception e) {
			logger.error(e, e);
		}
		try {
			Q_task_rankBean model = DataManager.getInstance().q_task_rankContainer.getMap().get(getModelid());
			RankTaskLog log = new RankTaskLog();
			log.setRoleId(vPlayer.getId());
			log.setUserId(vPlayer.getUserId());
			log.setStatus(3);
			log.setTaskmodelId(getModelid());
			log.setTaskInfo(JSON.toJSONString(this));
			log.setRankpoint(model.getQ_rewards_rank());
			log.setSid(vPlayer.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void changeTask() {
		Player player = PlayerManager.getInstance().getPlayer(getOwerId());
		if (player != null) {
			ResRankTaskChangeMessage sendMessage = new ResRankTaskChangeMessage();
			sendMessage.setTaskInfo(buildTaskInfo());
			MessageUtil.tell_player_message(player, sendMessage);
		}
	}

	@Override
	protected void dealResume() {
		//军衔任务基本不用扣除任务物品
	}

	@Override
	protected void dealRewards() {
		Player player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		if (player != null) {
			Q_task_rankBean taskModel = DataManager.getInstance().q_task_rankContainer.getMap().get(getModelid());
			if (taskModel == null) {
				logger.error(getModelid() + "军衔任务模型找不着", new NullPointerException());
				return;
			}
			if (taskModel.getQ_rewards_rank() != 0) {
				RankManager.getInstance().addranknum(player, taskModel.getQ_rewards_rank(), RankType.TASK);
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("获得军衔任务军功奖励:{1}"), Integer.toString(taskModel.getQ_rewards_rank()));
			}
		}
	}

	@Override
	public RankTaskInfo buildTaskInfo() {
		RankTaskInfo info = new RankTaskInfo();
		info.setModelId(getModelid());
		HashMap<Integer, Integer> endNeedCondition = endNeedCondition();
		Iterator<Integer> iterator = endNeedCondition.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			String keystr = Integer.toString(key);
			if (getRankActionSaveMap().containsKey(keystr)) {
				int value = getRankActionSaveMap().get(keystr);
				RankTaskAttribute rankTaskAttribute = new RankTaskAttribute();
				rankTaskAttribute.setType(key);
				rankTaskAttribute.setNum(value);
				info.getPermiseGoods().add(rankTaskAttribute);
			}
		}
		return info;
	}

	@Override
	public HashMap<Integer, Integer> endNeedKillMonster() {
		return new HashMap<Integer, Integer>();
	}

	@Override
	public HashSet<Integer> endNeedAchieve() {
		return new HashSet<Integer>();
	}

	@Override
	public HashMap<Integer, Integer> endNeedGoods() {
		return new HashMap<Integer, Integer>();
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

	public HashMap<Integer, Integer> endNeedCondition() {
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
		try {
			Q_task_rankBean model = DataManager.getInstance().q_task_rankContainer.getMap().get(getModelid());
			if (model == null) {
				logger.error(getModelid() + "军衔任务模型找不着", new NullPointerException());
				return result;
			}
			List<Integer[]> parseList = JSON.parseArray(model.getQ_condition(), Integer[].class);
			if (parseList.isEmpty()) {
				logger.error(getModelid() + "军衔任务模型条件数据解析出错", new NullPointerException());
				return result;
			}
			ListIterator<Integer[]> listIterator = parseList.listIterator();
			while (listIterator.hasNext()) {
				Integer[] integers = listIterator.next();
				if (integers.length < 2) {
					logger.error(getModelid() + "军衔任务模型条件数据解析出错", new NullPointerException());
					return result;
				}
				result.put(integers[0], integers[1]);
				if (integers.length == 3) {
					result.put(integers[1], integers[2]);
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return result;
	}

	/**
	 * 军衔任务事件处理
	 *
	 * @param actionType 事件类型
	 * @param num 数量
	 * @return boolean
	 */
	public boolean action(int actionType, int model, int num) {
		try {
			HashMap<Integer, Integer> endNeedCondition = endNeedCondition();
			if (endNeedCondition.containsKey(actionType)) {
				if (actionType == RankTaskEnum.USEITEM || actionType == RankTaskEnum.GETITEM) {
					num = Math.abs(num);
					Integer needitem = endNeedCondition.get(actionType);
					if (model == needitem && endNeedCondition.containsKey(needitem)) {
						Integer neednum = endNeedCondition.get(needitem);
						Integer nownum = getRankActionSaveMap().get(String.valueOf(actionType));
						nownum = nownum == null ? 0 : nownum;
						if (nownum < neednum) {
							nownum += num;
							if (nownum > neednum) {
								nownum = neednum;
							}
							getRankActionSaveMap().put(String.valueOf(actionType), nownum);
						}
						return true;
					} else {
						return false;
					}
				} else if (actionType == RankTaskEnum.INCENSELIP) {
					Integer neednum = endNeedCondition.get(actionType);
					Integer nownum = getRankActionSaveMap().get(String.valueOf(actionType));
					nownum = nownum == null ? 0 : nownum;
					if (nownum < neednum) {
						nownum = num;
						if (nownum > neednum) {
							nownum = neednum;
						}
						getRankActionSaveMap().put(String.valueOf(actionType), nownum);
					}
					return true;
				} else {
					Integer neednum = endNeedCondition.get(actionType);
					Integer nownum = getRankActionSaveMap().get(String.valueOf(actionType));
					nownum = nownum == null ? 0 : nownum;
					if (nownum < neednum) {
						nownum += num;
						if (nownum > neednum) {
							nownum = neednum;
						}
						getRankActionSaveMap().put(String.valueOf(actionType), nownum);
					}
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e, e);
			return false;
		}
	}

	/**
	 * 检查是否结束
	 *
	 * @param isPromp	是否发送提示到前端
	 * @param player
	 * @return
	 */
	@Override
	public boolean checkFinsh(boolean isPromp, Player player) {
		if (player == null) {
			player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		}
		if (player == null) {
			//角色以经离线
			return false;
		}
		if (player.getId() != getOwerId()) {
			return false;
		}

		try {
			HashMap<Integer, Integer> endNeedCondition = endNeedCondition();
			if (endNeedCondition.size() > 0) {
				Set<Integer> keySet = endNeedCondition.keySet();
				for (Integer keyInteger : keySet) {
					Integer neednum = endNeedCondition.get(keyInteger);
					Integer nownum = getRankActionSaveMap().get(String.valueOf(keyInteger));
					if (nownum == null || nownum < neednum) {
						if (isPromp) {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,任务条件未达成"));
						}
						return false;
					}
				}
			}
//			if (!isFinshAction()) {
//				if (isPromp) {
//					MessageUtil.notify_player(player, Notifys.ERROR, "很抱歉,任务条件未达成");
//				}
//				return false;
//			}
		} catch (Exception e) {
			logger.error("配置错误", e);
			return false;
		}
		return true;
	}
}
