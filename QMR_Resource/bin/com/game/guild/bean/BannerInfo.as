package com.game.guild.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 帮旗信息
	 */
	public class BannerInfo extends Bean {
	
		//帮会id
		private var _guildId: long;
		
		//帮会名
		private var _guildName: String;
		
		//帮会旗帜
		private var _guildBanner: String;
		
		//帮主名字
		private var _bangZhuName: String;
		
		//旗帜造型
		private var _bannerIcon: int;
		
		//旗帜等级
		private var _bannerLevel: int;
		
		//帮会创建时间
		private var _createTime: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会id
			writeLong(_guildId);
			//帮会名
			writeString(_guildName);
			//帮会旗帜
			writeString(_guildBanner);
			//帮主名字
			writeString(_bangZhuName);
			//旗帜造型
			writeInt(_bannerIcon);
			//旗帜等级
			writeByte(_bannerLevel);
			//帮会创建时间
			writeInt(_createTime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会id
			_guildId = readLong();
			//帮会名
			_guildName = readString();
			//帮会旗帜
			_guildBanner = readString();
			//帮主名字
			_bangZhuName = readString();
			//旗帜造型
			_bannerIcon = readInt();
			//旗帜等级
			_bannerLevel = readByte();
			//帮会创建时间
			_createTime = readInt();
			return true;
		}
		
		/**
		 * get 帮会id
		 * @return 
		 */
		public function get guildId(): long{
			return _guildId;
		}
		
		/**
		 * set 帮会id
		 */
		public function set guildId(value: long): void{
			this._guildId = value;
		}
		
		/**
		 * get 帮会名
		 * @return 
		 */
		public function get guildName(): String{
			return _guildName;
		}
		
		/**
		 * set 帮会名
		 */
		public function set guildName(value: String): void{
			this._guildName = value;
		}
		
		/**
		 * get 帮会旗帜
		 * @return 
		 */
		public function get guildBanner(): String{
			return _guildBanner;
		}
		
		/**
		 * set 帮会旗帜
		 */
		public function set guildBanner(value: String): void{
			this._guildBanner = value;
		}
		
		/**
		 * get 帮主名字
		 * @return 
		 */
		public function get bangZhuName(): String{
			return _bangZhuName;
		}
		
		/**
		 * set 帮主名字
		 */
		public function set bangZhuName(value: String): void{
			this._bangZhuName = value;
		}
		
		/**
		 * get 旗帜造型
		 * @return 
		 */
		public function get bannerIcon(): int{
			return _bannerIcon;
		}
		
		/**
		 * set 旗帜造型
		 */
		public function set bannerIcon(value: int): void{
			this._bannerIcon = value;
		}
		
		/**
		 * get 旗帜等级
		 * @return 
		 */
		public function get bannerLevel(): int{
			return _bannerLevel;
		}
		
		/**
		 * set 旗帜等级
		 */
		public function set bannerLevel(value: int): void{
			this._bannerLevel = value;
		}
		
		/**
		 * get 帮会创建时间
		 * @return 
		 */
		public function get createTime(): int{
			return _createTime;
		}
		
		/**
		 * set 帮会创建时间
		 */
		public function set createTime(value: int): void{
			this._createTime = value;
		}
		
	}
}