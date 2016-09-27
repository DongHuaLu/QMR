package com.game.horse.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 骑乘状态更换坐骑
	 */
	public class ReqChangeHorseMessage extends Message {
	
		//当前使用的坐骑阶层
		private var _curlayer: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前使用的坐骑阶层
			writeShort(_curlayer);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前使用的坐骑阶层
			_curlayer = readShort();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126211;
		}
		
		/**
		 * get 当前使用的坐骑阶层
		 * @return 
		 */
		public function get curlayer(): int{
			return _curlayer;
		}
		
		/**
		 * set 当前使用的坐骑阶层
		 */
		public function set curlayer(value: int): void{
			this._curlayer = value;
		}
		
	}
}