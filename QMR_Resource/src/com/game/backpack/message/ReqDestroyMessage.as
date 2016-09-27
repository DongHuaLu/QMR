package com.game.backpack.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 物品销毁
	 */
	public class ReqDestroyMessage extends Message {
	
		//格子编号
		private var _cellId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//格子编号
			writeInt(_cellId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//格子编号
			_cellId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104210;
		}
		
		/**
		 * get 格子编号
		 * @return 
		 */
		public function get cellId(): int{
			return _cellId;
		}
		
		/**
		 * set 格子编号
		 */
		public function set cellId(value: int): void{
			this._cellId = value;
		}
		
	}
}