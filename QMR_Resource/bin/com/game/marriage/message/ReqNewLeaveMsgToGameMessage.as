package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 增加新留言
	 */
	public class ReqNewLeaveMsgToGameMessage extends Message {
	
		//留言内容
		private var _content: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//留言内容
			writeString(_content);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//留言内容
			_content = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163205;
		}
		
		/**
		 * get 留言内容
		 * @return 
		 */
		public function get content(): String{
			return _content;
		}
		
		/**
		 * set 留言内容
		 */
		public function set content(value: String): void{
			this._content = value;
		}
		
	}
}