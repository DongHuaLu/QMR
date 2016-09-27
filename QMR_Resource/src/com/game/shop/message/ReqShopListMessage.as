package com.game.shop.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求商店出售物品列表
	 */
	public class ReqShopListMessage extends Message {
	
		//模型编号
		private var _shopModelId: int;
		
		//页号
		private var _page: int;
		
		//是否显示高于玩家等级的商品1 显示 0 不显示
		private var _gradeLimit: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//模型编号
			writeInt(_shopModelId);
			//页号
			writeByte(_page);
			//是否显示高于玩家等级的商品1 显示 0 不显示
			writeByte(_gradeLimit);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//模型编号
			_shopModelId = readInt();
			//页号
			_page = readByte();
			//是否显示高于玩家等级的商品1 显示 0 不显示
			_gradeLimit = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 105206;
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
		 * get 是否显示高于玩家等级的商品1 显示 0 不显示
		 * @return 
		 */
		public function get gradeLimit(): int{
			return _gradeLimit;
		}
		
		/**
		 * set 是否显示高于玩家等级的商品1 显示 0 不显示
		 */
		public function set gradeLimit(value: int): void{
			this._gradeLimit = value;
		}
		
	}
}