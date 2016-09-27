package com.game.task.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 快速完成
	 */
	public class ReqQuickFinshMessage extends Message {
	
		//任务ID
		private var _taskId: int;
		
		//0快速完成  1最优快速完成
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务ID
			writeInt(_taskId);
			//0快速完成  1最优快速完成
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务ID
			_taskId = readInt();
			//0快速完成  1最优快速完成
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120206;
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
		
		/**
		 * get 0快速完成  1最优快速完成
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0快速完成  1最优快速完成
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}