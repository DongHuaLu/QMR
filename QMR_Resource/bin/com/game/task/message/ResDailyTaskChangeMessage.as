package com.game.task.message{
	import com.game.task.bean.DailyTaskInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 日常任务变更
	 */
	public class ResDailyTaskChangeMessage extends Message {
	
		//当日日常任务己接取次数
		private var _daylyTaskacceptcount: int;
		
		//日常任务
		private var _task: DailyTaskInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当日日常任务己接取次数
			writeInt(_daylyTaskacceptcount);
			//日常任务
			writeBean(_task);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当日日常任务己接取次数
			_daylyTaskacceptcount = readInt();
			//日常任务
			_task = readBean(DailyTaskInfo) as DailyTaskInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120105;
		}
		
		/**
		 * get 当日日常任务己接取次数
		 * @return 
		 */
		public function get daylyTaskacceptcount(): int{
			return _daylyTaskacceptcount;
		}
		
		/**
		 * set 当日日常任务己接取次数
		 */
		public function set daylyTaskacceptcount(value: int): void{
			this._daylyTaskacceptcount = value;
		}
		
		/**
		 * get 日常任务
		 * @return 
		 */
		public function get task(): DailyTaskInfo{
			return _task;
		}
		
		/**
		 * set 日常任务
		 */
		public function set task(value: DailyTaskInfo): void{
			this._task = value;
		}
		
	}
}