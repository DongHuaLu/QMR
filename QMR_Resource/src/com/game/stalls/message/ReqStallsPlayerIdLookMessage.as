package com.game.stalls.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 点击某个摊位，进行看摊
	 */
	public class ReqStallsPlayerIdLookMessage extends Message {
	
		//摆摊的玩家ID
		private var _playerid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//摆摊的玩家ID
			writeLong(_playerid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//摆摊的玩家ID
			_playerid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123202;
		}
		
		/**
		 * get 摆摊的玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 摆摊的玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
	}
}