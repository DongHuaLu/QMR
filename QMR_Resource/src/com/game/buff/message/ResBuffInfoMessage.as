package com.game.buff.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Buff信息
	 */
	public class ResBuffInfoMessage extends Message {
	
		//buff Id
		private var _buffId: long;
		
		//Buff 数值 主要用于血池
		private var _value: int;
		
		//Buff 剩余时间
		private var _remain: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//buff Id
			writeLong(_buffId);
			//Buff 数值 主要用于血池
			writeInt(_value);
			//Buff 剩余时间
			writeInt(_remain);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//buff Id
			_buffId = readLong();
			//Buff 数值 主要用于血池
			_value = readInt();
			//Buff 剩余时间
			_remain = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 113104;
		}
		
		/**
		 * get buff Id
		 * @return 
		 */
		public function get buffId(): long{
			return _buffId;
		}
		
		/**
		 * set buff Id
		 */
		public function set buffId(value: long): void{
			this._buffId = value;
		}
		
		/**
		 * get Buff 数值 主要用于血池
		 * @return 
		 */
		public function get value(): int{
			return _value;
		}
		
		/**
		 * set Buff 数值 主要用于血池
		 */
		public function set value(value: int): void{
			this._value = value;
		}
		
		/**
		 * get Buff 剩余时间
		 * @return 
		 */
		public function get remain(): int{
			return _remain;
		}
		
		/**
		 * set Buff 剩余时间
		 */
		public function set remain(value: int): void{
			this._remain = value;
		}
		
	}
}