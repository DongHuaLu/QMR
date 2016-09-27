package com.game.task.message{
	import com.game.task.bean.MainTaskInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 主线任务变更
	 */
	public class ResMainTaskChangeMessage extends Message {
	
		//主线任务
		private var _task: MainTaskInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//主线任务
			writeBean(_task);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//主线任务
			_task = readBean(MainTaskInfo) as MainTaskInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120104;
		}
		
		/**
		 * get 主线任务
		 * @return 
		 */
		public function get task(): MainTaskInfo{
			return _task;
		}
		
		/**
		 * set 主线任务
		 */
		public function set task(value: MainTaskInfo): void{
			this._task = value;
		}
		
	}
}