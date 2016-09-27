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
	public class ReqPlayerCheckOnlineMessage extends Message {
	
		//玩家ID
		private var _othersid: long;
		
		//屏幕坐标x
		private var _x: int;
		
		//屏幕坐标y
		private var _y: int;
		
		//面板类型
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家ID
			writeLong(_othersid);
			//屏幕坐标x
			writeShort(_x);
			//屏幕坐标y
			writeShort(_y);
			//面板类型
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家ID
			_othersid = readLong();
			//屏幕坐标x
			_x = readShort();
			//屏幕坐标y
			_y = readShort();
			//面板类型
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 103210;
		}
		
		/**
		 * get 玩家ID
		 * @return 
		 */
		public function get othersid(): long{
			return _othersid;
		}
		
		/**
		 * set 玩家ID
		 */
		public function set othersid(value: long): void{
			this._othersid = value;
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
		
	}
}