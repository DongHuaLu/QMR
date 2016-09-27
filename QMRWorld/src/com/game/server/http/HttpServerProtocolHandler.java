package com.game.server.http;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.game.backend.manager.BackendManager;
import com.game.backend.struct.ServerRequest;
import com.game.card.manager.CardManager;
import com.game.card.script.ICardParseScript;
import com.game.command.ICommand;
import com.game.db.bean.GameUser;
import com.game.db.dao.UserDao;
import com.game.db.dao.WorldDao;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.manager.ManagerPool;
import com.game.player.message.ResChangePlayerUserToClientMessage;
import com.game.player.message.ResPlayerNameInfoToGameMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerWorldInfo;
import com.game.prompt.structs.Notifys;
import com.game.recharge.RechargeEntry;
import com.game.recharge.RechargeManager;
import com.game.script.manager.ScriptManager;
import com.game.script.structs.ScriptEnum;
import com.game.server.WorldServer;
import com.game.server.log.WorldRegLog;
import com.game.utils.MessageUtil;

/**
 * @author heyang E-mail: szy_heyang@163.com
 *
 * @version 1.0.0
 *
 * @since 2011-5-1
 *
 * Mina SSL服务器管理
 */
public class HttpServerProtocolHandler extends IoHandlerAdapter {

	protected static Logger log = Logger.getLogger(HttpServerProtocolHandler.class);

	public HttpServerProtocolHandler() {
		//log.setLevel(Level.ERROR);
	}

	public void sessionCreated(IoSession iosession)
		throws Exception {
		//log.debug("连接创建"+iosession.getRemoteAddress());
	}

	public void sessionOpened(IoSession iosession)
		throws Exception {
		log.debug(iosession + " http create!");
		for (int i = 0; i < WorldServer.getHttpConfig().getAllows().size(); i++) {
			//log.debug(WorldServer.getHttpConfig().getAllows().get(i));
		}
	}

	public void sessionClosed(IoSession iosession)
		throws Exception {
		//log.debug("http连接关闭");
	}

	public void sessionIdle(IoSession iosession, IdleStatus idlestatus)
		throws Exception {
	}

	public void exceptionCaught(IoSession iosession, Throwable cause)
		throws Exception {
		log.error("http服务器异常" + cause.getMessage());

	}
	//玩家角色数据接口
	private UserDao userDao = new UserDao();

	public UserDao getUserDao() {
		return userDao;
	}
	//玩家角色数据接口
	private WorldDao worldDao = new WorldDao();

