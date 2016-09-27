package com.game.mail.structs;

import com.game.backpack.structs.Item;
import com.game.object.GameObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨鸿岚 保存邮件具体信息
 */
public class MailDetailInfoData extends GameObject implements Cloneable {

	private static final long serialVersionUID = 89185523243847163L;
	//保存类型
	private transient int saveType;
	//邮件Id
	private long mailid;
	//发送者Id
	private long senderid;
	//接受者Id
	private long receiverid;
	//发件人名字
	private String szSenderName;
	//接收人名字
	private String szReceiverName;
	//邮件标题
	private String szTitle;
	//邮件内容
	private String szNotice;
	//是否已读取
	private byte btRead;
	//金币类型
	private byte btGoldType;
	//金币数量
	private int ngold;
	//是否有附件
	private byte btAccessory;
	//发送时间
	private int nsendTime;
	//是否系统邮件
	private byte btSystem;
	//是否是回信
	private byte btReturn;
	//邮件物品列表
	private List<Item> itemdata = new ArrayList<Item>();

	public int getSaveType() {
		return saveType;
	}

	public void setSaveType(int saveType) {
		this.saveType = saveType;
	}

	/**
	 * get 邮件Id
	 *
	 * @return
	 */
	public long getMailid() {
		return mailid;
	}

	/**
	 * set 邮件Id
	 */
	public void setMailid(long mailid) {
		this.mailid = mailid;
	}

	/**
	 * get 发送者Id
	 *
	 * @return
	 */
	public long getSenderid() {
		return senderid;
	}

	/**
	 * set 发送者Id
	 */
	public void setSenderid(long senderid) {
		this.senderid = senderid;
	}

	/**
	 * get 接受者Id
	 *
	 * @return
	 */
	public long getReceiverid() {
		return receiverid;
	}

	/**
	 * set 接受者Id
	 */
	public void setReceiverid(long receiverid) {
		this.receiverid = receiverid;
	}

	/**
	 * get 发件人名字
	 *
	 * @return
	 */
	public String getSzSenderName() {
		return szSenderName;
	}

	/**
	 * set 发件人名字
	 */
	public void setSzSenderName(String szSenderName) {
		this.szSenderName = szSenderName;
	}

	/**
	 * get 接收人名字
	 *
	 * @return
	 */
	public String getSzReceiverName() {
		return szReceiverName;
	}

	/**
	 * set 接收人名字
	 */
	public void setSzReceiverName(String szReceiverName) {
		this.szReceiverName = szReceiverName;
	}

	/**
	 * get 邮件标题
	 *
	 * @return
	 */
	public String getSzTitle() {
		return szTitle;
	}

	/**
	 * set 邮件标题
	 */
	public void setSzTitle(String szTitle) {
		this.szTitle = szTitle;
	}

	/**
	 * get 邮件内容
	 *
	 * @return
	 */
	public String getSzNotice() {
		return szNotice;
	}

	/**
	 * set 邮件内容
	 */
	public void setSzNotice(String szNotice) {
		this.szNotice = szNotice;
	}

	/**
	 * get 是否已读取
	 *
	 * @return
	 */
	public byte getBtRead() {
		return btRead;
	}

	/**
	 * set 是否已读取
	 */
	public void setBtRead(byte btRead) {
		this.btRead = btRead;
	}

	/**
	 * get 金币类型
	 *
	 * @return
	 */
	public byte getBtGoldType() {
		return btGoldType;
	}

	/**
	 * set 金币类型
	 */
	public void setBtGoldType(byte btGoldType) {
		this.btGoldType = btGoldType;
	}

	/**
	 * get 金币数量
	 *
	 * @return
	 */
	public int getNgold() {
		return ngold;
	}

	/**
	 * set 金币数量
	 */
	public void setNgold(int ngold) {
		this.ngold = ngold;
	}

	/**
	 * get 是否有附件
	 *
	 * @return
	 */
	public byte getBtAccessory() {
		return btAccessory;
	}

	/**
	 * set 是否有附件
	 */
	public void setBtAccessory(byte btAccessory) {
		this.btAccessory = btAccessory;
	}

	/**
	 * get 发送时间
	 *
	 * @return
	 */
	public int getNsendTime() {
		return nsendTime;
	}

	/**
	 * set 发送时间
	 */
	public void setNsendTime(int nsendTime) {
		this.nsendTime = nsendTime;
	}

	/**
	 * get 是否系统邮件
	 *
	 * @return
	 */
	public byte getBtSystem() {
		return btSystem;
	}

	/**
	 * set 是否系统邮件
	 */
	public void setBtSystem(byte btSystem) {
		this.btSystem = btSystem;
	}

	/**
	 * get 是否是回信
	 *
	 * @return
	 */
	public byte getBtReturn() {
		return btReturn;
	}

	/**
	 * set 是否是回信
	 */
	public void setBtReturn(byte btReturn) {
		this.btReturn = btReturn;
	}

	/**
	 * get 邮件物品列表
	 *
	 * @return
	 */
	public List<Item> getItemdata() {
		return itemdata;
	}

	/**
	 * set 邮件物品列表
	 */
	public void setItemdata(List<Item> itemdata) {
		this.itemdata = itemdata;
	}

	public void calbtAccessory() {
		if (getNgold() != 0 || !getItemdata().isEmpty()) {
			setBtAccessory((byte) 1);
		} else {
			setBtAccessory((byte) 0);
		}
	}

	@Override
	public MailDetailInfoData clone() throws CloneNotSupportedException {
		return (MailDetailInfoData) super.clone();
	}
}
