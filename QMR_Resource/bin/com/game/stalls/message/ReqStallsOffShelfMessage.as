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
	 * 商品下架
	 */
	public class ReqStallsOffShelfMessage extends Message {
	
		//道具唯一ID，-1金币，-2元宝
		private var _goodsid: long;
		
		//下架道具数量
		private var _num: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//道具唯一ID，-1金币，-2元宝
			writeLong(_goodsid);
			//下架道具数量
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//道具唯一ID，-1金币，-2元宝
			_goodsid = readLong();
			//下架道具数量
			_num = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123207;
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
		 * get 下架道具数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 下架道具数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}