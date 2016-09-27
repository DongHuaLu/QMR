package com.game.task.message{
	import com.game.task.bean.TreasureHuntTaskInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 寻宝任务变更
	 */
	public class ResTreasureHuntTaskChangeMessage extends Message {
	
		//寻宝任务
		private var _task: TreasureHuntTaskInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//寻宝任务
			writeBean(_task);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//寻宝任务
			_task = readBean(TreasureHuntTaskInfo) as TreasureHuntTaskInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120111;
		}
		
		/**
		 * get 寻宝任务
		 * @return 
		 */
		public function get task(): TreasureHuntTaskInfo{
			return _task;
		}
		
		/**
		 * set 寻宝任务
		 */
		public function set task(value: TreasureHuntTaskInfo): void{
			this._task = value;
		}
		
	}
}