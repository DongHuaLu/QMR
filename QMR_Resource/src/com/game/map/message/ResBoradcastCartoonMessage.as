package com.game.map.message{
	import com.game.map.bean.CartoonInfo;
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 播放动画
	 */
	public class ResBoradcastCartoonMessage extends Message {
	
		//动画信息
		private var _cartoon: CartoonInfo;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//动画信息
			writeBean(_cartoon);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//动画信息
			_cartoon = readBean(CartoonInfo) as CartoonInfo;
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 101137;
		}
		
		/**
		 * get 动画信息
		 * @return 
		 */
		public function get cartoon(): CartoonInfo{
			return _cartoon;
		}
		
		/**
		 * set 动画信息
		 */
		public function set cartoon(value: CartoonInfo): void{
			this._cartoon = value;
		}
		
	}
}