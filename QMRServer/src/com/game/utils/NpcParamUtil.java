package com.game.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.game.data.bean.Q_panel_dataBean;
import com.game.manager.ManagerPool;
import com.game.npc.bean.ServiceInfo;
import com.game.player.structs.Player;
import com.game.scripts.bean.ButtonInfo;
import com.game.scripts.bean.PanelInfo;
import com.game.scripts.bean.PanelStatusInfo;
import com.game.scripts.bean.PanelTxtInfo;
import com.game.scripts.message.ReqScriptExcuteMessage;
import com.game.scripts.message.ResPanelStatusInfoToClientMessage;
import com.game.scripts.message.ResShowPaneleInfoToClientMessage;

/**
 * NPC解析
 * @author zhangrong
 */
public class NpcParamUtil {
	public static HashMap<String, String> sayMap = new HashMap<String, String>();
	private static HashMap<String, String> vMap = new HashMap<String, String>();
	
	/**按钮
	 * 
	 */
	public static String BUTTON = "button";
	
	/**NPC对话
	 * 
	 * @param player
	 * @param infos
	 */
	public static void npcsay(Player player,List<ServiceInfo> infos){
		player.getNpcverify().clear();
		for (Entry<String, String> entry : sayMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (BUTTON.equals(value)) {
				String[] strlist = key.split(Symbol.FENHAO);
				vMap.clear();
				ServiceInfo info = new ServiceInfo();
				info.setServiceId(20);
				for (int i = 0; i < strlist.length; i++) {
					String[] tab = strlist[i].split("=");
					vMap.put(tab[0], tab[1]);
					if (i==0) {
						info.setServiceName(tab[1]);
					}
				}
				
				String jsonString = JSON.toJSONString(vMap);
				info.setServiceParameter(jsonString);
				infos.add(info);
				player.getNpcverify().add(jsonString);
			}
		}
		sayMap.clear();
	}
	
	
	/**NPC对话,传入数组
	 * String [][] str= {{"name=领取奖励;id=1" ,NpcParamUtil.BUTTON}};
	 * @param player
	 * @param infos
	 * @param tab
	 */
	public static void say(Player player,List<ServiceInfo> infos,String [][] tab) {
		sayMap.clear();
		for (String[] strings : tab) {
			sayMap.put(strings[0], strings[1]);
		}
		npcsay( player,infos);
	}
	
	
	/**解析检查NPC字符串
	 * @param player
	 * @param parameters
	 * @return
	 */
	public static HashMap<String, Object> resolve(Player player , String parameters){
		if(player.getNpcverify().contains(parameters)){
			@SuppressWarnings("unchecked")
			HashMap<String, Object> paramMap = JSON.parseObject(parameters, HashMap.class);
			return paramMap;
		}
		return null;
	}
	
	
	
	
	
