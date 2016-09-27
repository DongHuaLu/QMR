package com.game.activities.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 零点通知事件
	 */
	public class ResCallEveryDay0ClockMessage extends Message {
	
		//通知事件类型
		private var _eventType: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//通知事件类型
			writeInt(_eventType);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//通知事件类型
			_eventType = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 138103;
		}
		
		/**
		 * get 通知事件类型
		 * @return 
		 */
		public function get eventType(): int{
			return _eventType;
		}
		
		/**
		 * set 通知事件类型
		 */
		public function set eventType(value: int): void{
			this._eventType = value;
		}
		
	}
}