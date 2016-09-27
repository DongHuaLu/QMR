package com.game.country.bean{
	import com.game.utils.long;
	import com.game.country.bean.CountryMemberInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 王城结构信息
	 */
	public class CountryStructureInfo extends Bean {
	
		//国家ID
		private var _country: int;
		
		//攻城时间
		private var _Siegetime: String;
		
		//王城帮会ID
		private var _guildid: long;
		
		//王城帮会名字
		private var _guildname: String;
		
		//王城成员信息列表
		private var _countrymemberInfolist: Vector.<CountryMemberInfo> = new Vector.<CountryMemberInfo>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//国家ID
			writeInt(_country);
			//攻城时间
			writeString(_Siegetime);
			//王城帮会ID
			writeLong(_guildid);
			//王城帮会名字
			writeString(_guildname);
			//王城成员信息列表
			writeShort(_countrymemberInfolist.length);
			for (var i: int = 0; i < _countrymemberInfolist.length; i++) {
				writeBean(_countrymemberInfolist[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//国家ID
			_country = readInt();
			//攻城时间
			_Siegetime = readString();
			//王城帮会ID
			_guildid = readLong();
			//王城帮会名字
			_guildname = readString();
			//王城成员信息列表
			var countrymemberInfolist_length : int = readShort();
			for (var i: int = 0; i < countrymemberInfolist_length; i++) {
				_countrymemberInfolist[i] = readBean(CountryMemberInfo) as CountryMemberInfo;
			}
			return true;
		}
		
		/**
		 * get 国家ID
		 * @return 
		 */
		public function get country(): int{
			return _country;
		}
		
		/**
		 * set 国家ID
		 */
		public function set country(value: int): void{
			this._country = value;
		}
		
		/**
		 * get 攻城时间
		 * @return 
		 */
		public function get Siegetime(): String{
			return _Siegetime;
		}
		
		/**
		 * set 攻城时间
		 */
		public function set Siegetime(value: String): void{
			this._Siegetime = value;
		}
		
		/**
		 * get 王城帮会ID
		 * @return 
		 */
		public function get guildid(): long{
			return _guildid;
		}
		
		/**
		 * set 王城帮会ID
		 */
		public function set guildid(value: long): void{
			this._guildid = value;
		}
		
		/**
		 * get 王城帮会名字
		 * @return 
		 */
		public function get guildname(): String{
			return _guildname;
		}
		
		/**
		 * set 王城帮会名字
		 */
		public function set guildname(value: String): void{
			this._guildname = value;
		}
		
		/**
		 * get 王城成员信息列表
		 * @return 
		 */
		public function get countrymemberInfolist(): Vector.<CountryMemberInfo>{
			return _countrymemberInfolist;
		}
		
		/**
		 * set 王城成员信息列表
		 */
		public function set countrymemberInfolist(value: Vector.<CountryMemberInfo>): void{
			this._countrymemberInfolist = value;
		}
		
	}
}