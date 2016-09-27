package com.game.task.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 任务追踪
	 */
	public class RankTaskAttribute extends Bean {
	
		//任务类型
		private var _type: int;
		
		//数量
		private var _num: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务类型
			writeInt(_type);
			//数量
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务类型
			_type = readInt();
			//数量
			_num = readInt();
			return true;
		}
		
		/**
		 * get 任务类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 任务类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 数量
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 数量
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
	}
}