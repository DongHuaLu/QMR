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
	 * 商品调整
	 */
	public class ReqStallsAdjustPricesMessage extends Message {
	
		//道具唯一ID，-1金币，-2元宝
		private var _goodsid: long;
		
		//道具模版ID，-1金币，-2元宝
		private var _goodsmodelid: int;
		
		//金币价格
		private var _pricegold: int;
		
		//元宝价格
		private var _priceyuanbao: int;
		
		//新位置
		private var _pos: int;
		
		//新的数量
		private var _num: int;
		
		//原始数量（只是用来检测，后端另有验证）
		private var _currentnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具唯一ID，-1金币，-2元宝
			writeLong(_goodsid);
			//道具模版ID，-1金币，-2元宝
			writeInt(_goodsmodelid);
			//金币价格
			writeInt(_pricegold);
			//元宝价格
			writeInt(_priceyuanbao);
			//新位置
			writeInt(_pos);
			//新的数量
			writeInt(_num);
			//原始数量（只是用来检测，后端另有验证）
			writeInt(_currentnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具唯一ID，-1金币，-2元宝
			_goodsid = readLong();
			//道具模版ID，-1金币，-2元宝
			_goodsmodelid = readInt();
			//金币价格
			_pricegold = readInt();
			//元宝价格
			_priceyuanbao = readInt();
			//新位置
			_pos = readInt();
			//新的数量
			_num = readInt();
			//原始数量（只是用来检测，后端另有验证）
			_currentnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123206;
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
		 * get 道具模版ID，-1金币，-2元宝
		 * @return 
		 */
		public function get goodsmodelid(): int{
			return _goodsmodelid;
		}
		
		/**
		 * set 道具模版ID，-1金币，-2元宝
		 */
		public function set goodsmodelid(value: int): void{
			this._goodsmodelid = value;
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
		 * get 新位置
		 * @return 
		 */
		public function get pos(): int{
			return _pos;
		}
		
		/**
		 * set 新位置
		 */
		public function set pos(value: int): void{
			this._pos = value;
		}
		
		/**
		 * get 新的数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 新的数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 原始数量（只是用来检测，后端另有验证）
		 * @return 
		 */
		public function get currentnum(): int{
			return _currentnum;
		}
		
		/**
		 * set 原始数量（只是用来检测，后端另有验证）
		 */
		public function set currentnum(value: int): void{
			this._currentnum = value;
		}
		
	}
}