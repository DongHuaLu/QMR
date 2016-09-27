package com.game.map.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 进入地图请求
	 */
	public class ReqEnterMapMessage extends Message {
	
		//屏幕宽度
		private var _width: int;
		
		//屏幕高度
		private var _height: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//屏幕宽度
			writeInt(_width);
			//屏幕高度
			writeInt(_height);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//屏幕宽度
			_width = readInt();
			//屏幕高度
			_height = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101208;
		}
		
		/**
		 * get 屏幕宽度
		 * @return 
		 */
		public function get width(): int{
			return _width;
		}
		
		/**
		 * set 屏幕宽度
		 */
		public function set width(value: int): void{
			this._width = value;
		}
		
		/**
		 * get 屏幕高度
		 * @return 
		 */
		public function get height(): int{
			return _height;
		}
		
		/**
		 * set 屏幕高度
		 */
		public function set height(value: int): void{
			this._height = value;
		}
		
	}
}