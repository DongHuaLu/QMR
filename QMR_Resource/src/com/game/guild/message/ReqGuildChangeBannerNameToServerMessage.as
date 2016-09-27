package com.game.guild.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 更换帮旗名字
	 */
	public class ReqGuildChangeBannerNameToServerMessage extends Message {
	
		//帮会Id
		private var _guildId: long;
		
		//旗帜名字
		private var _bannerName: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会Id
			writeLong(_guildId);
			//旗帜名字
			writeString(_bannerName);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会Id
			_guildId = readLong();
			//旗帜名字
			_bannerName = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121216;
		}
		
		/**
		 * get 帮会Id
		 * @return 
		 */
		public function get guildId(): long{
			return _guildId;
		}
		
		/**
		 * set 帮会Id
		 */
		public function set guildId(value: long): void{
			this._guildId = value;
		}
		
		/**
		 * get 旗帜名字
		 * @return 
		 */
		public function get bannerName(): String{
			return _bannerName;
		}
		
		/**
		 * set 旗帜名字
		 */
		public function set bannerName(value: String): void{
			this._bannerName = value;
		}
		
	}
}