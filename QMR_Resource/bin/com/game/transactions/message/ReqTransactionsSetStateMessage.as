package com.game.transactions.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 设置交易状态
	 */
	public class ReqTransactionsSetStateMessage extends Message {
	
		//0交易中，1锁定，2确定交易
		private var _state: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0交易中，1锁定，2确定交易
			writeByte(_state);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0交易中，1锁定，2确定交易
			_state = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122209;
		}
		
		/**
		 * get 0交易中，1锁定，2确定交易
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 0交易中，1锁定，2确定交易
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
	}
}