package com.game.gm.manager;

import org.apache.log4j.Logger;

import com.game.chat.manager.ChatManager;
import com.game.data.reload.ReLoadManager;
import com.game.gm.message.GmCommandToGateMessage;
import com.game.gm.message.GmCommandToServerMessage;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.GmState;
import com.game.player.structs.Player;
import com.game.server.impl.WServer;
import com.game.server.loader.CheckConfigXmlLoader;
import com.game.server.loader.ServerHeartConfigXmlLoader;
import com.game.systemgrant.manager.SystemgrantManager;
import com.game.utils.Global;
import com.game.utils.HttpUtil;
import com.game.utils.MessageUtil;
import com.game.utils.StringUtil;

/**
 * 
 * @author 赵聪慧
 * @2012-10-30 下午11:14:53
 */
public class BGGmcommandManager {
	
	private static Logger log = Logger.getLogger(BGGmcommandManager.class);
	
	private static BGGmcommandManager instance=new BGGmcommandManager();
	public static BGGmcommandManager getInstance(){
		return instance;
	}
	
	public void gmcommand(GmCommandToServerMessage msg){
//		msg.getAction();
		String[] split = msg.getCommand().split(" ");
		if ("&reload".equalsIgnoreCase(split[0])) {
			ReLoadManager.getInstance().reLoadBybg(split[1], msg.getHttpresult());
		} else if ("&script".equalsIgnoreCase(split[0])) {
			ManagerPool.scriptManager.reload(Integer.parseInt(split[1]), msg.getHttpresult());
		}else if ("&addgm".equalsIgnoreCase(split[0])) {
			Player p = PlayerManager.getInstance().getOnlinePlayerByName(split[1]);
			int level = 1;
			if(split.length>=3 && StringUtil.isNumeric(split[2])){
				int tolevel = Integer.parseInt(split[2]);
				if(tolevel ==1 || tolevel ==2){
					level = Integer.parseInt(split[2]);
				}
			}
			if(p!=null){
				p.setGmlevel(level); 
				p.setGmState(GmState.GM.getValue());
			}
			try {
				HttpUtil.wget(msg.getHttpresult()+"&result=1&location=server");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("&sd".equalsIgnoreCase(split[0])) {
			WServer.getGameConfig().stGmServerTime(split[1], split[2], split[3],"");
		} else if ("&loadscript".equalsIgnoreCase(split[0])) {
			ManagerPool.scriptManager.load(split[0], msg.getHttpresult());
			
		} else if ("&setparam".equalsIgnoreCase(split[0])){
//			try {
//				String type = split[1];
//				if(type.equals("999")){ Global.HEART_WEB = split[2]; } //服务器心跳地址字符串
//				else if(type.equals("998")){ Global.HEART_PARA = split[2]; } //服务器心跳参数字符串
//				else if(type.equals("1000")){ ChatManager.AUTOPROHIBIT_LEVEL = Integer.parseInt(split[2]); }//触发自动禁言最高等级
//				else if(type.equals("1001")){ ChatManager.AUTOPROHIBIT_LENGTH= Integer.parseInt(split[2]); }//触发自动禁言聊天内容长度
//				else if(type.equals("1002")){ ChatManager.AUTOPROHIBIT_TIME = Integer.parseInt(split[2]); }//自动禁言记录时长
//				else if(type.equals("1003")){ ChatManager.AUTOPROHIBIT_PROHIBITTIME = Integer.parseInt(split[2]); }//禁言时长
//				else if(type.equals("1004")){ ChatManager.AUTOPROHIBIT_COUNT = Integer.parseInt(split[2]); }//自动禁言相似重复次数
//				else if(type.equals("1005")){ ChatManager.AUTOPROHIBIT_SEMBLANCE = Integer.parseInt(split[2]); }//自动禁言相似度
//				HttpUtil.wget(msg.getHttpresult()+"&result=1&location=server&tip="+split[1]+"_"+split[2]);
//				return;
//			} catch (Exception e) {
//				log.error(e, e);
//			}
//			try {
//				HttpUtil.wget(msg.getHttpresult()+"&result=0&location=server&tip="+split[1]+"_"+split[2]);
//			} catch (Exception e) {
//				log.error(e, e);
//			}
		} else if("&setheart".equalsIgnoreCase(split[0])){
			GmCommandToGateMessage togatemsg = new GmCommandToGateMessage();
			togatemsg.setCommand(msg.getCommand());
			togatemsg.setPlayerId(0L);
			MessageUtil.send_to_gate(togatemsg);
			try {
				HttpUtil.wget(msg.getHttpresult()+"&result=1&location=server&tip=setheart");
			} catch (Exception e) {
				log.error(e, e);
			}
		} else if("&setcheckparam".equalsIgnoreCase(split[0])){
			WServer.checkconfig = new CheckConfigXmlLoader().load("server-config/check-config.xml");
			Global.CHECK_BETWEEN = WServer.checkconfig.getCheckbetween();
			Global.DISTANCE = WServer.checkconfig.getDistance();
			Global.BASE_SPEED = WServer.checkconfig.getBasespeed();
			try {
				HttpUtil.wget(msg.getHttpresult()+"&result=1&location=server&tip=setcheckparam");
			} catch (Exception e) {
				log.error(e, e);
			}
		} else if("&setserverheart".equalsIgnoreCase(split[0])){
			WServer.serverheartconfig = new ServerHeartConfigXmlLoader().load("server-config/server-heart-config.xml");
			Global.HEART_PARA = WServer.serverheartconfig.getHeart_para();
			Global.HEART_WEB  = WServer.serverheartconfig.getHeart_web();
			log.info("修改服务器心跳地址配置成功 ["+Global.HEART_PARA+"||"+Global.HEART_WEB+"]");
			try {
				HttpUtil.wget(msg.getHttpresult()+"&result=1&location=server&tip=setserverheart");
			} catch (Exception e) {
				log.error(e, e);
			}
		} else if("&setgateheart".equalsIgnoreCase(split[0])){
			GmCommandToGateMessage togate = new GmCommandToGateMessage();
			togate.setCommand(split[0]);
			MessageUtil.send_to_gate(togate);
		} else if ("&loadgrantdata".equalsIgnoreCase(split[0])) {	//重载全服邮件
			SystemgrantManager.getInstance().system_GrantBean_load();
		}
	}
	
	public static void main(String[] args){
//		String source = "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";
//		String source = "H8KLCAAAAAAAAADDhcKZTW/Cm0AQwobDv8OLwp5RwrTCn8KzwrvCvlVVVcOlw5Bew5pbwpUDwrHCqUPCg8KDa3DCqibDsn/Dr8KCwpZ4woPDgThkVj0kEQ7CvDzCmsKdd2Z2w71MwpglwotncsKbP8Ksw4jCosOew63Cs8KELMKLw7TDqcKJLMOIwrLDnFzCrcOTTXZ1wpsuw6/Ct8Ouw6fCqnI3LMOrw6rDqmPCucOZwpQPwp/Di3JVwpHChMKsd8O5w6rDmj3DjWxCw7LDpsKvw5ZaUcOOwpjCtcOGSsOafFpnwpsvw6UqK8Oaw5vCgDLCmsKQwqLCrMOqOsOfZGTDoS4ew7YbwrLDoAlpw57CksKuwrPDpjbDmlzDrcOcw6vDq2xXOcOEw4MhIUwfWX/CpkXDtR5YfcKEwpVacsOKwq0FwqpEH8KWUjnCk8OVwqDDhcOVwoRxw5XDgsO9AiPDuSkqG0TCtcOTwqjDqsOtwqjDn8KWwrt8W187woDCkFTCvSI1RlrDjQFUwo/DlMKyw6HCoMKyaVJACyrChMKow4wowpDCjhZOwpJ1ZMO9L0AVwrPCgloWw4XDt8K0wroPSUXCmMKpw5pQw6rDkhTCpMOsB8KVNk7Cm8KVwqlywobCq8KGUWXCiGrClcKkAsKoEXAhwqrCnEZlaMKoLFh/LsKlwrVCWiUGwqLCqgfCo8KqwqZZORorD1kFZ1pSwrBtWcOowrHCmiFWwphEw6Vzw4I6w6wrHsOEVSplDCjDocOaAMOrw7vDihlrHmtrwqt0w7XDuDPDjcKLw7YRd1Naw5fCu8O8dl9nw67Cph/DjxfCsH/DqB4gwq8KwpZlw5bDlQAuHW3DvXfCm8K1wrXDszEtw7ZZWwgOCcKCNMOvwqTCmwREw5YWL8OaAl1bdsOaBl1aRcOEwoYRw63Cm8Okwq3DqcO+w6nDtz7Dn8K2wonCnsKuwrLCr03Doil/EWZiZwPDt8K2wrt8fcKXVXV7S8O7wr8BPHrDkm5cw7LCucOEw7jClcO9w4nCiibCmW/DpjUfTsOxDB3CkDtmV8K6JcOXXMKfGHpswqrDk8OTwrARHQ1UWGVZM8Otw7k8wqBoOcOWacKbwojDmhbCvxJ5w6lmXcKxHcOdScK/w5TDj8KmcyFrw7MRbQxHw6szwo7DpsOnHcOdLVh/w5bDgXI0wpvDo8Oow6FpwoLCvsKafMKEw6UMLGh+Ok3DgMOgw6QzbWgWwrNFK1AGOMOEMF3Cpx3DgXReWsOTeMOSMcKGFi99w7TCnETDlxbDg8OYwrHDrcOMJhrCtE8FHcOJw446wpZFJBgqw4F5w5oAw7pcFWgDcsOzCMKkw5FbdcKgbcO+U8Ozw5BnwrItw4DDq8OvPhk1wpIiZBvDhCzDiMOcw5UHC3bDhMOHw68qEV5aw4bDmCB4w60owpsPwq8NMXrClMOXw5Yxw7bCkV57wqzCt8OGw540w4FETcO2eMO9ScKFUUDCqcOJKmZNFlbCuMKqeRwtwrDClsOtwqh9wpwtwrDCunTCoMKNPsORB8OaYxM9wobCtsKIw5FMwrzCtsKcaibClx7Du8K+wq3Cl8Kowolew6Izwq1/OsOIwqhCccKJwozCuVsHa8KVUsOHw4Mmw4R5wrPDk1YRwrXCscKnwqJAWkc4CMOww5JRw44vwrzCtsKNOHDCnWslcmLCt8Ouw7FOwr9DwpMoA8KXw4XDmsKswodfwqlyScKVMsOKFcOjPsK2w5vCq8KrIVAxCWoiwrpZasKlBcKnUcOOw546bcKDb8KLTsOaRsKYDMK9w7bDscOsDT8kw4HDoRvDnmTDmGnDs8KRwpjDhMOeP8KZw7N2w67ClsOsw6TDsMONw7nDucO9PcOvw7APwoMLwpEDJSEAAA==";
//		try {
//			String decode = CodedUtil.decodeBase64(source);
//			System.out.println(decode);
//			String unzip = ZipUtil.uncompress(decode); 
//			String dest = CodedUtil.decodeBase64(unzip);
//			System.out.println(unzip);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
