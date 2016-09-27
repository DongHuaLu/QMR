package com.game.mail.message{
	import com.game.utils.long;
	import com.game.mail.bean.MailDetailInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 收取附件返回
	 */
	public class ResMailGetItemToClientMessage extends Message {
	
		//错误代码
		private var _btErrorCode: int;
		
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		private var _itemid: long;
		
		//读取邮件的内容
		private var _mailDetail: MailDetailInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误代码
			writeByte(_btErrorCode);
			//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
			writeLong(_itemid);
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
			//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
			_itemid = readLong();
			//读取邮件的内容
			_mailDetail = readBean(MailDetailInfo) as MailDetailInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124103;
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
		 * get 要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		 * @return 
		 */
		public function get itemid(): long{
			return _itemid;
		}
		
		/**
		 * set 要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		 */
		public function set itemid(value: long): void{
			this._itemid = value;
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