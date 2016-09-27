package com.game.task.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 任务增加次数
	 */
	public class ResTaskGoldAddNumMessage extends Message {
	
		//任务类型
		private var _tasktype: int;
		
		//现在的最大次数
		private var _nowmaxnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务类型
			writeInt(_tasktype);
			//现在的最大次数
			writeInt(_nowmaxnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务类型
			_tasktype = readInt();
			//现在的最大次数
			_nowmaxnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120118;
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
		 * get 现在的最大次数
		 * @return 
		 */
		public function get nowmaxnum(): int{
			return _nowmaxnum;
		}
		
		/**
		 * set 现在的最大次数
		 */
		public function set nowmaxnum(value: int): void{
			this._nowmaxnum = value;
		}
		
	}
}