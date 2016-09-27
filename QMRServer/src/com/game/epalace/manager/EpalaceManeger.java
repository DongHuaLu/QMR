package com.game.epalace.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.config.Config;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_explorepalace_mapBean;
import com.game.data.bean.Q_explorepalace_rewardsBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_task_explorepalaceBean;
import com.game.dblog.LogService;
import com.game.epalace.log.EpalaceLog;
import com.game.epalace.message.ReqEpalaceAbandonTaskToGameMessage;
import com.game.epalace.message.ReqEpalaceTaskEndToGameMessage;
import com.game.epalace.message.ResEpalaceDiceToClientMessage;
import com.game.epalace.message.ResEpalaceOpenToGameMessage;
import com.game.epalace.message.ResEpalaceRewardInfoToClientMessage;
import com.game.epalace.structs.Epalace;
import com.game.epalace.structs.Epos;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.FruitRewardAttr;
import com.game.structs.Reasons;
import com.game.task.manager.TaskManager;
import com.game.task.struts.TreasureHuntTask;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;


public class EpalaceManeger {
	Logger log = Logger.getLogger(EpalaceManeger.class);
	private static Object obj = new Object();

	//玩家管理类实例
	private static EpalaceManeger manager;
	

	
	private EpalaceManeger(){
		directionmap.put(ON, UN);
		directionmap.put(UN, ON);
		directionmap.put(RIGHT, LEFT);
		directionmap.put(LEFT, RIGHT);
	}
	
	public static EpalaceManeger getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new EpalaceManeger();
			}
		}
		return manager;
	}

	private static HashMap<Integer, Integer> directionmap = new HashMap<Integer, Integer>();

	private int  ON = 0;	//上
	private int  RIGHT	= 2;//右
	private int  UN = 4;	//下
	private int  LEFT = 6;	//左
	
