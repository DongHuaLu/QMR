package com.game.task.message{
	import com.game.task.bean.RankTaskInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 军衔任务列表变更
	 */
	public class ResRankTaskChangeMessage extends Message {
	
		//军衔任务变更
		private var _taskInfo: RankTaskInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//军衔任务变更
			writeBean(_taskInfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//军衔任务变更
			_taskInfo = readBean(RankTaskInfo) as RankTaskInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120115;
		}
		
		/**
		 * get 军衔任务变更
		 * @return 
		 */
		public function get taskInfo(): RankTaskInfo{
			return _taskInfo;
		}
		
		/**
		 * set 军衔任务变更
		 */
		public function set taskInfo(value: RankTaskInfo): void{
			this._taskInfo = value;
		}
		
	}
}