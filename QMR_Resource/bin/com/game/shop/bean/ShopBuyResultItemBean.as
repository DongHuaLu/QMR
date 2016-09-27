package com.game.shop.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 购买返回信息
	 */
	public class ShopBuyResultItemBean extends Bean {
	
		//物品ID
		private var _goodsid: long;
		
		//物品数量
		private var _num: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品ID
			writeLong(_goodsid);
			//物品数量
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品ID
			_goodsid = readLong();
			//物品数量
			_num = readInt();
			return true;
		}
		
		/**
		 * get 物品ID
		 * @return 
		 */
		public function get goodsid(): long{
			return _goodsid;
		}
		
		/**
		 * set 物品ID
		 */
		public function set goodsid(value: long): void{
			this._goodsid = value;
		}
		
		/**
		 * get 物品数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 物品数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}