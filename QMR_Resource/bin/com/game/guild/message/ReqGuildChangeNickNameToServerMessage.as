package com.game.guild.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 修改昵称
	 */
	public class ReqGuildChangeNickNameToServerMessage extends Message {
	
		//帮会Id
		private var _guildId: long;
		
		//被操作玩家ID
		private var _userId: long;
		
		//昵称
		private var _nickName: String;
		
		//分组名
		private var _groupName: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会Id
			writeLong(_guildId);
			//被操作玩家ID
			writeLong(_userId);
			//昵称
			writeString(_nickName);
			//分组名
			writeString(_groupName);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会Id
			_guildId = readLong();
			//被操作玩家ID
			_userId = readLong();
			//昵称
			_nickName = readString();
			//分组名
			_groupName = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121209;
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
		 * get 被操作玩家ID
		 * @return 
		 */
		public function get userId(): long{
			return _userId;
		}
		
		/**
		 * set 被操作玩家ID
		 */
		public function set userId(value: long): void{
			this._userId = value;
		}
		
		/**
		 * get 昵称
		 * @return 
		 */
		public function get nickName(): String{
			return _nickName;
		}
		
		/**
		 * set 昵称
		 */
		public function set nickName(value: String): void{
			this._nickName = value;
		}
		
		/**
		 * get 分组名
		 * @return 
		 */
		public function get groupName(): String{
			return _groupName;
		}
		
		/**
		 * set 分组名
		 */
		public function set groupName(value: String): void{
			this._groupName = value;
		}
		
	}
}