package com.game.gm.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * GM等级
	 */
	public class GmLevelMessage extends Message {
	
		//Gm等级
		private var _level: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//Gm等级
			writeInt(_level);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//Gm等级
			_level = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 200101;
		}
		
		/**
		 * get Gm等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set Gm等级
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
	}
}