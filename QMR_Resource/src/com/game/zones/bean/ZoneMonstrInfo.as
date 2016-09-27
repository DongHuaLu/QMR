package com.game.zones.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 副本怪物信息
	 */
	public class ZoneMonstrInfo extends Bean {
	
		//怪物id
		private var _monstrmodid: int;
		
		//怪物数量
		private var _monstrnum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//怪物id
			writeInt(_monstrmodid);
			//怪物数量
			writeInt(_monstrnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//怪物id
			_monstrmodid = readInt();
			//怪物数量
			_monstrnum = readInt();
			return true;
		}
		
		/**
		 * get 怪物id
		 * @return 
		 */
		public function get monstrmodid(): int{
			return _monstrmodid;
		}
		
		/**
		 * set 怪物id
		 */
		public function set monstrmodid(value: int): void{
			this._monstrmodid = value;
		}
		
		/**
		 * get 怪物数量
		 * @return 
		 */
		public function get monstrnum(): int{
			return _monstrnum;
		}
		
		/**
		 * set 怪物数量
		 */
		public function set monstrnum(value: int): void{
			this._monstrnum = value;
		}
		
	}
}