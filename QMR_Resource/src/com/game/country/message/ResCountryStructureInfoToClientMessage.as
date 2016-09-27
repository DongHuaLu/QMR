package com.game.country.message{
	import com.game.country.bean.CountryStructureInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 请求王城结构信息（打开面板）
	 */
	public class ResCountryStructureInfoToClientMessage extends Message {
	
		//王城结构信息
		private var _countrystructureInfo: Vector.<CountryStructureInfo> = new Vector.<CountryStructureInfo>();
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			var i: int = 0;
			//王城结构信息
			writeShort(_countrystructureInfo.length);
			for (i = 0; i < _countrystructureInfo.length; i++) {
				writeBean(_countrystructureInfo[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			var i: int = 0;
			//王城结构信息
			var countrystructureInfo_length : int = readShort();
			for (i = 0; i < countrystructureInfo_length; i++) {
				_countrystructureInfo[i] = readBean(CountryStructureInfo) as CountryStructureInfo;
			}
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146105;
		}
		
		/**
		 * get 王城结构信息
		 * @return 
		 */
		public function get countrystructureInfo(): Vector.<CountryStructureInfo>{
			return _countrystructureInfo;
		}
		
		/**
		 * set 王城结构信息
		 */
		public function set countrystructureInfo(value: Vector.<CountryStructureInfo>): void{
			this._countrystructureInfo = value;
		}
		
	}
}