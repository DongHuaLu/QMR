package com.game.shop.message{
	import com.game.shop.bean.ShopItemInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 商品列表
	 */
	public class ResShopItemListMessage extends Message {
	
		//模型编号
		private var _shopModelId: int;
		
		//页号
		private var _page: int;
		
		//指定页的列表
		private var _shopItemList: Vector.<ShopItemInfo> = new Vector.<ShopItemInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//模型编号
			writeInt(_shopModelId);
			//页号
			writeByte(_page);
			//指定页的列表
			writeShort(_shopItemList.length);
			for (i = 0; i < _shopItemList.length; i++) {
				writeBean(_shopItemList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//模型编号
			_shopModelId = readInt();
			//页号
			_page = readByte();
			//指定页的列表
			var shopItemList_length : int = readShort();
			for (i = 0; i < shopItemList_length; i++) {
				_shopItemList[i] = readBean(ShopItemInfo) as ShopItemInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 105105;
		}
		
		/**
		 * get 模型编号
		 * @return 
		 */
		public function get shopModelId(): int{
			return _shopModelId;
		}
		
		/**
		 * set 模型编号
		 */
		public function set shopModelId(value: int): void{
			this._shopModelId = value;
		}
		
		/**
		 * get 页号
		 * @return 
		 */
		public function get page(): int{
			return _page;
		}
		
		/**
		 * set 页号
		 */
		public function set page(value: int): void{
			this._page = value;
		}
		
		/**
		 * get 指定页的列表
		 * @return 
		 */
		public function get shopItemList(): Vector.<ShopItemInfo>{
			return _shopItemList;
		}
		
		/**
		 * set 指定页的列表
		 */
		public function set shopItemList(value: Vector.<ShopItemInfo>): void{
			this._shopItemList = value;
		}
		
	}
}