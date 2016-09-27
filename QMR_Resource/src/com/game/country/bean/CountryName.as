package com.game.country.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 国家名称信息
	 */
	public class CountryName extends Bean {
	
		//国家
		private var _country: int;
		
		//名称
		private var _name: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//国家
			writeInt(_country);
			//名称
			writeString(_name);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//国家
			_country = readInt();
			//名称
			_name = readString();
			return true;
		}
		
		/**
		 * get 国家
		 * @return 
		 */
		public function get country(): int{
			return _country;
		}
		
		/**
		 * set 国家
		 */
		public function set country(value: int): void{
			this._country = value;
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