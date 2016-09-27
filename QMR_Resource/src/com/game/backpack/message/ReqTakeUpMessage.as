package com.game.backpack.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 拾取
	 */
	public class ReqTakeUpMessage extends Message {
	
		//掉落ID
		private var _goodsId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//掉落ID
			writeLong(_goodsId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//掉落ID
			_goodsId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104211;
		}
		
		/**
		 * get 掉落ID
		 * @return 
		 */
		public function get goodsId(): long{
			return _goodsId;
		}
		
		/**
		 * set 掉落ID
		 */
		public function set goodsId(value: long): void{
			this._goodsId = value;
		}
		
	}
}