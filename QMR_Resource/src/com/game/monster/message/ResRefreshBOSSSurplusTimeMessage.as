package com.game.monster.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * BOSS刷新剩余时间
	 */
	public class ResRefreshBOSSSurplusTimeMessage extends Message {
	
		//剩余时间（秒）
		private var _time: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//剩余时间（秒）
			writeInt(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//剩余时间（秒）
			_time = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 114113;
		}
		
		/**
		 * get 剩余时间（秒）
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 剩余时间（秒）
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}