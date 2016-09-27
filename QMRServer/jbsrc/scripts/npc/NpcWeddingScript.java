package scripts.npc;

import org.apache.log4j.Logger;

import com.game.count.structs.CountTypes;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.marriage.message.ResEdibleInfoToClientMessage;
import com.game.marriage.message.ResWeddingbanquetToClientMessage;
import com.game.marriage.structs.Marriage;
import com.game.marriage.structs.RedEnvelope;
import com.game.marriage.structs.Wedding;
import com.game.npc.script.INpcDefaultActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;

public class NpcWeddingScript implements  INpcDefaultActionScript{
	protected Logger log = Logger.getLogger(NpcWeddingScript.class);
	public static int scriptId = 5013;		//scriptid
	@Override
	public int getId() {
		return scriptId;
	}


	
	/**点击NPC触发
	 * 
	 */
	@Override
	public void defaultAction(Player player, NPC npc) {
		try {
			if(!npc.getParameters().containsKey("marriageid")){
				return;
			}
			
			long marriageid=(Long) npc.getParameters().get("marriageid");
			int num=(Integer) npc.getParameters().get("num");
			if (num <= 0) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的下手过慢了，该桌上的菜肴已经被吃光了"));
				return;
			}
			
			if (npc.getParameters().containsKey("player_"+ player.getId())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("婚宴均为流水席，您已经食用过该餐桌上的食物，无法再次食用。请转到请到下一席"));
				return;
			}
			
			if (marriageid == player.getMarriageid()) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("这是您自己的婚宴，请不要与宾客争夺菜肴"));
				return;
			}
			
			Marriage marriage = ManagerPool.marriageManager.getMarriage(marriageid);
			if (marriage == null) {
				return;
			}
	
			double distance = MapUtils.countDistance(player.getPosition(), npc.getPosition());	//得到距离
			if (distance > MapUtils.GRID_BORDER * 10) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("距离过远，请靠近婚宴餐桌才能享用菜肴"));
				return;
			}
			
			
			Wedding wedding = ManagerPool.marriageManager.getWedding(marriageid);
			
			if (wedding != null) {
				boolean is = false;	//默认没有给红包
				for (RedEnvelope redEnvelope : wedding.getRedenvelopes()) {
					if (redEnvelope.getPlayerid() == player.getId()) {
						is = true;//检测到已经给过红包
						break;
					}
				}
				
				if (is) {//给过红包，弹出食用婚宴界面消息
					ResEdibleInfoToClientMessage cmsg= new ResEdibleInfoToClientMessage();
					cmsg.setFoodnum(num);
					cmsg.setMarriageid(marriageid);
					cmsg.setNpcid(npc.getId());
					long exp = ManagerPool.countManager.getCount(player, CountTypes.WEDDING_EXP, null);
					long zhenqi = ManagerPool.countManager.getCount(player, CountTypes.WEDDING_ZHENQI, null);
					cmsg.setTotalexp((int) exp);
					cmsg.setTotalzhenqi((int) zhenqi);
					cmsg.setType(wedding.getType());
					cmsg.setBride(marriage.getSpouseslist().get(1).getName());
					cmsg.setBridegroom(marriage.getSpouseslist().get(0).getName());
					MessageUtil.tell_player_message(player, cmsg);
					
				}else {	//如果没给过红包，发下面2个消息
					ResWeddingbanquetToClientMessage cmsg = new ResWeddingbanquetToClientMessage();
					cmsg.setBride(marriage.getSpouseslist().get(1).getName());
					cmsg.setBridegroom(marriage.getSpouseslist().get(0).getName());
					cmsg.setFoodnum(num);
					cmsg.setNpcid(npc.getId());
					cmsg.setMarriageid(marriageid);
					long leiji = ManagerPool.countManager.getCount(player, CountTypes.RED_ENVELOPE, null);
					cmsg.setTotalredenvelope((int) leiji);
					cmsg.setType(wedding.getType());
					MessageUtil.tell_player_message(player, cmsg);
					
					
					ResEdibleInfoToClientMessage cmsg2= new ResEdibleInfoToClientMessage();
					cmsg2.setBride(marriage.getSpouseslist().get(1).getName());
					cmsg2.setBridegroom(marriage.getSpouseslist().get(0).getName());
					cmsg2.setFoodnum(num);
					cmsg2.setMarriageid(marriageid);
					cmsg2.setNpcid(npc.getId());
					long exp = ManagerPool.countManager.getCount(player, CountTypes.WEDDING_EXP, null);
					long zhenqi = ManagerPool.countManager.getCount(player, CountTypes.WEDDING_ZHENQI, null);
					cmsg2.setTotalexp((int) exp);
					cmsg2.setTotalzhenqi((int) zhenqi);
					cmsg2.setType(wedding.getType());
					MessageUtil.tell_player_message(player, cmsg2);
				}
			}
		} catch (Exception e) {
			log.error(e,e);
		}
	}





}
