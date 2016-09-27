package com.game.mail.bean{
	import com.game.utils.long;
	import com.game.mail.bean.MailSummaryItem;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 邮件摘要
	 */
	public class MailSummaryInfo extends Bean {
	
		//邮件Id
		private var _mailid: long;
		
		//发件人
		private var _szSenderName: String;
		
		//邮件标题
		private var _szTitle: String;
		
		//是否已读取
		private var _btRead: int;
		
		//是否有附件
		private var _btAccessory: int;
		
		//发送时间
		private var _nSendTime: int;
		
		//是否系统邮件
		private var _btSystem: int;
		
		//邮件摘要物品列表
		private var _summaryitemlist: Vector.<MailSummaryItem> = new Vector.<MailSummaryItem>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//邮件Id
			writeLong(_mailid);
			//发件人
			writeString(_szSenderName);
			//邮件标题
			writeString(_szTitle);
			//是否已读取
			writeByte(_btRead);
			//是否有附件
			writeByte(_btAccessory);
			//发送时间
			writeInt(_nSendTime);
			//是否系统邮件
			writeByte(_btSystem);
			//邮件摘要物品列表
			writeShort(_summaryitemlist.length);
			for (var i: int = 0; i < _summaryitemlist.length; i++) {
				writeBean(_summaryitemlist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//邮件Id
			_mailid = readLong();
			//发件人
			_szSenderName = readString();
			//邮件标题
			_szTitle = readString();
			//是否已读取
			_btRead = readByte();
			//是否有附件
			_btAccessory = readByte();
			//发送时间
			_nSendTime = readInt();
			//是否系统邮件
			_btSystem = readByte();
			//邮件摘要物品列表
			var summaryitemlist_length : int = readShort();
			for (var i: int = 0; i < summaryitemlist_length; i++) {
				_summaryitemlist[i] = readBean(MailSummaryItem) as MailSummaryItem;
			}
			return true;
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
		
		/**
		 * get 发件人
		 * @return 
		 */
		public function get szSenderName(): String{
			return _szSenderName;
		}
		
		/**
		 * set 发件人
		 */
		public function set szSenderName(value: String): void{
			this._szSenderName = value;
		}
		
		/**
		 * get 邮件标题
		 * @return 
		 */
		public function get szTitle(): String{
			return _szTitle;
		}
		
		/**
		 * set 邮件标题
		 */
		public function set szTitle(value: String): void{
			this._szTitle = value;
		}
		
		/**
		 * get 是否已读取
		 * @return 
		 */
		public function get btRead(): int{
			return _btRead;
		}
		
		/**
		 * set 是否已读取
		 */
		public function set btRead(value: int): void{
			this._btRead = value;
		}
		
		/**
		 * get 是否有附件
		 * @return 
		 */
		public function get btAccessory(): int{
			return _btAccessory;
		}
		
		/**
		 * set 是否有附件
		 */
		public function set btAccessory(value: int): void{
			this._btAccessory = value;
		}
		
		/**
		 * get 发送时间
		 * @return 
		 */
		public function get nSendTime(): int{
			return _nSendTime;
		}
		
		/**
		 * set 发送时间
		 */
		public function set nSendTime(value: int): void{
			this._nSendTime = value;
		}
		
		/**
		 * get 是否系统邮件
		 * @return 
		 */
		public function get btSystem(): int{
			return _btSystem;
		}
		
		/**
		 * set 是否系统邮件
		 */
		public function set btSystem(value: int): void{
			this._btSystem = value;
		}
		
		/**
		 * get 邮件摘要物品列表
		 * @return 
		 */
		public function get summaryitemlist(): Vector.<MailSummaryItem>{
			return _summaryitemlist;
		}
		
		/**
		 * set 邮件摘要物品列表
		 */
		public function set summaryitemlist(value: Vector.<MailSummaryItem>): void{
			this._summaryitemlist = value;
		}
		
	}
}