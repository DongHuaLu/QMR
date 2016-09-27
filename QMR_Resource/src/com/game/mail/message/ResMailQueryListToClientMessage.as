package com.game.mail.message{
	import com.game.mail.bean.MailSummaryInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 获得邮件列表返回
	 */
	public class ResMailQueryListToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//邮件列表
		private var _mailSummaryList: Vector.<MailSummaryInfo> = new Vector.<MailSummaryInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//错误代码
			writeByte(_btErrorCode);
			//邮件列表
			writeShort(_mailSummaryList.length);
			for (i = 0; i < _mailSummaryList.length; i++) {
				writeBean(_mailSummaryList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//错误代码
			_btErrorCode = readByte();
			//邮件列表
			var mailSummaryList_length : int = readShort();
			for (i = 0; i < mailSummaryList_length; i++) {
				_mailSummaryList[i] = readBean(MailSummaryInfo) as MailSummaryInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124101;
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
		 * get 邮件列表
		 * @return 
		 */
		public function get mailSummaryList(): Vector.<MailSummaryInfo>{
			return _mailSummaryList;
		}
		
		/**
		 * set 邮件列表
		 */
		public function set mailSummaryList(value: Vector.<MailSummaryInfo>): void{
			this._mailSummaryList = value;
		}
		
	}
}