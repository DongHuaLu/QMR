package com.game.emperorcity.bean{
	import com.game.emperorcity.bean.EmperorMemberInfo;
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 皇城结构信息
	 */
	public class EmperorStructureInfo extends Bean {
	
		//皇城占领者国家ID
		private var _countryid: int;
		
		//皇城帮会ID
		private var _guildid: long;
		
		//皇城帮会名字
		private var _guildname: String;
		
		//皇城成员信息列表
		private var _countrymemberInfolist: Vector.<EmperorMemberInfo> = new Vector.<EmperorMemberInfo>();
		//在位天数
		private var _day: int;
		
		//皇城攻城时间
		private var _emperorWartime: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//皇城占领者国家ID
			writeInt(_countryid);
			//皇城帮会ID
			writeLong(_guildid);
			//皇城帮会名字
			writeString(_guildname);
			//皇城成员信息列表
			writeShort(_countrymemberInfolist.length);
			for (var i: int = 0; i < _countrymemberInfolist.length; i++) {
				writeBean(_countrymemberInfolist[i]);
			}
			//在位天数
			writeInt(_day);
			//皇城攻城时间
			writeString(_emperorWartime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//皇城占领者国家ID
			_countryid = readInt();
			//皇城帮会ID
			_guildid = readLong();
			//皇城帮会名字
			_guildname = readString();
			//皇城成员信息列表
			var countrymemberInfolist_length : int = readShort();
			for (var i: int = 0; i < countrymemberInfolist_length; i++) {
				_countrymemberInfolist[i] = readBean(EmperorMemberInfo) as EmperorMemberInfo;
			}
			//在位天数
			_day = readInt();
			//皇城攻城时间
			_emperorWartime = readString();
			return true;
		}
		
		/**
		 * get 皇城占领者国家ID
		 * @return 
		 */
		public function get countryid(): int{
			return _countryid;
		}
		
		/**
		 * set 皇城占领者国家ID
		 */
		public function set countryid(value: int): void{
			this._countryid = value;
		}
		
		/**
		 * get 皇城帮会ID
		 * @return 
		 */
		public function get guildid(): long{
			return _guildid;
		}
		
		/**
		 * set 皇城帮会ID
		 */
		public function set guildid(value: long): void{
			this._guildid = value;
		}
		
		/**
		 * get 皇城帮会名字
		 * @return 
		 */
		public function get guildname(): String{
			return _guildname;
		}
		
		/**
		 * set 皇城帮会名字
		 */
		public function set guildname(value: String): void{
			this._guildname = value;
		}
		
		/**
		 * get 皇城成员信息列表
		 * @return 
		 */
		public function get countrymemberInfolist(): Vector.<EmperorMemberInfo>{
			return _countrymemberInfolist;
		}
		
		/**
		 * set 皇城成员信息列表
		 */
		public function set countrymemberInfolist(value: Vector.<EmperorMemberInfo>): void{
			this._countrymemberInfolist = value;
		}
		
		/**
		 * get 在位天数
		 * @return 
		 */
		public function get day(): int{
			return _day;
		}
		
		/**
		 * set 在位天数
		 */
		public function set day(value: int): void{
			this._day = value;
		}
		
		/**
		 * get 皇城攻城时间
		 * @return 
		 */
		public function get emperorWartime(): String{
			return _emperorWartime;
		}
		
		/**
		 * set 皇城攻城时间
		 */
		public function set emperorWartime(value: String): void{
			this._emperorWartime = value;
		}
		
	}
}