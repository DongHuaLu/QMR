package scripts.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.game.backpack.script.IConsumeGoldScript;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.scripts.bean.PanelInfo;
import com.game.scripts.bean.PanelTxtInfo;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
/**消费元宝返还礼金活动（聚宝盆）
 * 
 * @author zhangrong
 *
 */
public class ConsumeGoldScript implements IConsumeGoldScript{

	@Override
	public int getId() {
		return ScriptEnum.RETBINDGOLD;
	}
	
	//活动ID（活动开始日期做ID，慎重修改）
	private String activitieid= "20130204";
	//返还百分比, 1 = 百分百
	private double retPercentage = 1;
	//开始时间
	private String actstart = "2013-2-4 00:00:01";
	//结束时间
	private String actend = "2013-2-15 23:59:59";
	//面板ID
	private int panelid = 19;
	
	
	
	/**消费元宝触发
	 * 
	 */
	@Override
	public boolean consumegold(Player player, int num, Reasons reason,long action) {
		if (num < 0 ) {
			int gold = Math.abs(num);
			Date startdate = TimeUtil.getDateByString(actstart);
			Date enddate = TimeUtil.getDateByString(actend);
			if ( System.currentTimeMillis() >= startdate.getTime() && System.currentTimeMillis() < enddate.getTime()) {
				double lijing = (double)gold * retPercentage;

				if(player.getRetbindgoldid()!=null && player.getRetbindgoldid().equals(activitieid)){
					player.setRetbindgoldsum(player.getRetbindgoldsum()+lijing);
					player.setRetcurrbindgold(player.getRetcurrbindgold()+lijing);
				}else {
					player.setRetbindgoldid(activitieid);
					player.setRetbindgoldsum(lijing);
					player.setRetcurrbindgold(lijing);
				}
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("聚宝盆活动：消费{1}元宝，获得返还{2}礼金"),gold+"", (int)(lijing) +"");
			}
		}
		return false;
	}
	
	
	
	
	/**登录检测返还礼金活动
	 * 
	 */
	public void loginRetbindgold(List<Object> plist){
		Player player = (Player)plist.get(0);
		if (player == null) {
			return;
		}
		
		Date startdate = TimeUtil.getDateByString(actstart);
		Date enddate = TimeUtil.getDateByString(actend);
		if ( System.currentTimeMillis() >= startdate.getTime() && System.currentTimeMillis() < enddate.getTime()) {
			//如果是活动时间内，展示说  明按钮和累计礼金信息
			ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
			sendMessage.setScriptid(getId());
			sendMessage.setType(1);
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("method", "showreceiveretbindgold");	
			sendMessage.setMessageData(JSON.toJSONString(paramMap));
			MessageUtil.tell_player_message(player, sendMessage);	
			player.getPanelverify().put("105_showreceiveretbindgold", "");
		}else {//如果活动已经结束，而且可领取礼金大于1，展示  可领取按钮
			if (player.getRetcurrbindgold() >= 1) {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("method", "showreceiveretbindgold");
				ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
				sendMessage.setScriptid(getId());
				sendMessage.setType(2);
				sendMessage.setMessageData(JSON.toJSONString(paramMap));
				MessageUtil.tell_player_message(player, sendMessage);	
				player.getPanelverify().put("105_showreceiveretbindgold", "");
			}
		}
	}
	
	
	/**展示 (面板)
	 * 
	 */
	public void showreceiveretbindgold(List<Object> plist){
		Player player = (Player)plist.get(0);
		if (player == null) {
			return;
		}
		Date startdate = TimeUtil.getDateByString(actstart);
		Date enddate = TimeUtil.getDateByString(actend);
		if ( System.currentTimeMillis() >= startdate.getTime() && System.currentTimeMillis() < enddate.getTime()) {
			PanelInfo  panel = NpcParamUtil.getPanelInfo(player, panelid);
			List<String> txtlist =new ArrayList<String>();
			txtlist.add("labAllBindGold#0#"+(int)player.getRetcurrbindgold());
			txtlist.add("labDayBindGold#0#"+(int)(player.getRetbindgoldsum()* 0.1));
			txtlist.add("btnGet#2#"+ResManager.getInstance().getString("活动结束后可领取"));
			txtlist.add("labWordTime#1#[time:"+(enddate.getTime() - System.currentTimeMillis())/1000 +"]");
			panel.setPaneltxtinfolist(getPanelTxtInfo(txtlist));
			NpcParamUtil.showPanel(player, panel);

		}else {
			PanelInfo  panel = NpcParamUtil.getPanelInfo(player, panelid);
			List<String> panellist = new ArrayList<String>();
			List<String> txtlist =new ArrayList<String>();
			if (player.getRetcurrbindgold() >= 1) {
				txtlist.add("labAllBindGold#0#"+(int)player.getRetcurrbindgold());
				txtlist.add("labDayBindGold#0#"+(int)(player.getRetbindgoldsum()/10));
				panellist.add("btnGet#" + this.getId() + "#receiveretbindgold#"+panelid);
				txtlist.add("btnGet#2#"+ResManager.getInstance().getString("领取奖励"));
			}else{
				txtlist.add("labAllBindGold#0#0");
				txtlist.add("labDayBindGold#0#0");
				txtlist.add("btnGet#2#"+ResManager.getInstance().getString("已经全部领取"));
			}
			txtlist.add("labWordTime#0#"+ResManager.getInstance().getString("已结束"));
			panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , panellist));
			panel.setPaneltxtinfolist(getPanelTxtInfo(txtlist));
			NpcParamUtil.showPanel(player, panel);
		}
	}

	
	
	
	/**领取返还礼金
	 * 
	 */
	public void receiveretbindgold(List<Object> list){
		Player player = (Player)list.get(0);
		if (player == null) {
			return;
		}
		
		Date startdate = TimeUtil.getDateByString(actstart);
		Date enddate = TimeUtil.getDateByString(actend);
		if ( System.currentTimeMillis() >= startdate.getTime() && System.currentTimeMillis() < enddate.getTime()) {
			//活动进行期间不可领取
			
		}else {
			if (player.getRetcurrbindgold() >= 1) {
				int ling = (int) (player.getRetbindgoldsum() * 0.1);
				int day = TimeUtil.GetCurDay(0);
				if (day != player.getRetbindgoldday()) {
					if(player.getRetcurrbindgold() >= ling ){
						player.setRetcurrbindgold(player.getRetcurrbindgold() - ling);
						player.setRetbindgoldday(day);
						ManagerPool.backpackManager.changeBindGold(player, ling, Reasons.def21,  Config.getId());
						MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("获得{1}礼金"),ling+"");
					}else {
						ling = (int) player.getRetcurrbindgold();
						player.setRetcurrbindgold(0);
						ManagerPool.backpackManager.changeBindGold(player, ling, Reasons.def21,  Config.getId());
						MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("获得{1}礼金，(已全部领取)"),ling+"");
						
						ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
						sendMessage.setScriptid(this.getId());
						sendMessage.setType(0);
						sendMessage.setMessageData("");
						MessageUtil.tell_player_message(player, sendMessage);	
					}
				}else {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("每天只能领取一次，礼金总额的10%，明天再来吧"),ling+"");
				}
				List<Object> list2=new ArrayList<Object>();
				list2.add(player);
				showreceiveretbindgold(list2);//刷新面板
				return;
			}
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("没有可领取的礼金了"));
		}
	}
	


	
	/**转换自定义文本内容
	 * 
	 * @param player
	 * @param idx  面板数据id
	 * @param list 按钮列表
	 * @return
	 */
	public static List<PanelTxtInfo> getPanelTxtInfo( List<String> list){
		List<PanelTxtInfo> txtList = new ArrayList<PanelTxtInfo>();
		for (String string : list) {
			String[] strlist = string.split(Symbol.JINGHAO_REG);
			if (strlist.length >= 3) {
				PanelTxtInfo txtInfo = new PanelTxtInfo();
				txtInfo.setName(strlist[0]);
				txtInfo.setType( Byte.valueOf(strlist[1]));
				txtInfo.setContent(strlist[2]);
				txtList.add(txtInfo);
			}
		}
		return txtList;
	}
	
	

}
