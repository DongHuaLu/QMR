package com.game.mail.message{
	import com.game.mail.bean.MailDetailInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回读取邮件的结果
	 */
	public class ResMailQueryDetailToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//读取邮件的内容
		private var _mailDetail: MailDetailInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_btErrorCode);
			//读取邮件的内容
			writeBean(_mailDetail);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误代码
			_btErrorCode = readByte();
			//读取邮件的内容
			_mailDetail = readBean(MailDetailInfo) as MailDetailInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124102;
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
		 * get 读取邮件的内容
		 * @return 
		 */
		public function get mailDetail(): MailDetailInfo{
			return _mailDetail;
		}
		
		/**
		 * set 读取邮件的内容
		 */
		public function set mailDetail(value: MailDetailInfo): void{
			this._mailDetail = value;
		}
		
	}
}