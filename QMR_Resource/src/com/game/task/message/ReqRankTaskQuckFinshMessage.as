package com.game.task.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 军衔任务快速完成
	 */
	public class ReqRankTaskQuckFinshMessage extends Message {
	
		//任务模型
		private var _modelId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务模型
			writeInt(_modelId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务模型
			_modelId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120214;
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
		
	}
}