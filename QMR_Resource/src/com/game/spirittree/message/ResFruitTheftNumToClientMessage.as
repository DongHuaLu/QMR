package com.game.spirittree.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 灵树抢摘次数信息
	 */
	public class ResFruitTheftNumToClientMessage extends Message {
	
		//抢摘次数
		private var _theftnum: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//抢摘次数
			writeInt(_theftnum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//抢摘次数
			_theftnum = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 133109;
		}
		
		/**
		 * get 抢摘次数
		 * @return 
		 */
		public function get theftnum(): int{
			return _theftnum;
		}
		
		/**
		 * set 抢摘次数
		 */
		public function set theftnum(value: int): void{
			this._theftnum = value;
		}
		
	}
}