package com.game.gem.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 点击宝石激活或者升级
	 */
	public class ReqGemActivationORUpMessage extends Message {
	
		//装备部位
		private var _pos: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//装备部位
			writeByte(_pos);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//装备部位
			_pos = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 132203;
		}
		
		/**
		 * get 装备部位
		 * @return 
		 */
		public function get pos(): int{
			return _pos;
		}
		
		/**
		 * set 装备部位
		 */
		public function set pos(value: int): void{
			this._pos = value;
		}
		
	}
}