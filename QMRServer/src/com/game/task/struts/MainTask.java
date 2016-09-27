package com.game.task.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.collect.manager.CollectManager;
import com.game.data.bean.Q_task_mainBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.pet.manager.PetOptManager;
import com.game.pet.manager.PetScriptManager;
import com.game.player.manager.PlayerAttributeManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.player.structs.AttributeChangeReason;
import com.game.prompt.structs.Notifys;
import com.game.rank.manager.RankManager;
import com.game.rank.structs.RankType;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Reasons;
import com.game.task.bean.MainTaskInfo;
import com.game.task.bean.TaskAttribute;
import com.game.task.log.MainTaskLog;
import com.game.task.manager.TaskManager;
import com.game.task.message.ResMainTaskChangeMessage;
import com.game.task.message.ResTaskFinshMessage;
import com.game.task.script.IMainTaskFinishTaskAction;
import com.game.task.script.ITaskRewardsScript;
import com.game.utils.MessageUtil;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;

public class MainTask extends Task {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MainTask.class);
	
	private int modelid=0;
	
	public int getModelid() {
		return modelid;
	}
	public void setModelid(int modelid) {
		this.modelid = modelid;
	}
	@Override
	public void finshTask(Player player) {
		if (logger.isDebugEnabled()) {
			logger.debug("finshTask() - start");
		}
		if (player == null) {
			player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		}
		if (player == null) {
			return;
		}
		if (player.getId() != getOwerId()) {
			return;
		}
//		Player player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		String beforereceiveAble=JSONserializable.toString(player.getTaskRewardsReceiveAble());		
		player.getCurrentMainTasks().remove(this);
		ResTaskFinshMessage msg=new ResTaskFinshMessage();
		msg.setModelId(getModelid());
		msg.setType(Task.MAINTASK);
		MessageUtil.tell_player_message(player, msg);
		try{
			dealResume();
			dealRewards();			
		}catch (Exception e) {
			logger.error(e,e);
		}

//		try{ 用于药袋子任务调试
//			if(getModelid()==10180){
//				throw new Exception("玩家(" + player.getId() + ")完成药袋子任务调用！");
//			}
//		}catch (Exception e) {
//			logger.error(e,e);
//		}
		if(logger.isDebugEnabled()){
			logger.debug(getModelid()+"主线任务结束");
		}
		MainTaskLog log=new MainTaskLog();
		try{
			log.setRoleId(player.getId());
			log.setFinishafterReceiveAble(JSONserializable.toString(player.getTaskRewardsReceiveAble()));
			log.setFinishbeforeReceiveAble(beforereceiveAble);
			log.setFinishmodelId(getModelid());
			log.setFinishtaskInfo(JSONserializable.toString(this));
			log.setFinishlevel(player.getLevel());
			log.setFinishonlinetime(player.getAccunonlinetime());
			log.setUserId(player.getUserId());
		}catch (Exception e) {
			logger.error(e,e);
		}
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if (model == null) {
			logger.error(getModelid() + "主线任务模型找不着", new NullPointerException());
			return;
		}
		int q_next_task = model.getQ_next_task();
		if(q_next_task!=0){
			TaskManager.getInstance().acceptMainTask(player, q_next_task);	
		}
		PlayerAttributeManager.getInstance().countPlayerAttribute(player, PlayerAttributeType.TASK_CHATPER);
		try {
			MainTask accept = player.getCurrentMainTasks().get(0);
			if(accept!=null){
				log.setAcceptafterReceiveAble(JSONserializable.toString(player.getTaskRewardsReceiveAble()));
				log.setAcceptbeforeReceiveAble(beforereceiveAble);
				log.setAcceptmodelId(accept.getModelid());
				log.setAccepttaskInfo(JSONserializable.toString(accept));
				log.setAcceptlevel(player.getLevel());
				log.setAcceptonlinetime(player.getAccunonlinetime());
			}
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e,e);
		}
		try{
			IMainTaskFinishTaskAction finishTask=(IMainTaskFinishTaskAction) ScriptManager.getInstance().getScript(ScriptEnum.TASK_FINISHAFTER);
			if(finishTask==null){
				logger.info("完成主线任务脚本找不到");
			}else{
				finishTask.finishMainTaskAfter(player, this);
			}
		}catch (Exception e) {
			logger.error(e,e);
		}
		try{
			CollectManager.getInstance().sendCollectInfo(player,(byte)0);
		}catch (Exception e) {
			logger.error(e,e);
		}
		if(getModelid()==TaskManager.ACTIVITY_NEED_MAINTASK){
			player.setActivityDailyTask(true);
			TaskManager.getInstance().acceptDailyTask(player);
		}
		PetScriptManager.getInstance().finshTask(player);
	}
	private void init(){
		if (logger.isDebugEnabled()) {
			logger.debug("init() - start"+getModelid());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("init() - end"+getModelid());
		}
		
	}

	@Override
	public void giveUpTask() {
		//主线任务不能放弃 
	}
	
	@Override
	public byte acqType() {
		return MAINTASK;
	}
	@Override
	public byte deliveryType() {
		Q_task_mainBean q_task_mainBean = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if(q_task_mainBean==null){
			logger.error(getModelid()+"主线任务模型找不着");
			return (byte)1;
		}
		byte returnbyte = (byte)q_task_mainBean.getQ_finsh_type();
		return returnbyte;
	}
	@Override
	protected void dealResume() {
		Player onLinePlayer = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		if(onLinePlayer!=null){
			Q_task_mainBean taskModel = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
			if(taskModel==null){
				logger.error(getModelid()+"主线任务模型找不着",new NullPointerException());
				return;
			}
			Set<Integer> keySet = endNeedGoods().keySet();
			for (Integer key : keySet) {
				Integer num= endNeedGoods().get(key);
				BackpackManager.getInstance().removeItem(onLinePlayer,key,num,Reasons.TASKRESUME,getId());
			}
			String q_end_resume_goods = taskModel.getQ_end_resume_goods();
			StringBuilder builder=new StringBuilder();
			if(q_end_resume_goods!=null&&!"".equals(q_end_resume_goods)){
				String[] split = q_end_resume_goods.split(Symbol.FENHAO_REG);
				for (String string : split) {
					if(string!=null&&!"".equals(string)){
						String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
						int goodsmodel = Integer.parseInt(split2[0]);
						int goodsnum = Integer.parseInt(split2[1]);
//						Q_itemBean model = DataManager.getInstance().q_itemContainer.getMap().get(goodsmodel);
						String name=BackpackManager.getInstance().getName(goodsmodel);
						builder.append(name).append("(").append(goodsnum).append("),");
						BackpackManager.getInstance().removeItem(onLinePlayer, goodsmodel,goodsnum,Reasons.TASKRESUME,getId());	
					}
				}	
			}
			if(builder.length()>0){
				String substring = builder.substring(0, builder.length()-1);
				MessageUtil.notify_player(onLinePlayer,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("主线任务扣除物品:{1}"),substring);
			}
		}
		
	}
	@Override
	protected void dealRewards() {
		Q_task_mainBean taskModel = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if(taskModel==null){
			logger.error(getModelid()+"主线任务模型找不着",new NullPointerException());
			return;
		}		
		String q_rewards_achieve = taskModel.getQ_rewards_achieve();
		Player player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		int q_rewards_pet = taskModel.getQ_rewards_pet();
		if(q_rewards_pet!=0){
			PetOptManager.getInstance().addPet(player, q_rewards_pet,"maintask",getId());	
		}
		if (!StringUtil.isBlank(q_rewards_achieve)) {
			String[] split = q_rewards_achieve.split(Symbol.FENHAO_REG);
			for (String string : split) {
				if (StringUtils.isBlank(string)) {
					continue;
				}
				@SuppressWarnings("unused")
				int parseInt = Integer.parseInt(string);
				// TODO 奖励成就
			}
		}
		StringBuilder builder=new StringBuilder();
		StringBuilder goodsrewards=new StringBuilder();
		//奖励绑定元宝
		int q_rewards_bindYuanBao = taskModel.getQ_rewards_bindYuanBao();
		if(q_rewards_bindYuanBao>0){
			BackpackManager.getInstance().changeBindGold(player,q_rewards_bindYuanBao,Reasons.TASKREWARDS,getId());
			builder.append(String.format(ResManager.getInstance().getString("礼金(%s),"), q_rewards_bindYuanBao));
		}
		
		//奖励金币
		int q_rewards_coin = taskModel.getQ_rewards_coin();
		if(q_rewards_coin>0){
			BackpackManager.getInstance().changeMoney(player, q_rewards_coin,Reasons.TASKREWARDS,getId());
			builder.append(String.format(ResManager.getInstance().getString("铜币(%s),"), q_rewards_coin));
		}
		
		//奖励 经验
		int q_rewards_exp = taskModel.getQ_rewards_exp();
		if(q_rewards_exp>0){
			PlayerManager.getInstance().addExp(player, q_rewards_exp, AttributeChangeReason.TASKREWARDS);
			builder.append(String.format(ResManager.getInstance().getString("经验(%s),"), q_rewards_exp));
		}
		int q_rewards_exploit = taskModel.getQ_rewards_exploit();
		
		if(q_rewards_exploit>0){
			RankManager.getInstance().addranknum(player, taskModel.getQ_rewards_exploit(),RankType.TASK);
			builder.append(String.format(ResManager.getInstance().getString("军功(%s),"), q_rewards_exploit));
		}
		//奖励声望
		int q_rewards_prestige = taskModel.getQ_rewards_prestige();
		if(q_rewards_prestige>0){
			PlayerManager.getInstance().addBattleExp(player, q_rewards_prestige, AttributeChangeReason.TASKREWARDS);
			builder.append(String.format(ResManager.getInstance().getString("声望(%s),"), q_rewards_prestige));
		}
		//奖励 真气
		int q_rewards_zq = taskModel.getQ_rewards_zq();
		if(q_rewards_zq>0){
			PlayerManager.getInstance().addZhenqi(player, q_rewards_zq,AttributeChangeReason.TASKREWARDS);
			builder.append(String.format(ResManager.getInstance().getString("真气(%s),"), q_rewards_zq));
		}
		
//		String q_chapter_buff = taskModel.getQ_chapter_buff();
//		if(q_chapter_buff!=null&&!q_chapter_buff.equals("")){
//			int parseInt = Integer.parseInt(q_chapter_buff);
//			if(parseInt>0){
//				BuffManager.getInstance().addBuff(player, player, parseInt, 0, 0,0);
//			}
//		}
		// 任务奖励物品序列（物品ID_数量_强化等级_附加属性类型|附加属性比例;物品ID_数量;物品ID_数量）
		String q_rewards_goods = taskModel.getQ_rewards_goods();
		if (!StringUtil.isBlank(q_rewards_goods)) {
			String[] goods = q_rewards_goods.split(Symbol.FENHAO_REG);
			for (String goodsExpress : goods) {
				if (goodsExpress != null && !goodsExpress.equals("")) {
					String[] items = goodsExpress.split(Symbol.XIAHUAXIAN_REG);
					if (items.length >= 2) {
						boolean isbind = true;
						if (items[0].startsWith("!")) {
							isbind = false;
							items[0] = items[0].substring(1, items[0].length());
						}
						int modelid = Integer.parseInt(items[0]);
						int num = Integer.parseInt(items[1]);
						int qianghua = 0;
						int sex = 0;
						if (items.length >= 3) {
							sex = Byte.parseByte(items[2]);
						}

						if (items.length >= 4) {
							qianghua = Integer.parseInt(items[3]);
						}
						String append = "";
						if (items.length >= 5) {
							append = items[4];
						}
						if (player.getSex() == sex || sex == 0) {
							append = append.replace(Symbol.DOUHAO, Symbol.FENHAO);
							// Q_itemBean model =
							// DataManager.getInstance().q_itemContainer.getMap().get(modelid);
							String name = BackpackManager.getInstance().getName(modelid);
							goodsrewards.append(name).append("(").append(num).append("),");
							List<Item> createItems = Item.createItems(modelid, num, isbind, 0, qianghua, append);
							List<Item> spilthGoods = new ArrayList<Item>();
							for (Item item : createItems) {
								if (BackpackManager.getInstance().hasAddSpace(player, item)) {
									BackpackManager.getInstance().addItem(player, item, Reasons.TASKREWARDS, getId());
								} else {
									spilthGoods.add(item);
								}
							}
							BackpackManager.getInstance().addAbleReceieve(player, spilthGoods);
						}
					}
				}
			}
		}
		if(builder.length()>0){
			String substring = builder.substring(0, builder.length()-1);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("获得主线任务奖励:{1}"),substring);
		}
		if(goodsrewards.length()>0){
			String substring = goodsrewards.substring(0,goodsrewards.length());
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("获得主线任务奖励物品:{1}"),substring);
		}
		if(taskModel.getQ_rewards_scrpt()!=0){
			try{
				ITaskRewardsScript script=(ITaskRewardsScript) ScriptManager.getInstance().getScript(taskModel.getQ_rewards_scrpt());
				if(script!=null){
					script.rewards(player, this);	
				}else{
					logger.error("任务奖励脚本找不到脚本ID:"+taskModel.getQ_rewards_scrpt());
				}
			}catch (Exception e) {
				logger.error(e,e);
			}
		}
	}
	public void initTask(Player player, int modelId) {
		this.modelid=modelId;
		this.owerId=player.getId();
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelId);
		if(model.getQ_end_needaction()==1){
			isFinshAction=false;
		}
		init();
		player.setCurrentMainTaskId(modelId);
		player.getCurrentMainTasks().add(this);
		changeTask();
	}
	public MainTaskInfo buildTaskInfo() {
		MainTaskInfo info=new MainTaskInfo();
		info.setModelId(getModelid());
		info.setIsFinshAction((byte)(isFinshAction()?1:0));
		Player player = PlayerManager.getInstance().getPlayer(getOwerId());
		for (Integer achieveModel : endNeedAchieve()) {
			//TODO  任务条件  成就
			info.getPermiseAchieve().add(achieveModel);
		}
		Set<Integer> keySet = endNeedGoods().keySet();
		for (Integer key : keySet) {
			Integer need = endNeedGoods().get(key);
			int num = BackpackManager.getInstance().getItemNum(player,key);
			if(num>need){
				num=need;
			}
			TaskAttribute taskAttribute = new TaskAttribute();
			taskAttribute.setModel(key);
			taskAttribute.setNum(num);
			info.getPermiseGoods().add(taskAttribute);
		}
		Set<Integer> keySet2 = endNeedKillMonster().keySet();
		for (Integer key : keySet2) {
			if( getKillmonsters().containsKey(String.valueOf(key))){
				Integer integer = getKillmonsters().get(String.valueOf(key));
				TaskAttribute taskAttribute=new TaskAttribute();
				taskAttribute.setModel(key);
				taskAttribute.setNum(integer);
				info.getPermiseMonster().add(taskAttribute);
			}
		}		
		return info;
	}
	@Override
	public void changeTask() {
		Player player = PlayerManager.getInstance().getPlayer(getOwerId());
		if(player!=null){
			ResMainTaskChangeMessage msg=new ResMainTaskChangeMessage();
			msg.setTask(buildTaskInfo());
			MessageUtil.tell_player_message(player, msg);	
		}else{
			logger.error("切换主线任务("+this.getModelid()+")时所属角色("+this.getOwerId()+")找不到");
		}
		
	}
	
	@Override
	public HashMap<Integer, Integer> endNeedKillMonster() {
		HashMap<Integer, Integer> result=new HashMap<Integer, Integer>();
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if(model==null){
			logger.error(getModelid()+"主线任务模型找不着",new NullPointerException());
			return result;
		}
//		model.getQ_end_need_horselevel();
//		model.getQ_end_need_conquertaskcount();
//		model.getQ_end_need_dailytaskcount();

		String q_end_need_killmonster = model.getQ_end_need_killmonster();
		if(q_end_need_killmonster!=null&&!"".equals(q_end_need_killmonster)){
			String[] split = q_end_need_killmonster.split(Symbol.FENHAO_REG);
			for (String string : split) {
				if(string.startsWith("@")){
					string=string.replace("@","");
				}
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
		HashSet<Integer> result=new HashSet<Integer>();
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if(model==null){
			logger.error(getModelid()+"主线任务模型找不着",new NullPointerException());
			return result;
		}
		String q_end_need_achieve = model.getQ_end_need_achieve();
		if(q_end_need_achieve!=null&&!"".equals(q_end_need_achieve)){
//			if(q_end_need_achieve.startsWith("@")){
//				q_end_need_achieve=q_end_need_achieve.replace("@","");
//			}
			String[] achieves = q_end_need_achieve.split(Symbol.FENHAO);
			for (String achieve : achieves) {
				if(achieve.startsWith("@")){
					achieve=achieve.replace("@","");
				}
				String[] split2 = achieve.split(Symbol.XIAHUAXIAN_REG);
				int parseInt = Integer.parseInt(split2[0]);
				result.add(parseInt);
			}
		}
		return result;
	}
	@Override
	public HashMap<Integer, Integer> endNeedGoods() {
		HashMap<Integer,Integer> result=new HashMap<Integer, Integer>();
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if(model==null){
			logger.error(getModelid()+"主线任务模型找不着",new NullPointerException());
			return result;
		}
		String q_end_need_goods = model.getQ_end_need_goods();
		if(q_end_need_goods!=null&&!"".equals(q_end_need_goods)){
//			if(q_end_need_goods.startsWith("@")){
//				q_end_need_goods=q_end_need_goods.replace("@","");
//			}
			String[] split = q_end_need_goods.split(Symbol.FENHAO_REG);
			for (String string : split) {
				if(string.startsWith("@")){
					string=string.replace("@","");
				}
				String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
				int parseInt = Integer.parseInt(split2[0]);
				int parseInt2 = Integer.parseInt(split2[1]);
				result.put(parseInt,parseInt2);
			}
		}
		return result;
	}
	@Override
	public int endNeedHorseLevel() {
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if(model==null){
			return 0;
		}
		return model.getQ_end_need_horselevel();
	}
	@Override
	public int endNeedConquerTaskCount() {
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if(model==null){
			return 0;
		}
		return model.getQ_end_need_conquertaskcount();
	}
	@Override
	public int endNeedDailyTaskCount() {
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if(model==null){
			return 0;
		}
		return model.getQ_end_need_dailytaskcount();
	}
	
	@Override
	public boolean checkFinsh(boolean isPromp,Player player) {
		if(player==null){
			player= PlayerManager.getInstance().getOnLinePlayer(getOwerId());	
		}
		if(player==null){
			return false;
		}
		if(player.getId()!=getOwerId()){
			return false;
		}
		Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(modelid);
		if(model==null){
			logger.error(getModelid()+"主线任务模型找不着",new NullPointerException());
			return true;
		}
		if(player.getTaskRewardsReceiveAble().size()>0){
			MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("请先领走领取区域的物品"));
			return false;
		}
		if(player.getLevel()<model.getQ_accept_needmingrade()){
			MessageUtil.notify_player(player,Notifys.NORMAL,ResManager.getInstance().getString("需要{1}级才可开始此任务"),model.getQ_accept_needmingrade()+"");
			return false;
		}
		return super.checkFinsh(isPromp,player);
	}
	
	
}
