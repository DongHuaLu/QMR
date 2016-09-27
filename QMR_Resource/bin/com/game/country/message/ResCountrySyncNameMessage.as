package com.game.country.message{
	import com.game.country.bean.CountryName;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 同步国家名称
	 */
	public class ResCountrySyncNameMessage extends Message {
	
		//国家名称列表
		private var _countryName: Vector.<CountryName> = new Vector.<CountryName>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//国家名称列表
			writeShort(_countryName.length);
			for (i = 0; i < _countryName.length; i++) {
				writeBean(_countryName[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//国家名称列表
			var countryName_length : int = readShort();
			for (i = 0; i < countryName_length; i++) {
				_countryName[i] = readBean(CountryName) as CountryName;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146118;
		}
		
		/**
		 * get 国家名称列表
		 * @return 
		 */
		public function get countryName(): Vector.<CountryName>{
			return _countryName;
		}
		
		/**
		 * set 国家名称列表
		 */
		public function set countryName(value: Vector.<CountryName>): void{
			this._countryName = value;
		}
		
	}
}