package com.game.marriage.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 婚姻信息
	 */
	public class ResMarriageinfoToClientMessage extends Message {
	
		//配偶ID
		private var _spouseid: long;
		
		//配偶名字
		private var _spousename: String;
		
		//婚姻唯一ID
		private var _marriageid: long;
		
		//结婚时间（秒）
		private var _time: int;
		
		//戒指模版ID
		private var _ringmodelid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//配偶ID
			writeLong(_spouseid);
			//配偶名字
			writeString(_spousename);
			//婚姻唯一ID
			writeLong(_marriageid);
			//结婚时间（秒）
			writeInt(_time);
			//戒指模版ID
			writeInt(_ringmodelid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//配偶ID
			_spouseid = readLong();
			//配偶名字
			_spousename = readString();
			//婚姻唯一ID
			_marriageid = readLong();
			//结婚时间（秒）
			_time = readInt();
			//戒指模版ID
			_ringmodelid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163114;
		}
		
		/**
		 * get 配偶ID
		 * @return 
		 */
		public function get spouseid(): long{
			return _spouseid;
		}
		
		/**
		 * set 配偶ID
		 */
		public function set spouseid(value: long): void{
			this._spouseid = value;
		}
		
		/**
		 * get 配偶名字
		 * @return 
		 */
		public function get spousename(): String{
			return _spousename;
		}
		
		/**
		 * set 配偶名字
		 */
		public function set spousename(value: String): void{
			this._spousename = value;
		}
		
		/**
		 * get 婚姻唯一ID
		 * @return 
		 */
		public function get marriageid(): long{
			return _marriageid;
		}
		
		/**
		 * set 婚姻唯一ID
		 */
		public function set marriageid(value: long): void{
			this._marriageid = value;
		}
		
		/**
		 * get 结婚时间（秒）
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 结婚时间（秒）
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 戒指模版ID
		 * @return 
		 */
		public function get ringmodelid(): int{
			return _ringmodelid;
		}
		
		/**
		 * set 戒指模版ID
		 */
		public function set ringmodelid(value: int): void{
			this._ringmodelid = value;
		}
		
	}
}