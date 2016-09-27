package com.game.store.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开格子结果
	 */
	public class ResStoreOpenCellResultMessage extends Message {
	
		//格子编号
		private var _cellId: int;
		
		//1成功 0失败
		private var _isSuccess: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//格子编号
			writeInt(_cellId);
			//1成功 0失败
			writeInt(_isSuccess);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//格子编号
			_cellId = readInt();
			//1成功 0失败
			_isSuccess = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 112106;
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
		
		/**
		 * get 1成功 0失败
		 * @return 
		 */
		public function get isSuccess(): int{
			return _isSuccess;
		}
		
		/**
		 * set 1成功 0失败
		 */
		public function set isSuccess(value: int): void{
			this._isSuccess = value;
		}
		
	}
}