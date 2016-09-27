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
	 * 友好帮会信息
	 */
	public class FriendGuild extends Bean {
	
		//帮会id
		private var _guildId: long;
		
		//友好帮会列表
		private var _friendGuilds: Vector.<long> = new Vector.<long>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//帮会id
			writeLong(_guildId);
			//友好帮会列表
			writeShort(_friendGuilds.length);
			for (var i: int = 0; i < _friendGuilds.length; i++) {
				writeLong(_friendGuilds[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//帮会id
			_guildId = readLong();
			//友好帮会列表
			var friendGuilds_length : int = readShort();
			for (var i: int = 0; i < friendGuilds_length; i++) {
				_friendGuilds[i] = readLong();
			}
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
		 * get 友好帮会列表
		 * @return 
		 */
		public function get friendGuilds(): Vector.<long>{
			return _friendGuilds;
		}
		
		/**
		 * set 友好帮会列表
		 */
		public function set friendGuilds(value: Vector.<long>): void{
			this._friendGuilds = value;
		}
		
	}
}