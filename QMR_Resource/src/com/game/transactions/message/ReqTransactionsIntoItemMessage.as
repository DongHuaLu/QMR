package com.game.transactions.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 往交易框放入道具
	 */
	public class ReqTransactionsIntoItemMessage extends Message {
	
		//放入交易框的位置
		private var _itemposition: int;
		
		//道具唯一ID
		private var _itemid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//放入交易框的位置
			writeShort(_itemposition);
			//道具唯一ID
			writeLong(_itemid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//放入交易框的位置
			_itemposition = readShort();
			//道具唯一ID
			_itemid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122205;
		}
		
		/**
		 * get 放入交易框的位置
		 * @return 
		 */
		public function get itemposition(): int{
			return _itemposition;
		}
		
		/**
		 * set 放入交易框的位置
		 */
		public function set itemposition(value: int): void{
			this._itemposition = value;
		}
		
		/**
		 * get 道具唯一ID
		 * @return 
		 */
		public function get itemid(): long{
			return _itemid;
		}
		
		/**
		 * set 道具唯一ID
		 */
		public function set itemid(value: long): void{
			this._itemid = value;
		}
		
	}
}