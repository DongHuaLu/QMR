package com.game.task.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.backpack.bean.ItemInfo;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.country.manager.CountryManager;
import com.game.data.bean.Q_monsterBean;
import com.game.data.bean.Q_npcBean;
import com.game.data.bean.Q_task_conquerBean;
import com.game.data.bean.Q_task_daily_condBean;
import com.game.data.bean.Q_task_daily_rewardsBean;
import com.game.data.bean.Q_task_explorepalaceBean;
import com.game.data.bean.Q_task_mainBean;
import com.game.data.bean.Q_task_rankBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.guildflag.manager.GuildFlagManager;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.npc.manager.NpcManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.LaterTask;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.rank.manager.RankManager;
import com.game.rank.structs.RankType;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.task.log.ConquerTaskAcceptLog;
import com.game.task.log.ConquerTaskDevourLog;
import com.game.task.log.DailyTaskAcceptLog;
import com.game.task.log.DailyTaskReducedDifficulty;
import com.game.task.log.DailyTaskUpAchieveLog;
import com.game.task.log.MainTaskLog;
import com.game.task.message.*;
import com.game.task.script.IMainTaskAcceptAction;
import com.game.task.struts.ConquerTask;
import com.game.task.struts.DailyTask;
import com.game.task.struts.LaterRestTask;
import com.game.task.struts.MainTask;
import com.game.task.struts.MonsterModelComparator;
import com.game.task.struts.RankTask;
import com.game.task.struts.RankTaskEnum;
import com.game.task.struts.Task;
import com.game.task.struts.TreasureHuntTask;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.ServerParamUtil;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

/**
 * 任务管理器
 * @author 赵聪慧
 *
 */
public class TaskManager {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TaskManager.class);
	//每日可接讨伐任务数
	public static int CONQUERTASK_DAYMAXACCEPT=10;
	//身上可有的最大讨伐任务数
	public static int CONQUERTASK_MAXNUM=20;
	//每日可吞噬的讨伐任务数
	public static int CONQUERTASK_DAYDEVOUR=5;
	//日常任务最小等级
//	private static int DAILYTASK_NEEDGRADE=30;
	//激活任务系统需要完成的主线任务
	public static int ACTIVITY_NEED_MAINTASK=10880;
	//日常任务每日可接数量
	private static int DAILYTASK_DAYMAXACCEPT=20;
	
	//人物出生默认学会的任务
	public final static int CREATEPLAYERDEFAULTTASK=10000;
	//100%吞噬所需元宝
	public static int devourFullNeedGold=10;
	
	private static TaskManager instance=new TaskManager();
	public static TaskManager getInstance(){
		return instance;
	}
	private TaskManager(){
		
	}
	
	/**
	 * 接取任务
	 * @param player	
	 * @param type		任务类型
	 * @param modelId	任务模型ID
	 */
	public void acceptMainTask(Player player,int modelId){
		Task taskByModelId = getTaskByModelId(player, Task.MAINTASK, modelId);
		if(taskByModelId!=null){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("该任务不可重复接取"));
			return;
		}
		if(player.getCurrentMainTasks().size()>0){
			//身上有任务 
			return;
		}
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelId);
		if(model==null){
			//找不到的任务模型
			return;
		}
		MainTask task = new MainTask();
		task.initTask(player, modelId);
		try{
			IMainTaskAcceptAction acceptAction=(IMainTaskAcceptAction) ScriptManager.getInstance().getScript(ScriptEnum.TASK_ACCEPTAFTER);
			if(acceptAction==null){
				logger.info("接受主线任务脚本找不到");
			}else{
				acceptAction.acceptMainTaskAfter(player, task);
			}
		}catch (Exception e) {
			logger.error(e,e);
		}
		logger.debug("接收主线任务"+modelId);
	}
	
	/**
	 * 接取军衔任务
	 * @param player
	 * @param modelId
	 */
	public void acceptRankTask(Player player,int modelId, boolean bosend){
		Task taskByModelId = getTaskByModelId(player, Task.RANKTASK, modelId);
		if(taskByModelId!=null){
			//MessageUtil.notify_player(player, Notifys.ERROR, "该军衔任务不可重复接取");
			return;
		}
		Q_task_rankBean model = DataManager.getInstance().q_task_rankContainer.getMap().get(modelId);
		if(model==null){
			//找不到的任务模型
			return;
		}
		if (player.getCurrentMainTaskId() < 10980){
			//没有完成对应主线任务，不能接取
			return;
		}
		if (player.getLevel() < model.getQ_show_level()) {
			//等级不够，不能接取
			return;
		}
		if (player.getFinishedRankTasks().contains(model.getQ_id())) {
			//已经完成的任务，不能接取
			return;
		}
		if (model.getQ_front_task()!=0) {
			if (!player.getFinishedRankTasks().contains(model.getQ_front_task())) {
				//前置任务没有完成，不能接取
				return;
			}
		}
		if (model.getQ_type()!=0) {
			if (model.getQ_type() == 1) {//王城
				if (!CountryManager.getInstance().getstrtimeinfo(0).startsWith(ResManager.getInstance().getString("今日"))) {
					//今天没有王城战，不能接取
					return;
				}
			}else if (model.getQ_type() == 2){//领地
				if (!GuildFlagManager.getInstance().getFlagWarstrTime().startsWith(ResManager.getInstance().getString("今日"))) {
					//今天没有领地战，不能接取
					return;
				}
			}
		}
		RankTask task = new RankTask();
		task.initTask(player, modelId, bosend);
		logger.debug("接收军衔任务"+modelId);
	}
	
	public void acceptDailyTask(Player player){
		if(!player.isActivityDailyTask()){
			return;
		}
		if(player.getCurrentDailyTasks().size()>0){
			return;
		}
		if(player.getLaterList().size()>0){
			if(player.getLaterList().size()==1&&player.getLaterList().get(0).getRun() instanceof LaterRestTask){
				
			}else{
				return;	
			}
		}
		//同一天 接取的次数达到上限
		if(TimeUtil.isSameDay(player.getDailyTaskTime(),System.currentTimeMillis())&&player.getDailyTaskCount()>=DAILYTASK_DAYMAXACCEPT){
			return;
		}
		DailyTask task=new DailyTask();
		task.initTask(player);
		int dailyTaskCount = player.getDailyTaskCount();
		player.setDailyTaskCount(dailyTaskCount+1);
		player.setDailyTaskTime(System.currentTimeMillis());
		player.getCurrentDailyTasks().add(task);
		task.changeTask();
		if(task.checkFinsh(false, player)){
			task.finshTask(player);
		}
		try {
			DailyTaskAcceptLog log=new DailyTaskAcceptLog();
			log.setCondmodelId(task.getCondid());
			log.setRewardsmodelId(task.getRewardId());
			log.setRoleId(player.getId());
			log.setTaskcount(dailyTaskCount);
			log.setTaskId(task.getId());
			log.setSid(player.getCreateServerId());
			log.setTaskInfo(JSONserializable.toString(task));
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e,e);
		}	
