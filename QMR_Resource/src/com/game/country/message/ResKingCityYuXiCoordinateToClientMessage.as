package com.game.country.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 玉玺坐标
	 */
	public class ResKingCityYuXiCoordinateToClientMessage extends Message {
	
		//玉玺持有人坐标X
		private var _mx: int;
		
		//玉玺持有人坐标Y
		private var _my: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玉玺持有人坐标X
			writeShort(_mx);
			//玉玺持有人坐标Y
			writeShort(_my);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玉玺持有人坐标X
			_mx = readShort();
			//玉玺持有人坐标Y
			_my = readShort();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 146111;
		}
		
		/**
		 * get 玉玺持有人坐标X
		 * @return 
		 */
		public function get mx(): int{
			return _mx;
		}
		
		/**
		 * set 玉玺持有人坐标X
		 */
		public function set mx(value: int): void{
			this._mx = value;
		}
		
		/**
		 * get 玉玺持有人坐标Y
		 * @return 
		 */
		public function get my(): int{
			return _my;
		}
		
		/**
		 * set 玉玺持有人坐标Y
		 */
		public function set my(value: int): void{
			this._my = value;
		}
		
	}
}