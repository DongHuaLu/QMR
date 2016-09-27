package com.game.task.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 军衔任务完成
	 */
	public class ResRankTaskFinshMessage extends Message {
	
		//任务模型
		private var _modelId: int;
		
		//任务完成类型   0普通完成 1一键完成 
		private var _finshType: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务模型
			writeInt(_modelId);
			//任务完成类型   0普通完成 1一键完成 
			writeInt(_finshType);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务模型
			_modelId = readInt();
			//任务完成类型   0普通完成 1一键完成 
			_finshType = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120116;
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
		 * get 任务完成类型   0普通完成 1一键完成 
		 * @return 
		 */
		public function get finshType(): int{
			return _finshType;
		}
		
		/**
		 * set 任务完成类型   0普通完成 1一键完成 
		 */
		public function set finshType(value: int): void{
			this._finshType = value;
		}
		
	}
}