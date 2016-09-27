package com.game.transactions.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 元宝改变
	 */
	public class ReqTransactionsChangeYuanbaoMessage extends Message {
	
		//元宝数量
		private var _yuanbao: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//元宝数量
			writeInt(_yuanbao);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//元宝数量
			_yuanbao = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122208;
		}
		
		/**
		 * get 元宝数量
		 * @return 
		 */
		public function get yuanbao(): int{
			return _yuanbao;
		}
		
		/**
		 * set 元宝数量
		 */
		public function set yuanbao(value: int): void{
			this._yuanbao = value;
		}
		
	}
}