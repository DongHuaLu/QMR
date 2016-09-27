package com.game.stalls.bean{
	import com.game.backpack.bean.ItemInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 摊位物品信息
	 */
	public class StallsGoodsInfo extends Bean {
	
		//金币价格
		private var _pricegold: int;
		
		//元宝价格
		private var _priceyuanbao: int;
		
		//出售的道具信息
		private var _iteminfo: com.game.backpack.bean.ItemInfo;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//金币价格
			writeInt(_pricegold);
			//元宝价格
			writeInt(_priceyuanbao);
			//出售的道具信息
			writeBean(_iteminfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//金币价格
			_pricegold = readInt();
			//元宝价格
			_priceyuanbao = readInt();
			//出售的道具信息
			_iteminfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			return true;
		}
		
		/**
		 * get 金币价格
		 * @return 
		 */
		public function get pricegold(): int{
			return _pricegold;
		}
		
		/**
		 * set 金币价格
		 */
		public function set pricegold(value: int): void{
			this._pricegold = value;
		}
		
		/**
		 * get 元宝价格
		 * @return 
		 */
		public function get priceyuanbao(): int{
			return _priceyuanbao;
		}
		
		/**
		 * set 元宝价格
		 */
		public function set priceyuanbao(value: int): void{
			this._priceyuanbao = value;
		}
		
		/**
		 * get 出售的道具信息
		 * @return 
		 */
		public function get iteminfo(): com.game.backpack.bean.ItemInfo{
			return _iteminfo;
		}
		
		/**
		 * set 出售的道具信息
		 */
		public function set iteminfo(value: com.game.backpack.bean.ItemInfo): void{
			this._iteminfo = value;
		}
		
	}
}