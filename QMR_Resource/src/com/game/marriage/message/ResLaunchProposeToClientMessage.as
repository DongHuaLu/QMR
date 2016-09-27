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
	 * 得到求婚对象信息
	 */
	public class ResLaunchProposeToClientMessage extends Message {
	
		//求婚对象ID
		private var _suitorobjid: long;
		
		//求婚对象名字
		private var _suitorobjname: String;
		
		//求婚对象等级
		private var _suitorobjlv: int;
		
		//求婚对象帮会名字
		private var _guildname: String;
		
		//求婚对象头像
		private var _avatarid: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//求婚对象ID
			writeLong(_suitorobjid);
			//求婚对象名字
			writeString(_suitorobjname);
			//求婚对象等级
			writeShort(_suitorobjlv);
			//求婚对象帮会名字
			writeString(_guildname);
			//求婚对象头像
			writeInt(_avatarid);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//求婚对象ID
			_suitorobjid = readLong();
			//求婚对象名字
			_suitorobjname = readString();
			//求婚对象等级
			_suitorobjlv = readShort();
			//求婚对象帮会名字
			_guildname = readString();
			//求婚对象头像
			_avatarid = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163101;
		}
		
		/**
		 * get 求婚对象ID
		 * @return 
		 */
		public function get suitorobjid(): long{
			return _suitorobjid;
		}
		
		/**
		 * set 求婚对象ID
		 */
		public function set suitorobjid(value: long): void{
			this._suitorobjid = value;
		}
		
		/**
		 * get 求婚对象名字
		 * @return 
		 */
		public function get suitorobjname(): String{
			return _suitorobjname;
		}
		
		/**
		 * set 求婚对象名字
		 */
		public function set suitorobjname(value: String): void{
			this._suitorobjname = value;
		}
		
		/**
		 * get 求婚对象等级
		 * @return 
		 */
		public function get suitorobjlv(): int{
			return _suitorobjlv;
		}
		
		/**
		 * set 求婚对象等级
		 */
		public function set suitorobjlv(value: int): void{
			this._suitorobjlv = value;
		}
		
		/**
		 * get 求婚对象帮会名字
		 * @return 
		 */
		public function get guildname(): String{
			return _guildname;
		}
		
		/**
		 * set 求婚对象帮会名字
		 */
		public function set guildname(value: String): void{
			this._guildname = value;
		}
		
		/**
		 * get 求婚对象头像
		 * @return 
		 */
		public function get avatarid(): int{
			return _avatarid;
		}
		
		/**
		 * set 求婚对象头像
		 */
		public function set avatarid(value: int): void{
			this._avatarid = value;
		}
		
	}
}