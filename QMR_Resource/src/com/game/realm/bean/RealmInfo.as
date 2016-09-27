package com.game.realm.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 境界信息
	 */
	public class RealmInfo extends Bean {
	
		//境界等级
		private var _realmlevel: int;
		
		//境界强化等级
		private var _intensifylevel: int;
		
		//境界突破祝福值
		private var _blessingnum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//境界等级
			writeInt(_realmlevel);
			//境界强化等级
			writeInt(_intensifylevel);
			//境界突破祝福值
			writeInt(_blessingnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//境界等级
			_realmlevel = readInt();
			//境界强化等级
			_intensifylevel = readInt();
			//境界突破祝福值
			_blessingnum = readInt();
			return true;
		}
		
		/**
		 * get 境界等级
		 * @return 
		 */
		public function get realmlevel(): int{
			return _realmlevel;
		}
		
		/**
		 * set 境界等级
		 */
		public function set realmlevel(value: int): void{
			this._realmlevel = value;
		}
		
		/**
		 * get 境界强化等级
		 * @return 
		 */
		public function get intensifylevel(): int{
			return _intensifylevel;
		}
		
		/**
		 * set 境界强化等级
		 */
		public function set intensifylevel(value: int): void{
			this._intensifylevel = value;
		}
		
		/**
		 * get 境界突破祝福值
		 * @return 
		 */
		public function get blessingnum(): int{
			return _blessingnum;
		}
		
		/**
		 * set 境界突破祝福值
		 */
		public function set blessingnum(value: int): void{
			this._blessingnum = value;
		}
		
	}
}