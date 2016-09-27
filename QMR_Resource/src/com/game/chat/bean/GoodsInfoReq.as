package com.game.chat.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 装备信息类
	 */
	public class GoodsInfoReq extends Bean {
	
		//物品Id
		private var _goodId: long;
		
		//索引位置
		private var _index: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//物品Id
			writeLong(_goodId);
			//索引位置
			writeInt(_index);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//物品Id
			_goodId = readLong();
			//索引位置
			_index = readInt();
			return true;
		}
		
		/**
		 * get 物品Id
		 * @return 
		 */
		public function get goodId(): long{
			return _goodId;
		}
		
		/**
		 * set 物品Id
		 */
		public function set goodId(value: long): void{
			this._goodId = value;
		}
		
		/**
		 * get 索引位置
		 * @return 
		 */
		public function get index(): int{
			return _index;
		}
		
		/**
		 * set 索引位置
		 */
		public function set index(value: int): void{
			this._index = value;
		}
		
	}
}