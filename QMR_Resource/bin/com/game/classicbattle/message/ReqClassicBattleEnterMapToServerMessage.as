package com.game.classicbattle.message{
	import net.Message;
	
	/** 
	 * @author Commuication Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * 进入副本
	 */
	public class ReqClassicBattleEnterMapToServerMessage extends Message {
	
		//进入类型，0使用元宝，1使用铜币
		private var _type: int;
		
		
		/**
		 * 写入字节缓存
		 */
		override protected function writing(): Boolean{
			//进入类型，0使用元宝，1使用铜币
			writeInt(_type);
			return true;
		}
		
		/**
		 * 读取字节缓存
		 */
		override protected function reading(): Boolean{
			//进入类型，0使用元宝，1使用铜币
			_type = readInt();
			return true;
		}
		
		/**
		 * get id
		 * @return 
		 */
		override public function getId(): int {
			return 165204;
		}
		
		/**
		 * get 进入类型，0使用元宝，1使用铜币
		 * @return 
		 */
		public function get type(): int{
			return _type;
		}
		
		/**
		 * set 进入类型，0使用元宝，1使用铜币
		 */
		public function set type(value: int): void{
			this._type = value;
		}
		
	}
}