package com.game.transactions.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 金币改变
	 */
	public class ReqTransactionsChangeGoldMessage extends Message {
	
		//金币数量
		private var _gold: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//金币数量
			writeInt(_gold);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//金币数量
			_gold = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122207;
		}
		
		/**
		 * get 金币数量
		 * @return 
		 */
		public function get gold(): int{
			return _gold;
		}
		
		/**
		 * set 金币数量
		 */
		public function set gold(value: int): void{
			this._gold = value;
		}
		
	}
}