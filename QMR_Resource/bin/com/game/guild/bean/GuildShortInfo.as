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
	 * 帮会简略信息
	 */
	public class GuildShortInfo extends Bean {
	
		//帮会id
		private var _guildId: long;
		
		//帮会国家
		private var _guildCountry: int;
		
		//帮会名字
		private var _guildName: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会id
			writeLong(_guildId);
			//帮会国家
			writeInt(_guildCountry);
			//帮会名字
			writeString(_guildName);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会id
			_guildId = readLong();
			//帮会国家
			_guildCountry = readInt();
			//帮会名字
			_guildName = readString();
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
		 * get 帮会国家
		 * @return 
		 */
		public function get guildCountry(): int{
			return _guildCountry;
		}
		
		/**
		 * set 帮会国家
		 */
		public function set guildCountry(value: int): void{
			this._guildCountry = value;
		}
		
		/**
		 * get 帮会名字
		 * @return 
		 */
		public function get guildName(): String{
			return _guildName;
		}
		
		/**
		 * set 帮会名字
		 */
		public function set guildName(value: String): void{
			this._guildName = value;
		}
		
	}
}