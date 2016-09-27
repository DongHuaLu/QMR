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
	 * 购买商品
	 */
	public class ReqStallsBuyMessage extends Message {
	
		//摆摊的玩家ID
		private var _playerid: long;
		
		//商品金币价格
		private var _pricegold: int;
		
		//商品元宝价格
		private var _priceyuanbao: int;
		
		//商品道具唯一ID，-1金币，-2元宝
		private var _goodsid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//摆摊的玩家ID
			writeLong(_playerid);
			//商品金币价格
			writeInt(_pricegold);
			//商品元宝价格
			writeInt(_priceyuanbao);
			//商品道具唯一ID，-1金币，-2元宝
			writeLong(_goodsid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//摆摊的玩家ID
			_playerid = readLong();
			//商品金币价格
			_pricegold = readInt();
			//商品元宝价格
			_priceyuanbao = readInt();
			//商品道具唯一ID，-1金币，-2元宝
			_goodsid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123204;
		}
		
		/**
		 * get 摆摊的玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 摆摊的玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 商品金币价格
		 * @return 
		 */
		public function get pricegold(): int{
			return _pricegold;
		}
		
		/**
		 * set 商品金币价格
		 */
		public function set pricegold(value: int): void{
			this._pricegold = value;
		}
		
		/**
		 * get 商品元宝价格
		 * @return 
		 */
		public function get priceyuanbao(): int{
			return _priceyuanbao;
		}
		
		/**
		 * set 商品元宝价格
		 */
		public function set priceyuanbao(value: int): void{
			this._priceyuanbao = value;
		}
		
		/**
		 * get 商品道具唯一ID，-1金币，-2元宝
		 * @return 
		 */
		public function get goodsid(): long{
			return _goodsid;
		}
		
		/**
		 * set 商品道具唯一ID，-1金币，-2元宝
		 */
		public function set goodsid(value: long): void{
			this._goodsid = value;
		}
		
	}
}