//	101	2		地宫寻宝活动起点增加次数
//	102	2		地宫活动增加次数
//	103		1001|500;1002|500;1003|400	地宫随机事件（ID|几率;ID|几率）
//	104	30		地宫至宝经验奖励系数
//	105	30		地宫至宝经铜币励系数
//	106	30		地宫至宝经真气励系数
//	107	30		地宫至宝经礼金励系数
//	108		1001|5|2000;	地宫至宝道具奖励（ID|数量|几率）

	
	
	
	
	/**获取数据库格子信息
	 * @return 
	 * 
	 */
	public HashMap<Integer, Q_explorepalace_mapBean> getPosData(){
		return ManagerPool.dataManager.q_explorepalace_mapContainer.getMap();
	}
	
	
	
	
	/**获取数据库奖励信息
	 * @return 
	 * 
	 */
	public HashMap<Integer, Q_explorepalace_rewardsBean> getRewardData(){
		return ManagerPool.dataManager.q_explorepalace_rewardsContainer.getMap();
	}
	
	
	
	
	/**获取数据库任务信息
	 * @return 
	 * 
	 */
	public HashMap<Integer, Q_task_explorepalaceBean> getTaskData(){
		return ManagerPool.dataManager.q_task_explorepalaceContainer.getMap();
	}
	
	
	
	
	
	
	
	//-----------------------------------------------------------------------------------
	
	/**打开面板
	 * 
	 * @param parameter
	 */
	public void stReqEpalaceOpenToGameMessage(Player player) {
		int ms = (int)(System.currentTimeMillis()/1000);
		restorationEpalaceTime(player);
		ResEpalaceOpenToGameMessage cmsg = new ResEpalaceOpenToGameMessage();
		Epalace epalace =player.getEpalace();
		cmsg.setMovenum((byte) epalace.getMovenum());
		cmsg.setPos((byte) epalace.getPos());
		cmsg.setTime(ms - epalace.getTime());
		cmsg.setTask(checktask(player));
		cmsg.setAppearanceInfo(ManagerPool.transactionsManager.setPlayerAppearanceInfo(player));
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	
	
	
	
	/**丢骰子
	 * 
	 * @param parameter
	 */
	public void stReqEpalaceDiceToGameMessage(Player player) {
		if (player.getLevel() < 20) {
			MessageUtil.notify_player(player, Notifys.ERROR,  ResManager.getInstance().getString("20级以上才可参与地宫探险。"));
			return;
		}
		Epalace epalace =player.getEpalace();
		if (epalace.getEposlist().size() > 0) {
			return;
		}
		
		if (checktask(player) > 0) {
			MessageUtil.notify_player(player, Notifys.ERROR,  ResManager.getInstance().getString("请先完成地宫专属任务。"));
			return;
		}
		
		
		int movenum = epalace.getMovenum();
		if (movenum > 0) {
			epalace.setMovenum(movenum-1);
			if (movenum == 12) {
				epalace.setTime((int)(System.currentTimeMillis()/1000));
			}
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR,  ResManager.getInstance().getString("请等待投掷次数恢复"));
			return;
		}
		
		int rnd = RandomUtils.random(1, 6);
		EpalaceLog elog = new EpalaceLog();
		HashMap<Integer, Q_explorepalace_mapBean> mapdata = getPosData();
		if (mapdata.containsKey(epalace.getPos())) {
			int orientation = epalace.getDirection();
			if (orientation < 0) {
				orientation = ManagerPool.epalaceManeger.getDirection(epalace.getPos(),0);
			}
			for (int i = 0; i < rnd; i++) {
				int posnum = 0;
				if (i ==0) {
					int posid = epalace.getPos();
					Epos yepos =setpos( posid,-1);
					epalace.getEposlist().add(yepos);
					posnum = getPositionNum(posid);
					if (posnum > 2) {	//有岔路
						int nextposid = getBranchroadRND(posid);
						yepos.setNextpos((byte) nextposid);
						yepos.setContinuedtime(14);
						Epos newepos =setpos(nextposid,posid);
						epalace.getEposlist().add(newepos);
					}else {
						int nextposid = getNextPos(posid,orientation);
						yepos.setNextpos((byte) nextposid);
						epalace.getEposlist().add(setpos(nextposid,posid));
					}
				}else {
					Epos epos = epalace.getEposlist().get(i);
					posnum = getPositionNum(epos.getCurrentpos());
					if (posnum > 2) {
						int nextposid = getBranchroadRND(epos.getCurrentpos());
						epos.setContinuedtime(14);
						epos.setNextpos((byte) nextposid);
						Epos newepos = setpos(nextposid,epos.getCurrentpos());
						epalace.getEposlist().add(newepos);
					}else {
						int nextposid =getNextPos(epos);
						epos.setNextpos((byte) nextposid);
						epalace.getEposlist().add(setpos(nextposid,epos.getCurrentpos()));
					}
				}
				
			}

			ResEpalaceDiceToClientMessage cmsg = new ResEpalaceDiceToClientMessage();
			cmsg.setMovenum((byte) epalace.getMovenum());
			cmsg.setNum((byte) rnd);
			if (epalace.getMovenum() == 11) {
				cmsg.setTime(0);
			}else {
				cmsg.setTime((int) ((System.currentTimeMillis()/1000)-epalace.getTime()));
			}
			restorationEpalaceTime(player);
			MessageUtil.tell_player_message(player, cmsg);
			
			elog.setPlayerid(player.getId());
			elog.setLastnum(epalace.getMovenum());
			elog.setWalknum(rnd);
			elog.setTargetlattice(epalace.getEposlist().get(epalace.getEposlist().size()-1).getCurrentpos());
			elog.setSid(player.getCreateServerId());
			LogService.getInstance().execute(elog);
		}
	}

	
	/**调用时间计数器
	 * 
	 * @param player
	 */
	public void restorationEpalaceTime(Player player) {
		Epalace epalace =player.getEpalace();
		if (epalace.getMovenum() == 12) {
			return ;
		}
		int ms = (int)(System.currentTimeMillis()/1000);
		int num = ms - epalace.getTime();
		int sum = num/(2*60*60);
		if (sum > 0) {
			if (epalace.getMovenum() +sum > 12) {
				epalace.setMovenum(12);
				epalace.setTime(0);
			}else {
				epalace.setMovenum(epalace.getMovenum() +sum);
				epalace.setTime(sum*2*60*60 + epalace.getTime());
			}
			
		}
	}
	
	
	
	
	
	/**取得当前格子方位数量 如果大于2，表示有岔路
	 * 
	 */
	public int getPositionNum(int pos){
		HashMap<Integer, Q_explorepalace_mapBean> mapdata = getPosData();
		Q_explorepalace_mapBean data = mapdata.get(pos);
		int num = 0;
		if (data != null) {
			if (data.getQ_left() > 0 ) {
				num = num + 1;
			}
			if (data.getQ_on() > 0 ) {
				num = num + 1;
			}
			
			if (data.getQ_right() > 0 ) {
				num = num + 1;
			}	
			
			if (data.getQ_under() > 0 ) {
				num = num + 1;
			}	
		}
		return num;
	}

	
	/**岔路随机
	 * 
	 * @param pos
	 * @param mapdata
	 * @return
	 */
	public int getBranchroadRND(int pos){
		HashMap<Integer, Q_explorepalace_mapBean> mapdata = getPosData();
		int rnd = RandomUtils.random(1, 3)-1;
		List<Integer> list=new ArrayList<Integer>();
		Q_explorepalace_mapBean data = mapdata.get(pos);
		if (data != null) {
			if (data.getQ_on() > 0 ) {
				list.add(data.getQ_on());
			}
			
			if (data.getQ_left() > 0 ) {
				list.add(data.getQ_left());
			}

			if (data.getQ_right() > 0 ) {
				list.add(data.getQ_right());
			}	
			
			if (data.getQ_under() > 0 ) {
				list.add(data.getQ_under());
			}	
		}
		return list.get(rnd);
	}
	
	
	/**获取前进方向的格子ID
	 * 
	 * @param pos
	 * @param mapdata
	 * @return
	 */
	public int getNextPos(int posid,int orientation){
		HashMap<Integer, Q_explorepalace_mapBean> mapdata = getPosData();
		Q_explorepalace_mapBean data = mapdata.get(posid);
		if (data != null) {
			
			if (data.getQ_left() > 0 && data.getQ_left() != posid && orientation == LEFT) {
				return data.getQ_left();
			}
			if (data.getQ_on() > 0 && data.getQ_on() != posid  && orientation == ON) {
				return data.getQ_on();
			}
			
			if (data.getQ_right() > 0 && data.getQ_right() != posid && orientation == RIGHT) {
				return data.getQ_right();
			}	
			
			if (data.getQ_under() > 0 && data.getQ_under() != posid && orientation == UN) {
				return data.getQ_under();
			}
			
			
			int dn = directionmap.get(orientation);
			if (data.getQ_left() > 0 && data.getQ_left() != posid && dn != LEFT) {
				return data.getQ_left();
			}
			if (data.getQ_on() > 0 && data.getQ_on() != posid && dn != ON) {
				return data.getQ_on();
			}
			if (data.getQ_right() > 0 && data.getQ_right() != posid && dn != RIGHT) {
				return data.getQ_right();
			}	
			
			if (data.getQ_under() > 0 && data.getQ_under() != posid && dn != UN) {
				return data.getQ_under();
			}

		}
		return 0;
	}
	
	
	/**获取前进方向的格子ID
	 * 
	 * @param pos
	 * @param mapdata
	 * @return
	 */
	public int getNextPos(Epos epos){
		if (epos.getLeftpos() > 0 && epos.getPreviouspos() != epos.getLeftpos() ) {
			return epos.getLeftpos();
		}
		if (epos.getOnpos() > 0 && epos.getPreviouspos() != epos.getOnpos() ) {
			return epos.getOnpos();
		}
		if (epos.getRightpos() > 0 && epos.getPreviouspos() != epos.getRightpos() ) {
			return epos.getRightpos();
		}
		if (epos.getUnderpos() > 0 && epos.getPreviouspos() != epos.getUnderpos() ) {
			return epos.getUnderpos();
		}
		return 0;
	}
	
	
	/**设置格子
	 * 
	 * @param pos
	 * @return
	 */
	public Epos setpos(int pos,int ypos){
		HashMap<Integer, Q_explorepalace_mapBean> mapdata = getPosData();
		Q_explorepalace_mapBean posdata = mapdata.get(pos);
		if (posdata != null) {
			Epos epos = new Epos();
			epos.setCurrentpos((byte) posdata.getQ_id());
			epos.setOnpos((byte) posdata.getQ_on());
			epos.setLeftpos((byte) posdata.getQ_left());
			epos.setRightpos((byte) posdata.getQ_right());
			epos.setUnderpos((byte) posdata.getQ_under());
			epos.setPreviouspos((byte) ypos);
			epos.setEventid( posdata.getQ_eventid());
			return epos;
		}else {
			log.error("不存在的格子ID="+pos);
		}
		return null;
	}
	
	
	
	 /**得到前进方向
	  * 
	  * @param pos
	  * @param nextPos
	  * @return
	  */
	public int getDirection(int pos,int nextPos){
		HashMap<Integer, Q_explorepalace_mapBean> mapdata = getPosData();
		Q_explorepalace_mapBean data = mapdata.get(pos);
		if (data != null) {
			if (data.getQ_left() > 0 && data.getQ_left() == nextPos) {
				return LEFT;
			}
			if (data.getQ_on() > 0 && data.getQ_on() == nextPos) {
				return ON;
			}
			
			if (data.getQ_right() > 0 && data.getQ_right() == nextPos) {
				return RIGHT;
			}	
			
			if (data.getQ_under() > 0 && data.getQ_under() == nextPos) {
				return UN;
			}	
			
			
			//以防万一，找不到方向，在这里初始一个方向
			if (data.getQ_left() > 0) {
				return LEFT;
			}
			if (data.getQ_on() > 0 ) {
				return ON;
			}
			
			if (data.getQ_right() > 0) {
				return RIGHT;
			}	
			
			if (data.getQ_under() > 0 ) {
				return UN;
			}	
			
		}
		return -1;
	}
	
	
	
	/**奖励发放
	 * 
	 */
	public void epalaceReward(Player player,int type){
		int rndtyoe = 0;
		if (type == 1006 ) {		//随机事件
			rndtyoe = type;
			//地宫随机事件（ID|几率;ID|几率）
			String rndstr = ManagerPool.dataManager.q_globalContainer.getMap().get(103).getQ_string_value();
			List<Integer> idlist = new ArrayList<Integer>();
			List<Integer> rndlist = new ArrayList<Integer>();
			String[] rnddb = rndstr.split(Symbol.FENHAO);
			for (String string : rnddb) {
				String[] rew = string.split(Symbol.SHUXIAN_REG);
				if (rew.length == 2) {
					idlist.add(Integer.parseInt(rew[0]));
					rndlist.add(Integer.parseInt(rew[1]));
				}
			}
			if (rndlist.size() > 0) {
				int idx = RandomUtils.randomIndexByProb(rndlist);
				type=idlist.get(idx);
			}
		}
		
		
		if (type == 1004) {
			List<Q_explorepalace_rewardsBean> rewardslist = new ArrayList<Q_explorepalace_rewardsBean> ();
			List<Integer> rndList=new ArrayList<Integer>();
			HashMap<Integer, Q_explorepalace_rewardsBean> rewarddata = getRewardData();
			Iterator<Entry<Integer, Q_explorepalace_rewardsBean>> data = rewarddata.entrySet().iterator();
			while (data.hasNext()) {
				Entry<Integer, Q_explorepalace_rewardsBean> rewardEntry =  data.next();
				Q_explorepalace_rewardsBean db = rewardEntry.getValue();
				if (player.getLevel() >= db.getQ_mingrade() &&  player.getLevel() <=db.getQ_maxgrade()) {
					rewardslist.add(db);
					rndList.add(db.getQ_rewardsrnd());
				}
			}
			if (rewardslist.size() == 0) {
				return;
			}
			
			int rnd = RandomUtils.randomIndexByProb(rndList);	//随机奖励条目
			Q_explorepalace_rewardsBean rewardsBean = rewardslist.get(rnd);
			int sumrnd = rewardsBean.getQ_buffrnd() + rewardsBean.getQ_rewardsrnd();//在BUFF几率和数值奖励之间随机
			ResEpalaceRewardInfoToClientMessage rewardinfomsg=new ResEpalaceRewardInfoToClientMessage();
			if (rndtyoe > 0) {
				rewardinfomsg.setType(rndtyoe);
			}else {
				rewardinfomsg.setType(type);
			}
			if (RandomUtils.random(1, sumrnd) > rewardsBean.getQ_rewardsrnd()) {
				//奖励数值// -1铜币，-2元宝，-3真气，-4经验  -5绑定元宝
				
				if (rewardsBean.getQ_rewards_bindyuanbao() > 0) {
					FruitReward fruitReward = new FruitReward();
					fruitReward.setItemModelid(-5);
					fruitReward.setNum(rewardsBean.getQ_rewards_bindyuanbao());
					giveRewarded(player,fruitReward,0);
					rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
				}
				if (rewardsBean.getQ_rewards_coin() > 0) {
					FruitReward fruitReward = new FruitReward();
					fruitReward.setItemModelid(-1);
					fruitReward.setNum(rewardsBean.getQ_rewards_coin());
					giveRewarded(player,fruitReward,0);
					rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
				}
				if (rewardsBean.getQ_rewards_exp() > 0) {
					FruitReward fruitReward = new FruitReward();
					fruitReward.setItemModelid(-4);
					fruitReward.setNum(rewardsBean.getQ_rewards_exp());
					giveRewarded(player,fruitReward,0);
					rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
				}
				if (rewardsBean.getQ_rewards_zq() > 0) {
					FruitReward fruitReward = new FruitReward();
					fruitReward.setItemModelid(-3);
					fruitReward.setNum(rewardsBean.getQ_rewards_zq());
					giveRewarded(player,fruitReward,0);
					rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
				}
				
			}else {
				//奖励BUFF
				Q_buffBean buffdata = ManagerPool.dataManager.q_buffContainer.getMap().get(rewardsBean.getQ_buff_id());
				if (buffdata != null) {
					ManagerPool.buffManager.addBuff(player, player, rewardsBean.getQ_buff_id(), 0, 0, 0);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜您在地宫寻宝中获得了{1}BUFF"), buffdata.getQ_buff_name());
					rewardinfomsg.setBuffid(rewardsBean.getQ_buff_id());
				}
			}
			if (rndtyoe > 0) {
				rewardinfomsg.setType(rndtyoe);
			}else {
				rewardinfomsg.setType(type);
			}
			MessageUtil.tell_player_message(player, rewardinfomsg);
		}else if (type == 1001 || type == 1002 ) {	//加行动次数
			int num = ManagerPool.dataManager.q_globalContainer.getMap().get(101).getQ_int_value();
			if (type == 1002 ) {
				num = ManagerPool.dataManager.q_globalContainer.getMap().get(102).getQ_int_value();
			}
			Epalace epalace = player.getEpalace();
			if(epalace.getMovenum() < 12){
				if (num + epalace.getMovenum() >12) {
					num = 12- epalace.getMovenum();
				}
				epalace.setMovenum(epalace.getMovenum()+num);
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您在地宫寻宝中获得：投掷次数+{1}"),num+"");
				ResEpalaceRewardInfoToClientMessage rewardinfomsg=new ResEpalaceRewardInfoToClientMessage();
				FruitReward fruitReward = new FruitReward();
				fruitReward.setItemModelid(0);
				fruitReward.setNum(num);
				rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
				if (rndtyoe > 0) {
					rewardinfomsg.setType(rndtyoe);
				}else {
					rewardinfomsg.setType(type);
				}
				MessageUtil.tell_player_message(player, rewardinfomsg);
			}
		}else if (type == 1003 ) {		//任务
			List<Q_task_explorepalaceBean> tasklist = new ArrayList<Q_task_explorepalaceBean> ();
			HashMap<Integer, Q_task_explorepalaceBean> taskdata = getTaskData();
			Iterator<Entry<Integer, Q_task_explorepalaceBean>> data = taskdata.entrySet().iterator();
			while (data.hasNext()) {
				Entry<Integer, Q_task_explorepalaceBean> rewardEntry =  data.next();
				Q_task_explorepalaceBean db = rewardEntry.getValue();
				if (player.getLevel() >= db.getQ_mingrade() &&  player.getLevel() <=db.getQ_maxgrade()) {
					tasklist.add(db);
				}
			}
			if (tasklist.size() > 0) {
				int rnd = RandomUtils.random(1, tasklist.size())-1;
				Q_task_explorepalaceBean task =tasklist.get(rnd);
				TaskManager.getInstance().acceptTreasureHuntTask(player, task.getQ_id());	
			}
			
		}else if (type == 1005 ) {		//地宫至宝
			int exp = ManagerPool.dataManager.q_globalContainer.getMap().get(104).getQ_int_value();//经验;
			int money = ManagerPool.dataManager.q_globalContainer.getMap().get(105).getQ_int_value();//铜币;
			int zhenqi = ManagerPool.dataManager.q_globalContainer.getMap().get(106).getQ_int_value();//真气;
			int lijing = ManagerPool.dataManager.q_globalContainer.getMap().get(107).getQ_int_value();//礼金;
			Q_characterBean basisdb = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
			ResEpalaceRewardInfoToClientMessage rewardinfomsg=new ResEpalaceRewardInfoToClientMessage();
			if (rndtyoe > 0) {
				rewardinfomsg.setType(rndtyoe);
			}else {
				rewardinfomsg.setType(type);
			}
			
			if (basisdb != null) {
				//奖励数值// -1铜币，-2元宝，-3真气，-4经验  -5绑定元宝
				if (basisdb.getQ_basis_exp() > 0 && exp > 0) {
					FruitReward fruitReward = new FruitReward();
					fruitReward.setItemModelid(-4);
					fruitReward.setNum(basisdb.getQ_basis_exp()*exp);
					giveRewarded(player,fruitReward,1);
					rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
				}
				if (basisdb.getQ_basis_money() > 0 && money>0) {
					FruitReward fruitReward = new FruitReward();
					fruitReward.setItemModelid(-1);
					fruitReward.setNum(basisdb.getQ_basis_money()*money);
					giveRewarded(player,fruitReward,1);
					rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
				}
				if (basisdb.getQ_basis_zhenqi() > 0 && zhenqi > 0) {
					FruitReward fruitReward = new FruitReward();
					fruitReward.setItemModelid(-3);
					fruitReward.setNum(basisdb.getQ_basis_zhenqi()*zhenqi);
					giveRewarded(player,fruitReward,1);
					rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
				}
				if (basisdb.getQ_basis_bindgold() > 0 && lijing > 0) {
					FruitReward fruitReward = new FruitReward();
					fruitReward.setItemModelid(-5);
					fruitReward.setNum(basisdb.getQ_basis_bindgold()*lijing);
					giveRewarded(player,fruitReward,1);
					rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
				}
			}
			int[][] itemwer = resolvestrall();
			for (int i = 0; i < itemwer.length; i++) {
				FruitReward fruitReward = new FruitReward();
				fruitReward.setItemModelid(itemwer[i][0]);
				fruitReward.setNum(itemwer[i][1]);
				fruitReward.setBind(true);
				giveRewarded(player,fruitReward,1);
				rewardinfomsg.getFruitrewardinfo().add(fruitReward.makeinfo());
			}
			MessageUtil.tell_player_message(player, rewardinfomsg);
		}
	}
	
