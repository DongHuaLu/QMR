package com.game.arrow.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 星斗信息
	 */
	public class StarInfo extends Bean {
	
		//星斗主等阶
		private var _starmainlv: int;
		
		//星斗子等阶
		private var _starsublv: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//星斗主等阶
			writeInt(_starmainlv);
			//星斗子等阶
			writeInt(_starsublv);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//星斗主等阶
			_starmainlv = readInt();
			//星斗子等阶
			_starsublv = readInt();
			return true;
		}
		
		/**
		 * get 星斗主等阶
		 * @return 
		 */
		public function get starmainlv(): int{
			return _starmainlv;
		}
		
		/**
		 * set 星斗主等阶
		 */
		public function set starmainlv(value: int): void{
			this._starmainlv = value;
		}
		
		/**
		 * get 星斗子等阶
		 * @return 
		 */
		public function get starsublv(): int{
			return _starsublv;
		}
		
		/**
		 * set 星斗子等阶
		 */
		public function set starsublv(value: int): void{
			this._starsublv = value;
		}
		
	}
}