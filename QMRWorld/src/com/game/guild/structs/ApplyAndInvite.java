package com.game.guild.structs;

import com.game.object.GameObject;

/**
 *
 * @author 杨洪岚 帮会申请邀请类
 */
public class ApplyAndInvite extends GameObject {
	
	private static final long serialVersionUID = 8144655499663344872L;
	//入帮及外交关系邀请类型
	public static byte Apply_Type = 1;		//申请类型
	public static byte Invite_Type = 2;		//邀请类型
	public static byte Diplomatic_Friend_Type = 3;	//外交友帮类型
	public static byte Diplomatic_Enemy_Type = 4;	//外交敌帮类型

	private long guildid;		//公会id
	private byte type;		//类型
	private long srcid;		//操作id
	private long destid;		//被操作id

	public long getDestid() {
		return destid;
	}

	public void setDestid(long destid) {
		this.destid = destid;
	}

	public long getGuildid() {
		return guildid;
	}

	public void setGuildid(long guildid) {
		this.guildid = guildid;
	}

	public long getSrcid() {
		return srcid;
	}

	public void setSrcid(long srcid) {
		this.srcid = srcid;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}
}