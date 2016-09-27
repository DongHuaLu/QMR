package com.game.task.message{
	import com.game.task.bean.ConquerTaskInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 讨伐任务变更
	 */
	public class ResConquerTaskChangeMessage extends Message {
	
		//当日讨伐接取次数
		private var _conquerTaskAcceptCount: int;
		
		//当日讨伐接取最大次数
		private var _conquerTaskAcceptMaxCount: int;
		
		//今日吞噬数
		private var _devourCount: int;
		
		//讨伐任务
		private var _task: ConquerTaskInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当日讨伐接取次数
			writeInt(_conquerTaskAcceptCount);
			//当日讨伐接取最大次数
			writeInt(_conquerTaskAcceptMaxCount);
			//今日吞噬数
			writeInt(_devourCount);
			//讨伐任务
			writeBean(_task);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当日讨伐接取次数
			_conquerTaskAcceptCount = readInt();
			//当日讨伐接取最大次数
			_conquerTaskAcceptMaxCount = readInt();
			//今日吞噬数
			_devourCount = readInt();
			//讨伐任务
			_task = readBean(ConquerTaskInfo) as ConquerTaskInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120106;
		}
		
		/**
		 * get 当日讨伐接取次数
		 * @return 
		 */
		public function get conquerTaskAcceptCount(): int{
			return _conquerTaskAcceptCount;
		}
		
		/**
		 * set 当日讨伐接取次数
		 */
		public function set conquerTaskAcceptCount(value: int): void{
			this._conquerTaskAcceptCount = value;
		}
		
		/**
		 * get 当日讨伐接取最大次数
		 * @return 
		 */
		public function get conquerTaskAcceptMaxCount(): int{
			return _conquerTaskAcceptMaxCount;
		}
		
		/**
		 * set 当日讨伐接取最大次数
		 */
		public function set conquerTaskAcceptMaxCount(value: int): void{
			this._conquerTaskAcceptMaxCount = value;
		}
		
		/**
		 * get 今日吞噬数
		 * @return 
		 */
		public function get devourCount(): int{
			return _devourCount;
		}
		
		/**
		 * set 今日吞噬数
		 */
		public function set devourCount(value: int): void{
			this._devourCount = value;
		}
		
		/**
		 * get 讨伐任务
		 * @return 
		 */
		public function get task(): ConquerTaskInfo{
			return _task;
		}
		
		/**
		 * set 讨伐任务
		 */
		public function set task(value: ConquerTaskInfo): void{
			this._task = value;
		}
		
	}
}