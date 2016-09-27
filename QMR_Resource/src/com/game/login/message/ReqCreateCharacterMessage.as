package com.game.login.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 创建角色
	 */
	public class ReqCreateCharacterMessage extends Message {
	
		//角色名字
		private var _name: String;
		
		//角色性别 1-男 2-女
		private var _sex: int;
		
		//是否自动生成
		private var _auto: int;
		
		//角色头像
		private var _icon: String;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//角色名字
			writeString(_name);
			//角色性别 1-男 2-女
			writeByte(_sex);
			//是否自动生成
			writeByte(_auto);
			//角色头像
			writeString(_icon);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//角色名字
			_name = readString();
			//角色性别 1-男 2-女
			_sex = readByte();
			//是否自动生成
			_auto = readByte();
			//角色头像
			_icon = readString();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 100203;
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
		 * get 角色性别 1-男 2-女
		 * @return 
		 */
		public function get sex(): int{
			return _sex;
		}
		
		/**
		 * set 角色性别 1-男 2-女
		 */
		public function set sex(value: int): void{
			this._sex = value;
		}
		
		/**
		 * get 是否自动生成
		 * @return 
		 */
		public function get auto(): int{
			return _auto;
		}
		
		/**
		 * set 是否自动生成
		 */
		public function set auto(value: int): void{
			this._auto = value;
		}
		
		/**
		 * get 角色头像
		 * @return 
		 */
		public function get icon(): String{
			return _icon;
		}
		
		/**
		 * set 角色头像
		 */
		public function set icon(value: String): void{
			this._icon = value;
		}
		
	}
}