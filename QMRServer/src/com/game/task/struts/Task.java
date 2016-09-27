package com.game.task.struts;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.count.manager.CountManager;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_monsterBean;
import com.game.data.manager.DataManager;
import com.game.horse.manager.HorseManager;
import com.game.horse.struts.Horse;
import com.game.languageres.manager.ResManager;
import com.game.object.GameObject;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.task.script.IMainTaskReachAction;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

public abstract class Task extends GameObject implements Comparator<Task>{
	/**
	 * Logger for this class
	 */
	private static transient final Logger logger = Logger.getLogger(Task.class);
	/**
	 * 自动交付
	 */
	public static transient final byte COMMIT_AUTO=1;
	/**
	 * NPC交付
	 */
	public static transient final byte COMMIT_NPC=2;
	
	/**
	 * 其它类型交付  由其它系统调用任务finsh来完成交付
	 */
	public static transient final byte COMMIT_OTHER=3;
	/**
	 * 主线任务
	 */
	public static transient final byte  MAINTASK=0;
	/**
	 * 讨伐任务
	 */
	public static transient final byte CONQUERTASK=1;
	/**
	 * 日常任务
	 */
	public static transient final byte DAILYTASK=2;
	
	/**
	 * 探宝任务
	 */
	public static transient final byte TREASUREHUNTTASK = 3;
	
	/**
	 * 军衔任务
	 */
	public static transient final byte RANKTASK = 4;
	
	/**
	 * 任务事件  杀死怪物
	 */
	public static transient final short ACTION_TYPE_KILLMONSTER=0;
	
	public static transient final short ACTION_TYPE_GOODS=1;//不需要统计直接读取包裹
	
	public static transient final short ACTION_TYPE_ACHIEVE=2;//不需要统计直接读取成就数据
	
	public static transient final short ACTION_TYPE_ACTION=3;//完成指定动作
	
	public static transient final short ACTION_TYPE_RANK=4;//军衔任务事件
	/**
	 * 
	 */
	private static transient final long serialVersionUID = 1L;
	
	/***************需要存储的块BEGIN****************/
//	protected int modelId; //模型ID
	protected long owerId; //所属角色
	//刺杀怪物数统计
	protected HashMap<String,Integer> killmonsters=new HashMap<String, Integer>();
	
	protected boolean isFinshAction=true;
	
	
	private boolean reached = false;
	
//	//完成需要刺杀怪物数
//	protected HashMap<String,Integer> endNeedKillMonster=new HashMap<String, Integer>();
//	//完成需要成就
//	protected HashSet<Integer> endNeedAchieve=new HashSet<Integer>();
//	//完成需要物品
//	protected HashMap<String,Integer> endNeedGoods=new HashMap<String, Integer>();
	
	
	
	public HashMap<String, Integer> getKillmonsters() {
		return killmonsters;
	}

	public boolean isFinshAction() {
		return isFinshAction;
	}

	public void setFinshAction(boolean isFinshAction) {
		this.isFinshAction = isFinshAction;
	}

	public void setKillmonsters(HashMap<String, Integer> killmonsters) {
		this.killmonsters = killmonsters;
	}

	public abstract HashMap<Integer, Integer> endNeedKillMonster();

	public abstract HashSet<Integer> endNeedAchieve();

	public abstract HashMap<Integer, Integer> endNeedGoods();
	
	public abstract int endNeedHorseLevel();
	
	public abstract int endNeedConquerTaskCount();
	
	public abstract int endNeedDailyTaskCount();	
		
	/***************需要存储的块END******************/
	public boolean action(short actionType,int model,int value){
		

		switch (actionType) {
		case ACTION_TYPE_GOODS:
			if (this instanceof RankTask) {
				if (!((RankTask) this).action(value <= 0 ? RankTaskEnum.USEITEM : RankTaskEnum.GETITEM, model, value)){
					return false;
				}
			} else {
				if(!endNeedGoods().keySet().contains(model)){
					return false;
				}
			}
			break;
		case ACTION_TYPE_KILLMONSTER:	
			if(endNeedKillMonster().keySet().contains(model)){
				Integer need = endNeedKillMonster().get(model);
				Integer integer = getKillmonsters().get(String.valueOf(model));
				
				integer=integer==null?0:integer;
				if(integer<need){
					integer+=value;
					if(integer>need){
						integer=need;
					}
					getKillmonsters().put(String.valueOf(model), integer);
				}
			}else{
				return false;
			}
			break;
		case ACTION_TYPE_ACHIEVE:
			if(!endNeedAchieve().contains(model)){
				return false;
			}
			break;
		case ACTION_TYPE_ACTION:
			setFinshAction(true);
			break;
		case ACTION_TYPE_RANK:
			if (this instanceof RankTask) {
				if (!((RankTask) this).action(model, 0, value)){
					return false;
				}
			}else{
				return false;
			}
			break;
		default:
			return false;
		}
		changeTask();
		boolean checkFinsh = checkFinsh(false,null);
		if(!reached && checkFinsh){
			reached = true;
			if(this instanceof MainTask){
				try{
					Player onLinePlayer = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
					//达成
					IMainTaskReachAction script=(IMainTaskReachAction) ScriptManager.getInstance().getScript(ScriptEnum.MAINTASK_REACH);
					if(script==null){
						logger.error("任务达成事件脚本找不到");
					}else{
						script.action(onLinePlayer,(MainTask) this);
					}	
				}catch (Exception e) {
					logger.error(e,e);
				}
			}
			if(deliveryType() == COMMIT_AUTO ){
				return true;
			}
		}		
		return false;
	}
	
