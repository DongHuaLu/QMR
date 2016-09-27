package com.game.chat.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 按角色名查询  支持模糊
	 */
	public class RoleQueryMessage extends Message {
	
		//页码 从1开始
		private var _page: int;
		
		//角色名
		private var _roleLikeName: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//页码 从1开始
			writeInt(_page);
			//角色名
			writeString(_roleLikeName);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//页码 从1开始
			_page = readInt();
			//角色名
			_roleLikeName = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 111202;
		}
		
		/**
		 * get 页码 从1开始
		 * @return 
		 */
		public function get page(): int{
			return _page;
		}
		
		/**
		 * set 页码 从1开始
		 */
		public function set page(value: int): void{
			this._page = value;
		}
		
		/**
		 * get 角色名
		 * @return 
		 */
		public function get roleLikeName(): String{
			return _roleLikeName;
		}
		
		/**
		 * set 角色名
		 */
		public function set roleLikeName(value: String): void{
			this._roleLikeName = value;
		}
		
	}
}