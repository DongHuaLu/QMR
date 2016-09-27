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
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_task_conquerBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.dblog.base.Log;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.pet.manager.PetScriptManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.player.structs.AttributeChangeReason;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.task.bean.ConquerTaskInfo;
import com.game.task.bean.TaskAttribute;
import com.game.task.log.ConquerTaskFinishLog;
import com.game.task.manager.TaskManager;
import com.game.task.message.ResConquerTaskChangeMessage;
import com.game.task.message.ResTaskFinshMessage;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

public class ConquerTask extends Task {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConquerTask.class);
	
	private static final long serialVersionUID = 1L;
	//奖励物品 需要在接受任务时就将物品随机属性计算出
	private List<Item> rewardsGoods=new ArrayList<Item>();
	private List<Integer> rewardsAchieve=new ArrayList<Integer>();
	private int exp=0;
	private int copper=0;
	private int zhengqi=0;
	private int shengwang=0;
	private int bindgold=0;
	private int sid;
	@Log(logField="sid",fieldType="int")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getBindgold() {
		return bindgold;
	}
	public void setBindgold(int bindgold) {
		this.bindgold = bindgold;
	}
	public List<Integer> getRewardsAchieve() {
		return rewardsAchieve;
	}
	public void setRewardsAchieve(List<Integer> rewardsAchieve) {
		this.rewardsAchieve = rewardsAchieve;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getCopper() {
		return copper;
	}
	public void setCopper(int copper) {
		this.copper = copper;
	}
	public int getZhengqi() {
		return zhengqi;
	}
	public void setZhengqi(int zhengqi) {
		this.zhengqi = zhengqi;
	}
	public int getShengwang() {
		return shengwang;
	}
	public void setShengwang(int shengwang) {
		this.shengwang = shengwang;
	}
	private int modelid=0;
	
	private long acceptTime=0;
	
	public int getModelid() {
		return modelid;
	}
	public void setModelid(int modelid) {
		this.modelid = modelid;
	}
	public List<Item> getRewardsGoods() {
		return rewardsGoods;
	}
	public void setRewardsGoods(List<Item> rewardsGoods) {
		this.rewardsGoods = rewardsGoods;
	}
		
	public long getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(long acceptTime) {
		this.acceptTime = acceptTime;
	}
	
	@Override
	public void finshTask(Player player) {
		if (logger.isDebugEnabled()) {
			logger.debug("finshTask() - start");
		}
		if (player == null) {
			player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		}
		if(player==null){
			return;
		}
		if(player.getId()!=getOwerId()){
			return;
		}
		player.getCurrentConquerTasks().remove(this);

		String beforeReceiveAble = JSONserializable.toString(player.getTaskRewardsReceiveAble());
		
		ResTaskFinshMessage msg=new ResTaskFinshMessage();
		msg.setConquerTadkId(getId());
		msg.setModelId(getModelid());
		msg.setType(Task.CONQUERTASK);
		MessageUtil.tell_player_message(player, msg);
		ManagerPool.countManager.addCount(player, CountTypes.CONQUERTASK_FINSH, CountTypes.CONQUERTASK_FINSH.getValue(), CountManager.COUNT_DAY, 1, 0);
		dealResume();
		dealRewards();
		TaskManager.getInstance().action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.COMPLETECONQUERTASK, 1);
		if (logger.isDebugEnabled()) {
			logger.debug("finshTask() - end");
		}
		try {
			ConquerTaskFinishLog log=new ConquerTaskFinishLog();
			log.setRoleid(player.getId());
			log.setTaskid(getId());
			log.setTaskInfo(JSONserializable.toString(this));
			log.setTaskRewardsReceiveAble(JSONserializable.toString(player.getTaskRewardsReceiveAble()));
			log.setBeforeTaskRewardsReceiveable(beforeReceiveAble);
			log.setSid(player.getCreateServerId());
			LogService.getInstance().execute(log);
		} catch (Exception e) {
			logger.error(e,e);
		}
		PetScriptManager.getInstance().finshTask(player);
	}
	@Override
	public void giveUpTask() {
		//讨伐任务不能放弃 
	}
	
	@Override
	public byte acqType() {
		return CONQUERTASK;
	}
	@Override
	public byte deliveryType() {
		return COMMIT_AUTO;
	}
	@Override
	protected void dealResume() {
	}
	@Override
	protected void dealRewards() {
		StringBuilder builder=new StringBuilder();
		StringBuilder goodsrewards=new StringBuilder();
		Q_task_conquerBean model = DataManager.getInstance().q_task_conquerContainer.getMap().get(modelid);
//		PlayerManager.getInstance().getPlayer(getOwerId());
		Player player = PlayerManager.getInstance().getPlayer(getOwerId());
//		Player player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		if(model==null){
			logger.error(getModelid()+"讨伐任务模型找不到",new Exception());
		}else{
			String q_rewards_achieve = model.getQ_rewards_achieve();
			
			String[] split = q_rewards_achieve.split(Symbol.FENHAO_REG);
			for (String string : split) {
				if(StringUtils.isBlank(string)){
					continue;
				}
				@SuppressWarnings("unused")
				int parseInt = Integer.parseInt(string);
				//TODO 奖励成就
			}			
		}
		//奖励绑定元宝
		int q_rewards_bindYuanBao =getBindgold(); 
				//model.getQ_rewards_bindyuanbao();
		if(q_rewards_bindYuanBao>0){
			BackpackManager.getInstance().changeBindGold(player,q_rewards_bindYuanBao,Reasons.TASKREWARDS,getId());
			builder.append(String.format(ResManager.getInstance().getString("礼金(%s),"), q_rewards_bindYuanBao));
		}
		
		//奖励金币
		int q_rewards_coin = getCopper();
		if(q_rewards_coin>0){
			BackpackManager.getInstance().changeMoney(player, q_rewards_coin,Reasons.TASKREWARDS,getId());
			builder.append(String.format(ResManager.getInstance().getString("铜币(%s),"), q_rewards_coin));
		}
		
		//奖励 经验
		int q_rewards_exp = getExp();
		if(q_rewards_exp>0){
			PlayerManager.getInstance().addExp(player, q_rewards_exp, AttributeChangeReason.TASKREWARDS);
			builder.append(String.format(ResManager.getInstance().getString("经验(%s),"), q_rewards_exp));
		}


		//奖励声望
		int q_rewards_prestige = getShengwang();
		if(q_rewards_prestige>0){
			PlayerManager.getInstance().addBattleExp(player, q_rewards_prestige, AttributeChangeReason.TASKREWARDS);
			builder.append(String.format(ResManager.getInstance().getString("声望(%s),"), q_rewards_prestige));
		}
		//奖励 真气
		int q_rewards_zq = getZhengqi();
		if(q_rewards_zq>0){
			PlayerManager.getInstance().addZhenqi(player, q_rewards_zq,AttributeChangeReason.TASKREWARDS);
			builder.append(String.format(ResManager.getInstance().getString("真气(%s),"), q_rewards_zq));
		}	
		//任务奖励物品序列（物品ID_数量_强化等级_附加属性类型|附加属性比例;物品ID_数量;物品ID_数量）
		List<Item> rewardsGoods = getRewardsGoods();
		List<Item> spilthGoods=new ArrayList<Item>();		
		for (Item item : rewardsGoods) {
//			Q_itemBean itemmodel = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
			goodsrewards.append(BackpackManager.getInstance().getName(item.getItemModelId())).append("(").append(item.getNum()).append("),");	
			int grid = BackpackManager.getInstance().getBackpackAbleAddGridId(player, item);
			if(grid!=-1){
				BackpackManager.getInstance().addItem(player, item,Reasons.TASKREWARDS,getId());
			}else{
				spilthGoods.add(item);
			}
		}
		BackpackManager.getInstance().addAbleReceieve(player, spilthGoods);
		if(builder.length()>0){
			String substring = builder.substring(0, builder.length()-1);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("完成讨伐任务获得奖励:{1}"),substring);
		}
		if(goodsrewards.length()>0){
			String substring = goodsrewards.substring(0, goodsrewards.length()-1);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("完成讨伐任务获得奖励:{1}"),substring);
		}
	}
	
	public void initTask(Player player, int goodModel) {
		if (logger.isDebugEnabled()) {
			logger.debug("init() - start");
		}
		setOwerId(player.getId());
		acceptTime=System.currentTimeMillis();
		Q_itemBean q_itemBean = DataManager.getInstance().q_itemContainer.getMap().get(goodModel);
		List<Q_task_conquerBean> list = DataManager.getInstance().q_task_conquerContainer.getList();
		List<Q_task_conquerBean> result=new ArrayList<Q_task_conquerBean>();
		for (Q_task_conquerBean model : list) {
			//指定卷轴颜色在等级区间 的任务模型
			if(model.getQ_mingrade()<=player.getLevel()&&player.getLevel()<=model.getQ_maxgrade()&&q_itemBean.getQ_default()==model.getQ_scroll_type()){
				result.add(model);
			}
		}
		Q_task_conquerBean model= result.get(RandomUtils.random(result.size()));
		modelid=model.getQ_id();
		buidRewards(model);
		if (logger.isDebugEnabled()) {
			logger.debug("init() - end");
		}
		
		player.setConquerTaskCount(player.getConquerTaskCount()+1);
		player.setConquerTaskTime(System.currentTimeMillis());
		player.getCurrentConquerTasks().add(this);
	}
	
	private List<Item> buildExcludeRewards(String express){
		if(express==null||express.equals("")){
			return null;
		}
		String[] split = express.split(Symbol.FENHAO_REG);
		List<String> item=new ArrayList<String>();
		List<Integer> prob=new ArrayList<Integer>();
		for (String string : split) {
			item.add(string);
			String[] element = string.split(Symbol.XIAHUAXIAN_REG);
			int itemprob = Integer.parseInt(element[0]);
			prob.add(itemprob);
			
//			男互斥几率_道具ID_数量_性别（0通，1男，2女）_绑定（0,1绑定）_消失时间_强化等级_N5(附加属性条数)或者附加属性(类型|值);
		}
		int index = RandomUtils.randomIndexByProb(prob);
		if(index==-1){
			logger.error("机率计算出错"+prob);
			return null;
		}
		String string = item.get(index);
		List<Item> createItems =null;
		if(!StringUtils.isBlank(string)){
			String[] element = string.split(Symbol.XIAHUAXIAN_REG);
//			int itemprob=Integer.parseInt(element[0]);
			int itemmodel=Integer.parseInt(element[1]);
			int num = Integer.parseInt(element[2]);
//			int sex=Integer.parseInt(element[3]);
			boolean isbind=Integer.parseInt(element[4])==1;
			long losttime=Long.parseLong(element[5]);
			int gradenum = Integer.parseInt(element[6]);
			if(element.length>=8&&!StringUtils.isBlank(element[7])){
				if(element[7].startsWith("n")||element[7].startsWith("N")){
					String appendnum=element[7].substring(1, element[7].length());
					int appendNum = Integer.parseInt(appendnum);
					createItems = Item.createItems(itemmodel, num, isbind, losttime,gradenum,appendNum);
				}else{
					createItems = Item.createItems(itemmodel, num, isbind, losttime,gradenum,element[7]);
				}
			}
		}
//		互斥几率_道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);
		return createItems;
	}
	
	
	private void buidRewards(Q_task_conquerBean model){
//		任务奖励物品序列（物品ID_数量_性别需求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;物品ID_数量_性别需求;物品ID_数量）
		rewardsGoods.clear();
		Player player = PlayerManager.getInstance().getPlayer(getOwerId());
//				PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		if(player.getSex()==1){
			//互斥男性奖励
			String manRewards= model.getQ_man_rewards_goods();
			List<Item> item= buildExcludeRewards(manRewards);
			if(item!=null&&item.size()>0){
				rewardsGoods.addAll(item);
			}
		}else{
			//互斥女性奖励
			String manRewards= model.getQ_women_rewards_goods();
			List<Item> item= buildExcludeRewards(manRewards);
			if(item!=null&&item.size()>0){
				rewardsGoods.addAll(item);
			}
		}
		
		//无互斥机率的奖励项
		String rewardsExpress=model.getQ_rewards_goods();
		String[] split = rewardsExpress.split(Symbol.FENHAO_REG);
		List<Item> result=new ArrayList<Item>();
		
		for (String string : split) {
			if(StringUtils.isBlank(string)){
				continue;
			}
			boolean isbind=true;
			String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
			if(split2[0].startsWith("!")){
				isbind=false;
				split2[0]=split2[0].substring(1,split2[0].length());
			}
			int goodsModel= Integer.parseInt(split2[0]);
			int num= Integer.parseInt(split2[1]);
			int qianghua=0;
			int sex=0;
			if(split2.length>=3){
				sex = Byte.parseByte(split2[2]);	
			}
			if(split2.length>=4){
				qianghua=Integer.parseInt(split2[3]);	
			}
			String append="";
			if(split2.length>=5){
				append=split2[4];
			}
			if(player.getSex()==sex){
				append = append.replace(Symbol.DOUHAO,Symbol.FENHAO);
				List<Item> createItems = Item.createItems(goodsModel, num, isbind,0,qianghua, append);
				result.addAll(createItems);
			}
		}
		setExp(model.getQ_rewards_exp());
		setCopper(model.getQ_rewards_coin());
		setShengwang(model.getQ_rewards_prestige());
		setZhengqi(model.getQ_rewards_zq());
		setBindgold(model.getQ_rewards_bindyuanbao());
		if (logger.isDebugEnabled()) {
			logger.debug("buildRewards(String) - end");
		}
		rewardsGoods.addAll(result);
	}
	@SuppressWarnings("unused")
	public ConquerTaskInfo buildTaskInfo() {
		Player player=PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		ConquerTaskInfo info=new ConquerTaskInfo();
		info.setModelId(getModelid());
		info.setId(getId());
		for (Integer achieveModel : endNeedAchieve()) {
			//TODO  任务条件  成就
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
			Integer integer = killmonsters.get(String.valueOf(key));
//			if(integer!=null){
				TaskAttribute taskAttribute=new TaskAttribute();
				taskAttribute.setModel(key);
				taskAttribute.setNum(integer==null?0:integer);
				info.getPermiseMonster().add(taskAttribute);	
//			}
		}
		for (Item item : rewardsGoods) {
			info.getRewards().add(item.buildItemInfo());
		}
		info.setRewardsBindGold(getBindgold());
		info.setRewardsCopper(getCopper());
		info.setRewardsExp(getExp());
		info.setRewardsSW(getShengwang());
		info.setRewardsZQ(getZhengqi());
		return info;
	}
	@Override
	public void changeTask() {
		Player player = PlayerManager.getInstance().getPlayer(getOwerId());
		ResConquerTaskChangeMessage msg=new ResConquerTaskChangeMessage();
		msg.setConquerTaskAcceptCount(player.getConquerTaskCount());
		msg.setConquerTaskAcceptMaxCount(TaskManager.CONQUERTASK_DAYMAXACCEPT + player.getConquerTaskMaxCount());
		msg.setDevourCount(player.getDaydevourcount());
		msg.setTask(buildTaskInfo());
		MessageUtil.tell_player_message(player, msg);
	}
	
	/**
	 * 是否作用于本条任务
	 * @param actionType
	 * @param model
	 * @param value
	 * @return	可作用数量
	 */
	public int isAction(short actionType,int model){
		Player player = PlayerManager.getInstance().getPlayer(getOwerId());
		switch (actionType) {
		case ACTION_TYPE_GOODS:
			if(!endNeedGoods().keySet().contains(model)){
				return 0;
			}
			Integer neednum = endNeedGoods().get(model);
			int itemNum = BackpackManager.getInstance().getItemNum(player,model);
			if(itemNum>=neednum){
				return 0;
			}
			return neednum-itemNum;
		case ACTION_TYPE_KILLMONSTER:
			if(!endNeedKillMonster().keySet().contains(model)){
				return 0;
			}
			Integer need = endNeedKillMonster().get(model);
			Integer num = killmonsters.get(String.valueOf(model));
			num=num==null?0:num;
			if (num>=need) {
				return 0;
			}
			return need-num;
		case ACTION_TYPE_ACHIEVE:
			if(!endNeedAchieve().contains(model)){
				return 0;
			}
			return 1;
		}
		return 0;
	}
	
	/**
	 * 所需数量
	 * @param actionType
	 * @param model
	 * @return
	 */
	public int needNum(short actionType,int model){
		Integer neednum=0;
		switch (actionType) {
		case ACTION_TYPE_GOODS:
			if(!endNeedGoods().keySet().contains(model)){
				return 0;
			}
			neednum = endNeedGoods().get(model);
			return neednum==null?0:neednum;
		case ACTION_TYPE_KILLMONSTER:
			if(!endNeedKillMonster().keySet().contains(model)){
				return 0;
			}
			neednum = endNeedKillMonster().get(model);
			return neednum==null?0:neednum;
		case ACTION_TYPE_ACHIEVE:
			if(!endNeedAchieve().contains(model)){
				return 0;
			}
			return 1;
		}
		return 0;
	}
	
	
	@Override
	public HashMap<Integer, Integer> endNeedKillMonster() {
		HashMap<Integer,Integer> result=new HashMap<Integer, Integer>();
		Q_task_conquerBean model = DataManager.getInstance().q_task_conquerContainer.getMap().get(getModelid());
		if(model==null){
			logger.error(getModelid()+"讨伐任务条件模型找不着",new NullPointerException());
			return result;
		}
		String q_monstercount = model.getQ_monstercount();
		String[] split = q_monstercount.split(Symbol.FENHAO_REG);
		for (String string : split) {
			if(StringUtils.isBlank(string)){
				continue;
			}
			if(string.startsWith("@")){
				string=string.replace("@","");
			}
			String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
			result.put(Integer.parseInt(split2[0]),Integer.parseInt(split2[1]));	
		}
		return result;
	}
	@Override
	public HashSet<Integer> endNeedAchieve() {
		HashSet<Integer> result=new HashSet<Integer>();
		return result;
	}
	@Override
	public HashMap<Integer, Integer> endNeedGoods() {
		HashMap<Integer,Integer> result=new HashMap<Integer, Integer>();
		return result;
	}
	public static void main(String args[]){
		String a="1saaaaa";
		System.err.println(a.substring(1,a.length()));
	}
//	@Override
//	public boolean isAction(short actionType, int model) {
//		switch (actionType) {
//		case ACTION_TYPE_GOODS:
//			if (endNeedGoods().keySet().contains(model)) {
//				Integer integer = endNeedGoods().get(model);
//				if (integer != null && integer > 0) {
//					return true;
//				}
//			}
//			break;
//		case ACTION_TYPE_KILLMONSTER:
//			if (endNeedKillMonster().keySet().contains(model)) {
//				Integer integer = endNeedKillMonster().get(model);
//				if (integer != null && integer > 0) {
//					return true;
//				}
//			}
//			break;
//		case ACTION_TYPE_ACHIEVE:
//			if(endNeedAchieve().contains(model)){
//				//TODO 需要成就系统
//				return false;
//			}
//		}
//		return false;
//	}
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
