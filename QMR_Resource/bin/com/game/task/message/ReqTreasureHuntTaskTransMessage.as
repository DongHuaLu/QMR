package com.game.task.message{
	import com.game.utils.long;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 讨伐任务传送
	 */
	public class ReqTreasureHuntTaskTransMessage extends Message {
	
		//任务ID
		private var _taskId: long;
		
		//目标地图ID
		private var _mapid: int;
		
		//目标x
		private var _x: int;
		
		//目标y
		private var _y: int;
		
		//目标line
		private var _line: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//任务ID
			writeLong(_taskId);
			//目标地图ID
			writeInt(_mapid);
			//目标x
			writeInt(_x);
			//目标y
			writeInt(_y);
			//目标line
			writeInt(_line);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//任务ID
			_taskId = readLong();
			//目标地图ID
			_mapid = readInt();
			//目标x
			_x = readInt();
			//目标y
			_y = readInt();
			//目标line
			_line = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 120212;
		}
		
		/**
		 * get 任务ID
		 * @return 
		 */
		public function get taskId(): long{
			return _taskId;
		}
		
		/**
		 * set 任务ID
		 */
		public function set taskId(value: long): void{
			this._taskId = value;
		}
		
		/**
		 * get 目标地图ID
		 * @return 
		 */
		public function get mapid(): int{
			return _mapid;
		}
		
		/**
		 * set 目标地图ID
		 */
		public function set mapid(value: int): void{
			this._mapid = value;
		}
		
		/**
		 * get 目标x
		 * @return 
		 */
		public function get x(): int{
			return _x;
		}
		
		/**
		 * set 目标x
		 */
		public function set x(value: int): void{
			this._x = value;
		}
		
		/**
		 * get 目标y
		 * @return 
		 */
		public function get y(): int{
			return _y;
		}
		
		/**
		 * set 目标y
		 */
		public function set y(value: int): void{
			this._y = value;
		}
		
		/**
		 * get 目标line
		 * @return 
		 */
		public function get line(): int{
			return _line;
		}
		
		/**
		 * set 目标line
		 */
		public function set line(value: int): void{
			this._line = value;
		}
		
	}
}