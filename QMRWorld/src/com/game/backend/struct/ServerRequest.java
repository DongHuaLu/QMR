package com.game.backend.struct;

import java.util.List;

public class ServerRequest {

	private String username;
	private String msgid;
	private String actionid;
	private String serverid;
	private String roleid;
	private String zone;
	private String rolename;
	private String time;  //禁言时间  单位:分钟
	private List<String> roleids;
	private String content;
	private long userid;
	private String title;
	private String num; //数量
	private String oid; //订单号
	private int consume;
	private int totalconsume;
	
	private String items;
	private String sitem;
	
	private int gmlevel;
	private int isDeleted;

	private String command;//gm命令
	
//	http://192.168.1.55:8080/QMRBackend/c/callback.do?c=gmcommand&result=aaa&tip=testtip
//	执行回执
	private String resultHttp;//192.168.1.55
	private String resultPort;//8080
	private String resultproject;//QMRBackend
	private String resultpath;//c
	private String resultaction;//callback.do?c=gmcommand
	
	public int getConsume() {
		return consume;
	}
	public void setConsume(int consume) {
		this.consume = consume;
	}
	public int getTotalconsume() {
		return totalconsume;
	}
	public void setTotalconsume(int totalconsume) {
		this.totalconsume = totalconsume;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public int getGmlevel() {
		return gmlevel;
	}
	public void setGmlevel(int gmlevel) {
		this.gmlevel = gmlevel;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	private String type;
	
	public String getItems() {
		return items;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getSitem() {
		return sitem;
	}
	public void setSitem(String sitem) {
		this.sitem = sitem;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public List<String> getRoleids() {
		return roleids;
	}
	public void setRoleids(List<String> roleids) {
		this.roleids = roleids;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getActionid() {
		return actionid;
	}
	public void setActionid(String actionid) {
		this.actionid = actionid;
	}
	public String getServerid() {
		return serverid;
	}
	public void setServerid(String serverid) {
		this.serverid = serverid;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getResultHttp() {
		return resultHttp;
	}
	public void setResultHttp(String resultHttp) {
		this.resultHttp = resultHttp;
	}
	public String getResultPort() {
		return resultPort;
	}
	public void setResultPort(String resultPort) {
		this.resultPort = resultPort;
	}
	public String getResultproject() {
		return resultproject;
	}
	public void setResultproject(String resultproject) {
		this.resultproject = resultproject;
	}
	public String getResultpath() {
		return resultpath;
	}
	public void setResultpath(String resultpath) {
		this.resultpath = resultpath;
	}
	public String getResultaction() {
		return resultaction;
	}
	public void setResultaction(String resultaction) {
		this.resultaction = resultaction;
	}
	
}
