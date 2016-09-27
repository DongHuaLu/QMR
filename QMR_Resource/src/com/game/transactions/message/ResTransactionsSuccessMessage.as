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
	 * 交易成功
	 */
	public class ResTransactionsSuccessMessage extends Message {
	
		//交易获得经验
		private var _tsexp: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//交易获得经验
			writeLong(_tsexp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//交易获得经验
			_tsexp = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122109;
		}
		
		/**
		 * get 交易获得经验
		 * @return 
		 */
		public function get tsexp(): long{
			return _tsexp;
		}
		
		/**
		 * set 交易获得经验
		 */
		public function set tsexp(value: long): void{
			this._tsexp = value;
		}
		
	}
}