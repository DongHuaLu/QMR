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
	 * 广播玩家帮会信息
	 */
	public class ResPlayerGuildToClientMessage extends Message {
	
		//玩家Id
		private var _playerId: long;
		
		//帮会Id
		private var _guildId: long;
		
		//队伍Id
		private var _teamId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家Id
			writeLong(_playerId);
			//帮会Id
			writeLong(_guildId);
			//队伍Id
			writeLong(_teamId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家Id
			_playerId = readLong();
			//帮会Id
			_guildId = readLong();
			//队伍Id
			_teamId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121123;
		}
		
		/**
		 * get 玩家Id
		 * @return 
		 */
		public function get playerId(): long{
			return _playerId;
		}
		
		/**
		 * set 玩家Id
		 */
		public function set playerId(value: long): void{
			this._playerId = value;
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
		 * get 队伍Id
		 * @return 
		 */
		public function get teamId(): long{
			return _teamId;
		}
		
		/**
		 * set 队伍Id
		 */
		public function set teamId(value: long): void{
			this._teamId = value;
		}
		
	}
}