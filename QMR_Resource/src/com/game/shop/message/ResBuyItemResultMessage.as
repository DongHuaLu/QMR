package com.game.shop.message{
	import com.game.shop.bean.ShopBuyResultItemBean;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 购买成功消息
	 */
	public class ResBuyItemResultMessage extends Message {
	
		//销售Id
		private var _sellId: int;
		
		//花费类型
		private var _costType: int;
		
		//物品列表
		private var _goodsinfo: Vector.<ShopBuyResultItemBean> = new Vector.<ShopBuyResultItemBean>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//销售Id
			writeInt(_sellId);
			//花费类型
			writeInt(_costType);
			//物品列表
			writeShort(_goodsinfo.length);
			for (i = 0; i < _goodsinfo.length; i++) {
				writeBean(_goodsinfo[i]);
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
			//花费类型
			_costType = readInt();
			//物品列表
			var goodsinfo_length : int = readShort();
			for (i = 0; i < goodsinfo_length; i++) {
				_goodsinfo[i] = readBean(ShopBuyResultItemBean) as ShopBuyResultItemBean;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 105106;
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
		 * get 花费类型
		 * @return 
		 */
		public function get costType(): int{
			return _costType;
		}
		
		/**
		 * set 花费类型
		 */
		public function set costType(value: int): void{
			this._costType = value;
		}
		
		/**
		 * get 物品列表
		 * @return 
		 */
		public function get goodsinfo(): Vector.<ShopBuyResultItemBean>{
			return _goodsinfo;
		}
		
		/**
		 * set 物品列表
		 */
		public function set goodsinfo(value: Vector.<ShopBuyResultItemBean>): void{
			this._goodsinfo = value;
		}
		
	}
}