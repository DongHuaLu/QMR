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
	 * 修改公告
	 */
	public class ReqGuildChangeBulletinToServerMessage extends Message {
	
		//帮会Id
		private var _guildId: long;
		
		//帮会公告
		private var _guildBulletin: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会Id
			writeLong(_guildId);
			//帮会公告
			writeString(_guildBulletin);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会Id
			_guildId = readLong();
			//帮会公告
			_guildBulletin = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 121213;
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
		 * get 帮会公告
		 * @return 
		 */
		public function get guildBulletin(): String{
			return _guildBulletin;
		}
		
		/**
		 * set 帮会公告
		 */
		public function set guildBulletin(value: String): void{
			this._guildBulletin = value;
		}
		
	}
}