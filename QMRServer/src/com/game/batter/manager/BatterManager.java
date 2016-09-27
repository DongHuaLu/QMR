package com.game.batter.manager;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.batter.message.ResMomentKillToClientMessage;
import com.game.batter.message.ResMonsterBatterToClientMessage;
import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffType;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_evencutBean;
import com.game.data.bean.Q_monsterBean;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.monster.structs.Hatred;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqSyncPlayerEventcutMessage;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.Global;
import com.game.utils.MessageUtil;

public class BatterManager {

	protected Logger log = Logger.getLogger(BatterManager.class);
	//玩家管理类实例
	private static BatterManager manager;
	private static Object obj = new Object();
	private BatterManager(){}
	
	public static BatterManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new BatterManager();
			}
		}
		return manager;
	}
	
	/**连击BOSS触发BUFF
	 * 
	 */
	private int BOSSBATTERBUFF = 30810; 
	
		
		
	/**设置连斩
	 * 
	 */
	public void setBatter(List<Hatred> hatreds, Monster monster, Fighter attacter){
		//Player maxenemy = null;	//最仇恨者
		Player killer = null;  //击杀 者
		Q_monsterBean q_monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
		if (q_monsterBean == null) {
			return ;
		}
		
		if(attacter != null && attacter instanceof Pet) {	//击杀者如果是宠物，那就获取主人
			killer=PlayerManager.getInstance().getPlayer(((Pet)attacter).getOwnerId());
		}
		
		if(attacter != null && attacter instanceof Player) {
			killer=(Player)attacter;
		}
		
//		Fighter attacker = hatreds.get(0).getTarget();  //最仇恨的对象(已自动排序)
//		
//		if(attacker != null && attacker instanceof Pet){
//			maxenemy=PlayerManager.getInstance().getPlayer(((Pet)attacker).getOwnerId());
//		}
//		
//		if (attacker != null && attacker instanceof Player) {
//			maxenemy = (Player) attacker;
//		}
		if (killer != null ) {
			int num = killer.getEvencutnum() + 1;
			if(ManagerPool.monsterManager.getAttenuation(attacter.getLevel(), monster. getLevel(), monster) == 1.0  || num%10 == 0){
			int montype = q_monsterBean.getQ_monster_type();
				if (montype == 1) {
					addBatter(1, killer, monster);
				}
			}
		}
	}


	
	/**增加连杀
	 * @return 
	 * 
	 */
	public void addBatter(int montype, Player killer, Monster monster){
		if (montype == 1) {
			Q_evencutBean olddb = getEvencutDB(killer); //得到当前身上BUFF对应的连斩数据表
			if (olddb != null) {
				int s = olddb.getQ_countdown()*1000;
				if (killer.getEvencutnum() > 800) {
					s =  500;	//大于800后设置为500毫秒
				}

				if(killer.getEvencuttime() + s > System.currentTimeMillis()){//累加
					int num = killer.getEvencutnum()+1;
					if(num > killer.getEvencutatk()){
						killer.setEvencuttime(System.currentTimeMillis());
						killer.setEvencutnum(num);
						killer.setEvencutatk(num);
						killer.setEvencutbufftime(System.currentTimeMillis());
					}else {
						killer.setEvencuttime(System.currentTimeMillis());
						killer.setEvencutnum(num);
						//最大连斩数超过历史最大连斩数
						if(killer.getEvencutatk() > killer.getMaxEventcut() && killer.getLevel() >= Global.SYNC_PLAYER_LEVEL && killer.getEvencutatk() >= Global.SYNC_EVENT_CUT){
							killer.setMaxEventcut(killer.getEvencutatk());
							killer.setMaxEventcutTime(System.currentTimeMillis());
							killer.setEvencutmapid(killer.getMapModelId());
							killer.setEvencutmapx(killer.getPosition().getX());
							killer.setEvencutmapy(killer.getPosition().getY());
							killer.setEvencutmonid(monster.getModelId());
							
							//同步连斩
							ReqSyncPlayerEventcutMessage msg = new ReqSyncPlayerEventcutMessage();
							msg.setPlayerId(killer.getId());
							msg.setEventcut(killer.getMaxEventcut());
							msg.setEventcutTime(killer.getMaxEventcutTime());
							msg.setMapModelId(killer.getEvencutmapid());
							msg.setMapX(killer.getEvencutmapx());
							msg.setMapY(killer.getEvencutmapy());
							msg.setMonsterModelId(killer.getEvencutmonid());
							
							MessageUtil.send_to_world(msg);
						}
					}
//					if (num == 600) {
//						MessageUtil.notify_All_player( Notifys.SROLL, "玩家{1}犹如人屠白起再世，剑锋所指，千军溃散，鬼哭神嚎。刷新了众英豪创下的连斩记录，成为睥睨天下的连斩之王！",killer.getName());	
//					}
					//MessageUtil.notify_player(killer, Notifys.NORMAL, "连斩{1}",num+"");
				}else {
					//清除，重新计算
					killer.setEvencuttime(System.currentTimeMillis());
					killer.setEvencutnum(1);
					MessageUtil.notify_player(killer, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("每十连斩将触发【斩击】秒杀普通怪，每点连斩数可为您加成1点额外伤害"));	
				}
				ResMonsterBatterToClientMessage cmsg = new ResMonsterBatterToClientMessage();
				cmsg.setAtk(killer.getEvencutatk());
				cmsg.setNum(killer.getEvencutnum());
				cmsg.setCountdowntime(olddb.getQ_countdown());
				cmsg.setType((byte) 1);
				MessageUtil.tell_player_message(killer, cmsg);
				
				Q_evencutBean newdb = getEvencutDB(killer);//得到下一个可用的连斩数据表
				if (newdb != null ) {
					Buff buff = getBatterBuff(killer);
					if (olddb.getQ_id() < newdb.getQ_id()) {	//老的小于新的，则更新BUFF
						Q_evencutBean buffdb = getbuffBydb(killer);
						if (buffdb != null) {
							if(newdb.getQ_id() > buffdb.getQ_id()){	//新的大于当前BUFF所在数据 ，，则更新BUFF
								ManagerPool.buffManager.removeByBuffId( killer, olddb.getQ_buff_id());
								ManagerPool.buffManager.removeByBuffId( killer, buffdb.getQ_buff_id());
								Q_buffBean buffmod = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());
								long timenum = buff.getTotalTime() - buffmod.getQ_effect_time()*1000;
								if (timenum < 0) {
									timenum = 0;
								}
								ManagerPool.buffManager.addBuff(killer, killer, newdb.getQ_buff_id(), timenum  , 0, 0);
								MessageUtil.notify_player(killer, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得了【{1}】连斩状态，攻击力加成{2}"),newdb.getQ_buff_name(),killer.getEvencutatk()+"");
							}
						}else {
							ManagerPool.buffManager.removeByBuffId( killer, olddb.getQ_buff_id());//不管有没有，先删除一次
							if (buff != null) { //身上当前连斩BUFF
								Q_buffBean buffmod = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());
								long timenum = buff.getTotalTime() - buffmod.getQ_effect_time() *1000 ;
								if (timenum < 0) {
									timenum = 0;
								}
								ManagerPool.buffManager.addBuff(killer, killer, newdb.getQ_buff_id(), timenum , 0, 0);
							}else {
								ManagerPool.buffManager.addBuff(killer, killer, newdb.getQ_buff_id(), 0, 0, 0);
							}

							MessageUtil.notify_player(killer, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您获得了【{1}】连斩状态，攻击力加成{2}"),newdb.getQ_buff_name(),killer.getEvencutatk()+"");
						}
					}
				}
			}
		}
	}
	

	
	
	
	/**是否能秒杀怪物 true 
	 * 
	 * @param player
	 * @return
	 */
	public boolean checkEvencut(Player player ,Monster monster){
		int num = player.getEvencutnum() + 1;
		if (num%10 == 0) {
			Q_monsterBean q_monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
			if (q_monsterBean != null) {
				if (q_monsterBean.getQ_monster_type() == 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	/**获取可用的是BUFF
	 * 
	 * @param player
	 * @return
	 */
	public Q_evencutBean  getEvencutDB(Player player) {
		List<Q_evencutBean> dblist = ManagerPool.dataManager.q_evencutContainer.getList();
		for (Q_evencutBean q_evencutBean : dblist) {
			if (player.getEvencutnum() < q_evencutBean.getQ_evencut_num()) {
				return q_evencutBean;
			}
		}
		return null ;
	}
	
	
	
	public Q_evencutBean getbuffBydb(Player player){
		List<Q_evencutBean> dblist = ManagerPool.dataManager.q_evencutContainer.getList();
		List<Buff> buff = ManagerPool.buffManager.getBuffByType( player, BuffType.KILLBUFF);
		if (buff.size() > 0) {
			for (Q_evencutBean q_evencutBean : dblist) {
				if (q_evencutBean.getQ_buff_id() == buff.get(0).getModelId()) {
					return q_evencutBean;
				}
			}	
		}
		return null; 
	}


	/**重新加BUFF
	 * 
	 * @param player
	 * @param db
	 */
	public void addbuff(Player player,Q_evencutBean db){
		List<Q_evencutBean> dblist = ManagerPool.dataManager.q_evencutContainer.getList();
		for (Q_evencutBean q_evencutBean : dblist) {
			ManagerPool.buffManager.removeByBuffId( player, q_evencutBean.getQ_buff_id());
		}
		ManagerPool.buffManager.addBuff(player, player, db.getQ_buff_id(), 0, 0, 0);
		
	}
	
	
	
	/**获取当前连斩BUFF
	 * 
	 * @param player
	 * @return
	 */
	public Buff getBatterBuff(Player player){
		List<Q_evencutBean> dblist = ManagerPool.dataManager.q_evencutContainer.getList();
		for (Q_evencutBean q_evencutBean : dblist) {
			List<Buff> buff = ManagerPool.buffManager.getBuffByModelId( player, q_evencutBean.getQ_buff_id());
			if (buff.size() > 0) {
				return buff.get(0);
			}
		}
		return null; 
	}
	
	
	
	
	
	
	
	
	/**对BOSS连击
	 * 
	 */
	public void bossBatter(Player player ){
		if(player.getBossbattertime() + 5*1000 > System.currentTimeMillis()) {
			player.setBossbattertime(System.currentTimeMillis());
			player.setBossbatternum(player.getBossbatternum() + 1);
			if (player.getBossbatternum() == 2 ) {
				ManagerPool.buffManager.addBuff(player, player,BOSSBATTERBUFF, 0, 0, 0);
			}
			//MessageUtil.notify_player(player, Notifys.NORMAL, "连击{1}",player.getBossbatternum()+"");
		}else {//清除
			player.setBossbatternum(1);
			player.setBossbattertime(System.currentTimeMillis());
		} 
		ResMonsterBatterToClientMessage cmsg = new ResMonsterBatterToClientMessage();
		int atk = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel()).getQ_batter_atk();
		cmsg.setAtk(player.getBossbatternum()*atk);
		cmsg.setNum(player.getBossbatternum());
		cmsg.setCountdowntime(5);
		cmsg.setType((byte) 2);
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	/**获取连击BOSS攻击力
	 * @param player
	 * @return
	 */
	public int getbossBatter(Player player){
		HashMap<Integer, Q_characterBean> data = ManagerPool.dataManager.q_characterContainer.getMap();
		if (data != null) {
			int atk =data.get(player.getLevel()).getQ_batter_atk();
			return player.getBossbatternum()*atk;	
		}
		return 0;
	}
	
	
	/**登陆发送连斩攻击力
	 * 
	 * @param player
	 */
	public void loginBatter(Player player){
		ResMomentKillToClientMessage cmsg = new ResMomentKillToClientMessage();
		cmsg.setEvencutatk(player.getEvencutatk());
		cmsg.setBatteratk(getbossBatter( player));
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	
	
	
	
}
