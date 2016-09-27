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
	 * B玩家接受交易请求
	 */
	public class ReqTransactionsAcceptMessage extends Message {
	
		//交易ID
		private var _transid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//交易ID
			writeLong(_transid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//交易ID
			_transid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122202;
		}
		
		/**
		 * get 交易ID
		 * @return 
		 */
		public function get transid(): long{
			return _transid;
		}
		
		/**
		 * set 交易ID
		 */
		public function set transid(value: long): void{
			this._transid = value;
		}
		
	}
}