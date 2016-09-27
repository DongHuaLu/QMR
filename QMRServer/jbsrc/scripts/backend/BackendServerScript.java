package scripts.backend;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.json.JSONserializable;
import com.game.mail.manager.MailServerManager;
import com.game.map.manager.MapManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Reasons;
import com.game.utils.ParseUtil;
import com.game.utils.ScriptsUtils;

public class BackendServerScript implements IScript {

	private static Logger log = Logger.getLogger(BackendServerScript.class);
	private static Logger txlog = Logger.getLogger("txlog");
	
	@Override
	public int getId() {
		return ScriptEnum.BACKENDSERVER;
	}
	
	//腾讯道具发放接口
	public void addItemTx(List<String> params){
		String oid="", roleid="", items = "";
		 //-1初始化 0-成功 1-失败
		String ret = "-1"; String msg = "初始化";
		try{
			oid = params.get(0); roleid = params.get(1); items = params.get(2);
			//发放道具
			List<Item> additems = new ArrayList<Item>();
			List<HashMap<String, String>> listmap = (List<HashMap<String, String>>) JSONserializable.toList(items, HashMap.class);
			for(HashMap<String, String> map: listmap){
				int itemid = ParseUtil.getMapInt(map, "itemmodelid");
				int num = ParseUtil.getMapInt(map, "num");
				int bind = ParseUtil.getMapInt(map, "bind");
				int losttime = ParseUtil.getMapInt(map, "losttime"); //losttime = 0无过期时间  >0过期秒数
				List<Item> createitems = Item.createItems(itemid, num, bind==0?false:true, losttime==0?0:System.currentTimeMillis()+losttime*1000);
				additems.addAll(createitems);
			}
			Player player = PlayerManager.getInstance().getOnLinePlayer(Long.valueOf(roleid)); //得到在线玩家
			if(player!=null){ //在线 加背包 
				if(!BackpackManager.getInstance().addItems(player, additems, Reasons.TXITEM, Config.getId())){
					//加背包失败 发邮件
					MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "", "", (byte)1, 0, additems);
				}
			}else{ //不在线 发邮件
				if(!MailServerManager.getInstance().sendSystemMail(Long.valueOf(roleid), "", "", "", (byte)1, 0, additems)){  //TODO 标题和内容
					txlog.error("腾讯发道具发送邮件失败"+oid+"\t"+roleid+"\t"+items);
				}
			}
			ret = "0"; msg = "成功";
		}catch (Exception e) {
			log.error(e, e);
			txlog.error(e);//记录文本日志
			ret = "1"; msg=e.toString();
		}
		//通知世界服
		ScriptsUtils.callWorld(ScriptEnum.TXADDITEMS, "doResAddItem", oid, ret, msg);
	}

	//关监狱 [0]=roleid1,roleid2,roleid3
	public void inprison(List<String> params){
		if(params.size()>0){
			String roleid = params.get(0);
			Player player = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
			if(player!=null){
				player.setHeartCheckTimes(500); //丢进监狱
				if(params.size()>1){
					String time = params.get(1);
					player.setPrisonRemainTime(player.getPrisonRemainTime()+Integer.parseInt(time)*60); //PrisonRemainTime 单位是秒
					//通知前端关监狱时间变更
					PlayerManager.getInstance().sendPlayerPrisonState(player);
				}
			}
		}
	}
	
	//从监狱放出来 [0]=roleid1,roleid2,roleid3
	public void outprison(List<String> params){
		if(params.size()>0){
			String roleid = params.get(0);
			Player player = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
			if(player!=null){
				player.setPrisonRemainTime(0); //蹲监狱剩余时间
				player.setPrisonEnterTime(0L);
				if(params.size()>1){
					int prisontimes = Integer.parseInt(params.get(1));
					player.setPrisonTimes(prisontimes); //
				}
			}
		}
	}
	
	//全部从监狱放出来
	public void alloutprison(List<String> params){
		if(params.size()>0){
			String serverid = params.get(0);
			com.game.map.structs.Map prisonmap = MapManager.getInstance().getMap(Integer.parseInt(serverid), 1, 42122); //取监狱地图
			Iterator<Player> it = prisonmap.getPlayers().values().iterator();
			while(it.hasNext()){
				Player target = it.next();
				if(target!=null){
					target.setPrisonRemainTime(0); //蹲监狱剩余时间
					if(params.size()>1){
						int prisontimes = Integer.parseInt(params.get(1));
						target.setPrisonTimes(prisontimes); //
					}
				}
			}
		}
	}
	
	//移除105号脚本
	@SuppressWarnings("unchecked")
	public void remove105script(List<String> params){
		try {
			Field field = ScriptManager.getInstance().getClass().getDeclaredField("scripts");
			field.setAccessible(true);
			ConcurrentHashMap<Integer, IScript> scripts = (ConcurrentHashMap<Integer, IScript>) field.get(ScriptManager.getInstance());
			log.error(scripts.contains(105));
			scripts.remove(105); //移除105号脚本
			field.setAccessible(false);
			log.error("移除105号脚本成功");
		} catch (SecurityException e) {
			log.error(e, e);
		} catch (NoSuchFieldException e) {
			log.error(e, e);
		} catch (IllegalArgumentException e) {
			log.error(e, e);
		} catch (IllegalAccessException e) {
			log.error(e, e);
		}
		log.error("移除105号脚本失败");
	}
	
	public static void main(String[] args){
		List<String> params = new ArrayList<String>();
		params.add("oid1"); params.add("370034342543226"); params.add("[{\"itmemmodelid\":1011,\"num\":3,\"bind\":1,\"losttime\":1},{\"itmemmodelid\":101111,\"num\":3,\"bind\":1,\"losttime\":1}]");
		BackendServerScript s = new BackendServerScript();
		s.addItemTx(params);
	}
	
	
}
