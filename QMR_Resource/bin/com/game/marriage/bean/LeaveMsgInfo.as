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
	 * 留言信息
	 */
	public class LeaveMsgInfo extends Bean {
	
		//发布时间
		private var _time: int;
		
		//发布人名字
		private var _playername: String;
		
		//内容
		private var _content: String;
		
		//1未读取，0已经读取
		private var _alread: int;
		
		//当前留言索引ID
		private var _msgid: long;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//发布时间
			writeInt(_time);
			//发布人名字
			writeString(_playername);
			//内容
			writeString(_content);
			//1未读取，0已经读取
			writeByte(_alread);
			//当前留言索引ID
			writeLong(_msgid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//发布时间
			_time = readInt();
			//发布人名字
			_playername = readString();
			//内容
			_content = readString();
			//1未读取，0已经读取
			_alread = readByte();
			//当前留言索引ID
			_msgid = readLong();
			return true;
		}
		
		/**
		 * get 发布时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 发布时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
		/**
		 * get 发布人名字
		 * @return 
		 */
		public function get playername(): String{
			return _playername;
		}
		
		/**
		 * set 发布人名字
		 */
		public function set playername(value: String): void{
			this._playername = value;
		}
		
		/**
		 * get 内容
		 * @return 
		 */
		public function get content(): String{
			return _content;
		}
		
		/**
		 * set 内容
		 */
		public function set content(value: String): void{
			this._content = value;
		}
		
		/**
		 * get 1未读取，0已经读取
		 * @return 
		 */
		public function get alread(): int{
			return _alread;
		}
		
		/**
		 * set 1未读取，0已经读取
		 */
		public function set alread(value: int): void{
			this._alread = value;
		}
		
		/**
		 * get 当前留言索引ID
		 * @return 
		 */
		public function get msgid(): long{
			return _msgid;
		}
		
		/**
		 * set 当前留言索引ID
		 */
		public function set msgid(value: long): void{
			this._msgid = value;
		}
		
	}
}