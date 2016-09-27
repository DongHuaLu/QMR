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
	 * 删除帮会成员
	 */
	public class ReqGuildDeleteMemberToServerMessage extends Message {
	
		//帮会Id
		private var _guildId: long;
		
		//被操作玩家ID
		private var _userId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会Id
			writeLong(_guildId);
			//被操作玩家ID
			writeLong(_userId);
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
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121211;
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
		
	}
}