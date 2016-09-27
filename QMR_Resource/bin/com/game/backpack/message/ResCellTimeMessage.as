package com.game.backpack.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 打开格子所需时间
	 */
	public class ResCellTimeMessage extends Message {
	
		//格子编号
		private var _cellId: int;
		
		//剩余时间(秒)
		private var _timeRemaining: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//格子编号
			writeInt(_cellId);
			//剩余时间(秒)
			writeInt(_timeRemaining);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//格子编号
			_cellId = readInt();
			//剩余时间(秒)
			_timeRemaining = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 104109;
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
		 * get 剩余时间(秒)
		 * @return 
		 */
		public function get timeRemaining(): int{
			return _timeRemaining;
		}
		
		/**
		 * set 剩余时间(秒)
		 */
		public function set timeRemaining(value: int): void{
			this._timeRemaining = value;
		}
		
	}
}