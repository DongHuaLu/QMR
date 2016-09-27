package com.game.transactions.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 自动拒绝交易
	 */
	public class ReqAutorefusaldealMessage extends Message {
	
		//0默认不拒绝，1自动拒绝
		private var _state: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0默认不拒绝，1自动拒绝
			writeByte(_state);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0默认不拒绝，1自动拒绝
			_state = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122211;
		}
		
		/**
		 * get 0默认不拒绝，1自动拒绝
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 0默认不拒绝，1自动拒绝
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
	}
}