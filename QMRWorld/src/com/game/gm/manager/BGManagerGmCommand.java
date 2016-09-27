package com.game.gm.manager;

import org.apache.log4j.Logger;

import com.game.backend.struct.ServerRequest;
import com.game.backend.util.BGServerUtil;
import com.game.data.reload.ReLoadManager;
import com.game.gm.message.GmCommandToServerMessage;
import com.game.script.manager.ScriptManager;
import com.game.utils.HttpUtil;
import com.game.utils.MessageUtil;

/**
 * 后台GM命令
 * @author 赵聪慧
 * @2012-10-30 上午11:03:53
 */
public class BGManagerGmCommand {
	private static Logger log = Logger.getLogger(BGManagerGmCommand.class);
	private static BGManagerGmCommand instance=new BGManagerGmCommand();
	public static BGManagerGmCommand getInstance(){
		return instance;
	}

	public String command(ServerRequest req) {
		String result = "-1";
		String[] split =req.getCommand().split(" ");
		if ("&reload".equalsIgnoreCase(split[0])) {
			//ReLoadManager.getInstance().reLoadBybg(split[1], BGServerUtil.buildResultHttp(req)); //世界服与游戏服加载分离
			GmCommandToServerMessage msg=new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(msg);
			result = "1";
		} else if("&worldreload".equalsIgnoreCase(split[0])){
			ReLoadManager.getInstance().reLoadBybg(split[1], BGServerUtil.buildResultHttp(req));
			result = "1";
		} else if ("&script".equalsIgnoreCase(split[0])) {
			GmCommandToServerMessage msg=new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(msg);
			result="1";
		} else if ("&loadscript".equalsIgnoreCase(split[0])) {
			GmCommandToServerMessage msg=new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(msg);
			result="1";			
		} else if ("&worldscript".equalsIgnoreCase(split[0])) {
			return ScriptManager.getInstance().reloadbybg(Integer.parseInt(split[1]),BGServerUtil.buildResultHttp(req));
		} else if ("&addgm".equalsIgnoreCase(split[0])) {
			GmCommandToServerMessage msg=new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(msg);
			result="1";
		} else if ("&worldloadscript".equalsIgnoreCase(split[0])) {			
			return ScriptManager.getInstance().loadByBg(split[1],BGServerUtil.buildResultHttp(req));
		} else if ("&sd".equalsIgnoreCase(split[0])) {
			//TODO 还要改世界服开服时间
			GmCommandToServerMessage msg=new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(Integer.parseInt(req.getServerid()), msg);
			result="1";
		} else if ("&setparam".equalsIgnoreCase(split[0])){
			GmCommandToServerMessage msg=new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(Integer.parseInt(req.getServerid()), msg);
			result="1";
		} else if("&setheart".equalsIgnoreCase(split[0])){
			GmCommandToServerMessage msg=new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(Integer.parseInt(req.getServerid()), msg);
			result="1";
		} else if("&setcheckparam".equalsIgnoreCase(split[0])){
			GmCommandToServerMessage msg=new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(Integer.parseInt(req.getServerid()), msg);
			result="1";
		} else if("&setserverheart".equalsIgnoreCase(split[0])){
			GmCommandToServerMessage msg = new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(Integer.parseInt(req.getServerid()), msg);
			result="1";
		} else if("&setgateheart".equalsIgnoreCase(split[0])){
			GmCommandToServerMessage msg = new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(Integer.parseInt(req.getServerid()), msg);
			result="1";
		} else if ("&loadgrantdata".equalsIgnoreCase(split[0])) {	//重载全服邮件
			GmCommandToServerMessage msg = new GmCommandToServerMessage();
			msg.setAction(req.getActionid());
			msg.setCommand(req.getCommand());
			msg.setHttpresult(BGServerUtil.buildResultHttp(req));
			MessageUtil.send_to_game(Integer.parseInt(req.getServerid()), msg);
			result="1";
		
		} else {
			try {
				HttpUtil.wget(BGServerUtil.buildResultHttp(req)+"&result=0&location=world&tip=commandnotfound["+split[0]+"]");
				result = "1";
			} catch (Exception e) {
				result = "0";
				log.error(e, e);
			}
		}
		return result;
	}
	

}
