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
	 * 收取附件
	 */
	public class ReqMailGetItemToServerMessage extends Message {
	
		//邮件Id
		private var _mailid: long;
		
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		private var _itemid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//邮件Id
			writeLong(_mailid);
			//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
			writeLong(_itemid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//邮件Id
			_mailid = readLong();
			//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
			_itemid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124153;
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
		
	}
}