	//------------------------------------自定义面板-----------------------------------------

	
	/**产生面板
	 * 
	 * @param player
	 * @param idx  面板数据id
	 * @return
	 */
	public static PanelInfo getPanelInfo(Player player ,int idx ){
		Q_panel_dataBean data = ManagerPool.dataManager.q_panel_dataContainer.getMap().get(idx);
		if (data != null) {
			PanelInfo panelInfo = new PanelInfo();
			panelInfo.setPanelmap(data.getQ_panel_xml());
			return panelInfo;
		}
		return null;
	}
		
	
	/**转换自定义按钮
	 * 
	 * @param player
	 * @param idx  面板数据id
	 * @param list 按钮列表
	 * @return
	 */
	public static List<ButtonInfo> getbuttonInfo(Player player , List<String> list){
		List<ButtonInfo> buttonInfos = new ArrayList<ButtonInfo>();
		for (String string : list) {
			String[] strlist = string.split(Symbol.JINGHAO_REG);
			if (strlist.length >= 3) {
				String param = "";
				ButtonInfo buttonInfo = new ButtonInfo();
				buttonInfo.setName(strlist[0]);
				buttonInfo.setScriptId(Integer.valueOf(strlist[1]));
				buttonInfo.setMethod(strlist[2]);
				
				if (strlist.length>= 4) {
					String[] strings = strlist[3].split(Symbol.DOUHAO);
					for (int i = 0; i < strings.length; i++) {
						buttonInfo.getParas().add(strings[i]);
					}
					param = strlist[3];
				}
				buttonInfos.add(buttonInfo);
				if (!strlist[1].equals("0")) {	//写入验证 
					player.getPanelverify().put(strlist[1]+"_"+strlist[2], param);
				}
			}
			
		}
		return buttonInfos;
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
	
	
	
	
	/**展示面板
	 * 
	 * @param player
	 * @param idx
	 * @param list
	 * "anniu1#1001#setmap#1,2,3,4,5"  
	 *  控件名称#脚本ID#执行方法#变量
	 */
	public static void showPanel(Player player,PanelInfo panelInfo){
		ResShowPaneleInfoToClientMessage cmsg = new ResShowPaneleInfoToClientMessage();
		cmsg.setPanelInfo(panelInfo);
		MessageUtil.tell_player_message(player, cmsg);
	}

	
	
	//------------------------------------------------------------------------------

	/**获取设置面板状态格式
	 * 
	 * @param player
	 * @param idx  面板数据id
	 * @param list 按钮列表
	 * "anniu1#1001#setmap#1,2,3,4,5"  
	 *  按钮名称#脚本ID#执行方法#变量
	 *  "btnyanzheng#0#0#0" 让按钮变灰色
	 * @return
	 */
	public static PanelStatusInfo getPanelStatusInfo(Player player ,int idx , List<String> list,int type){
		Q_panel_dataBean data = ManagerPool.dataManager.q_panel_dataContainer.getMap().get(idx);
		if (data != null) {
			PanelStatusInfo panelStatusInfo = new PanelStatusInfo();
			panelStatusInfo.setType((byte) type);
			panelStatusInfo.setPanelname(data.getQ_panel_id());
			for (String string : list) {
				String[] strlist = string.split(Symbol.JINGHAO_REG);
				if (strlist.length >= 3) {
					String param = "";
					ButtonInfo buttonInfo = new ButtonInfo();
					buttonInfo.setName(strlist[0]);
					buttonInfo.setScriptId(Integer.valueOf(strlist[1]));
					buttonInfo.setMethod(strlist[2]);
					
					if (strlist.length>= 4) {
						String[] strings = strlist[3].split(Symbol.DOUHAO);
						for (int i = 0; i < strings.length; i++) {
							buttonInfo.getParas().add(strings[i]);
						}
						param = strlist[3];
					}
					panelStatusInfo.getButtoninfolist().add(buttonInfo);
					
					if (!strlist[1].equals("0")) {	//写入验证 
						player.getPanelverify().put(strlist[1]+"_"+strlist[2], param);
					}
					
				}
			}
			return panelStatusInfo;
		}
		return null;
	}	
	
	
	/**设置改变面板状态
	 * 
	 * @param player
	 * @param idx
	 * @param list
	 * "btnyanzheng#0#0#0" 让按钮变灰色
	 * 
	 */
	public static void setPanel(Player player,int idx , List<String> list,int type){
		ResPanelStatusInfoToClientMessage cmsg = new ResPanelStatusInfoToClientMessage();
		cmsg.setPanelStatusInfo(getPanelStatusInfo(player , idx ,list,type));
		MessageUtil.tell_player_message(player, cmsg);
	}
	

	
	/**检查验证面板按钮调用方法和参数是否合法
	 * 
	 * @param player
	 * @param msg
	 * @return
	 */
	public static boolean buttonVerification(Player player ,ReqScriptExcuteMessage msg){
		if (msg.getScriptId() == 1001027 || msg.getScriptId() == 104 || msg.getScriptId() == 106) {
			return true;
		}
		
		String key=msg.getScriptId()+"_"+msg.getMethod();
		if(player.getPanelverify().containsKey(key)){
			String variable= player.getPanelverify().get(key);
			String vString = "";
			if (variable.contains("@")) {	//如果包含@表示由前端输入返回值，不需要继续验证
				return true;
			}
			if (msg.getParas().size() > 0) {
				for (int i = 0; i < msg.getParas().size(); i++) {
					if (msg.getParas().size() - 1 == i) {
						vString=vString+msg.getParas().get(i);
					}else{
						vString=vString+msg.getParas().get(i)+",";
					}
				}
				if (!vString.equals(variable)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	
	
	/**测试
	 * 
	 * @param player
	 * @param idx
	 */
	public static void Testjiaoben(Player player ,int idx ){
		PanelInfo panel = getPanelInfo(player, idx);
		
		List<String> blist =new ArrayList<String>();
		blist.add("btnyanzheng#1009101#setmap");
		blist.add("anniu2#1002#setmapxxx#1,2,3,4,5,6");
		panel.setButtoninfolist(getbuttonInfo(player , blist));
		
		List<String> txtlist =new ArrayList<String>();
		txtlist.add("labNitice#0#替换内容dfhfdghghfghfgh");
		panel.setPaneltxtinfolist(getPanelTxtInfo(txtlist));
		
		getPanelTxtInfo(txtlist);
		showPanel(player , panel);
	}
	
	
	
	
	
	
}












