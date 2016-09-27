package com.game.backpack.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 游戏币变化
	 */
	public class ResMoneyChangeMessage extends Message {
	
		//游戏币数量
		private var _money: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//游戏币数量
			writeInt(_money);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//游戏币数量
			_money = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104105;
		}
		
		/**
		 * get 游戏币数量
		 * @return 
		 */
		public function get money(): int{
			return _money;
		}
		
		/**
		 * set 游戏币数量
		 */
		public function set money(value: int): void{
			this._money = value;
		}
		
	}
}