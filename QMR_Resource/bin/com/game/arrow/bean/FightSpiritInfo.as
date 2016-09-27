package com.game.arrow.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 战魂信息
	 */
	public class FightSpiritInfo extends Bean {
	
		//战魂类型 1日 2月 3金 4木 5水 6火 7土
		private var _type: int;
		
		//战魂数量
		private var _num: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//战魂类型 1日 2月 3金 4木 5水 6火 7土
			writeInt(_type);
			//战魂数量
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//战魂类型 1日 2月 3金 4木 5水 6火 7土
			_type = readInt();
			//战魂数量
			_num = readInt();
			return true;
		}
		
		/**
		 * get 战魂类型 1日 2月 3金 4木 5水 6火 7土
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 战魂类型 1日 2月 3金 4木 5水 6火 7土
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 战魂数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 战魂数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}