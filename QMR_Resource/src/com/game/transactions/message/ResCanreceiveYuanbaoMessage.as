package com.game.transactions.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送可领取元宝
	 */
	public class ResCanreceiveYuanbaoMessage extends Message {
	
		//可领取元宝
		private var _canryuanbao: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//可领取元宝
			writeInt(_canryuanbao);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//可领取元宝
			_canryuanbao = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 122110;
		}
		
		/**
		 * get 可领取元宝
		 * @return 
		 */
		public function get canryuanbao(): int{
			return _canryuanbao;
		}
		
		/**
		 * set 可领取元宝
		 */
		public function set canryuanbao(value: int): void{
			this._canryuanbao = value;
		}
		
	}
}