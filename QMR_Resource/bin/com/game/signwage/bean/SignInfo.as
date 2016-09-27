package com.game.signwage.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 签到信息
	 */
	public class SignInfo extends Bean {
	
		//当前年
		private var _year: int;
		
		//当前月
		private var _month: int;
		
		//当前天
		private var _day: int;
		
		//签到日期
		private var _daylist: Vector.<int> = new Vector.<int>();
		//签到奖励列表
		private var _award: Vector.<int> = new Vector.<int>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前年
			writeInt(_year);
			//当前月
			writeInt(_month);
			//当前天
			writeInt(_day);
			//签到日期
			writeShort(_daylist.length);
			for (var i: int = 0; i < _daylist.length; i++) {
				writeInt(_daylist[i]);
			}
			//签到奖励列表
			writeShort(_award.length);
			for (var i: int = 0; i < _award.length; i++) {
				writeInt(_award[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前年
			_year = readInt();
			//当前月
			_month = readInt();
			//当前天
			_day = readInt();
			//签到日期
			var daylist_length : int = readShort();
			for (var i: int = 0; i < daylist_length; i++) {
				_daylist[i] = readInt();
			}
			//签到奖励列表
			var award_length : int = readShort();
			for (var i: int = 0; i < award_length; i++) {
				_award[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get 当前年
		 * @return 
		 */
		public function get year(): int{
			return _year;
		}
		
		/**
		 * set 当前年
		 */
		public function set year(value: int): void{
			this._year = value;
		}
		
		/**
		 * get 当前月
		 * @return 
		 */
		public function get month(): int{
			return _month;
		}
		
		/**
		 * set 当前月
		 */
		public function set month(value: int): void{
			this._month = value;
		}
		
		/**
		 * get 当前天
		 * @return 
		 */
		public function get day(): int{
			return _day;
		}
		
		/**
		 * set 当前天
		 */
		public function set day(value: int): void{
			this._day = value;
		}
		
		/**
		 * get 签到日期
		 * @return 
		 */
		public function get daylist(): Vector.<int>{
			return _daylist;
		}
		
		/**
		 * set 签到日期
		 */
		public function set daylist(value: Vector.<int>): void{
			this._daylist = value;
		}
		
		/**
		 * get 签到奖励列表
		 * @return 
		 */
		public function get award(): Vector.<int>{
			return _award;
		}
		
		/**
		 * set 签到奖励列表
		 */
		public function set award(value: Vector.<int>): void{
			this._award = value;
		}
		
	}
}