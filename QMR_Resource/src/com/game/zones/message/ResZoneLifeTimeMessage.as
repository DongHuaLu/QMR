package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 计时器显示，副本存在时间
	 */
	public class ResZoneLifeTimeMessage extends Message {
	
		//剩余时间（秒）
		private var _surplustime: int;
		
		//副本编号
		private var _zoneid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//剩余时间（秒）
			writeInt(_surplustime);
			//副本编号
			writeInt(_zoneid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//剩余时间（秒）
			_surplustime = readInt();
			//副本编号
			_zoneid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128110;
		}
		
		/**
		 * get 剩余时间（秒）
		 * @return 
		 */
		public function get surplustime(): int{
			return _surplustime;
		}
		
		/**
		 * set 剩余时间（秒）
		 */
		public function set surplustime(value: int): void{
			this._surplustime = value;
		}
		
		/**
		 * get 副本编号
		 * @return 
		 */
		public function get zoneid(): int{
			return _zoneid;
		}
		
		/**
		 * set 副本编号
		 */
		public function set zoneid(value: int): void{
			this._zoneid = value;
		}
		
	}
}