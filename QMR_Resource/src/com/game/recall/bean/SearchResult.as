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
	 * 查询结果
	 */
	public class SearchResult extends Bean {
	
		//角色id
		private var _id: long;
		
		//角色名称
		private var _name: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色id
			writeLong(_id);
			//角色名称
			writeString(_name);
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
		
	}
}