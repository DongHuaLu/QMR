package com.game.backpack.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 绑定元宝变化
	 */
	public class ResBindGoldChangeMessage extends Message {
	
		//元宝数量
		private var _bindgold: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//元宝数量
			writeInt(_bindgold);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//元宝数量
			_bindgold = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104111;
		}
		
		/**
		 * get 元宝数量
		 * @return 
		 */
		public function get bindgold(): int{
			return _bindgold;
		}
		
		/**
		 * set 元宝数量
		 */
		public function set bindgold(value: int): void{
			this._bindgold = value;
		}
		
	}
}