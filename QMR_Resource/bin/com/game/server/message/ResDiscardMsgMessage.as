package com.game.server.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 被丢弃的消息
	 */
	public class ResDiscardMsgMessage extends Message {
	
		//消息内容
		private var _msgcont: String;
		
		//消息ID
		private var _msgid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//消息内容
			writeString(_msgcont);
			//消息ID
			writeInt(_msgid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//消息内容
			_msgcont = readString();
			//消息ID
			_msgid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 300102;
		}
		
		/**
		 * get 消息内容
		 * @return 
		 */
		public function get msgcont(): String{
			return _msgcont;
		}
		
		/**
		 * set 消息内容
		 */
		public function set msgcont(value: String): void{
			this._msgcont = value;
		}
		
		/**
		 * get 消息ID
		 * @return 
		 */
		public function get msgid(): int{
			return _msgid;
		}
		
		/**
		 * set 消息ID
		 */
		public function set msgid(value: int): void{
			this._msgid = value;
		}
		
	}
}