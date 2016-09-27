package com.game.country.message{
	import com.game.country.bean.CountryTopInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 王城战排行榜信息
	 */
	public class ResCountryTopInfoToClientMessage extends Message {
	
		//王城战排行榜信息
		private var _countryTopInfolist: Vector.<CountryTopInfo> = new Vector.<CountryTopInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//王城战排行榜信息
			writeShort(_countryTopInfolist.length);
			for (i = 0; i < _countryTopInfolist.length; i++) {
				writeBean(_countryTopInfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//王城战排行榜信息
			var countryTopInfolist_length : int = readShort();
			for (i = 0; i < countryTopInfolist_length; i++) {
				_countryTopInfolist[i] = readBean(CountryTopInfo) as CountryTopInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146114;
		}
		
		/**
		 * get 王城战排行榜信息
		 * @return 
		 */
		public function get countryTopInfolist(): Vector.<CountryTopInfo>{
			return _countryTopInfolist;
		}
		
		/**
		 * set 王城战排行榜信息
		 */
		public function set countryTopInfolist(value: Vector.<CountryTopInfo>): void{
			this._countryTopInfolist = value;
		}
		
	}
}