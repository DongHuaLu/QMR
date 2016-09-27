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
	public class TaskAttribute extends Bean {
	
		//模型
		private var _model: int;
		
		//数量
		private var _num: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//模型
			writeInt(_model);
			//数量
			writeInt(_num);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//模型
			_model = readInt();
			//数量
			_num = readInt();
			return true;
		}
		
		/**
		 * get 模型
		 * @return 
		 */
		public function get model(): int{
			return _model;
		}
		
		/**
		 * set 模型
		 */
		public function set model(value: int): void{
			this._model = value;
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