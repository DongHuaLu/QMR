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
	 * 批量收取附件
	 */
	public class ReqMailListGetItemToServerMessage extends Message {
	
		//邮件Id列表
		private var _mailidlist: Vector.<long> = new Vector.<long>();
		//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
		private var _itemid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//邮件Id列表
			writeShort(_mailidlist.length);
			for (i = 0; i < _mailidlist.length; i++) {
				writeLong(_mailidlist[i]);
			}
			//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
			writeLong(_itemid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//邮件Id列表
			var mailidlist_length : int = readShort();
			for (i = 0; i < mailidlist_length; i++) {
				_mailidlist[i] = readLong();
			}
			//要取得的附件物品编号,为-1表示全部收取,为0表示收取金钱，其他表示真正物品编号
			_itemid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 124158;
		}
		
		/**
		 * get 邮件Id列表
		 * @return 
		 */
		public function get mailidlist(): Vector.<long>{
			return _mailidlist;
		}
		
		/**
		 * set 邮件Id列表
		 */
		public function set mailidlist(value: Vector.<long>): void{
			this._mailidlist = value;
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