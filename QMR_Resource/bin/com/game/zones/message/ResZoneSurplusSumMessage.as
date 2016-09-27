package com.game.zones.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 扫荡剩余总次数
	 */
	public class ResZoneSurplusSumMessage extends Message {
	
		//单人战役剩余次数
		private var _num: int;
		
		//七曜战将剩余次数
		private var _qiyaonum: int;
		
		//最近的可挑战七曜战将副本
		private var _qiyaozoneid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//单人战役剩余次数
			writeInt(_num);
			//七曜战将剩余次数
			writeInt(_qiyaonum);
			//最近的可挑战七曜战将副本
			writeInt(_qiyaozoneid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//单人战役剩余次数
			_num = readInt();
			//七曜战将剩余次数
			_qiyaonum = readInt();
			//最近的可挑战七曜战将副本
			_qiyaozoneid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 128113;
		}
		
		/**
		 * get 单人战役剩余次数
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 单人战役剩余次数
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 七曜战将剩余次数
		 * @return 
		 */
		public function get qiyaonum(): int{
			return _qiyaonum;
		}
		
		/**
		 * set 七曜战将剩余次数
		 */
		public function set qiyaonum(value: int): void{
			this._qiyaonum = value;
		}
		
		/**
		 * get 最近的可挑战七曜战将副本
		 * @return 
		 */
		public function get qiyaozoneid(): int{
			return _qiyaozoneid;
		}
		
		/**
		 * set 最近的可挑战七曜战将副本
		 */
		public function set qiyaozoneid(value: int): void{
			this._qiyaozoneid = value;
		}
		
	}
}