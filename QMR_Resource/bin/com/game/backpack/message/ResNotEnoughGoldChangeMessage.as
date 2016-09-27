package com.game.backpack.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 元宝不足
	 */
	public class ResNotEnoughGoldChangeMessage extends Message {
	
		//缺少元宝数量
		private var _gold: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//缺少元宝数量
			writeInt(_gold);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//缺少元宝数量
			_gold = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104112;
		}
		
		/**
		 * get 缺少元宝数量
		 * @return 
		 */
		public function get gold(): int{
			return _gold;
		}
		
		/**
		 * set 缺少元宝数量
		 */
		public function set gold(value: int): void{
			this._gold = value;
		}
		
	}
}