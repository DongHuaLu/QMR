package com.game.spirittree.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.arrow.structs.ArrowReasonsType;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_spirittree_pack_conBean;
import com.game.data.bean.Q_spirittree_packetBean;
import com.game.data.manager.DataManager;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.structs.RankType;
import com.game.server.impl.WServer;
import com.game.spirittree.message.ReqContinuousRipeningToGameMessage;
import com.game.spirittree.message.ReqContinuousRipeningToWorldMessage;
import com.game.spirittree.message.ReqGetAllFruitInfoToGameMessage;
import com.game.spirittree.message.ReqGetAllFruitInfoToWorldMessage;
import com.game.spirittree.message.ReqGetSingleFruitInfoToGameMessage;
import com.game.spirittree.message.ReqGetSingleFruitInfoToWorldMessage;
import com.game.spirittree.message.ReqGuildFruitLogToWorldMessage;
import com.game.spirittree.message.ReqGuildFruitOperatingToGameMessage;
import com.game.spirittree.message.ReqGuildFruitOperatingToWorldMessage;
import com.game.spirittree.message.ReqGuildGMToWorldMessage;
import com.game.spirittree.message.ReqOpenGuildFruitToWorldMessage;
import com.game.spirittree.message.ReqRipeningDecYBToGameMessage;
import com.game.spirittree.message.ReqRipeningFruitToGameMessage;
import com.game.spirittree.message.ReqRipeningFruitToWorldMessage;
import com.game.spirittree.message.ReqUrgeRipeToWorldMessage;
import com.game.spirittree.message.ResActivityReturnFruitToGameMessage;
import com.game.spirittree.message.ResFruitOperatingToGameMessage;
import com.game.spirittree.message.ResGameMakeFruitInfoMessage;
import com.game.spirittree.message.ResGuildAccExpToWorldMessage;
import com.game.spirittree.message.ResRipeningDecYBToWorldMessage;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.FruitRewardAttr;
import com.game.stalls.manager.StallsManager;
import com.game.structs.Reasons;
import com.game.task.struts.RankTaskEnum;
import com.game.task.struts.Task;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;


public class SpiritTreeManager {
	
	private Logger log = Logger.getLogger(StallsManager.class);
	
	private static Object obj = new Object();
	// 管理类实例
	private static SpiritTreeManager manager;

	private SpiritTreeManager() {}

	public static SpiritTreeManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new SpiritTreeManager();
			}
		}
		return manager;
	}
	
	//银色奇异果数据索引
	private int SilverKiwi = 10001;
	//金色奇异果数据索引
	private int GoldKiwi = 20001;
	
	/**组包内容
	 * @return 
	 * 
	 */
	public HashMap<Integer, Q_spirittree_pack_conBean> getpackcondata(){
		 HashMap<Integer, Q_spirittree_pack_conBean> data = DataManager.getInstance().q_spirittree_pack_conContainer.getMap();
		return data;
	}
	
	
