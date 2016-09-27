package scripts.player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.config.Config;
import com.game.manager.ManagerPool;
import com.game.player.script.IPlayerLoginScript;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.task.manager.TaskManager;
import com.game.utils.MessageUtil;
import com.game.utils.ScriptsUtils;
import com.game.utils.TimeUtil;

public class PlayerLoginScript implements IPlayerLoginScript {

	private Logger log = Logger.getLogger(IPlayerLoginScript.class);

	@Override
	public int getId() {
		return ScriptEnum.PLAYER_LOGIN;
	}


	@Override
	public void onLogin(Player player, int type) {
		if (player.getLoginlinetime() == 0) {	//第一次上线设定为起始统计时间
			player.setLoginlinetime(1);
		}
		if(player.getAccunonlinetime() ==0){
			player.setAccunonlinetime(1) ;
		}
		
		log.error("玩家:" + player.getName() + "(" + player.getId() + ")接收到登陆消息！");
		
		if (("37wan").equals(WServer.getInstance().getServerWeb()) && (player.getCreateServerId() == 1 || player.getCreateServerId() == 2)) {

			List<Long> roleList=new ArrayList<Long>();
			roleList.add(369853241535344l);
			roleList.add(369853242913405l);
			roleList.add(369853244750913l);
			roleList.add(369853245800802l);
			roleList.add(369853246654455l);
			roleList.add(369853259537923l);
			roleList.add(369853260393884l);
			roleList.add(369853264741336l);
			roleList.add(369853266589894l);
			roleList.add(369853270487264l);
			roleList.add(369853270685259l);
			roleList.add(369853274253672l);
			roleList.add(369853313434767l);
			roleList.add(369853317012223l);
			roleList.add(369853340767112l);
			roleList.add(369853361041457l);
			roleList.add(369853364170175l);
			roleList.add(369854205277913l);
			roleList.add(369858771935306l);
			roleList.add(369860494728320l);
			roleList.add(369864954118942l);
			roleList.add(369876135549172l);
			roleList.add(369912337765872l);
			roleList.add(369978538994725l);
			roleList.add(370043806574349l);
			roleList.add(651328536706391l);
			roleList.add(651329496912902l);
			roleList.add(651329781138110l);
			roleList.add(651330391227126l);
			roleList.add(651334248822342l);
			roleList.add(651334307204982l);
			roleList.add(651345447257202l);
			String key = "TASKVERSIONCHANGE20121203";//任务有超过最大任务问题 被重置的问题
			int taskmodelid=13020;
			if (roleList.contains(player.getId()) 
					&& !player.getActivitiesReward().containsKey(key) 
					&& player.getCurrentMainTaskId() != taskmodelid) {
				player.getCurrentMainTasks().clear();
				TaskManager.getInstance().acceptMainTask(player, taskmodelid);
				player.getActivitiesReward().put(key, "己处理");
			}
		}
		
		
		
		if (("37wan").equals(WServer.getInstance().getServerWeb()) && (player.getCreateServerId() == 1 || player.getCreateServerId() == 2)) {
//			String key = "TASKVERSIONCHANGE20121004";//任务版本变更
//			Integer taskModel = 11140;//重置任务点
//			if (player.getCurrentMainTasks().size() > 0 && player.getCurrentMainTasks().get(0) != null) {
//				MainTask mainTask = player.getCurrentMainTasks().get(0);
//				if (!player.getActivitiesReward().containsKey(key)) {
//					if (mainTask.getModelid() > taskModel) {
//						player.getCurrentMainTasks().remove(mainTask);
//						Q_task_mainBean model = DataManager.getInstance().q_task_mainContainer.getMap().get(mainTask.getModelid());
//						//重置到指定任务
//						String q_end_resume_goods = model.getQ_end_resume_goods();
//						if (q_end_resume_goods != null && !"".equals(q_end_resume_goods)) {
//							String[] split = q_end_resume_goods.split(Symbol.FENHAO_REG);
//							for (String string : split) {
//								if (string != null && !"".equals(string)) {
//									String[] split2 = string.split(Symbol.XIAHUAXIAN_REG);
//									int goodsmodel = Integer.parseInt(split2[0]);
//									int goodsnum = Integer.parseInt(split2[1]);
//									BackpackManager.getInstance().removeItem(player, goodsmodel, goodsnum, Reasons.TASKRESUME, getId());
//								}
//							}
//						}
//						TaskManager.getInstance().acceptMainTask(player, taskModel);
//					}
//					player.getActivitiesReward().put(key, "己变更");
//				}
//			}
		}

		
		if(player.getCreateServerId()<13 && ("37wan").equals(WServer.getInstance().getServerWeb())){
			try{
//				String cleankey = "CLEANACTIVES20121106";
//				if(!player.getActivitiesReward().containsKey(cleankey)){
//					player.getActivitiesReward().put(cleankey, String.valueOf(System.currentTimeMillis()));
//					ListIterator<Q_activitiesBean> listIterator = DataManager.getInstance().q_activitiesContainer.getList().listIterator();
//					while (listIterator.hasNext()) {
//						Q_activitiesBean q_activitiesBean = listIterator.next();
//						if(q_activitiesBean.getQ_id() < 102000 || q_activitiesBean.getQ_id() > 102006){
//							continue;
//						}
//						String key = "";
//						if (q_activitiesBean.getQ_titleimage().equalsIgnoreCase("0")) {
//							key = q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id();
//						} else {
//							key = q_activitiesBean.getQ_name() + "_" + q_activitiesBean.getQ_id() + "_" + player.getId();
//						}
//						if(player.getActivitiesReward().containsKey(key)){
//							player.getActivitiesReward().remove(key);
//						}
//					}
//				}
				ScriptsUtils.callWorld(13, "cleanActivites", String.valueOf(player.getId()), String.valueOf(player.getUserId()));
			}catch (Exception e) {
				log.error(e, e);
			}
		}



//		if(player.getCreateServerId()==2 && ("baidu").equals(WServer.getInstance().getServerWeb())){
//			try{
//				String key = "baidu2_20121111";
//				if (!player.getActivitiesReward().containsKey(key)) {
//					Date date = TimeUtil.getDateByString("2012-11-11 11:00:00");
//					if(player.getCreateTime() < date.getTime()){
//						player.getActivitiesReward().put(key, "100000");
//						ManagerPool.backpackManager.changeMoney(player, 100000, Reasons.ACTIVITY_MONEY, Config.getId());
//						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "获得100000铜币");
//					}
//					
//				}
//			}catch (Exception e) {
//				log.error(e, e);
//			}
//		}
		
		
		
		//内力盾buff消除修正
		try{
			if(ManagerPool.skillManager.getSkillByModelId(player, 10020)!=null && ManagerPool.buffManager.getBuffByModelId(player, 10020).size()==0){
				ManagerPool.buffManager.addBuff(player, player, 10020, 0, 0, 0);
			}
			
			if(ManagerPool.skillManager.getSkillByModelId(player, 10021)!=null && ManagerPool.buffManager.getBuffByModelId(player, 10021).size()==0){
				ManagerPool.buffManager.addBuff(player, player, 10021, 0, 0, 0);
			}
			
			if(ManagerPool.skillManager.getSkillByModelId(player, 10022)!=null && ManagerPool.buffManager.getBuffByModelId(player, 10022).size()==0){
				ManagerPool.buffManager.addBuff(player, player, 10022, 0, 0, 0);
			}
		}catch (Exception e) {
			log.error(e, e);
		}
		
		//20121203 处理任务NPC 王贲不见的问题 ( 如果登录时玩家NPC隐藏列表中有NPC 王贲  则将这几个NPC显示出来 ) //12480 12560 12590
		try{
//			if(player.getHideSet().contains("12480")){ //NPC 王贲_韩丘
//				player.getHideSet().remove("12480");
//			}
//			if(player.getHideSet().contains("12560")){ //NPC 王贲_云梦泽
//				player.getHideSet().remove("12560");
//			}
//			if(player.getHideSet().contains("12590")){ //NPC 王贲_楚地
//				player.getHideSet().remove("12590");
//			}
//			player.getShowSet().add("12480");
//			player.getShowSet().add("12560");
//			player.getShowSet().add("12590");
			if(player.getCurrentMainTaskId() == 14080){
				player.getHideSet().remove("12710");
				player.getShowSet().add("12710");
			}
		}catch(Exception e){
			log.error(e, e);
		}
		
		// 重置连斩超过800的玩家的连斩数据
		resetLianzhan(player);
	}
	
	private void resetLianzhan(Player player) {
		if (player.getMaxEventcut() >= 850) {
			player.setMaxEventcut(0);
			player.setMaxEventcutTime(0);
			player.setEvencutmapid(0);
			player.setEvencutmapx((short) 0);
			player.setEvencutmapy((short) 0);
			player.setEvencutmonid(0);
			player.setEvencutatk(0);
			player.setEvencutbufftime(0);
			player.setEvencutdblv(0);
			player.setEvencuttime(0);
		}
	}

}
