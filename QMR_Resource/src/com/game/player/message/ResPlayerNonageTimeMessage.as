package com.game.player.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家防沉迷在线时间
	 */
	public class ResPlayerNonageTimeMessage extends Message {
	
		//玩家防沉迷在线时间
		private var _time: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家防沉迷在线时间
			writeInt(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家防沉迷在线时间
			_time = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103122;
		}
		
		/**
		 * get 玩家防沉迷在线时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 玩家防沉迷在线时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}