package com.game.friend.bean{
	import com.game.utils.long;
	import com.game.player.bean.PlayerAppearanceInfo;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 好友角色造型信息
	 */
	public class FriendModeInfo extends Bean {
	
		//玩家ID
		private var _playerid: long;
		
		//玩家名字
		private var _playername: String;
		
		//玩家等级
		private var _playerlv: int;
		
		//造型信息
		private var _appearanceInfo: com.game.player.bean.PlayerAppearanceInfo;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家ID
			writeLong(_playerid);
			//玩家名字
			writeString(_playername);
			//玩家等级
			writeInt(_playerlv);
			//造型信息
			writeBean(_appearanceInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家ID
			_playerid = readLong();
			//玩家名字
			_playername = readString();
			//玩家等级
			_playerlv = readInt();
			//造型信息
			_appearanceInfo = readBean(com.game.player.bean.PlayerAppearanceInfo) as com.game.player.bean.PlayerAppearanceInfo;
			return true;
		}
		
		/**
		 * get 玩家ID
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家ID
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
		/**
		 * get 玩家名字
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 玩家名字
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 玩家等级
		 * @return 
		 */
		public function get playerlv(): int{
			return _playerlv;
		}
		
		/**
		 * set 玩家等级
		 */
		public function set playerlv(value: int): void{
			this._playerlv = value;
		}
		
		/**
		 * get 造型信息
		 * @return 
		 */
		public function get appearanceInfo(): com.game.player.bean.PlayerAppearanceInfo{
			return _appearanceInfo;
		}
		
		/**
		 * set 造型信息
		 */
		public function set appearanceInfo(value: com.game.player.bean.PlayerAppearanceInfo): void{
			this._appearanceInfo = value;
		}
		
	}
}