package com.game.maze.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 发送换层消息
	 */
	public class ResChangeFloorMessage extends Message {
	
		//换层类型 0-未换层， 1-成功 2-后退 3-最后一层
		private var _type: int;
		
		//现在层数
		private var _floor: int;
		
		//剩余层数
		private var _remain: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//换层类型 0-未换层， 1-成功 2-后退 3-最后一层
			writeInt(_type);
			//现在层数
			writeInt(_floor);
			//剩余层数
			writeInt(_remain);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//换层类型 0-未换层， 1-成功 2-后退 3-最后一层
			_type = readInt();
			//现在层数
			_floor = readInt();
			//剩余层数
			_remain = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 145101;
		}
		
		/**
		 * get 换层类型 0-未换层， 1-成功 2-后退 3-最后一层
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 换层类型 0-未换层， 1-成功 2-后退 3-最后一层
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
		/**
		 * get 现在层数
		 * @return 
		 */
		public function get floor(): int{
			return _floor;
		}
		
		/**
		 * set 现在层数
		 */
		public function set floor(value: int): void{
			this._floor = value;
		}
		
		/**
		 * get 剩余层数
		 * @return 
		 */
		public function get remain(): int{
			return _remain;
		}
		
		/**
		 * set 剩余层数
		 */
		public function set remain(value: int): void{
			this._remain = value;
		}
		
	}
}