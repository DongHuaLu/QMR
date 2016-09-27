package com.game.task.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 任务通过元宝增加次数上限
	 */
	public class ReqTaskGoldAddNumMessage extends Message {
	
		//任务类型
		private var _tasktype: int;
		
		//增加次数
		private var _addnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务类型
			writeInt(_tasktype);
			//增加次数
			writeInt(_addnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务类型
			_tasktype = readInt();
			//增加次数
			_addnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120216;
		}
		
		/**
		 * get 任务类型
		 * @return 
		 */
		public function get tasktype(): int{
			return _tasktype;
		}
		
		/**
		 * set 任务类型
		 */
		public function set tasktype(value: int): void{
			this._tasktype = value;
		}
		
		/**
		 * get 增加次数
		 * @return 
		 */
		public function get addnum(): int{
			return _addnum;
		}
		
		/**
		 * set 增加次数
		 */
		public function set addnum(value: int): void{
			this._addnum = value;
		}
		
	}
}