package com.game.task.bean{
	import com.game.task.bean.RankTaskAttribute;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 军功任务
	 */
	public class RankTaskInfo extends Bean {
	
		//任务模型
		private var _modelId: int;
		
		//任务追踪
		private var _permiseGoods: Vector.<RankTaskAttribute> = new Vector.<RankTaskAttribute>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务模型
			writeInt(_modelId);
			//任务追踪
			writeShort(_permiseGoods.length);
			for (var i: int = 0; i < _permiseGoods.length; i++) {
				writeBean(_permiseGoods[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务模型
			_modelId = readInt();
			//任务追踪
			var permiseGoods_length : int = readShort();
			for (var i: int = 0; i < permiseGoods_length; i++) {
				_permiseGoods[i] = readBean(RankTaskAttribute) as RankTaskAttribute;
			}
			return true;
		}
		
		/**
		 * get 任务模型
		 * @return 
		 */
		public function get modelId(): int{
			return _modelId;
		}
		
		/**
		 * set 任务模型
		 */
		public function set modelId(value: int): void{
			this._modelId = value;
		}
		
		/**
		 * get 任务追踪
		 * @return 
		 */
		public function get permiseGoods(): Vector.<RankTaskAttribute>{
			return _permiseGoods;
		}
		
		/**
		 * set 任务追踪
		 */
		public function set permiseGoods(value: Vector.<RankTaskAttribute>): void{
			this._permiseGoods = value;
		}
		
	}
}