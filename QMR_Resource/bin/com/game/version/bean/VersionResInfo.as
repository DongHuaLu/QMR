package com.game.version.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * RES文件版本信息
	 */
	public class VersionResInfo extends Bean {
	
		//版本号
		private var _version: int;
		
		//表名
		private var _tabname: String;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//版本号
			writeInt(_version);
			//表名
			writeString(_tabname);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//版本号
			_version = readInt();
			//表名
			_tabname = readString();
			return true;
		}
		
		/**
		 * get 版本号
		 * @return 
		 */
		public function get version(): int{
			return _version;
		}
		
		/**
		 * set 版本号
		 */
		public function set version(value: int): void{
			this._version = value;
		}
		
		/**
		 * get 表名
		 * @return 
		 */
		public function get tabname(): String{
			return _tabname;
		}
		
		/**
		 * set 表名
		 */
		public function set tabname(value: String): void{
			this._tabname = value;
		}
		
	}
}