package com.game.country.message{
	import com.game.country.bean.CountryWarInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 地图广播攻城玉玺即时消息
	 */
	public class ResCountrySiegeYuXiImmediateToClientMessage extends Message {
	
		//攻城玉玺消息
		private var _countrywarinfo: CountryWarInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//攻城玉玺消息
			writeBean(_countrywarinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//攻城玉玺消息
			_countrywarinfo = readBean(CountryWarInfo) as CountryWarInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146104;
		}
		
		/**
		 * get 攻城玉玺消息
		 * @return 
		 */
		public function get countrywarinfo(): CountryWarInfo{
			return _countrywarinfo;
		}
		
		/**
		 * set 攻城玉玺消息
		 */
		public function set countrywarinfo(value: CountryWarInfo): void{
			this._countrywarinfo = value;
		}
		
	}
}