package com.game.guild.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 创建公会
	 */
	public class ReqGuildCreateToServerMessage extends Message {
	
		//公会名字
		private var _guildName: String;
		
		//公会旗帜
		private var _guildBanner: String;
		
		//公会旗帜造型
		private var _guildBannerIcon: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//公会名字
			writeString(_guildName);
			//公会旗帜
			writeString(_guildBanner);
			//公会旗帜造型
			writeInt(_guildBannerIcon);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//公会名字
			_guildName = readString();
			//公会旗帜
			_guildBanner = readString();
			//公会旗帜造型
			_guildBannerIcon = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121201;
		}
		
		/**
		 * get 公会名字
		 * @return 
		 */
		public function get guildName(): String{
			return _guildName;
		}
		
		/**
		 * set 公会名字
		 */
		public function set guildName(value: String): void{
			this._guildName = value;
		}
		
		/**
		 * get 公会旗帜
		 * @return 
		 */
		public function get guildBanner(): String{
			return _guildBanner;
		}
		
		/**
		 * set 公会旗帜
		 */
		public function set guildBanner(value: String): void{
			this._guildBanner = value;
		}
		
		/**
		 * get 公会旗帜造型
		 * @return 
		 */
		public function get guildBannerIcon(): int{
			return _guildBannerIcon;
		}
		
		/**
		 * set 公会旗帜造型
		 */
		public function set guildBannerIcon(value: int): void{
			this._guildBannerIcon = value;
		}
		
	}
}