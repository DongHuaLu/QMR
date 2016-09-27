package com.game.player.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 检查指定玩家是否在线
	 */
	public class ResPlayerCheckOnlineMessage extends Message {
	
		//他人ID
		private var _othersid: long;
		
		//他人名字
		private var _othersname: String;
		
		//屏幕坐标x
		private var _x: int;
		
		//屏幕坐标y
		private var _y: int;
		
		//面板类型
		private var _type: int;
		
		//0不在线，1在线
		private var _isonline: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//他人ID
			writeLong(_othersid);
			//他人名字
			writeString(_othersname);
			//屏幕坐标x
			writeShort(_x);
			//屏幕坐标y
			writeShort(_y);
			//面板类型
			writeByte(_type);
			//0不在线，1在线
			writeByte(_isonline);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//他人ID
			_othersid = readLong();
			//他人名字
			_othersname = readString();
			//屏幕坐标x
			_x = readShort();
			//屏幕坐标y
			_y = readShort();
			//面板类型
			_type = readByte();
			//0不在线，1在线
			_isonline = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103125;
		}
		
		/**
		 * get 他人ID
		 * @return 
		 */
		public function get othersid(): long{
			return _othersid;
		}
		
		/**
		 * set 他人ID
		 */
		public function set othersid(value: long): void{
			this._othersid = value;
		}
		
		/**
		 * get 他人名字
		 * @return 
		 */
		public function get othersname(): String{
			return _othersname;
		}
		
		/**
		 * set 他人名字
		 */
		public function set othersname(value: String): void{
			this._othersname = value;
		}
		
		/**
		 * get 屏幕坐标x
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 屏幕坐标x
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 屏幕坐标y
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 屏幕坐标y
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
		/**
		 * get 面板类型
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 面板类型
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 0不在线，1在线
		 * @return 
		 */
		public function get isonline(): int{
			return _isonline;
		}
		
		/**
		 * set 0不在线，1在线
		 */
		public function set isonline(value: int): void{
			this._isonline = value;
		}
		
	}
}