//	/**地宫至宝，道具奖励解析(随机)
//	 * @return 
//	 * 
//	 */
//	public int[] resolvestr(){
//		//地宫至宝道具奖励（ID|数量|几率）
//		String rndstr = ManagerPool.dataManager.q_globalContainer.getMap().get(108).getQ_string_value();
//		List<Integer> idlist = new ArrayList<Integer>();
//		List<Integer> rndlist = new ArrayList<Integer>();
//		List<Integer> numlist = new ArrayList<Integer>();
//		String[] rnddb = rndstr.split(Symbol.FENHAO);
//		for (String string : rnddb) {
//			String[] rew = string.split(Symbol.SHUXIAN_REG);
//			if (rew.length == 3) {
//				idlist.add(Integer.parseInt(rew[0]));
//				numlist.add(Integer.parseInt(rew[1]));
//				rndlist.add(Integer.parseInt(rew[2]));
//			}
//		}
//		int [] rew ={0,0};
//		if (idlist.size() > 0) {
//			int idx = RandomUtils.randomIndexByProb(rndlist);
//			rew[0]=idlist.get(idx);
//			rew[1]=numlist.get(idx);	
//		}
//		return rew;
//	}
//	
//	
	/**地宫至宝，道具奖励解析（全给）
	 * @return 
	 * 
	 */
	public int[][] resolvestrall(){
		//地宫至宝道具奖励（ID|数量|几率）
		String rndstr = ManagerPool.dataManager.q_globalContainer.getMap().get(108).getQ_string_value();
		String[] rnddb = rndstr.split(Symbol.FENHAO);
		int[][] rews = new int[rnddb.length][2];
		int idx = 0;
		for (String string : rnddb) {
			String[] rew = string.split(Symbol.SHUXIAN_REG);
			if (rew.length == 2) {
				rews[idx][0] = Integer.parseInt(rew[0]);
				rews[idx][1] = Integer.parseInt(rew[1]);
				idx = idx +1;
			}
		}
		return rews;
	}
	
	
	
	
	
	/**检查地宫任务是否完成
	 * -1没任务，0完成任务，大于0，没完成任务
	 * @param player
	 * @return
	 */
	public int checktask(Player player) {
		if (player.getCurrentTreasureHuntTasks().size() > 0) {
			TreasureHuntTask treasureHuntTask = player.getCurrentTreasureHuntTasks().get(0);
			if(treasureHuntTask.checkFinsh(false, player)){
				return 0;
			}
			return treasureHuntTask.getModelId();
		}
		return -1;
	}
	
	
	

	/**得到地宫奖励  ,0普通奖励，1地宫至宝
	 * 
	 * @param msg
	 */
	public void giveRewarded(Player player , FruitReward fruitReward,int type) {
		String rewardedname = ResManager.getInstance().getString("恭喜您在地宫寻宝中获得:");
		if (type == 1) {
			 rewardedname = ResManager.getInstance().getString("恭喜！打开地宫至宝，获得:");
		}
		int id = fruitReward.getItemModelid();
		long action = Config.getId();
		// -1铜币，-2元宝，-3真气，-4经验  -5绑定元宝
		if (fruitReward.getNum() == 0) {
			return ;
		}
		boolean issuccess = true;
		List<Item> createItems = new ArrayList<Item>();
		String itemname="";
		if (id == -1) {
			itemname = ResManager.getInstance().getString("铜币");
			if(player != null && ManagerPool.backpackManager.changeMoney(player, fruitReward.getNum(), Reasons.DIGONG_GIVE_MONEY, action)){
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");	
			}else {
				issuccess =false;
			}
			
		}else if (id== -2) {
//			itemname = ResManager.getInstance().getString("元宝");
//			if(player != null && ManagerPool.backpackManager.addGold(player, fruitReward.getNum(), Reasons.DIGONG_GIVE_GOLD, action)){
//				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
//			}else {
//				issuccess =false;
//			}
		}else if ( id == -3) {
			itemname = ResManager.getInstance().getString("真气");
			if(player != null){
				ManagerPool.playerManager.addZhenqi(player, fruitReward.getNum(), AttributeChangeReason.EPALACEAWARD);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -4) {
			itemname = ResManager.getInstance().getString("经验");
			if(player != null){
				ManagerPool.playerManager.addExp(player, fruitReward.getNum(), AttributeChangeReason.EPALACEAWARD);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -5) {
			itemname = ResManager.getInstance().getString("礼金");
			if(player != null && ManagerPool.backpackManager.changeBindGold(player, fruitReward.getNum(), Reasons.DIGONG_GIVE_BINDGOLD, action)){
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}	
		}else if (id > 0) {
			Q_itemBean itemMode = ManagerPool.dataManager.q_itemContainer.getMap().get(fruitReward.getItemModelid());
			if (itemMode != null) {
				itemname = itemMode.getQ_name();
				createItems = Item.createItems(fruitReward.getItemModelid(), fruitReward.getNum(), fruitReward.isBind(),((fruitReward.getLosttime() == 0) ? fruitReward.getLosttime() : (System.currentTimeMillis() + fruitReward.getLosttime() * 1000)) , fruitReward.getStrenglevel(), null);
				List<FruitRewardAttr> attrs = fruitReward.getFruitRewardAttrslist();
				//写入属性
				if (itemMode.getQ_type()== ItemTypeConst.EQUIP) {
					if (attrs.size() > 0) {
						for (Item item : createItems) {
							Equip equip = (Equip)item;
							for (FruitRewardAttr attr : attrs) {
								Attribute attribute = new Attribute();
								attribute.setType(attr.getType());
								attribute.setValue(attr.getValue());
								equip.getAttributes().add(attribute);
							}
						}
					}
				}

				if(player != null && ManagerPool.backpackManager.getEmptyGridNum(player) >= createItems.size()) {
					BackpackManager.getInstance().addItems(player, createItems,Reasons.DIGONG_GIVE_ITEM,action);
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("{1}{2}({3})。"),rewardedname,itemMode.getQ_name(),fruitReward.getNum()+"");
				}else {
					issuccess =false;
				}
			}else {
				log.error(rewardedname+"道具item不存在：["+id +"]");
			}
		}else{
			log.error(rewardedname+"ID类型未知：["+id+"]");
		}
		
		if(issuccess == false){
			itemname = itemname+"("+fruitReward.getNum()+")";
			if (id > 0) {
				ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems);
			}else {
				if (id == -1 ) {	//铜币
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createMoney(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
//				}else if (id == -2 ) {	//元宝
//					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,"系统邮件",rewardedname+":"+itemname,(byte) 2,fruitReward.getNum(),new ArrayList<Item>());
				}else if (id == -3) {	//真气
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createZhenQi(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				}else if ( id == -4 ) {	//经验
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createExp(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				}else if ( id == -5) {	//礼金
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createBindGold(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				}
			}
			if (player != null) {
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("由于您的包裹已满，")+rewardedname+"："+itemname+ResManager.getInstance().getString(" 已经通过邮件发送给您。"));
			}
		}
	}

	
	
	/**放弃任务
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqEpalaceAbandonTaskToGameMessage(Player player,ReqEpalaceAbandonTaskToGameMessage msg) {

	}

	
	/**完成任务
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqEpalaceTaskEndToGameMessage(Player player,ReqEpalaceTaskEndToGameMessage msg) {
		HashMap<Integer, Q_task_explorepalaceBean> taskbaen = getTaskData();
		if (taskbaen.containsKey(msg.getTaskid())) {
			if (msg.getType() == 1) {//元宝完成任务
				Q_task_explorepalaceBean data = taskbaen.get(msg.getTaskid());
				List<TreasureHuntTask> taskdb = player.getCurrentTreasureHuntTasks();
				for (TreasureHuntTask treasureHuntTask : taskdb) {
					if (msg.getTaskid() == treasureHuntTask.getModelId()) {
						if (data.getQ_usegold() > 0) {
							if (ManagerPool.backpackManager.checkGold(player, data.getQ_usegold())) {
								ManagerPool.backpackManager.changeGold(player, -data.getQ_usegold(), Reasons.DIGONG_YBTASK, Config.getId());
								treasureHuntTask.finshTask(player);//完成任务
								return;
							}else {
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，完成此任务需要{1}元宝"), data.getQ_usegold()+"");
								return;
							}
						}else {
							MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，此任务不能使用元宝立即完成功能"));
							return;
						}

					}
				}
			}else {
				if(checktask(player) == 0){//手动完成
					List<TreasureHuntTask> taskdb = player.getCurrentTreasureHuntTasks();
					for (TreasureHuntTask treasureHuntTask : taskdb) {
						if (msg.getTaskid() == treasureHuntTask.getModelId()) {
							treasureHuntTask.finshTask(player);//完成任务
							return;
						}
					}
				}
			}
		}
	}

	
	
	/**设置立即完成
	 * 
	 * @param player
	 */
	public void setimmediatelyComplete(Player player) {
		Epalace epalace = player.getEpalace();
		List<Epos> pathlist = epalace.getEposlist();
		if (pathlist.size() > 0) {
			for (int i = 0; i < pathlist.size(); i++) {
				if (pathlist.get(i).getPreviouspos() > 0 && ( pathlist.get(i).getEventid() == 1005 || i == pathlist.size()-1 )){
					ManagerPool.epalaceManeger.epalaceReward(player, pathlist.get(i).getEventid());
					if ( i == pathlist.size()-1) {
						epalace.setPos(pathlist.get(i).getCurrentpos());
						int fx = ManagerPool.epalaceManeger.getDirection(pathlist.get(i).getPreviouspos(),pathlist.get(i).getCurrentpos());
						epalace.setDirection((byte) fx);	
					}

				}
			}
		}
		pathlist.clear();
	}
	

	
	
	
	
	
	
	

	
}
