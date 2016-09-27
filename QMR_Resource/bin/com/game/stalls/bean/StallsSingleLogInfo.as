package com.game.stalls.bean{
	import com.game.utils.long;
	import com.game.backpack.bean.ItemInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 摊位单个交易日志
	 */
	public class StallsSingleLogInfo extends Bean {
	
		//交易时间（秒）
		private var _transactiontime: int;
		
		//交易类型，0出售，1购买
		private var _transactiontype: int;
		
		//交易者名字
		private var _tradersname: String;
		
		//交易者ID
		private var _tradersid: long;
		
		//金币价格
		private var _pricegold: int;
		
		//元宝价格
		private var _priceyuanbao: int;
		
		//道具信息
		private var _iteminfo: com.game.backpack.bean.ItemInfo;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//交易时间（秒）
			writeInt(_transactiontime);
			//交易类型，0出售，1购买
			writeByte(_transactiontype);
			//交易者名字
			writeString(_tradersname);
			//交易者ID
			writeLong(_tradersid);
			//金币价格
			writeInt(_pricegold);
			//元宝价格
			writeInt(_priceyuanbao);
			//道具信息
			writeBean(_iteminfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//交易时间（秒）
			_transactiontime = readInt();
			//交易类型，0出售，1购买
			_transactiontype = readByte();
			//交易者名字
			_tradersname = readString();
			//交易者ID
			_tradersid = readLong();
			//金币价格
			_pricegold = readInt();
			//元宝价格
			_priceyuanbao = readInt();
			//道具信息
			_iteminfo = readBean(com.game.backpack.bean.ItemInfo) as com.game.backpack.bean.ItemInfo;
			return true;
		}
		
		/**
		 * get 交易时间（秒）
		 * @return 
		 */
		public function get transactiontime(): int{
			return _transactiontime;
		}
		
		/**
		 * set 交易时间（秒）
		 */
		public function set transactiontime(value: int): void{
			this._transactiontime = value;
		}
		
		/**
		 * get 交易类型，0出售，1购买
		 * @return 
		 */
		public function get transactiontype(): int{
			return _transactiontype;
		}
		
		/**
		 * set 交易类型，0出售，1购买
		 */
		public function set transactiontype(value: int): void{
			this._transactiontype = value;
		}
		
		/**
		 * get 交易者名字
		 * @return 
		 */
		public function get tradersname(): String{
			return _tradersname;
		}
		
		/**
		 * set 交易者名字
		 */
		public function set tradersname(value: String): void{
			this._tradersname = value;
		}
		
		/**
		 * get 交易者ID
		 * @return 
		 */
		public function get tradersid(): long{
			return _tradersid;
		}
		
		/**
		 * set 交易者ID
		 */
		public function set tradersid(value: long): void{
			this._tradersid = value;
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
		 * get 道具信息
		 * @return 
		 */
		public function get iteminfo(): com.game.backpack.bean.ItemInfo{
			return _iteminfo;
		}
		
		/**
		 * set 道具信息
		 */
		public function set iteminfo(value: com.game.backpack.bean.ItemInfo): void{
			this._iteminfo = value;
		}
		
	}
}