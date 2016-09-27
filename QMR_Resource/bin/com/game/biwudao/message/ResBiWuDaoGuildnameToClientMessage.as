package com.game.biwudao.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 比武岛旗帜占领者帮会名字更新
	 */
	public class ResBiWuDaoGuildnameToClientMessage extends Message {
	
		//旗帜占领者帮会名字
		private var _guildname: String;
		
		//旗帜占领者帮会id
		private var _guildid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//旗帜占领者帮会名字
			writeString(_guildname);
			//旗帜占领者帮会id
			writeLong(_guildid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//旗帜占领者帮会名字
			_guildname = readString();
			//旗帜占领者帮会id
			_guildid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 157104;
		}
		
		/**
		 * get 旗帜占领者帮会名字
		 * @return 
		 */
		public function get guildname(): String{
			return _guildname;
		}
		
		/**
		 * set 旗帜占领者帮会名字
		 */
		public function set guildname(value: String): void{
			this._guildname = value;
		}
		
		/**
		 * get 旗帜占领者帮会id
		 * @return 
		 */
		public function get guildid(): long{
			return _guildid;
		}
		
		/**
		 * set 旗帜占领者帮会id
		 */
		public function set guildid(value: long): void{
			this._guildid = value;
		}
		
	}
}