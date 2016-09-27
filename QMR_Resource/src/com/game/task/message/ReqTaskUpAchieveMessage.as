package com.game.task.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 提升奖励
	 */
	public class ReqTaskUpAchieveMessage extends Message {
	
		//任务ID
		private var _taskId: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务ID
			writeInt(_taskId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务ID
			_taskId = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120203;
		}
		
		/**
		 * get 任务ID
		 * @return 
		 */
		public function get taskId(): int{
			return _taskId;
		}
		
		/**
		 * set 任务ID
		 */
		public function set taskId(value: int): void{
			this._taskId = value;
		}
		
	}
}