package com.game.bag.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 物品从跨服包裹取出
	 */
	public class ReqBagItemMoveOutMessage extends Message {
	
		//包裹格子号(跨服包裹)
		private var _cellId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//包裹格子号(跨服包裹)
			writeInt(_cellId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//包裹格子号(跨服包裹)
			_cellId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 160203;
		}
		
		/**
		 * get 包裹格子号(跨服包裹)
		 * @return 
		 */
		public function get cellId(): int{
			return _cellId;
		}
		
		/**
		 * set 包裹格子号(跨服包裹)
		 */
		public function set cellId(value: int): void{
			this._cellId = value;
		}
		
	}
}