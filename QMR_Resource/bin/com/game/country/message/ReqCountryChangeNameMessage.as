package com.game.country.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 更改国家名称
	 */
	public class ReqCountryChangeNameMessage extends Message {
	
		//名称
		private var _name: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//名称
			writeString(_name);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//名称
			_name = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146210;
		}
		
		/**
		 * get 名称
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 名称
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
	}
}