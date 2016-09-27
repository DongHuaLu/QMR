package scripts.item;

import java.util.ArrayList;
import java.util.List;

import com.game.activities.log.TailiLog;
import com.game.activities.message.ReqGetTailiToWorldMessage;
import com.game.backpack.script.IItemScript;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.ButtonInfo;
import com.game.scripts.bean.PanelInfo;
import com.game.scripts.bean.PanelTxtInfo;
import com.game.server.impl.WServer;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;

/**
 * 送台历的面板
 * @author Administrator
 *
 */
public class TailiGift implements IItemScript {
	int panelId = 23;

	@Override
	public int getId() {
		return 1009190;
	}
	
	@Override
	public boolean use(Item item, Player player, String... parameters) {
		PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelId);
		
		List<ButtonInfo> buttonInfos = new ArrayList<ButtonInfo>();
		ButtonInfo button = new ButtonInfo();
		button.setName("btnGetRewards");
		button.setScriptId(Integer.valueOf(this.getId()));
		button.setMethod("open");
		button.getParas().add(String.valueOf(item.getId()));
		button.getParas().add("@");
		button.getParas().add("editName");
		button.getParas().add("editPhone");
		button.getParas().add("editAddress");
		buttonInfos.add(button);
		player.getPanelverify().put(this.getId()+"_open", "@");
		panel.setButtoninfolist(buttonInfos);
		
		List<PanelTxtInfo> paneltxtinfolist = new ArrayList<PanelTxtInfo>();
		PanelTxtInfo txtInfo = new PanelTxtInfo();
		txtInfo.setType((byte) 1);
		txtInfo.setName("btnGetRewards");
		txtInfo.setContent("请确认信息是否正确？");
		paneltxtinfolist.add(txtInfo);
		panel.setPaneltxtinfolist(paneltxtinfolist);
		
		NpcParamUtil.showPanel(player, panel);
		return false;
	}

	public void open(List<Object> para){
		if (para.size() != 6) return ;
		
		Player player = (Player) para.get(0);
		if (player == null) return ;
		
		long itemId = Long.valueOf((String)para.get(1));
		Item item = ManagerPool.backpackManager.getItemById(player, itemId);
		if(item == null) return ;
		
		String name = (String)para.get(3);
		String phone = (String)para.get(4);
		String addr = (String)para.get(5);
		
		if(name==null || phone==null || addr==null || name.trim().length()==0 || phone.trim().length()==0 || addr.trim().length()==0){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("填写信息不完整，请完善信息后再提交"));
			return;
		}
		
		if (name.length() > 1000) {
			name = name.substring(0, 1000);
		}
		
		if (phone.length() > 20) {
			phone = phone.substring(0, 20);
		}
		
		if (addr.length() > 1000) {
			addr = addr.substring(0, 1000);
		}
		
		if (ManagerPool.backpackManager.removeItem(player, item, 1, Reasons.GOODUSE, Config.getId())) {
			TailiLog log = new TailiLog();
			
			log.setName(name);
			log.setPhone(phone);
			log.setAddr(addr);
			log.setAgent(WServer.getInstance().getServerWeb());
			log.setRole(player.getName());
			log.setUser(player.getUserName());
			log.setZone(player.getServerId());
			LogService.getInstance().execute(log);
			
			// 转到世界服取当日充值金额
			ReqGetTailiToWorldMessage msg = new ReqGetTailiToWorldMessage();
			msg.setAddr(log.getAddr());
			msg.setName(log.getName());
			msg.setPhone(log.getPhone());
			msg.setPlayerid(player.getId());
			MessageUtil.send_to_world(msg);
			
			NpcParamUtil.setPanel(player, panelId, new ArrayList<String>(), 1);
		}
	}
}
