package com.game.arrow.message{
	import com.game.arrow.bean.BowInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回箭支信息
	 */
	public class ResBowInfoMessage extends Message {
	
		//通知类型
		private var _notifytype: int;
		
		//箭支信息
		private var _bowinfo: BowInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//通知类型
			writeInt(_notifytype);
			//箭支信息
			writeBean(_bowinfo);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//通知类型
			_notifytype = readInt();
			//箭支信息
			_bowinfo = readBean(BowInfo) as BowInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 151103;
		}
		
		/**
		 * get 通知类型
		 * @return 
		 */
		public function get notifytype(): int{
			return _notifytype;
		}
		
		/**
		 * set 通知类型
		 */
		public function set notifytype(value: int): void{
			this._notifytype = value;
		}
		
		/**
		 * get 箭支信息
		 * @return 
		 */
		public function get bowinfo(): BowInfo{
			return _bowinfo;
		}
		
		/**
		 * set 箭支信息
		 */
		public function set bowinfo(value: BowInfo): void{
			this._bowinfo = value;
		}
		
	}
}