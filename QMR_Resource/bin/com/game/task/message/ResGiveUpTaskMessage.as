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
	 * 任务放弃
	 */
	public class ResGiveUpTaskMessage extends Message {
	
		//任务类型 0主线 1讨伐 2日常 3寻宝
		private var _type: int;
		
		//选择的任务ID
		private var _taskid: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务类型 0主线 1讨伐 2日常 3寻宝
			writeInt(_type);
			//选择的任务ID
			writeLong(_taskid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务类型 0主线 1讨伐 2日常 3寻宝
			_type = readInt();
			//选择的任务ID
			_taskid = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120112;
		}
		
		/**
		 * get 任务类型 0主线 1讨伐 2日常 3寻宝
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 任务类型 0主线 1讨伐 2日常 3寻宝
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 选择的任务ID
		 * @return 
		 */
		public function get taskid(): long{
			return _taskid;
		}
		
		/**
		 * set 选择的任务ID
		 */
		public function set taskid(value: long): void{
			this._taskid = value;
		}
		
	}
}