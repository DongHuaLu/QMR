package com.game.task.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 军衔任务一键快速完成后所有军功值
	 */
	public class ResRankTaskQuickFinshAllMessage extends Message {
	
		//所有军功值
		private var _allrankexp: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//所有军功值
			writeInt(_allrankexp);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//所有军功值
			_allrankexp = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120117;
		}
		
		/**
		 * get 所有军功值
		 * @return 
		 */
		public function get allrankexp(): int{
			return _allrankexp;
		}
		
		/**
		 * set 所有军功值
		 */
		public function set allrankexp(value: int): void{
			this._allrankexp = value;
		}
		
	}
}