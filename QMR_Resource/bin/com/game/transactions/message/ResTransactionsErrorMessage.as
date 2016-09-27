package com.game.transactions.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 错误消息
	 */
	public class ResTransactionsErrorMessage extends Message {
	
		//错误id（1放入道具异常，2金币量超出，3元宝量超出）
		private var _errorid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//错误id（1放入道具异常，2金币量超出，3元宝量超出）
			writeInt(_errorid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//错误id（1放入道具异常，2金币量超出，3元宝量超出）
			_errorid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122111;
		}
		
		/**
		 * get 错误id（1放入道具异常，2金币量超出，3元宝量超出）
		 * @return 
		 */
		public function get errorid(): int{
			return _errorid;
		}
		
		/**
		 * set 错误id（1放入道具异常，2金币量超出，3元宝量超出）
		 */
		public function set errorid(value: int): void{
			this._errorid = value;
		}
		
	}
}