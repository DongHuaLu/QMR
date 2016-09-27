package com.game.stalls.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 修改摊位名字
	 */
	public class ReqChangeStallsNameMessage extends Message {
	
		//摊位名字
		private var _name: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//摊位名字
			writeString(_name);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//摊位名字
			_name = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123211;
		}
		
		/**
		 * get 摊位名字
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 摊位名字
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
	}
}