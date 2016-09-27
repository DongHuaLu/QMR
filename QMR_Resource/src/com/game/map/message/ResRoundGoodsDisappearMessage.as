package com.game.map.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 周围消失物品
	 */
	public class ResRoundGoodsDisappearMessage extends Message {
	
		//消失物品列表
		private var _goodsIds: Vector.<long> = new Vector.<long>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//消失物品列表
			writeShort(_goodsIds.length);
			for (i = 0; i < _goodsIds.length; i++) {
				writeLong(_goodsIds[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//消失物品列表
			var goodsIds_length : int = readShort();
			for (i = 0; i < goodsIds_length; i++) {
				_goodsIds[i] = readLong();
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101107;
		}
		
		/**
		 * get 消失物品列表
		 * @return 
		 */
		public function get goodsIds(): Vector.<long>{
			return _goodsIds;
		}
		
		/**
		 * set 消失物品列表
		 */
		public function set goodsIds(value: Vector.<long>): void{
			this._goodsIds = value;
		}
		
	}
}