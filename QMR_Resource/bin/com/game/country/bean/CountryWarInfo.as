package com.game.country.bean{
	import com.game.utils.long;
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 攻城战即时信息
	 */
	public class CountryWarInfo extends Bean {
	
		//玉玺持有人帮会ID
		private var _holderguildid: long;
		
		//玉玺持有人ID
		private var _holderid: long;
		
		//玉玺持有人名字
		private var _holdername: String;
		
		//玉玺持有人坐标X
		private var _mx: int;
		
		//玉玺持有人坐标Y
		private var _my: int;
		
		//玉玺持有时间（秒）
		private var _holdertime: int;
		
		//攻城战结束剩余时间（秒）
		private var _warendtime: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玉玺持有人帮会ID
			writeLong(_holderguildid);
			//玉玺持有人ID
			writeLong(_holderid);
			//玉玺持有人名字
			writeString(_holdername);
			//玉玺持有人坐标X
			writeShort(_mx);
			//玉玺持有人坐标Y
			writeShort(_my);
			//玉玺持有时间（秒）
			writeInt(_holdertime);
			//攻城战结束剩余时间（秒）
			writeInt(_warendtime);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玉玺持有人帮会ID
			_holderguildid = readLong();
			//玉玺持有人ID
			_holderid = readLong();
			//玉玺持有人名字
			_holdername = readString();
			//玉玺持有人坐标X
			_mx = readShort();
			//玉玺持有人坐标Y
			_my = readShort();
			//玉玺持有时间（秒）
			_holdertime = readInt();
			//攻城战结束剩余时间（秒）
			_warendtime = readInt();
			return true;
		}
		
		/**
		 * get 玉玺持有人帮会ID
		 * @return 
		 */
		public function get holderguildid(): long{
			return _holderguildid;
		}
		
		/**
		 * set 玉玺持有人帮会ID
		 */
		public function set holderguildid(value: long): void{
			this._holderguildid = value;
		}
		
		/**
		 * get 玉玺持有人ID
		 * @return 
		 */
		public function get holderid(): long{
			return _holderid;
		}
		
		/**
		 * set 玉玺持有人ID
		 */
		public function set holderid(value: long): void{
			this._holderid = value;
		}
		
		/**
		 * get 玉玺持有人名字
		 * @return 
		 */
		public function get holdername(): String{
			return _holdername;
		}
		
		/**
		 * set 玉玺持有人名字
		 */
		public function set holdername(value: String): void{
			this._holdername = value;
		}
		
		/**
		 * get 玉玺持有人坐标X
		 * @return 
		 */
		public function get mx(): int{
			return _mx;
		}
		
		/**
		 * set 玉玺持有人坐标X
		 */
		public function set mx(value: int): void{
			this._mx = value;
		}
		
		/**
		 * get 玉玺持有人坐标Y
		 * @return 
		 */
		public function get my(): int{
			return _my;
		}
		
		/**
		 * set 玉玺持有人坐标Y
		 */
		public function set my(value: int): void{
			this._my = value;
		}
		
		/**
		 * get 玉玺持有时间（秒）
		 * @return 
		 */
		public function get holdertime(): int{
			return _holdertime;
		}
		
		/**
		 * set 玉玺持有时间（秒）
		 */
		public function set holdertime(value: int): void{
			this._holdertime = value;
		}
		
		/**
		 * get 攻城战结束剩余时间（秒）
		 * @return 
		 */
		public function get warendtime(): int{
			return _warendtime;
		}
		
		/**
		 * set 攻城战结束剩余时间（秒）
		 */
		public function set warendtime(value: int): void{
			this._warendtime = value;
		}
		
	}
}