package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 婚宴时间
	 */
	public class ResWeddingTimeToClientMessage extends Message {
	
		//婚宴类型，1普通，2大型，3豪华
		private var _type: int;
		
		//月
		private var _month: int;
		
		//日
		private var _day: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//婚宴类型，1普通，2大型，3豪华
			writeByte(_type);
			//月
			writeByte(_month);
			//日
			writeByte(_day);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//婚宴类型，1普通，2大型，3豪华
			_type = readByte();
			//月
			_month = readByte();
			//日
			_day = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163122;
		}
		
		/**
		 * get 婚宴类型，1普通，2大型，3豪华
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 婚宴类型，1普通，2大型，3豪华
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 月
		 * @return 
		 */
		public function get month(): int{
			return _month;
		}
		
		/**
		 * set 月
		 */
		public function set month(value: int): void{
			this._month = value;
		}
		
		/**
		 * get 日
		 * @return 
		 */
		public function get day(): int{
			return _day;
		}
		
		/**
		 * set 日
		 */
		public function set day(value: int): void{
			this._day = value;
		}
		
	}
}