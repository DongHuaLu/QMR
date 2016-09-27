package com.game.dianjiangchun.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 点绛唇保存信息
	 */
	public class DianjiangchunInfo extends Bean {
	
		//状态
		private var _status: int;
		
		//真气值
		private var _nInfuriatingvalue: int;
		
		//领取真气值
		private var _nReceiveintinfuriatingvalue: int;
		
		//使用次数
		private var _btUsecount: int;
		
		//最大使用次数
		private var _btMaxcount: int;
		
		//免费改运次数
		private var _btFreechangeluckcount: int;
		
		//免费改运最大次数
		private var _btFreechangeluckMaxcount: int;
		
		//色子信息列表
		private var _bosonList: Vector.<int> = new Vector.<int>();
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//状态
			writeInt(_status);
			//真气值
			writeInt(_nInfuriatingvalue);
			//领取真气值
			writeInt(_nReceiveintinfuriatingvalue);
			//使用次数
			writeByte(_btUsecount);
			//最大使用次数
			writeByte(_btMaxcount);
			//免费改运次数
			writeByte(_btFreechangeluckcount);
			//免费改运最大次数
			writeByte(_btFreechangeluckMaxcount);
			//色子信息列表
			writeShort(_bosonList.length);
			for (var i: int = 0; i < _bosonList.length; i++) {
				writeInt(_bosonList[i]);
			}
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//状态
			_status = readInt();
			//真气值
			_nInfuriatingvalue = readInt();
			//领取真气值
			_nReceiveintinfuriatingvalue = readInt();
			//使用次数
			_btUsecount = readByte();
			//最大使用次数
			_btMaxcount = readByte();
			//免费改运次数
			_btFreechangeluckcount = readByte();
			//免费改运最大次数
			_btFreechangeluckMaxcount = readByte();
			//色子信息列表
			var bosonList_length : int = readShort();
			for (var i: int = 0; i < bosonList_length; i++) {
				_bosonList[i] = readInt();
			}
			return true;
		}
		
		/**
		 * get 状态
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 状态
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
		/**
		 * get 真气值
		 * @return 
		 */
		public function get nInfuriatingvalue(): int{
			return _nInfuriatingvalue;
		}
		
		/**
		 * set 真气值
		 */
		public function set nInfuriatingvalue(value: int): void{
			this._nInfuriatingvalue = value;
		}
		
		/**
		 * get 领取真气值
		 * @return 
		 */
		public function get nReceiveintinfuriatingvalue(): int{
			return _nReceiveintinfuriatingvalue;
		}
		
		/**
		 * set 领取真气值
		 */
		public function set nReceiveintinfuriatingvalue(value: int): void{
			this._nReceiveintinfuriatingvalue = value;
		}
		
		/**
		 * get 使用次数
		 * @return 
		 */
		public function get btUsecount(): int{
			return _btUsecount;
		}
		
		/**
		 * set 使用次数
		 */
		public function set btUsecount(value: int): void{
			this._btUsecount = value;
		}
		
		/**
		 * get 最大使用次数
		 * @return 
		 */
		public function get btMaxcount(): int{
			return _btMaxcount;
		}
		
		/**
		 * set 最大使用次数
		 */
		public function set btMaxcount(value: int): void{
			this._btMaxcount = value;
		}
		
		/**
		 * get 免费改运次数
		 * @return 
		 */
		public function get btFreechangeluckcount(): int{
			return _btFreechangeluckcount;
		}
		
		/**
		 * set 免费改运次数
		 */
		public function set btFreechangeluckcount(value: int): void{
			this._btFreechangeluckcount = value;
		}
		
		/**
		 * get 免费改运最大次数
		 * @return 
		 */
		public function get btFreechangeluckMaxcount(): int{
			return _btFreechangeluckMaxcount;
		}
		
		/**
		 * set 免费改运最大次数
		 */
		public function set btFreechangeluckMaxcount(value: int): void{
			this._btFreechangeluckMaxcount = value;
		}
		
		/**
		 * get 色子信息列表
		 * @return 
		 */
		public function get bosonList(): Vector.<int>{
			return _bosonList;
		}
		
		/**
		 * set 色子信息列表
		 */
		public function set bosonList(value: Vector.<int>): void{
			this._bosonList = value;
		}
		
	}
}