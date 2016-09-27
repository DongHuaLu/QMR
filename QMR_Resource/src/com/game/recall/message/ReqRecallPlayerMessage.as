package com.game.recall.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 召回玩家
	 */
	public class ReqRecallPlayerMessage extends Message {
	
		//玩家名称
		private var _name: String;
		
		//0:系统召回 1:玩家召回
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//玩家名称
			writeString(_name);
			//0:系统召回 1:玩家召回
			writeByte(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//玩家名称
			_name = readString();
			//0:系统召回 1:玩家召回
			_type = readByte();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 168202;
		}
		
		/**
		 * get 玩家名称
		 * @return 
		 */
		public function get name(): String{
			return _name;
		}
		
		/**
		 * set 玩家名称
		 */
		public function set name(value: String): void{
			this._name = value;
		}
		
		/**
		 * get 0:系统召回 1:玩家召回
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 0:系统召回 1:玩家召回
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}