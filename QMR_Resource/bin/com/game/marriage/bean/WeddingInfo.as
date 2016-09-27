package com.game.marriage.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 婚宴信息
	 */
	public class WeddingInfo extends Bean {
	
		//结婚id
		private var _marriageid: long;
		
		//开始时间（秒）
		private var _time: int;
		
		//婚宴类型，1普通，2大型，3豪华
		private var _type: int;
		
		//婚姻状态，0未开始，1进行中，2结束
		private var _status: int;
		
		//新郎名字
		private var _bridegroom: String;
		
		//新娘名字
		private var _bride: String;
		
		//红包数量
		private var _rednum: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//结婚id
			writeLong(_marriageid);
			//开始时间（秒）
			writeInt(_time);
			//婚宴类型，1普通，2大型，3豪华
			writeByte(_type);
			//婚姻状态，0未开始，1进行中，2结束
			writeByte(_status);
			//新郎名字
			writeString(_bridegroom);
			//新娘名字
			writeString(_bride);
			//红包数量
			writeInt(_rednum);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//结婚id
			_marriageid = readLong();
			//开始时间（秒）
			_time = readInt();
			//婚宴类型，1普通，2大型，3豪华
			_type = readByte();
			//婚姻状态，0未开始，1进行中，2结束
			_status = readByte();
			//新郎名字
			_bridegroom = readString();
			//新娘名字
			_bride = readString();
			//红包数量
			_rednum = readInt();
			return true;
		}
		
		/**
		 * get 结婚id
		 * @return 
		 */
		public function get marriageid(): long{
			return _marriageid;
		}
		
		/**
		 * set 结婚id
		 */
		public function set marriageid(value: long): void{
			this._marriageid = value;
		}
		
		/**
		 * get 开始时间（秒）
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 开始时间（秒）
		 */
		public function set time(value: int): void{
			this._time = value;
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
		 * get 婚姻状态，0未开始，1进行中，2结束
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 婚姻状态，0未开始，1进行中，2结束
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
		/**
		 * get 新郎名字
		 * @return 
		 */
		public function get bridegroom(): String{
			return _bridegroom;
		}
		
		/**
		 * set 新郎名字
		 */
		public function set bridegroom(value: String): void{
			this._bridegroom = value;
		}
		
		/**
		 * get 新娘名字
		 * @return 
		 */
		public function get bride(): String{
			return _bride;
		}
		
		/**
		 * set 新娘名字
		 */
		public function set bride(value: String): void{
			this._bride = value;
		}
		
		/**
		 * get 红包数量
		 * @return 
		 */
		public function get rednum(): int{
			return _rednum;
		}
		
		/**
		 * set 红包数量
		 */
		public function set rednum(value: int): void{
			this._rednum = value;
		}
		
	}
}