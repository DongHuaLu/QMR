package com.game.zones.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 多人副本展示信息
	 */
	public class ZoneTeamInfo extends Bean {
	
		//副本编号
		private var _zoneid: int;
		
		//次数用完后通关状态，1已参与，2已通关
		private var _clearancestatus: int;
		
		//进入次数
		private var _enternum: int;
		
		//0关闭，1开启
		private var _isopen: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//副本编号
			writeInt(_zoneid);
			//次数用完后通关状态，1已参与，2已通关
			writeByte(_clearancestatus);
			//进入次数
			writeInt(_enternum);
			//0关闭，1开启
			writeByte(_isopen);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//副本编号
			_zoneid = readInt();
			//次数用完后通关状态，1已参与，2已通关
			_clearancestatus = readByte();
			//进入次数
			_enternum = readInt();
			//0关闭，1开启
			_isopen = readByte();
			return true;
		}
		
		/**
		 * get 副本编号
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本编号
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
		/**
		 * get 次数用完后通关状态，1已参与，2已通关
		 * @return 
		 */
		public function get clearancestatus(): int{
			return _clearancestatus;
		}
		
		/**
		 * set 次数用完后通关状态，1已参与，2已通关
		 */
		public function set clearancestatus(value: int): void{
			this._clearancestatus = value;
		}
		
		/**
		 * get 进入次数
		 * @return 
		 */
		public function get enternum(): int{
			return _enternum;
		}
		
		/**
		 * set 进入次数
		 */
		public function set enternum(value: int): void{
			this._enternum = value;
		}
		
		/**
		 * get 0关闭，1开启
		 * @return 
		 */
		public function get isopen(): int{
			return _isopen;
		}
		
		/**
		 * set 0关闭，1开启
		 */
		public function set isopen(value: int): void{
			this._isopen = value;
		}
		
	}
}