//		if(task.checkFinsh(false,player)){
//			task.finshTask(player);
//		}
	}
	
	public boolean acceptConquerTask(Player player,int goodModel) {		
		ConquerTask task = new ConquerTask();
		task.initTask(player, goodModel);

		Collections.sort(player.getCurrentConquerTasks(),new Comparator<ConquerTask>() {
			@Override
			public int compare(ConquerTask o1, ConquerTask o2) {
				int model1 = o1.getModelid();
				int model2 = o2.getModelid();
				Q_task_conquerBean task1model = DataManager.getInstance().q_task_conquerContainer.getMap().get(model1);
				Q_task_conquerBean task2model = DataManager.getInstance().q_task_conquerContainer.getMap().get(model2);
				if(task1model!=null&&task2model!=null){
					String monsterExp1 = task1model.getQ_monstercount();
					String monsterExp2 = task2model.getQ_monstercount();
					if(StringUtils.isNotBlank(monsterExp1)&&StringUtil.isNotBlank(monsterExp2)){
						monsterExp1=monsterExp1.replace("@","");
						monsterExp2=monsterExp2.replace("@","");
						String[] split1 = monsterExp1.split(Symbol.FENHAO_REG);
						String[] split2 = monsterExp2.split(Symbol.FENHAO_REG);
						List<Integer> model1list=new ArrayList<Integer>();
						for (String string : split1) {
							String[] split = string.split(Symbol.XIAHUAXIAN_REG);
							if(StringUtil.isNumeric(split[0])){
								model1list.add(Integer.parseInt(split[0]));
							}
						}
						List<Integer> model2list=new ArrayList<Integer>();
						for (String string : split2) {
							String[] split = string.split(Symbol.XIAHUAXIAN_REG);
							if(StringUtil.isNumeric(split[0])){
								model2list.add(Integer.parseInt(split[0]));
							}
						}
						if (model1list.size() > 0 && model2list.size() > 0) {
							Integer monstermodel1 = Collections.max(model1list, new MonsterModelComparator());
							Integer monstermodel2 = Collections.max(model2list, new MonsterModelComparator());
							Q_monsterBean monster1 = DataManager.getInstance().q_monsterContainer.getMap().get(monstermodel1);
							Q_monsterBean monster2 = DataManager.getInstance().q_monsterContainer.getMap().get(monstermodel2);
							if (monster1 != null && monster2 != null) {
								// 先按目标怪物等级排序
								if (monster1.getQ_grade() != monster2.getQ_grade()) {
									return monster1.getQ_grade() - monster2.getQ_grade();
								}
								// 怪物等级一样的话按任务模型排序
								if (model1 != model2) {
									return model1 - model2;
								}
							} else {
								logger.error("严重错误角色身上有的任务找不到任务模型模型ID1:" + model1 + "_ID2:" + model2);
							}
						}else{
							logger.error("击杀目标配置错误任务 ID1="+task1model+"表达式"+monsterExp1+" ID1="+task2model+"表达式"+monsterExp2);
						}
					}	
				}else{
					logger.error("严重错误角色身上有的任务找不到任务模型模型ID1:"+model1+"_ID2:"+model2);
				}
				//模型和等级都相同按接取时间排
				if(o1.getAcceptTime()!=o2.getAcceptTime()){
					return (int)(o1.getAcceptTime()-o2.getAcceptTime());
				}
				return (int)(o1.getId()-o2.getId());
			}
		});
	
		ResConquerTaskListUpdateMessage msg=new ResConquerTaskListUpdateMessage();
		msg.setConquerTaskAcceptCount(player.getConquerTaskCount());
		msg.setConquerTaskAcceptMaxCount(TaskManager.CONQUERTASK_DAYMAXACCEPT + player.getConquerTaskMaxCount());
		msg.setDevourCount(player.getDaydevourcount());
		//重排
		List<ConquerTask> currentConquerTasks = player.getCurrentConquerTasks();
		
		for (ConquerTask conquerTask : currentConquerTasks) {
			msg.getTask().add(conquerTask.buildTaskInfo());
			
		}
//		if(task.endNeedKillMonster().size()>0){
//			for (Integer monsterModelId : task.endNeedKillMonster().keySet()) {
//				Q_monsterBean monsterModel = DataManager.getInstance().q_monsterContainer.getMap().get(monsterModelId);	
//				if(monsterModel!=null&&monsterModel.getQ_info_sync()!=0){
//					ResTargetMonsterChangeMessage resMsg=new ResTargetMonsterChangeMessage();
//					TargetMonsterInfo targetMonsterInfo = MonsterStateManager.getInstance().getMonsterInfo().get(player.getCreateServerId()+"_"+monsterModelId);
//					if(targetMonsterInfo==null){
//						int publicserver= WServer.getGameConfig().getServerByCountry(ServerType.PUBLIC.getValue());
//						targetMonsterInfo = MonsterStateManager.getInstance().getMonsterInfo().get(publicserver+"_"+monsterModelId);
//					}
////					TargetMonsterInfo targetMonsterInfo = MonsterStateManager.getInstance().getMonsterInfo().get(monsterModelId);
//					if(targetMonsterInfo==null){
//						targetMonsterInfo=new TargetMonsterInfo();
//						targetMonsterInfo.setHp(0);
//						targetMonsterInfo.setKiller("");
//						targetMonsterInfo.setModelId(monsterModelId);
//						targetMonsterInfo.setState((byte)0);
//						long reviveTime = System.currentTimeMillis() + monsterModel.getQ_revive_time() * 1000;
//						if (monsterModel.getQ_refreshtime() != null && !monsterModel.getQ_refreshtime().equals("")) {
//							if (!TimeUtil.isSatisfiedBy(monsterModel.getQ_refreshtime(), new Date(reviveTime))) {
//								targetMonsterInfo.setReliveTime((int) (TimeUtil.getNextValidTime(monsterModel.getQ_refreshtime()).getTime()/1000));
//								
//							} else {
//								targetMonsterInfo.setReliveTime((int) (reviveTime/1000));
//							}
//						} else {
//							targetMonsterInfo.setReliveTime((int) (reviveTime/1000));
//						}
//						
//					}
//					resMsg.setMonsterInfo(targetMonsterInfo);
//					MessageUtil.tell_player_message(player, resMsg);
//				}
//			}	
//		}
		//发送重排消息 
		MessageUtil.tell_player_message(player, msg);
		
		loginMonsterInfo(player);
		logger.debug("接收讨伐任务");
		try{
			ConquerTaskAcceptLog log=new ConquerTaskAcceptLog();
			log.setRoleId(player.getId());
			log.setTaskcount(player.getConquerTaskCount());
			log.setTaskId(task.getId());
			log.setTaskmodelId(task.getModelid());
			log.setTaskInfo(JSONserializable.toString(task));
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);	
		}catch (Exception e) {
			logger.error(e,e);
		}
		return true;
	}

	/**
	 * 接取寻宝任务
	 * @param player
	 * @param modelId
	 */
	public void acceptTreasureHuntTask(Player player,int modelId){
		TreasureHuntTask task=new TreasureHuntTask();
		task.setModelId(modelId);
		task.setOwerId(player.getId());
		player.getCurrentTreasureHuntTasks().add(task);
		task.changeTask();
	}
	
	/**
	 * 放弃任务
	 * @param player
	 * @param type
	 * @param taskId
	 */
	public void giveUpTask(Player player,int type,long taskId){
		if(type==Task.TREASUREHUNTTASK){
			List<TreasureHuntTask> currentTreasureHuntTasks = player.getCurrentTreasureHuntTasks();
			TreasureHuntTask task=null;
			for (TreasureHuntTask treasureHuntTask : currentTreasureHuntTasks) {
				if(treasureHuntTask.getId()==taskId){
					task=treasureHuntTask;
				}
			}
			if(task==null){
				return;
			}
			player.getCurrentTreasureHuntTasks().remove(task);
			ResGiveUpTaskMessage msg=new ResGiveUpTaskMessage();
			msg.setType(Task.TREASUREHUNTTASK);
			msg.setTaskid(taskId);
			MessageUtil.tell_player_message(player, msg);
		}
	}
	
	
	/**
	 * 主线结束任务
	 * @param player
	 * @param taskId
	 * @param l 
	 * @return
	 */
	public boolean finshTask(Player player, int taskId) {
		Task task = getTaskByModelId(player, Task.MAINTASK, taskId);
		if (task == null) {
			logger.error("角色身上找不到请求的任务,角色 :" + player.getId() + "任务ID:" + taskId);
			return false;
		}
		MainTask maintask = (MainTask) task;
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(maintask.getModelid());
		if(model==null){
			return false;
		}
		
		//采集任务和读条任务，后端自动触发，不由前端触发
		if(model.getQ_task_type()==4 || model.getQ_task_type()==6){
			return false;
		}
		
		if (model.getQ_finsh_type() == Task.COMMIT_NPC) {
			int q_endnpc = model.getQ_endnpc();
			if (q_endnpc == 0) {
				logger.error("该任务需要NPC提交但未配置NPC模型任务ID=" + taskId);
				return false;
			}
			Q_npcBean q_npcBean = DataManager.getInstance().q_npcContainer.getMap().get(q_endnpc);
			if (q_npcBean == null) {
				logger.error("该任务需要NPC提交但找不到对应的NPC模型ID=" + q_endnpc);
				return false;
			}
			if (!NpcManager.checkFunction(q_endnpc, NpcManager.FUNCTION_TASK)) {
				logger.error("该NPC没有提交任务的功能");
				return false;
			}
			if (!NpcManager.checkDistance(q_endnpc, player.getPosition())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("距离NPC过远"));
				return false;
			}
		}
		if(!task.checkFinsh(true,player)){
			//检查不通过
			return false;
		}
		task.finshTask(player);
		return true;
	}
	
	private static Task getTaskByModelId(Player player,int type,int modelId){
		switch (type) {
		case Task.MAINTASK:
			List<MainTask> currentMainTasks = player.getCurrentMainTasks();
			for (MainTask mainTask : currentMainTasks) {
				if(mainTask.getModelid()==modelId){
					return mainTask;
				}
			}
			break;
		case Task.CONQUERTASK:
			List<ConquerTask> currentConquerTasks2 = player.getCurrentConquerTasks();
			for (ConquerTask conquerTask : currentConquerTasks2) {
				if(conquerTask.getModelid()==modelId){
					return conquerTask;
				}
			}
			break;
		case Task.DAILYTASK:
			List<DailyTask> currentDailyTasks = player.getCurrentDailyTasks();
			for (DailyTask dailyTask : currentDailyTasks) {
				if(dailyTask.getCondid()==modelId){
					return dailyTask;
				}
			}
			break;
		case Task.TREASUREHUNTTASK:
			List<TreasureHuntTask> currentTreasureHuntTasks = player.getCurrentTreasureHuntTasks();
			for (TreasureHuntTask treasureHuntTask : currentTreasureHuntTasks) {
				if(treasureHuntTask.getModelId()==modelId){
					return treasureHuntTask;
				}
			}
			break;
		case Task.RANKTASK:
			List<RankTask> currentRankTasks = player.getCurrentRankTasks();
			for (RankTask rankTask : currentRankTasks) {
				if (rankTask.getModelid() == modelId) {
					return rankTask;
				}
			}
			break;
		}
		return null;
	}
	
	/**
	 * 三种类型的任务ID都是唯一的
	 * @return
	 */
	public Task getTaskByModelId(Player player,int modelId){
		Task task= getTaskByModelId(player,Task.MAINTASK, modelId);
		if(task!=null){
			return task;
		}
		task=getTaskByModelId(player,Task.DAILYTASK, modelId);
		if(task!=null){
			return task;
		}
		task=getTaskByModelId(player,Task.CONQUERTASK, modelId);
		if(task!=null){
			return task;
		}
		task = getTaskByModelId(player,Task.TREASUREHUNTTASK, modelId);
		if(task!=null){
			return task;
		}
		task = getTaskByModelId(player,Task.RANKTASK, modelId);
		if(task!=null){
			return task;
		}
		return null;
	}
	
	/**
	 * 一键直接完成军衔任务
	 * @param player
	 * @param taskId
	 */
	public int quickFinishRankTask(Player player,int taskId){
		int ranktaskNum = 0;
		try {
			HashMap<String, List<Integer>> ranktaskMap = null;
			if (ServerParamUtil.getNormalParamMap().containsKey("RANKTASK" + "_" + WServer.getInstance().getServerId())) {
				String ranktaskString = ServerParamUtil.getNormalParamMap().get("RANKTASK" + "_" + WServer.getInstance().getServerId());
				ranktaskMap = JSON.parseObject(ranktaskString, HashMap.class);
			}
			if (ranktaskMap != null) {
				Calendar calendar = Calendar.getInstance();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				if (ranktaskMap.containsKey(date)) {
					List<Integer> ranktaskList = ranktaskMap.get(date);
					if (ranktaskList != null && !ranktaskList.isEmpty()) {
						ranktaskNum = ranktaskList.get(0);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			return 0;
		}
		if (player.getCurrentRankTasks().size()<=0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有任何军衔任务"));
			return 0;
		}
		RankTask rankTask = (RankTask) getTaskByModelId(player, Task.RANKTASK, taskId);
		if (rankTask == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有这个要快速完成的军衔任务"));
			return 0;
		}
		Q_task_rankBean rankBean = DataManager.getInstance().q_task_rankContainer.getMap().get(rankTask.getModelid());
		if (rankBean == null) {
			logger.error(rankTask.getModelid()+"==没有这个军衔任务数据");
			return 0;
		}
		if(!BackpackManager.getInstance().checkGold(player, rankBean.getQ_quickfinsh_gold())){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，需要{1}元宝才能快速完成日常任务，元宝不足"),rankBean.getQ_quickfinsh_gold()+"");
			return 0;
		}
		if (BackpackManager.getInstance().changeGold(player, -rankBean.getQ_quickfinsh_gold(), Reasons.RANK_DELGOLD,Config.getId())){
			int oldrankexp = player.getRankexp();
			rankTask.quickFinshTask(player);
			if (oldrankexp != player.getRankexp() && ranktaskNum != 0) {
				int addnum = rankBean.getQ_rewards_rank()*(ranktaskNum - 1);
				RankManager.getInstance().addranknum(player, addnum, RankType.TASK);
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("当期活动快速完成军衔任务获得额外军功奖励:{1}"), Integer.toString(addnum));
			}
			return rankBean.getQ_rewards_rank() * (ranktaskNum != 0 ? ranktaskNum : 1);
		}else{
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，扣除元宝失败，不能快速完成日常任务"));
			return 0;
		}
	}
	
	/**
	 * 一键直接完成所有军衔任务
	 * @param player
	 * @param taskId
	 */
	public void quickFinishAllRankTask(Player player){
		int ranktaskNum = 0;
		try {
			HashMap<String, List<Integer>> ranktaskMap = null;
			if (ServerParamUtil.getNormalParamMap().containsKey("RANKTASK" + "_" + WServer.getInstance().getServerId())) {
				String ranktaskString = ServerParamUtil.getNormalParamMap().get("RANKTASK" + "_" + WServer.getInstance().getServerId());
				ranktaskMap = JSON.parseObject(ranktaskString, HashMap.class);
			}
			if (ranktaskMap != null) {
				Calendar calendar = Calendar.getInstance();
				String date = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
				if (ranktaskMap.containsKey(date)) {
					List<Integer> ranktaskList = ranktaskMap.get(date);
					if (ranktaskList != null && !ranktaskList.isEmpty()) {
						ranktaskNum = ranktaskList.get(0);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("军衔任务奖励倍数数据错误"));
			return;
		}
		ResRankTaskQuickFinshAllMessage sendMessage = new ResRankTaskQuickFinshAllMessage();
		ListIterator<RankTask> listIterator = player.getCurrentRankTasks().listIterator(player.getCurrentRankTasks().size());
		while(listIterator.hasPrevious()) {
			RankTask rankTask =  listIterator.previous();
			if (rankTask != null) {
				int rankexp = 0;
				if (player.getCurrentRankTasks().size()<=0) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有任何军衔任务"));
					break;
				}
				if (rankTask == null) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有这个要快速完成的军衔任务"));
					break;
				}
				Q_task_rankBean rankBean = DataManager.getInstance().q_task_rankContainer.getMap().get(rankTask.getModelid());
				if (rankBean == null) {
					logger.error(rankTask.getModelid()+"==没有这个军衔任务数据");
					break;
				}
				if(!BackpackManager.getInstance().checkGold(player, rankBean.getQ_quickfinsh_gold())){
					MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，需要{1}元宝才能快速完成日常任务，元宝不足"),rankBean.getQ_quickfinsh_gold()+"");
					break;
				}
				if (BackpackManager.getInstance().changeGold(player, -rankBean.getQ_quickfinsh_gold(), Reasons.RANK_DELGOLD,Config.getId())){
					int oldrankexp = player.getRankexp();
					listIterator.remove();
					rankTask.quickAllFinshTask(player);
					int q_next_task = rankBean.getQ_next_task();
					if (q_next_task != 0) {
						boolean bogettask = true;
						Task taskByModelId = getTaskByModelId(player, Task.RANKTASK, q_next_task);
						if(taskByModelId!=null){
							//MessageUtil.notify_player(player, Notifys.ERROR, "该军衔任务不可重复接取");
							bogettask = false;
						}
						Q_task_rankBean model = DataManager.getInstance().q_task_rankContainer.getMap().get(q_next_task);
						if(model==null){
							//找不到的任务模型
							bogettask = false;
						}
						if (player.getCurrentMainTasks().isEmpty()) {
							//没有完成对应主线任务，不能接取
							bogettask = false;
						}
						if (player.getCurrentMainTasks().get(0).getModelid() < 11580){
							//没有完成对应主线任务，不能接取
							bogettask = false;
						}
						if (player.getLevel() < model.getQ_show_level()) {
							//等级不够，不能接取
							bogettask = false;
						}
						if (player.getFinishedRankTasks().contains(model.getQ_id())) {
							//已经完成的任务，不能接取
							bogettask = false;
						}
						if (model.getQ_front_task()!=0) {
							if (!player.getFinishedRankTasks().contains(model.getQ_front_task())) {
								//前置任务没有完成，不能接取
								bogettask = false;
							}
						}
						if (model.getQ_type()!=0) {
							if (model.getQ_type() == 1) {//王城
								if (!CountryManager.getInstance().getstrtimeinfo(0).startsWith(ResManager.getInstance().getString("今日"))) {
									//今天没有王城战，不能接取
									bogettask = false;
								}
							}else if (model.getQ_type() == 2){//领地
								if (!GuildFlagManager.getInstance().getFlagWarstrTime().startsWith(ResManager.getInstance().getString("今日"))) {
									//今天没有领地战，不能接取
									bogettask = false;
								}
							}
						}
						if (bogettask) {
							RankTask task = new RankTask();
							task.quickAllInitTask(player, q_next_task, true);
							listIterator.add(task);
							logger.debug("接收军衔任务"+q_next_task);
						}
					}
					if (oldrankexp != player.getRankexp() && ranktaskNum != 0) {
						int addnum = rankBean.getQ_rewards_rank()*(ranktaskNum - 1);
						RankManager.getInstance().addranknum(player, addnum, RankType.TASK);
						MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("当期活动快速完成军衔任务获得额外军功奖励:{1}"), Integer.toString(addnum));
					}
					rankexp = rankBean.getQ_rewards_rank() * (ranktaskNum != 0 ? ranktaskNum : 1);
				}else{
					MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，扣除元宝失败，不能快速完成日常任务"));
					break;
				}
				if (rankexp == 0) {
					break;
				} else {
					sendMessage.setAllrankexp(sendMessage.getAllrankexp() + rankexp);
				}
			}
		}
		if (sendMessage.getAllrankexp() != 0) {
			MessageUtil.tell_player_message(player, sendMessage);
			ParseUtil parseUtil = new ParseUtil();
			if (ranktaskNum > 0) {
				parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】使用一键军功功能，快速完成了军功任务并获得军功奖励加倍!{@}"), player.getName()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.RANK.getValue()));
			}else {
				parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】使用一键军功功能，快速完成了军功任务!{@}"), player.getName()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.RANK.getValue()));
			}
			MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.RANK.getValue());

		}
		sendRankTaskInfoList(player, false);
	}
	
	/**
	 * 一键直接完成环任务
	 * @param player
	 * @param taskId
	 * @param type 
	 */
	public void supperFinsh(Player player,int taskId, byte type){
		
		if(player.getCurrentDailyTasks().size()<=0){
			return;
		}
		final DailyTask nowTask = player.getCurrentDailyTasks().get(0);
				//getTaskByModelId(player,Task.DAILYTASK, taskId);
		if(nowTask==null){
			return;
		}
		if(!(nowTask instanceof DailyTask)){
			//只有日常任务可以快速完成
			return;
		}
		if(player.getDailyTaskCount()>20){
			return;
		}
		long action=Config.getId();
		if(type==0){
			int needgold=100;
			//快速完成
			if(!BackpackManager.getInstance().checkGold(player, needgold)){
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，需要{1}元宝才能快速完成日常任务，元宝不足"),needgold+"");
				return;
			}			
			List<DailyTask> finishTask=new ArrayList<DailyTask>();
			if(player.getSupperFinshSerial().size()>0){
				finishTask.addAll(player.getSupperFinshSerial());
			}else{
				int dailyTaskCount = player.getDailyTaskCount();
				while (dailyTaskCount < DailyTask.maxCount) {
					DailyTask task = new DailyTask();
					task.initTask(player);
					dailyTaskCount++;
					finishTask.add(task);
				}
			}
			List<Item> rewardsGoods=new ArrayList<Item>();
//			额外奖励
			List<Item> extraRewardsGoods=new ArrayList<Item>();
			for (DailyTask dailyTask : finishTask) {
				rewardsGoods.addAll(dailyTask.getRewardsGoods());
				if(extraRewardsGoods.size()<=0){
					extraRewardsGoods.addAll(dailyTask.getExtraRewardsGoods());
				}
			}
			rewardsGoods.addAll(nowTask.getRewardsGoods());
			if(extraRewardsGoods.size()>=0){
				extraRewardsGoods.addAll(nowTask.getExtraRewardsGoods());
			}
			//检查空格数
			int needCells = BackpackManager.getInstance().needEmptyCellNum(player, rewardsGoods);
			if(needCells>0){
				if(player.getSupperFinshSerial().size()<=0){
					player.getSupperFinshSerial().addAll(finishTask);
					player.setSupperFinshSerial(finishTask);
				}
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，包裹空间已满，还需要{1}个空格子才可以进行此操作"),String.valueOf(needCells));
				return;
			}
			BackpackManager.getInstance().changeGold(player, -needgold, Reasons.DAILYTASKQUCKFINSH,action);
			player.getCurrentDailyTasks().remove(nowTask);	
			nowTask.noResumeFinshTask(DailyTask.FINISH_TYPE_SUPPERFINISH);
			if(finishTask.size()>0){
				DailyTask dailyTask = finishTask.get(0);
				player.setDailyTaskCount(player.getDailyTaskCount()+1);
				player.setDailyTaskTime(System.currentTimeMillis());
				dailyTask.changeTask();
				final Player innerplayer=player;
				for(int i=1;i<=finishTask.size();i++){
					
					final DailyTask now = finishTask.get(i-1);
					final DailyTask next=i<finishTask.size()?finishTask.get(i):null;
					
					PlayerManager.getInstance().addLaterTask(player, new Runnable() {
						@Override
						public void run() {
							int dailyTaskCount = innerplayer.getDailyTaskCount();
							now.noResumeFinshTask(DailyTask.FINISH_TYPE_SUPPERFINISH);
							if(next!=null){
								innerplayer.setDailyTaskCount(dailyTaskCount+1);
								innerplayer.setDailyTaskTime(System.currentTimeMillis());
								next.changeTask();
							}
						}
					}, i*2*1000);
				}
				PlayerManager.getInstance().addLaterTask(player, new Runnable() {
					@Override
					public void run() {
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】使用一键日常功能，快速完成了所有剩下的日常任务并获得大量额外经验加成!{@}"), innerplayer.getName()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(innerplayer),GuideType.TASK_DAY.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.TASK_DAY.getValue());
					}
				}, finishTask.size()*2*1000);
			}
			player.getSupperFinshSerial().clear();
			
			
		}else if(type==1){
			int needgold=100;
			//最优快速完成
			if(!BackpackManager.getInstance().checkGold(player, needgold)){
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，需要{1}元宝才能快速最优完成日常任务，元宝不足"),needgold+"");
				return;
			}	
			final List<Q_task_daily_rewardsBean> taskModelByRich = getTaskRewardsModelByRich(player, 10);
			if(taskModelByRich==null||taskModelByRich.size()<=0){
				logger.error("找不到奖励丰厚度为10的任务,策划数据配置错误");
				return;
			}
			
			List<DailyTask> finishTask=new ArrayList<DailyTask>();
			if(player.getSupperOptimalFinshSerial().size()>0){
				finishTask.addAll(player.getSupperOptimalFinshSerial());
			}else{
				int dailyTaskCount = player.getDailyTaskCount();
				while (dailyTaskCount < DailyTask.maxCount) {
					DailyTask task = new DailyTask();
					task.initTask(player);

					dailyTaskCount++;
					finishTask.add(task);
				}
			}
			List<Item> rewardsGoods=new ArrayList<Item>();
			//额外奖励
			List<Item> extraRewardsGoods=new ArrayList<Item>();
			for (DailyTask dailyTask : finishTask) {
				rewardsGoods.addAll(dailyTask.getRewardsGoods());
				if(extraRewardsGoods.size()<=0){
					extraRewardsGoods.addAll(dailyTask.getExtraRewardsGoods());
				}
			}
			rewardsGoods.addAll(nowTask.getRewardsGoods());
			if(extraRewardsGoods.size()<=0){
				extraRewardsGoods.addAll(nowTask.getExtraRewardsGoods());
			}
			//检查空格数
			int needCells = BackpackManager.getInstance().needEmptyCellNum(player, rewardsGoods);
			if(needCells>0){
				if(player.getSupperOptimalFinshSerial().size()<=0){
					player.getSupperOptimalFinshSerial().addAll(finishTask);
					player.setSupperOptimalFinshSerial(finishTask);
				}
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，需要包裹预留{1}个空格子才可以进行此操作"),String.valueOf(needCells));
				return;
			}
			BackpackManager.getInstance().changeGold(player, -needgold, Reasons.DAILYTASKSUPPERFINSH,action);
			player.getCurrentDailyTasks().remove(nowTask);
			Q_task_daily_rewardsBean rewardsModel = taskModelByRich.get(RandomUtils.random(taskModelByRich.size()));
			nowTask.upAchrive(rewardsModel, player);
			nowTask.noResumeFinshTask(DailyTask.FINISH_TYPE_OPTIMALSUPPERFINISH);
			if(finishTask.size()>0){
				DailyTask dailyTask = finishTask.get(0);
				player.setDailyTaskCount(player.getDailyTaskCount()+1);
				player.setDailyTaskTime(System.currentTimeMillis());
				dailyTask.changeTask();
				
				Q_task_daily_rewardsBean rewmodel = taskModelByRich.get(RandomUtils.random(taskModelByRich.size()));
				dailyTask.upAchrive(rewmodel, player);
				final Player innerplayer=player;
				for(int i=1;i<=finishTask.size();i++){
					
					final DailyTask now = finishTask.get(i-1);
					final DailyTask next=i<finishTask.size()?finishTask.get(i):null;
					PlayerManager.getInstance().addLaterTask(player, new Runnable() {
						@Override
						public void run() {
							int dailyTaskCount = innerplayer.getDailyTaskCount();
							now.noResumeFinshTask(DailyTask.FINISH_TYPE_OPTIMALSUPPERFINISH);
							logger.info("finsh"+innerplayer.getDailyTaskCount());
							if(next!=null){
								innerplayer.setDailyTaskCount(dailyTaskCount+1);
								innerplayer.setDailyTaskTime(System.currentTimeMillis());
								next.changeTask();
								Q_task_daily_rewardsBean rewmodel = taskModelByRich.get(RandomUtils.random(taskModelByRich.size()));
								next.upAchrive(rewmodel, innerplayer);
								logger.info("start and upachrive"+innerplayer.getDailyTaskCount());
							}
						}
					}, i*3*1000);
				}
				
				PlayerManager.getInstance().addLaterTask(player, new Runnable() {
					@Override
					public void run() {
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】使用一键日常功能，快速完成了所有剩下的日常任务并获得大量额外经验加成!{@}"), innerplayer.getName()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(innerplayer),GuideType.TASK_DAY.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.TASK_DAY.getValue());
					}
				}, finishTask.size()*3*1000);
			}
		}else if(type==2){
			int needgold=10;
			if(!BackpackManager.getInstance().checkGold(player, needgold)){
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，需要{1}元宝才能立即完成本环任务，元宝不足"),needgold+"");
				return;
			}	
			BackpackManager.getInstance().changeGold(player, -needgold, Reasons.DAILYFINSHCURRENTLOOP,action);
			player.getCurrentDailyTasks().remove(nowTask);	
			nowTask.noResumeFinshTask(DailyTask.FINISH_TYPE_COMMON);
			TaskManager.getInstance().acceptDailyTask(player);
		}
	}
	
	/**
	 * 吞噬
	 * @param player
	 * @param taskId
	 */
	public void devourTask(Player player,long taskId,boolean isFull){
		long action=Config.getId();
		
		ConquerTask taskById=null;
		List<ConquerTask> currentConquerTasks2 = player.getCurrentConquerTasks();
		for (ConquerTask conquerTask : currentConquerTasks2) {
			if(conquerTask.getId()==taskId){
				taskById= conquerTask;
			}
		} 
		if(taskById==null){
			return;
		}
		if(player.getDaydevourcount()>=CONQUERTASK_DAYDEVOUR){
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，每日限制吞噬{1}个任务，今日已达上限"),CONQUERTASK_DAYDEVOUR+"");
			return ;
		}
		ConquerTask task=(ConquerTask) taskById;
		Q_task_conquerBean model = DataManager.getInstance().q_task_conquerContainer.getMap().get(task.getModelid());
		if(model.getQ_devour_able()==0){
			MessageUtil.notify_player(player,Notifys.ERROR, ResManager.getInstance().getString("很抱歉，该任务不可被吞噬"));
			return;
		}
		List<ConquerTask> currentConquerTasks = player.getCurrentConquerTasks();
		int indexOf = currentConquerTasks.indexOf(taskById);
		if(indexOf>=currentConquerTasks.size()){
			//最后一条了
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("下方没有可吞噬的任务"));
			return;
		}		
		ConquerTask conquerTask = currentConquerTasks.get(indexOf+1);
		if(conquerTask==null){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("下方没有可吞噬的任务"));
			return;
		}
		if(isFull&&!BackpackManager.getInstance().checkGold(player, devourFullNeedGold)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("所需元宝不足"));
			return;
		}
		String beforeTaskInfo=JSONserializable.toString(conquerTask);
		String resumeTaskInfo=JSONserializable.toString(task);
		if(player.getDaydevourcount()==0){
			player.setDaydevourTime(System.currentTimeMillis());
		}
		player.setDaydevourcount(player.getDaydevourcount()+1);
		double ratio=(double)model.getQ_devour_prop()/1000d;
		if(isFull){
			BackpackManager.getInstance().changeGold(player, -devourFullNeedGold, Reasons.CONQUERTASKDEVOUR, action);
			conquerTask.setExp(conquerTask.getExp()+task.getExp());
			conquerTask.setCopper(conquerTask.getCopper()+task.getCopper());
			conquerTask.setZhengqi(conquerTask.getZhengqi()+task.getZhengqi());
			conquerTask.setShengwang(conquerTask.getShengwang()+task.getShengwang());
		}else{
			conquerTask.setExp(conquerTask.getExp()+(int)(task.getExp()*ratio));
			conquerTask.setCopper(conquerTask.getCopper()+(int)(task.getCopper()*ratio));
			conquerTask.setZhengqi(conquerTask.getZhengqi()+(int)(task.getZhengqi()*ratio));
			conquerTask.setShengwang(conquerTask.getShengwang()+(int)(task.getShengwang()*ratio));	
		}
		//移除被吞噬的讨伐任务  只移除了任务，没有减少count 所以完成2个吞噬5任务就=完成了10个任务
		player.getCurrentConquerTasks().remove(taskById);
		
		ResConquerTaskAnnexMessage msg=new ResConquerTaskAnnexMessage();
		msg.setVanishTaskId(taskById.getId());
		MessageUtil.tell_player_message(player, msg);
		ResConquerTaskChangeMessage changeMsg=new ResConquerTaskChangeMessage();
		changeMsg.setConquerTaskAcceptCount(player.getConquerTaskCount());
		changeMsg.setConquerTaskAcceptMaxCount(TaskManager.CONQUERTASK_DAYMAXACCEPT + player.getConquerTaskMaxCount());
		changeMsg.setDevourCount(player.getDaydevourcount());
		changeMsg.setTask(conquerTask.buildTaskInfo());
		MessageUtil.tell_player_message(player, changeMsg);
		
		try {
			ConquerTaskDevourLog log = new ConquerTaskDevourLog();
			log.setAfterTaskInfo(JSONserializable.toString(conquerTask));
			log.setBeforeTaskInfo(beforeTaskInfo);
			log.setDevourCount(player.getDaydevourcount());
			log.setResumeTaskId(task.getId());
			log.setResumeTaskInfo(resumeTaskInfo);
			log.setRoleId(player.getId());
			log.setTaskId(conquerTask.getId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e, e);
		}
	}
	
	public void supperCurrentTaskFinsh(Player player,long taskId){
		long action = Config.getId();
		int needgold=10;
		ConquerTask task=null;
		if(player.getCurrentConquerTasks().size()>0){
			for (ConquerTask currtask:player.getCurrentConquerTasks()) {
				if(currtask.getId()==taskId){
					task=currtask;
					break;
				}
			}
		}
		if(task==null){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("指定的任务不存在"));
		}else{
			Q_task_conquerBean taskdata = ManagerPool.dataManager.q_task_conquerContainer.getMap().get(task.getModelid());
			if (taskdata == null) {
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("该任务静态数据不存在，不能完成"));
				return;
			}
			if (taskdata.getQ_scroll_type() >= 4) {
				long count = CountManager.getInstance().getCount(player, CountTypes.CONQUERTASKQUICK_NUM, null);
				if (count >= 1) {
					MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("紫色讨伐卷轴每日只允许秒杀1次"));
					return;
				}
			}
			if(BackpackManager.getInstance().checkGold(player, needgold)){
				if(BackpackManager.getInstance().changeGold(player, -needgold, Reasons.CONQUERTASKQUICKFINSH, action)){
					task.finshTask(player);
					if (taskdata != null && taskdata.getQ_scroll_type() >= 4) {
						CountManager.getInstance().addCount(player, CountTypes.CONQUERTASKQUICK_NUM, null, CountManager.COUNT_DAY, 1, 0);
						List<GoodsInfoRes> itemInfos = new ArrayList<GoodsInfoRes>();
						String str =  "";
						for (Item item : task.getRewardsGoods()) {
							GoodsInfoRes goodsInfoRes = new GoodsInfoRes();
							str = str+"\f";
							goodsInfoRes.setItemInfo(item.buildItemInfo());
							itemInfos.add(goodsInfoRes);
							
						}
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】通过一键讨伐功能，快速完成了紫色讨伐任务获得紫色装备")+str+ "{@}", player.getName()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.TASK_TAOFA.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),itemInfos,GuideType.TASK_TAOFA.getValue());
					}

				}else{
					MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("元宝扣除失败"));
					logger.error("以经作过元宝量检查 不应该扣除失败请检查代码",new Exception());
				}
			}else{
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("元宝数量不足"));	
			}
		}
	}
	
