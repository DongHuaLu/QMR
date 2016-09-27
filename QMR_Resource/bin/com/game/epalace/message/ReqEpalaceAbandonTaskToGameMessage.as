package com.game.epalace.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 放弃任务
	 */
	public class ReqEpalaceAbandonTaskToGameMessage extends Message {
	
		//任务ID
		private var _taskid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务ID
			writeInt(_taskid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务ID
			_taskid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 143204;
		}
		
		/**
		 * get 任务ID
		 * @return 
		 */
		public function get taskid(): int{
			return _taskid;
		}
		
		/**
		 * set 任务ID
		 */
		public function set taskid(value: int): void{
			this._taskid = value;
		}
		
	}
}