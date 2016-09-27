package com.game.shop.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求下架商品
	 */
	public class ReqNotSaleMessage extends Message {
	
		//销售Id
		private var _sellId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//销售Id
			writeInt(_sellId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//销售Id
			_sellId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 105203;
		}
		
		/**
		 * get 销售Id
		 * @return 
		 */
		public function get sellId(): int{
			return _sellId;
		}
		
		/**
		 * set 销售Id
		 */
		public function set sellId(value: int): void{
			this._sellId = value;
		}
		
	}
}