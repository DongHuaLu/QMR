package com.game.guild.bean{
	import com.game.guild.bean.BannerInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 查看其他帮会信息
	 */
	public class OtherGuildInfo extends Bean {
	
		//帮会旗帜
		private var _guildBanner: BannerInfo;
		
		//帮会职位等级
		private var _guildPowerLevel: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会旗帜
			writeBean(_guildBanner);
			//帮会职位等级
			writeByte(_guildPowerLevel);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会旗帜
			_guildBanner = readBean(BannerInfo) as BannerInfo;
			//帮会职位等级
			_guildPowerLevel = readByte();
			return true;
		}
		
		/**
		 * get 帮会旗帜
		 * @return 
		 */
		public function get guildBanner(): BannerInfo{
			return _guildBanner;
		}
		
		/**
		 * set 帮会旗帜
		 */
		public function set guildBanner(value: BannerInfo): void{
			this._guildBanner = value;
		}
		
		/**
		 * get 帮会职位等级
		 * @return 
		 */
		public function get guildPowerLevel(): int{
			return _guildPowerLevel;
		}
		
		/**
		 * set 帮会职位等级
		 */
		public function set guildPowerLevel(value: int): void{
			this._guildPowerLevel = value;
		}
		
	}
}