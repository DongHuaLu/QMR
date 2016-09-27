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
	 * 催熟奇异果
	 */
	public class ReqRipeningFruitToGameMessage extends Message {
	
		//果实ID
		private var _fruitid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//果实ID
			writeLong(_fruitid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//果实ID
			_fruitid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133203;
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