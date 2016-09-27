package com.game.recall.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 召回日志
	 */
	public class History extends Bean {
	
		//角色id
		private var _id: long;
		
		//角色名称
		private var _name: String;
		
		//发起召回的时间
		private var _time: long;
		
		//0:未回归 1:已回归
		private var _back: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色id
			writeLong(_id);
			//角色名称
			writeString(_name);
			//发起召回的时间
			writeLong(_time);
			//0:未回归 1:已回归
			writeByte(_back);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色id
			_id = readLong();
			//角色名称
			_name = readString();
			//发起召回的时间
			_time = readLong();
			//0:未回归 1:已回归
			_back = readByte();
			return true;
		}
		
		/**
		 * get 角色id
		 * @return 
		 */
		public function get id(): long{
			return _id;
		}
		
		/**
		 * set 角色id
		 */
		public function set id(value: long): void{
			this._id = value;
		}
		
		/**
		 * get 角色名称
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 角色名称
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 发起召回的时间
		 * @return 
		 */
		public function get time(): long{
			return _time;
		}
		
		/**
		 * set 发起召回的时间
		 */
		public function set time(value: long): void{
			this._time = value;
		}
		
		/**
		 * get 0:未回归 1:已回归
		 * @return 
		 */
		public function get back(): int{
			return _back;
		}
		
		/**
		 * set 0:未回归 1:已回归
		 */
		public function set back(value: int): void{
			this._back = value;
		}
		
	}
}