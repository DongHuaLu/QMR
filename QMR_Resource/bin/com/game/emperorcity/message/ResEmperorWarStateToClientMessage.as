package com.game.emperorcity.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 皇城争霸战状态
	 */
	public class ResEmperorWarStateToClientMessage extends Message {
	
		//0未开启，1进行中，2已结束
		private var _state: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//0未开启，1进行中，2已结束
			writeByte(_state);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//0未开启，1进行中，2已结束
			_state = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 169102;
		}
		
		/**
		 * get 0未开启，1进行中，2已结束
		 * @return 
		 */
		public function get state(): int{
			return _state;
		}
		
		/**
		 * set 0未开启，1进行中，2已结束
		 */
		public function set state(value: int): void{
			this._state = value;
		}
		
	}
}