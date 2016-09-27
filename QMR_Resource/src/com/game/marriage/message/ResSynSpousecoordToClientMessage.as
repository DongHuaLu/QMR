package com.game.marriage.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 同地图同步配偶坐标
	 */
	public class ResSynSpousecoordToClientMessage extends Message {
	
		//所在坐标X
		private var _mx: int;
		
		//所在坐标Y
		private var _my: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//所在坐标X
			writeShort(_mx);
			//所在坐标Y
			writeShort(_my);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//所在坐标X
			_mx = readShort();
			//所在坐标Y
			_my = readShort();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 163123;
		}
		
		/**
		 * get 所在坐标X
		 * @return 
		 */
		public function get mx(): int{
			return _mx;
		}
		
		/**
		 * set 所在坐标X
		 */
		public function set mx(value: int): void{
			this._mx = value;
		}
		
		/**
		 * get 所在坐标Y
		 * @return 
		 */
		public function get my(): int{
			return _my;
		}
		
		/**
		 * set 所在坐标Y
		 */
		public function set my(value: int): void{
			this._my = value;
		}
		
	}
}