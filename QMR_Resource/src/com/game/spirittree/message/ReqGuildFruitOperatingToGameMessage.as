package com.game.spirittree.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 行会灵树操作
	 */
	public class ReqGuildFruitOperatingToGameMessage extends Message {
	
		//类型：1浇水，2采摘
		private var _type: int;
		
		//果实ID
		private var _fruitid: long;
		
		//果实主人ID
		private var _hostid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型：1浇水，2采摘
			writeByte(_type);
			//果实ID
			writeLong(_fruitid);
			//果实主人ID
			writeLong(_hostid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型：1浇水，2采摘
			_type = readByte();
			//果实ID
			_fruitid = readLong();
			//果实主人ID
			_hostid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133205;
		}
		
		/**
		 * get 类型：1浇水，2采摘
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：1浇水，2采摘
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 果实ID
		 * @return 
		 */
		public function get fruitid(): long{
			return _fruitid;
		}
		
		/**
		 * set 果实ID
		 */
		public function set fruitid(value: long): void{
			this._fruitid = value;
		}
		
		/**
		 * get 果实主人ID
		 * @return 
		 */
		public function get hostid(): long{
			return _hostid;
		}
		
		/**
		 * set 果实主人ID
		 */
		public function set hostid(value: long): void{
			this._hostid = value;
		}
		
	}
}