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
	 * 讨伐任务吞噬
	 */
	public class ResConquerTaskAnnexMessage extends Message {
	
		//被吞噬的任务(要消失)
		private var _vanishTaskId: long;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//被吞噬的任务(要消失)
			writeLong(_vanishTaskId);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//被吞噬的任务(要消失)
			_vanishTaskId = readLong();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120107;
		}
		
		/**
		 * get 被吞噬的任务(要消失)
		 * @return 
		 */
		public function get vanishTaskId(): long{
			return _vanishTaskId;
		}
		
		/**
		 * set 被吞噬的任务(要消失)
		 */
		public function set vanishTaskId(value: long): void{
			this._vanishTaskId = value;
		}
		
	}
}