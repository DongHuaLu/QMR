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
	 * 讨伐任务快速完成
	 */
	public class ReqConquerTaskQuckFinshMessage extends Message {
	
		//任务ID
		private var _taskId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务ID
			writeLong(_taskId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务ID
			_taskId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120213;
		}
		
		/**
		 * get 任务ID
		 * @return 
		 */
		public function get taskId(): long{
			return _taskId;
		}
		
		/**
		 * set 任务ID
		 */
		public function set taskId(value: long): void{
			this._taskId = value;
		}
		
	}
}