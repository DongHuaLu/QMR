package com.game.amulet.message{
	import com.game.amulet.bean.AmuletInfo;
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 护身符信息
	 */
	public class ResAmuletInfoMessage extends Message {
	
		//角色ID
		private var _playerid: long;
		
		//护身符信息
		private var _info: AmuletInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色ID
			writeLong(_playerid);
			//护身符信息
			writeBean(_info);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色ID
			_playerid = readLong();
			//护身符信息
			_info = readBean(AmuletInfo) as AmuletInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 166101;
		}
		
		/**
		 * get 角色ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 角色ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 护身符信息
		 * @return 
		 */
		public function get info(): AmuletInfo{
			return _info;
		}
		
		/**
		 * set 护身符信息
		 */
		public function set info(value: AmuletInfo): void{
			this._info = value;
		}
		
	}
}