/**获取灵树果实消息
 * 
 * @param player
 * @param msg
 */
	public void stReqGetAllFruitInfoToGameMessage(Player player,ReqGetAllFruitInfoToGameMessage msg) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，灵树功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		ReqGetAllFruitInfoToWorldMessage wmsg = new ReqGetAllFruitInfoToWorldMessage();
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world( wmsg);
	}
	
	/**单个灵树操作
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqGetSingleFruitInfoToGameMessage(Player player,ReqGetSingleFruitInfoToGameMessage msg) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，灵树功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		
		if(ManagerPool.backpackManager.getEmptyGridNum(player) <= 0  && msg.getType() == 3 ) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("采摘果实前，请预留一个包裹空格。"));
			return;
		}
		
		ReqGetSingleFruitInfoToWorldMessage wmsg = new ReqGetSingleFruitInfoToWorldMessage();
		wmsg.setPlayerid(player.getId());
		wmsg.setFruitid(msg.getFruitid());
		wmsg.setType(msg.getType());
		MessageUtil.send_to_world( wmsg);
		
		
	}

	
	
	/**世界服务器转发--得到果实奖励
	 * 
	 * @param msg
	 */
	public void stResGameMakeFruitInfoMessage(ResGameMakeFruitInfoMessage msg) {
		Player player =ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		
		try {
			FruitReward fruitReward = (FruitReward) JSONserializable.toObject(msg.getJsFruitdata(),FruitReward.class);
			int id = fruitReward.getItemModelid();
			int tiao=fruitReward.getFruitRewardAttrslist().size();
			long action = msg.getEventid();
			//-1铜币，-2元宝，-3经验，-4真气  -5礼金
			if (fruitReward.getNum() == 0) {
				return ;
			}
			
			if (msg.getPlayerid() != msg.getHostid()) {
				//军衔任务加次数
				ManagerPool.taskManager.action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.HARVESTINGSPIRITTREEFRUIT, 1);
			}

			
			boolean issuccess = true;
			List<Item> createItems = new ArrayList<Item>();
			String itemname="";
			if (id == -1) {
				itemname = ResManager.getInstance().getString("铜币");
				if(player != null && ManagerPool.backpackManager.changeMoney(player, fruitReward.getNum(), Reasons.SPIRITTREE_MONEY, action)){
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！摘取果实获得了{1}({2})。"),itemname,fruitReward.getNum()+"");	
				}else {
					issuccess =false;
				}
				
//			}else if (id== -2) {
//				itemname = "元宝";
//				if(player != null && ManagerPool.backpackManager.changeGold(player, fruitReward.getNum(), Reasons.SPIRITTREE_MONEY, action)){
//					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, "恭喜！摘取果实获得了{1}({2})。",itemname,fruitReward.getNum()+"");
//				}else {
//					issuccess =false;
//				}
			}else if ( id == -3) {
				itemname = ResManager.getInstance().getString("真气");
				if(player != null){
					ManagerPool.playerManager.addZhenqi(player, fruitReward.getNum(),AttributeChangeReason.SPIRITTREE);
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！摘取果实获得了{1}({2})。"),itemname,fruitReward.getNum()+"");
				}
			}else if (id == -4) {
				itemname = ResManager.getInstance().getString("经验");
				if(player != null){
					ManagerPool.playerManager.addExp(player, fruitReward.getNum(),AttributeChangeReason.SPIRITTREE);
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！摘取果实获得了{1}({2})。"),itemname,fruitReward.getNum()+"");
				}
			}else if (id == -5) {
				itemname = ResManager.getInstance().getString("礼金");
				if(player != null && ManagerPool.backpackManager.changeBindGold(player, fruitReward.getNum(), Reasons.SPIRITTREE_BIND_YUANBAO, action)){
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！摘取果实获得了{1}({2})。"),itemname,fruitReward.getNum()+"");
				}	
				
				
			}else if (id == -6) {	//战魂
				itemname = ResManager.getInstance().getString("七曜战魂");
				if(player != null){
					ManagerPool.arrowManager.addFightSpiritNum(player, 1, fruitReward.getNum(), true, ArrowReasonsType.SPIRITTREE);
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！摘取果实获得了{1}({2})"),itemname,fruitReward.getNum()+"");
				}
			}else if (id == -7) {	//军功
				itemname = ResManager.getInstance().getString("军功值");
				if(player != null){
					ManagerPool.rankManager.addranknum(player, fruitReward.getNum(), RankType.OTHER);
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！摘取果实获得了{1}({2})"),itemname,fruitReward.getNum()+"");
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
						BackpackManager.getInstance().addItems(player, createItems,Reasons.SPIRITTREE_ITEM,action);
						MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！摘取果实获得了{1}（{2}）。"),itemMode.getQ_name(),fruitReward.getNum()+"");
					}else {
						issuccess =false;
					}
				}else {
					log.error("果实奖励道具不存在：["+msg.getJsFruitdata() +"]");
				}
			}else{
				log.error("果实奖励ID类型未知：["+msg.getJsFruitdata() +"]");
			}
			
			if(issuccess == false){
				if (id > 0) {
					ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("灵树果实采摘奖励:")+itemname +"("+fruitReward.getNum()+")",(byte) 1,0,createItems);
				}else {
					if (id == -1 ) {	//铜币
						List<Item> createItems2 = new ArrayList<Item>();
						createItems2.add(Item.createMoney(fruitReward.getNum()));
						ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("灵树果实采摘奖励:")+itemname+"("+fruitReward.getNum()+")",(byte) 1,0,createItems2);
					//}else if (id == -2 ) {	//元宝
					//	ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,"系统邮件","灵树果实采摘奖励:"+itemname,(byte) 2,fruitReward.getNum(),new ArrayList<Item>());
					}else if (id == -3) {	//真气
						List<Item> createItems2 = new ArrayList<Item>();
						createItems2.add(Item.createZhenQi(fruitReward.getNum()));
						ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("灵树果实采摘奖励:")+itemname+"("+fruitReward.getNum()+")",(byte) 1,0,createItems2);
					}else if ( id == -4 ) {	//经验
						List<Item> createItems2 = new ArrayList<Item>();
						createItems2.add(Item.createExp(fruitReward.getNum()));
						ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("灵树果实采摘奖励:")+itemname+"("+fruitReward.getNum()+")",(byte) 1,0,createItems2);
					}else if ( id == -5) {	//礼金
						List<Item> createItems2 = new ArrayList<Item>();
						createItems2.add(Item.createBindGold(fruitReward.getNum()));
						ManagerPool.mailServerManager.sendSystemMail(msg.getPlayerid(),null,ResManager.getInstance().getString("系统邮件"),ResManager.getInstance().getString("灵树果实采摘奖励:")+itemname+"("+fruitReward.getNum()+")",(byte) 1,0,createItems2);
					}
				}
				if (player != null) {
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("由于您的包裹已满，灵树果实奖励：")+itemname+"("+fruitReward.getNum()+ResManager.getInstance().getString(") 已经通过邮件发送给您。"));
				}
			}
			
			if (player != null) {
				Q_spirittree_pack_conBean pack_conBean = getpackcondata().get(fruitReward.getIdx());
				if (pack_conBean.getQ_notice_type() >= 3) {
					String fruitname = ResManager.getInstance().getString("普通果实");
					if (msg.getType() == 1) {
						fruitname = ResManager.getInstance().getString("银色奇异果");
					}else if (msg.getType() == 2) {
						fruitname = ResManager.getInstance().getString("金色奇异果");
					}

					if (tiao == 6 && fruitReward.getStrenglevel() == 10) {
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("玩家【%s】摘取【%s】获得顶级装备【%s】，战力瞬间爆棚！{@}"), player.getName(),fruitname,itemname), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.SPIRITTREE.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.SPIRITTREE.getValue());
					}else {
						MessageUtil.notify_All_player(Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜【{1}】摘取【{2}】，获得了：{3}({4})"), player.getName(),fruitname,itemname,fruitReward.getNum()+"");
					}
					
					
				}
			}
		} catch (Exception e) {
			log.error("果实奖励反序列化出错：["+msg.getJsFruitdata() +"]"+e);
		}
	}

	  
	
	/**世界服务器转发--给灵树果实浇水或者仙露得到经验
	 * 
	 * @param msg
	 */
	public void stResFruitOperatingToGameMessage(ResFruitOperatingToGameMessage msg) {
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		if (player != null) {
			if (msg.getPlayerid() == msg.getHostid() && msg.getExp()> 0) {//给自己的果实浇水
				if(msg.getType() == 1){
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("恭喜！给灵树果实浇水获得经验{1}。"),msg.getExp()+"");
				}else if(msg.getType() == 2){
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("恭喜！给灵树果实浇灌灵露获得经验{1}。"),msg.getExp()+"");
				}
			}
			if (msg.getExp() > 0) {
				ManagerPool.playerManager.addExp(player, msg.getExp(),AttributeChangeReason.SPIRITTREEFRUIT);
			}

			if (msg.getType() == 1) {
				ManagerPool.taskManager.action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.SPIRITTREEWATERING, 1);//军衔任务加次数
			}
			
		}
	}

	
	
	/**催熟果实扣元宝
	 * 
	 * @param msg
	 */
	public void stReqRipeningDecYBToGameMessage(ReqRipeningDecYBToGameMessage msg) {
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		if (player != null) {
			long action=Config.getId();
			ResRipeningDecYBToWorldMessage wmsg=new ResRipeningDecYBToWorldMessage();
			if(msg.getYuanbao()>0 && ManagerPool.backpackManager.checkGold(player, msg.getYuanbao())) {
				ManagerPool.backpackManager.changeGold(player,-msg.getYuanbao(),Reasons.SPIRITTREE_RIP_YUANBAO,action);
				wmsg.setType((byte) 1);
			}else {
				wmsg.setType((byte) 0);
				MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("催熟果实所需元宝不足，是否前往充值"));
			}
			wmsg.setFruitid(msg.getFruitid());
			wmsg.setPlayerid(msg.getPlayerid());
			wmsg.setYuanbao(msg.getYuanbao());
			MessageUtil.send_to_world( wmsg);
		}
	}
	
	/**催熟奇异果
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqRipeningFruitToGameMessage(Player player,ReqRipeningFruitToGameMessage msg) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，灵树功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		if(ManagerPool.backpackManager.getEmptyGridNum(player) > 0) {
			ReqRipeningFruitToWorldMessage wmsg = new ReqRipeningFruitToWorldMessage();
			wmsg.setFruitid(msg.getFruitid());
			wmsg.setPlayerid(player.getId());
			MessageUtil.send_to_world( wmsg);
		}else {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("催熟果实前，请预留一个包裹空格。"));
		}
	}

	
	
	
	//------------------------------帮会灵树---------------------------
	
	
	/**打开帮会灵树
	 * 
	 * @param player
	 */
	public void stReqOpenGuildFruitToGameMessage(Player player) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，灵树功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		ReqOpenGuildFruitToWorldMessage wmsg= new ReqOpenGuildFruitToWorldMessage();
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world( wmsg);
	}

	
	/**帮会灵树操作
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqGuildFruitOperatingToGameMessage(Player player,ReqGuildFruitOperatingToGameMessage msg) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，灵树功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		ReqGuildFruitOperatingToWorldMessage wmsg = new ReqGuildFruitOperatingToWorldMessage();
		wmsg.setFruitid(msg.getFruitid());
		wmsg.setHostid(msg.getHostid());
		wmsg.setType(msg.getType());
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world( wmsg);
	}

	
	
	/**请求获取行会灵树日志ToWorld消息
	 * 
	 * @param player
	 */
	public void stReqGuildFruitLogToGameMessage(Player player) {
		if (WServer.getInstance().isConnectWorld()==false) {
			MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，发生未知错误，灵树功能暂时停止使用，请联系客服，或稍后再试。"));
			return ;
		}
		ReqGuildFruitLogToWorldMessage wmsg = new ReqGuildFruitLogToWorldMessage();
		wmsg.setPlayerid(player.getId());
		MessageUtil.send_to_world( wmsg);
	}

	
	/**世界服务器，玩家偷窃后，给果实主人加补偿经验
	 * 
	 * @param msg
	 */
	public void stResGuildAccExpToWorldMessage(ResGuildAccExpToWorldMessage msg) {
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		if (player != null && msg.getExp() > 0) {
			ManagerPool.playerManager.addExp(player, msg.getExp(),AttributeChangeReason.SPIRITTREEFRUIT);
		}
	}

	
	/**灵树GM指令
	 * 
	 * @param msg
	 */
	public void testReqGuildGMToWorldMessage(Player player,int type) {
		ReqGuildGMToWorldMessage wmsg = new ReqGuildGMToWorldMessage();
		wmsg.setPlayerid(player.getId());
		wmsg.setType(type);
		MessageUtil.send_to_world( wmsg);
	}

	
	/**检查果实完成后，收取道具
	 * 
	 * @param msg
	 */
	public void stResActivityReturnFruitToGameMessage(ResActivityReturnFruitToGameMessage msg) {
		Player player = ManagerPool.playerManager.getOnLinePlayer(msg.getPlayerid());
		if (player != null) {
			if (msg.getType() == 2) {
				if (ManagerPool.backpackManager.removeItem(player, 9121, 1, Reasons.URGE_RIPE,Config.getId())) {
					ReqUrgeRipeToWorldMessage wmsg = new ReqUrgeRipeToWorldMessage();
					wmsg.setFruitid(msg.getFruitid());
					wmsg.setPlayerid(msg.getPlayerid());
					wmsg.setType(msg.getType());
					MessageUtil.send_to_world(wmsg);
				}
			}
		}
	}

	
	
	/**转发连续采摘数量，并且扣元宝
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqContinuousRipeningToGameMessage(Player player,ReqContinuousRipeningToGameMessage msg) {
		long action = Config.getId();
		if (msg.getNum() > 0 && msg.getNum() <= 50) {
			Q_spirittree_packetBean kiwibean = null;
			if (msg.getType() == 1) {
				kiwibean= ManagerPool.dataManager.q_spirittree_packetContainer.getMap().get(SilverKiwi);
			}else if (msg.getType() == 2) {
				kiwibean=ManagerPool.dataManager.q_spirittree_packetContainer.getMap().get(GoldKiwi);
			}
			if (kiwibean != null && kiwibean.getQ_urgeripening() > 0) {
				int yb = kiwibean.getQ_urgeripening()*msg.getNum();
				if(!BackpackManager.getInstance().checkGold(player, yb)){
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("元宝不足"));
					return;
				}
				BackpackManager.getInstance().changeGold(player,-yb,Reasons.URGE_YB_RIPE,action);
				ReqContinuousRipeningToWorldMessage wmsg = new ReqContinuousRipeningToWorldMessage();
				wmsg.setNum(msg.getNum());
				wmsg.setType(msg.getType());
				wmsg.setPlayerid(player.getId());
				MessageUtil.send_to_world(wmsg);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("请输入1到50之间"));
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
