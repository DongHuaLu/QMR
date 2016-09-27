package com.game.guildflag.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 帮会领地信息
	 */
	public class GuildFlagInfo extends Bean {
	
		//帮会ID
		private var _guildid: long;
		
		//帮会名字
		private var _guildname: String;
		
		//帮会旗帜等级
		private var _guildflaglevel: int;
		
		//占领地图id
		private var _mapmodelid: int;
		
		//血量百分比
		private var _hppercentage: int;
		
		//帮会旗帜id
		private var _guildflagid: int;
		
		//帮主ID
		private var _guildheadid: long;
		
		//帮主名字
		private var _guildheadname: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会ID
			writeLong(_guildid);
			//帮会名字
			writeString(_guildname);
			//帮会旗帜等级
			writeInt(_guildflaglevel);
			//占领地图id
			writeInt(_mapmodelid);
			//血量百分比
			writeInt(_hppercentage);
			//帮会旗帜id
			writeInt(_guildflagid);
			//帮主ID
			writeLong(_guildheadid);
			//帮主名字
			writeString(_guildheadname);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会ID
			_guildid = readLong();
			//帮会名字
			_guildname = readString();
			//帮会旗帜等级
			_guildflaglevel = readInt();
			//占领地图id
			_mapmodelid = readInt();
			//血量百分比
			_hppercentage = readInt();
			//帮会旗帜id
			_guildflagid = readInt();
			//帮主ID
			_guildheadid = readLong();
			//帮主名字
			_guildheadname = readString();
			return true;
		}
		
		/**
		 * get 帮会ID
		 * @return 
		 */
		public function get guildid(): long{
			return _guildid;
		}
		
		/**
		 * set 帮会ID
		 */
		public function set guildid(value: long): void{
			this._guildid = value;
		}
		
		/**
		 * get 帮会名字
		 * @return 
		 */
		public function get guildname(): String{
			return _guildname;
		}
		
		/**
		 * set 帮会名字
		 */
		public function set guildname(value: String): void{
			this._guildname = value;
		}
		
		/**
		 * get 帮会旗帜等级
		 * @return 
		 */
		public function get guildflaglevel(): int{
			return _guildflaglevel;
		}
		
		/**
		 * set 帮会旗帜等级
		 */
		public function set guildflaglevel(value: int): void{
			this._guildflaglevel = value;
		}
		
		/**
		 * get 占领地图id
		 * @return 
		 */
		public function get mapmodelid(): int{
			return _mapmodelid;
		}
		
		/**
		 * set 占领地图id
		 */
		public function set mapmodelid(value: int): void{
			this._mapmodelid = value;
		}
		
		/**
		 * get 血量百分比
		 * @return 
		 */
		public function get hppercentage(): int{
			return _hppercentage;
		}
		
		/**
		 * set 血量百分比
		 */
		public function set hppercentage(value: int): void{
			this._hppercentage = value;
		}
		
		/**
		 * get 帮会旗帜id
		 * @return 
		 */
		public function get guildflagid(): int{
			return _guildflagid;
		}
		
		/**
		 * set 帮会旗帜id
		 */
		public function set guildflagid(value: int): void{
			this._guildflagid = value;
		}
		
		/**
		 * get 帮主ID
		 * @return 
		 */
		public function get guildheadid(): long{
			return _guildheadid;
		}
		
		/**
		 * set 帮主ID
		 */
		public function set guildheadid(value: long): void{
			this._guildheadid = value;
		}
		
		/**
		 * get 帮主名字
		 * @return 
		 */
		public function get guildheadname(): String{
			return _guildheadname;
		}
		
		/**
		 * set 帮主名字
		 */
		public function set guildheadname(value: String): void{
			this._guildheadname = value;
		}
		
	}
}