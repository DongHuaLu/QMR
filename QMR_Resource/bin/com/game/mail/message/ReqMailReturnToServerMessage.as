package com.game.mail.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 退信
	 */
	public class ReqMailReturnToServerMessage extends Message {
	
		//邮件Id
		private var _mailid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//邮件Id
			writeLong(_mailid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//邮件Id
			_mailid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124157;
		}
		
		/**
		 * get 邮件Id
		 * @return 
		 */
		public function get mailid(): long{
			return _mailid;
		}
		
		/**
		 * set 邮件Id
		 */
		public function set mailid(value: long): void{
			this._mailid = value;
		}
		
	}
}