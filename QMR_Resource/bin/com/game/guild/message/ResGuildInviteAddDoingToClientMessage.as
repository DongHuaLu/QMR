package com.game.guild.message{
	import com.game.utils.long;
	import com.game.friend.bean.FriendModeInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 邀请加入帮会发给被操作玩家
	 */
	public class ResGuildInviteAddDoingToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//帮会Id
		private var _guildId: long;
		
		//帮会名字
		private var _guildName: String;
		
		//邀请人名字
		private var _inviteName: String;
		
		//邀请人等级
		private var _inviteLevel: int;
		
		//邀请人造型信息
		private var _inviteModeInfo: com.game.friend.bean.FriendModeInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_btErrorCode);
			//帮会Id
			writeLong(_guildId);
			//帮会名字
			writeString(_guildName);
			//邀请人名字
			writeString(_inviteName);
			//邀请人等级
			writeShort(_inviteLevel);
			//邀请人造型信息
			writeBean(_inviteModeInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_btErrorCode = readByte();
			//帮会Id
			_guildId = readLong();
			//帮会名字
			_guildName = readString();
			//邀请人名字
			_inviteName = readString();
			//邀请人等级
			_inviteLevel = readShort();
			//邀请人造型信息
			_inviteModeInfo = readBean(com.game.friend.bean.FriendModeInfo) as com.game.friend.bean.FriendModeInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121105;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get btErrorCode(): int{
			return _btErrorCode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set btErrorCode(value: int): void{
			this._btErrorCode = value;
		}
		
		/**
		 * get 帮会Id
		 * @return 
		 */
		public function get guildId(): long{
			return _guildId;
		}
		
		/**
		 * set 帮会Id
		 */
		public function set guildId(value: long): void{
			this._guildId = value;
		}
		
		/**
		 * get 帮会名字
		 * @return 
		 */
		public function get guildName(): String{
			return _guildName;
		}
		
		/**
		 * set 帮会名字
		 */
		public function set guildName(value: String): void{
			this._guildName = value;
		}
		
		/**
		 * get 邀请人名字
		 * @return 
		 */
		public function get inviteName(): String{
			return _inviteName;
		}
		
		/**
		 * set 邀请人名字
		 */
		public function set inviteName(value: String): void{
			this._inviteName = value;
		}
		
		/**
		 * get 邀请人等级
		 * @return 
		 */
		public function get inviteLevel(): int{
			return _inviteLevel;
		}
		
		/**
		 * set 邀请人等级
		 */
		public function set inviteLevel(value: int): void{
			this._inviteLevel = value;
		}
		
		/**
		 * get 邀请人造型信息
		 * @return 
		 */
		public function get inviteModeInfo(): com.game.friend.bean.FriendModeInfo{
			return _inviteModeInfo;
		}
		
		/**
		 * set 邀请人造型信息
		 */
		public function set inviteModeInfo(value: com.game.friend.bean.FriendModeInfo): void{
			this._inviteModeInfo = value;
		}
		
	}
}