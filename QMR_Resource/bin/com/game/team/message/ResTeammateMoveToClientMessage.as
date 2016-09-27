package com.game.team.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 队友移动XY，显示在小地图，返回给前端
	 */
	public class ResTeammateMoveToClientMessage extends Message {
	
		//队员ID
		private var _memberid: long;
		
		//队员所在坐标X
		private var _mx: int;
		
		//队员所在坐标Y
		private var _my: int;
		
		//类型：0移动，1首次坐标
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//队员ID
			writeLong(_memberid);
			//队员所在坐标X
			writeShort(_mx);
			//队员所在坐标Y
			writeShort(_my);
			//类型：0移动，1首次坐标
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//队员ID
			_memberid = readLong();
			//队员所在坐标X
			_mx = readShort();
			//队员所在坐标Y
			_my = readShort();
			//类型：0移动，1首次坐标
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 118112;
		}
		
		/**
		 * get 队员ID
		 * @return 
		 */
		public function get memberid(): long{
			return _memberid;
		}
		
		/**
		 * set 队员ID
		 */
		public function set memberid(value: long): void{
			this._memberid = value;
		}
		
		/**
		 * get 队员所在坐标X
		 * @return 
		 */
		public function get mx(): int{
			return _mx;
		}
		
		/**
		 * set 队员所在坐标X
		 */
		public function set mx(value: int): void{
			this._mx = value;
		}
		
		/**
		 * get 队员所在坐标Y
		 * @return 
		 */
		public function get my(): int{
			return _my;
		}
		
		/**
		 * set 队员所在坐标Y
		 */
		public function set my(value: int): void{
			this._my = value;
		}
		
		/**
		 * get 类型：0移动，1首次坐标
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 类型：0移动，1首次坐标
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}