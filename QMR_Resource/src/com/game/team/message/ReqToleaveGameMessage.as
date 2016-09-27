package com.game.team.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家离队
	 */
	public class ReqToleaveGameMessage extends Message {
	
		//要离队的玩家ID
		private var _playerid: long;
		
		//0开除 ，1 自己离开 ，2下线
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//要离队的玩家ID
			writeLong(_playerid);
			//0开除 ，1 自己离开 ，2下线
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//要离队的玩家ID
			_playerid = readLong();
			//0开除 ，1 自己离开 ，2下线
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118206;
		}
		
		/**
		 * get 要离队的玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 要离队的玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 0开除 ，1 自己离开 ，2下线
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0开除 ，1 自己离开 ，2下线
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}