	public WorldDao getWorldDao() {
		return worldDao;
	}

//	
//	0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效,   100：帐号信息不存在   
//msgid = 1修改帐号，	2充值  ,   3后台 ， 4 服务器监控， 5 手机验证
//{svrtmpplayername=laodao, idh=0, msgid=1, login_account=laodaotest, zoneid=1, errormsg=6, svrtmpaccount=laodaoeasda, password=123456, agent=37wan, idl=0}
//{msgid=3, actionid=1, querytype=1, querystring=n }	
	public void messageReceived(final IoSession iosession, final Object obj) throws Exception {
		WorldServer.getInstance().addCommand(new ICommand() {

			@Override
			public void action() {
				try {
					IoBuffer buff = (IoBuffer) obj;
					buff.flip();
					//读取传过来的安全子陵
					byte[] bytes = buff.array();

//			    	Charset charset = Charset.forName("UTF-8");  
//			    	ByteBuffer byteBuffer = ByteBuffer.allocateDirect(186140);
//			    	byteBuffer.put(bytes);
//			    	byteBuffer.flip();  
//		          CharBuffer charBuffer = charset.decode(byteBuffer);  

					String str = new String(bytes);
					log.error(iosession + " http send:" + str + "!");
					@SuppressWarnings("unchecked")
					HashMap<String, String> map = (HashMap<String, String>) JSONserializable.toObject(str, HashMap.class);
					if (map == null) {
						log.error(str + "解析出错");
						return;
					}





					int msgid = Integer.valueOf(map.get("msgid"));	//类型
					if (msgid == 1) {	//改帐号
						IoBuffer out = IoBuffer.allocate(4 * 10);
						String newusername = (String) map.get("login_account");	//新的帐号
						String oldusername = (String) map.get("svrtmpaccount");	//原来的临时帐号
						int server = Integer.valueOf(map.get("zoneid"));
						int errormsgid = Integer.valueOf(map.get("errormsg"));
//					     long  idl = Long.valueOf(map.get("idl"));
//					     long  idh = Long.valueOf(map.get("idh"));
//					     long lid2=((idh<<32) | idl);

//					    Player player = ManagerPool.playerManager.getPlayer(lid2);


						//		比较安全指令是否正确
						//        if(ssl != null && security_ssl.equals(ssl))
						//        {
						//        	//返回安全验证通过指令
						//            bytes = allow_ssl.getBytes("UTF-8");
						//            IoBuffer out = IoBuffer.allocate(bytes.length);
						//            out.put(bytes);
						//            out.flip();
						//            iosession.write(out);
						//        }else{
						//        	iosession.close(true);
						//        }

						GameUser gameUser = userDao.selectGameUser(oldusername, server);	//通过用户名取得用户数据

						if (gameUser != null) {
							List<Player> players = ManagerPool.playerManager.getPlayersByUser(String.valueOf(gameUser.getUserid()));
							if (errormsgid != 0) {
								if (errormsgid == 1) {//用户已经存在
									sayToPlayers(players, "此账号已存在,您输入的密码错误，请回忆正确的密码，或者注册新账号", errormsgid);
								} else if (errormsgid == 2) {//注册错误
									sayToPlayers(players, "注册错误。", errormsgid);
								} else if (errormsgid == 3) {//用户名不符合规定
									sayToPlayers(players, "用户名不符合规定。", errormsgid);
								} else if (errormsgid == 4) {//密码不符合规定
									sayToPlayers(players, "密码不符合规定。", errormsgid);
								} else if (errormsgid == 5) {//
									sayToPlayers(players, "未知错误。", errormsgid);
								} else if (errormsgid == 6) {//加密验证失效
									sayToPlayers(players, "加密验证失效。", errormsgid);
								} else {
									sayToPlayers(players, "未知的错误：" + errormsgid, errormsgid);
								}
								out.put((errormsgid + "").getBytes("UTF-8"));
								out.flip();
								iosession.write(out);
								return;
							}

							int num = userDao.selectByName(newusername, server);
							if (num > 0) {
								log.error(newusername + "账户名已存在");
								sayToPlayers(players, "账户名已存在。", 1);
								errormsgid = 1;
								return;
							}

							List<PlayerWorldInfo> playerinfos = worldDao.selectById(String.valueOf(gameUser.getUserid()));
							boolean is = false;
							for (PlayerWorldInfo playerWorldInfo : playerinfos) {
								if (playerWorldInfo.getChangaccount() > 0) {
									is = true;
								}
							}

							if (is) {
								gameUser.setUsername(newusername);	//修改username
								if (userDao.updateGameUser(gameUser) == 1) {
									ResPlayerNameInfoToGameMessage msg = new ResPlayerNameInfoToGameMessage();
									for (PlayerWorldInfo playerWorldInfo : playerinfos) {
										//修改内存里的数据
										playerWorldInfo.setChangaccount(0);
										PlayerWorldInfo worinfo = ManagerPool.playerManager.getPlayerWorldInfo(playerWorldInfo.getId());
										if (worinfo != null) {
											worinfo.setChangaccount(0);
										}

										msg.setPlayerId(playerWorldInfo.getId());
										msg.setChangeName((byte) playerWorldInfo.getChangeName());
										msg.setChangeUser((byte) playerWorldInfo.getChangaccount());
										MessageUtil.send_to_game(msg);	//通知游戏服务器
									}
									worldDao.updatechangaccount(String.valueOf(gameUser.getUserid()));//修改数据库标记
									log.error("修改账户名成功");
									sayToPlayers(players, "修改账户名成功", 0, newusername);
									errormsgid = 0;

									WorldRegLog regLog = new WorldRegLog();
									regLog.setUserid(gameUser.getUserid());
									regLog.setUsername(gameUser.getUsername());
									regLog.setType(0);
									regLog.setRevisetime(System.currentTimeMillis());	//最后注册时间日志
									regLog.setCreateserver(server);
									LogService.getInstance().execute(regLog);
								} else {
									log.error("修改账户名失败");
									sayToPlayers(players, "修改账户名失败", 2);
									errormsgid = 2;
								}
							} else {
								log.error("修改账户名失败");
								sayToPlayers(players, "修改账户名失败", 1);
								errormsgid = 1;
							}
						} else {
							log.error("原帐号找不到信息：" + str);
							errormsgid = 100;
						}
						out.put((errormsgid + "").getBytes("UTF-8"));
						out.flip();
						iosession.write(out);
					} else if (msgid == 2) {	//充值
						//充值接口
						RechargeEntry entry = new RechargeEntry(map, str);
						int reacharge = RechargeManager.getInstance().reacharge(entry);
						log.info(reacharge);
						IoBuffer out = IoBuffer.allocate(4 * 10);
						out.put((reacharge + "").getBytes("UTF-8"));
						out.flip();
						iosession.write(out);
					} else if (msgid == 3) {
						//后台接口  //{msgid=3, serverid=1, actionid=1, roleid=1 }
						ServerRequest sr = (ServerRequest) JSONserializable.toObject(str, ServerRequest.class);

						String result = BackendManager.getInstance().processHttpRequest(sr, str);

						byte[] r = result.getBytes("UTF-8");
						IoBuffer out = IoBuffer.allocate(r.length);
						out.put(r);
						out.flip();
						iosession.write(out);
					} else if (msgid == 4) { //服务器监控信息
						String actionid = map.get("actionid");
						if (!StringUtils.isBlank(actionid)) {
							if ("2".equals(actionid)) {
								String reply = "I'm alive";
								byte[] b = reply.getBytes("UTF-8");
								IoBuffer out = IoBuffer.allocate(b.length);
								out.put(b);
								out.flip();
								iosession.write(out);
							}
						}
					} else if (msgid == 5) {//手机验证相关消息
						CardManager.getInstance().processHttpRequest(iosession, map);
					} else if (msgid == 6) {
//						int result = GmChatManager.getInstance().receivedMsg(iosession,map);
//						IoBuffer out = IoBuffer.allocate(4 * 10);
//						out.put((result + "").getBytes("UTF-8"));
//						out.flip();
//						iosession.write(out);
					} else if (msgid == 7) {//HTTP验证服务器直接给东西
						ICardParseScript script = (ICardParseScript) ScriptManager.getInstance().getScript(ScriptEnum.CARDPARSE);
						if (script != null) {
							try {
								script.httpCardParse(iosession, map);
							} catch (Exception e) {
								log.error(e, e);
							}
						} else {
							log.error("没有CDKEY解析脚本");
						}
					} else {
						log.error("HTTP消息错误，msgid类型未知：" + msgid);
					}
				} catch (Exception e) {
					log.error(e, e);
				} finally {
					iosession.close(false);
				}
			}

			@Override
			public Object clone() throws CloneNotSupportedException {
				// TODO Auto-generated method stub
				return super.clone();
			}
		});



	}

	public void sayToPlayers(List<Player> players, String str, int errid) {
		sayToPlayers(players, str, errid, "");
	}

	/**
	 * 发送给当前帐号下的角色
	 *
	 * @param players
	 * @param str
	 */
	public void sayToPlayers(List<Player> players, String str, int errid, String newid) {
		if (players != null) {
			ResChangePlayerUserToClientMessage msg = new ResChangePlayerUserToClientMessage();
			for (Player player : players) {
				MessageUtil.notify_player(player, Notifys.NORMAL, str);
				msg.setPlayerId(player.getId());
				msg.setResult((byte) errid);
				msg.setNewname(newid);
				MessageUtil.tell_player_message(player, msg);
			}
		}
	}

	public void messageSent(IoSession iosession, Object obj)
		throws Exception {
		if (!iosession.isClosing()) {
			iosession.close(true);
		}
	}
}
