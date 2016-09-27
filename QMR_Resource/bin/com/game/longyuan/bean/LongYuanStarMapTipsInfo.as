package com.game.longyuan.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 龙元心法星图Tips信息
	 */
	public class LongYuanStarMapTipsInfo extends Bean {
	
		//当前的龙元心法阶段（星图）
		private var _longyuanactlv: int;
		
		//是否已经激活
		private var _isachieve: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前的龙元心法阶段（星图）
			writeByte(_longyuanactlv);
			//是否已经激活
			writeByte(_isachieve);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前的龙元心法阶段（星图）
			_longyuanactlv = readByte();
			//是否已经激活
			_isachieve = readByte();
			return true;
		}
		
		/**
		 * get 当前的龙元心法阶段（星图）
		 * @return 
		 */
		public function get longyuanactlv(): int{
			return _longyuanactlv;
		}
		
		/**
		 * set 当前的龙元心法阶段（星图）
		 */
		public function set longyuanactlv(value: int): void{
			this._longyuanactlv = value;
		}
		
		/**
		 * get 是否已经激活
		 * @return 
		 */
		public function get isachieve(): int{
			return _isachieve;
		}
		
		/**
		 * set 是否已经激活
		 */
		public function set isachieve(value: int): void{
			this._isachieve = value;
		}
		
	}
}