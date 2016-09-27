package com.game.task.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 交付任务
	 */
	public class ReqTaskFinshMessage extends Message {
	
		//任务类型 主线 日常 讨伐
		private var _type: int;
		
		//讨伐任务ID
		private var _conquererTaskId: long;
		
		//任务模型
		private var _taskId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务类型 主线 日常 讨伐
			writeByte(_type);
			//讨伐任务ID
			writeLong(_conquererTaskId);
			//任务模型
			writeInt(_taskId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务类型 主线 日常 讨伐
			_type = readByte();
			//讨伐任务ID
			_conquererTaskId = readLong();
			//任务模型
			_taskId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120202;
		}
		
		/**
		 * get 任务类型 主线 日常 讨伐
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 任务类型 主线 日常 讨伐
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 讨伐任务ID
		 * @return 
		 */
		public function get conquererTaskId(): long{
			return _conquererTaskId;
		}
		
		/**
		 * set 讨伐任务ID
		 */
		public function set conquererTaskId(value: long): void{
			this._conquererTaskId = value;
		}
		
		/**
		 * get 任务模型
		 * @return 
		 */
		public function get taskId(): int{
			return _taskId;
		}
		
		/**
		 * set 任务模型
		 */
		public function set taskId(value: int): void{
			this._taskId = value;
		}
		
	}
}