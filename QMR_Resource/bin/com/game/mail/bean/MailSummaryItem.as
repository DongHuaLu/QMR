package com.game.mail.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 邮件摘要物品
	 */
	public class MailSummaryItem extends Bean {
	
		//物品Id
		private var _itemid: int;
		
		//物品数目
		private var _itemnum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品Id
			writeInt(_itemid);
			//物品数目
			writeInt(_itemnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品Id
			_itemid = readInt();
			//物品数目
			_itemnum = readInt();
			return true;
		}
		
		/**
		 * get 物品Id
		 * @return 
		 */
		public function get itemid(): int{
			return _itemid;
		}
		
		/**
		 * set 物品Id
		 */
		public function set itemid(value: int): void{
			this._itemid = value;
		}
		
		/**
		 * get 物品数目
		 * @return 
		 */
		public function get itemnum(): int{
			return _itemnum;
		}
		
		/**
		 * set 物品数目
		 */
		public function set itemnum(value: int): void{
			this._itemnum = value;
		}
		
	}
}