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
	 * 修改职权
	 */
	public class ReqGuildChangePowerLevelToServerMessage extends Message {
	
		//帮会Id
		private var _guildId: long;
		
		//被操作玩家ID
		private var _userId: long;
		
		//新职权
		private var _newPowerLevel: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会Id
			writeLong(_guildId);
			//被操作玩家ID
			writeLong(_userId);
			//新职权
			writeByte(_newPowerLevel);
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
			//新职权
			_newPowerLevel = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121210;
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
		 * get 新职权
		 * @return 
		 */
		public function get newPowerLevel(): int{
			return _newPowerLevel;
		}
		
		/**
		 * set 新职权
		 */
		public function set newPowerLevel(value: int): void{
			this._newPowerLevel = value;
		}
		
	}
}