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
	 * 申请加入帮会发给帮会操作人
	 */
	public class ResGuildApplyAddDoingToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//被操作玩家ID（申请人）
		private var _userId: long;
		
		//申请人名字
		private var _applyName: String;
		
		//申请人等级
		private var _applyLevel: int;
		
		//申请人造型信息
		private var _applyModeInfo: com.game.friend.bean.FriendModeInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_btErrorCode);
			//被操作玩家ID（申请人）
			writeLong(_userId);
			//申请人名字
			writeString(_applyName);
			//申请人等级
			writeShort(_applyLevel);
			//申请人造型信息
			writeBean(_applyModeInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_btErrorCode = readByte();
			//被操作玩家ID（申请人）
			_userId = readLong();
			//申请人名字
			_applyName = readString();
			//申请人等级
			_applyLevel = readShort();
			//申请人造型信息
			_applyModeInfo = readBean(com.game.friend.bean.FriendModeInfo) as com.game.friend.bean.FriendModeInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121104;
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
		 * get 被操作玩家ID（申请人）
		 * @return 
		 */
		public function get userId(): long{
			return _userId;
		}
		
		/**
		 * set 被操作玩家ID（申请人）
		 */
		public function set userId(value: long): void{
			this._userId = value;
		}
		
		/**
		 * get 申请人名字
		 * @return 
		 */
		public function get applyName(): String{
			return _applyName;
		}
		
		/**
		 * set 申请人名字
		 */
		public function set applyName(value: String): void{
			this._applyName = value;
		}
		
		/**
		 * get 申请人等级
		 * @return 
		 */
		public function get applyLevel(): int{
			return _applyLevel;
		}
		
		/**
		 * set 申请人等级
		 */
		public function set applyLevel(value: int): void{
			this._applyLevel = value;
		}
		
		/**
		 * get 申请人造型信息
		 * @return 
		 */
		public function get applyModeInfo(): com.game.friend.bean.FriendModeInfo{
			return _applyModeInfo;
		}
		
		/**
		 * set 申请人造型信息
		 */
		public function set applyModeInfo(value: com.game.friend.bean.FriendModeInfo): void{
			this._applyModeInfo = value;
		}
		
	}
}