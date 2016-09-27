package com.game.login.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 角色信息类
	 */
	public class CharacterInfo extends Bean {
	
		//角色Id
		private var _playerId: long;
		
		//角色名字
		private var _name: String;
		
		//角色等级
		private var _level: int;
		
		//角色性别
		private var _sex: int;
		
		//最后登录时间
		private var _longintime: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色Id
			writeLong(_playerId);
			//角色名字
			writeString(_name);
			//角色等级
			writeInt(_level);
			//角色性别
			writeByte(_sex);
			//最后登录时间
			writeInt(_longintime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色Id
			_playerId = readLong();
			//角色名字
			_name = readString();
			//角色等级
			_level = readInt();
			//角色性别
			_sex = readByte();
			//最后登录时间
			_longintime = readInt();
			return true;
		}
		
		/**
		 * get 角色Id
		 * @return 
		 */
		public function get playerId(): long{
			return _playerId;
		}
		
		/**
		 * set 角色Id
		 */
		public function set playerId(value: long): void{
			this._playerId = value;
		}
		
		/**
		 * get 角色名字
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 角色名字
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 角色等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 角色等级
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
		/**
		 * get 角色性别
		 * @return 
		 */
		public function get sex(): int{
			return _sex;
		}
		
		/**
		 * set 角色性别
		 */
		public function set sex(value: int): void{
			this._sex = value;
		}
		
		/**
		 * get 最后登录时间
		 * @return 
		 */
		public function get longintime(): int{
			return _longintime;
		}
		
		/**
		 * set 最后登录时间
		 */
		public function set longintime(value: int): void{
			this._longintime = value;
		}
		
	}
}