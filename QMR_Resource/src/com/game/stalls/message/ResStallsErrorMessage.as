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
	 * 错误信息
	 */
	public class ResStallsErrorMessage extends Message {
	
		//玩家ID，或者摊位号
		private var _playerid: long;
		
		//错误类型，1摊位不存在
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家ID，或者摊位号
			writeLong(_playerid);
			//错误类型，1摊位不存在
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家ID，或者摊位号
			_playerid = readLong();
			//错误类型，1摊位不存在
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123111;
		}
		
		/**
		 * get 玩家ID，或者摊位号
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家ID，或者摊位号
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 错误类型，1摊位不存在
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 错误类型，1摊位不存在
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}