package com.game.stalls.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 修改摊位名字
	 */
	public class ResChangeStallsNameMessage extends Message {
	
		//摊位名字
		private var _name: String;
		
		//修改结果，0成功1失败
		private var _status: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//摊位名字
			writeString(_name);
			//修改结果，0成功1失败
			writeByte(_status);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//摊位名字
			_name = readString();
			//修改结果，0成功1失败
			_status = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 123110;
		}
		
		/**
		 * get 摊位名字
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 摊位名字
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 修改结果，0成功1失败
		 * @return 
		 */
		public function get status(): int{
			return _status;
		}
		
		/**
		 * set 修改结果，0成功1失败
		 */
		public function set status(value: int): void{
			this._status = value;
		}
		
	}
}