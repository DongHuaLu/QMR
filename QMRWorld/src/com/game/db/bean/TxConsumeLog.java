package com.game.db.bean;

public class TxConsumeLog {

	private String oid;
	private String username;
	private String userid;
	private String roleid;
	private String serverid;
	private String items;
	private int consume;
	private long times;
	private String date;
	// -1 参数验证不通过 0-初始  1-发到游戏服 2-回调世界服  4-PHP确认
	private int state;
	private int isconfirm; //PHP是否已经反馈
	private String result; //PHP反馈结果
	private String ret; //即时返回值
	private String msg; //即时返回信息
	private String content;
	private String desc;
	private String confirmmsg;
	
	private String callbackurl;
	
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getConfirmmsg() {
		return confirmmsg;
	}
	public void setConfirmmsg(String confirmmsg) {
		this.confirmmsg = confirmmsg;
	}
	public int getIsconfirm() {
		return isconfirm;
	}
	public void setIsconfirm(int isconfirm) {
		this.isconfirm = isconfirm;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCallbackurl() {
		return callbackurl;
	}
	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getServerid() {
		return serverid;
	}
	public void setServerid(String serverid) {
		this.serverid = serverid;
	}
	public long getTimes() {
		return times;
	}
	public void setTimes(long times) {
		this.times = times;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public int getConsume() {
		return consume;
	}
	public void setConsume(int consume) {
		this.consume = consume;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return username+"\t"+userid+"\t"+serverid+"\t"+consume+"\t"+times+"\t"+date+"\t"+content+"\t"+desc+"\t"+ret+"\t"+msg+"\t"+isconfirm;
	}
	
}
