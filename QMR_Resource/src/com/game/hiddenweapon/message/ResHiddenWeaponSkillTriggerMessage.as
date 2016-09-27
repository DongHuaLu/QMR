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
	 * 暗器技能触发
	 */
	public class ResHiddenWeaponSkillTriggerMessage extends Message {
	
		//暗器技能id
		private var _skill: int;
		
		//暗器技能等级
		private var _level: int;
		
		//玩家id
		private var _playerid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//暗器技能id
			writeInt(_skill);
			//暗器技能等级
			writeInt(_level);
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
			//暗器技能等级
			_level = readInt();
			//玩家id
			_playerid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 162109;
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
		 * get 暗器技能等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 暗器技能等级
		 */
		public function set level(value: int): void{
			this._level = value;
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