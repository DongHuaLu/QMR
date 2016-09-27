package com.game.horse.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 改变骑乘状态
	 */
	public class ReqChangeRidingStateMessage extends Message {
	
		//当前使用的坐骑阶层
		private var _curlayer: int;
		
		//是否骑乘，1骑乘，0未骑乘
		private var _status: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//当前使用的坐骑阶层
			writeShort(_curlayer);
			//是否骑乘，1骑乘，0未骑乘
			writeByte(_status);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//当前使用的坐骑阶层
			_curlayer = readShort();
			//是否骑乘，1骑乘，0未骑乘
			_status = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 126202;
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
		
		/**
		 * get 是否骑乘，1骑乘，0未骑乘
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 是否骑乘，1骑乘，0未骑乘
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
	}
}