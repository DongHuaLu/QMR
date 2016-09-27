package com.game.epalace.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 返回前端骰子数值
	 */
	public class ResEpalaceDiceToClientMessage extends Message {
	
		//骰子数值
		private var _num: int;
		
		//已经移动次数
		private var _movenum: int;
		
		//恢复次数冷却时间
		private var _time: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//骰子数值
			writeByte(_num);
			//已经移动次数
			writeByte(_movenum);
			//恢复次数冷却时间
			writeInt(_time);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//骰子数值
			_num = readByte();
			//已经移动次数
			_movenum = readByte();
			//恢复次数冷却时间
			_time = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 143102;
		}
		
		/**
		 * get 骰子数值
		 * @return 
		 */
		public function get num(): int{
			return _num;
		}
		
		/**
		 * set 骰子数值
		 */
		public function set num(value: int): void{
			this._num = value;
		}
		
		/**
		 * get 已经移动次数
		 * @return 
		 */
		public function get movenum(): int{
			return _movenum;
		}
		
		/**
		 * set 已经移动次数
		 */
		public function set movenum(value: int): void{
			this._movenum = value;
		}
		
		/**
		 * get 恢复次数冷却时间
		 * @return 
		 */
		public function get time(): int{
			return _time;
		}
		
		/**
		 * set 恢复次数冷却时间
		 */
		public function set time(value: int): void{
			this._time = value;
		}
		
	}
}