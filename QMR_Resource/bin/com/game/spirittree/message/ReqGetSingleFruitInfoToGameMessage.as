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
	 * 请求获取单个灵树果实信息
	 */
	public class ReqGetSingleFruitInfoToGameMessage extends Message {
	
		//类型：0查看，1浇水，2，浇仙露，3采摘
		private var _type: int;
		
		//果实ID
		private var _fruitid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//类型：0查看，1浇水，2，浇仙露，3采摘
			writeByte(_type);
			//果实ID
			writeLong(_fruitid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//类型：0查看，1浇水，2，浇仙露，3采摘
			_type = readByte();
			//果实ID
			_fruitid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133202;
		}
		
		/**
		 * get 类型：0查看，1浇水，2，浇仙露，3采摘
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：0查看，1浇水，2，浇仙露，3采摘
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
		
	}
}