	/**
	 * 检查是否结束
	 * @param isPromp	是否发送提示到前端
	 * @param player 
	 * @return 
	 */
	public boolean checkFinsh(boolean isPromp, Player player){
//		Player player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());
		if(player==null){
			player = PlayerManager.getInstance().getOnLinePlayer(getOwerId());	
		}
		if(player==null){
			//角色以经离线
			return false;
		}
		if(player.getId()!=getOwerId()){
			return false;
		}
		
		try{
			if(endNeedAchieve().size()>0){
				//TODO 检查成就
				if(isPromp)MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("完成该任务需要成就"));
				return false;
			}
			if(endNeedGoods().size()>0){
				Set<Integer> keySet = endNeedGoods().keySet();
				for (Integer model : keySet) {
					Integer neednum = endNeedGoods().get(model);
					int itemNum = BackpackManager.getInstance().getItemNum(player,model);
					if(itemNum<neednum){
						if(isPromp)MessageUtil.notify_player(player,Notifys.ERROR, BackpackManager.getInstance().getName(model)+ResManager.getInstance().getString("数量不足"));
						return false;
					}
				}
			}
			if(endNeedKillMonster().size()>0){
				Set<Integer> keySet = endNeedKillMonster().keySet();
				for (Integer model : keySet) {
					Integer neednum = endNeedKillMonster().get(model);
					Integer num = getKillmonsters().get(String.valueOf(model));
					if(num==null||num<neednum){
							Q_monsterBean q_monsterBean = DataManager.getInstance().q_monsterContainer.getMap().get(model);
							if(isPromp)MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("击杀")+ q_monsterBean.getQ_name()+ResManager.getInstance().getString("数量不足"));
							return false;

					}
				}
			}
			int endNeedHorseLevel = endNeedHorseLevel();
			if(endNeedHorseLevel>0){
				Horse horse = HorseManager.getInstance().getHorse(player);
				if(horse==null||endNeedHorseLevel>horse.getLayer()){
					if(isPromp)MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("完成该任务需要座骑达到{1}阶"),endNeedHorseLevel+"");
					return false;
				}
			}
			int endNeedConquerTaskCount = endNeedConquerTaskCount();
			long count = CountManager.getInstance().getCount(player, CountTypes.CONQUERTASK_FINSH, CountTypes.CONQUERTASK_FINSH.getValue());
			if(endNeedConquerTaskCount>0&&endNeedConquerTaskCount>count){
				if(isPromp)MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("完成该任务需要当日 完成{1}个讨伐任务"),endNeedConquerTaskCount+"");
				return false;
			}
			int endNeedDailyTaskCount = endNeedDailyTaskCount();
			if(endNeedDailyTaskCount>0){
				if(!TimeUtil.isSameDay(player.getDailyTaskTime(),System.currentTimeMillis())){
					if(isPromp)MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("完成该任务需要当日 完成{1}个日常任务"),endNeedDailyTaskCount+"");
					return false;
				}else{
					int finshdailytask=player.getDailyTaskCount()-player.getCurrentDailyTasks().size();
					if(finshdailytask<endNeedDailyTaskCount){
						if(isPromp)MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("完成该任务需要当日 完成{1}个日常任务"),endNeedDailyTaskCount+"");
						return false;
					}
				}
			}
			
			if(!isFinshAction()){
				if(isPromp)MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉,任务条件未达成"));
				return false;
			}
			
		}catch (Exception e) {
			logger.error("配置错误",e);
			return false;
		}
		return true;
	}
	
	protected abstract void dealResume();

	protected abstract void dealRewards();
	
	@Override
	public int compare(Task o1, Task o2) {
		if(o1.acqType()!=o2.acqType()){
			return o1.acqType()-o2.acqType();
		}
		return 0;
	}
	public long getOwerId() {
		return owerId;
	}
	public void setOwerId(long owerId) {
		this.owerId = owerId;
	}
	
	/**
	 * 获得任务类型
	 * @return
	 */
	public abstract byte acqType();
	
	/**
	 * 交付类型
	 * @return
	 */
	public abstract byte deliveryType();
	
	/**
	 * 放弃任务
	 */
	public abstract void giveUpTask();
	/**
	 * 任务结束
	 */
	public abstract void finshTask(Player vPlayer);
	
	public abstract void changeTask();
	
	public abstract Object buildTaskInfo();
	
}
