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
	 * 同步删除帮会信息给客户端
	 */
	public class ResGuildDeleteInfoRoundToClientMessage extends Message {
	
		//被通知的玩家
		private var _playerid: long;
		
		//被通知的帮会id
		private var _guildid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//被通知的玩家
			writeLong(_playerid);
			//被通知的帮会id
			writeLong(_guildid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//被通知的玩家
			_playerid = readLong();
			//被通知的帮会id
			_guildid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121392;
		}
		
		/**
		 * get 被通知的玩家
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 被通知的玩家
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 被通知的帮会id
		 * @return 
		 */
		public function get guildid(): long{
			return _guildid;
		}
		
		/**
		 * set 被通知的帮会id
		 */
		public function set guildid(value: long): void{
			this._guildid = value;
		}
		
	}
}