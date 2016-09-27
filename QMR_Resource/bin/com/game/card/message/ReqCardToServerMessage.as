package com.game.card.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求签证CDKEY
	 */
	public class ReqCardToServerMessage extends Message {
	
		//CDKEY
		private var _card: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//CDKEY
			writeString(_card);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//CDKEY
			_card = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 137101;
		}
		
		/**
		 * get CDKEY
		 * @return 
		 */
		public function get card(): String{
			return _card;
		}
		
		/**
		 * set CDKEY
		 */
		public function set card(value: String): void{
			this._card = value;
		}
		
	}
}