package com.game.task.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 领取任务
	 */
	public class ReqTaskReceiveMessage extends Message {
	
		//任务类型 主线 日常 （讨伐使用卷轴完成领用）
		private var _type: int;
		
		//任务模型
		private var _modelId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务类型 主线 日常 （讨伐使用卷轴完成领用）
			writeByte(_type);
			//任务模型
			writeInt(_modelId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务类型 主线 日常 （讨伐使用卷轴完成领用）
			_type = readByte();
			//任务模型
			_modelId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120201;
		}
		
		/**
		 * get 任务类型 主线 日常 （讨伐使用卷轴完成领用）
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 任务类型 主线 日常 （讨伐使用卷轴完成领用）
		 */
		public function set type(value: int): void{
			this._type = value;
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