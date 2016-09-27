package com.game.stalls.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 商品上架
	 */
	public class ReqStallsProductWasAddedMessage extends Message {
	
		//道具唯一ID，-1金币，-2元宝
		private var _goodsid: long;
		
		//金币价格
		private var _pricegold: int;
		
		//元宝价格
		private var _priceyuanbao: int;
		
		//要上架的道具数量
		private var _num: int;
		
		//商品总数
		private var _goodsnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具唯一ID，-1金币，-2元宝
			writeLong(_goodsid);
			//金币价格
			writeInt(_pricegold);
			//元宝价格
			writeInt(_priceyuanbao);
			//要上架的道具数量
			writeInt(_num);
			//商品总数
			writeInt(_goodsnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具唯一ID，-1金币，-2元宝
			_goodsid = readLong();
			//金币价格
			_pricegold = readInt();
			//元宝价格
			_priceyuanbao = readInt();
			//要上架的道具数量
			_num = readInt();
			//商品总数
			_goodsnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123205;
		}
		
		/**
		 * get 道具唯一ID，-1金币，-2元宝
		 * @return 
		 */
		public function get goodsid(): long{
			return _goodsid;
		}
		
		/**
		 * set 道具唯一ID，-1金币，-2元宝
		 */
		public function set goodsid(value: long): void{
			this._goodsid = value;
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
		 * get 要上架的道具数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 要上架的道具数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 商品总数
		 * @return 
		 */
		public function get goodsnum(): int{
			return _goodsnum;
		}
		
		/**
		 * set 商品总数
		 */
		public function set goodsnum(value: int): void{
			this._goodsnum = value;
		}
		
	}
}