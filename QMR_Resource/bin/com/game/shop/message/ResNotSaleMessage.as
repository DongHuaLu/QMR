package com.game.shop.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 下架商品列表
	 */
	public class ResNotSaleMessage extends Message {
	
		//销售Id
		private var _sellId: int;
		
		//下架商品列表
		private var _itemIds: Vector.<int> = new Vector.<int>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//销售Id
			writeInt(_sellId);
			//下架商品列表
			writeShort(_itemIds.length);
			for (i = 0; i < _itemIds.length; i++) {
				writeInt(_itemIds[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//销售Id
			_sellId = readInt();
			//下架商品列表
			var itemIds_length : int = readShort();
			for (i = 0; i < itemIds_length; i++) {
				_itemIds[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 105101;
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
		
		/**
		 * get 下架商品列表
		 * @return 
		 */
		public function get itemIds(): Vector.<int>{
			return _itemIds;
		}
		
		/**
		 * set 下架商品列表
		 */
		public function set itemIds(value: Vector.<int>): void{
			this._itemIds = value;
		}
		
	}
}