//	public void taskTrans(Player player,long taskId,byte type,int mapid,int x,int y,int line){
//		if(PlayerState.FIGHT.compare(player.getState())){
//			MessageUtil.notify_player(player, Notifys.ERROR,"很抱歉，您尚未脱离战斗状态，无法传送");
//			return;
//		}
//		switch (type) {
//		case Task.DAILYTASK:
//			transByDailyTask(player,mapid,x,y,line);
//			break;
//		case Task.CONQUERTASK:
//			transByConquerTask(player,taskId,mapid,x,y,line);
//			break;
//		}
//	}
	
	public void transByDailyTask(Player player,int mapid,int x,int y,int line){
		int playerVipId = VipManager.getInstance().getPlayerVipId(player);
		if(playerVipId<1||playerVipId>3){
			
			return;
		}
		if(player.getCurrentDailyTasks().size()<=0){
			return;
		}
		if (PlayerState.FIGHT.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您尚未脱离战斗状态，无法传送"));
			return;
		}
		if(player.isDie()){
			return;
		}
		DailyTask dailyTask = player.getCurrentDailyTasks().get(0);
		int condid = dailyTask.getCondid();
		Q_task_daily_condBean model = DataManager.getInstance().q_task_daily_condContainer.getMap().get(condid);
		String targetMonster = model.getQ_end_needkillmonster();
		String targetGoods = model.getQ_end_needgoods();
		if(targetMonster.startsWith("@")){
			targetMonster=targetMonster.replace("@","");
		}
		if(targetGoods.startsWith("@")){
			targetGoods=targetGoods.replace("@","");
		}
		
		if(StringUtils.isBlank(targetMonster)&&StringUtils.isBlank(targetGoods)){
			return;
		}
		if(!StringUtils.isBlank(targetMonster)){
			String[] split = targetMonster.split(Symbol.FENHAO_REG);
			for (String string : split) {
				if(StringUtils.isBlank(string)){
					continue;
				}
				String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
				if(split2.length<5){
					continue;
				}
				int targetmap=Integer.parseInt(split2[2]);
				int targetx=Integer.parseInt(split2[3]);
				int targety=Integer.parseInt(split2[4]);
				if(targetmap==mapid&&targetx==x&&targety==y){
					Position buildPosition = MapUtils.buildPosition(targetx*MapUtils.GRID_BORDER, targety*MapUtils.GRID_BORDER);				
					List<Grid> roundNoBlockGrid = MapUtils.getRoundNoBlockGrid(buildPosition, 30, targetmap);
					if(roundNoBlockGrid==null||roundNoBlockGrid.size()<=0){
						logger.error("策划数据有问题，落点周围找不到可落脚的点");
						continue;
					}
					Grid grid = roundNoBlockGrid.get(RandomUtils.random(roundNoBlockGrid.size()));
					if(player.getMapModelId()==targetmap){
						MapManager.getInstance().changePosition(player, grid.getCenter());
					}else{
						MapManager.getInstance().changeMap(player, targetmap, targetmap, 0, grid.getCenter(), this.getClass().getName() + ".transByDailyTask 2");					
					}
					return;
				}
			}
		}
		
		if(!StringUtil.isBlank(targetGoods)){
			String[] split = targetGoods.split(Symbol.FENHAO_REG);
			for (String string : split) {
				if(StringUtils.isBlank(string)){
					continue;
				}
				String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
				if(split2.length<5){
					continue;
				}
				int targetmap=Integer.parseInt(split2[2]);
				int targetx=Integer.parseInt(split2[3]);
				int targety=Integer.parseInt(split2[4]);
				if(targetmap==mapid&&targetx==x&&targety==y){
					Position buildPosition = MapUtils.buildPosition(targetx*MapUtils.GRID_BORDER, targety*MapUtils.GRID_BORDER);				
					List<Grid> roundNoBlockGrid = MapUtils.getRoundNoBlockGrid(buildPosition, 30, targetmap);
					if(roundNoBlockGrid==null||roundNoBlockGrid.size()<=0){
						logger.error("策划数据有问题，落点周围找不到可落脚的点");
						continue;
					}
					Grid grid = roundNoBlockGrid.get(RandomUtils.random(roundNoBlockGrid.size()));
					if(player.getMapModelId()==targetmap){
						MapManager.getInstance().changePosition(player, grid.getCenter());
					}else{
						MapManager.getInstance().changeMap(player, targetmap, targetmap, 0, grid.getCenter(), this.getClass().getName() + ".transByDailyTask 1");					
					}
					return;
				}
			}
		}
	}

	public void transByConquerTask(Player player,long taskId,int mapid,int x,int y,int line) {

		int playerVipId = VipManager.getInstance().getPlayerVipId(player);
		if(playerVipId<1||playerVipId>3){
			
			return;
		}
		
		if (PlayerState.FIGHT.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您尚未脱离战斗状态，无法传送"));
			return;
		}
		if(player.isDie()){
			return;
		}
		if (player.getCurrentConquerTasks().size() > 0) {
			ConquerTask targettask = null;
			for (ConquerTask task : player.getCurrentConquerTasks()) {
				if (task.getId() == taskId) {
					targettask = task;
					break;
				}
			}
			if (targettask == null) {
				return;
			}
			Q_task_conquerBean taskModel = DataManager.getInstance().q_task_conquerContainer.getMap().get(targettask.getModelid());
			String target = taskModel.getQ_monstercount();
			if (target.startsWith("@")) {
				target = target.replace("@", "");
			}
			if (StringUtils.isBlank(target)) {
				return;
			}
			String[] split = target.split(Symbol.XIAHUAXIAN_REG);
			if (split.length < 5) {
				return;
			}
			int targetmap = Integer.parseInt(split[2]);
			int targetx = Integer.parseInt(split[3]);
			int targety = Integer.parseInt(split[4]);
			Position buildPosition = MapUtils.buildPosition(targetx * MapUtils.GRID_BORDER, targety * MapUtils.GRID_BORDER);
			List<Grid> roundNoBlockGrid = MapUtils.getRoundNoBlockGrid(buildPosition, 30, targetmap);
			if (roundNoBlockGrid == null || roundNoBlockGrid.size() <= 0) {
				logger.error("策划数据有问题，落点周围找不到可落脚的点");
				return;
			}
			Grid grid = roundNoBlockGrid.get(RandomUtils.random(roundNoBlockGrid.size()));
			if (player.getMapModelId() == targetmap) {
				MapManager.getInstance().changePosition(player, grid.getCenter());
			} else {
				MapManager.getInstance().changeMap(player, targetmap, targetmap, 0, grid.getCenter(), this.getClass().getName() + ".transByConquerTask");
			}
		}
	}
	

	public void transByTreasureHuntTask(Player player,long taskId,int mapid,int x,int y,int line) {
		int playerVipId = VipManager.getInstance().getPlayerVipId(player);
		if(playerVipId<1||playerVipId>3){
			return;
		}
		if (PlayerState.FIGHT.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您尚未脱离战斗状态，无法传送"));
			return;
		}
		if(player.isDie()){
			return;
		}
		if (player.getCurrentTreasureHuntTasks().size() > 0) {
			TreasureHuntTask targettask = null;
			for (TreasureHuntTask task : player.getCurrentTreasureHuntTasks()) {
				if (task.getId() == taskId) {
					targettask = task;
					break;
				}
			}
			if (targettask == null) {
				return;
			}
			Q_task_explorepalaceBean model= DataManager.getInstance().q_task_explorepalaceContainer.getMap().get(targettask.getModelId());
			String target=model.getQ_end_needkillmonster();
			if(StringUtil.isBlank(target)){
				return;
			}
			if (target.startsWith("@")) {
				target = target.replace("@", "");
			}
			if (StringUtils.isBlank(target)) {
				return;
			}
			String[] split = target.split(Symbol.XIAHUAXIAN_REG);
			if (split.length < 5) {
				return;
			}
			int targetmap = Integer.parseInt(split[2]);
			int targetx = Integer.parseInt(split[3]);
			int targety = Integer.parseInt(split[4]);
			Position buildPosition = MapUtils.buildPosition(targetx * MapUtils.GRID_BORDER, targety * MapUtils.GRID_BORDER);
			List<Grid> roundNoBlockGrid = MapUtils.getRoundNoBlockGrid(buildPosition, 30, targetmap);
			if (roundNoBlockGrid == null || roundNoBlockGrid.size() <= 0) {
				logger.error("策划数据有问题，落点周围找不到可落脚的点");
				return;
			}
			Grid grid = roundNoBlockGrid.get(RandomUtils.random(roundNoBlockGrid.size()));
			if (player.getMapModelId() == targetmap) {
				MapManager.getInstance().changePosition(player, grid.getCenter());
			} else {
				MapManager.getInstance().changeMap(player, targetmap, targetmap, 0, grid.getCenter(), this.getClass().getName() + ".transByTreasureHuntTask");
			}
		}
	}
	
	
	
	
	
	/**
	 * 提升奖励 
	 */
	public void upAchrive(Player player,int taskId){
		int upAchriveNeedgold=5;
		int targerich=10;
		if(player.getCurrentDailyTasks().size()<=0){
//			MessageUtil.notify_player(player, Notifys.ERROR,"很抱歉，当前没有未完成的日常任务");
			return;
		}
		Task task =player.getCurrentDailyTasks().get(0);
//				getTaskByModelId(player, Task.DAILYTASK, taskId);
		if(task==null){
			return;
		}
		if(!(task instanceof DailyTask)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，只有日常任务可以提升奖励星级"));
			return;
		}
		DailyTask dailyTask=(DailyTask) task;
//		Gold gold = BackpackManager.getInstance().getGold(player);
		if(!BackpackManager.getInstance().checkGold(player, upAchriveNeedgold)){
//		if(!BackpackManager.getInstance().checkGold(player, gold, upAchriveNeedgold)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，需要{1}元宝才能将奖励提升至{2}星，元宝不足"),upAchriveNeedgold+"",targerich+"");
			return;
		}
		Q_task_daily_rewardsBean model = DataManager.getInstance().q_task_daily_rewardsContainer.getMap().get(dailyTask.getRewardId());
		if(model==null){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，只有日常任务可以提升奖励星级"));
			return;
		}
		if(model.getQ_rich()>=targerich){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，已经是最高奖励"));
			return;
		}
		List<Q_task_daily_rewardsBean> taskModelByRich = getTaskRewardsModelByRich(player, targerich);
		if(taskModelByRich==null||taskModelByRich.size()<=0){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，找不到奖励丰厚度为{1}的任务"),targerich+"");
			return;
		}
		long action=Config.getId();
		if(!BackpackManager.getInstance().changeGold(player, -upAchriveNeedgold, Reasons.DAILYTASKUPACHRIVE,action)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，扣除元宝失败"));
			return;
		}
		String beforetaskinfo=JSONserializable.toString(dailyTask);
		Q_task_daily_rewardsBean rewardsModel = taskModelByRich.get(RandomUtils.random(taskModelByRich.size()));
		dailyTask.upAchrive(rewardsModel,player);		
		try {
			DailyTaskUpAchieveLog log=new DailyTaskUpAchieveLog();
			log.setAfterachieveid(dailyTask.getRewardId());
			log.setAfterTaskInfo(JSONserializable.toString(dailyTask));
			log.setBeforeachieveid(model.getQ_id());
			log.setBeforeTaskInfo(beforetaskinfo);
			log.setRoleid(player.getId());
			log.setTaskid(dailyTask.getId());
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e,e);
		}
	}

	/**
	 * 降低难度
	 */
	public void reducedDifficulty(Player player,int taskId){
		int downtaskneedgold=5;
		int targethard=1;
		if(player.getCurrentDailyTasks().size()<=0){
//			MessageUtil.notify_player(player, Notifys.ERROR,"很抱歉，当前没有未完成的日常任务");
			return;
		}
		Task task =player.getCurrentDailyTasks().get(0);
//				getTaskByModelId(player, Task.DAILYTASK, taskId);
		if(task==null){
			return;
		}
		if(!(task instanceof DailyTask)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，只有日常任务可以提升奖励星级"));
			return;
		}
		DailyTask dailyTask=(DailyTask) task;
//		Gold gold = BackpackManager.getInstance().getGold(player);
//		if(!BackpackManager.getInstance().checkGold(player, gold, downtaskneedgold)){
		if(!BackpackManager.getInstance().checkGold(player, downtaskneedgold)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，需要{1}元宝才能将难度降至{2}星，元宝不足"),downtaskneedgold+"",targethard+"");
			return;
		}
//		if(player.getGold()<downtaskneedgold){
//			MessageUtil.notify_player(player, Notifys.ERROR,"很抱歉，需要{1}元宝才能将难度降至{2}星，元宝不足",downtaskneedgold+"",targethard+"");
//			return;
//		}
		Q_task_daily_condBean model = DataManager.getInstance().q_task_daily_condContainer.getMap().get(dailyTask.getCondid());
		if(model==null){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，只有日常任务可以降低难度等级"));
			return;
		}
		if(model.getQ_hard()<=1){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，已经是最低难度"));
			return;
		}
		List<Q_task_daily_condBean> taskModelByDifficulty = getTaskCondModelByDifficulty(player, targethard);
		if(taskModelByDifficulty==null){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，找不到难度等级为{1}的任务"),targethard+"");
			return;
		}
		long action=Config.getId();
		if(!BackpackManager.getInstance().changeGold(player, -downtaskneedgold,Reasons.DAILYTASKREDUCED,action)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("很抱歉，扣除元宝失败"));
			return;
		}
		String beforeTaskInfo=JSONserializable.toString(dailyTask);
		Q_task_daily_condBean targetModel = taskModelByDifficulty.get(RandomUtils.random(taskModelByDifficulty.size()));
		dailyTask.reducedDifficulty(targetModel);
		try {
			DailyTaskReducedDifficulty log=new DailyTaskReducedDifficulty();
			log.setAfterCondModelId(dailyTask.getCondid());
			log.setAfterTaskInfo(JSONserializable.toString(dailyTask));
			log.setBeforeCondModelId(model.getQ_id());
			log.setBeforeTaskInfo(beforeTaskInfo);
			log.setRoleid(player.getId());
			log.setTaskid(dailyTask.getId());
			log.setSid(player.getCreateServerId());
		} catch (Exception e) {
			logger.error(e,e);
		}
	}
	
	private List<Q_task_daily_condBean>  getTaskCondModelByDifficulty(Player player,int difficulty){
		List<Q_task_daily_condBean> list = DataManager.getInstance().q_task_daily_condContainer.getList();
		List<Q_task_daily_condBean> result=new ArrayList<Q_task_daily_condBean>();
		for (Q_task_daily_condBean model: list) {
			if(model.getQ_maxgrade()>=player.getLevel()&&player.getLevel()>=model.getQ_mingrade()&&model.getQ_hard()==difficulty){
				result.add(model);
			}
		}
		return result;
	}
	
	private List<Q_task_daily_rewardsBean>  getTaskRewardsModelByRich(Player player,int rich){
		List<Q_task_daily_rewardsBean> list = DataManager.getInstance().q_task_daily_rewardsContainer.getList();
		List<Q_task_daily_rewardsBean> result=new ArrayList<Q_task_daily_rewardsBean>();
		
		for (Q_task_daily_rewardsBean model: list) {
			if(model.getQ_maxgrade()>=player.getLevel()&&player.getLevel()>=model.getQ_mingrade()&&model.getQ_rich()==rich){
				result.add(model);
			}
		}
		return result;
	}
	
	/**
	 * 有任务事件发生 通知到各个任务
	 * @param actionType
	 * @param value
	 */
	public void action(Player player,short actionType,int model,int num){
		List<MainTask> currentMainTasks = player.getCurrentMainTasks();
		List<DailyTask> currentDailyTasks = player.getCurrentDailyTasks();
		List<ConquerTask> currentConquerTasks = player.getCurrentConquerTasks();
		List<TreasureHuntTask> currentTreasureHuntTasks = player.getCurrentTreasureHuntTasks();
		List<RankTask> currentRankTasks = player.getCurrentRankTasks();
		List<Task> finshTask=new ArrayList<Task>();
		
		if (currentConquerTasks.size() > 0) {
			List<ConquerTask> list=new ArrayList<ConquerTask>();
			for (ConquerTask task : currentConquerTasks) {
				if(task.endNeedKillMonster().keySet().contains(model)&&actionType==Task.ACTION_TYPE_KILLMONSTER){
					list.add(task);	
				}
			}
			if (list.size() > 0) {
				int actionnum = num;
				int indextag = list.size() - 1;
				while (actionnum > 0 && indextag >= 0) {
					ConquerTask conquerTask = list.get(indextag);
					int action = conquerTask.isAction(actionType, model);
					Integer integer = conquerTask.getKillmonsters().get(String.valueOf(model));
					if (action > 0 && integer != null && integer > 0) {
						actionnum -= action;
						if (conquerTask.action(actionType, model, num)) {
							finshTask.add(conquerTask);
						}
					}
					indextag--;
				}
				ConquerTask conquerTask = list.get(list.size() - 1);
				if (!finshTask.contains(conquerTask) && actionnum > 0) {
					if (conquerTask.action(actionType, model, actionnum)) {
						finshTask.add(conquerTask);
					}
					// 最后一条任务还没完
				}
			}
			
		}

		for (DailyTask dailyTask : currentDailyTasks) {
			if(dailyTask.action(actionType,model, num)){
				finshTask.add(dailyTask);
			}
		}
		
		for (TreasureHuntTask treasureHuntTask : currentTreasureHuntTasks) {
			if(treasureHuntTask.action(actionType, model, num)){
				finshTask.add(treasureHuntTask);
			}
		}
		
		for (RankTask rankTask : currentRankTasks) {
			if (rankTask.action(actionType, model, num)) {
				finshTask.add(rankTask);
			}
		}
		
		for (MainTask mainTask : currentMainTasks) {
			Q_task_mainBean q_task_mainBean = DataManager.getInstance().q_task_mainContainer.getMap().get(mainTask.getModelid());
			if(q_task_mainBean!=null)
			if(q_task_mainBean.getQ_accept_needmingrade()<=player.getLevel()){
				//由于主线任务都是主动接取的。 所以新任务还是要接取  只是不发生任何任务事件
				if(mainTask.action(actionType,model,num)){
					finshTask.add(mainTask);
				}
			}
		}
		for (Task task : finshTask) {
			task.finshTask(player);
		}
	}
	
	/**
	 * 领取可领取区域的任务
	 */
	public void receiveRewardsAbleArea(Player player){
		List<Item> taskRewardsReceiveAble = player.getTaskRewardsReceiveAble();
		int size = taskRewardsReceiveAble.size();
		if(size<=0){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("没有可领取的物品"));
			return;
		}
		if(player.getTransactionsinfo()!=null){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("交易中不可领取"));
			return;
		}
		long action=Config.getId();
		if(!BackpackManager.getInstance().hasAddSpace(player, taskRewardsReceiveAble)){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("背包空间不足,请先清理包裹"));
		}else{
			BackpackManager.getInstance().addItems(player, taskRewardsReceiveAble,Reasons.TASKGETKRECEIVEABLE,action);
			BackpackManager.getInstance().removeAbleReceieveAll(player);
		}
	}
		
	/**
	 * 定时事件
	 * @param player
	 * @return
	 */
	public void zeroClockAction(Player player) {
		if(player.getCurrentMainTasks().size()<=0){
			return;
		}
		MainTask mainTask = player.getCurrentMainTasks().get(0);
		if(mainTask==null){
			return;
		}
		Q_task_mainBean q_task_mainBean = DataManager.getInstance().q_task_mainContainer.getMap().get(mainTask.getModelid());
		if(q_task_mainBean.getQ_chapter()<1){
			return;
		}
		
		boolean tag=false;
		if(player.getDaydevourcount() > 0){
			tag=true;
			player.setDaydevourcount(0);
			player.setDaydevourTime(System.currentTimeMillis());
			
		}
		if(player.getConquerTaskCount()>0){
			tag=true;
			player.setConquerTaskCount(0);
			player.setConquerTaskTime(System.currentTimeMillis());
		}
		if (player.getConquerTaskMaxCount()>0) {
			tag=true;
			player.setConquerTaskMaxCount(0);
		}
		if (player.getRankTaskCount()>0) {
			tag=true;
			player.setRankTaskCount(0);
			player.setRankTaskTime(System.currentTimeMillis());
			sendRankTaskInfoList(player, true);
		}
		if(player.isActivityDailyTask()&&player.getDailyTaskCount()>0){
			tag=true;
		}
		
		if(tag){
			if(player.getLaterList()!=null&&player.getLaterList().size()>0){
				long finshTime=0;
				LinkedList<LaterTask> laterList = player.getLaterList();
				for (LaterTask laterTask : laterList) {
					long startTime = laterTask.getStartTime();
					int count = laterTask.getCount();
					int interval = laterTask.getInterval();
//					int nowcount = laterTask.getNowcount();
					long time=startTime+count*interval;
					if(time>finshTime){
						finshTime=time;
					}
				}
				int extratime=(int)(finshTime-System.currentTimeMillis())+1000;//延时一秒
				final Player innerplayer=player;
				PlayerManager.getInstance().addLaterTask(player,new LaterRestTask() {
					@Override
					public void run() {
						// 不是同一天
						innerplayer.getCurrentDailyTasks().clear();
						innerplayer.setDailyTaskCount(0);
						innerplayer.setDailyTaskTime(System.currentTimeMillis());
						DailyTask task = new DailyTask();
						task.initTask(innerplayer);
						int dailyTaskCount = innerplayer.getDailyTaskCount();
						innerplayer.setDailyTaskCount(dailyTaskCount + 1);
						innerplayer.setDailyTaskTime(System.currentTimeMillis());
						innerplayer.getCurrentDailyTasks().add(task);
						sendTaskInfo(innerplayer, true);
						if(task.checkFinsh(false, innerplayer)){
							task.finshTask(innerplayer);
						}
					}
				}, extratime);
			}else{
				if(player.isActivityDailyTask()&&player.getDailyTaskCount()>0){
					player.getCurrentDailyTasks().clear();
					player.setDailyTaskCount(0);
					player.setDailyTaskTime(System.currentTimeMillis());
					DailyTask task = new DailyTask();
					task.initTask(player);
					int dailyTaskCount = player.getDailyTaskCount();
					player.setDailyTaskCount(dailyTaskCount + 1);
					player.setDailyTaskTime(System.currentTimeMillis());
					player.getCurrentDailyTasks().add(task);
					sendTaskInfo(player,true);
					if(task.checkFinsh(false, player)){
						task.finshTask(player);
					}
				}else{
					sendTaskInfo(player,true);	
				}
					
			}
				
		}
		
	}

	/**
	 * 
	 * @param player
	 */
	public void loginCheckTask(Player player){
		boolean isnewdailytask = false;
		if(player.getCurrentMainTasks().size()<=0){
			TaskManager.getInstance().resetTask(player);
		}
		if (player.getCurrentMainTasks().size() > 0) {
			MainTask mainTask = player.getCurrentMainTasks().get(0);
			if (mainTask != null) {
				Q_task_mainBean q_task_mainBean = DataManager.getInstance().q_task_mainContainer
						.getMap().get(mainTask.getModelid());
				if(q_task_mainBean==null){
					logger.error("任务模型找不到重置任务"+mainTask.getModelid());
					TaskManager.getInstance().resetTask(player);
					sendRankTaskInfoList(player, isnewdailytask);
					sendTaskInfo(player,isnewdailytask);
					return;
				}
				if (q_task_mainBean.getQ_chapter() >= 1) {
					if (player.getDailyTaskCount() > 20) {
						player.getCurrentDailyTasks().clear();
					}
					if (player.getDailyTaskCount() > 20) {
						player.setDailyTaskCount(20);
					}
					
					ArrayList<Task> taskList=new ArrayList<Task>();
					taskList.addAll(player.getCurrentDailyTasks());
					taskList.addAll(player.getCurrentConquerTasks());
					if (player.getCurrentMainTasks().size() > 0) {
						for (MainTask maintask : player.getCurrentMainTasks()) {
							if (q_task_mainBean.getQ_finsh_type() == Task.COMMIT_AUTO ) {
								taskList.add(maintask);
							}
						}
					}
					for (Task task : taskList) {
						if(task.checkFinsh(false, player)){
							task.finshTask(player);
						}
					}
					if(!player.isActivityDailyTask()&&mainTask.getModelid()>TaskManager.ACTIVITY_NEED_MAINTASK){
						player.setActivityDailyTask(true);
					}
					
					if (player.isActivityDailyTask()
							&& !TimeUtil.isSameDay(player.getDailyTaskTime(),
									System.currentTimeMillis())) {
						// 不是同一天
						player.getCurrentDailyTasks().clear();
						player.setDailyTaskCount(0);
						player.setDailyTaskTime(System.currentTimeMillis());
						DailyTask task = new DailyTask();
						task.initTask(player);
						int dailyTaskCount = player.getDailyTaskCount();
						player.setDailyTaskCount(dailyTaskCount + 1);
						player.setDailyTaskTime(System.currentTimeMillis());
						player.getCurrentDailyTasks().add(task);
						isnewdailytask = true;						
					}
					if (!TimeUtil.isSameDay(player.getDaydevourTime(),
							System.currentTimeMillis())) {
						player.setDaydevourcount(0);
						player.setDaydevourTime(System.currentTimeMillis());

					}
					if (!TimeUtil.isSameDay(player.getConquerTaskTime(),
							System.currentTimeMillis())) {
						player.setConquerTaskCount(0);
						player.setConquerTaskMaxCount(0);
						player.setConquerTaskTime(System.currentTimeMillis());
					}
					if (!TimeUtil.isSameDay(player.getRankTaskTime(), System.currentTimeMillis())) {
						player.setRankTaskCount(0);
						player.setRankTaskTime(System.currentTimeMillis());
						isnewdailytask = true;
					}
				}
			}
			
			
		}
		
		if(!player.getCurrentMainTasks().isEmpty()){
			MainTask currentMainTask = player.getCurrentMainTasks().get(0);
			player.setCurrentMainTaskId(currentMainTask.getModelid());
		}
		sendRankTaskInfoList(player, isnewdailytask);
		sendTaskInfo(player,isnewdailytask);
		if (isnewdailytask && player.getCurrentDailyTasks().size() > 0) {
			DailyTask task = player.getCurrentDailyTasks().get(0);
			if (task.checkFinsh(false, player)) {
				task.finshTask(player);
			}
		}
	}
	
	public void resetTask(Player player){
		player.getCurrentMainTasks().clear();
//		player.getCurrentDailyTasks().clear();
//		player.getCurrentConquerTasks().clear();
//		player.getCurrentTreasureHuntTasks().clear();
//		player.getFinishedTasks().clear();
//		player.setDailyTaskCount(0);
//		player.setDailyTaskTime(System.currentTimeMillis());
//		player.setConquerTaskCount(0);
//		player.setConquerTaskTime(System.currentTimeMillis());
//		player.setDaydevourcount(0);
//		player.setDaydevourTime(System.currentTimeMillis());
		String beforeReceiveAble=JSONserializable.toString(player.getTaskRewardsReceiveAble());
		player.getTaskRewardsReceiveAble().clear();	
		TaskManager.getInstance().acceptMainTask(player, TaskManager.CREATEPLAYERDEFAULTTASK);
//		sendTaskInfo(player, true);
		try {
			Task task = TaskManager.getInstance().getTaskByModelId(player, TaskManager.CREATEPLAYERDEFAULTTASK);
			if(task!=null){
				MainTaskLog log=new MainTaskLog();
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
		}	
	}
	
	
	public void sendTaskInfo(Player player,boolean isnewdailytask){
		ResTaskListMessage msg=new ResTaskListMessage();
		List<Item> taskRewardsReceiveAble = player.getTaskRewardsReceiveAble();
		for (Item item : taskRewardsReceiveAble) {
			ItemInfo buildItemInfo = item.buildItemInfo();
			msg.getAbleAct().add(buildItemInfo);
		}
		List<ConquerTask> currentConquerTasks = player.getCurrentConquerTasks();
		
		for (ConquerTask conquerTask : currentConquerTasks) {
			msg.getConqueryTask().add(conquerTask.buildTaskInfo());
		}		
		List<MainTask> currentMainTasks = player.getCurrentMainTasks();
		for (MainTask mainTask : currentMainTasks) {
			msg.getMainTask().add(mainTask.buildTaskInfo());
		}
		List<DailyTask> currentDailyTasks = player.getCurrentDailyTasks();
		for (DailyTask dailyTask : currentDailyTasks) {
			
			msg.getDailyTask().add(dailyTask.buildTaskInfo());
		}
		List<TreasureHuntTask> currentTreasureHuntTasks = player.getCurrentTreasureHuntTasks();
		for (TreasureHuntTask treasureHuntTask : currentTreasureHuntTasks) {
			msg.getTreasureHuntTask().add(treasureHuntTask.buildTaskInfo());
		}
		
		
		msg.setDaylyTaskacceptcount(player.getDailyTaskCount());
		msg.setConquerTaskAcceptCount(player.getConquerTaskCount());
		msg.setConquerTaskAcceptMaxCount(TaskManager.CONQUERTASK_DAYMAXACCEPT + player.getConquerTaskMaxCount());
		msg.setDevourCount(player.getDaydevourcount());
		msg.setIshasnewdailytask(isnewdailytask?1:0);
		MessageUtil.tell_player_message(player, msg);
		
		sendRankTaskInfoList(player,false);
	}
	
	public void sendRankTaskInfoList(Player player, boolean isnewday){
		if (isnewday) {
			player.setRankTaskCount(0);
			player.setRankTaskTime(System.currentTimeMillis());
			player.getCurrentRankTasks().clear();
			player.getFinishedRankTasks().clear();
			ListIterator<Q_task_rankBean> listIterator = DataManager.getInstance().q_task_rankContainer.getList().listIterator();
			while(listIterator.hasNext()) {
				Q_task_rankBean q_task_rankBean =  listIterator.next();
				if (q_task_rankBean!=null) {
					acceptRankTask(player, q_task_rankBean.getQ_id(), false);
				}
			}
		}
		ResRankTaskListMessage sendMessage = new ResRankTaskListMessage();
		ListIterator<RankTask> listIterator = player.getCurrentRankTasks().listIterator();
		while(listIterator.hasNext()) {
			RankTask rankTask =  listIterator.next();
			if (rankTask != null) {
				sendMessage.getTasklist().add(rankTask.buildTaskInfo());
			}
		}
		sendMessage.getCompletetasklist().addAll(player.getFinishedRankTasks());
		MessageUtil.tell_player_message(player, sendMessage);
		if (isnewday && player.getCurrentRankTasks().size() > 0) {
			for (RankTask rankTask : player.getCurrentRankTasks()) {
				if (rankTask.action(Task.ACTION_TYPE_RANK, RankTaskEnum.EVERYDAYLOGIN, 1)) {
					rankTask.finshTask(player);
					break;
				}
			}
		}
	}
	
	public void addRankTaskLevelUp(Player player){
		ListIterator<Q_task_rankBean> listIterator = DataManager.getInstance().q_task_rankContainer.getList().listIterator();
		while(listIterator.hasNext()) {
			Q_task_rankBean q_task_rankBean =  listIterator.next();
			if (q_task_rankBean!=null) {
				acceptRankTask(player, q_task_rankBean.getQ_id(), true);
			}
		}
	}
	
	public void loginMonsterInfo(Player player){
		List<ConquerTask> currentConquerTasks = player.getCurrentConquerTasks();
		Set<Integer> monstermodels=new HashSet<Integer>();
		if(currentConquerTasks==null||currentConquerTasks.size()<=0){
			return;
		}
		for (ConquerTask conquerTask : currentConquerTasks) {
			Set<Integer> keySet = conquerTask.endNeedKillMonster().keySet();
			for (Integer integer : keySet) {
				Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(integer);
				if(q_monsterBean.getQ_info_sync()!=0){
					monstermodels.add(integer);
				}
			}			
		}
		if(monstermodels.size()<=0){
			return;
		}
		int serverByCountry = WServer.getGameConfig().getServerByCountry(player.getLocate()); 
		List<Integer> list=new ArrayList<Integer>();
		list.addAll(monstermodels);
		ReqTargetMonsterMessage msg=new ReqTargetMonsterMessage();
		msg.setModelIds(list);
		msg.setReqRoleId(player.getId());
		msg.setServerId(serverByCountry);
		MessageUtil.send_to_world(msg);
	}

	/**
	 * 获取主线任务状态
	 * 返回值
	 * 1-已完成
	 * 2-进行中（未达到交付条件）
	 * 3-可交付（已达到交付条件，但未交付）
	 * 4-未接受
	 */
	public int getMainTaskState(Player player, int modelId){
		if(player.getCurrentMainTasks().size()<=0){
			return 1;
		}
		MainTask mainTask = player.getCurrentMainTasks().get(0);
		
		if(mainTask.getModelid()>modelId){
			return 1;
		}
		
		if(mainTask.getModelid()<modelId){
			return 4;
		}
		
		if(mainTask.getModelid()==modelId){
			if(mainTask.checkFinsh(false, player)){
				return 3;
			}else{
				return 2;
			}
		}
		return 4;
	}
	
	/**
	 * 使主线任务中特殊完成条件达到完成状态
	 * 
	 */
	public void satisfiedMainTask(Player player, int taskModelId){
		if(player.getCurrentMainTasks().size()>0&&player.getCurrentMainTasks().get(0).getModelid()==taskModelId){
			action(player, Task.ACTION_TYPE_ACTION,0,0);
		}
	}
	
	/**
	 * 获取地图中指定剧情怪物
	 * @param player
	 * @param monsterModelId
	 * @return
	 */
	public List<Monster> getStoryMonster(Player player, int monsterModelId){
		List<Monster> monsters = new ArrayList<Monster>();
		Map map = ManagerPool.mapManager.getMap(player);
		if(map==null){
			return monsters;
		}
		
		for (Monster monster : map.getMonsters().values()) {
			if(monster.getModelId() == monsterModelId && monster.canSee(player)){
				monsters.add(monster);
			}
		}
		return monsters;
	}
	
	/**
	 * 立即完成主线任务
	 * @param player
	 * @param modelId
	 */
	public void finishMainTask(Player player, int modelId){
		if(player.getCurrentMainTasks()!=null && player.getCurrentMainTasks().size()>0){
			MainTask mainTask = player.getCurrentMainTasks().get(0);
			if(mainTask.getModelid() == modelId) mainTask.finshTask(null);
		}
	}
	
	/**
	 * 花费元宝改变任务接取次数上限
	 * @return
	 */
	public void reqTaskGoldAddNumToServer(Player player, ReqTaskGoldAddNumMessage message) {
		if (player == null) {
			logger.error(String.format("玩家不存在，消息id[%d]，Role列表[%s]", message.getId(), JSON.toJSONString(message.getRoleId())));
			return;
		}
		if (message.getAddnum() == 0) {
			return;
		}
		if (message.getAddnum() > 10 || player.getConquerTaskMaxCount() + message.getAddnum() > 10) {
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("您今天最大只能增加%d次讨伐任务次数上限"), 10));
			return;
		}
		int costGold = message.getAddnum() * 10;
		if (!BackpackManager.getInstance().checkGold(player, costGold)) {
			MessageUtil.notify_player(player, Notifys.ERROR, String.format(ResManager.getInstance().getString("您的元宝不足%s，不能增加讨伐任务次数上限。"), String.valueOf(costGold)));
			return;
		}
		long actionid = Config.getId();
		BackpackManager.getInstance().changeGold(player, -costGold, Reasons.def12, actionid);
		player.setConquerTaskMaxCount(player.getConquerTaskMaxCount() + message.getAddnum());
		MessageUtil.notify_player(player, Notifys.SUCCESS, String.format(ResManager.getInstance().getString("您成功增加%d次讨伐任务次数上限"), message.getAddnum()));
		ResTaskGoldAddNumMessage sendMessage = new ResTaskGoldAddNumMessage();
		sendMessage.setTasktype(message.getTasktype());
		sendMessage.setNowmaxnum(TaskManager.CONQUERTASK_DAYMAXACCEPT + player.getConquerTaskMaxCount());
		MessageUtil.tell_player_message(player, sendMessage);
	}
	
	
}
