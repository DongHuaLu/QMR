package com.game.map.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 变换方面
	 */
	public class ReqChangeDirectMessage extends Message {
	
		//人物面对方向
		private var _dir: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//人物面对方向
			writeByte(_dir);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//人物面对方向
			_dir = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101213;
		}
		
		/**
		 * get 人物面对方向
		 * @return 
		 */
		public function get dir(): int{
			return _dir;
		}
		
		/**
		 * set 人物面对方向
		 */
		public function set dir(value: int): void{
			this._dir = value;
		}
		
	}
}