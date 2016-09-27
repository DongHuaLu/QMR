package com.game.epalace.bean{
	import net.Bean;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 走路格子信息
	 */
	public class EpalaceInfo extends Bean {
	
		//前进方向，上右下左0246，没有就-1
		private var _direction: int;
		
		//当前位置
		private var _currentpos: int;
		
		//当前格子触发事件ID
		private var _eventid: int;
		
		//当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
		private var _forkdirection: int;
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//前进方向，上右下左0246，没有就-1
			writeByte(_direction);
			//当前位置
			writeByte(_currentpos);
			//当前格子触发事件ID
			writeInt(_eventid);
			//当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
			writeByte(_forkdirection);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//前进方向，上右下左0246，没有就-1
			_direction = readByte();
			//当前位置
			_currentpos = readByte();
			//当前格子触发事件ID
			_eventid = readInt();
			//当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
			_forkdirection = readByte();
			return true;
		}
		
		/**
		 * get 前进方向，上右下左0246，没有就-1
		 * @return 
		 */
		public function get direction(): int{
			return _direction;
		}
		
		/**
		 * set 前进方向，上右下左0246，没有就-1
		 */
		public function set direction(value: int): void{
			this._direction = value;
		}
		
		/**
		 * get 当前位置
		 * @return 
		 */
		public function get currentpos(): int{
			return _currentpos;
		}
		
		/**
		 * set 当前位置
		 */
		public function set currentpos(value: int): void{
			this._currentpos = value;
		}
		
		/**
		 * get 当前格子触发事件ID
		 * @return 
		 */
		public function get eventid(): int{
			return _eventid;
		}
		
		/**
		 * set 当前格子触发事件ID
		 */
		public function set eventid(value: int): void{
			this._eventid = value;
		}
		
		/**
		 * get 当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
		 * @return 
		 */
		public function get forkdirection(): int{
			return _forkdirection;
		}
		
		/**
		 * set 当前格子岔路选择方向（展示罗盘），上右下左0246，没有就-1，-2表示最后一格
		 */
		public function set forkdirection(value: int): void{
			this._forkdirection = value;
		}
		
	}
}