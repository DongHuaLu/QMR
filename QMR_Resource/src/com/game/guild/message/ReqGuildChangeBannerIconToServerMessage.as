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
	 * 更换帮旗造型
	 */
	public class ReqGuildChangeBannerIconToServerMessage extends Message {
	
		//帮会Id
		private var _guildId: long;
		
		//旗帜造型
		private var _bannerIcon: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会Id
			writeLong(_guildId);
			//旗帜造型
			writeInt(_bannerIcon);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会Id
			_guildId = readLong();
			//旗帜造型
			_bannerIcon = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121215;
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
		
	}
}