package com.game.chat.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玩家简要信息
	 */
	public class RoleChatInfo extends Bean {
	
		//角色ID
		private var _id: long;
		
		//角色名
		private var _name: String;
		
		//等级
		private var _level: int;
		
		//性别
		private var _sex: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色ID
			writeLong(_id);
			//角色名
			writeString(_name);
			//等级
			writeInt(_level);
			//性别
			writeByte(_sex);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色ID
			_id = readLong();
			//角色名
			_name = readString();
			//等级
			_level = readInt();
			//性别
			_sex = readByte();
			return true;
		}
		
		/**
		 * get 角色ID
		 * @return 
		 */
		public function get id(): long{
			return _id;
		}
		
		/**
		 * set 角色ID
		 */
		public function set id(value: long): void{
			this._id = value;
		}
		
		/**
		 * get 角色名
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 角色名
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 等级
		 * @return 
		 */
		public function get level(): int{
			return _level;
		}
		
		/**
		 * set 等级
		 */
		public function set level(value: int): void{
			this._level = value;
		}
		
		/**
		 * get 性别
		 * @return 
		 */
		public function get sex(): int{
			return _sex;
		}
		
		/**
		 * set 性别
		 */
		public function set sex(value: int): void{
			this._sex = value;
		}
		
	}
}