package com.game.hiddenweapon.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家抵抗暗器技能
	 */
	public class ResHiddenWeaponSkillRestrictionMessage extends Message {
	
		//暗器技能id
		private var _skill: int;
		
		//玩家id
		private var _playerid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//暗器技能id
			writeInt(_skill);
			//玩家id
			writeLong(_playerid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//暗器技能id
			_skill = readInt();
			//玩家id
			_playerid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 162110;
		}
		
		/**
		 * get 暗器技能id
		 * @return 
		 */
		public function get skill(): int{
			return _skill;
		}
		
		/**
		 * set 暗器技能id
		 */
		public function set skill(value: int): void{
			this._skill = value;
		}
		
		/**
		 * get 玩家id
		 * @return 
		 */
		public function get playerid(): long{
			return _playerid;
		}
		
		/**
		 * set 玩家id
		 */
		public function set playerid(value: long): void{
			this._playerid = value;
		}
		
	}
}