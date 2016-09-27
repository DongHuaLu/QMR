package com.game.arrow.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 同步弓箭外观
	 */
	public class ResRoundArrowExteriorMessage extends Message {
	
		//玩家id
		private var _playerid: long;
		
		//弓箭等级
		private var _arrowid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家id
			writeLong(_playerid);
			//弓箭等级
			writeInt(_arrowid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家id
			_playerid = readLong();
			//弓箭等级
			_arrowid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 151106;
		}
		
		/**
		 * get 玩家id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家id
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 弓箭等级
		 * @return 
		 */
		public function get arrowid(): int{
			return _arrowid;
		}
		
		/**
		 * set 弓箭等级
		 */
		public function set arrowid(value: int): void{
			this._arrowid = value;
		}
		
	}
}