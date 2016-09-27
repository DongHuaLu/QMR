package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 真气变化
	 */
	public class ResPlayerZhenqiChangeMessage extends Message {
	
		//当前真气
		private var _currentZhenqi: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前真气
			writeInt(_currentZhenqi);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前真气
			_currentZhenqi = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103113;
		}
		
		/**
		 * get 当前真气
		 * @return 
		 */
		public function get currentZhenqi(): int{
			return _currentZhenqi;
		}
		
		/**
		 * set 当前真气
		 */
		public function set currentZhenqi(value: int): void{
			this._currentZhenqi = value;
		}
		
	}
}