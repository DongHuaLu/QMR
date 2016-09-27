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
	 * 退信返回
	 */
	public class ResMailReturnToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//邮件Id
		private var _mailid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_btErrorCode);
			//邮件Id
			writeLong(_mailid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_btErrorCode = readByte();
			//邮件Id
			_mailid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124107;
		}
		
		/**
		 * get 错误代码
		 * @return 
		 */
		public function get btErrorCode(): int{
			return _btErrorCode;
		}
		
		/**
		 * set 错误代码
		 */
		public function set btErrorCode(value: int): void{
			this._btErrorCode = value;
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